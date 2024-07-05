/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spm.ui.action;

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
import spp.model.Bankrek;
import spm.services.BankrekServices;

/**
 *
 * @author erzy
 */
@Controller
@RequestMapping("/bankrek")
public class BankrekAction {

    private static final Logger log = LoggerFactory.getLogger(BankrekAction.class);
    @Autowired
    BankrekServices bankrekServices;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {

        return "/common/listbankrek";

    }

    @RequestMapping(value = "/addbankrek", method = RequestMethod.GET)
    public ModelAndView addbankrek(Bankrek bankrek) {
        return new ModelAndView("/common/addbankrek", "progcmd", bankrek);

    }

    @RequestMapping(value = "/simpanbankrek", method = RequestMethod.POST)
    public String prosessimpan(@Valid @ModelAttribute("progcmd") Bankrek bankrek, BindingResult result) {
        if (result.hasErrors()) {
            return "/common/addbankrek";
        } else {
            bankrek.setIdEntry(9999);
            bankrek.setTglEntry(new Timestamp(System.currentTimeMillis()));
            bankrekServices.insertBankrek(bankrek);

        }
        return "redirect:/bankrek";
    }

    @RequestMapping(value = "/delbankrek/{id}", method = RequestMethod.GET)
    public ModelAndView delbank(@PathVariable Integer id) {
        final Bankrek Bankrek = bankrekServices.getBankrekById(id);
        return new ModelAndView("/common/delbankrek", "spdBTLMaster", Bankrek);
    }

    @RequestMapping(value = "/deletebankrek", method = RequestMethod.POST)
    public String deletebank(@Valid @ModelAttribute("spdBTLMaster") Bankrek Bankrek) {
        bankrekServices.deleteBankrek(Bankrek.getIdBankrek());
        return "redirect:/bankrek";
    }

    @RequestMapping(value = "/updatebankrek/{id}", method = RequestMethod.GET)
    public ModelAndView editfungsi(@PathVariable Integer id) {
        final Bankrek Bankrek = bankrekServices.getBankrekById(id);
        return new ModelAndView("/common/updatebankrek", "spdBTLMaster", Bankrek);
    }

    @RequestMapping(value = "/updatebankreks", method = RequestMethod.POST)
    public String prosesupdate(@Valid @ModelAttribute("spdBTLMaster") Bankrek Bankrek, BindingResult result) {
        System.out.println(" result.hasErrors() = " + result.hasErrors());
        if (result.hasErrors()) {
            return "/common/updatebankrek";
        } else {
            Bankrek.setIdEdit(9999);
            Bankrek.setTglEdit(new Timestamp(System.currentTimeMillis()));
            bankrekServices.updateBankrek(Bankrek);

        }
        return "redirect:/bankrek";
    }

    @RequestMapping(value = "/json/listbankrekjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdljson(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String namabankrek = request.getParameter("namabankrek");
        final String idskpd = request.getParameter("idskpd");
        param.put("idskpd", idskpd);
        param.put("namabankrek", namabankrek);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = bankrekServices.getBanyakAllBankrek(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bankrekServices.getBankrek(param));
        return mapData;
    }
}
