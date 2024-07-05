package sipkd.ui.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import sipkd.model.Pengguna;
import sipkd.model.SpdPembatalan;
import sipkd.services.ReferensiServices;
import sipkd.services.SpdService;
import sipkd.services.SpdPembatalanRevService;
import sipkd.util.BigDecimalPropertyEditor;
import sipkd.util.SipkdHelpers;
import sipkd.util.SqlDatePropertyEditor;

/**
 *
 * @author zainab
 */
@Controller
@RequestMapping("/spd/pembatalanspdrev")
public class SpdPembatalanRevAction {

    private static final Logger log = LoggerFactory.getLogger(SpdPembatalanRevAction.class);
    @Autowired
    SpdService spdService;

    @Autowired
    SpdPembatalanRevService spdPembatalanRevService;

    @Autowired
    ReferensiServices referensiServices;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/pembatalanspdrev", method = RequestMethod.GET)
    public String pembatalan(final HttpServletResponse response, final HttpServletRequest request) throws IOException {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        boolean status = false;
       String ipaddr = null;
        try {
             ipaddr = pengguna.getIpAddress();
        } catch (Exception e) {
        }
        if (ipaddr != null && StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotBlank(ipaddr)) {
            //status = new IpAddressMatcher(ipaddr).matches(request) || new IpAddressMatcher("127.0.0.1").matches(request);
            //status = Objects.equals(ipaddr, SipkdHelpers.getIpAddr(request)) || new IpAddressMatcher("127.0.0.1").matches(request) || new IpAddressMatcher("0:0:0:0:0:0:0:1").matches(request);
            status = Objects.equals(ipaddr, SipkdHelpers.getIpAddr(request)) || new IpAddressMatcher("10.10.40.162").matches(request) || new IpAddressMatcher("0:0:0:0:0:0:0:1").matches(request);
        }
        log.debug(ipaddr + " status = " + status + " request " + SipkdHelpers.getIpAddr(request));
        status = true;
        if (status) {
            return "spd/pengajuanspd/pembatalanspdrev";
        } else {
            // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Anda tidak berhak mengakses halaman pengesahan");
            return "deny";
        }

    }

    @RequestMapping(value = "/json/getlistspdvalidasi", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspdvalidasi(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String id = request.getParameter("id");
        log.debug("id  = " + id);
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", id);
        log.debug(param.toString());
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = spdPembatalanRevService.getbanyakvalidasispd(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdPembatalanRevService.getlistvalidasispd(param));
        return mapData;

    }

    @RequestMapping(value = "/addbatalrev/{idSpdSah}/{keterangan}", method = RequestMethod.GET)
    public ModelAndView editbiaya(@PathVariable Integer idSpdSah, @PathVariable String keterangan,final HttpServletRequest request) {
        final SpdPembatalan batal = spdPembatalanRevService.getBatalById(idSpdSah);
        final String idskpd2 = batal.getNamaSkpd();
        request.setAttribute("jns", keterangan);
        request.setAttribute("namaSkpd", idskpd2);

        return new ModelAndView("spd/pengajuanspd/listbatalspdrev", "refsetor", batal);
    }

    @RequestMapping(value = "/simpanbatalrev", method = RequestMethod.POST)
    public
    String prosessimpan(@Valid @ModelAttribute("refsetor") SpdPembatalan batalSpd,
            BindingResult result, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = request.getParameter("tahunAngg");
        //final String jns = (String) request.getAttribute("jns");
        // log.debug("---- >> "+jns);
        //batalSpd.setIdEntry(pengguna.getIdPengguna());
        
         try {
                batalSpd.setIdEntry(pengguna.getIdPengguna());
            } catch (Exception e) {
                 batalSpd.setIdEntry(0);
            }
        
        Integer id = batalSpd.getIdSpdSah();
        final String jns = batalSpd.getKeterangan();
        log.debug("---- >> "+jns);
            final Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("value", id);
            parameter.put("tglentry", new Timestamp(System.currentTimeMillis()));
            parameter.put("stat", "P");
            parameter.put("status", "1");
           
             if (jns.equals("BTL")) {  /*di update jadi P lagi */
                spdPembatalanRevService.updatespdbatalrincibtl(parameter);
            } else if (jns.equals("BL")) {
                spdPembatalanRevService.updatespdbatalrincibl(parameter);
            } else if (jns.equals("BTL-BANTUAN")) {
                spdPembatalanRevService.updatespdbatalrincibtlbantuan(parameter);
                log.debug("----^^ >> "+jns);
                log.debug("----%% >> "+parameter);
            } else if (jns.equals("BIAYA")) {
                spdPembatalanRevService.updatespdbatalrincibiaya(parameter);
            } 
          
            spdPembatalanRevService.updatespdsahbatal(parameter);
            spdPembatalanRevService.insertThSpdSah(batalSpd);
      

        return "redirect:/spd/pembatalanspdrev/pembatalanspdrev";
    }
    
     

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}