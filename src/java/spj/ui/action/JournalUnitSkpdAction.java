/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
package spj.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spp.model.Pengguna;
import spp.model.Skpd; 
import spp.model.JournalUnitSkpd;
import spj.services.JournalUnitSkpdServices;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SipkdHelpers;

import spj.util.SqlDatePropertyEditor;

/**
 *
 * @author Zainab
 */

@Controller
@RequestMapping("/journalunitskpd")
public class JournalUnitSkpdAction {

    private static final Logger log = LoggerFactory.getLogger(JournalUnitSkpdAction.class);

    @Autowired
    JournalUnitSkpdServices journalServices;

    
    @RequestMapping(value = "/addjournal", method = RequestMethod.GET)
    public ModelAndView add(final JournalUnitSkpd journal, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());

            journal.setIdskpd(listSkpd.get(0).getIdSkpd());
        }
        
        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        
        log.debug("TAHUN ANGGARAN == ",tahunAnggaran);
        log.debug("TAHUN AJJAH == ",tahun);
    
        return new ModelAndView("spj/journalunitskpd/addjournal", "refsetor", journal);
    }

    @RequestMapping(value = "/json/getnamaakun", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> namaakun(final HttpServletRequest request) {
        final String kode = request.getParameter("kode");
        final Map< String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", journalServices.getNamaAkun(kode));

        return mapData;
    }
    
    @RequestMapping(value = "/json/getnojurnal", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> nojurnal(final HttpServletRequest request) {
        final String tahun = request.getParameter("tahun");
        final Map< String, Object> mapData = new HashMap<String, Object>(4);
         mapData.put("aData", journalServices.getNoJournal(tahun));
        
        return mapData;
    }

    @RequestMapping(value = "/listakun", method = RequestMethod.GET)
    public String indexlistakun(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "spj/akun/listakun";
    }
    
    @RequestMapping(value = "/listkegiatan", method = RequestMethod.GET)
    public String indexlistkegiatan(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "spj/kegiatan/listkegiatan";
    }
    
    @RequestMapping(value = "/listjournal", method = RequestMethod.GET)
    public String indexlistjournal(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "spj/journalunitskpd/listjournal";
    }

    @RequestMapping(value = "/json/listakunjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listakunjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kode = request.getParameter("kode");
        final String nama = request.getParameter("nama");
        final String kodeAkunref = request.getParameter("kodeAkunref");
        
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("kodeAkunref",kodeAkunref); 
        param.put("nama",nama);
        param.put("kode",kode);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        
        final long banyak = journalServices.getBanyakAllAkun(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", journalServices.getAllAkun(param));
        return mapData;
    }
    
    @RequestMapping(value = "/json/listkegiatanjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listkegiatanjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kodeKeg = request.getParameter("kodeKeg");
        final String namaKeg = request.getParameter("namaKeg");
        
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("namaKeg",namaKeg);
        param.put("kodeKeg",kodeKeg);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        
        final long banyak = journalServices.getBanyakKegiatan(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", journalServices.getKegiatan(param));
        return mapData;
    }
    
    @RequestMapping(value = "/json/listjurnaljson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listjurnaljson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String idskpd = request.getParameter("idskpd");
        
        param.put("idskpd", idskpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        
        final long banyak = journalServices.getBanyakAllJurnal(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", journalServices.getAllJurnal(param));
        return mapData;
    }
    
    @RequestMapping(value = "/json/listjurnalupdate", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listjurnalupdate(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final String nojur = request.getParameter("noJournal");
        final String idskpd = request.getParameter("idskpd");
        
        param.put("idskpd", idskpd);
        param.put("noJournal", nojur);
        param.put("offset", offset);
        param.put("limit", limit);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = journalServices.getBanyakListJurnal(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", journalServices.getListJurnal(param));
        return mapData;
    }
    
    @RequestMapping(value = "/json/getlistbanyakrinci ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getlistspjrincibanyak(final HttpServletRequest request) {
        final String noJournal = request.getParameter("noJournal");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("noJournal", noJournal);
        param.put("idskpd", idskpd);
        
        return journalServices.getBanyakListJurnal(param);
    }
    
    
    @RequestMapping(value = "/json/valtabel", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> valtabel(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(2);
        final String noJournal = request.getParameter("noJournal");
        final String idskpd = request.getParameter("idskpd");
        
        param.put("noJournal", noJournal);
        param.put("idskpd", idskpd);
        
        final Map< String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("cek", 1);
        mapData.put("aData", journalServices.getListJurnal(param));

        return mapData;

    }
    
    @RequestMapping(value = "/json/prosessimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final Map< String, Object> param = new HashMap<String, Object>(2);
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilainr = (List) mapdata.get("nilainr");
        List<JournalUnitSkpd> nrJournalUmum = new ArrayList<>();
        
        final String noDok = (String) mapdata.get("noDok");
        final String idskpd = (String) mapdata.get("idskpd");
        final String tglDok = (String) mapdata.get("tglDok");
        final String noJournal = (String) mapdata.get("noJournal");
        final String keterangan = (String) mapdata.get("ketJour");
        final String tglket = (String) mapdata.get("tglket");
        final String tglPosting = ((String) mapdata.get("tglPosting"));
        final String koreksiBpk = ((String) mapdata.get("koreksiBpk"));
        
        
        param.put("tahun", tahun);
        param.put("tglPosting", tglket);
        final String nojournaldoc = journalServices.getNoJournalDok(param);
        
        log.debug("NO JURNAL DOK =============== "+nojournaldoc);
        
        for (Map<String, Object> mapnilainr : nilainr) {
            log.debug("   nilainr ========== " + mapnilainr + " || class ==  " + mapnilainr.getClass());
            JournalUnitSkpd journal = new JournalUnitSkpd();
            
            journal.setTahun(tahun);
            journal.setIdEntry(pengguna.getIdPengguna());
            
            journal.setNoJournalDok(nojournaldoc);
            journal.setTglPosting(tglPosting);
            journal.setNoDok(noDok);
            journal.setTglDok(tglDok);
            journal.setNoJournal(noJournal);
            journal.setKetJour(keterangan);
            journal.setKoreksiBPK(koreksiBpk);
            
            Object idbas = mapnilainr.get("idbas");
            Object debet = mapnilainr.get("debet");
            Object kredit = mapnilainr.get("kredit");
            Object kegiatan = mapnilainr.get("kegiatan");
            Object jenis = mapnilainr.get("jenis");
            Object beban = mapnilainr.get("beban");

            journal.setIdskpd(SipkdHelpers.getIntFromString( idskpd));
            journal.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            journal.setNilaiDebet(SipkdHelpers.getBigDecimalFromString(debet.toString()));
            journal.setNilaiKredit(SipkdHelpers.getBigDecimalFromString(kredit.toString()));
            journal.setIdKegiatan(kegiatan.toString());
            journal.setJenis(jenis.toString());
            journal.setBeban(beban.toString());
            
            nrJournalUmum.add(journal); 
        }
        
        journalServices.insertJournal(nrJournalUmum);
        
        return "Data Journal Berhasil Disimpan";
    }
    
    @RequestMapping(value = "/json/prosessimpanupdate", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpanupdate(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final Map< String, Object> param = new HashMap<String, Object>(2);
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilainr = (List) mapdata.get("nilainr");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<Map<String, Object>> nilainojurnal = (List) mapdata.get("nilainojurnal");
        List<JournalUnitSkpd> nrJournalUmum = new ArrayList<>();
        List<JournalUnitSkpd> listJournalUmum = new ArrayList<>();
        
        final String noDok = (String) mapdata.get("noDok");
        final String tglDok = (String) mapdata.get("tglDok");
        final String idskpd = (String) mapdata.get("idskpd");
        final String noJournal = (String) mapdata.get("noJournal");
        final String keterangan = (String) mapdata.get("ketJour");
        final String tglket = (String) mapdata.get("tglket");
        final String tglPosting = ((String) mapdata.get("tglPosting")) ;
        final Integer jumNR = (Integer) mapdata.get("banyakNR");
        final Integer jumkosong = (Integer) mapdata.get("jumkosong");
        final String nojourdok = (String) mapdata.get("nojourdok");
        final String koreksiBpk = ((String) mapdata.get("koreksiBpk")) ;
      
        param.put("tahun", tahun);
        param.put("tglPosting", tglket);
        //final String nojournaldoc = journalServices.getNoJournalDok(param);
        
        for (Map<String, Object> mapnilai : nilailist) {
            JournalUnitSkpd journal = new JournalUnitSkpd();
            
            journal.setTahun(tahun);
            journal.setIdEntry(pengguna.getIdPengguna());
            
            journal.setNoJournalDok(nojourdok);
            journal.setTglPosting(tglPosting);
            journal.setNoDok(noDok);
            journal.setTglDok(tglDok);
            journal.setNoJournal(noJournal);
            journal.setKetJour(keterangan);
            journal.setKoreksiBPK(koreksiBpk);
            
            Object idbas = mapnilai.get("idbas");
            Object debet = mapnilai.get("debet");
            Object kredit = mapnilai.get("kredit");
            Object kegiatan = mapnilai.get("kegiatan");
            Object jenis = mapnilai.get("jenis");
            Object beban = mapnilai.get("beban");
            Object idjur = mapnilai.get("idjur");
            
            journal.setIdJour(SipkdHelpers.getIntFromString(idjur.toString()));
            journal.setIdskpd(SipkdHelpers.getIntFromString(idskpd));
            journal.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            journal.setNilaiDebet(SipkdHelpers.getBigDecimalFromString(debet.toString()));
            journal.setNilaiKredit(SipkdHelpers.getBigDecimalFromString(kredit.toString()));
            journal.setIdKegiatan(kegiatan.toString());
            journal.setJenis(jenis.toString());
            journal.setBeban(beban.toString());
            
            listJournalUmum.add(journal); 
        }
        
        journalServices.updateJournal(listJournalUmum);
        
        if (jumkosong > 0){
            for (Map<String, Object> mapkosong : nilainojurnal) {
                JournalUnitSkpd journal = new JournalUnitSkpd();
                journal.setIdEntry(pengguna.getIdPengguna());
                journal.setTahun(tahun);
                Object idjur = mapkosong.get("idjur");
                journal.setIdJour(SipkdHelpers.getIntFromString(idjur.toString()));
                
                journalServices.updateAktifById(journal);
            }
        }
        
        if (jumNR > 0){
            for (Map<String, Object> mapnilainr : nilainr) {
                JournalUnitSkpd journal = new JournalUnitSkpd();

                journal.setTahun(tahun);
                journal.setIdEntry(pengguna.getIdPengguna());

                journal.setNoJournalDok(nojourdok);
                journal.setTglPosting(tglPosting);
                journal.setNoDok(noDok);
                journal.setTglDok(tglDok);
                journal.setNoJournal(noJournal);
                journal.setKetJour(keterangan);
                journal.setKoreksiBPK(koreksiBpk);

                Object idbas = mapnilainr.get("idbas");
                Object debet = mapnilainr.get("debet");
                Object kredit = mapnilainr.get("kredit");
                Object kegiatan = mapnilainr.get("kegiatan");
                Object jenis = mapnilainr.get("jenis");
                Object beban = mapnilainr.get("beban");

                journal.setIdskpd(SipkdHelpers.getIntFromString(idskpd));
                journal.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
                journal.setNilaiDebet(SipkdHelpers.getBigDecimalFromString(debet.toString()));
                journal.setNilaiKredit(SipkdHelpers.getBigDecimalFromString(kredit.toString()));
                journal.setIdKegiatan(kegiatan.toString());
                journal.setJenis(jenis.toString());
                journal.setBeban(beban.toString());

                
                nrJournalUmum.add(journal); 

            }
            
        log.debug("PARAM ==== "+nrJournalUmum);
        journalServices.insertJournal(nrJournalUmum);
        
        }
        
        return "Data Journal Berhasil Disimpan";
    }
    
    @RequestMapping(value = "/json/prosesupdateaktif", method = RequestMethod.POST)
    public @ResponseBody 
    String hapusspddanrincibyspd(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String nojur = (String) mapdata.get("nojur");
        final String tahun = (String) mapdata.get("tahun");
        
        JournalUnitSkpd journal = new JournalUnitSkpd();
        journal.setIdEntry(pengguna.getIdPengguna());
        journal.setTahun(tahun);
        journal.setNoJournal(nojur);
        
        journalServices.updateAktifJournal(journal);
        return " Data Jumnal Umum SKPD nomor " + nojur + "  tahun  " + tahun + " berhasil dihapus ";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
