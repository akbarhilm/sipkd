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
import spp.model.JournalSkpd;
import spj.services.JournalSkpdServices;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SipkdHelpers;

import spj.util.SqlDatePropertyEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/journalskpd")
public class JournalSkpdAction {

    private static final Logger log = LoggerFactory.getLogger(JournalSkpdAction.class);

    @Autowired
    JournalSkpdServices journalServices;

    @RequestMapping(value = "/indexjournalskpd", method = RequestMethod.GET)
    public ModelAndView addjournal(final JournalSkpd spj, final HttpServletRequest request, Model model) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        final List<JournalSkpd> unit = journalServices.getUnit();
        model.addAttribute("listUnit", unit);

        return new ModelAndView("spj/journalskpd/journalskpd", "refsppup", spj);
    }

    @RequestMapping(value = "/json/journalskpd", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistjournalspj(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");

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

    @RequestMapping(value = "/json/getlistbanyakrinci ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getlistspjrincibanyak(final HttpServletRequest request) {
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);

        return journalServices.getBanyakListJournal(param);
    }

    @RequestMapping(value = "/json/gettotal", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> totalAll(final HttpServletRequest request) {
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);

        final Map< String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", journalServices.getTotal(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getnamaakun", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> namaakun(final HttpServletRequest request) {
        final String kode = request.getParameter("kode");
        final Map< String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", journalServices.getNamaAkun(kode));

        return mapData;
    }

    @RequestMapping(value = "/json/prosessimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        Integer idskpd = SipkdHelpers.getIntFromString((String) mapdata.get("idskpd"));
        String tahun = (String) mapdata.get("tahun");
        Object idrowcek = mapdata.get("idrow");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<Map<String, Object>> nilainr = (List) mapdata.get("nilainr");
        List<JournalSkpd> listJournalSkpd = new ArrayList<>();
        List<JournalSkpd> nrJournalSkpd = new ArrayList<>();
        
        log.debug(" jumlah idrow ============= " + mapdata.get("idrow")+"   ||   "+ (mapdata.get("idrow")).getClass());
        
        Integer idrow = SipkdHelpers.getIntFromString(idrowcek.toString());//SipkdHelpers.getIntFromString((String) mapdata.get("idrow"));
        
        for (Map<String, Object> mapnilai : nilailist) {
            log.debug("   nilailist " + mapnilai+"   "+ mapnilai.get("nilaiDebet").getClass());
            JournalSkpd journalSkpd = new JournalSkpd();
            
            journalSkpd.setIdEntry(pengguna.getIdPengguna());
            journalSkpd.setIdskpd(idskpd);
            journalSkpd.setTahun(tahun);
            
            Object nilaiDebet = mapnilai.get("nilaiDebet");
            Object nilaiKredit = mapnilai.get("nilaiKredit");
            Object idBas = mapnilai.get("idBas");
            Object nilaiSaldo = mapnilai.get("nilaiSaldo");
        
            journalSkpd.setNilaiSaldo(SipkdHelpers.getBigDecimalFromString(nilaiSaldo.toString()));
            journalSkpd.setNilaiKredit(SipkdHelpers.getBigDecimalFromString(nilaiKredit.toString()));
            journalSkpd.setIdBas( SipkdHelpers.getIntFromString(idBas.toString())  );
            journalSkpd.setNilaiDebet(SipkdHelpers.getBigDecimalFromString(nilaiDebet.toString())); 
            
            listJournalSkpd.add(journalSkpd); 
        }
        
        if (idrow > 0){
            for (Map<String, Object> mapnilainr : nilainr) {
                log.debug("   nilainr " + mapnilainr+"   "+ mapnilainr.get("nilaiDebet").getClass());
                JournalSkpd journalSkpd = new JournalSkpd();

                journalSkpd.setIdEntry(pengguna.getIdPengguna());
                journalSkpd.setIdskpd(idskpd);
                journalSkpd.setTahun(tahun);

                Object nilaiDebet = mapnilainr.get("nilaiDebet");
                Object nilaiKredit = mapnilainr.get("nilaiKredit");
                Object idBas =   mapnilainr.get("idBas");
                Object nilaiSaldo = mapnilainr.get("nilaiSaldo");

                journalSkpd.setNilaiSaldo(SipkdHelpers.getBigDecimalFromString(nilaiSaldo.toString()));
                journalSkpd.setNilaiKredit(SipkdHelpers.getBigDecimalFromString(nilaiKredit.toString()));
                journalSkpd.setIdBas( SipkdHelpers.getIntFromString(idBas.toString()));
                journalSkpd.setNilaiDebet(SipkdHelpers.getBigDecimalFromString(nilaiDebet.toString())); 

                nrJournalSkpd.add(journalSkpd); 
            }
            
            journalServices.insertJourSaldoAwal(nrJournalSkpd);
        }
        
       journalServices.updateJourSaldoAwal(listJournalSkpd);
       
      
        return "Data Journal Berhasil Disimpan";

    }
   

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

    private BigDecimal BigDecimal(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
