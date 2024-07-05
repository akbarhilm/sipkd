package spj.ui.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.Valid;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spj.services.LaporanSkpdServices;
import spj.services.CetakBKUPengeluaranServices;
import spp.model.Pengguna;
import spp.model.CetakSkpd;
import spp.model.JournalSPJ;
import spp.model.Skpd;
import spp.model.BukuKasUmum;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SipkdHelpers;
import net.sf.jasperreports.engine.JRException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.poi.PoiContext;
import org.jxls.transform.poi.PoiTransformer;
import spj.services.CetakReportServices;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/laporanbkukeluar")
public class LaporanDanCetakBKUPengeluaranAction {

    private static final Logger log = LoggerFactory.getLogger(LaporanDanCetakBKUPengeluaranAction.class);
    @Autowired
    CetakReportServices cetakReportServices;
    
    @Autowired
    CetakBKUPengeluaranServices laporancetakbkuService;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/indexlaporanbkukeluar", method = RequestMethod.GET)
    public ModelAndView addbku(final BukuKasUmum bku, final HttpServletRequest request, Model model) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        Integer idskpd = listSkpd.get(0).getIdSkpd();

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        //final List<BukuKasUmum> bulan = laporancetakbkuService.getBulanBKU(param);
        //model.addAttribute("listBulanBKU", bulan);
        return new ModelAndView("bkukeluar/bkukeluar", "refcetak", bku);
    }

    @RequestMapping(value = "/indexlaporanbkukeluarprovinsi", method = RequestMethod.GET)
    public ModelAndView addbkuprov(final BukuKasUmum bku, final HttpServletRequest request, Model model) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("bkukeluar/bkukeluarprovinsi", "refcetak", bku);
    }

    @RequestMapping(value = "/listskpd", method = RequestMethod.GET)
    public String indexlist(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "cetak/listskpd";
    }

    @RequestMapping(value = "/listkegiatanpopup", method = RequestMethod.GET)
    public ModelAndView listkegiatan(final HttpServletResponse response, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("/bkukeluar/listkegiatanpopup");
    }

    @RequestMapping(value = "/json/listpopupkegiatan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpopupkegiatan(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(8);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String tahunAnggaran = request.getParameter("tahun");
        final String namaKegiatan = request.getParameter("nama");
        final String idskpd = request.getParameter("idskpd");
        final String kodeKegiatan = request.getParameter("kode");

        log.debug("ID SKPD = " + idskpd);
        param.put("tahun", tahunAnggaran);
        param.put("nama", namaKegiatan);
        param.put("idskpd", idskpd);
        param.put("kode", kodeKegiatan);

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = laporancetakbkuService.getCountKegiatan(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", laporancetakbkuService.getKegiatan(param));
        return mapData;
    }

    @RequestMapping(value = "/json/prosessimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {

        final String bulan = (String) mapdata.get("bulan");
        final String idskpd = (String) mapdata.get("idskpd");
        final String tahun = (String) mapdata.get("tahun");

        log.debug("Parameter : " + idskpd + "===" + bulan + "===" + tahun);

        final BukuKasUmum bku = new BukuKasUmum();
        bku.setTahun(tahun);
        bku.setIdskpd(SipkdHelpers.getIntFromString(idskpd));
        bku.setBulan(bulan);

        laporancetakbkuService.insertBKUPengeluaran(bku);
        return "Proses Buku Kas Umum Berhasil";

    }

    @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
    public void prosescetak(HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String jenislaporan = request.getParameter("jenislaporan");
        final String idskpd = request.getParameter("idskpd");
        final String tgl1 = request.getParameter("tgl1");
        final String tgl2 = request.getParameter("tgl2");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String bulan = request.getParameter("bulan");
        
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        String kodewilproses = "0";
        if (idskpd.equals("761") || idskpd.equals("1234")) {
            if (pengguna.getKodeGrup().equals("11")) {
                kodewilproses = request.getParameter("kodewilproses");
            } else {
                kodewilproses = pengguna.getKodeProses();
            }
        }

        try {
            //final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");

            map.put("SUBREPORT_DIR", pathReport);
            map.put("TAHUN", tahunAnggaran);
            map.put("TGL_AWAL", tgl1);
            map.put("IDSKPD", idskpd);
            map.put("ID_SKPD", idskpd);
            map.put("TGL_AKHIR", tgl2);
            map.put("IDKEGIATAN", idkegiatan);
            map.put("BULAN", bulan);
            map.put("KODE_WIL_SP2DPROSES", kodewilproses);
            
            List<Map> listhasil = laporancetakbkuService.getnilaiparam(map);
            map.put("NAMA_DAERAH", listhasil.get(0).get("N_DAERAH_JUDUL"));
            map.put("NAMA_DAERAH_LOW", listhasil.get(0).get("N_DAERAH"));
            map.put("NO_PERDA", listhasil.get(0).get("I_PERDA_NO"));
            map.put("THN_PERDA", listhasil.get(0).get("C_PERDA_TAHUN"));
            map.put("TGL_PERDA", listhasil.get(0).get("C_PERDA_TGL"));
            map.put("NAMA_KOTA", listhasil.get(0).get("N_KOTA"));

            log.debug("jenis laporan === " + jenislaporan);

            if ("1".equals(jenislaporan)) { // Bku Pengeluaran
                log.debug("jenis " + jenislaporan);
                JasperPrint jasperPrint;
                if (idskpd.equals("761") || idskpd.equals("1234")) {
                    map.put("pathreport", pathReport + "/Report_BKU-Pengeluaran_PPKD.jasper");
                        jasperPrint = cetakReportServices.cetakReport(map);
                    //jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_BKU-Pengeluaran_PPKD.jasper", map, jdbcConnection);
                } else {
                    map.put("pathreport", pathReport + "/Report_BKU-Pengeluaran.jasper");
                        jasperPrint = cetakReportServices.cetakReport(map);
                    //jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_BKU-Pengeluaran.jasper", map, jdbcConnection);
                }

                final String filename = tahunAnggaran + "-" + "BKU-Pengeluaran" + "-" + bulan + ".pdf";
                response.setHeader("Content-disposition", "attachment; filename=" + filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();

            } else if ("2".equals(jenislaporan)) { // BKU Per Kegiatan (untuk sekarang tidak pernah dipanggil karena per kegiatan tidak bisa cetak report)
                map.put("pathreport", pathReport + "/Report_Realisasi_BKU_PerKegiatan.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_Realisasi_BKU_PerKegiatan.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran + "-" + "REALISASI_BKU_PERKEGIATAN" + "-" + idkegiatan + ".pdf";
                response.setHeader("Content-disposition", "attachment; filename=" + filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/json/setComboBulan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> setComboBulan(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        mapData.put("aData", laporancetakbkuService.getBulanBKU(param));

        return mapData;
    }

    @RequestMapping(value = "/json/setComboBulan2", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> setComboBulan2(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        String kodewilproses = "0";
        if (idskpd.equals("761") || idskpd.equals("1234")) {
            if (pengguna.getKodeGrup().equals("11")) {
                kodewilproses = request.getParameter("kodewilproses");
            } else {
                kodewilproses = pengguna.getKodeProses();
            }
        }

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("kodewilproses", kodewilproses);

        mapData.put("aData", laporancetakbkuService.getBulanBKUPengeluaran2(param));

        return mapData;
    }

    @RequestMapping(value = "json/getTanggalBelumJurnal", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getTanggalBelumJurnal(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String bulan = request.getParameter("bulan");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        String kodewilproses = "0";
        if (idskpd.equals("761") || idskpd.equals("1234")) {
            if (pengguna.getKodeGrup().equals("11")) {
                kodewilproses = request.getParameter("kodewilproses");
            } else {
                kodewilproses = pengguna.getKodeProses();
            }
        }

        final Map<String, Object> mapData = new HashMap<String, Object>(1);
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("bulan", bulan);
        param.put("kodewilproses", kodewilproses);

        mapData.put("aData", laporancetakbkuService.getTanggalBelumJurnal(param));

        return mapData;
    }

    @RequestMapping(value = "/json/gridbkupengeluaran", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistjournalspj(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        final String bulan = request.getParameter("bulan");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String jenislaporan = request.getParameter("jenislaporan");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        String kodewilproses = "0";
        if (idskpd.equals("761") || idskpd.equals("1234")) {
            if (pengguna.getKodeGrup().equals("11")) {
                kodewilproses = request.getParameter("kodewilproses");
            } else {
                kodewilproses = pengguna.getKodeProses();
            }
        }

        final Map< String, Object> param = new HashMap<String, Object>(2);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("bulan", bulan);
        param.put("idkegiatan", idkegiatan);
        param.put("idskpd", idskpd);
        param.put("kodewilproses", kodewilproses);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        if ("1".equals(jenislaporan)) { // BKU Pengeluaran
            final int banyak = laporancetakbkuService.getCountListBkuPengeluaran(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", laporancetakbkuService.getListBkuPengeluaran(param));

        } else if ("2".equals(jenislaporan)) { // BKU Per Kegiatan
            final int banyak = laporancetakbkuService.getCountListBkuPerKegiatan(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", laporancetakbkuService.getListBkuPerKegiatan(param));
        }

        return mapData;
    }

    @RequestMapping(value = "/xls/bkuxls", method = RequestMethod.GET)
    public void xlsbku(final HttpServletRequest request, final HttpServletResponse response) throws FileNotFoundException, IOException, InvalidFormatException {
        final String pathXls = servletContext.getInitParameter("PATH_XLS");
        response.setHeader("Content-disposition", "attachment; filename=bku-pengeluaran.xls");
        response.setContentType("application/vnd.ms-excel");
        String kodeakun = request.getParameter("kodeakun");
        String namaakun = request.getParameter("namaakun");
        String tahunAnggaran = request.getParameter("tahun");
        String idskpd = request.getParameter("idskpd");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        String kodewilproses = "0";
        if (idskpd.equals("761") || idskpd.equals("1234")) {
            if (pengguna.getKodeGrup().equals("11")) {
                kodewilproses = request.getParameter("kodewilproses");
            } else {
                kodewilproses = pengguna.getKodeProses();
            }
        }
        log.debug(" =============== idskpd ============= " + idskpd);
        String bulan = request.getParameter("bulan");
        HashMap<String, Object> param = new HashMap<String, Object>(9);
        param.put("offset", 0);
        param.put("kodeakun", kodeakun);
        param.put("namaakun", namaakun);
        param.put("tahun", tahunAnggaran);
        param.put("bulan", bulan);
        param.put("kodewilproses", kodewilproses);
        param.put("idskpd", SipkdHelpers.getIntFromString(idskpd));
        int banyak = laporancetakbkuService.getBanyakListXlsBku(param);
        param.put("limit", banyak);
        param.put("iSortCol_0", 1);
        param.put("sSortDir_0", "ASC");
        //InputStream is = servletContext.getResourceAsStream(pathXls+"/bku.xls");
        InputStream is = servletContext.getResourceAsStream("/WEB-INF/xls/lap_bku_pengeluaran.xls");
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
        context.putVar("datas", laporancetakbkuService.getListXlsBku(param));
        xlsArea.applyAt(new CellRef("Template!A1"), context);
        xlsArea.processFormulas();
        workbook.write(out);
        is.close();
        out.close();
    }

}
