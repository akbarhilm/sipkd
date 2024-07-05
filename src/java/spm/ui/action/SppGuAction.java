package spm.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
//import spp.model.NilaiDataGu;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.SppGu;
import spp.model.SppGuRinci;
import spm.services.ReferensiServices;
import spm.services.SpdBLServices;
import spm.services.SppGuServices;
import spm.util.BigDecimalPropertyEditor;
import spm.util.SipkdHelpers;
import spm.util.SqlDatePropertyEditor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/sppgu")
public class SppGuAction {

    private static final Logger log = LoggerFactory.getLogger(SppGuAction.class);

    @Autowired
    SppGuServices sppGuServices;

    @Autowired
    ReferensiServices referensiServices;

    @Autowired
    SpdBLServices spdBLServices;

    private final NumberFormat sdf = NumberFormat.getNumberInstance(new Locale("id"));

    @RequestMapping(value = "/indexsppgu", method = RequestMethod.GET)
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

        return "spp/gu/indexsppgu";

    }

    @RequestMapping(value = "/indexsppgu/{idskpd}", method = RequestMethod.GET)
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
        return "spp/gu/indexsppgu";
    }

    @RequestMapping(value = "/json/getlistsppgu", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistsppgu(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        // final String skpd = request.getParameter("namaskpd");
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
        //param.put("namaskpd", skpd);
        param.put("idskpd", idskpd);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = sppGuServices.getBanyakSppGu(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppGuServices.getAllSppGu(param));
        return mapData;
    }

    @RequestMapping(value = "/addsppgu", method = RequestMethod.GET)
    public ModelAndView add(final SppGu sppGu, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final Map<String, Object> paramBank = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            paramBank.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBank.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            final Map<String, String> dataBank = sppGuServices.getBankRekByIdSkpd(paramBank);
            log.debug(" >>>>>>>>>>>>>  " + dataBank.toString());
            sppGu.setKodeBank(dataBank.get("C_BANK"));
            sppGu.setNamaBank(dataBank.get("N_BANK"));
            sppGu.setNomorRekBank(dataBank.get("I_REK_BANKSPM"));
            final Map<String, Object> paramBendahara = new HashMap<String, Object>();
            paramBendahara.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBendahara.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            final Map<String, String> dataBendahara = sppGuServices.getBendaharaByIdSkpd(paramBendahara);
            log.debug(" >>>>>>>>>>>>>  " + dataBendahara.toString());
            sppGu.setNipPptk(dataBendahara.get("I_NIP_PKBLJ"));
            sppGu.setNamaPptk(dataBendahara.get("N_PKBLJ"));
            final Map<String, Object> paramPagu = new HashMap<String, Object>();

            paramPagu.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramPagu.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));

            final Map<String, String> dataPagu = sppGuServices.getDataPagu(paramPagu);

            request.setAttribute("totalSpp", dataPagu.get("TOTAL_SPP"));
            request.setAttribute("totalSpj", dataPagu.get("TOTAL_SPJ"));
            request.setAttribute("nilaiSisaPaguSpp", dataPagu.get("PAGU_SPP"));

            request.setAttribute("isall", 0);
        }
        return new ModelAndView("spp/gu/addsppgu", "refsppgu", sppGu);

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
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        log.debug(param.toString());
        final long banyak = sppGuServices.getBanyakSpdBL(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppGuServices.getAllSpdBL(param));
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

    /*@RequestMapping(value = "/json/getdatapagu/{id}", method = RequestMethod.GET)
     public @ResponseBody
     List<SppGu> getdatapagu(@PathVariable Integer id, final HttpServletRequest request) {
     final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
     final Map< String, Object> param = new HashMap<String, Object>(2);
     param.put("tahun", tahunAnggaran);
     param.put("idskpd", id);
     return sppGuServices.getTotalPaguDanSpp(param);
     }*/
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
        return sppGuServices.getBanyakSpdBL(param);
    }

    @RequestMapping(value = "/addsppgu/{idskpd}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable Integer idskpd, final SppGu sppGu, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final Map<String, Object> paramBank = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            paramBank.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBank.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            final Map<String, String> dataBank = sppGuServices.getBankRekByIdSkpd(paramBank);
            log.debug(" >>>>>>>>>>>>>  " + dataBank.toString());
            sppGu.setKodeBank(dataBank.get("C_BANK"));
            sppGu.setNamaBank(dataBank.get("N_BANK"));
            sppGu.setNomorRekBank(dataBank.get("I_REK_BANKSPM"));
            final Map<String, Object> paramBendahara = new HashMap<String, Object>();
            paramBendahara.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBendahara.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            final Map<String, String> dataBendahara = sppGuServices.getBendaharaByIdSkpd(paramBendahara);
            log.debug(" >>>>>>>>>>>>>  " + dataBendahara.toString());
            sppGu.setNipPptk(dataBendahara.get("I_NIP_PKBLJ"));
            sppGu.setNamaPptk(dataBendahara.get("N_PKBLJ"));

            final Map<String, Object> paramPagu = new HashMap<String, Object>();

            paramPagu.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramPagu.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));

            final Map<String, String> dataPagu = sppGuServices.getDataPagu(paramPagu);

            request.setAttribute("totalSpp", dataPagu.get("TOTAL_SPP"));
            request.setAttribute("totalSpj", dataPagu.get("TOTAL_SPJ"));
            request.setAttribute("nilaiSisaPaguSpp", dataPagu.get("PAGU_SPP"));

            request.setAttribute("isall", 0);
        }
        sppGu.setSkpd(referensiServices.getDetailSkpdById(idskpd));
        return new ModelAndView("spp/gu/addsppgu", "refsppgu", sppGu);

    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpanspdbtl(@Valid @ModelAttribute("refsppgu") SppGu sppGu,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        sppGu.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        //log.debug(" hasil ################# " + result.getFieldError());
        final StringBuilder sburl = new StringBuilder("redirect:/sppgu/editsppgu/");
        if (result.hasErrors()) {
            return "spp/gu/addsppgu";
        } else {
            sppGu.setTglSpp(new Date(System.currentTimeMillis()));
            sppGu.setBulan(SipkdHelpers.splitString(sppGu.getBulan(), "/", 0));
            sppGu.setKodeUangMuka("0");
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sppGu.setIdEntry(pengguna.getIdPengguna());
            sppGu.setTglEntry(new Timestamp(System.currentTimeMillis()));
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final List<SppGuRinci> listSppGuRinci = new ArrayList<SppGuRinci>(banyakrinci);
            for (int i = 0; i < banyakrinci; i++) {

                final String penanda = "idspd" + (i + 1);
                final String idspd = request.getParameter(penanda);
                if (!StringUtils.isEmpty(idspd)) {
                    final SppGuRinci sppGuRinci = new SppGuRinci();

                    sppGuRinci.setIdSpd(SipkdHelpers.getIntFromString(idspd));
                    sppGuRinci.setNilaiSpp(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaiSpp" + (i + 1))));
                    sppGuRinci.setIdEntry(pengguna.getIdPengguna());
                    sppGuRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));

                    listSppGuRinci.add(sppGuRinci);
                }
            }
            sppGu.setSppGuRinci(listSppGuRinci);
            sppGuServices.insertSppGu(sppGu);
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil ditambahkan ")
                    .toString());

            return sburl.append(sppGu.getId()).toString();
        }
    }

    @RequestMapping(value = "/editsppgu/{idspp}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idspp, final HttpServletRequest request, SppGu sppGu,
            Map TOTAl_SPP) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {

            final Map<String, Object> paramPagu = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            paramPagu.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramPagu.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));

            final Map<String, String> dataPagu = sppGuServices.getDataPagu(paramPagu);

            request.setAttribute("totalSpp", dataPagu.get("TOTAL_SPP"));
            request.setAttribute("totalSpj", dataPagu.get("TOTAL_SPJ"));
            request.setAttribute("nilaiSisaPaguSpp", dataPagu.get("PAGU_SPP"));

            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        request.setAttribute("kodekegiatanfilter", request.getParameter("kodekegiatanfilter"));
        request.setAttribute("nospdfilter", request.getParameter("nospdfilter"));
       // request.setAttribute("totalSpp", sppGuServices.getDataPagu(TOTAl_SPP));

        sppGu = sppGuServices.getSppGuById(idspp);

        return new ModelAndView("spp/gu/editsppgu", "refsppgu", sppGu);
    }

    @RequestMapping(value = "/prosesupdate", method = RequestMethod.POST)
    public String prosesupdate(@Valid @ModelAttribute("refsppgu") SppGu sppGu,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        sppGu.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

        final StringBuilder sburl = new StringBuilder("redirect:/sppgu/editsppgu/");
        if (result.hasErrors()) {
            return "spp/gu/addsppgu";
        } else {
            sppGu.setTglSpp(new Date(System.currentTimeMillis()));
            sppGu.setBulan(SipkdHelpers.splitString(sppGu.getBulan(), "/", 0));
            sppGu.setKodeUangMuka("0");
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sppGu.setIdEntry(pengguna.getIdPengguna());
            sppGu.setTglEntry(new Timestamp(System.currentTimeMillis()));
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final List<SppGuRinci> listSppGuRinci = new ArrayList<SppGuRinci>(banyakrinci);
            final Integer idSpp = sppGu.getId();
            for (int i = 0; i < banyakrinci; i++) {

                final String penanda = "idspd" + (i + 1);
                final String idspd = request.getParameter(penanda);
                if (!StringUtils.isEmpty(idspd)) {
                    final SppGuRinci sppGuRinci = new SppGuRinci();
                    sppGuRinci.setIdspp(idSpp);
                    sppGuRinci.setIdSpd(SipkdHelpers.getIntFromString(idspd));
                    sppGuRinci.setNilaiSpp(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaiSpp" + (i + 1))));
                    sppGuRinci.setIdEntry(pengguna.getIdPengguna());
                    sppGuRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));

                    listSppGuRinci.add(sppGuRinci);
                }
            }
            sppGu.setSppGuRinci(listSppGuRinci);
            sppGuServices.updateSppGu(sppGu);
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil diupdate ")
                    .toString());

            return sburl.append(sppGu.getId())
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
        return sppGuServices.getTotalSPDDanSPP(param);
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
