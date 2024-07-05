package spj.ui.action;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import spj.services.CetakReportServices;
import spp.model.Pengguna;
import spp.model.Skpd;
import spj.services.CetakValidasiSPJServices;
import spj.services.SpjServices;
import spj.util.SipkdHelpers;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/cetakspj")
public class CetakSpjAction {

    private static final Logger log = LoggerFactory.getLogger(CetakSpjAction.class);

    @Autowired
    CetakReportServices cetakReportServices;
    @Autowired
    CetakValidasiSPJServices cetakValidasiSPJServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/indexspj", method = RequestMethod.GET)
    public String cetak(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));

        }
        return "cetak/spj/cetakspjbl";

    }

    @RequestMapping(value = "/json/getlistspjcetak", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspdcetak(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Integer idskpd = listSkpd.get(0).getIdSkpd();
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
        param.put("idskpd", idskpd);
        log.debug(param.toString());
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = cetakValidasiSPJServices.getBanyakgetSpjCetakBl(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", cetakValidasiSPJServices.getSpjCetakBl(param));
        return mapData;

    }

    @RequestMapping(value = "/json/insertspjcetak", method = RequestMethod.POST)
    public @ResponseBody
    String submitcetak(@RequestBody List<Map<String, Object>> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String pathjasper = servletContext.getRealPath("/WEB-INF/report/");
        final String pathpdf = servletContext.getRealPath("/WEB-INF/pdf/");
        for (Map<String, Object> param : mapdata) {
            log.debug(" mapdata " + mapdata.toString());
            String idspj = (param.get("idspj")).toString();
            String statuscetak = (param.get("statuscetak")).toString();
            String nipPenggunaAnggaran = (param.get("nipPenggunaAnggaran")).toString();
            String nrkPenggunaAnggaran = (param.get("nrkPenggunaAnggaran")).toString();
            String namaPenggunaAnggaran = (param.get("namaPenggunaAnggaran")).toString();
            String nipVerifikator = (param.get("nipVerifikator")).toString();
            String nrkVerifikator = (param.get("nrkVerifikator")).toString();
            String namaVerifikator = (param.get("namaVerifikator")).toString();

            int spjid = SipkdHelpers.getIntFromString(idspj);
            int cetakstatus = SipkdHelpers.getIntFromString(statuscetak);
            int persen = (85/100)*100;
            log.debug("  id spj ================= "+ idspj);
            param.put("userid", pengguna.getIdPengguna());
            param.put("filejasper", pathjasper);
            param.put("filepdf", pathpdf);
            param.put("idspj", spjid);
            param.put("nipPenggunaAnggaran", nipPenggunaAnggaran);
            param.put("nrkPenggunaAnggaran", nrkPenggunaAnggaran);
            param.put("namaPenggunaAnggaran", namaPenggunaAnggaran);
            param.put("nipVerifikator", nipVerifikator);
            param.put("nrkVerifikator", nrkVerifikator);
            param.put("namaVerifikator", namaVerifikator);
            param.put("statuscetak", cetakstatus);
            param.put("tglentry", new Timestamp(System.currentTimeMillis()));
            
            log.debug("  param ================= "+ param.toString());
            
            cetakValidasiSPJServices.insertSpjCetak(param);
        }

        return "Simpan Data Cetak SPJ Sukses";

    }
    
    
     @RequestMapping(value = "/{idSpj}/{nospj}/{bulan}/{iskpd}/{namafile}", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer idSpj,@PathVariable String nospj,@PathVariable String bulan,@PathVariable String iskpd,@PathVariable String namafile) {
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
            map.put("value", idSpj);
            map.put("bl", bulan);
            map.put("ns", nospj);
            map.put("is", iskpd);
            map.put("nf", namafile);
            map.put("np", cetakValidasiSPJServices.getnilaiparam(map));
            final Map<String, Object> mapData = new HashMap<String, Object>(4);
            //final int byk = cetakValidasiSPJServices.getbanyaksppcetakbtl3(map);
            //mapData.put("banyak3", byk);
            
                        map.put("NOMOR_SPJ",nospj);
                        map.put("ID_SKPD", iskpd);
                        map.put("BULAN", bulan);
                        map.put("TAHUN",tahunAnggaran);
                        List<Map> listhasil = cetakValidasiSPJServices.getnilaiparam(map);
                        map.put("NAMA_DAERAH",listhasil.get(0).get("N_DAERAH_JUDUL"));
                        map.put("NAMA_DAERAH_LOW",listhasil.get(0).get("N_DAERAH"));
                        map.put("NO_PERDA",listhasil.get(0).get("I_PERDA_NO"));
                        map.put("THN_PERDA",listhasil.get(0).get("C_PERDA_TAHUN"));
                        map.put("TGL_PERDA",listhasil.get(0).get("C_PERDA_TGL"));
                        map.put("NAMA_KOTA",listhasil.get(0).get("N_KOTA"));
                        map.put("PERATURAN_1",listhasil.get(0).get("E_PERATURAN_SPD1"));
                        map.put("PERATURAN_2",listhasil.get(0).get("E_PERATURAN_SPD2"));
                        map.put("PERATURAN_3",listhasil.get(0).get("E_PERATURAN_SPD3"));
                        map.put("PERATURAN_4",listhasil.get(0).get("E_PERATURAN_SPD4"));
                        map.put("PERATURAN_5",listhasil.get(0).get("E_PERATURAN_SPD5"));
                        //JasperPrint jasperPrint = JasperFillManager.fillReport(servletContext.getRealPath("WEB-INF/report/Report_SPP-UPGU.jasper"), map, jdbcConnection);
                        map.put("pathreport", pathReport + "/Report_SPJ_BelanjaLangsung.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                        //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_SPJ_BelanjaLangsung.jasper", map, jdbcConnection);
                        final String nospdC = nospj;
                        final String filename = "SPP_LS_BL"+"."+tahunAnggaran+"."+nospdC+".pdf";
                        response.setHeader("Content-Disposition",filename);
                         ServletOutputStream output = response.getOutputStream();
                         JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                         output.close();
                  
                      
       
            
     
    } catch (Exception e) {

            e.printStackTrace();

        }

    }
    
    
    
    
    

    @RequestMapping(value = "/json/hapusspjcetak", method = RequestMethod.POST)
    public @ResponseBody
    String hapussppcetak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        int idspj = (Integer) mapdata.get("idspj");
        cetakValidasiSPJServices.deleteSpjCetak(idspj);
        return "Data SPJ No " + mapdata.get("nospj") + " berhasil dibatalkan  ";
    }

}
