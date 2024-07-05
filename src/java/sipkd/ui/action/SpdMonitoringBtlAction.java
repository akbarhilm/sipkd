package sipkd.ui.action;

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
import sipkd.services.ReferensiServices;
import sipkd.services.SpdService;
import sipkd.model.Pengguna;
import sipkd.util.SipkdHelpers;


/**
 *
 * @author sapto
 */
@Controller
@RequestMapping("/spd/monitoringbtl")
public class SpdMonitoringBtlAction {

    private static final Logger log = LoggerFactory.getLogger(SpdMonitoringBtlAction.class);
    @Autowired
    SpdService spdService;

    @Autowired
    ReferensiServices referensiServices;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;

  
    
    @RequestMapping(value = "/indexmonitoringbtl", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
              
        return "spd/monitoring/monitoringbtl";

    }

    
    
  @RequestMapping(value = "/json/prosescetakbtl", method = RequestMethod.GET)
   public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String kodewilayah = request.getParameter("kproses");
            final String idskpd =  request.getParameter("idskpd");
            final String namwil = request.getParameter("namwil");
            final String tanggal =  request.getParameter("tgl");
            final String tgll = SipkdHelpers.getStringDateFormatFromString(tanggal, "yyyyMMdd", "dd/MM/yyyy");
        try {
            final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");
            final String data = request.getParameter("data");
            //log.debug(data);
            
            map.put("SUBREPORT_DIR", pathReport);
            map.put("thn", tahunAnggaran);
            map.put("kw", kodewilayah);
            map.put("idskpd", idskpd);
            map.put("np", spdService.getnilaiparam(map));
           
              
                        map.put("ID_SKPD",idskpd);
                        map.put("TAHUN",tahunAnggaran);
                         map.put("TANGGAL",tgll);
                        List<Map> listhasil = spdService.getnilaiparam(map);
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
                        //JasperPrint jasperPrint = JasperFillManager.fillReport(servletContext.getRealPath("WEB-INF/report/Report_SPP-UPGU.jasper"), map, jdbcConnection);
                        JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_MonitoringPengeluaran_BTL.jasper", map, jdbcConnection);
                        final String filename = tahunAnggaran+"-"+"SPD-MONITORING-BTL"+"-"+idskpd+".pdf";
                        response.setHeader("Content-disposition", "attachment; filename="+ filename);
                        response.setContentType("application/pdf");
                        ServletOutputStream output = response.getOutputStream();
                        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                        output.close();
                 

        } catch (Exception e) {

            e.printStackTrace();

        }

      
        
    }

}
