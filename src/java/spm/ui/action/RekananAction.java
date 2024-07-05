/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.ui.action;

import java.sql.Timestamp;
import java.util.HashMap;
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
import spp.model.Pengguna;
import spp.model.Rekanan;
import spm.services.RekananServices;

/**
 *
 * @author reyvan adryan
 */
@Controller
@RequestMapping("/rekanan")
public class RekananAction {
    
    private static final Logger log = LoggerFactory.getLogger(RekananAction.class);
    @Autowired
    RekananServices rekananServices;
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "/ref/rekanan/listrekanan";
    }
    
    @RequestMapping(value = "/addrekanan", method = RequestMethod.GET)
    public ModelAndView addrekanan(Rekanan rekanan) {
     return new ModelAndView("/ref/rekanan/addrekanan", "progcmd", rekanan);
    
    }
    
    @RequestMapping(value = "/json/listjsonrekanan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsonrekanan(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String namarekanan = request.getParameter("namarekanan");
        param.put("namarekanan", namarekanan);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = rekananServices.getCountRekanan(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", rekananServices.getRekanan(param));
        return mapData;
    }
     @RequestMapping(value = "/prosessimpanr", method = RequestMethod.POST)
    public String prosessimpans(@Valid @ModelAttribute("progcmd") Rekanan rekanan, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/ref/rekanan/addrekanan";
        } else {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            rekanan.setIdEntry(pengguna.getIdPengguna());
            rekanan.setTglEntry(new Timestamp(System.currentTimeMillis()));
            rekananServices.insertRekanan(rekanan);
           
        }
         return "redirect:/rekanan";
        
    }
    @RequestMapping(value = "/delrekanan/{id}", method = RequestMethod.GET)
    public ModelAndView delrekanan(@PathVariable Integer id) {
        final Rekanan rekanan = rekananServices.getRekananById(id);
        return new ModelAndView("/ref/rekanan/delrekanan", "spdBTLMaster", rekanan);
    }
      
    @RequestMapping(value = "/deleterekanan", method = RequestMethod.POST)
    public String deletebank(@Valid @ModelAttribute("spdBTLMaster") Rekanan rekanan) {
        rekananServices.deleteRekanan(rekanan.getIdRekanan());
        return "redirect:/rekanan";
    }
    @RequestMapping(value = "/uprekanan/{id}", method = RequestMethod.GET)
    public ModelAndView uprekanan(@PathVariable Integer id) {
       
        final Rekanan rekanan = rekananServices.getRekananById(id);
        return new ModelAndView("/ref/rekanan/updaterekanan", "spdBTLMaster", rekanan);
    }
      
    @RequestMapping(value = "/updaterekanan", method = RequestMethod.POST)
    public String updaterekanan(@Valid @ModelAttribute("spdBTLMaster") Rekanan rekanan, BindingResult result, final HttpServletRequest request) {
        System.out.println(" result.hasErrors() = "+ result.hasErrors());
        if (result.hasErrors()) {
            return "/ref/rekanan/updaterekanan";
        } else {
         final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            
        rekanan.setIdEdit(pengguna.getIdPengguna());
        rekanan.setTglEdit(new Timestamp(System.currentTimeMillis()));
        rekananServices.updateRekanan(rekanan);
        }
        return "redirect:/rekanan";
        
    }
}
