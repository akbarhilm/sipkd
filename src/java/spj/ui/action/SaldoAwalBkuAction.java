package spj.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spj.services.SaldoAwalBkuServices;
import spp.model.SaldoAwalBku;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SqlDatePropertyEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spj.util.SipkdHelpers;
import spp.model.Pengguna;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/saldoAwalBku")
public class SaldoAwalBkuAction {

    private static final Logger log = LoggerFactory.getLogger(SaldoAwalBkuAction.class);
    @Autowired
    SaldoAwalBkuServices saldoServices;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/indexsaldoawal", method = RequestMethod.GET)
    public ModelAndView indexprosesbku(final SaldoAwalBku saldo, final HttpServletRequest request) {

        return new ModelAndView("saldoawal/saldoawal", "saldoawal", saldo);
    }

    @RequestMapping(value = "/json/listsaldoawal", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsaldoawal(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(2);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(5);
        final int banyak = saldoServices.getBanyakAkun(param);

        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("banyakData", banyak);
        mapData.put("aaData", saldoServices.getAkun(param));

        return mapData;

    }

    @RequestMapping(value = "/json/prosessimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<SaldoAwalBku> listRinci = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");

        for (Map<String, Object> mapnilailist : nilailist) {
            SaldoAwalBku saldoawal = new SaldoAwalBku();

            saldoawal.setTahun(tahun);
            saldoawal.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
            saldoawal.setIdEntry(pengguna.getIdPengguna());

            Object nilaisaldo = mapnilailist.get("nilaisaldo");
            Object idbas = mapnilailist.get("idbas");
            Object kodeakun = mapnilailist.get("kodeakun");
            Object status = mapnilailist.get("status");

            saldoawal.setNilaiSaldo(SipkdHelpers.getBigDecimalFromString(nilaisaldo.toString()));
            saldoawal.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            saldoawal.setKodeStatus(status.toString());
            saldoawal.setKodeAkun(kodeakun.toString());

            listRinci.add(saldoawal);

        }

        saldoServices.insertSaldoAwal(listRinci);

        return "Data Saldo Kas Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/getStatusSaldo", method = RequestMethod.GET)
    public @ResponseBody
    Integer getStatusSaldo(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        return saldoServices.getStatusSaldo(param);
    }
    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
