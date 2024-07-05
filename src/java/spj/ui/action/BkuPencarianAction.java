/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
package spj.ui.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.poi.PoiContext;
import org.jxls.transform.poi.PoiTransformer;
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
import spj.services.BkuPencarianServices;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SipkdHelpers;

import spj.util.SqlDatePropertyEditor;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/bkupencarian")
public class BkuPencarianAction {

    private static final Logger log = LoggerFactory.getLogger(BkuPencarianAction.class);

    @Autowired
    ServletContext servletContext;
    
    @Autowired
    BkuPencarianServices bkuServices;

    @RequestMapping(value = "/indexbku", method = RequestMethod.GET)
    public ModelAndView index(final BukuKasUmum bku, final HttpServletRequest request) {

        return new ModelAndView("bkupencarian/bkupencarian", "refbku", bku);
    }

    @RequestMapping(value = "/indexbku11", method = RequestMethod.GET)
    public ModelAndView index11(final BukuKasUmum bku, final HttpServletRequest request) {

        return new ModelAndView("bkupencarian/bkupencarian11", "refbku", bku);
    }

    @RequestMapping(value = "/listkegiatan", method = RequestMethod.GET)
    public ModelAndView listakunbukubesar(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bkupencarian/listkegiatan", "refkegiatan", bku);
    }

    @RequestMapping(value = "/listkegiatan11", method = RequestMethod.GET)
    public ModelAndView listkegiatan11(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bkupencarian/listkegiatan11", "refkegiatan", bku);
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
        String jeniscari = request.getParameter("jeniscari");
        
        if("8".equals(jeniscari)){
            jeniscari = "TU";
        }
        
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("nama", nama);
        param.put("kode", kode);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("jeniscari", jeniscari);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListKegiatan(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListKegiatan(param));

        return mapData;
    }

    @RequestMapping(value = "/listpptk", method = RequestMethod.GET)
    public ModelAndView listpptk(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bkupencarian/listpptk", "refkegiatan", bku);
    }

    @RequestMapping(value = "/listpptk11", method = RequestMethod.GET)
    public ModelAndView listpptk11(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bkupencarian/listpptk11", "refkegiatan", bku);
    }

    @RequestMapping(value = "/json/listpptkjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpptkjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String nip = request.getParameter("nip");
        final String nama = request.getParameter("nama");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("nama", nama);
        param.put("nip", nip);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListPptk(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListPptk(param));

        return mapData;
    }

    @RequestMapping(value = "/listnodokumen", method = RequestMethod.GET)
    public ModelAndView listnodokumen(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bkupencarian/listnodokumen", "refkegiatan", bku);
    }

    @RequestMapping(value = "/listnodokumen11", method = RequestMethod.GET)
    public ModelAndView listnodokumen11(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bkupencarian/listnodokumen11", "refkegiatan", bku);
    }

