package sipkd.ui.action; 

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

import sipkd.model.Dokttd;
import sipkd.services.DokttdServices;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/dokttd")
public class DokttdAction {

    private static final Logger log = LoggerFactory.getLogger(DokttdAction.class);
    @Autowired
    DokttdServices dokttdServices;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {

        return "/ref/dokttd/listdokttd";
    }

    @RequestMapping(value = "/adddokttd", method = RequestMethod.GET)
    public ModelAndView terserahmauapaja(Dokttd dokttd) {
        return new ModelAndView("/ref/dokttd/adddokttd", "fungsicmda", dokttd);

   }

    @RequestMapping(value = "/json/getlistdokttd", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsons(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("dokttd");
        param.put("dokttd", skpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = dokttdServices.getCountDokttd(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", dokttdServices.getDokttd(param));
        return mapData;
    }
    
    @RequestMapping(value = "/simpandokttd", method = RequestMethod.POST)
    public String prosessimpan(@Valid @ModelAttribute("fungsicmda") Dokttd dokttd, BindingResult result) {
        if (result.hasErrors()) {
            return "/ref/dokttd/adddokttd";
        } else {
            
            dokttd.setIdEntry(9999);
            dokttd.setTglEntry(new Timestamp(System.currentTimeMillis()));
            dokttdServices.insertdokttd(dokttd);

        }
        return "redirect:/dokttd";
    }
    
    @RequestMapping(value = "/updatedokttd/{id}", method = RequestMethod.GET)
    public ModelAndView editfungsi(@PathVariable Integer id) {
        final Dokttd dokttd = dokttdServices.getDokttdById(id);
        return new ModelAndView("/ref/dokttd/updatedokttd", "spdBTLMaster", dokttd);
    }
    
    @RequestMapping(value = "/updatedokttds", method = RequestMethod.POST)
    public String prosesupdate(@Valid @ModelAttribute("spdBTLMaster") Dokttd dokttd, BindingResult result) {
        System.out.println(" result.hasErrors() = " + result.hasErrors());
        if (result.hasErrors()) {
            return "/ref/dokttd/updatedokttd";
        } else {
            dokttd.setIdEdit(9999);
            dokttd.setTglEdit(new Timestamp(System.currentTimeMillis()));
            dokttdServices.updatedokttd(dokttd);

        }
        return "redirect:/dokttd";
    }
    
    @RequestMapping(value = "/deletedokttd", method = RequestMethod.POST)
    public String deletedokttd(@Valid @ModelAttribute("spdBTLMaster") Dokttd dokttd) {
        dokttdServices.deletedokttd(dokttd.getId());
        return "redirect:/dokttd";
    }

    @RequestMapping(value = "/deldokttd/{id}", method = RequestMethod.GET)
    public ModelAndView deldokttd(@PathVariable Integer id) {
        final Dokttd dokttd = dokttdServices.getDokttdById(id);
        return new ModelAndView("/ref/dokttd/deldokttd", "spdBTLMaster", dokttd);
    }

}
