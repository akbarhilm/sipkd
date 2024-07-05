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
import spp.model.Akun;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.SppBiaya;
import spp.model.SppBiayaRinci;
import spp.services.ReferensiServices;
import spp.services.SpdBLServices;
import spp.services.SppBiayaServices;
import spp.util.BigDecimalPropertyEditor;
import spp.util.SipkdHelpers;
import spp.util.SqlDatePropertyEditor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/biaya_old")
public class SppBiayaAction_old {

    private static final Logger log = LoggerFactory.getLogger(SppBiayaAction_old.class);

    @Autowired
    SppBiayaServices sppBiayaServices;

    @Autowired
    ReferensiServices referensiServices;

    @Autowired
    SpdBLServices spdBLServices;

    @RequestMapping(value = "/listbiaya", method = RequestMethod.GET)
    public String index(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            // request.setAttribute("skpd", listSkpd.get(0));
        }
        return "spp/biaya/listbiaya";

    }

    @RequestMapping(value = "/listbiaya/{idskpd}", method = RequestMethod.GET)
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
        return "spp/biaya/listbiaya";
    }

    @RequestMapping(value = "/json/getlistsppbiaya", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistsppbiaya(final HttpServletRequest request) {
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
        final int banyak = sppBiayaServices.getBanyakSppBiaya(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppBiayaServices.getAllSppBiaya(param));
        return mapData;
    }

    @RequestMapping(value = "/addsppbiaya", method = RequestMethod.GET)
    public ModelAndView add(final SppBiaya sppBiaya, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final Map<String, Object> paramBank = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            paramBank.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBank.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            final Map<String, String> dataBank = sppBiayaServices.getBankRekByIdSkpd(paramBank);
            log.debug(" >>>>>>>>>>>>>  " + dataBank.toString());
            sppBiaya.setKodeBank(dataBank.get("C_BANK"));
            sppBiaya.setNamaBank(dataBank.get("N_BANK"));
            sppBiaya.setNomorRekBank(dataBank.get("I_REK_BANKSPM"));
            request.setAttribute("isall", 0);

        }
        return new ModelAndView("spp/biaya/addsppbiaya", "refsppbiaya", sppBiaya);

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
        final String spdno = request.getParameter("spdno");
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
        final long banyak = sppBiayaServices.getBanyakSpdBiaya(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppBiayaServices.getAllSpdBiaya(param));
        return mapData;

    }

    @RequestMapping(value = "/json/getanggarandanspdbantuanlangsung/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, BigDecimal> getanggarandanspd(@PathVariable Integer id, final HttpServletRequest request) {
        final String TahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("tahun", TahunAnggaran);
        param.put("idskpd", id);
        return spdBLServices.getTotalAnggaranDanSpd(param);
    }

    @RequestMapping(value = "/json/getlistspdbiayabanyak ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getlistspdbiayabanyak(final HttpServletRequest request) {
        final String TahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idspp = request.getParameter("idspp");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", TahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idspp", idspp);
        return sppBiayaServices.getBanyakSpdBiaya(param);
    }

    @RequestMapping(value = "/addsppbiaya/{idskpd}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable Integer idskpd, final SppBiaya sppBiaya, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);

        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final Map<String, Object> paramBank = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            paramBank.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBank.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            final Map<String, String> dataBank = sppBiayaServices.getBankRekByIdSkpd(paramBank);
            final Map<String, String> dataBankDki = sppBiayaServices.getBankDki(paramBank); // data bank nya diganti jadi BANK DKI
            //log.debug(" >>>>>>>>>>>>>  " + dataBank.toString());

            if (dataBankDki != null) {
                sppBiaya.setKodeBank(dataBankDki.get("C_BANK"));
                sppBiaya.setKodeBankTransfer(dataBankDki.get("C_BANK_TRANSFER"));
                sppBiaya.setNamaBankTransfer(dataBankDki.get("N_BANK_TRANSFER"));
                sppBiaya.setIdBank(dataBankDki.get("I_ID").toString());
            }
            
            //sppBiaya.setKodeBank(dataBank.get("C_BANK"));
            //sppBiaya.setNamaBank(dataBank.get("N_BANK"));
            sppBiaya.setNomorRekBank(dataBank.get("I_REK_BANKSPM"));
            request.setAttribute("isall", 0);

            final Map<String, Object> paramBendahara = new HashMap<String, Object>();
            paramBendahara.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBendahara.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            final Map<String, String> dataBendahara = sppBiayaServices.getBendaharaByIdSkpd(paramBendahara);
            //
            if (dataBendahara != null) {
                sppBiaya.setNipPptk(dataBendahara.get("I_NIP_PKBLJ"));
                sppBiaya.setNamaPptk(dataBendahara.get("N_PKBLJ"));
            }

        }

        sppBiaya.setSkpd(referensiServices.getDetailSkpdById(idskpd));
        return new ModelAndView("spp/biaya/addsppbiaya", "refsppbiaya", sppBiaya);

    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpanspdbtl(@Valid @ModelAttribute("refsppbiaya") SppBiaya sppBiaya,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        sppBiaya.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        //log.debug(" hasil ################# " + result.getFieldError());
        final StringBuilder sburl = new StringBuilder("redirect:/biaya/editsppbiaya/");
        if (result.hasErrors()) {
            return "spp/biaya/addsppbiaya";
        } else {
            log.debug("---> " + request.getParameter("namaPenerima"));
            log.debug("---> " + request.getParameter("namaYayasan"));
            final String namatujuan = request.getParameter("dkinama");
            
            sppBiaya.setNamaTujuan(namatujuan);
            sppBiaya.setTglSpp(new Date(System.currentTimeMillis()));
            sppBiaya.setBulan(SipkdHelpers.splitString(sppBiaya.getBulan(), "/", 0));
            sppBiaya.setKodeUangMuka("0");
            sppBiaya.setNamaPenerima(request.getParameter("namaPenerima"));
            sppBiaya.setNamaYayasan(request.getParameter("namaYayasan"));
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sppBiaya.setIdEntry(pengguna.getIdPengguna());
            sppBiaya.setTglEntry(new Timestamp(System.currentTimeMillis()));
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final List<SppBiayaRinci> listSppBiayaRinci = new ArrayList<SppBiayaRinci>(banyakrinci);
            //log.debug(" Map param  "+  request.getParameterMap().toString());
            for (int i = 0; i < banyakrinci; i++) {

                final String penanda = "cekpilih" + (i + 1);
                final String idBiaya = request.getParameter(penanda);
                //    log.debug(penanda+" idBl ################# " + idBl);
                if (idBiaya != null && !idBiaya.isEmpty()) {

                    final String noSpd = request.getParameter("noSpd" + idBiaya);
                    final SppBiayaRinci sppBiayaRinci = new SppBiayaRinci();
                    log.debug(" noSpd ################# " + noSpd);
                    final Akun akun = new Akun();
                    akun.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idakun" + idBiaya)));
                    sppBiayaRinci.setAkun(akun);

                    sppBiayaRinci.setIdBiaya(SipkdHelpers.getIntFromString(idBiaya));
                    sppBiayaRinci.setNoSpd(noSpd);
                    sppBiayaRinci.setIdSpd(SipkdHelpers.getIntFromString(request.getParameter("idspd" + idBiaya)));
                    sppBiayaRinci.setNilaiSpp(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaispp" + idBiaya)));
                    sppBiayaRinci.setIdEntry(pengguna.getIdPengguna());
                    sppBiayaRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));

                    listSppBiayaRinci.add(sppBiayaRinci);
                }
            }
            sppBiaya.setSppBiayaRinci(listSppBiayaRinci);
            final List<Skpd> listSkpd = pengguna.getSkpd();
            final Integer idskpd = listSkpd.get(0).getIdSkpd();

            sppBiaya.setIdskpd(idskpd);
            sppBiayaServices.insertSppBiaya(sppBiaya);
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil ditambahkan ")
                    .toString());

            return sburl.append(sppBiaya.getId()).toString();
        }
    }

    @RequestMapping(value = "/deletesppbiaya/{idspp}", method = RequestMethod.GET)
    public ModelAndView hapus(@PathVariable Integer idspp, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }
        final SppBiaya sppBiaya = sppBiayaServices.getSppBiayaById(idspp);
        return new ModelAndView("spp/biaya/deletesppbiaya", "refsppbiaya", sppBiaya);
    }

    @RequestMapping(value = "/prosesdelete", method = RequestMethod.POST)
    public String deletesppbiaya(@Valid @ModelAttribute("refsppbiaya") SppBiaya sppBiaya) {
        sppBiayaServices.deleteSppBiayaMaster(sppBiaya.getId());
        return "redirect:/biaya/listbiaya/";
    }

    @RequestMapping(value = "/editsppbiaya/{idspp}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idspp, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }

        final SppBiaya sppBiaya = sppBiayaServices.getSppBiayaById(idspp);
        return new ModelAndView("spp/biaya/editsppbiaya", "refsppbiaya", sppBiaya);
    }

    @RequestMapping(value = "/prosesupdate", method = RequestMethod.POST)
    public String prosesupdatesppup(@Valid @ModelAttribute("refsppbiaya") SppBiaya sppBiaya,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        sppBiaya.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        //log.debug(" hasil ################# " + result.getFieldError());
        final StringBuilder sburl = new StringBuilder("redirect:/biaya/editsppbiaya/");
        if (result.hasErrors()) {
            return "spp/biaya/addsppbiaya";
        } else {
            log.debug("---> " + request.getParameter("namaPenerima"));
            log.debug("---> " + request.getParameter("namaYayasan"));
            log.debug("---> " + request.getParameter("alamatBantuan"));
            final String namatujuan = request.getParameter("dkinama");
            
            sppBiaya.setNamaTujuan(namatujuan);
            sppBiaya.setTglSpp(new Date(System.currentTimeMillis()));
            sppBiaya.setBulan(SipkdHelpers.splitString(sppBiaya.getBulan(), "/", 0));
            sppBiaya.setKodeUangMuka("0");
            sppBiaya.setNamaPenerima(request.getParameter("namaPenerima"));
            sppBiaya.setNamaYayasan(request.getParameter("namaYayasan"));
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sppBiaya.setIdEntry(pengguna.getIdPengguna());
            sppBiaya.setTglEntry(new Timestamp(System.currentTimeMillis()));
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final List<SppBiayaRinci> listSppBiayaRinci = new ArrayList<SppBiayaRinci>(banyakrinci);
            //log.debug(" Map param  "+  request.getParameterMap().toString());
            for (int i = 0; i < banyakrinci; i++) {

                final String penanda = "cekpilih" + (i + 1);
                final String idBiaya = request.getParameter(penanda);
                //    log.debug(penanda+" idBl ################# " + idBl);
                if (idBiaya != null && !idBiaya.isEmpty()) {

                    final String noSpd = request.getParameter("noSpd" + idBiaya);
                    final SppBiayaRinci sppBiayaRinci = new SppBiayaRinci();
                    log.debug(" noSpd ################# " + noSpd);
                    final Akun akun = new Akun();
                    akun.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idakun" + idBiaya)));
                    sppBiayaRinci.setAkun(akun);

                    sppBiayaRinci.setIdBiaya(SipkdHelpers.getIntFromString(idBiaya));
                    sppBiayaRinci.setNoSpd(noSpd);
                    sppBiayaRinci.setIdSpd(SipkdHelpers.getIntFromString(request.getParameter("idspd" + idBiaya)));
                    sppBiayaRinci.setNilaiSpp(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaispp" + idBiaya)));
                    sppBiayaRinci.setIdEntry(pengguna.getIdPengguna());
                    sppBiayaRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));

                    listSppBiayaRinci.add(sppBiayaRinci);
                }
            }
            sppBiaya.setSppBiayaRinci(listSppBiayaRinci);
            sppBiayaServices.updateSppBiaya(sppBiaya);
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil diupdate ")
                    .toString());

            return sburl.append(sppBiaya.getId()).toString();
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
