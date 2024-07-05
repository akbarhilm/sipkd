package sp2d.ui.action;

//import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.sql.DataSource;
import javax.validation.Valid;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.catalina.util.Base64;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.SpmTerima;
import sp2d.services.SpmTerimaServices;
import sp2d.util.BigDecimalPropertyEditor;
import sp2d.util.SqlDatePropertyEditor;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sp2d.config.DefaultTrustManager;
import sp2d.services.CetakValidasiSP2DServices;
import sp2d.util.SipkdHelpers;

/**
 *
 * @author Anita
 */
@Controller
@RequestMapping("/spmterima")
public class SpmTerimaAction {

    private static final Logger log = LoggerFactory.getLogger(SpmTerimaAction.class);
    @Autowired
    SpmTerimaServices spmTerimaServices;

    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;

    @Autowired
    CetakValidasiSP2DServices cetakValidasiSP2DServices;

    private final RestTemplate rest;
    private final HttpHeaders headers;
    private HttpStatus status;
    //private static final String resourceUrl = "http://localhost:8081/TERIMA/halamanterimadata/json/terimapostdata";
    //private static final String resourceUrl = "http://10.100.111.152:9876/getResponseEbku"; // development
    private static final String resourceUrl = "http://10.100.111.153:9876/getResponseEbku"; // produksi

    public SpmTerimaAction() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    @RequestMapping(value = "/json/kirimpostdata", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, Object> hapusspmcetak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        // System.out.println(" ------------> " + mapdata.toString());
        log.debug("KIRIM POST DATA -- mapdata.toString() ------------> " + mapdata.toString());
        HttpEntity<Map<String, Object>> requestPostData = new HttpEntity<Map<String, Object>>(mapdata);
        ResponseEntity<Map> response = rest.exchange(resourceUrl, HttpMethod.POST, requestPostData, Map.class);

        log.debug("KIRIM POST DATA -- response.getBody() ------------> " + response.getBody());

        return response.getBody();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "/sp2d/terimaspm/listterimaspm";
    }

    @RequestMapping(value = "/json/getlistspmterima", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listspmterima(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(11);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String kodewilayah = pengguna.getKodeProses();

        final String tahun = request.getParameter("tahun");
        param.put("tahun", tahun);

        //final String codeWilSp2dproses = request.getParameter("codeWilSp2dproses");
        param.put("codeWilSp2dproses", kodewilayah);

        final String nospmfilter = request.getParameter("nospmfilter");
        final String kodeskpdfilter = request.getParameter("kodeskpdfilter");
        final String namaskpdfilter = request.getParameter("namaskpdfilter");

        param.put("nospmfilter", nospmfilter);
        param.put("kodeskpdfilter", kodeskpdfilter);
        param.put("namaskpdfilter", namaskpdfilter);

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(11);
        final int banyak = spmTerimaServices.getCountSpmTerima(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spmTerimaServices.getSpmTerima(param));
        return mapData;
    }

    @RequestMapping(value = "/insertspmterima/{id}", method = RequestMethod.GET)
    public ModelAndView insertspmterima(@PathVariable Integer id, final HttpServletRequest request) {
        SpmTerima spmTerima = spmTerimaServices.getSpmTerimaById(id);

        String jenis = spmTerima.getCodeJenis();
        String beban = spmTerima.getCodeBeban();

        final Map<String, Object> param = new HashMap<String, Object>();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        param.put("idspm", id);
        param.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));

        final SpmTerima spm = spmTerimaServices.getKodeBank(param);

        if ("BL".equals(jenis) && "LS".equals(beban)) {
            final SpmTerima kodeva = spmTerimaServices.getKodeVA(param);
            spmTerima.setKodeVA(kodeva.getKodeVA());
            spmTerima.setNoNpwp(kodeva.getNoNpwp());
            spmTerima.setNamaNpwp(kodeva.getNamaNpwp());
            spmTerima.setAlamatNpwp(kodeva.getAlamatNpwp());
            spmTerima.setKotaNpwp(kodeva.getKotaNpwp());
            spmTerima.setNamaTujuan(kodeva.getNamaRekananBank());
            spmTerima.setKodePkp(kodeva.getKodePkp());

        } else {
            spmTerima.setKodeVA("0");
            spmTerima.setNoNpwp("");
            spmTerima.setNamaNpwp("");
            spmTerima.setAlamatNpwp("");
            spmTerima.setKotaNpwp("");
            spmTerima.setNamaTujuan(spm.getNamaTujuan());
        }

        spmTerima.setKodeBank(spm.getKodeBank());
        spmTerima.setNamaBank(spm.getNamaBank());
        spmTerima.setNoRekening(spm.getNoRekening());
        spmTerima.setNamaRekanan(spm.getNamaRekanan());
        spmTerima.setNpwpRekanan(spm.getNpwpRekanan());

