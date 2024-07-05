package spm.ui.action;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
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
import spm.services.CetakReportServices;
import spp.model.Pengguna;
import spp.model.Skpd;
import spm.services.CetakValidasiSPMServices;
import spm.util.SipkdHelpers;
import spp.model.SppUp;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/cetakspm")
public class CetakSpmAction {

    private static final Logger log = LoggerFactory.getLogger(CetakSpmAction.class);
    @Autowired
    CetakValidasiSPMServices cetakValidasiSPMServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;

    @Autowired
    CetakReportServices cetakReportServices;

    @RequestMapping(value = "/indexspmup", method = RequestMethod.GET)
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
        return "cetak/spm/index";

    }

    @RequestMapping(value = "/json/getlistspmcetak", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspmcetak(final HttpServletRequest request) {

        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("namaskpd");
        final String idskpd = request.getParameter("idskpd");
        final String beban = request.getParameter("beban");
        final String jenis = request.getParameter("jenis");
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
        final int banyak = cetakValidasiSPMServices.getbanyakspmcetak(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", cetakValidasiSPMServices.getlistspmcetak(param));
        request.setAttribute("beban", beban);
        request.setAttribute("jenis", jenis);
        return mapData;
    }

    @RequestMapping(value = "/json/insertspmcetak", method = RequestMethod.POST)
    public @ResponseBody
    String submitcetakspm(@RequestBody List<Map<String, Object>> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        /*final String pathjasper = servletContext.getRealPath("/WEB-INF/report/");
         final String pathpdf = servletContext.getRealPath("/WEB-INF/pdf/");*/
        final String tahunAnggaran1 = (String) request.getSession().getAttribute("tahunAnggaran");
        final String pathjasper = servletContext.getInitParameter("PATH_REPORT");
        final String pathpdf = servletContext.getInitParameter("PATH_PDF");
        final String spm = "/SPM/";
        boolean hasil = false;
        boolean hsl = false;
        String dirname = tahunAnggaran1;
        File dir = new File(pathpdf + dirname + spm);
        dir.mkdir();
        final String pdf = FilenameUtils.separatorsToUnix(dir.getAbsolutePath());// dirspd+"\\";
        final String pdfspm = pdf + "/";
        log.debug("==== PDF 3===> " + pdf);
        try {

            String kb;// = (String) mapdata.get(0).get("kodeBeban");
            String kj;// = (String) mapdata.get(0).get("kodeJenis");
            String tahunAnggaran;// = (String) mapdata.get(0).get("tahun");
            String idskpd;// = (String) mapdata.get(0).get("idSkpd");
            String idspm;// = (String) mapdata.get(0).get("id");
            String idspp;// = (String) mapdata.get(0).get("idspp");
            String uangmuka;// = (String) mapdata.get(0).get("uangmuka");
            log.debug("mapdata : " + mapdata.size());

            //log.debug("mapdata idspp : " + idspp);
            //log.debug("mapdata uangmuka: " + uangmuka);
            //log.debug("mapdata idskpd : " + idskpd);
            //log.debug("mapdata beban : " + kb);
            //log.debug("mapdata jenis : " + kj);
            for (Map<String, Object> mapdetil : mapdata) {

                kb = (String) mapdetil.get("kodeBeban");
                kj = (String) mapdetil.get("kodeJenis");
                tahunAnggaran = (String) mapdetil.get("tahun");
                idskpd = (String) mapdetil.get("idSkpd");
                idspm = (String) mapdetil.get("id");
                idspp = (String) mapdetil.get("idspp");
                uangmuka = (String) mapdetil.get("uangmuka");

                mapdetil.put("userid", pengguna.getIdPengguna());
                mapdetil.put("filejasper", pathjasper);
                mapdetil.put("filepdf", pdfspm);
                mapdetil.put("statcam", "3");
                mapdetil.put("tglentry", new Timestamp(System.currentTimeMillis()));

                if ((kb.equals("LS")) && (kj.equals("BL"))) {

                    final Map<String, Object> map = new HashMap<String, Object>(4);
                    map.put("idspp", idspp);
                    map.put("idskpd", idskpd);
                    map.put("tahun", tahunAnggaran);
                    map.put("uangmuka", uangmuka);
                    List<Map> listhasil = cetakValidasiSPMServices.getidkegkon(map);
                    BigDecimal keg = null;
                    BigDecimal kon = null;
                    String uk = null;
                    if (listhasil != null) {
                        keg = (BigDecimal) listhasil.get(0).get("IDKEGIATAN");
                        kon = (BigDecimal) listhasil.get(0).get("IDKONTRAK");
                        uk = (String) listhasil.get(0).get("UANGMUKA");
                    }

                    List<Map> listkodeblud = cetakValidasiSPMServices.getKodeSumbDana(map); // CEK DATA SUMBER DANA BLUD
                    String kodeblud = (String) listkodeblud.get(0).get("KODE_SUMBDANA");
                    log.debug("************** KODE SUMBER DANA BLUD ===> " + kodeblud);

                    List<Map> listkodeumk = cetakValidasiSPMServices.getKodeUmk(map); // JIKA UNTUK KONTRAK TSB ADA UANG MUKA
                    BigDecimal kodeumk = (BigDecimal) listkodeumk.get(0).get("KODE_UMK");

                    List<Map> listmulti = cetakValidasiSPMServices.getKodeMultiyear(map); // CEK KEGIATAN MULTIYEAR
                    //log.debug("************** listmulti.toString() ===> " + listmulti.toString());

                    String cumk = (String) listmulti.get(0).get("C_UANGMUKA");
                    String cumkpot = (String) listmulti.get(0).get("C_UANGMUKA_POT");
                    String my = "0";

                    if ("0".equals(cumk) && "1".equals(cumkpot)) {
                        my = "1"; // MULTIYEAR
                    }

                    if ("1".equals(kodeumk.toString()) || "1".equals(my)) {

                        map.put("idkegiatan", listhasil.get(0).get("IDKEGIATAN"));
                        map.put("idkontrak", listhasil.get(0).get("IDKONTRAK"));
                        map.put("idspm", idspm);
                        log.debug("idspm ===== " + idspm);

                        List<Map> listhasil1 = cetakValidasiSPMServices.getnilai(map);
                        BigDecimal nikon = (BigDecimal) listhasil1.get(0).get("V_KONTR");
                        BigDecimal nispp = (BigDecimal) listhasil1.get(0).get("V_SPP");
                        BigDecimal nipot = (BigDecimal) listhasil1.get(0).get("V_POT_SPM");
                        BigDecimal sel = (BigDecimal) listhasil1.get(0).get("SELISIH");
                        //Integer selisihInt = SipkdHelpers.getIntFromString(sel.toString());
                        log.debug("--- selisihInt ======= " + SipkdHelpers.getIntFromString(sel.toString()));

                        List<Map> listumk = cetakValidasiSPMServices.getSisaUmk(map);
                        BigDecimal nilaisisa = (BigDecimal) listumk.get(0).get("NILAI_SISA");
                        BigDecimal nilaikontrak = (BigDecimal) listumk.get(0).get("NILAI_KONTRAK");
                        BigDecimal nilairealisasi = (BigDecimal) listumk.get(0).get("NILAI_REALISASI");
                        //Integer sisaInt = SipkdHelpers.getIntFromString(nilaisisa.toString());
                        log.debug("************** nilaisisa ===> " + nilaisisa);

                        //if (selisihInt < 0) {//if (nispp.compareTo(nikon) >= 0) {
                        //return "Nilai SPM Sebesar " + SipkdHelpers.formatBigDecimalPropertyEditor(nispp) + " - Nilai Potongan Sebesar " + SipkdHelpers.formatBigDecimalPropertyEditor(nipot) + " > Nilai Kontrak Sebesar " + SipkdHelpers.formatBigDecimalPropertyEditor(nikon) + ", Harus Input Potongan Terlebih Dahulu...Selisih Sebesar " + SipkdHelpers.formatBigDecimalPropertyEditor(sel) + " ";
                        /*  -- revisi 6 Des 2018, parseInt max 9 char
                         if (sisaInt < 0) { // JIKA NILAI TOTAL REALISASI > DARI NILAI KONTRAK
                         return "Nilai Realisasi Lebih Besar dari Nilai Kontrak -- Nilai Realisasi = Total Nilai SPP - Total Nilai Potongan UMK ";
                         } else {
                         cetakValidasiSPMServices.insertspmcetak(mapdetil);
                         }
                         */
                        if (nilaisisa.compareTo(new BigDecimal("0")) == -1) { // JIKA NILAI TOTAL REALISASI > DARI NILAI KONTRAK
                            return "Nilai Realisasi Lebih Besar dari Nilai Kontrak -- Nilai Realisasi = Total Nilai SPP - Total Nilai Potongan UMK ";
                        } else {
                            cetakValidasiSPMServices.insertspmcetak(mapdetil);
                        }

                    } else if ("1".equals(kodeblud.toString())) { // CEK KEGIATAN SUMBER DANA - BLUD
                        log.debug("************** KODE SUMBER DANA BLUD === 1 " + kodeblud);

                        List<Map> listnilaiblud = cetakValidasiSPMServices.getNilaiBlud(map);
                        BigDecimal nspp = (BigDecimal) listnilaiblud.get(0).get("V_SPP");
                        BigDecimal npot = (BigDecimal) listnilaiblud.get(0).get("V_POT");

                        /* -- revisi 6 Des 2018, parseInt max 9 char
                         Integer nilaispp = SipkdHelpers.getIntFromString(nspp.toString());
                         Integer nilaipot = SipkdHelpers.getIntFromString(npot.toString());

                         if (nilaispp > nilaipot) { // JIKA BELANJA > PENDAPATAN BLUD
                         return "Total Nilai Belanja BLUD tidak Boleh Lebih Besar dari Total Pendapatan BLUD";
                         } else {
                         cetakValidasiSPMServices.insertspmcetak(mapdetil);
                         }
                         */
                        if (nspp.compareTo(npot) == 1) { // JIKA BELANJA > PENDAPATAN BLUD
                            return "Total Nilai Belanja BLUD tidak Boleh Lebih Besar dari Total Pendapatan BLUD";
                        } else {
                            cetakValidasiSPMServices.insertspmcetak(mapdetil);
                        }

                    } else {
                        log.debug("xxxxxx");
                        cetakValidasiSPMServices.insertspmcetak(mapdetil);
                    }
                } else if ((kb.equals("LS")) && (kj.equals("BTL"))) {
                    final Map<String, Object> map1 = new HashMap<String, Object>(4);
                    map1.put("idspm", idspm);
                    map1.put("idspp", idspp);
                    map1.put("idskpd", idskpd);
                    map1.put("tahun", tahunAnggaran);

                    List<Map> listpotongan = cetakValidasiSPMServices.getNilaiPotonganBtl(map1);
                    BigDecimal npotsimpeg = (BigDecimal) listpotongan.get(0).get("POT_SIMPEG");
                    BigDecimal npotspm = (BigDecimal) listpotongan.get(0).get("POT_SPM");
                    log.debug("=============== masuk LS BTL ==============");
                    if (npotsimpeg.compareTo(BigDecimal.ZERO) == 1 && npotspm.compareTo(BigDecimal.ZERO) == 0) {
                        return "Total Potongan SPM Belum Diinput / Tidak Boleh Nol, Silahkan Input Potongan Terlebih Dulu";
                    } else {
                        log.debug("else LS BTL");
                        cetakValidasiSPMServices.insertspmcetak(mapdetil);
                    }
                } else {
                    log.debug("xxxxxx");
                    cetakValidasiSPMServices.insertspmcetak(mapdetil);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("ERROR ========= " + e.toString());
            return "No SPM ini, Tidak Ada Kegiatan dan Kontrak";

        }

        return "Simpan Data Cetak SPM Sukses";
    }

    @RequestMapping(value = "/json/hapusspmcetak",
            method = RequestMethod.POST)
    public @ResponseBody
    String hapusspmcetak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) throws IOException {
        /*
         final String pathhistory = servletContext.getInitParameter("PATH_HISTORY");
         final String tahun= (String) request.getSession().getAttribute("tahunAnggaran");
         final String namaFile = (String) mapdata.get("namafile");
         final String nospm = (String) mapdata.get("nospm");
         int idspm = (int) mapdata.get("idspd");
         final String spm = "/SPM/";
         boolean hasil = false;
         boolean hsl = false;
         String dirname = tahun;
         File dir = new File(pathhistory + dirname + spm);
         dir.mkdir();
         final String his = FilenameUtils.separatorsToUnix(dir.getAbsolutePath());
         final String hisspm = his + "/";
         final Map< String, Object> param = new HashMap<String, Object>(2);
         log.debug("Parameter Delete 1 ===> "+pathhistory+"--"+tahun+"--"+namaFile+" === "+nospm);
         param.put("tahun", tahun);
         param.put("nospm", nospm);
         param.put("value", idspm);
         List<SppUp> listSpm = cetakValidasiSPMServices.getPathFile(param);
         for (int i = 0; i < listSpm.size(); i++) {
         SppUp get = listSpm.get(i);
         String pathPdf = listSpm.get(i).getAlamatPdfOutput();
         String jenis = listSpm.get(i).getKodeJenis();
         String beban = listSpm.get(i).getKodeBeban();
         String level = listSpm.get(i).getLevelSkpd();
         String nihil = listSpm.get(i).getKodeNihil();
         final String lokasiDanNamaPdfAsal =  pathPdf+namaFile;
         final String lokasiDanNamaPdfTujuan =  hisspm+namaFile;
         log.debug("Parameter Delete 2 ===> "+pathPdf+"--"+nihil+"--"+lokasiDanNamaPdfAsal+"--"+lokasiDanNamaPdfTujuan);
         final int ids = cetakValidasiSPMServices.getbanyakspmcetakbtl5(param);
         final Map<String, Object> mapData = new HashMap<String, Object>(4);
         mapData.put("idspp", ids);
         final int byk = cetakValidasiSPMServices.getbanyakspmcetakbtl3(mapData);
         final int byk1 = cetakValidasiSPMServices.getbanyakspmcetakbtl4(mapData);
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
         return "Karena File PDF SPM Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
         }


         }else
         {
         if (asal.exists())
         {
         FileUtils.moveFile(asal, tujuan);
         }else
         {
         return "File PDF SPM Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPM Terbentuk Lalu Ulangi Lagi Proses Batal Cetak...";
         }



         }
         }else if (byk1 > 0)
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
         return "Karena File PDF SPM Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
         }


         }else
         {
         if (asal.exists())
         {
         FileUtils.moveFile(asal, tujuan);
         }else
         {
         return "File PDF SPM Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPM Terbentuk Lalu Ulangi Lagi Proses Batal Cetak...";
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
         return "Karena File PDF SPM Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
         }


         }else
         {
         if (asal.exists())
         {
         FileUtils.moveFile(asal, tujuan);
         }else
         {
         return "File PDF SPM Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPM Terbentuk Lalu Ulangi Lagi Proses Batal Cetak...";
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
         return "Karena File PDF SPM Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
         }


         }else
         {
         if (asal.exists())
         {
         FileUtils.moveFile(asal, tujuan);
         }else
         {
         return "File PDF SPM Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPM Terbentuk Lalu Ulangi Lagi Proses Batal Cetak...";
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
         return "Karena File PDF SPM Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
         }


         }else
         {
         if (asal.exists())
         {
         FileUtils.moveFile(asal, tujuan);
         }else
         {
         return "File PDF SPM Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPM Terbentuk Lalu Ulangi Lagi Proses Batal Cetak...";
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
         return "Karena File PDF SPM Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
         }


         }else
         {
         if (asal.exists())
         {
         FileUtils.moveFile(asal, tujuan);
         }else
         {
         return "File PDF SPM Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPM Terbentuk Lalu Ulangi Lagi Proses Batal Cetak...";
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
         return "Karena File PDF SPM Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
         }


         }else
         {
         if (asal.exists())
         {
         FileUtils.moveFile(asal, tujuan);
         }else
         {
         return "File PDF SPM Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPM Terbentuk Lalu Ulangi Lagi Proses Batal Cetak...";
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
         return "Karena File PDF SPM Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
         }


         }else
         {
         if (asal.exists())
         {
         FileUtils.moveFile(asal, tujuan);
         }else
         {
         return "File PDF SPM Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPM Terbentuk Lalu Ulangi Lagi Proses Batal Cetak...";
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
         return "Karena File PDF SPM Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
         }


         }else
         {
         if (asal.exists())
         {
         FileUtils.moveFile(asal, tujuan);
         }else
         {
         return "File PDF SPM Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPM Terbentuk Lalu Ulangi Lagi Proses Batal Cetak...";
         }



         }
         } else if (beban.equals("LS")) {

         if (nihil.equals("1")) {

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
         return "Karena File PDF SPM Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
         }


         }else
         {
         if (asal.exists())
         {
         FileUtils.moveFile(asal, tujuan);
         }else
         {
         return "File PDF SPM Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPM Terbentuk Lalu Ulangi Lagi Proses Batal Cetak...";
         }



         }

         }else
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
         return "Karena File PDF SPM Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
         }


         }else
         {
         if (asal.exists())
         {
         FileUtils.moveFile(asal, tujuan);
         }else
         {
         return "File PDF SPM Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPM Terbentuk Lalu Ulangi Lagi Proses Batal Cetak...";
         }



         }

         }



         }

         }

         }

         } */
        cetakValidasiSPMServices.deletespmcetak((Integer) mapdata.get("idspd"));
        return "Data SPM No " + mapdata.get("nospm") + " Berhasil Dibatalkan  ";
    }

    /*Tambahan batal dari anita*/
    @RequestMapping(value = "/json/hapusspmcetak1",
            method = RequestMethod.POST)
    public @ResponseBody
    String hapusspmcetak1(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) throws IOException {
        final String pathhistory = servletContext.getInitParameter("PATH_HISTORY");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String namaFile = (String) mapdata.get("namafile");
        final String nospm = (String) mapdata.get("nospm");
        int idspm = (int) mapdata.get("idspd");
        final String spm = "/SPM/";
        boolean hasil = false;
        boolean hsl = false;
        String dirname = tahun;
        File dir = new File(pathhistory + dirname + spm);
        dir.mkdir();
        final String his = FilenameUtils.separatorsToUnix(dir.getAbsolutePath());
        final String hisspm = his + "/";
        final Map< String, Object> param = new HashMap<String, Object>(2);
        log.debug("Parameter Delete 1 ===> " + pathhistory + "--" + tahun + "--" + namaFile + " === " + nospm);
        param.put("tahun", tahun);
        param.put("nospm", nospm);
        param.put("value", idspm);
        List<SppUp> listSpm = cetakValidasiSPMServices.getPathFile(param);
        for (int i = 0; i < listSpm.size(); i++) {
            SppUp get = listSpm.get(i);
            String pathPdf = listSpm.get(i).getAlamatPdfOutput();
            String jenis = listSpm.get(i).getKodeJenis();
            String beban = listSpm.get(i).getKodeBeban();
            String level = listSpm.get(i).getLevelSkpd();
            String nihil = listSpm.get(i).getKodeNihil();
            final String lokasiDanNamaPdfAsal = pathPdf + namaFile;
            final String lokasiDanNamaPdfTujuan = hisspm + namaFile;
            log.debug("Parameter Delete 2 ===> " + pathPdf + "--" + nihil + "--" + lokasiDanNamaPdfAsal + "--" + lokasiDanNamaPdfTujuan);
            final int ids = cetakValidasiSPMServices.getbanyakspmcetakbtl5(param);
            final Map<String, Object> mapData = new HashMap<String, Object>(4);
            mapData.put("idspp", ids);
            final int byk = cetakValidasiSPMServices.getbanyakspmcetakbtl3(mapData);
            final int byk1 = cetakValidasiSPMServices.getbanyakspmcetakbtl4(mapData);
            /*
             File asal = new File(lokasiDanNamaPdfAsal);
             File tujuan = new File(lokasiDanNamaPdfTujuan);
             log.debug("cek asal anitaikan="+asal);
             log.debug("cek tujuan anitaikan="+tujuan);
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
             return "Karena File PDF SPM Tidak Ada, Silahkan Ulangi Lagi Proses Batal Cetak...";
             }
             }else
             {
             if (asal.exists())
             {
             FileUtils.moveFile(asal, tujuan);
             }else
             {
             return "File PDF SPM Belum Terbentuk, Tunggu Beberapa Saat Hingga File PDF SPM Terbentuk Lalu Ulangi Lagi Proses Batal Cetak...";
             }
             } */
        }
        cetakValidasiSPMServices.deletespmcetak((Integer) mapdata.get("idspd"));
        return "Data SPM No " + mapdata.get("nospm") + " Berhasil Dibatalkan  ";
    }

    /**
     * ******
     *
     */
    @RequestMapping(value = "/{kodeBeban}/{kodeJenis}/{kodeNihil}/{id}/{nospm}/{iskpd}/{lskpd}/{namafile}", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response, @PathVariable String kodeBeban, @PathVariable String kodeJenis, @PathVariable String kodeNihil, @PathVariable Integer id, @PathVariable String nospm, @PathVariable String iskpd, @PathVariable String lskpd, @PathVariable String namafile) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        try {
            //final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");

            // System.out.println("pathReport " + pathReport);
            map.put("SUBREPORT_DIR", pathReport);
            map.put("value", id);
            map.put("kb", kodeBeban);
            map.put("kj", kodeJenis);
            map.put("ns", nospm);
            map.put("nf", namafile);
            map.put("ls", lskpd);
            map.put("np", cetakValidasiSPMServices.getnilaiparam(map));
            map.put("kn", kodeNihil);
            final int ids = cetakValidasiSPMServices.getbanyakspmcetakbtl5(map);

            final Map<String, Object> mapData = new HashMap<String, Object>(4);
            mapData.put("idspp", ids);

            final int byk = cetakValidasiSPMServices.getbanyakspmcetakbtl3(mapData);
            final int byk1 = cetakValidasiSPMServices.getbanyakspmcetakbtl4(mapData);
            List<Map> listhasil = cetakValidasiSPMServices.getnilaiparam(map);
            map.put("NAMA_DAERAH", listhasil.get(0).get("N_DAERAH_JUDUL"));
            map.put("NAMA_DAERAH_LOW", listhasil.get(0).get("N_DAERAH"));
            map.put("NO_PERDA", listhasil.get(0).get("I_PERDA_NO"));
            map.put("THN_PERDA", listhasil.get(0).get("C_PERDA_TAHUN"));
            map.put("TGL_PERDA", listhasil.get(0).get("C_PERDA_TGL"));
            map.put("NAMA_KOTA", listhasil.get(0).get("N_KOTA"));
            map.put("PERATURAN_1", listhasil.get(0).get("E_PERATURAN_SPD1"));
            map.put("PERATURAN_2", listhasil.get(0).get("E_PERATURAN_SPD2"));
            map.put("PERATURAN_3", listhasil.get(0).get("E_PERATURAN_SPD3"));
            map.put("PERATURAN_4", listhasil.get(0).get("E_PERATURAN_SPD4"));
            map.put("PERATURAN_5", listhasil.get(0).get("E_PERATURAN_SPD5"));

            // log.debug("ID SPP : "+ids);
            log.debug("banyak 3 : " + byk);
            log.debug("bayar bunga : " + byk1);
            log.debug(" no spm ========= " + nospm);

            if (lskpd.equals("3")) {
                if (kodeBeban.equals("LS")) {

                    if ((kodeJenis.equals("BTL"))) {

                        if (byk > 0) {
                            log.debug("====== : " + byk);
                            map.put("NOMOR_SPM", nospm);
                            map.put("ID_SKPD", iskpd);
                            map.put("TAHUN", tahunAnggaran);
                            map.put("pathreport", pathReport + "/Report_SPM-LS_BelanjaTTerduga.jasper");
                            JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                            //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPM-LS_BelanjaTTerduga.jasper", map, jdbcConnection);
                            final String nospdC = nospm;
                            final String filename = "SPP_LS_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                            response.setHeader("Content-Disposition", filename);
                            ServletOutputStream output = response.getOutputStream();
                            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                            output.close();
                        } else if (byk1 > 0) {
                            log.debug("!!!!!!!! : " + byk1);
                            map.put("NOMOR_SPM", nospm);
                            map.put("ID_SKPD", iskpd);
                            map.put("TAHUN", tahunAnggaran);
                            map.put("pathreport", pathReport + "/Report_SPM-LS_BelanjaTidakLangsung-PPKD.jasper");
                            JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                            //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPM-LS_BelanjaTidakLangsung-PPKD.jasper", map, jdbcConnection);
                            final String nospdC = nospm;
                            final String filename = "SPP_LS_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                            response.setHeader("Content-Disposition", filename);
                            ServletOutputStream output = response.getOutputStream();
                            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                            output.close();

                        }

                    } else if ((kodeJenis.equals("BIAYA"))) {

                        map.put("NOMOR_SPM", nospm);
                        map.put("ID_SKPD", iskpd);
                        map.put("TAHUN", tahunAnggaran);
                        map.put("pathreport", pathReport + "/Report_SPM-LS_Pembiayaan.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                        //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPM-LS_Pembiayaan.jasper", map, jdbcConnection);
                        final String nospdC = nospm;
                        final String filename = "SPP_LS_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                        response.setHeader("Content-Disposition", filename);
                        ServletOutputStream output = response.getOutputStream();
                        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                        output.close();

                    } else if ((kodeJenis.equals("BTL-BANTUAN"))) {

                        map.put("NOMOR_SPM", nospm);
                        map.put("ID_SKPD", iskpd);
                        map.put("TAHUN", tahunAnggaran);
                        map.put("pathreport", pathReport + "/Report_SPM-LS_HibahBantuan.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                        //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPM-LS_HibahBantuan.jasper", map, jdbcConnection);
                        final String nospdC = nospm;
                        final String filename = "SPP_LS_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                        response.setHeader("Content-Disposition", filename);
                        ServletOutputStream output = response.getOutputStream();
                        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                        output.close();

                    }
                }

            } else {
                if ((kodeJenis.equals("BTL"))) {

                    if (kodeBeban.equals("LS")) {

                        map.put("NOMOR_SPM", nospm);
                        map.put("ID_SKPD", iskpd);
                        map.put("TAHUN", tahunAnggaran);
                        map.put("pathreport", pathReport + "/Report_SPM-LS_BelanjaTidakLangsung.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                        //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPM-LS_BelanjaTidakLangsung.jasper", map, jdbcConnection);
                        final String nospdC = nospm;
                        final String filename = "SPP_LS_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                        response.setHeader("Content-Disposition", filename);
                        ServletOutputStream output = response.getOutputStream();
                        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                        output.close();
                    }

                } else if ((kodeJenis.equals("RESTITUSI"))) {
                    log.debug("------- :" + kodeJenis);
                    map.put("NOMOR_SPM", nospm);
                    map.put("ID_SKPD", iskpd);
                    map.put("TAHUN", tahunAnggaran);
                    map.put("pathreport", pathReport + "/Report_SPM-LS_Restitusi.jasper");
                    JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                    //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPM-LS_Restitusi.jasper", map, jdbcConnection);
                    final String nospdC = nospm;
                    final String filename = "SPP_LS_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                    response.setHeader("Content-Disposition", filename);
                    ServletOutputStream output = response.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                    output.close();

                } else if (kodeJenis.equals("BL")) {

                    if (kodeBeban.equals("UP")) {

                        map.put("NOMOR_SPM", nospm);
                        map.put("ID_SKPD", iskpd);
                        map.put("TAHUN", tahunAnggaran);
                        map.put("pathreport", pathReport + "/Report_SPM-UPGU.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                        //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPM-UPGU.jasper", map, jdbcConnection);
                        final String nospdC = nospm;
                        final String filename = "SPP_UP_GU_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                        map.put("namaFile", filename);
                        response.setHeader("Content-Disposition", filename);
                        ServletOutputStream output = response.getOutputStream();
                        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                        output.close();
                    } else if (kodeBeban.equals("TU")) {

                        map.put("NOMOR_SPM", nospm);
                        map.put("ID_SKPD", iskpd);
                        map.put("TAHUN", tahunAnggaran);
                        map.put("pathreport", pathReport + "/Report_SPM-TU.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                        //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPM-TU.jasper", map, jdbcConnection);
                        final String nospdC = nospm;
                        final String filename = "SPP_TUP_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                        response.setHeader("Content-Disposition", filename);
                        ServletOutputStream output = response.getOutputStream();
                        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                        output.close();
                    } else if (kodeBeban.equals("GU")) {

                        map.put("NOMOR_SPM", nospm);
                        map.put("ID_SKPD", iskpd);
                        map.put("TAHUN", tahunAnggaran);
                        map.put("pathreport", pathReport + "/Report_SPM-UPGU.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                        //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPM-UPGU.jasper", map, jdbcConnection);
                        final String nospdC = nospm;
                        final String filename = "SPP_GUP_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                        response.setHeader("Content-Disposition", filename);
                        ServletOutputStream output = response.getOutputStream();
                        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                        output.close();
                    } else if (kodeBeban.equals("LS")) {

                        if (kodeNihil.equals("1")) {

                            log.debug("Masuk Kode nihil");

                            map.put("NOMOR_SPM", nospm);
                            map.put("ID_SKPD", iskpd);
                            map.put("TAHUN", tahunAnggaran);
                            //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPM-LS_Nihil.jasper", map, jdbcConnection);
                            map.put("pathreport", pathReport + "/Report_SPM-LS_BelanjaBLUD.jasper");
                            JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                            //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPM-LS_BelanjaBLUD.jasper", map, jdbcConnection);
                            final String nospdC = nospm;
                            final String filename = "SPP_GUP_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                            response.setHeader("Content-Disposition", filename);
                            ServletOutputStream output = response.getOutputStream();
                            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                            output.close();

                        } else {

                            log.debug("Masuk BUKAN Kode nihil");
                            map.put("NOMOR_SPM", nospm);
                            map.put("ID_SKPD", iskpd);
                            map.put("TAHUN", tahunAnggaran);
                            map.put("pathreport", pathReport + "/Report_SPM-LS_BarangJasa.jasper");
                            JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                            //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SPM-LS_BarangJasa.jasper", map, jdbcConnection);
                            final String nospdC = nospm;
                            final String filename = "SPP_GUP_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                            response.setHeader("Content-Disposition", filename);
                            ServletOutputStream output = response.getOutputStream();
                            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                            output.close();
                        }

                    }

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    /**
     * ******
     *
     */
}
