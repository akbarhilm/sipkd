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
import spp.model.JournalPenerimaan;
import spj.services.JournalPenerimaanServices;
import spj.util.BigDecimalPropertyEditor;

import spj.util.SqlDatePropertyEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/journalpnrm")
public class JournalPenerimaanAction {

    private static final Logger log = LoggerFactory.getLogger(JournalPenerimaanAction.class);

    @Autowired
    JournalPenerimaanServices journalServices;

    @RequestMapping(value = "/indexjournalpnrm", method = RequestMethod.GET)
    public ModelAndView addjournal(final JournalPenerimaan spj, final HttpServletRequest request, Model model) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        final List<JournalPenerimaan> loket = journalServices.getLoket();
        model.addAttribute("listLoket", loket);

        return new ModelAndView("spj/journalpnrm/journalpnrm", "refsppup", spj);
    }

    @RequestMapping(value = "/json/listjournalpnrm", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistjournalspj(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahun");
        final String tglvalidasi = request.getParameter("tglvalidasi");
        final String loket = request.getParameter("loket");

        final Map< String, Object> param = new HashMap<String, Object>(2);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("loket", loket);
        param.put("tglvalidasi", tglvalidasi);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(5); 
        final int banyak = journalServices.getBanyakListJournal(param);
        log.debug("cek banyakkkkkk  ======== ",banyak);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("banyakData", banyak);
        mapData.put("aaData", journalServices.getListJurnal(param));
        
        return mapData;
        
    }
    
    @RequestMapping(value = "/json/setTanggal", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> tanggal(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String loket = request.getParameter("loket");
        
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("loket", loket);
       
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        
        mapData.put("aData", journalServices.getTanggal(param));
        return mapData;
    }

    
@RequestMapping(value = "/prosejournal", method = RequestMethod.POST)
    public String prosesjournal(@Valid @ModelAttribute("refsppup") JournalPenerimaan pnrm,
            BindingResult result, final HttpServletRequest request,
            final RedirectAttributes redirectAttributes) {
        
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String loket = request.getParameter("loket");
        final String tgl = request.getParameter("tglvalidasi");
        
        pnrm.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        pnrm.setIdEntry(pengguna.getIdPengguna());
        pnrm.setLoket(loket);
        pnrm.setTglvalidasi(tgl);
        
        journalServices.insertJournalPenerimaan(pnrm);
       
        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data jurnal")
                    .append(" berhasil diproses ")
                    .toString());
               
       return "redirect:/journalpnrm/indexjournalpnrm";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }


}
