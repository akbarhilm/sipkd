package sipkd.ui.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
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
import sipkd.model.Pengguna;
import sipkd.model.Skpd;
import sipkd.model.SpdBTLMaster;
import sipkd.model.SpdBtlDetail;
import sipkd.services.ReferensiServices;
import sipkd.services.SpdService;
import sipkd.util.BigDecimalPropertyEditor;
import sipkd.util.SipkdHelpers;
import sipkd.util.SqlDatePropertyEditor;

@Controller
@RequestMapping("/spd/pengajuan")
public class SpdPengajuanBtlAction {

    private static final Logger log = LoggerFactory.getLogger(SpdPengajuanBtlAction.class);
    @Autowired
    SpdService spdService;

    @Autowired
    ReferensiServices referensiServices;

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/btl/index", method = RequestMethod.GET)
    public String index() {
        return "spd/pengajuanspd/index";

    }

    @RequestMapping(value = "/btl/indexskpd/{id}", method = RequestMethod.GET)
    public String indexskpd(final HttpServletRequest request, @PathVariable Integer id) {
        final Skpd skpd = referensiServices.getDetailSKpd(id);
        if (skpd != null) {
            request.setAttribute("idskpd", skpd.getIdSkpd());
            request.setAttribute("namaskpd", skpd.getKodeSkpd() + "/" + skpd.getNamaSkpd());

        }
        return "spd/pengajuanspd/index";

    }

    @RequestMapping(value = "/btl/json/getlistspd", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspd(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String id = request.getParameter("id");
        log.debug("id  = " + id);
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", id);
        log.debug(param.toString());
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = spdService.getBanyakAnggaranBtlSkpd(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdService.getAnggaranBtlSkpd(param));
        return mapData;

    }

    @RequestMapping(value = "/btl/add", method = RequestMethod.GET)
    public ModelAndView spdbtladd(SpdBTLMaster spdBTLMaster, final HttpServletRequest request) {
        request.setAttribute("isadd", 1);
        spdBTLMaster.setTahunAnggaran((String) request.getSession().getAttribute("tahunAnggaran"));
        return new ModelAndView("spd/pengajuanspd/addpengajuanspdbtl", "spdBTLMaster", spdBTLMaster);

    }

    @RequestMapping(value = "/btl/addskpd/{id}", method = RequestMethod.GET)
    public ModelAndView spdbtladdskpd(SpdBTLMaster spdBTLMaster, final HttpServletRequest request, @PathVariable Integer id) {
        request.setAttribute("isadd", 1);
        final Skpd skpd = referensiServices.getDetailSKpd(id);
        spdBTLMaster.setSkpd(skpd);
        spdBTLMaster.setTahunAnggaran((String) request.getSession().getAttribute("tahunAnggaran"));
        return new ModelAndView("spd/pengajuanspd/addpengajuanspdbtl", "spdBTLMaster", spdBTLMaster);

    }

    @RequestMapping(value = "/btl/edit/{id}", method = RequestMethod.GET)
    public ModelAndView spdbtledit(@PathVariable Integer id, final HttpServletRequest request) {
        request.setAttribute("isadd", request.getParameter("isadd"));
        return new ModelAndView("spd/pengajuanspd/addpengajuanspdbtl", "spdBTLMaster", spdService.getdataspdbtlmaster(id));

    }

    @RequestMapping(value = "/json/getanggarandanspd/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, BigDecimal> getanggarandanspd(@PathVariable Integer id, final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", id);
        return spdService.getTotalAnggaranDanSpd(param);
    }

