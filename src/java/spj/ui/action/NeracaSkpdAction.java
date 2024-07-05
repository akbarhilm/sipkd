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
import spj.services.NeracaSkpdServices;
import spp.model.Pengguna;
import spp.model.Neraca;
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
@RequestMapping("/neracaskpd")
public class NeracaSkpdAction {

    private static final Logger log = LoggerFactory.getLogger(NeracaSkpdAction.class);
    @Autowired
    CetakReportServices cetakReportServices;
    
    @Autowired
    NeracaSkpdServices neracaService;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;

    
    @RequestMapping(value = "/indexneracaskpd", method = RequestMethod.GET)
    public ModelAndView indexneraca(final Neraca neraca, final HttpServletRequest request) {
        
        return new ModelAndView("neraca/neracaskpd", "refneraca", neraca);
    }
    
    @RequestMapping(value = "/indexneracaskpdall", method = RequestMethod.GET)
    public ModelAndView indexneracaall(final Neraca neraca, final HttpServletRequest request) {
        
        return new ModelAndView("neraca/neracaskpdall", "refneraca", neraca);
    }
    
    @RequestMapping(value = "/cetakneracaskpdall", method = RequestMethod.GET)
    public ModelAndView cetakneracaall(final Neraca neraca, final HttpServletRequest request) {
        
        return new ModelAndView("neraca/cetakneracaskpdall", "refneraca", neraca);
    }
    
    @RequestMapping(value = "/cetakneracaskpd", method = RequestMethod.GET)
    public ModelAndView cetakneracaskpd(final Neraca neraca, final HttpServletRequest request) {
        
        return new ModelAndView("neraca/cetakneracaskpd", "refneraca", neraca);
    }
        
    @RequestMapping(value = "/json/prosessimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        
        final String idskpd = (String) mapdata.get("idskpd");
        final String kodeskpd = (String) mapdata.get("kodeskpd");
        final String namaskpd = (String) mapdata.get("namaskpd");
        final String bulan = (String) mapdata.get("bulan");
        /*
        final String postawal = (String) mapdata.get("postawal");
        final String postakhir = (String) mapdata.get("postakhir");
        */
        final Neraca neraca = new Neraca();
        
        neraca.setIdEntry(pengguna.getIdPengguna());
        neraca.setTahun(tahun);
        neraca.setIdskpd(SipkdHelpers.getIntFromString(idskpd));
        neraca.setNamaskpd(namaskpd);
        neraca.setKodeskpd(kodeskpd);
        neraca.setBulan(bulan);
        
        if (idskpd.equals("761") || idskpd.equals("1234")) {
            log.debug("============= MASUK 761 ==============");
            neracaService.insertNeracaPkpd(neraca);
        } else{
            neracaService.insertNeracaSkpd(neraca);
        }
        
        return "Proses Neraca SKPD Berhasil Disimpan";
    }
    
    @RequestMapping(value = "/json/setBulan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> tanggal(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
       
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        
        mapData.put("aData", neracaService.getBulan(param));
        
        return mapData;
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
            final String bulan = request.getParameter("bulan");
            
            map.put("SUBREPORT_DIR", pathReport);
            
            map.put("TAHUN",tahunAnggaran);
            map.put("ID_SKPD",idskpd);
            
            List<Map> listhasil = neracaService.getnilaiparam(map);
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
            final String filename = tahunAnggaran+"-"+"JURNAL-NERACA-SKPD"+"-"+idskpd+".pdf";
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
        
        return neracaService.getKodeStatus(param);
    }
    
    @RequestMapping(value = "/json/getKodeStatusProvinsi", method = RequestMethod.GET)
    public @ResponseBody
    String getkodeststusprovinsi(final HttpServletRequest request) {
        final String tanggal = request.getParameter("tanggal");
        
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("tanggal", tanggal);
        
       
        return neracaService.getKodeStatusProvinsi(param);
    }
    
}