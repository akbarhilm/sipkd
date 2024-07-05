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
@RequestMapping("/cetakssppph22")
public class CetakSSPPPh22Action {

    private static final Logger log = LoggerFactory.getLogger(CetakSSPPPh22Action.class);
    @Autowired
    CetakValidasiSP2DServices cetakValidasiSP2DServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/indexpph22", method = RequestMethod.GET)
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
          final String kw = cetakValidasiSP2DServices.getnamaWilayah(kproses) ;
         // final String tg =  new Timestamp(System.currentTimeMillis());
          request.setAttribute("namwil", kw);
          //request.setAttribute("tgl",  new Timestamp(System.currentTimeMillis()));
          
          
          
          
        return "cetak/ssp/ssppph22";

    }

     
    
   @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
   public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String kodewilayah = request.getParameter("kproses");
            final String tanggal =  request.getParameter("tgl");
            final String tgl = SipkdHelpers.getStringDateFormatFromString(tanggal, "yyyyMMdd", "dd/MM/yyyy");
            final String nosp2d =  request.getParameter("nsp2d");
            final String namwil = request.getParameter("namwil");
            final String idskpd = request.getParameter("idskpd");
        try {
            final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");
            final String data = request.getParameter("data");
            //log.debug(data);SALDO_AWAL
            
             log.debug("KODE WIL= "+kodewilayah);
            log.debug("NO SP2D= "+nosp2d);
            log.debug("IDSKPD= "+idskpd);
            log.debug("TAHUN= "+tahunAnggaran);
            
            map.put("SUBREPORT_DIR", pathReport);
            map.put("thn", tahunAnggaran);
            map.put("kw", kodewilayah);
            map.put("tgl", tgl);
            map.put("nf", namwil);
            map.put("np", cetakValidasiSP2DServices.getnilaiparam(map));
           
              
                        map.put("NOMOR_SP2D", nosp2d);
                        map.put("ID_SKPD", idskpd);
                        map.put("TAHUN", tahunAnggaran);
                        map.put("WIL_PROSES", kodewilayah);
                        List<Map> listhasil = cetakValidasiSP2DServices.getnilaiparam(map);
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
                        JasperPrint jasperPrint = JasperFillManager.fillReport( pathReport+"/Report_CetakSSP_PPH22.jasper", map, jdbcConnection);
                        final String filename = tahunAnggaran+"-"+"SSP-PPh22"+"-"+kodewilayah+"-"+nosp2d+".pdf";
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
