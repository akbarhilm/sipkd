/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import spp.model.Bast;
import spp.model.Pengguna;
import spp.model.Skpd;
import spj.services.BastServices;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SqlDatePropertyEditor;

/**
 *
 * @author erzypratama
 */
@Controller
@RequestMapping("/bast")
public class BastAction {

    private static final Logger log = LoggerFactory.getLogger(BastAction.class);
    @Autowired
    BastServices bastServices;

    @RequestMapping(method = RequestMethod.GET)
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
        request.setAttribute("kodekegiatanfilter", request.getParameter("kodekegiatanfilter"));
        request.setAttribute("namakegiatanfilter", request.getParameter("namakegiatanfilter"));
        return "/ref/bast/listbast";

    }

    @RequestMapping(value = "/json/listbastjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listbastjson(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("kodeskpd");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kodekegiatan = request.getParameter("kodekegiatan");
        final String namakegiatan = request.getParameter("namakegiatan");
        final String nomorkontrak = request.getParameter("nomorkontrak");
        final String nomorbast = request.getParameter("nomorbast");
        final String idskpd = request.getParameter("idskpd");
        param.put("idskpd", idskpd);
        param.put("kodekegiatan", StringUtils.trimAllWhitespace(kodekegiatan));
        param.put("namakegiatan", StringUtils.trimAllWhitespace(namakegiatan));
        param.put("nomorkontrak", StringUtils.trimAllWhitespace(nomorkontrak));
        param.put("nomorbast", StringUtils.trimAllWhitespace(nomorbast));
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("kodeskpd", skpd);
        param.put("tahun", tahunAnggaran);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = bastServices.getBanyakAllBast(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bastServices.getBast(param));
        return mapData;
    }

    @RequestMapping(value = "/addbast", method = RequestMethod.GET)
    public ModelAndView add(final Bast bast, final HttpServletRequest request) {

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
        return new ModelAndView("/ref/bast/addbast", "progcmd", bast);
    }

    @RequestMapping(value = "/simpanbast", method = RequestMethod.POST)
    public String prosessimpans(@Valid @ModelAttribute("progcmd") Bast bast, BindingResult result, final HttpServletRequest request) {

        if (result.hasErrors()) {
            return "/ref/bast/addbast";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            bast.setIdEntry(pengguna.getIdPengguna());
            bast.setTglEntry(new Timestamp(System.currentTimeMillis()));
            bastServices.insertBast(bast);

        }
        return "redirect:/bast";

    }

    @RequestMapping(value = "/updatebasts", method = RequestMethod.POST)
    public String prosesupdate(@Valid @ModelAttribute("spdBTLMaster") Bast bast, BindingResult result, final HttpServletRequest request) {
        System.out.println(" result.hasErrors() = " + result.hasErrors());
        if (result.hasErrors()) {
            return "/ref/bast/updatebast";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            bast.setIdEntry(pengguna.getIdPengguna());
            bast.setTglEntry(new Timestamp(System.currentTimeMillis()));
            bastServices.updateBast(bast);
        }
        return "redirect:/bast";
    }

    @RequestMapping(value = "/updatebast/{id}", method = RequestMethod.GET)
    public ModelAndView updatebast(@PathVariable Integer id, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            request.setAttribute("isall", 1);
            request.setAttribute("tahunAnggaran", tahunAnggaran);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            request.setAttribute("isall", 0);
            request.setAttribute("tahunAnggaran", tahunAnggaran);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        Bast bast = bastServices.getBastById(id);
        return new ModelAndView("/ref/bast/updatebast", "spdBTLMaster", bast);
    }

    @RequestMapping(value = "/deletebast/{id}", method = RequestMethod.GET)
    public ModelAndView delbast(@PathVariable Integer id, final HttpServletRequest request) {
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
        final Bast bast = bastServices.getBastById(id);
        return new ModelAndView("/ref/bast/deletebast", "spdBTLMaster", bast);
    }

    @RequestMapping(value = "/deletebasts", method = RequestMethod.POST)
    public String deletebast(@Valid @ModelAttribute("spdBTLMaster") Bast bast) {
        bastServices.deleteBast(bast.getIdBast());
        return "redirect:/bast";
    }

    @RequestMapping(value = "/json/listpopupkontrak", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsonrekanan(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("kontrak");
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("kontrak", skpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = bastServices.getBanyakAllKontrak(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bastServices.getKontrak(param));
        return mapData;
    }

    @RequestMapping(value = "/listpopupkontrak", method = RequestMethod.GET)
    public ModelAndView listkontrak(final HttpServletResponse response, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));

        }
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("/ref/bast/listpopupkontrak");
    }

    @RequestMapping(value = "/json/listpopupakun", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpopupakun(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        final String idkegiatan = request.getParameter("idkegiatan");

        param.put("tahun", tahunAnggaran);
        param.put("idkegiatan", idkegiatan);
        param.put("idskpd", idskpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = bastServices.getBanyakAkun(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bastServices.getAkun(param));
        return mapData;
    }

    @RequestMapping(value = "/listpopupakun/{idkegiatan}", method = RequestMethod.GET)
    public ModelAndView listpopupakun(@PathVariable Integer idkegiatan, Bast bast, final HttpServletResponse response, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));

        }
        //bast = bastServices.getKegiatanById(idkegiatan);
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("/ref/bast/listpopupakun", "progcmd", bast);
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}
