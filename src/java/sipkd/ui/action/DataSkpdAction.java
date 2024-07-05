/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipkd.ui.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sipkd.services.SpdBtlBantuanServices;
import sipkd.services.SpdService;
import sipkd.services.SpdRevService;
import sipkd.util.SipkdHelpers;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/common")
public class DataSkpdAction {

    @Autowired
    SpdService spdService;

    @Autowired
    SpdRevService spdRevService;

    @Autowired
    SpdBtlBantuanServices spdBtlBantuanServices;

    @RequestMapping(value = "/listskpd", method = RequestMethod.GET)
    public String index(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "common/listskpd";
    }

    @RequestMapping(value = "/listskpdbtl", method = RequestMethod.GET)
    public String indexbtl(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "common/listskpdbtl";
    }

    @RequestMapping(value = "/listskpdrev", method = RequestMethod.GET)
    public String indexrev(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "common/listskpdrev";
    }

    @RequestMapping(value = "/listskpdbtlrevjson", method = RequestMethod.GET)
    public String indexbtlrev(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "common/listskpdbtlrevjson";
    }

    @RequestMapping(value = "/json/listskpdbtljson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdbtljson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("skpd");
        param.put("skpd", skpd);
        param.put("tahun", (String) request.getSession().getAttribute("tahunAnggaran"));
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = spdService.getBanyakAllSkpdBTL( param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdService.getAllSkpdBTL( param));
        return mapData;
    }

    @RequestMapping(value = "/json/listskpdbtlrevjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdbtlrevjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("skpd");
        param.put("skpd", skpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = spdRevService.getBanyakAllSkpd(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdRevService.getAllSkpd(param));
        return mapData;
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
        final String levelSkpd = request.getParameter("levelSkpd");
        param.put("skpd", skpd);
        param.put("tahun", (String) request.getSession().getAttribute("tahunAnggaran"));
        param.put("levelSkpd", levelSkpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = spdService.getBanyakAllSkpd(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdService.getAllSkpd(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listskpdjsonrev", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsonrev(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("skpd");
        final String levelSkpd = request.getParameter("levelSkpd");
        param.put("skpd", skpd);
        param.put("levelSkpd", levelSkpd);
        param.put("tahun", (String) request.getSession().getAttribute("tahunAnggaran"));
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = spdRevService.getBanyakAllSkpd(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdRevService.getAllSkpd(param));
        return mapData;
    }

    @RequestMapping(value = "/listskpdkordinatorbtlbantuan", method = RequestMethod.GET)
    public String listskpdkordinatorbtlbantuan(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "common/listskpdkordinatorbtlbantuan";
    }

    @RequestMapping(value = "/json/listskpdbtlbantuanjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdbtlbantuanjson(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("skpd");
        param.put("skpd", skpd);
        param.put("offset", offset);
        param.put("tahun", (String) request.getSession().getAttribute("tahunAnggaran"));
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = spdBtlBantuanServices.getBanyakSkpdBtlBantuan(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdBtlBantuanServices.getAllSkpdBtlBantuan(param));
        return mapData;
    }

    @RequestMapping(value = "/listUrusan", method = RequestMethod.GET)
    public String indexUrusan(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "common/listUrusan";
    }

    @RequestMapping(value = "/json/getpagudansisa/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, BigDecimal> getpagudansisa(@PathVariable Integer id, final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        return spdService.getPaguDanSisa(id, tahunAnggaran);
    }

    @RequestMapping(value = "/json/getpagudansisarev/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, BigDecimal> getpagudansisarev(@PathVariable Integer id, final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        return spdRevService.getPaguDanSisaRev(id, tahunAnggaran);
    }

    @RequestMapping(value = "/listpejabatppkd/{id}/{kode}/{nilai}", method = RequestMethod.GET)
    public String listpejabatppkd(@PathVariable Integer id, @PathVariable String kode, @PathVariable Double nilai, final HttpServletResponse response, final HttpServletRequest request) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        request.setAttribute("id", id);
        request.setAttribute("kode", kode);
        request.setAttribute("nilai", nilai);
        return "common/listpejabatppkd";
    }

    @RequestMapping(value = "/json/listpejabatppkdjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpejabatppkdjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String nilaispd = request.getParameter("nilaiSpd");
        final String kode = request.getParameter("kode");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("kodeDok", kode);
        param.put("nilaispd", SipkdHelpers.getBigDecimalFromString(nilaispd));
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = spdService.getBanyakPejabatPPKD(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdService.getAllPejabatPpkd(param));
        return mapData;
    }
}
