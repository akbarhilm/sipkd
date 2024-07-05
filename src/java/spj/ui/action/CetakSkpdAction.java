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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import spp.model.Skpd;
import spj.services.ReferensiServices;
import spj.util.SipkdHelpers;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/cetaksipkd")
public class CetakSkpdAction {

    private static final Logger log = LoggerFactory.getLogger(CetakSkpdAction.class);
    @Autowired
    DataSource dataSource;
    
    @Autowired
    ServletContext servletContext;
    ReferensiServices referensiServices;
  
    
  
    
    
 @RequestMapping(value = "/json/listdataskpdrootjson", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> listdataskpdrootjson() {
        return referensiServices.getAllSkpdRoot();
    }

    @RequestMapping(value = "/json/listdataskpdanakjson", method = RequestMethod.GET)
    public @ResponseBody
    List<Skpd> listdataskpdanakjson(final HttpServletRequest request) {
        final Integer id = SipkdHelpers.getIntFromString(request.getParameter("id"));
        final Map map = new HashMap(1);
        map.put("induk", id);
        return referensiServices.getAllSkpdAnak(map);
    }
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
            JasperPrint jasperPrint = JasperFillManager.fillReport(servletContext.getRealPath("WEB-INF/report/laporan_sipkd.jasper"), map, jdbcConnection);
            final String filename = "laporansipkd.pdf";
            response.setHeader("Content-Disposition", "filename=" + filename);
            ServletOutputStream output = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            output.close();
            
        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
