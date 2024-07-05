/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import spp.model.Sp2dBlTu;
//import spp.model.Sp2dBlLsRinci;
import sp2d.services.Sp2dBlTuServices;
import sp2d.util.BigDecimalPropertyEditor;
import sp2d.util.SqlDatePropertyEditor;

/**
 *
 * @author Xalamaster
 */
@Controller
@RequestMapping("/sp2dbltu")
public class Sp2dBlTuAction {
    private static final Logger log = LoggerFactory.getLogger(Sp2dBlTuAction.class);
    @Autowired
    Sp2dBlTuServices sp2dBlTuServices;

    @RequestMapping(value = "/indexsp2dbltu", method = RequestMethod.GET)
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
        return "sp2d/bl/tu/indexsp2dbltu";

    }

    @RequestMapping(value = "/indexsp2dbltu/{idskpd}", method = RequestMethod.GET)
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
        skpd = sp2dBlTuServices.getSkpdById(idskpd);
        String namaskpd = skpd.getNamaSkpd();
        String kodeSkpd = skpd.getKodeSkpd();
        Integer id = skpd.getIdSkpd();

        request.setAttribute("namaskpd", namaskpd);
        request.setAttribute("kodeSkpd", kodeSkpd);
        request.setAttribute("idskpds", id);

        return "sp2d/bl/tu/indexsp2dbltu";

    }

    @RequestMapping(value = "/json/getlistsp2dbltu", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistsp2dbltu(final HttpServletRequest request) {
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
        final int banyak = sp2dBlTuServices.getCountSp2dBlTu(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sp2dBlTuServices.getSp2dBlTu(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getlistsp2drincibltu", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistsp2drincibltu(final HttpServletRequest request) {
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
        final int banyak = sp2dBlTuServices.getCountSp2dRinciBlTu(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sp2dBlTuServices.getSp2dRinciBlTu(param));
        return mapData;
    }

    @RequestMapping(value = "/addsp2dbltu/{idspp}/{idspm}/{idskpd}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable Integer idspp, @PathVariable Integer idspm, @PathVariable Integer idskpd, Sp2dBlTu sp2dBlTu, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("idspm", idspm);
        param.put("idspp", idspp);
        //param.put("kodewilayah", kodewilayah);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        Integer banyakharikerja = sp2dBlTuServices.getCountHariKerjaSp2d(new Date(System.currentTimeMillis()));
        String tglRekam;
        if (banyakharikerja > 0) {
            final HariKerja hasilKerja = sp2dBlTuServices.getHariKerjaSp2d(new Date(System.currentTimeMillis()));
            String tanggalRekam = hasilKerja.getTglRekam().toString();
            tglRekam = tanggalRekam;
        } else {
            Date tgl = new Date(System.currentTimeMillis());
            tglRekam = tgl.toString();
        }
        //String tanggalRekam = hasilKerja.getTglRekam().toString();

        request.setAttribute("tglsp2d", tglRekam);

        sp2dBlTu = sp2dBlTuServices.getSp2dBlTuById(param);

        return new ModelAndView("sp2d/bl/tu/addsp2dbltu", "refsp2d", sp2dBlTu);

    }
    
    @RequestMapping(value = "/editsp2dbltu/{idspp}/{idspm}/{idskpd}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idspp, @PathVariable Integer idspm, @PathVariable Integer idskpd, Sp2dBlTu sp2dBlTu, final HttpServletRequest request) {
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
        sp2dBlTu = sp2dBlTuServices.getSp2dBlTuByIdSp2d(param);

        return new ModelAndView("sp2d/bl/tu/editsp2dbltu", "refsp2d", sp2dBlTu);

    }

    @RequestMapping(value = "/deletesp2dbltu/{idspp}/{idspm}/{idskpd}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Integer idspp, @PathVariable Integer idspm, @PathVariable Integer idskpd, Sp2dBlTu sp2dBlTu, final HttpServletRequest request) {
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
        sp2dBlTu = sp2dBlTuServices.getSp2dBlTuByIdSp2d(param);

        return new ModelAndView("sp2d/bl/tu/deletesp2dbltu", "refsp2d", sp2dBlTu);

    }

    @RequestMapping(value = "/listkbud", method = RequestMethod.GET)
    public ModelAndView listkbud(final HttpServletResponse response, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        request.setAttribute("pengguna", pengguna);
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        //  spj = spjServices.getSpjById(idspj);
        return new ModelAndView("sp2d/bl/tu/listkbud");
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
        final int banyak = sp2dBlTuServices.getCountKbud(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sp2dBlTuServices.getKbud(param));
        return mapData;
    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpan(@Valid @ModelAttribute("refsp2d") Sp2dBlTu sp2dBlTu, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/sp2dbltu/addsp2dbltu";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sp2dBlTu.setIdEntry(pengguna.getIdPengguna());
            sp2dBlTu.setTglEntry(new Timestamp(System.currentTimeMillis()));
            sp2dBlTuServices.insertSp2dBlTu(sp2dBlTu);
        }
        Integer idskpd = sp2dBlTu.getSkpd().getIdSkpd();

        StringBuilder urlcx = new StringBuilder("redirect:/sp2dbltu/indexsp2dbltu/");
        StringBuilder f = urlcx.append(idskpd);

        return f.toString();
    }
    
    @RequestMapping(value = "/prosesubah", method = RequestMethod.POST)
    public String prosesubah(@Valid @ModelAttribute("refsp2d") Sp2dBlTu sp2dBlTu, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/sp2dbltu/editsp2dbltu";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sp2dBlTu.setIdEdit(pengguna.getIdPengguna());
            sp2dBlTu.setTglEdit(new Timestamp(System.currentTimeMillis()));
            sp2dBlTuServices.updateSp2dBlTu(sp2dBlTu);
        }
        Integer idskpd = sp2dBlTu.getSkpd().getIdSkpd();

        StringBuilder urlcx = new StringBuilder("redirect:/sp2dbltu/indexsp2dbltu/");
        StringBuilder f = urlcx.append(idskpd);

        return f.toString();
    }
    
    @RequestMapping(value = "/proseshapus", method = RequestMethod.POST)
    public String proseshapus(@Valid @ModelAttribute("refsp2d") Sp2dBlTu sp2dBtlLs, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/sp2dbltu/deletesp2dbltu";
        } else {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sp2dBtlLs.setIdEdit(pengguna.getIdPengguna());
            sp2dBtlLs.setIdEntry(pengguna.getIdPengguna());
            sp2dBtlLs.setTahun(tahunAnggaran);
            //sp2dBtlLs.setTglEdit(new Timestamp(System.currentTimeMillis()));

            sp2dBlTuServices.deleteSp2dBtlLs(sp2dBtlLs);
        }
        Integer idskpd = sp2dBtlLs.getSkpd().getIdSkpd();

        StringBuilder urlcx = new StringBuilder("redirect:/sp2dbltu/indexsp2dbltu/");
        StringBuilder f = urlcx.append(idskpd);

        return f.toString();
    }
    
    @RequestMapping(value = "/json/getTotal", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getTotal(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(2);
        final String idspp = request.getParameter("idspp");
        param.put("idspp", idspp);
        
        final Map< String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("cek", 1);
        mapData.put("aData", sp2dBlTuServices.getTotalBlTu(param));

        return mapData;

    }
    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
