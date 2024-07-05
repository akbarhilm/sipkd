package sp2d.ui.action;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.Sp2d;
import spp.model.SppUp;
import sp2d.services.CetakValidasiSP2DServices;
import sp2d.util.SipkdHelpers;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/cetaksp2dpotbtlblbulanan")
public class CetakSp2dPotBtlBlBulananAction {

    private static final Logger log = LoggerFactory.getLogger(CetakSp2dPotBtlBlBulananAction.class);
    @Autowired
    CetakValidasiSP2DServices cetakValidasiSP2DServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/indexpotongan", method = RequestMethod.GET)
    public String indexspmup(HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        final String kproses = pengguna.getKodeProses();
        final String kw = cetakValidasiSP2DServices.getnamaWilayah(kproses);
        // final String tg =  new Timestamp(System.currentTimeMillis());
        request.setAttribute("namwil", kw);
          //request.setAttribute("tgl",  new Timestamp(System.currentTimeMillis()));

        return "cetak/sp2d/potonganbtlblbulanan";

    }

    @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String kodewilayah = request.getParameter("kproses");
        final String bulan = request.getParameter("tgl");

        //final String bulan = SipkdHelpers.substringNullSafe(tanggal, 3, 5);
        final String namwil = request.getParameter("namwil");
        try {
            final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");
            final String data = request.getParameter("data");
            //log.debug(data);

            map.put("SUBREPORT_DIR", pathReport);
            map.put("thn", tahunAnggaran);
            map.put("kw", kodewilayah);
            map.put("tgl", bulan);
            map.put("nf", namwil);
            map.put("np", cetakValidasiSP2DServices.getnilaiparam(map));

            map.put("WILAYAH", kodewilayah);
            map.put("BULAN", bulan);
            map.put("TAHUN", tahunAnggaran);
            List<Map> listhasil = cetakValidasiSP2DServices.getnilaiparam(map);
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
            //JasperPrint jasperPrint = JasperFillManager.fillReport(servletContext.getRealPath("WEB-INF/report/Report_SPP-UPGU.jasper"), map, jdbcConnection);
            JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_DaftarPotongan-Bulanan.jasper", map, jdbcConnection);
            final String filename = tahunAnggaran + "-" + "SP2D-DAFTAR-POT-BULANAN-BL-BTL" + "-" + kodewilayah + "-" + bulan + ".pdf";
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            response.setContentType("application/pdf");
            ServletOutputStream output = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            output.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
