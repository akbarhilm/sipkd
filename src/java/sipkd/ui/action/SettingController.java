package sipkd.ui.action;

import java.io.IOException;
import java.util.logging.Level;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sipkd.model.Pengguna;
import sipkd.util.SipkdPref;

@Controller
@RequestMapping("/settings")
public class SettingController {

    private static final Logger log = LoggerFactory.getLogger(SettingController.class);
    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/printer", method = RequestMethod.GET)
    public String printer(final HttpServletRequest request) {
        try {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            final SipkdPref sipkdPref = new SipkdPref();
            request.setAttribute("printer", sipkdPref.getPrinter(pengguna.getNamaPengguna(), servletContext.getRealPath("/WEB-INF/pref/")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "setting/printer";
    }

    @RequestMapping(value = "/json/saveprinter", method = RequestMethod.POST)
    public @ResponseBody
    String saveprinter(@RequestBody String printer, final HttpServletRequest request) {
        try {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            final SipkdPref sipkdPref = new SipkdPref();
            sipkdPref.setPrinter(pengguna.getNamaPengguna(), servletContext.getRealPath("/WEB-INF/pref/"), printer);
            request.setAttribute("printer", printer);
            return "Simpan Setting Printer Berhasil";
        } catch (IOException ex) {
            return "Simpan Setting Printer gagal";
        }

    }
}
