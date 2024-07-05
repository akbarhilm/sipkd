package spj.ui.action;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spp.model.Pengguna;
import spj.services.UserManagementServices;
import spp.model.Skpd;

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

    @RequestMapping(method = RequestMethod.GET)
    public String index(final Principal principal, final HttpServletRequest req) {
        log.debug(new StringBuilder(principal.getName()).append(" tahunAnggaran ").append(req.getSession().getAttribute("tahunAnggaran")).toString());
        final Pengguna pengguna = userManagementServices.loadPenggunaByUsername(principal.getName());
        req.getSession().setAttribute("pengguna", pengguna);
        final List<Skpd> listSkpd = pengguna.getSkpd();
        return "dashboard/index";
    }
}
