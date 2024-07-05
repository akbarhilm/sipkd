package sp2d.ui.action;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sp2d.util.PrintReportTemplate;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import sp2d.services.HistoriTransaksiServices;
import spp.model.Pengguna;
import spp.model.Histori;
import sp2d.services.CetakValidasiSP2DServices;
import sp2d.services.UserManagementServices;
import sp2d.util.BigDecimalPropertyEditor;
import sp2d.util.SipkdHelpers;
import sp2d.util.SqlDatePropertyEditor;
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

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/histori")
public class HistoriTransaksiAction extends PrintReportTemplate {

    private static final Logger log = LoggerFactory.getLogger(HistoriTransaksiAction.class);
    @Autowired
    UserManagementServices userManagementServices;
     @Autowired
    HistoriTransaksiServices histser;
     
     @Autowired
    ServletContext servletContext;

    @Autowired

    CetakValidasiSP2DServices cetakService;
    
     @RequestMapping(value = "/mutasitransall", method = RequestMethod.GET)
    public ModelAndView mutasitransall(final Principal principal, final HttpServletRequest req) {
       

        final Pengguna pengguna = (Pengguna) req.getSession().getAttribute("pengguna");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        Histori hist = new Histori();
        if(!pengguna.getKodeProses().equals("0")){
         hist = histser.getRekening(SipkdHelpers.getIntFromString(pengguna.getKodeProses()));
        }
        req.setAttribute("wil", pengguna.getKodeProses());
        
        return new ModelAndView("historitransaksi/mutasitransall", "progcmd", hist);
    }

    @RequestMapping(value = "/json/listtransall", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listtrxall(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String rek = request.getParameter("rekening");
        
        

        
            param.put("rekening", rek);
        
        
           param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = histser.getBanyakTransaksiall(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", histser.getTransaksiall(param));

        return mapData;
    }
    
     @RequestMapping(value = "/json/getmutasiall", method = RequestMethod.POST,consumes = "application/json")
    public @ResponseBody
    Map<String, String>  getmutasiall(@RequestBody Map<String, Object> mapdata,final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);

         String account = (String) mapdata.get("account");
         String sdate = (String) mapdata.get("startdate");
         String edate = (String) mapdata.get("enddate");
       param.put("rek",account);
        param.put("startdate",sdate);
         param.put("enddate",edate);
       
        log.debug("accb"+account);
       histser.getMutasiAll(param);

        return null;
    }
    
    @RequestMapping(value = "/json/saldoakhirall", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> saldoakhirall(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
         
        final String rek = request.getParameter("rekening");
       
       
            param.put("rekening", rek);
       
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", histser.getSaldoAkhirall(param));

        return mapData;
    }
    
    @RequestMapping(value = "/json/prosescetakall", method = RequestMethod.GET)
    public void processRequestall(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        
       
        final String rek = request.getParameter("rekening");
        final String sdate = request.getParameter("startdate");
        final String edate = request.getParameter("endate");
        final Map<String, Object> map = new HashMap<String, Object>();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String pathReport = servletContext.getInitParameter("PATH_REPORT");
        map.put("TAHUN",tahunAnggaran);
        map.put("SUBREPORT_DIR", pathReport);
        map.put("NOREKENING", rek);
        map.put("TGLREQUESTMIN",sdate);
        map.put("TGLREQUESTMAX",edate);
       
        
        //map.put("TIPE", tipe);

        List<Map> listhasil = cetakService.getnilaiparam(map);
        map.put("NAMA_DAERAH", listhasil.get(0).get("N_DAERAH_JUDUL"));
        map.put("NAMA_DAERAH_LOW", listhasil.get(0).get("N_DAERAH"));
        map.put("NO_PERDA", listhasil.get(0).get("I_PERDA_NO"));
        map.put("THN_PERDA", listhasil.get(0).get("C_PERDA_TAHUN"));
        map.put("TGL_PERDA", listhasil.get(0).get("C_PERDA_TGL"));
        map.put("NAMA_KOTA", listhasil.get(0).get("N_KOTA"));
        map.put("PERATURAN_1", listhasil.get(0).get("E_PERATURAN_SPD1"));
        map.put("PERATURAN_2", listhasil.get(0).get("E_PERATURAN_SPD2"));
        map.put("PERATURAN_3", listhasil.get(0).get("E_PERATURAN_SPD3"));
        map.put("PERATURAN_4", listhasil.get(0).get("E_PERATURAN_SPD4"));
        map.put("PERATURAN_5", listhasil.get(0).get("E_PERATURAN_SPD5"));
       
            map.put("pathreport", pathReport + "/Report_MutasiRekeningKoranPPKD.jasper");

            map.put("filename", tahunAnggaran + "- Transaksi Histori Rekening Bank.pdf");
        
       

        printReportToPdfStream(response, map);
    }
    
    @RequestMapping(value = "/xls/cetakxls", method = RequestMethod.GET)
    public void xlsbku(final HttpServletRequest request, final HttpServletResponse response) throws FileNotFoundException, IOException, InvalidFormatException {
        //final String pathXls = servletContext.getInitParameter("PATH_XLS");
        response.setHeader("Content-disposition", "attachment; filename=Mutasi_Rekening.xls");
        response.setContentType("application/vnd.ms-excel");
         final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        String rekening = request.getParameter("rekening");
         final String sdate = request.getParameter("startdate");
        final String edate = request.getParameter("endate");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        //log.debug(" =============== idskpd ============= " + idskpd);
        //String triwulan = request.getParameter("triwulan");
        HashMap<String, Object> param = new HashMap<String, Object>(9);
        param.put("offset", 0);
        param.put("tglawal",sdate);
        param.put("tglakhir",edate);
        //param.put("kodeakun", kodeakun);
        //param.put("namaakun", namaakun);
        param.put("tahun", tahunAnggaran);
       // param.put("triwulan", triwulan);
        //param.put("kodewilproses", kodewilproses);
        param.put("rekening", rekening);
        int banyak = histser.getBanyakListXls(param);
        param.put("limit", banyak);
        param.put("iSortCol_0", 1);
        param.put("sSortDir_0", "ASC");
        //InputStream is = servletContext.getResourceAsStream(pathXls+"/bku.xls");
        InputStream is = servletContext.getResourceAsStream("/WEB-INF/xls/mutasiall.xls");
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
        context.putVar("datas", histser.getListXls(param));
        xlsArea.applyAt(new CellRef("Template!A1"), context);
        xlsArea.processFormulas();
        workbook.write(out);
        is.close();
        out.close();
    }
    
   /* private static final Properties properties = new Properties();

    static {
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("sso.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }*/
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

   
}
