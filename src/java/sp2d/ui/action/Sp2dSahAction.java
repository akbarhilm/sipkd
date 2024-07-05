package sp2d.ui.action;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sp2d.services.CetakValidasiSP2DServices;
import sp2d.services.UserManagementServices;
import sp2d.util.SipkdHelpers;
import spp.model.Pengguna;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/sp2dsah")
public class Sp2dSahAction {

    private static final Logger log = LoggerFactory.getLogger(Sp2dSahAction.class);
    @Autowired
    UserManagementServices userManagementServices;
    @Autowired
    CetakValidasiSP2DServices cetakValidasiSP2DServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;

    /*  @RequestMapping(method = RequestMethod.GET)
     public String index(  final HttpServletRequest req) {
     return "pengesahan/index";
     }*/
 /*   @RequestMapping(value = "/indexsp2dsah", method = RequestMethod.GET)
     public String indexspmup(final HttpServletRequest request) {
     final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
     final List<Skpd> listSkpd = pengguna.getSkpd();
     if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
     log.debug(" listSkpd masuk == " + listSkpd.size());
     request.setAttribute("isall", 1);
     } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
     request.setAttribute("isall", 0);
     request.setAttribute("skpd", listSkpd.get(0));
     }
     return "sp2d/pengesahan/index";

     }*/
    @RequestMapping(value = "/json/getlistsp2d", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspmcetak(final HttpServletRequest request) {

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
        param.put("namaskpd", skpd);
        param.put("idskpd", SipkdHelpers.getIntFromString(idskpd));
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = cetakValidasiSP2DServices.getbanyaksp2dsah(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", cetakValidasiSP2DServices.getlistsp2dsah(param));
        return mapData;
    }

    @RequestMapping(value = "/indexsp2dsah", method = RequestMethod.GET)
    public String indexsp2dsah(final HttpServletResponse response, final HttpServletRequest request) throws IOException {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        boolean status = false;
        final String ipaddr = pengguna.getIpAddress();
        if (ipaddr != null && StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotBlank(ipaddr)) {
            //  status = new IpAddressMatcher(ipaddr).matches(request) || new IpAddressMatcher("127.0.0.1").matches(request);
            //status = Objects.equals(ipaddr, SipkdHelpers.getIpAddr(request)) || new IpAddressMatcher("127.0.0.1").matches(request) || new IpAddressMatcher("0:0:0:0:0:0:0:1").matches(request);
            status = (pengguna.getNamaPengguna().contains("99")) || (pengguna.getNamaPengguna().contains("ADMIN") || Objects.equals(ipaddr, SipkdHelpers.getIpAddr(request))) || (new IpAddressMatcher("127.0.0.1").matches(request)) || (new IpAddressMatcher("0:0:0:0:0:0:0:1").matches(request));
        }
        log.debug(ipaddr + " status = " + status + " request " + SipkdHelpers.getIpAddr(request));

        //if (/*status*/true) {
        if (status) {
            return "sp2d/pengesahan/index";
        } else {
            // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Anda tidak berhak mengakses halaman pengesahan");
            return "deny";
        }

    }

    @RequestMapping(value = "/indexsp2dsahtransfer", method = RequestMethod.GET)
    public String indexsp2dsahtransfer(final HttpServletResponse response, final HttpServletRequest request) throws IOException {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        boolean status = false;
        final String ipaddr = pengguna.getIpAddress();
        if (ipaddr != null && StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotBlank(ipaddr)) {
            //  status = new IpAddressMatcher(ipaddr).matches(request) || new IpAddressMatcher("127.0.0.1").matches(request);
            status = (pengguna.getNamaPengguna().contains("99")) || (pengguna.getNamaPengguna().contains("ADMIN") || Objects.equals(ipaddr, SipkdHelpers.getIpAddr(request))) || (new IpAddressMatcher("127.0.0.1").matches(request)) || (new IpAddressMatcher("0:0:0:0:0:0:0:1").matches(request));
            //status = Objects.equals(ipaddr, SipkdHelpers.getIpAddr(request)) || new IpAddressMatcher("127.0.0.1").matches(request) || new IpAddressMatcher("0:0:0:0:0:0:0:1").matches(request);
        }
        log.debug(ipaddr + " status = " + status + " request " + SipkdHelpers.getIpAddr(request));

        if (status) {
            return "sp2d/pengesahan/validasitransfer";
        } else {
            // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Anda tidak berhak mengakses halaman pengesahan");
            return "deny";
        }

    }

    @RequestMapping(value = "/json/updatesp2dsah", method = RequestMethod.POST)
    public @ResponseBody
    String submitcetak(@RequestBody List<Map<String, Object>> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        for (Map<String, Object> mapdetil : mapdata) {
            mapdetil.put("userid", pengguna.getIdPengguna());
            mapdetil.put("tgluserid", new Timestamp(System.currentTimeMillis()));
            cetakValidasiSP2DServices.updatesp2dsah(mapdetil);
        }

        return "Pengesahan SP2D Sukses";

    }

    @RequestMapping(value = "/printvalidasi/cetakpengesahan/{idspd}", method = RequestMethod.GET)
    public String cetakpengesahan(final HttpServletRequest request, @PathVariable String idspd, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        request.setAttribute("idsp2d", cetakValidasiSP2DServices.getsp2dsahid(SipkdHelpers.getIntFromString(idspd)));
        request.setAttribute("datasp2d", cetakValidasiSP2DServices.getsp2dsahbyidsp2d(SipkdHelpers.getIntFromString(idspd)));
        request.setAttribute("no", cetakValidasiSP2DServices.getsp2dsahnomor(SipkdHelpers.getIntFromString(idspd)));
        request.setAttribute("nospd", idspd);
        return "sp2d/pengesahan/cetakvalidasi";

    }

    @RequestMapping(value = "/print/cetakbarcodepengesahanbynosp2d/{idsp2d}", method = RequestMethod.GET)
    public void cetakpengesahan(final HttpServletResponse response, @PathVariable String idsp2d) throws WriterException, IOException {
        BitMatrix bitMatrix = new Code128Writer().encode(idsp2d, BarcodeFormat.CODE_128, 90, 40, null);
        MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());

    }

    @RequestMapping(value = "/json/getUmkSah", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getUmkSah(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkontrak = request.getParameter("idkontrak");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idkontrak", idkontrak);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", cetakValidasiSP2DServices.getUmkSah(param));
        return mapData;
    }

}
