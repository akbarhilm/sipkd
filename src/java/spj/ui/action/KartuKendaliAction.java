/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
package spj.ui.action;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spj.services.CetakReportServices;
import spp.model.BukuKasUmum;
import spj.services.KartuKendaliServices;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SipkdHelpers;

import spj.util.SqlDatePropertyEditor;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/kartukendali")
public class KartuKendaliAction {

    private static final Logger log = LoggerFactory.getLogger(KartuKendaliAction.class);

    @Autowired
    CetakReportServices cetakReportServices;
    
    @Autowired
    KartuKendaliServices bkuServices;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/indexkartukendali", method = RequestMethod.GET)
    public ModelAndView index(final BukuKasUmum bku, final HttpServletRequest request) {

        return new ModelAndView("kartukendali/kartukendali", "refbku", bku);
    }

    @RequestMapping(value = "/listkegiatan", method = RequestMethod.GET)
    public ModelAndView listakunbukubesar(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("kartukendali/listkegiatan", "refkegiatan", bku);
    }

    @RequestMapping(value = "/json/listkegiatanjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listkegiatanjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kode = request.getParameter("kode");
        final String nama = request.getParameter("nama");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("nama", nama);
        param.put("kode", kode);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListKegiatan(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListKegiatan(param));

        return mapData;
    }

    @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String idskpd = request.getParameter("idskpd");
        final String bulan = request.getParameter("bulan");
        final String idkegiatan = request.getParameter("idkegiatan");

        try {
            //final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");

            map.put("SUBREPORT_DIR", pathReport);
            map.put("TAHUN", tahunAnggaran);
            map.put("ID_SKPD", idskpd);
            map.put("BULAN", bulan);
            map.put("ID_KEGIATAN", idkegiatan);

            List<Map> listhasil = bkuServices.getnilaiparam(map);
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

            map.put("pathreport", pathReport + "/Report_KartuKendali-Kegiatan.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
            //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_KartuKendali-Kegiatan.jasper", map, jdbcConnection);
            final String filename = tahunAnggaran + "-" + "Kartu Kendali Kegiatan" + ".pdf";
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            response.setContentType("application/pdf");
            ServletOutputStream output = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            output.close();

        } catch (Exception e) {
            //e.printStackTrace();
            e.getMessage();
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
