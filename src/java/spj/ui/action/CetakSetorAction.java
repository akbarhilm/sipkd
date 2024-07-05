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
import spj.services.CetakValidasiSETORServices;
import spj.util.SipkdHelpers;
import spp.model.Setor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/cetaksetor")
public class CetakSetorAction {
    
    private static final Logger log = LoggerFactory.getLogger(CetakSetorAction.class);
    @Autowired
    CetakReportServices cetakReportServices;
    @Autowired
    CetakValidasiSETORServices cetakValidasiSETORServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;
    
    @RequestMapping(value = "/indexsetor", method = RequestMethod.GET)
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
        return "cetak/setor/index";
        
    }
    
    @RequestMapping(value = "/json/getlistsetorcetak", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistsetorcetak(final HttpServletRequest request) {
        
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
        final int banyak = cetakValidasiSETORServices.getbanyaksetorcetak(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", cetakValidasiSETORServices.getlistsetorcetak(param));
        return mapData;
    }
    
    @RequestMapping(value = "/json/insertsetorcetak", method = RequestMethod.POST)
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
            cetakValidasiSETORServices.insertsetorcetak(mapdetil);
        }
        
        return "Simpan data cetak sukses";
        
    }
    
    @RequestMapping(value = "/json/hapussetorcetak",
            method = RequestMethod.POST)
    public @ResponseBody
    String hapusspmcetak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        cetakValidasiSETORServices.deletesetorcetak((Integer) mapdata.get("idspd"));
        return "Data Setoran No " + mapdata.get("nosetor") + " berhasil dibatalkan  ";
    }

    /**
     * ******
     *
     */
    @RequestMapping(value = "/{kodeBeban}/{kodeJenis}/{id}/{nosetor}/{iskpd}/{lskpd}/{namafile}", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response, @PathVariable String kodeBeban, @PathVariable String kodeJenis, @PathVariable Integer id, @PathVariable String nosetor, @PathVariable String iskpd, @PathVariable String lskpd, @PathVariable String namafile) {
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
            map.put("ns", nosetor);
            map.put("nf", namafile);
            map.put("ls", lskpd);
            map.put("np", cetakValidasiSETORServices.getnilaiparam(map));
            final Map<String, Object> mapData = new HashMap<String, Object>(4);
           // final int byk = cetakValidasiServices.getbanyaksppcetakbtl3(map);
           // mapData.put("banyak3", byk);
            
                            map.put("NOMOR_SETOR", nosetor);
                            map.put("ID_SKPD", iskpd);
                            map.put("TAHUN",tahunAnggaran);
                            List<Map> listhasil = cetakValidasiSETORServices.getnilaiparam(map);
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
                            //JasperPrint jasperPrint = JasperFillManager.fillReport(servletContext.getRealPath("WEB-INF/report/Report_SPP-UPGU.jasper"), map, jdbcConnection);
                            map.put("pathreport", pathReport + "/Report_SetoranBelanja.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                        //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_SetoranBelanja.jasper", map, jdbcConnection);
                            final String nospdC = nosetor;
                            final String filename = "SPP_LS_BL" + "." + tahunAnggaran + "." + nospdC + ".pdf";
                            response.setHeader("Content-Disposition", filename);
                            ServletOutputStream output = response.getOutputStream();
                            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                            output.close();
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
        
    }

    /**
     * ******
     *
     */
}
