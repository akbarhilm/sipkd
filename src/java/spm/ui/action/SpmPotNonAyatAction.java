/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spm.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.SpmPotNonAyat;
import spm.services.SpmPotNonAyatServices;
import spm.services.SpmPotAyatServices;
import spm.util.BigDecimalPropertyEditor;
import spm.util.SipkdHelpers;
import spm.util.SqlDatePropertyEditor;

/**
 *
 * @author Xalamaster
 */
@Controller
@RequestMapping("/spmpotnonayat")
public class SpmPotNonAyatAction {

    @Autowired
    SpmPotAyatServices spmpotayatServices;

    @Autowired
    SpmPotNonAyatServices spmpotnonayatServices;
    private static final Logger log = LoggerFactory.getLogger(SpmPotNonAyatAction.class);

    @RequestMapping(value = "/indexspm/{idspm}", method = RequestMethod.GET)
    public String index(@PathVariable Integer idspm, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();

        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("idspm", idspm);
            request.setAttribute("nospm", spmpotayatServices.getNoSpm(idspm));
            request.setAttribute("tglspm", SipkdHelpers.formatSqlDatePropertyEditor(spmpotayatServices.getTglSpm(idspm)));
        }

        return "/spm/spmpotnonayat/addspmpotnonayat";
    }

    @RequestMapping(value = "/json/totspmjson/{idspm}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpotnonayattotjson(@PathVariable Integer idspm, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(3);

        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            log.debug(" listSkpd masuk toString() == " + listSkpd.toString());

        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            //final Integer idskpd = listSkpd.get(0).getIdSkpd();
            //log.debug(idskpd+" tahunAnggaran cek === "+tahunAnggaran);

        }
        final String idskpd = request.getParameter("idskpd");
        final String tahun = request.getParameter("tahun");

        log.debug("PARAM TAHUN ACTION === " + tahun);
        log.debug("PARAM IDSKPD ACTION == " + idskpd);
        log.debug("PARAM IDSPM ACTION === " + idspm);

        param.put("idskpd", idskpd);
        param.put("tahun", tahun);
        param.put("idspm", idspm);

        final Map< String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("cek", 1);
        mapData.put("aData", spmpotnonayatServices.getTotSpm(param));

        return mapData;

    }

    //tidak duginakan karena diganti dengan getkontrak per tanggal 13122018 by mus
    @RequestMapping(value = "/json/nilaikontrak/{idspm}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> nilaikontrak(@PathVariable Integer idspm, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(3);
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());

        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            /*final Integer idskpd = listSkpd.get(0).getIdSkpd();
             param.put("idskpd", idskpd);
             param.put("tahun", tahunAnggaran);
             param.put("idspm", idspm);*/
        }

        final String idskpd = request.getParameter("idskpd");
        final String tahun = request.getParameter("tahun");

        param.put("idskpd", idskpd);
        param.put("tahun", tahun);
        param.put("idspm", idspm);
        final Map< String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("cek", 1);
        mapData.put("aData", spmpotnonayatServices.getVkontrak(param));

        return mapData;

    }
    
    @RequestMapping(value = "/json/getkontrak/{idspm}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getkontrak(@PathVariable Integer idspm, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(3);
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());

        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            /*final Integer idskpd = listSkpd.get(0).getIdSkpd();
             param.put("idskpd", idskpd);
             param.put("tahun", tahunAnggaran);
             param.put("idspm", idspm);*/
        }

        final String idskpd = request.getParameter("idskpd");
        final String tahun = request.getParameter("tahun");

        param.put("idskpd", idskpd);
        param.put("tahun", tahun);
        param.put("idspm", idspm);
        final Map< String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("cek", 1);
        mapData.put("aData", spmpotnonayatServices.getKontrak(param));

        return mapData;

    }

    @RequestMapping(value = "/json/getjamsostek", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> jamsostek(final HttpServletRequest request) {
        final String nilaiKontrak = request.getParameter("nilaikontrak");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("nilaiKontrak", nilaiKontrak);

        final Map< String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", spmpotnonayatServices.getJamsostek(param));

        return mapData;
    }

    @RequestMapping(value = "/json/valtabel/{idspm}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpotnonayatjson(@PathVariable Integer idspm, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(3);
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());

        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            /*final Integer idskpd = listSkpd.get(0).getIdSkpd();
             param.put("idskpd", idskpd);
             param.put("tahun", tahunAnggaran);
             param.put("idspm", idspm);*/
        }

        final String idskpd = request.getParameter("idskpd");
        final String tahun = request.getParameter("tahun");

        param.put("idskpd", idskpd);
        param.put("tahun", tahun);
        param.put("idspm", idspm);

        final Map< String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("cek", 1);
        mapData.put("aData", spmpotnonayatServices.getValTblSpm(param));

        return mapData;

    }

    @RequestMapping(value = "/json/prosespindahsimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, String> mapdata, final HttpServletRequest request) {
        final Timestamp tglSkrg = new Timestamp(System.currentTimeMillis());
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Integer idskpd = listSkpd.get(0).getIdSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(7);

        for (int i = 1; i <= 11; i++) {

            SpmPotNonAyat spmpotnonayat = new SpmPotNonAyat();
            spmpotnonayat.setIdSpm(SipkdHelpers.getIntFromString(mapdata.get("idspm")));
            spmpotnonayat.setTahun(SipkdHelpers.getIntFromString(tahunAnggaran));
            spmpotnonayat.setIdSkpd(idskpd);
            spmpotnonayat.setNilaiPot(SipkdHelpers.getBigDecimalFromString((String) mapdata.get("vpot" + i)));
            spmpotnonayat.setcPot(mapdata.get("cpot" + i));
            float persen = SipkdHelpers.getFloatFromString(mapdata.get("persen" + i));
            if (i == 2) {
                // persen = SipkdHelpers.getIntFromString(mapdata.get("cekpphps22status")) == 1 ? (persen * 10) : persen;
                spmpotnonayat.setStatusPPN(mapdata.get("cekpphps22status"));
            } else if (i == 6) {
                //  persen = SipkdHelpers.getIntFromString(mapdata.get("cekpersen6status")) == 1 ? (persen * 10) : persen;
                spmpotnonayat.setStatusPPN(mapdata.get("cekpersen6status"));
            } else if (i == 10) {
                //  persen = SipkdHelpers.getIntFromString(mapdata.get("cekpersen10")) == 1 ? (persen * 10) : persen;
                spmpotnonayat.setStatusPPN(mapdata.get("cekpersen10"));
            } else if (i == 11) {
                //  persen = SipkdHelpers.getIntFromString(mapdata.get("cekpersen10")) == 1 ? (persen * 10) : persen;
                spmpotnonayat.setStatusPPN(mapdata.get("cekpersen11"));
            } else {
                spmpotnonayat.setStatusPPN("0");
            }
            
            if (i == 9){
                spmpotnonayat.setKodePns(mapdata.get("cpnsdb"));
                spmpotnonayat.setKodePegawai(mapdata.get("cpegdb"));
            } else {
                spmpotnonayat.setKodePns("0");
                spmpotnonayat.setKodePegawai("0");
            }
            spmpotnonayat.setPersen(persen);
            spmpotnonayat.setKodeAkunPajak(mapdata.get("kap" + i).toString());
            spmpotnonayat.setKodeJenisSetor(mapdata.get("kjs" + i).toString());
            final Integer status = SipkdHelpers.getIntFromString(mapdata.get("status" + i));
            final String addoredit = mapdata.get("addoredit" + i);

            if ("add".equals(addoredit)) {// (status == 0) {
                log.debug("************* masuk add index - " + i);
                spmpotnonayat.setIdEntry(pengguna.getIdPengguna());
                spmpotnonayat.setTglEntry(tglSkrg);
                spmpotnonayatServices.addPotayat(spmpotnonayat);
            } else if ("edit".equals(addoredit)) {
                log.debug("(\"************* masuk edit index - " + i);
                spmpotnonayat.setIdEdit(pengguna.getIdPengguna());
                spmpotnonayat.setTglEdit(tglSkrg);
                spmpotnonayatServices.updatePotayat(spmpotnonayat);
            }

        }
        return "Data Pagu SPP GUP/UP  berhasil disimpan ";

    }

    @RequestMapping(value = "/json/getPotUmk", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getPotUmk(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(3);

        final String idskpd = request.getParameter("idskpd");
        final String tahun = request.getParameter("tahun");
        final String idspm = request.getParameter("idspm");

        param.put("idskpd", idskpd);
        param.put("tahun", tahun);
        param.put("idspm", idspm);

        final Map< String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("cek", 1);
        mapData.put("aData", spmpotnonayatServices.getPotUmk(param));

        return mapData;

    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