        return new ModelAndView("/sp2d/terimaspm/insertspmterima", "progcmd1", spmTerima);
    }

    @RequestMapping(value = "/prosesinsert", method = RequestMethod.POST)
    public String prosesupdate(@Valid @ModelAttribute("progcmd1") SpmTerima spmTerima, BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        final Integer id = spmTerima.getIdSpmCetak();
        final StringBuilder sburl = new StringBuilder("redirect:/spmterima/insertspmterima/" + id);
        System.out.println(" result.hasErrors() = " + result.hasErrors());
        if (result.hasErrors()) {
            return "/sp2d/terimaspm/listterimaspm";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            //log.debug("spm terima anita " +spmTerima.getCodeWilSp2dproses());
            spmTerima.setIdEdit(pengguna.getIdPengguna());
            spmTerima.setTglEdit(new Timestamp(System.currentTimeMillis()));
            spmTerimaServices.updateSpmTerima(spmTerima);
        }
        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                .append(" berhasil disimpan ")
                .toString());
        return sburl.toString();
    }

    @RequestMapping(value = "/delspmterima/{id}", method = RequestMethod.GET)
    public ModelAndView delspmterima(@PathVariable Integer id) {
        final SpmTerima spmTerima = spmTerimaServices.getSpmTerimaById(id);
        return new ModelAndView("/sp2d/terimaspm/delspmtelahterima", "progcmd2", spmTerima);

    }

    @RequestMapping(value = "/prosesdelete", method = RequestMethod.POST)
    public String prosesdelete(@Valid @ModelAttribute("progcmd2") SpmTerima spmTerima, BindingResult result, final HttpServletRequest request) {
        final String idSpmCetak = request.getParameter("idSpmCetak");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        spmTerima.setIdEdit(pengguna.getIdPengguna());
        spmTerima.setTglEdit(new Timestamp(System.currentTimeMillis()));
        //log.debug("cek anita");
        spmTerimaServices.delSpmTerima(spmTerima);
        return "redirect:/spmterima";
    }

    @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String idSpmCetak = request.getParameter("idSpmCetak");
        final String tahun = request.getParameter("tahun");

        try {
            final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            //final String tahun = (String) request.getSession().getAttribute("tahun");

            List<Map> listhasil = cetakValidasiSP2DServices.getnilaiparam(map);

            final String pathReport = servletContext.getInitParameter("PATH_REPORT");

            map.put("SUBREPORT_DIR", pathReport);
            map.put("TAHUN", tahun); //parameter ke jasper
            map.put("IDSPM", idSpmCetak);
            map.put("NAMA_BPKD", listhasil.get(0).get("N_BPKD"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_TandaTerima_SPM.jasper", map, jdbcConnection);
            final String filename = tahun + idSpmCetak + "-" + "Report-Tanda-Terima-SPM" + ".pdf";
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

    @RequestMapping(value = "json/getSPMBelumSP2D", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSPMBelumSP2D(final HttpServletRequest request) {
        final Integer idSpmCetak = SipkdHelpers.getIntFromString(request.getParameter("idSpmCetak"));

        final Map<String, Object> mapData = new HashMap<String, Object>(1);
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("id", idSpmCetak);

        mapData.put("aData", spmTerimaServices.getIdSpm(param));

        return mapData;
    }

    @RequestMapping(value = "/json/inquirynpwp", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Object inquirynpwp(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        try {
            final String npwp = (String) mapdata.get("npwp");
            InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/txt/npwp.txt");
            String resourceUrl = SipkdHelpers.readFromInputStream(inputStream);
            SSLContext sslctx = SSLContext.getInstance("SSL");
            sslctx.init(null, new X509TrustManager[]{new DefaultTrustManager()
            }, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslctx.getSocketFactory()
            );

            String urlParameter = "?npwp=" + npwp;
            String url = (resourceUrl.split("\\|")[0] + urlParameter);
            String username = resourceUrl.split("\\|")[1];
            String password = resourceUrl.split("\\|")[2];
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes()));

            byte[] output;
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String c;
            StringBuilder sb = new StringBuilder();
            while ((c = reader.readLine()) != null) {
                sb.append(c);
            }
            Map<String, String> mapData = new HashMap<String, String>();
            Object returnObject;
            JSONObject jsonData = new JSONObject(sb.toString());
            if (jsonData.getJSONObject("RespHeader").getString("respCode").equals("1")) {
                Iterator<String> nameItr = jsonData.getJSONObject("RespBody").keys();
                while (nameItr.hasNext()) {
                    String name = nameItr.next();
                    if (name.equals("NPWP") || name.equals("NAMA") || name.equals("ALAMAT") || name.equals("KABKOT")) {
                        if (name.equals("ALAMAT") && jsonData.getJSONObject("RespBody").getString(name).length() > 75) {
                            mapData.put(name, jsonData.getJSONObject("RespBody").getString(name).substring(0, 75));
                        } else {
                            mapData.put(name, jsonData.getJSONObject("RespBody").getString(name));
                        }
                    }
                }
                returnObject = mapData;
            } else {
                mapData.put("error", jsonData.getJSONObject("RespHeader").getString("respDesc"));
                returnObject = mapData;
            }
            reader.close();
            con.disconnect();

            return returnObject;
        } catch (Exception ex) {
            log.debug("ERROR - " + ex.getMessage());
        }
        return "ERROR";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
