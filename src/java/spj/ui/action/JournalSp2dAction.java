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
import spp.model.JournalSp2d;
import spj.services.JournalSp2dServices;
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
@RequestMapping("/journalsp2d")
public class JournalSp2dAction {

    private static final Logger log = LoggerFactory.getLogger(JournalSp2dAction.class);

    @Autowired
    JournalSp2dServices journalServices;

    @RequestMapping(value = "/indexjournalsp2d", method = RequestMethod.GET)
    public ModelAndView addjournal(final JournalSp2d spj, final HttpServletRequest request, Model model) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        final List<JournalSp2d> wilayah = journalServices.getWilayah();
        model.addAttribute("listWilayah", wilayah);

        return new ModelAndView("spj/journalsp2d/journalsp2d", "refsppup", spj);
    }

    @RequestMapping(value = "/json/journalsp2d", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistjournalspj(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahun");
        final String tanggal = request.getParameter("tanggal");
        final String wilayah = request.getParameter("wilayah");
        final String idskpd = request.getParameter("idskpd");
        final String kodegrup = request.getParameter("kodegrup");

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
        param.put("tglsah", tanggal);
        param.put("wilayah", wilayah);
        param.put("idskpd", idskpd);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4); 
        
        if ("10".equals(kodegrup)){
            log.debug("masuk ke 10");
            final int banyak = journalServices.getBanyakListJournalSkpd(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("banyakData", banyak);
            mapData.put("aaData", journalServices.getListJurnalSkpd(param));
        
        }
        
        if ("11".equals(kodegrup)){
            final int banyak = journalServices.getBanyakListJournal(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("banyakData", banyak);
            mapData.put("aaData", journalServices.getListJurnal(param));
        
        }
        
        return mapData;
    }
    
    @RequestMapping(value = "/json/setTanggal", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> tanggal(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String wilayah = request.getParameter("kodewil");
        final String idskpd = request.getParameter("idskpd");
        final String kodegrup = request.getParameter("kodegrup");
        
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("wilayah", wilayah);
        param.put("idskpd", idskpd);
       
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        
        if ("10".equals(kodegrup)){
            mapData.put("aData", journalServices.getTanggalSkpd(param));
        }
        if ("11".equals(kodegrup)){
            mapData.put("aData", journalServices.getTanggal(param));
        }
        
        return mapData;
    }

    
@RequestMapping(value = "/prosejournal", method = RequestMethod.POST)
    public String prosesjournal(@Valid @ModelAttribute("refsppup") JournalSp2d sp2d,
            BindingResult result, final HttpServletRequest request,
            final RedirectAttributes redirectAttributes) {
        
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String wilayah = request.getParameter("wilayah");
        final String tgl = request.getParameter("tglsah");
        
        sp2d.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        sp2d.setIdEntry(pengguna.getIdPengguna());
        sp2d.setWilayah(wilayah);
        sp2d.setTglsah(tgl);
       
        journalServices.insertJournalSp2d(sp2d);
       
        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data jurnal")
                    .append(" berhasil diproses ")
                    .toString());
               
       return "redirect:/journalsp2d/indexjournalsp2d";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }


}
