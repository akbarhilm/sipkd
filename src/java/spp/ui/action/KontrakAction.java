package spp.ui.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spp.config.DefaultTrustManager;
import spp.model.Kontrak;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.services.CetakReportServices;
import spp.services.KontrakServices;
import spp.services.ReferensiServices;
import spp.services.RekananServices;
import spp.util.BigDecimalPropertyEditor;
import spp.util.SipkdHelpers;
import spp.util.SqlDatePropertyEditor;

@Controller
@RequestMapping("/kontrak")
public class KontrakAction {

    private static final Logger log = LoggerFactory.getLogger(KontrakAction.class);

    @Autowired
    RekananServices rekananServices;

    @Autowired
    KontrakServices kontrakServices;

    @Autowired
    ReferensiServices referensiServices;

    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;

    @Autowired
    CetakReportServices cetakReportServices;

    private final RestTemplate rest;
    private final HttpHeaders headers;
    private HttpStatus status;
    //private static final String resourceUrl = "http://localhost:8086/TERIMA/halamanterimadata/json/terimapostdata";
    private static final String resourceUrl = "http://10.100.111.152:9876/getResponseEbku"; // development
    //private static final String resourceUrl = "http://10.100.111.153:9876/getResponseEbku"; // produksi

    public KontrakAction() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    @RequestMapping(value = "/json/kirimpostdata", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, Object> hapusspmcetak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {

        HttpEntity<Map<String, Object>> requestPostData = new HttpEntity<Map<String, Object>>(mapdata);
        ResponseEntity<Map> response = rest.exchange(resourceUrl, HttpMethod.POST, requestPostData, Map.class);

        return response.getBody();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(final Kontrak kontrak, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        return new ModelAndView("/common/kontrak/listkontrak", "progcmd", kontrak);
    }

    @RequestMapping(value = "/{idskpd}", method = RequestMethod.GET)
    public String index(@PathVariable Integer idskpd, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        request.setAttribute("skpd", referensiServices.getDetailSkpdById(idskpd));
        return "/common/kontrak/listkontrak";
    }

    @RequestMapping(value = "/addkontrak", method = RequestMethod.GET)
    public ModelAndView add(final Kontrak kontrak, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            request.setAttribute("isall", 1);
            request.setAttribute("tahunAnggaran", tahunAnggaran);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            request.setAttribute("isall", 0);
            request.setAttribute("tahunAnggaran", tahunAnggaran);
            request.setAttribute("skpd", listSkpd.get(0));
        }

        return new ModelAndView("/common/kontrak/addkontrak", "progcmd", kontrak);
    }

    @RequestMapping(value = "/prosessimpankontrak", method = RequestMethod.POST)
    public String prosessimpankontrak(@Valid @ModelAttribute("progcmd") Kontrak kontrak,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final HttpServletRequest request) {
        /*if (result.hasErrors()) {
         return "/common/kontrak/addkontrak";
         } else {*/

        final BigDecimal totalkontrak = kontrak.getNilaiKontrakTotal();
        final StringBuilder sburl = new StringBuilder("redirect:/kontrak/updatekontrak/");

        if (totalkontrak == null) {
            kontrak.setNilaiKontrakTotal(kontrak.getNilaiSpd());
        }

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        kontrak.setIdEntry(pengguna.getIdPengguna());
        kontrak.setTglEntry(new Timestamp(System.currentTimeMillis()));

        if ("NON PKP".equals(kontrak.getKodePkp())) {
            kontrak.setKodePkp("0");
        } else {
            kontrak.setKodePkp("1");
        }
        kontrakServices.insertKontrak(kontrak);
        log.debug("kontrak.getIdKontrak() ************** " + kontrak.getIdKontrak());

        //}
        // return "redirect:/kontrak"; // kalo langsung balik ke panel index kontrak
        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data Kontrak")
                .append(" berhasil disimpan ")
                .toString());

        return sburl.append(kontrak.getIdKontrak()).toString();

    }

