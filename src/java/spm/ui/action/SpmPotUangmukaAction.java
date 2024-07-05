package spm.ui.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spm.services.SpmPotAyatServices;
import spm.services.SpmPotUangmukaServices;
import spm.util.SipkdHelpers;
import spp.model.Pengguna;
import spp.model.Setor;
import spp.model.Skpd;
import spp.model.SpmPotUangmuka;

@Controller
@RequestMapping({"/spmpotuangmuka"})
public class SpmPotUangmukaAction {

    @Autowired
    SpmPotUangmukaServices spmpotServices;
    @Autowired
    SpmPotAyatServices spmpotayatServices;
    private static final Logger log = LoggerFactory.getLogger(SpmPotUangmukaAction.class);

    @RequestMapping(value = {"/indexspm/{idspm}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView index(@PathVariable Integer idspm, SpmPotUangmukaAction spmpot, HttpServletRequest request) {
        Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Skpd> listSkpd = pengguna.getSkpd();
        Map<String, Object> listhasil = spmpotServices.getDataSpp(idspm);

        if ((listSkpd.isEmpty()) || ((!listSkpd.isEmpty()) && (listSkpd.size() > 1))) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", Integer.valueOf(1));
        } else if ((!listSkpd.isEmpty()) && (listSkpd.size() == 1)) {
            request.setAttribute("isall", Integer.valueOf(0));
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("idspm", idspm);
            request.setAttribute("nospm", this.spmpotayatServices.getNoSpm(idspm));
            request.setAttribute("tglspm", this.spmpotayatServices.getTglSpm(idspm));
            request.setAttribute("kodeumk", this.spmpotayatServices.getKodeUmk(idspm));
            request.setAttribute("idspp", listhasil.get("I_ID"));
            request.setAttribute("idkontrak", listhasil.get("I_IDKONTRAK"));
        }
        return new ModelAndView("/spm/spmpotuangmuka/addspmpotuangmuka", "cmdSpmPot", spmpot);
    }

