/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
package sp2d.ui.action;

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
import sp2d.services.JournalSp2dServices;
import sp2d.util.BigDecimalPropertyEditor;

import sp2d.util.SqlDatePropertyEditor;
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

        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("kode", pengguna.getKodeProses());
        
        final List<JournalSp2d> wilayah = journalServices.getWilayahByKode(param);
        model.addAttribute("listWilayah", wilayah);

        return new ModelAndView("spj/journalsp2d/journalsp2d", "refsppup", spj);
    }

    @RequestMapping(value = "/indexjournalsp2dAll", method = RequestMethod.GET)
    public ModelAndView addjournalAll(final JournalSp2d spj, final HttpServletRequest request, Model model) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        final List<JournalSp2d> wilayah = journalServices.getWilayah();
        model.addAttribute("listWilayah", wilayah);

        return new ModelAndView("spj/journalsp2d/journalsp2dAll", "refsppup", spj);
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
        param.put("tglsahakhir", tanggal);
        param.put("wilayah", wilayah);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        /*if ("8".equals(kodegrup)) {
            log.debug("masuk ke 8");
            final int banyak = journalServices.getBanyakListJournalSkpd(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("banyakData", banyak);
            mapData.put("aaData", journalServices.getListJurnalSkpd(param));

        }

        if ("11".equals(kodegrup)) {*/

            final int banyak = journalServices.getBanyakListJournal(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("banyakData", banyak);
            mapData.put("aaData", journalServices.getListJurnal(param));

        //}

        return mapData;
    }

    @RequestMapping(value = "/json/journalsp2dAll", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> journalsp2dAll(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahun");
        final String tanggalawal = request.getParameter("tanggalawal");
        final String tanggalakhir = request.getParameter("tanggalakhir");
        final String wilayah = request.getParameter("wilayah");
        final String idskpd = request.getParameter("idskpd");
        final String kodegrup = request.getParameter("kodegrup");

        final Map< String, Object> param = new HashMap<String, Object>(2);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        log.debug("tanggal awal === "+tanggalawal);
        log.debug("tanggal akhir == "+tanggalakhir);
        
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("tglsah", tanggalawal);
        param.put("tglsahakhir", tanggalakhir);
        param.put("wilayah", wilayah);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final int banyak = journalServices.getBanyakListJournal(param);
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
        final String wilayah = request.getParameter("kodewil");
        final String idskpd = request.getParameter("idskpd");
        final String kodegrup = request.getParameter("kodegrup");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("wilayah", wilayah);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        
        mapData.put("aData", journalServices.getTanggal(param));
        /*

        if ("8".equals(kodegrup)) {
            mapData.put("aData", journalServices.getTanggalSkpd(param));
        }
        if ("11".equals(kodegrup)) {
            if ("ALL".equals(wilayah)) {
                mapData.put("aData", journalServices.getTanggalAll(param));
            } else {
                mapData.put("aData", journalServices.getTanggal(param));
            }

        }*/

        return mapData;
    }

    @RequestMapping(value = "/json/setWilayah", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> wilayah(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String wilayah = request.getParameter("kodewil");
        final String idskpd = request.getParameter("idskpd");
        /*final String kodegrup = request.getParameter("kodegrup");
        
         final Map< String, Object> param = new HashMap<String, Object>(3);
         param.put("tahun", tahunAnggaran);
         param.put("wilayah", wilayah);
         param.put("idskpd", idskpd);*/

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", journalServices.getWilayah());

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
        sp2d.setTglsahakhir(tgl);

        journalServices.insertJournalSp2d(sp2d);

        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data jurnal")
                .append(" berhasil diproses ")
                .toString());

        return "redirect:/journalsp2d/indexjournalsp2d";
    }
    
    @RequestMapping(value = "/json/prosessimpanjurnal", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        
        final String wilayah = (String) mapdata.get("wilayah");
        final String tgl = (String) mapdata.get("tglsah");
        final String keterangan = (String) mapdata.get("keterangan");
        
        log.debug("wilayah jurnal SP2D -------------- "+wilayah);
        log.debug("tgl sah jurnal SP2D -------------- "+tgl);
        
        JournalSp2d sp2d = new JournalSp2d();
        
        sp2d.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        sp2d.setIdEntry(pengguna.getIdPengguna());
        sp2d.setWilayah(wilayah);
        sp2d.setTglsah(tgl);
        sp2d.setTglsahakhir(tgl);
        sp2d.setKetJurnal(keterangan);
        
        journalServices.insertJournalSp2d(sp2d);
        //journalServices.insertSp2dJour(sp2d);
       
        return "Proses Jurnal SP2D Berhasil Disimpan";
    }

    @RequestMapping(value = "/prosejournalAll", method = RequestMethod.POST)
    public String prosejournalAll(@Valid @ModelAttribute("refsppup") JournalSp2d sp2d,
            BindingResult result, final HttpServletRequest request,
            final RedirectAttributes redirectAttributes) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String wilayah = request.getParameter("wilayah");
        final String tgl = request.getParameter("tglsah");
        final String tglakhir = request.getParameter("tglsahakhir");

        sp2d.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        sp2d.setIdEntry(pengguna.getIdPengguna());
        sp2d.setWilayah(wilayah);
        sp2d.setTglsah(tgl);
        sp2d.setTglsahakhir(tglakhir);

        if ("ALL".equals(wilayah)) {
            journalServices.insertJournalSp2dAll(sp2d);

        } else {
            journalServices.insertJournalSp2d(sp2d);
        }

        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data jurnal")
                .append(" berhasil diproses ")
                .toString());

        return "redirect:/journalsp2d/indexjournalsp2dAll";
    }
    
    @RequestMapping(value = "/json/getBanyakSp2dJour", method = RequestMethod.GET)
    public @ResponseBody
    Integer getBanyakNoBukti(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String tglsah = request.getParameter("tglsah");
        final String wilayah = request.getParameter("wilayah");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("tglsah", tglsah);
        param.put("wilayah", wilayah);

        return journalServices.getBanyakSp2dJour(param);
    }

    @RequestMapping(value = "/json/getKodeProsesJour", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getKodeProsesJour(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String wilayah = request.getParameter("kodewil");
        final String tglsah = request.getParameter("tglsah");
        
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("wilayah", wilayah);
        param.put("tglsah", tglsah);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        
        mapData.put("aData", journalServices.getKodeProsesJour(param));
    
        return mapData;
    }
    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
