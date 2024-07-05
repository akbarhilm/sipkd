package sp2d.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spp.model.TrxBankDki;
import spp.model.Pengguna;
import sp2d.services.TrxBankDkiServices;
import sp2d.util.BigDecimalPropertyEditor;
import sp2d.util.SqlDatePropertyEditor;

@Controller
@RequestMapping("/transaksiBankDki")
public class TrxBankDkiAction {

    private static final Logger log = LoggerFactory.getLogger(TrxBankDkiAction.class);
    @Autowired
    TrxBankDkiServices trxServices;
    
    @RequestMapping(value = "/indextrx", method = RequestMethod.GET)
    public ModelAndView indextrx(final TrxBankDki trx, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        
        
        return new ModelAndView("trxbankdki/transaksi", "reftransaksi", trx);

    }
    
    
    @RequestMapping(value = "/json/getlisttransaksi", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlisttransaksi(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("namaskpd");
        final String kodewilayah = request.getParameter("wilproses");
        
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
        param.put("idskpd", idskpd);
        param.put("wilproses", kodewilayah);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = trxServices.getBanyakTransaksi(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", trxServices.getListTransaksi(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getComboUser", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getComboUser(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String wilayah = request.getParameter("kodewil");
        
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
	
	mapData.put("aData", trxServices.getComboUser(param));
        return mapData;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
