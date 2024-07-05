package spj.ui.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import spj.services.ReferensiServices;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/ref")
public class BendaharaAction {

    private static final Logger log = LoggerFactory.getLogger(BendaharaAction.class);
    @Autowired
    ReferensiServices referensiServices;

    @RequestMapping(value = "/listbendahara",method = RequestMethod.GET)
    public String index() {
        return "/ref/bendahara/listbendahara";

    }

    @RequestMapping(value = "/json/listbendaharajson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdljson(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("namabendahara");
        final String namabendahara = request.getParameter("namabendahara");
        param.put("nama", namabendahara);
        param.put("skpd", skpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = referensiServices.getBanyakBendaharaSpp(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", referensiServices.getAllBendaharaSpp(param));
        return mapData;
    }
}
