package sipkd.ui.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sipkd.services.BeritaLoginServices;
import sipkd.services.UserManagementServices;


/**
 *
 * @author zainab
 */
@Controller
@RequestMapping("/beritalogin")
public class BeritaLoginAction {

    private static final Logger log = LoggerFactory.getLogger(BeritaLoginAction.class);
    @Autowired
    BeritaLoginServices beritaService;
    
    @Autowired
    UserManagementServices userManagementServices;

    
    
    @RequestMapping(value = "/json/getBerita", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getBerita(final HttpServletRequest request) {
        //final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        log.debug("************ GET BERITA IN ACTION ");
        final Map< String, Object> param = new HashMap<String, Object>(3);
       // param.put("tahun", tahunAnggaran);
       
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
	
	//mapData.put("aData", userManagementServices.getBerita(param));
        
        log.debug("************ MAPDATA ==  "+ mapData);
        
        mapData.put("aData", "TES");
        return mapData;
    } 
    
    
    /*@RequestMapping(value = "/json/getBerita", method = RequestMethod.POST)
    public @ResponseBody
    String getBerita(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        log.debug("===================== GET BERITA =====================");
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", "TES DATA");

        return "get berita";
    }*/
    
    @RequestMapping(value = "/json/getfilepdf", method = RequestMethod.GET)
    public void getfilepdf(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //final String pathReport = servletContext.getInitParameter("PATH_PDF");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            response.setContentType("APPLICATION/OCTET-STREAM");
            final String filename = "File.pdf";
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            response.setContentType("application/pdf");
            FileInputStream fileInputStream = new FileInputStream("D:/pdf_berita/permendagri-32-2017.pdf");
            int i;
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
            fileInputStream.close();
            out.close();
                    
        } catch (Exception e) {
            //e.printStackTrace();
            e.getMessage();
        }
    }
}
