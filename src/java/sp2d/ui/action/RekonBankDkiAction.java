package sp2d.ui.action;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sp2d.services.RekonBankDkiServices;
import sp2d.util.SipkdHelpers;
import sp2d.util.SqlDatePropertyEditor;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/rekonbankdki")
public class RekonBankDkiAction {

    private static final Logger log = LoggerFactory.getLogger(RekonBankDkiAction.class);
    @Autowired
    RekonBankDkiServices rekonServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/indexrekon", method = RequestMethod.GET)
    public String indexrekon(final HttpServletRequest request) {
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        return "rekon/rekon";
    }

    @RequestMapping(value = "/json/getrekon", method = RequestMethod.GET)
    public void testxt(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");

        final String tanggal = request.getParameter("tanggal");
        final Map<String, Object> map = new HashMap<String, Object>();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        map.put("tanggal", tanggal);
        map.put("tahun", tahunAnggaran);

        List<Map> listhasil = rekonServices.getListRekon(map);

        final Integer banyarekon = rekonServices.getBanyakRekon(map);

        String rekon = "";
        String spasi = "";
        String data1, data2, norek;
        Integer panjang, banyak;

        for (int i = 0; i < banyarekon; i++) {
            data1 = (String) listhasil.get(i).get("REKON1");
            data2 = (String) listhasil.get(i).get("REKON2");
            norek = (String) listhasil.get(i).get("NOREK");

            panjang = listhasil.get(i).get("NOREK").toString().length();
            banyak = 20 - panjang;

            for (int j = 0; j < banyak; j++) {
                spasi = spasi + " ";
            }
            
           // log.debug("************** REKON NOREK + SPASI ===== "+norek + spasi);
            rekon = rekon + data1 + norek + spasi + data2 + "\n";
        }

        try {

            //final String pathReport = servletContext.getInitParameter("PATH_REPORT");
            //final String data = "TES CREATE TXT FILE";
            final String filename = "Rekon SP2D " + tanggal + ".prn";
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            response.setContentType("application/pdf");
            ServletOutputStream output = response.getOutputStream();
            output.print(rekon);
            output.close();

        } catch (Exception e) {
            //e.printStackTrace();
            e.getMessage();
        }
    }

    @InitBinder
    public
            void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        //webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
