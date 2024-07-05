package spp.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
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
import org.springframework.ui.Model;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spp.model.Akun;
import spp.model.Kegiatan;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.SppTu;
import spp.model.SppTuRinci;
import spp.services.BastServices;
import spp.services.ReferensiServices;
import spp.services.SpdBLServices;
import spp.services.SpdServices;
import spp.services.SppTuServices;
import spp.util.BigDecimalPropertyEditor;
import spp.util.SipkdHelpers;
import spp.util.SqlDatePropertyEditor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/spptu")
public class SppTuAction {

    private static final Logger log = LoggerFactory.getLogger(SppTuAction.class);

    @Autowired
    SppTuServices sppTuServices;

    @Autowired
    ReferensiServices referensiServices;

    @Autowired
    SpdBLServices spdBLServices;

    @Autowired
    SpdServices spdServices;

    @Autowired
    BastServices bastServices;

    @RequestMapping(value = "/indexspptu", method = RequestMethod.GET)
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
        return "spp/tu/indexspptu";

    }

    @RequestMapping(value = "/indexspptu/{idskpd}", method = RequestMethod.GET)
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
        return "spp/tu/indexspptu";
    }
    
    @RequestMapping(value = "/json/cektglspjtujurnal", method = RequestMethod.GET)
    public @ResponseBody
    String cekspjtujurnal(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        
        final Map< String, Object> param = new HashMap<String, Object>(2);
        
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        
        return sppTuServices.cekTglSpjTuJurnal(param);
    }

    @RequestMapping(value = "/json/getlistspptu", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspptu(final HttpServletRequest request) {
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
        final int banyak = sppTuServices.getBanyakSppTu(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppTuServices.getAllSppTu(param));
        return mapData;
    }

    @RequestMapping(value = "/addspptu", method = RequestMethod.GET)
    public ModelAndView add(final SppTu sppTu, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final Map<String, Object> paramBank = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            paramBank.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBank.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            final Map<String, String> dataBank = sppTuServices.getBankRekByIdSkpd(paramBank);
            log.debug(" >>>>>>>>>>>>>  " + dataBank.toString());
            sppTu.setKodeBank(dataBank.get("C_BANK"));
            sppTu.setNamaBank(dataBank.get("N_BANK"));
            sppTu.setNomorRekBank(dataBank.get("I_REK_BANKSTS"));
            request.setAttribute("isall", 0);
        }
        return new ModelAndView("spp/tu/addspptu", "refspptu", sppTu);

    }

    @RequestMapping(value = "/json/getlistspdbl", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspd(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kodekegiatan = request.getParameter("kodekegiatan");
        final String spdno = request.getParameter("spdno");
        final String id = request.getParameter("id");
        param.put("id", id);
        param.put("kodekegiatan", kodekegiatan.toString()); //StringUtils.trimAllWhitespace(kodekegiatan));
        param.put("spdno", StringUtils.trimAllWhitespace(spdno));
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idspp", SipkdHelpers.getIntFromString(request.getParameter("idspp")));
        log.debug(param.toString());
        final Map<String, Object> mapData = new HashMap<String, Object>(5);
        log.debug(param.toString());
        final long banyak = sppTuServices.getBanyakSpdBL(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppTuServices.getAllSpdBL(param));
        return mapData;

    }

    @RequestMapping(value = "/json/getanggarandanspdbantuanlangsung/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, BigDecimal> getanggarandanspd(@PathVariable Integer id, final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", id);
        return spdBLServices.getTotalAnggaranDanSpd(param);
    }

    @RequestMapping(value = "/json/getlistspdblbanyak ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getlistspdblbanyak(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idspp = request.getParameter("idspp");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idspp", idspp);
        return sppTuServices.getBanyakSpdBL(param);
    }

    @RequestMapping(value = "/addspptu/{idskpd}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable Integer idskpd, final SppTu sppTu, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final Map<String, Object> paramBank = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            paramBank.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBank.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            final Map<String, String> dataBank = sppTuServices.getBankRekByIdSkpd(paramBank);
            final Map<String, String> dataBankDki = sppTuServices.getBankDki(paramBank); // data bank nya diganti jadi BANK DKI
            //log.debug(" >>>>>>>>>>>>>  " + dataBank.toString());

            /* if (dataBankDki != null) {
             sppTu.setKodeBank(dataBankDki.get("C_BANK"));
             sppTu.setKodeBankTransfer(dataBankDki.get("C_BANK_TRANSFER"));
             sppTu.setNamaBankTransfer(dataBankDki.get("N_BANK_TRANSFER"));
             sppTu.setIdBank(dataBankDki.get("I_ID").toString());
                
             }*/
            if (dataBank != null) {
                //sppTu.setKodeBank(dataBank.get("C_BANK"));
                //sppTu.setNamaBank(dataBank.get("N_BANK"));
                sppTu.setKodeBank(dataBank.get("C_BANK"));
                sppTu.setKodeBankTransfer(dataBank.get("C_BANK_TRANSFER"));
                sppTu.setNamaBankTransfer(dataBank.get("N_BANK"));
                sppTu.setIdBank(dataBank.get("I_IDBANKPFK").toString());
                sppTu.setNomorRekBank(dataBank.get("I_REK_BANKSPM"));
            }
            final Map<String, Object> paramBendahara = new HashMap<String, Object>();
            paramBendahara.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBendahara.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            final Map<String, String> dataBendahara = sppTuServices.getBendaharaByIdSkpd(paramBendahara);

            if (dataBendahara != null) {
                sppTu.setNipPptk(dataBendahara.get("I_NIP_PKBLJ"));
                sppTu.setNamaPptk(dataBendahara.get("N_PKBLJ"));
            }
            request.setAttribute("isall", 0);
        }
        request.setAttribute("kodekegiatanfilter", request.getParameter("kodekegiatanfilter"));
        request.setAttribute("nospdfilter", request.getParameter("nospdfilter"));
        sppTu.setSkpd(referensiServices.getDetailSkpdById(idskpd));
        return new ModelAndView("spp/tu/addspptu", "refspptu", sppTu);

    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpanspdbl(@Valid @ModelAttribute("refspptu") SppTu sppTu,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        sppTu.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        //log.debug(" hasil ################# " + result.getFieldError());
        final StringBuilder sburl = new StringBuilder("redirect:/spptu/edispptu/");
        if (result.hasErrors()) {
            return "spp/tu/addspptu";
        } else {
            
            final String namatujuan = request.getParameter("dkinama");
            
            sppTu.setNamaTujuan(namatujuan);
            sppTu.setTglSpp(new Date(System.currentTimeMillis()));
            sppTu.setBulan(SipkdHelpers.splitString(sppTu.getBulan(), "/", 0));
            sppTu.setKodeUangMuka("0");
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sppTu.setIdEntry(pengguna.getIdPengguna());
            sppTu.setTglEntry(new Timestamp(System.currentTimeMillis()));
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final List<SppTuRinci> listSppTuRinci = new ArrayList<SppTuRinci>(banyakrinci);
            //log.debug(" Map param  "+  request.getParameterMap().toString());
            for (int i = 0; i < banyakrinci; i++) {

                final String penanda = "cekpilih" + (i + 1);
                final String idBl = request.getParameter(penanda);
                //    log.debug(penanda+" idBl ################# " + idBl);
                if (idBl != null && !idBl.isEmpty()) {

                    final String noSpd = request.getParameter("noSpd" + idBl);
                    final SppTuRinci sppTuRinci = new SppTuRinci();
                    log.debug(" noSpd ################# " + noSpd);
                    final Akun akun = new Akun();
                    akun.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idakun" + idBl)));
                    sppTuRinci.setAkun(akun);
                    final Kegiatan kegiatan = new Kegiatan();
                    kegiatan.setIdKegiatan(SipkdHelpers.getIntFromString(request.getParameter("idkegiatan" + idBl)));
                    sppTuRinci.setKegiatan(kegiatan);
                    sppTuRinci.setIdBl(SipkdHelpers.getIntFromString(idBl));
                    sppTuRinci.setNoSpd(noSpd);
                    sppTuRinci.setIdSpd(SipkdHelpers.getIntFromString(request.getParameter("idspd" + idBl)));
                    sppTuRinci.setNilaiSpp(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaispp" + idBl)));
                    sppTuRinci.setIdEntry(pengguna.getIdPengguna());
                    sppTuRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));

                    listSppTuRinci.add(sppTuRinci);
                }
            }
            sppTu.setSppTuRinci(listSppTuRinci);
            sppTuServices.insertSppTu(sppTu);
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil ditambahkan ")
                    .toString());

            return sburl.append(sppTu.getId()).toString();
        }
    }

    @RequestMapping(value = "/edispptu/{idspp}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idspp, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {

            request.setAttribute("isall", 0);
        }
        request.setAttribute("kodekegiatanfilter", request.getParameter("kodekegiatanfilter"));
        request.setAttribute("nospdfilter", request.getParameter("nospdfilter"));
        final SppTu sppTu = sppTuServices.getSppTuById(idspp);
        return new ModelAndView("spp/tu/edispptu", "refspptu", sppTu);
    }

    @RequestMapping(value = "/delspptu/{idspp}", method = RequestMethod.GET)
    public ModelAndView hapus(@PathVariable Integer idspp, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {

            request.setAttribute("isall", 0);
        }
        final SppTu sppTu = sppTuServices.getSppTuById(idspp);
        return new ModelAndView("spp/tu/delspptu", "refspptu", sppTu);
    }

    @RequestMapping(value = "/prossesdelete", method = RequestMethod.POST)
    public String deletespptu(@Valid @ModelAttribute("refspptu") SppTu sppTu) {
        sppTuServices.deleteSppTuMaster(sppTu.getId());
        return "redirect:/spptu/indexspptu/";
    }

    @RequestMapping(value = "/listspdpopup", method = RequestMethod.GET)
    public ModelAndView listspdpopup(final HttpServletResponse response, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("/spp/tu/listspdpopup");
    }

    @RequestMapping(value = "/json/listspdpopup", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listspdpopup(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("spd");
        param.put("spd", skpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = spdServices.getBanyakSpd(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdServices.getSpd(param));
        return mapData;
    }

    @RequestMapping(value = "/prosesupdate", method = RequestMethod.POST)
    public String prosesupdateSppTu(@Valid @ModelAttribute("refspptu") SppTu sppTu,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        sppTu.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

        final StringBuilder sburl = new StringBuilder("redirect:/spptu/edispptu/");
        if (result.hasErrors()) {
            return "spp/tu/addspptu";
        } else {
            sppTu.setTglSpp(new Date(System.currentTimeMillis()));
            sppTu.setBulan(SipkdHelpers.splitString(sppTu.getBulan(), "/", 0));
            sppTu.setKodeUangMuka("0");
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sppTu.setIdEntry(pengguna.getIdPengguna());
            sppTu.setTglEntry(new Timestamp(System.currentTimeMillis()));
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final List<SppTuRinci> listSppTuRinci = new ArrayList<SppTuRinci>(banyakrinci);
            final Integer idSpp = sppTu.getId();
            for (int i = 0; i < banyakrinci; i++) {

                final String penanda = "cekpilih" + (i + 1);
                final String isCek = request.getParameter(penanda);

                if (isCek != null && !isCek.isEmpty()) {
                    final String idBl = request.getParameter("idblhidden" + (i + 1));//request.getParameter(penanda);
                    log.debug(isCek + " ******idBl******* isCek " + idBl);
                    final String noSpd = request.getParameter("noSpd" + (i + 1));
                    final SppTuRinci sppTuRinci = new SppTuRinci();
                    log.debug(" noSpd ################# " + noSpd);
                    final Akun akun = new Akun();
                    akun.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idakun" + (i + 1))));
                    sppTuRinci.setAkun(akun);
                    final Kegiatan kegiatan = new Kegiatan();
                    kegiatan.setIdKegiatan(SipkdHelpers.getIntFromString(request.getParameter("idkegiatan" + (i + 1))));
                    sppTuRinci.setKegiatan(kegiatan);
                    sppTuRinci.setIdBl(SipkdHelpers.getIntFromString(idBl));
                    sppTuRinci.setNoSpd(noSpd);
                    sppTuRinci.setIdspp(idSpp);
                    sppTuRinci.setIdSpd(SipkdHelpers.getIntFromString(request.getParameter("idspd" + (i + 1))));
                    sppTuRinci.setNilaiSpp(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaispp" + (i + 1))));
                    sppTuRinci.setIdEntry(pengguna.getIdPengguna());
                    sppTuRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));

                    listSppTuRinci.add(sppTuRinci);
                }
            }
            sppTu.setSppTuRinci(listSppTuRinci);
            sppTuServices.updateSppTu(sppTu);
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil diupdate ")
                    .toString());

            return sburl.append(sppTu.getId())
                    .toString();
        }
    }
    
    @RequestMapping(value = "/json/getBanyakTU", method = RequestMethod.GET)
    public @ResponseBody
    Integer getBanyakTU(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        return sppTuServices.getBanyakTU(param);
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
