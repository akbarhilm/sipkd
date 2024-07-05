package spj.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import spp.model.Akun;
import spp.model.Kegiatan;
import spp.model.Npd;
import spp.model.NpdRinci;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.Spj;
import spp.model.SpjRinci;
import spp.model.SppTu;
import spp.model.SppTuRinci;

import spp.model.SppUp;
import spj.services.NpdServices;
import spj.services.ReferensiServices;
import spj.services.SpjServices;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SipkdHelpers;
import spj.util.SqlDatePropertyEditor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/npd")
public class NpdAction {

    private static final Logger log = LoggerFactory.getLogger(NpdAction.class);

    @Autowired
    NpdServices npdServices;

    @Autowired
    ReferensiServices referensiServices;

    @RequestMapping(value = "/indexnpd", method = RequestMethod.GET)
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

        final Map< String, Object> param = new HashMap<String, Object>(2);
        Integer idskpd = listSkpd.get(0).getIdSkpd();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        return "npd/indexnpd";

    }

    @RequestMapping(value = "/json/getlistnpd", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistnpd(final HttpServletRequest request) {
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
        //  param.put("namaskpd", skpd);
        param.put("idskpd", idskpd);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = npdServices.getCountNpd(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", npdServices.getNpd(param));
        return mapData;
    }

    @RequestMapping(value = "/addnpd", method = RequestMethod.GET)
    public ModelAndView add(final Npd npd, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();

        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }
        request.setAttribute("skpd", listSkpd.get(0));

        return new ModelAndView("npd/addnpd", "npd", npd);
    }

    @RequestMapping(value = "/pilihkegiatanprogram", method = RequestMethod.GET)
    public String pilihkegiatanprogram(final HttpServletResponse response, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        Integer idskpd = listSkpd.get(0).getIdSkpd();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        request.setAttribute("idskpd", idskpd);
        request.setAttribute("tahun", tahunAnggaran);
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "npd/listkegiatan";
    }

    @RequestMapping(value = "/json/listkegiatanpopup", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listkegiatanpopup(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kegiatan = request.getParameter("kegiatan");
        final String program = request.getParameter("program");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");

        param.put("kegiatan", kegiatan);
        param.put("program", program);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = npdServices.getCountKegiatanNpd(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", npdServices.getKegiatanNpd(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getlistnpdrinci", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistnpdrinci(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String idnpd = request.getParameter("idnpd");
        // final String skpd = request.getParameter("namaskpd");
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
        param.put("idkegiatan", idkegiatan);
        param.put("idnpd", idnpd);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = npdServices.getCountNpdRinci(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", npdServices.getNpdRinci(param));
        return mapData;
    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpanspdbl(@Valid @ModelAttribute("npd") Npd npd,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        npd.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        //log.debug(" hasil ################# " + result.getFieldError());
        final StringBuilder sburl = new StringBuilder("redirect:/npd/editnpd/");
        if (result.hasErrors()) {
            return "npd/addnpd";
        } else {
            npd.setNipPptk(request.getParameter("nipPptk"));
            npd.setNamaPptk(request.getParameter("namaPptk"));
         //   final Skpd skpd = new Skpd();
            //   final Kegiatan kegiatan = new Kegiatan();
            //   skpd.setIdSkpd(SipkdHelpers.getIntFromString(request.getParameter("idskpd")));
            //  kegiatan.setIdKegiatan(SipkdHelpers.getIntFromString(request.getParameter("idKegiatan")));
            // npd.setSkpd(skpd);
            // npd.setKegiatan(kegiatan);
            npd.setTglNpd(request.getParameter("tanggalNpd"));
            npd.setKetNpd(request.getParameter("ketNpd"));
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            npd.setIdEntry(pengguna.getIdPengguna());
            npd.setTglEntry(new Timestamp(System.currentTimeMillis()));

            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final List<NpdRinci> listNpdRinci = new ArrayList<NpdRinci>(banyakrinci);
            //log.debug(" Map param  "+  request.getParameterMap().toString());
            for (int i = 0; i < banyakrinci; i++) {

                final String penanda = "cekpilih" + (i + 1);
                final String idakun = request.getParameter(penanda);
                //    log.debug(penanda+" idBl ################# " + idBl);
                if (idakun != null && !idakun.isEmpty()) {

                    final NpdRinci npdRinci = new NpdRinci();
                    final Akun akun = new Akun();
                    akun.setIdAkun(SipkdHelpers.getIntFromString(idakun));
                    npdRinci.setAkun(akun);
                    npdRinci.setNilaiNpd(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaiNpd" + idakun)));
                    npdRinci.setIdEntry(pengguna.getIdPengguna());
                    npdRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));
                    listNpdRinci.add(npdRinci);
                }
            }
            npd.setNpdRinci(listNpdRinci);
            npdServices.insertNpd(npd);
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil ditambahkan ")
                    .toString());

            return sburl.append(npd.getIdNpd()).toString();
        }
    }

    @RequestMapping(value = "/json/getlistnpdrincibanyak ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getlistspjrincibanyak(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idKegiatan");
        final String idnpd = request.getParameter("idNpd");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);
        param.put("idnpd", idnpd);

        return npdServices.getCountNpdRinci(param);
    }

    @RequestMapping(value = "/editnpd/{idnpd}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idnpd, final HttpServletRequest request) {
         final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();

        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }
        request.setAttribute("skpd", listSkpd.get(0));
        
        final Npd npd = npdServices.getNpdById(idnpd);
        return new ModelAndView("npd/editnpd", "npd", npd);
    }
    
    @RequestMapping(value = "/deletenpd/{idnpd}", method = RequestMethod.GET)
    public ModelAndView deletenpd(@PathVariable Integer idnpd, final HttpServletRequest request) {
         final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();

        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }
        request.setAttribute("skpd", listSkpd.get(0));
        
        final Npd npd = npdServices.getNpdById(idnpd);
        return new ModelAndView("npd/deletenpd", "npd", npd);
    }
    @RequestMapping(value = "/prosesubah", method = RequestMethod.POST)
    public String prosesubah(@Valid @ModelAttribute("npd") Npd npd,  final BindingResult result,  final RedirectAttributes redirectAttributes,  final Model model, final HttpServletRequest request) {
         npd.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        //log.debug(" hasil ################# " + result.getFieldError());
        final StringBuilder sburl = new StringBuilder("redirect:/npd/editnpd/");
        if (result.hasErrors()) {
            return "npd/editnpd";
        } else {
            npd.setNipPptk(request.getParameter("nipPptk"));
            npd.setNamaPptk(request.getParameter("namaPptk"));
         //   final Skpd skpd = new Skpd();
            //   final Kegiatan kegiatan = new Kegiatan();
            //   skpd.setIdSkpd(SipkdHelpers.getIntFromString(request.getParameter("idskpd")));
            //  kegiatan.setIdKegiatan(SipkdHelpers.getIntFromString(request.getParameter("idKegiatan")));
            // npd.setSkpd(skpd);
            // npd.setKegiatan(kegiatan);
            npd.setTglNpd(request.getParameter("tanggalNpd"));
            npd.setKetNpd(request.getParameter("ketNpd"));
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            npd.setIdEntry(pengguna.getIdPengguna());
            npd.setTglEntry(new Timestamp(System.currentTimeMillis()));

            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final List<NpdRinci> listNpdRinci = new ArrayList<NpdRinci>(banyakrinci);
            //log.debug(" Map param  "+  request.getParameterMap().toString());
            for (int i = 0; i < banyakrinci; i++) {

                final String penanda = "cekpilih" + (i + 1);
                final String idakun = request.getParameter(penanda);
                //    log.debug(penanda+" idBl ################# " + idBl);
                if (idakun != null && !idakun.isEmpty()) {

                    final NpdRinci npdRinci = new NpdRinci();
                    final Akun akun = new Akun();
                    akun.setIdAkun(SipkdHelpers.getIntFromString(idakun));
                    npdRinci.setAkun(akun);
                    npdRinci.setNilaiNpd(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaiNpd" + idakun)));
                    npdRinci.setIdEntry(pengguna.getIdPengguna());
                    npdRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));
                    listNpdRinci.add(npdRinci);
                }
            }
            npd.setNpdRinci(listNpdRinci);
            npdServices.updateNpd(npd);
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil ditambahkan ")
                    .toString());

            return sburl.append(npd.getIdNpd()).toString();
        }
    }
    
     @RequestMapping(value = "/prosesdelete", method = RequestMethod.POST)
    public String deletenpd(@Valid @ModelAttribute("npd") Npd npd) {
        npdServices.deleteNpd(npd.getIdNpd());
        return "redirect:/npd/indexnpd/";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
