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

import spp.model.Bank;
import spp.services.BankServices;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/bank")
public class BankAction {

    private static final Logger log = LoggerFactory.getLogger(BankAction.class);
    @Autowired
    BankServices bankServices;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {

        return "/common/bank/latgridfunc";
    }

    @RequestMapping(value = "/addbank", method = RequestMethod.GET)
    public ModelAndView terserahmauapaja(Bank bank) {
        return new ModelAndView("/common/bank/addbank", "fungsicmda", bank);

    }

    @RequestMapping(value = "/json/listjsonbank", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsons(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String namabank = request.getParameter("namabank");
        param.put("namabank", namabank);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = bankServices.getCountBank(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bankServices.getBank(param));
        return mapData;
    }

    @RequestMapping(value = "/simpanbank", method = RequestMethod.POST)
    public String prosessimpan(@Valid @ModelAttribute("fungsicmda") Bank bank, BindingResult result) {
        if (result.hasErrors()) {
            return "/common/bank/addbank";
        } else {
            bank.setIdEntry(9999);
            bank.setTglEntry(new Timestamp(System.currentTimeMillis()));
            bankServices.insertbank(bank);

        }
        return "redirect:/bank";
    }

    @RequestMapping(value = "/delbank/{id}", method = RequestMethod.GET)
    public ModelAndView delbank(@PathVariable Integer id) {
        final Bank bank = bankServices.getBankById(id);
        return new ModelAndView("/common/bank/delbank", "spdBTLMaster", bank);
    }

    @RequestMapping(value = "/deletebank", method = RequestMethod.POST)
    public String deletebank(@Valid @ModelAttribute("spdBTLMaster") Bank bank) {
        bankServices.deletebank(bank.getIdBank());
        return "redirect:/bank";
    }

    @RequestMapping(value = "/updatebank/{id}", method = RequestMethod.GET)
    public ModelAndView editfungsi(@PathVariable Integer id) {
        final Bank bank = bankServices.getBankById(id);
        return new ModelAndView("/common/bank/updatebank", "spdBTLMaster", bank);
    }

    @RequestMapping(value = "/updatebanks", method = RequestMethod.POST)
    public String prosesupdate(@Valid @ModelAttribute("spdBTLMaster") Bank bank, BindingResult result) {
        System.out.println(" result.hasErrors() = " + result.hasErrors());
        if (result.hasErrors()) {
            return "/common/bank/updatebank";
        } else {
            bank.setIdEdit(9999);
            bank.setTglEdit(new Timestamp(System.currentTimeMillis()));
            bankServices.updatebank(bank);

        }
        return "redirect:/bank";
    }

}