    @RequestMapping(value = "/json/listnomordok", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listnomordok(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String nodokumen = request.getParameter("nodokumen");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String jenisdok = request.getParameter("jenisdok");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("nodokumen", nodokumen);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("jenisdok", jenisdok);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListNoDokumen(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListNoDokumen(param));

        return mapData;
    }

    @RequestMapping(value = "/json/listpencarian", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpencarian(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String jenis = request.getParameter("jenis");
        final String jenisdokumen = request.getParameter("jenisdokumen");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String nodokumen = request.getParameter("nodokumen");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String jenispajak = request.getParameter("jenispajak");
        final String namafilter = request.getParameter("namafilter");
        final String nipfilter = request.getParameter("nipfilter");
        final String tglAwal = request.getParameter("tglAwal");
        final String tglAkhir = request.getParameter("tglAkhir");
        
        log.debug("Tanggal Awal == "+tglAwal);
        log.debug("Tanggal Akhir = "+tglAkhir);
        
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("idkegiatan", idkegiatan);
        param.put("nodokumen", nodokumen);
        param.put("namafilter", namafilter);
        param.put("nipfilter", nipfilter);
        param.put("jenispajak", jenispajak);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("tglAwal", tglAwal);
        param.put("tglAkhir", tglAkhir);

        log.debug("jenis =========== " + jenis);
        log.debug("jenis dokumen === " + jenisdokumen);
        log.debug("jenis pajak ===== " + jenispajak);

        final Map<String, Object> mapData = new HashMap<String, Object>(8);

        if ("1".equals(jenis)) { // no dokumen
            if ("SP2D".equals(jenisdokumen)) {
                final long banyak = bkuServices.getBanyakCariNoDok(param);
                mapData.put("sEcho", request.getParameter("sEcho"));
                mapData.put("iTotalRecords", banyak);
                mapData.put("iTotalDisplayRecords", banyak);
                mapData.put("aaData", bkuServices.getPencarianNoDok(param));

            } else if ("SPJ".equals(jenisdokumen)) {
                final long banyak = bkuServices.getBanyakCariNoDokSpj(param);
                mapData.put("sEcho", request.getParameter("sEcho"));
                mapData.put("iTotalRecords", banyak);
                mapData.put("iTotalDisplayRecords", banyak);
                mapData.put("aaData", bkuServices.getPencarianNoDokSPJ(param));

            }

        } else if ("2".equals(jenis)) { // sisa uang di pptk
            final long banyak = bkuServices.getBanyakCariSisaUangPptk(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getPencarianSisaUangPptk(param));

        } else if ("3".equals(jenis)) { // kegiatan
            final long banyak = bkuServices.getBanyakCariKegiatan(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getPencarianKegiatan(param));

        } else if ("4".equals(jenis)) { // cek
            final long banyak = bkuServices.getBanyakCariCek(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getPencarianCek(param));

        } else if ("5".equals(jenis)) { // setoran
            final long banyak = bkuServices.getBanyakCariSetoran(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getPencarianSetoran(param));

        } else if ("6".equals(jenis)) { // pajak
            if ("PS".equals(jenispajak)) {
                final long banyak = bkuServices.getBanyakCariPajakAll(param);
                mapData.put("sEcho", request.getParameter("sEcho"));
                mapData.put("iTotalRecords", banyak);
                mapData.put("iTotalDisplayRecords", banyak);
                mapData.put("aaData", bkuServices.getPencarianPajakAll(param));

            } else {
                final long banyak = bkuServices.getBanyakCariPajak(param);
                mapData.put("sEcho", request.getParameter("sEcho"));
                mapData.put("iTotalRecords", banyak);
                mapData.put("iTotalDisplayRecords", banyak);
                mapData.put("aaData", bkuServices.getPencarianPajak(param));

            }

        } else if ("7".equals(jenis)) { // sisa uang per pptk
            final long banyak = bkuServices.getBanyakCariSisaUangPerPptk(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getPencarianSisaUangPerPptk(param));

        } else if ("8".equals(jenis)) { // kegiatan TU
            final long banyak = bkuServices.getBanyakCariKegiatanTU(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getPencarianKegiatanTU(param));

        }

        return mapData;
    }

    @RequestMapping(value = "/json/getNilaiAnggaran", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getNilaiAnggaran(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String idskpd = request.getParameter("idskpd");
        final String jeniscari = request.getParameter("jenis");
        
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        
        if("8".equals(jeniscari)){
            mapData.put("aData", bkuServices.getNilaiAnggaranTU(param));

        } else {
            mapData.put("aData", bkuServices.getNilaiAnggaran(param));
        }
        
        return mapData;
    }
    
    @RequestMapping(value = "/xls/bkupencarianxls", method = RequestMethod.GET)
    public void xlsbku(final HttpServletRequest request, final HttpServletResponse response) throws FileNotFoundException, IOException, InvalidFormatException {
        final String pathXls = servletContext.getInitParameter("PATH_XLS");
        response.setHeader("Content-disposition", "attachment; filename=bku-pengeluaran.xls");
        response.setContentType("application/vnd.ms-excel");
        String tanggalAwal = request.getParameter("tglawal");
        String tanggalAkhir = request.getParameter("tglakhir");
        String tahunAnggaran = request.getParameter("tahun");
        String idskpd = request.getParameter("idskpd");
        String idkegiatan = request.getParameter("idkeg");
        /*
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        String kodewilproses = "0";
        if (idskpd.equals("761")) {
            if (pengguna.getKodeGrup().equals("11")) {
                kodewilproses = request.getParameter("kodewilproses");
            } else {
                kodewilproses = pengguna.getKodeProses();
            }
        }*/
        //log.debug(" =============== idskpd ============= " + idskpd);
        //String bulan = request.getParameter("bulan");
        HashMap<String, Object> param = new HashMap<String, Object>(9);
        param.put("offset", 0);
        param.put("tglAwal", tanggalAwal);
        param.put("tglAkhir", tanggalAkhir);
        param.put("tahun", tahunAnggaran);
        
        //param.put("kodewilproses", kodewilproses);
        param.put("idskpd", SipkdHelpers.getIntFromString(idskpd));
        param.put("idkegiatan", idkegiatan);
        int banyak = bkuServices.getBanyakListXlsBkuPencarianKegiatan(param);
        param.put("limit", banyak);
        param.put("iSortCol_0", 1);
        param.put("sSortDir_0", "ASC");
        //InputStream is = servletContext.getResourceAsStream(pathXls+"/bku.xls");
        InputStream is = servletContext.getResourceAsStream("/WEB-INF/xls/lap_bku_pencarian_kegiatan.xls");
        //log.debug(" =============== tes path ============= "+is.read());
        ServletOutputStream out = response.getOutputStream();
        Workbook workbook = WorkbookFactory.create(is);
        PoiTransformer transformer = PoiTransformer.createTransformer(workbook);
        log.debug(" =============== " + transformer.getWorkbook().getSheetName(0));
// Transformer transformer = TransformerFactory.createTransformer(is, out);
        // XlsArea xlsArea = new XlsArea("Template!A1", transformer);
        AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
        log.debug(" =============== " + areaBuilder.build());
        List<Area> xlsAreaList = areaBuilder.build();
// getting the main area from the list
        Area xlsArea = xlsAreaList.get(0);
// creating a new PoiContext and setting our sample employees data into it under "employees" key
        Context context = new PoiContext();
        context.putVar("datas", bkuServices.getListXlsBkuPencarianKegiatan(param));
        xlsArea.applyAt(new CellRef("Template!A1"), context);
        xlsArea.processFormulas();
        workbook.write(out);
        is.close();
        out.close();
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
