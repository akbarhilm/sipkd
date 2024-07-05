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
import sp2d.services.Sp2dBtlLsServices;
import sp2d.util.BigDecimalPropertyEditor;
import sp2d.util.SqlDatePropertyEditor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/sp2dbtlls")
public class Sp2dBtlLsAction {

    private static final Logger log = LoggerFactory.getLogger(Sp2dBtlLsAction.class);
    @Autowired
    Sp2dBtlLsServices sp2dBtlLsServices;

    @RequestMapping(value = "/indexsp2dbtlls", method = RequestMethod.GET)
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
        return "sp2d/btl/ls/indexsp2dbtlls";

    }

    @RequestMapping(value = "/indexsp2dbtlls/{idskpd}", method = RequestMethod.GET)
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
        skpd = sp2dBtlLsServices.getSkpdById(idskpd);
        String namaskpd = skpd.getNamaSkpd();
        String kodeSkpd = skpd.getKodeSkpd();
        Integer id = skpd.getIdSkpd();

        request.setAttribute("namaskpd", namaskpd);
        request.setAttribute("kodeSkpd", kodeSkpd);
        request.setAttribute("idskpds", id);

        return "sp2d/btl/ls/indexsp2dbtlls";

    }

    @RequestMapping(value = "/json/getlistsp2dbtlls", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistsp2dbtlls(final HttpServletRequest request) {
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
        final int banyak = sp2dBtlLsServices.getCountSp2dBtlLs(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sp2dBtlLsServices.getSp2dBtlLs(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getlistsp2drincibtlls", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistsp2drincibtlls(final HttpServletRequest request) {
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
        final int banyak = sp2dBtlLsServices.getCountSp2dRinciBtlLs(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sp2dBtlLsServices.getSp2dRinciBtlLs(param));
        return mapData;
    }

    @RequestMapping(value = "/addsp2dbtlls/{idspp}/{idspm}/{idskpd}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable Integer idspp, @PathVariable Integer idspm, @PathVariable Integer idskpd, Sp2dLs sp2dBtlLs, final HttpServletRequest request) {
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

        sp2dBtlLs = sp2dBtlLsServices.getSp2dBtlLsById(param);

        return new ModelAndView("sp2d/btl/ls/addsp2dbtlls", "refsp2d", sp2dBtlLs);

    }
    
    @RequestMapping(value = "/editsp2dbtlls/{idspp}/{idspm}/{idskpd}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idspp, @PathVariable Integer idspm, @PathVariable Integer idskpd, Sp2dLs sp2dBtlLs, final HttpServletRequest request) {
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
        sp2dBtlLs = sp2dBtlLsServices.getSp2dBtlLsByIdSp2d(param);

        return new ModelAndView("sp2d/btl/ls/editsp2dbtlls", "refsp2d", sp2dBtlLs);

    }

    @RequestMapping(value = "/deletesp2dbtlls/{idspp}/{idspm}/{idskpd}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Integer idspp, @PathVariable Integer idspm, @PathVariable Integer idskpd, Sp2dLs sp2dBtlLs, final HttpServletRequest request) {
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
        sp2dBtlLs = sp2dBtlLsServices.getSp2dBtlLsByIdSp2d(param);

        return new ModelAndView("sp2d/btl/ls/deletesp2dbtlls", "refsp2d", sp2dBtlLs);

    }

    @RequestMapping(value = "/listkbud", method = RequestMethod.GET)
    public ModelAndView listkbud(final HttpServletResponse response, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        request.setAttribute("pengguna", pengguna);
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        //  spj = spjServices.getSpjById(idspj);
        return new ModelAndView("sp2d/btl/ls/listkbud");
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
    public String prosessimpan(@Valid @ModelAttribute("refsp2d") Sp2dLs sp2dBtlLs, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/sp2dbtlls/addsp2dbtlls";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sp2dBtlLs.setIdEntry(pengguna.getIdPengguna());
            sp2dBtlLs.setTglEntry(new Timestamp(System.currentTimeMillis()));
            
            sp2dBtlLs.setNilaiBersihSp2d(new BigDecimal(sp2dBtlLs.getNilaiBersihSp2d().longValue()));
            sp2dBtlLs.setNilaiSp2d(new BigDecimal(sp2dBtlLs.getNilaiSp2d().longValue()));
            sp2dBtlLs.setNilaiPotSp2d(new BigDecimal(sp2dBtlLs.getNilaiPotSp2d().longValue()));
            
            sp2dBtlLsServices.insertSp2dBtlLs(sp2dBtlLs);
        }
        Integer idskpd = sp2dBtlLs.getSkpd().getIdSkpd();

        StringBuilder urlcx = new StringBuilder("redirect:/sp2dbtlls/indexsp2dbtlls/");
        StringBuilder f = urlcx.append(idskpd);

        return f.toString();
    }
    
    @RequestMapping(value = "/prosesubah", method = RequestMethod.POST)
    public String prosesubah(@Valid @ModelAttribute("refsp2d") Sp2dLs sp2dBtlLs, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/sp2dbtlls/editsp2dbtlls";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sp2dBtlLs.setIdEdit(pengguna.getIdPengguna());
            sp2dBtlLs.setTglEdit(new Timestamp(System.currentTimeMillis()));
            sp2dBtlLsServices.updateSp2dBtlLs(sp2dBtlLs);
        }
        Integer idskpd = sp2dBtlLs.getSkpd().getIdSkpd();

        StringBuilder urlcx = new StringBuilder("redirect:/sp2dbtlls/indexsp2dbtlls/");
        StringBuilder f = urlcx.append(idskpd);

        return f.toString();
    }
    
    @RequestMapping(value = "/proseshapus", method = RequestMethod.POST)
    public String proseshapus(@Valid @ModelAttribute("refsp2d") Sp2dLs sp2dBtlLs, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/sp2dbtlls/deletesp2dbtlls";
        } else {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sp2dBtlLs.setIdEdit(pengguna.getIdPengguna());
            sp2dBtlLs.setIdEntry(pengguna.getIdPengguna());
            sp2dBtlLs.setTahun(tahunAnggaran);
            //sp2dBtlLs.setTglEdit(new Timestamp(System.currentTimeMillis()));

            sp2dBtlLsServices.deleteSp2dBtlLs(sp2dBtlLs);
        }
        Integer idskpd = sp2dBtlLs.getSkpd().getIdSkpd();

        StringBuilder urlcx = new StringBuilder("redirect:/sp2dbtlls/indexsp2dbtlls/");
        StringBuilder f = urlcx.append(idskpd);

        return f.toString();
    }
    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
