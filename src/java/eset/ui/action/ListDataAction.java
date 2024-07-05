package eset.ui.action;

import eset.model.Eset;
import eset.model.EsetRinci;
import eset.model.LoginForm;
import java.io.IOException;
import java.security.Principal;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import eset.model.Pengguna;
import eset.services.ListDataServices;
import eset.services.UserManagementServices;
import eset.util.BigDecimalPropertyEditor;
import eset.util.PrintReportTemplate;
import eset.util.SipkdHelpers;
import eset.util.SqlDatePropertyEditor;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/listdatabank")
public class ListDataAction extends PrintReportTemplate {

    private static final Logger log = LoggerFactory.getLogger(ListDataAction.class);
    
        @Autowired
    ServletContext servletContext;
    
    @Autowired
    ListDataServices esetServices;
    /* private static final Properties properties = new Properties();

     static {
     try {
     properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("sso.properties"));
     } catch (IOException ex) {
     ex.printStackTrace();
     }
     }*/

    @RequestMapping(value = "/sp2d", method = RequestMethod.GET)
    public String index(final LoginForm login, final HttpServletRequest request) {
        request.getSession().setAttribute("tahunAnggaran", request.getSession().getAttribute("tahunAnggaran"));
        return "eset/sp2d/databank";
    }

    @RequestMapping(value = "/json/wilayah", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listwil(final HttpServletRequest request) {

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = esetServices.getBanyakListWil();
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", esetServices.getListWil());
        return mapData;
    }
    
    @RequestMapping(value = "/json/listsp2dsc", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsp2dSC(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String tahun = request.getParameter("tahun");
        final String tglb = request.getParameter("tglbayar");
        final String wilsp2d = request.getParameter("wilayah");
        final String nosp2d = request.getParameter("nosp2d");
        final String kodeskpd = request.getParameter("kodeskpd");
        final String sb = request.getParameter("statusbank");
        param.put("statusbank",sb);
        param.put("tahun", tahun);
        param.put("tglbayar", tglb);
        param.put("wilsp2d", wilsp2d);
         param.put("nosp2d", nosp2d);
        param.put("kodeskpd", kodeskpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = esetServices.getCS(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", esetServices.getSp2dTestSuc(param));
        return mapData;
    }
    
    
   
   
    @RequestMapping(value = "/json/sumsp2d", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> sumsp2d(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        
        final String tahun = request.getParameter("tahun");
        final String tglv = request.getParameter("tglvalid");
        final String wilsp2d = request.getParameter("wilayah");
        param.put("tahun", tahun);
        param.put("tglvalid", tglv);
        param.put("wilsp2d", wilsp2d);
   
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        
        mapData.put("sEcho", request.getParameter("sEcho"));
  
        mapData.put("aaData", esetServices.getSumSp2d(param));
        return mapData;
    }
    
    
    @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
       
        final String sb = request.getParameter("statusbank");
        final String tgl = request.getParameter("tglbayar");
        final String wil = request.getParameter("wilsp2d");
        final Map<String, Object> map = new HashMap<String, Object>();
        final String pathReport = servletContext.getInitParameter("PATH_REPORT");

        map.put("SUBREPORT_DIR", pathReport);
       
    
        if (sb.equals("1")) {
            map.put("TGL_PROSES", tgl);
            map.put("WIL_SP2D",wil);
            map.put("pathreport", pathReport + "/Report_SP2D-Berhasil.jasper");
            map.put("filename", tgl + " - Report_SP2D-Berhasil.pdf");
        }
        if (sb.equals("9")) {
            map.put("TAHUN_PROSES", tgl);
            map.put("WIL_SP2D",wil);
            map.put("pathreport", pathReport + "/Report_SP2D-Gagal.jasper");
            map.put("filename", tgl + " - Report_SP2D-Gagal.pdf");
        }
        
         if (sb.equals("0")) {
            map.put("TAHUN_PROSES", tgl);
            map.put("pathreport", pathReport + "/Report_SP2D-NoResponse.jasper");
             map.put("filename", tgl + " - Report_SP2D-NoResponse.pdf");
        }
        //map.put("TIPE", tipe);

//        List<Map> listhasil = cetakService.getnilaiparam(map);
//        map.put("NAMA_DAERAH", listhasil.get(0).get("N_DAERAH_JUDUL"));
//        map.put("NAMA_DAERAH_LOW", listhasil.get(0).get("N_DAERAH"));
//        map.put("NO_PERDA", listhasil.get(0).get("I_PERDA_NO"));
//        map.put("THN_PERDA", listhasil.get(0).get("C_PERDA_TAHUN"));
//        map.put("TGL_PERDA", listhasil.get(0).get("C_PERDA_TGL"));
//        map.put("NAMA_KOTA", listhasil.get(0).get("N_KOTA"));
//        map.put("PERATURAN_1", listhasil.get(0).get("E_PERATURAN_SPD1"));
//        map.put("PERATURAN_2", listhasil.get(0).get("E_PERATURAN_SPD2"));
//        map.put("PERATURAN_3", listhasil.get(0).get("E_PERATURAN_SPD3"));
//        map.put("PERATURAN_4", listhasil.get(0).get("E_PERATURAN_SPD4"));
//        map.put("PERATURAN_5", listhasil.get(0).get("E_PERATURAN_SPD5"));

       

        printReportToPdfStream(response, map);
    }

   
     @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}
