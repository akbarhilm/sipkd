package spj.ui.action;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spp.model.Akun;
import spp.model.Kegiatan;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.Spj;
import spp.model.SpjRinci;
import spp.model.BapKas;

import spp.model.SppUp;
import spj.services.ReferensiServices;
import spj.services.SpjServices;
import spj.services.BapKasServices;
import spj.services.CetakReportServices;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SipkdHelpers;
import spj.util.SqlDatePropertyEditor;
import spp.model.BapKasRinci;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/bapkas")
public class BapKasAction {

    private static final Logger log = LoggerFactory.getLogger(BapKasAction.class);

    @Autowired
    CetakReportServices cetakReportServices;

    @Autowired
    BapKasServices bapKasServices;

    @Autowired
    ReferensiServices referensiServices;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/indexbapkas", method = RequestMethod.GET)
    public String index(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        int thn = Integer.valueOf(tahunAnggaran);
        int th = thn + 1;
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("tahun1", th);
        param.put("idskpd", listSkpd.get(0).getIdSkpd());

        final int tglBkuPros = bapKasServices.getCountTglBkuProses(param);
        log.debug("Parameter THN + 1----> " + param);
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }

        request.setAttribute("tglBkuPros", tglBkuPros);
        return "bapkas/indexbapkas";

    }

    @RequestMapping(value = "/json/getlistbapkas", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspjbl(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        final String tp = request.getParameter("tglBkuPros");
        final String skpd = request.getParameter("namaskpd");
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
        param.put("idskpd", idskpd);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = bapKasServices.getCountBapKas(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bapKasServices.getBapKas(param));
        return mapData;
    }

    @RequestMapping(value = "/addbapkas/{idskpd}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable Integer idskpd, final BapKas bapkas, final HttpServletRequest request, Model model) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }
        bapkas.setSkpd(referensiServices.getDetailSkpdById(idskpd));

        return new ModelAndView("bapkas/addbapkas", "refsppup", bapkas);

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
        mapData.put("aData", bapKasServices.getBulanList(param));
        log.debug("Parameter ===> " + mapData.toString());

        return mapData;
    }

    @RequestMapping(value = "/json/setBulanEdit", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> tanggal1(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bapKasServices.getBulanListEdit(param));
        log.debug("Parameter ===> " + mapData.toString());

        return mapData;
    }

    @RequestMapping(value = "/editbapkas/{is}/{tahun}/{tgl}/{bln}/{idb}", method = RequestMethod.GET)
    public ModelAndView editspjbl(@PathVariable String is, BapKas bapkas, @PathVariable String tahun, @PathVariable String tgl, @PathVariable String bln, @PathVariable Integer idb, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();

        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        final Map< String, Object> param = new HashMap<String, Object>(3);
        String t = tgl;
        //String tglkon = SipkdHelpers.getStringDateFormatFromString(t,"dd/MM/yyyy","yyyyMMdd");
        bapkas.setBlnBkuBa(bln);
        //bapkas.setTglBkuBa(tglkon);
        param.put("idskpd", is);
        param.put("tahun", tahun);
        param.put("bulan", bln);
        bapkas = bapKasServices.getBapKasById(param);
        return new ModelAndView("bapkas/editbapkas", "refsppup", bapkas);
    }

    @RequestMapping(value = "/editbapkas2", method = RequestMethod.GET)
    public ModelAndView editbapkas2(@PathVariable String is, BapKas bapkas, @PathVariable String tahun, @PathVariable String tgl, @PathVariable String bln, @PathVariable String idb, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();

        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        final Map< String, Object> param = new HashMap<String, Object>(3);
        String t = tgl;
        //String tglkon = SipkdHelpers.getStringDateFormatFromString(t,"dd/MM/yyyy","yyyyMMdd");
        bapkas.setBlnBkuBa(bln);
        //bapkas.setTglBkuBa(tglkon);
        param.put("idskpd", is);
        param.put("tahun", tahun);
        param.put("bulan", bln);
        bapkas = bapKasServices.getBapKasById(param);
        return new ModelAndView("bapkas/editbapkas", "refsppup", bapkas);
    }

    @RequestMapping(value = "/deletebapkas/{is}/{tahun}/{bln}", method = RequestMethod.GET)
    public ModelAndView deletespjbl(@PathVariable String is, BapKas bapkas, @PathVariable String tahun, @PathVariable String bln, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idskpd", is);
        param.put("tahun", tahun);
        param.put("bulan", bln);
        bapkas = bapKasServices.getBapKasById(param);
        return new ModelAndView("bapkas/deletebapkas", "refsppup", bapkas);
    }

    @RequestMapping(value = "/viewbapkas/{is}/{tahun}/{bln}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable String is, BapKas bapkas, @PathVariable String tahun, @PathVariable String bln, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idskpd", is);
        param.put("tahun", tahun);
        param.put("bulan", bln);
        bapkas = bapKasServices.getBapKasById(param);
        return new ModelAndView("bapkas/viewbapkas", "refsppup", bapkas);
    }

    @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String idskpd = request.getParameter("idskpd");
        final String tahun = request.getParameter("tahun");
        final String bulan = request.getParameter("bulan");

        try {
            //final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");

            map.put("SUBREPORT_DIR", pathReport);
            map.put("ID_SKPD", idskpd);
            map.put("TAHUN", tahun);
            map.put("BULAN", bulan);

            List<Map> listhasil = bapKasServices.getnilaiparam(map);
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
            map.put("PERATURAN_6", listhasil.get(0).get("E_PERATURAN_SPD6"));
            map.put("PERATURAN_7", listhasil.get(0).get("E_PERATURAN_SPD7"));

            map.put("pathreport", pathReport + "/Report_BAP_Kas.jasper");
            JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
            //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_BAP_Kas.jasper", map, jdbcConnection);

            final String filename = "Laporan-Berita_Acara_Pemeriksaan-Kas-" + "." + tahunAnggaran + "." + idskpd + ".pdf";
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

    @RequestMapping(value = "/cetakbapkas/{is}/{tahun}/{bln}/{namafile}", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response, @PathVariable String is, @PathVariable Integer tahun, @PathVariable String bln, @PathVariable String namafile) {

        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        try {
            //final Connection jdbcConnection = dataSource.getConnection();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            final Map<String, Object> map = new HashMap<String, Object>();
            final String pathReport = servletContext.getInitParameter("PATH_REPORT");
            List<Map> listhasil = bapKasServices.getnilaiparam(map);
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
            map.put("PERATURAN_6", listhasil.get(0).get("E_PERATURAN_SPD6"));
            map.put("PERATURAN_7", listhasil.get(0).get("E_PERATURAN_SPD7"));

            map.put("SUBREPORT_DIR", pathReport);
            map.put("ID_SKPD", is);
            map.put("TAHUN", tahun);
            map.put("BULAN", bln);

            map.put("pathreport", pathReport + "/Report_BAP_Kas.jasper");
            JasperPrint jasperPrint = cetakReportServices.cetakReport(map);
            //JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_BAP_Kas.jasper", map, jdbcConnection);
            final String filename = "Laporan-Berita_Acara_Pemeriksaan-Kas-" + "." + tahunAnggaran + "." + is + ".pdf";
            map.put("namaFile", filename);
            response.setHeader("Content-Disposition", filename);
            ServletOutputStream output = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            output.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpan(@Valid @ModelAttribute("refsppup") BapKas bapkas, BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes) {

        Integer idskpd = bapkas.getSkpd().getIdSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(3);
        final Map< String, Object> param1 = new HashMap<String, Object>(3);
        final Map< String, Object> param2 = new HashMap<String, Object>(3);
        param.put("idskpd", idskpd);
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final StringBuilder sburl = new StringBuilder("redirect:/bapkas/editbapkas/{idb}");
        final StringBuilder sburlindex = new StringBuilder("redirect:/bapkas/indexbapkas/");
        StringBuilder finalurl = new StringBuilder();
        StringBuilder urlc = new StringBuilder();
        StringBuilder urlcs = new StringBuilder();
        if (idskpd.equals(1234) || idskpd.equals(761)) {
            String t = bapkas.getTglBkuBa();
            String tgl = SipkdHelpers.getStringDateFormatFromString(t, "yyyyMMdd", "dd/MM/yyyy");
            bapkas.setTglBkuBa(tgl);
            log.debug("tanggal dari jsp ==> " + t + " dikonversi ==> " + tgl + " masuk ke bapkas ==> " + bapkas.getTglBkuBa() + " Kode SKPD ===> " + idskpd);

            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            bapkas.setIdEntry(pengguna.getIdPengguna());
            bapkas.setTglEntry(new Timestamp(System.currentTimeMillis()));
            String is = String.valueOf(idskpd);
            bapKasServices.insertBapKas(bapkas);
            int thn = Integer.valueOf(tahun);
            int th = thn + 1;
            param2.put("tahun", th);
            param2.put("saldobank", bapkas.getNilaiUangSaldoBank());
            param2.put("saldobankdebet", bapkas.getNilaiUangSaldoBank());
            param2.put("idskpd", is);
            param2.put("idbas", "9948");
            log.debug("Saldo Bank TH INSERT==> " + th + " Saldo Bank Nilai ==> " + bapkas.getNilaiUangSaldoBank() + " IDSKPD ==> " + is + " PARAM 2 ===> " + param2);
            bapKasServices.insertSaldoAwalBank(param2);
        } else {
            String t = bapkas.getTglBkuBa();
            String tgl = SipkdHelpers.getStringDateFormatFromString(t, "yyyyMMdd", "dd/MM/yyyy");
            bapkas.setTglBkuBa(tgl);
            bapkas.setKodeWilSp2d("-");
            log.debug("tanggal dari jsp ==> " + t + " dikonversi ==> " + tgl + " masuk ke bapkas ==> " + bapkas.getTglBkuBa() + " Kode SKPD ===> " + idskpd);

            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            bapkas.setIdEntry(pengguna.getIdPengguna());
            bapkas.setTglEntry(new Timestamp(System.currentTimeMillis()));
            String is = String.valueOf(idskpd);
            bapKasServices.insertBapKas(bapkas);
            int thn = Integer.valueOf(tahun);
            int th = thn + 1;
            param2.put("tahun", th);
            param2.put("saldobank", bapkas.getNilaiUangSaldoBank());
            param2.put("saldobankdebet", bapkas.getNilaiUangSaldoBank());
            param2.put("idskpd", is);
            param2.put("idbas", "9948");
            log.debug("Saldo Bank TH INSERT==> " + th + " Saldo Bank Nilai ==> " + bapkas.getNilaiUangSaldoBank() + " IDSKPD ==> " + is + " PARAM 2 ===> " + param2);
            bapKasServices.insertSaldoAwalBank(param2);
        }

        return "redirect:/bapkas/indexbapkas";
    }

    @RequestMapping(value = "/prosesubah", method = RequestMethod.POST)
    public String prosesubah(@Valid @ModelAttribute("refsppup") BapKas bapkas, BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes
    ) {
        final StringBuilder sburl = new StringBuilder("redirect:/bapkas/editbapkas/");
        final Map< String, Object> param1 = new HashMap<String, Object>(3);
        final Map< String, Object> param = new HashMap<String, Object>(3);
        final Map< String, Object> param2 = new HashMap<String, Object>(3);
        Integer idskpd = bapkas.getSkpd().getIdSkpd();
        String bln = bapkas.getBlnBkuBa();
        if (idskpd.equals(1234) || idskpd.equals(761)) {
            String t = bapkas.getTglBkuBa();
            String tgl = SipkdHelpers.getStringDateFormatFromString(t, "yyyyMMdd", "dd/MM/yyyy");
            bapkas.setTglBkuBa(tgl);
            log.debug("tanggal dari jsp ==> " + t + " dikonversi ==> " + tgl + " masuk ke bapkas ==> " + bapkas.getTglBkuBa() + " Kode SKPD ===> " + idskpd);
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            bapkas.setIdEntry(pengguna.getIdPengguna());
            bapkas.setTglEntry(new Timestamp(System.currentTimeMillis()));

            String is = String.valueOf(idskpd);
            log.debug("Bulan nya===> " + bln);
            final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
            param1.put("idskpd", is);
            param1.put("tahun", tahun);
            param1.put("blnBkuBa", bln);
            bapKasServices.updateBapKasAll(bapkas);
            int thn = Integer.valueOf(tahun);
            int th = thn + 1;
            param2.put("tahun", th);
            param2.put("saldobank", bapkas.getNilaiUangSaldoBank());
            param2.put("saldobankdebet", bapkas.getNilaiUangSaldoBank());
            param2.put("idbas", "9948");
            param2.put("idskpd", is);
            log.debug("Saldo Bank TH UPDATE ==> " + th + " Saldo Bank Nilai ==> " + bapkas.getNilaiUangSaldoBank() + " IDSKPD ==> " + is + " PARAM 2 ===> " + param2);
            //bapKasServices.updateSaldoAwalBank(param2);
        } else {
            String t = bapkas.getTglBkuBa();
            String tgl = SipkdHelpers.getStringDateFormatFromString(t, "yyyyMMdd", "dd/MM/yyyy");
            bapkas.setTglBkuBa(tgl);
            bapkas.setKodeWilSp2d("-");
            log.debug("tanggal dari jsp ==> " + t + " dikonversi ==> " + tgl + " masuk ke bapkas ==> " + bapkas.getTglBkuBa() + " Kode SKPD ===> " + idskpd);
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            bapkas.setIdEntry(pengguna.getIdPengguna());
            bapkas.setTglEntry(new Timestamp(System.currentTimeMillis()));
            log.debug("Bulan nya===> " + bln);
            String is = String.valueOf(idskpd);
            final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
            param1.put("idskpd", is);
            param1.put("tahun", tahun);
            //param1.put("blnBkuBa", bln);
            bapKasServices.updateBapKasAll(bapkas);
            int thn = Integer.valueOf(tahun);
            int th = thn + 1;
            param2.put("tahun", th);
            param2.put("saldobank", bapkas.getNilaiUangSaldoBank());
            param2.put("saldobankdebet", bapkas.getNilaiUangSaldoBank());
            param2.put("idbas", "9948");
            param2.put("idskpd", is);
            log.debug("Saldo Bank TH UPDATE ==> " + th + " Saldo Bank Nilai ==> " + bapkas.getNilaiUangSaldoBank() + " IDSKPD ==> " + is + " PARAM 2 ===> " + param2);
            //bapKasServices.updateSaldoAwalBank(param2);
        }

        StringBuilder finalurl = new StringBuilder();
        StringBuilder urlc = new StringBuilder();
        StringBuilder urlcs = new StringBuilder();
        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data Berita Acara Pemerikasaan Kas ")
                .append(" Berhasil Diupdate ")
                .toString());

        return "redirect:/bapkas/indexbapkas";
    }

    @RequestMapping(value = "/prosesdelete", method = RequestMethod.POST)
    public String deletebank(@Valid @ModelAttribute("refsppup") BapKas bapkas, final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(3);
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        int idskpd = bapkas.getSkpd().getIdSkpd();
        String bln = bapkas.getBlnBkuBa();
        String bulan = request.getParameter("blnBkuBa");
        String idba = request.getParameter("idSkpdBAPKas");
        String is = String.valueOf(idskpd);
        int thn = Integer.valueOf(tahun);
        int th = thn + 1;
        param.put("idskpd", is);
        param.put("tahun", tahun);
        param.put("tahun1", th);
        param.put("idbas", "9948");
        param.put("blnBkuBa", bln);
        param.put("idba", idba);

        //param rinci
        //final Map< String, Object> param2 = new HashMap<String, Object>(3);
        //int id = bapkas.getSkpd().getIdBapKas();
        //String id = String.valueOf(idSkpdBAPKas);
        //String id = request.getParameter("idSkpdBAPKas");
        //param2.put("value", id);
        log.debug("Para Hapus ===> " + bulan + " =========== " + param.toString());
        bapKasServices.deleteBapKas(param);
        // bapKasServices.deleteSaldoAwalBank(param); // tidak menghapus ke tmjoursaldoawal
        // bapKasServices.deleteBapKasRinci2(param2);
        return "redirect:/bapkas/indexbapkas";
    }

    @RequestMapping(value = "/json/getlistbapkasrinci", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listaset(final HttpServletRequest request) {
        final String tahun = request.getParameter("tahunAngg");
        final String idSkpdBAPKas = request.getParameter("idSkpdBAPKas");
        final Map< String, Object> param = new HashMap<String, Object>(9);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        //param.put("tahun",tahun);
        param.put("idSkpdBAPKas", idSkpdBAPKas);
        log.debug("cek anitaikan" + idSkpdBAPKas);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = bapKasServices.getBanyakAllBAPKAS(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bapKasServices.getAllBAPKAS(param));
        return mapData;
    }

    @RequestMapping(value = "/prosessimpan2", method = RequestMethod.POST)
    public String prosessimpan2(@Valid @ModelAttribute("refsppup") BapKas bapkas,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        bapkas.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        //log.debug(" hasil ################# " + result.getFieldError());
        final StringBuilder sburl = new StringBuilder("redirect:/bapkas/editbapkas2");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        /* if (result.hasErrors()) {
         return "spp/btl/addsppbtl";
         } else { */
        Integer idskpd = bapkas.getSkpd().getIdSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(3);
        final Map< String, Object> param1 = new HashMap<String, Object>(3);
        final Map< String, Object> param2 = new HashMap<String, Object>(3);
        param.put("idskpd", idskpd);
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        //final StringBuilder sburl = new StringBuilder("redirect:/bapkas/editbapkas/");
        final StringBuilder sburlindex = new StringBuilder("redirect:/bapkas/indexbapkas/");
        StringBuilder finalurl = new StringBuilder();
        StringBuilder urlc = new StringBuilder();
        StringBuilder urlcs = new StringBuilder();

        if (idskpd.equals(1234) || idskpd.equals(761)) {
            String t = bapkas.getTglBkuBa();
            String tgl = SipkdHelpers.getStringDateFormatFromString(t, "yyyyMMdd", "dd/MM/yyyy");
            bapkas.setTglBkuBa(tgl);
            log.debug("anitaikan - tanggal dari jsp ==> " + t + " dikonversi ==> " + tgl + " masuk ke bapkas ==> " + bapkas.getTglBkuBa() + " Kode SKPD ===> " + idskpd);

            bapkas.setIdEntry(pengguna.getIdPengguna());
            bapkas.setTglEntry(new Timestamp(System.currentTimeMillis()));
            String is = String.valueOf(idskpd);
            //bapKasServices.insertBapKas(bapkas);

            //-------------Insert ke saldo awal Bank-------------
            //jika bulan = 12
            String bln = request.getParameter("blnBkuBa");
            if (bln == "12") {
                log.debug("anitaikan  bulannya adalah 12 - ");
                int thn = Integer.valueOf(tahun);
                int th = thn + 1;
                param2.put("tahun", th);
                param2.put("saldobank", bapkas.getNilaiUangSaldoBank());
                param2.put("saldobankdebet", bapkas.getNilaiUangSaldoBank());
                param2.put("idskpd", is);
                //BAS: 3897 - 1.1.01.03.01.002 - Sisa LS
                param2.put("idbas", "3897");
                //param c akun - ambil make query
                log.debug("Saldo Bank TH INSERT==> " + th + " Saldo Bank Nilai ==> " + bapkas.getNilaiUangSaldoBank() + " IDSKPD ==> " + is + " PARAM 2 ===> " + param2);
                //bapKasServices.insertSaldoAwalBank(param2);
            }
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            log.debug("anitaikan cek banyak rinci=  " + banyakrinci);
            final List<BapKasRinci> listBapKasRinci = new ArrayList<BapKasRinci>(banyakrinci);
            //log.debug(" Map param  "+  request.getParameterMap().toString());
            for (int i = 0; i < banyakrinci; i++) {
                int indeks2 = i + 1;

                final String penanda = "pilih" + (i + 1);
                final String nourutidx = request.getParameter(penanda);

                final String idrow = "pilih" + (i + 1);
                final String idrowx = request.getParameter(idrow);

                //if (nourutidx != null && !nourutidx.isEmpty()) { //ini masalahnya
                final BapKasRinci bapKasRinci = new BapKasRinci();
                final Akun akun = new Akun();
                akun.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idakun" + indeks2)));
                bapKasRinci.setIdEntry(pengguna.getIdPengguna());
                bapKasRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));
                bapKasRinci.setNamaBapKas(request.getParameter("namaBapKas" + indeks2));
                bapKasRinci.setNilaiBapKas(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaiBapKas" + indeks2)));

                listBapKasRinci.add(bapKasRinci);
                log.debug("cek anitaikan listBapKasRinci=" + listBapKasRinci);
                //}
            }

            bapkas.setBapKasRinci(listBapKasRinci);
            bapKasServices.insertBapKasAll(bapkas);
        } else {
            String t = bapkas.getTglBkuBa();
            String tgl = SipkdHelpers.getStringDateFormatFromString(t, "yyyyMMdd", "dd/MM/yyyy");
            bapkas.setTglBkuBa(tgl);
            bapkas.setKodeWilSp2d("-");
            log.debug("anitaikan - tanggal dari jsp ==> " + t + " dikonversi ==> " + tgl + " masuk ke bapkas ==> " + bapkas.getTglBkuBa() + " Kode SKPD ===> " + idskpd);

            bapkas.setIdEntry(pengguna.getIdPengguna());
            bapkas.setTglEntry(new Timestamp(System.currentTimeMillis()));
            String is = String.valueOf(idskpd);
            //bapKasServices.insertBapKas(bapkas);

            //-------------Insert ke saldo awal Bank-------------
            //jika bulan = 12
            String bln = request.getParameter("blnBkuBa");
            if (bln == "12") {
                log.debug("anitaikan  bulannya adalah 12 - ");
                int thn = Integer.valueOf(tahun);
                int th = thn + 1;
                param2.put("tahun", th);
                param2.put("saldobank", bapkas.getNilaiUangSaldoBank());
                param2.put("saldobankdebet", bapkas.getNilaiUangSaldoBank());
                param2.put("idskpd", is);
                //BAS: 3897 - 1.1.01.03.01.002 - Sisa LS
                param2.put("idbas", "3897");
                //param c akun - ambil make query
                log.debug("Saldo Bank TH INSERT==> " + th + " Saldo Bank Nilai ==> " + bapkas.getNilaiUangSaldoBank() + " IDSKPD ==> " + is + " PARAM 2 ===> " + param2);
                bapKasServices.insertSaldoAwalBank(param2);
            }
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            log.debug("anitaikan cek banyak rinci=  " + banyakrinci);
            final List<BapKasRinci> listBapKasRinci = new ArrayList<BapKasRinci>(banyakrinci);
            //log.debug(" Map param  "+  request.getParameterMap().toString());
            for (int i = 0; i < banyakrinci; i++) {
                int indeks2 = i + 1;

                final String penanda = "pilih" + (i + 1);
                final String nourutidx = request.getParameter(penanda);

                final String idrow = "pilih" + (i + 1);
                final String idrowx = request.getParameter(idrow);

                //if (nourutidx != null && !nourutidx.isEmpty()) { //ini masalahnya
                final BapKasRinci bapKasRinci = new BapKasRinci();
                final Akun akun = new Akun();
                akun.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idakun" + indeks2)));
                bapKasRinci.setIdEntry(pengguna.getIdPengguna());
                bapKasRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));
                bapKasRinci.setNamaBapKas(request.getParameter("namaBapKas" + indeks2));
                bapKasRinci.setNilaiBapKas(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaiBapKas" + indeks2)));

                listBapKasRinci.add(bapKasRinci);
                log.debug("cek anitaikan listBapKasRinci=" + listBapKasRinci);
                //}
            }

            bapkas.setBapKasRinci(listBapKasRinci);
            bapKasServices.insertBapKasAll(bapkas);

        }

        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                .append(" berhasil ditambahkan ")
                .toString());

        //return sburl.append(bapkas.getIdSkpdBAPKas()).toString(); //ini buat redirect ke panel edit
        return "redirect:/bapkas/indexbapkas";
        //}
    }

    @RequestMapping(value = "/prosesubah2", method = RequestMethod.POST)
    public String prosesubah2(@Valid @ModelAttribute("refsppup") BapKas bapkas,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        bapkas.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        //log.debug(" hasil ################# " + result.getFieldError());
        final StringBuilder sburl = new StringBuilder("redirect:/bapkas/editbapkas2");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        /* if (result.hasErrors()) {
         return "spp/btl/addsppbtl";
         } else { */
        Integer idskpd = bapkas.getSkpd().getIdSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(3);
        final Map< String, Object> param1 = new HashMap<String, Object>(3);
        final Map< String, Object> param2 = new HashMap<String, Object>(3);
        param.put("idskpd", idskpd);
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        //final StringBuilder sburl = new StringBuilder("redirect:/bapkas/editbapkas/");
        final StringBuilder sburlindex = new StringBuilder("redirect:/bapkas/indexbapkas/");
        StringBuilder finalurl = new StringBuilder();
        StringBuilder urlc = new StringBuilder();
        StringBuilder urlcs = new StringBuilder();

        if (idskpd.equals(1234) || idskpd.equals(761)) {
            String t = bapkas.getTglBkuBa();
            String tgl = SipkdHelpers.getStringDateFormatFromString(t, "yyyyMMdd", "dd/MM/yyyy");
            bapkas.setTglBkuBa(tgl);
            log.debug("anitaikan - tanggal dari jsp ==> " + t + " dikonversi ==> " + tgl + " masuk ke bapkas ==> " + bapkas.getTglBkuBa() + " Kode SKPD ===> " + idskpd);

            bapkas.setIdEntry(pengguna.getIdPengguna());
            bapkas.setTglEntry(new Timestamp(System.currentTimeMillis()));
            String is = String.valueOf(idskpd);
            //bapKasServices.insertBapKas(bapkas);

            //-------------Insert ke saldo awal Bank-------------
            //jika bulan = 12
            String bln = request.getParameter("blnBkuBa");
            if (bln == "12") {
                log.debug("anitaikan  bulannya adalah 12 - ");
                int thn = Integer.valueOf(tahun);
                int th = thn + 1;
                param2.put("tahun", th);
                param2.put("saldobank", bapkas.getNilaiUangSaldoBank());
                param2.put("saldobankdebet", bapkas.getNilaiUangSaldoBank());
                param2.put("idskpd", is);
                //BAS: 3897 - 1.1.01.03.01.002 - Sisa LS
                param2.put("idbas", "3897");
                //param c akun - ambil make query
                log.debug("Saldo Bank TH INSERT==> " + th + " Saldo Bank Nilai ==> " + bapkas.getNilaiUangSaldoBank() + " IDSKPD ==> " + is + " PARAM 2 ===> " + param2);
                //bapKasServices.insertSaldoAwalBank(param2);
            }
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            log.debug("anitaikan cek banyak rinci=  " + banyakrinci);
            final List<BapKasRinci> listBapKasRinci = new ArrayList<BapKasRinci>(banyakrinci);
            //log.debug(" Map param  "+  request.getParameterMap().toString());
            for (int i = 0; i < banyakrinci; i++) {
                int indeks2 = i + 1;

                final String penanda = "cekpilih" + (i + 1);

                final String nourutidx = request.getParameter(penanda);
                log.debug("cek anitaikan listBapKasRinci penanda=" + penanda);
                log.debug("cek anitaikan listBapKasRinci nourutidx=" + nourutidx);

                //if (nourutidx != null && !nourutidx.isEmpty()) { //ini masalahnya
                final BapKasRinci bapKasRinci = new BapKasRinci();
                final Akun akun = new Akun();
                akun.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idakun" + indeks2)));
                bapKasRinci.setIdEntry(pengguna.getIdPengguna());
                bapKasRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));
                bapKasRinci.setNamaBapKas(request.getParameter("namaBapKas" + indeks2));
                bapKasRinci.setNilaiBapKas(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaiBapKas" + indeks2)));

                listBapKasRinci.add(bapKasRinci);
                log.debug("cek anitaikan listBapKasRinci=" + listBapKasRinci);
                //}
            }

            bapkas.setBapKasRinci(listBapKasRinci);
            bapKasServices.updateBapKasAll(bapkas);
        } else {
            String t = bapkas.getTglBkuBa();
            String tgl = SipkdHelpers.getStringDateFormatFromString(t, "yyyyMMdd", "dd/MM/yyyy");
            bapkas.setTglBkuBa(tgl);
            bapkas.setKodeWilSp2d("-");
            log.debug("anitaikan - tanggal dari jsp ==> " + t + " dikonversi ==> " + tgl + " masuk ke bapkas ==> " + bapkas.getTglBkuBa() + " Kode SKPD ===> " + idskpd);

            bapkas.setIdEntry(pengguna.getIdPengguna());
            bapkas.setTglEntry(new Timestamp(System.currentTimeMillis()));
            String is = String.valueOf(idskpd);
            //bapKasServices.insertBapKas(bapkas);

            //-------------Insert ke saldo awal Bank-------------
            //jika bulan = 12
            String bln = request.getParameter("blnBkuBa");
            if (bln == "12") {
                log.debug("anitaikan  bulannya adalah 12 - ");
                int thn = Integer.valueOf(tahun);
                int th = thn + 1;
                param2.put("tahun", th);
                param2.put("saldobank", bapkas.getNilaiUangSaldoBank());
                param2.put("saldobankdebet", bapkas.getNilaiUangSaldoBank());
                param2.put("idskpd", is);
                //BAS: 3897 - 1.1.01.03.01.002 - Sisa LS
                param2.put("idbas", "3897");
                //param c akun - ambil make query
                //log.debug("Saldo Bank TH INSERT==> " + th + " Saldo Bank Nilai ==> " + bapkas.getNilaiUangSaldoBank() + " IDSKPD ==> " + is + " PARAM 2 ===> " + param2);
                //bapKasServices.insertSaldoAwalBank(param2);
            }
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final int idrowbaru = SipkdHelpers.getIntFromString(request.getParameter("idrowbaru"));

            final int banyakrincibaru = banyakrinci - idrowbaru;
            log.debug("cek anitaikan banyakrinci=========" + banyakrinci);
            final List<BapKasRinci> listBapKasRinci = new ArrayList<BapKasRinci>(banyakrinci);
            final List<BapKasRinci> listBapKasRincibaru = new ArrayList<BapKasRinci>(banyakrincibaru);

            for (int i = 0; i < banyakrinci; i++) {
                int indeks2 = i + 1;
                final String penanda = "cekpilih" + (i + 1);

                final String nourutidx = request.getParameter(penanda);
                //log.debug("cek anitaikan listBapKasRinci penanda=" +penanda);
                //log.debug("cek anitaikan listBapKasRinci nourutidx=" +nourutidx);
                //if (nourutidx != null && !nourutidx.isEmpty()) { //ini masalahnya
                final String nilaiBapKas = request.getParameter("nilaiBapKas" + indeks2);
                final String idSkpdBAPKas = request.getParameter("idSkpdBAPKas" + indeks2);
                final String idSkpdBAPKasRinci = request.getParameter("idSkpdBAPKasRinci" + indeks2);
                log.debug("cek anitaikan nilaiBapKas=" + nilaiBapKas);
                log.debug("cek anitaikan idSkpdBAPKas=" + idSkpdBAPKas);
                log.debug("cek anitaikan idSkpdBAPKasRinci=" + idSkpdBAPKasRinci);
                final BapKasRinci bapKasRinci = new BapKasRinci();
                final Akun akun = new Akun();
                akun.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idakun" + indeks2)));

                bapKasRinci.setIdEntry(pengguna.getIdPengguna());
                bapKasRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));
                bapKasRinci.setNamaBapKas(request.getParameter("namaBapKas" + indeks2));

                bapKasRinci.setNilaiBapKas(SipkdHelpers.getBigDecimalFromString(nilaiBapKas));
                bapKasRinci.setIdSkpdBAPKas(SipkdHelpers.getIntFromString(idSkpdBAPKas));
                bapKasRinci.setIdSkpdBAPKasRinci(SipkdHelpers.getIntFromString(idSkpdBAPKasRinci));

                listBapKasRinci.add(bapKasRinci);
                //}
            }

            bapkas.setBapKasRinci(listBapKasRinci);
            bapKasServices.updateBapKasAll(bapkas);

        }

        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                .append(" berhasil ditambahkan ")
                .toString());

        //return sburl.append(bapkas.getIdSkpdBAPKas()).toString(); //ini buat redirect ke panel edit
        return "redirect:/bapkas/indexbapkas";
        //}
    }

    /*
     @RequestMapping(value = "/json/getlistBAPKasRinci ", method = RequestMethod.GET)
     public @ResponseBody
     Integer getlistBAPKasRinci(final HttpServletRequest request) {
     final String TahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
     final String idskpd = request.getParameter("idskpd");
     final Map< String, Object> param = new HashMap<String, Object>(6);
     param.put("tahun", TahunAnggaran);
     param.put("idskpd", idskpd);
     return bapKasServices.getBanyakBAPKasRinci(param);
     }
     */
    @RequestMapping(value = "/json/getbanyakrincibapkas ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getBanyakBAPKasRinci(final HttpServletRequest request) {
        //final String TahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idSkpdBAPKas = request.getParameter("idSkpdBAPKas");
        //final String idskpd = request.getParameter("idskpd");

        log.debug(" Map param getbanyakrincibapkas " + idSkpdBAPKas);
        final Map< String, Object> param = new HashMap<String, Object>(6);
        //param.put("tahun", TahunAnggaran);
        //param.put("idskpd", idskpd);
        param.put("idSkpdBAPKas", idSkpdBAPKas);
        return bapKasServices.getBanyakAllBAPKAS(param);
    }

    @RequestMapping(value = "/json/getNilaiKas", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getNilaiKas(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String bulan = request.getParameter("bulan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("bulan", bulan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bapKasServices.getNilaiKas(param));
        return mapData;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
