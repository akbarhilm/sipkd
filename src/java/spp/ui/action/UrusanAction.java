package spp.ui.action;

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

import spp.model.Urusan;
import spp.services.UrusanServices;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/urusan")
public class UrusanAction {

    private static final Logger log = LoggerFactory.getLogger(UrusanAction.class);
    @Autowired
    UrusanServices urusanServices;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {

        return "/ref/urusan/listurusan";
    }

    @RequestMapping(value = "/json/listurusanjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsons(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String namaurusan = request.getParameter("namaurusan");
        param.put("namaurusan", namaurusan);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = urusanServices.getBanyakAllUrusan(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", urusanServices.getUrusan(param));
        return mapData;
    }

    @RequestMapping(value = "addurusan", method = RequestMethod.GET)
    public ModelAndView addrekanan(Urusan urusan) {
        return new ModelAndView("/ref/urusan/addurusan", "progcmd", urusan);

    }

    @RequestMapping(value = "/json/listfungsijson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjson(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("fungsi");
        param.put("fungsi", skpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = urusanServices.getBanyakAllFungsi(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", urusanServices.getAllFungsi(param));
        return mapData;
    }

    @RequestMapping(value = "/listfungsipopup", method = RequestMethod.GET)
    public ModelAndView listrekanan(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("/ref/urusan/listfungsipopup");
    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpan(@Valid @ModelAttribute("progcmd") Urusan urusan, BindingResult result, final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/urusan/addurusan";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            urusan.setIdEntry(pengguna.getIdPengguna());
            urusan.setTglEntry(new Timestamp(System.currentTimeMillis()));
            urusanServices.insertUrusan(urusan);
        }
        return "redirect:/urusan";
    }
    @RequestMapping(value = "/upurusan/{id}", method = RequestMethod.GET)
    public ModelAndView upurusan(@PathVariable Integer id) {
       
        final Urusan urusan = urusanServices.getUrusanById(id);
        return new ModelAndView("/ref/urusan/updateurusan", "progcmd", urusan);
    }
    @RequestMapping(value = "/delurusan/{id}", method = RequestMethod.GET)
    public ModelAndView delurusan(@PathVariable Integer id) {
       
        final Urusan urusan = urusanServices.getUrusanById(id);
        return new ModelAndView("/ref/urusan/deleteurusan", "progcmd", urusan);
    }
     @RequestMapping(value = "/prosesupdate", method = RequestMethod.POST)
    public String updaterekanan(@Valid @ModelAttribute("progcmd") Urusan urusan, BindingResult result, final HttpServletRequest request) {
        System.out.println(" result.hasErrors() = "+ result.hasErrors());
        if (result.hasErrors()) {
            return "/ref/urusan/upurusan";
        } else {
         final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            
        urusan.setIdEdit(pengguna.getIdPengguna());
        urusan.setTglEdit(new Timestamp(System.currentTimeMillis()));
        urusanServices.updateUrusan(urusan);
        }
        return "redirect:/urusan";
        
    }
    @RequestMapping(value = "/prosesdelete", method = RequestMethod.POST)
    public String deletebank(@Valid @ModelAttribute("progcmd") Urusan urusan) {
        urusanServices.deleteUrusan(urusan.getIdUrusan());
        return "redirect:/urusan";
    }
}
