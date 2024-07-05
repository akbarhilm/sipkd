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
import spj.services.BkuServices;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SipkdHelpers;

import spj.util.SqlDatePropertyEditor;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/bku")
public class BkuAction {

    private static final Logger log = LoggerFactory.getLogger(BkuAction.class);

    @Autowired
    BkuServices bkuServices;

    @RequestMapping(value = "/indexbku", method = RequestMethod.GET)
    public ModelAndView index(final BukuKasUmum bku, final HttpServletRequest request) {

        return new ModelAndView("bku/indexbku", "refbku", bku);
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
        return new ModelAndView("bku/addbku", "refbku", bku);

    }

    @RequestMapping(value = "/json/getNipPPTK", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getNipPPTK(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getNamaPPTK(param));

        return mapData;
    }

    @RequestMapping(value = "/json/setNoBukti", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> setNoBukti(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String jenis = request.getParameter("jenis");
        final String idskpd = request.getParameter("idskpd");
        final String bulan = request.getParameter("bulan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("bulan", bulan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        if ("JO".equals(jenis)) {
            mapData.put("aData", bkuServices.getNoBukti(param));
        }

        return mapData;
    }

    @RequestMapping(value = "/json/getBanyakNoBukti", method = RequestMethod.GET)
    public @ResponseBody
    Integer getBanyakNoBukti(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String bulan = request.getParameter("bulan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("bulan", bulan);

        return bkuServices.getBannyaNoBukti(param);
    }

    @RequestMapping(value = "/json/listbkunm", method = RequestMethod.GET)

    public @ResponseBody
    Map<String, Object> listbkunm(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String nobku = request.getParameter("nobku");

        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);
        param.put("nobku", nobku);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListNM(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListNM(param));

        return mapData;
    }

    @RequestMapping(value = "/json/listbkunp", method = RequestMethod.GET)

    public @ResponseBody
    Map<String, Object> listbkunp(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String nobku = request.getParameter("nobku");

        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);
        param.put("nobku", nobku);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListNP(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListNP(param));

        return mapData;
    }

    @RequestMapping(value = "/json/listbku", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listbkujson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String keterangan = request.getParameter("keterangan");
        final String idspp = request.getParameter("idspp");

        param.put("idspp", idspp);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        if ("upgutu".equals(keterangan)) {
            final long banyak = bkuServices.getBannyakListBkuUpgutu(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getListBkuUpgutu(param));

        } else if ("LS1".equals(keterangan)) {
            final long banyak = bkuServices.getBannyakListBkuLs1(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getListBkuLs1(param));

        } else if ("LS2".equals(keterangan)) {
            final long banyak = bkuServices.getBannyakListBkuLs2(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getListBkuLs2(param));

        } else if ("LS3".equals(keterangan)) {
            final long banyak = bkuServices.getBannyakListBkuLs3(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getListBkuLs3(param));

        } else if ("LS4".equals(keterangan)) {
            final long banyak = bkuServices.getBannyakListBkuLs4(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getListBkuLs4(param));

        }

        return mapData;
    }

    @RequestMapping(value = "/json/listbkuindex", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> indexlistbkujson(final HttpServletRequest request) {
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

        final long banyak = bkuServices.getBannyakListIndex(bukukas);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListIndex(bukukas));

        return mapData;
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

    @RequestMapping(value = "/json/listbkusp2d", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listbkusp2d(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idskpd = request.getParameter("idskpd");
        final String tahun = request.getParameter("tahun");
        final String idtransaksi = request.getParameter("idtransaksi");
        final String nobku = request.getParameter("nobku");

        param.put("idskpd", idskpd);
        param.put("idtransaksi", idtransaksi);
        param.put("tahun", tahun);
        param.put("nobku", nobku);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListSp2dEdit(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListSp2dEdit(param));

        return mapData;
    }

    @RequestMapping(value = "/json/prosessimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BukuKasUmum> listBKU = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String filling = (String) mapdata.get("fileinbox");

        for (Map<String, Object> mapnilailist : nilailist) {
            BukuKasUmum bukukas = new BukuKasUmum();

            bukukas.setTahun(tahun);
            bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
            bukukas.setIdEntry(pengguna.getIdPengguna());
            bukukas.setBeban(beban);
            bukukas.setJenis(jenis);
            bukukas.setInboxFile(filling);

            Object nilaimasuk = mapnilailist.get("nilaimasuk");
            Object nilaikeluar = mapnilailist.get("nilaikeluar");
            Object kodetransaksi = mapnilailist.get("kodetransaksi");
            Object idsp2d = mapnilailist.get("idsp2d");
            Object idbas = mapnilailist.get("idbas");
            Object nodok = mapnilailist.get("nodok");
            Object tgldok = mapnilailist.get("tgldok");
            Object tglpost = mapnilailist.get("tglpost");
            Object kodekegiatan = mapnilailist.get("kodekegiatan");
            Object uraianbukti = mapnilailist.get("uraianbukti");
            Object idkegiatan = mapnilailist.get("idkegiatan");
            Object kodeakun = mapnilailist.get("kodeakun");

            bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
            bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
            bukukas.setKodeTransaksi(kodetransaksi.toString());
            bukukas.setIdsp2d(SipkdHelpers.getIntFromString(idsp2d.toString()));
            bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            bukukas.setNoDok(nodok.toString());
            bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok.toString()));
            bukukas.setTglPosting(tglpost.toString());
            bukukas.setKodeKeg(kodekegiatan.toString());
            bukukas.setUraianBukti(uraianbukti.toString());
            bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan.toString()));
            bukukas.setKodeakun(kodeakun.toString());

