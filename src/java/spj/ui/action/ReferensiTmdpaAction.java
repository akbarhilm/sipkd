/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spp.model.Tmdpa;
import spp.model.Pengguna;
import spp.model.Skpd;
//import spp.model.Rekanan;
//import spp.services.RekananServices;
//import spp.services.
import spj.services.TmdpaServices;

/**
 *
 * @author Xalamaster
 */

@Controller
@RequestMapping("/refftmdpa")
public class ReferensiTmdpaAction {

    @Autowired
    TmdpaServices tmdpaServices;
    private static final Logger log = LoggerFactory.getLogger(ReferensiTmdpaAction.class);

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(Tmdpa tmdpa, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
            return new ModelAndView("/ref/tmdpa/indextmdpa", "cmdTmdpa", tmdpa);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            //final Integer idskpd = 2;
            final Integer idskpd = listSkpd.get(0).getIdSkpd();
            final Map< String, Object> param = new HashMap<String, Object>(2);
            param.put("tahun", tahunAnggaran);
            param.put("idskpd", idskpd);
            //request.setAttribute("test", idskpd);
            //final tmdpa = tmdpaServices.getTmdpaById(idskpd, tahunAnggaran);
            request.setAttribute("datatmdpa", tmdpaServices.getTmdpaById(param));
        }
        //return "ref/tmdpa/edittmdpa";
        return new ModelAndView("/ref/tmdpa/edittmdpa", "cmdTmdpa", tmdpa);
    }

    @RequestMapping(value = "/updatetmdpa", method = RequestMethod.POST)
    public String updatetmdpa(@Valid @ModelAttribute("cmdTmdpa") Tmdpa tmdpa, BindingResult result, final RedirectAttributes redirectAttributes, final HttpServletRequest request) {
        System.out.println(" result.hasErrors() = " + result.hasErrors());

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        tmdpa.setIdEdit(pengguna.getIdPengguna());
        tmdpa.setTglEdit(new Timestamp(System.currentTimeMillis()));
        tmdpaServices.updateTmdpa(tmdpa);

        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ").append(" berhasil diubah ").toString());
        return "redirect:/refftmdpa";
    }

    @RequestMapping(value = "/skpdlistpopup", method = RequestMethod.GET)
    public String listpopup(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "/ref/tmdpa/skpdlistpopup";
    }

    @RequestMapping(value = "/json/listskpdjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);

        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("skpd");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        param.put("tahun", tahunAnggaran);
        param.put("skpd", skpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = tmdpaServices.getBanyakSkpd(param);

        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", tmdpaServices.getAllTmdpa(param));
        return mapData;
    }

}
