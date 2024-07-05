/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
package spj.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spp.model.Pengguna;
import spp.model.Skpd;
import spj.util.SipkdHelpers;
import spp.model.Setor;
import spp.model.SetorRinci;
import spj.services.SetorJukorServices;
import spj.util.BigDecimalPropertyEditor;

import spj.util.SqlDatePropertyEditor;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/setorjukor")
public class SetorJukorAction {

    private static final Logger log = LoggerFactory.getLogger(SetorAction.class);

    @Autowired
    SetorJukorServices setorServices;

    @RequestMapping(method = RequestMethod.GET)
    public String index(final Setor setor, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());

            setor.setIdskpd(listSkpd.get(0).getIdSkpd());
        }

        return "/setorjukor/setor";
    }

    @RequestMapping(value = "/indexbtl", method = RequestMethod.GET)
    public String indexbtl(final Setor setor, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());

            setor.setIdskpd(listSkpd.get(0).getIdSkpd());
        }

        return "/setorjukor/setorbtl";
    }

    @RequestMapping(value = "/json/listsetor", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsetor(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("skpd");
        param.put("skpd", skpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final Integer banyak = setorServices.getCountSetor(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getSetor(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listsetorbtl", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsetorbtl(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String skpd = request.getParameter("skpd");
        param.put("skpd", skpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final Integer banyak = setorServices.getCountSetorBtl(param);

        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getSetorBtl(param));
        return mapData;
    }

    @RequestMapping(value = "/addsetor", method = RequestMethod.GET)
    public ModelAndView add(final Setor setor, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());

            setor.setIdskpd(listSkpd.get(0).getIdSkpd());
        }
        return new ModelAndView("setorjukor/addsetor", "refsetor", setor);

    }

    @RequestMapping(value = "/addsetorbtl", method = RequestMethod.GET)
    public ModelAndView addbtl(final Setor setor, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());

            setor.setIdskpd(listSkpd.get(0).getIdSkpd());
        }
        return new ModelAndView("setorjukor/addsetorbtl", "refsetor", setor);
    }

    @RequestMapping(value = "/json/getdata/{kegiatan}/{tahun}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsetorjson(@PathVariable String kegiatan, @PathVariable String tahun, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = tahun;
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(3);
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());

        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            final Integer idskpd = listSkpd.get(0).getIdSkpd();
            param.put("idskpd", idskpd);
            param.put("tahun", tahunAnggaran);
            param.put("kegiatan", kegiatan);
        }

        final Map< String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("cek", 1);
        mapData.put("keg", kegiatan);
        mapData.put("aData", setorServices.getKegiatan(param));

        return mapData;

    }

    @RequestMapping(value = "/json/getforsetorrinci", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsons(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idkegiatan");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String id = request.getParameter("id");
        param.put("id", id);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final Integer banyak = setorServices.getCountForRinci(param);

        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getForAddRinci(param));
        return mapData;

    }

    @RequestMapping(value = "/json/getlistsetorrincibanyak ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getlistspjrincibanyak(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idkegiatan");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);
        return setorServices.getCountForRinci(param);
    }

    @RequestMapping(value = "/simpansetor", method = RequestMethod.POST)
    public String prosessimpan(@Valid @ModelAttribute("refsetor") Setor setor,
            BindingResult result, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = request.getParameter("tahunAngg");

        setor.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        setor.setTahunAngg(SipkdHelpers.getIntFromString(tahunAnggaran));
        setor.setIdEntry(pengguna.getIdPengguna());

        final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
        final List<SetorRinci> listSppTuRinci = new ArrayList<SetorRinci>(banyakrinci);

        for (int i = 1; i <= banyakrinci; i++) {
            final SetorRinci setorRinci = new SetorRinci();
            setor.setNoBku("-1");

            setorRinci.setIdkegiatan(SipkdHelpers.getIntFromString(request.getParameter("idkegiatan")));
            setorRinci.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + i)));
            setorRinci.setNilaiSetor(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaisetor" + i)));
            setorRinci.setIdEntry(pengguna.getIdPengguna());
            setorRinci.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

            listSppTuRinci.add(setorRinci);

        }
        setor.setSetorRinci(listSppTuRinci);
        setorServices.insertSetor(setor);

        return "redirect:/setorjukor";
    }

    @RequestMapping(value = "/json/getforaddsetorrincibtl", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsonsaddbtl(final HttpServletRequest request) {
        //final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String idSetor = request.getParameter("idSetor");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String id = request.getParameter("id");

        param.put("id", id);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idSetor", idSetor);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final Integer banyak = setorServices.getCountForAddRinciBtl(param);

        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getForAddRinciBtl(param));
        return mapData;
    }

    @RequestMapping(value = "/simpansetorbtl", method = RequestMethod.POST)
    public String prosessimpanbtl(@Valid @ModelAttribute("refsetor") Setor setor,
            BindingResult result, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = request.getParameter("tahunAngg");
        final String idskpd = request.getParameter("skpd.idSkpd");

        setor.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        setor.setTahunAngg(SipkdHelpers.getIntFromString(tahunAnggaran));
        setor.setIdEntry(pengguna.getIdPengguna());

        final String idSetor = request.getParameter("idSetor");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));

        final List<SetorRinci> listSppTuRinci = new ArrayList<SetorRinci>(banyakrinci);

        for (int i = 1; i <= banyakrinci; i++) {
            final SetorRinci setorRinci = new SetorRinci();

            setorRinci.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + i)));
            setorRinci.setNilaiSetor(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaisetor" + i)));
            setorRinci.setIdEntry(pengguna.getIdPengguna());
            setorRinci.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

            listSppTuRinci.add(setorRinci);
        }
        setor.setSetorRinci(listSppTuRinci);
        setorServices.insertSetorBtl(setor);

        return "redirect:/setorjukor/indexbtl";

    }

    @RequestMapping(value = "/editsetor/{idsetor}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idsetor, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        final Setor setor = setorServices.getSetorById(idsetor);
        return new ModelAndView("setorjukor/editsetor", "refsetor", setor);
    }

    @RequestMapping(value = "/deletesetorbl/{idsetor}", method = RequestMethod.GET)
    public ModelAndView deletesetorbl(@PathVariable Integer idsetor, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        final Setor setor = setorServices.getSetorById(idsetor);
        return new ModelAndView("setorjukor/deletesetor", "refsetor", setor);
    }

    @RequestMapping(value = "/deletesetorbtl/{idsetor}", method = RequestMethod.GET)
    public ModelAndView deletesetorbtl(@PathVariable Integer idsetor, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        final Setor setor = setorServices.getSetorByIdBtl(idsetor);
        return new ModelAndView("setorjukor/deletesetorbtl", "refsetor", setor);
    }

    @RequestMapping(value = "/json/getforedit", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpjsons(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idKegiatan = request.getParameter("idKegiatan");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String idSetor = request.getParameter("idSetor");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idKegiatan);
        param.put("idSetor", idSetor);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final Integer banyak = setorServices.getCountEditSetor(param);

        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getEditSetor(param));

        return mapData;
    }

    @RequestMapping(value = "/simpansetor2", method = RequestMethod.POST)
    public String prosesup(@Valid @ModelAttribute("refsetor") Setor setor,
            BindingResult result, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = request.getParameter("tahunAngg");

        setor.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        setor.setTahunAngg(SipkdHelpers.getIntFromString(tahunAnggaran));
        setor.setIdEntry(pengguna.getIdPengguna());
        setor.setIdSetor(SipkdHelpers.getIntFromString(request.getParameter("idSetor")));

        final Map< String, Object> param = new HashMap<String, Object>(6);

        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Integer idskpd = listSkpd.get(0).getIdSkpd();
        final String idkegiatan = request.getParameter("idkegiatan");

        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);

        final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
        final List<SetorRinci> listSppTuRinci = new ArrayList<SetorRinci>(banyakrinci);

        for (int i = 1; i <= banyakrinci; i++) {
            final SetorRinci setorRinci = new SetorRinci();

            setorRinci.setIdkegiatan(SipkdHelpers.getIntFromString(request.getParameter("idkegiatan")));
            setorRinci.setIdsetor(SipkdHelpers.getIntFromString(request.getParameter("idSetor")));
            setorRinci.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + i)));
            setorRinci.setNilaiSetor(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaisetor" + i)));
            setorRinci.setIdEntry(pengguna.getIdPengguna());
            setorRinci.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

            listSppTuRinci.add(setorRinci);
        }
        setor.setSetorRinci(listSppTuRinci);
        setorServices.updateSetor(setor);

        return "redirect:/setorjukor";

    }

    @RequestMapping(value = "/json/getdatakegiatan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getdatakegiatan(final HttpServletRequest request) {

        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idSetor = request.getParameter("idSetor");

        final Map< String, Object> param = new HashMap<String, Object>(6);

        request.setAttribute("isall", 0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idSetor", idSetor);

        final Map< String, Object> mapData = new HashMap<String, Object>(5);
        mapData.put("cek", 1);
        mapData.put("aData", setorServices.getKegiatanById(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getlistsetoreditbanyak ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getlistsetoreditbanyak(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String idSetor = request.getParameter("idSetor");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);
        param.put("idSetor", idSetor);

        return setorServices.getCountEditSetor(param);
    }

    @RequestMapping(value = "/editsetorbtl/{idsetor}", method = RequestMethod.GET)
    public ModelAndView editbtl(@PathVariable Integer idsetor, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        final Setor setor = setorServices.getSetorByIdBtl(idsetor);
        return new ModelAndView("setorjukor/editsetorbtl", "refsetor", setor);
    }

    @RequestMapping(value = "/json/getforsetorrincibtl", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsonsbtl(final HttpServletRequest request) {
        //final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idSetor = request.getParameter("idSetor");
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahunAnggaran");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String id = request.getParameter("id");

        param.put("id", id);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idSetor", idSetor);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final Integer banyak = setorServices.getCountForRinciBtl(param);

        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getForRinciBtl(param));
        return mapData;
    }

    @RequestMapping(value = "/simpansetor2btl", method = RequestMethod.POST)
    public String prosesupdatebtl(@Valid @ModelAttribute("refsetor") Setor setor,
            BindingResult result, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = request.getParameter("tahunAngg");
        final String idskpd = request.getParameter("skpd.idSkpd");

        setor.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        setor.setTahunAngg(SipkdHelpers.getIntFromString(tahunAnggaran));
        setor.setIdEntry(pengguna.getIdPengguna());
        setor.setIdSetor(SipkdHelpers.getIntFromString(request.getParameter("idSetor")));

        final String idSetor = request.getParameter("idSetor");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        param.put("tahun", tahunAnggaran);
        param.put("idSetor", idSetor);
        param.put("idskpd", idskpd);

        //final Integer banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));//setorServices.getCountForRinciBtl(param);
        Integer vtahun = SipkdHelpers.getIntFromString((String) request.getSession().getAttribute("tahunAnggaran"));
        Integer vtahunangg = SipkdHelpers.getIntFromString(tahunAnggaran);

        Integer banyakrinci;
/*
        banyakrinci = setorServices.getCountForRinciBtl(param);

        final List<SetorRinci> listSppTuRinci = new ArrayList<SetorRinci>(banyakrinci);

        for (int i = 0; i < banyakrinci; i++) {
            final SetorRinci setorRinci = new SetorRinci();

            final String penanda = "cekpilih" + (i + 1);
            final String idBl = request.getParameter(penanda);

            if (idBl != null && !idBl.isEmpty()) {
                setorRinci.setIdBl(SipkdHelpers.getIntFromString(idBl));
                setorRinci.setIdsetor(SipkdHelpers.getIntFromString(request.getParameter("idSetor")));
                setorRinci.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + idBl)));
                setorRinci.setNilaiSetor(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaisetor" + idBl)));
                setorRinci.setIdEntry(pengguna.getIdPengguna());
                setorRinci.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

                listSppTuRinci.add(setorRinci);
            }

        }
        setor.setSetorRinci(listSppTuRinci);
        */
        setorServices.updateSetorBtl(setor);

        return "redirect:/setorjukor/indexbtl";
    }

    @RequestMapping(value = "/json/getIdskpdLama ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getIdskpdLama(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        return setorServices.getIdskpdLama(param);
    }

    @RequestMapping(value = "/json/getbanyakstsblls ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getbanyakstsblls(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahun = request.getParameter("tahun");
        final String nojurnal = request.getParameter("nojurnal");
        final String idkegiatan = request.getParameter("idkegiatan");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("nojurnal", nojurnal);
        param.put("idkegiatan", idkegiatan);

        return setorServices.getBanyakAkunJukorBlLs(param);
    }

    @RequestMapping(value = "/listjukor", method = RequestMethod.GET)
    public ModelAndView listakunbukubesar(final Setor setor, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("setorjukor/listjukor", "refkegiatan", setor);
    }

    @RequestMapping(value = "/json/listkegjukor", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listkegjukor(final HttpServletRequest request) {
        //final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idskpd = request.getParameter("idskpd");
        final String tahun = request.getParameter("tahun");
        final String nosetor = request.getParameter("nosetor");
        final String nojurnal = request.getParameter("nojurnal");
        final String beban = request.getParameter("beban");
        final String idskpdbaru = request.getParameter("idskpdbaru");

        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("nosetor", nosetor);
        param.put("idskpdbaru", idskpdbaru);
        param.put("nojurnal", nojurnal);
        param.put("beban", beban);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final Integer banyak = setorServices.getBanyakKegJukorBlLs(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getListKegJukorBlLs(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getListAkunJukor", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getListAkunJukor(final HttpServletRequest request) {
        //final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idskpd = request.getParameter("idskpd");
        final String tahun = request.getParameter("tahun");
        final String nojurnal = request.getParameter("nojurnal");
        final String idkegiatan = request.getParameter("idkegiatan");

        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("nojurnal", nojurnal);
        param.put("idkegiatan", idkegiatan);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final Integer banyak = setorServices.getBanyakAkunJukorBlLs(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getListAkunJukorBlLs(param));
        return mapData;
    }

    @RequestMapping(value = "/deletesetorbl", method = RequestMethod.POST)
    public String deletesetorbl(@Valid @ModelAttribute("refsetor") Setor setor,
            BindingResult result, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final SetorRinci setorRinci = new SetorRinci();
        setorRinci.setIdsetor(SipkdHelpers.getIntFromString(request.getParameter("idSetor")));

        setorServices.deleteSetor(setorRinci);

        return "redirect:/setorjukor";

    }

    @RequestMapping(value = "/listjukorbtl", method = RequestMethod.GET)
    public ModelAndView listjukorbtl(final Setor setor, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("setorjukor/listjukorbtl", "refkegiatan", setor);
    }

    @RequestMapping(value = "/json/listkegjukorbtl", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listkegjukorbtl(final HttpServletRequest request) {
        //final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idskpd = request.getParameter("idskpd");
        final String tahun = request.getParameter("tahun");
        final String nosetor = request.getParameter("nosetor");
        final String nojurnal = request.getParameter("nojurnal");
        final String idskpdbaru = request.getParameter("idskpdbaru");

        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("nosetor", nosetor);
        param.put("nojurnal", nojurnal);
        param.put("idskpdbaru", idskpdbaru);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final Integer banyak = setorServices.getBanyakJukorBtl(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getListJukorBtl(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getListAkunJukorBtl", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getListAkunJukorBtl(final HttpServletRequest request) {
        //final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idskpd = request.getParameter("idskpd");
        final String tahun = request.getParameter("tahun");
        final String nojurnal = request.getParameter("nojurnal");

        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("nojurnal", nojurnal);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final Integer banyak = setorServices.getBanyakAkunJukorBtl(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getListAkunJukorBtl(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getbanyakstsbtl ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getbanyakstsbtl(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahun = request.getParameter("tahun");
        final String nojurnal = request.getParameter("nojurnal");
        
        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("nojurnal", nojurnal);
        
        return setorServices.getBanyakAkunJukorBtl(param);
    }

    @RequestMapping(value = "/deletesetorbtl", method = RequestMethod.POST)
    public String deletesetorbtl(@Valid @ModelAttribute("refsetor") Setor setor,
            BindingResult result, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final SetorRinci setorRinci = new SetorRinci();
        setorRinci.setIdsetor(SipkdHelpers.getIntFromString(request.getParameter("idSetor")));

        setorServices.deleteSetor(setorRinci);

        return "redirect:/setorjukor/indexbtl";

    }

    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