    @RequestMapping(value = "/json/getlistkontrak", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistkontrak(final HttpServletRequest request) throws ParseException {

        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("namaskpd");
        final String idskpd = request.getParameter("idskpd");
        final String kodeMetode = request.getParameter("kodemetode");
        final String rekanan = request.getParameter("rekanan");
        final String namakegiatan = request.getParameter("namakegiatan");
        /*DecimalFormatSymbols symbols = new DecimalFormatSymbols();
         symbols.setGroupingSeparator('.');
         symbols.setDecimalSeparator(',');
         String pattern = "#.##0,0#";
         DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
         decimalFormat.setParseBigDecimal(true);
         */
        //Parse String Now
        final BigDecimal nilai1 = SipkdHelpers.getBigDecimalFromString(request.getParameter("nilai1"));
        final BigDecimal nilai2 = SipkdHelpers.getBigDecimalFromString(request.getParameter("nilai2"));

        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date tglmulaii = null;
        Date tglakhirr = null;
        String tmptglmulai = request.getParameter("tglmulai");
        String tmptglakhir = request.getParameter("tglakhir");
        if (tmptglmulai != null && tmptglmulai.trim().length() > 0 && tmptglakhir != null && tmptglakhir.trim().length() > 0) {
            tglmulaii = new Date(sdf.parse(tmptglmulai).getTime());
            tglakhirr = new Date(sdf.parse(tmptglakhir).getTime());
        }
        // final java.util.Date tglmulai = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH).parse(tglmulaii);
        // final java.util.Date tglakhir = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH).parse(tglakhirr);

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
        param.put("idskpd", idskpd);
        param.put("namakegiatan", namakegiatan);
        param.put("kodemetode", kodeMetode);
        param.put("rekanan", rekanan);
        param.put("nilai1", nilai1);
        param.put("nilai2", nilai2);
        param.put("tglmulai", tglmulaii);
        param.put("tglakhir", tglakhirr);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = kontrakServices.getCountKontrak(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", kontrakServices.getKontrak(param));
        return mapData;
    }

    @RequestMapping(value = "/deletekontraks", method = RequestMethod.POST)
    public String prosesdelete(@Valid @ModelAttribute("progcmd") Kontrak kontrak, BindingResult result, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        kontrak.setIdEntry(pengguna.getIdPengguna());
        kontrak.setTglEntry(new Timestamp(System.currentTimeMillis()));
        kontrakServices.deleteKontrak(kontrak.getIdKontrak());

        return "redirect:/kontrak";
    }

    @RequestMapping(value = "/delkontrak/{id}", method = RequestMethod.GET)
    public ModelAndView deletekontrak(@PathVariable Integer id, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            request.setAttribute("isall", 1);
            request.setAttribute("tahunAnggaran", tahunAnggaran);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            request.setAttribute("isall", 0);
            request.setAttribute("tahunAnggaran", tahunAnggaran);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        Kontrak kontrak = kontrakServices.getKontrakById(id);
        return new ModelAndView("/common/kontrak/deletekontrak", "progcmd", kontrak);
    }

    @RequestMapping(value = "/updatekontraks", method = RequestMethod.POST)
    public String prosesupdate(@Valid @ModelAttribute("progcmd") Kontrak kontrak, BindingResult result, final HttpServletRequest request) {
        // System.out.println(" result.hasErrors() = " + result.hasErrors());
        if (result.hasErrors()) {
            return "/common/kontrak/updatekontrak";
        } else {

            final Integer kodeMulti = SipkdHelpers.getIntFromString(request.getParameter("kodeMultiyear"));
            log.debug(" total kontrak UPDATE KONTRAK ========== " + kodeMulti);
            if (kodeMulti == 0) {
                log.debug(" total kontrak null ========== ");
                kontrak.setNilaiKontrakTotal(kontrak.getNilaiSpd());
            }

            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            kontrak.setIdEntry(pengguna.getIdPengguna());
            kontrak.setTglEntry(new Timestamp(System.currentTimeMillis()));
            if ("NON PKP".equals(kontrak.getKodePkp())) {
                kontrak.setKodePkp("0");
            } else {
                kontrak.setKodePkp("1");
            }
            kontrakServices.updateKontrak(kontrak);
        }
        return "redirect:/kontrak";
    }

    @RequestMapping(value = "/updatekontrak/{id}", method = RequestMethod.GET)
    public ModelAndView updatekontrak(@PathVariable Integer id, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            request.setAttribute("isall", 1);
            request.setAttribute("tahunAnggaran", tahunAnggaran);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            request.setAttribute("isall", 0);
            request.setAttribute("tahunAnggaran", tahunAnggaran);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        Kontrak kontrak = kontrakServices.getKontrakById(id);
        request.setAttribute("nilaiSpd", SipkdHelpers.formatBigDecimalPropertyEditor(kontrak.getNilaiSpd()));

        return new ModelAndView("/common/kontrak/updatekontrak", "progcmd", kontrak);
    }

    @RequestMapping(value = "/updatekontrakketerangan/{id}", method = RequestMethod.GET)
    public ModelAndView updatekontrakketerangan(@PathVariable Integer id, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            request.setAttribute("isall", 1);
            request.setAttribute("tahunAnggaran", tahunAnggaran);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            request.setAttribute("isall", 0);
            request.setAttribute("tahunAnggaran", tahunAnggaran);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        final Kontrak kontrak = kontrakServices.getKontrakById(id);

        request.setAttribute("nilaiSpd", SipkdHelpers.formatBigDecimalPropertyEditor(kontrak.getNilaiSpd()));

        return new ModelAndView("/common/kontrak/updatekontrakketerangan", "progcmd", kontrak);
    }

    @RequestMapping(value = "/listrekananpopup", method = RequestMethod.GET)
    public ModelAndView listrekanan(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("/common/kontrak/listrekananpopup");
    }

    @RequestMapping(value = "/listmetodepopup", method = RequestMethod.GET)
    public ModelAndView listmetode(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("/common/kontrak/listmetodepopup");
    }

    @RequestMapping(value = "/listkegiatanpopup", method = RequestMethod.GET)
    public ModelAndView listkegiatan(final HttpServletResponse response, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("/common/kontrak/listkegiatanpopup");
    }

    @RequestMapping(value = "/json/listpopuprekanan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsonrekanan(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("rekanan");
        param.put("rekanan", skpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = rekananServices.getCountRekanan(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", rekananServices.getRekanan(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listpopupmetode", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsonmetode(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("namametode");
        param.put("namametode", skpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = kontrakServices.getCountMetode(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", kontrakServices.getMetode(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listpopupkegiatan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpopupkegiatan(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String tahunAnggaran = request.getParameter("tahun");
        final String namaskpd = request.getParameter("namaskpd");
        final String idskpd = request.getParameter("idskpd");
        final String idkontrak = request.getParameter("idkontrak");
        final String kodekeg = request.getParameter("kodekeg");
        final String namakeg = request.getParameter("namakeg");

        param.put("tahun", tahunAnggaran);
        param.put("namaskpd", namaskpd);
        param.put("idskpd", idskpd);
        param.put("idkontrak", idkontrak);
        param.put("kodekeg", kodekeg);
        param.put("namakeg", namakeg);

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = kontrakServices.getCountKegiatan(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", kontrakServices.getKegiatan(param));
        return mapData;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

    @RequestMapping(value = "/{idKontrak}/{iskpd}/{namafile}", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer idKontrak, @PathVariable String iskpd, @PathVariable String namafile) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        try {
            //final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");
            final String nokon = kontrakServices.getnoKontrak(idKontrak);
            final String idkon = (String) idKontrak.toString();
            map.put("SUBREPORT_DIR", pathReport);
            map.put("value", idKontrak);
            map.put("nf", namafile);
            log.debug("idkontrak : " + idKontrak + " " + iskpd + " " + tahunAnggaran + " " + pathReport);

            final Map<String, Object> mapData = new HashMap<String, Object>(4);
            map.put("ID_SKPD", iskpd);
            map.put("TAHUN", tahunAnggaran);
            map.put("NO_KONTRAK", idkon);

            map.put("pathreport", pathReport + "/Report_KontrakInduk.jasper");
            JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
            //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_KontrakInduk.jasper", map, jdbcConnection);
            final String filename = "SPP_LS_BL" + "." + tahunAnggaran + ".pdf";
            response.setHeader("Content-Disposition", filename);
            ServletOutputStream output = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            output.close();

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();

        }

    }

    @RequestMapping(value = "/json/validasisisakontrak ", method = RequestMethod.GET)
    public @ResponseBody
    String getvalidasi(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idspd = request.getParameter("idspd");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String idkontrak = request.getParameter("idkontrak");

        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("idskpd", idskpd);
        param.put("tahun", tahun);
        param.put("idspd", idspd);
        param.put("idkegiatan", idkegiatan);
        param.put("idkontrak", idkontrak);

        return kontrakServices.getSisaKontrak(param);

    }

    @RequestMapping(value = "/json/gettotalnilaikontrakperbast", method = RequestMethod.GET)
    public @ResponseBody
    BigDecimal getTotalNilaiKontrakPerBast(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idkontrak = request.getParameter("idkontrak");

        final Map< String, Integer> param = new HashMap<String, Integer>(3);
        param.put("idSkpd", SipkdHelpers.getIntFromString(idskpd));
        param.put("tahun", SipkdHelpers.getIntFromString(tahun));
        param.put("idKontrak", SipkdHelpers.getIntFromString(idkontrak));

        return kontrakServices.getTotalNilaiKontrakPerBast(param);

    }

    @RequestMapping(value = "/json/ceknokontrakeksis", method = RequestMethod.GET)
    public @ResponseBody
    Boolean ceknokontrakeksis(final HttpServletRequest request) {
        final String idkontrak = request.getParameter("nokontrak");
        return kontrakServices.validasiNomorKontrak(idkontrak);

    }

    @RequestMapping(value = "/json/getKodeMultiyear", method = RequestMethod.GET)
    public @ResponseBody
    Integer getBanyakNoBukti(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idkegiatan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);

        return kontrakServices.getKodeMultiyear(param);
    }

    @RequestMapping(value = "/json/getSisaKontrakSpj", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSisaKontrakSpj(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(2);
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String idkontrak = request.getParameter("idkontrak");

        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);
        param.put("idkontrak", idkontrak);

        final Map< String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("cek", 1);
        mapData.put("aData", kontrakServices.getSisaKontrakSpj(param));

        return mapData;

    }

    @RequestMapping(value = "/listbankinduk", method = RequestMethod.GET)
    public ModelAndView listbankinduk(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("/common/kontrak/listbankinduk");
    }

    @RequestMapping(value = "/json/listbankindukjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listbankindukjson(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kode = request.getParameter("kode");
        final String nama = request.getParameter("nama");

        param.put("kode", kode);
        param.put("nama", nama);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = kontrakServices.getCountBankInduk(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", kontrakServices.getBankInduk(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getNoKontrakCek", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getNoKontrakCek(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(2);
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String nokontrak = request.getParameter("nokontrak");
        final String idkontrak = request.getParameter("idkontrak");

        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("nokontrak", nokontrak);
        param.put("idkontrak", idkontrak);

        final Map< String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("cek", 1);
        mapData.put("aData", kontrakServices.getNoKontrakCek(param));

        return mapData;

    }

    @RequestMapping(value = "/json/getSisaKontrakSpd", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSisaKontrakSpd(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(2);
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idspd = request.getParameter("idspd");
        final String idkontrak = request.getParameter("idkontrak");

        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("idspd", idspd);
        param.put("idkontrak", idkontrak);

        final Map< String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("cek", 1);
        mapData.put("aData", kontrakServices.getSisaKontrakSpd(param));

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
                    if (name.equals("NPWP") || name.equals("NAMA") || name.equals("ALAMAT") || name.equals("KABKOT") || name.equals("STATUS_PKP")) {
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

    @RequestMapping(value = "/json/getSisaAnggaran", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSisaAnggaran(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(2);
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String idkontrak = request.getParameter("idkontrak");

        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);
        param.put("idkontrak", idkontrak);

        final Map< String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("aData", kontrakServices.getSisaAnggaran(param));

        return mapData;

    }

}
