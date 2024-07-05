package sp2d.ui.action;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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

/**
 *
 * @author hari
 */
@Controller
@RequestMapping("/halamanterimadata")
public class TerimaDataPostAction {

    private final RestTemplate rest;
    private final HttpHeaders headers;
    private HttpStatus status;

    public TerimaDataPostAction() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";

    }

    @RequestMapping(value = "/json/terimapostdata",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, Object> hapusspmcetak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        /*
        		"kodebank":	"111",
		"namabank":	"BANK	DKI",
		"norek":	"10120064394",
		"nama":	"TURMIDI",
		"alamat":	"JL	JEMBATAN	V	DALAM	NO	11B	RT001	RW004		DURI	PULO-GAMBIR,JAKPUS	10140",
		"npwp":	"120000000000000"
         */
        Map<String, Object> hasil = new HashMap<String, Object>();
        hasil.put("kodebank", "111");
        hasil.put("namabank", "BANK	DKI");
        hasil.put("norek", "10120064394");
        hasil.put("alamat", "JL	JEMBATAN V DALAM NO 11B	RT001 RW004 DURI PULO-GAMBIR,JAKPUS 10140");
        return hasil;
    }
}
