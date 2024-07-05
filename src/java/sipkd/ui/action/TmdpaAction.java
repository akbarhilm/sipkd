/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipkd.ui.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sipkd.model.Tmdpa;
import sipkd.model.Pengguna;
//import spp.model.Rekanan;
//import spp.services.RekananServices;
//import spp.services.
import sipkd.services.TmdpaServices;

/**
 *
 * @author Xalamaster
 */
@Controller
@RequestMapping("/tmdpa")
public class TmdpaAction {

    @Autowired
    TmdpaServices tmdpaServices;

    private static final Logger log = LoggerFactory.getLogger(TmdpaAction.class);

    //@Autowired
    //RekananServices rekananServices;
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(Tmdpa tmdpa) {
        return new ModelAndView("/ref/tmdpa/indextmdpa", "cmdTmdpa", tmdpa);
    }
    
    @RequestMapping(value = "/skpdlistpopup", method = RequestMethod.GET)
    public String listpopup(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "/ref/tmdpa/skpdlistpopup";
    }


    @RequestMapping(value = "/ubahtmdpa", method = RequestMethod.POST)
    public String ubahtmdpa(@Valid @ModelAttribute("cmdtmdpa") Tmdpa tmdpa, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return ("/ref/tmdpa/indextmdpa");
        } else {

        }
        return "redirect:/tmdpa";
    }
     
    @RequestMapping(value = "/json/listskpdjson", method = RequestMethod.GET)
        public @ResponseBody
    Map<String, Object> listskpdjson(final HttpServletRequest request) {
        
        final Map< String, Object> param = new HashMap<String, Object>(6);
        
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("skpd");
        final String tahunAnggaran =  (String) request.getSession().getAttribute("tahunAnggaran");
        
        param.put("tahun", tahunAnggaran);
        param.put("skpd", skpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        
        final long banyak = tmdpaServices.getBanyakSkpd(param);
        
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", tmdpaServices.getAllTmdpa(param));
        return mapData;
    }
    
    
        @RequestMapping(value = "/updatetmdpa", method = RequestMethod.POST)
    public String updatetmdpa(@Valid @ModelAttribute("cmdTmdpa") Tmdpa tmdpa, BindingResult result, final RedirectAttributes redirectAttributes, final HttpServletRequest request) {
        System.out.println(" result.hasErrors() = "+ result.hasErrors());
        /*if (result.hasErrors()) {
            return "redirect:/tmdpa";
        } else {
         final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            
        tmdpa.setIdEdit(pengguna.getIdPengguna());
        //tmdpa.setTglEdit(new Timestamp(System.currentTimeMillis()));
        tmdpaServices.updateTmdpa(tmdpa);
        }*/
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna"); 
        tmdpa.setIdEdit(pengguna.getIdPengguna());
        tmdpa.setTglEdit(new Timestamp(System.currentTimeMillis()));
        tmdpaServices.updateTmdpa(tmdpa);
        
        final StringBuilder sburl = new StringBuilder("redirect:/tmdpa");
        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ").append(" berhasil diubah ").toString());
        return "redirect:/tmdpa";
        
    }
    
    
    /*
    @RequestMapping(value = "/json/gettmdpa/{id}", method = RequestMethod.GET)
     public @ResponseBody
     Map<String, Object> gettmdpa(@PathVariable Integer id, final HttpServletRequest request) {
    final String tahunAnggaran =  request.getParameter("tahun");

     final Map< String, Object> param = new HashMap<String, Object>(2);
     param.put("tahun", tahunAnggaran);
     param.put("idskpd", id);
     return tmdpaServices.getTmdpa(param);
     } */
/*
      @RequestMapping(value = "/json/gettmdpa/{id}/{tahun}", method = RequestMethod.GET)
     public @ResponseBody
     List<Tmdpa> gettmdpa(@PathVariable Integer id, @PathVariable Integer tahunAnggaran, final HttpServletRequest request) {
     //final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        
     final Map< String, Object> param = new HashMap<String, Object>(2);
     param.put("tahun", tahunAnggaran);
     param.put("idskpd", id);
     return tmdpaServices.getTmdpa(param);
     }*/
/*
    @RequestMapping(value = "/json/gettmdpa/{id}/{tahun}", method = RequestMethod.GET)
    public @ResponseBody
    List<Tmdpa> gettmdpa(@PathVariable Integer id, @PathVariable Integer tahunAnggaran, final HttpServletRequest request) {
        // final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map<String, Object> param = new LinkedHashMap<String, Object>(2);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", id);
        return tmdpaServices.getTmdpa(param);
    }*/
}
