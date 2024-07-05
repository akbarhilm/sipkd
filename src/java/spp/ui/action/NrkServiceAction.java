package spp.ui.action;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spp.model.SppUp;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.jboss.resteasy.client.jaxrs.ResteasyClient;
//import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
//import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author zainab
 */
@Controller
@RequestMapping("/nrkservice")
public class NrkServiceAction {

    private static final Logger log = LoggerFactory.getLogger(NrkServiceAction.class);

    private final RestTemplate rest;
    private final HttpHeaders headers;
    private HttpStatus status;
    private static final String resourceUrl = "http://soadev.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegawaiSIPKD";

    public NrkServiceAction() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");

    }

    @RequestMapping(value = "/json/getnrkAsli", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, Object> getnrkAsli(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        log.debug("KIRIM POST DATA -- mapdata.toString() ------------> " + mapdata.toString());

        HttpEntity<Map<String, Object>> requestPostData = new HttpEntity<Map<String, Object>>(mapdata);
        ResponseEntity<Map> response = rest.exchange(resourceUrl, HttpMethod.POST, requestPostData, Map.class);
        log.debug("KIRIM POST DATA -- response.getBody() ------------> " + response.getBody());

        return response.getBody();
    }
    
    @RequestMapping(value = "/json/getnrk", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getnrkget(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        log.debug("KIRIM POST DATA -- mapdata.toString() ------------> " + mapdata.toString());

        HttpEntity<Map<String, Object>> requestPostData = new HttpEntity<Map<String, Object>>(mapdata);
        ResponseEntity<Map> response = rest.exchange(resourceUrl, HttpMethod.GET, requestPostData, Map.class);
        log.debug("KIRIM POST DATA -- response.getBody() ------------> " + response.getBody());

        return response.getBody();
    }
    
    
    @RequestMapping(value = "/indexnrk", method = RequestMethod.GET)
    public ModelAndView indexnrk(final SppUp spp, final HttpServletRequest request) {

        return new ModelAndView("nrkservice/nrkservice", "nrk", spp);

    }

}
