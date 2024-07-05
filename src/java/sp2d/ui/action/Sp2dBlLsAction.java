 

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
import spp.model.Sp2dBlLs;
//import spp.model.Sp2dBlLsRinci;
import sp2d.services.Sp2dBlLsServices;
import sp2d.util.BigDecimalPropertyEditor;
import sp2d.util.SqlDatePropertyEditor;

/**
 *
 * @author Xalamaster
 */
@Controller
@RequestMapping("/sp2dblls")
public class Sp2dBlLsAction {
    private static final Logger log = LoggerFactory.getLogger(Sp2dBlLsAction.class);
    @Autowired
    Sp2dBlLsServices sp2dBlLsServices;

    @RequestMapping(value = "/indexsp2dblls", method = RequestMethod.GET)
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
        return "sp2d/bl/ls/indexsp2dblls";

    }

    @RequestMapping(value = "/indexsp2dblls/{idskpd}", method = RequestMethod.GET)
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
        skpd = sp2dBlLsServices.getSkpdById(idskpd);
        String namaskpd = skpd.getNamaSkpd();
        String kodeSkpd = skpd.getKodeSkpd();
        Integer id = skpd.getIdSkpd();

        request.setAttribute("namaskpd", namaskpd);
        request.setAttribute("kodeSkpd", kodeSkpd);
        request.setAttribute("idskpds", id);

        return "sp2d/bl/ls/indexsp2dblls";

    }

    @RequestMapping(value = "/json/getlistsp2dblls", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistsp2dblls(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("namaskpd");
        final String kodewilayah = request.getParameter("wilproses");
        final Map< String, Object> param = new HashMap<>(2);
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
        final Map<String, Object> mapData = new HashMap<>(4);
        final int banyak = sp2dBlLsServices.getCountSp2dBlLs(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sp2dBlLsServices.getSp2dBlLs(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getlistsp2drinciblls", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistsp2drinciblls(final HttpServletRequest request) {
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
        final int banyak = sp2dBlLsServices.getCountSp2dRinciBlLs(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sp2dBlLsServices.getSp2dRinciBlLs(param));
        return mapData;
    }

    @RequestMapping(value = "/addsp2dblls/{idspp}/{idspm}/{idskpd}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable Integer idspp, @PathVariable Integer idspm, @PathVariable Integer idskpd, Sp2dBlLs sp2dBlLs, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("idspm", idspm);
        param.put("idspp", idspp);
        //param.put("kodewilayah", kodewilayah);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        Integer banyakharikerja = sp2dBlLsServices.getCountHariKerjaSp2d(new Date(System.currentTimeMillis()));
        String tglRekam;
        if (banyakharikerja > 0) {
            final HariKerja hasilKerja = sp2dBlLsServices.getHariKerjaSp2d(new Date(System.currentTimeMillis()));
            String tanggalRekam = hasilKerja.getTglRekam().toString();
            tglRekam = tanggalRekam;
        } else {
            Date tgl = new Date(System.currentTimeMillis());
            tglRekam = tgl.toString();
        }
        //String tanggalRekam = hasilKerja.getTglRekam().toString();

        request.setAttribute("tglsp2d", tglRekam);

        sp2dBlLs = sp2dBlLsServices.getSp2dBlLsById(param);

        return new ModelAndView("sp2d/bl/ls/addsp2dblls", "refsp2d", sp2dBlLs);

    }
    
    @RequestMapping(value = "/editsp2dblls/{idspp}/{idspm}/{idskpd}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idspp, @PathVariable Integer idspm, @PathVariable Integer idskpd, Sp2dBlLs sp2dBlLs, final HttpServletRequest request) {
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
        sp2dBlLs = sp2dBlLsServices.getSp2dBlLsByIdSp2d(param);

        return new ModelAndView("sp2d/bl/ls/editsp2dblls", "refsp2d", sp2dBlLs);

    }

    @RequestMapping(value = "/deletesp2dblls/{idspp}/{idspm}/{idskpd}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Integer idspp, @PathVariable Integer idspm, @PathVariable Integer idskpd, Sp2dBlLs sp2dBlLs, final HttpServletRequest request) {
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
        sp2dBlLs = sp2dBlLsServices.getSp2dBlLsByIdSp2d(param);

        return new ModelAndView("sp2d/bl/ls/deletesp2dblls", "refsp2d", sp2dBlLs);

    }

    @RequestMapping(value = "/listkbud", method = RequestMethod.GET)
    public ModelAndView listkbud(final HttpServletResponse response, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        request.setAttribute("pengguna", pengguna);
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        //  spj = spjServices.getSpjById(idspj);
        return new ModelAndView("sp2d/bl/ls/listkbud");
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
        final int banyak = sp2dBlLsServices.getCountKbud(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sp2dBlLsServices.getKbud(param));
        return mapData;
    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpan(@Valid @ModelAttribute("refsp2d") Sp2dBlLs sp2dBlLs, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/sp2dblls/addsp2dblls";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            
            
            log.debug("nilai bersih ==== "+sp2dBlLs.getNilaiBersihSp2d().longValue());
            log.debug("nilai potongan == "+sp2dBlLs.getNilaiPotSp2d().longValue());
            log.debug("nilai spp/spm === "+sp2dBlLs.getNilaiSp2d().longValue());
            log.debug("nilai BERSIH SP2D === "+sp2dBlLs.getNilaiBersihSp2d());
            
            sp2dBlLs.setIdEntry(pengguna.getIdPengguna());
            sp2dBlLs.setTglEntry(new Timestamp(System.currentTimeMillis()));
            sp2dBlLs.setNilaiBersihSp2d(new BigDecimal(sp2dBlLs.getNilaiBersihSp2d().longValue()));
            sp2dBlLs.setNilaiSp2d(new BigDecimal(sp2dBlLs.getNilaiSp2d().longValue()));
            sp2dBlLs.setNilaiPotSp2d(new BigDecimal(sp2dBlLs.getNilaiPotSp2d().longValue()));
            //log.debug("Nilai Bersih : "+sp2dBlLs.getNilaiBersihSp2d().toString());
            
            sp2dBlLsServices.insertSp2dBlLs(sp2dBlLs);
        }
        Integer idskpd = sp2dBlLs.getSkpd().getIdSkpd();

        StringBuilder urlcx = new StringBuilder("redirect:/sp2dblls/indexsp2dblls/");
        StringBuilder f = urlcx.append(idskpd);

        return f.toString();
    }
    
    @RequestMapping(value = "/prosesubah", method = RequestMethod.POST)
    public String prosesubah(@Valid @ModelAttribute("refsp2d") Sp2dBlLs sp2dBlLs, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/sp2dblls/editsp2dblls";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sp2dBlLs.setIdEdit(pengguna.getIdPengguna());
            sp2dBlLs.setTglEdit(new Timestamp(System.currentTimeMillis()));
            sp2dBlLsServices.updateSp2dBlLs(sp2dBlLs);
        }
        Integer idskpd = sp2dBlLs.getSkpd().getIdSkpd();

        StringBuilder urlcx = new StringBuilder("redirect:/sp2dblls/indexsp2dblls/");
        StringBuilder f = urlcx.append(idskpd);

        return f.toString();
    }
    
    @RequestMapping(value = "/proseshapus", method = RequestMethod.POST)
    public String proseshapus(@Valid @ModelAttribute("refsp2d") Sp2dBlLs sp2dBtlLs, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/sp2dblls/deletesp2dblls";
        } else {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            sp2dBtlLs.setIdEdit(pengguna.getIdPengguna());
            sp2dBtlLs.setIdEntry(pengguna.getIdPengguna());
            sp2dBtlLs.setTahun(tahunAnggaran);
            //sp2dBtlLs.setTglEdit(new Timestamp(System.currentTimeMillis()));

            sp2dBlLsServices.deleteSp2dBtlLs(sp2dBtlLs);
        }
        Integer idskpd = sp2dBtlLs.getSkpd().getIdSkpd();

        StringBuilder urlcx = new StringBuilder("redirect:/sp2dblls/indexsp2dblls/");
        StringBuilder f = urlcx.append(idskpd);

        return f.toString();
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