    @RequestMapping(value = "/json/getspddetaillist/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<SpdBtlDetail> getspddetaillist(@PathVariable Integer id, final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", id);
        param.put("idspd", 9999);
        log.debug(" param=======######## " + param.toString());
        return spdService.getListSPDDetailBySKPDDanTahun(param, 1);
    }

    @RequestMapping(value = "/json/getlistspddetailbyskpddantahun", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspddetailbyskpddantahun(final HttpServletRequest request) {

        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Integer idskpd = SipkdHelpers.getIntFromString(request.getParameter("idskpd"));
        final Integer idspd = SipkdHelpers.getIntFromString(request.getParameter("idspd"));
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
        param.put("idskpd", idskpd);
        final Integer paridspd = (idspd == 0 ? 99999 : idspd);
        param.put("idspd", paridspd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = spdService.getBanyakTotalSPDBySKPDDanTahun(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdService.getListSPDDetailBySKPDDanTahun(param, (SipkdHelpers.equals(addoredit, "1") ? 1 : 2)));
        return mapData;
    }

    @RequestMapping(value = "/btl/prosessimpan", method = RequestMethod.POST)
    public String prosessimpanspdbtl(@Valid @ModelAttribute("spdBTLMaster") SpdBTLMaster spdBTLMaster,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        spdBTLMaster.setTahunAnggaran((String) request.getSession().getAttribute("tahunAnggaran"));
        log.debug(" result " + result.getFieldError());
        StringBuilder sburl = new StringBuilder("redirect:/spd/pengajuan/btl/edit/");
        if (result.hasErrors()) {
            return "spd/pengajuanspd/addpengajuanspdbtl";
        } else {
            spdBTLMaster.setTglSpd(new Date(System.currentTimeMillis()));
            spdBTLMaster.setBulanAwal(SipkdHelpers.splitString(spdBTLMaster.getBulanAwal(), "/", 0));
            spdBTLMaster.setBulanAkhir(SipkdHelpers.splitString(spdBTLMaster.getBulanAkhir(), "/", 0));
            if (spdBTLMaster.getIdSpd() != null && spdBTLMaster.getIdSpd().longValue() > 0) {
                spdService.updatespdmaster(spdBTLMaster);
                sburl.append(spdBTLMaster.getIdSpd());
            } else {
                spdService.insertspdmaster(spdBTLMaster);
                sburl.append(spdBTLMaster.getIdSpd()).append("?isadd=1");
            }
            log.info(" ---> " + result.getFieldError());
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil ditambahkan ")
                    .toString());

            return sburl.toString();
        }
    }

    @RequestMapping(value = "/json/addspddetail",
            method = RequestMethod.POST)
    public @ResponseBody
    String addSpdDetail(@RequestBody Map mapdata, final HttpServletRequest request) {

        final Integer idspd = SipkdHelpers.getIntFromString(mapdata.get("nospdform").toString());

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<String> listNomorSpd = new ArrayList<String>();
        for (Object key : mapdata.keySet()) {

            String[] jenis = StringUtils.split(key.toString(), "X");
            if (jenis != null && jenis.length > 1) {
                if (jenis[0].equals("nilaiSpd")) {
                    listNomorSpd.add(jenis[1]);
                }
            }
        }

        for (String nospdakun : listNomorSpd) {
            final String nilaiSpdX = (String) mapdata.get("nilaiSpdX" + nospdakun);
            if (nilaiSpdX != null && !nilaiSpdX.isEmpty()) {
                log.debug(SipkdHelpers.getBigDecimalFromString((String) mapdata.get("nilaiSpdX" + nospdakun)) + " = " + mapdata.get("nilaiSpdX" + nospdakun));
                final BigDecimal nilaiAnggSpd = SipkdHelpers.getBigDecimalFromString((String) mapdata.get("nilaiSpdX" + nospdakun));
                final SpdBtlDetail spdBtlDetail = new SpdBtlDetail();
                final String idBtl = (String) mapdata.get("idbtlX" + nospdakun);
                spdBtlDetail.setIdSpd(idspd);
                spdBtlDetail.setIdBtl(SipkdHelpers.getIntFromString(idBtl));
                spdBtlDetail.setNilaiAnggaranSPDCurrent(nilaiAnggSpd);
                spdBtlDetail.setIdAkun(SipkdHelpers.getIntFromString(nospdakun));
                final String idspddetail = (String) mapdata.get("idspddetailX" + nospdakun);
                if (nilaiAnggSpd.compareTo(new BigDecimal(0)) > 0) {
                    log.info(" ======== " + idspddetail + "  :  id spd " + nilaiSpdX);
                    final Map<String, Object> parammap = new HashMap<String, Object>(2);
                    parammap.put("idSpd", idspd);
                    parammap.put("idBtl", idBtl);
                    final boolean isupdate = spdService.getcekspddetailbyidspdandidbtl(parammap) > 0;
                    int idpengguna = 0;
                    try {
                        idpengguna = pengguna.getIdPengguna();
                    } catch (Exception e) {
                        idpengguna = 0;
                    }
                    if (isupdate) {

                        spdBtlDetail.setIdEdit(idpengguna);
                        spdBtlDetail.setTglEdit(new Timestamp(System.currentTimeMillis()));
                        spdBtlDetail.setIdSpdRinci(SipkdHelpers.getIntFromString(idspddetail));
                        spdService.updatenilaispddetail(spdBtlDetail);
                    } else {
                        spdBtlDetail.setIdEntry(idpengguna);
                        spdBtlDetail.setTglEntry(new Timestamp(System.currentTimeMillis()));
                        spdService.insertspddetail(spdBtlDetail);
                    }
                } else {
                    // spdService.hapusspdrincibtl(SipkdHelpers.getIntFromString(idspddetail));
                    final Map<String, Object> parammaphapus = new HashMap<String, Object>(2);
                    parammaphapus.put("idSpd", idspd);
                    parammaphapus.put("idBl", spdBtlDetail.getIdBtl());
                    parammaphapus.put("idAkun", spdBtlDetail.getIdAkun());
                    spdService.hapusspdrincibtlbyakundanspd(parammaphapus);
                }

            }
        }
        return "Data SPD Detail berhasil disimpan ";
    }

    @RequestMapping(value = "/json/hapusspddanrincibyspd",
            method = RequestMethod.POST)
    public @ResponseBody
    String hapusspddanrincibyspd(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        spdService.hapusspddanspdrincibtl((Integer) mapdata.get("idspd"));
        return "Data SPD No " + mapdata.get("nospd") + "  di   " + mapdata.get("skpd") + " berhasil dihapus ";
    }

    @RequestMapping(value = "/btl/pdf/reportpengajuanspd/{id}", method = RequestMethod.GET)
    public void downloadPdfSpdBtl(HttpServletResponse response, @PathVariable Integer id) throws IOException {

        final Map uploadlog = spdService.getcetakspdbyidspd(id);
        final String alamatPdf = (String) uploadlog.get("ALAMATPDF");
        final File f = new File(alamatPdf);
        if (f.exists()) {
            response.setContentType("application/x-pdf");
            response.setHeader("Content-Disposition", "attachment;filename=" + f.getName());
            response.setHeader("Cache-Control", "cache, must-revalidate");
            response.setHeader("Pragma", "public");
            FileCopyUtils.copy(new FileInputStream(f), response.getOutputStream());
        } else {
            response.getWriter().println("PDF tidak tersedia ");
        }

    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}