            listBKU.add(bukukas);
        }

        bkuServices.insertBKU(listBKU);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosessimpansetorup", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpansetorup(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        BukuKasUmum bukukas = new BukuKasUmum();

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String nilaikeluar = (String) mapdata.get("nilaikeluar");
        final Integer nilaimasuk = (Integer) mapdata.get("nilaimasuk");
        final String filling = (String) mapdata.get("fileinbox");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");
        final String akun = (String) mapdata.get("akun");
        final String idbas = (String) mapdata.get("idbas");

        bukukas.setTahun(tahun);
        bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setTglPosting(tglpost);
        bukukas.setKodeTransaksi(kodetrans);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setJenis(jenis);
        bukukas.setBeban(beban);
        bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
        bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar));
        bukukas.setInboxFile(filling);
        bukukas.setNamaPptk(namapptk);
        bukukas.setNipPptk(nippptk);
        bukukas.setUraian(uraian);
        bukukas.setKodeWilayah(pengguna.getKodeProses());
        bukukas.setKodePembayaran(carabayar);
        bukukas.setKodeUangPersediaan(carabayar);
        bukukas.setKodeKoreksi("0");
        bukukas.setAkunPn(akun);
        bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas));

        bkuServices.insertBkuSetorUp(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosessimpanjasagiro", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpanjasagiro(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        BukuKasUmum bukukas = new BukuKasUmum();

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String nilaikeluar = (String) mapdata.get("nilaikeluar");
        final String nilaimasuk = (String) mapdata.get("nilaimasuk");
        final String filling = (String) mapdata.get("fileinbox");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");
        final String akun = (String) mapdata.get("akun");
        final String idbas = (String) mapdata.get("idbas");
        final String nobkuref = (String) mapdata.get("nobkuref");
        final String jenisbayar = (String) mapdata.get("jenisbayar");

        bukukas.setTahun(tahun);
        bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setTglPosting(tglpost);
        bukukas.setKodeTransaksi(kodetrans);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
        bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar));
        bukukas.setInboxFile(filling);
        bukukas.setUraian(uraian);
        bukukas.setKodeWilayah(pengguna.getKodeProses());
        bukukas.setKodePembayaran(carabayar);
        bukukas.setKodeUangPersediaan(carabayar);
        bukukas.setKodeKoreksi("0");
        bukukas.setAkunPn(akun);
        bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas));
        bukukas.setNoBkuRef(nobkuref);
        bukukas.setJenisPembayaran(jenisbayar);

        bkuServices.insertBkuJasaGiro(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosessimpansetorsb", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpansetorsb(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        BukuKasUmum bukukas = new BukuKasUmum();

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String nilaikeluar = (String) mapdata.get("nilaikeluar");
        final String nilaimasuk = (String) mapdata.get("nilaimasuk");
        final String filling = (String) mapdata.get("fileinbox");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");
        final String akun = (String) mapdata.get("akun");
        final Integer idbas = (Integer) mapdata.get("idbas");

        bukukas.setTahun(tahun);
        bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setTglPosting(tglpost);
        bukukas.setKodeTransaksi(kodetrans);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setJenis(jenis);
        bukukas.setBeban(beban);
        bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk));
        bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar));
        bukukas.setInboxFile(filling);
        bukukas.setNamaPptk(namapptk);
        bukukas.setNipPptk(nippptk);
        bukukas.setUraian(uraian);
        bukukas.setKodeWilayah(pengguna.getKodeProses());
        bukukas.setKodePembayaran(carabayar);
        bukukas.setKodeUangPersediaan(carabayar);
        bukukas.setKodeKoreksi("0");
        bukukas.setAkunPn(akun);
        bukukas.setIdBas(idbas);

        bkuServices.insertBkuSetorUp(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosessimpancek", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpancek(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        BukuKasUmum bukukas = new BukuKasUmum();

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String nilaikeluar = (String) mapdata.get("nilaikeluar");
        final String nilaimasuk = (String) mapdata.get("nilaimasuk");
        final String filling = (String) mapdata.get("fileinbox");
        final String akunpn = (String) mapdata.get("akunpn");
        final String akunpg = (String) mapdata.get("akunpg");
        final String nilaicek = (String) mapdata.get("nilaicek");
        final String idbaspn = (String) mapdata.get("idbaspn");
        final String idbaspg = (String) mapdata.get("idbaspg");
        final String uraianpn = (String) mapdata.get("uraianpn");
        final String uraianpg = (String) mapdata.get("uraianpg");
        final String carabayarpn = (String) mapdata.get("carabayarpn");
        final String carabayarpg = (String) mapdata.get("carabayarpg");

        bukukas.setTahun(tahun);
        bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setTglPosting(tglpost);
        bukukas.setKodeTransaksi(kodetrans);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
        bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar));
        bukukas.setInboxFile(filling);
        bukukas.setAkunPg(akunpg);
        bukukas.setAkunPn(akunpn);
        bukukas.setIdbasPg(SipkdHelpers.getIntFromString(idbaspg));
        bukukas.setIdbasPn(SipkdHelpers.getIntFromString(idbaspn));
        bukukas.setNilaiCek(SipkdHelpers.getBigDecimalFromString(nilaicek));
        bukukas.setKodeWilayah(pengguna.getKodeProses());
        bukukas.setUraianPg(uraianpg);
        bukukas.setUraianPn(uraianpn);
        bukukas.setCaraBayarPg(carabayarpg);
        bukukas.setCaraBayarPn(carabayarpn);
        bukukas.setKodeKoreksi("0");

        bkuServices.insertBkuCek(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/simpantanpakeg", method = RequestMethod.POST)
    public @ResponseBody
    String simpantanpakeg(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BukuKasUmum> listBKU = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String filling = (String) mapdata.get("fileinbox");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");

        for (Map<String, Object> mapnilailist : nilailist) {
            BukuKasUmum bukukas = new BukuKasUmum();

            bukukas.setTahun(tahun);
            bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
            bukukas.setIdEntry(pengguna.getIdPengguna());
            bukukas.setTglPosting(tglpost);
            bukukas.setKodeTransaksi(kodetrans);
            bukukas.setNoDok(nobukti);
            bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
            bukukas.setJenis(jenis);
            bukukas.setBeban(beban);
            bukukas.setInboxFile(filling);
            bukukas.setNamaPptk(namapptk);
            bukukas.setNipPptk(nippptk);
            bukukas.setUraian(uraian);
            bukukas.setKodeWilayah(pengguna.getKodeProses());
            bukukas.setKodePembayaran(carabayar);
            bukukas.setKodeUangPersediaan(carabayar);
            bukukas.setKodeKoreksi("0");

            Object nilaimasuk = mapnilailist.get("nilaimasuk");
            Object nilaikeluar = mapnilailist.get("nilaikeluar");
            Object idbas = mapnilailist.get("idbas");
            Object uraianbukti = mapnilailist.get("uraianbukti");
            Object kodeakun = mapnilailist.get("kodeakun");

            bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
            bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
            bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            bukukas.setUraianBukti(uraianbukti.toString());
            bukukas.setKodeakun(kodeakun.toString());

            listBKU.add(bukukas);
        }

        bkuServices.insertBkuTanpaKeg(listBKU);

        return "Data Buku Kas Umum Berhasil Disimpan";
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
        final String kodetransaksi = (String) mapdata.get("kodetransaksi");
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
                bukukas.setKodeTransaksi(kodetransaksi);
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

                Object nilaimasuk = mapnilailist.get("nilaimasuk");
                Object nilaikeluar = mapnilailist.get("nilaikeluar");
                Object idbas = mapnilailist.get("idbas");
                Object uraianbukti = mapnilailist.get("uraianbukti");
                Object kodeakun = mapnilailist.get("kodeakun");

                bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
                bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
                bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
                bukukas.setUraianBukti(uraianbukti.toString());
                bukukas.setKodeakun(kodeakun.toString());

                listBKU.add(bukukas);
            }

        }

        bkuServices.insertBkuSpj(listBKU);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosessimpannpd", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpannpd(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BukuKasUmum> listBKU = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String filling = (String) mapdata.get("fileinbox");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");

        for (Map<String, Object> mapnilailist : nilailist) {
            BukuKasUmum bukukas = new BukuKasUmum();

            bukukas.setTahun(tahun);
            bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
            bukukas.setIdEntry(pengguna.getIdPengguna());
            bukukas.setTglPosting(tglpost);
            bukukas.setKodeTransaksi(kodetrans);
            bukukas.setNoDok(nobukti);
            bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
            bukukas.setJenis(jenis);
            bukukas.setBeban(beban);
            bukukas.setInboxFile(filling);
            bukukas.setNamaPptk(namapptk);
            bukukas.setNipPptk(nippptk);
            bukukas.setUraian(uraian);
            bukukas.setKodeWilayah(pengguna.getKodeProses());
            bukukas.setKodePembayaran(carabayar);
            bukukas.setKodeUangPersediaan(carabayar);
            bukukas.setKodeKoreksi("0");

            Object nilaimasuk = mapnilailist.get("nilaimasuk");
            Object nilaikeluar = mapnilailist.get("nilaikeluar");
            Object idbas = mapnilailist.get("idbas");
            Object kodekegiatan = mapnilailist.get("kodekegiatan");
            Object uraianbukti = mapnilailist.get("uraianbukti");
            Object idkegiatan = mapnilailist.get("idkegiatan");
            Object kodeakun = mapnilailist.get("kodeakun");
            Object idspd = mapnilailist.get("idspd");

            bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
            bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
            bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            bukukas.setKodeKeg(kodekegiatan.toString());
            bukukas.setUraianBukti(uraianbukti.toString());
            bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan.toString()));
            bukukas.setKodeakun(kodeakun.toString());
            bukukas.setIdSpd(SipkdHelpers.getIntFromString(idspd.toString()));

            listBKU.add(bukukas);
        }

        bkuServices.insertBkuSpj(listBKU);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosessimpanpajak", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpanpajak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BukuKasUmum> listBKU = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String filling = (String) mapdata.get("fileinbox");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");
        final String kodekegiatan = (String) mapdata.get("kodekegiatan");
        final String idkegiatan = (String) mapdata.get("idkegiatan");

        for (Map<String, Object> mapnilailist : nilailist) {
            BukuKasUmum bukukas = new BukuKasUmum();

            bukukas.setTahun(tahun);
            bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
            bukukas.setIdEntry(pengguna.getIdPengguna());
            bukukas.setTglPosting(tglpost);
            bukukas.setKodeTransaksi(kodetrans);
            bukukas.setNoDok(nobukti);
            bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
            bukukas.setJenis(jenis);
            bukukas.setBeban(beban);
            bukukas.setInboxFile(filling);
            bukukas.setNamaPptk(namapptk);
            bukukas.setNipPptk(nippptk);
            bukukas.setUraian(uraian);
            bukukas.setKodeWilayah(pengguna.getKodeProses());
            bukukas.setKodePembayaran(carabayar);
            bukukas.setKodeUangPersediaan(carabayar);
            bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan.toString()));
            bukukas.setKodeKeg(kodekegiatan);
            bukukas.setKodeKoreksi("0");

            Object nilaimasuk = mapnilailist.get("nilaimasuk");
            Object nilaikeluar = mapnilailist.get("nilaikeluar");
            Object idbas = mapnilailist.get("idbas");
            Object uraianbukti = mapnilailist.get("uraianbukti");
            Object kodeakun = mapnilailist.get("kodeakun");

            bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
            bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
            bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            bukukas.setUraianBukti(uraianbukti.toString());
            bukukas.setKodeakun(kodeakun.toString());

            listBKU.add(bukukas);
        }

        bkuServices.insertBkuSpj(listBKU);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosessimpannpnm", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpannpnm(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BukuKasUmum> listBKU = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetransaksi = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String idkegiatan = (String) mapdata.get("idkegiatan");
        final String kodekegiatan = (String) mapdata.get("kodekegiatan");
        final String filling = (String) mapdata.get("fileinbox");
        final String akunpg = (String) mapdata.get("akunpg");
        final String akunpn = (String) mapdata.get("akunpn");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String idbaspn = (String) mapdata.get("idbaspn");
        final String idbaspg = (String) mapdata.get("idbaspg");
        final String uraianpn = (String) mapdata.get("uraianpn");
        final String uraianpg = (String) mapdata.get("uraianpg");
        final String carabayarpn = (String) mapdata.get("carabayarpn");
        final String carabayarpg = (String) mapdata.get("carabayarpg");

        for (Map<String, Object> mapnilailist : nilailist) {
            BukuKasUmum bukukas = new BukuKasUmum();

            Object penanda = mapnilailist.get("penanda");
            Integer valid = SipkdHelpers.getIntFromString(penanda.toString());

            if (valid == 1) {
                bukukas.setTahun(tahun);
                bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
                bukukas.setIdEntry(pengguna.getIdPengguna());
                bukukas.setTglPosting(tglpost);
                bukukas.setKodeTransaksi(kodetransaksi);
                bukukas.setNoDok(nobukti);
                bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
                bukukas.setJenis(jenis);
                bukukas.setBeban(beban);
                bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan));
                bukukas.setKodeKeg(kodekegiatan);
                bukukas.setInboxFile(filling);
                bukukas.setNamaPptk(namapptk);
                bukukas.setNipPptk(nippptk);
                bukukas.setAkunPg(akunpg);
                bukukas.setAkunPn(akunpn);
                bukukas.setIdbasPg(SipkdHelpers.getIntFromString(idbaspg));
                bukukas.setIdbasPn(SipkdHelpers.getIntFromString(idbaspn));
                bukukas.setUraian("");
                bukukas.setKodeWilayah(pengguna.getKodeProses());
                bukukas.setUraianPg(uraianpg);
                bukukas.setUraianPn(uraianpn);
                bukukas.setCaraBayarPg(carabayarpg);
                bukukas.setCaraBayarPn(carabayarpn);
                bukukas.setKodeKoreksi("0");

                log.debug(" getCaraBayarPg :::::: " + bukukas.getCaraBayarPg());
                log.debug(" getCaraBayarPn :::::: " + bukukas.getCaraBayarPn());

                Object nilainpd = mapnilailist.get("nilainpd");

                bukukas.setNilaiCek(SipkdHelpers.getBigDecimalFromString(nilainpd.toString()));

                listBKU.add(bukukas);
            }
        }

        bkuServices.insertBkuNPNM(listBKU);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/updatespj", method = RequestMethod.POST)
    public @ResponseBody
    String updatespj(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BukuKasUmum> listBKU = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetransaksi = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String idkegiatan = (String) mapdata.get("idkegiatan");
        final String kodekegiatan = (String) mapdata.get("kodekegiatan");
        final String fileinbox = (String) mapdata.get("fileinbox");
        final String nobku = (String) mapdata.get("noBKU");
        final String uraian = (String) mapdata.get("uraian");
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
                bukukas.setKodeTransaksi(kodetransaksi);
                bukukas.setNoDok(nobukti);
                bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
                bukukas.setJenis(jenis);
                bukukas.setBeban(beban);
                bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan));
                bukukas.setKodeKeg(kodekegiatan);
                bukukas.setInboxFile(fileinbox);
                bukukas.setNamaPptk(namaPPTK);
                bukukas.setNipPptk(nipPPTK);
                bukukas.setUraian(uraian);
                bukukas.setNoBKU(SipkdHelpers.getIntFromString(nobku.toString()));
                bukukas.setKodeWilayah(pengguna.getKodeProses());
                bukukas.setKodePembayaran(carabayar);
                bukukas.setKodeUangPersediaan(carabayar);
                bukukas.setKodeKoreksi("0");

                Object nilaimasuk = mapnilailist.get("nilaimasuk");
                Object nilaikeluar = mapnilailist.get("nilaikeluar");
                Object idbas = mapnilailist.get("idbas");
                Object uraianbukti = mapnilailist.get("uraianbukti");
                Object kodeakun = mapnilailist.get("kodeakun");

                bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
                bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
                bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
                bukukas.setUraianBukti(uraianbukti.toString());
                bukukas.setKodeakun(kodeakun.toString());

                listBKU.add(bukukas);
            }
        }

        bkuServices.updateBkuSpj(listBKU);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosessimpanupdate", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpanupdate(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        //final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<Map<String, Object>> nilainr = (List) mapdata.get("nilainr");
        List<BukuKasUmum> listBKU = new ArrayList<>();
        List<BukuKasUmum> nrlistBKU = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = mapdata.get("tgldok").toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String tahun = (String) mapdata.get("tahun");
        final Integer jumNR = (Integer) mapdata.get("jumNR");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String fileinbox = (String) mapdata.get("fileinbox");
        final String uraian = (String) mapdata.get("uraian");
        final String nobku = (String) mapdata.get("noBKU");
        final String carabayar = (String) mapdata.get("carabayar");

        for (Map<String, Object> mapnilailist : nilailist) {
            BukuKasUmum bukukas = new BukuKasUmum();

            bukukas.setTahun(tahun);
            bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
            bukukas.setIdEntry(pengguna.getIdPengguna());
            bukukas.setTglPosting(tglpost);
            bukukas.setKodeTransaksi(kodetrans);
            bukukas.setNoDok(nobukti);
            bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
            bukukas.setJenis(jenis);
            bukukas.setBeban(beban);
            bukukas.setNamaPptk(namapptk);
            bukukas.setNipPptk(nippptk);
            bukukas.setInboxFile(fileinbox);
            bukukas.setUraian(uraian);
            bukukas.setNoBKU(SipkdHelpers.getIntFromString(nobku.toString()));
            bukukas.setKodePembayaran(carabayar);
            bukukas.setKodeUangPersediaan(carabayar);
            bukukas.setKodeKoreksi("0");

            Object nilaimasuk = mapnilailist.get("nilaimasuk");
            Object nilaikeluar = mapnilailist.get("nilaikeluar");
            Object idbas = mapnilailist.get("idbas");
            Object kodekegiatan = mapnilailist.get("kodekegiatan");
            Object uraianbukti = mapnilailist.get("uraianbukti");
            Object idkegiatan = mapnilailist.get("idkegiatan");
            Object kodeakun = mapnilailist.get("kodeakun");
            Object idbku = mapnilailist.get("idBku");
            Object idspd = mapnilailist.get("idspd");

            log.debug("prosessimpanupdate / kode akun ============ " + kodeakun);

            bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
            bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
            bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            bukukas.setKodeKeg(kodekegiatan.toString());
            bukukas.setUraianBukti(uraianbukti.toString());
            bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan.toString()));
            bukukas.setKodeakun(kodeakun.toString());
            bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku.toString()));
            bukukas.setIdSpd(SipkdHelpers.getIntFromString(idspd.toString()));

            listBKU.add(bukukas);
        }

        bkuServices.updateBku(listBKU);

        if (jumNR > 0) {
            for (Map<String, Object> mapnilainr : nilainr) {
                BukuKasUmum bukukas = new BukuKasUmum();

                bukukas.setTahun(tahun);
                bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
                bukukas.setIdEntry(pengguna.getIdPengguna());
                bukukas.setTglPosting(tglpost);
                bukukas.setKodeTransaksi(kodetrans);
                bukukas.setNoDok(nobukti);
                bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
                bukukas.setJenis(jenis);
                bukukas.setBeban(beban);
                bukukas.setNamaPptk(namapptk);
                bukukas.setNipPptk(nippptk);
                bukukas.setInboxFile(fileinbox);
                bukukas.setUraian(uraian);
                bukukas.setNoBKU(SipkdHelpers.getIntFromString(nobku.toString()));
                bukukas.setKodeWilayah(pengguna.getKodeProses());
                bukukas.setKodePembayaran(carabayar);
                bukukas.setKodeUangPersediaan(carabayar);
                bukukas.setKodeKoreksi("0");

                Object nilaimasuk = mapnilainr.get("nilaimasuk");
                Object nilaikeluar = mapnilainr.get("nilaikeluar");
                Object idbas = mapnilainr.get("idbas");
                Object kodekegiatan = mapnilainr.get("kodekegiatan");
                Object uraianbukti = mapnilainr.get("uraianbukti");
                Object idkegiatan = mapnilainr.get("idkegiatan");
                Object kodeakun = mapnilainr.get("kodeakun");
                Object idspd = mapnilainr.get("idspd");

                bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
                bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
                bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
                bukukas.setKodeKeg(kodekegiatan.toString());
                bukukas.setUraianBukti(uraianbukti.toString());
                bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan.toString()));
                bukukas.setKodeakun(kodeakun.toString());
                bukukas.setIdSpd(SipkdHelpers.getIntFromString(idspd.toString()));

                nrlistBKU.add(bukukas);
            }
            bkuServices.insertBkuSpj(nrlistBKU);
        }

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosesupdatepajak", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdatepajak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        //final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<Map<String, Object>> nilainr = (List) mapdata.get("nilainr");
        List<BukuKasUmum> listBKU = new ArrayList<>();
        List<BukuKasUmum> nrlistBKU = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = mapdata.get("tgldok").toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String tahun = (String) mapdata.get("tahun");
        final Integer jumNR = (Integer) mapdata.get("jumNR");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String fileinbox = (String) mapdata.get("fileinbox");
        final String uraian = (String) mapdata.get("uraian");
        final String nobku = (String) mapdata.get("noBKU");
        final String carabayar = (String) mapdata.get("carabayar");
        final String kodekegiatan = (String) mapdata.get("kodekegiatan");
        final String idkegiatan = (String) mapdata.get("idkegiatan");
        final String nobkuref = (String) mapdata.get("nobkuref");

        for (Map<String, Object> mapnilailist : nilailist) {
            BukuKasUmum bukukas = new BukuKasUmum();

            bukukas.setTahun(tahun);
            bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
            bukukas.setIdEntry(pengguna.getIdPengguna());
            bukukas.setTglPosting(tglpost);
            bukukas.setKodeTransaksi(kodetrans);
            bukukas.setNoDok(nobukti);
            bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
            bukukas.setJenis(jenis);
            bukukas.setBeban(beban);
            bukukas.setNamaPptk(namapptk);
            bukukas.setNipPptk(nippptk);
            bukukas.setInboxFile(fileinbox);
            bukukas.setUraian(uraian);
            bukukas.setNoBKU(SipkdHelpers.getIntFromString(nobku.toString()));
            bukukas.setKodePembayaran(carabayar);
            bukukas.setKodeUangPersediaan(carabayar);
            bukukas.setKodeKoreksi("0");
            bukukas.setNoBkuPj(nobkuref);

            Object nilaimasuk = mapnilailist.get("nilaimasuk");
            Object nilaikeluar = mapnilailist.get("nilaikeluar");
            Object idbas = mapnilailist.get("idbas");
            Object uraianbukti = mapnilailist.get("uraianbukti");
            Object kodeakun = mapnilailist.get("kodeakun");
            Object idbku = mapnilailist.get("idBku");
            Object idbkuref = mapnilailist.get("idbkuref");

            log.debug("prosessimpanupdate / kode akun ============ " + kodeakun);

            bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
            bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
            bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            bukukas.setKodeKeg(kodekegiatan);
            bukukas.setUraianBukti(uraianbukti.toString());
            bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan));
            bukukas.setKodeakun(kodeakun.toString());
            bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku.toString()));
            bukukas.setIdBkuRef(SipkdHelpers.getIntFromString(idbkuref.toString()));

            listBKU.add(bukukas);
        }

        bkuServices.updateBkuPjPn(listBKU);

        if (jumNR > 0) {
            for (Map<String, Object> mapnilainr : nilainr) {
                BukuKasUmum bukukas = new BukuKasUmum();

                bukukas.setTahun(tahun);
                bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
                bukukas.setIdEntry(pengguna.getIdPengguna());
                bukukas.setTglPosting(tglpost);
                bukukas.setKodeTransaksi(kodetrans);
                bukukas.setNoDok(nobukti);
                bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
                bukukas.setJenis(jenis);
                bukukas.setBeban(beban);
                bukukas.setNamaPptk(namapptk);
                bukukas.setNipPptk(nippptk);
                bukukas.setInboxFile(fileinbox);
                bukukas.setUraian(uraian);
                bukukas.setNoBKU(SipkdHelpers.getIntFromString(nobku.toString()));
                bukukas.setKodeWilayah(pengguna.getKodeProses());
                bukukas.setKodePembayaran(carabayar);
                bukukas.setKodeUangPersediaan(carabayar);
                bukukas.setKodeKoreksi("0");
                bukukas.setNoBkuPj(nobkuref);

                Object nilaimasuk = mapnilainr.get("nilaimasuk");
                Object nilaikeluar = mapnilainr.get("nilaikeluar");
                Object idbas = mapnilainr.get("idbas");
                Object uraianbukti = mapnilainr.get("uraianbukti");
                Object kodeakun = mapnilainr.get("kodeakun");

                bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
                bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
                bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
                bukukas.setKodeKeg(kodekegiatan);
                bukukas.setUraianBukti(uraianbukti.toString());
                bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan));
                bukukas.setKodeakun(kodeakun.toString());

                nrlistBKU.add(bukukas);
            }
            bkuServices.insertBkuPjPn(nrlistBKU);
        }

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/simpanupdatenpd", method = RequestMethod.POST)
    public @ResponseBody
    String simpanupdatenpd(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BukuKasUmum> listBKU = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetransaksi = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String idkegiatan = (String) mapdata.get("idkegiatan");
        final String kodekegiatan = (String) mapdata.get("kodekegiatan");
        final String filling = (String) mapdata.get("fileinbox");
        final String akunpg = (String) mapdata.get("akunpg");
        final String akunpn = (String) mapdata.get("akunpn");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String nobku = (String) mapdata.get("nobku");
        final String uraianpn = (String) mapdata.get("uraianpn");
        final String uraianpg = (String) mapdata.get("uraianpg");
        final String idbaspn = (String) mapdata.get("idbaspn");
        final String idbaspg = (String) mapdata.get("idbaspg");
        final String carabayarpn = (String) mapdata.get("carabayarpn");
        final String carabayarpg = (String) mapdata.get("carabayarpg");

        for (Map<String, Object> mapnilailist : nilailist) {
            BukuKasUmum bukukas = new BukuKasUmum();

            Object penanda = mapnilailist.get("penanda");
            Integer valid = SipkdHelpers.getIntFromString(penanda.toString());

            if (valid == 1) {
                bukukas.setTahun(tahun);
                bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
                bukukas.setIdEntry(pengguna.getIdPengguna());
                bukukas.setTglPosting(tglpost);
                bukukas.setKodeTransaksi(kodetransaksi);
                bukukas.setNoDok(nobukti);
                bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
                bukukas.setJenis(jenis);
                bukukas.setBeban(beban);
                bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan));
                bukukas.setKodeKeg(kodekegiatan);
                bukukas.setInboxFile(filling);
                bukukas.setNamaPptk(namapptk);
                bukukas.setNipPptk(nippptk);
                bukukas.setAkunPg(akunpg);
                bukukas.setAkunPn(akunpn);
                bukukas.setUraian("");
                bukukas.setKodeWilayah(pengguna.getKodeProses());
                bukukas.setNoBKU(SipkdHelpers.getIntFromString(nobku));
                bukukas.setUraianPn(uraianpn);
                bukukas.setUraianPg(uraianpg);
                bukukas.setCaraBayarPg(carabayarpg);
                bukukas.setCaraBayarPn(carabayarpn);
                bukukas.setKodeKoreksi("0");
                bukukas.setIdbasPg(SipkdHelpers.getIntFromString(idbaspg));
                bukukas.setIdbasPn(SipkdHelpers.getIntFromString(idbaspn));

                Object nilainpd = mapnilailist.get("nilainpd");

                bukukas.setNilaiCek(SipkdHelpers.getBigDecimalFromString(nilainpd.toString()));

                listBKU.add(bukukas);
            }
        }

        bkuServices.updateBkuNPNM(listBKU);

        /*for (Map<String, Object> mapnilailist : nilailist) {
         BukuKasUmum bukukas = new BukuKasUmum();

         bukukas.setTahun(tahun);
         bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
         bukukas.setIdEntry(pengguna.getIdPengguna());
         bukukas.setTglPosting(tglpost);
         bukukas.setKodeTransaksi(kodetransaksi);
         bukukas.setNoDok(nobukti);
         bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
         bukukas.setJenis(jenis);
         bukukas.setBeban(beban);
         bukukas.setNamaPptk(namapptk);
         bukukas.setNipPptk(nippptk);
         bukukas.setInboxFile(filling);
         bukukas.setNoBKU(SipkdHelpers.getIntFromString(nobku));
         bukukas.setAkunPn(akunpn);
         bukukas.setAkunPg(akunpg);
         bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan));
         bukukas.setKodeKeg(kodekegiatan);
         bukukas.setNilaiCek(SipkdHelpers.getBigDecimalFromString(nilainpd.toString()));

         Object nilaimasuk = mapnilailist.get("nilaimasuk");
         Object nilaikeluar = mapnilailist.get("nilaikeluar");
         Object idbas = mapnilailist.get("idbas");
         Object kodekegiatan = mapnilailist.get("kodekegiatan");
         Object uraianbukti = mapnilailist.get("uraianbukti");
         Object idkegiatan = mapnilailist.get("idkegiatan");
         Object kodeakun = mapnilailist.get("kodeakun");
         Object idbku = mapnilailist.get("idBku");

         bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
         bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
         bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
         bukukas.setKodeKeg(kodekegiatan.toString());
         bukukas.setUraianBukti(uraianbukti.toString());
         bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan.toString()));
         bukukas.setKodeakun(kodeakun.toString());
         bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku.toString()));

         listBKU.add(bukukas);
         }

         log.debug("Jumlah NR == " + jumNR);

         if ("NP".equals(kodetrans)) {
         bkuServices.updateBkuNP(listBKU);

         } else if ("NM".equals(kodetrans)) {
         bkuServices.updateBkuNM(listBKU);
         }

         if (jumNR > 0) {

         for (Map<String, Object> mapnilainr : nilainr) {
         BukuKasUmum bukukas = new BukuKasUmum();

         bukukas.setTahun(tahun);
         bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
         bukukas.setIdEntry(pengguna.getIdPengguna());
         bukukas.setTglPosting(tglpost);
         bukukas.setKodeTransaksi(kodetrans);
         bukukas.setNoDok(nobukti);
         bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
         bukukas.setJenis(jenis);
         bukukas.setBeban(beban);
         bukukas.setNamaPptk(namapptk);
         bukukas.setNipPptk(nippptk);
         bukukas.setInboxFile(fileinbox);
         bukukas.setUraian(uraian);
         bukukas.setNoBKU(SipkdHelpers.getIntFromString(nobku.toString()));
         bukukas.setAkunPn(akuntunai);
         bukukas.setNilaiCek(SipkdHelpers.getBigDecimalFromString(nilainpd.toString()));
         bukukas.setKodeWilayah(pengguna.getKodeProses());

         Object nilaimasuk = mapnilainr.get("nilaimasuk");
         Object nilaikeluar = mapnilainr.get("nilaikeluar");
         Object idbas = mapnilainr.get("idbas");
         Object kodekegiatan = mapnilainr.get("kodekegiatan");
         Object uraianbukti = mapnilainr.get("uraianbukti");
         Object idkegiatan = mapnilainr.get("idkegiatan");
         Object kodeakun = mapnilainr.get("kodeakun");

         bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
         bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
         bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
         bukukas.setKodeKeg(kodekegiatan.toString());
         bukukas.setUraianBukti(uraianbukti.toString());
         bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan.toString()));
         bukukas.setKodeakun(kodeakun.toString());

         nrlistBKU.add(bukukas);

         }

         if ("NP".equals(kodetrans)) {
         bkuServices.insertBkuNP(nrlistBKU);

         } else if ("NM".equals(kodetrans)) {
         bkuServices.insertBkuNM(nrlistBKU);
         }

         }*/
        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/simpanupdatels2", method = RequestMethod.POST)
    public @ResponseBody
    String simpanupdatels2(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<Map<String, Object>> nilainr = (List) mapdata.get("nilainr");
        List<BukuKasUmum> listBKU = new ArrayList<>();
        List<BukuKasUmum> nrlistBKU = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = mapdata.get("tgldok").toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String tahun = (String) mapdata.get("tahun");
        final Integer jumNR = (Integer) mapdata.get("jumNR");
        final String fileinbox = (String) mapdata.get("fileinbox");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String uraian = (String) mapdata.get("uraian");
        final String nobku = (String) mapdata.get("noBKU");
        final String carabayar = (String) mapdata.get("carabayar");
        final String idkegiatan = (String) mapdata.get("idkeg");
        final String kodekegiatan = (String) mapdata.get("kodekeg");

        for (Map<String, Object> mapnilailist : nilailist) {
            BukuKasUmum bukukas = new BukuKasUmum();

            bukukas.setTahun(tahun);
            bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
            bukukas.setIdEntry(pengguna.getIdPengguna());
            bukukas.setTglPosting(tglpost);
            bukukas.setKodeTransaksi(kodetrans);
            bukukas.setNoDok(nobukti);
            bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
            bukukas.setJenis(jenis);
            bukukas.setBeban(beban);
            bukukas.setInboxFile(fileinbox);
            bukukas.setNamaPptk(namapptk);
            bukukas.setNipPptk(nippptk);
            bukukas.setUraian(uraian);
            bukukas.setNoBKU(SipkdHelpers.getIntFromString(nobku.toString()));
            bukukas.setKodePembayaran(carabayar);
            bukukas.setKodeUangPersediaan(carabayar);
            bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan.toString()));
            bukukas.setKodeKeg(kodekegiatan);
            bukukas.setKodeKoreksi("0");

            Object nilaimasuk = mapnilailist.get("nilaimasuk");
            Object nilaikeluar = mapnilailist.get("nilaikeluar");
            Object idbas = mapnilailist.get("idbas");
            Object uraianbukti = mapnilailist.get("uraianbukti");
            Object kodeakun = mapnilailist.get("kodeakun");
            Object idbku = mapnilailist.get("idBku");

            bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
            bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
            bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            bukukas.setUraianBukti(uraianbukti.toString());
            bukukas.setKodeakun(kodeakun.toString());
            bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku.toString()));

            listBKU.add(bukukas);
        }

        bkuServices.updateBkuLs2(listBKU);

        if (jumNR > 0) {
            for (Map<String, Object> mapnilainr : nilainr) {
                BukuKasUmum bukukas = new BukuKasUmum();

                bukukas.setTahun(tahun);
                bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
                bukukas.setIdEntry(pengguna.getIdPengguna());
                bukukas.setTglPosting(tglpost);
                bukukas.setKodeTransaksi(kodetrans);
                bukukas.setNoDok(nobukti);
                bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
                bukukas.setJenis(jenis);
                bukukas.setBeban(beban);
                bukukas.setInboxFile(fileinbox);
                bukukas.setNamaPptk(namapptk);
                bukukas.setNipPptk(nippptk);
                bukukas.setUraian(uraian);
                bukukas.setNoBKU(SipkdHelpers.getIntFromString(nobku.toString()));
                bukukas.setKodeWilayah(pengguna.getKodeProses());
                bukukas.setKodePembayaran(carabayar);
                bukukas.setKodeUangPersediaan(carabayar);
                bukukas.setKodeKoreksi("0");

                Object nilaimasuk = mapnilainr.get("nilaimasuk");
                Object nilaikeluar = mapnilainr.get("nilaikeluar");
                Object idbas = mapnilainr.get("idbas");
                Object uraianbukti = mapnilainr.get("uraianbukti");
                Object kodeakun = mapnilainr.get("kodeakun");

                bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
                bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
                bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
                bukukas.setUraianBukti(uraianbukti.toString());
                bukukas.setKodeakun(kodeakun.toString());

                nrlistBKU.add(bukukas);

            }
            bkuServices.insertBkuTanpaKeg(nrlistBKU);

        }

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosesupdatesetorup", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdatesetorup(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = mapdata.get("tgldok").toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String tahun = (String) mapdata.get("tahun");
        final String pengeluaran = (String) mapdata.get("nilaikeluar");
        final String idbku = (String) mapdata.get("idbku");
        final String fileinbox = (String) mapdata.get("fileinbox");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");
        final String akun = (String) mapdata.get("akun");
        final Integer idbas = (Integer) mapdata.get("idbas");

        BukuKasUmum bukukas = new BukuKasUmum();

        bukukas.setTahun(tahun);
        bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setTglPosting(tglpost);
        bukukas.setKodeTransaksi(kodetrans);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setJenis(jenis);
        bukukas.setBeban(beban);
        bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(pengeluaran));
        bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku));
        bukukas.setInboxFile(fileinbox);
        bukukas.setNamaPptk(namapptk);
        bukukas.setNipPptk(nippptk);
        bukukas.setUraian(uraian);
        bukukas.setKodePembayaran(carabayar);
        bukukas.setKodeUangPersediaan(carabayar);
        bukukas.setKodeKoreksi("0");
        bukukas.setAkunPn(akun);
        bukukas.setIdBas(idbas);

        bkuServices.updateBkuSetorUp(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosesupdatejasagiro", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdatejasagiro(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = mapdata.get("tgldok").toString();
        final String tahun = (String) mapdata.get("tahun");
        final String nilaikeluar = (String) mapdata.get("nilaikeluar");
        final String nilaimasuk = (String) mapdata.get("nilaimasuk");
        final String idbku = (String) mapdata.get("idbku");
        final String fileinbox = (String) mapdata.get("fileinbox");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");
        final String nobkuref = (String) mapdata.get("nobkuref");
        final String jenisbayar = (String) mapdata.get("jenisbayar");

        BukuKasUmum bukukas = new BukuKasUmum();

        bukukas.setTahun(tahun);
        bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setTglPosting(tglpost);
        bukukas.setKodeTransaksi(kodetrans);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar));
        bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk));
        bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku));
        bukukas.setInboxFile(fileinbox);
        bukukas.setUraian(uraian);
        bukukas.setKodePembayaran(carabayar);
        bukukas.setKodeUangPersediaan(carabayar);
        bukukas.setKodeKoreksi("0");
        bukukas.setNoBkuRef(nobkuref);
        bukukas.setJenisPembayaran(jenisbayar);

        bkuServices.updateBkuJasaGiro(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosesupdatebpjs", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdatebpjs(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = mapdata.get("tgldok").toString();
        final String tahun = (String) mapdata.get("tahun");
        final String nilaikeluar = (String) mapdata.get("nilaikeluar");
        final String nilaimasuk = (String) mapdata.get("nilaimasuk");
        final String idbku = (String) mapdata.get("idbku");
        final String fileinbox = (String) mapdata.get("fileinbox");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");
        final String akun = (String) mapdata.get("akun");
        final String idbas = (String) mapdata.get("idbas");
        final String nobkuref = (String) mapdata.get("nobkuref");
        final String jenisbayar = (String) mapdata.get("jenisbayar");

        BukuKasUmum bukukas = new BukuKasUmum();

        bukukas.setTahun(tahun);
        bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setTglPosting(tglpost);
        bukukas.setKodeTransaksi(kodetrans);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar));
        bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk));
        bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku));
        bukukas.setInboxFile(fileinbox);
        bukukas.setUraian(uraian);
        bukukas.setKodePembayaran(carabayar);
        bukukas.setKodeUangPersediaan(carabayar);
        bukukas.setKodeKoreksi("0");
        bukukas.setAkunPn(akun);
        bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas));
        bukukas.setNoBkuRef(nobkuref);
        bukukas.setJenisPembayaran(jenisbayar);

        bkuServices.updateBkuBpjs(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosesupdatelainlain", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdatelainlain(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = mapdata.get("tgldok").toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String tahun = (String) mapdata.get("tahun");
        final String nilaimasuk = (String) mapdata.get("nilaimasuk");
        final String nilaikeluar = (String) mapdata.get("nilaikeluar");
        final String idbku = (String) mapdata.get("idbku");
        final String fileinbox = (String) mapdata.get("fileinbox");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");
        final String akun = (String) mapdata.get("akun");
        final Integer idbas = (Integer) mapdata.get("idbas");

        BukuKasUmum bukukas = new BukuKasUmum();

        bukukas.setTahun(tahun);
        bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setTglPosting(tglpost);
        bukukas.setKodeTransaksi(kodetrans);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setJenis(jenis);
        bukukas.setBeban(beban);
        bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar));
        bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk));
        bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku));
        bukukas.setInboxFile(fileinbox);
        bukukas.setNamaPptk(namapptk);
        bukukas.setNipPptk(nippptk);
        bukukas.setUraian(uraian);
        bukukas.setKodePembayaran(carabayar);
        bukukas.setKodeUangPersediaan(carabayar);
        bukukas.setKodeKoreksi("0");
        bukukas.setAkunPn(akun);
        bukukas.setIdBas(idbas);

        bkuServices.updateBkuSetorUp(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosesupdatesp2d", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdatesp2d(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String idskpd = (String) mapdata.get("idskpd");
        final String tahun = (String) mapdata.get("tahun");
        final String idtransaksi = (String) mapdata.get("idtransaksi");
        final String fileinbox = (String) mapdata.get("fileinbox");
        final String nobku = (String) mapdata.get("nobku");

        BukuKasUmum bukukas = new BukuKasUmum();
        log.debug("prosesupdatesp2d **** nobku ========== " + nobku);
        bukukas.setTahun(tahun);
        bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
        bukukas.setIdTransaksi(idtransaksi);
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setInboxFile(fileinbox);
        bukukas.setNoBKU(SipkdHelpers.getIntFromString(nobku));

        bkuServices.updateBkuSp2d(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosesupdatecek", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdatecek(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = mapdata.get("tgldok").toString();
        final String tahun = (String) mapdata.get("tahun");
        //final String nilaimasuk = (String) mapdata.get("nilaimasuk");
        //final String nilaikeluar = (String) mapdata.get("nilaikeluar");
        final String idbku = (String) mapdata.get("idbku");
        final String fileinbox = (String) mapdata.get("fileinbox");
        final String akunpn = (String) mapdata.get("akunpn");
        final String akunpg = (String) mapdata.get("akunpg");
        final String nilaicek = (String) mapdata.get("nilaicek");
        final String nobku = (String) mapdata.get("nobku");
        final String uraianpn = (String) mapdata.get("uraianpn");
        final String uraianpg = (String) mapdata.get("uraianpg");

        BukuKasUmum bukukas = new BukuKasUmum();

        bukukas.setTahun(tahun);
        bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setTglPosting(tglpost);
        bukukas.setKodeTransaksi(kodetrans);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        //bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar));
        //bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk));
        bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku));
        bukukas.setInboxFile(fileinbox);
        bukukas.setAkunPg(akunpg);
        bukukas.setAkunPn(akunpn);
        bukukas.setNilaiCek(SipkdHelpers.getBigDecimalFromString(nilaicek));
        bukukas.setNoBKU(SipkdHelpers.getIntFromString(nobku));
        bukukas.setKodeWilayah(pengguna.getKodeProses());
        bukukas.setUraianPg(uraianpg);
        bukukas.setUraianPn(uraianpn);
        bukukas.setKodeKoreksi("0");

        bkuServices.updateBkuCek(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/getTotal", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> totalAll(final HttpServletRequest request) {
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String bulan = request.getParameter("bulan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("bulan", bulan);

        final Map< String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuServices.getNilaiIndex(param));

        return mapData;
    }

    @RequestMapping(value = "/editbku/", method = RequestMethod.GET)
    public ModelAndView edit(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        //final String noBukti = request.getParameter("noBukti");
        final String noBku = request.getParameter("noBKU");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        //param.put("nobukti", noBukti);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);
        //param.put("tglPosting", tglPosting);
        //param.put("kotransaksi", kodeTransaksi);
        param.put("noBku", noBku);

        final BukuKasUmum bku = bkuServices.getBkuEdit(param);

        return new ModelAndView("bku/editbku", "refbku", bku);
    }

    @RequestMapping(value = "/editbkupajak/", method = RequestMethod.GET)
    public ModelAndView editbkupajak(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String noBku = request.getParameter("noBKU");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);
        param.put("noBku", noBku);
        param.put("nobku", noBku); // jangan dihapus, untuk param getNilaiBkuPg

        final BukuKasUmum bku = bkuServices.getBkuEditPajak(param);

        final String saldoawal = bku.getNoBkuPj();

        if ("-1".equals(saldoawal)) {
            bku.setNilaiBku(bkuServices.getNilaiBkuPg(param).getNilaiPajak());
        }

        return new ModelAndView("bku/editbkupajak", "refbku", bku);
    }

    @RequestMapping(value = "/editbkupajakkoreksi/", method = RequestMethod.GET)
    public ModelAndView editbkupajakkoreksi(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String noBku = request.getParameter("noBKU");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);
        param.put("noBku", noBku);

        final BukuKasUmum bku = bkuServices.getBkuEditPajak(param);

        return new ModelAndView("bku/editbkupajakkoreksi", "refbku", bku);
    }

    @RequestMapping(value = "/editbkunpd/{kodeTransaksi}", method = RequestMethod.GET)
    public ModelAndView editbkunpd(@PathVariable String kodeTransaksi, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String nobku = request.getParameter("nobku");
        //final String akuntunai = "1.1.1.03.02"; // akun tunai

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }
        log.debug("tahun ===================== " + tahun);
        final Map< String, Object> param = new HashMap<String, Object>(3);
        //param.put("nobukti", noBukti);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);
        //param.put("tglPosting", tglPosting);
        param.put("kotransaksi", kodeTransaksi);
        param.put("nobku", nobku);
        //param.put("akuntunai", akuntunai);

        final BukuKasUmum bku = bkuServices.getBkuEditNPD(param);

        return new ModelAndView("bku/editbkunpnm", "refbku", bku);
    }

    @RequestMapping(value = "/editbkuspj/{noBKU}", method = RequestMethod.GET)
    public ModelAndView editbkuspj(@PathVariable Integer noBKU, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("nobku", noBKU);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);
        //param.put("tglPosting", tglPosting);

        final BukuKasUmum bku = bkuServices.getBkuSPJEdit(param);

        return new ModelAndView("bku/editbkuspj", "refbku", bku);
    }

    @RequestMapping(value = "/editbkuls/{kodeTransaksi}", method = RequestMethod.GET)
    public ModelAndView editbkuls(@PathVariable String kodeTransaksi, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String noBku = request.getParameter("noBKU");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        //param.put("nobukti", noBukti);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);
        //param.put("tglPosting", tglPosting);
        param.put("kotransaksi", kodeTransaksi);
        param.put("noBku", noBku);

        final BukuKasUmum bku = bkuServices.getBkuEdit(param);

        return new ModelAndView("bku/editbkuls", "refbku", bku);
    }

    @RequestMapping(value = "/editbkuls2/{kodeTransaksi}", method = RequestMethod.GET)
    public ModelAndView editbkuls2(@PathVariable String kodeTransaksi, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String noBku = request.getParameter("noBKU");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        //param.put("nobukti", noBukti);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);
        //param.put("tglPosting", tglPosting);
        param.put("kotransaksi", kodeTransaksi);
        param.put("noBku", noBku);

        final BukuKasUmum bku = bkuServices.getBkuEdit(param);

        return new ModelAndView("bku/editbkuls2", "refbku", bku);
    }

    @RequestMapping(value = "/editbkustup/{idBku}", method = RequestMethod.GET)
    public ModelAndView editbkustup(@PathVariable Integer idBku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idBku);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditStUp(param);

        return new ModelAndView("bku/editbkustup", "refbku", bku);
    }

    @RequestMapping(value = "/editbkujasagiro/{idBku}", method = RequestMethod.GET)
    public ModelAndView editbkujasagiro(@PathVariable Integer idBku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idBku);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditStUp(param);

        return new ModelAndView("bku/editbkujasagiro", "refbku", bku);
    }

    @RequestMapping(value = "/editbkubpjs/{idBku}", method = RequestMethod.GET)
    public ModelAndView editbkubpjs(@PathVariable Integer idBku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idBku);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditStUp(param);

        return new ModelAndView("bku/editbkubpjs", "refbku", bku);
    }

    @RequestMapping(value = "/editbkulainlain/{idBku}", method = RequestMethod.GET)
    public ModelAndView editbkulainlain(@PathVariable Integer idBku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idBku);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditLainLain(param);

        return new ModelAndView("bku/editbkulainlain", "refbku", bku);
    }

    @RequestMapping(value = "/editbkukasmasuk/{idBku}", method = RequestMethod.GET)
    public ModelAndView editbkukasmasuk(@PathVariable Integer idBku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idBku);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditStUp(param);

        return new ModelAndView("bku/editbkukasmasuk", "refbku", bku);
    }

    @RequestMapping(value = "/editbkucek/{noBKU}", method = RequestMethod.GET)
    public ModelAndView editbkucek(@PathVariable Integer noBKU, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("noBKU", noBKU);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditCek(param);

        return new ModelAndView("bku/editbkucek", "refbku", bku);
    }

    @RequestMapping(value = "/editbkusp2d/{idTransaksi}/{noBKU}", method = RequestMethod.GET)
    public ModelAndView editbkusp2d(@PathVariable Integer idTransaksi, @PathVariable Integer noBKU, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        log.debug("editbkusp2d -- nobku ================= " + noBKU);
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idtransaksi", idTransaksi);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);
        param.put("nobku", noBKU);

        final BukuKasUmum bku = bkuServices.getBkuEditSp2d(param);

        return new ModelAndView("bku/editbkusp2d", "refbku", bku);
    }

    @RequestMapping(value = "/listkegiatan", method = RequestMethod.GET)
    public ModelAndView listakunbukubesar(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bku/listkegiatan", "refkegiatan", bku);
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
        final String jenistrx = request.getParameter("jenistrx");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("nama", nama);
        param.put("kode", kode);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        if ("SPJ".equals(keterangan)) {
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

        } else if ("BANTUAN".equals(keterangan)) {
            final long banyak = bkuServices.getBanyakKegiatanBantuan(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getKegiatanBantuan(param));

        } else if ("BL".equals(keterangan)) {
            final long banyak = bkuServices.getBanyakKegiatanBl(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getKegiatanBl(param));
        }

        return mapData;
    }

    @RequestMapping(value = "/listakun", method = RequestMethod.GET)
    public ModelAndView listakun(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bku/listakun", "refkegiatan", bku);
    }

    @RequestMapping(value = "/listkegiatannm", method = RequestMethod.GET)
    public ModelAndView listkegiatannm(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bku/listkegiatanNM", "refkegiatan", bku);
    }

    @RequestMapping(value = "/listkegiatannp", method = RequestMethod.GET)
    public ModelAndView listkegiatannp(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bku/listkegiatanNP", "refkegiatan", bku);
    }

    @RequestMapping(value = "/listkegiatansetoran", method = RequestMethod.GET)
    public ModelAndView listkegiatansetoran(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bku/listkegiatanSetoran", "refkegiatan", bku);
    }

    @RequestMapping(value = "/listkegiatansetoranTU", method = RequestMethod.GET)
    public ModelAndView listkegiatansetoranTU(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bku/listkegiatanSetoranTU", "refkegiatan", bku);
    }

    @RequestMapping(value = "/json/listkegiatanNPDjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listkegiatanNPDjson(final HttpServletRequest request) {

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
        final String beban = request.getParameter("beban");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("nama", nama);
        param.put("kode", kode);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("beban", beban);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        if ("NP".equals(keterangan)) {
            final long banyak = bkuServices.getBanyakKegiatanNP(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getKegiatanNP(param));

        } else if ("NM".equals(keterangan)) {
            final long banyak = bkuServices.getBanyakKegiatanNM(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getKegiatanNM(param));

        } else if ("SETORAN".equals(keterangan)) {

            if ("TU".equals(beban)) {
                final long banyak = bkuServices.getBanyakKegiatanSetorTU(param);
                mapData.put("sEcho", request.getParameter("sEcho"));
                mapData.put("iTotalRecords", banyak);
                mapData.put("iTotalDisplayRecords", banyak);
                mapData.put("aaData", bkuServices.getKegiatanSetorTU(param));
            } else {
                final long banyak = bkuServices.getBanyakKegiatanByBeban(param);
                mapData.put("sEcho", request.getParameter("sEcho"));
                mapData.put("iTotalRecords", banyak);
                mapData.put("iTotalDisplayRecords", banyak);
                mapData.put("aaData", bkuServices.getKegiatanByBeban(param));
            }

        }

        return mapData;
    }

    @RequestMapping(value = "/json/listakun", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listakun(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kode = request.getParameter("kode");
        final String nama = request.getParameter("nama");
        final String saldoawal = request.getParameter("saldoawal");
        //final String idskpd = request.getParameter("idskpd");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("nama", nama);
        param.put("kode", kode);
        param.put("saldoawal", saldoawal);
        //param.put("idskpd", idskpd);
        log.debug("CEK SALDO AWAL AKUN ==================== " + saldoawal);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakAkunLain(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getAkunLain(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getAkunCombo", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAkunCombo(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String keterangan = request.getParameter("keterangan");
        final String beban = request.getParameter("beban");
        final String idspd = request.getParameter("idspd");
        String pajak = request.getParameter("pajak");

        if ("".equals(pajak)) {
            pajak = "BUKANPAJAK";
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("pajak", pajak);
        param.put("idkegiatan", idkegiatan);
        param.put("idspd", idspd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        if ("SPJ".equals(keterangan)) {
            mapData.put("aData", bkuServices.getAkunCombo(param));

        } else if ("BTL".equals(keterangan)) {
            mapData.put("aData", bkuServices.getAkunComboBtl(param));

        } else if ("BANTUAN".equals(keterangan)) {
            mapData.put("aData", bkuServices.getAkunComboBantuan(param));

        } else if ("BL".equals(keterangan)) {
            if ("TU".equals(beban)) {
                mapData.put("aData", bkuServices.getAkunComboTU(param));
            } else {
                mapData.put("aData", bkuServices.getAkunComboBl(param));
            }

        } else if ("BIAYA".equals(keterangan)) {
            mapData.put("aData", bkuServices.getAkunComboBiaya(param));
        }

        return mapData;
    }

    @RequestMapping(value = "/json/getSkpdBl", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSkpdBl(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuServices.getSkpdBl(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getSkpdBtl", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSkpdBtl(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getSkpdBtl(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getBanyakEdit", method = RequestMethod.GET)
    public @ResponseBody
    Integer getBanyakEdit(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahun = request.getParameter("tahun");
        final String keterangan = request.getParameter("ket");
        final String nobukti = request.getParameter("nobukti");
        final String tglpost = request.getParameter("tglpos");
        final String nobku = request.getParameter("nobku");
        final String akuntunai = "1.1.1.03.02";
        final Integer hasil;

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("nobukti", nobukti);
        param.put("tglposting", tglpost);
        param.put("nobku", nobku);
        param.put("akuntunai", akuntunai);

        if ("LS2".equals(keterangan)) {
            hasil = bkuServices.getBanyakRinciEditLs2(param);

        } else if ("NPD".equals(keterangan)) {
            hasil = bkuServices.getBanyakRinciNpdEdit(param);
        } else {
            hasil = bkuServices.getBanyakRinciSpjEdit(param);
        }

        return hasil;
    }

    @RequestMapping(value = "/json/valtabel", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> valtabel(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahun = request.getParameter("tahun");
        final String keterangan = request.getParameter("ket");
        final String nobukti = request.getParameter("nobukti");
        final String tglpost = request.getParameter("tglpos");
        final String nobku = request.getParameter("nobku");
        final String akuntunai = "1.1.1.03.02";

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("nobukti", nobukti);
        param.put("tglposting", tglpost);
        param.put("nobku", nobku);
        param.put("akuntunai", akuntunai);

        final Map< String, Object> mapData = new HashMap<String, Object>(2);

        log.debug("keterangan val tabel ====== " + keterangan);
        if ("LS2".equals(keterangan)) {
            mapData.put("aData", bkuServices.getRinciEditLs2(param));

        } else if ("NPD".equals(keterangan)) {
            mapData.put("aData", bkuServices.getRinciNpdEdit(param));

        } else if ("PJPN".equals(keterangan)) {
            mapData.put("aData", bkuServices.getRinciPjPn(param));

        } else {
            mapData.put("aData", bkuServices.getRinciSpjEdit(param));

        }

        return mapData;

    }

    @RequestMapping(value = "/json/getStatusSuadana", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getStatusSuadana(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getStatusSuadana(param));

        return mapData;
    }

    @RequestMapping(value = "/json/hapusdatalama", method = RequestMethod.POST)
    public @ResponseBody
    String hapusdatalama(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BukuKasUmum> listBKU = new ArrayList<>();

        // final String banyakdata = (String) mapdata.get("banyakdata");
        for (Map<String, Object> mapnilailist : nilailist) {
            BukuKasUmum bukukas = new BukuKasUmum();

            Object idBku = mapnilailist.get("idBku");
            bukukas.setTahun(tahun);
            bukukas.setIdBku(SipkdHelpers.getIntFromString(idBku.toString()));

            listBKU.add(bukukas);

        }

        bkuServices.deleteById(listBKU);

        return "Data Lama Berhasil Dihapus";
    }

    @RequestMapping(value = "/json/getNilaiSisaSpj", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getNilaiSisaSpj(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String nobku = request.getParameter("nobku");
        final String idskpd = request.getParameter("idskpd");
        final String beban = request.getParameter("beban");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String bulan = request.getParameter("bulan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("nobku", nobku);
        param.put("beban", beban);
        param.put("idkegiatan", idkegiatan);
        param.put("bulan", bulan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getNilaiSisaSpj(param));

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
            log.debug("************************ getNilaiValidasiSisaSpj UP");
            mapData.put("aData", bkuServices.getNilaiValidasiSisaSpj(param));

        } else if ("TU".equals(beban)) {
            log.debug("************************ getNilaiValidasiSisaSpj TU");
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

    @RequestMapping(value = "/json/updatespjkoreksi", method = RequestMethod.POST)
    public @ResponseBody
    String updatespjkoreksi(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        final String idskpd = (String) mapdata.get("idskpd");
        final String fileinbox = (String) mapdata.get("fileinbox");
        final String nobku = (String) mapdata.get("nobku");
        BukuKasUmum bukukas = new BukuKasUmum();

        bukukas.setTahun(tahun);
        bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setInboxFile(fileinbox);
        bukukas.setNoBKU(SipkdHelpers.getIntFromString(nobku));

        bkuServices.updateBkuSpjKoreksi(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/editbkuspjkoreksi/{noBKU}", method = RequestMethod.GET)
    public ModelAndView editbkuspjkoreksi(@PathVariable Integer noBKU, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("nobku", noBKU);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);
        //param.put("tglPosting", tglPosting);

        final BukuKasUmum bku = bkuServices.getBkuSPJEdit(param);

        return new ModelAndView("bku/editbkuspjkoreksi", "refbku", bku);
    }

    @RequestMapping(value = "/json/getSetorUPSemester", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSetorUPSemester(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String semester = request.getParameter("semester");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("semester", semester);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getSetorUPSemester(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getBendahara", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getBendahara(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getBendahara(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getWilayahByLogin", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getWilayahByLogin(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String kodewilayah = pengguna.getKodeProses();

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("wilayah", kodewilayah);
        param.put("ket", "login");

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getWilayah(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getSaldoAwal", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSaldoAwal(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getSaldoAwal(param));

        return mapData;
    }

    @RequestMapping(value = "/monitoringbku/", method = RequestMethod.GET)
    public ModelAndView monitoringbku(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        //final String noBukti = request.getParameter("noBukti");
        final String noBku = request.getParameter("noBKU");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);
        param.put("noBku", noBku);

        final BukuKasUmum bku = bkuServices.getBkuEdit(param);

        return new ModelAndView("monitoringbku/editbku", "refbku", bku);
    }

    @RequestMapping(value = "/monitoringbkunpd/{kodeTransaksi}", method = RequestMethod.GET)
    public ModelAndView monitoringbkunpd(@PathVariable String kodeTransaksi, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String nobku = request.getParameter("nobku");
        final String akuntunai = "1.1.1.03.02"; // akun tunai

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);
        param.put("kotransaksi", kodeTransaksi);
        param.put("nobku", nobku);

        final BukuKasUmum bku = bkuServices.getBkuEditNPD(param);

        return new ModelAndView("monitoringbku/editbkunpnm", "refbku", bku);
    }

    @RequestMapping(value = "/monitoringbkuspj/{noBKU}", method = RequestMethod.GET)
    public ModelAndView monitoringbkuspj(@PathVariable Integer noBKU, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("nobku", noBKU);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);
        //param.put("tglPosting", tglPosting);

        final BukuKasUmum bku = bkuServices.getBkuSPJEdit(param);

        return new ModelAndView("monitoringbku/editbkuspj", "refbku", bku);
    }

    @RequestMapping(value = "/monitoringbkuls/{kodeTransaksi}", method = RequestMethod.GET)
    public ModelAndView monitoringbkuls(@PathVariable String kodeTransaksi, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String noBku = request.getParameter("noBKU");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        //param.put("nobukti", noBukti);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);
        //param.put("tglPosting", tglPosting);
        param.put("kotransaksi", kodeTransaksi);
        param.put("noBku", noBku);

        final BukuKasUmum bku = bkuServices.getBkuEdit(param);

        return new ModelAndView("monitoringbku/editbkuls", "refbku", bku);
    }

    @RequestMapping(value = "/monitoringbkuls2/{kodeTransaksi}", method = RequestMethod.GET)
    public ModelAndView monitoringbkuls2(@PathVariable String kodeTransaksi, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String noBku = request.getParameter("noBKU");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);
        param.put("kotransaksi", kodeTransaksi);
        param.put("noBku", noBku);

        final BukuKasUmum bku = bkuServices.getBkuEdit(param);

        return new ModelAndView("monitoringbku/editbkuls2", "refbku", bku);
    }

    @RequestMapping(value = "/monitoringbkustup/{idBku}", method = RequestMethod.GET)
    public ModelAndView monitoringbkustup(@PathVariable Integer idBku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idBku);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditStUp(param);

        return new ModelAndView("monitoringbku/editbkustup", "refbku", bku);
    }

    @RequestMapping(value = "/monitoringbkujasagiro/{idBku}", method = RequestMethod.GET)
    public ModelAndView monitoringbkujasagiro(@PathVariable Integer idBku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idBku);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditStUp(param);

        return new ModelAndView("monitoringbku/editbkujasagiro", "refbku", bku);
    }

    @RequestMapping(value = "/monitoringbkubpjs/{idBku}", method = RequestMethod.GET)
    public ModelAndView monitoringbkubpjs(@PathVariable Integer idBku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idBku);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditStUp(param);

        return new ModelAndView("monitoringbku/editbkubpjs", "refbku", bku);
    }

    @RequestMapping(value = "/monitoringbkulainlain/{idBku}", method = RequestMethod.GET)
    public ModelAndView monitoringbkulainlain(@PathVariable Integer idBku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idBku);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditLainLain(param);

        return new ModelAndView("monitoringbku/editbkulainlain", "refbku", bku);
    }

    @RequestMapping(value = "/monitoringbkukasmasuk/{idBku}", method = RequestMethod.GET)
    public ModelAndView monitoringbkukasmasuk(@PathVariable Integer idBku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idBku);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditStUp(param);

        return new ModelAndView("monitoringbku/editbkukasmasuk", "refbku", bku);
    }

    @RequestMapping(value = "/monitoringbkucek/{noBKU}", method = RequestMethod.GET)
    public ModelAndView monitoringbkucek(@PathVariable Integer noBKU, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("noBKU", noBKU);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditCek(param);

        return new ModelAndView("monitoringbku/editbkucek", "refbku", bku);
    }

    @RequestMapping(value = "/monitoringbkusp2d/{idTransaksi}/{noBKU}", method = RequestMethod.GET)
    public ModelAndView monitoringbkusp2d(@PathVariable Integer idTransaksi, @PathVariable Integer noBKU, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idtransaksi", idTransaksi);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);
        param.put("nobku", noBKU);

        final BukuKasUmum bku = bkuServices.getBkuEditSp2d(param);

        return new ModelAndView("monitoringbku/editbkusp2d", "refbku", bku);
    }

    @RequestMapping(value = "/monitoringbkupajak/", method = RequestMethod.GET)
    public ModelAndView monitoringbkupajak(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String noBku = request.getParameter("noBKU");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);
        param.put("noBku", noBku);

        final BukuKasUmum bku = bkuServices.getBkuEditPajak(param);

        return new ModelAndView("monitoringbku/editbkupajak", "refbku", bku);
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
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String kodewilayah = pengguna.getKodeProses();
        
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("kodewilayah", kodewilayah);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getBulanByRekap(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getSisaPajak", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSisaPajak(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String jenis = request.getParameter("jenis");
        final String nobku = request.getParameter("nobku");
        final String beban = request.getParameter("beban");
        final String keterangan = request.getParameter("keterangan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("jenis", jenis);
        param.put("nobku", nobku);
        param.put("beban", beban);
        param.put("keterangan", keterangan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getSisaPajak(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getSaldoAwalPajak", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSaldoAwalPajak(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String jenis = request.getParameter("jenis");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("jenis", jenis);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getSaldoAwalPajak(param));

        return mapData;
    }

    @RequestMapping(value = "/liststbl", method = RequestMethod.GET)
    public ModelAndView liststbl(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bku/listSetoranBl", "refkegiatan", bku);
    }

    @RequestMapping(value = "/listsetoran", method = RequestMethod.GET)
    public ModelAndView listsetoran(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bku/listSetoran", "refkegiatan", bku);
    }

    @RequestMapping(value = "/liststbtl", method = RequestMethod.GET)
    public ModelAndView liststbtl(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bku/listSetoranBtl", "refkegiatan", bku);
    }

    @RequestMapping(value = "/json/listsetoranjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsetoranjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kode = request.getParameter("kode");
        final String nama = request.getParameter("nama");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String beban = request.getParameter("beban");
        final String jenis = request.getParameter("jenis");
        final String nobku = request.getParameter("nobku");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("nama", nama);
        param.put("kode", kode);
        param.put("nobku", nobku);
        param.put("beban", beban);
        param.put("jenis", jenis);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListST(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListST(param));

        return mapData;
    }

    @RequestMapping(value = "/json/liststbljson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> liststbljson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kode = request.getParameter("kode");
        final String nama = request.getParameter("nama");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String beban = request.getParameter("beban");
        final String nobku = request.getParameter("nobku");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("nama", nama);
        param.put("kode", kode);
        param.put("nobku", nobku);
        param.put("beban", beban);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListStBl(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListStBl(param));

        return mapData;
    }

    @RequestMapping(value = "/json/liststbtljson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> liststbtljson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kode = request.getParameter("kode");
        final String nama = request.getParameter("nama");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String nobku = request.getParameter("nobku");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("nama", nama);
        param.put("kode", kode);
        param.put("nobku", nobku);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListStBtl(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListStBtl(param));

        return mapData;
    }

    @RequestMapping(value = "/json/listakunstbtl", method = RequestMethod.GET)

    public @ResponseBody
    Map<String, Object> listakunstbtl(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String novalidasi = request.getParameter("novalidasi");
        final String nobku = request.getParameter("nobku");

        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("nobku", nobku);
        param.put("novalidasi", novalidasi);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakAkunStBtl(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getAkunStBtl(param));

        return mapData;
    }

    @RequestMapping(value = "/json/listakunstbl", method = RequestMethod.GET)

    public @ResponseBody
    Map<String, Object> listakunstbl(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String novalidasi = request.getParameter("novalidasi");
        final String beban = request.getParameter("beban");
        final String nobku = request.getParameter("nobku");

        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("novalidasi", novalidasi);
        param.put("nobku", nobku);
        param.put("beban", beban);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakAkunStBl(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getAkunStBl(param));

        return mapData;
    }

    @RequestMapping(value = "/editbkustbtl/{kodeTransaksi}", method = RequestMethod.GET)
    public ModelAndView editbkustbtl(@PathVariable String kodeTransaksi, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String noBku = request.getParameter("noBKU");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        //param.put("nobukti", noBukti);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);
        //param.put("tglPosting", tglPosting);
        param.put("kotransaksi", kodeTransaksi);
        param.put("noBku", noBku);

        final BukuKasUmum bku = bkuServices.getBkuEdit(param);

        return new ModelAndView("bku/editbkustbtl", "refbku", bku);
    }

    @RequestMapping(value = "/editbkustbl/{kodeTransaksi}", method = RequestMethod.GET)
    public ModelAndView editbkustbl(@PathVariable String kodeTransaksi, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String noBku = request.getParameter("noBKU");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);
        param.put("kotransaksi", kodeTransaksi);
        param.put("noBku", noBku);

        final BukuKasUmum bku = bkuServices.getBkuEdit(param);

        return new ModelAndView("bku/editbkustbl", "refbku", bku);
    }

    @RequestMapping(value = "/json/updatestbtl", method = RequestMethod.POST)
    public @ResponseBody
    String updatestbtl(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BukuKasUmum> listBKU = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String filling = (String) mapdata.get("fileinbox");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");
        final String nobku = (String) mapdata.get("noBKU");

        for (Map<String, Object> mapnilailist : nilailist) {
            BukuKasUmum bukukas = new BukuKasUmum();

            bukukas.setTahun(tahun);
            bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
            bukukas.setIdEntry(pengguna.getIdPengguna());
            bukukas.setTglPosting(tglpost);
            bukukas.setKodeTransaksi(kodetrans);
            bukukas.setNoDok(nobukti);
            bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
            bukukas.setJenis(jenis);
            bukukas.setBeban(beban);
            bukukas.setInboxFile(filling);
            bukukas.setNamaPptk(namapptk);
            bukukas.setNipPptk(nippptk);
            bukukas.setUraian(uraian);
            bukukas.setKodeWilayah(pengguna.getKodeProses());
            bukukas.setKodePembayaran(carabayar);
            bukukas.setKodeUangPersediaan(carabayar);
            bukukas.setKodeKoreksi("0");
            bukukas.setNoBKU(SipkdHelpers.getIntFromString(nobku));

            Object nilaimasuk = mapnilailist.get("nilaimasuk");
            Object nilaikeluar = mapnilailist.get("nilaikeluar");
            Object idbas = mapnilailist.get("idbas");
            Object uraianbukti = mapnilailist.get("uraianbukti");
            Object kodeakun = mapnilailist.get("kodeakun");

            bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
            bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
            bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            bukukas.setUraianBukti(uraianbukti.toString());
            bukukas.setKodeakun(kodeakun.toString());

            listBKU.add(bukukas);
        }

        bkuServices.updateBkuSt(listBKU);

        return "Data Buku Kas Umum Berhasil Diubah";
    }

    @RequestMapping(value = "/json/updatestbl", method = RequestMethod.POST)
    public @ResponseBody
    String updatestbl(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BukuKasUmum> listBKU = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String filling = (String) mapdata.get("fileinbox");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");
        final String nobku = (String) mapdata.get("noBKU");

        for (Map<String, Object> mapnilailist : nilailist) {
            BukuKasUmum bukukas = new BukuKasUmum();

            bukukas.setTahun(tahun);
            bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
            bukukas.setIdEntry(pengguna.getIdPengguna());
            bukukas.setTglPosting(tglpost);
            bukukas.setKodeTransaksi(kodetrans);
            bukukas.setNoDok(nobukti);
            bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
            bukukas.setJenis(jenis);
            bukukas.setBeban(beban);
            bukukas.setInboxFile(filling);
            bukukas.setNamaPptk(namapptk);
            bukukas.setNipPptk(nippptk);
            bukukas.setUraian(uraian);
            bukukas.setKodeWilayah(pengguna.getKodeProses());
            bukukas.setKodePembayaran(carabayar);
            bukukas.setKodeUangPersediaan(carabayar);
            bukukas.setKodeKoreksi("0");
            bukukas.setNoBKU(SipkdHelpers.getIntFromString(nobku));

            Object nilaimasuk = mapnilailist.get("nilaimasuk");
            Object nilaikeluar = mapnilailist.get("nilaikeluar");
            Object idbas = mapnilailist.get("idbas");
            Object kodekegiatan = mapnilailist.get("kodekegiatan");
            Object uraianbukti = mapnilailist.get("uraianbukti");
            Object idkegiatan = mapnilailist.get("idkegiatan");
            Object kodeakun = mapnilailist.get("kodeakun");
            Object idspd = mapnilailist.get("idspd");

            bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
            bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
            bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            bukukas.setKodeKeg(kodekegiatan.toString());
            bukukas.setUraianBukti(uraianbukti.toString());
            bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan.toString()));
            bukukas.setKodeakun(kodeakun.toString());
            bukukas.setIdSpd(SipkdHelpers.getIntFromString(idspd.toString()));

            listBKU.add(bukukas);
        }

        bkuServices.updateBkuStBl(listBKU);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/getSaldoJasaGiro", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSaldoJasaGiro(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String nobku = request.getParameter("nobku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("nobku", nobku);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getSaldoJasaGiro(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getSaldoBpjs", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSaldoBpjs(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String nobku = request.getParameter("nobku");
        final String akun = request.getParameter("akun");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("nobku", nobku);
        param.put("idbas", akun);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getSaldoBpjs(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getKodeSA", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getKodeSA(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(3);

        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String nobku = request.getParameter("nobku");

        param.put("idskpd", idskpd);
        param.put("tahun", tahun);
        param.put("nobku", nobku);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getKodeSA(param));

        return mapData;

    }

    @RequestMapping(value = "/listpajakterima", method = RequestMethod.GET)
    public ModelAndView listpajakterima(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bku/listpajakterima", "refkegiatan", bku);
    }

    @RequestMapping(value = "/json/listpajakterimajson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpajakterimajson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kode = request.getParameter("kode");
        final String kodekeg = request.getParameter("kodekeg");
        final String jenis = request.getParameter("jenis");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("jenis", jenis);
        param.put("kodekeg", kodekeg);
        param.put("kode", kode);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListPnPajak(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListPnPajak(param));

        return mapData;
    }

    @RequestMapping(value = "/json/listpajakpg", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpajakpg(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String nobku = request.getParameter("nobku");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("nobku", nobku);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListPgPajak(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListPgPajak(param));

        return mapData;
    }

    @RequestMapping(value = "/json/prosessimpanpjpg", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpanpjpg(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BukuKasUmum> listBKU = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetransaksi = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String idkegiatan = (String) mapdata.get("idkegiatan");
        final String kodekegiatan = (String) mapdata.get("kodekegiatan");
        final String filling = (String) mapdata.get("fileinbox");
        final String uraianinput = (String) mapdata.get("uraianinput");
        final String carabayar = (String) mapdata.get("carabayar");
        final String nobku = (String) mapdata.get("nobku");

        for (Map<String, Object> mapnilailist : nilailist) {
            BukuKasUmum bukukas = new BukuKasUmum();

            bukukas.setTahun(tahun);
            bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
            bukukas.setIdEntry(pengguna.getIdPengguna());
            bukukas.setTglPosting(tglpost);
            bukukas.setKodeTransaksi(kodetransaksi);
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
            bukukas.setNoBkuPj(nobku);
            bukukas.setNamaPptk("");
            bukukas.setNipPptk("");
            bukukas.setKodeKoreksi("0");

            Object nilaimasuk = mapnilailist.get("nilaimasuk");
            Object nilaikeluar = mapnilailist.get("nilaikeluar");
            Object idbas = mapnilailist.get("idbas");
            Object kodeakun = mapnilailist.get("kodeakun");

            bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
            bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
            bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            bukukas.setUraianBukti(uraianinput);
            bukukas.setKodeakun(kodeakun.toString());

            listBKU.add(bukukas);

        }

        bkuServices.insertBkuPjPg(listBKU);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosessimpanpajaksa", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpanpajaksa(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        BukuKasUmum bukukas = new BukuKasUmum();

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String nilaikeluar = (String) mapdata.get("nilaikeluar");
        final String nilaimasuk = (String) mapdata.get("nilaimasuk");
        final String filling = (String) mapdata.get("fileinbox");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");
        final String nobku = (String) mapdata.get("nobku");

        bukukas.setTahun(tahun);
        bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setTglPosting(tglpost);
        bukukas.setKodeTransaksi(kodetrans);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setJenis(jenis);
        bukukas.setBeban(beban);
        bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
        bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar));
        bukukas.setInboxFile(filling);
        bukukas.setUraian(uraian);
        bukukas.setKodeWilayah(pengguna.getKodeProses());
        bukukas.setKodePembayaran(carabayar);
        bukukas.setKodeUangPersediaan(carabayar);
        bukukas.setKodeKoreksi("0");
        bukukas.setNoBkuPj(nobku);

        bkuServices.insertBkuPajakSA(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/listpajakpgedit", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpajakpgedit(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String nobku = request.getParameter("nobku");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("nobku", nobku);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListPgPajakEdit(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListPgPajakEdit(param));

        return mapData;
    }

    @RequestMapping(value = "/json/prosesupdatepjpg", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdatepjpg(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String idskpd = (String) mapdata.get("idskpd");
        final String tglpost = (String) mapdata.get("tglposting");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = mapdata.get("tgldok").toString();
        final String tahun = (String) mapdata.get("tahun");
        final String fileinbox = (String) mapdata.get("fileinbox");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");
        final String nobku = (String) mapdata.get("nobku");

        BukuKasUmum bukukas = new BukuKasUmum();

        bukukas.setTahun(tahun);
        bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setTglPosting(tglpost);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setInboxFile(fileinbox);
        bukukas.setUraian(uraian);
        bukukas.setKodePembayaran(carabayar);
        bukukas.setKodeUangPersediaan(carabayar);
        bukukas.setKodeKoreksi("0");
        bukukas.setNoBKU(SipkdHelpers.getIntFromString(nobku.toString()));

        bkuServices.updateBkuPjPg(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/getJenisBku", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getJenisBku(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getJenisBku(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getSaldoAawalJGBP", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSaldoAawalJGBP(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String kodetrx = request.getParameter("kodetrx");
        final String akun = request.getParameter("akun");
        final String nobku = request.getParameter("nobku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("kodetrx", kodetrx);
        param.put("idbas", akun);
        param.put("nobku", nobku);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getSaldoAawalJGBP(param));

        return mapData;
    }

    @RequestMapping(value = "/listterimajgbp", method = RequestMethod.GET)
    public ModelAndView listterimajgbp(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bku/listjgbp", "refkegiatan", bku);
    }

    @RequestMapping(value = "/json/listjgbp", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listjgbp(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kode = request.getParameter("kode");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String kodetrx = request.getParameter("kodetrx");
        //final String keterangan = request.getParameter("keterangan");
        final String nobku = request.getParameter("nobku");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("kodetrx", kodetrx);
        param.put("kode", kode);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        //param.put("keterangan", keterangan);
        param.put("nobku", nobku);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListJGBP(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListJGBP(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getDataKBUD", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getDataKBUD(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String kodewilayah = pengguna.getKodeProses();

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("wilayah", kodewilayah);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bkuServices.getDataKBUD(param));

        return mapData;
    }

    @RequestMapping(value = "/monitoringbkuppkd/{idBku}", method = RequestMethod.GET)
    public ModelAndView monitoringbkuppkd(@PathVariable Integer idBku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());
        }

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idBku);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditStUp(param);

        return new ModelAndView("monitoringbku/editbkuppkd", "refbku", bku);
    }

    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

    private Integer Integer(String parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
