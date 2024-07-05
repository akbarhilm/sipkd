package spp.ui.action;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.services.CetakValidasiSPPServices;
import spp.util.SipkdHelpers;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/validasispp")
public class ValidasiSppAction {

    private static final Logger log = LoggerFactory.getLogger(ValidasiSppAction.class);
    @Autowired
    CetakValidasiSPPServices cetakValidasiSPPServices;
    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        return "validasi/spp/index";

    }

    @RequestMapping(value = "/json/getlistsppvalidasi", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspppup(final HttpServletRequest request) {

        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("namaskpd");
        final String idskpd = request.getParameter("idskpd");
        final Map< String, Object> param = new HashMap<String, Object>(2);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String addoredit = request.getParameter("addoredit");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("namaskpd", skpd);
        param.put("idskpd", SipkdHelpers.getIntFromString(idskpd));

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = cetakValidasiSPPServices.getbanyaksppsah(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", cetakValidasiSPPServices.getlistsppsah(param));
        return mapData;
    }

    @RequestMapping(value = "/json/insertsppvalidasi", method = RequestMethod.POST)
    public @ResponseBody
    String submitcetak(@RequestBody List<Map<String, Object>> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        for (Map<String, Object> mapdetil : mapdata) {
            mapdetil.put("userid", pengguna.getIdPengguna()); 
            mapdetil.put("tglentry", new Timestamp(System.currentTimeMillis()));
            cetakValidasiSPPServices.insertsppsah(mapdetil);
        }

        return "simpan data cetak sukes";

    }
}
