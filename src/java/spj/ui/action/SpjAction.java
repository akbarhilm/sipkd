package spj.ui.action;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spp.model.Akun;
import spp.model.Kegiatan;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.Spj;
import spp.model.SpjRinci;

import spp.model.SppUp;
import spj.services.ReferensiServices;
import spj.services.SpjServices;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SipkdHelpers;
import spj.util.SqlDatePropertyEditor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/spj")
public class SpjAction {

    private static final Logger log = LoggerFactory.getLogger(SpjAction.class);

    @Autowired
    SpjServices spjServices;

    @Autowired
    ReferensiServices referensiServices;

    @RequestMapping(value = "/indexspj", method = RequestMethod.GET)
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

        final Map< String, Object> param = new HashMap<String, Object>(2);
        Integer idskpd = listSkpd.get(0).getIdSkpd();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        final int banyakspj = spjServices.getBanyakSpjBelumSah(param);

        if (banyakspj >= 1) {
            request.setAttribute("banyakspjbelumsah", 1);
        }

        return "spj/indexspj";

    }

    @RequestMapping(value = "/json/getlistspj", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspjbl(final HttpServletRequest request) {
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
        //  param.put("namaskpd", skpd);
        param.put("idskpd", idskpd);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = spjServices.getCountSpj(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spjServices.getSpj(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getlistspjrincikegiatan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspjrincikegiatan(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        final String idspj = request.getParameter("idspj");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String beban = request.getParameter("beban");
        //final String kodeaktif = request.getParameter("kodeaktif");

        log.debug("BEBAN =========== " + beban);

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
        param.put("idskpd", idskpd);
        param.put("idspj", idspj);
        param.put("idkegiatan", idkegiatan);
        param.put("beban", beban);
        //param.put("kodeaktif", kodeaktif);

        final Map<String, Object> mapData = new HashMap<String, Object>(5);

        if ("TU".equals(beban)) {
            log.debug("MASUK BEBAN TU");

            final int banyak = spjServices.getCountSpjRinciKegiatanTU(param);
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("aaData", spjServices.getSpjRinciKegiatanTU(param));

        } else {
            log.debug("MASUK BEBAN UP/GU");

            final int banyak = spjServices.getCountSpjRinciKegiatan(param);
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("aaData", spjServices.getSpjRinciKegiatan(param));
        }

        return mapData;
    }

    @RequestMapping(value = "/json/getlistspjrinci", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspjrinci(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        // NANTI DIGANTI
        final String idspj = request.getParameter("idspj");
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
        param.put("idskpd", idskpd);
        param.put("idspj", idspj);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = spjServices.getCountSpjRinci(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spjServices.getSpjRinci(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listpilihkegiatan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistpilihkegiatan(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        // NANTI DIGANTI
        final String idspj = request.getParameter("idspj");
        final String kodeaktif = "1"; //request.getParameter("kodeaktif");
        final String namakegiatan = request.getParameter("namakegiatan");
        final String namaprogram = request.getParameter("namaprogram");
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
        param.put("idskpd", idskpd);
        param.put("idspj", idspj);
        param.put("namaprogram", namaprogram);
        param.put("namakegiatan", namakegiatan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        
       // if ("1".equals(kodeaktif)) {
            final int banyak = spjServices.getCountComboKegiatanAktif1(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", spjServices.getComboKegiatanAktif1(param));
            /*
        } else if ("0".equals(kodeaktif)) {
            final int banyak = spjServices.getCountComboKegiatan(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", spjServices.getComboKegiatan(param));
        }*/

        return mapData;
    }

    @RequestMapping(value = "/addspj", method = RequestMethod.GET)
    public ModelAndView add(final SppUp sppUp, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        return new ModelAndView("spp/spj/addspjbl", "spj", sppUp);
    }

    @RequestMapping(value = "/addspjbl/{idskpd}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable Integer idskpd, final Spj spj, final HttpServletRequest request, Model model) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }
        spj.setSkpd(referensiServices.getDetailSkpdById(idskpd));
        spj.setBendahara(spjServices.getBendaharaById(param));
        request.setAttribute("adanilaispj", 0);

        final List<Spj> bulan = spjServices.getBulan(param);
        model.addAttribute("listBulan", bulan);

        final int bulanCompare = spjServices.getBulanAdaSpj(param);
        request.setAttribute("bulanadaspj", bulanCompare);

        final String nihilnihil = spjServices.getNihilNihil(param);
        request.setAttribute("nihilnihil", nihilnihil);

        return new ModelAndView("spj/addspjbl", "refsppup", spj);

    }

    @RequestMapping(value = "/json/getbulansudahspj", method = RequestMethod.GET)
    public @ResponseBody
    Integer bulansudahspj(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String bulan = request.getParameter("bulan");
        final Integer idskpd = listSkpd.get(0).getIdSkpd();

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("bulan", bulan);

        final Integer bulansudahspj = spjServices.getBulanSudahSpj(param);

        if (bulansudahspj >= 1) {
            request.setAttribute("bulansudahspj", 1);
        } else if (bulansudahspj == 0) {
            request.setAttribute("bulansudahspj", 0);
        }

        return bulansudahspj;
    }

    @RequestMapping(value = "/json/getbanyakbulan ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getbanyakbulan(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        return spjServices.getBanyakBulan(param);
    }

    @RequestMapping(value = "/editspjbl/{idspj}", method = RequestMethod.GET)
    public ModelAndView editspjbl(@PathVariable Integer idspj, Spj spj, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();

        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }

        spj = spjServices.getSpjById(idspj);
        Integer idskpd = spj.getSkpd().getIdSkpd();
        String bulan = SipkdHelpers.splitString(spj.getBulan(), "/", 0);
        final String tahunAnggaran = spj.getTahun();
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idspj", idspj);
        param.put("idskpd", idskpd);
        param.put("tahun", tahunAnggaran);
        final BigDecimal totalspj = spjServices.getTotalSpjByIdSkpdAndTahun(param);
        if (totalspj.compareTo(BigDecimal.ZERO) > 0) {
            request.setAttribute("adanilaispj", 1);
        }
        return new ModelAndView("spj/editspjbl", "refsppup", spj);
    }

    @RequestMapping(value = "/deletespjbl/{idspj}", method = RequestMethod.GET)
    public ModelAndView deletespjbl(@PathVariable Integer idspj, Spj spj, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        spj = spjServices.getSpjById(idspj);
        return new ModelAndView("spj/deletespjbl", "refsppup", spj);
    }

    @RequestMapping(value = "/editkegiatan/{kodebeban}/{idkegiatan}/{idspj}", method = RequestMethod.GET)
    public ModelAndView editspjbl(@PathVariable String kodebeban, @PathVariable Integer idkegiatan, @PathVariable Integer idspj, Spj spj, final HttpServletRequest request, final HttpServletResponse response) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        // spj = spjServices.getSpjById(idspj);
        final Map< String, Object> param = new HashMap<String, Object>(2);
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("idspj", idspj);
        }

        param.put("idkegiatan", idkegiatan);
        param.put("kodebeban", kodebeban);
        request.setAttribute("idspj", idspj);

        spj = spjServices.getKegiatanById(param);
        return new ModelAndView("spj/listkegiatanpopupedit", "refspj", spj);
    }

    @RequestMapping(value = "/tambahkegiatan/{idspj}", method = RequestMethod.GET)
    public ModelAndView tambahkegiatan(@PathVariable Integer idspj, final HttpServletResponse response, final HttpServletRequest request, Spj spj) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("idspj", idspj);
        }
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        spj = spjServices.getSpjById(idspj);
        return new ModelAndView("spj/listkegiatanpopup", "refspj", spj);
    }

    @RequestMapping(value = "/pilihkegiatan/{idspj}", method = RequestMethod.GET)
    public ModelAndView pilihkegiatan(final HttpServletResponse response, @PathVariable Integer idspj, final HttpServletRequest request, Spj spj) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        spj = spjServices.getSpjById(idspj);
        return new ModelAndView("spj/pilihkegiatan", "spjpilihkegiatan", spj);
    }

    @RequestMapping(value = "/json/getlistspjrincibanyak ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getlistspjrincibanyak(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idspj = request.getParameter("idspj");
        final String idkegiatan = request.getParameter("idKegiatan");
        final String beban = request.getParameter("beban");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idspj", idspj);
        param.put("idkegiatan", idkegiatan);
        param.put("beban", beban);

        return spjServices.getCountSpjRinciKegiatan(param);
    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpan(@Valid @ModelAttribute("refsppup") Spj spj, BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        String bulan = SipkdHelpers.splitString(spj.getBulan(), "/", 0);
        Integer idskpd = spj.getSkpd().getIdSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("bulan", bulan);
        param.put("idskpd", idskpd);
        final int banyakbulan = spjServices.getCountBulanSpjByIdSkpdDanBulan(param);

        final StringBuilder sburl = new StringBuilder("redirect:/spj/editspjbl/");
        final StringBuilder sburlindex = new StringBuilder("redirect:/spj/indexspj/");
        StringBuilder finalurl = new StringBuilder();
        StringBuilder urlc = new StringBuilder();
        StringBuilder urlcs = new StringBuilder();
        if (result.hasErrors()) {
            return "/spj/addspjbllllll";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            spj.setBulan(SipkdHelpers.splitString(spj.getBulan(), "/", 0));
            spj.setIdEntry(pengguna.getIdPengguna());
            spj.setTglEntry(new Timestamp(System.currentTimeMillis()));
            spjServices.insertSpj(spj);

        }
        String nihil = spj.getNihil();
        if (nihil.equals("0")) {
            urlc = new StringBuilder("redirect:/spj/editspjbl/");
            finalurl = urlc.append(spj.getIdSpj());

            log.debug("indexoooo", "indexoooo");
        } else {
            urlcs = new StringBuilder("redirect:/spj/indexspj/");
            finalurl = urlcs;

            log.debug("editooooo", "editooooo");
        }
        return finalurl.toString();
    }

    @RequestMapping(value = "/prosesubah", method = RequestMethod.POST)
    public String prosesubah(@Valid @ModelAttribute("refsppup") Spj spj, BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes
    ) {
        String bulan = SipkdHelpers.splitString(spj.getBulan(), "/", 0);
        Integer idskpd = spj.getSkpd().getIdSkpd();
        Integer idspj = spj.getIdSpj();
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("bulan", bulan);
        param.put("idskpd", idskpd);

        final int banyakbulan = spjServices.getCountBulanSpjByIdSkpdDanBulan(param);
        final String bulanyangsama = spjServices.getBulanByBulan(idspj);
        log.debug("lloooo", bulanyangsama);
        log.debug("llooooxxx", bulan);
        if (banyakbulan >= 1) {
            if (!bulan.equals(bulanyangsama)) {
                redirectAttributes.addFlashAttribute("pesanbulan", new StringBuilder("Bulan SPJ Sudah Ada").toString());
                StringBuilder urlcx = new StringBuilder("redirect:/spj/editspjbl/");
                StringBuilder f = urlcx.append(idspj);
                return f.toString();
            }
        }
        final StringBuilder sburl = new StringBuilder("redirect:/spj/editspjbl/");
        if (result.hasErrors()) {
            return "/spj/editspjbl";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            spj.setBulan(SipkdHelpers.splitString(spj.getBulan(), "/", 0));
            spj.setIdEntry(pengguna.getIdPengguna());
            spj.setTglEntry(new Timestamp(System.currentTimeMillis()));
            spjServices.updateSpj(spj);
        }
        StringBuilder finalurl = new StringBuilder();
        StringBuilder urlc = new StringBuilder();
        StringBuilder urlcs = new StringBuilder();
        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data SPJ Master ")
                .append(" Berhasil Diupdate ")
                .toString());
        String nihil = spj.getNihil();

        if (nihil.equals("0")) {
            urlc = new StringBuilder("redirect:/spj/editspjbl/");
            finalurl = urlc.append(spj.getIdSpj());

            log.debug("indexoooo", "indexoooo");
        } else {
            urlcs = new StringBuilder("redirect:/spj/indexspj/");
            finalurl = urlcs;

            log.debug("editooooo", "editooooo");
        }
        return finalurl.toString();
    }

    @RequestMapping(value = "/json/prosessimpanrincikegiatan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpanrincikegiatan(@RequestBody List<Map<String, String>> listmapdata, final HttpServletRequest request) {
        final Timestamp tglSkrg = new Timestamp(System.currentTimeMillis());
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        for (Map<String, String> mapdata : listmapdata) {
            String idbl = mapdata.get("idbl");
            if (idbl != null) {
                log.debug(" mapdata " + mapdata.toString());
                SpjRinci spjRinci = new SpjRinci();
                spjRinci.setTglEntry(tglSkrg);
                spjRinci.setIdEntry(pengguna.getIdPengguna());
                spjRinci.setIdBl(SipkdHelpers.getIntFromString(idbl));
                spjRinci.setIdSpj(SipkdHelpers.getIntFromString(mapdata.get("idspj")));
                final Kegiatan kegiatan = new Kegiatan();
                kegiatan.setIdKegiatan(SipkdHelpers.getIntFromString(mapdata.get("idkegiatan")));
                spjRinci.setKegiatan(kegiatan);
                final Akun akun = new Akun();
                akun.setIdAkun(SipkdHelpers.getIntFromString(mapdata.get("idbas")));
                spjRinci.setAkun(akun);
                String bebankode;
                String kode = mapdata.get("kodebeban").toString();
                spjRinci.setKodeBeban((kode));
                spjRinci.setNilai_spj(SipkdHelpers.getBigDecimalFromString((String) mapdata.get("nilaispj")));

                spjServices.insertSpjRinci(spjRinci);
                spjRinci = null;
            }
        }

        return "Data SPJ  berhasil disimpan ";
    }

    @RequestMapping(value = "/json/prosesdeleteupdaterincikegiatan/{idspj}", method = RequestMethod.POST)
    public @ResponseBody
    String prosesdeleteupdaterincikegiatan(@PathVariable Integer idspj, @Valid @ModelAttribute("refspj") Spj spj, @RequestBody List<Map<String, String>> listmapdata, final HttpServletRequest request) {
        final Timestamp tglSkrg = new Timestamp(System.currentTimeMillis());
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        //  final int idspj = SipkdHelpers.getIntFromString(request.getParameter("idspj"));
        // spjServices.deleteUpdateSpjRinci(idspj);
        return "delete berhasil";
    }

    @RequestMapping(value = "/json/prosesubahrincikegiatan/{idspj}/{idkegiatan}/{beban}", method = RequestMethod.POST)
    public @ResponseBody
    String prosesubahrincikegiatan(@PathVariable Integer idspj, @PathVariable Integer idkegiatan, @PathVariable String beban, @RequestBody List<Map<String, String>> listmapdata, final HttpServletRequest request) {
        final Timestamp tglSkrg = new Timestamp(System.currentTimeMillis());
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("idspj", idspj);
        param.put("idkegiatan", idkegiatan);
        param.put("beban", beban);
       // spjServices.deleteUpdateSpjRinci(param);
        for (Map<String, String> mapdata : listmapdata) {
            log.debug(" mapdata " + mapdata.toString());
            
            SpjRinci spjRinci = new SpjRinci();
            spjRinci.setTglEntry(tglSkrg);
            spjRinci.setIdEntry(pengguna.getIdPengguna());
            spjRinci.setTglEdit(tglSkrg);
            spjRinci.setIdEdit(pengguna.getIdPengguna());
            spjRinci.setIdBl(SipkdHelpers.getIntFromString(mapdata.get("idbl")));
            spjRinci.setIdSpj(SipkdHelpers.getIntFromString(mapdata.get("idspj")));
            final Kegiatan kegiatan = new Kegiatan();
            kegiatan.setIdKegiatan(SipkdHelpers.getIntFromString(mapdata.get("idkegiatan")));
            spjRinci.setKegiatan(kegiatan);
            final Akun akun = new Akun();
            akun.setIdAkun(SipkdHelpers.getIntFromString(mapdata.get("idbas")));
            spjRinci.setAkun(akun);
            spjRinci.setKodeBeban((mapdata.get("kodebeban")));
            spjRinci.setNilai_spj(SipkdHelpers.getBigDecimalFromString((String) mapdata.get("nilaispj")));
            // Integer idspj = SipkdHelpers.getIntFromString(mapdata.get("idspj"));
            // spjServices.deleteUpdateSpjRinci(idspj);
            log.debug("Keterangan Add or Edit :: "+(String) mapdata.get("addeditval"));
            final String addoredit = (String) mapdata.get("addeditval");
            if ("add".equals(addoredit)){
                spjServices.insertSpjRinci(spjRinci);
            } else {
                spjServices.updateSpjRinci(spjRinci);
            }
            
            spjRinci = null;
        }

        return "Data SPJ  berhasil diupdate ";
    }

    @RequestMapping(value = "/json/proseshapusspjrinci", method = RequestMethod.POST)
    public @ResponseBody
    String proseshapusspjrinci(@RequestBody List<Map<String, String>> listmapdata, final HttpServletRequest request) {
        final Timestamp tglSkrg = new Timestamp(System.currentTimeMillis());
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        SpjRinci spjRinci = new SpjRinci();
        for (Map<String, String> mapdata : listmapdata) {
            log.debug(" mapdata " + mapdata.toString());

            spjRinci.setIdSpj(SipkdHelpers.getIntFromString(mapdata.get("idspj")));
            final Kegiatan kegiatan = new Kegiatan();
            kegiatan.setIdKegiatan(SipkdHelpers.getIntFromString(mapdata.get("idkegiatan")));
            spjRinci.setKegiatan(kegiatan);
            spjRinci.setKodeBeban((mapdata.get("kodebeban")));
            spjServices.deleteSpjRinciKegiatan(spjRinci);

        }

        return "Data SPJ  berhasil dihapus ";
    }

    @RequestMapping(value = "/prosesdeletespjblmaster", method = RequestMethod.POST)
    public String deletebank(@Valid @ModelAttribute("refsppup") Spj spj) {
        spjServices.deleteSpjBlMaster(spj.getIdSpj());
        return "redirect:/spj/indexspj";
    }

    //------------------ UNTUK JOURNAL SPJ ------------------------------
    @RequestMapping(value = "/indexjournalspj", method = RequestMethod.GET)
    public ModelAndView addjournal(final Spj spj, final HttpServletRequest request, Model model) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        Integer idskpd = listSkpd.get(0).getIdSkpd();

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        final List<Spj> bulan = spjServices.getBulanJournal(param);
        model.addAttribute("listBulanJournal", bulan);
        // log.debug(" bulan.get(0).getIdSpj()   ===   "+bulan.get(0).getIdSpj());
        try {
            request.setAttribute("idSpj", bulan.get(0).getIdSpj());
        } catch (Exception e) {
        }

        return new ModelAndView("spj/indexjournalspj", "refsppup", spj);
    }

    @RequestMapping(value = "/json/getlistjournalspj", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistjournalspj(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        final String bulan = request.getParameter("bulan");
        final String idSpj = request.getParameter("idSpj");
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
        param.put("bulan", bulan);
        param.put("idSpj", idSpj);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = spjServices.getBanyakJourSpj(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spjServices.getListJourSpj(param));
        return mapData;
    }

    @RequestMapping(value = "/prosejournal", method = RequestMethod.POST)
    public String prosesjournal(@Valid @ModelAttribute("refsppup") Spj spj,
            BindingResult result, final HttpServletRequest request,
            final RedirectAttributes redirectAttributes) {

        final StringBuilder sburl = new StringBuilder("redirect:/indexjournalspj");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Integer idskpd = listSkpd.get(0).getIdSkpd();
        final String bulan = request.getParameter("bulan");
        final Integer idSpj = SipkdHelpers.getIntFromString(request.getParameter("idSpj"));

        spj.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        spj.setIdEntry(pengguna.getIdPengguna());
        spj.setIdskpd(idskpd.toString());
        spj.setBulan(bulan);
        spj.setIdSpj(idSpj);

        spjServices.insertJournalSpj(spj);

        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                .append(" berhasil ditambahkan ")
                .toString());

        return "redirect:/spj/indexjournalspj";
    }

    @RequestMapping(value = "/json/prosessimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpanjson(@RequestBody Map<String, String> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        Spj spj = new Spj();
        spj.setTahun(mapdata.get("tahun"));
        spj.setIdskpd(mapdata.get("idskpd"));
        spj.setBulan(mapdata.get("bulan"));
        spj.setIdSpj(SipkdHelpers.getIntFromString(mapdata.get("idSpj")));
        spj.setIdEntry(pengguna.getIdPengguna());

        log.debug("CEK SPJ : TAHUN ==============", spj.getTahun());

        spjServices.insertJournalSpj(spj);
        return "Data Pagu SPP GUP/UP  berhasil disimpan ";

    }

    @RequestMapping(value = "/json/validasisisaspj ", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getvalidasi(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("idskpd", idskpd);
        param.put("tahun", tahun);

        final Map<String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("aData", spjServices.getListValidasiUpgu(param));
        return mapData;

    }

    @RequestMapping(value = "/json/getkodeaktif", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getkodeaktif(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(2);
        final Map< String, Object> mapData = new HashMap<String, Object>(4);
        final String idskpd = request.getParameter("idskpd");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        param.put("idskpd", idskpd);
        param.put("tahun", tahun);
        mapData.put("aData", spjServices.getKodeAktif(param));

        return mapData;
    }

    @RequestMapping(value = "/json/gettotalpagu", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> gettotalpagu(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(2);
        final Map< String, Object> mapData = new HashMap<String, Object>(4);
        final String idskpd = request.getParameter("idskpd");
        final String idspj = request.getParameter("idspj");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);
        param.put("idspj", idspj);
        param.put("tahun", tahun);
        mapData.put("aData", spjServices.getTotalPagu(param));

        return mapData;
    }

    @RequestMapping(value = "/json/gettotalpagutu", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> gettotalpagutu(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(2);
        final Map< String, Object> mapData = new HashMap<String, Object>(4);
        final String idskpd = request.getParameter("idskpd");
        final String idspj = request.getParameter("idspj");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);
        param.put("idspj", idspj);
        param.put("tahun", tahun);
        mapData.put("aData", spjServices.getTotalPaguTU(param));

        return mapData;
    }

    @RequestMapping(value = "/json/gettotalpaguindex", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> gettotalpaguindex(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(2);
        final Map< String, Object> mapData = new HashMap<String, Object>(4);
        final String idskpd = request.getParameter("idskpd");
        final String idspj = request.getParameter("idspj");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);
        param.put("idspj", idspj);
        param.put("tahun", tahun);
        mapData.put("aData", spjServices.getTotalPaguIndex(param));

        return mapData;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
