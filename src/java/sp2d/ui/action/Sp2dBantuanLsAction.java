package sp2d.ui.action;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spp.model.HariKerja;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.Sp2dLs;
import sp2d.services.Sp2dBantuanLsServices;
import sp2d.util.BigDecimalPropertyEditor;
import sp2d.util.SqlDatePropertyEditor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/sp2dbantuanls")
public class Sp2dBantuanLsAction {

    private static final Logger log = LoggerFactory.getLogger(Sp2dBantuanLsAction.class);
    @Autowired
    Sp2dBantuanLsServices sp2dBantuanLsServices;

    @RequestMapping(value = "/indexsp2dbantuanls", method = RequestMethod.GET)
    public String index(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("pengguna", pengguna);
        }
        return "sp2d/bantuanls/indexsp2dbantuanls";

    }

    @RequestMapping(value = "/indexsp2dbantuanls/{idskpd}", method = RequestMethod.GET)
    public String index(@PathVariable Integer idskpd, final HttpServletRequest request, Skpd skpd) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("pengguna", pengguna);
        }
        skpd = sp2dBantuanLsServices.getSkpdById(idskpd);
        String namaskpd = skpd.getNamaSkpd();
        String kodeSkpd = skpd.getKodeSkpd();
        Integer id = skpd.getIdSkpd();

        request.setAttribute("namaskpd", namaskpd);
        request.setAttribute("kodeSkpd", kodeSkpd);
        request.setAttribute("idskpds", id);

        return "sp2d/bantuanls/indexsp2dbantuanls";

    }

    @RequestMapping(value = "/json/getlistsp2dbantuanls", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistsp2dBantuanLs(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("namaskpd");
        final String kodewilayah = request.getParameter("wilproses");
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
        param.put("wilproses", kodewilayah);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = sp2dBantuanLsServices.getCountSp2dBantuanLs(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sp2dBantuanLsServices.getSp2dBantuanLs(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getlistsp2drincibantuanls", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistsp2drincibtlls(final HttpServletRequest request) {
        final String idspp = request.getParameter("idspp");
        final String idskpdkoord = request.getParameter("idskpdkoord");
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
        param.put("idspp", idspp);
        param.put("idskpdkoord", idskpdkoord);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = sp2dBantuanLsServices.getCountSp2dRinciBantuanLs(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sp2dBantuanLsServices.getSp2dRinciBantuanLs(param));
        return mapData;
    }

    @RequestMapping(value = "/addsp2dbantuanls/{idspp}/{idspm}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable Integer idspp, @PathVariable Integer idspm, Sp2dLs sp2dBantuanLs, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("idspm", idspm);
        param.put("idspp", idspp);
        param.put("tahun", tahunAnggaran);

        Integer banyakharikerja = sp2dBantuanLsServices.getCountHariKerjaSp2d(new Date(System.currentTimeMillis()));
        String tglRekam;
        if (banyakharikerja > 0) {
            final HariKerja hasilKerja = sp2dBantuanLsServices.getHariKerjaSp2d(new Date(System.currentTimeMillis()));
            String tanggalRekam = hasilKerja.getTglRekam().toString();
            tglRekam = tanggalRekam;
        } else {
            Date tgl = new Date(System.currentTimeMillis());
            tglRekam = tgl.toString();
        }
        //String tanggalRekam = hasilKerja.getTglRekam().toString();

        request.setAttribute("tglsp2d", tglRekam);

        sp2dBantuanLs = sp2dBantuanLsServices.getSp2dBantuanLsById(param);

        return new ModelAndView("sp2d/bantuanls/addsp2dbantuanls", "refsp2d", sp2dBantuanLs);

    }

    @RequestMapping(value = "/editsp2dbantuanls/{idspp}/{idspm}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idspp, @PathVariable Integer idspm, Sp2dLs sp2dBantuanLs, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(2);
        //param.put("kodewilayah", kodewilayah);
        param.put("idspm", idspm);
        param.put("idspp", idspp);
        //param.put("kodewilayah", kodewilayah);
        param.put("tahun", tahunAnggaran);
        sp2dBantuanLs = sp2dBantuanLsServices.getSp2dBantuanLsById(param);

        return new ModelAndView("sp2d/bantuanls/editsp2dbantuanls", "refsp2d", sp2dBantuanLs);

    }

    @RequestMapping(value = "/deletesp2dbantuanls/{idspp}/{idspm}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Integer idspp, @PathVariable Integer idspm, Sp2dLs sp2dBantuanLs, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(2);
        //param.put("kodewilayah", kodewilayah);
        param.put("idspm", idspm);
        param.put("idspp", idspp);
        //param.put("kodewilayah", kodewilayah);
        param.put("tahun", tahunAnggaran);
        sp2dBantuanLs = sp2dBantuanLsServices.getSp2dBantuanLsById(param);

        return new ModelAndView("sp2d/bantuanls/deletesp2dbantuanls", "refsp2d", sp2dBantuanLs);

    }

    @RequestMapping(value = "/listkbud", method = RequestMethod.GET)
    public ModelAndView listkbud(final HttpServletResponse response, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        request.setAttribute("pengguna", pengguna);
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        //  spj = spjServices.getSpjById(idspj);
        return new ModelAndView("sp2d/bantuanls/listkbud");
    }

    @RequestMapping(value = "/json/getlistpopupkbud", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistpopupkbud(final HttpServletRequest request) {
        final String kodewilayah = request.getParameter("kodewilayah");
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
        param.put("kodewilayah", kodewilayah);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = sp2dBantuanLsServices.getCountKbud(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sp2dBantuanLsServices.getKbud(param));
        return mapData;
    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpan(@Valid @ModelAttribute("refsp2d") Sp2dLs sp2dBantuanLs, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/sp2dBantuanLs/addsp2dBantuanLs";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sp2dBantuanLs.setIdEntry(pengguna.getIdPengguna());
            sp2dBantuanLs.setTglEntry(new Timestamp(System.currentTimeMillis()));
            sp2dBantuanLsServices.insertSp2dBantuanLs(sp2dBantuanLs);
        }
        Integer idskpd = sp2dBantuanLs.getSkpd().getIdSkpdkoord();

        StringBuilder urlcx = new StringBuilder("redirect:/sp2dbantuanls/indexsp2dbantuanls/");
        StringBuilder f = urlcx.append(idskpd);

        return f.toString();
    }

    @RequestMapping(value = "/prosesubah", method = RequestMethod.POST)
    public String prosesubah(@Valid @ModelAttribute("refsp2d") Sp2dLs sp2dBantuanLs, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/sp2dBantuanLs/editsp2dBantuanLs";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sp2dBantuanLs.setIdEdit(pengguna.getIdPengguna());
            sp2dBantuanLs.setTglEdit(new Timestamp(System.currentTimeMillis()));
            sp2dBantuanLsServices.updateSp2dBantuanLs(sp2dBantuanLs);
        }
        Integer idskpd = sp2dBantuanLs.getSkpd().getIdSkpdkoord();

        StringBuilder urlcx = new StringBuilder("redirect:/sp2dbantuanls/indexsp2dbantuanls/");
        StringBuilder f = urlcx.append(idskpd);

        return f.toString();
    }

    @RequestMapping(value = "/proseshapus", method = RequestMethod.POST)
    public String proseshapus(@Valid @ModelAttribute("refsp2d") Sp2dLs sp2dBtlLs, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/sp2dbantuanls/deletesp2dbantuanls";
        } else {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sp2dBtlLs.setIdEdit(pengguna.getIdPengguna());
            sp2dBtlLs.setIdEntry(pengguna.getIdPengguna());
            sp2dBtlLs.setTahun(tahunAnggaran);
            //sp2dBtlLs.setTglEdit(new Timestamp(System.currentTimeMillis()));

            sp2dBantuanLsServices.deleteSp2dBtlLs(sp2dBtlLs);
        }
        Integer idskpd = sp2dBtlLs.getSkpd().getIdSkpd();

        StringBuilder urlcx = new StringBuilder("redirect:/sp2dbantuanls/indexsp2dbantuanls/");
        StringBuilder f = urlcx.append(idskpd);

        return f.toString();
    }
    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
