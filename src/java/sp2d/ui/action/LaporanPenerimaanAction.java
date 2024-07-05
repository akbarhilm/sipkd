package sp2d.ui.action;

import java.sql.Connection;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import spp.model.Pengguna;
import sp2d.services.LaporanPnrmServices;
import sp2d.util.SipkdHelpers;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/lappnrm")
public class LaporanPenerimaanAction {

    private static final Logger log = LoggerFactory.getLogger(LaporanPenerimaanAction.class);
    @Autowired
    LaporanPnrmServices sp2dService;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/indexpnrmskpd", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return "laporanpenerimaan/penerimaanskpd";

    }
    
    @RequestMapping(value = "/indexpnrmharian", method = RequestMethod.GET)
    public String indexpnrmharian(HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return "laporanpenerimaan/rekappnrmharian";

    }

    @RequestMapping(value = "/json/prosescetakpnrmskpd", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        //final String kodewilayah = request.getParameter("kproses");
        final String idskpd = request.getParameter("idskpd");
        final String tanggal = request.getParameter("tgl");
        final String tgll = SipkdHelpers.getStringDateFormatFromString(tanggal, "yyyyMMdd", "dd/MM/yyyy");
        try {
            final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");
            final String data = request.getParameter("data");
            //log.debug(data);

            map.put("SUBREPORT_DIR", pathReport);
            //map.put("WILAYAH", kodewilayah);
            map.put("IDSKPD", idskpd);
            map.put("TAHUN", tahunAnggaran);
            map.put("TANGGAL", tgll);
            List<Map> listhasil = sp2dService.getnilaiparam(map);
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
            JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_Realisasi_Penerimaan_Skpd.jasper", map, jdbcConnection);
            final String filename = tahunAnggaran + "-" + "Penerimaan-SKPD" + "-" + idskpd + ".pdf";
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            response.setContentType("application/pdf");
            ServletOutputStream output = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            output.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
    
    @RequestMapping(value = "/json/prosescetakpnrmharian", method = RequestMethod.GET)
    public void prosescetakpnrmharian(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String kodewilayah = request.getParameter("wilayah");
        final String tanggal = request.getParameter("tgl");
        final String tgll = SipkdHelpers.getStringDateFormatFromString(tanggal, "yyyyMMdd", "dd/MM/yyyy");
        try {
            final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");
           
            map.put("SUBREPORT_DIR", pathReport);
            map.put("WILAYAH", kodewilayah);
            map.put("TAHUN", tahunAnggaran);
            map.put("TANGGAL", tgll);
            List<Map> listhasil = sp2dService.getnilaiparam(map);
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
            JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_Kotamadya_RekapBerkalaHarian.jasper", map, jdbcConnection);
            final String filename = tgll + "-" + "Rekap+Penerimaan-Harian" + ".pdf";
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            response.setContentType("application/pdf");
            ServletOutputStream output = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            output.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
    
    @RequestMapping(value = "/json/setWilayah", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> setWilayah(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
	
	mapData.put("aData", sp2dService.getWilayah(param));
        return mapData;
    }


    @RequestMapping(value = "/listskpdpnrm", method = RequestMethod.GET)
    public String listskpdinduk(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "laporanpenerimaan/listskpdpnrm";
    }
    
    @RequestMapping(value = "/json/listskpdpnrmjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("skpd");
        final String kodeskpd = request.getParameter("kodeskpd");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        param.put("skpd", skpd);
        param.put("tahun", tahunAnggaran);
        param.put("kodeskpd", kodeskpd);
        param.put("wilayah", pengguna.getKodeProses());
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = sp2dService.getBanyakListSkpdPnrm(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sp2dService.getListSkpdPnrm(param));
        return mapData;
    }


}
