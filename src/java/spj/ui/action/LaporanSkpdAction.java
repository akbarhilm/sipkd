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
import spj.services.LaporanSkpdServices;
import spp.model.Pengguna;
import spp.model.CetakSkpd;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SipkdHelpers;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import spj.services.CetakReportServices;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/laporanskpd")
public class LaporanSkpdAction {

    private static final Logger log = LoggerFactory.getLogger(LaporanSkpdAction.class);
    @Autowired
    CetakReportServices cetakReportServices;
    
    @Autowired
    LaporanSkpdServices cetakService;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;

    
    @RequestMapping(value = "/indexlaporan", method = RequestMethod.GET)
    public ModelAndView add(final CetakSkpd cetak, final HttpServletRequest request, Model model) {
        
        return new ModelAndView("laporanskpd/laporanskpd", "refcetak", cetak);
    }
    
    @RequestMapping(value = "/indexlaporan11", method = RequestMethod.GET)
    public ModelAndView add11(final CetakSkpd cetak, final HttpServletRequest request, Model model) {
        return new ModelAndView("laporanskpd11/laporanskpd11", "refcetak", cetak);
    }
    
    @RequestMapping(value = "/listskpd", method = RequestMethod.GET)
    public String indexlist(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "cetak/listskpd";
    }
    
    @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        //final String kodewilayah = request.getParameter("kproses");
        final String idskpd = request.getParameter("idskpd");
        final String tglpost = request.getParameter("tglpost");
        final String idbas = request.getParameter("idbas");
        final String jenislaporan = request.getParameter("jenislaporan");
        final String nojur = request.getParameter("nojur");
            
        try {
            //final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");
            
            map.put("SUBREPORT_DIR", pathReport);
            map.put("TAHUN",tahunAnggaran);
            //map.put("thn", tahunAnggaran);
           
            map.put("TANGGAL",tglpost);
            map.put("IDSKPD",idskpd);
            map.put("IDBAS",idbas);
            map.put("ID_SKPD",idskpd);
            
            map.put("NO_JURNAL",nojur);
            
            List<Map> listhasil = cetakService.getnilaiparam(map);
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
            
            log.debug("jenis laporan === "+jenislaporan);
           
            if("1".equals(jenislaporan)){
                map.put("pathreport", pathReport + "/Report_JurnalSkpd_Nojur.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_JurnalSkpd_Nojur.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"JURNAL-UMUM-SKPD"+"-"+nojur+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
                
            } else if("2".equals(jenislaporan)){
                if (idbas == ""){
                    map.put("pathreport", pathReport + "/Report_JurnalSkpd_Idskpd.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                    //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_JurnalSkpd_Idskpd.jasper", map, jdbcConnection);
                    final String filename = tahunAnggaran+"-"+"JURNAL-UMUM-SKPD"+"-"+idskpd+"-"+tglpost+".pdf";
                    response.setHeader("Content-disposition", "attachment; filename="+ filename);
                    response.setContentType("application/pdf");
                    ServletOutputStream output = response.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                    output.close();

               } else {
                    map.put("pathreport", pathReport + "/Report_JurnalSkpd_Idbas.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                    //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_JurnalSkpd_Idbas.jasper", map, jdbcConnection);
                    final String filename = tahunAnggaran+"-"+"JURNAL-UMUM-SKPD"+"-"+idskpd+"-"+idbas+"-"+tglpost+".pdf";
                    response.setHeader("Content-disposition", "attachment; filename="+ filename);
                    response.setContentType("application/pdf");
                    ServletOutputStream output = response.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                    output.close();
               }
            } else if("3".equals(jenislaporan)){
                map.put("pathreport", pathReport + "/Report_TMJurnalSkpd.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_TMJurnalSkpd.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"JURNAL-UMUM-SKPD"+"-"+nojur+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
                
            } else if("4".equals(jenislaporan)){
                map.put("pathreport", pathReport + "/Report_LRA_Provinsi.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_LRA_Provinsi.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"LRA-Permendagri-13"+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
                
            } else if("5".equals(jenislaporan)){
                log.debug("MASUK jenis laporan 5, map === "+map);
                map.put("pathreport", pathReport + "/Report_LRA-PP_Provinsi.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_LRA-PP_Provinsi.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"LRA-PP-71"+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
            }

        } catch (Exception e) {
            //e.printStackTrace();
            e.getMessage().toString();
        }
    }
}
