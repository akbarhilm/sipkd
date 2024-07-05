package sp2d.ui.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.catalina.util.Base64;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sp2d.services.LaporanPnrmServices;
import sp2d.services.Sp2dPajakTransferServices;
import sp2d.util.SipkdHelpers;
import spp.model.Pengguna;
import spp.model.Sp2dPajakJson;
import spp.model.Sp2dPajakTransfer;

@Controller
@RequestMapping({"/sp2dpajaktransfer"})
public class Sp2dPajakTransferAction {

    private static final Logger log = LoggerFactory.getLogger(Sp2dPajakTransferAction.class);
    @Autowired
    Sp2dPajakTransferServices sp2dServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    LaporanPnrmServices cetakServices;
    @Autowired
    DataSource dataSource;

    public Sp2dPajakTransferAction() {
    }

    @RequestMapping(value = {"/indextransfer"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String indextransfer(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        boolean status = false;
        String ipaddr = pengguna.getIpAddress();
        if ((ipaddr != null) && (StringUtils.isNotEmpty(ipaddr)) && (StringUtils.isNotBlank(ipaddr))) {
            status = (pengguna.getNamaPengguna().contains("99")) || (pengguna.getNamaPengguna().contains("ADMIN") || Objects.equals(ipaddr, SipkdHelpers.getIpAddr(request))) || (new IpAddressMatcher("127.0.0.1").matches(request)) || (new IpAddressMatcher("0:0:0:0:0:0:0:1").matches(request));
        }
        log.debug(ipaddr + " status = " + status + " request " + SipkdHelpers.getIpAddr(request));

        //true: dibuka semua, status: sesuai IP/user admin
        if (status) {
            return "sp2d/pajaktransfer/indextransfer";
        } else {
            // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Anda tidak berhak mengakses halaman pengesahan");
            return "deny";
        }
    }

    @RequestMapping(value = {"/indextransfercetak"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String indextransfercetak(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        boolean status = false;
        String ipaddr = pengguna.getIpAddress();
        if ((ipaddr != null) && (StringUtils.isNotEmpty(ipaddr)) && (StringUtils.isNotBlank(ipaddr))) {
            status = (pengguna.getNamaPengguna().contains("99")) || (pengguna.getNamaPengguna().contains("ADMIN") || Objects.equals(ipaddr, SipkdHelpers.getIpAddr(request))) || (new IpAddressMatcher("127.0.0.1").matches(request)) || (new IpAddressMatcher("0:0:0:0:0:0:0:1").matches(request));
        }
        log.debug(ipaddr + " status = " + status + " request " + SipkdHelpers.getIpAddr(request));
        //true: dibuka semua, status: sesuai IP/user admin
        if (status) {
            return "sp2d/pajaktransfer/indextransfercetak";
        } else {
            // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Anda tidak berhak mengakses halaman pengesahan");
            return "deny";
        }
    }

    @RequestMapping(value = {"/popupkonfirmasi"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String indexlistsekolah(HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "sp2d/pajaktransfer/popupkonfirmasi";
    }

    @RequestMapping(value = {"/json/getlistindex"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> getlistspmcetak(HttpServletRequest request) {
        Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        String kodewil = pengguna.getKodeProses();
        String tahunAnggaran = request.getParameter("tahun");
        String tglsah = request.getParameter("tglsah");
        String kodeskpdfilter = request.getParameter("kodeskpdfilter");
        String namaskpdfilter = request.getParameter("namaskpdfilter");
        String nosp2dfilter = request.getParameter("nosp2dfilter");

        Map<String, Object> param = new HashMap(2);
        Integer offset = Integer.valueOf(request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")).intValue() : 0);
        Integer limit = Integer.valueOf(request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")).intValue() : 0);
        Integer iSortCol_0 = Integer.valueOf(request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")).intValue() : 0);
        String sSortDir_0 = request.getParameter("sSortDir_0");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("wilayah", kodewil);
        param.put("tglsah", tglsah);
        param.put("kodeskpdfilter", kodeskpdfilter);
        param.put("namaskpdfilter", namaskpdfilter);
        param.put("nosp2dfilter", nosp2dfilter);
        Map<String, Object> mapData = new HashMap(4);
        int banyak = sp2dServices.getBanyakListIndex(param).intValue();
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", Integer.valueOf(banyak));
        mapData.put("iTotalDisplayRecords", Integer.valueOf(banyak));
        mapData.put("aaData", sp2dServices.getListIndex(param));
        return mapData;
    }

    @RequestMapping(value = {"/json/getKodeStan"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> getKodeStan(HttpServletRequest request) {
        Map<String, Object> mapData = new HashMap(4);
        mapData.put("aData", sp2dServices.getKodeStan());

        return mapData;
    }

    @RequestMapping(value = {"/json/getPajak"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public Sp2dPajakTransfer getPajak(HttpServletRequest request) {
        Map<String, Object> param = new HashMap(2);

        String idspmpot = request.getParameter("idspmpot");
        param.put("idspmpot", idspmpot);
        return sp2dServices.getPajak(param);
    }

    @RequestMapping(value = {"/json/updateInquiry"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public void updateInquiry(HttpServletRequest request) {
        Map<String, Object> param = new HashMap(2);
        String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        String idapp = request.getParameter("idapp");
        String npwprekanan = request.getParameter("npwprekanan");
        String namawp = request.getParameter("namawp");
        String alamatwp = request.getParameter("alamatwp");
        String kodebilling = request.getParameter("kodebilling");
        String map = request.getParameter("map");
        String kjs = request.getParameter("kjs");
        String masapajak = request.getParameter("masapajak");
        param.put("tahun", tahunAnggaran);
        param.put("idapp", idapp);
        param.put("npwprekanan", npwprekanan);
        param.put("namawp", namawp);
        param.put("alamatwp", alamatwp);
        param.put("kodebilling", kodebilling);
        param.put("map", map);
        param.put("kjs", kjs);
        param.put("masapajak", masapajak);

        sp2dServices.updateInquiry(param);
    }

    @RequestMapping(value = {"/json/inquirynpwpdjp"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    public Object inquirynpwpdjp(@RequestBody Map<String, String> mapdata, HttpServletRequest request) {
        Object returnObject;
        try {
            String text = (String) SipkdHelpers.getDjpService(servletContext).get(0);
            String[] textSplited = text.split("\\|");
            String link = textSplited[0];
            String username = textSplited[1];
            String password = textSplited[2];
            JSONObject data = new JSONObject();
            JSONObject header = new JSONObject();
            JSONArray body = new JSONArray();
            JSONObject bodyElement = new JSONObject();

            String idchannel = "10";
            String bulkidrequest = sp2dServices.getGeneratedId("BULKIDREQUEST");

            String idrequest = sp2dServices.getGeneratedId("IDREQUEST");
            String taxid = (String) mapdata.get("npwp");
            String typeoftax = (String) mapdata.get("akun");
            String subtypeoftax = (String) mapdata.get("kjs");
            String idapp = (String) mapdata.get("idapp");
            String kodeapp = "001";

            String url = link;
            header.put("idchannel", "10");
            header.put("bulkidrequest", bulkidrequest);

            bodyElement.put("idrequest", idrequest);
            bodyElement.put("taxid", taxid);
            bodyElement.put("typeoftax", typeoftax);
            bodyElement.put("subtypeoftax", subtypeoftax);
            bodyElement.put("idapp", idapp);
            bodyElement.put("kodeapp", "001");

            body.put(0, bodyElement);
            data.put("header", header);
            data.put("data", body);
            log.debug("Data 01 (Link) - " + link);
            log.debug("Data 02 (Request) - " + data.toString());
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes()));

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(data.toString());

            wr.flush();
            wr.close();
            log.debug("Output : " + con.getOutputStream());
            log.debug("Response Code : " + con.getResponseCode());
            log.debug("Response Message : " + con.getResponseMessage());
            log.debug("Error Stream : " + con.getErrorStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String c;
            while ((c = reader.readLine()) != null) {
                sb.append(c);
            }

            log.debug("Data 03 (Response) - " + sb.toString());

            JSONObject json = new JSONObject(sb.toString());
            JSONArray dataArray = json.getJSONArray("data");
            JSONObject dataArray2 = json.getJSONObject("header");
            log.debug("Data 04 (Response Header) - " + dataArray2.toString());
            log.debug("Data 05 (Response Body) - " + dataArray.toString());

            Map<Object, Object> returnData = new HashMap();

            Map<String, String> dataElement = new HashMap();
            Iterator<String> nameItr2 = dataArray2.keys();

            while (nameItr2.hasNext()) {
                String name2 = (String) nameItr2.next();
                dataElement.put(name2, dataArray2.getString(name2));
            }

            Iterator<String> nameItr = ((JSONObject) dataArray.get(0)).keys();
            while (nameItr.hasNext()) {
                String name = (String) nameItr.next();
                dataElement.put(name, ((JSONObject) dataArray.get(0)).getString(name));
            }

            reader.close();
            con.disconnect();
            log.debug("Response JSON - " + json.toString());
            returnObject = dataElement;
        } catch (Exception ex) {
            returnObject = ex.getMessage();
            log.debug("ERROR - " + ex.getMessage());
            log.debug("ERROR - " + ex.getStackTrace());
        }
        return returnObject;
    }

    @RequestMapping(value = {"/json/ssptes"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    public Object ssptes(@RequestBody Map<String, String> mapdata, HttpServletRequest request) {
        Object returnObject;
        try {
            String text = (String) SipkdHelpers.getDjpService(servletContext).get(1);
            String[] textSplited = text.split("\\|");
            String link = textSplited[0];
            String username = textSplited[1];
            String password = textSplited[2];
            JSONObject data = new JSONObject();
            JSONObject header = new JSONObject();
            JSONArray body = new JSONArray();
            JSONObject bodyElement = new JSONObject();

            String idchannel = "10";
            String bulkidrequest = (String) mapdata.get("bulkid");
            log.debug("Racka1 : " + text);
            log.debug("Racka2 : " + link);
            log.debug("Racka3 : " + password);
            log.debug("BULK ID REQUEST =============== " + bulkidrequest);

            String idrequest = (String) mapdata.get("idrequest");
            String taxid = (String) mapdata.get("npwprekanan");
            String taxpayername = (String) mapdata.get("namawp");
            String debitaccountno = (String) mapdata.get("norek");
            String taxpayeraddress = (String) mapdata.get("alamatwp");
            String taxpayercity = (String) mapdata.get("kotawp");
            String typeoftax = (String) mapdata.get("akupajak");
            String subtypeoftax = (String) mapdata.get("kjs");
            String taxperiod = (String) mapdata.get("masapajak");
            String numberofprovisionletter = (String) mapdata.get("nosk");
            String numberoftaxobject = (String) mapdata.get("nop");
            String identitynumber = (String) mapdata.get("noidentitas");
            String paymentamount = (String) mapdata.get("nilaipajak");
            String taxpayerid = (String) mapdata.get("npwppenyetor");
            String taxpayeridname = (String) mapdata.get("namapenyetor");
            String paymentdescription = (String) mapdata.get("uraian");
            String idapp = (String) mapdata.get("idspmpotformat");
            String kodeapp = "001";

            String url = link;
            header.put("idchannel", "10");
            header.put("bulkidrequest", bulkidrequest);

            bodyElement.put("idrequest", idrequest);
            bodyElement.put("taxid", taxid);
            bodyElement.put("taxpayername", taxpayername);
            bodyElement.put("taxpayeraddress", taxpayeraddress);
            bodyElement.put("taxpayercity", taxpayercity);
            bodyElement.put("typeoftax", typeoftax);
            bodyElement.put("subtypeoftax", subtypeoftax);
            bodyElement.put("taxperiod", taxperiod);
            bodyElement.put("numberofprovisionletter", numberofprovisionletter);
            bodyElement.put("numberoftaxobject", numberoftaxobject);
            bodyElement.put("identitynumber", identitynumber);
            bodyElement.put("paymentamount", paymentamount);
            bodyElement.put("taxpayerid", taxpayerid);
            bodyElement.put("taxpayeridname", taxpayeridname);
            bodyElement.put("paymentdescription", paymentdescription);
            bodyElement.put("debitaccountno", debitaccountno);
            bodyElement.put("idapp", idapp);
            bodyElement.put("kodeapp", "001");

            body.put(0, bodyElement);
            data.put("header", header);
            data.put("data", body);
            log.debug("Data - " + data.toString());
            log.debug("Bulk dan Id - " + bulkidrequest + " - " + idrequest);

            String sb = "{\"header\":{\"idchannel\":\"10\",\"bulkidrequest\":\"" + bulkidrequest + "\"},\"data\":[{\"idrequest\":\"" + idrequest + "\",\"idresponse\":\"091710361498239\",\"debitaccountno\":\"000000012345\",\"taxid\":\"010016293051000\",\"taxpayername\":\"nama npwp\",\"taxpayeraddress\":\"alamat\",\"taxpayercity\":\"jakarta\",\"typeoftax\":\"411121\",\"subtypeoftax\":\"111\",\"typeoftaxdescription\":\"map\",\"subtypeoftaxdescription\":\"kode jenis setoran\",\"taxperiod\":\"10102018\",\"numberofprovisionletter\":\"000000000000000\",\"numberoftaxobject\":\"000000000000000000\",\"identitynumber\":\"0000000000000000\",\"paymentamount\":\"200000\",\"taxpayerid\":\"100000890092000\",\"taxpayeridname\":\"kantor kas daerah pemprov dki\",\"paymentdescription\":\"sp2d 210\",\"billingccy\":\"IDR\",\"branchcode\":\"000101\",\"processingcode\":\"400100\",\"responsecode\":\"00\",\"responsecodedescription\":\"Pembuatan Kode Billing Berhasil\",\"billingcode\":\"000000000003207\",\"expirydate\":\"20181010121212\",\"stan\":\"400014\",\"datetimeofpayment\":\"20181010121212\",\"datetimeoftransmission\":\"20181010121212\",\"settlementdate\":\"20180905\",\"ntbnumber\":\"723614982712\",\"ntpnnumber\":\"0000000000000000\",\"bpnstatus\":\"3\"}]}";

            log.debug("Response - " + sb.toString());

            JSONObject json = new JSONObject(sb.toString());
            JSONArray dataArray = json.getJSONArray("data");
            JSONObject dataArray2 = json.getJSONObject("header");
            Map<Object, Object> returnData = new HashMap();

            int count = 0;

            Map<String, String> dataElement = new HashMap();
            Iterator<String> nameItr2 = dataArray2.keys();

            while (nameItr2.hasNext()) {
                String name2 = (String) nameItr2.next();
                dataElement.put(name2, dataArray2.getString(name2));
            }

            Iterator<String> nameItr = ((JSONObject) dataArray.get(0)).keys();
            while (nameItr.hasNext()) {
                String name = (String) nameItr.next();
                dataElement.put(name, ((JSONObject) dataArray.get(0)).getString(name));
            }

            log.debug("Response JSON - " + json.toString());
            returnObject = dataElement;
        } catch (Exception ex) {
            returnObject = ex.getMessage();
            log.debug("ERROR - " + ex.getMessage());
            log.debug("ERROR - " + ex.getStackTrace());
        }
        return returnObject;
    }

    @RequestMapping(value = {"/json/createbilling"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    public Object ssp(@RequestBody Map<String, String> mapdata, HttpServletRequest request) {
        Object returnObject;
        Sp2dPajakJson sp2dPajakJson = new Sp2dPajakJson();
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        String action = "BILLING";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
//        Date requestDate = new Date(System.currentTimeMillis());
        Timestamp requestDate = new Timestamp(System.currentTimeMillis());
        try {
            String text = (String) SipkdHelpers.getDjpService(servletContext).get(1);
            String[] textSplited = text.split("\\|");
            String link = textSplited[0];
            String username = textSplited[1];
            String password = textSplited[2];
            JSONObject data = new JSONObject();
            JSONObject header = new JSONObject();
            JSONArray body = new JSONArray();
            JSONObject bodyElement = new JSONObject();

            //String idchannel = "10";
            String bulkidrequest = (String) mapdata.get("bulkid");
            log.debug("LINK PASS : " + text + " " + link + " " + password);

            log.debug("BULK ID REQUEST =============== " + bulkidrequest);

            String idrequest = (String) mapdata.get("idrequest");
            String taxid = (String) mapdata.get("npwprekanan");
            String taxpayername = (String) mapdata.get("namawp");
            String debitaccountno = (String) mapdata.get("norek");
            String taxpayeraddress = (String) mapdata.get("alamatwp");
            String taxpayercity = (String) mapdata.get("kotawp");
            String typeoftax = (String) mapdata.get("akupajak");
            String subtypeoftax = (String) mapdata.get("kjs");
            String taxperiod = (String) mapdata.get("masapajak");
            String numberofprovisionletter = (String) mapdata.get("nosk");
            String numberoftaxobject = (String) mapdata.get("nop");
            String identitynumber = (String) mapdata.get("noidentitas");
            String paymentamount = (String) mapdata.get("nilaipajak");
            String taxpayerid = (String) mapdata.get("npwppenyetor");
            String taxpayeridname = (String) mapdata.get("namapenyetor");
            String paymentdescription = (String) mapdata.get("uraian");
            String idapp = (String) mapdata.get("idspmpotformat");
            String kodeapp = "001";

            String url = link;
            header.put("idchannel", "10");
            header.put("bulkidrequest", bulkidrequest);

            bodyElement.put("idrequest", idrequest);
            bodyElement.put("taxid", taxid);
            bodyElement.put("taxpayername", taxpayername);
            bodyElement.put("taxpayeraddress", taxpayeraddress);
            bodyElement.put("taxpayercity", taxpayercity);
            bodyElement.put("typeoftax", typeoftax);
            bodyElement.put("subtypeoftax", subtypeoftax);
            bodyElement.put("taxperiod", taxperiod);
            bodyElement.put("numberofprovisionletter", numberofprovisionletter);
            bodyElement.put("numberoftaxobject", numberoftaxobject);
            bodyElement.put("identitynumber", identitynumber);
            bodyElement.put("paymentamount", paymentamount);
            bodyElement.put("taxpayerid", taxpayerid);
            bodyElement.put("taxpayeridname", taxpayeridname);
            bodyElement.put("paymentdescription", paymentdescription);
            bodyElement.put("debitaccountno", debitaccountno);
            bodyElement.put("idapp", idapp);
            bodyElement.put("kodeapp", "001");

            body.put(0, bodyElement);
            data.put("header", header);
            data.put("data", body);
            log.debug("Data - " + data.toString());
            sp2dPajakJson.setIdApp(idapp);
            sp2dPajakJson.setKodeApp(kodeapp);
            sp2dPajakJson.setTahun(tahun);
            sp2dPajakJson.setJsonRequest(data.toString());
            sp2dPajakJson.setKodeAction(action);
//            sp2dPajakJson.setTimeResponse(dateFormat.format(requestDate).toString());
            sp2dPajakJson.setTimeRequest(requestDate);
            //String reqTimestamp = dateFormat.format(requestDate).toString();
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes()));

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

            wr.write(data.toString());

            //to_timestamp(reqTimestamp, 'H24:mm:ss.FF')
            wr.flush();
            wr.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String c;
            while ((c = reader.readLine()) != null) {
                sb.append(c);
            }
//            Date responseDate = new Date(System.currentTimeMillis());
            Timestamp responseDate = new Timestamp(System.currentTimeMillis());
            log.debug("Response - " + sb.toString());

            JSONObject json = new JSONObject(sb.toString());
            JSONArray dataArray = json.getJSONArray("data");
            JSONObject dataResponseHeader = json.getJSONObject("header");
            //Map<Object, Object> returnData = new HashMap();

            JSONObject dataResponseBody = (JSONObject) dataArray.get(0);

            JSONObject dataRequestBody = bodyElement;

            dataResponseBody = sp2dServices.validateJson(dataRequestBody, dataResponseBody, action);
            sp2dPajakJson.setJsonResponse(json.toString());
//            sp2dPajakJson.setTimeResponse(dateFormat.format(responseDate).toString());
            sp2dPajakJson.setTimeResponse(responseDate);
            if (dataResponseBody.has("error")) {
                sp2dPajakJson.setError(dataResponseBody.getString("error"));
            }
            sp2dServices.insertJson(sp2dPajakJson);

            int count = 0;

            Map<String, String> dataElement = new HashMap();
            Iterator<String> nameItr2 = dataResponseHeader.keys();

            while (nameItr2.hasNext()) {
                String name2 = (String) nameItr2.next();
                dataElement.put(name2, dataResponseHeader.getString(name2));
            }

            Iterator<String> nameItr = dataResponseBody.keys();
            while (nameItr.hasNext()) {
                String name = (String) nameItr.next();
                if (((JSONObject) dataArray.get(0)).get(name).equals(null)) {
                    dataElement.put(name, "-");
                } else {
                    dataElement.put(name, dataResponseBody.getString(name));
                }
            }

            reader.close();
            con.disconnect();
            log.debug("Response JSON - " + json.toString());

            returnObject = dataElement;
        } catch (Exception ex) {
            returnObject = ex.getMessage().toString();
            log.debug("ERROR - " + ex.getMessage());
            log.debug("ERROR - " + ex.getStackTrace());
        }
        return returnObject;
    }

    @RequestMapping(value = {"/json/paymentbilling"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    public Object billing(@RequestBody Map<String, String> mapdata, HttpServletRequest request) {
        Object returnObject;
        Sp2dPajakJson sp2dPajakJson = new Sp2dPajakJson();
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        String action = "PAYMENT";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
//        Date requestDate = new Date(System.currentTimeMillis());
        Timestamp requestDate = new Timestamp(System.currentTimeMillis());
        try {
            String text = (String) SipkdHelpers.getDjpService(servletContext).get(2);
            String[] textSplited = text.split("\\|");
            String link = textSplited[0];
            String username = textSplited[1];
            String password = textSplited[2];
            JSONObject data = new JSONObject();
            JSONObject header = new JSONObject();
            JSONArray body = new JSONArray();
            JSONObject bodyElement = new JSONObject();

            String idchannel = "10";
            String bulkidrequest = (String) mapdata.get("bulkid");

            log.debug("BULK ID REQUEST =============== " + bulkidrequest);

            String idrequest = (String) mapdata.get("idrequest");
            String debitaccountno = (String) mapdata.get("norek");
            String billingcode = (String) mapdata.get("kodebilling");
            String taxpayerid = (String) mapdata.get("npwppenyetor");
            String taxpayeridname = (String) mapdata.get("namapenyetor");
            String paymentdescription = (String) mapdata.get("uraian");
            String idapp = (String) mapdata.get("idapp");
            String kodeapp = "001";
            String kjs = (String) mapdata.get("kjs");
            String map = (String) mapdata.get("map");
            String npwp = (String) mapdata.get("npwp");

            String url = link;
            header.put("idchannel", "10");
            header.put("bulkidrequest", bulkidrequest);

            bodyElement.put("idrequest", idrequest);
            bodyElement.put("debitaccountno", debitaccountno);
            bodyElement.put("billingcode", billingcode);
            bodyElement.put("taxpayerid", taxpayerid);
            bodyElement.put("taxpayeridname", taxpayeridname);
            bodyElement.put("paymentdescription", paymentdescription);
            bodyElement.put("idapp", idapp);
            bodyElement.put("kodeapp", kodeapp);

            body.put(0, bodyElement);
            data.put("header", header);
            data.put("data", body);
            log.debug("Data - " + data.toString());
            sp2dPajakJson.setIdApp(idapp);
            sp2dPajakJson.setKodeApp(kodeapp);
            sp2dPajakJson.setTahun(tahun);
            sp2dPajakJson.setJsonRequest(data.toString());
            sp2dPajakJson.setKodeAction(action);
//            sp2dPajakJson.setTimeRequest(dateFormat.format(requestDate).toString());
            sp2dPajakJson.setTimeRequest(requestDate);

            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes()));

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(data.toString());

            wr.flush();
            wr.close();
            log.debug("Output : " + con.getOutputStream());
            log.debug("Response Code : " + con.getResponseCode());
            log.debug("Response Message : " + con.getResponseMessage());
            log.debug("Error Stream : " + con.getErrorStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String c;
            while ((c = reader.readLine()) != null) {
                sb.append(c);
            }
//            Date responseDate = new Date(System.currentTimeMillis());
            Timestamp responseDate = new Timestamp(System.currentTimeMillis());
            log.debug("Response - " + sb.toString());

            JSONObject json = new JSONObject(sb.toString());
            JSONArray dataArray = json.getJSONArray("data");
            JSONObject dataResponseHeader = json.getJSONObject("header");
            //Map<Object, Object> returnData = new HashMap();
            JSONObject dataResponseBody = (JSONObject) dataArray.get(0);

            JSONObject dataRequestBody = bodyElement;
            dataRequestBody.put("taxid", npwp);
            dataRequestBody.put("typeoftax", map);
            dataRequestBody.put("subtypeoftax", kjs);

            dataResponseBody = sp2dServices.validateJson(dataRequestBody, dataResponseBody, action);
            sp2dPajakJson.setJsonResponse(json.toString());
//            sp2dPajakJson.setTimeResponse(dateFormat.format(responseDate).toString());
            sp2dPajakJson.setTimeResponse(responseDate);
            if (dataResponseBody.has("error")) {
                sp2dPajakJson.setError(dataResponseBody.getString("error"));
            }
            sp2dServices.insertJson(sp2dPajakJson);

            Map<String, String> dataElement = new HashMap();
            Iterator<String> nameItr2 = dataResponseHeader.keys();

            while (nameItr2.hasNext()) {
                String name2 = (String) nameItr2.next();
                dataElement.put(name2, dataResponseHeader.getString(name2));
            }

            Iterator<String> nameItr = dataResponseBody.keys();
            while (nameItr.hasNext()) {
                String name = (String) nameItr.next();
                if (dataResponseBody.get(name).equals(null)) {
                    dataElement.put(name, "-");
                } else {
                    dataElement.put(name, dataResponseBody.getString(name));
                }
            }

            reader.close();
            con.disconnect();
            log.debug("Response JSON - " + json.toString());

            returnObject = dataElement;
        } catch (Exception ex) {
            returnObject = ex.getMessage().toString();
            log.debug("ERROR - " + ex.getMessage());
            log.debug("ERROR - " + ex.getStackTrace());
        }
        return returnObject;
    }

    @RequestMapping(value = {"/json/reinquirybilling"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    public Object reinquirybilling(@RequestBody Map<String, String> mapdata, HttpServletRequest request) {
        Object returnObject;
        Sp2dPajakJson sp2dPajakJson = new Sp2dPajakJson();
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        String action = "REINQUIRY";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
//        Date requestDate = new Date(System.currentTimeMillis());
        Timestamp requestDate = new Timestamp(System.currentTimeMillis());
        try {
            String text = (String) SipkdHelpers.getDjpService(servletContext).get(4);
            String[] textSplited = text.split("\\|");
            String link = textSplited[0];
            String username = textSplited[1];
            String password = textSplited[2];
            JSONObject data = new JSONObject();
            JSONObject header = new JSONObject();
            JSONArray body = new JSONArray();
            JSONObject bodyElement = new JSONObject();

            String idchannel = "10";
            String bulkidrequest = (String) mapdata.get("bulkid");

            log.debug("BULK ID REQUEST =============== " + bulkidrequest);

            String idrequest = (String) mapdata.get("idrequest");
            String billingcode = (String) mapdata.get("kodebilling");
            String ntbnumber = (String) mapdata.get("ntb");
            String idapp = (String) mapdata.get("idapp");
            String kodeapp = "001";

            String url = link;
            header.put("idchannel", "10");
            header.put("bulkidrequest", bulkidrequest);

            bodyElement.put("idrequest", idrequest);
            bodyElement.put("billingcode", billingcode);
            bodyElement.put("ntbnumber", ntbnumber);
            bodyElement.put("idapp", idapp);
            bodyElement.put("kodeapp", "001");

            body.put(0, bodyElement);
            data.put("header", header);
            data.put("data", body);
            log.debug("Data - " + data.toString());
            sp2dPajakJson.setIdApp(idapp);
            sp2dPajakJson.setKodeApp(kodeapp);
            sp2dPajakJson.setTahun(tahun);
            sp2dPajakJson.setJsonRequest(data.toString());
            sp2dPajakJson.setKodeAction(action);
//            sp2dPajakJson.setTimeRequest(dateFormat.format(requestDate).toString());
            sp2dPajakJson.setTimeRequest(requestDate);
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes()));

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(data.toString());

            wr.flush();
            wr.close();
            log.debug("Output : " + con.getOutputStream());
            log.debug("Response Code : " + con.getResponseCode());
            log.debug("Response Message : " + con.getResponseMessage());
            log.debug("Error Stream : " + con.getErrorStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String c;
            while ((c = reader.readLine()) != null) {
                sb.append(c);
            }
//            Date responseDate = new Date(System.currentTimeMillis());
            Timestamp responseDate = new Timestamp(System.currentTimeMillis());

            JSONObject json = new JSONObject(sb.toString());
            JSONArray dataArray = json.getJSONArray("data");
            JSONObject dataResponseHeader = json.getJSONObject("header");
            Map<Object, Object> returnData = new HashMap();
            sp2dPajakJson.setJsonResponse(json.toString());
//            sp2dPajakJson.setTimeResponse(dateFormat.format(responseDate).toString());
            sp2dPajakJson.setTimeResponse(responseDate);
            sp2dServices.insertJson(sp2dPajakJson);
            
            Map<String, String> dataElement = new HashMap();
            Iterator<String> nameItr2 = dataResponseHeader.keys();

            while (nameItr2.hasNext()) {
                String name2 = (String) nameItr2.next();
                dataElement.put(name2, dataResponseHeader.getString(name2));
            }

            Iterator<String> nameItr = ((JSONObject) dataArray.get(0)).keys();
            while (nameItr.hasNext()) {
                String name = (String) nameItr.next();
                if (((JSONObject) dataArray.get(0)).get(name).equals(null)) {
                    dataElement.put(name, "-");
                } else {
                    dataElement.put(name, ((JSONObject) dataArray.get(0)).getString(name));
                }
            }

            reader.close();
            con.disconnect();

            returnObject = dataElement;
        } catch (Exception ex) {
            returnObject = ex.getMessage().toString();
            log.debug("ERROR - " + ex.getMessage());
            log.debug("ERROR - " + ex.getStackTrace());
        }
        return returnObject;
    }

    @RequestMapping(value = {"/json/inquirybilling"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    public Object inquirybilling(@RequestBody Map<String, String> mapdata, HttpServletRequest request) {
        Object returnObject;
        Sp2dPajakJson sp2dPajakJson = new Sp2dPajakJson();
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        String action = "INQUIRY";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
//        Date requestDate = new Date(System.currentTimeMillis());
        Timestamp requestDate = new Timestamp(System.currentTimeMillis());
        try {
            String text = (String) SipkdHelpers.getDjpService(servletContext).get(3);
            String[] textSplited = text.split("\\|");
            String link = textSplited[0];
            String username = textSplited[1];
            String password = textSplited[2];
            JSONObject data = new JSONObject();
            JSONObject header = new JSONObject();
            JSONArray body = new JSONArray();
            JSONObject bodyElement = new JSONObject();

            String idchannel = "10";
            String bulkidrequest = sp2dServices.getGeneratedId("BULKIDREQUEST");

            String idrequest = sp2dServices.getGeneratedId("IDREQUEST");
            String billingcode = (String) mapdata.get("billingcode");
            String idapp = (String) mapdata.get("idapp");
            String subtypeoftax = (String) mapdata.get("subtypeoftax");
            String typeoftax = (String) mapdata.get("typeoftax");
            String taxid = (String) mapdata.get("taxid");
            //String taxpayerid = (String) mapdata.get("taxpayerid");
            String paymentamount = (String) mapdata.get("paymentamount");
            String kodeapp = "001";

            String url = link;
            header.put("idchannel", idchannel);
            header.put("bulkidrequest", bulkidrequest);

            bodyElement.put("idrequest", idrequest);
            bodyElement.put("billingcode", billingcode);
            bodyElement.put("idapp", idapp);
            bodyElement.put("kodeapp", kodeapp);

            body.put(0, bodyElement);
            data.put("header", header);
            data.put("data", body);
            log.debug("Data - " + data.toString());
            sp2dPajakJson.setIdApp(idapp);
            sp2dPajakJson.setKodeApp(kodeapp);
            sp2dPajakJson.setTahun(tahun);
            sp2dPajakJson.setJsonRequest(data.toString());
            sp2dPajakJson.setKodeAction(action);
//            sp2dPajakJson.setTimeRequest(dateFormat.format(requestDate).toString());
            sp2dPajakJson.setTimeRequest(requestDate);
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes()));

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(data.toString());

            wr.flush();
            wr.close();
            log.debug("Output : " + con.getOutputStream());
            log.debug("Response Code : " + con.getResponseCode());
            log.debug("Response Message : " + con.getResponseMessage());
            log.debug("Error Stream : " + con.getErrorStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String c;
            while ((c = reader.readLine()) != null) {
                sb.append(c);
            }
//            Date responseDate = new Date(System.currentTimeMillis());
            Timestamp responseDate = new Timestamp(System.currentTimeMillis());

            log.debug("Response - " + sb.toString());

            JSONObject json = new JSONObject(sb.toString());
            JSONArray dataArray = json.getJSONArray("data");
            JSONObject dataResponseHeader = json.getJSONObject("header");
            Map<Object, Object> returnData = new HashMap();
            JSONObject dataResponseBody = (JSONObject) dataArray.get(0);
            JSONObject dataRequestBody = bodyElement;
            dataRequestBody.put("paymentamount", paymentamount);
            dataRequestBody.put("subtypeoftax", subtypeoftax);
            dataRequestBody.put("typeoftax", typeoftax);
            dataRequestBody.put("taxid", taxid);
            //dataRequestBody.put("taxpayerid", taxpayerid);
            
            dataResponseBody = sp2dServices.validateJson(dataRequestBody, dataResponseBody, action);
            sp2dPajakJson.setJsonResponse(json.toString());
//            sp2dPajakJson.setTimeResponse(dateFormat.format(responseDate).toString());
            sp2dPajakJson.setTimeResponse(responseDate);
            if (dataResponseBody.has("error")) {
                sp2dPajakJson.setError(dataResponseBody.getString("error"));
            }
            sp2dServices.insertJson(sp2dPajakJson);
            
            Map<String, String> dataElement = new HashMap();
            Iterator<String> nameItr2 = dataResponseHeader.keys();

            while (nameItr2.hasNext()) {
                String name2 = (String) nameItr2.next();
                dataElement.put(name2, dataResponseHeader.getString(name2));
            }

            Iterator<String> nameItr = ((JSONObject) dataArray.get(0)).keys();
            while (nameItr.hasNext()) {
                String name = (String) nameItr.next();
                if (((JSONObject) dataArray.get(0)).get(name).equals(null)) {
                    dataElement.put(name, "-");
                } else {
                    dataElement.put(name, ((JSONObject) dataArray.get(0)).getString(name));
                }
            }

            reader.close();
            con.disconnect();
            log.debug("Response JSON - " + json.toString());

            returnObject = dataElement;
        } catch (Exception ex) {
            log.debug("ERROR - " + ex.getMessage());
            log.debug("ERROR - " + ex.getStackTrace());
            returnObject = ex.getMessage().toString();
        }
        return returnObject;
    }

    @RequestMapping(value = {"/json/simpandjpbank"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> simpandjpbank(@RequestBody Map<String, Object> mapdata, HttpServletRequest request) {
        Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        String tahun = (String) mapdata.get("tahun");
        String idsp2d = (String) mapdata.get("idsp2d");
        String idspmpot = (String) mapdata.get("idspmpot");
        String kodepotspm = (String) mapdata.get("kodepotspm");
        String kodetrx = (String) mapdata.get("kodetrx");
        String norek = (String) mapdata.get("norek");
        String npwprekanan = (String) mapdata.get("npwprekanan");
        String namawp = (String) mapdata.get("namawp");
        String alamatwp = (String) mapdata.get("alamatwp");
        String kotawp = (String) mapdata.get("kotawp");
        String kjs = (String) mapdata.get("kjs");
        String akupajak = (String) mapdata.get("akupajak");
        String masapajak = (String) mapdata.get("masapajak");
        String tahunpajak = (String) mapdata.get("tahunpajak");
        String nosk = (String) mapdata.get("nosk");
        String nop = (String) mapdata.get("nop");
        String noidentitas = (String) mapdata.get("noidentitas");
        String nilaipajak = (String) mapdata.get("nilaipajak");
        String npwppenyetor = (String) mapdata.get("npwppenyetor");
        String namapenyetor = (String) mapdata.get("namapenyetor");
        String uraian = (String) mapdata.get("uraian");
        String koderequest = (String) mapdata.get("koderequest");
        String kodeapp = (String) mapdata.get("kodeapp");
        String idrequest = sp2dServices.getGeneratedId("IDREQUEST");
        String bulkid = sp2dServices.getGeneratedId("BULKIDREQUEST");

        Sp2dPajakTransfer sp2d = new Sp2dPajakTransfer();

        sp2d.setTahun(tahun);
        sp2d.setIdSp2d(Integer.valueOf(SipkdHelpers.getIntFromString(idsp2d.toString())));
        sp2d.setIdSpmPot(Integer.valueOf(SipkdHelpers.getIntFromString(idspmpot.toString())));
        sp2d.setKodePotSpm(kodepotspm);
        sp2d.setKodeTrx(kodetrx);
        sp2d.setNoRekening(norek);
        sp2d.setNpwpRekanan(npwprekanan);
        sp2d.setNamaWp(namawp);
        sp2d.setAlamatWp(alamatwp);
        sp2d.setKotaWp(kotawp);
        sp2d.setKodeJenisSetor(kjs);
        sp2d.setAkunPajak(akupajak);
        sp2d.setMasaPajak(masapajak);
        sp2d.setTahunPajak(tahunpajak);
        sp2d.setNoSk(nosk);
        sp2d.setNop(nop);
        sp2d.setNoIdentitas(noidentitas);
        sp2d.setNilaiPajak(SipkdHelpers.getBigDecimalFromString(nilaipajak));
        sp2d.setNpwpPenyetor(npwppenyetor);
        sp2d.setNamaPenyetor(namapenyetor);
        sp2d.setUraianPajak(uraian);
        sp2d.setKodeRequest(koderequest);
        sp2d.setKodeApp(kodeapp);
        sp2d.setBulkIdRequest(bulkid);
        sp2d.setIdRequest(idrequest);
        sp2d.setIdChannel("10");

        if ("1".equals(koderequest)) { //jika create kode billing maka insert
            sp2dServices.insertPajakBank(sp2d);
        }

        Map<String, Object> data = new HashMap(4);

        data.put("idrequest", idrequest);
        data.put("bulkid", bulkid);

        return data;
    }

    @RequestMapping(value = {"/json/updatedjpbank"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String updatedjpbank(@RequestBody Map<String, Object> mapdata, HttpServletRequest request) {
        Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        String tahun = (String) mapdata.get("tahun");
        String idsp2d = (String) mapdata.get("idsp2d");
        String idspmpot = (String) mapdata.get("idspmpot");
        String bulkidreq = (String) mapdata.get("bulkidreq");
        String idrequest = (String) mapdata.get("idrequest");
        String idresponse = (String) mapdata.get("idresponse");
        String norek = (String) mapdata.get("norek");
        String npwprekanan = (String) mapdata.get("npwprekanan");
        String namawp = (String) mapdata.get("namawp");
        String alamatwp = (String) mapdata.get("alamatwp");
        String kotawp = (String) mapdata.get("kotawp");
        String kjs = (String) mapdata.get("kjs");
        String akupajak = (String) mapdata.get("akupajak");
        String namakjs = (String) mapdata.get("namakjs");
        String namaakunpajak = (String) mapdata.get("namaakunpajak");
        String masapajak = (String) mapdata.get("masapajak");
        String nosk = (String) mapdata.get("nosk");
        String nop = (String) mapdata.get("nop");
        String noidentitas = (String) mapdata.get("noidentitas");
        String nilaipajak = (String) mapdata.get("nilaipajak");
        String npwppenyetor = (String) mapdata.get("npwppenyetor");
        String namapenyetor = (String) mapdata.get("namapenyetor");
        String uraian = (String) mapdata.get("uraian");
        String kodeproses = (String) mapdata.get("kodeproses");
        String koderesponse = (String) mapdata.get("koderesponse");
        String uraianresponse = (String) mapdata.get("uraianresponse");
        String kodebilling = (String) mapdata.get("kodebilling");
        String tglbillexp = (String) mapdata.get("tglbillexp");
        String kodestan = (String) mapdata.get("kodestan");
        String tglbayar = (String) mapdata.get("tglbayar");
        String tgltransmisi = (String) mapdata.get("tgltransmisi");
        String tglbuku = (String) mapdata.get("tglbuku");
        String ntb = (String) mapdata.get("ntb");
        String ntpn = (String) mapdata.get("ntpn");
        String statusbpn = (String) mapdata.get("statusbpn");
        String koderequest = (String) mapdata.get("koderequest");

        Sp2dPajakTransfer sp2d = new Sp2dPajakTransfer();

        sp2d.setTahun(tahun);
        sp2d.setIdSp2d(Integer.valueOf(SipkdHelpers.getIntFromString(idsp2d.toString())));
        sp2d.setIdSpmPot(Integer.valueOf(SipkdHelpers.getIntFromString(idspmpot.toString())));
        sp2d.setBulkIdRequest(bulkidreq);
        sp2d.setIdRequest(idrequest);
        sp2d.setIdResponse(idresponse);
        sp2d.setNoRekening(norek);
        sp2d.setNpwpRekanan(npwprekanan);
        sp2d.setNamaWp(namawp);
        sp2d.setAlamatWp(alamatwp);
        sp2d.setKotaWp(kotawp);
        sp2d.setKodeJenisSetor(kjs);
        sp2d.setNamaKJS(namakjs);
        sp2d.setNamaMAP(namaakunpajak);
        sp2d.setAkunPajak(akupajak);
        sp2d.setMasaPajak(masapajak);
        sp2d.setNoSk(nosk);
        sp2d.setNop(nop);
        sp2d.setNoIdentitas(noidentitas);
        sp2d.setNilaiPajak(SipkdHelpers.getBigDecimalFromString(nilaipajak));
        sp2d.setNpwpPenyetor(npwppenyetor);
        sp2d.setNamaPenyetor(namapenyetor);
        sp2d.setUraianPajak(uraian);
        sp2d.setKodeProses(kodeproses);
        sp2d.setKodeResponse(koderesponse);
        sp2d.setUraianResponse(uraianresponse);
        sp2d.setKodeBilling(kodebilling);
        sp2d.setTglBillExpString(tglbillexp);
        sp2d.setKodeStan(kodestan);
        sp2d.setTglBayarString(tglbayar);
        sp2d.setTglTransmisiString(tgltransmisi);
        sp2d.setTglBuku(tglbuku);
        sp2d.setNtb(ntb);
        sp2d.setNtpn(ntpn);
        sp2d.setStatusBpn(statusbpn);
        sp2d.setKodeRequest(koderequest);

        if ("1".equals(koderequest)) {
            sp2dServices.updatePajakBankCreateBilling(sp2d);
        } else if ("2".equals(koderequest)) {
            sp2dServices.updatePajakBankPaymentBilling(sp2d);
        } else {
            sp2dServices.updatePajakBankReInquiryBilling(sp2d);
        }
        //sp2dServices.updatePajakBank(sp2d);

        return "Data SP2D Bank Berhasil Diubah";
    }

    @RequestMapping(value = {"/json/updatespmpot"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String updatespmpot(@RequestBody Map<String, Object> mapdata, HttpServletRequest request) {
        Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        String tahun = (String) mapdata.get("tahun");
        String idspmpot = (String) mapdata.get("idspmpot");
        String bulkidreq = (String) mapdata.get("bulkidreq");
        String idrequest = (String) mapdata.get("idrequest");
        String kodebilling = (String) mapdata.get("kodebilling");
        String tglbillexp = (String) mapdata.get("tglbillexp");
        String tglbuku = (String) mapdata.get("tglbuku");
        String statusbpn = (String) mapdata.get("statusbpn");
        String tglbayar = (String) mapdata.get("tglbayar");
        String ntb = (String) mapdata.get("ntb");
        String ntpn = (String) mapdata.get("ntpn");
        String kodestan = (String) mapdata.get("kodestan");
        String koderequest = (String) mapdata.get("koderequest");

        Sp2dPajakTransfer sp2d = new Sp2dPajakTransfer();

        sp2d.setTahun(tahun);
        sp2d.setIdSpmPot(Integer.valueOf(SipkdHelpers.getIntFromString(idspmpot.toString())));
        sp2d.setBulkIdRequest(bulkidreq);
        sp2d.setIdRequest(idrequest);
        sp2d.setKodeBilling(kodebilling);
        sp2d.setTglBillExpString(tglbillexp);
        sp2d.setTglBuku(tglbuku);
        sp2d.setStatusBpn(statusbpn);
        sp2d.setTglBayarString(tglbayar);
        sp2d.setNtb(ntb);
        sp2d.setNtpn(ntpn);
        sp2d.setKodeStan(kodestan);
        sp2d.setKodeRequest(koderequest);

        if ("1".equals(koderequest)) {
            sp2dServices.updateSpmPotCreateBilling(sp2d);
        } else {
            sp2dServices.updateSpmPotPaymentBilling(sp2d);
        }
        //sp2dServices.updateSpmPot(sp2d);

        return "Data Potongan SPM Berhasil Diubah";
    }

    @RequestMapping(value = {"/json/getTglSp2dSah"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> getTglSp2dSah(HttpServletRequest request
    ) {
        Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        String kodewil = pengguna.getKodeProses();

        Map<String, Object> param = new HashMap(3);
        param.put("tahun", tahunAnggaran);
        param.put("kodewil", kodewil);

        Map<String, Object> mapData = new HashMap(4);

        mapData.put("aData", sp2dServices.getTglSp2dSah(param));
        return mapData;
    }

    @RequestMapping(value = {"/json/updatespmpotreinq"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String updatespmpotreinq(@RequestBody Map<String, Object> mapdata, HttpServletRequest request) {
        Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        String tahun = (String) mapdata.get("tahun");
        String idspmpot = (String) mapdata.get("idspmpot");
        String tglbuku = (String) mapdata.get("tglbuku");
        String statusbpn = (String) mapdata.get("statusbpn");
        String tglbayar = (String) mapdata.get("tglbayar");
        String ntb = (String) mapdata.get("ntb");
        String ntpn = (String) mapdata.get("ntpn");
        String npwprekanan = (String) mapdata.get("npwprekanan");
        String namawp = (String) mapdata.get("namawp");
        String alamatwp = (String) mapdata.get("alamatwp");
        String kodestan = (String) mapdata.get("kodestan");
        String masapajak = (String) mapdata.get("masapajak");
        String kodebilling = (String) mapdata.get("kodebilling");

        Sp2dPajakTransfer sp2d = new Sp2dPajakTransfer();

        sp2d.setTahun(tahun);
        sp2d.setIdSpmPot(Integer.valueOf(SipkdHelpers.getIntFromString(idspmpot.toString())));
        sp2d.setTglBuku(tglbuku);
        sp2d.setTglBayarString(tglbayar);
        sp2d.setNtb(ntb);
        sp2d.setNtpn(ntpn);
        sp2d.setStatusBpn(statusbpn);
        sp2d.setKodeStan(kodestan);
        sp2d.setNpwpRekanan(npwprekanan);
        sp2d.setNamaWp(namawp);
        sp2d.setAlamatWp(alamatwp);
        sp2d.setMasaPajak(masapajak);
        sp2d.setKodeBilling(kodebilling);

        sp2dServices.updateSpmPotReInq(sp2d);

        return "Data Potongan SPM Berhasil Diubah";
    }

    @RequestMapping(value = {"/json/getlistindexcetak"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> getlistindexcetak(HttpServletRequest request) {
        Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        String kodewil = pengguna.getKodeProses();
        String tahunAnggaran = request.getParameter("tahun");
        String tglsah = request.getParameter("tglsah");
        String kodeskpdfilter = request.getParameter("kodeskpdfilter");
        String namaskpdfilter = request.getParameter("namaskpdfilter");
        String nosp2dfilter = request.getParameter("nosp2dfilter");

        Map<String, Object> param = new HashMap(2);
        Integer offset = Integer.valueOf(request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")).intValue() : 0);
        Integer limit = Integer.valueOf(request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")).intValue() : 0);
        Integer iSortCol_0 = Integer.valueOf(request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")).intValue() : 0);
        String sSortDir_0 = request.getParameter("sSortDir_0");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("wilayah", kodewil);
        param.put("tglsah", tglsah);
        param.put("kodeskpdfilter", kodeskpdfilter);
        param.put("namaskpdfilter", namaskpdfilter);
        param.put("nosp2dfilter", nosp2dfilter);
        Map<String, Object> mapData = new HashMap(4);
        int banyak = sp2dServices.getBanyakListIndexCetak(param).intValue();
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", Integer.valueOf(banyak));
        mapData.put("iTotalDisplayRecords", Integer.valueOf(banyak));
        mapData.put("aaData", sp2dServices.getListIndexCetak(param));
        return mapData;
    }

    @RequestMapping(value = {"/json/getTglSp2dSahCetak"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> getTglSp2dSahCetak(HttpServletRequest request) {
        Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        String kodewil = pengguna.getKodeProses();

        Map<String, Object> param = new HashMap(3);
        param.put("tahun", tahunAnggaran);
        param.put("kodewil", kodewil);

        Map<String, Object> mapData = new HashMap(4);

        mapData.put("aData", sp2dServices.getTglSp2dSahCetak(param));
        return mapData;
    }

    @RequestMapping(value = {"/json/prosescetak"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        String idspmpot = request.getParameter("idspmpot");
        String kodetrx = request.getParameter("kodetrx");
        String nosp2d = request.getParameter("nosp2d");
        String kodewil = pengguna.getKodeProses();

        Sp2dPajakTransfer sp2d = new Sp2dPajakTransfer();

        sp2d.setTahun(tahunAnggaran);
        sp2d.setIdSpmPot(Integer.valueOf(SipkdHelpers.getIntFromString(idspmpot.toString())));

        Map<String, Object> map = new HashMap();
        String pathReport = servletContext.getInitParameter("PATH_REPORT");

        map.put("SUBREPORT_DIR", pathReport);
        map.put("TAHUN", tahunAnggaran);
        map.put("IDSPMPOT", sp2d.getIdSpmPot());

        List<Map> listhasil = cetakServices.getnilaiparam(map);
        map.put("NAMA_DAERAH", ((Map) listhasil.get(0)).get("N_DAERAH_JUDUL"));
        map.put("NAMA_DAERAH_LOW", ((Map) listhasil.get(0)).get("N_DAERAH"));
        map.put("NO_PERDA", ((Map) listhasil.get(0)).get("I_PERDA_NO"));
        map.put("THN_PERDA", ((Map) listhasil.get(0)).get("C_PERDA_TAHUN"));
        map.put("TGL_PERDA", ((Map) listhasil.get(0)).get("C_PERDA_TGL"));
        map.put("NAMA_KOTA", ((Map) listhasil.get(0)).get("N_KOTA"));
        map.put("PERATURAN_1", ((Map) listhasil.get(0)).get("E_PERATURAN_SPD1"));
        map.put("PERATURAN_2", ((Map) listhasil.get(0)).get("E_PERATURAN_SPD2"));
        map.put("PERATURAN_3", ((Map) listhasil.get(0)).get("E_PERATURAN_SPD3"));
        map.put("PERATURAN_4", ((Map) listhasil.get(0)).get("E_PERATURAN_SPD4"));
        map.put("PERATURAN_5", ((Map) listhasil.get(0)).get("E_PERATURAN_SPD5"));
        map.put("pathreport", pathReport + "/Report_BuktiPenerimaanNegara.jasper");
        map.put("filename", tahunAnggaran + "_" + kodewil + "_" + nosp2d + "_" + kodetrx + ".pdf");

        Connection con = null;
        String filename = tahunAnggaran + "_" + kodewil + "_" + nosp2d + "_" + kodetrx + ".pdf";
        try {
            con = dataSource.getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_BuktiPenerimaanNegara.jasper", map, con);
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            ServletOutputStream output = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);

            sp2dServices.updateCountCetak(sp2d);
            output.close();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @RequestMapping(value = {"/json/prosesEC"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    public Object prosesEC(@RequestBody Map<String, String> mapdata, HttpServletRequest request) {
        Object returnObject;
        Sp2dPajakJson sp2dPajakJson = new Sp2dPajakJson();
        //final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        String action = "PROSESEC";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
//        Date requestDate = new Date(System.currentTimeMillis());
        Timestamp requestDate = new Timestamp(System.currentTimeMillis());
        try {
            String text = (String) SipkdHelpers.getDjpService(servletContext).get(1);
            String[] textSplited = text.split("\\|");
            String link = textSplited[0];
            String username = textSplited[1];
            String password = textSplited[2];
            JSONObject data = new JSONObject();
            JSONObject header = new JSONObject();
            JSONArray body = new JSONArray();
            JSONObject bodyElement = new JSONObject();

            //String idchannel = "10";
            //String bulkidrequest = (String) mapdata.get("bulkid");
            String bulkidrequest = sp2dServices.getGeneratedId("BULKIDREQUEST");

            String idrequest = sp2dServices.getGeneratedId("IDREQUEST");
            log.debug("LINK PASS : " + text + " " + link + " " + password);

            //log.debug("BULK ID REQUEST =============== " + bulkidrequest);
            //String idrequest = (String) mapdata.get("idrequest");
            String tahun = (String) mapdata.get("tahun");
            String idspmpot = (String) mapdata.get("idspmpot");
            String idsp2d = (String) mapdata.get("idsp2d");
            String taxid = (String) mapdata.get("npwprekanan");
            String taxpayername = (String) mapdata.get("namawp");
            String debitaccountno = (String) mapdata.get("norek");
            String taxpayeraddress = (String) mapdata.get("alamatwp");
            String taxpayercity = (String) mapdata.get("kotawp");
            String typeoftax = (String) mapdata.get("akupajak");
            String subtypeoftax = (String) mapdata.get("kjs");
            String taxperiod = (String) mapdata.get("masapajak");
            String numberofprovisionletter = (String) mapdata.get("nosk");
            String numberoftaxobject = (String) mapdata.get("nop");
            String identitynumber = (String) mapdata.get("noidentitas");
            String paymentamount = (String) mapdata.get("nilaipajak");
            String taxpayerid = (String) mapdata.get("npwppenyetor");
            String taxpayeridname = (String) mapdata.get("namapenyetor");
            String paymentdescription = (String) mapdata.get("uraian");
            String idapp = (String) mapdata.get("idspmpotformat");
            String kodeapp = "001";

            String url = link;
            header.put("idchannel", "10");
            header.put("bulkidrequest", bulkidrequest);

            bodyElement.put("idrequest", idrequest);
            bodyElement.put("taxid", taxid);
            bodyElement.put("taxpayername", taxpayername);
            bodyElement.put("taxpayeraddress", taxpayeraddress);
            bodyElement.put("taxpayercity", taxpayercity);
            bodyElement.put("typeoftax", typeoftax);
            bodyElement.put("subtypeoftax", subtypeoftax);
            bodyElement.put("taxperiod", taxperiod);
            bodyElement.put("numberofprovisionletter", numberofprovisionletter);
            bodyElement.put("numberoftaxobject", numberoftaxobject);
            bodyElement.put("identitynumber", identitynumber);
            bodyElement.put("paymentamount", paymentamount);
            bodyElement.put("taxpayerid", taxpayerid);
            bodyElement.put("taxpayeridname", taxpayeridname);
            bodyElement.put("paymentdescription", paymentdescription);
            bodyElement.put("debitaccountno", debitaccountno);
            bodyElement.put("idapp", idapp);
            bodyElement.put("kodeapp", "001");

            body.put(0, bodyElement);
            data.put("header", header);
            data.put("data", body);
            log.debug("Data - " + data.toString());
            sp2dPajakJson.setIdApp(idapp);
            sp2dPajakJson.setKodeApp(kodeapp);
            sp2dPajakJson.setTahun(tahun);
            sp2dPajakJson.setJsonRequest(data.toString());
            sp2dPajakJson.setKodeAction(action);
//            sp2dPajakJson.setTimeResponse(dateFormat.format(requestDate).toString());
            sp2dPajakJson.setTimeRequest(requestDate);
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes()));

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(data.toString());

            wr.flush();
            wr.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String c;
            while ((c = reader.readLine()) != null) {
                sb.append(c);
            }
            Timestamp responseDate = new Timestamp(System.currentTimeMillis());
            log.debug("Response - " + sb.toString());

            JSONObject json = new JSONObject(sb.toString());
            JSONArray dataArray = json.getJSONArray("data");
            JSONObject dataArray2 = json.getJSONObject("header");
            Map<Object, Object> returnData = new HashMap();
            sp2dPajakJson.setJsonResponse(json.toString());
//            sp2dPajakJson.setTimeResponse(dateFormat.format(responseDate).toString());
            sp2dPajakJson.setTimeResponse(responseDate);
            sp2dServices.insertJson(sp2dPajakJson);
            
            int count = 0;

            Map<String, String> dataElement = new HashMap();
            Iterator<String> nameItr2 = dataArray2.keys();

            while (nameItr2.hasNext()) {
                String name2 = (String) nameItr2.next();
                dataElement.put(name2, dataArray2.getString(name2));
            }

            Iterator<String> nameItr = ((JSONObject) dataArray.get(0)).keys();
            while (nameItr.hasNext()) {
                String name = (String) nameItr.next();
                if (((JSONObject) dataArray.get(0)).get(name).equals(null)) {
                    dataElement.put(name, "-");
                } else {
                    dataElement.put(name, ((JSONObject) dataArray.get(0)).getString(name));
                }
            }

            reader.close();
            con.disconnect();
            log.debug("Response JSON - " + json.toString());

            returnObject = dataElement;

            Sp2dPajakTransfer sp2d = new Sp2dPajakTransfer();

            sp2d.setTahun(tahun);
            sp2d.setIdSp2d(Integer.valueOf(SipkdHelpers.getIntFromString(idsp2d.toString())));
            sp2d.setIdSpmPot(Integer.valueOf(SipkdHelpers.getIntFromString(idspmpot.toString())));
            sp2d.setBulkIdRequest(dataElement.get("bulkidrequest").toString());
            sp2d.setIdRequest(dataElement.get("idrequest").toString());
            sp2d.setIdChannel(dataElement.get("idchannel").toString());
            sp2d.setIdResponse(dataElement.get("idresponse").toString());
            sp2d.setNoRekening(dataElement.get("debitaccountno").toString());
            sp2d.setNpwpRekanan(dataElement.get("taxid").toString());
            sp2d.setNamaWp(dataElement.get("taxpayername").toString());
            sp2d.setAlamatWp(dataElement.get("taxpayeraddress").toString());
            sp2d.setKotaWp(dataElement.get("taxpayercity").toString());
            sp2d.setKodeJenisSetor(dataElement.get("subtypeoftax").toString());
            sp2d.setNamaKJS(dataElement.get("subtypeoftaxdescription").toString());
            sp2d.setAkunPajak(dataElement.get("typeoftax").toString());
            sp2d.setNamaMAP(dataElement.get("typeoftaxdescription").toString());
            sp2d.setMasaPajak(dataElement.get("taxperiod").toString());
            sp2d.setNoSk(dataElement.get("numberofprovisionletter").toString());
            sp2d.setNop(dataElement.get("numberoftaxobject").toString());
            sp2d.setNoIdentitas(dataElement.get("identitynumber").toString());
            sp2d.setNilaiPajak(SipkdHelpers.getBigDecimalFromString(dataElement.get("paymentamount").toString()));
            sp2d.setNpwpPenyetor(dataElement.get("taxpayerid").toString());
            sp2d.setNamaPenyetor(dataElement.get("taxpayeridname").toString());
            sp2d.setUraianPajak(dataElement.get("paymentdescription").toString());
            sp2d.setKodeProses(dataElement.get("processingcode").toString());
            sp2d.setKodeResponse(dataElement.get("responsecode").toString());
            sp2d.setUraianResponse(dataElement.get("responsecodedescription").toString());
            sp2d.setKodeBilling(dataElement.get("billingcode").toString());
            sp2d.setTglBillExpString(dataElement.get("expirydate").toString());
            sp2d.setKodeStan(dataElement.get("stan").toString());
            sp2d.setTglBayarString(dataElement.get("datetimeofpayment").toString());
            sp2d.setTglTransmisiString(dataElement.get("datetimeoftransmission").toString());
            sp2d.setTglBuku(dataElement.get("settlementdate").toString());
            sp2d.setNtb(dataElement.get("ntbnumber").toString());
            sp2d.setNtpn(dataElement.get("ntpnnumber").toString());
            sp2d.setStatusBpn(dataElement.get("bpnstatus").toString());
            sp2d.setKodeRequest("2");

            sp2dServices.updateEC(sp2d);

        } catch (Exception ex) {
            returnObject = ex.getMessage().toString();
            log.debug("ERROR - " + ex.getMessage());
            log.debug("ERROR - " + ex.getStackTrace());
        }
        return returnObject;
    }
}
