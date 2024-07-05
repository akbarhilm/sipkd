/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
package spm.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spp.model.Pengguna;
import spp.model.LoginForm;
import spm.services.UbahPasswordServices;
import spm.util.BigDecimalPropertyEditor;
import spm.util.SipkdHelpers;

import spm.util.SqlDatePropertyEditor;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/ubahpass")
public class UbahPasswordAction {

    private static final Logger log = LoggerFactory.getLogger(UbahPasswordAction.class);

    @Autowired
    UbahPasswordServices ubahServices;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(final LoginForm login, final HttpServletRequest request) {
        
        return new ModelAndView("ubahpass/ubahpass", "refbku", login);
    }

    @RequestMapping(value = "/json/prosesubah", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpansetorup(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        
        LoginForm login = new LoginForm();

        final String user = (String) mapdata.get("user");
        final String pass = (String) mapdata.get("pass");
        
        login.setUsername(user);
        login.setPasswordBaru(pass);
        login.setIdEntry(pengguna.getIdPengguna());
        
        ubahServices.updatePass(login);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

    private Integer Integer(String parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
