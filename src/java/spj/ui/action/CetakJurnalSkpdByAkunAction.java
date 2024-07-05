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
import spj.services.CetakSkpdByNojurServices;
import spp.model.CetakSkpd;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import spj.services.CetakReportServices;


/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/cetakbyakun")
public class CetakJurnalSkpdByAkunAction {

    private static final Logger log = LoggerFactory.getLogger(CetakJurnalSkpdByAkunAction.class);
    @Autowired
    CetakReportServices cetakReportServices;
    
    @Autowired
    CetakSkpdByNojurServices cetakService;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;

    
    @RequestMapping(value = "/indexcetak", method = RequestMethod.GET)
    public ModelAndView add(final CetakSkpd cetak, final HttpServletRequest request, Model model) {
        
        return new ModelAndView("cetak/jurskpdbyakun", "refcetak", cetak);
    }
    
        
    @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        //final String kodewilayah = request.getParameter("kproses");
        final String idskpd = request.getParameter("idskpd");
        final String tglpost = request.getParameter("tglpost");
        final String idbas = request.getParameter("idbas");
            
        try {
            //final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");
            
            map.put("SUBREPORT_DIR", pathReport);
            map.put("thn", tahunAnggaran);
           
            map.put("TAHUN",tahunAnggaran);
            map.put("TANGGAL",tglpost);
            map.put("IDSKPD",idskpd);
            map.put("IDBAS",idbas);
            
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
            
            log.debug("idskpd =========== "+idskpd);

           if (idbas == ""){
               map.put("pathreport", pathReport + "/Report_JurnalSkpd_Idskpd.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_JurnalSkpd_Idskpd.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"JURNAL-UMUM-SKPD"+"-"+idskpd+"-"+tglpost+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
                
           } else {
               map.put("pathreport", pathReport + "/Report_JurnalSkpd_Idbas.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_JurnalSkpd_Idbas.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"JURNAL-UMUM-SKPD"+"-"+idskpd+"-"+idbas+"-"+tglpost+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
           }
           

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
