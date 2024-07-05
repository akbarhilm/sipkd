package spp.ui.action;

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
import spp.model.Akun;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.SppBantuan;
import spp.model.SppBantuanRinci;
import spp.services.ReferensiServices;
import spp.services.SppBantuanServices;
import spp.util.BigDecimalPropertyEditor;
import spp.util.SipkdHelpers;
import spp.util.SqlDatePropertyEditor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/sppbantuan")
public class SppBantuanAction {

    private static final Logger log = LoggerFactory.getLogger(SppBantuanAction.class);

    @Autowired
    SppBantuanServices sppBantuanServices;

    @Autowired
    ReferensiServices referensiServices;

    @RequestMapping(value = "/indexsppbantuan", method = RequestMethod.GET)
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
        return "spp/bantuan/indexsppbantuan";

    }

    @RequestMapping(value = "/indexsppbantuan/{idskpd}", method = RequestMethod.GET)
    public String index(@PathVariable Integer idskpd, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            //request.setAttribute("level", listSkpd.get(0).getLevelSkpd());

        }
        request.setAttribute("skpd", referensiServices.getDetailSkpdById(idskpd));
        return "spp/bantuan/indexsppbantuan";
    }

    @RequestMapping(value = "/json/getlistsppbantuan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistsppBantuan(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String idskpdkoor = request.getParameter("idskpdkoor");
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
        //param.put("namaskpd", skpd);
        // param.put("idskpd", idskpd);
        param.put("idskpdkoor", idskpdkoor);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = sppBantuanServices.getBanyakSppBantuan(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppBantuanServices.getAllSppBantuan(param));
        return mapData;
    }

    @RequestMapping(value = "/addsppbantuan", method = RequestMethod.GET)
    public ModelAndView add(final SppBantuan sppBantuan, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }
        return new ModelAndView("spp/bantuan/addsppbantuan", "refsppbantuan", sppBantuan);

    }

    @RequestMapping(value = "/json/listjsonskpdkoorpopup", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistskpdkoor(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        log.debug(param.toString());
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        log.debug(param.toString());
        final long banyak = sppBantuanServices.getBanyakSkpdKoorSah(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppBantuanServices.getSkpdKoorSah(param));
        return mapData;

    }

    @RequestMapping(value = "/listskpdkoorpopup", method = RequestMethod.GET)
    public ModelAndView listskpdkoorpopup(final HttpServletResponse response, final HttpServletRequest request) {
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
        return new ModelAndView("/spp/bantuan/listskpdkoorpopup");
    }

    @RequestMapping(value = "/json/getlistsppbantuanbanyak ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getlistspdblbanyak(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        return sppBantuanServices.getBanyakSppBantuan(param);
    }

    @RequestMapping(value = "/addsppbantuan/{idskpdkoor}", method = RequestMethod.GET)
    public ModelAndView add(
            @PathVariable Integer idskpdkoor,
            final SppBantuan sppBantuan,
            final HttpServletRequest request) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {

            final Map<String, Object> paramBank = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            paramBank.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBank.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            final Map<String, String> dataBank = sppBantuanServices.getBankRekByIdSkpd(paramBank);
            // GA USAH PAKE DEFAULT.. DIKOSONGIN AJA.. DIPILIH SENDIRI
            //sppBantuan.setKodeBank(dataBank.get("C_BANK"));
            //sppBantuan.setNamaBank(dataBank.get("N_BANK"));
            //sppBantuan.setRekeningBank(dataBank.get("I_REK_BANKSPM"));

            final Map<String, Object> paramBendahara = new HashMap<String, Object>();
            paramBendahara.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBendahara.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            try {
                final Map<String, String> dataBendahara = sppBantuanServices.getBendaharaByIdSkpd(paramBendahara);

                sppBantuan.setNipPptk(dataBendahara.get("I_NIP_PKBANTUAN"));
                sppBantuan.setNamaPptk(dataBendahara.get("N_PKBANTUAN"));

                sppBantuan.setNamaBendahara(dataBendahara.get("N_PKBANTUAN"));
                sppBantuan.setNipBendahara(dataBendahara.get("I_NIP_PKBANTUAN"));
            } catch (Exception e) {
            }
            final Map< String, Object> param = new HashMap<String, Object>();

            String namaSkpdKoor = sppBantuanServices.getNamaSkpdKoordinatorById(idskpdkoor);
            request.setAttribute("namaSkpdKoor", namaSkpdKoor);
            request.setAttribute("isall", 0);
            request.setAttribute("idskpdd", listSkpd.get(0).getIdSkpd());

        }
        sppBantuan.setSkpd(referensiServices.getDetailSkpdById(idskpdkoor));

        return new ModelAndView("spp/bantuan/addsppbantuan", "refsppbantuan", sppBantuan);

    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpanspdbl(@Valid @ModelAttribute("refsppbantuan") SppBantuan sppBantuan,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        sppBantuan.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        //log.debug(" hasil ################# " + result.getFieldError());
        final StringBuilder sburl = new StringBuilder("redirect:/sppbantuan/edisppbantuan/");
        if (result.hasErrors()) {
            return "spp/bantuan/addsppbantuan";
        } else {

            //final String namatujuan = request.getParameter("dkinama");
            String namatujuan;
            
            if ("".equals(request.getParameter("dkinama"))){
                namatujuan = request.getParameter("namaPenerima");
            } else {
                namatujuan = request.getParameter("dkinama");
            }

            sppBantuan.setNamaTujuan(namatujuan);
            sppBantuan.setTglSpp(new Date(System.currentTimeMillis()));
            sppBantuan.setBulan(SipkdHelpers.splitString(sppBantuan.getBulan(), "/", 0));
            sppBantuan.setKodeUangMuka("0");
            sppBantuan.setNamaPenerima(request.getParameter("namaPenerima"));
            sppBantuan.setNamaYayasan(request.getParameter("namaYayasan"));
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sppBantuan.setIdEntry(pengguna.getIdPengguna());
            sppBantuan.setTglEntry(new Timestamp(System.currentTimeMillis()));

            final SppBantuanRinci sppBantuanRinci = new SppBantuanRinci();
            final String idBtlBantuan = request.getParameter("idBtlBantuan");
            //    log.debug(penanda+" idBl ################# " + idBl);
            if (idBtlBantuan != null && !idBtlBantuan.isEmpty()) {

                final String noSpd = request.getParameter("noSpd" + idBtlBantuan);
                final Akun akun = new Akun();
                akun.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("akun.idAkun" + idBtlBantuan)));
                sppBantuanRinci.setAkun(akun);
                sppBantuanRinci.setIdBtlBantuan(SipkdHelpers.getIntFromString(idBtlBantuan));
                sppBantuanRinci.setNoSpd(noSpd);
                sppBantuanRinci.setIdskpdkoor(SipkdHelpers.getIntFromString(request.getParameter("idskpdkoor" + idBtlBantuan)));
                sppBantuanRinci.setIdSpd(SipkdHelpers.getIntFromString(request.getParameter("idSpd" + idBtlBantuan)));
                sppBantuanRinci.setNilaiSpp(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaiSpp" + idBtlBantuan)));
                sppBantuanRinci.setIdEntry(pengguna.getIdPengguna());
                sppBantuanRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));
                sppBantuanServices.insertSppBantuan(sppBantuanRinci, sppBantuan);
            }

        }

        /*try {
         sppBantuanServices.insertSppBantuanRinci(sppBantuanRinci, sppBantuan);
         } catch (Exception e) {
         e.printStackTrace();
         }*/
        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                .append(" berhasil ditambahkan ")
                .toString());

        //  sburl.append(sppBantuan.getIdBtlBantuan()).toString();
        return sburl.append(sppBantuan.getId() + "/" + sppBantuan.getIdBtlBantuan() + "/" + sppBantuan.getIdskpdkoor()).toString() + "/" + sppBantuan.getIdSpd().toString();

    }

    @RequestMapping(value = "/edisppbantuan/{idspp}/{idbtlbantuan}/{idskpdkoor}/{idspd}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idspp,
            @PathVariable Integer idbtlbantuan,
            @PathVariable Integer idspd,
            @PathVariable Integer idskpdkoor,
            final HttpServletRequest request,
            SppBantuan sppBantuan,
            SppBantuanRinci sppBantuanRinci) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {

            final Map<String, Object> paramBantuan = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

            paramBantuan.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            paramBantuan.put("idskpdkoor", idskpdkoor);
            paramBantuan.put("idspp", idspp);
            paramBantuan.put("idBtlBantuan", idbtlbantuan);
            paramBantuan.put("idSpd", idspd);

            final Map<String, Object> dataBantuan = sppBantuanServices.getDetailRinciBantuan(paramBantuan);

            request.setAttribute("namakegiatan", dataBantuan.get("KEGIATAN"));
            request.setAttribute("nospd", dataBantuan.get("NOSPD"));
            //request.setAttribute("nospd", 0);
            request.setAttribute("idakun", dataBantuan.get("I_IDBAS"));
            request.setAttribute("namaakun", dataBantuan.get("N_AKUN"));
            request.setAttribute("nilaiSpd", SipkdHelpers.formatBigDecimalPropertyEditor((BigDecimal) dataBantuan.get("SPD")));
            request.setAttribute("nilaiSppSisa", SipkdHelpers.formatBigDecimalPropertyEditor((BigDecimal) dataBantuan.get("SPP_SEBELUM")));
            request.setAttribute("nilaiSpp", SipkdHelpers.formatBigDecimalPropertyEditor((BigDecimal) dataBantuan.get("SPP")));
            request.setAttribute("isall", 0);
            String namaSkpdKoor = sppBantuanServices.getNamaSkpdKoordinatorById(idskpdkoor);
            request.setAttribute("namaSkpdKoor", namaSkpdKoor);
        }

        sppBantuan = sppBantuanServices.getSppBantuanById(idspp);

        return new ModelAndView("spp/bantuan/editsppbantuan", "refsppbantuan", sppBantuan);

    }

    @RequestMapping(value = "/json/listspdpopup", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listspdpopup(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String tahunAnggaran = request.getParameter("tahun");
        //final String idskpd = request.getParameter("idskpd");
        final String idskpdkoor = request.getParameter("idskpdkoor");
        //param.put("idskpd", idskpd);
        param.put("idskpdkoor", idskpdkoor);
        param.put("tahun", tahunAnggaran);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = sppBantuanServices.getBanyakSpdKegiatan(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppBantuanServices.getSpdKegiatan(param));
        return mapData;
    }

    @RequestMapping(value = "/listspdpopup/{idskpdkoor}", method = RequestMethod.GET)
    public ModelAndView listspdpopup(@PathVariable Integer idskpdkoor, final SppBantuan sppBantuan, final HttpServletResponse response, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        sppBantuan.setSkpd(referensiServices.getDetailSkpdById(idskpdkoor));
        request.setAttribute("idskpdkordinator", idskpdkoor);
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("spp/bantuan/listspdpopup", "refsppbantuan", sppBantuan);
    }

    @RequestMapping(value = "/prosesupdate", method = RequestMethod.POST)
    public String prosesubahspdbl(@Valid @ModelAttribute("refsppbantuan") SppBantuan sppBantuan,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        sppBantuan.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        //log.debug(" hasil ################# " + result.getFieldError());
        final StringBuilder sburl = new StringBuilder("redirect:/sppbantuan/edisppbantuan/");
        //if (result.hasErrors()) {
        //  return "spp/bantuan/addsppbantuan";
        // } else {
        //final String namatujuan = request.getParameter("dkinama");
        String namatujuan;
            
            if ("".equals(request.getParameter("dkinama"))){
                namatujuan = request.getParameter("namaPenerima");
            } else {
                namatujuan = request.getParameter("dkinama");
                
            }
        sppBantuan.setNamaTujuan(namatujuan);
        sppBantuan.setTglSpp(new Date(System.currentTimeMillis()));
        sppBantuan.setBulan(SipkdHelpers.splitString(sppBantuan.getBulan(), "/", 0));
        sppBantuan.setKodeUangMuka("0");
        sppBantuan.setNamaPenerima(request.getParameter("namaPenerima"));
        sppBantuan.setNamaYayasan(request.getParameter("namaYayasan"));
        sppBantuan.setAlamatBantuan(request.getParameter("alamatBantuan"));
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        sppBantuan.setIdEntry(pengguna.getIdPengguna());
        sppBantuan.setTglEntry(new Timestamp(System.currentTimeMillis()));

        final SppBantuanRinci sppBantuanRinci = new SppBantuanRinci();
        final String idBtlBantuan = request.getParameter("idBtlBantuan");
        //    log.debug(penanda+" idBl ################# " + idBl);
        if (idBtlBantuan != null && !idBtlBantuan.isEmpty()) {

            /* final String noSpd = request.getParameter("noSpd" + idBtlBantuan);
             log.debug(" noSpd ################# " + noSpd);
             final Akun akun = new Akun();
             akun.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("akun.idAkun" + idBtlBantuan)));
             sppBantuanRinci.setAkun(akun);
             sppBantuanRinci.setIdBtlBantuan(SipkdHelpers.getIntFromString(idBtlBantuan));
             sppBantuanRinci.setNoSpd(noSpd);
             sppBantuanRinci.setIdskpdkoor(SipkdHelpers.getIntFromString(request.getParameter("idskpdkoor" + idBtlBantuan)));
             sppBantuanRinci.setIdSpd(SipkdHelpers.getIntFromString(request.getParameter("idSpd" + idBtlBantuan)));*/
            sppBantuanRinci.setNilaiSpp(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaiSpp" + idBtlBantuan)));
            //sppBantuanRinci.setIdEntry(pengguna.getIdPengguna());
            //sppBantuanRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));

        }

        sppBantuanServices.updateSppBantuan(sppBantuan);
        sppBantuanServices.updateSppBantuanRinci(sppBantuanRinci, sppBantuan);
        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                .append(" Data berhasil diubah ")
                .toString());

        return sburl.append(sppBantuan.getId() + "/" + sppBantuan.getIdBtlBantuan() + "/" + sppBantuan.getIdskpdkoor()).toString() + "/" + sppBantuan.getIdSpd().toString();
    }

    @RequestMapping(value = "/delsppbantuan/{idspp}/{idskpdkoor}", method = RequestMethod.GET)
    public ModelAndView hapus(@PathVariable Integer idspp, @PathVariable Integer idskpdkoor, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {

            final Map<String, Object> paramBantuan = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

            paramBantuan.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            paramBantuan.put("idskpdkoor", idskpdkoor);
            request.setAttribute("isall", 0);
            String namaSkpdKoor = sppBantuanServices.getNamaSkpdKoordinatorById(idskpdkoor);
            request.setAttribute("namaSkpdKoor", namaSkpdKoor);
        }
        final SppBantuan sppBantuan = sppBantuanServices.getSppBantuanById(idspp);
        return new ModelAndView("spp/bantuan/delsppbantuan", "refsppbantuan", sppBantuan);
    }

    @RequestMapping(value = "/prosesdelete", method = RequestMethod.POST)
    public String delete(@Valid @ModelAttribute("refsppbantuan") SppBantuan sppBantuan, BindingResult result) {
        sppBantuanServices.deleteSppBantuanMaster(sppBantuan.getId());
        return "redirect:/sppbantuan/indexsppbantuan/";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
