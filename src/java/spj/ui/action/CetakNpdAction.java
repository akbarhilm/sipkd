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
import spj.services.CetakValidasiNPDServices;
import spj.util.SipkdHelpers;
import spp.model.Setor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/cetaknpd")
public class CetakNpdAction {
    
    private static final Logger log = LoggerFactory.getLogger(CetakNpdAction.class);
    @Autowired
    CetakReportServices cetakReportServices;
    @Autowired
    CetakValidasiNPDServices cetakValidasiNPDServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;
    
    @RequestMapping(value = "/indexnpd", method = RequestMethod.GET)
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
        return "cetak/npd/index";
        
    }
    
    @RequestMapping(value = "/json/getlistnpdcetak", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistnpdcetak(final HttpServletRequest request) {
        
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
        final int banyak = cetakValidasiNPDServices.getbanyaknpdcetak(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", cetakValidasiNPDServices.getlistnpdcetak(param));
        return mapData;
    }
    
    @RequestMapping(value = "/json/insertnpdcetak", method = RequestMethod.POST)
    public @ResponseBody
    String submitcetakspm(@RequestBody List<Map<String, Object>> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String pathjasper = servletContext.getRealPath("/WEB-INF/report/");
        final String pathpdf = servletContext.getRealPath("/WEB-INF/pdf/");
        for (Map<String, Object> mapdetil : mapdata) {
            mapdetil.put("userid", pengguna.getIdPengguna());
            mapdetil.put("filejasper", pathjasper);
            mapdetil.put("filepdf", pathpdf);
           // mapdetil.put("terima", "1");
            mapdetil.put("tglentry", new Timestamp(System.currentTimeMillis()));
            cetakValidasiNPDServices.insertnpdcetak(mapdetil);
        }
        
        return "Simpan data cetak sukses";
        
    }
    
    @RequestMapping(value = "/json/hapusnpdcetak",
            method = RequestMethod.POST)
    public @ResponseBody
    String hapusspmcetak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        cetakValidasiNPDServices.deletenpdcetak((Integer) mapdata.get("idNpd"));
        return "Data NPD No " + mapdata.get("nonpd") + " berhasil dibatalkan  ";
    }

    /**
     * ******
     *
     */
    @RequestMapping(value = "/{idNpd}/{noNpd}/{iskpd}/{lskpd}/{namafile}", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response,  @PathVariable Integer idNpd, @PathVariable String noNpd, @PathVariable String iskpd, @PathVariable String lskpd, @PathVariable String namafile) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        try {
            //final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");
            // System.out.println("pathReport " + pathReport);
            map.put("SUBREPORT_DIR", pathReport);
            map.put("value", idNpd);
            map.put("ns", noNpd);
            map.put("nf", namafile);
            map.put("ls", lskpd);
            map.put("np", cetakValidasiNPDServices.getnilaiparam(map));
            final Map<String, Object> mapData = new HashMap<String, Object>(4);
            //final int byk = cetakValidasiNPDServices.getbanyaksppcetakbtl3(map);
          //  mapData.put("banyak3", byk);
          //  log.debug(" no spm ========= " + noNpd);
            
                            map.put("NOMOR_NPD", noNpd);
                            map.put("ID_SKPD", iskpd);
                            map.put("TAHUN",tahunAnggaran);
                            List<Map> listhasil = cetakValidasiNPDServices.getnilaiparam(map);
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
                            map.put("pathreport", pathReport + "/Report_NPD.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                            //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_NPD.jasper", map, jdbcConnection);
                            final String nospdC = noNpd;
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
