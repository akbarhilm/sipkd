package spj.ui.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
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
import spj.services.ProsesBkuServices;
import spp.model.Pengguna;
import spp.model.BukuKasUmum;
import spp.model.ProsesBku;
import spp.model.Skpd;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SipkdHelpers;
import spj.util.SqlDatePropertyEditor;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/prosesBku")
public class ProsesBkuAction {

    private static final Logger log = LoggerFactory.getLogger(ProsesBkuAction.class);
    @Autowired
    ProsesBkuServices bkuServices;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;

    
    @RequestMapping(value = "/indexprosesbku", method = RequestMethod.GET)
    public ModelAndView indexprosesbku(final BukuKasUmum bku, final HttpServletRequest request) {
        
        return new ModelAndView("prosesbku/prosesbku", "refneraca", bku);
    }
    
    @RequestMapping(value = "/indexprosesbkuall", method = RequestMethod.GET)
    public ModelAndView indexprosesbkuall(final BukuKasUmum bku, final HttpServletRequest request) {
        
        return new ModelAndView("prosesbku/prosesbkuall", "refneraca", bku);
    }
        
    @RequestMapping(value = "/prosejournal11", method = RequestMethod.POST)
    public String prosesjournal11(@Valid @ModelAttribute("refsppup") BukuKasUmum bku,
            BindingResult result, final HttpServletRequest request, 
            final RedirectAttributes redirectAttributes) {
        
        final StringBuilder sburl = new StringBuilder("redirect:/journalspj/indexjournalspj");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String kodewilayah = pengguna.getKodeProses();
        final String tgl1 = request.getParameter("tglAwal");
        final String tgl2 = request.getParameter("tglAkhir");
        final String idskpd = request.getParameter("idskpdpop");
        
        final String tglawal = SipkdHelpers.getStringDateFormatFromString(tgl1, "yyyyMMdd", "dd/MM/yyyy");
        final String tglakhir = SipkdHelpers.getStringDateFormatFromString(tgl2, "yyyyMMdd", "dd/MM/yyyy");
        
        
        bku.setIdEntry(pengguna.getIdPengguna());
        bku.setTahun(tahun);
        bku.setIdskpd(SipkdHelpers.getIntFromString(idskpd));
        bku.setTglAkhir(tglakhir);
        bku.setTglAwal(tglawal);
        bku.setKodeWilayah(kodewilayah);
       
        if (idskpd.equals("761") || idskpd.equals("1234")) {
            log.debug("PPKD 761 ================== proc_j_bku_ppkd");
            bkuServices.insertProsesBkuPPKD(bku);
       
        } else {
            log.debug("SKPD ================== proc_j_bku_perskpd");
            bkuServices.insertProsesBku(bku);
       
        }
       
        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data Jurnal BKU")
                    .append(" Berhasil Diproses ")
                    .toString());
               
