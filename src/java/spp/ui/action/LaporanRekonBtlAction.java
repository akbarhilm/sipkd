package spp.ui.action;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spp.model.SppBtlGaji;
import spp.services.CetakReportServices;
import spp.services.CetakValidasiSPPServices;
import spp.services.SppBtlGajiServices;
import spp.util.BigDecimalPropertyEditor;
import spp.util.SqlDatePropertyEditor;
import spp.util.SipkdHelpers;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/monitoringbtl")
public class LaporanRekonBtlAction {

    private static final Logger log = LoggerFactory.getLogger(LaporanRekonBtlAction.class);
    
    @Autowired
    CetakValidasiSPPServices cetakService;
    
    @Autowired
    SppBtlGajiServices sppBtlGajiServices;
    
    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;
    
    @Autowired
    CetakReportServices cetakReportServices;

    @RequestMapping(value = "/indexmonbtl", method = RequestMethod.GET)
    public ModelAndView indexmonbtl(final SppBtlGaji sppBtlGaji, final HttpServletRequest request, Model model) {

        return new ModelAndView("spp/monitoring/indexmonbtl", "refcetak", sppBtlGaji);

    }
    
    @RequestMapping(value = "/json/getBulan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getBulan(final HttpServletRequest request) {
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", sppBtlGajiServices.getBulan(param));

        return mapData;
    }

    @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String idskpd = request.getParameter("idskpd");
        //final String jenislaporan = request.getParameter("jenislaporan");
        final String bulan = request.getParameter("bulan");
        //final String kodegabung = request.getParameter("kodegabung");

        //final String bulan = request.getParameter("bulan");
        //final String saldo = request.getParameter("saldo");
        //final BigDecimal saldo = SipkdHelpers.getBigDecimalFromString(request.getParameter("saldo")); //request.getParameter("saldo");
        //final String akun = request.getParameter("akun");
        //final String wilayah = request.getParameter("wilayah");

        try {
            //final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>(4);
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");

            map.put("SUBREPORT_DIR", pathReport);
            map.put("TAHUN", tahunAnggaran);
            map.put("IDSKPD", SipkdHelpers.getBigDecimalFromString(idskpd));
            //map.put("ID_SKPD", idskpd);
            //map.put("TGL", tgl);
            map.put("BULAN", bulan);
            //map.put("KODEPAJAK",jenislaporan);
            //map.put("SALDOAWAL",saldo);
            //map.put("KODEAKUN",akun);

            /*
            List<Map> listhasil = cetakService.getnilaiparam(map);
            map.put("NAMA_DAERAH_JUDUL", listhasil.get(0).get("N_DAERAH_JUDUL"));
            map.put("NAMA_DAERAH", listhasil.get(0).get("N_DAERAH_JUDUL"));
            map.put("NAMA_DAERAH_LOW", listhasil.get(0).get("N_DAERAH"));
            map.put("NO_PERDA", listhasil.get(0).get("I_PERDA_NO"));
            map.put("THN_PERDA", listhasil.get(0).get("C_PERDA_TAHUN"));
            map.put("TGL_PERDA", listhasil.get(0).get("C_PERDA_TGL"));
            map.put("NAMA_KOTA", listhasil.get(0).get("N_KOTA"));
            map.put("PERATURAN_1", listhasil.get(0).get("E_PERATURAN_SPD1"));
            map.put("PERATURAN_2", listhasil.get(0).get("E_PERATURAN_SPD2"));
            map.put("PERATURAN_3", listhasil.get(0).get("E_PERATURAN_SPD3"));
            map.put("PERATURAN_4", listhasil.get(0).get("E_PERATURAN_SPD4"));
            map.put("PERATURAN_5", listhasil.get(0).get("E_PERATURAN_SPD5"));
            */
            //JasperPrint jasperPrint = null;
            String filename = "";
            /*
            if ("1".equals(jenislaporan)) {
                jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_RealisasiBelanja_Provinsi.jasper", map, jdbcConnection);
                filename = tahunAnggaran + "-" + "Laporan_Realisasi_Belanja_Provinsi" + ".pdf";
            } else if ("2".equals(jenislaporan)) {
                if ("1".equals(kodegabung)) {
                    jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_RealisasiBelanja_SKPD-Induk.jasper", map, jdbcConnection);
                    filename = tahunAnggaran + "-" + "Laporan_Realisasi_Belanja_SKPD_Gabungan" + ".pdf";
                } else {
                    jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_RealisasiBelanja_SKPD.jasper", map, jdbcConnection);
                    filename = tahunAnggaran + "-" + "Laporan_Realisasi_Belanja_SKPD" + ".pdf";
                }
            }
            */
            map.put("pathreport", pathReport + "/Report_Rekon_RekapPenghasilanSKPD.jasper");
                            JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
            //jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_Rekon_RekapPenghasilanSKPD.jasper", map, jdbcConnection);
            filename = tahunAnggaran + "-" + "Laporan_Rekon_SPP-BTL_SIMPEG" + ".pdf";
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            response.setContentType("application/pdf");
            ServletOutputStream output = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            output.close();

        } catch (Exception e) {
            //e.printStackTrace();
            e.getMessage();
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
