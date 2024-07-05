/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
package spj.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spp.model.NoJurnal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import spj.services.NoJurnalServices;
import spj.util.BigDecimalPropertyEditor;

import spj.util.SqlDatePropertyEditor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/nojurnal")
public class NoJurnalAction {

    private static final Logger log = LoggerFactory.getLogger(NoJurnalAction.class);

    @Autowired
    NoJurnalServices nojurServices;
    
    @RequestMapping(value = "/listnojurnal2", method = RequestMethod.GET)
    public String indexlistnojur(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "nojurnal/listnojurnalskpd";
    }

    @RequestMapping(value = "/listnojurnal", method = RequestMethod.GET)
    public ModelAndView add(final NoJurnal nojur, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("nojurnal/listnojurnal", "refnojur", nojur);
    } 
    
    @RequestMapping(value = "/listnojurnalskpd", method = RequestMethod.GET)
    public ModelAndView addlist(final NoJurnal nojur, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("nojurnal/listnojurnalskpd", "refnojur", nojur);
    } 
    
    @RequestMapping(value = "/json/listnojurnal", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listnojurjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        
        final String idskpd = request.getParameter("idskpd");
        final String kodegrup = request.getParameter("kodegrup");
        
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("idskpd", idskpd);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        if ("9".equals(kodegrup)){
            log.debug("MASUK KE KODE GROUP === "+kodegrup);
            final long banyak = nojurServices.getBanyakNoJurnal(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", nojurServices.getNoJurnal(param));
            
       }
       
       if ("11".equals(kodegrup)){
            log.debug("MASUK KE KODE GROUP 11");
            final long banyak2 = nojurServices.getBanyakNoJurnalAll(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak2);
            mapData.put("iTotalDisplayRecords", banyak2);
            mapData.put("aaData", nojurServices.getNoJurnalAll(param));
        }
        
        return mapData;
    }
    
    @RequestMapping(value = "/json/listnojurnalskpd", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listnojurskpdjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        
        final String idskpd = request.getParameter("idskpd");
        final String kodegrup = request.getParameter("kodegrup");
        
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("idskpd", idskpd);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        if ("9".equals(kodegrup)){
            final long banyak = nojurServices.getBanyakNoJurnalSkpd(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", nojurServices.getNoJurnalSkpd(param));
       }
       
       if ("11".equals(kodegrup)){
            final long banyak2 = nojurServices.getBanyakNoJurnalSkpdAll(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak2);
            mapData.put("iTotalDisplayRecords", banyak2);
            mapData.put("aaData", nojurServices.getNoJurnalSkpdAll(param));
        }
        
        return mapData;
    }


    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
