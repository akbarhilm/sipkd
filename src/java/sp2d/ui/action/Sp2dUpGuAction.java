package sp2d.ui.action;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
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
import sp2d.services.Sp2dBtlLsServices;
import sp2d.services.Sp2dUpGuServices;
import sp2d.util.BigDecimalPropertyEditor;
import sp2d.util.SqlDatePropertyEditor;

@Controller
@RequestMapping("/sp2dupgu")
public class Sp2dUpGuAction {

    private static final Logger log = LoggerFactory.getLogger(Sp2dUpGuAction.class);
    @Autowired
    Sp2dUpGuServices sp2dUpGuServices;
    @Autowired
    Sp2dBtlLsServices sp2dBtlLsServices;

    @RequestMapping(value = "/indexsp2dupgu", method = RequestMethod.GET)
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
        return "sp2d/upgu/indexsp2dupgu";

    }

    @RequestMapping(value = "/indexsp2dupgu/{idskpd}", method = RequestMethod.GET)
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
        skpd = sp2dUpGuServices.getSkpdById(idskpd);
        String namaskpd = skpd.getNamaSkpd();
        String kodeSkpd = skpd.getKodeSkpd();
        Integer id = skpd.getIdSkpd();

        request.setAttribute("namaskpd", namaskpd);
        request.setAttribute("kodeSkpd", kodeSkpd);
        request.setAttribute("idskpds", id);

        return "sp2d/upgu/indexsp2dupgu";

    }

    @RequestMapping(value = "/json/getlistsp2dupgu", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistsp2dupgu(final HttpServletRequest request) {
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
        final int banyak = sp2dUpGuServices.getCountSp2dUpGu(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sp2dUpGuServices.getSp2dUpGu(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getlistsp2drinciupgu", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistsp2drinciupgu(final HttpServletRequest request) {
        final String idspp = request.getParameter("idspp");
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

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = sp2dUpGuServices.getCountSp2dRinciUpGu(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sp2dUpGuServices.getSp2dRinciUpGu(param));
        return mapData;
    }

    @RequestMapping(value = "/addsp2dupgu/{idspp}/{idspm}/{idskpd}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable Integer idspp, @PathVariable Integer idspm, @PathVariable Integer idskpd, Sp2dLs sp2dupgu, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("idspm", idspm);
        param.put("idspp", idspp);
        //param.put("kodewilayah", kodewilayah);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        Integer banyakharikerja = sp2dBtlLsServices.getCountHariKerjaSp2d(new Date(System.currentTimeMillis()));
        String tglRekam;
        if (banyakharikerja > 0) {
            final HariKerja hasilKerja = sp2dBtlLsServices.getHariKerjaSp2d(new Date(System.currentTimeMillis()));
            String tanggalRekam = hasilKerja.getTglRekam().toString();
            tglRekam = tanggalRekam;
        } else {
            Date tgl = new Date(System.currentTimeMillis());
            tglRekam = tgl.toString();
        }
        //String tanggalRekam = hasilKerja.getTglRekam().toString();

        request.setAttribute("tglsp2d", tglRekam);

        sp2dupgu = sp2dUpGuServices.getSp2dUpGuById(param);

        return new ModelAndView("sp2d/upgu/addsp2dupgu", "refsp2d", sp2dupgu);

    }

    @RequestMapping(value = "/editsp2dupgu/{idspp}/{idspm}/{idskpd}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idspp, @PathVariable Integer idspm, @PathVariable Integer idskpd, Sp2dLs sp2dupgu, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(2);
        //param.put("kodewilayah", kodewilayah);
        param.put("idspm", idspm);
        param.put("idspp", idspp);
        //param.put("kodewilayah", kodewilayah);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        sp2dupgu = sp2dUpGuServices.getSp2dUpGuByIdSp2d(param);

        return new ModelAndView("sp2d/upgu/editsp2dupgu", "refsp2d", sp2dupgu);

    }

    @RequestMapping(value = "/deletesp2dupgu/{idspp}/{idspm}/{idskpd}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Integer idspp, @PathVariable Integer idspm, @PathVariable Integer idskpd, Sp2dLs sp2dupgu, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(2);
        //param.put("kodewilayah", kodewilayah);
        param.put("idspm", idspm);
        param.put("idspp", idspp);
        //param.put("kodewilayah", kodewilayah);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        sp2dupgu = sp2dUpGuServices.getSp2dUpGuByIdSp2d(param);

        return new ModelAndView("sp2d/upgu/deletesp2dupgu", "refsp2d", sp2dupgu);

    }

    @RequestMapping(value = "/listkbud", method = RequestMethod.GET)
    public ModelAndView listkbud(final HttpServletResponse response, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        request.setAttribute("pengguna", pengguna);
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        //  spj = spjServices.getSpjById(idspj);
        return new ModelAndView("sp2d/upgu/listkbud");
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
        final int banyak = sp2dBtlLsServices.getCountKbud(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sp2dBtlLsServices.getKbud(param));
        return mapData;
    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpan(@Valid @ModelAttribute("refsp2d") Sp2dLs sp2dupgu, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/sp2dupgu/addsp2dupgu";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sp2dupgu.setIdEntry(pengguna.getIdPengguna());
            sp2dupgu.setTglEntry(new Timestamp(System.currentTimeMillis()));
            sp2dUpGuServices.insertSp2dUpGu(sp2dupgu);
        }
        Integer idskpd = sp2dupgu.getSkpd().getIdSkpd();

        StringBuilder urlcx = new StringBuilder("redirect:/sp2dupgu/indexsp2dupgu/");
        StringBuilder f = urlcx.append(idskpd);

        return f.toString();
    }

    @RequestMapping(value = "/prosesubah", method = RequestMethod.POST)
    public String prosesubah(@Valid @ModelAttribute("refsp2d") Sp2dLs sp2dupgu, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/sp2dupgu/editsp2dupgu";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sp2dupgu.setIdEdit(pengguna.getIdPengguna());
            sp2dupgu.setTglEdit(new Timestamp(System.currentTimeMillis()));
            sp2dUpGuServices.updateSp2dUpGu(sp2dupgu);
        }
        Integer idskpd = sp2dupgu.getSkpd().getIdSkpd();

        StringBuilder urlcx = new StringBuilder("redirect:/sp2dupgu/indexsp2dupgu/");
        StringBuilder f = urlcx.append(idskpd);

        return f.toString();
    }

    @RequestMapping(value = "/proseshapus", method = RequestMethod.POST)
    public String proseshapus(@Valid @ModelAttribute("refsp2d") Sp2dLs sp2dBtlLs, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/sp2dupgu/deletesp2dupgu";
        } else {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sp2dBtlLs.setIdEdit(pengguna.getIdPengguna());
            sp2dBtlLs.setIdEntry(pengguna.getIdPengguna());
            sp2dBtlLs.setTahun(tahunAnggaran);
            //sp2dBtlLs.setTglEdit(new Timestamp(System.currentTimeMillis()));

            sp2dUpGuServices.deleteSp2dBtlLs(sp2dBtlLs);
        }
        Integer idskpd = sp2dBtlLs.getSkpd().getIdSkpd();

        StringBuilder urlcx = new StringBuilder("redirect:/sp2dupgu/indexsp2dupgu/");
        StringBuilder f = urlcx.append(idskpd);

        return f.toString();
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
