/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
package spj.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.BukuKasUmum;
import spj.services.BkuSpjPajakServices;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SipkdHelpers;

import spj.util.SqlDatePropertyEditor;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/bkuspjpajak")
public class BkuSpjPajakAction {

    private static final Logger log = LoggerFactory.getLogger(BkuSpjPajakAction.class);

    @Autowired
    BkuSpjPajakServices bkuServices;

    @RequestMapping(value = "/indexbku", method = RequestMethod.GET)
    public ModelAndView index(final BukuKasUmum bku, final HttpServletRequest request) {

        return new ModelAndView("bkuspjpajak/indexbku", "refbku", bku);
    }

    @RequestMapping(value = "/addbku", method = RequestMethod.GET)
    public ModelAndView addbku(final BukuKasUmum bku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());

            bku.setIdskpd(listSkpd.get(0).getIdSkpd());
        }
        return new ModelAndView("bkuspjpajak/addbku", "refbku", bku);

    }

    @RequestMapping(value = "/json/listbkuspj", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listbkuspjjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idkegiatan = request.getParameter("idkegiatan");
        final String idskpd = request.getParameter("idskpd");
        final String tahun = request.getParameter("tahun");
        final String nobukti = request.getParameter("nobukti");
        final String nobku = request.getParameter("nobku");
        final String beban = request.getParameter("beban");

        param.put("idkegiatan", idkegiatan);
        param.put("nobukti", nobukti);
        param.put("idskpd", idskpd);
        param.put("tahun", tahun);
        param.put("nobku", nobku);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        if ("UP".equals(beban)) {
            final long banyak = bkuServices.getBannyakListSPJ(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getListSPJ(param));

        } else if ("TU".equals(beban)) {
            final long banyak = bkuServices.getBannyakListSpjTU(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getListSpjTU(param));

        }

        return mapData;
    }

    @RequestMapping(value = "/json/prosessimpanspj", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpanspj(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BukuKasUmum> listBKU = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        //final String kodetransaksi = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String idkegiatan = (String) mapdata.get("idkegiatan");
        final String kodekegiatan = (String) mapdata.get("kodekegiatan");
        final String filling = (String) mapdata.get("fileinbox");
        final String uraianinput = (String) mapdata.get("uraianinput");
        final String carabayar = (String) mapdata.get("carabayar");
        final String nipPPTK = (String) mapdata.get("nipPPTK");
        final String namaPPTK = (String) mapdata.get("namaPPTK");

        for (Map<String, Object> mapnilailist : nilailist) {
            BukuKasUmum bukukas = new BukuKasUmum();

            Object penanda = mapnilailist.get("penanda");
            Integer valid = SipkdHelpers.getIntFromString(penanda.toString());

            if (valid == 1) {
                bukukas.setTahun(tahun);
                bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
                bukukas.setIdEntry(pengguna.getIdPengguna());
                bukukas.setTglPosting(tglpost);
                //bukukas.setKodeTransaksi(kodetransaksi);
                bukukas.setNoDok(nobukti);
                bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
                bukukas.setJenis(jenis);
                bukukas.setBeban(beban);
                bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan));
                bukukas.setKodeKeg(kodekegiatan);
                bukukas.setInboxFile(filling);
                bukukas.setUraian(uraianinput);
                bukukas.setKodeWilayah(pengguna.getKodeProses());
                bukukas.setKodePembayaran(carabayar);
                bukukas.setKodeUangPersediaan(carabayar);
                bukukas.setNipPptk(nipPPTK);
                bukukas.setNamaPptk(namaPPTK);
                bukukas.setKodeKoreksi("0");

                Object kodetransaksi = mapnilailist.get("kodetransaksi");
                Object nilaimasuk = mapnilailist.get("nilaimasuk");
                Object nilaikeluar = mapnilailist.get("nilaikeluar");
                Object idbas = mapnilailist.get("idbas");
                Object uraianbukti = mapnilailist.get("uraianbukti");
                Object kodeakun = mapnilailist.get("kodeakun");
                Object nilainetto = mapnilailist.get("nilainetto");
                Object nilipajak = mapnilailist.get("nilipajak");

                bukukas.setKodeTransaksi(kodetransaksi.toString());
                bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
                bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
                bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
                bukukas.setUraianBukti(uraianbukti.toString());
                bukukas.setKodeakun(kodeakun.toString());
                bukukas.setNilaiPajak(SipkdHelpers.getBigDecimalFromString(nilipajak.toString()));
                bukukas.setNilaiSpjNetto(SipkdHelpers.getBigDecimalFromString(nilainetto.toString()));

                listBKU.add(bukukas);
            }
        }

        bkuServices.insertBkuSpj(listBKU);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/editbkuspj/", method = RequestMethod.GET)
    public ModelAndView editbkuspj(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String nobukti = request.getParameter("nobukti");
        final String tglpost = request.getParameter("tglpost");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("nobukti", nobukti);
        param.put("tglpost", tglpost);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);
        //param.put("tglPosting", tglPosting);

        final BukuKasUmum bku = bkuServices.getBkuEdit(param);

        return new ModelAndView("bkuspjpajak/editbku", "refbku", bku);
    }

    @RequestMapping(value = "/listkegiatan", method = RequestMethod.GET)
    public ModelAndView listakunbukubesar(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bkuspjpajak/listkegiatan", "refkegiatan", bku);
    }

    @RequestMapping(value = "/json/listkegiatanjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listkegiatanjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kode = request.getParameter("kode");
        final String nama = request.getParameter("nama");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String keterangan = request.getParameter("keterangan");
        final String carabayar = request.getParameter("carabayar");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("nama", nama);
        param.put("kode", kode);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        if ("3".equals(carabayar)) {
            final long banyak = bkuServices.getBanyakKegiatanSpjPanjar(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getKegiatanSpjPanjar(param));
        } else {
            final long banyak = bkuServices.getBanyakKegiatanSPJ(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);

            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getKegiatanSPJ(param));
        }
        return mapData;
    }

    @RequestMapping(value = "/json/getNilaiSisaSpj", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getNilaiSisaSpj(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String nobku = request.getParameter("nobku");
        final String idskpd = request.getParameter("idskpd");
        final String beban = request.getParameter("beban");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String carabayar = request.getParameter("carabayar");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("nobku", nobku);
        param.put("beban", beban);
        param.put("idkegiatan", idkegiatan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        /*
        TUNAI khusus untuk UP/GU, karena sumber tunai dari Cek.. Cek bebannya default UP
        */
        if("1".equals(carabayar)){ // TUNAI
            mapData.put("aData", bkuServices.getNilaiSisaSpjTunai(param));
        } else { // BANK
            mapData.put("aData", bkuServices.getNilaiSisaSpj(param));
        }
        

        return mapData;
    }

    @RequestMapping(value = "/json/getNilaiValidasiSisaSpj", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getNilaiValidasiSisaSpj(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String nobku = request.getParameter("nobku");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String beban = request.getParameter("beban");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("nobku", nobku);
        param.put("idkegiatan", idkegiatan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        if ("UP".equals(beban)) {
            mapData.put("aData", bkuServices.getNilaiValidasiSisaSpj(param));

        } else if ("TU".equals(beban)) {
            //log.debug("************************ getNilaiValidasiSisaSpj TU");
            mapData.put("aData", bkuServices.getNilaiValidasiSisaSpjTU(param));
        }

        return mapData;
    }

    @RequestMapping(value = "/json/getBebanSpjTu", method = RequestMethod.GET)
    public @ResponseBody
    Integer getBebanSpjTu(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idkegiatan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);

        return bkuServices.getBebanSpjTu(param);
    }

    @RequestMapping(value = "/json/getKodeTutup", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getKodeTutup(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getKodeTutup(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getKodeTutupMax", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getKodeTutupMax(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getKodeTutupMax(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getNamaNipSpjPanjar", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getNamaNipSpjPanjar(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idkegiatan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getNamaNipSpjPanjar(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getBanyakListSPJ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getBanyakListSPJ(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        final Map< String, Object> param = new HashMap<String, Object>(3);

        final String idkegiatan = request.getParameter("idkegiatan");
        final String tahun = request.getParameter("tahun");
        final String nobukti = request.getParameter("nobukti");
        final String nobku = request.getParameter("nobku");
        final String beban = request.getParameter("beban");
        final String idskpd = request.getParameter("idskpd");
        Integer banyak = 0;

        param.put("tahun", tahunAnggaran);
        param.put("idkegiatan", idkegiatan);
        param.put("nobukti", nobukti);
        param.put("idskpd", idskpd);
        param.put("tahun", tahun);
        param.put("nobku", nobku);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        if ("UP".equals(beban)) {
            banyak = bkuServices.getBannyakListSPJ(param);

        } else if ("TU".equals(beban)) {
            banyak = bkuServices.getBannyakListSpjTU(param);

        }

        return banyak;
    }

    @RequestMapping(value = "/json/getBulanByRekap", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getBulanByRekap(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getBulanByRekap(param));

        return mapData;
    }

    @RequestMapping(value = "/json/listpajak", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpajak(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idskpd = request.getParameter("idskpd");
        final String tahun = request.getParameter("tahun");
        final String idbas = request.getParameter("idbas");
        final String nobkuref = request.getParameter("nobkuref");

        param.put("idskpd", idskpd);
        param.put("tahun", tahun);
        param.put("idbas", idbas);
        param.put("nobkuref", nobkuref);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListPajak(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListPajak(param));

        return mapData;
    }

    @RequestMapping(value = "/listpajak", method = RequestMethod.GET)
    public ModelAndView listpajak(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bkuspjpajak/listpajak", "refkegiatan", bku);
    }

    @RequestMapping(value = "/json/listindex", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listindex(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String bulan = request.getParameter("bulan");
        final String idskpd = request.getParameter("idskpd");
        final String tahun = request.getParameter("tahun");
        final String kodewilayah = pengguna.getKodeProses();

        //log.debug(" kode wilayah =========== " + kodewilayah);
        //final Integer idskpd2 = SipkdHelpers.getIntFromString(idskpd);
        BukuKasUmum bukukas = new BukuKasUmum();

        bukukas.setTahun(tahun);
        bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd));
        bukukas.setBulan(bulan);
        bukukas.setKodeWilayah(kodewilayah);
        bukukas.setOffset(offset);
        bukukas.setLimit(limit);
        bukukas.setiSortCol_0(iSortCol_0);
        bukukas.setsSortDir_0(sSortDir_0);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListIndex(bukukas);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListIndex(bukukas));

        return mapData;
    }

    @RequestMapping(value = "/json/getDataPajak", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getDataPajak(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String nobukti = request.getParameter("nobukti");
        final String tglpost = request.getParameter("tglpost");
        final String nobkuref = request.getParameter("nobkuref");
        
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("nobukti", nobukti);
        param.put("idkegiatan", idkegiatan);
        param.put("tglpost", tglpost);
        param.put("nobkuref", nobkuref);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuServices.getDataPajak(param));

        return mapData;
    }

    @RequestMapping(value = "/json/prosesupdatespj", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdatespj(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BukuKasUmum> listBKU = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        //final String kodetransaksi = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String idkegiatan = (String) mapdata.get("idkegiatan");
        final String kodekegiatan = (String) mapdata.get("kodekegiatan");
        final String filling = (String) mapdata.get("fileinbox");
        final String uraianinput = (String) mapdata.get("uraianinput");
        final String carabayar = (String) mapdata.get("carabayar");
        final String nipPPTK = (String) mapdata.get("nipPPTK");
        final String namaPPTK = (String) mapdata.get("namaPPTK");
        final String nobkuspj = (String) mapdata.get("nobkuspj");

        for (Map<String, Object> mapnilailist : nilailist) {
            BukuKasUmum bukukas = new BukuKasUmum();

            Object penanda = mapnilailist.get("penanda");
            Integer valid = SipkdHelpers.getIntFromString(penanda.toString());

            Object idbkuvar = mapnilailist.get("idbku");
            Integer idbku = SipkdHelpers.getIntFromString(idbkuvar.toString());

            String statusspj = "";

            if (valid == 1 || idbku > 0) {

                bukukas.setTahun(tahun);
                bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
                bukukas.setIdEntry(pengguna.getIdPengguna());
                bukukas.setTglPosting(tglpost);
                //bukukas.setKodeTransaksi(kodetransaksi);
                bukukas.setNoDok(nobukti);
                bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
                bukukas.setJenis(jenis);
                bukukas.setBeban(beban);
                bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan));
                bukukas.setKodeKeg(kodekegiatan);
                bukukas.setInboxFile(filling);
                bukukas.setUraian(uraianinput);
                bukukas.setKodeWilayah(pengguna.getKodeProses());
                bukukas.setKodePembayaran(carabayar);
                bukukas.setKodeUangPersediaan(carabayar);
                bukukas.setNipPptk(nipPPTK);
                bukukas.setNamaPptk(namaPPTK);
                bukukas.setKodeKoreksi("0");
                bukukas.setNoBkuSpj(SipkdHelpers.getIntFromString(nobkuspj));

                Object kodetransaksi = mapnilailist.get("kodetransaksi");
                Object nilaimasuk = mapnilailist.get("nilaimasuk");
                Object nilaikeluar = mapnilailist.get("nilaikeluar");
                Object idbas = mapnilailist.get("idbas");
                Object uraianbukti = mapnilailist.get("uraianbukti");
                Object kodeakun = mapnilailist.get("kodeakun");
                Object nilainetto = mapnilailist.get("nilainetto");
                Object nilipajak = mapnilailist.get("nilipajak");
                Object nobku = mapnilailist.get("nobku");
                Object status = mapnilailist.get("status");
                
                String kodetrx = kodetransaksi.toString();

                bukukas.setKodeTransaksi(kodetransaksi.toString());
                bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
                bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
                bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
                bukukas.setUraianBukti(uraianbukti.toString());
                bukukas.setKodeakun(kodeakun.toString());
                bukukas.setNilaiPajak(SipkdHelpers.getBigDecimalFromString(nilipajak.toString()));
                bukukas.setNilaiSpjNetto(SipkdHelpers.getBigDecimalFromString(nilainetto.toString()));
                bukukas.setNoBKU(SipkdHelpers.getIntFromString(nobku.toString()));
                bukukas.setIdBku(SipkdHelpers.getIntFromString(idbkuvar.toString()));
                
                if ("JJ".equals(kodetrx)) {
                    log.debug("ACTION VALID VALUE ************ "+valid);
                    log.debug("ACTION IDBKU VALUE ************ "+idbku);
                
                    
                    if (valid == 1 && idbku > 0) {
                        statusspj = "edit";
                    } else if (valid == 1 && idbku == 0) {
                        statusspj = "add";
                    } else if (valid == 0 && idbku > 0) {
                        statusspj = "delete";
                    }
                    
                    log.debug("ACTION STATUS SPJ VALUE ************ "+statusspj);
                
                } else { // untuk PAJAK
                    statusspj = status.toString();
                }

                bukukas.setBkuStatus(statusspj);

                listBKU.add(bukukas);
            }
        }

        bkuServices.updateBkuSpj(listBKU);

        return "Data Buku Kas Umum Berhasil Diubah";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
