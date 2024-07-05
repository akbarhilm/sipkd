package spp.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.SppUp;
import spp.model.SppUpRinci;
import spp.services.ReferensiServices;
import spp.services.SpdBLServices;
import spp.services.SppUpServices;
import spp.util.BigDecimalPropertyEditor;
import spp.util.SipkdHelpers;
import spp.util.SqlDatePropertyEditor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/sppup")
public class SppUpAction {

    private static final Logger log = LoggerFactory.getLogger(SppUpAction.class);

    @Autowired
    SppUpServices sppUpServices;

    @Autowired
    ReferensiServices referensiServices;

    @Autowired
    SpdBLServices spdBLServices;

    @RequestMapping(value = "/indexspppup", method = RequestMethod.GET)
    public String index(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            final Skpd skpd = listSkpd.get(0);
            request.setAttribute("skpd", skpd);
            final Map< String, Object> param = new HashMap<String, Object>(2);
            param.put("idskpd", skpd.getIdSkpd());
            param.put("tahun", request.getSession().getAttribute("tahunAnggaran"));
            final int banyak = sppUpServices.getCekBanyakSppUp(param);
            log.debug(" banyak =XXXXXXXXXXXX= " + banyak);
            if (banyak == 0) {
                request.setAttribute("banyak", 0);
            } else {
                request.setAttribute("banyak", 1);
            }
        }

