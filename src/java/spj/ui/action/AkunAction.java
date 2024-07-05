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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import spj.services.AkunServices;
import spj.util.BigDecimalPropertyEditor;
import spp.model.Pengguna;
import spp.model.Akun;

import spj.util.SqlDatePropertyEditor;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/akun")
public class AkunAction {

    private static final Logger log = LoggerFactory.getLogger(AkunAction.class);

    @Autowired
    AkunServices akunServices;

    @RequestMapping(value = "/listakun", method = RequestMethod.GET)
    public String indexlistakun(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "spj/akun/listakun";
    }
    
    @RequestMapping(value = "/listakunsalsoawal", method = RequestMethod.GET)
    public String listakunawal(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "spj/akun/listakunsalsoawal";
    }
    
    @RequestMapping(value = "/listakunbukubesarskpd", method = RequestMethod.GET)
    public ModelAndView listakunbukubesar(final Akun akun, final HttpServletRequest request,final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("spj/akun/listakunbukubesar", "refakun", akun);
    }
    
    @RequestMapping(value = "/listakunbukubesarprovinsi", method = RequestMethod.GET)
    public ModelAndView listakunbukubesarprovinsi(final Akun akun, final HttpServletRequest request,final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("spj/akun/listakunbukubesarprovinsi", "refakun", akun);
    }
    
    @RequestMapping(value = "/json/listakunjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listakunjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kode = request.getParameter("kode");
        final String nama = request.getParameter("nama");
        final String kodeAkunref = request.getParameter("kodeAkunref");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("kodeAkunref", kodeAkunref);
        param.put("nama", nama);
        param.put("kode", kode);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = akunServices.getBanyakAllAkun(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", akunServices.getAllAkun(param));
        return mapData;
    }
    
    @RequestMapping(value = "/json/listakun123", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listakun123(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kode = request.getParameter("kode");
        final String nama = request.getParameter("nama");
        final String kodeAkunref = request.getParameter("kodeAkunref");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("kodeAkunref", kodeAkunref);
        param.put("nama", nama);
        param.put("kode", kode);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = akunServices.getBanyakAkun123(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", akunServices.getAkun123(param));
        return mapData;
    }
    
    @RequestMapping(value = "/json/listakunbukubesar", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listakunbukubesar(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String idskpd = request.getParameter("idskpd");
        final String tahun = request.getParameter("tahun");
        final String nama = request.getParameter("nama");
        final String kode = request.getParameter("kode");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("kode", kode);
        param.put("nama", nama);
        param.put("idskpd", idskpd);
        param.put("tahun", tahun);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        
        if ("761".equals(idskpd) || "1234".equals(idskpd)){
            final long banyak = akunServices.getBanyakAkunBukuBesarPpkd(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", akunServices.getAkunBukuBesarPpkd(param));
        } else {
            final long banyak = akunServices.getBanyakAkunBukuBesar(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", akunServices.getAkunBukuBesar(param));
        }
        
        return mapData;
    }


    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
