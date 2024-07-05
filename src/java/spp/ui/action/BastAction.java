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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spp.model.Akun;
import spp.model.Bast;
import spp.model.Kontrak;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.services.BastServices;
import spp.util.BigDecimalPropertyEditor;
import spp.util.SipkdHelpers;
import spp.util.SqlDatePropertyEditor;

/**
 *
 * @author erzypratama
 */
@Controller
@RequestMapping("/bast")
public class BastAction {

    private static final Logger log = LoggerFactory.getLogger(BastAction.class);
    @Autowired
    BastServices bastServices;

    @RequestMapping(method = RequestMethod.GET)
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
        request.setAttribute("kodekegiatanfilter", request.getParameter("kodekegiatanfilter"));
        request.setAttribute("namakegiatanfilter", request.getParameter("namakegiatanfilter"));
        return "/ref/bast/listbast";

    }

    @RequestMapping(value = "/json/listbastjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listbastjson(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("kodeskpd");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kodekegiatan = request.getParameter("kodekegiatan");
        final String namakegiatan = request.getParameter("namakegiatan");
        final String nomorkontrak = request.getParameter("nomorkontrak");
        final String nomorbast = request.getParameter("nomorbast");
        final String idskpd = request.getParameter("idskpd");
        param.put("idskpd", idskpd);
        param.put("kodekegiatan", StringUtils.trimAllWhitespace(kodekegiatan));
        param.put("namakegiatan", StringUtils.trimAllWhitespace(namakegiatan));
        param.put("nomorkontrak", StringUtils.trimAllWhitespace(nomorkontrak));
        param.put("nomorbast", StringUtils.trimAllWhitespace(nomorbast));
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("kodeskpd", skpd);
        param.put("tahun", tahunAnggaran);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = bastServices.getBanyakAllBast(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bastServices.getBast(param));
        return mapData;
    }

    @RequestMapping(value = "/addbast", method = RequestMethod.GET)
    public ModelAndView add(final Bast bast, final HttpServletRequest request) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();

        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
            request.setAttribute("tahunAnggaran", tahunAnggaran);

        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            request.setAttribute("isall", 0);
            request.setAttribute("tahunAnggaran", tahunAnggaran);
            request.setAttribute("skpd", listSkpd.get(0));

        }
        return new ModelAndView("/ref/bast/addbast", "progcmd", bast);
    }

    @RequestMapping(value = "/simpanbast", method = RequestMethod.POST)
    public String prosessimpans(@Valid @ModelAttribute("progcmd") Bast bast, BindingResult result, final HttpServletRequest request) {

        if (result.hasErrors()) {
            return "/ref/bast/addbast";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            final int banyakakun = SipkdHelpers.getIntFromString(request.getParameter("banyakakun"));
            log.debug(" CEK BANYAK AKUN ================== " + banyakakun);
            final List<Bast> listBast = new ArrayList<>(banyakakun);
            final Skpd skpd = bast.getSkpd();
            final Kontrak kontrak = bast.getKontrak();
            for (int i = 1; i <= banyakakun; i++) {
                final String penanda = "cekpilih" + i;
                log.debug(" CEK PENANDA ================== " + penanda);
                log.debug(" CEK IDAKUN ================== " + request.getParameter("idAkun" + i));
                final String nourutidx = request.getParameter(penanda);
                log.debug(" CEK PILIH nourutidx ################# " + nourutidx);
                if (nourutidx != null && !nourutidx.isEmpty()) {
                    //if ("pilih".equals(nourutidx)) {
                    final Bast addBast = new Bast();
                    addBast.setIdEntry(pengguna.getIdPengguna());
                    addBast.setTglEntry(new Timestamp(System.currentTimeMillis()));
                    addBast.setTahunAnggaran(bast.getTahunAnggaran());
                    addBast.setSkpd(skpd);
                    addBast.setIdkegiatan(bast.getIdkegiatan());
                    addBast.setKontrak(kontrak);
                    addBast.setIdKontrak(SipkdHelpers.getIntFromString(request.getParameter("kontrak.idKontrak")));
                    addBast.setNoBast(bast.getNoBast());
                    addBast.setTglBast(bast.getTglBast());
                    addBast.setNamaPptk(bast.getNamaPptk());
                    addBast.setNipPptk(bast.getNipPptk());
                    addBast.setNamaPemeriksaBarang(bast.getNamaPemeriksaBarang());
                    addBast.setNipPemeriksaBarang(bast.getNipPemeriksaBarang());
                    final Akun akun = new Akun();
                    akun.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + i)));
                    addBast.setAkun(akun);
                    addBast.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + i)));
                    addBast.setNilaiPrestasi(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaiprestasi" + i)));
                    addBast.setNilaiBast(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaibast" + i)));
                    addBast.setKetBast(bast.getKetBast());
                    addBast.setStatusUangMuka(bast.getStatusUangMuka());
                    addBast.setIdSpd(SipkdHelpers.getIntFromString(request.getParameter("idspd" + i)));
                    addBast.setIdBl(SipkdHelpers.getIntFromString(request.getParameter("idBl" + i)));
                    log.debug(new StringBuilder(" bast.getIdKontrak() ").append(addBast.getIdKontrak()).append("  getIdkegiatan   ").append(addBast.getIdkegiatan()).append("  getIdAkun   ").append(addBast.getIdAkun()).toString());
                    listBast.add(addBast);
                }
            }
            bastServices.insertBastList(listBast);
            //bastServices.insertBast(bast);
        }
        return "redirect:/bast";
    }

    @RequestMapping(value = "/json/listpopupkontrak", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsonrekanan(final HttpServletRequest request) {
        final Map< String, Object> param = new LinkedHashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("kontrak");
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");

        final String kodekegiatan = StringUtils.trimWhitespace(request.getParameter("kodekegiatan"));
        final String namakegiatan = StringUtils.trimWhitespace(request.getParameter("namakegiatan"));
        final String nokontrak = StringUtils.trimWhitespace(request.getParameter("nokontrak"));

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("kodekegiatan", kodekegiatan);
        param.put("namakegiatan", namakegiatan);
        param.put("nokontrak", nokontrak);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("kontrak", skpd);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = bastServices.getBanyakAllKontrak(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        //  log.debug(bastServices.getKontrak(param).toString());
        mapData.put("aaData", bastServices.getKontrak(param));
        return mapData;
    }

    @RequestMapping(value = "/listpopupkontrak", method = RequestMethod.GET)
    public ModelAndView listkontrak(final HttpServletResponse response, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("/ref/bast/listpopupkontrak");
    }

    @RequestMapping(value = "/json/listpopupakun", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpopupakun(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String idspd = request.getParameter("idspd");
        final String idbast = request.getParameter("idbast");
        final String idkontrak = request.getParameter("idkontrak");
        final String nobast = request.getParameter("nobast");

        param.put("idspd", idspd);
        param.put("tahun", tahunAnggaran);
        param.put("idkegiatan", idkegiatan);
        param.put("idskpd", idskpd);
        param.put("idbast", idbast);
        param.put("idkontrak", idkontrak);
        param.put("nobast", nobast);

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = bastServices.getBanyakAkun(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bastServices.getAkun(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listpopupakunupdate/{nobast}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpopupakunupdate(final HttpServletRequest request, @PathVariable Integer nobast) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String idspd = request.getParameter("idspd");
        final String idbast = request.getParameter("idbast");
        final String idkontrak = request.getParameter("idkontrak");

        param.put("idspd", idspd);
        param.put("nobast", nobast);
        param.put("tahun", tahunAnggaran);
        param.put("idkegiatan", idkegiatan);
        param.put("idskpd", idskpd);
        param.put("idbast", idbast);
        param.put("idkontrak", idkontrak);

        log.debug("NO BAST ========= " + nobast);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = bastServices.getBanyakAkun(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);

        final List<Bast> listBast = bastServices.getAkun(param);

        mapData.put("aaData", listBast);
        return mapData;
    }

    @RequestMapping(value = "/json/banyakpopupakun", method = RequestMethod.GET)
    public @ResponseBody
    Integer banyakpopupakun(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);

        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String idspd = request.getParameter("idspd");
        final String idbast = request.getParameter("idbast");
        final String idkontrak = request.getParameter("idkontrak");
        final String nobast = request.getParameter("nobast");

        param.put("tahun", tahunAnggaran);
        param.put("idkegiatan", idkegiatan);
        param.put("idskpd", idskpd);
        param.put("idspd", idspd);
        param.put("idbast", idbast);
        param.put("idkontrak", idkontrak);
        param.put("nobast", nobast);

        return bastServices.getBanyakAkunSpd(param);
        //return bastServices.getBanyakAkun(param);
    }

    @RequestMapping(value = "/listpopupakun/{idkegiatan}", method = RequestMethod.GET)
    public ModelAndView listpopupakun(@PathVariable Integer idkegiatan, Bast bast, final HttpServletResponse response, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));

        }
        //bast = bastServices.getKegiatanById(idkegiatan);
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("/ref/bast/listpopupakun", "progcmd", bast);
    }

    @RequestMapping(value = "/updatebast/{nobast}/{idSkpd}/{tahun}", method = RequestMethod.GET)
    public ModelAndView updatebast(@PathVariable String nobast,
            @PathVariable Integer idSkpd, @PathVariable Integer tahun,
            final HttpServletRequest request) {
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
        final Map mapparam = new HashMap<>(3);
        mapparam.put("nobast", nobast);
        mapparam.put("idskpd", idSkpd);
        mapparam.put("tahun", tahun);
        final List<Bast> listbast = bastServices.getBastByNoBastSkpdAndTahun(mapparam);
        request.setAttribute("listbast", listbast);
        return new ModelAndView("/ref/bast/updatebast", "spdBTLMaster", listbast.get(0));
    }

    @RequestMapping(value = "/deletebast/{nobast}/{idSkpd}/{tahun}", method = RequestMethod.GET)
    public ModelAndView delbast(@PathVariable String nobast,
            @PathVariable Integer idSkpd, @PathVariable Integer tahun, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();

        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
            request.setAttribute("tahunAnggaran", tahunAnggaran);

        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            request.setAttribute("isall", 0);
            request.setAttribute("tahunAnggaran", tahunAnggaran);
            request.setAttribute("skpd", listSkpd.get(0));

        }
        final Map mapparam = new HashMap<>(3);
        mapparam.put("nobast", nobast);
        mapparam.put("idskpd", idSkpd);
        mapparam.put("tahun", tahun);
        final List<Bast> listbast = bastServices.getBastByNoBastSkpdAndTahun(mapparam);
        request.setAttribute("listbast", listbast);
        return new ModelAndView("/ref/bast/deletebast", "spdBTLMaster", listbast.get(0));
    }

    @RequestMapping(value = "/updatebast", method = RequestMethod.POST)
    public String prosesupdatebast(@Valid @ModelAttribute("progcmd") Bast bast, BindingResult result, final HttpServletRequest request) {

        if (result.hasErrors()) {
            return "/ref/bast/addbast";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            final int banyakakun = SipkdHelpers.getIntFromString(request.getParameter("banyakakun"));
            final List<Bast> listBast = new ArrayList<>(banyakakun);
            final Skpd skpd = bast.getSkpd();
            final Kontrak kontrak = bast.getKontrak();

            log.debug("BANYAK AKUN update ============ " + banyakakun);

            for (int i = 1; i <= banyakakun; i++) {
                final String penanda = "cekpilih" + i;
                final String nourutidx = request.getParameter(penanda);
                log.debug(" CEK PILIH nourutidx ################# " + nourutidx);

                final Bast addBast = new Bast();
                addBast.setIdEntry(pengguna.getIdPengguna());
                addBast.setTglEntry(new Timestamp(System.currentTimeMillis()));
                addBast.setTahunAnggaran(bast.getTahunAnggaran());
                addBast.setSkpd(skpd);
                addBast.setIdkegiatan(bast.getIdkegiatan());
                addBast.setKontrak(kontrak);

                //addBast.setIdKontrak(bast.getIdKontrak());
                addBast.setIdKontrak(SipkdHelpers.getIntFromString(request.getParameter("kontrak.idKontrak")));
                log.debug("ID KONTRAK ========== " + SipkdHelpers.getIntFromString(request.getParameter("kontrak.idKontrak")));

                addBast.setNoBast(bast.getNoBast());
                addBast.setTglBast(bast.getTglBast());
                addBast.setNamaPptk(bast.getNamaPptk());
                addBast.setNipPptk(bast.getNipPptk());
                addBast.setNamaPemeriksaBarang(bast.getNamaPemeriksaBarang());
                addBast.setNipPemeriksaBarang(bast.getNipPemeriksaBarang());
                addBast.setIdKegAwal(bast.getIdKegAwal());
                log.debug("ACTION - ID KEGIATAN AWAL ========== " + bast.getIdKegAwal());
                final Akun akun = new Akun();
                akun.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + i)));
                addBast.setAkun(akun);
                addBast.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + i)));
                addBast.setNilaiPrestasi(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaiprestasi" + i)));
                addBast.setNilaiBast(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaibast" + i)));
                addBast.setKetBast(bast.getKetBast());
                addBast.setIdBast(SipkdHelpers.getIntFromString(request.getParameter("idbast" + i)));
                addBast.setIdSpd(SipkdHelpers.getIntFromString(request.getParameter("idspd" + i)));
                //addBast.setStatusUangMuka(bast.getStatusUangMuka());statusUM
                addBast.setStatusUangMuka(SipkdHelpers.getIntFromString(request.getParameter("statusUM")));
                addBast.setIdBl(SipkdHelpers.getIntFromString(request.getParameter("idBl" + i)));
                listBast.add(addBast);
                log.debug("UPDATE BAST == statusUM === " + SipkdHelpers.getIntFromString(request.getParameter("statusUM")));
                log.debug("UPDATE BAST == bast.getStatusUangMuka() === " + bast.getStatusUangMuka());
                final String ketedit = request.getParameter("addoredit" + i);

                if ("add".equals(ketedit)) {
                    if (nourutidx != null && !nourutidx.isEmpty()) {
                        bastServices.insertBast(addBast);
                    }

                } else if ("edit".equals(ketedit)) {
                    if (nourutidx != null && !nourutidx.isEmpty()) { // jika sebelumnya dipilih menjadi tidak dipilih
                        bastServices.updateBast(addBast);
                    } else {
                        addBast.setNilaiBast(SipkdHelpers.getBigDecimalFromString("0"));  // nilai bast nya diupdate ke 0
                        bastServices.updateBast(addBast);
                    }

                }
            }
            //bastServices.updateBastList(listBast);

        }
        return "redirect:/bast";

    }

    @RequestMapping(value = "/deletebast", method = RequestMethod.POST)
    public String prosesdeletebast(@Valid @ModelAttribute("progcmd") Bast bast, BindingResult result, final HttpServletRequest request) {
        final Map<String, Object> map = new HashMap<>(3);
        map.put("nobast", bast.getNoBast());
        map.put("tahun", bast.getTahunAnggaran());
        map.put("idskpd", bast.getSkpd().getIdSkpd());
        bastServices.deleteBastByNoBastSkpdAndTahun(map);

        return "redirect:/bast";

    }

    @RequestMapping(value = "/json/getsisabast", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> sisabast(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String idkontrak = request.getParameter("idkontrak");
        final String idbast = request.getParameter("idbast");
        final String nobast = request.getParameter("nobast");
        final String kodemulti = request.getParameter("kodemulti");

        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);
        param.put("idkontrak", idkontrak);
        param.put("idbast", idbast);
        param.put("nobast", nobast);
        param.put("kodemulti", kodemulti);

        final Map< String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bastServices.getSisaBast(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getidspd", method = RequestMethod.GET)
    public @ResponseBody
    String getidspd(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);

        final String idkontrak = request.getParameter("idkontrak");

        return bastServices.getIdspd(idkontrak);
    }

    @RequestMapping(value = "/json/getKontrakRinci", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getBanyakNoBukti(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkontrak = request.getParameter("idkontrak");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idkontrak", idkontrak);

        final Map< String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bastServices.getStatusKontrakRinci(param));

        return mapData;
    }

    @RequestMapping(value = "/json/listakunkontrakrinci", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listakunkontrakrinci(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String idspd = request.getParameter("idspd");
        final String idbast = request.getParameter("idbast");
        final String idkontrak = request.getParameter("idkontrak");
        final String nobast = request.getParameter("nobast");
        final String kodemulti = request.getParameter("kodemulti");

        param.put("idspd", idspd);
        param.put("tahun", tahunAnggaran);
        param.put("idkegiatan", idkegiatan);
        param.put("idskpd", idskpd);
        param.put("idbast", idbast);
        param.put("idkontrak", idkontrak);
        param.put("nobast", nobast);
        param.put("kodemulti", kodemulti);

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bastServices.getBanyakAkunSpd(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bastServices.getAkunSpd(param));
        return mapData;

    }

    @RequestMapping(value = "/json/listakunkontrakrinciupdate/{nobast}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listakunkontrakrinciupdate(final HttpServletRequest request, @PathVariable Integer nobast) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String idspd = request.getParameter("idspd");
        final String idbast = request.getParameter("idbast");
        final String idkontrak = request.getParameter("idkontrak");
        final String kodemulti = request.getParameter("kodemulti");

        param.put("idspd", idspd);
        param.put("nobast", nobast);
        param.put("tahun", tahunAnggaran);
        param.put("idkegiatan", idkegiatan);
        param.put("idskpd", idskpd);
        param.put("idbast", idbast);
        param.put("idkontrak", idkontrak);
        param.put("kodemulti", kodemulti);

        log.debug("NO BAST ========= " + nobast);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = bastServices.getBanyakAkunKontrakRinci(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);

        final List<Bast> listBast = bastServices.getAkunKontrakRinci(param);

        mapData.put("aaData", listBast);
        return mapData;
    }

    @RequestMapping(value = "/json/getKodeUMK", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getKodeUMK(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idkegiatan");

        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);

        final Map< String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bastServices.getKodeUMK(param));

        return mapData;
    }

    @RequestMapping(value = "/json/listpaguakunkontrak", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpaguakunkontrak(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String idkontrak = request.getParameter("idkontrak");
        final String nobast = request.getParameter("nobast");

        param.put("tahun", tahunAnggaran);
        param.put("idkegiatan", idkegiatan);
        param.put("idskpd", idskpd);
        param.put("idkontrak", idkontrak);
        param.put("nobast", nobast);

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bastServices.getBanyakAkunKontrak(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bastServices.getAkunKontrak(param));
        return mapData;

    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}
