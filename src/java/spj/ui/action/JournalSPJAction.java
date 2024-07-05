/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
package spj.ui.action;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spp.model.Pengguna;
import spp.model.Skpd; 
import spp.model.JournalSPJ;
import spj.services.JournalSPJServices;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SipkdHelpers;

import spj.util.SqlDatePropertyEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/journalspj")
public class JournalSPJAction {

    private static final Logger log = LoggerFactory.getLogger(JournalSPJAction.class);

    @Autowired
    JournalSPJServices journalServices;

    
    @RequestMapping(value = "/indexjournalspj", method = RequestMethod.GET)
    public ModelAndView addjournal(final JournalSPJ spj, final HttpServletRequest request, Model model) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        Integer idskpd = listSkpd.get(0).getIdSkpd();
        
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        final List<JournalSPJ> bulan = journalServices.getBulanJournal(param);
        model.addAttribute("listBulanJournal", bulan);
       
        try {
            request.setAttribute("idSpj", bulan.get(0).getIdSpj());
        } catch (Exception e) {
        }

        return new ModelAndView("spj/journalspj/indexjournalspj", "refsppup", spj);
    }
    
    @RequestMapping(value = "/indexjournalspj11", method = RequestMethod.GET)
    public ModelAndView addjournal11(final JournalSPJ spj, final HttpServletRequest request, Model model) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        return new ModelAndView("spj/journalspj/indexjournalspj11", "refsppup", spj);
    }

    @RequestMapping(value = "/json/getlistjournalspj", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistjournalspj(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        final String bulan = request.getParameter("bulan");
        final String idSpj = request.getParameter("idSpj");
        final String skpd = request.getParameter("namaskpd");
        final Map< String, Object> param = new HashMap<String, Object>(2);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String addoredit = request.getParameter("addoredit");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("bulan", bulan);
        param.put("idSpj", idSpj);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = journalServices.getBanyakJourSpj(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", journalServices.getListJourSpj(param));
        return mapData;
    }
    
    @RequestMapping(value = "/prosejournal", method = RequestMethod.POST)
    public String prosesjournal(@Valid @ModelAttribute("refsppup") JournalSPJ spj,
            BindingResult result, final HttpServletRequest request,
            final RedirectAttributes redirectAttributes) {
        
        final StringBuilder sburl = new StringBuilder("redirect:/journalspj/indexjournalspj");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Integer idskpd = listSkpd.get(0).getIdSkpd();
        final String bulan = request.getParameter("bulan");
        final Integer idSpj = SipkdHelpers.getIntFromString(request.getParameter("idSpj"));
       
        
        spj.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        spj.setIdEntry(pengguna.getIdPengguna());
        spj.setIdskpd(idskpd.toString());
        spj.setBulan(bulan);
        spj.setIdSpj(idSpj);
       
        journalServices.insertJournalSpj(spj);
       
       
        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data jurnal")
                    .append(" berhasil diproses ")
                    .toString());
               
       return "redirect:/journalspj/indexjournalspj";
    }
    
    @RequestMapping(value = "/json/prosessimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        
        final String idskpd = (String) mapdata.get("idskpd");
        final String idspj = (String) mapdata.get("idspj");
        final String bulan = (String) mapdata.get("bulan");
        
        final JournalSPJ spj = new JournalSPJ();
        
            spj.setIdEntry(pengguna.getIdPengguna());
            spj.setTahun(tahun);
            spj.setIdskpd(idskpd);
            spj.setBulan(bulan);
            spj.setIdSpj(SipkdHelpers.getIntFromString(idspj));
            
            journalServices.insertJournalSpj(spj);
       
        return "Proses LRA Berhasil Disimpan";
    }
    
    @RequestMapping(value = "/json/setBulan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> tanggal(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
       
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", journalServices.getBulanJournal(param));
        
        return mapData;
    }
    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
