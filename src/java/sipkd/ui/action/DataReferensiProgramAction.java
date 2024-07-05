/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipkd.ui.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sipkd.services.ReferensiServices;
import sipkd.services.SpdService;

/**
 *
 * @author R Tarman
 */
@Controller
@RequestMapping("/ref/program")
public class DataReferensiProgramAction {

     @Autowired
    ReferensiServices referensiServices;
     
        @Autowired
    SpdService spdService;
    
    @RequestMapping(value = "/listprogram", method = RequestMethod.GET)
    public String index(final HttpServletRequest request) {
        request.setAttribute("listprogramjsp", referensiServices.getprogram());
        return "refprogram/index";
    }
    @RequestMapping(value = "/listprogram1", method = RequestMethod.GET)
    public String index1(final HttpServletRequest request) {
        request.setAttribute("listprogram1jsp", referensiServices.getprogram());
        return "refprogram/index1";
    }
    
    @RequestMapping(value = "/json/listurusanjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listurusanjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap< String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = referensiServices.getBanyakAllUrusan(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", referensiServices.getAllUrusan(param));
        return mapData;
    }
    
     
    @RequestMapping(value = "/json/listprogramjson", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> listprogramjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = referensiServices.getBanyakAllProgram(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", referensiServices.getAllProgram(param));
        return mapData;
    }

}
