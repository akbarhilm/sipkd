package eset.ui.action;

import eset.model.Eset;
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
import eset.util.SipkdHelpers;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/popup")
public class CommonListAction {

    private static final Logger log = LoggerFactory.getLogger(CommonListAction.class);
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
     public ModelAndView index(Eset eset,final HttpServletResponse response,final HttpServletRequest request) {
         final Map< String, Object> param = new HashMap<String, Object>(6);
        final String idsp2d =  request.getParameter("idsp2d");
         final String tahun = request.getParameter("tahun");
        final String tglv = request.getParameter("tglvalid");
        final String wilsp2d = request.getParameter("wilayah");
          param.put("tahun", tahun);
        param.put("tglvalid", tglv);
        param.put("wilsp2d", wilsp2d);
        param.put("idsp2d",idsp2d);
       response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        eset = esetServices.getSp2dById(param);
       
        return new ModelAndView("popup/sp2d","progcmd",eset);
        
        
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
        param.put("tahun", tahun);
        param.put("tglvalid", tglv);
        param.put("wilsp2d", wilsp2d);
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
}
