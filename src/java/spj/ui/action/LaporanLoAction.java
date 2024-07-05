package spj.ui.action;

import java.sql.Connection;
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
import spj.services.LoServices;
import spp.model.Pengguna;
import spp.model.LO;
import spp.model.Skpd;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SipkdHelpers;
import org.springframework.ui.Model;
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
@RequestMapping("/lo")
public class LaporanLoAction {

    private static final Logger log = LoggerFactory.getLogger(LaporanLoAction.class);
    @Autowired
    CetakReportServices cetakReportServices;
    
    @Autowired
    LoServices cetakService;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;
   
    @RequestMapping(value = "/indexlaporan", method = RequestMethod.GET)
    public ModelAndView add(final LO laporanlo, final HttpServletRequest request, Model model) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }
        Integer idskpd = listSkpd.get(0).getIdSkpd();
        
        /*final List<LaporanLra> skpd = cetakService.getSkpdCombo(idskpd);
        model.addAttribute("listSkpd", skpd);
*/
        return new ModelAndView("lo/laporanlo", "refcetak", laporanlo);

    }
    
    @RequestMapping(value = "/loprovinsi", method = RequestMethod.GET)
    public ModelAndView loprovinsi(final LO lo, final HttpServletRequest request) {
        
        return new ModelAndView("lo/laporanloprovinsi", "refcetak", lo);
    }
    
    @RequestMapping(value = "/proseslo", method = RequestMethod.GET)
    public ModelAndView proseslo(final LO lo, final HttpServletRequest request) {
        
        return new ModelAndView("lo/proseslo", "refcetak", lo);
    }
    
    @RequestMapping(value = "/listskpd", method = RequestMethod.GET)
    public String indexlist(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "cetak/listskpd";
    }
    
    @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String idskpd = request.getParameter("idskpd");
        final String jenislaporan = request.getParameter("jenislaporan");
        final String bulan = request.getParameter("bulan");
            
        try {
            //final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");
            
            map.put("SUBREPORT_DIR", pathReport);
            map.put("TAHUN",tahunAnggaran);
            map.put("ID_SKPD",idskpd);
            map.put("BULAN",bulan);
        
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
            
            if("1".equals(jenislaporan)){
                /*
                JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_LRA_Provinsi.jasper", map, jdbcConnection);
                
                final String filename = tahunAnggaran+"-"+"LRA-Permendagri-13-PROVINSI"+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();*/
                
            } else if("2".equals(jenislaporan)){
                map.put("pathreport", pathReport + "/Report_LO-PP_Provinsi.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_LO-PP_Provinsi.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"LRA-PP-71-PROVINSI"+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
                
            } else if("3".equals(jenislaporan)){
                /*
                JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_LRA_SKPD.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"LRA-Permendagri-13-SKPD-"+idskpd+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
                        */
                
            } else if("4".equals(jenislaporan)){
                map.put("pathreport", pathReport + "/Report_LO-PP_SKPD.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_LO-PP_SKPD.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"LRA-PP-71-SKPD-"+idskpd+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
                
            } else if("5".equals(jenislaporan)){
                /*
                JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_LRA_SKPDInduk.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"LRA-Permendagri-13-GABUNGAN-SKPD-"+idskpd+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();*/
                
            } else if("6".equals(jenislaporan)){
                map.put("pathreport", pathReport + "/Report_LO-PP_SKPDInduk.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_LO-PP_SKPDInduk.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"LRA-PP-71-GABUNGAN-SKPD-"+idskpd+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
                
            } 

        } catch (Exception e) {
            //e.printStackTrace();
            e.getMessage();
        }
    }
    
    @RequestMapping(value = "/json/prosessimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        
        //final String idskpd = (String) mapdata.get("idskpd");
        final String bulan = (String) mapdata.get("bulan");
        
        final LO lo = new LO();
        
        lo.setIdEntry(pengguna.getIdPengguna());
        lo.setTahun(tahun);
        lo.setBulan(bulan);
        
        cetakService.insertLo(lo);
        
        return "Proses LRA Berhasil Disimpan";
    }
    
    @RequestMapping(value = "/json/setBulan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> tanggal(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
       
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        
        mapData.put("aData", cetakService.getBulan(param));
        
        return mapData;
    }
    
    @RequestMapping(value = "/json/getIdInduk", method = RequestMethod.GET)
    public @ResponseBody
    Integer getIdInduk(final HttpServletRequest request) {
       final Integer idskpd = SipkdHelpers.getIntFromString(request.getParameter("idskpd"));

       return cetakService.getIdInduk(idskpd);
    }
    
}
