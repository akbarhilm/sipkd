package sipkd.ui.action;

import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.Valid;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sipkd.services.ReferensiServices;
import sipkd.services.SpdRevService;
import sipkd.model.Pengguna;
import sipkd.model.SpdBTLMaster;
import sipkd.util.SipkdHelpers;


/**
 *
 * @author sapto
 */
@Controller
@RequestMapping("/spd/monitoringlaporan")
public class SpdMonitoringLaporanAction {

    private static final Logger log = LoggerFactory.getLogger(SpdMonitoringLaporanAction.class);
    @Autowired
    SpdRevService spdRevService;

    @Autowired
    ReferensiServices referensiServices;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;

  
    
    @RequestMapping(value = "/indexml", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
              
        return "spd/pengajuanspd/indexml";

    }
    
    
  /*   @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpanspdbtl(@Valid @ModelAttribute("spdBTLMaster") SpdBTLMaster spdBTLMaster,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        spdBTLMaster.setTahunAnggaran((String) request.getSession().getAttribute("tahunAnggaran"));
        log.debug(" result " + result.getFieldError());
        final StringBuilder sburl = new StringBuilder("redirect:/spd/pengajuanbl/bl/edit/");
        if (result.hasErrors()) {
            return "spd/pengajuanspd/addpengajuanspdbantuanlangsung";
        } else {
            spdBTLMaster.setTglSpd(new Date(System.currentTimeMillis()));
            spdBTLMaster.setBulanAwal(SipkdHelpers.splitString(spdBTLMaster.getBulanAwal(), "/", 0));
            spdBTLMaster.setBulanAkhir(SipkdHelpers.splitString(spdBTLMaster.getBulanAkhir(), "/", 0));
            if (spdBTLMaster.getIdSpd() != null && spdBTLMaster.getIdSpd().longValue() > 0) {
                spdBLServices.updatespdmaster(spdBTLMaster);
                sburl.append(spdBTLMaster.getIdSpd());
            } else {
                spdBLServices.insertspdmaster(spdBTLMaster);
                sburl.append(spdBTLMaster.getIdSpd()).append("?isadd=1");
            }
            log.info(" ---> " + result.getFieldError());
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil ditambahkan ")
                    .toString());

            return sburl.toString();
        }
    }
*/  
    
    
    @RequestMapping(value = "/json/getlistmonlap", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistmonlap(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String id = request.getParameter("id");
        log.debug("id  = " + id);
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String tanggal1 =  request.getParameter("tgl1");
        final String tanggal2 =  request.getParameter("tgl2");
        final String tgl1 = SipkdHelpers.getStringDateFormatFromString(tanggal1, "yyyyMMdd", "MM/dd/yyyy");
        final String tgl2 = SipkdHelpers.getStringDateFormatFromString(tanggal2, "yyyyMMdd", "MM/dd/yyyy");
         log.debug("^^^^^^^^ = "+tgl1);
         
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", id);
        param.put("tgl1", tgl1);
        param.put("tgl2", tgl2);
        log.debug("Isi Param = "+param);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = spdRevService.getbanyakmonlap(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spdRevService.getlistmonlap(param));
        return mapData;

    }
        
    
  @RequestMapping(value = "/prosescetaklaporan", method = RequestMethod.GET)
   public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String kodewilayah = request.getParameter("kproses");
            final String idskpd =  request.getParameter("idskpd");
            final String namwil = request.getParameter("namwil");
            final String tanggal1 =  request.getParameter("tgl1");
            final String tanggal2 =  request.getParameter("tgl2");
            final String tgl1 = SipkdHelpers.getStringDateFormatFromString(tanggal1, "yyyyMMdd", "MM/dd/yyyy");
            final String tgl2 = SipkdHelpers.getStringDateFormatFromString(tanggal2, "yyyyMMdd", "MM/dd/yyyy");
        try {
            final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT")+"/";
            final String data = request.getParameter("data");
           
            map.put("SUBREPORT_DIR", pathReport);
            map.put("thn", tahunAnggaran);
            map.put("kw", kodewilayah);
            map.put("idskpd", idskpd);
            map.put("np", spdRevService.getnilaiparam(map));
           
              
                        map.put("ID_SKPD",idskpd);
                        map.put("TAHUN",tahunAnggaran);
                        map.put("TGL_AWAL",tgl1);
                        map.put("TGL_AKHIR",tgl2);
                        List<Map> listhasil = spdRevService.getnilaiparam(map);
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
                        JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_Realisasi-Pengeluaran.jasper", map, jdbcConnection);
                        log.debug("&&&&&&&&&&&& = "+jasperPrint);
                        final String filename = tahunAnggaran+"-"+"SPD-LAPORAN-REALISASI-ANGGARAN"+".pdf";
                        response.setHeader("Content-disposition", "attachment; filename="+ filename);
                        response.setContentType("application/pdf");
                        ServletOutputStream output = response.getOutputStream();
                        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                        output.close();
                 

        } catch (Exception e) {

            e.printStackTrace();

        }

      
        
    }
   
   
    @RequestMapping(value = "/json/getmonlapass", method = RequestMethod.GET)
   public void processRequest1(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String kodewilayah = request.getParameter("kproses");
            final String idskpd =  request.getParameter("idskpd");
            final String namwil = request.getParameter("namwil");
            final String tanggal1 =  request.getParameter("tgl1");
            final String tanggal2 =  request.getParameter("tgl2");
            final String ja =  request.getParameter("options");
            final String tgl1 = SipkdHelpers.getStringDateFormatFromString(tanggal1, "yyyyMMdd", "MM/dd/yyyy");
            final String tgl2 = SipkdHelpers.getStringDateFormatFromString(tanggal2, "yyyyMMdd", "MM/dd/yyyy");
            log.debug("******* = "+ja);
           
        try {
            final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");
            final String data = request.getParameter("data");
           
            map.put("SUBREPORT_DIR", pathReport);
            map.put("thn", tahunAnggaran);
            map.put("kw", kodewilayah);
            map.put("idskpd", idskpd);
            map.put("np", spdRevService.getnilaiparam(map));
           
              
                        map.put("ID_SKPD",idskpd);
                        map.put("TAHUN",tahunAnggaran);
                        map.put("JENIS_APBD",ja);
                        map.put("TGL_INPUT",tgl2);
                        log.debug("+++++++ = "+ja);
                        List<Map> listhasil = spdRevService.getnilaiparam(map);
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
                        JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_RealisasiAnggaran-BL.jasper", map, jdbcConnection);
                         log.debug("++++++++ = "+jasperPrint);
                         
                        final String filename = tahunAnggaran+"-"+"SPD-LAPORAN-REALISASI-ASISTEN"+".pdf";
                        response.setHeader("Content-disposition", "attachment; filename="+ filename);
                        response.setContentType("application/pdf");
                        ServletOutputStream output = response.getOutputStream();
                        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                        output.close();
                 

        } catch (Exception e) {

            e.printStackTrace();

        }

      
        
    }

}
