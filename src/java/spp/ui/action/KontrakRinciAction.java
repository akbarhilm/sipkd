/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import spp.model.KontrakRinci;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.services.KontrakRinciServices;
import spp.util.BigDecimalPropertyEditor;
import spp.util.SipkdHelpers;
import spp.util.SqlDatePropertyEditor;

/**
 *
 * @author zainab
 */
@Controller
@RequestMapping("/kontrakrinci")
public class KontrakRinciAction {

    private static final Logger log = LoggerFactory.getLogger(KontrakRinciAction.class);
    @Autowired
    KontrakRinciServices kontrakServices;

    @RequestMapping(value = "/addkontrakrinci", method = RequestMethod.GET)
    public ModelAndView addkontrakrinci(final HttpServletResponse response, final KontrakRinci kontrak, final HttpServletRequest request, Model model) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());

            kontrak.setIdSkpd(listSkpd.get(0).getIdSkpd());
        }

        final Integer idskpd = kontrak.getIdSkpd();
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        final Map< String, Object> param = new HashMap<String, Object>(6);

        param.put("idskpd", idskpd);
        param.put("tahun", tahun);

        return new ModelAndView("common/kontrakrinci/addkontrakrinci", "progcmd", kontrak);
    }

    @RequestMapping(value = "/json/listakun", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listbastjson(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("kodeskpd");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idspd = request.getParameter("idspd");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String idkontrak = request.getParameter("idkontrak");
        final String idskpd = request.getParameter("idskpd");

        param.put("idskpd", idskpd);
        param.put("tahun", tahunAnggaran);
        param.put("idspd", idspd);
        param.put("idkegiatan", idkegiatan);
        param.put("idkontrak", idkontrak);

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("kodeskpd", skpd);
        param.put("tahun", tahunAnggaran);
        final Map<String, Object> mapData = new HashMap<String, Object>(6);
        final long banyak = kontrakServices.getBanyakAkun(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", kontrakServices.getAkun(param));
        return mapData;
    }

    @RequestMapping(value = "/json/prosessimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<KontrakRinci> listRinci = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");
        final String idkegiatan = (String) mapdata.get("idkegiatan");
        final String idkontrak = (String) mapdata.get("idkontrak");

        for (Map<String, Object> mapnilailist : nilailist) {
            KontrakRinci bukukas = new KontrakRinci();

            Object penanda = mapnilailist.get("penanda");
            Integer valid = SipkdHelpers.getIntFromString(penanda.toString());

            if (valid == 1) {
                bukukas.setTahun(tahun);
                bukukas.setIdSkpd(SipkdHelpers.getIntFromString(idskpd.toString()));
                bukukas.setIdEntry(pengguna.getIdPengguna());
                bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan));
                bukukas.setIdKontrak(SipkdHelpers.getIntFromString(idkontrak));

                Object nilairinci = mapnilailist.get("nilairinci");
                Object nilaiumk = mapnilailist.get("nilaiumk");
                Object idbas = mapnilailist.get("idbas");

                bukukas.setNilaiKontrak(SipkdHelpers.getBigDecimalFromString(nilairinci.toString()));
                bukukas.setNilaiUmk(SipkdHelpers.getBigDecimalFromString(nilaiumk.toString()));
                bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));

                listRinci.add(bukukas);
            }
        }

        kontrakServices.insertKontrakRinci(listRinci);

        return "Data Kontrak Rinci Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosesupdate", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdate(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<KontrakRinci> listRinci = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");
        final String idkegiatan = (String) mapdata.get("idkegiatan");
        final String idkontrak = (String) mapdata.get("idkontrak");

        for (Map<String, Object> mapnilailist : nilailist) {
            KontrakRinci bukukas = new KontrakRinci();

            Object penanda = mapnilailist.get("penanda");
            Integer valid = SipkdHelpers.getIntFromString(penanda.toString());

            if (valid == 1) {
                bukukas.setTahun(tahun);
                bukukas.setIdSkpd(SipkdHelpers.getIntFromString(idskpd.toString()));
                bukukas.setIdEntry(pengguna.getIdPengguna());
                bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan));
                bukukas.setIdKontrak(SipkdHelpers.getIntFromString(idkontrak));

                Object nilairinci = mapnilailist.get("nilairinci");
                Object nilaiumk = mapnilailist.get("nilaiumk");
                Object idbas = mapnilailist.get("idbas");
                Object idrinci = mapnilailist.get("idrinci");

                bukukas.setNilaiKontrak(SipkdHelpers.getBigDecimalFromString(nilairinci.toString()));
                bukukas.setNilaiUmk(SipkdHelpers.getBigDecimalFromString(nilaiumk.toString()));
                bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
                bukukas.setIdKontrakRinci(SipkdHelpers.getIntFromString(idrinci.toString()));

                log.debug("UPDATE ACTION getIdKontrakRinci =========== " + bukukas.getIdKontrakRinci());

                listRinci.add(bukukas);
            }
        }

        kontrakServices.updateKontrakRinci(listRinci);

        return "Data Kontrak Rinci Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/getNilaiKontrak", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getNoKontrakCek(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(2);
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkontrak = request.getParameter("idkontrak");

        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("id", idkontrak);

        final Map< String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("cek", 1);
        mapData.put("aData", kontrakServices.getNilaiKontrak(param));

        return mapData;

    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}
