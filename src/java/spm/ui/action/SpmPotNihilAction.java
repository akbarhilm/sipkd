package spm.ui.action;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spm.services.SpmPotAyatServices;
import spm.services.SpmPotNihilServices;
import spm.util.SipkdHelpers;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.SpmPotNihil;

@Controller
@RequestMapping({"/spmpotnihil"})
public class SpmPotNihilAction {

    @Autowired
    SpmPotNihilServices spmpotServices;
    @Autowired
    SpmPotAyatServices spmpotayatServices;
    private static final Logger log = LoggerFactory.getLogger(SpmPotNihilAction.class);

    @RequestMapping(value = {"/indexspm/{idspm}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView index(@PathVariable Integer idspm, SpmPotNihilAction spmpot, HttpServletRequest request) {
        Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Skpd> listSkpd = pengguna.getSkpd();
        if ((listSkpd.isEmpty()) || ((!listSkpd.isEmpty()) && (listSkpd.size() > 1))) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", Integer.valueOf(1));
        } else if ((!listSkpd.isEmpty()) && (listSkpd.size() == 1)) {
            request.setAttribute("isall", Integer.valueOf(0));
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("idspm", idspm);
            request.setAttribute("nospm", this.spmpotayatServices.getNoSpm(idspm));
            request.setAttribute("tglspm", this.spmpotayatServices.getTglSpm(idspm));
        }
        return new ModelAndView("/spm/spmpotnihil/addspmpotnihil", "cmdSpmPot", spmpot);
    }

    @RequestMapping(value = {"/json/getlistnihil"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> listnihil(HttpServletRequest request) {
        Map<String, Object> param = new HashMap(6);

        Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        List<Skpd> listSkpd = pengguna.getSkpd();
        Integer offset = Integer.valueOf(request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")).intValue() : 0);
        Integer limit = Integer.valueOf(request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")).intValue() : 0);
        Integer iSortCol_0 = Integer.valueOf(request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")).intValue() : 0);
        String sSortDir_0 = request.getParameter("sSortDir_0");
        String nospm = request.getParameter("nospm");
        String spm = request.getParameter("idspm");
        Integer idskpd = ((Skpd) listSkpd.get(0)).getIdSkpd();
        String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        param.put("nospm", nospm);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idspm", spm);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);

        Map<String, Object> mapData = new HashMap(4);

        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", Integer.valueOf(8));
        mapData.put("iTotalDisplayRecords", Integer.valueOf(8));
        mapData.put("aaData", this.spmpotServices.getAkunPendapatan(param));
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
            for (int i = 1; i <= 4; i++) {
                SpmPotNihil spmpot = new SpmPotNihil();
                spmpot.setIdSpm(Integer.valueOf(SipkdHelpers.getIntFromString((String) mapdata.get("idspm"))));
                spmpot.setTahun(Integer.valueOf(SipkdHelpers.getIntFromString(tahunAnggaran)));
                spmpot.setIdSkpd(idskpd);
                spmpot.setNilaiPot(SipkdHelpers.getBigDecimalFromString((String) mapdata.get("vpot" + i)));
                //spmpot.setcPot((String) mapdata.get("cpot" + i));
                spmpot.setIdBas((String) mapdata.get("cpot" + i));
                log.debug("vpot" + spmpot.getNilaiPot());
                log.debug("vvvv" + mapdata.get("vpot" + i));
                //Integer status = Integer.valueOf(SipkdHelpers.getIntFromString((String) mapdata.get("statuss" + i)));
                log.debug("taun======" + tahunAnggaran + spmpot.getTahun());
                log.debug("idskpd=====" + idskpd + spmpot.getIdSkpd());
                log.debug("idspm=====" + mapdata.get("idspm") + spmpot.getIdSpm());

                log.debug("idbas=====" + mapdata.get("cpot" + i) + spmpot.getIdBas());
                log.debug("nilai======" + mapdata.get("vpot" + i) + spmpot.getNilaiPot());

                final String addoredit = mapdata.get("addoredit" + i);
                log.debug("wowow" + addoredit);
                if ("add".equals(addoredit)) {//(status.intValue() == 1) {
                    log.debug("masukadd");
                    spmpot.setIdEntry(pengguna.getIdPengguna());
                    spmpot.setTglEntry(tglSkrg);
                    log.debug("penguna=====" + pengguna.getIdPengguna() + spmpot.getIdEntry());
                    spmpotServices.addPot(spmpot);

                } else if ("edit".equals(addoredit)) {
                    log.debug("masuked");
                    log.debug("babababas" + spmpot.getIdBas());
                    spmpot.setIdEdit(pengguna.getIdPengguna());
                    spmpot.setTglEdit(tglSkrg);
                    spmpotServices.updatePot(spmpot);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Data Pagu SPP GUP/UP  berhasil disimpan ";
    }
}
