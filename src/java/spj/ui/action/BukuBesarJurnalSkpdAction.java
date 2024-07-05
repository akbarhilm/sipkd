package spj.ui.action;

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
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spj.services.BukuBesarJurnalSkpdServices;
import spp.model.Pengguna;
import spp.model.BukuBesar;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SipkdHelpers;
import spj.util.SqlDatePropertyEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spj.services.CetakReportServices;


/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/bukubesarskpd")
public class BukuBesarJurnalSkpdAction {

    private static final Logger log = LoggerFactory.getLogger(BukuBesarJurnalSkpdAction.class);
    
    @Autowired
    CetakReportServices cetakReportServices;
    
    @Autowired
    BukuBesarJurnalSkpdServices cetakService;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;

    
    @RequestMapping(value = "/indexbukubesarskpd", method = RequestMethod.GET)
    public ModelAndView addjournal(final BukuBesar bukubesar, final HttpServletRequest request) {
        
        return new ModelAndView("bukubesar/bukubesarskpd", "refbukubesar", bukubesar);
    }
    
    @RequestMapping(value = "/json/gettanggalposting", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> tglposting(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String akun = request.getParameter("akun");

        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("idskpd", idskpd);
        param.put("kodeAkun", akun);

        final String tanggal = cetakService.getTanggalPosting(param);

        final Map<String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("aData", tanggal);
        return mapData;
    }
   
    @RequestMapping(value = "/listskpd", method = RequestMethod.GET)
    public String indexlist(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "cetak/listskpd";
    }
    
    @RequestMapping(value = "/json/prosessimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        
        final String idskpd = (String) mapdata.get("idskpd");
        final String kodeakun = (String) mapdata.get("kodeakun");
        final String postawal = (String) mapdata.get("postawal");
        final String postakhir = (String) mapdata.get("postakhir");
        
        final BukuBesar buku = new BukuBesar();
        
        buku.setIdEntry(pengguna.getIdPengguna());
        buku.setTahun(tahun);
        buku.setIdskpd(SipkdHelpers.getIntFromString(idskpd));
        buku.setKodeakun(kodeakun);
        buku.setTglPosting(postakhir);
        buku.setTglPostingAwal(postawal);
        
        cetakService.insertBukuBesarSkpd(buku);
        
        return "Jurnal Buku Besar SKPD Berhasil Disimpan";
    }
    
    @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String idskpd = request.getParameter("idskpd");
        final String kodeakun = request.getParameter("kodeakun");
            
        try {
            //final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");
            
            map.put("SUBREPORT_DIR", pathReport);
            
            map.put("TAHUN",tahunAnggaran);
            map.put("KODE_AKUN",kodeakun);
            map.put("ID_SKPD",idskpd);
            
            List<Map> listhasil = cetakService.getnilaiparam(map);
            map.put("NAMA_DAERAH",listhasil.get(0).get("N_DAERAH_JUDUL"));
            map.put("NAMA_DAERAH_LOW",listhasil.get(0).get("N_DAERAH"));
            map.put("NO_PERDA",listhasil.get(0).get("I_PERDA_NO"));
            map.put("THN_PERDA",listhasil.get(0).get("C_PERDA_TAHUN"));
            map.put("TGL_PERDA",listhasil.get(0).get("C_PERDA_TGL"));
            map.put("NAMA_KOTA",listhasil.get(0).get("N_KOTA"));
            map.put("PERATURAN_1",listhasil.get(0).get("E_PERATURAN_SPD1"));
            map.put("PERATURAN_2",listhasil.get(0).get("E_PERATURAN_SPD2"));
            map.put("PERATURAN_3",listhasil.get(0).get("E_PERATURAN_SPD3"));
            map.put("PERATURAN_4",listhasil.get(0).get("E_PERATURAN_SPD4"));
            map.put("PERATURAN_5",listhasil.get(0).get("E_PERATURAN_SPD5"));
            
            map.put("pathreport", pathReport + "/Report_JurnalBukuBesar-SKPD.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
            //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_JurnalBukuBesar-SKPD.jasper", map, jdbcConnection);
            final String filename = tahunAnggaran+"-"+"JURNAL-BUKU-BESAR-SKPD"+"-"+idskpd+"-"+kodeakun+".pdf";
            response.setHeader("Content-disposition", "attachment; filename="+ filename);
            response.setContentType("application/pdf");
            ServletOutputStream output = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            output.close();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping(value = "/json/getlistbukubesar", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistbukubesar(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        final String kodeakun = request.getParameter("kodeakun");
        
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
        param.put("idskpd", idskpd);
        param.put("kodeakun", kodeakun);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = cetakService.getBannyakListBukuBesar(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", cetakService.getListBukuBesar(param));
        return mapData;
    }
    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}