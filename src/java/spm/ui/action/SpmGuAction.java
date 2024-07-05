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
import spp.model.SpmGu;
import spp.model.SpmProses;
import spp.model.SppGu;
import spm.services.ReferensiServices;
import spm.services.SpmGuServices;
import spm.util.BigDecimalPropertyEditor;
import spm.util.SipkdHelpers;
import spm.util.SqlDatePropertyEditor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/spmgu")
public class SpmGuAction {

    private static final Logger log = LoggerFactory.getLogger(SpmGuAction.class);

    @Autowired
    SpmGuServices spmGuServices;

    @Autowired
    ReferensiServices referensiServices;

    @RequestMapping(value = "/indexspmgu", method = RequestMethod.GET)
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
        return "spm/gu/indexspmgu";
    }

    @RequestMapping(value = "/indexspmgu/{idskpd}", method = RequestMethod.GET)
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
        return "spm/gu/indexspmgu";
    }

    @RequestMapping(value = "/json/getlistspmgu", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspppgu(final HttpServletRequest request) {
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
        final int banyak = spmGuServices.getBanyakSpmGu(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spmGuServices.getAllSpmGu(param));
        return mapData;
    }

    @RequestMapping(value = "/editspmgu/{idspp}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idspp, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final Map<String, Object> paramproses = new HashMap<>(2);
        paramproses.put("jenis", 3);
        paramproses.put("beban", "GU");
        final SpmProses spmProses = referensiServices.getSpmBatasProses(paramproses);
        final boolean status = SipkdHelpers.compareDateWithCurrent(spmProses.getBatasWaktu());
        request.setAttribute("status", status);
        
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }
        final SppGu sppGu = spmGuServices.getSpmGUById(idspp);
        return new ModelAndView("spm/gu/editspmgu", "refsppgu", sppGu);
    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpanspdbtl(@Valid @ModelAttribute("refsppgu") SpmGu spmGu,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        spmGu.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        final StringBuilder sburl = new StringBuilder("redirect:/spmgu/editspmgu/");
        if (result.hasErrors()) {
            return "spm/gu/editspmgu";
        } else {
            spmGu.setTglSpp(new Date(System.currentTimeMillis()));
            spmGu.setBulan(SipkdHelpers.splitString(spmGu.getBulan(), "/", 0));
            spmGu.setKodeUangMuka("0");
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            spmGu.setIdEntry(pengguna.getIdPengguna());
            spmGu.setTglEntry(new Timestamp(System.currentTimeMillis()));
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final Integer idspm = spmGu.getIdspm();
            if (idspm != null && idspm.compareTo(0) > 0) {
                spmGuServices.updateSpmGuMaster(spmGu);
            } else {
                spmGuServices.insertSpmGuMaster(spmGu);
            }

            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil ditambahkan ")
                    .toString());

            return sburl.append(spmGu.getId()).toString();
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}
