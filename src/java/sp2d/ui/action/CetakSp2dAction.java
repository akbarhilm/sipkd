package sp2d.ui.action;

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
import javax.sql.DataSource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import sp2d.services.CetakValidasiSP2DServices;
import sp2d.util.SipkdHelpers;
import spp.model.SppUp;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/cetaksp2d")
public class CetakSp2dAction {

    private static final Logger log = LoggerFactory.getLogger(CetakSp2dAction.class);
    @Autowired
    CetakValidasiSP2DServices cetakValidasiSP2DServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/indexsp2dup", method = RequestMethod.GET)
    public String indexspmup(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        return "cetak/sp2d/index";

    }

    @RequestMapping(value = "/json/getlistsp2dcetak", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspmcetak(final HttpServletRequest request) {

        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("namaskpd");
        final String idskpd = request.getParameter("idskpd");
        final String kproses = request.getParameter("kproses");
        final String user = request.getParameter("user");
        final String level = request.getParameter("levelSkpd");
        final String kskpd = request.getParameter("kodeskpd");
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
        param.put("user", user);
        param.put("idskpd", SipkdHelpers.getIntFromString(idskpd));
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = cetakValidasiSP2DServices.getbanyaksp2dcetak(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", cetakValidasiSP2DServices.getlistsp2dcetak(param));
        return mapData;
    }

    /*  @RequestMapping(value = "/json/updatesp2dcetak", method = RequestMethod.POST)
     public @ResponseBody
     String submitcetakspm(@RequestBody List<Map<String, Object>> mapdata, final HttpServletRequest request) {
     final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
     final String pathjasper = servletContext.getRealPath("/WEB-INF/report/");
     final String pathpdf = servletContext.getRealPath("/WEB-INF/pdf/");
     for (Map<String, Object> mapdetil : mapdata) {
     mapdetil.put("userid", pengguna.getIdPengguna());
     mapdetil.put("filejasper", pathjasper);
     mapdetil.put("filepdf", pathpdf);
     mapdetil.put("tglentry", new Timestamp(System.currentTimeMillis()));
             
     cetakValidasiServices.updatesp2dcetak( mapdetil);
     }

     return "simpan data cetak sukes";

     }*/
    @RequestMapping(value = "/json/updatesp2dcetak", method = RequestMethod.POST)
    // public String prosesubah(@RequestBody List<Map<String, Object>> mapdata,@Valid @ModelAttribute("refsp2d") SppUp sppUp, BindingResult result, final HttpServletRequest request) {
    public @ResponseBody
    String submitcetak(@RequestBody List<Map<String, Object>> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        /*final String pathjasper = servletContext.getRealPath("/WEB-INF/report/");
        final String pathpdf = servletContext.getRealPath("/WEB-INF/pdf/");*/
        final String tahunAnggaran1 = (String) request.getSession().getAttribute("tahunAnggaran");
        final String pathjasper = servletContext.getInitParameter("PATH_REPORT");
        final String pathpdf = servletContext.getInitParameter("PATH_PDF");
        final String sp2d = "/SP2D/";
        boolean hasil = false;
        boolean hsl = false;
        String dirname = tahunAnggaran1;
        File dir = new File(pathpdf + dirname + sp2d);
        dir.mkdir();
        final String pdf = FilenameUtils.separatorsToUnix(dir.getAbsolutePath());// dirspd+"\\";
        final String pdfsp2d = pdf + "/";
        log.debug("==== PDF 3===> " + pdf);

        for (Map<String, Object> mapdetil : mapdata) {
            mapdetil.put("userid", pengguna.getIdPengguna());
            mapdetil.put("tgluserid", new Timestamp(System.currentTimeMillis()));
            /*mapdetil.put("filejasper", pathjasper);
            mapdetil.put("filepdf", pdfsp2d);
            mapdetil.put("statcam", "3");*/
            cetakValidasiSP2DServices.updatesp2dcetak(mapdetil);
        }

        return "Proses Data SP2D Cetak Sukses";

    }

    /**
     * ******
     *
     */
     @RequestMapping(value={"/{id}/{kodeBeban}/{kodeJenis}/{idSp2d}/{nosp2d}/{iskpd}/{ls}/{kpros}/{kodeNihil}/{namafile}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public void processRequest(HttpServletRequest request, HttpServletResponse response, @PathVariable String kodeBeban, @PathVariable String kodeJenis, @PathVariable Integer id, @PathVariable String nosp2d, @PathVariable String iskpd, @PathVariable String ls, @PathVariable String kpros, @PathVariable String namafile, @PathVariable String kodeNihil)
  {
    response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
    response.setContentType("application/pdf");
    try
    {
      Connection jdbcConnection = this.dataSource.getConnection();
      Map<String, Object> map = new HashMap();
      String tahunAnggaran = (String)request.getSession().getAttribute("tahunAnggaran");
      String kproses = request.getParameter("kproses");
      String pathReport = this.servletContext.getInitParameter("PATH_REPORT");
      log.debug("PATH Report Jasper nya ==> " + pathReport);
      map.put("SUBREPORT_DIR", pathReport);
      map.put("value", id);
      map.put("kb", kodeBeban);
      map.put("kj", kodeJenis);
      
      map.put("ns", nosp2d);
      map.put("nf", namafile);
      map.put("kpros", kpros);
      map.put("ls", ls);
      map.put("np", this.cetakValidasiSP2DServices.getnilaiparam(map));
      Map<String, Object> mapData = new HashMap(4);
      int byk = this.cetakValidasiSP2DServices.getbanyaksp2dcetakbtl3(map).intValue();
      int byk1 = this.cetakValidasiSP2DServices.getbanyaksp2dcetakbtl4(map).intValue();
      log.debug("Cek Jumlah tuk TIDAK TERDUGA ==> " + byk);
      int bykres = this.cetakValidasiSP2DServices.getbanyaksp2dcetakres3(map).intValue();
      List<Map> listhasil = this.cetakValidasiSP2DServices.getnilaiparam(map);
      map.put("NAMA_DAERAH", ((Map)listhasil.get(0)).get("N_DAERAH_JUDUL"));
      map.put("NAMA_DAERAH_LOW", ((Map)listhasil.get(0)).get("N_DAERAH"));
      map.put("NO_PERDA", ((Map)listhasil.get(0)).get("I_PERDA_NO"));
      map.put("THN_PERDA", ((Map)listhasil.get(0)).get("C_PERDA_TAHUN"));
      map.put("TGL_PERDA", ((Map)listhasil.get(0)).get("C_PERDA_TGL"));
      map.put("NAMA_KOTA", ((Map)listhasil.get(0)).get("N_KOTA"));
      map.put("PERATURAN_1", ((Map)listhasil.get(0)).get("E_PERATURAN_SPD1"));
      map.put("PERATURAN_2", ((Map)listhasil.get(0)).get("E_PERATURAN_SPD2"));
      map.put("PERATURAN_3", ((Map)listhasil.get(0)).get("E_PERATURAN_SPD3"));
      map.put("PERATURAN_4", ((Map)listhasil.get(0)).get("E_PERATURAN_SPD4"));
      map.put("PERATURAN_5", ((Map)listhasil.get(0)).get("E_PERATURAN_SPD5"));
      
      mapData.put("banyak3", Integer.valueOf(byk));
      if (ls.equals("3"))
      {
        if (kodeBeban.equals("LS")) {
          if (kodeJenis.equals("BTL"))
          {
            if (byk > 0)
            {
              log.debug("Map Parameter yang MASUK ==> " + map.toString());
              map.put("NOMOR_SP2D", nosp2d);
              map.put("ID_SKPD", iskpd);
              map.put("TAHUN", tahunAnggaran);
              map.put("WIL_PROSES", kpros);
              JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SP2D-LS_BelanjaTTerduga.jasper", map, jdbcConnection);
              String nospdC = nosp2d;
              String filename = "SPP_LS_BL." + tahunAnggaran + "." + nospdC + ".pdf";
              response.setHeader("Content-Disposition", filename);
              ServletOutputStream output = response.getOutputStream();
              JasperExportManager.exportReportToPdfStream(jasperPrint, output);
              output.close();
            }
            else if (byk1 > 0)
            {
              log.debug("masuk bayar bunga");
              map.put("NOMOR_SP2D", nosp2d);
              map.put("ID_SKPD", iskpd);
              map.put("TAHUN", tahunAnggaran);
              map.put("WIL_PROSES", kpros);
              log.debug("Parameter + PATH Report Jasper nya ==> " + map.toString() + " ==> " + pathReport);
              JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SP2D-LS_BelanjaTidakLangsung-PPKD.jasper", map, jdbcConnection);
              String nospdC = nosp2d;
              String filename = "SPP_LS_BL." + tahunAnggaran + "." + nospdC + ".pdf";
              response.setHeader("Content-Disposition", filename);
              ServletOutputStream output = response.getOutputStream();
              JasperExportManager.exportReportToPdfStream(jasperPrint, output);
              output.close();
            }
            else if (iskpd.equals("761") || iskpd.equals("1234"))
            {
              map.put("NOMOR_SP2D", nosp2d);
              map.put("ID_SKPD", iskpd);
              map.put("TAHUN", tahunAnggaran);
              map.put("WIL_PROSES", kpros);
              JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SP2D-LS_BelanjaTidakLangsung.jasper", map, jdbcConnection);
              String nospdC = nosp2d;
              String filename = "SPP_LS_BL." + tahunAnggaran + "." + nospdC + ".pdf";
              response.setHeader("Content-Disposition", filename);
              ServletOutputStream output = response.getOutputStream();
              JasperExportManager.exportReportToPdfStream(jasperPrint, output);
              output.close();
            }
          }
          else if (kodeJenis.equals("BIAYA"))
          {
            map.put("NOMOR_SP2D", nosp2d);
            map.put("ID_SKPD", iskpd);
            map.put("TAHUN", tahunAnggaran);
            map.put("WIL_PROSES", kpros);
            JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SP2D-LS_Pembiayaan.jasper", map, jdbcConnection);
            String nospdC = nosp2d;
            String filename = "SPP_LS_BL." + tahunAnggaran + "." + nospdC + ".pdf";
            response.setHeader("Content-Disposition", filename);
            ServletOutputStream output = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            output.close();
          }
          else if (kodeJenis.equals("BTL-BANTUAN"))
          {
            map.put("NOMOR_SP2D", nosp2d);
            map.put("ID_SKPD", iskpd);
            map.put("TAHUN", tahunAnggaran);
            map.put("WIL_PROSES", kpros);
            JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SP2D-LS_HibahBantuan.jasper", map, jdbcConnection);
            String nospdC = nosp2d;
            String filename = "SPP_LS_BL." + tahunAnggaran + "." + nospdC + ".pdf";
            response.setHeader("Content-Disposition", filename);
            ServletOutputStream output = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            output.close();
          }
        }
      }
      else if (kodeJenis.equals("RESTITUSI"))
      {
        log.debug("------- :" + kodeJenis);
        map.put("NOMOR_SP2D", nosp2d);
        map.put("ID_SKPD", iskpd);
        map.put("TAHUN", tahunAnggaran);
        map.put("WIL_PROSES", kpros);
        JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SP2D-LS_Restitusi.jasper", map, jdbcConnection);
        String nospdC = nosp2d;
        String filename = "SPP_LS_BL." + tahunAnggaran + "." + nospdC + ".pdf";
        response.setHeader("Content-Disposition", filename);
        ServletOutputStream output = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
        output.close();
      }
      else if (kodeJenis.equals("BTL"))
      {
        if (kodeBeban.equals("LS"))
        {
          map.put("NOMOR_SP2D", nosp2d);
          map.put("ID_SKPD", iskpd);
          map.put("TAHUN", tahunAnggaran);
          map.put("WIL_PROSES", kpros);
          JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SP2D-LS_BelanjaTidakLangsung.jasper", map, jdbcConnection);
          String nospdC = nosp2d;
          String filename = "SPP_LS_BL." + tahunAnggaran + "." + nospdC + ".pdf";
          response.setHeader("Content-Disposition", filename);
          ServletOutputStream output = response.getOutputStream();
          JasperExportManager.exportReportToPdfStream(jasperPrint, output);
          output.close();
        }
      }
      else if (kodeJenis.equals("BL"))
      {
        if (kodeBeban.equals("UP"))
        {
          map.put("SUBREPORT_DIR", pathReport);
          map.put("NOMOR_SP2D", nosp2d);
          map.put("TAHUN", tahunAnggaran);
          map.put("WIL_PROSES", kpros);
          JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SP2D-UPGU.jasper", map, jdbcConnection);
          String filename = "SPM_UPGU_BL.pdf";
          response.setHeader("Content-Disposition", "SPM_UPGU_BL.pdf");
          ServletOutputStream output = response.getOutputStream();
          JasperExportManager.exportReportToPdfStream(jasperPrint, output);
          output.close();
        }
        else if (kodeBeban.equals("TU"))
        {
          map.put("SUBREPORT_DIR", pathReport);
          map.put("NOMOR_SP2D", nosp2d);
          map.put("TAHUN", tahunAnggaran);
          map.put("WIL_PROSES", kpros);
          JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SP2D-TU.jasper", map, jdbcConnection);
          String filename = "SPDBL.pdf";
          response.setHeader("Content-Disposition", "SPDBL.pdf");
          ServletOutputStream output = response.getOutputStream();
          JasperExportManager.exportReportToPdfStream(jasperPrint, output);
          output.close();
        }
        else if (kodeBeban.equals("GU"))
        {
          map.put("SUBREPORT_DIR", pathReport);
          map.put("NOMOR_SP2D", nosp2d);
          map.put("TAHUN", tahunAnggaran);
          map.put("WIL_PROSES", kpros);
          JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SP2D-UPGU.jasper", map, jdbcConnection);
          String filename = "SPDBLB.pdf";
          response.setHeader("Content-Disposition", "SPDBLB.pdf");
          ServletOutputStream output = response.getOutputStream();
          JasperExportManager.exportReportToPdfStream(jasperPrint, output);
          output.close();
        }
        else if (kodeBeban.equals("LS"))
        {
          if (kodeNihil.equals("1"))
          {
            map.put("SUBREPORT_DIR", pathReport);
            map.put("NOMOR_SP2D", nosp2d);
            map.put("TAHUN", tahunAnggaran);
            map.put("WIL_PROSES", kpros);
            JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SP2D-LS_BelanjaBLUD.jasper", map, jdbcConnection);
            String filename = "SPDB.pdf";
            response.setHeader("Content-Disposition", "SPDB.pdf");
            ServletOutputStream output = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            output.close();
          }
          else
          {
            map.put("SUBREPORT_DIR", pathReport);
            map.put("NOMOR_SP2D", nosp2d);
            map.put("TAHUN", tahunAnggaran);
            map.put("WIL_PROSES", kpros);
            JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SP2D-LS_BarangJasa.jasper", map, jdbcConnection);
            String filename = "SPDB.pdf";
            response.setHeader("Content-Disposition", "SPDB.pdf");
            ServletOutputStream output = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            output.close();
          }
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

    /**
     * ******
     *
     */
    @RequestMapping(value = "/json/hapussp2dcetak",
            method = RequestMethod.POST)
    public @ResponseBody
    //String hapussppcetak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) throws IOException {
       String hapussppcetak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {           
         log.debug("ID nya ==> "+mapdata.get("idspd"));
         cetakValidasiSP2DServices.deletesp2dcetak((Integer) mapdata.get("idspd"));
         return "Data SP2D No " + mapdata.get("nosp2d") + " Berhasil Dibatalkan  ";
        
      /*  
        final String pathhistory = servletContext.getInitParameter("PATH_HISTORY");
        final String tahun= (String) request.getSession().getAttribute("tahunAnggaran");
        final String namaFile = (String) mapdata.get("namafile");
        final String nosp2d = (String) mapdata.get("nosp2d");
        int idsp2d = (int) mapdata.get("idspd");
        final String sp2d = "/SP2D/";
        boolean hasil = false;
        boolean hsl = false;
        String dirname = tahun;
        File dir = new File(pathhistory + dirname + sp2d);
        dir.mkdir();
         final String his = FilenameUtils.separatorsToUnix(dir.getAbsolutePath());
         final String hisspm = his + "/";
        final Map< String, Object> param = new HashMap<String, Object>(2);
        log.debug("Parameter Delete 1 ===> "+pathhistory+"--"+tahun+"--"+idsp2d+" === "+nosp2d);
        param.put("tahun", tahun);
        param.put("nosp2d", nosp2d);
        param.put("value", idsp2d);
        List<SppUp> listSp2d = cetakValidasiSP2DServices.getPathFile(param);
        for (int i = 0; i < listSp2d.size(); i++) {
                SppUp get = listSp2d.get(i);
                String pathPdf = listSp2d.get(i).getAlamatPdfOutput();
                String jenis = listSp2d.get(i).getKodeJenis();
                String beban = listSp2d.get(i).getKodeBeban();
                String level = listSp2d.get(i).getLevelSkpd();
                int idskpd = listSp2d.get(i).getSkpd().getIdSkpd();
                final String is = String.valueOf(idskpd);
                final String lokasiDanNamaPdfAsal =  pathPdf+namaFile;
                final String lokasiDanNamaPdfTujuan =  hisspm+namaFile;
                log.debug("Parameter Delete 2 ===> "+beban+"--"+jenis+"--"+level+"--"+lokasiDanNamaPdfTujuan);
                //final int ids = cetakValidasiSPMServices.getbanyakspmcetakbtl5(param);
                //final Map<String, Object> mapData = new HashMap<String, Object>(4);
                //mapData.put("idspp", ids);
                final int byk = cetakValidasiSP2DServices.getbanyaksp2dcetakbtl3(param);
                //final int byk1 = cetakValidasiSP2DServices.getbanyaksp2dcetakbtl4(mapData);
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
                                Path tjn = Paths.get(hisspm+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SP2D Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SP2D Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SP2D Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
                                }
                               
                                
                                
                            }
                        }else if (byk == 0) 
                        {
                            File asal = new File(lokasiDanNamaPdfAsal);
                            File tujuan = new File(lokasiDanNamaPdfTujuan);
                            if ( tujuan.exists() )
                            {
                                Path asl = Paths.get(pathPdf+namaFile);
                                Path tjn = Paths.get(hisspm+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SP2D Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SP2D Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SP2D Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
                                }
                               
                                
                                
                            }
                            
                        }else if (is.equals("761"))
                        {
                            
                            File asal = new File(lokasiDanNamaPdfAsal);
                            File tujuan = new File(lokasiDanNamaPdfTujuan);
                            if ( tujuan.exists() )
                            {
                                Path asl = Paths.get(pathPdf+namaFile);
                                Path tjn = Paths.get(hisspm+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SP2D Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SP2D Belum Terbentuk, Tunggu Beberapa Saat Hingga File Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
                                }
                               
                                
                                
                            }
                            
                        }

                    } else if ((jenis.equals("4"))) {

                        File asal = new File(lokasiDanNamaPdfAsal);
                            File tujuan = new File(lokasiDanNamaPdfTujuan);
                            if ( tujuan.exists() )
                            {
                                Path asl = Paths.get(pathPdf+namaFile);
                                Path tjn = Paths.get(hisspm+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SP2D Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SP2D Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SP2D Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
                                }
                               
                                
                                
                            }

                    } else if ((jenis.equals("2"))) {

                        File asal = new File(lokasiDanNamaPdfAsal);
                            File tujuan = new File(lokasiDanNamaPdfTujuan);
                            if ( tujuan.exists() )
                            {
                                Path asl = Paths.get(pathPdf+namaFile);
                                Path tjn = Paths.get(hisspm+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SP2D Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SP2D Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SP2D Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
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
                                Path tjn = Paths.get(hisspm+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SP2D Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SP2D Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SP2D Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
                                }
                               
                                
                                
                            }

                    } else  if ((jenis.equals("1"))) {

                    if (beban.equals("LS")) {
                        File asal = new File(lokasiDanNamaPdfAsal);
                            File tujuan = new File(lokasiDanNamaPdfTujuan);
                            if ( tujuan.exists() )
                            {
                                Path asl = Paths.get(pathPdf+namaFile);
                                Path tjn = Paths.get(hisspm+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SP2D Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SP2D Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SP2D Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
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
                                Path tjn = Paths.get(hisspm+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SP2D Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SP2D Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SP2D Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
                                }
                               
                                
                                
                            }
                    } else if (beban.equals("TU")) {
                        File asal = new File(lokasiDanNamaPdfAsal);
                            File tujuan = new File(lokasiDanNamaPdfTujuan);
                            if ( tujuan.exists() )
                            {
                                Path asl = Paths.get(pathPdf+namaFile);
                                Path tjn = Paths.get(hisspm+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SP2D Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SP2D Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SP2D Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
                                }
                               
                                
                                
                            }
                    } else if (beban.equals("GU")) {

                        File asal = new File(lokasiDanNamaPdfAsal);
                            File tujuan = new File(lokasiDanNamaPdfTujuan);
                            if ( tujuan.exists() )
                            {
                                Path asl = Paths.get(pathPdf+namaFile);
                                Path tjn = Paths.get(hisspm+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SP2D Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SP2D Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SP2D Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
                                }
                               
                                
                                
                            }
                    } else if (beban.equals("LS")) {
                        
                         
                            File asal = new File(lokasiDanNamaPdfAsal);
                            File tujuan = new File(lokasiDanNamaPdfTujuan);
                            if ( tujuan.exists() )
                            {
                                Path asl = Paths.get(pathPdf+namaFile);
                                Path tjn = Paths.get(hisspm+namaFile);
                                
                                if (asal.exists())
                                {
                                   Files.move(asl, tjn, REPLACE_EXISTING);
                                }else
                                {
                                  
                                   FileUtils.copyFile(tujuan, asal);
                                   return "Karena File PDF SP2D Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
                                }
                                

                            }else
                            {
                                if (asal.exists())
                                {
                                   FileUtils.moveFile(asal, tujuan); 
                                }else
                                {
                                   return "File PDF SP2D Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SP2D Terbentuk Lalu Ulangi Lagi Proses Batal Cetak..."; 
                                }
                               
                                
                                
                            }
                    
                    }

                }

            }   
                
        }
        cetakValidasiSP2DServices.deletesp2dcetak((Integer) mapdata.get("idspd"));
        return "Data SP2D No " + mapdata.get("nospp") + " Berhasil Dibatalkan  ";*/
    }

}
