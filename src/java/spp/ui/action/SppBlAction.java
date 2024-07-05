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
import spp.model.SppBl;
import spp.model.SppBlRinci;
import spp.services.BastServices;
import spp.services.ReferensiServices;
import spp.services.SpdBLServices;
import spp.services.SppBlServices;
import spp.util.BigDecimalPropertyEditor;
import spp.util.SipkdHelpers;
import spp.util.SqlDatePropertyEditor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/bl")
public class SppBlAction {

    private static final Logger log = LoggerFactory.getLogger(SppBlAction.class);

    @Autowired
    SppBlServices sppBlServices;

    @Autowired
    BastServices bastServices;

    @Autowired
    ReferensiServices referensiServices;

    @Autowired
    SpdBLServices spdBLServices;
    private List<SppBlRinci> listSppBlRinci;
    private String kodekegiatan;

    @RequestMapping(value = "/indexsppbl", method = RequestMethod.GET)
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
        return "spp/bl/indexsppbl";

    }

    @RequestMapping(value = "/indexsppbl/{idskpd}", method = RequestMethod.GET)
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
        return "spp/bl/indexsppbl";
    }

    @RequestMapping(value = "/json/getlistsppbl", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistsppbl(final HttpServletRequest request) {
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
        final int banyak = sppBlServices.getBanyakSppBl(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppBlServices.getAllSppBl(param));
        return mapData;
    }

    @RequestMapping(value = "/addsppbl", method = RequestMethod.GET)
    public ModelAndView add(final SppBl sppBl, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }
        return new ModelAndView("spp/bl/addsppbl", "refsppbl", sppBl);

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
        final String idkegiatan = request.getParameter("idkegiatan");
        final String idakun = request.getParameter("idakun");
        param.put("kodekegiatan", StringUtils.trimAllWhitespace(kodekegiatan));
        param.put("spdno", StringUtils.trimAllWhitespace(spdno));
        final String nomorBast = request.getParameter("nomorBast");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);
        param.put("idakun", idakun);
        param.put("idspp", SipkdHelpers.getIntFromString(request.getParameter("idspp")));
        param.put("nomorBast", SipkdHelpers.getIntFromString(nomorBast));
        log.debug(param.toString());
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        log.debug(param.toString());
        final long banyak = sppBlServices.getBanyakSpdBl(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppBlServices.getAllSpdBl(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getstatusuangmuka", method = RequestMethod.GET)
    public @ResponseBody
    Integer getStatusUangMuka(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idkegiatan");
        final Integer noBast = SipkdHelpers.getIntFromString(request.getParameter("nomorBast"));
        final Map< String, Object> param = new HashMap<String, Object>(4);
        param.put("nobast", noBast);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);
        param.put("tahun", tahunAnggaran);

        return bastServices.getStatusUangMuka(param);
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

    @RequestMapping(value = "/json/getlistspdblbanyak ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getlistspdblbanyak(final HttpServletRequest request) {
        final String TahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idspp = request.getParameter("idspp");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String idakun = request.getParameter("idakun");
        final String nomorBast = request.getParameter("nomorBast");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", TahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idspp", idspp);
        param.put("idkegiatan", idkegiatan);
        param.put("idakun", idakun);
        param.put("nomorBast", SipkdHelpers.getIntFromString(nomorBast));
        return sppBlServices.getBanyakSpdBl(param);
    }

    @RequestMapping(value = "/addsppbl/{idskpd}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable Integer idskpd, final SppBl sppBl, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        sppBl.setSkpd(referensiServices.getDetailSkpdById(idskpd));
        return new ModelAndView("spp/bl/addsppbl", "refsppbl", sppBl);

    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpanSppBl(@Valid @ModelAttribute("refsppbl") SppBl sppBl,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        sppBl.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

        final StringBuilder sburl = new StringBuilder("redirect:/bl/editsppbl/");
        /*if (result.hasErrors()) {
         return "spp/bl/addsppbl";
         } else */
        {

            sppBl.setTglSpp(new Date(System.currentTimeMillis()));
            sppBl.setBulan(SipkdHelpers.splitString(sppBl.getBulan(), "/", 0));
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sppBl.setIdEntry(pengguna.getIdPengguna());
            sppBl.setTglEntry(new Timestamp(System.currentTimeMillis()));
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final List<SppBlRinci> listSppBlRinci = new ArrayList<>(banyakrinci);
            final Integer idSpp = sppBl.getId();
            for (int i = 0; i < banyakrinci; i++) {
                final Integer c = i + 1;
                final String penanda = "cekpilih" + (i + 1);
                final String idAkun = request.getParameter(penanda);
                final String idbas = request.getParameter("idakun" + c);
                //    log.debug(penanda+" idAkun ################# " + idAkun);
                //if (idAkun != null && !idAkun.isEmpty()) {

                final String noSpd = request.getParameter("noSpd" + c);
                final SppBlRinci sppBlRinci = new SppBlRinci();

                final Akun akun = new Akun();
                akun.setIdAkun(SipkdHelpers.getIntFromString(idbas));
                log.debug(" noSpd ################# " + noSpd + "    akun  " + akun.getIdAkun());
                sppBlRinci.setAkun(akun);
                final Kegiatan kegiatan = new Kegiatan();
                kegiatan.setIdKegiatan(SipkdHelpers.getIntFromString(request.getParameter("idkegiatan" + c)));
                sppBlRinci.setKegiatan(kegiatan);
                sppBlRinci.setIdBl(SipkdHelpers.getIntFromString(request.getParameter("idblhidden" + c)));
                sppBlRinci.setNoSpd(noSpd);
                sppBlRinci.setIdspp(idSpp);
                sppBlRinci.setIdSpd(SipkdHelpers.getIntFromString(request.getParameter("idspd" + c)));
                sppBlRinci.setNilaiSpp(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaispp" + c)));
                sppBlRinci.setIdEntry(pengguna.getIdPengguna());
                sppBlRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));

                listSppBlRinci.add(sppBlRinci);
                //}
            }

            sppBl.setSppBlRinci(listSppBlRinci);
            sppBlServices.insertSppBl(sppBl);
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil disimpan ")
                    .toString());

            return sburl.append(sppBl.getId())
                    .toString();

        }
    }

    @RequestMapping(value = "/deletesppbl/{idspp}", method = RequestMethod.GET)
    public ModelAndView hapus(@PathVariable Integer idspp, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }
        final SppBl sppBl = sppBlServices.getSppBlByIdExceptIdBast(idspp);//sppBlServices.getSppBlById(idspp);
        log.debug("id spp ============ " + sppBl.getId());
        return new ModelAndView("spp/bl/deletesppbl", "refsppbl", sppBl);
    }

    @RequestMapping(value = "/prosesdelete", method = RequestMethod.POST)
    public String deletesppbl(@Valid @ModelAttribute("refsppbl") SppBl sppBl) {
        sppBlServices.deleteSppBlMaster(sppBl.getId());
        return "redirect:/bl/indexsppbl/";
    }

    @RequestMapping(value = "/editsppbl/{idspp}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idspp, final HttpServletRequest request, SppBl sppBl) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);

        }

        request.setAttribute("nospdfilter", request.getParameter("nospdfilter"));
        Boolean x = sppBl.getKodeUangMuka();
        sppBl = sppBlServices.getSppBlById(idspp);
        if (x = true) {
            sppBl = sppBlServices.getSppBlByIdExceptIdBast(idspp);
        } else {
            sppBl = sppBlServices.getSppBlById(idspp);
        }
        //final SppBl sppBl = sppBlServices.getSppBlById(idspp);
        return new ModelAndView("spp/bl/editsppbl", "refsppbl", sppBl);
    }

    @RequestMapping(value = "/prosesupdate", method = RequestMethod.POST)
    public String prosesupdateSppBl(@Valid @ModelAttribute("refsppbl") SppBl sppBl,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        sppBl.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

        final StringBuilder sburl = new StringBuilder("redirect:/bl/editsppbl/");
        /*  if (result.hasErrors()) {
         return "spp/bl/addsppbl";
         } else */
        {
            sppBl.setTglSpp(new Date(System.currentTimeMillis()));
            sppBl.setBulan(SipkdHelpers.splitString(sppBl.getBulan(), "/", 0));
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sppBl.setIdEntry(pengguna.getIdPengguna());
            sppBl.setTglEntry(new Timestamp(System.currentTimeMillis()));
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final List<SppBlRinci> listSppBlRinci = new ArrayList<SppBlRinci>(banyakrinci);
            final Integer idSpp = sppBl.getId();
            for (int i = 0; i < banyakrinci; i++) {

                final String penanda = "cekpilih" + (i + 1);
                final String idAkun = request.getParameter(penanda);
                //    log.debug(penanda+" idAkun ################# " + idAkun);
                if (idAkun != null && !idAkun.isEmpty()) {

                    final String noSpd = request.getParameter("noSpd" + idAkun);
                    final SppBlRinci sppBlRinci = new SppBlRinci();

                    final Akun akun = new Akun();
                    akun.setIdAkun(SipkdHelpers.getIntFromString(idAkun));
                    log.debug(" noSpd ################# " + noSpd + "    akun  " + akun.getIdAkun());
                    sppBlRinci.setAkun(akun);
                    final Kegiatan kegiatan = new Kegiatan();
                    kegiatan.setIdKegiatan(SipkdHelpers.getIntFromString(request.getParameter("idkegiatan" + idAkun)));
                    sppBlRinci.setKegiatan(kegiatan);
                    sppBlRinci.setIdBl(SipkdHelpers.getIntFromString(request.getParameter("idblhidden" + idAkun)));
                    sppBlRinci.setNoSpd(noSpd);
                    sppBlRinci.setIdspp(idSpp);
                    sppBlRinci.setIdSpd(SipkdHelpers.getIntFromString(request.getParameter("idspd" + idAkun)));
                    sppBlRinci.setNilaiSpp(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaispp" + idAkun)));
                    sppBlRinci.setIdEntry(pengguna.getIdPengguna());
                    sppBlRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));

                    listSppBlRinci.add(sppBlRinci);
                }

            }
            sppBl.setSppBlRinci(listSppBlRinci);
            sppBlServices.updateSppBl(sppBl);
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil diupdate ")
                    .toString());

            return sburl.append(sppBl.getId())
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
        return sppBlServices.getTotalSPDDanSPP(param);
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

    @RequestMapping(value = "/listbastpopup", method = RequestMethod.GET)
    public ModelAndView listbast(final HttpServletResponse response, final HttpServletRequest request) {
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
        return new ModelAndView("/spp/bl/listbastpopup");
    }

    @RequestMapping(value = "/listbastpopup2", method = RequestMethod.GET)
    public ModelAndView listbast2(final HttpServletResponse response, final HttpServletRequest request) {
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
        return new ModelAndView("/spp/bl/listbastpopup2");
    }

    @RequestMapping(value = "/json/listbastpopup", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpopupbast(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("namaskpd");
        final String kodeskpd = request.getParameter("kodeskpd");
        final String idskpd = request.getParameter("idskpd");
        final String nomorBast = request.getParameter("nomorBast");
        param.put("idskpd", idskpd);
        param.put("tahun", tahunAnggaran);
        param.put("namaskpd", skpd);
        param.put("kodeskpd", kodeskpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("nomorBast", SipkdHelpers.getIntFromString(nomorBast));
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = sppBlServices.getCountBast(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppBlServices.getBast(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listbastpopup2", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpopupbast2(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("namaskpd");
        final String kodeskpd = request.getParameter("kodeskpd");
        final String idskpd = request.getParameter("idskpd");
        param.put("idskpd", idskpd);
        param.put("tahun", tahunAnggaran);
        param.put("namaskpd", skpd);
        param.put("kodeskpd", kodeskpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = sppBlServices.getCountBast2(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppBlServices.getBast2(param));
        return mapData;
    }

}
