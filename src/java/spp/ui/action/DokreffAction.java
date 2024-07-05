package spp.ui.action;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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

import spp.model.Dokreff;
import spp.services.DokreffServices;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/dokreff")
public class DokreffAction {

    private static final Logger log = LoggerFactory.getLogger(DokreffAction.class);
    @Autowired
    DokreffServices dokreffServices;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {

        return "/ref/dokreff/listdokreff";
    }


@RequestMapping(value = "/json/listjsondokreff", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsons(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String dokreff = request.getParameter("dokreff");
        param.put("dokreff", dokreff);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = dokreffServices.getCountDokreff(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", dokreffServices.getDokreff(param));
        return mapData;
    
    
    }
    
    @RequestMapping(value = "/adddokreff", method = RequestMethod.GET)
    public ModelAndView terserahmauapaja(final Dokreff dokreff) {
        return new ModelAndView("/ref/dokreff/adddokreff", "fungsicmda", dokreff);

    }
    
    @RequestMapping(value = "/simpandokreff", method = RequestMethod.POST)
    public String prosessimpan(@Valid @ModelAttribute("fungsicmda") Dokreff dokreff, BindingResult result) {
        if (result.hasErrors()) {
            return "/ref/dokreff/adddokreff";
        } else {
            dokreff.setIdEntry(9999);
            dokreff.setTglEntry(new Timestamp(System.currentTimeMillis()));
            dokreffServices.insertdokreff(dokreff);
            

        }
        return "redirect:/dokreff";
    }
    
    @RequestMapping(value = "/deldokreff/{id}", method = RequestMethod.GET)
    public ModelAndView deldokreff(@PathVariable Integer id) {
        final Dokreff dokreff = dokreffServices.getDokreffById(id);
        return new ModelAndView("/ref/dokreff/deldokreff", "spdBTLMaster", dokreff);
    }

    @RequestMapping(value = "/deletedokreff", method = RequestMethod.POST)
    public String deletedokreff(@Valid @ModelAttribute("spdBTLMaster") Dokreff dokreff) {
        dokreffServices.deletedokreff(dokreff.getIdDokreff());
        return "redirect:/dokreff";
    }
    
    @RequestMapping(value = "/updokreff/{id}", method = RequestMethod.GET)
    public ModelAndView editfungsi(@PathVariable Integer id) {
        final Dokreff dokreff = dokreffServices.getDokreffById(id);
        return new ModelAndView("/ref/dokreff/updatedokreff", "spdBTLMaster", dokreff);
    }

    @RequestMapping(value = "/updatedokreff", method = RequestMethod.POST)
    public String prosesupdate(@Valid @ModelAttribute("spdBTLMaster") Dokreff dokreff, BindingResult result) {
        System.out.println(" result.hasErrors() = " + result.hasErrors());
        if (result.hasErrors()) {
            return "/ref/dokreff/updatedokreff";
        } else {
            dokreff.setIdEntry(9999);
            dokreff.setTglEntry(new Timestamp(System.currentTimeMillis()));
            dokreffServices.updatedokreff(dokreff);
            dokreffServices.historydokreff(dokreff);

        }
        return "redirect:/dokreff";
    }
}