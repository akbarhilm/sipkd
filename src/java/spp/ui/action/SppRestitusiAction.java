package spp.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
import spp.model.SppBtl;
import spp.model.SppRestitusiRinci;
import spp.services.ReferensiServices;
import spp.services.SpdBLServices;
import spp.services.SppRestitusiServices;
import spp.util.BigDecimalPropertyEditor;
import spp.util.SipkdHelpers;
import spp.util.SqlDatePropertyEditor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/restitusi")
public class SppRestitusiAction {

    private static final Logger log = LoggerFactory.getLogger(SppRestitusiAction.class);

    @Autowired
    SppRestitusiServices sppRestitusiServices;

    @Autowired
    ReferensiServices referensiServices;

    @Autowired
    SpdBLServices spdBLServices;

    @RequestMapping(value = "/indexres", method = RequestMethod.GET)
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
        return "spp/restitusi/indexres";

    }

    @RequestMapping(value = "/indexres/{idskpd}", method = RequestMethod.GET)
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
        return "spp/restitusi/indexres";
    }

    @RequestMapping(value = "/json/getlistspprestitusi", method = RequestMethod.GET)
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
        final int banyak = sppRestitusiServices.getBanyakSppRes(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppRestitusiServices.getAllSppRes(param));
        return mapData;
    }

    @RequestMapping(value = "/addspprestitusi", method = RequestMethod.GET)
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
            final Map<String, String> dataBank = sppRestitusiServices.getBankRekByIdSkpd(paramBank);
            sppBtl.setKodeBank(dataBank.get("C_BANK"));
            sppBtl.setNamaBank(dataBank.get("N_BANK"));
            sppBtl.setNomorRekBank(dataBank.get("I_REK_BANKSPM"));
            final Map<String, String> dataBendahara = sppRestitusiServices.getBendaharaByIdSkpd(paramBank);
            if (dataBendahara != null) {
                sppBtl.setNipPptk(dataBendahara.get("I_NIP_PKDPT"));
                sppBtl.setNamaPptk(dataBendahara.get("N_PKDPT"));
            }
            request.setAttribute("isall", 0);

        }
        return new ModelAndView("spp/restitusi/addspprestitusi", "refsppbtl", sppBtl);

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
        return sppRestitusiServices.getBanyakSpdBtl(param);
    }

    @RequestMapping(value = "/addspprestitusi/{idskpd}", method = RequestMethod.GET)
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
            final Map<String, String> dataBank = sppRestitusiServices.getBankRekByIdSkpd(paramBank);
            final Map<String, String> dataBankDki = sppRestitusiServices.getBankDki(paramBank); // data bank nya diganti jadi BANK DKI
            //log.debug(" >>>>>>>>>>>>>  " + dataBank.toString());

            if (dataBankDki != null) {
                sppBtl.setKodeBank(dataBankDki.get("C_BANK_TRANSFER"));
                sppBtl.setNamaBank(dataBankDki.get("N_BANK_TRANSFER"));
            }
            if (dataBank != null) {
                //sppBtl.setKodeBank(dataBank.get("C_BANK"));
                //sppBtl.setNamaBank(dataBank.get("N_BANK"));
                sppBtl.setNomorRekBank(dataBank.get("I_REK_BANKSTS"));
            }
            request.setAttribute("isall", 0);

            final Map<String, Object> paramBendahara = new HashMap<String, Object>();
            paramBendahara.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBendahara.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            final Map<String, String> dataBendahara = sppRestitusiServices.getBendaharaByIdSkpd(paramBendahara);

            if (dataBendahara != null) {
                sppBtl.setNipPptk(dataBendahara.get("I_NIP_PKDPT"));
                sppBtl.setNamaPptk(dataBendahara.get("N_PKDPT"));
            }

        }

        sppBtl.setSkpd(referensiServices.getDetailSkpdById(idskpd));
        return new ModelAndView("spp/restitusi/addspprestitusi", "refsppbtl", sppBtl);

    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpanspdbtl(@Valid @ModelAttribute("refsppbtl") SppBtl sppBtl,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        sppBtl.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

        final StringBuilder sburl = new StringBuilder("redirect:/restitusi/editspprestitusi/");
        if (result.hasErrors()) {
            return "spp/restitusi/addspprestitusi";
        } else {
            sppBtl.setTglSpp(new Date(System.currentTimeMillis()));
            sppBtl.setBulan(SipkdHelpers.splitString(sppBtl.getBulan(), "/", 0));
            sppBtl.setKodeUangMuka("0");
            sppBtl.setValidasi(sppBtl.getValidasi());
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sppBtl.setIdEntry(pengguna.getIdPengguna());
            sppBtl.setTglEntry(new Timestamp(System.currentTimeMillis()));
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final List<SppRestitusiRinci> listSppRestitusiRinci = new ArrayList<SppRestitusiRinci>(banyakrinci);
            
            for (int i = 0; i < banyakrinci; i++) {

                final String penanda = "cekpilih" + (i + 1);
                final String nourutidx = request.getParameter(penanda);
                if (nourutidx != null && !nourutidx.isEmpty()) {
                     
                    final SppRestitusiRinci sppRestitusiRinci = new SppRestitusiRinci();
                     
                    sppRestitusiRinci.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + nourutidx)));
                    sppRestitusiRinci.setIdPnrm(SipkdHelpers.getIntFromString(request.getParameter("idPnrm" + nourutidx)));
                    sppRestitusiRinci.setIdPnrmRinci(SipkdHelpers.getIntFromString(request.getParameter("idPnrmRinci" + nourutidx)));
                    sppRestitusiRinci.setNilaiSppSaatini(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaiSppSaatini" + nourutidx)));
                    sppRestitusiRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));
                    sppRestitusiRinci.setIdEntry(pengguna.getIdPengguna());

                    listSppRestitusiRinci.add(sppRestitusiRinci);
                }
            }
            sppBtl.setSppRestitusiRinci(listSppRestitusiRinci);
            sppRestitusiServices.insertSppRes(sppBtl);
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil ditambahkan ")
                    .toString());

            return sburl.append(sppBtl.getId()).toString();
        }
    }

    @RequestMapping(value = "/deletespprestitusi/{idspp}", method = RequestMethod.GET)
    public ModelAndView hapus(@PathVariable Integer idspp, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }
        final SppBtl sppBtl = sppRestitusiServices.getSppBtlById(idspp);
        return new ModelAndView("spp/restitusi/deletespprestitusi", "refsppbtl", sppBtl);
    }

    @RequestMapping(value = "/prosesdelete", method = RequestMethod.POST)
    public String deletesppbtl(@Valid @ModelAttribute("refsppbtl") SppBtl sppBtl) {
        sppRestitusiServices.deleteSppBtlMaster(sppBtl.getId());
        return "redirect:/restitusi/indexres/";
    }

    @RequestMapping(value = "/editspprestitusi/{idspp}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idspp, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        request.setAttribute("nospdfilter", request.getParameter("nospdfilter"));
        final SppBtl sppBtl = sppRestitusiServices.getSppBtlById(idspp);
        return new ModelAndView("spp/restitusi/editspprestitusi", "refsppbtl", sppBtl);
    }

    @RequestMapping(value = "/prosesupdate", method = RequestMethod.POST)
    public String prosesupdatesppup(@Valid @ModelAttribute("refsppbtl") SppBtl sppBtl,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        sppBtl.setTahun((String) request.getSession().getAttribute("tahunAnggaran")); 
        final StringBuilder sburl = new StringBuilder("redirect:/restitusi/editspprestitusi/");
        if (result.hasErrors()) {
            return "spp/restitusi/addspprestitusi";
        } else {
            sppBtl.setTglSpp(new Date(System.currentTimeMillis()));
            sppBtl.setBulan(SipkdHelpers.splitString(sppBtl.getBulan(), "/", 0));
            sppBtl.setKodeUangMuka("0");
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sppBtl.setIdEntry(pengguna.getIdPengguna());
            sppBtl.setTglEntry(new Timestamp(System.currentTimeMillis()));
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
              final List<SppRestitusiRinci> listSppRestitusiRinci = new ArrayList<SppRestitusiRinci>(banyakrinci);
           
            for (int i = 0; i < banyakrinci; i++) {

                final String penanda = "cekpilih" + (i + 1);
                final String nourutidx = request.getParameter(penanda);
                if (nourutidx != null && !nourutidx.isEmpty()) {
                     
                    final SppRestitusiRinci sppRestitusiRinci = new SppRestitusiRinci();
                     
                    sppRestitusiRinci.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + nourutidx)));
                    sppRestitusiRinci.setIdPnrm(SipkdHelpers.getIntFromString(request.getParameter("idPnrm" + nourutidx)));
                    sppRestitusiRinci.setIdPnrmRinci(SipkdHelpers.getIntFromString(request.getParameter("idPnrmRinci" + nourutidx)));
                    sppRestitusiRinci.setNilaiSppSaatini(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaiSppSaatini" + nourutidx)));
                    sppRestitusiRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));
                    sppRestitusiRinci.setIdEntry(pengguna.getIdPengguna());

                    listSppRestitusiRinci.add(sppRestitusiRinci);
                }
            }
            sppBtl.setSppRestitusiRinci(listSppRestitusiRinci) ;
            sppRestitusiServices.updateSppBtl(sppBtl);
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil diupdate ")
                    .toString());

            return sburl.append(sppBtl.getId()).toString();
        }
    }

    @RequestMapping(value = "/json/ceknovalidasi ", method = RequestMethod.GET)
    public @ResponseBody
    Map getNoValidasiByIdSkpd(final HttpServletRequest request) {
        final String noValidasi = request.getParameter("noValidasi");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        Map<String, Object> mapHasil = new LinkedHashMap<>(3);
        if (listSkpd != null && !listSkpd.isEmpty()) {
            Map mapnoVal = sppRestitusiServices.getNoValidasiByIdSkpd(noValidasi);

            Skpd skpd = listSkpd.get(0);
            if (mapnoVal != null && !mapnoVal.isEmpty() && skpd != null) {
                log.debug(mapnoVal.toString());
                if (skpd.getIdSkpd().equals(((BigDecimal) mapnoVal.get("I_IDSKPD")).intValue())) {
                    mapHasil.put("id", 1);
                    mapHasil.put("status", "NO VALIDASI VALID");
                    mapHasil.put("data", mapnoVal);
                } else {
                    mapHasil.put("id", 0);
                    mapHasil.put("status", "NO VALIDASI BUKAN SKPD ANDA");
                    mapHasil.put("data", null);
                }
            } else {

                mapHasil.put("id", 0);
                mapHasil.put("status", "NO VALIDASI TIDAK ADA ");
                mapHasil.put("data", null);
            }
        }

        return mapHasil;
    }

    @RequestMapping(value = "/json/getrinciakunbynomorvalidasi", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getRinciAkunByNomorValidasi(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Integer idskpd = SipkdHelpers.getIntFromString(request.getParameter("idskpd"));
        final String novalidasi = request.getParameter("novalidasi");
        final Integer idspp = SipkdHelpers.getIntFromString(request.getParameter("idspp"));

        final Map< String, Object> param = new HashMap<String, Object>();
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        param.put("idspp", idspp);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("novalidasi", novalidasi);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);

        log.debug(param.toString());
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        log.debug(param.toString());
        final long banyak = sppRestitusiServices.getBanyakAkunByNomorValidasi(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppRestitusiServices.getAkunByNomorValidasi(param));
        return mapData;

    }

    @RequestMapping(value = "/json/gettotalnilaiakunbynomorvalidasi", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, BigDecimal> getTotalNilaiAkunByNomorValidasi(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Integer idskpd = SipkdHelpers.getIntFromString(request.getParameter("idskpd"));
        final String novalidasi = request.getParameter("novalidasi");
        final Integer idspp = SipkdHelpers.getIntFromString(request.getParameter("idspp"));
        final Map< String, Object> param = new HashMap<String, Object>(4);
        param.put("idspp", idspp);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("novalidasi", novalidasi);
        return sppRestitusiServices.getTotalNilaiAkunByNomorValidasi(param);
    }

    @RequestMapping(value = "/json/getbanyakrinci", method = RequestMethod.GET)
    public @ResponseBody
    Integer getbanyakrinci(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Integer idskpd = SipkdHelpers.getIntFromString(request.getParameter("idskpd"));
        final String novalidasi = request.getParameter("novalidasi");
        final Integer idspp = SipkdHelpers.getIntFromString(request.getParameter("idspp"));
        final Map< String, Object> param = new HashMap<String, Object>(4);
        param.put("idspp", idspp);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("novalidasi", novalidasi);
        return sppRestitusiServices.getBanyakAkunByNomorValidasi(param);
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