        return "spp/up/indexspppup";

    }

    @RequestMapping(value = "/indexspppup/{idskpd}", method = RequestMethod.GET)
    public String index(@PathVariable Integer idskpd, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = request.getParameter("tahun");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));

        }
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("idskpd", idskpd);
        param.put("tahun", request.getSession().getAttribute("tahunAnggaran"));
        final int banyak = sppUpServices.getCekBanyakSppUp(param);
        log.debug(" banyak =XXXXXXXXXXXX= " + banyak);
        if (banyak == 0) {
            request.setAttribute("banyak", 0);
        } else {
            request.setAttribute("banyak", 1);
        }
        request.setAttribute("skpd", referensiServices.getDetailSkpdById(idskpd));

        return "spp/up/indexspppup";
    }

    @RequestMapping(value = "/json/getlistspppup", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspppup(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("namaskpd");
        final Map< String, Object> param = new HashMap<>(2);
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
        final Map<String, Object> mapData = new HashMap<>(4);
        final int banyak = sppUpServices.getBanyakSppUp(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppUpServices.getAllSppUp(param));
        return mapData;
    }

    @RequestMapping(value = "/addspppup", method = RequestMethod.GET)
    public ModelAndView add(final SppUp sppUp, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final Map<String, Object> paramBank = new HashMap<>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            paramBank.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBank.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            final Map<String, String> dataBank = sppUpServices.getBankRekByIdSkpd(paramBank);
            log.debug(" >>>>>>>>>>>>>  " + dataBank.toString());
            sppUp.setKodeBank(dataBank.get("C_BANK"));
            sppUp.setNamaBank(dataBank.get("N_BANK"));
            sppUp.setNomorRekBank(dataBank.get("I_REK_BANKSTS"));
            request.setAttribute("isall", 0);
        }
        return new ModelAndView("spp/up/addsppup", "refsppup", sppUp);

    }

    @RequestMapping(value = "/json/getlistspdbl", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspd(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final Map< String, Object> param = new HashMap<>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kodekegiatan = request.getParameter("kodekegiatan");
        final String spdno = request.getParameter("spdno");
        param.put("kodekegiatan", StringUtils.trimAllWhitespace(kodekegiatan));
        param.put("spdno", StringUtils.trimAllWhitespace(spdno));

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idspp", SipkdHelpers.getIntFromString(request.getParameter("idspp")));
        log.debug(param.toString());
        final Map<String, Object> mapData = new HashMap<>(4);
        log.debug(param.toString());
        final long banyak = sppUpServices.getBanyakSpdBL(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppUpServices.getAllSpdBL(param));
        return mapData;

    }

    @RequestMapping(value = "/json/getanggarandanspdbantuanlangsung/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, BigDecimal> getanggarandanspd(@PathVariable Integer id, final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<>(2);
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
        final Map< String, Object> param = new HashMap<>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idspp", idspp);
        return sppUpServices.getBanyakSpdBL(param);
    }

    @RequestMapping(value = "/addspppup/{idskpd}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable Integer idskpd, final SppUp sppUp, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final Map<String, Object> paramBank = new HashMap<>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            paramBank.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBank.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            final Map<String, String> dataBank = sppUpServices.getBankRekByIdSkpd(paramBank);
            final Map<String, String> dataBankDki = sppUpServices.getBankDki(paramBank); // data bank nya diganti jadi BANK DKI
            //log.debug(" >>>>>>>>>>>>>  " + dataBank.toString());
            /*
            if (dataBankDki != null) {
                sppUp.setKodeBank(dataBankDki.get("C_BANK"));
                sppUp.setKodeBankTransfer(dataBankDki.get("C_BANK_TRANSFER"));
                sppUp.setNamaBankTransfer(dataBankDki.get("N_BANK_TRANSFER"));
                sppUp.setIdBank(dataBankDki.get("I_ID").toString());

            }*/

            //sppUp.setKodeBank(dataBank.get("C_BANK"));
            //sppUp.setNamaBank(dataBank.get("N_BANK"));
            sppUp.setKodeBank(dataBank.get("C_BANK"));
            sppUp.setKodeBankTransfer(dataBank.get("C_BANK_TRANSFER"));
            sppUp.setNamaBankTransfer(dataBank.get("N_BANK"));
            sppUp.setIdBank(dataBank.get("I_IDBANKPFK").toString());

            sppUp.setNomorRekBank(dataBank.get("I_REK_BANKSPM"));
            request.setAttribute("isall", 0);
        }
        sppUp.setSkpd(referensiServices.getDetailSkpdById(idskpd));
        return new ModelAndView("spp/up/addsppup", "refsppup", sppUp);

    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpanspdbtl(@Valid @ModelAttribute("refsppup") SppUp sppUp,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        sppUp.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        //log.debug(" hasil ################# " + result.getFieldError());
        final StringBuilder sburl = new StringBuilder("redirect:/sppup/edispppup/");
        if (result.hasErrors()) {
            return "spp/up/addsppup";
        } else {
            
            final String namatujuan = request.getParameter("dkinama");
            
            sppUp.setNamaTujuan(namatujuan);
            sppUp.setTglSpp(new Date(System.currentTimeMillis()));
            sppUp.setBulan(SipkdHelpers.splitString(sppUp.getBulan(), "/", 0));
            sppUp.setKodeUangMuka("0");
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sppUp.setIdEntry(pengguna.getIdPengguna());
            sppUp.setTglEntry(new Timestamp(System.currentTimeMillis()));
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final List<SppUpRinci> listSppUpRinci = new ArrayList<>(banyakrinci);
            for (int i = 0; i < banyakrinci; i++) {

                final String penanda = "idspd" + (i + 1);
                final String idspd = request.getParameter(penanda);
                if (!StringUtils.isEmpty(idspd)) {
                    final SppUpRinci sppUpRinci = new SppUpRinci();

                    sppUpRinci.setIdSpd(SipkdHelpers.getIntFromString(idspd));
                    sppUpRinci.setNilaiSpp(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaiSpp" + (i + 1))));
                    sppUpRinci.setIdEntry(pengguna.getIdPengguna());
                    sppUpRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));

                    listSppUpRinci.add(sppUpRinci);
                }
            }
            sppUp.setSppUpRinci(listSppUpRinci);
            sppUpServices.insertSppUp(sppUp);
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil ditambahkan ")
                    .toString());

            return sburl.append(sppUp.getId()).toString();
        }
    }

    @RequestMapping(value = "/edispppup/{idspp}", method = RequestMethod.GET)
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
        final SppUp sppUp = sppUpServices.getSppUPById(idspp);
        return new ModelAndView("spp/up/editsppup", "refsppup", sppUp);
    }

    @RequestMapping(value = "/prosesupdate", method = RequestMethod.POST)
    public String prosesupdatesppup(@Valid @ModelAttribute("refsppup") SppUp sppUp,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        sppUp.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

        final StringBuilder sburl = new StringBuilder("redirect:/sppup/edispppup/");
        if (result.hasErrors()) {
            return "spp/up/addsppup";
        } else {
            sppUp.setTglSpp(new Date(System.currentTimeMillis()));
            sppUp.setBulan(SipkdHelpers.splitString(sppUp.getBulan(), "/", 0));
            sppUp.setKodeUangMuka("0");
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sppUp.setIdEntry(pengguna.getIdPengguna());
            sppUp.setTglEntry(new Timestamp(System.currentTimeMillis()));
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final List<SppUpRinci> listSppUpRinci = new ArrayList<SppUpRinci>(banyakrinci);
            final Integer idSpp = sppUp.getId();
            for (int i = 0; i < banyakrinci; i++) {

                final String penanda = "idspd" + (i + 1);
                final String idspd = request.getParameter(penanda);
                if (!StringUtils.isEmpty(idspd)) {
                    final SppUpRinci sppUpRinci = new SppUpRinci();
                    sppUpRinci.setIdspp(idSpp);
                    sppUpRinci.setIdSpd(SipkdHelpers.getIntFromString(idspd));
                    sppUpRinci.setNilaiSpp(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaiSpp" + (i + 1))));
                    sppUpRinci.setIdEntry(pengguna.getIdPengguna());
                    sppUpRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));

                    listSppUpRinci.add(sppUpRinci);
                }
            }
            sppUp.setSppUpRinci(listSppUpRinci);
            sppUpServices.updateSppUp(sppUp);
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil diupdate ")
                    .toString());

            return sburl.append(sppUp.getId())
                    .toString();
        }
    }

    @RequestMapping(value = "/json/gettotalspddanspp", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, BigDecimal> gettotalspddanspp(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idspp", SipkdHelpers.getIntFromString(request.getParameter("idspp")));
        final String kodekegiatan = request.getParameter("kodekegiatan");
        final String spdno = request.getParameter("spdno");
        param.put("kodekegiatan", StringUtils.trimAllWhitespace(kodekegiatan));
        param.put("spdno", StringUtils.trimAllWhitespace(spdno));
        return sppUpServices.getTotalSPDDanSPP(param);
    }

    //----------------------------------------
    @RequestMapping(value = "/json/gettotalnilaispd ", method = RequestMethod.GET)
    public @ResponseBody
    String gettotalnilaispd(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idspp = request.getParameter("idspp");
        final Map< String, Object> param = new HashMap<>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idspp", idspp);
        return sppUpServices.getTotalSpdBL(param);
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
