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
import sipkd.services.SpdRevService;
import sipkd.util.BigDecimalPropertyEditor;
import sipkd.util.SipkdHelpers;
import sipkd.util.SqlDatePropertyEditor;

@Controller
@RequestMapping("/spd/pengajuanrev")
public class SpdPengajuanBtlRevAction {

    private static final Logger log = LoggerFactory.getLogger(SpdPengajuanBtlRevAction.class);
    @Autowired
    SpdRevService spdRevService;

    @Autowired
    ReferensiServices referensiServices;

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/btl/indexbtlrev", method = RequestMethod.GET)
    public String index() {
        return "spd/pengajuanspd/indexbtlrev";

    }

    @RequestMapping(value = "/btl/indexskpd/{id}", method = RequestMethod.GET)
    public String indexskpd(final HttpServletRequest request, @PathVariable Integer id) {
        final Skpd skpd = referensiServices.getDetailSKpd(id);
        if (skpd != null) {
            request.setAttribute("idskpd", skpd.getIdSkpd());
            request.setAttribute("namaskpd", skpd.getKodeSkpd() + "/" + skpd.getNamaSkpd());

        }
        return "spd/pengajuanspd/indexbtlrev";

    }

    
    @RequestMapping(value = "/btl/json/getlistspdrev", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspdrev(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String id = request.getParameter("id");
        log.debug("TEEEEEEEE  = " + id);
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
        final long banyak = spdRevService.getBanyakAnggaranBtlSkpdRev(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdRevService.getAnggaranBtlSkpdRev(param));
        return mapData;

    }
    
    @RequestMapping(value = "/btl/add", method = RequestMethod.GET)
    public ModelAndView spdbtladd(SpdBTLMaster spdBTLMaster, final HttpServletRequest request) {
        request.setAttribute("isadd", 1);
        spdBTLMaster.setTahunAnggaran((String) request.getSession().getAttribute("tahunAnggaran"));
        return new ModelAndView("spd/pengajuanspd/addpengajuanspdbtl", "spdBTLMaster", spdBTLMaster);

    }
    
    @RequestMapping(value = "/btl/addrev", method = RequestMethod.GET)
    public ModelAndView spdbtladdrev(SpdBTLMaster spdBTLMaster, final HttpServletRequest request) {
        request.setAttribute("isadd", 1);
        spdBTLMaster.setTahunAnggaran((String) request.getSession().getAttribute("tahunAnggaran"));
        return new ModelAndView("spd/pengajuanspd/addpengajuanspdbtlrev", "spdBTLMaster", spdBTLMaster);

    }

    @RequestMapping(value = "/btl/addskpd/{id}", method = RequestMethod.GET)
    public ModelAndView spdbtladdskpd(SpdBTLMaster spdBTLMaster, final HttpServletRequest request, @PathVariable Integer id) {
        request.setAttribute("isadd", 1);
        final Skpd skpd = referensiServices.getDetailSKpd(id);
        spdBTLMaster.setSkpd(skpd);
        spdBTLMaster.setTahunAnggaran((String) request.getSession().getAttribute("tahunAnggaran"));
        return new ModelAndView("spd/pengajuanspd/addpengajuanspdbtl", "spdBTLMaster", spdBTLMaster);

    }
    
     @RequestMapping(value = "/btl/addskpdrev/{id}", method = RequestMethod.GET)
    public ModelAndView spdbtladdskpdrev(SpdBTLMaster spdBTLMaster, final HttpServletRequest request, @PathVariable Integer id) {
        request.setAttribute("isadd", 1);
        final Skpd skpd = referensiServices.getDetailSKpd(id);
        spdBTLMaster.setSkpd(skpd);
        spdBTLMaster.setTahunAnggaran((String) request.getSession().getAttribute("tahunAnggaran"));
        return new ModelAndView("spd/pengajuanspd/addpengajuanspdbtl", "spdBTLMaster", spdBTLMaster);

    }

    @RequestMapping(value = "/btl/edit/{id}", method = RequestMethod.GET)
    public ModelAndView spdbtledit(@PathVariable Integer id, final HttpServletRequest request) {
        request.setAttribute("isadd", request.getParameter("isadd"));         
        return new ModelAndView("spd/pengajuanspd/addpengajuanspdbtl", "spdBTLMaster", spdRevService.getdataspdbtlmaster(id));

    }
    
     @RequestMapping(value = "/btl/editrev/{id}", method = RequestMethod.GET)
    public ModelAndView spdbtleditrev(@PathVariable Integer id, final HttpServletRequest request) {
        request.setAttribute("isadd", request.getParameter("isadd"));         
        return new ModelAndView("spd/pengajuanspd/addpengajuanspdbtlrev", "spdBTLMaster", spdRevService.getdataspdbtlmaster(id));

    }

    @RequestMapping(value = "/json/getanggarandanspd/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, BigDecimal> getanggarandanspd(@PathVariable Integer id, final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", id);
        return spdRevService.getTotalAnggaranDanSpd(param);
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
        return spdRevService.getListSPDDetailBySKPDDanTahun(param, 1);
    }

