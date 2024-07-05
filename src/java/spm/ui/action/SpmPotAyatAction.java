/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spm.ui.action;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spp.model.SpmPotAyat;
import spp.model.Pengguna;
import spp.model.Skpd;
import spm.services.SpmPotAyatServices;
import spm.util.SipkdHelpers;

/**
 *
 * @author Xalamaster
 */
@Controller
@RequestMapping("/spmpotayat")
public class SpmPotAyatAction {

    @Autowired
    SpmPotAyatServices spmpotayatServices;
    private static final Logger log = LoggerFactory.getLogger(SpmPotAyatAction.class);

    @RequestMapping(value = "/indexspm/{idspm}", method = RequestMethod.GET)
    public ModelAndView index(@PathVariable Integer idspm, SpmPotAyat spmpotayat, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();

        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("idspm", idspm);
            request.setAttribute("nospm", spmpotayatServices.getNoSpm(idspm));
            request.setAttribute("tglspm", spmpotayatServices.getTglSpm(idspm));

        }

        return new ModelAndView("/spm/spmpotayat/addspmpotayat", "cmdSpmPot", spmpotayat);
    }

    @RequestMapping(value = "/indexspmgaji/{idspm}/{idspp}/{kode}", method = RequestMethod.GET)
    public ModelAndView indexspmgaji(@PathVariable Integer idspm, @PathVariable Integer idspp, @PathVariable Integer kode, SpmPotAyat spmpotayat, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();

        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("idspm", idspm);
            request.setAttribute("idspp", idspp);
            request.setAttribute("kode", kode);
            request.setAttribute("nospm", spmpotayatServices.getNoSpm(idspm));
            request.setAttribute("tglspm", spmpotayatServices.getTglSpm(idspm));
            
            request.setAttribute("jumkotpot", spmpotayatServices.getJumKotPotSpm(idspm));
        }

        return new ModelAndView("/spm/spmpotayat/addspmpotayatgaji", "cmdSpmPot", spmpotayat);
    }

    @RequestMapping(value = "/spmlistpopup", method = RequestMethod.GET)
    public String listpopup(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "/spm/spmpotayat/spmlistpopup";
    }

    @RequestMapping(value = "/json/listspmjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listspmjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String spm = request.getParameter("spm");
        final Integer idskpd = listSkpd.get(0).getIdSkpd();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("spm", spm);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = spmpotayatServices.getBanyakSpm(param);

        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spmpotayatServices.getAllSpm(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listpotayatjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpotayatjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String nospm = request.getParameter("nospm");
        final String spm = request.getParameter("spm");
        final Integer idskpd = listSkpd.get(0).getIdSkpd();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        param.put("nospm", nospm);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("spm", spm);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        //final long banyak = spmpotayatServices.getBanyakPotAyat(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", 8);
        mapData.put("iTotalDisplayRecords", 8);
        mapData.put("aaData", spmpotayatServices.getAllPotAyat(param));
        return mapData;
    }
    
    @RequestMapping(value = "/json/listpotayatgajijson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpotayatgajijson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String idspm = request.getParameter("nospm");
        final String idspp = request.getParameter("idspp");
        final String kode = request.getParameter("kodeSimpeg");
        final Integer idskpd = listSkpd.get(0).getIdSkpd();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        param.put("idspm", idspm);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idspp", idspp);
        param.put("macam", kode);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        
        log.debug("=================== "+param.toString());
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = spmpotayatServices.getBanyakPotAyatGaji(param);
        log.debug("banyak ======= "+banyak);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spmpotayatServices.getAllPotAyatGaji(param));
        return mapData;
    }

    @RequestMapping(value = "/tambahspmpotayat", method = RequestMethod.POST)
    public String prosesupdate(@Valid @ModelAttribute("cmdSpmPot") SpmPotAyat spmpotayat, final RedirectAttributes redirectAttributes, BindingResult result, final HttpServletRequest request) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Integer idskpd = listSkpd.get(0).getIdSkpd();
        //final Timestamp Date = new Timestamp(System.currentTimeMillis());
        final Map< String, Object> param = new HashMap<String, Object>(10);
        //final Integer statuss;
        //final BigDecimal vpot;
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("pengguna", pengguna.getIdPengguna());
        //param.put("tgledit", Date);
        param.put("nospm", request.getParameter("nospm"));

        String kata = "Data SPM Potongan Ayat berhasil disimpan";
        redirectAttributes.addFlashAttribute("pesan", kata);
        return "redirect:/spmpotayat";
    }

    @RequestMapping(value = "/json/prosespindahsimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, String> mapdata, final HttpServletRequest request) {
        final Timestamp tglSkrg = new Timestamp(System.currentTimeMillis());
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Integer idskpd = listSkpd.get(0).getIdSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(7);

        for (int i = 1; i <= 9; i++) {

            SpmPotAyat spmpotayat = new SpmPotAyat();
            spmpotayat.setIdSpm(SipkdHelpers.getIntFromString(mapdata.get("idspm")));
            spmpotayat.setTahun(SipkdHelpers.getIntFromString(tahunAnggaran));
            spmpotayat.setIdSkpd(idskpd);
            spmpotayat.setNilaiPot(SipkdHelpers.getBigDecimalFromString((String) mapdata.get("vpot" + i)));
            spmpotayat.setcPot(mapdata.get("cpot" + i));
            
            if(mapdata.get("cpot" + i).toString().equals("06")){
                spmpotayat.setKodeAkunPajak("411121");
                spmpotayat.setKodeJenisSetor("100");
            } else {
                spmpotayat.setKodeAkunPajak("");
                spmpotayat.setKodeJenisSetor("");
            }

            /*if (i == 9){
             spmpotayat.setPersen(SipkdHelpers.getIntFromString(mapdata.get("asuransi")));
             } else{
             spmpotayat.setPersen(0);
             }*/
            spmpotayat.setPersen(0.0f);

            final Integer status = SipkdHelpers.getIntFromString(mapdata.get("statuss" + i));

            log.debug("ID SPM ========== " + mapdata.get("idspm"));
            log.debug("ID SKPD ========= " + idskpd);
            log.debug("V POT =========== " + (String) mapdata.get("vpot" + i));
            log.debug("C POT =========== " + mapdata.get("cpot" + i));
            log.debug("PERSEN ========== " + spmpotayat.getPersen());
            log.debug("STATUS ========== " + status);

            final String addoredit = mapdata.get("addoredit" + i);

            if ("add".equals(addoredit)) { //if(status == 1){
                spmpotayat.setIdEntry(pengguna.getIdPengguna());
                spmpotayat.setTglEntry(tglSkrg);
                spmpotayatServices.addPotayat(spmpotayat);
                //spmpotayat.setIdSkpd(spmpotayatServices.addPotayat(spmpotayat));
            } else if ("edit".equals(addoredit)) {
                spmpotayat.setIdEdit(pengguna.getIdPengguna());
                spmpotayat.setTglEdit(tglSkrg);
                spmpotayatServices.updatePotayat(spmpotayat);
            }

        }
        return "Data Potongan SPM Berhasil Disimpan ";

    }
    
    @RequestMapping(value = "/json/simpanpotgaji", method = RequestMethod.POST)
    public @ResponseBody
    String simpanpotgaji(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Timestamp tglSkrg = new Timestamp(System.currentTimeMillis());
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        
        final Integer tahun = SipkdHelpers.getIntFromString(mapdata.get("tahun").toString());
        final Integer idskpd = SipkdHelpers.getIntFromString(mapdata.get("idskpd").toString());
        final Integer idspm = SipkdHelpers.getIntFromString(mapdata.get("idspm").toString());
        List<Map<String, Object>> nilailist = (List) mapdata.get("isilist");
        List<SpmPotAyat> listRinci = new ArrayList<>();

        for (Map<String, Object> mapnilailist : nilailist) {

            SpmPotAyat spmpotayat = new SpmPotAyat();
            spmpotayat.setIdSpm(idspm);
            spmpotayat.setTahun(tahun);
            spmpotayat.setIdSkpd(idskpd);
            spmpotayat.setNilaiPot(SipkdHelpers.getBigDecimalFromString(mapnilailist.get("isi").toString()));
            spmpotayat.setcPot(mapnilailist.get("cpot").toString());
            spmpotayat.setKodeEdit(mapnilailist.get("addoredit").toString());
            spmpotayat.setPersen(0.0f);
            //spmpotayat.setIdPot(mapnilailist.get("idtmpot").toString());
            
            spmpotayat.setIdEntry(pengguna.getIdPengguna());
            spmpotayat.setTglEntry(tglSkrg);
            spmpotayat.setIdEdit(pengguna.getIdPengguna());
            spmpotayat.setTglEdit(tglSkrg);
            
            if(mapnilailist.get("cpot").toString().equals("06")){
                spmpotayat.setKodeAkunPajak("411121");
                spmpotayat.setKodeJenisSetor("100");
            } else {
                spmpotayat.setKodeAkunPajak("");
                spmpotayat.setKodeJenisSetor("");
            }
            /*
            log.debug("ID SPM ========== " + idspm);
            log.debug("ID SKPD ========= " + idskpd);
            log.debug("V POT =========== " + SipkdHelpers.getBigDecimalFromString(mapnilailist.get("isi").toString()));
            log.debug("C POT =========== " + mapnilailist.get("cpot").toString());
            log.debug("PERSEN ========== " + spmpotayat.getPersen());
            */
            listRinci.add(spmpotayat);
        }
        spmpotayatServices.addPotayatGaji(listRinci);
        return "Data Potongan SPM Berhasil Disimpan ";

    }

}
