package sipkd.ui.action;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
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
import org.springframework.security.web.util.matcher.IpAddressMatcher;
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
import sipkd.services.SpdBiayaRevService;
import sipkd.util.BigDecimalPropertyEditor;
import sipkd.util.SipkdHelpers;
import sipkd.util.SqlDatePropertyEditor;

@Controller
@RequestMapping("/spd/pengajuanbiayarev")
public class SpdPengajuanBiayaRevAction {

    private static final Logger log = LoggerFactory.getLogger(SpdPengajuanBiayaRevAction.class);
    @Autowired
    SpdBiayaRevService spdBiayaRevService;

    @Autowired
    ReferensiServices referensiServices;

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/biaya/indexbiayarev", method = RequestMethod.GET)
    public String index(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("id", 3);
        mapData.put("tahun", tahunAnggaran);
        
        final Skpd skpd = referensiServices.getDetailSKpdByLevel(mapData);
        if (skpd != null) {
            request.setAttribute("idskpd", skpd.getIdSkpd());
            request.setAttribute("namaskpd", skpd.getNamaSkpd());
        }
        return "spd/pengajuanspd/indexbiayarev";

    }

    @RequestMapping(value = "/biaya/indexskpd/{id}", method = RequestMethod.GET)
    public String indexskpd(final HttpServletRequest request, @PathVariable Integer id) {
        final Skpd skpd = referensiServices.getDetailSKpd(id);
        if (skpd != null) {
            request.setAttribute("idskpd", skpd.getIdSkpd());
            request.setAttribute("namaskpd", skpd.getNamaSkpd());
        }
        return "spd/pengajuanspd/indexbiayarev";

    }

    @RequestMapping(value = "/json/getpagudansisabiaya/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, BigDecimal> getpagudansisa(@PathVariable Integer id, final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        return spdBiayaRevService.getPaguDanSisa(id, tahunAnggaran);
    }
    
