package spj.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import spp.model.Kontrak;
import spp.model.Pengguna;
import spp.model.Skpd;
import spj.services.KontrakServices;
import spj.services.ReferensiServices;
import spj.services.RekananServices;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SipkdHelpers;
import spj.util.SqlDatePropertyEditor;

@Controller
@RequestMapping("/kontrak")
public class KontrakAction {

    private static final Logger log = LoggerFactory.getLogger(KontrakAction.class);

    @Autowired
    RekananServices rekananServices;

    @Autowired
    KontrakServices kontrakServices;

    @Autowired
    ReferensiServices referensiServices;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(final Kontrak kontrak, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        return new ModelAndView("/common/kontrak/listkontrak", "progcmd", kontrak);
    }

    @RequestMapping(value = "/{idskpd}", method = RequestMethod.GET)
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
        return "/common/kontrak/listkontrak";
    }

    @RequestMapping(value = "/addkontrak", method = RequestMethod.GET)
    public ModelAndView add(final Kontrak kontrak, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            request.setAttribute("isall", 1);
            request.setAttribute("tahunAnggaran", tahunAnggaran);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            request.setAttribute("isall", 0);
            request.setAttribute("tahunAnggaran", tahunAnggaran);
            request.setAttribute("skpd", listSkpd.get(0));
        }

        return new ModelAndView("/common/kontrak/addkontrak", "progcmd", kontrak);
    }

    @RequestMapping(value = "/prosessimpankontrak", method = RequestMethod.POST)
    public String prosessimpankontrak(@Valid @ModelAttribute("progcmd") Kontrak kontrak, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/common/kontrak/addkontrak";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            kontrak.setIdEntry(pengguna.getIdPengguna());
            kontrak.setTglEntry(new Timestamp(System.currentTimeMillis()));
            kontrakServices.insertKontrak(kontrak);

        }
        return "redirect:/kontrak";

    }

    @RequestMapping(value = "/json/getlistkontrak", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistkontrak(final HttpServletRequest request) throws ParseException {

        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("namaskpd");
        final String idskpd = request.getParameter("idskpd");
        final String kodeMetode = request.getParameter("kodemetode");
        final String rekanan = request.getParameter("rekanan");
        final String namakegiatan = request.getParameter("namakegiatan");
        /*DecimalFormatSymbols symbols = new DecimalFormatSymbols();
         symbols.setGroupingSeparator('.');
         symbols.setDecimalSeparator(',');
         String pattern = "#.##0,0#";
         DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
         decimalFormat.setParseBigDecimal(true);
         */
        //Parse String Now
        final BigDecimal nilai1 = SipkdHelpers.getBigDecimalFromString(request.getParameter("nilai1"));
        final BigDecimal nilai2 = SipkdHelpers.getBigDecimalFromString(request.getParameter("nilai2"));

        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date tglmulaii = null;
        Date tglakhirr = null;
        String tmptglmulai = request.getParameter("tglmulai");
        String tmptglakhir = request.getParameter("tglakhir");
        if (tmptglmulai != null && tmptglmulai.trim().length() > 0 && tmptglakhir != null && tmptglakhir.trim().length() > 0) {
            tglmulaii = new Date(sdf.parse(tmptglmulai).getTime());
            tglakhirr = new Date(sdf.parse(tmptglakhir).getTime());
        }
        // final java.util.Date tglmulai = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH).parse(tglmulaii);
        // final java.util.Date tglakhir = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH).parse(tglakhirr);

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
        param.put("namakegiatan", namakegiatan);
        param.put("kodemetode", kodeMetode);
        param.put("rekanan", rekanan);
        param.put("nilai1", nilai1);
        param.put("nilai2", nilai2);
        param.put("tglmulai", tglmulaii);
        param.put("tglakhir", tglakhirr);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = kontrakServices.getCountKontrak(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", kontrakServices.getKontrak(param));
        return mapData;
    }

    @RequestMapping(value = "/deletekontraks", method = RequestMethod.POST)
    public String prosesdelete(@Valid @ModelAttribute("progcmd") Kontrak kontrak, BindingResult result, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        kontrak.setIdEntry(pengguna.getIdPengguna());
        kontrak.setTglEntry(new Timestamp(System.currentTimeMillis()));
        kontrakServices.deleteKontrak(kontrak.getIdKontrak());

        return "redirect:/kontrak";
    }

    @RequestMapping(value = "/delkontrak/{id}", method = RequestMethod.GET)
    public ModelAndView deletekontrak(@PathVariable Integer id, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            request.setAttribute("isall", 1);
            request.setAttribute("tahunAnggaran", tahunAnggaran);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            request.setAttribute("isall", 0);
            request.setAttribute("tahunAnggaran", tahunAnggaran);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        Kontrak kontrak = kontrakServices.getKontrakById(id);
        return new ModelAndView("/common/kontrak/deletekontrak", "progcmd", kontrak);
    }

    @RequestMapping(value = "/updatekontraks", method = RequestMethod.POST)
    public String prosesupdate(@Valid @ModelAttribute("progcmd") Kontrak kontrak, BindingResult result, final HttpServletRequest request) {
       // System.out.println(" result.hasErrors() = " + result.hasErrors());
        if (result.hasErrors()) {
            return "/common/kontrak/updatekontrak";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            kontrak.setIdEntry(pengguna.getIdPengguna());
            kontrak.setTglEntry(new Timestamp(System.currentTimeMillis()));
            kontrakServices.updateKontrak(kontrak);
        }
        return "redirect:/kontrak";
    }

    @RequestMapping(value = "/updatekontrak/{id}", method = RequestMethod.GET)
    public ModelAndView updatekontrak(@PathVariable Integer id, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            request.setAttribute("isall", 1);
            request.setAttribute("tahunAnggaran", tahunAnggaran);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            request.setAttribute("isall", 0);
            request.setAttribute("tahunAnggaran", tahunAnggaran);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        Kontrak kontrak = kontrakServices.getKontrakById(id);
        return new ModelAndView("/common/kontrak/updatekontrak", "progcmd", kontrak);
    }

    @RequestMapping(value = "/listrekananpopup", method = RequestMethod.GET)
    public ModelAndView listrekanan(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("/common/kontrak/listrekananpopup");
    }

    @RequestMapping(value = "/listmetodepopup", method = RequestMethod.GET)
    public ModelAndView listmetode(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("/common/kontrak/listmetodepopup");
    }

    @RequestMapping(value = "/listkegiatanpopup", method = RequestMethod.GET)
    public ModelAndView listkegiatan(final HttpServletResponse response, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("/common/kontrak/listkegiatanpopup");
    }

    @RequestMapping(value = "/json/listpopuprekanan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsonrekanan(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("rekanan");
        param.put("rekanan", skpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = rekananServices.getCountRekanan(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", rekananServices.getRekanan(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listpopupmetode", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsonmetode(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("namametode");
        param.put("namametode", skpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = kontrakServices.getCountMetode(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", kontrakServices.getMetode(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listpopupkegiatan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpopupkegiatan(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String tahunAnggaran = request.getParameter("tahun");
        final String namaskpd = request.getParameter("namaskpd");
        final String idskpd = request.getParameter("idskpd");
        param.put("tahun", tahunAnggaran);
        param.put("namaskpd", namaskpd);
         param.put("idskpd", idskpd);

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = kontrakServices.getCountKegiatan(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", kontrakServices.getKegiatan(param));
        return mapData;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}
