package spj.ui.action;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spp.model.Pengguna;
import spp.model.Skpd;
import spj.services.ReferensiServices;
import spj.util.SipkdHelpers;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/refskpd")
public class ReferensiSkpdAction {

    private static final Logger log = LoggerFactory.getLogger(ReferensiSkpdAction.class);
    @Autowired
    ReferensiServices referensiServices;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(final HttpServletRequest request) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        return "ref/skpd/index";
    }

    @RequestMapping(value = "/addskpd/{id}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable Integer id, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        Skpd skpd = referensiServices.getSkpdById(id);
        return new ModelAndView("/ref/skpd/addskpd", "spdBTLMaster", skpd);
    }

    @RequestMapping(value = "/simpanskpd", method = RequestMethod.POST)
    public String prosessimpans(@Valid @ModelAttribute("spdBTLMaster") Skpd skpd, BindingResult result, final HttpServletRequest request, final HttpServletResponse response) {
               response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        if (result.hasErrors()) {
            
            return "/ref/skpd/addskpd";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            skpd.setIdEntry(pengguna.getIdPengguna());
            skpd.setTglEntry(new Timestamp(System.currentTimeMillis()));
            referensiServices.insertSkpd(skpd);

        }
        return "redirect:/refskpd/index";

    }

    @RequestMapping(value = "/json/listdataskpdrootjson", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> listdataskpdrootjson() {
        return referensiServices.getAllSkpdRoot();
    }

    @RequestMapping(value = "/json/listdataskpdanakjson", method = RequestMethod.GET)
    public @ResponseBody
    List<Skpd> listdataskpdanakjson(final HttpServletRequest request) {
        final Integer id = SipkdHelpers.getIntFromString(request.getParameter("id"));
        final Map map = new HashMap(1);
        map.put("induk", id);
        return referensiServices.getAllSkpdAnak(map);
    }

    @RequestMapping(value = "/editskpd/{id}", method = RequestMethod.GET)

    public ModelAndView editskpd(@PathVariable Integer id, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        Skpd skpd = referensiServices.getSkpdById(id);
        return new ModelAndView("/ref/skpd/editskpd", "spdBTLMaster", skpd);
    }

    @RequestMapping(value = "/editskpds", method = RequestMethod.POST)
    public String prosesupdate(@Valid @ModelAttribute("spdBTLMaster") Skpd skpd, BindingResult result, final HttpServletRequest request, final HttpServletResponse response) {
        System.out.println(" result.hasErrors() = " + result.hasErrors());
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        if (result.hasErrors()) { 
            
            return "/ref/skpd/editskpd";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            skpd.setIdEdit(pengguna.getIdPengguna());
            skpd.setTglEdit(new Timestamp(System.currentTimeMillis()));
            referensiServices.editSkpd(skpd);
        }
        return "redirect:/refskpd/index";
    }

    @RequestMapping(value = "/json/urusanlistpopup", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsonrekanan(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("urusan");
        param.put("urusan", skpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = referensiServices.getBanyakAllUrusan(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", referensiServices.getUrusan(param));
        return mapData;
    }

    @RequestMapping(value = "/urusanlistpopup", method = RequestMethod.GET)
    public ModelAndView listkontrak(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("/ref/skpd/urusanlistpopup");
    }

}
