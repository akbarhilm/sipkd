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
import eset.services.EsetServices;
import eset.services.UserManagementServices;
import eset.util.BigDecimalPropertyEditor;
import eset.util.SipkdHelpers;
import eset.util.SqlDatePropertyEditor;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/eset")
public class EsetAction {

    private static final Logger log = LoggerFactory.getLogger(EsetAction.class);
    @Autowired
    EsetServices esetServices;
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
        return "eset/sp2d/index";
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

    @RequestMapping(value = "/json/listsp2d", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsp2d(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String tahun = request.getParameter("tahun");
        final String tglv = request.getParameter("tglvalid");
        final String wilsp2d = request.getParameter("wilayah");
        final String nosp2d = request.getParameter("nosp2d");
        final String kodeskpd = request.getParameter("kodeskpd");
        param.put("tahun", tahun);
        param.put("tglvalid", tglv);
        param.put("wilsp2d", wilsp2d);
         param.put("nosp2d", nosp2d);
        param.put("kodeskpd", kodeskpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = esetServices.getBanyakListSp2dApp(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", esetServices.getListSp2dApp(param));
        return mapData;
    }
    ////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/json/listsp2dsc", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsp2dSC(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String tahun = request.getParameter("tahun");
        final String tglv = request.getParameter("tglvalid");
        final String wilsp2d = request.getParameter("wilayah");
        final String nosp2d = request.getParameter("nosp2d");
        final String kodeskpd = request.getParameter("kodeskpd");
        final String sb = request.getParameter("statusbank");
        param.put("statusbank",sb);
        param.put("tahun", tahun);
        param.put("tglvalid", tglv);
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
    
    @RequestMapping(value = "/json/listsp2df", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsp2df(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String tahun = request.getParameter("tahun");
        final String tglv = request.getParameter("tglvalid");
        final String wilsp2d = request.getParameter("wilayah");
        final String nosp2d = request.getParameter("nosp2d");
        final String kodeskpd = request.getParameter("kodeskpd");
        param.put("tahun", tahun);
        param.put("tglvalid", tglv);
        param.put("wilsp2d", wilsp2d);
         param.put("nosp2d", nosp2d);
        param.put("kodeskpd", kodeskpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = esetServices.getCF(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", esetServices.getSp2dTestFail(param));
        return mapData;
    }
    
    @RequestMapping(value = "/json/listsp2dnr", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsp2dnr(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String tahun = request.getParameter("tahun");
        final String tglv = request.getParameter("tglvalid");
        final String wilsp2d = request.getParameter("wilayah");
        final String nosp2d = request.getParameter("nosp2d");
        final String kodeskpd = request.getParameter("kodeskpd");
        param.put("tahun", tahun);
        param.put("tglvalid", tglv);
        param.put("wilsp2d", wilsp2d);
         param.put("nosp2d", nosp2d);
        param.put("kodeskpd", kodeskpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = esetServices.getCNR(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", esetServices.getSp2dTestNR(param));
        return mapData;
    }
    ////////////////////////////////////////////////////////////////////////////
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
    
    @RequestMapping(value = "/json/prosessp2d", method = RequestMethod.POST)
    public @ResponseBody
    String prosessp2d(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("listid");
        List<EsetRinci> listRinci = new ArrayList<>();
       
        Eset eset = new Eset();
        for (Map<String, Object> mapnilailist : nilailist) {
            EsetRinci esrin = new EsetRinci();
             Object idsp2d = mapnilailist.get("idsp2d");
             log.debug("xxx"+idsp2d.toString());
             esrin.setIdSp2d(SipkdHelpers.getIntFromString((String) idsp2d));
             esrin.setIdEntry(pengguna.getIdPengguna());
             
             listRinci.add(esrin);
        
        }

        eset.setListesrin(listRinci);
        esetServices.updateSp2d(eset);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }
     @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}
