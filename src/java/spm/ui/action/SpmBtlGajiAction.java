package spm.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
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
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.SpmBtlLs;
import spp.model.SpmProses;
import spp.model.SppBtl;
//import spm.services.SppBtlServices;
import spm.services.ReferensiServices;
import spm.services.SpmBtlGajiServices;
import spm.util.BigDecimalPropertyEditor;
import spm.util.SipkdHelpers;
import spm.util.SqlDatePropertyEditor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/spmbtlgaji")
public class SpmBtlGajiAction {

    private static final Logger log = LoggerFactory.getLogger(SpmBtlGajiAction.class);

    @Autowired
    SpmBtlGajiServices spmBtlGajiServices;

    //@Autowired
    //SppBtlServices sppBtlServices;

    @Autowired
    ReferensiServices referensiServices;

    @RequestMapping(value = "/indexspmbtlgaji", method = RequestMethod.GET)
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
        return "spm/btlgaji/indexspmbtlgaji";
    }
/*
    @RequestMapping(value = "/indexspmbtlgaji/{idskpd}", method = RequestMethod.GET)
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
        return "spm/btlgaji/indexspmbtlgaji";
    }
*/
    @RequestMapping(value = "/json/getlistspmbtlgaji", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspppup(final HttpServletRequest request) {
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
        final int banyak = spmBtlGajiServices.getBanyakSpmBtlGaji(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spmBtlGajiServices.getAllSpmBtlGaji(param));
        return mapData;
    }
    
    @RequestMapping(value = "/editspmbtlgaji/{idspp}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idspp, final HttpServletRequest request, SpmBtlLs spmBtlLs) {
        final Map<String, Object> paramproses = new HashMap<>(2);
        paramproses.put("jenis", 1);
        paramproses.put("beban", "LS");
        final SpmProses spmProses = referensiServices.getSpmBatasProses(paramproses);
        final boolean status = SipkdHelpers.compareDateWithCurrent(spmProses.getBatasWaktu());
        request.setAttribute("status", status);
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            //final Integer idspm = spmBtlLsServices.getIdSpmByIdSpp(idspp);

            //request.setAttribute("cek", spmBtlLsServices.getPotonganValidator(idspm));
        }
        final SppBtl sppBtl = spmBtlGajiServices.getSpmBtlGajiById(idspp);
        final Integer cek = spmBtlGajiServices.getCekSpm(idspp);
        final Integer cek2 = spmBtlGajiServices.getCekPotongan(idspp);
        

        //Integer ids = spmBtlLs.getIdspm();
        if (cek == 0) {
            request.setAttribute("setids", 0);
            //request.setAttribute("cek", 0);
        } else {
            //final Integer idspm = spmBtlGajiServices.getIdSpmByIdSpp(idspp);
            request.setAttribute("setids", 1);
            //request.setAttribute("cek", spmBtlGajiServices.getPotonganValidator(idspp));
        }
        
        if(cek2 == 2)
            request.setAttribute("cek", 0);
        else
            request.setAttribute("cek", 1);

        return new ModelAndView("spm/btlgaji/editspmbtlgaji", "refsppbtl", sppBtl);
    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpanspdbtl(@Valid @ModelAttribute("refsppbtl") SpmBtlLs spmBtlLs,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        spmBtlLs.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        final StringBuilder sburl = new StringBuilder("redirect:/spmbtlgaji/indexspmbtlgaji/");
        if (result.hasErrors()) {
            return "spm/btlgaji/editspmbtlgaji";
        } else {
            spmBtlLs.setTglSpp(new Date(System.currentTimeMillis()));
            spmBtlLs.setBulan(SipkdHelpers.splitString(spmBtlLs.getBulan(), "/", 0));
            spmBtlLs.setKodeUangMuka("0");
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            spmBtlLs.setIdEntry(pengguna.getIdPengguna());
            spmBtlLs.setTglEntry(new Timestamp(System.currentTimeMillis()));
            //final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final Integer idspm = spmBtlLs.getIdspm();
            if (idspm != null && idspm.compareTo(0) > 0) {
                //spmBtlGajiServices.updateSpmBtlGaji(spmBtlLs);
            } else {
                spmBtlGajiServices.insertSpmBtlGaji(spmBtlLs);
            }
            //if(spmBtlLs.isPotongan())
            
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil ditambahkan " + spmBtlLs.getKodePot())
                    .toString());

            return sburl.toString();
        }
    }
    
    @RequestMapping(value = "/json/hapusdataspm",
            method = RequestMethod.POST)
    public @ResponseBody
    String hapusspmcetak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        spmBtlGajiServices.deleteDataSpmByid((Integer) mapdata.get("idspm"));
        return "Data SPM No " + mapdata.get("nospm") + " berhasil dihapus  ";
    }
    
/*
    
    @RequestMapping(value = "/json/getanggarandanspdbantuanlangsung/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, BigDecimal> getanggarandanspd(@PathVariable Integer id, final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", id);
        return spmBtlGajiServices.getTotalAnggaranDanSpd(param);
    }

    

    @RequestMapping(value = "/json/getlistspdbtl", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspd(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Integer idskpd = listSkpd.get(0).getIdSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kodekegiatan = request.getParameter("kodekegiatan");
       // final String spdno = request.getParameter("spdno");
        // param.put("kodekegiatan", StringUtils.trimAllWhitespace(kodekegiatan));
        // param.put("spdno", StringUtils.trimAllWhitespace(spdno));

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
        final long banyak = spmBtlGajiServices.getBanyakSpdBtl(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spmBtlGajiServices.getAllSpdBtl(param));
        return mapData;

    }
*/
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}
