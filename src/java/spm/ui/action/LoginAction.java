package spm.ui.action;

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
import spm.services.BeritaLoginServices;
import spp.model.Berita;
import spp.model.LoginForm;

/**
 *
 * @author User
 */
@Controller
public class LoginAction {

    private static final Logger log = LoggerFactory.getLogger(LoginAction.class);

    @Autowired
    BeritaLoginServices beritaService;

    @RequestMapping(value = "/login2", method = RequestMethod.GET)
    public String index(@ModelAttribute LoginForm loginForm) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView index(final LoginForm loginForm, final HttpServletRequest request, Model model) {
        final Map< String, Object> param = new HashMap<String, Object>(3);
        
        final List<Berita> berita = beritaService.getBerita(param);
        model.addAttribute("listBerita", berita);
        
        final Map<String, Object> img = beritaService.getImagePopup(param);
        final Integer banyakImg = beritaService.getBanyakImagePopup(param);
        
        if (banyakImg > 0){
            request.setAttribute("pathImage", img.get("N_PDF_FILE"));
        }
        
        request.setAttribute("banyak", beritaService.getBanyakBerita(param));
        request.setAttribute("banyakImage", beritaService.getBanyakImagePopup(param));
        
        return new ModelAndView("login", "formlogin", loginForm);
    }


    @RequestMapping(value = "/deny", method = RequestMethod.GET)
    public String denied() {
        return "login";
    }

    @RequestMapping(value = "/expired", method = RequestMethod.GET)
    public String expired() {
        return "login";
    }

    
    
 
}
