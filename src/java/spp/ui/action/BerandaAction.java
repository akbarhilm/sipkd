package spp.ui.action;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spp.model.Pengguna;
import spp.services.UserManagementServices;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/beranda")
public class BerandaAction {

    private static final Logger log = LoggerFactory.getLogger(BerandaAction.class);
    @Autowired
    UserManagementServices userManagementServices;
    /* private static final Properties properties = new Properties();

     static {
     try {
     properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("sso.properties"));
     } catch (IOException ex) {
     ex.printStackTrace();
     }
     }*/

    @RequestMapping(method = RequestMethod.GET)
    public String index(final Principal principal, final HttpServletRequest req) {
        //  log.debug(" namaaaaa  " + new StringBuilder(principal.getName()).append(" tahunAnggaran ").append(req.getSession().getAttribute("tahunAnggaran")).toString());
        final Pengguna pengguna = userManagementServices.loadPenggunaByUsername(principal.getName());
        req.getSession().setAttribute("pengguna", pengguna);
        req.getSession().setAttribute("tahunAnggaran", req.getSession().getAttribute("tahunAnggaran")/*SipkdHelpers.splitString(principal.getName(), "|", 1) */);
//sso         
// req.getSession().setAttribute("logoutsso", properties.getProperty("sso.logout"));
        return "dashboard/index";
    }
}
