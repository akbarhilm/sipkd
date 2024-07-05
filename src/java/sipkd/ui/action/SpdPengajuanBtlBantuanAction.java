package sipkd.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
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
import sipkd.model.Pengguna;
import sipkd.model.Skpd;
import sipkd.model.SpdBTLMaster;
import sipkd.model.SpdBtlDetail;
import sipkd.services.ReferensiServices;
import sipkd.services.SpdBtlBantuanServices;
import sipkd.util.BigDecimalPropertyEditor;
import sipkd.util.SipkdHelpers;
import sipkd.util.SqlDatePropertyEditor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/spd/pengajuanbtlbantuan")
public class SpdPengajuanBtlBantuanAction {

    private static final Logger log = LoggerFactory.getLogger(SpdPengajuanBtlBantuanAction.class);

    @Autowired
    SpdBtlBantuanServices spdBtlBantuanServices;

    @Autowired
    ReferensiServices referensiServices;

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/btlbantuan/index", method = RequestMethod.GET)
    public String index(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("id", 3);
        mapData.put("tahun", tahunAnggaran);
        
        final Skpd skpd = referensiServices.getDetailSKpdByLevel(mapData);
        if (skpd != null) {
            request.setAttribute("idskpd", skpd.getIdSkpd());
            request.setAttribute("namaskpd", skpd.getNamaSkpd());
            request.setAttribute("kodeskpd", skpd.getKodeSkpd());
        }
        return "spd/pengajuanspd/indexbtlbantuan";

    }

    @RequestMapping(value = "/json/getpagudansisabtlbantuan/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, BigDecimal> getpagudansisa(@PathVariable Integer id, final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        return spdBtlBantuanServices.getPaguDanSisaBtlBantuan(id, tahunAnggaran);
    }

