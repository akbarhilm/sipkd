/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spp.model.BankBantuan;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.services.BankBantuanServices;
import spp.util.BigDecimalPropertyEditor;
import spp.util.SqlDatePropertyEditor;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/bankbantuan")
public class BankBantuanAction {

    private static final Logger log = LoggerFactory.getLogger(BankBantuanAction.class);
    @Autowired
    BankBantuanServices bankServices;

    @RequestMapping(value = "/addbank", method = RequestMethod.GET)
    public ModelAndView add(final BankBantuan bank, final HttpServletRequest request) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();

        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
            request.setAttribute("tahunAnggaran", tahunAnggaran);

        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            request.setAttribute("isall", 0);
            request.setAttribute("tahunAnggaran", tahunAnggaran);
            request.setAttribute("skpd", listSkpd.get(0));

        }
        return new ModelAndView("/bankbantuan/bankbantuan", "progcmd", bank);
    }

    @RequestMapping(value = "/listsppbantuan", method = RequestMethod.GET)
    public ModelAndView listsppbantuan(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("/bankbantuan/listsppbantuan");
    }
    
    @RequestMapping(value = "/json/listsppbantuanjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsppbantuanjson(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("kodeskpd");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = bankServices.getBanyakListBantuan(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bankServices.getListBantuan(param));
        return mapData;
    }

    @RequestMapping(value = "/updatebank", method = RequestMethod.POST)
    public String prosesupdatesppbantuan(@Valid @ModelAttribute("progcmd") BankBantuan bank, BindingResult result, final HttpServletRequest request) {

        if (result.hasErrors()) {
            return "/bankbantuan/bankbantuan";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            bank.setIdEntry(pengguna.getIdPengguna());
            
            bankServices.updateKodeBank(bank);
            
        }
        return "redirect:/addbank";

    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}
