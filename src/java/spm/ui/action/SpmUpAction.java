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
import org.springframework.util.StringUtils;
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
import spp.model.SpmUp;
import spp.model.SppUp;
import spm.services.ReferensiServices;
import spm.services.SpmUpServices;
import spm.util.BigDecimalPropertyEditor;
import spm.util.SipkdHelpers;
import spm.util.SqlDatePropertyEditor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/spmup")
public class SpmUpAction {

    private static final Logger log = LoggerFactory.getLogger(SpmUpAction.class);

    @Autowired
    SpmUpServices spmUpServices;

    @Autowired
    ReferensiServices referensiServices;

    @RequestMapping(value = "/indexspmup", method = RequestMethod.GET)
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
        return "spm/up/indexspmup";
    }

    @RequestMapping(value = "/indexspmup/{idskpd}", method = RequestMethod.GET)
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
        return "spm/up/indexspmup";
    }

    @RequestMapping(value = "/json/getlistspmup", method = RequestMethod.GET)
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
        final int banyak = spmUpServices.getBanyakSpmUp(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spmUpServices.getAllSpmUp(param));
        return mapData;
    }

    @RequestMapping(value = "/editspmup/{idspp}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idspp, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }
        final SppUp sppUp = spmUpServices.getSpmUPById(idspp);
        return new ModelAndView("spm/up/editspmup", "refsppup", sppUp);
    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpanspdbtl(@Valid @ModelAttribute("refsppup") SpmUp spmUp,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        spmUp.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        final StringBuilder sburl = new StringBuilder("redirect:/spmup/editspmup/");
        if (result.hasErrors()) {
            return "spm/up/editspmup";
        } else {
            spmUp.setTglSpp(new Date(System.currentTimeMillis()));
            spmUp.setBulan(SipkdHelpers.splitString(spmUp.getBulan(), "/", 0));
            spmUp.setKodeUangMuka("0");
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            spmUp.setIdEntry(pengguna.getIdPengguna());
            spmUp.setTglEntry(new Timestamp(System.currentTimeMillis()));
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final Integer idspm = spmUp.getIdspm();
            if (idspm != null && idspm.compareTo(0) > 0) {
                spmUpServices.updateSpmUpMaster(spmUp);
            } else {
                spmUpServices.insertSpmUpMaster(spmUp);
            }

            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil ditambahkan ")
                    .toString());

            return sburl.append(spmUp.getId()).toString();
        }
    }

    @RequestMapping(value = "/json/getlistspdblspm", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspd(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final Map< String, Object> param = new HashMap<>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kodekegiatan = request.getParameter("kodekegiatan");
        final String spdno = request.getParameter("spdno");
        param.put("kodekegiatan", StringUtils.trimAllWhitespace(kodekegiatan));
        param.put("spdno", StringUtils.trimAllWhitespace(spdno));

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idspp", SipkdHelpers.getIntFromString(request.getParameter("idspp")));
        log.debug(param.toString());
        final Map<String, Object> mapData = new HashMap<>(4);
        log.debug(param.toString());
        final long banyak = spmUpServices.getBanyakSpdBLSPM(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spmUpServices.getAllSpdBLSPM(param));
        return mapData;

    }

    @RequestMapping(value = "/json/hapusdataspm",
            method = RequestMethod.POST)
    public @ResponseBody
    String hapusspmcetak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        spmUpServices.deleteDataSpmByid((Integer) mapdata.get("idspm"));
        return "Data SPM No " + mapdata.get("nospm") + " berhasil dihapus  ";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}