    @RequestMapping(value = "/btlbantuan/json/getlistspd", method = RequestMethod.GET)
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
        final long banyak = spdBtlBantuanServices.getBanyakAnggaranBtlBantuanSkpd(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdBtlBantuanServices.getAnggaranBtlBantuanSkpd(param));
        return mapData;

    }

    @RequestMapping(value = "/btlbantuan/addpengajuanspdbtlbantuan", method = RequestMethod.GET)
    public ModelAndView spdbtlbantuanadd(SpdBTLMaster spdBTLMaster, final HttpServletRequest request) {
        request.setAttribute("isadd", 1);
        spdBTLMaster.setTahunAnggaran((String) request.getSession().getAttribute("tahunAnggaran"));
        return new ModelAndView("spd/pengajuanspd/addpengajuanspdbtlbantuan", "spdBTLMaster", spdBTLMaster);

    }

    @RequestMapping(value = "/btlbantuan/addpengajuanspdbtlbantuanskpd/{id}", method = RequestMethod.GET)
    public ModelAndView spdbtladdskpd(SpdBTLMaster spdBTLMaster, final HttpServletRequest request, @PathVariable Integer id) {
        request.setAttribute("isadd", 1);
        final Skpd skpd = referensiServices.getDetailSKpd(id);
        spdBTLMaster.setSkpd(skpd);
        spdBTLMaster.setTahunAnggaran((String) request.getSession().getAttribute("tahunAnggaran"));
        request.setAttribute("idskpdinduk", id);
        request.setAttribute("namaskpdinduk", skpd.getNamaSkpd());
        return new ModelAndView("spd/pengajuanspd/addpengajuanspdbtlbantuan", "spdBTLMaster", spdBTLMaster);

    }

    @RequestMapping(value = "/json/getlistspddetailbyskpddantahun", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspddetailbyskpddantahun(final HttpServletRequest request) {
        final String kodekegiatanfilter = (String) request.getParameter("kodekegiatanfilter");
        final String namakegiatanfilter = (String) request.getParameter("namakegiatanfilter");

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

        param.put("kodekegiatan", kodekegiatanfilter);
        param.put("namakegiatan", namakegiatanfilter);
        log.info(param.toString());
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
          final long banyak = spdBtlBantuanServices.getBanyakTotalSPDBySKPDDanTahun(param);
         log.info("banyak : "+banyak);
      
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdBtlBantuanServices.getListSPDDetailBySKPDDanTahun(param, (SipkdHelpers.equals(addoredit, "1") ? 1 : 2)));
        return mapData;
    }

    @RequestMapping(value = "/json/getanggarandanspd/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, BigDecimal> getanggarandanspd(@PathVariable Integer id, final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", id);
        return spdBtlBantuanServices.getTotalAnggaranDanSpd(param);
    }

    @RequestMapping(value = "/btlbantuan/prosessimpan", method = RequestMethod.POST)
    public String prosessimpanspdbtl(@Valid @ModelAttribute("spdBTLMaster") SpdBTLMaster spdBTLMaster,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        spdBTLMaster.setTahunAnggaran((String) request.getSession().getAttribute("tahunAnggaran"));
        log.debug(" result " + result.getFieldError());
        Skpd skpdKoord = spdBTLMaster.getSkpdKordinator();
        StringBuilder sburl = new StringBuilder("redirect:/spd/pengajuanbtlbantuan/btlbantuan/edit/").append(skpdKoord.getIdSkpd()).append("/");
        if (result.hasErrors()) {
            return "spd/pengajuanspd/addpengajuanspdbtlbantuan";
        } else {
            spdBTLMaster.setTglSpd(new Date(System.currentTimeMillis()));
            spdBTLMaster.setBulanAwal(SipkdHelpers.splitString(spdBTLMaster.getBulanAwal(), "/", 0));
            spdBTLMaster.setBulanAkhir(SipkdHelpers.splitString(spdBTLMaster.getBulanAkhir(), "/", 0));
            if (spdBTLMaster.getIdSpd() != null && spdBTLMaster.getIdSpd().longValue() > 0) {
                spdBtlBantuanServices.updatespdmaster(spdBTLMaster);
                sburl.append(spdBTLMaster.getIdSpd());
            } else {
                spdBtlBantuanServices.insertspdmaster(spdBTLMaster);
                sburl.append(spdBTLMaster.getIdSpd()).append("?isadd=1");
            }
            log.info(" ---> " + result.getFieldError());
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil ditambahkan ")
                    .toString());

            return sburl.toString();
        }
    }

    @RequestMapping(value = "/btlbantuan/edit/{idskpdkoord}/{id}", method = RequestMethod.GET)
    public ModelAndView spdbtledit(@PathVariable Integer idskpdkoord, @PathVariable Integer id, final HttpServletRequest request) {
        request.setAttribute("isadd", request.getParameter("isadd"));
        SpdBTLMaster spdBtlMaster = spdBtlBantuanServices.getdataspdbtlbatuanmaster(id);
        spdBtlMaster.setSkpdKordinator(spdBtlBantuanServices.getAllSkpdById(idskpdkoord));
        //log.info(" spdBtlMaster = " + spdBtlMaster.getSkpdKordinator().getNamaSkpd()); // edit 14 Jan 16 by zainab, kalo hasilnya null jadi error
        return new ModelAndView("spd/pengajuanspd/addpengajuanspdbtlbantuan", "spdBTLMaster", spdBtlMaster);

    }

    @RequestMapping(value = "/json/addspddetail",
            method = RequestMethod.POST)
    public @ResponseBody
    String addSpdDetail(@RequestBody Map mapdata, final HttpServletRequest request) {

        final Integer idspd = SipkdHelpers.getIntFromString(mapdata.get("nospdform").toString());
        final String idSkpdKoord = mapdata.get("idskpdkoordinator").toString();
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
        log.info(idSkpdKoord + " mapdata " + mapdata.toString());
        for (String nospdbtl : listNomorSpd) {
            final String nilaiSpdX = (String) mapdata.get("nilaiSpdX" + nospdbtl);
            final String idAkunX = (String) mapdata.get("idakundetailX" + nospdbtl);
            if (nilaiSpdX != null && !nilaiSpdX.isEmpty()) {
                log.debug(SipkdHelpers.getBigDecimalFromString((String) mapdata.get("nilaiSpdX" + nospdbtl)) + " = " + mapdata.get("nilaiSpdX" + nospdbtl));
                final BigDecimal nilaiAnggSpd = SipkdHelpers.getBigDecimalFromString((String) mapdata.get("nilaiSpdX" + nospdbtl));
                final SpdBtlDetail spdBtlDetail = new SpdBtlDetail();
                final String idBtl = nospdbtl;// (String) mapdata.get("idbtlX" + nospdbtl);
                spdBtlDetail.setIdSpd(idspd);
                spdBtlDetail.setIdSkpdKoord(SipkdHelpers.getIntFromString(idSkpdKoord));
                spdBtlDetail.setIdBtl(SipkdHelpers.getIntFromString(idBtl));
                spdBtlDetail.setNilaiAnggaranSPDCurrent(nilaiAnggSpd);
                spdBtlDetail.setIdAkun(SipkdHelpers.getIntFromString(idAkunX));
                final String idspddetail = (String) mapdata.get("idspddetailX" + nospdbtl);
                if (nilaiAnggSpd.compareTo(new BigDecimal(0)) > 0) {
                    log.debug(" ======== " + idspddetail + "  :  id spd " + nilaiSpdX);
                    final Map<String, Object> parammap = new HashMap<String, Object>(2);
                    parammap.put("idSpd", idspd);
                    parammap.put("idBtl", idBtl);
                    final boolean isupdate = spdBtlBantuanServices.getcekspddetailbyidspdandidbtl(parammap) > 0;
                    int idpengguna = 0;
                    try {
                        idpengguna =pengguna.getIdPengguna();
                    } catch (Exception e) {
                    }
                    if (isupdate) {
                        spdBtlDetail.setIdEdit(idpengguna );
                        spdBtlDetail.setTglEdit(new Timestamp(System.currentTimeMillis()));
                        spdBtlDetail.setIdSpdRinci(SipkdHelpers.getIntFromString(idspddetail));
                        spdBtlBantuanServices.updatenilaispddetail(spdBtlDetail);
                    } else {
                        spdBtlDetail.setIdEntry(idpengguna);
                        spdBtlDetail.setTglEntry(new Timestamp(System.currentTimeMillis()));
                        spdBtlBantuanServices.insertspddetail(spdBtlDetail);
                    }
                } else {
                    // spdBiayaService.hapusspdrincibtl(SipkdHelpers.getIntFromString(idspddetail));
                    final Map<String, Object> parammaphapus = new HashMap<String, Object>(2);
                    parammaphapus.put("idSpd", idspd);
                    parammaphapus.put("idBtl", idBtl);
                    spdBtlBantuanServices.hapusspdrincibtlbyakundanspd(parammaphapus);
                }

            }
        }
        return "Data SPD Detail berhasil disimpan ";
    }

    @RequestMapping(value = "/json/hapusspddanrincibyspd",
            method = RequestMethod.POST)
    public @ResponseBody
    String hapusspddanrincibyspd(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        spdBtlBantuanServices.hapusspddanspdrincibtl((Integer) mapdata.get("idspd"));
        return " Data SPD No " + mapdata.get("nospd") + "  di   " + mapdata.get("skpd") + " berhasil dihapus ";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
