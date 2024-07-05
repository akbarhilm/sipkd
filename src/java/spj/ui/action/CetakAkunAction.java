package spj.ui.action;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spj.util.SipkdHelpers;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/cetakakun")
public class CetakAkunAction {

    private static final Logger log = LoggerFactory.getLogger(CetakAkunAction.class);
    @Autowired
    DataSource dataSource;
    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/reports/{format}/{id}", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response, @PathVariable("format") String format, @PathVariable Integer id) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        try {
            final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            log.debug(request.getContextPath() + "/static/assets/img/logodki-0000.jpg");
            map.put("logo", "/logo/logodki-0000.jpg");
            map.put("value", id);
            JasperPrint jasperPrint = JasperFillManager.fillReport(servletContext.getRealPath("WEB-INF/report/Report_Akun.jasper"), map, jdbcConnection);
            final String filename = "daftarakun.pdf";
            response.setHeader("Content-Disposition", "filename=" + filename);
            ServletOutputStream output = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            output.close();
            
        } catch (Exception e) {

            e.printStackTrace();

            
        }
        
        
    }
    
    
}