    @RequestMapping(value = "/json/getlistspddetailbyskpddantahunrev", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspddetailbyskpddantahunrev(final HttpServletRequest request) {

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
        final long banyak = spdRevService.getBanyakTotalSPDBySKPDDanTahun(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdRevService.getListSPDDetailBySKPDDanTahunRev(param, (SipkdHelpers.equals(addoredit, "1") ? 1 : 2)));
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
                spdRevService.updatespdmaster(spdBTLMaster);
                sburl.append(spdBTLMaster.getIdSpd());
            } else {
                spdRevService.insertspdmaster(spdBTLMaster);
                sburl.append(spdBTLMaster.getIdSpd()).append("?isadd=1");
            }
            log.info(" ---> " + result.getFieldError());
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil ditambahkan ")
                    .toString());

            return sburl.toString();
        }
    }
    
    
     @RequestMapping(value = "/btl/prosessimpanrev", method = RequestMethod.POST)
    public String prosessimpanspdbtlrev(@Valid @ModelAttribute("spdBTLMaster") SpdBTLMaster spdBTLMaster,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        spdBTLMaster.setTahunAnggaran((String) request.getSession().getAttribute("tahunAnggaran"));
        log.debug(" result " + result.getFieldError());
        StringBuilder sburl = new StringBuilder("redirect:/spd/pengajuanrev/btl/editrev/");
        if (result.hasErrors()) {
            return "spd/pengajuanspd/addpengajuanspdbtlrev";
        } else {
            spdBTLMaster.setTglSpd(new Date(System.currentTimeMillis()));
            spdBTLMaster.setBulanAwal(SipkdHelpers.splitString(spdBTLMaster.getBulanAwal(), "/", 0));
            spdBTLMaster.setBulanAkhir(SipkdHelpers.splitString(spdBTLMaster.getBulanAkhir(), "/", 0));
            if (spdBTLMaster.getIdSpd() != null && spdBTLMaster.getIdSpd().longValue() > 0) {
                spdRevService.updatespdmaster(spdBTLMaster);
                sburl.append(spdBTLMaster.getIdSpd());
            } else {
                spdRevService.insertspdmaster(spdBTLMaster);
                sburl.append(spdBTLMaster.getIdSpd()).append("?isadd=1");
            }
            log.info(" ---> " + result.getFieldError());
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil ditambahkan ")
                    .toString());

            return sburl.toString();
        }
    }

    @RequestMapping(value = "/json/addspddetailrev",
            method = RequestMethod.POST)
    public @ResponseBody
    String addSpdDetailrev(@RequestBody Map mapdata, final HttpServletRequest request) {

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
        
        log.debug("************** addSpdDetailrev listNomorSpd = "+listNomorSpd.toString());
        
        for (String nospdakun : listNomorSpd) {
            
            log.debug("************** addSpdDetailrev LOOPING  nospdakun = "+nospdakun);
            final String nilaiSpdX = (String) mapdata.get("nilaiSpdX" + nospdakun);
            log.debug("************** addSpdDetailrev LOOPING  nilaiSpdX = "+nilaiSpdX);
            
            if (nilaiSpdX != null && !nilaiSpdX.isEmpty()) {
                log.debug("************** addSpdDetailrev Masuk IF "+SipkdHelpers.getBigDecimalFromString((String) mapdata.get("nilaiSpdX" + nospdakun)) + " = " + mapdata.get("nilaiSpdX" + nospdakun));
                final BigDecimal nilaiAnggSpd = SipkdHelpers.getBigDecimalFromString((String) mapdata.get("nilaiSpdX" + nospdakun));
                final SpdBtlDetail spdBtlDetail = new SpdBtlDetail();
                final String idBtl = (String) mapdata.get("idbtlX" + nospdakun);
                spdBtlDetail.setIdSpd(idspd);
                spdBtlDetail.setIdBtl(SipkdHelpers.getIntFromString(idBtl));
                spdBtlDetail.setNilaiAnggaranSPDCurrent(nilaiAnggSpd);
                spdBtlDetail.setIdAkun(SipkdHelpers.getIntFromString(nospdakun));
                log.info("===> "+nospdakun);
                final String idspddetail = (String) mapdata.get("idspddetailX" + nospdakun);
              //  if (nilaiAnggSpd.compareTo(new BigDecimal(0)) > 0) {
                    log.info(" ======== " + idspddetail + "  :  id spd " + nilaiSpdX);
                    final Map<String, Object> parammap = new HashMap<String, Object>(2);
                    parammap.put("idSpd", idspd);
                    parammap.put("idBtl", idBtl);
                    final boolean isupdate = spdRevService.getcekspddetailbyidspdandidbtl(parammap) > 0;
                    if (isupdate) {
                        try {
                             spdBtlDetail.setIdEdit(pengguna.getIdPengguna());
                        } catch (Exception e) {
                             spdBtlDetail.setIdEdit(0);
                        }
                        spdBtlDetail.setTglEdit(new Timestamp(System.currentTimeMillis()));
                        spdBtlDetail.setIdSpdRinci(SipkdHelpers.getIntFromString(idspddetail));
                        spdRevService.updatenilaispddetail(spdBtlDetail);
                    } else {
                        try {
                             spdBtlDetail.setIdEdit(pengguna.getIdPengguna());
                             spdBtlDetail.setIdEntry(pengguna.getIdPengguna());
                        } catch (Exception e) {
                             spdBtlDetail.setIdEdit(0);
                             spdBtlDetail.setIdEntry(0);
                        }
                        spdBtlDetail.setTglEntry(new Timestamp(System.currentTimeMillis()));
                        spdRevService.insertspddetail(spdBtlDetail);
                    } 
             /*   } else {
                    // spdService.hapusspdrincibtl(SipkdHelpers.getIntFromString(idspddetail));
                    final Map<String, Object> parammaphapus = new HashMap<String, Object>(2);
                    parammaphapus.put("idSpd", idspd);
                    parammaphapus.put("idAkun", spdBtlDetail.getIdAkun());
                    spdRevService.hapusspdrincibtlbyakundanspd(parammaphapus);
                }*/

            }
            
        }
        return "Data SPD Detail berhasil disimpan ";
    }

    @RequestMapping(value = "/json/hapusspddanrincibyspd",
            method = RequestMethod.POST)
    public @ResponseBody
    String hapusspddanrincibyspd(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        spdRevService.hapusspddanspdrincibtl((Integer) mapdata.get("idspd"));
        return "Data SPD No " + mapdata.get("nospd") + "  di   " + mapdata.get("skpd") + " berhasil dihapus ";
    }

    @RequestMapping(value = "/btl/pdf/reportpengajuanspd/{id}", method = RequestMethod.GET)
    public void downloadPdfSpdBtl(HttpServletResponse response, @PathVariable Integer id) throws IOException {

        final Map uploadlog = spdRevService.getcetakspdbyidspd(id);
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
