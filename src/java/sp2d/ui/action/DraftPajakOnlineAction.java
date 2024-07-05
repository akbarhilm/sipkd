package sp2d.ui.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.Principal;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.BankPfk;
import sp2d.services.DraftPajakOnlineServices;
import sp2d.services.HistoriTransaksiServices;
import sp2d.util.BigDecimalPropertyEditor;
import sp2d.util.SipkdHelpers;
import sp2d.util.SqlDatePropertyEditor;
import spp.model.Histori;

@Controller
@RequestMapping("/DraftPajakOnline")
public class DraftPajakOnlineAction {

    private static final Logger log = LoggerFactory.getLogger(DraftPajakOnlineAction.class);
    @Autowired
    DraftPajakOnlineServices dpoServices;
     @Autowired
    ServletContext servletContext;
     @Autowired
    HistoriTransaksiServices histser;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
   public ModelAndView mutasitransall(final Principal principal, final HttpServletRequest req) {
         final Pengguna pengguna = (Pengguna) req.getSession().getAttribute("pengguna");
         final Map< String, Object> param = new HashMap<String, Object>(3);
        Histori hist = new Histori();
        
         hist = histser.getRekening(SipkdHelpers.getIntFromString(pengguna.getKodeProses()));
        
        return new ModelAndView("draftpajakonline/index", "progcmd", hist);

    }

    @RequestMapping(value = "/xls/cetakxls", method = RequestMethod.GET)
    public void xlsbku(final HttpServletRequest request, final HttpServletResponse response) throws FileNotFoundException, IOException, InvalidFormatException {
        //final String pathXls = servletContext.getInitParameter("PATH_XLS");
        response.setHeader("Content-disposition", "attachment; filename=draftpajakonline.xls");
        response.setContentType("application/vnd.ms-excel");
         final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        //String rekening = request.getParameter("rekening");
         final String tglsp2d = request.getParameter("tglsp2d");
        
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
       final String wil = pengguna.getKodeProses();
        //log.debug(" =============== idskpd ============= " + idskpd);
        //String triwulan = request.getParameter("triwulan");
        HashMap<String, Object> param = new HashMap<String, Object>(9);
        param.put("offset", 0);
        param.put("tglsp2dsah",tglsp2d);
        param.put("wil",wil);
        //param.put("kodeakun", kodeakun);
        //param.put("namaakun", namaakun);
        param.put("tahun", tahunAnggaran);
       // param.put("triwulan", triwulan);
        //param.put("kodewilproses", kodewilproses);
        //param.put("rekening", rekening);
        int banyak = dpoServices.getBanyakDraftPajakOnline(param);
        param.put("limit", banyak);
        param.put("iSortCol_0", 1);
        param.put("sSortDir_0", "ASC");
        //InputStream is = servletContext.getResourceAsStream(pathXls+"/bku.xls");
        InputStream is = servletContext.getResourceAsStream("/WEB-INF/xls/draftpajakonline.xls");
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
        context.putVar("datas", dpoServices.getListDraftPajakOnline(param));
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
