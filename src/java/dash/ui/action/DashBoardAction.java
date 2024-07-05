package dash.ui.action;

import dash.entity.DashBoardMapper;
import dash.model.Pengguna;
import dash.model.Sekolah;
import dash.services.DashBoardServices;
import dash.services.LoginServices;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/dash")
public class DashBoardAction {

    private static final Logger log = LoggerFactory.getLogger(DashBoardAction.class);
   @Autowired
    DashBoardServices dashServ;

    /* private static final Properties properties = new Properties();

     static {
     try {
     properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("sso.properties"));
     } catch (IOException ex) {
     ex.printStackTrace();
     }
     }*/
    

     @RequestMapping(value = "/json/listpenerimaan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listterima(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final Map< String, Object> param = new HashMap<String, Object>(6);
//        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
//        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
//        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
//        final String sSortDir_0 = request.getParameter("sSortDir_0");
//        final String namapengguna = request.getParameter("namapengguna");
//        final String nrk = request.getParameter("nrk");
//        
//        param.put("idskpd", idskpd);
//        param.put("namapengguna", namapengguna);
//        param.put("nrk", nrk);
//        param.put("offset", offset);
//        param.put("limit", limit);
//        param.put("iSortCol_0", iSortCol_0);
//        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        //final long banyak = listServices.getBanyakPengguna(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
       // mapData.put("iTotalRecords", banyak);
       // mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", dashServ.getDataPenerimaan(param));
        return mapData;
    }
    
    @RequestMapping(value = "/json/listpengeluaran", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listkeluar(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final Map< String, Object> param = new HashMap<String, Object>(6);
//        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
//        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
//        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
//        final String sSortDir_0 = request.getParameter("sSortDir_0");
//        final String namapengguna = request.getParameter("namapengguna");
//        final String nrk = request.getParameter("nrk");
//        
//        param.put("idskpd", idskpd);
//        param.put("namapengguna", namapengguna);
//        param.put("nrk", nrk);
//        param.put("offset", offset);
//        param.put("limit", limit);
//        param.put("iSortCol_0", iSortCol_0);
//        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        //final long banyak = listServices.getBanyakPengguna(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
       // mapData.put("iTotalRecords", banyak);
       // mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", dashServ.getDataPengeluaran(param));
        return mapData;
    }
    
    @RequestMapping(value = "/json/saldoakhir", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> saldoakhir(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final Map< String, Object> param = new HashMap<String, Object>(6);
//        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
//        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
//        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
//        final String sSortDir_0 = request.getParameter("sSortDir_0");
//        final String namapengguna = request.getParameter("namapengguna");
//        final String nrk = request.getParameter("nrk");
//        
//        param.put("idskpd", idskpd);
//        param.put("namapengguna", namapengguna);
//        param.put("nrk", nrk);
//        param.put("offset", offset);
//        param.put("limit", limit);
//        param.put("iSortCol_0", iSortCol_0);
//        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        //final long banyak = listServices.getBanyakPengguna(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
       // mapData.put("iTotalRecords", banyak);
       // mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", dashServ.getSaldoakhir());
        return mapData;
    }
    
    @RequestMapping(value = "/json/lastupdate", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> lu(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final Map< String, Object> param = new HashMap<String, Object>(6);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        //final long banyak = listServices.getBanyakPengguna(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
       // mapData.put("iTotalRecords", banyak);
       // mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", dashServ.getLastUpdate());
        return mapData;
    }

    
    
}
