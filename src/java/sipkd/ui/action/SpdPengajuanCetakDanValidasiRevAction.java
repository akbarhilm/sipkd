package sipkd.ui.action;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
//import java.nio.file.Paths;
import java.nio.file.Files;
import org.apache.commons.io.FileUtils;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.Timestamp;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sipkd.model.Pengguna;
import sipkd.model.SpdBTLMaster;
import sipkd.services.ReferensiServices;
import sipkd.services.SpdRevService;
import sipkd.util.SipkdHelpers;

/**
 *
 * @author sapto
 */
@Controller
@RequestMapping("/spd/pengajuancetakrev")
public class SpdPengajuanCetakDanValidasiRevAction {

    private static final Logger log = LoggerFactory.getLogger(SpdPengajuanCetakDanValidasiRevAction.class);
    @Autowired
    SpdRevService spdRevService;

    @Autowired
    ReferensiServices referensiServices;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/indexcetakrev", method = RequestMethod.GET)
    public String cetak() {
        return "spd/pengajuanspd/cetakrev";

    }

    /**
     * ******
     *
     */
    @RequestMapping(value = "/{jenis}/{idspd}/{nospd}/{namafile}", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response, @PathVariable String jenis, @PathVariable Integer idspd, @PathVariable String nospd, @PathVariable String namafile) {

        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        try {
            final Connection jdbcConnection = dataSource.getConnection();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final Map<String, Object> map = new HashMap<String, Object>();
            //servletContext.getInitParameter("PATH_REPORT");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");
            map.put("SUBREPORT_DIR", pathReport);
            map.put("value", idspd);//D:\New SIPKD\Report\ PropertiesAplikasi.PATH_REPORT   servletContext.getRealPath("/WEB-INF/report/SPD_BelanjaTidakLangsung_Lampiran.jasper")
            map.put("jns", jenis);
            map.put("ns", nospd);
            map.put("nf", namafile);
            map.put("tahun", tahunAnggaran);
            map.put("np", spdRevService.getnilaiparam(map));
             List<Map> listhasil = spdRevService.getnilaiparam(map);
             List<Map> listhasil1 = spdRevService.getnilaiparam1(map);
               map.put("NAMA_DAERAH",listhasil.get(0).get("N_DAERAH_JUDUL"));
               map.put("NAMA_DAERAH_LOW",listhasil.get(0).get("N_DAERAH"));
               map.put("NO_PERDA",listhasil.get(0).get("I_PERDA_NO"));
               map.put("THN_PERDA",listhasil.get(0).get("C_PERDA_TAHUN"));
               map.put("TGL_PERDA",listhasil.get(0).get("C_PERDA_TGL"));
               map.put("NAMA_KOTA",listhasil.get(0).get("N_KOTA"));
               map.put("PERATURAN_1",listhasil1.get(0).get("E_PERATURAN_SPD1"));
               map.put("PERATURAN_2",listhasil1.get(0).get("E_PERATURAN_SPD2"));
               map.put("PERATURAN_3",listhasil.get(0).get("E_PERATURAN_SPD3"));
               map.put("PERATURAN_4",listhasil.get(0).get("E_PERATURAN_SPD4"));
               map.put("PERATURAN_5",listhasil1.get(0).get("E_PERATURAN_SPD5"));
               map.put("PERATURAN_6",listhasil1.get(0).get("E_PERATURAN_SPD6"));
               map.put("PERATURAN_7",listhasil.get(0).get("E_PERATURAN_SPD7"));
               
            if (jenis.equals("BTL")) {
                map.put("SUBREPORT_DIR", pathReport);
                map.put("NOMOR_SPD", nospd);
                map.put("TAHUN", tahunAnggaran);
                JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_SPD_BelanjaTidakLangsung.jasper", map, jdbcConnection);
                final String nospdC = nospd;
                final String filename = "SPDRevBTL"+"."+tahunAnggaran+"."+nospdC+".pdf";
                map.put("namaFile",filename);
                response.setHeader("Content-Disposition",filename);
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
            } else if (jenis.equals("BL")) {
                map.put("SUBREPORT_DIR", pathReport);
                map.put("NOMOR_SPD", nospd);
                map.put("TAHUN", tahunAnggaran);
                JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_SPD_BelanjaLangsung.jasper", map, jdbcConnection);
               final String nospdC = nospd;
               final String filename = "SPDRevBL"+"."+tahunAnggaran+"."+nospdC+".pdf";
               map.put("namaFile",filename);
               response.setHeader("Content-Disposition",filename);
               ServletOutputStream output = response.getOutputStream();
               JasperExportManager.exportReportToPdfStream(jasperPrint, output);
               output.close();
            } else if (jenis.equals("BTL BANTUAN")) {
                map.put("SUBREPORT_DIR", pathReport);
                map.put("NOMOR_SPD", nospd);
                map.put("TAHUN", tahunAnggaran);
                JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_SPD_BelanjaBantuan.jasper", map, jdbcConnection);
               final String nospdC = nospd;
               final String filename = "SPDRevBTLBANTUAN"+"."+tahunAnggaran+"."+nospdC+".pdf";
               map.put("namaFile",filename);
               response.setHeader("Content-Disposition", filename);
               ServletOutputStream output = response.getOutputStream();
               JasperExportManager.exportReportToPdfStream(jasperPrint, output);
               output.close();
            } else if (jenis.equals("BIAYA")) {
                map.put("SUBREPORT_DIR", pathReport);
                map.put("NOMOR_SPD", nospd);
                map.put("TAHUN", tahunAnggaran);
                JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_SPD_Pembiayaan.jasper", map, jdbcConnection);
                final String nospdC = nospd;
                final String filename = "SPDRevBIAYA"+"."+tahunAnggaran+"."+nospdC+".pdf";
                map.put("namaFile",filename);
                response.setHeader("Content-Disposition",filename);
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
            }

           
        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    /**
     * ******
     *
     */
    @RequestMapping(value = "/json/insertspdcetak", method = RequestMethod.POST)
    public @ResponseBody
    String submitcetak(@RequestBody List<Map<String, Object>> mapdata, final HttpServletRequest request, HttpServletResponse response) {
        servletContext.getInitParameter("PATH_REPORT");
       final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String pathjasper = servletContext.getInitParameter("PATH_REPORT");
        final String pathpdf = servletContext.getInitParameter("PATH_PDF");
        log.debug("==== PDF 0===> "+pathpdf);
        final String spd = "/SPD/";
        boolean hasil = false;
        boolean hsl = false;
        String dirname = tahunAnggaran;
        File dir = new File(pathpdf + dirname + spd);
        dir.mkdir();
         final String pdf = FilenameUtils.separatorsToUnix(dir.getAbsolutePath());// dirspd+"\\";
         final String pdfspd = pdf + "/";
        log.debug("==== PDF 3===> " + pdf);
        for (Map<String, Object> mapdetil : mapdata) {
           // mapdetil.put("userid", pengguna.getIdPengguna());
             try {
                mapdetil.put("userid", pengguna.getIdPengguna());
            } catch (Exception e) {
                mapdetil.put("userid", 0);
            }
            //mapdetil.put("filejasper", pathjasper);
            mapdetil.put("filepdf", pdfspd); 
           // mapdetil.put("tahun", tahunAnggaran);
           // mapdetil.put("statcam", "3"); 
            mapdetil.put("tglentry", new Timestamp(System.currentTimeMillis()));
            mapdetil.put("stat", "2");
            log.debug(" ==> " + mapdetil);
            spdRevService.updatespdcetak(mapdetil);
        }

        return "Simpan Data Cetak SPD APBDP Sukses";

    }

    @RequestMapping(value = "/json/getlistspdcetak", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspdcetak(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String id = request.getParameter("id");
        log.debug("id  = " + id);
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String nilaispd = request.getParameter("nilaiSpd");
        final String kode = request.getParameter("kode");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", id);
        param.put("nilaispd", SipkdHelpers.getBigDecimalFromString(nilaispd));
        param.put("kodeDok", kode);
        log.debug(param.toString());
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = spdRevService.getbanyakcetakspdbtl(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdRevService.getlistcetakspdbtl(param));
        return mapData;

    }

    @RequestMapping(value = "/json/getlistspdvalidasi", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspdvalidasi(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String id = request.getParameter("id");
        log.debug("id  = " + id);
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", id);
        log.debug(param.toString());
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = spdRevService.getbanyakvalidasispd(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdRevService.getlistvalidasispd(param));
        return mapData;

    }

    @RequestMapping(value = "/validasirev", method = RequestMethod.GET)
    public String validasi(final HttpServletResponse response, final HttpServletRequest request) throws IOException {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        boolean status = false;
        String ipaddr = null;
          try {
             ipaddr = pengguna.getIpAddress();
        } catch (Exception e) {
        }
        if (ipaddr != null && StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotBlank(ipaddr)) {
            //status = new IpAddressMatcher(ipaddr).matches(request) || new IpAddressMatcher("127.0.0.1").matches(request);
            //status = Objects.equals(ipaddr, SipkdHelpers.getIpAddr(request)) || new IpAddressMatcher("127.0.0.1").matches(request)  || new IpAddressMatcher("0:0:0:0:0:0:0:1").matches(request);
            status = Objects.equals(ipaddr, SipkdHelpers.getIpAddr(request)) || new IpAddressMatcher("10.10.40.162").matches(request) || new IpAddressMatcher("0:0:0:0:0:0:0:1").matches(request);
        }
        log.debug(ipaddr + " status = " + status + " request " + SipkdHelpers.getIpAddr(request));
        //status = true;
        if (status) {
            return "spd/pengajuanspd/validasirev";
        } else {
            // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Anda tidak berhak mengakses halaman pengesahan");
            return "deny";
        }

    }

    /*   @RequestMapping(value = "/json/submitvalidasi", method = RequestMethod.POST)
     public @ResponseBody
     String submitvalidasi(@RequestBody List<Map<String,Object>> listidspd, final HttpServletRequest request) {
     final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
     for (Map<String,Object> mapspd : listidspd) {
     //(#{idspd},#{userid},#{tglentry} ,#{tglentry})
     final Map<String, Object> parameter = new HashMap<String, Object>();
     parameter.put("idspd", mapspd.get("idspd") );
     parameter.put("nilaispd", mapspd.get("nilaispd") );
     parameter.put("tglentry", new Timestamp(System.currentTimeMillis()));
     parameter.put("userid", pengguna.getIdPengguna());
     //log.info(parameter.toString());
     spdRevService.insertspdsah(parameter);
     }

     return "Pengesahan sukses";

     }*/
    @RequestMapping(value = "/json/submitvalidasi", method = RequestMethod.POST)
    public @ResponseBody
    String submitvalidasi(@RequestBody List<Map<String, Object>> listidspd, final HttpServletRequest request, HttpServletResponse response) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        for (Map<String, Object> mapspd : listidspd) {
            //(#{idspd},#{userid},#{tglentry} ,#{tglentry})
            final Map<String, Object> parameter = new HashMap<String, Object>();
            final String jspd = (String) mapspd.get("jenisspd");
            parameter.put("idspd", mapspd.get("idspd"));
            parameter.put("nilaispd", mapspd.get("nilaispd"));
            //parameter.put("jenisspd", mapspd.get("jenisspd"));
            parameter.put("jenisspd", jspd);
            parameter.put("tglentry", new Timestamp(System.currentTimeMillis()));
            // parameter.put("userid", pengguna.getIdPengguna());
            parameter.put("stat", "2");
            log.debug("isi param  =====> " + parameter);
           
            if (jspd.equals("BTL")) {
                 //parameter.put("jenis", "1");
                parameter.put("jenisspd", jspd);
                spdRevService.updatespdsahrincibtl(parameter);
            } else if (jspd.equals("BL")) {
                // parameter.put("jenis", "2");
                parameter.put("jenisspd", jspd);
                spdRevService.updatespdsahrincibl(parameter);
            } else if (jspd.equals("BTL BANTUAN")) {
                 // parameter.put("jenis", "3");
                parameter.put("jenisspd", jspd);
                spdRevService.updatespdsahrincibantuan(parameter);
            } else if (jspd.equals("BIAYA")) {
                 // parameter.put("jenis", "4");
                parameter.put("jenisspd", jspd);
                spdRevService.updatespdsahrincibiaya(parameter);
            }
            log.debug("isi param jenis =====> " + parameter);
             spdRevService.updatespdsah(parameter);

        }

        return "Simpan Data Pengesahan SPD APBDP Sukses";

    }

    @RequestMapping(value = "/printvalidasi/cetakpengesahan/{idspd}", method = RequestMethod.GET)
    public String cetakpengesahan(final HttpServletRequest request, @PathVariable String idspd, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        request.setAttribute("dataspd", spdRevService.getspdsahbyidspd(SipkdHelpers.getIntFromString(idspd)));
        request.setAttribute("nospd", idspd);
        request.setAttribute("ispd", spdRevService.getspdsahid(SipkdHelpers.getIntFromString(idspd)));
        request.setAttribute("no", spdRevService.getspdsahnomor(SipkdHelpers.getIntFromString(idspd)));

        return "spd/pengajuanspd/cetakvalidasirev";

    }

    @RequestMapping(value = "/print/cetakbarcodepengesahanbynospd/{ispd}", method = RequestMethod.GET)
    public void cetakpengesahan(final HttpServletResponse response, @PathVariable String ispd) throws WriterException, IOException {
        BitMatrix bitMatrix = new Code128Writer().encode(ispd, BarcodeFormat.CODE_128, 90, 40, null);
        MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());

    }

    @RequestMapping(value = "/json/batalspdcetak",
            method = RequestMethod.POST)
    public @ResponseBody
    //String batalspdcetak(@RequestBody List<Map<String, Object>> mapdata, final HttpServletRequest request, HttpServletResponse response) {
    String batalspdcetak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        // for (Map<String, Object> mapdetil : mapdata) {
        mapdata.put("tglentry", new Timestamp(System.currentTimeMillis()));
        mapdata.put("stat", "P");
        log.debug("BATAL ==> " + mapdata);
        spdRevService.updatespdcetak1(mapdata);
        // }
        //spdRevService.updatespdcetak1((Integer) mapdata.get("idspd"));
        return "Data SPD APBDP Berhasil Dibatalkan  ";
    }

}
