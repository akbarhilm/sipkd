package spp.ui.action;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.SppUp;
import spp.services.CetakReportServices;
import spp.services.CetakValidasiSPPServices;
import spp.util.SipkdHelpers;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/cetakspp")
public class CetakSppAction {

    private static final Logger log = LoggerFactory.getLogger(CetakSppAction.class);
    @Autowired
    CetakValidasiSPPServices cetakValidasiSPPServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    CetakReportServices cetakReportServices;

    @RequestMapping(value = "/indexspppup", method = RequestMethod.GET)
    public String index(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        return "cetak/spp/index";

    }

    @RequestMapping(value = "/json/getlistspppcetak", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspppup(final HttpServletRequest request) {

        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("namaskpd");
        final String idskpd = request.getParameter("idskpd");
        final Map< String, Object> param = new HashMap<String, Object>(2);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String addoredit = request.getParameter("addoredit");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("namaskpd", skpd);
        param.put("idskpd", SipkdHelpers.getIntFromString(idskpd));
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = cetakValidasiSPPServices.getbanyaksppcetak(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", cetakValidasiSPPServices.getlistsppcetak(param));
        return mapData;
    }

    @RequestMapping(value = "/json/insertsppcetak", method = RequestMethod.POST)
    public @ResponseBody
    String submitcetak(@RequestBody List<Map<String, Object>> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
       /* final String pathjasper = servletContext.getRealPath("/WEB-INF/report/");
        final String pathpdf = servletContext.getRealPath("/WEB-INF/pdf/");*/
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String pathjasper = servletContext.getInitParameter("PATH_REPORT");
        final String pathpdf = servletContext.getInitParameter("PATH_PDF");
        log.debug("==== PDF 0===> "+pathpdf+"Tahun Angg = "+tahunAnggaran);
        final String spp = "/SPP/";
        boolean hasil = false;
        boolean hsl = false;
        String dirname = tahunAnggaran;
        File dir = new File(pathpdf + dirname + spp);
        dir.mkdir();
         final String pdf = FilenameUtils.separatorsToUnix(dir.getAbsolutePath());// dirspd+"\\";
         final String pdfspp = pdf + "/";
        log.debug("==== PDF 3===> " + pdf);
        for (Map<String, Object> mapdetil : mapdata) {
            mapdetil.put("userid", pengguna.getIdPengguna());
            mapdetil.put("filejasper", pathjasper);
            mapdetil.put("filepdf", pdfspp);
           // mapdetil.put("tahun", tahunAnggaran);
            mapdetil.put("tglentry", new Timestamp(System.currentTimeMillis()));
 log.debug("==== Parameter===> " + mapdetil);
            cetakValidasiSPPServices.insertsppcetak(mapdetil);
            //cetakValidasiSPPServices.updateSppBtt(mapdetil);
        }

        return "Simpan Data Cetak SPP Sukses";

    }
    
    
     @RequestMapping(value = "/json/hapussppcetak",
            method = RequestMethod.POST)
    public @ResponseBody
    String hapussppcetak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        cetakValidasiSPPServices.deletesppcetak((Integer) mapdata.get("idspd"));
        return "Data SPP No " + mapdata.get("nospp") + " berhasil dibatalkan  ";
    }
    

   /* @RequestMapping(value = "/json/hapussppcetak",
            method = RequestMethod.POST)
    public @ResponseBody
    String hapussppcetak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) throws IOException {
        final String pathhistory = servletContext.getInitParameter("PATH_HISTORY");
        final String tahun= (String) request.getSession().getAttribute("tahunAnggaran");
        final String namaFile = (String) mapdata.get("namafile");
        final String nospp = (String) mapdata.get("nospp");
        int idspp = (int) mapdata.get("idspd");
        final String spp = "/SPP/";
        boolean hasil = false;
        boolean hsl = false;
        String dirname = tahun;
        File dir = new File(pathhistory + dirname + spp);
        dir.mkdir();
         final String his = FilenameUtils.separatorsToUnix(dir.getAbsolutePath());
         final String hisspp = his + "/";
        final Map< String, Object> param = new HashMap<String, Object>(2);
        log.debug("Parameter Delete 1 ===> "+pathhistory+"--"+tahun+"--"+namaFile+" === "+nospp);
        param.put("tahun", tahun);
        param.put("nospp", nospp);
        param.put("value", idspp);
        List<SppUp> listSpp = cetakValidasiSPPServices.getPathFile(param);
        for (int i = 0; i < listSpp.size(); i++) {
                SppUp get = listSpp.get(i);
                String pathPdf = listSpp.get(i).getAlamatPdfOutput();
                String jenis = listSpp.get(i).getKodeJenis();
                String beban = listSpp.get(i).getKodeBeban();
                String level = listSpp.get(i).getLevelSkpd();
                final String lokasiDanNamaPdfAsal =  pathPdf+namaFile;
                final String lokasiDanNamaPdfTujuan =  hisspp+namaFile;
                log.debug("Parameter Delete 2 ===> "+pathPdf+"--"+jenis+"--"+lokasiDanNamaPdfAsal+"--"+lokasiDanNamaPdfTujuan);
                final int byk = cetakValidasiSPPServices.getbanyaksppcetakbtl3(param);
                final int byk1 = cetakValidasiSPPServices.getbanyaksppcetakbtl4(param);
             if (level.equals("3")) {
                if (beban.equals("LS")) {

                    if ((jenis.equals("1"))) {

                        if (byk > 0)
                        {
                             File asal = new File(lokasiDanNamaPdfAsal);
                            File tujuan = new File(lokasiDanNamaPdfTujuan);
                            if ( tujuan.exists() )
                            {
                                Path asl = Paths.get(pathPdf+namaFile);
                                Path tjn = Paths.get(hisspp+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SPP Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SPP Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPP Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
                                }
                               
                                
                                
                            }
                        }else if (byk1 > 0) 
                        {
                            File asal = new File(lokasiDanNamaPdfAsal);
                            File tujuan = new File(lokasiDanNamaPdfTujuan);
                            if ( tujuan.exists() )
                            {
                                Path asl = Paths.get(pathPdf+namaFile);
                                Path tjn = Paths.get(hisspp+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SPP Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SPP Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPP Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
                                }
                               
                                
                                
                            }
                            
                        }

                    } else if ((jenis.equals("4"))) {

                        File asal = new File(lokasiDanNamaPdfAsal);
                            File tujuan = new File(lokasiDanNamaPdfTujuan);
                            if ( tujuan.exists() )
                            {
                                Path asl = Paths.get(pathPdf+namaFile);
                                Path tjn = Paths.get(hisspp+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SPP Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SPP Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPP Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
                                }
                               
                                
                                
                            }

                    } else if ((jenis.equals("2"))) {

                        File asal = new File(lokasiDanNamaPdfAsal);
                            File tujuan = new File(lokasiDanNamaPdfTujuan);
                            if ( tujuan.exists() )
                            {
                                Path asl = Paths.get(pathPdf+namaFile);
                                Path tjn = Paths.get(hisspp+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SPP Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SPP Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPP Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
                                }
                               
                                
                                
                            }

                    } 
                    
                }

            } else {
                 if ((jenis.equals("5"))) {
                        File asal = new File(lokasiDanNamaPdfAsal);
                            File tujuan = new File(lokasiDanNamaPdfTujuan);
                            if ( tujuan.exists() )
                            {
                                Path asl = Paths.get(pathPdf+namaFile);
                                Path tjn = Paths.get(hisspp+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SPP Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SPP Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPP Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
                                }
                               
                                
                                
                            }

                    } else  if ((jenis.equals("1"))) {

                    if (beban.equals("LS")) {
                        File asal = new File(lokasiDanNamaPdfAsal);
                            File tujuan = new File(lokasiDanNamaPdfTujuan);
                            if ( tujuan.exists() )
                            {
                                Path asl = Paths.get(pathPdf+namaFile);
                                Path tjn = Paths.get(hisspp+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SPP Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SPP Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPP Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
                                }
                               
                                
                                
                            }
                    }

                } else if (jenis.equals("3")) {

                    if (beban.equals("UP")) {

                        File asal = new File(lokasiDanNamaPdfAsal);
                            File tujuan = new File(lokasiDanNamaPdfTujuan);
                            if ( tujuan.exists() )
                            {
                                Path asl = Paths.get(pathPdf+namaFile);
                                Path tjn = Paths.get(hisspp+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SPP Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SPP Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPP Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
                                }
                               
                                
                                
                            }
                    } else if (beban.equals("TU")) {
                        File asal = new File(lokasiDanNamaPdfAsal);
                            File tujuan = new File(lokasiDanNamaPdfTujuan);
                            if ( tujuan.exists() )
                            {
                                Path asl = Paths.get(pathPdf+namaFile);
                                Path tjn = Paths.get(hisspp+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SPP Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SPP Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPP Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
                                }
                               
                                
                                
                            }
                    } else if (beban.equals("GU")) {

                        File asal = new File(lokasiDanNamaPdfAsal);
                            File tujuan = new File(lokasiDanNamaPdfTujuan);
                            if ( tujuan.exists() )
                            {
                                Path asl = Paths.get(pathPdf+namaFile);
                                Path tjn = Paths.get(hisspp+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SPP Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SPP Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPP Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
                                }
                               
                                
                                
                            }
                    } else if (beban.equals("LS")) {

                       File asal = new File(lokasiDanNamaPdfAsal);
                            File tujuan = new File(lokasiDanNamaPdfTujuan);
                            if ( tujuan.exists() )
                            {
                                Path asl = Paths.get(pathPdf+namaFile);
                                Path tjn = Paths.get(hisspp+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SPP Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SPP Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPP Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
                                }
                               
                                
                                
                            }

                    }

                }

            }   
                
        }        
        cetakValidasiSPPServices.deletesppcetak((Integer) mapdata.get("idspd"));
        return "Data SPP No " + mapdata.get("nospp") + " berhasil dibatalkan  ";
    }
    
    */

    /**
     * ******
     *
     */
    @RequestMapping(value = "/{kodeBeban}/{kodeJenis}/{id}/{nospp}/{iskpd}/{lskpd}/{namafile}", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response, @PathVariable String kodeBeban, @PathVariable String kodeJenis, @PathVariable Integer id, @PathVariable String nospp, @PathVariable String iskpd, @PathVariable String lskpd, @PathVariable String namafile) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        try {
            //final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");
            // final String level = cetakValidasiServices.getlevel();
            System.out.println("pathReport " + pathReport);
            map.put("SUBREPORT_DIR", pathReport);
            map.put("value", id);
            map.put("kb", kodeBeban);
            map.put("kj", kodeJenis);
            map.put("ns", nospp);
            map.put("nf", namafile);
            map.put("ls", lskpd);
            map.put("tahun", tahunAnggaran);
            map.put("np", cetakValidasiSPPServices.getnilaiparam(map));
            final Map<String, Object> mapData = new HashMap<String, Object>(4);
            final int byk = cetakValidasiSPPServices.getbanyaksppcetakbtl3(map);
            final int byk1 = cetakValidasiSPPServices.getbanyaksppcetakbtl4(map);
            List<Map> listhasil = cetakValidasiSPPServices.getnilaiparam(map);
            List<Map> listhasil1 = cetakValidasiSPPServices.getnilaiparam1(map);
            map.put("NAMA_DAERAH", listhasil.get(0).get("N_DAERAH_JUDUL"));
            map.put("NAMA_DAERAH_LOW", listhasil.get(0).get("N_DAERAH"));
            map.put("NO_PERDA", listhasil.get(0).get("I_PERDA_NO"));
            map.put("THN_PERDA", listhasil.get(0).get("C_PERDA_TAHUN"));
            map.put("TGL_PERDA", listhasil.get(0).get("C_PERDA_TGL"));
            map.put("NAMA_KOTA", listhasil.get(0).get("N_KOTA"));
            map.put("PERATURAN_1", listhasil.get(0).get("E_PERATURAN_SPD1"));
            map.put("PERATURAN_2", listhasil1.get(0).get("E_PERATURAN_SPD2"));
            map.put("PERATURAN_3", listhasil.get(0).get("E_PERATURAN_SPD3"));
            map.put("PERATURAN_4", listhasil.get(0).get("E_PERATURAN_SPD4"));
            map.put("PERATURAN_5", listhasil.get(0).get("E_PERATURAN_SPD5"));
            mapData.put("banyak3", byk);
            
             log.debug("banyak 3 : "+byk);
             log.debug("bayar bunga : "+byk1);
             
            if (lskpd.equals("3")) {
                if (kodeBeban.equals("LS")) {

                    if ((kodeJenis.equals("BTL"))) {

                        if (byk > 0)
                        {   
                             log.debug("Map Parameter : "+map.toString());
                            log.debug("====== : "+byk);
                             cetakValidasiSPPServices.updateSppBtt(map);
                            map.put("NOMOR_SPP", nospp);
                            map.put("ID_SKPD", iskpd);
                            map.put("TAHUN", tahunAnggaran);
                            
                            map.put("pathreport", pathReport + "/Report_SPP-LS_BelanjaTTerduga.jasper");
                            JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                            //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPP-LS_BelanjaTTerduga.jasper", map, jdbcConnection);
                            final String nospdC = nospp;
                            final String filename = "SPP_LS_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                            response.setHeader("Content-Disposition", filename);
                            ServletOutputStream output = response.getOutputStream();
                            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                            output.close();
                        }else if (byk1 > 0) 
                        {
                            log.debug("!!!!!!!! : "+byk1);
                            map.put("NOMOR_SPP", nospp);
                            map.put("ID_SKPD", iskpd);
                            map.put("TAHUN", tahunAnggaran);
                            
                            map.put("pathreport", pathReport + "/Report_SPP-LS_BungaSubsidi.jasper");
                            JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                            //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPP-LS_BungaSubsidi.jasper", map, jdbcConnection);
                            final String nospdC = nospp;
                            final String filename = "SPP_LS_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                            response.setHeader("Content-Disposition", filename);
                            ServletOutputStream output = response.getOutputStream();
                            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                            output.close();
                            
                        }

                    } else if ((kodeJenis.equals("BIAYA"))) {

                        map.put("NOMOR_SPP", nospp);
                        map.put("ID_SKPD", iskpd);
                        map.put("TAHUN", tahunAnggaran);
                        
                        map.put("pathreport", pathReport + "/Report_SPP-LS_Pembiayaan.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                        //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPP-LS_Pembiayaan.jasper", map, jdbcConnection);
                        final String nospdC = nospp;
                        final String filename = "SPP_LS_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                        response.setHeader("Content-Disposition", filename);
                        ServletOutputStream output = response.getOutputStream();
                        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                        output.close();

                    } else if ((kodeJenis.equals("BTL-BANTUAN"))) {

                        map.put("NOMOR_SPP", nospp);
                        map.put("ID_SKPD", iskpd);
                        map.put("TAHUN", tahunAnggaran);
                        
                        map.put("pathreport", pathReport + "/Report_SPP-LS_HibahBantuan.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                        //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPP-LS_HibahBantuan.jasper", map, jdbcConnection);
                        final String nospdC = nospp;
                        final String filename = "SPP_LS_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                        response.setHeader("Content-Disposition", filename);
                        ServletOutputStream output = response.getOutputStream();
                        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                        output.close();

                    } 
                    
                }

            } else {
                 if ((kodeJenis.equals("RESTITUSI"))) {
                        log.debug("------- :"+kodeJenis);
                        map.put("NOMOR_SPP", nospp);
                        map.put("ID_SKPD", iskpd);
                        map.put("TAHUN", tahunAnggaran);
                        
                        map.put("pathreport", pathReport + "/Report_SPP-LS_Restitusi.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                        //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPP-LS_Restitusi.jasper", map, jdbcConnection);
                        final String nospdC = nospp;
                        final String filename = "SPP_LS_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                        response.setHeader("Content-Disposition", filename);
                        ServletOutputStream output = response.getOutputStream();
                        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                        output.close();

                    } else  if ((kodeJenis.equals("BTL"))) {

                    if (kodeBeban.equals("LS")) {
                        log.debug("Asup ka BTL LS");
                        map.put("NOMOR_SPP", nospp);
                        map.put("ID_SKPD", iskpd);
                        map.put("TAHUN", tahunAnggaran);
                        
                        map.put("pathreport", pathReport + "/Report_SPP-LS_BelanjaTidakLangsung.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                        //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPP-LS_BelanjaTidakLangsung.jasper", map, jdbcConnection);
                        final String nospdC = nospp;
                        final String filename = "SPP_LS_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                        response.setHeader("Content-Disposition", filename);
                        ServletOutputStream output = response.getOutputStream();
                        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                        output.close();
                    }

                } else if (kodeJenis.equals("BL")) {

                    if (kodeBeban.equals("UP")) {

                        map.put("NOMOR_SPP", nospp);
                        map.put("ID_SKPD", iskpd);
                        map.put("TAHUN", tahunAnggaran);
                        
                        map.put("pathreport", pathReport + "/Report_SPP-UPGU.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                        //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPP-UPGU.jasper", map, jdbcConnection);
                        System.out.println(" = " + pathReport + "/Report_SPP-UPGU.jasper");
                        final String nospdC = nospp;
                        final String filename = "SPP_UP_GU_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                        map.put("namaFile", filename);
                        response.setHeader("Content-Disposition", filename);
                        ServletOutputStream output = response.getOutputStream();
                        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                        output.close();
                    } else if (kodeBeban.equals("TU")) {
                        map.put("TAHUN", tahunAnggaran);
                        map.put("NOMOR_SPP", nospp);
                        map.put("ID_SKPD", iskpd);
                        map.put("TAHUN", tahunAnggaran);
                        
                        map.put("pathreport", pathReport + "/Report_SPP-TU.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                        //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPP-TU.jasper", map, jdbcConnection);
                        System.out.println(" = " + pathReport + "/Report_SPP-TU.jasper");
                        final String nospdC = nospp;
                        final String filename = "SPP_TUP_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                        response.setHeader("Content-Disposition", filename);
                        ServletOutputStream output = response.getOutputStream();
                        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                        output.close();
                    } else if (kodeBeban.equals("GU")) {

                        map.put("NOMOR_SPP", nospp);
                        map.put("ID_SKPD", iskpd);
                        map.put("TAHUN", tahunAnggaran);
                        
                        map.put("pathreport", pathReport + "/Report_SPP-UPGU.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                        //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPP-UPGU.jasper", map, jdbcConnection);
                        final String nospdC = nospp;
                        final String filename = "SPP_GUP_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                        response.setHeader("Content-Disposition", filename);
                        ServletOutputStream output = response.getOutputStream();
                        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                        output.close();
                    } else if (kodeBeban.equals("LS")) {

                        map.put("NOMOR_SPP", nospp);
                        map.put("ID_SKPD", iskpd);
                        map.put("TAHUN", tahunAnggaran);
                        
                        map.put("pathreport", pathReport + "/Report_SPP-LS_BarangJasa.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                        //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPP-LS_BarangJasa.jasper", map, jdbcConnection);
                        final String nospdC = nospp;
                        final String filename = "SPP_GUP_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                        response.setHeader("Content-Disposition", filename);
                        ServletOutputStream output = response.getOutputStream();
                        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                        output.close();

                    }

                }

            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();

        }

    }

    /**
     * ******
     *
     */
}
