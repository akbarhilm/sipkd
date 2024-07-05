package spm.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
import spp.model.Pengguna;
import spp.model.SppPaguUp;
import spm.services.ReferensiServices;
import spm.util.BigDecimalPropertyEditor;
import spm.util.SipkdHelpers;
import spm.util.SqlDatePropertyEditor;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/referensi")
public class ReferensiSppPaguUpAction {
    
    private static final Logger log = LoggerFactory.getLogger(ReferensiSppPaguUpAction.class);
    
    @Autowired
    ReferensiServices referensiServices;
    
    @RequestMapping(value = "/spp/indexspppaguup", method = RequestMethod.GET)
    public String index() {
        return "ref/indexspppaguup";
        
    }
    
    @RequestMapping(value = "/spp/json/getlistspppaguup", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspppaguup(final HttpServletRequest request) {
        
        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("namaskpd");
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
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = referensiServices.getBanyakSppPaguUp(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", referensiServices.getAllSppPaguUp(param));
        return mapData;
    }
    
    @RequestMapping(value = "/spp/json/prosessimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpanspp(@RequestBody List<Map<String, String>> listmapdata, final HttpServletRequest request) {
        final Timestamp tglSkrg = new Timestamp(System.currentTimeMillis());
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        
        for (Map<String, String> mapdata : listmapdata) {
            log.debug(" mapdata " + mapdata.toString());
            SppPaguUp sppPaguUp = new SppPaguUp();
            sppPaguUp.setTglEdit(tglSkrg);
            sppPaguUp.setIdEdit(pengguna.getIdPengguna());
            sppPaguUp.setId(SipkdHelpers.getIntFromString(mapdata.get("idspp")));
            sppPaguUp.setNilaiSpp(SipkdHelpers.getBigDecimalFromString((String) mapdata.get("nilaispp")));
            referensiServices.updateSppPaguUp(sppPaguUp);
            sppPaguUp = null;
        }
        
        return "Data Pagu SPP GUP/UP  berhasil disimpan ";
    }
    
    @RequestMapping(value = "/spp/json/prosespindahtahun", method = RequestMethod.POST)
    public @ResponseBody
    String prosespindahtahun(@RequestBody Map<String, String> mapdata, final HttpServletRequest request) {
        final Timestamp tglSkrg = new Timestamp(System.currentTimeMillis());
        final String tahunbaru = mapdata.get("tahunbaru");
        final String tahunlama = mapdata.get("tahunlama");
        referensiServices.updateSppPaguUpTahun(mapdata);
        
        return "Data Pagu SPP GUP/UP  berhasil dipindah ";
    }
    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}
