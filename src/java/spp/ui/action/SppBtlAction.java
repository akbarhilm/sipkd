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
import spp.model.SppBtl;
import spp.model.SppBtlRinci;
import spp.services.ReferensiServices;
import spp.services.SpdBLServices;
import spp.services.SppBtlServices;
import spp.util.BigDecimalPropertyEditor;
import spp.util.SipkdHelpers;
import spp.util.SqlDatePropertyEditor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/btl")
public class SppBtlAction {

    private static final Logger log = LoggerFactory.getLogger(SppBtlAction.class);

    @Autowired
    SppBtlServices sppBtlServices;

    @Autowired
    ReferensiServices referensiServices;

    @Autowired
    SpdBLServices spdBLServices;

    String bulanlisting = new String();

    @RequestMapping(value = "/indexbtl", method = RequestMethod.GET)
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
        return "spp/btl/indexbtl";

    }

    @RequestMapping(value = "/indexbtl/{idskpd}", method = RequestMethod.GET)
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
        return "spp/btl/indexbtl";
    }

    @RequestMapping(value = "/json/getlistsppbtl", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistsppbtl(final HttpServletRequest request) {
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
        final int banyak = sppBtlServices.getBanyakSppBtl(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppBtlServices.getAllSppBtl(param));
        return mapData;
    }

    @RequestMapping(value = "/addsppbtl", method = RequestMethod.GET)
    public ModelAndView add(final SppBtl sppBtl, final HttpServletRequest request) {
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
            final Map<String, String> dataBank = sppBtlServices.getBankRekByIdSkpd(paramBank);
            log.debug(" >>>>>>>>>>>>>  " + dataBank.toString());
            sppBtl.setKodeBank(dataBank.get("C_BANK"));
            sppBtl.setNamaBank(dataBank.get("N_BANK"));
            sppBtl.setNomorRekBank(dataBank.get("I_REK_BANKSPM"));
            request.setAttribute("isall", 0);

        }
        return new ModelAndView("spp/btl/addsppbtl", "refsppbtl", sppBtl);

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
        final long banyak = sppBtlServices.getBanyakSpdBtl(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppBtlServices.getAllSpdBtl(param));
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

    @RequestMapping(value = "/json/getlistspdbtlbanyak ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getlistspdbtlbanyak(final HttpServletRequest request) {
        final String TahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", TahunAnggaran);
        param.put("idskpd", idskpd);
        return sppBtlServices.getBanyakSpdBtl(param);
    }

    @RequestMapping(value = "/addsppbtl/{idskpd}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable Integer idskpd, final SppBtl sppBtl, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);

        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final Map<String, Object> paramBank = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            paramBank.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBank.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            final Map<String, String> dataBank = sppBtlServices.getBankRekByIdSkpd(paramBank);
            final Map<String, String> dataBankDki = sppBtlServices.getBankDki(paramBank); // data bank nya diganti jadi BANK DKI
            //log.debug(" >>>>>>>>>>>>>  " + dataBank.toString());

            if (dataBankDki != null) {
                sppBtl.setKodeBank(dataBankDki.get("C_BANK"));
                sppBtl.setKodeBankTransfer(dataBankDki.get("C_BANK_TRANSFER"));
                sppBtl.setNamaBankTransfer(dataBankDki.get("N_BANK_TRANSFER"));
                sppBtl.setIdBank(dataBankDki.get("I_ID").toString());
            }

            if (dataBank != null) {
                //sppBtl.setKodeBank(dataBank.get("C_BANK"));
                //sppBtl.setNamaBank(dataBank.get("N_BANK"));
                sppBtl.setNomorRekBank(dataBank.get("I_REK_BANKSPM"));
            }
            request.setAttribute("isall", 0);

            final Map<String, Object> paramBendahara = new HashMap<String, Object>();
            paramBendahara.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBendahara.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            final Map<String, String> dataBendahara = sppBtlServices.getBendaharaByIdSkpd(paramBendahara);

            if (dataBendahara != null) {
                sppBtl.setNipPptk(dataBendahara.get("I_NIP_PKBLJ"));
                sppBtl.setNamaPptk(dataBendahara.get("N_PKBLJ"));
            }

        }

        sppBtl.setSkpd(referensiServices.getDetailSkpdById(idskpd));
        return new ModelAndView("spp/btl/addsppbtl", "refsppbtl", sppBtl);

    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpanspdbtl(@Valid @ModelAttribute("refsppbtl") SppBtl sppBtl,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        sppBtl.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        bulanlisting = sppBtl.getBulanListing();
        //log.debug(" hasil ################# " + result.getFieldError());
        final StringBuilder sburl = new StringBuilder("redirect:/btl/editsppbtl/");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        /* if (result.hasErrors()) {
         return "spp/btl/addsppbtl";
         } else { */
        // ditambah karena nama penerima dan yayasan di disable untuk <> 761
        String namatujuan = request.getParameter("dkinama");
        /* String kodebank = request.getParameter("kodeBankTransfer");
        
         if ("111".equals(kodebank)){
         namatujuan = request.getParameter("dkinama");
         } else {
            
         }*/

        if (listSkpd.get(0).getIdSkpd() == 761 || listSkpd.get(0).getIdSkpd() == 1234) {
            final String aa = request.getParameter("namaPenerima");
            final String ss = request.getParameter("namaYayasan");

            if ("".equals(namatujuan) || namatujuan == null) {
                namatujuan = sppBtl.getNamaPenerima();
            }

            sppBtl.setNamaPenerima(request.getParameter("namaPenerima"));
            sppBtl.setNamaYayasan(request.getParameter("namaYayasan"));

        } else {
            sppBtl.setNamaPenerima("");
            sppBtl.setNamaYayasan("");
        }

        sppBtl.setNamaTujuan(namatujuan);
        sppBtl.setTglSpp(new Date(System.currentTimeMillis()));
        sppBtl.setBulan(SipkdHelpers.splitString(sppBtl.getBulan(), "/", 0));
        sppBtl.setKodeUangMuka("0");
        //sppBtl.setNamaTujuan(namatujuan); --> untuk sementara langsung di xml

        sppBtl.setIdEntry(pengguna.getIdPengguna());
        sppBtl.setTglEntry(new Timestamp(System.currentTimeMillis()));
        final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
        final List<SppBtlRinci> listSppBtlRinci = new ArrayList<SppBtlRinci>(banyakrinci);
        //log.debug(" Map param  "+  request.getParameterMap().toString());
        // final SppBtlRinci sppBtlRinci = new SppBtlRinci();
        for (int i = 0; i < banyakrinci; i++) {

            final String penanda = "cekpilih" + (i + 1);
            final String nourutidx = request.getParameter(penanda);
            //    log.debug(penanda+" idBl ################# " + idBl);

            if (nourutidx != null && !nourutidx.isEmpty()) {
                final String idBtl = request.getParameter("idbtl" + nourutidx);
                final String noSpd = request.getParameter("noSpd" + nourutidx);
                final SppBtlRinci sppBtlRinci = new SppBtlRinci();
                log.debug(" noSpd ################# " + noSpd);
                final Akun akun = new Akun();
                akun.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idakun" + nourutidx)));

                sppBtlRinci.setAkun(akun);
                log.debug("Akun nya ==>  " + sppBtlRinci.getAkun().getIdAkun());
                sppBtlRinci.setIdBtl(SipkdHelpers.getIntFromString(idBtl));
                sppBtlRinci.setNoSpd(noSpd);
                sppBtlRinci.setIdSpd(SipkdHelpers.getIntFromString(request.getParameter("idspd" + nourutidx)));
                sppBtlRinci.setNilaiSpp(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaispp" + nourutidx)));
                sppBtlRinci.setIdEntry(pengguna.getIdPengguna());
                sppBtlRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));

                listSppBtlRinci.add(sppBtlRinci);

            }
        }

        sppBtl.setSppBtlRinci(listSppBtlRinci);
        sppBtlServices.insertSppBtl(sppBtl);

        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                .append(" berhasil ditambahkan ")
                .toString());

        return sburl.append(sppBtl.getId()).toString();
        //}
    }

    @RequestMapping(value = "/deletesppbtl/{idspp}", method = RequestMethod.GET)
    public ModelAndView hapus(@PathVariable Integer idspp, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }
        final SppBtl sppBtl = sppBtlServices.getSppBtlById(idspp);
        return new ModelAndView("spp/btl/deletesppbtl", "refsppbtl", sppBtl);
    }

    @RequestMapping(value = "/prosesdeletebtl", method = RequestMethod.POST)
    public String prosesdeletebtl(@Valid @ModelAttribute("refsppbtl") SppBtl sppBtl) {
        log.debug("******************* MASUK PROSES DELETE BTL NORMAL");
        sppBtlServices.deleteSppBtlMaster(sppBtl.getId());
        return "redirect:/btl/indexbtl/";
    }

    @RequestMapping(value = "/editsppbtl/{idspp}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idspp, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        request.setAttribute("nospdfilter", request.getParameter("nospdfilter"));
        SppBtl sppBtl = sppBtlServices.getSppBtlById(idspp);
        sppBtl.setBulanListing(bulanlisting);
        return new ModelAndView("spp/btl/editsppbtl", "refsppbtl", sppBtl);
    }

    @RequestMapping(value = "/prosesupdate", method = RequestMethod.POST)
    public String prosesupdatesppup(@Valid @ModelAttribute("refsppbtl") SppBtl sppBtl,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        sppBtl.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        //log.debug(" hasil ################# " + result.getFieldError());
        final StringBuilder sburl = new StringBuilder("redirect:/btl/editsppbtl/");
        if (result.hasErrors()) {
            return "spp/btl/addsppbtl";
        } else {
            
            String namatujuan = request.getParameter("dkinama");

            if (listSkpd.get(0).getIdSkpd() == 761 || listSkpd.get(0).getIdSkpd() == 1234) {
                final String aa = request.getParameter("namaPenerima");
                final String ss = request.getParameter("namaYayasan");

                if ("".equals(namatujuan) || namatujuan == null) {
                    namatujuan = sppBtl.getNamaPenerima();
                }

                sppBtl.setNamaPenerima(request.getParameter("namaPenerima"));
                sppBtl.setNamaYayasan(request.getParameter("namaYayasan"));

            } else {
                sppBtl.setNamaPenerima("");
                sppBtl.setNamaYayasan("");
            }

            sppBtl.setNamaTujuan(namatujuan);
            sppBtl.setTglSpp(new Date(System.currentTimeMillis()));
            sppBtl.setBulan(SipkdHelpers.splitString(sppBtl.getBulan(), "/", 0));
            sppBtl.setKodeUangMuka("0");
            sppBtl.setNamaPenerima(request.getParameter("namaPenerima"));
            sppBtl.setNamaYayasan(request.getParameter("namaYayasan"));
            sppBtl.setAlamatBantuan(request.getParameter("alamatBantuan"));

            sppBtl.setIdEntry(pengguna.getIdPengguna());
            sppBtl.setTglEntry(new Timestamp(System.currentTimeMillis()));
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final List<SppBtlRinci> listSppBtlRinci = new ArrayList<SppBtlRinci>(banyakrinci);
            //log.debug(" Map param  "+  request.getParameterMap().toString());
            for (int i = 0; i < banyakrinci; i++) {

                final String penanda = "cekpilih" + (i + 1);
                final String nourutidx = request.getParameter(penanda);
                //    log.debug(penanda+" idBl ################# " + idBl);
                if (nourutidx != null && !nourutidx.isEmpty()) {
                    final String idBtl = request.getParameter("idbtl" + nourutidx);
                    final String noSpd = request.getParameter("noSpd" + nourutidx);
                    final String nilaispp = request.getParameter("nilaispp" + nourutidx);
                    final SppBtlRinci sppBtlRinci = new SppBtlRinci();
                    log.debug(" nilaispp ################# " + nilaispp);
                    final Akun akun = new Akun();
                    akun.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idakun" + nourutidx)));
                    log.debug("ID Akun Update ==> " + akun.getIdAkun());
                    sppBtlRinci.setAkun(akun);

                    sppBtlRinci.setIdBtl(SipkdHelpers.getIntFromString(idBtl));
                    sppBtlRinci.setNoSpd(noSpd);
                    sppBtlRinci.setIdSpd(SipkdHelpers.getIntFromString(request.getParameter("idspd" + nourutidx)));
                    sppBtlRinci.setNilaiSpp(SipkdHelpers.getBigDecimalFromString(nilaispp));
                    sppBtlRinci.setIdEntry(pengguna.getIdPengguna());
                    sppBtlRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));

                    listSppBtlRinci.add(sppBtlRinci);

                    int is = listSkpd.get(0).getIdSkpd();
                    int ia = sppBtlRinci.getAkun().getIdAkun();
                    log.debug("ID SKPD nya ===> " + is);
                    if (is == 761 || is == 1234) {
                        if (ia == 2277) {
                            sppBtlServices.updateSppBtt(sppBtl);
                        } else {
                            sppBtlServices.updateSppBxx(sppBtl);
                        }

                    }

                }
            }
            sppBtl.setSppBtlRinci(listSppBtlRinci);
            sppBtlServices.updateSppBtl(sppBtl);
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil diupdate ")
                    .toString());

            return sburl.append(sppBtl.getId()).toString();
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
