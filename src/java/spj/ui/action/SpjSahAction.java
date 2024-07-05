package spj.ui.action;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.sql.DataSource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import spj.services.CetakReportServices;
import spp.model.Skpd;
import spp.model.Pengguna;
import spj.services.CetakValidasiSPJServices;
import spj.util.SipkdHelpers;
import spj.services.UserManagementServices;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/spjsah")
public class SpjSahAction {

    private static final Logger log = LoggerFactory.getLogger(SpjSahAction.class);
    @Autowired
    CetakReportServices cetakReportServices;
    @Autowired
    UserManagementServices userManagementServices;
    @Autowired
    CetakValidasiSPJServices cetakValidasiSPJServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;

  /*  @RequestMapping(method = RequestMethod.GET)
    public String index(  final HttpServletRequest req) {
         return "pengesahan/index";
    }*/
    
    
    @RequestMapping(value = "/indexspjsah", method = RequestMethod.GET)
    public String indexspmup(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
       return "spj/pengesahan/index";

    }
    
    
  /*   @RequestMapping(value = "/indexspjsah", method = RequestMethod.GET)
    public String indexsp2dsah (final HttpServletResponse response, final HttpServletRequest request) throws IOException {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        boolean status = false;
        final String ipaddr = pengguna.getIpAddress();
        if (ipaddr != null && StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotBlank(ipaddr)) {
          //  status = new IpAddressMatcher(ipaddr).matches(request) || new IpAddressMatcher("127.0.0.1").matches(request);
            status = Objects.equals(ipaddr, SipkdHelpers.getIpAddr(request)) || new IpAddressMatcher("127.0.0.1").matches(request)  || new IpAddressMatcher("0:0:0:0:0:0:0:1").matches(request);
        }
        log.debug(ipaddr + " status = " + status + " request " +  SipkdHelpers.getIpAddr(request));
        if (status) {
            return "spj/pengesahan/index";
        } else {
            // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Anda tidak berhak mengakses halaman pengesahan");
            return "deny";
        }

    }*/
    
    
     @RequestMapping(value = "/json/getlistspjsah", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspmcetak(final HttpServletRequest request) {

        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("namaskpd");
        final String idskpd = request.getParameter("idskpd");
        final String kproses = request.getParameter("kproses");
        final String level = request.getParameter("levelSkpd");
        final String kskpd = request.getParameter("kodeskpd");
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
        final int banyak = cetakValidasiSPJServices.getbanyakspjsah(param) ;
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", cetakValidasiSPJServices.getlistspjsah(param) );
        return mapData;
    }
    
    
    @RequestMapping(value = "/json/updatespjsah", method = RequestMethod.POST)
    public @ResponseBody
    String submitcetak(@RequestBody List<Map<String, Object>> mapdata, final HttpServletRequest request) {  
             final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            
         for (Map<String, Object> mapdetil : mapdata) {
            String nju = (mapdetil.get("nju")).toString(); 
            mapdetil.put("userid", pengguna.getIdPengguna());
            mapdetil.put("nju", nju);
            mapdetil.put("tglentry", new Timestamp(System.currentTimeMillis()));
                      
            cetakValidasiSPJServices.insertspjsah( mapdetil);
        }
      
        return "Pengesahan SPJ sukses";
     
    }
    
    
    
     @RequestMapping(value = "/printvalidasi/cetakpengesahan/{nospd}", method = RequestMethod.GET)
    public String cetakpengesahan(final HttpServletRequest request, @PathVariable String nospd, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        request.setAttribute("dataspj", cetakValidasiSPJServices.getspjsahbyidspj(SipkdHelpers.getIntFromString(nospd)));
        request.setAttribute("nospd", nospd);
        return "spj/pengesahan/cetakvalidasi";

    }

    @RequestMapping(value = "/print/cetakbarcodepengesahanbynospj/{nospd}", method = RequestMethod.GET)
    public void cetakpengesahan(final HttpServletResponse response, @PathVariable String nospd) throws WriterException, IOException {
        BitMatrix bitMatrix = new Code128Writer().encode(nospd, BarcodeFormat.CODE_128, 90, 40, null);
        MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());

    }
    
    
    @RequestMapping(value = "/{idSpj}/{nospj}/{bulan}/{iskpd}/{namafile}", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer idSpj,@PathVariable String nospj,@PathVariable String bulan,@PathVariable String iskpd,@PathVariable String namafile) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        try {
            //final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");
           // final String level = cetakValidasiServices.getlevel();
            System.out.println("pathReport " + pathReport);
            map.put("SUBREPORT_DIR", pathReport);
            map.put("value", idSpj);
            map.put("bl", bulan);
            map.put("ns", nospj);
            map.put("is", iskpd);
            map.put("nf", namafile);
            map.put("np", cetakValidasiSPJServices.getnilaiparam(map));
            final Map<String, Object> mapData = new HashMap<String, Object>(4);
            //final int byk = cetakValidasiSPJServices.getbanyaksppcetakbtl3(map);
            //mapData.put("banyak3", byk);
            
                        map.put("NOMOR_SPJ",nospj);
                        map.put("ID_SKPD", iskpd);
                        map.put("BULAN", bulan);
                        map.put("TAHUN",tahunAnggaran);
                        List<Map> listhasil = cetakValidasiSPJServices.getnilaiparam(map);
                        map.put("NAMA_DAERAH",listhasil.get(0).get("N_DAERAH_JUDUL"));
                        map.put("NAMA_DAERAH_LOW",listhasil.get(0).get("N_DAERAH"));
                        map.put("NO_PERDA",listhasil.get(0).get("I_PERDA_NO"));
                        map.put("THN_PERDA",listhasil.get(0).get("C_PERDA_TAHUN"));
                        map.put("TGL_PERDA",listhasil.get(0).get("C_PERDA_TGL"));
                        map.put("NAMA_KOTA",listhasil.get(0).get("N_KOTA"));
                        map.put("PERATURAN_1",listhasil.get(0).get("E_PERATURAN_SPD1"));
                        map.put("PERATURAN_2",listhasil.get(0).get("E_PERATURAN_SPD2"));
                        map.put("PERATURAN_3",listhasil.get(0).get("E_PERATURAN_SPD3"));
                        map.put("PERATURAN_4",listhasil.get(0).get("E_PERATURAN_SPD4"));
                        map.put("PERATURAN_5",listhasil.get(0).get("E_PERATURAN_SPD5"));
                        //JasperPrint jasperPrint = JasperFillManager.fillReport(servletContext.getRealPath("WEB-INF/report/Report_SPP-UPGU.jasper"), map, jdbcConnection);
                        map.put("pathreport", pathReport + "/Report_SPJ_BelanjaLangsung.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                        //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_SPJ_BelanjaLangsung.jasper", map, jdbcConnection);
                        final String nospdC = nospj;
                        final String filename = "SPP_LS_BL"+"."+tahunAnggaran+"."+nospdC+".pdf";
                        response.setHeader("Content-Disposition",filename);
                         ServletOutputStream output = response.getOutputStream();
                         JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                         output.close();
                  
                      
       
            
     
    } catch (Exception e) {

            e.printStackTrace();

        }

    }
    
    
}