       return "redirect:/prosesBku/indexprosesbkuall";
    }
    
    @RequestMapping(value = "/prosejournal", method = RequestMethod.POST)
    public String prosesjournal(@Valid @ModelAttribute("refsppup") BukuKasUmum bku,
            BindingResult result, final HttpServletRequest request, 
            final RedirectAttributes redirectAttributes) {
        
        final StringBuilder sburl = new StringBuilder("redirect:/journalspj/indexjournalspj");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Integer idskpd = listSkpd.get(0).getIdSkpd();
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String kodewilayah = pengguna.getKodeProses();
        final String tgl1 = request.getParameter("tglAwal");
        //final String tgl2 = request.getParameter("tglAkhir");
        
        final String tglawal = SipkdHelpers.getStringDateFormatFromString(tgl1, "yyyyMMdd", "dd/MM/yyyy");
        //final String tglakhir = SipkdHelpers.getStringDateFormatFromString(tgl2, "yyyyMMdd", "dd/MM/yyyy");
        
        
        bku.setIdEntry(pengguna.getIdPengguna());
        bku.setTahun(tahun);
        bku.setIdskpd(idskpd);
        bku.setTglAkhir(tglawal);
        bku.setTglAwal(tglawal);
        bku.setKodeWilayah(kodewilayah);
       
        if (idskpd.equals("761") || idskpd.equals("1234")) {
            log.debug("PPKD 761 ================== proc_j_bku_ppkd");
            bkuServices.insertProsesBkuPPKD(bku);
       
        } else {
            log.debug("SKPD ================== proc_j_bku_perskpd");
            bkuServices.insertProsesBku(bku);
       
        }
       
        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data Jurnal BKU")
                    .append(" Berhasil Diproses ")
                    .toString());
               
       return "redirect:/prosesBku/indexprosesbku";
    }
    
    @RequestMapping(value = "/json/prosessimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String kodewilayah = pengguna.getKodeProses();
        
        final String idskpd = (String) mapdata.get("idskpd");
        final String tglawal = (String) mapdata.get("tglawal");
        //final String tglakhir = (String) mapdata.get("tglakhir");
        
        final BukuKasUmum bku = new BukuKasUmum();
        
        bku.setIdEntry(pengguna.getIdPengguna());
        bku.setTahun(tahun);
        bku.setIdskpd(SipkdHelpers.getIntFromString(idskpd));
        bku.setTglAkhir(tglawal);
        bku.setTglAwal(tglawal);
        bku.setKodeWilayah(kodewilayah);
        
        if (idskpd.equals("761") || idskpd.equals("1234")) {
            log.debug("PPKD 761 ================== proc_j_bku_ppkd");
            bkuServices.insertProsesBkuPPKD(bku);
       
        } else {
            log.debug("SKPD ================== proc_j_bku_perskpd");
            bkuServices.insertProsesBku(bku);
       
        }
        
        return "Jurnal BKU Berhasil Diproses";
    }
    
    @RequestMapping(value = "/json/listjournalbku", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listjournalpenetapan(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahun");
        final String tgl1 = request.getParameter("tgl1");
        //final String tgl2 = request.getParameter("tgl2");
        final String idskpd = request.getParameter("idskpd");
        
        final String tglW = SipkdHelpers.getStringDateFormatFromString(tgl1, "yyyyMMdd", "dd/MM/yyyy");
        //final String tglK = SipkdHelpers.getStringDateFormatFromString(tgl2, "yyyyMMdd", "dd/MM/yyyy");
        
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
        param.put("tgl1", tglW);
        param.put("tgl2", tglW);
        param.put("idskpd", idskpd);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(5); 
        final int banyak = bkuServices.getBanyakListJournal(param);
        
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("banyakData", banyak);
        mapData.put("aaData", bkuServices.getListJurnal(param));
        
        return mapData;
        
    }
    
    @RequestMapping(value = "/xls/bkuxls", method = RequestMethod.GET)
    public void xlsbku(final HttpServletRequest request, final HttpServletResponse response) throws FileNotFoundException, IOException, InvalidFormatException {
        final String pathXls = servletContext.getInitParameter("PATH_XLS");
        response.setHeader("Content-disposition", "attachment; filename=bku antar tanggal.xls");
        response.setContentType("application/vnd.ms-excel");
        String kodeakun = request.getParameter("kodeakun");
        String namaakun = request.getParameter("namaakun");
        String tahunAnggaran = request.getParameter("tahun");
        String idskpd = request.getParameter("idskpd");
        String tgl1 = request.getParameter("tglAwal");
        String tgl2 = request.getParameter("tglAkhir");
        
        String tglW = SipkdHelpers.getStringDateFormatFromString(tgl1, "yyyyMMdd", "dd/MM/yyyy");
        //String tglK = SipkdHelpers.getStringDateFormatFromString(tgl2, "yyyyMMdd", "dd/MM/yyyy");
        
        HashMap<String, Object> param = new HashMap<String, Object>(9);
        param.put("offset", 0);
        param.put("kodeakun", kodeakun);
        param.put("namaakun", namaakun);
        param.put("tahun", tahunAnggaran);
        param.put("tgl1", tglW);
        param.put("tgl2", tglW);
        param.put("idskpd", idskpd);
        
        int banyak = bkuServices.getBanyakListJournalXls(param);
        param.put("limit", banyak);
        param.put("iSortCol_0", 1);
        param.put("sSortDir_0", "ASC");
        InputStream is = servletContext.getResourceAsStream("/WEB-INF/xls/bkuproses.xls");
        
        ServletOutputStream out = response.getOutputStream();
        Workbook workbook = WorkbookFactory.create(is);
        PoiTransformer transformer = PoiTransformer.createTransformer(workbook);
        log.debug(" =============== "+transformer.getWorkbook().getSheetName(0));

        AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
        log.debug(" =============== "+areaBuilder.build());
        
        List<Area> xlsAreaList = areaBuilder.build();
// getting the main area from the list
        Area xlsArea = xlsAreaList.get(0);
// creating a new PoiContext and setting our sample employees data into it under "employees" key
        Context context = new PoiContext();
        context.putVar("datas", bkuServices.getListJurnalXls(param));
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