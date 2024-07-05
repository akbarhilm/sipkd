package spm.ui.action;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spp.model.Akun;
import spp.model.Bank;
import spp.model.Pengguna;
import spm.services.ReferensiServices;
import spm.util.SipkdHelpers;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/refakun")
public class ReferensiAkunAction {

    private static final Logger log = LoggerFactory.getLogger(ReferensiAkunAction.class);
    @Autowired
    ReferensiServices referensiServices;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "ref/akun/index";
    }

    @RequestMapping(value = "/json/listdataakunrootjson", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> listdataskpdrootjson() {
        return referensiServices.getAllAkunRoot();
    }

    @RequestMapping(value = "/json/listdataakunanakjson", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> listdataskpdanakjson(final HttpServletRequest request) {
        final String id = request.getParameter("id");
        final String kode = request.getParameter("kode");
        final Integer level = SipkdHelpers.getIntFromString(request.getParameter("level"));
        final Map map = new HashMap(1);
        map.put("induk", id);
        map.put("kode", kode);
        map.put("level", level + 1);
        return referensiServices.getAllAkunAnak(map);
    }

    @RequestMapping(value = "/index/updateakun/{id}", method = RequestMethod.GET)
    public ModelAndView editakun(@PathVariable Integer id, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        final Akun akun = referensiServices.getAkunById(id);
        return new ModelAndView("/ref/akun/editakun", "progcmd", akun);
    }
    @RequestMapping(value = "/index/tambahakun/{id}", method = RequestMethod.GET)
    public ModelAndView tambahakun(@PathVariable Integer id, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        final Akun akun = referensiServices.getAkunByIdTambah(id);
        return new ModelAndView("/ref/akun/tambahakun", "progcmd", akun);
    }

    @RequestMapping(value = "/prosesupdateakun", method = RequestMethod.POST)
    public String prosesupdate(@Valid @ModelAttribute("progcmd") Akun akun, BindingResult result, final HttpServletRequest request) {
        System.out.println(" result.hasErrors() = " + result.hasErrors());
        if (result.hasErrors()) {
            return "/common/ref/akun/editakun";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            akun.setIdEdit(pengguna.getIdPengguna());
            akun.setTglEdit(new Timestamp(System.currentTimeMillis()));
            referensiServices.updateAkun(akun);
        }
        return "redirect:/refakun/index";
    }
    
    @RequestMapping(value = "/prosessimpanakun", method = RequestMethod.POST)
    public String prosessimpan(@Valid @ModelAttribute("progcmd") Akun akun, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/common/ref/akun/tambahakun";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            akun.setIdEdit(pengguna.getIdPengguna());
            akun.setTglEdit(new Timestamp(System.currentTimeMillis()));
            referensiServices.insertAkun(akun);
        }
        return "redirect:/bank";
    }
}
