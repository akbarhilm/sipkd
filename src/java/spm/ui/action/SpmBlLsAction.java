/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spm.ui.action;

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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.SpmBlLs;
import spp.model.SpmProses;
import spp.model.SppBl;

//import spp.model.SpmBlLsUjiCoba;
//import spp.services.SppBlServices;
import spm.services.ReferensiServices;
import spm.services.SpmBlLsServices;
import spm.util.BigDecimalPropertyEditor;
import spm.util.SipkdHelpers;
import spm.util.SqlDatePropertyEditor;
import spm.services.SpmBtlLsServices;

/**
 *
 * @author Xalamaster
 */
@Controller
@RequestMapping("/spmblls")
public class SpmBlLsAction {

    private static final Logger log = LoggerFactory.getLogger(SpmBlLsAction.class);

    @Autowired
    SpmBtlLsServices spmBtlLsServices;

    @Autowired
    SpmBlLsServices spmBlLsServices;

    @Autowired
    ReferensiServices referensiServices;

    @RequestMapping(value = "/indexspmblls", method = RequestMethod.GET)
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
        return "spm/bl/ls/indexspmbl";
    }
    /*
     @RequestMapping(value = "/indexspmblls/{idskpd}", method = RequestMethod.GET)
     public String index(@PathVariable Integer idskpd, final HttpServletRequest request) {
     final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
     final List<Skpd> listSkpd = pengguna.getSkpd();
     if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
     log.debug(" listSkpd masuk == " + listSkpd.size());
     request.setAttribute("isall", 1);
     } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
     request.setAttribute("isall", 0);
     request.setAttribute("skpd", listSkpd.get(0));

     }
     request.setAttribute("skpd", referensiServices.getDetailSkpdById(idskpd));
     return "spm/bl/ls/indexspmbl";
     }
     */

    @RequestMapping(value = "/json/getlistspmblls", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspppup(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
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
        param.put("namaskpd", skpd);
        param.put("idskpd", idskpd);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = spmBlLsServices.getBanyakSpmBlLs(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spmBlLsServices.getAllSpmBlLs(param));
        return mapData;
    }

    @RequestMapping(value = "/editspmblls/{idspp}", method = RequestMethod.GET)
    public ModelAndView editspm(@PathVariable Integer idspp, final HttpServletRequest request, SppBl sppBl, SpmBlLs spmBlLs) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Map<String, Object> paramproses = new HashMap<>(2);
        paramproses.put("jenis", 3);
        paramproses.put("beban", "LS");
        final SpmProses spmProses = referensiServices.getSpmBatasProses(paramproses);
        final boolean status = SipkdHelpers.compareDateWithCurrent(spmProses.getBatasWaktu());
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("idspp", idspp);
            request.setAttribute("idskpdu", listSkpd.get(0).getIdSkpd());

        }

        sppBl = spmBlLsServices.getSpmBlLsById(idspp);

        final Integer cek = spmBlLsServices.getCekSpm(idspp);

        Integer ids = spmBlLs.getIdspm();
        if (cek == 0) {
            request.setAttribute("setids", 0);
            request.setAttribute("cek1", 0);
            request.setAttribute("cek2", 0);
        } else {
            final Integer idspm = spmBlLsServices.getIdSpmByIdSpp(idspp);
            request.setAttribute("setids", 1);
            request.setAttribute("cek1", spmBlLsServices.getPotonganValidatorNonAyat(idspm));
            request.setAttribute("cek2", spmBlLsServices.getPotonganValidatorUangMuka(idspm));
        }
        request.setAttribute("status", status);
        return new ModelAndView("spm/bl/ls/editspmblls", "refspmblls", sppBl);

        // final SppBtl sppBtl = spmBtlLsServices.getSpmBtlLsById(idspp);
        // return new ModelAndView("spm/btl/ls/editspmbtlls", "refsppbtl", sppBtl);
    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpanspdbl(@Valid @ModelAttribute("refspmblls") SpmBlLs spmBlLs,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        spmBlLs.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        final StringBuilder sburl = new StringBuilder("redirect:/spmblls/editspmblls/");
        if (result.hasErrors()) {
            log.debug("result ============= " + result.hasErrors());
            return "spm/bl/ls/editspmblls";
        } else {

            final String kodenihil = "1";//spmBlLs.getKodeNihil();

            spmBlLs.setIdskpd(listSkpd.get(0).getIdSkpd());
            spmBlLs.setTglSpp(new Date(System.currentTimeMillis()));
            spmBlLs.setBulan(SipkdHelpers.splitString(spmBlLs.getBulan(), "/", 0));
            spmBlLs.setKodeUangMuka(true);
            spmBlLs.setIdEntry(pengguna.getIdPengguna());
            spmBlLs.setTglEntry(new Timestamp(System.currentTimeMillis()));
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final Integer idspm = spmBlLs.getIdspm();

            if (idspm != null && idspm.compareTo(0) > 0) {
                spmBlLsServices.updateSpmBlLsMaster(spmBlLs);
            } else {
                spmBlLsServices.insertSpmBlLsMaster(spmBlLs);
            }

            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil ditambahkan ")
                    .toString());

            return sburl.append(spmBlLs.getId()).toString();
        }
    }

    @RequestMapping(value = "/json/getlistspdbl", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspd(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Integer idskpd = listSkpd.get(0).getIdSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kodekegiatan = request.getParameter("kodekegiatan");
        // final String spdno = request.getParameter("spdno");
        // param.put("kodekegiatan", StringUtils.trimAllWhitespace(kodekegiatan));
        // param.put("spdno", StringUtils.trimAllWhitespace(spdno));

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idspp", SipkdHelpers.getIntFromString(request.getParameter("idspp")));
        log.debug(param.toString());
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        log.debug(param.toString());
        final long banyak = spmBlLsServices.getBanyakSpdBl(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spmBlLsServices.getAllSpdBl(param));
        return mapData;

    }

    @RequestMapping(value = "/json/getAkunNihil", method = RequestMethod.GET)
    public @ResponseBody
    Integer totalAll(final HttpServletRequest request) {
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");//request.getParameter("tahun");
        final String idspp = request.getParameter("idspp");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahun);
        param.put("idspp", idspp);

        return spmBlLsServices.getAkunNihil(param);
    }

    @RequestMapping(value = "/json/getBasNihil", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getBasNihil(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        //param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", spmBlLsServices.getBasNihil(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getSppUangMuka", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSppUangMuka(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idspp = request.getParameter("idspp");
        final String idkontrak = request.getParameter("idkontrak");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idspp", idspp);
        param.put("idkontrak", idkontrak);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", spmBlLsServices.getSppUangMuka(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getKodePotUmk", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getKodePotUmk(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idspp = request.getParameter("idspp");
        final String idkontrak = request.getParameter("idkontrak");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idkontrak", idkontrak);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", spmBlLsServices.getKodePotUmk(param));

        return mapData;
    }

    @RequestMapping(value = "/editspmbllsPFK/{idspp}", method = RequestMethod.GET)
    public ModelAndView editspmbllsPFK(@PathVariable Integer idspp, final HttpServletRequest request, SppBl sppBl, SpmBlLs spmBlLs) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Map<String, Object> paramproses = new HashMap<>(2);
        paramproses.put("jenis", 3);
        paramproses.put("beban", "LS");
        final SpmProses spmProses = referensiServices.getSpmBatasProses(paramproses);
        final boolean status = SipkdHelpers.compareDateWithCurrent(spmProses.getBatasWaktu());
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("idspp", idspp);
            request.setAttribute("idskpdu", listSkpd.get(0).getIdSkpd());
        }

        sppBl = spmBlLsServices.getSpmBlLsPfkById(idspp);

        final Integer cek = spmBlLsServices.getCekSpm(idspp);

        Integer ids = spmBlLs.getIdspm();
        if (cek == 0) {
            request.setAttribute("setids", 0);
            request.setAttribute("cek1", 0);
            request.setAttribute("cek2", 0);
        } else {
            request.setAttribute("setids", 1);
            request.setAttribute("cek1", spmBlLsServices.getVaBank(idspp));
        }
        request.setAttribute("status", status);
        return new ModelAndView("spm/bl/ls/editspmbllsPFK", "refspmbllspfk", sppBl);
    }

    @RequestMapping(value = "/prosessimpanpfk", method = RequestMethod.POST)
    public String prosessimpanpfk(@Valid @ModelAttribute("refspmblls") SpmBlLs spmBlLs,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        spmBlLs.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        final StringBuilder sburl = new StringBuilder("redirect:/spmblls/editspmbllsPFK/");
        /* if (result.hasErrors()) {
         log.debug("result ============= "+result.hasErrors());
         return "spm/bl/ls/editspmblls";
         } else { */

        final String kodenihil = "1";//spmBlLs.getKodeNihil();
        // untuk sementara, karena VA belum di deploy ke produksi, jadi edit data pfk ga pek edit VA dulu -> VA = 0
        //spmBlLs.setVaBank("0");

        spmBlLs.setIdskpd(listSkpd.get(0).getIdSkpd());
        spmBlLs.setTglSpp(new Date(System.currentTimeMillis()));
        spmBlLs.setBulan(SipkdHelpers.splitString(spmBlLs.getBulan(), "/", 0));
        spmBlLs.setKodeUangMuka(true);
        spmBlLs.setIdEntry(pengguna.getIdPengguna());
        spmBlLs.setTglEntry(new Timestamp(System.currentTimeMillis()));
        final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
        final Integer idspm = spmBlLs.getIdspm();
        final Integer idspp = spmBlLs.getIdspp();

        log.debug("cek anitaikan prosessimpanpfk idspp=" + idspp);

        spmBlLsServices.updatePfk(spmBlLs);
        spmBlLsServices.updateSppPfk(spmBlLs);
        /*
         if (idspm != null && idspm.compareTo(0) > 0) {
         //spmBlLsServices.updateSpmBlLsMaster(spmBlLs);
         spmBlLsServices.updatePfk(spmBlLs);
         spmBlLsServices.updateSppPfk(spmBlLs);
         } else {
         spmBlLsServices.updatePfk(spmBlLs);
         }*/

        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                .append(" berhasil ditambahkan ")
                .toString());

        return sburl.append(spmBlLs.getId()).toString();
        //}
    }

    @RequestMapping(value = "/listbankinduk", method = RequestMethod.GET)
    public ModelAndView listbankinduk(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("/common/bank/listbankinduk");
    }

    @RequestMapping(value = "/json/listbankindukjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listbankindukjson(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kode = request.getParameter("kode");
        final String nama = request.getParameter("nama");

        param.put("kode", kode);
        param.put("nama", nama);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = spmBlLsServices.getCountBankInduk(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spmBlLsServices.getBankInduk(param));
        return mapData;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}
