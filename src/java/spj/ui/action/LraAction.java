package spj.ui.action;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spj.services.LraSkpdServices;
import spp.model.Pengguna;
import spp.model.LRA;
import spj.util.SipkdHelpers;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spj.services.CetakReportServices;


/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/lra")
public class LraAction {

    private static final Logger log = LoggerFactory.getLogger(LraAction.class);
    @Autowired
    CetakReportServices cetakReportServices;
    
    @Autowired
    LraSkpdServices lraService;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;

    
    @RequestMapping(value = "/indexlraskpd", method = RequestMethod.GET)
    public ModelAndView indexneraca(final LRA lra, final HttpServletRequest request) {
        
        return new ModelAndView("lra/lraskpd", "refneraca", lra);
    }
    
    @RequestMapping(value = "/indexlraskpdall", method = RequestMethod.GET)
    public ModelAndView indexneracaall(final LRA lra, final HttpServletRequest request) {
        
        return new ModelAndView("lra/lraskpdall", "refneraca", lra);
    }
        
    @RequestMapping(value = "/json/prosessimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        
        final String idskpd = (String) mapdata.get("idskpd");
        final String bulan = (String) mapdata.get("bulan");
        final String perubahan = (String) mapdata.get("perubahan");
        
        final LRA lra = new LRA();
        
        if(idskpd == ""){
            lra.setIdEntry(pengguna.getIdPengguna());
            lra.setTahun(tahun);
            lra.setBulan(bulan);
            lra.setPerubahan(perubahan);

            lraService.insertLraProvinsi(lra);
            
        } else{
            lra.setIdEntry(pengguna.getIdPengguna());
            lra.setTahun(tahun);
            lra.setIdskpd(SipkdHelpers.getIntFromString(idskpd));
            lra.setBulan(bulan);
            lra.setPerubahan(perubahan);

            lraService.insertLraSkpd(lra);
        }
        
        
        return "Proses LRA Berhasil Disimpan";
    }
    
    @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String idskpd = request.getParameter("idskpd");
            
        try {
            //final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");
            
            map.put("SUBREPORT_DIR", pathReport);
            
            map.put("TAHUN",tahunAnggaran);
            map.put("ID_SKPD",idskpd);
            
            List<Map> listhasil = lraService.getnilaiparam(map);
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
            
            map.put("pathreport", pathReport + "/Report_JurnalBukuBesar-SKPD.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
            //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_JurnalBukuBesar-SKPD.jasper", map, jdbcConnection);
            final String filename = tahunAnggaran+"-"+"JURNAL-LRA-SKPD"+"-"+idskpd+".pdf";
            response.setHeader("Content-disposition", "attachment; filename="+ filename);
            response.setContentType("application/pdf");
            ServletOutputStream output = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            output.close();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping(value = "/json/getKodeStatus", method = RequestMethod.GET)
    public @ResponseBody
    String getkodeststus(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tanggal = request.getParameter("tanggal");
        //final String jenisLRA = request.getParameter("jenisLRA");
        //final String kode;
        
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("idskpd", idskpd);
        param.put("tanggal", tanggal);
        
        return lraService.getKodeStatus(param);
    }
    
    @RequestMapping(value = "/json/getKodeStatusProvinsi", method = RequestMethod.GET)
    public @ResponseBody
    String getkodeststusprovinsi(final HttpServletRequest request) {
        final String tanggal = request.getParameter("tanggal");
        
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("tanggal", tanggal);
        
       
        return lraService.getKodeStatusProvinsi(param);
    }
    
}