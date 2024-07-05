/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.ui.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ws.services.BOSServices;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.util.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/BOS")
public class BOSAction {

    private static final Logger log = LoggerFactory.getLogger(BOSAction.class);
    @Autowired
    BOSServices bosServices;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";

    }

    @RequestMapping(value = "/k7a", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json")
    public @ResponseBody
    String K7A(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request,
            final HttpServletResponse response) throws IOException {
        boolean validate = false;
        Enumeration headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            Object header = headers.nextElement();
            if (((String) header).equals("authorization") && request.getHeader((String) header).substring(6).equals(Base64.encode(("siap:123").getBytes()))) {
                validate = true;
            }
        }
        if (validate) {
            final Map<String, Object> data = (Map<String, Object>) mapdata.get("data");
            JSONObject jsonString = new JSONObject();
            final int size = data.size();
            try {

                final String npsn = (String) data.get("npsn");
                final String triwulan = (String) data.get("triwulan");
                final String tahun = (String) data.get("tahun");

                final Map<String, Object> param = new HashMap<String, Object>(3);
                param.put("npsn", npsn);
                param.put("triwulan", triwulan);
                param.put("tahun", tahun);

                jsonString.put("header", new JSONObject()
                        .put("responsecode", "00")
                        .put("responsedescription", "Berhasil"))
                        .put("data", new JSONObject()
                                .put("summary", bosServices.getSummary7a(param))
                                .put("umum", bosServices.getHeaderk7a(param))
                                .put("detail", bosServices.getDetailk7a(param))
                        );

                return jsonString.toString();
            } catch (Exception e) {
                if (size != 3) {
                    jsonString.put("header", new JSONObject()
                            .put("responsecode", "90")
                            .put("responsedescription", "3 Parameter Required"));
                }
            }
            return jsonString.toString();

        } else {
            JSONObject resp = new JSONObject();
            resp.put("header", new JSONObject()
                    .put("responsecode", "401")
                    .put("responsedescription", "Unauthorize"));
            // response.sendError(401, "davai");
            return resp.toString();
        }

    }
}
