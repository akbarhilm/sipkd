package eset.ui.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import eset.services.BeritaLoginServices;
import eset.model.Berita;
import eset.model.LoginForm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author User
 */
@Controller
public class LoginAction {

    private static final Logger log = LoggerFactory.getLogger(LoginAction.class);

    @Autowired
    BeritaLoginServices beritaService;

     @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(@ModelAttribute LoginForm loginForm) {
        return "login";
    }

    @RequestMapping(value = "/deny", method = RequestMethod.GET)
    public String denied() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        return "login";
    }

    @RequestMapping(value = "/expired", method = RequestMethod.GET)
    public String expired() {
//        Authentication auth = SecurityContextHolder.getContext()
//                .getAuthentication();
//        final Pengguna pengguna = loginServices.loadUserByUsername(((Principal) auth.getPrincipal()).getName(), null);
//        loginServices.deleteLoginStatus(pengguna);
        return "login";
    }
    
    
 
}
