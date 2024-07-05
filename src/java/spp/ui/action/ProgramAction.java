/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spp.model.Pengguna;
import spp.model.Program;
import spp.services.ProgramServices;
import spp.util.BigDecimalPropertyEditor;
import spp.util.SqlDatePropertyEditor;

/**
 *
 * @author maman sulaeman
 */
@Controller
@RequestMapping("/program")
public class ProgramAction {

    private static final Logger log = LoggerFactory.getLogger(ProgramAction.class);
    @Autowired
    ProgramServices programServices;
   

    @RequestMapping(method = RequestMethod.GET)
    public String index() {

        return "/ref/program/listprogram";
    }

       @RequestMapping(value = "/addprogram", method = RequestMethod.GET)
    public ModelAndView addprogram(Program program) {
        return new ModelAndView("/ref/program/addprogram", "progcmd", program);
    }
    
       @RequestMapping(value = "/deleteprogram/{id}", method = RequestMethod.GET)
    public ModelAndView deleteprogram(@PathVariable Integer id) {
         Program program = programServices.getProgramById(id);
        return new ModelAndView("/ref/program/deleteprogram", "spdBTLMaster", program);
    }
    
    
       @RequestMapping(value = "/updateprogram/{id}", method = RequestMethod.GET)
    public ModelAndView updateprogram(@PathVariable Integer id) {
        final Program program = programServices.getProgramById(id);
        return new ModelAndView("/ref/program/updateprogram", "spdBTLMaster", program);
    }
    

    
    
      @RequestMapping(value = "/listurusan", method = RequestMethod.GET)
    public ModelAndView listurusan(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("/ref/program/listurusan");
    }


    @RequestMapping(value = "/json/listprogramjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdprogramjson(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String namaprogram = request.getParameter("namaProgram");
        param.put("namaprogram", namaprogram);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyaks = programServices.getBanyakAllProgram(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyaks);
        mapData.put("iTotalDisplayRecords", banyaks);
        mapData.put("aaData", programServices.getProgram(param));
        return mapData;
    }

    
    @RequestMapping(value = "/json/listurusanjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdprogjson(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("urusan");
        param.put("urusan", skpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyaks = programServices.getBanyakAllUrusan(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyaks);
        mapData.put("iTotalDisplayRecords", banyaks);
        mapData.put("aaData", programServices.getUrusan(param));
        return mapData;
    }

 
    
     @RequestMapping(value = "/simpanprog", method = RequestMethod.POST)
     public String simpanprog(@Valid @ModelAttribute("progcmd") Program program, BindingResult result, final HttpServletRequest request ) {
     if (result.hasErrors()) {
     return "/ref/program/addprogram";
     } else {
     final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            program.setIdEntry(pengguna.getIdPengguna());
            program.setTglEntry(new Timestamp(System.currentTimeMillis()));
     programServices.insertProgram(program);
    
     }
     return "redirect:/program";
     }
     
      @RequestMapping(value = "/updateprog", method = RequestMethod.POST)
    public String prosesupdate(@Valid @ModelAttribute("spdBTLMaster") Program program, BindingResult result, final HttpServletRequest request) {
        System.out.println(" result.hasErrors() = " + result.hasErrors());
        if (result.hasErrors()) {
            return "/ref/program/updateprog";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            program.setIdEntry(pengguna.getIdPengguna());
            program.setTglEntry(new Timestamp(System.currentTimeMillis()));
            programServices.updateProgram(program);
        }
        return "redirect:/program";
    }
    
    
      @RequestMapping(value = "/deleteprog", method = RequestMethod.POST)
    public String deletebast(@Valid @ModelAttribute("spdBTLMaster") Program program) {
        programServices.deleteProgram(program.getIdProgram());
        return "redirect:/program";
    }
    
    
    
    
     
       @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}
