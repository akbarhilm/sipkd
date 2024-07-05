package sp2d.ui.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import sp2d.services.CetakValidasiSP2DServices;
import sp2d.services.Sp2dTransferServices;
import sp2d.services.UserManagementServices;
import sp2d.util.SipkdHelpers;
import spp.model.Pengguna;
import spp.model.Sp2dSahTransfer;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/sp2dsahtransfer")
public class Sp2dSahTransferAction {

    private static final Logger log = LoggerFactory.getLogger(Sp2dSahTransferAction.class);
    @Autowired
    UserManagementServices userManagementServices;
    @Autowired
    CetakValidasiSP2DServices cetakValidasiSP2DServices;
    @Autowired
    Sp2dTransferServices transferServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;

    private final RestTemplate rest;
    private final HttpHeaders headers;
    private HttpStatus status;
    private static final String resourceUrlRealTime = "http://localhost:8086/TERIMA/halamanterimadata/json/terimasp2d";

    //private static final String resourceUrlRealTime = "http://10.100.111.153:9876/webiso"; // prod
    //private static final String resourceUrlRealTime = "http://10.100.111.152:9876/webmanual"; // dev
    public Sp2dSahTransferAction() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    @RequestMapping(value = "/json/transfersp2d", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    // Map<String, Object> transfersp2d(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
    Map<String, Object> transfersp2d(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) throws IOException {
        /*
         HttpEntity<Map<String, Object>> requestPostData = new HttpEntity<Map<String, Object>>(mapdata);

         final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
         final String kodewil = pengguna.getKodeProses();

         ResponseEntity<Map> response;

         response = rest.exchange(resourceUrlRealTime, HttpMethod.POST, requestPostData, Map.class);

         return response.getBody();
         */

        HttpEntity<Map<String, Object>> requestPostData = new HttpEntity<Map<String, Object>>(mapdata);
        InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/txt/transfer.txt");
        String url = SipkdHelpers.readFromInputStream(inputStream);

        ResponseEntity<Map> response = rest.exchange(url, HttpMethod.POST, requestPostData, Map.class);

        return response.getBody();
    }

    @RequestMapping(value = "/json/getlistsp2d", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspmcetak(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String kodewil = pengguna.getKodeProses();
        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("namaskpd");
        final String idskpd = request.getParameter("idskpd");
        final String kproses = request.getParameter("kproses");
        final String level = request.getParameter("levelSkpd");
        final String kskpd = request.getParameter("kodeskpd");
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
        param.put("kodewilayah", kodewil);
        param.put("namaskpd", skpd);
        param.put("idskpd", SipkdHelpers.getIntFromString(idskpd));
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        //final int banyak = cetakValidasiSP2DServices.getbanyaksp2dsah(param);
        final int banyak = transferServices.getbanyaksp2dtransfer(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", transferServices.getlistsp2dtransfer(param));
        //mapData.put("aaData", cetakValidasiSP2DServices.getlistsp2dsah(param));
        return mapData;
    }

    @RequestMapping(value = "/indextransfer", method = RequestMethod.GET)
    public String indextransfer(final HttpServletResponse response, final HttpServletRequest request) throws IOException {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        boolean status = false;
        final String ipaddr = pengguna.getIpAddress();
        /*
         if (ipaddr != null && StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotBlank(ipaddr)) {
         //  status = new IpAddressMatcher(ipaddr).matches(request) || new IpAddressMatcher("127.0.0.1").matches(request);
         status = Objects.equals(ipaddr, SipkdHelpers.getIpAddr(request)) || new IpAddressMatcher("127.0.0.1").matches(request) || new IpAddressMatcher("0:0:0:0:0:0:0:1").matches(request);
         }*/

        if ((ipaddr != null) && (StringUtils.isNotEmpty(ipaddr)) && (StringUtils.isNotBlank(ipaddr))) {
            status = (pengguna.getNamaPengguna().contains("99")) || (pengguna.getNamaPengguna().contains("ADMIN") || Objects.equals(ipaddr, SipkdHelpers.getIpAddr(request))) || (new IpAddressMatcher("127.0.0.1").matches(request)) || (new IpAddressMatcher("0:0:0:0:0:0:0:1").matches(request));
        }
        log.debug(ipaddr + " status = " + status + " request " + SipkdHelpers.getIpAddr(request));

        if (true) { // untuk dev
            //if (status) { // untuk prod
            return "sp2d/pengesahan/validasitransfer";
        } else {
            // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Anda tidak berhak mengakses halaman pengesahan");
            return "deny";
        }

    }

    @RequestMapping(value = "/json/simpansp2dbank", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        //pengguna.setNamaPengguna(rs.getString("I_PGUN"));
        //final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsp2d = (String) mapdata.get("idsp2d");
        final String nilaibayar = (String) mapdata.get("nilaibayar");
        final String msgkirim = (String) mapdata.get("msgkirim");
        final String tahun = (String) mapdata.get("tahun");
        final String kodebilling = (String) mapdata.get("kodebilling");
        final String bit4 = (String) mapdata.get("bit4");
        final String bit11 = (String) mapdata.get("bit11");
        final String bit12 = (String) mapdata.get("bit12");
        final String bit13 = (String) mapdata.get("bit13");
        final String bit37 = (String) mapdata.get("bit37");
        final String kodebank = (String) mapdata.get("kodebank");
        final String norektujuan = (String) mapdata.get("norektujuan");
        final String namatujuan = (String) mapdata.get("namatujuan");
        final String alamat = (String) mapdata.get("alamat");
        final String npwp = (String) mapdata.get("npwp");
        final String norekpengirim = (String) mapdata.get("norekpengirim");
        final String namapengirim = (String) mapdata.get("namapengirim");
        final String kodewil = (String) mapdata.get("kodewil");
        final String nodok = (String) mapdata.get("nodok");
        final String nilaibruto = (String) mapdata.get("nilaibruto");
        final String nilaipot = (String) mapdata.get("nilaipot");
        final String beban = (String) mapdata.get("beban");
        final String keterangan = (String) mapdata.get("keterangan");
        final String idskpd = (String) mapdata.get("idskpd");
        final String kodeskpd = (String) mapdata.get("kodeskpd");
        final String namaskpd = (String) mapdata.get("namaskpd");
        final String kodeakun = (String) mapdata.get("kodeakun");
        final String kodeauthor = (String) mapdata.get("kodeauthor");
        final String bit62 = (String) mapdata.get("bit62");

        Sp2dSahTransfer sp2d = new Sp2dSahTransfer();

        sp2d.setTahun(tahun);
        sp2d.setNilaiBayar(SipkdHelpers.getBigDecimalFromString(nilaibayar));
        sp2d.setIdSp2d(SipkdHelpers.getIntFromString(idsp2d.toString()));
        sp2d.setMsgKirim(msgkirim);
        sp2d.setKodeBilling(kodebilling);
        //sp2d.setPengguna(pengguna.getNamaPengguna());
        sp2d.setPengguna(kodeauthor);
        sp2d.setBit4(bit4);
        sp2d.setBit11(bit11);
        sp2d.setBit12(bit12);
        sp2d.setBit13(bit13);
        sp2d.setBit37(bit37);
        sp2d.setKodeBank(kodebank);
        sp2d.setNorekTujuan(norektujuan);
        sp2d.setNamaTujuan(namatujuan);
        sp2d.setAlamat(alamat);
        sp2d.setNpwp(npwp);
        sp2d.setNorekPengirim(norekpengirim);
        sp2d.setNamaPengirim(namapengirim);
        sp2d.setKodeWilayahProses(kodewil);
        sp2d.setNodokSp2d(nodok);
        sp2d.setNilaiBruto(nilaibruto);
        sp2d.setNilaiPot(nilaipot);
        sp2d.setKodeBeban(beban);
        sp2d.setKeterangan(keterangan);
        sp2d.setIdskpdRekon(idskpd);
        sp2d.setKodeSkpd(kodeskpd);
        sp2d.setNamaSkpd(namaskpd);
        sp2d.setKodeAkun(kodeakun);
        sp2d.setBit62(bit62);

        transferServices.insertSp2dBank(sp2d);

        return "Data SP2D Bank Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/getSysdate", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSysdate(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(3);

        final Map< String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", transferServices.getSysdate(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getKodeStan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getKodeStan(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(3);

        final Map< String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", transferServices.getKodeStan(param));

        return mapData;
    }

    @RequestMapping(value = "/json/updatesp2dbank", method = RequestMethod.POST)
    public @ResponseBody
    String updatesp2dbank(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        //final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String tahun = (String) mapdata.get("tahun");
        final String idsp2d = (String) mapdata.get("idsp2d");
        final String nilaibayarbank = (String) mapdata.get("nilaibayarbank");
        final String msgterimabank = (String) mapdata.get("msgterimabank");
        final String statusbank = (String) mapdata.get("statusbank");
        final String trxterimabank = (String) mapdata.get("trxterimabank");
        final String tglprosesbank = (String) mapdata.get("tglprosesbank");

        Sp2dSahTransfer sp2d = new Sp2dSahTransfer();

        sp2d.setTahun(tahun);
        sp2d.setNilaiBayarBank(SipkdHelpers.getBigDecimalFromString(nilaibayarbank));
        sp2d.setIdSp2d(SipkdHelpers.getIntFromString(idsp2d.toString()));
        sp2d.setMsgTerimaBank(msgterimabank);
        sp2d.setStatusBank(statusbank);
        sp2d.setTrxTerimaBank(trxterimabank);
        sp2d.setTglProses(tglprosesbank);

        transferServices.updateSp2dBank(sp2d);

        return "Data SP2D Bank Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/getBanyakTf", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getBanyakTf(final HttpServletRequest request) {

        final String tahun = request.getParameter("tahun");
        final Integer idsp2d = SipkdHelpers.getIntFromString(request.getParameter("idsp2d"));

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahun);
        param.put("idsp2d", idsp2d);

        final Map< String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", transferServices.getBanyakTf(param));
        //log.debug("CEK BANYAKKKKKKKKKKKKKKKKKKKKK toString ============  " + transferServices.getBanyakTf(param).toString());

        return mapData;
    }

    @RequestMapping(value = "/json/simpansp2dbankmanual", method = RequestMethod.POST)
    public @ResponseBody
    String simpansp2dbankmanual(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        //pengguna.setNamaPengguna(rs.getString("I_PGUN"));
        //final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsp2d = (String) mapdata.get("idsp2d");
        final String nilaibayar = (String) mapdata.get("nilaibayar");
        final String tahun = (String) mapdata.get("tahun");
        final String kodebank = (String) mapdata.get("kodebank");
        final String norektujuan = (String) mapdata.get("norektujuan");
        final String namatujuan = (String) mapdata.get("namatujuan");
        final String norekpengirim = (String) mapdata.get("norekpengirim");
        final String namapengirim = (String) mapdata.get("namapengirim");
        final String kodewil = (String) mapdata.get("kodewil");
        final String nodok = (String) mapdata.get("nodok");
        final String nilaibruto = (String) mapdata.get("nilaibruto");
        final String nilaipot = (String) mapdata.get("nilaipot");
        final String beban = (String) mapdata.get("beban");
        final String keterangan = (String) mapdata.get("keterangan");
        final String idskpd = (String) mapdata.get("idskpd");
        final String kodeskpd = (String) mapdata.get("kodeskpd");
        final String namaskpd = (String) mapdata.get("namaskpd");
        final String kodeakun = (String) mapdata.get("kodeakun");
        final String kodeauthor = pengguna.getIdPengguna().toString();
        final String bit62 = (String) mapdata.get("bit62");

        Sp2dSahTransfer sp2d = new Sp2dSahTransfer();

        sp2d.setTahun(tahun);
        sp2d.setNilaiBayar(SipkdHelpers.getBigDecimalFromString(nilaibayar));
        sp2d.setIdSp2d(SipkdHelpers.getIntFromString(idsp2d.toString()));
        sp2d.setPengguna(kodeauthor);
        sp2d.setKodeBank(kodebank);
        sp2d.setNorekTujuan(norektujuan);
        sp2d.setNamaTujuan(namatujuan);
        sp2d.setNorekPengirim(norekpengirim);
        sp2d.setNamaPengirim(namapengirim);
        sp2d.setKodeWilayahProses(kodewil);
        sp2d.setNodokSp2d(nodok);
        sp2d.setNilaiBruto(nilaibruto);
        sp2d.setNilaiPot(nilaipot);
        sp2d.setKodeBeban(beban);
        sp2d.setKeterangan(keterangan);
        sp2d.setIdskpdRekon(idskpd);
        sp2d.setKodeSkpd(kodeskpd);
        sp2d.setNamaSkpd(namaskpd);
        sp2d.setKodeAkun(kodeakun);
        sp2d.setBit62(bit62);

        transferServices.insertSp2dManual(sp2d);

        return "Data SP2D Bank Berhasil Disimpan";
    }
}
