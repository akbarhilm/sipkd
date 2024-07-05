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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.SpmBiayaLs;
import spp.model.SpmProses;
import spp.model.SppBiaya;
import spm.services.SppBiayaServices;
import spm.services.ReferensiServices;
import spm.services.SpmBiayaLsServices;
import spm.util.BigDecimalPropertyEditor;
import spm.util.SipkdHelpers;
import spm.util.SqlDatePropertyEditor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/spmbiayals")
public class SpmBiayaLsAction {

    private static final Logger log = LoggerFactory.getLogger(SpmBiayaLsAction.class);

    @Autowired
    SpmBiayaLsServices spmBiayaLsServices;

    @Autowired
    SppBiayaServices sppBiayaServices;

    @Autowired
    ReferensiServices referensiServices;

    @RequestMapping(value = "/indexspmbiayals", method = RequestMethod.GET)
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
        return "spm/biaya/ls/indexspmbiaya";
    }

    @RequestMapping(value = "/indexspmbiayals/{idskpd}", method = RequestMethod.GET)
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
        return "spm/biaya/ls/indexspmbiaya";
    }

    @RequestMapping(value = "/json/getlistspmbiayals", method = RequestMethod.GET)
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
        final int banyak = spmBiayaLsServices.getBanyakSpmBiayaLs(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spmBiayaLsServices.getAllSpmBiayaLs(param));
        return mapData;
    }

    @RequestMapping(value = "/editspmbiayals/{idspp}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idspp, final HttpServletRequest request) {
        final Map<String, Object> paramproses = new HashMap<>(2);
        paramproses.put("jenis",4 );
        paramproses.put("beban","LS" );        
        final SpmProses spmProses = referensiServices.getSpmBatasProses(paramproses);
        final boolean status = SipkdHelpers.compareDateWithCurrent(spmProses.getBatasWaktu());
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
         request.setAttribute("status", status);
        final SppBiaya sppBiaya = spmBiayaLsServices.getSpmBiayaLsById(idspp);
        return new ModelAndView("spm/biaya/ls/editspmbiayals", "refsppbiaya", sppBiaya);

    }

    @RequestMapping(value = "/json/getanggarandanspdbantuanlangsung/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, BigDecimal> getanggarandanspd(@PathVariable Integer id, final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", id);
        return spmBiayaLsServices.getTotalAnggaranDanSpd(param);
    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpanspdbiaya(@Valid @ModelAttribute("refsppbiaya") SpmBiayaLs spmBiayaLs,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        spmBiayaLs.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        final StringBuilder sburl = new StringBuilder("redirect:/spmbiayals/editspmbiayals/");
        if (result.hasErrors()) {
            return "spm/biaya/ls/editspmbiayals";
        } else {
            spmBiayaLs.setTglSpp(new Date(System.currentTimeMillis()));
            spmBiayaLs.setBulan(SipkdHelpers.splitString(spmBiayaLs.getBulan(), "/", 0));
            spmBiayaLs.setKodeUangMuka("0");
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            spmBiayaLs.setIdEntry(pengguna.getIdPengguna());
            spmBiayaLs.setTglEntry(new Timestamp(System.currentTimeMillis()));
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final Integer idspm = spmBiayaLs.getIdspm();
            if (idspm != null && idspm.compareTo(0) > 0) {
                spmBiayaLsServices.updateSpmBiayaLsMaster(spmBiayaLs);
            } else {
                spmBiayaLsServices.insertSpmBiayaLsMaster(spmBiayaLs);
            }

            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil ditambahkan ")
                    .toString());

            return sburl.append(spmBiayaLs.getId()).toString();
        }
    }

    @RequestMapping(value = "/json/getlistspdbiaya", method = RequestMethod.GET)
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
        final long banyak = spmBiayaLsServices.getBanyakSpdBiaya(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spmBiayaLsServices.getAllSpdBiaya(param));
        return mapData;

    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}
