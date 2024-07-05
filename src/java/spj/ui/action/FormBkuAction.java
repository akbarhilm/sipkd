package spj.ui.action;

import java.math.BigDecimal;
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
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spj.services.FormBkuServices;
import spp.model.Pengguna;
import spp.model.FormBku;
import spp.model.Skpd;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SipkdHelpers;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spj.services.CetakReportServices;
import spj.util.SqlDatePropertyEditor;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/formbku")
public class FormBkuAction {

    private static final Logger log = LoggerFactory.getLogger(FormBkuAction.class);
    @Autowired
    CetakReportServices cetakReportServices;
    
    @Autowired
    FormBkuServices cetakService;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;
   
    @RequestMapping(value = "/indexlaporan", method = RequestMethod.GET)
    public ModelAndView add(final FormBku bku, final HttpServletRequest request, Model model) {
        
        return new ModelAndView("formbku/formbku", "refcetak", bku);

    }
    
    @RequestMapping(value = "/indexlaporan10", method = RequestMethod.GET)
    public ModelAndView indexlaporan10(final FormBku bku, final HttpServletRequest request, Model model) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }
        Integer idskpd = listSkpd.get(0).getIdSkpd();
        
        return new ModelAndView("formbku/formbku10", "refcetak", bku);

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
        final String idskpd = request.getParameter("idskpd");
        final String jenislaporan = request.getParameter("jenislaporan");
        final String bulan = request.getParameter("bulan");
        final BigDecimal saldo = SipkdHelpers.getBigDecimalFromString(request.getParameter("saldo")); //request.getParameter("saldo");
        final String akun = request.getParameter("akun");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        
        //final String wilayah = request.getParameter("wilayah");
        //final Integer idskpdInt = SipkdHelpers.getIntFromString(request.getParameter("idskpd"));
        //log.debug("SALDOOOOOOOOOOOOOO AWAAAAAAAAAAALLL ======== "+saldo);
        try {
            //final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");
            
            map.put("SUBREPORT_DIR", pathReport);
            map.put("TAHUN",tahunAnggaran);
            map.put("IDSKPD",idskpd);
            map.put("ID_SKPD",idskpd);
            map.put("BULAN",bulan);
            map.put("KODEPAJAK",jenislaporan);
            map.put("SALDOAWAL",saldo);
            map.put("KODEAKUN",akun);
            map.put("WILAYAH",pengguna.getKodeProses());
            map.put("KODE_WIL_SP2DPROSES",pengguna.getKodeProses());
                        
            log.debug("WILAYAAAAAAAAAHHHHHHHHH ======== "+pengguna.getKodeProses());
            
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
            
            if("7".equals(jenislaporan)){
                map.put("pathreport", pathReport + "/Report_BukuPanjar-SKPD.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_BukuPanjar-SKPD.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"BKU Laporan Bulanan Panjar"+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
                
            } else if("8".equals(jenislaporan)){
                map.put("pathreport", pathReport + "/Report_BukuKasBank-SKPD.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_BukuKasBank-SKPD.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"BKU Laporan Bulanan Kas Bank"+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
                
            } else if("9".equals(jenislaporan)){
                map.put("pathreport", pathReport + "/Report_BukuKasTunai-SKPD.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_BukuKasTunai-SKPD.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"BKU Laporan Bulanan Tunai"+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
                
            } else if("22".equals(jenislaporan)){
                JasperPrint jasperPrint;
                if (idskpd.equals("761") || idskpd.equals("1234")) {
                    map.put("pathreport", pathReport + "/Report_BKU-Pengeluaran_PPKD.jasper");
                         jasperPrint = cetakReportServices.cetakReport(map);
                    //jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_BKU-Pengeluaran_PPKD.jasper", map, jdbcConnection);
                } else {
                    map.put("pathreport", pathReport + "/Report_BKU-Pengeluaran.jasper");
                         jasperPrint = cetakReportServices.cetakReport(map);
                    //jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_BKU-Pengeluaran.jasper", map, jdbcConnection);
                }
                
                
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_BKU-Pengeluaran.jasper", map, jdbcConnection);
                final String filename = "Laporan BKU Pengeluaran"+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
                
            } else if("27".equals(jenislaporan)){
                map.put("pathreport", pathReport + "/Report_BukuObjekBelanja-SKPD.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_BukuObjekBelanja-SKPD.jasper", map, jdbcConnection);
                final String filename = "Buku Objek Belanja"+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
                
            } else if("28".equals(jenislaporan)){
                map.put("pathreport", pathReport + "/Report_RegisterPengeluaran-SKPD.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_RegisterPengeluaran-SKPD.jasper", map, jdbcConnection);
                final String filename = "Laporan Register Pengeluaran SDKP"+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
                
            } else if("PJK".equals(jenislaporan)) { // PAJAK (PJK)
                map.put("pathreport", pathReport + "/Report_Daftar_Realisasi_PP_Pajak.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_Daftar_Realisasi_PP_Pajak.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"BKU-Laporan-Realisasi-Bulanan-Pajak"+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
                
            } else if("SK1".equals(jenislaporan)) { // Saldo Kas 
                map.put("pathreport", pathReport + "/Report_Laporan_SaldoKas-SKPD.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_Laporan_SaldoKas-SKPD.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"Laporan Saldo Kas Beban Belanja"+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
                
            } else if("48".equals(jenislaporan)) { // SPJ Belanja 
                map.put("pathreport", pathReport + "/Report_SPJ-Belanja.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_SPJ-Belanja.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"BKU Laporan SPJ Belanja"+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
                
            } else if("49".equals(jenislaporan)) { // SPJ Belanja Administratif
                map.put("pathreport", pathReport + "/Report_SPJ-BelanjaAdministratif.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_SPJ-BelanjaAdministratif.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"BKU Laporan SPJ Belanja Administratif"+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
                
            } else if("50".equals(jenislaporan)) { // SPJ Belanja Fungsional
                //log.debug("Parameter ==> "+jenislaporan+" ==> "+bulan+" ==> "+idskpd+" ==> "+tahunAnggaran);
                map.put("pathreport", pathReport + "/Report_SPJ-BelanjaFungsional.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_SPJ-BelanjaFungsional.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"BKU Laporan SPJ Belanja Fungsional"+".pdf";
                //log.debug("Nama File nya ==> "+filename);
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
                
            } else { // PAJAK ; P1-P6
                map.put("pathreport", pathReport + "/Report_BukuPajak-SKPD.jasper");
                        JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
                //JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_BukuPajak-SKPD.jasper", map, jdbcConnection);
                final String filename = tahunAnggaran+"-"+"BKU Laporan Bulanan Pajak"+".pdf";
                response.setHeader("Content-disposition", "attachment; filename="+ filename);
                response.setContentType("application/pdf");
                ServletOutputStream output = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.close();
            }

        } catch (Exception e) {
            //e.printStackTrace();
            e.getMessage();
        }
    }

    @RequestMapping(value = "/json/getSaldoAwal", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSaldoAwal(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String bulan = request.getParameter("bulan");
        final String idskpd = request.getParameter("idskpd");
        final String jenis = request.getParameter("jenis");
        
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("bulan", bulan);
        param.put("idskpd", idskpd);
        param.put("jenis", jenis);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
	
        if ("7".equals(jenis)){ // Panjar
            mapData.put("aData", cetakService.getSaldoAwalPanjar(param));
        } else if ("8".equals(jenis)){ // Bank
            mapData.put("aData", cetakService.getSaldoAwalBank(param));
        } else if ("9".equals(jenis)){ // Tunai
            mapData.put("aData", cetakService.getSaldoAwalTunai(param));
        }else { // jenis pajak P1-P6
            mapData.put("aData", cetakService.getSaldoAwalPajak(param));
        }
        
        return mapData;
    }
    
    
    @RequestMapping(value = "/json/getAkunBelanja", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAkunBelanja(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", cetakService.getAkunBelanja(param));

        return mapData;
    }
    
    @RequestMapping(value = "/json/getWilayah", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getWilayah(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        
        mapData.put("aData", cetakService.getWilayah(param));
        
        return mapData;
    }
    
    @RequestMapping(value = "/json/setBulan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> tanggal(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String kodewilayah = pengguna.getKodeProses();
        
        final String idskpd = request.getParameter("idskpd");
        
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("wilayah", kodewilayah);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
	
	mapData.put("aData", cetakService.getBulan(param));
        return mapData;
    }


    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
    
}