    @RequestMapping(value = "/biaya/json/getlistspd", method = RequestMethod.GET)
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
        final long banyak = spdBiayaRevService.getBanyakAnggaranBtlSkpd(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdBiayaRevService.getAnggaranBtlSkpd(param));
        return mapData;

    }

    @RequestMapping(value = "/biaya/add", method = RequestMethod.GET)
    public ModelAndView spdbtladd(SpdBTLMaster spdBTLMaster, final HttpServletRequest request) {
        request.setAttribute("isadd", 1);
        spdBTLMaster.setTahunAnggaran((String) request.getSession().getAttribute("tahunAnggaran"));
        return new ModelAndView("spd/pengajuanspd/addpengajuanspdbiayarev", "spdBTLMaster", spdBTLMaster);

    }

    @RequestMapping(value = "/biaya/addskpd/{id}", method = RequestMethod.GET)
    public ModelAndView spdbtladdskpd(SpdBTLMaster spdBTLMaster, final HttpServletRequest request, @PathVariable Integer id) {
        request.setAttribute("isadd", 1);
        final Skpd skpd = referensiServices.getDetailSKpd(id);
        spdBTLMaster.setSkpd(skpd);
        spdBTLMaster.setTahunAnggaran((String) request.getSession().getAttribute("tahunAnggaran"));
        return new ModelAndView("spd/pengajuanspd/addpengajuanspdbiayarev", "spdBTLMaster", spdBTLMaster);

    }

    @RequestMapping(value = "/biaya/editrev/{id}", method = RequestMethod.GET)
    public ModelAndView spdbtledit(@PathVariable Integer id, final HttpServletRequest request) {
        request.setAttribute("isadd", request.getParameter("isadd"));
        return new ModelAndView("spd/pengajuanspd/addpengajuanspdbiayarev", "spdBTLMaster", spdBiayaRevService.getdataspdbtlmaster(id));

    }

    @RequestMapping(value = "/json/getanggarandanspd/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, BigDecimal> getanggarandanspd(@PathVariable Integer id, final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", id);
        return spdBiayaRevService.getTotalAnggaranDanSpd(param);
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
        return spdBiayaRevService.getListSPDDetailBySKPDDanTahun(param, 1);
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
        final int banyak = spdBiayaRevService.getBanyakTotalSPDBySKPDDanTahun(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdBiayaRevService.getListSPDDetailBySKPDDanTahun(param, (SipkdHelpers.equals(addoredit, "1") ? 1 : 2)));
        return mapData;
    }

    @RequestMapping(value = "/biaya/prosessimpan", method = RequestMethod.POST)
    public String prosessimpanspdbtl(@Valid @ModelAttribute("spdBTLMaster") SpdBTLMaster spdBTLMaster,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        spdBTLMaster.setTahunAnggaran((String) request.getSession().getAttribute("tahunAnggaran"));
        log.debug(" result " + result.getFieldError());
        StringBuilder sburl = new StringBuilder("redirect:/spd/pengajuanbiayarev/biaya/editrev/");
        if (result.hasErrors()) {
            return "spd/pengajuanspd/addpengajuanspdbiayarev";
        } else {
            spdBTLMaster.setTglSpd(new Date(System.currentTimeMillis()));
            spdBTLMaster.setBulanAwal(SipkdHelpers.splitString(spdBTLMaster.getBulanAwal(), "/", 0));
            spdBTLMaster.setBulanAkhir(SipkdHelpers.splitString(spdBTLMaster.getBulanAkhir(), "/", 0));
            if (spdBTLMaster.getIdSpd() != null && spdBTLMaster.getIdSpd().longValue() > 0) {
                spdBiayaRevService.updatespdmaster(spdBTLMaster);
                sburl.append(spdBTLMaster.getIdSpd());
            } else {
                spdBiayaRevService.insertspdmaster(spdBTLMaster);
                sburl.append(spdBTLMaster.getIdSpd()).append("?isadd=1");
            }
            log.debug(" ---> " + result.getFieldError());
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
            //    if (nilaiAnggSpd.compareTo(new BigDecimal(0)) > 0) {
                    log.debug(" ======== " + idspddetail + "  :  id spd " + nilaiSpdX);
                    final Map<String, Object> parammap = new HashMap<String, Object>(2);
                    parammap.put("idSpd", idspd);
                    parammap.put("idBtl", idBtl);
                    final boolean isupdate = spdBiayaRevService.getcekspddetailbyidspdandidbtl(parammap) > 0;
                    if (isupdate) {
                        spdBtlDetail.setIdEdit(pengguna.getIdPengguna());
                        spdBtlDetail.setTglEdit(new Timestamp(System.currentTimeMillis()));
                        spdBtlDetail.setIdSpdRinci(SipkdHelpers.getIntFromString(idspddetail));
                        spdBiayaRevService.updatenilaispddetail(spdBtlDetail);
                    } else {
                        spdBtlDetail.setIdEntry(pengguna.getIdPengguna());
                        spdBtlDetail.setTglEntry(new Timestamp(System.currentTimeMillis()));
                        spdBiayaRevService.insertspddetail(spdBtlDetail);
                    }
            /*    } else {
                    // spdBiayaService.hapusspdrincibtl(SipkdHelpers.getIntFromString(idspddetail));
                    final Map<String, Object> parammaphapus = new HashMap<String, Object>(2);
                    parammaphapus.put("idSpd", idspd);
                    parammaphapus.put("idAkun", spdBtlDetail.getIdAkun());
                    spdBiayaRevService.hapusspdrincibtlbyakundanspd(parammaphapus);
                }*/

            }
        }
        return "Data SPD Detail berhasil disimpan ";
    }

    @RequestMapping(value = "/biaya/cetak", method = RequestMethod.GET)
    public String cetak() {
        return "spd/pengajuanspd/cetak";

    }

    @RequestMapping(value = "/biaya/json/submitcetak", method = RequestMethod.POST)
    public @ResponseBody
    String submitcetak(@RequestBody List<Map<String, Object>> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String pathjasper = servletContext.getRealPath("/WEB-INF/report/");
        final String pathpdf = servletContext.getRealPath("/WEB-INF/pdf/");
        for (Map<String, Object> mapdetil : mapdata) {
            mapdetil.put("userid", pengguna.getIdPengguna());
            mapdetil.put("filejasper", pathjasper);
            mapdetil.put("filepdf", pathpdf);
            mapdetil.put("tglentry", new Timestamp(System.currentTimeMillis()));
            spdBiayaRevService.insertspdcetak(mapdetil);
        }

        return "simpan data cetak sukes";

    }

    @RequestMapping(value = "/json/hapusspddanrincibyspd",
            method = RequestMethod.POST)
    public @ResponseBody
    String hapusspddanrincibyspd(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        spdBiayaRevService.hapusspddanspdrincibtl((Integer) mapdata.get("idspd"));
        return " Data SPD No " + mapdata.get("nospd") + "  di   " + mapdata.get("skpd") + " berhasil dihapus ";
    }

    @RequestMapping(value = "/biaya/json/getlistspdcetak", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspdcetak(final HttpServletRequest request) {
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
        final long banyak = spdBiayaRevService.getbanyakcetakspdbtl(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdBiayaRevService.getlistcetakspdbtl(param));
        return mapData;

    }

    @RequestMapping(value = "/biaya/pdf/reportpengajuanspd/{id}", method = RequestMethod.GET)
    public void downloadPdfSpdBtl(HttpServletResponse response, @PathVariable Integer id) throws IOException {

        final Map uploadlog = spdBiayaRevService.getcetakspdbyidspd(id);
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

    @RequestMapping(value = "/biaya/json/getlistspdvalidasi", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspdvalidasi(final HttpServletRequest request) {
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
        final long banyak = spdBiayaRevService.getbanyakcetakspdbtl(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdBiayaRevService.getlistcetakspdbtl(param));
        return mapData;

    }

    @RequestMapping(value = "/biaya/validasi", method = RequestMethod.GET)
    public String validasi(final HttpServletResponse response, final HttpServletRequest request) throws IOException {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        boolean status = false;
        final String ipaddr = pengguna.getIpAddress();
        if (ipaddr != null && StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotBlank(ipaddr)) {
            status = new IpAddressMatcher(ipaddr).matches(request) || new IpAddressMatcher("127.0.0.1").matches(request);
        }
        log.debug(ipaddr + " status = " + status + " request " + request.getRemoteAddr());
        if (status) {
            return "spd/pengajuanspd/validasi";
        } else {
            // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Anda tidak berhak mengakses halaman pengesahan");
            return "deny";
        }

    }

    @RequestMapping(value = "/biaya/json/submitvalidasi", method = RequestMethod.POST)
    public @ResponseBody
    String submitvalidasi(@RequestBody List<String> listidspd, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        for (String idspd : listidspd) {
            //(#{idspd},#{userid},#{tglentry} ,#{tglentry})
            final Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("idspd", idspd);
            parameter.put("tglentry", new Timestamp(System.currentTimeMillis()));
            parameter.put("userid", pengguna.getIdPengguna());
            log.info(parameter.toString());
            spdBiayaRevService.insertspdsah(parameter);
        }

        return "Pengesahan sukes";

    }

    @RequestMapping(value = "/print/cetakpengesahan/{nospd}", method = RequestMethod.GET)
    public String cetakpengesahan(final HttpServletRequest request, @PathVariable String nospd, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        request.setAttribute("nospd", nospd);
        return "spd/pengajuanspd/cetakvalidasi";

    }

    @RequestMapping(value = "/print/cetakbarcodepengesahanbynospd/{nospd}", method = RequestMethod.GET)
    public void cetakpengesahan(final HttpServletResponse response, @PathVariable String nospd) throws WriterException, IOException {
        BitMatrix bitMatrix = new Code128Writer().encode(nospd, BarcodeFormat.CODE_128, 90, 40, null);
        MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());

    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}
