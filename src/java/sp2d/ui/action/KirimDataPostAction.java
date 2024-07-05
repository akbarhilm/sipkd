package sp2d.ui.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import sp2d.config.DefaultTrustManager;
import sp2d.util.SipkdHelpers;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.SpmTerima;

/**
 *
 * @author hari
 */
@Controller
@RequestMapping("/halamanpostdata")
public class KirimDataPostAction {

    private static final Logger log = LoggerFactory.getLogger(KirimDataPostAction.class);

    @Autowired
    ServletContext servletContext;

    private final RestTemplate rest;
    private final HttpHeaders headers;
    private HttpStatus status;

    //private static final String resourceUrl = "http://localhost:8086/TERIMA/halamanterimadata/json/terimapostdata";
    private static final String resourceUrl = "http://10.100.111.152:9876/getResponseEbku"; // development
    //private static final String resourceUrl = "http://10.100.111.153:9876/getResponseEbku"; // produksi

    public KirimDataPostAction() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/json/kirimpostdata", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, Object> hapusspmcetak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        log.debug("KIRIM POST DATA -- mapdata.toString() ------------> " + mapdata.toString());
        HttpEntity<Map<String, Object>> requestPostData = new HttpEntity<Map<String, Object>>(mapdata);
        ResponseEntity<Map> response = rest.exchange(resourceUrl, HttpMethod.POST, requestPostData, Map.class);

        log.debug("KIRIM POST DATA -- response.getBody() ------------> " + response.getBody());

        return response.getBody();
    }

    @RequestMapping(value = "/teskirim", method = RequestMethod.GET)
    public ModelAndView addbku(final SpmTerima tes, final HttpServletRequest request) {

        return new ModelAndView("tesjson/teskirim", "refbku", tes);

    }

    @RequestMapping(value = "/json/ceknorek", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, String> ceknorek(@RequestBody Map<String, String> mapdata, final HttpServletRequest request) throws IOException {
        try {
            InputStream inputStreamOnUs = servletContext.getResourceAsStream("/WEB-INF/txt/norek.txt");
            InputStream inputStreamOffUs = servletContext.getResourceAsStream("/WEB-INF/txt/norekoffus.txt");
            String on = SipkdHelpers.readFromInputStream(inputStreamOnUs);
            String off = SipkdHelpers.readFromInputStream(inputStreamOffUs);
            SSLContext sslctx = SSLContext.getInstance("SSL");
            sslctx.init(null, new X509TrustManager[]{new DefaultTrustManager()
            }, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslctx.getSocketFactory()
            );
            JSONObject data = new JSONObject();
            final String norek = mapdata.get("norek");
            final String kodebank = mapdata.get("kodebank");
            String url = null, username = null, password = null;
            if (kodebank.equals("111")) {
                data.put("kodebank", kodebank);
                data.put("norek", norek);
                url = on.split("\\|")[0];
                username = on.split("\\|")[1];
                password = on.split("\\|")[2];
            } else {
                data.put("code", kodebank);
                data.put("account", norek);
                url = off.split("\\|")[0];
                username = off.split("\\|")[1];
                password = off.split("\\|")[2];
            }

            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes()));
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(data.toString());
            wr.flush();
            wr.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String c;
            StringBuilder sb = new StringBuilder();
            while ((c = reader.readLine()) != null) {
                if (!c.contains("]") && !c.contains("[")) {
                    sb.append(c);
                }
            }

            JSONObject json = new JSONObject(sb.toString());

            Iterator<String> nameItr = json.keys();
            Map<String, String> outMap = new HashMap<String, String>();
            while (nameItr.hasNext()) {
                String name = nameItr.next();
                outMap.put(name, json.getString(name));

            }
            con.disconnect();
            return outMap;
        } catch (Exception ex) {
            log.debug("ERROR - " + ex.getMessage());
        }
        return null;

        //LOCAL server bandung (tanpa user dan password)
//        HttpEntity<Map<String, Object>> requestPostData = new HttpEntity<Map<String, Object>>(mapdata);
//
//        InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/txt/norek.txt");
//        String url = SipkdHelpers.readFromInputStream(inputStream);
//
//        ResponseEntity<Map> response = rest.exchange(url, HttpMethod.POST, requestPostData, Map.class);
//
//        log.debug("KIRIM POST DATA -- response.getBody() ------------> " + response.getBody());
//
//        return response.getBody();
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

}
