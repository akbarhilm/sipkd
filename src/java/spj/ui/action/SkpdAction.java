package spj.ui.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import spp.model.Pengguna;
import spp.model.Skpd;
import spj.services.ReferensiServices;
import spj.util.SipkdHelpers;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/common")
public class SkpdAction {

    private static final Logger log = LoggerFactory.getLogger(ReferensiSppPaguUpAction.class);

    @Autowired
    ReferensiServices referensiServices;

    @RequestMapping(value = "/listskpd", method = RequestMethod.GET)
    public String index(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "common/listskpd";
    }

    // LIST SKPD ALL UNTUK SP2DBTLLS
    @RequestMapping(value = "/listskpdall", method = RequestMethod.GET)
    public String indexskpdall(final HttpServletResponse response, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "common/listskpdall";
    }

    @RequestMapping(value = "/listskpdwil", method = RequestMethod.GET)
    public String indexskpdwill(final HttpServletResponse response, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        request.setAttribute("pengguna", pengguna);
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "common/listskpdwill";
    }

    @RequestMapping(value = "/listskpdbantuan", method = RequestMethod.GET)
    public String indexskpdbantuan(final HttpServletResponse response, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        request.setAttribute("pengguna", pengguna);
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "common/listskpdbantuan";
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
        param.put("skpd", skpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = referensiServices.getBanyakSkpdBL(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", referensiServices.getAllSkpdBL(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listskpdjsonbantuan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsonbantuan(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("skpd");
        final String kodeskpd = request.getParameter("skpd");
        
        param.put("kodeskpd", kodeskpd);
        param.put("skpd", skpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = referensiServices.getBanyakSkpdBantuan(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", referensiServices.getAllSkpdBantuan(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listskpdjsonall", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsonall(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("skpd");
        final String kodeskpd = request.getParameter("kodeskpd");
        final String tahun = request.getParameter("tahun");

        param.put("skpd", skpd);
        param.put("kodeskpd", kodeskpd);
        param.put("tahun", tahun);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = referensiServices.getBanyakSkpdAll(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", referensiServices.getSkpdAll(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listskpdjsonwil", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsonwil(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("skpd");
        final String tahun = request.getParameter("tahun");
        final String kodewilayah = request.getParameter("kodewilayah");
        final String kodeskpd = request.getParameter("kodeskpd");

        param.put("kodeskpd", kodeskpd);
        param.put("skpd", skpd);
        param.put("kodewilayah", kodewilayah);
        param.put("tahun", tahun);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = referensiServices.getBanyakSkpdWil(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", referensiServices.getSkpdWil(param));
        return mapData;
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
        final long banyak = referensiServices.getBanyakPejabatPPKD(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", referensiServices.getAllPejabatPpkd(param));
        return mapData;
    }

    @RequestMapping(value = "/listskpdbankrek", method = RequestMethod.GET)
    public String listskpdbankrek(final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        return "common/popupbankrek";
    }
}