    @RequestMapping(value = {"/json/listpotjson"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> listpotjson(HttpServletRequest request) {
        Map<String, Object> param = new HashMap(6);

        Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        List<Skpd> listSkpd = pengguna.getSkpd();
        Integer offset = Integer.valueOf(request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")).intValue() : 0);
        Integer limit = Integer.valueOf(request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")).intValue() : 0);
        Integer iSortCol_0 = Integer.valueOf(request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")).intValue() : 0);
        String sSortDir_0 = request.getParameter("sSortDir_0");
        String nospm = request.getParameter("nospm");
        String spm = request.getParameter("spm");
        Integer idskpd = ((Skpd) listSkpd.get(0)).getIdSkpd();
        String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        param.put("nospm", nospm);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("spm", spm);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);

        Map<String, Object> mapData = new HashMap(4);

        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", Integer.valueOf(8));
        mapData.put("iTotalDisplayRecords", Integer.valueOf(8));
        mapData.put("aaData", this.spmpotServices.getAllPot(param));
        return mapData;
    }

    @RequestMapping(value = {"/json/prosespindahsimpan"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String prosessimpan(@RequestBody Map<String, String> mapdata, HttpServletRequest request) {
        Timestamp tglSkrg = new Timestamp(System.currentTimeMillis());
        Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Skpd> listSkpd = pengguna.getSkpd();
        Integer idskpd = ((Skpd) listSkpd.get(0)).getIdSkpd();
        Map<String, Object> param = new HashMap(7);
        try {
            for (int i = 1; i <= 8; i++) {
                String kodepot = (String) mapdata.get("cpot" + i);

                if (!"31".equals(kodepot)) {
                    SpmPotUangmuka spmpot = new SpmPotUangmuka();
                    spmpot.setIdSpm(Integer.valueOf(SipkdHelpers.getIntFromString((String) mapdata.get("idspm"))));
                    spmpot.setTahun(Integer.valueOf(SipkdHelpers.getIntFromString(tahunAnggaran)));
                    spmpot.setIdSkpd(idskpd);
                    spmpot.setNilaiPot(SipkdHelpers.getBigDecimalFromString((String) mapdata.get("vpot" + i)));
                    spmpot.setcPot((String) mapdata.get("cpot" + i));
                    
                    if ("33".equals(kodepot)) {
                        spmpot.setIdBas(SipkdHelpers.getIntFromString((String) mapdata.get("akun33")));
                    }
                    
                    if ("34".equals(kodepot)) {
                        spmpot.setIdBas(SipkdHelpers.getIntFromString((String) mapdata.get("akun34")));
                    }
                    
                    Integer status = Integer.valueOf(SipkdHelpers.getIntFromString((String) mapdata.get("statuss" + i)));

                    final String addoredit = mapdata.get("addoredit" + i);

                    if ("add".equals(addoredit)) {//(status.intValue() == 1) {
                        spmpot.setIdEntry(pengguna.getIdPengguna());
                        spmpot.setTglEntry(tglSkrg);
                        this.spmpotServices.addPot(spmpot);
                    } else if ("edit".equals(addoredit)) {
                        spmpot.setIdEdit(pengguna.getIdPengguna());
                        spmpot.setTglEdit(tglSkrg);
                        this.spmpotServices.updatePot(spmpot);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Data Pagu SPP GUP/UP  berhasil disimpan ";
    }

    @RequestMapping(value = "/potonganumkpop", method = RequestMethod.GET)
    public ModelAndView potonganumkpop(final SpmPotUangmuka umk, final HttpServletRequest request, final HttpServletResponse response, Model model) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");

        return new ModelAndView("/spm/spmpotuangmuka/addpotumk", "refkegiatan", umk);
    }
    
    @RequestMapping(value = {"/addpotonganumk"}, method = RequestMethod.GET)
    public ModelAndView addpotonganumk(SpmPotUangmuka umk, HttpServletRequest request) {
        Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
       
        return new ModelAndView("/spm/spmpotuangmuka/addpotumk", "refkegiatan", umk);
    }

    @RequestMapping(value = "/json/listpotumkjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpotumkjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String idspp = request.getParameter("idspp");
        final String idspm = request.getParameter("idspm");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String idkontrak = request.getParameter("idkontrak");
        final String kodeumk = request.getParameter("kodeumk");
        

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("idkontrak", idkontrak);
        param.put("idspp", idspp);
        param.put("idspm", idspm);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("kodeumk", kodeumk);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = spmpotServices.getBanyakPotUmk(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", spmpotServices.getListPotUmk(param));

        return mapData;
    }

    @RequestMapping(value = "/json/prosessimpanpotumk", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpanpotumk(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<SpmPotUangmuka> listUMK = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");
        final String idspm = (String) mapdata.get("idspm");
        final String cpot = (String) mapdata.get("cpot");

        for (Map<String, Object> mapnilailist : nilailist) {
            SpmPotUangmuka umk = new SpmPotUangmuka();

            umk.setTahun(SipkdHelpers.getIntFromString(tahun));
            umk.setIdSkpd(SipkdHelpers.getIntFromString(idskpd));
            umk.setIdEntry(pengguna.getIdPengguna());
            umk.setIdSpm(SipkdHelpers.getIntFromString(idspm));
            umk.setcPot(cpot);

            Object nilaipot = mapnilailist.get("nilaipot");
            Object idbas = mapnilailist.get("idbas");

            umk.setNilaiPot(SipkdHelpers.getBigDecimalFromString(nilaipot.toString()));
            umk.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            listUMK.add(umk);

        }

        spmpotServices.insertUMK(listUMK);

        return "Data Potongan UMK Berhasil Disimpan";
    }
    
    @RequestMapping(value = "/json/getKodeUmk", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getKodeUmk(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkontrak = request.getParameter("idkontrak");
        
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idkontrak", idkontrak);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", spmpotServices.getKodeUmk(param));
        
        return mapData;
    }
    
    @RequestMapping(value = "/json/getAkunDenda", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAkunDenda(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idspm = request.getParameter("idspm");
        
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idspm", idspm);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", spmpotServices.getAkunDenda(param));
        
        return mapData;
    }
    
}
