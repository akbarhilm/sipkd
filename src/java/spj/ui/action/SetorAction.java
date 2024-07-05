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
import spj.services.SetorServices;
import spj.util.BigDecimalPropertyEditor;

import spj.util.SqlDatePropertyEditor;

/**
 *
 * @author Husen
 */
@Controller
@RequestMapping("/setor")
public class SetorAction {

    private static final Logger log = LoggerFactory.getLogger(SetorAction.class);

    @Autowired
    SetorServices setorServices;

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

        return "/spj/setor/setor";
    }

    @RequestMapping(value = "/indextu", method = RequestMethod.GET)
    public String indextu(final Setor setor, final HttpServletRequest request) {
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

        return "/spj/setor/setortu";
    }

    @RequestMapping(value = "/indexupgu", method = RequestMethod.GET)
    public String indexupgu(final Setor setor, final HttpServletRequest request) {
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

        return "/spj/setor/setorupgu";
    }

    @RequestMapping(value = "/indexbiaya", method = RequestMethod.GET)
    public String indexbiaya(final Setor setor, final HttpServletRequest request) {
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

        return "/spj/setor/setorbiaya";
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

        return "/spj/setor/setorbtl";
    }

    @RequestMapping(value = "/indexbantuan", method = RequestMethod.GET)
    public String indexbantuan(final Setor setor, final HttpServletRequest request) {
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

        return "/spj/setor/setorbantuan";
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

    @RequestMapping(value = "/json/listsetortu", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsetortu(final HttpServletRequest request) {
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
        final Integer banyak = setorServices.getCountSetorTU(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getSetorTU(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listsetorupgu", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsetorupgu(final HttpServletRequest request) {
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
        final Integer banyak = setorServices.getCountSetorUpGu(param);

        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getSetorUP(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listsetorbiaya", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsetorbiaya(final HttpServletRequest request) {
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
        mapData.put("aaData", setorServices.getSetorBiaya(param));
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
        final Integer banyak = setorServices.getCountSetor(param);

        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getSetorBtl(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listsetorbantuan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsetorbantuan(final HttpServletRequest request) {
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
        mapData.put("aaData", setorServices.getSetorBantuan(param));
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
        return new ModelAndView("spj/setor/addsetor", "refsetor", setor);

    }

    @RequestMapping(value = "/addsetortu", method = RequestMethod.GET)
    public ModelAndView addsetortu(final Setor setor, final HttpServletRequest request) {
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
        return new ModelAndView("spj/setor/addsetortu", "refsetor", setor);

    }

    @RequestMapping(value = "/addsetorbantuan", method = RequestMethod.GET)
    public ModelAndView addbantuan(final Setor setor, final HttpServletRequest request) {
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
        return new ModelAndView("spj/setor/addsetorbantuan", "refsetor", setor);
    }

    @RequestMapping(value = "/addsetorupgu", method = RequestMethod.GET)
    public ModelAndView addupgu(final Setor setor, final HttpServletRequest request, Model model) {
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

        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Integer idskpd = listSkpd.get(0).getIdSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idsetor", "-999"); // tambah baru belum ada id

        final BigDecimal sisaTU = SipkdHelpers.getBigDecimalFromString((setorServices.getNilaiSisaUP(param))); //setorServices.getNilaiSisaTU(param);
        request.setAttribute("nilaisisaTU", sisaTU);

        final BigDecimal sisaUP = SipkdHelpers.getBigDecimalFromString((setorServices.getNilaiSisaUP(param)));//setorServices.getNilaiSisaUP(param);
        request.setAttribute("nilaisisaUP", sisaUP);

        return new ModelAndView("spj/setor/addsetorupgu", "refsetor", setor);

    }

    @RequestMapping(value = "/addsetorbiaya", method = RequestMethod.GET)
    public ModelAndView addbiaya(final Setor setor, final HttpServletRequest request) {
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
        return new ModelAndView("spj/setor/addsetorbiaya", "refsetor", setor);
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
        return new ModelAndView("spj/setor/addsetorbtl", "refsetor", setor);
    }

    @RequestMapping(value = "/simpansetor", method = RequestMethod.POST)
    public String prosessimpan(@Valid @ModelAttribute("refsetor") Setor setor,
            BindingResult result, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = request.getParameter("tahunAngg");
        final String kodeSA = request.getParameter("kodeSA");

        setor.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        setor.setTahunAngg(SipkdHelpers.getIntFromString(tahunAnggaran));
        setor.setIdEntry(pengguna.getIdPengguna());

        Integer vtahun = SipkdHelpers.getIntFromString((String) request.getSession().getAttribute("tahunAnggaran"));
        Integer vtahunangg = SipkdHelpers.getIntFromString(tahunAnggaran);

        if ("1".equals(kodeSA)) {
            final SetorRinci setorRinci = new SetorRinci();
            setorRinci.setNilaiSetor(setor.getNilaiSetor());
            setorRinci.setBeban(setor.getBeban());
            setorRinci.setIdEntry(pengguna.getIdPengguna());
            setorRinci.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
            List<SetorRinci> listsetorRinci = new ArrayList<>();

            listsetorRinci.add(setorRinci);
            setor.setSetorRinci(listsetorRinci);

            setorServices.insertSetorUPGU(setor);
        } else {
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final List<SetorRinci> listSppTuRinci = new ArrayList<SetorRinci>(banyakrinci);

            for (int i = 0; i < banyakrinci; i++) {
                final SetorRinci setorRinci = new SetorRinci();
                if (vtahun >= 2017 && vtahunangg >= 2017) {
                    final Integer penanda = (i + 1);

                    //if (idBl != null && !idBl.isEmpty()) {
                    setorRinci.setIdBl(SipkdHelpers.getIntFromString(request.getParameter("idbl" + penanda)));
                    setorRinci.setIdkegiatan(SipkdHelpers.getIntFromString(request.getParameter("idKegiatan")));
                    setorRinci.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + penanda)));
                    setorRinci.setNilaiSetor(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaisetor" + penanda)));
                    setorRinci.setIdEntry(pengguna.getIdPengguna());
                    setorRinci.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

                    listSppTuRinci.add(setorRinci);
                    //}
                } else { // POLA LAMA
                    final String penanda = "cekpilih" + (i + 1);
                    final String idBl = request.getParameter(penanda);
                    setor.setNoBku("-1");
                    if (idBl != null && !idBl.isEmpty()) {

                        setorRinci.setIdBl(SipkdHelpers.getIntFromString(idBl));
                        setorRinci.setIdkegiatan(SipkdHelpers.getIntFromString(request.getParameter("idkegiatan")));
                        setorRinci.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + idBl)));
                        setorRinci.setNilaiSetor(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaisetor" + idBl)));
                        setorRinci.setIdEntry(pengguna.getIdPengguna());
                        setorRinci.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

                        listSppTuRinci.add(setorRinci);
                    }
                }

            }
            setor.setSetorRinci(listSppTuRinci);
            setorServices.insertSetor(setor);
        }

        return "redirect:/setor";
    }

    @RequestMapping(value = "/simpansetortu", method = RequestMethod.POST)
    public String prosessimpantu(@Valid @ModelAttribute("refsetor") Setor setor,
            BindingResult result, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = request.getParameter("tahunAngg");

        setor.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        setor.setTahunAngg(SipkdHelpers.getIntFromString(tahunAnggaran));
        setor.setIdEntry(pengguna.getIdPengguna());

        final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
        final List<SetorRinci> listSppTuRinci = new ArrayList<SetorRinci>(banyakrinci);

        for (int i = 0; i < banyakrinci; i++) {

            final String penanda = "cekpilih" + (i + 1);
            final String idBl = request.getParameter(penanda);

            if (idBl != null && !idBl.isEmpty()) {

                final SetorRinci setorRinci = new SetorRinci();
                setorRinci.setIdBl(SipkdHelpers.getIntFromString(idBl));
                setorRinci.setIdkegiatan(SipkdHelpers.getIntFromString(request.getParameter("idKegiatan")));
                setorRinci.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + idBl)));
                setorRinci.setNilaiSetor(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaisetor" + idBl)));
                setorRinci.setIdEntry(pengguna.getIdPengguna());
                setorRinci.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

                listSppTuRinci.add(setorRinci);
            }
        }
        setor.setSetorRinci(listSppTuRinci);
        setorServices.insertSetor(setor);

        return "redirect:/setor/indextu";
    }

    @RequestMapping(value = "/simpansetorupgu", method = RequestMethod.POST)
    public String prosessimpanupgu(@Valid @ModelAttribute("refsetor") Setor setor,
            BindingResult result, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = request.getParameter("tahunAngg");

        setor.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        setor.setTahunAngg(SipkdHelpers.getIntFromString(tahunAnggaran));
        setor.setIdEntry(pengguna.getIdPengguna());

        final SetorRinci setorRinci = new SetorRinci();

        setorRinci.setNilaiSetor(setor.getNilaiSetor());
        setorRinci.setIdKegiatan(setor.getIdKegiatan());
        setorRinci.setBeban(setor.getBeban());
        setorRinci.setIdEntry(pengguna.getIdPengguna());
        setorRinci.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        List<SetorRinci> listsetorRinci = new ArrayList<>();

        listsetorRinci.add(setorRinci);
        setor.setSetorRinci(listsetorRinci);

        setorServices.insertSetorUPGU(setor);

        return "redirect:/setor/indexupgu";
    }

    @RequestMapping(value = "/simpansetorbiaya", method = RequestMethod.POST)
    public String prosessimpanbiaya(@Valid @ModelAttribute("refsetor") Setor setor,
            BindingResult result, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = request.getParameter("tahunAngg");

        setor.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        setor.setTahunAngg(SipkdHelpers.getIntFromString(tahunAnggaran));
        setor.setIdEntry(pengguna.getIdPengguna());

        final String idSetor = request.getParameter("idSetor");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        param.put("tahun", tahun);
        param.put("idSetor", idSetor);

        final Integer banyakrinci = setorServices.getCountForRinciBiaya(param);

        final List<SetorRinci> listSppTuRinci = new ArrayList<SetorRinci>(banyakrinci);

        for (int i = 0; i < banyakrinci; i++) {

            final String penanda = "cekpilih" + (i + 1);
            final String idBl = request.getParameter(penanda);

            if (idBl != null && !idBl.isEmpty()) {

                final SetorRinci setorRinci = new SetorRinci();
                setorRinci.setIdBl(SipkdHelpers.getIntFromString(idBl));
                setorRinci.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + idBl)));
                setorRinci.setNilaiSetor(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaisetor" + idBl)));
                setorRinci.setIdEntry(pengguna.getIdPengguna());
                setorRinci.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

                listSppTuRinci.add(setorRinci);
            }
        }
        setor.setSetorRinci(listSppTuRinci);
        setorServices.insertSetorBiaya(setor);

        return "redirect:/setor/indexbiaya";

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

        param.put("tahun", tahunAnggaran);
        param.put("idSetor", idSetor);
        param.put("idskpd", idskpd);

        Integer vtahun = SipkdHelpers.getIntFromString((String) request.getSession().getAttribute("tahunAnggaran"));
        Integer vtahunangg = SipkdHelpers.getIntFromString(tahunAnggaran);

        Integer banyakrinci;

        if (vtahun >= 2017 && vtahunangg >= 2017) {
            banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci")); //setorServices.getCountForAddRinciBtl(param);
        } else {
            banyakrinci = setorServices.getCountForAddRinciBtl(param);
        }

        final List<SetorRinci> listSppTuRinci = new ArrayList<SetorRinci>(banyakrinci);

        for (int i = 0; i < banyakrinci; i++) {
            final SetorRinci setorRinci = new SetorRinci();

            if (vtahun >= 2017 && vtahunangg >= 2017) {
                final Integer penanda = (i + 1);

                //  if (idBl != null && !idBl.isEmpty()) {
                setorRinci.setIdBl(SipkdHelpers.getIntFromString(request.getParameter("idBl" + penanda)));
                setorRinci.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + penanda)));
                setorRinci.setNilaiSetor(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaisetor" + penanda)));
                setorRinci.setIdEntry(pengguna.getIdPengguna());
                setorRinci.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

                listSppTuRinci.add(setorRinci);
                // }
            } else { // POLA LAMA
                final String penanda = "cekpilih" + (i + 1);
                final String idBl = request.getParameter(penanda);
                setor.setNoBku("-1");
                if (idBl != null && !idBl.isEmpty()) {

                    setorRinci.setIdBl(SipkdHelpers.getIntFromString(idBl));
                    setorRinci.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + idBl)));
                    setorRinci.setNilaiSetor(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaisetor" + idBl)));
                    setorRinci.setIdEntry(pengguna.getIdPengguna());
                    setorRinci.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

                    listSppTuRinci.add(setorRinci);
                }
            }

        }
        setor.setSetorRinci(listSppTuRinci);
        setorServices.insertSetorBtl(setor);

        return "redirect:/setor/indexbtl";

    }

    @RequestMapping(value = "/simpansetorbantuan", method = RequestMethod.POST)
    public String prosessimpanbantuan(@Valid @ModelAttribute("refsetor") Setor setor,
            BindingResult result, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = request.getParameter("tahunAngg");

        setor.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        setor.setTahunAngg(SipkdHelpers.getIntFromString(tahunAnggaran));
        setor.setIdEntry(pengguna.getIdPengguna());

        final String idSetor = request.getParameter("idSetor");
        final String kegiatan = request.getParameter("kegiatan");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idSetor", idSetor);
        param.put("kegiatan", kegiatan);

        final int banyakrinci = setorServices.getCountForRinciBantuan(param);
        final List<SetorRinci> listSppTuRinci = new ArrayList<SetorRinci>(banyakrinci);

        for (int i = 0; i < banyakrinci; i++) {

            final String penanda = "cekpilih" + (i + 1);
            final String idBl = request.getParameter(penanda);

            if (idBl != null && !idBl.isEmpty()) {

                final SetorRinci setorRinci = new SetorRinci();
                setorRinci.setIdBl(SipkdHelpers.getIntFromString(idBl));
                setorRinci.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + idBl)));
                setorRinci.setNilaiSetor(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaisetor" + idBl)));
                setorRinci.setIdEntry(pengguna.getIdPengguna());
                setorRinci.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

                listSppTuRinci.add(setorRinci);
            }
        }
        setor.setSetorRinci(listSppTuRinci);
        setorServices.insertSetorBantuan(setor);

        return "redirect:/setor/indexbantuan";
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

    @RequestMapping(value = "/json/getdatabantuan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsetorjsonbantuan(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String kegiatan = request.getParameter("kegiatan");

        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(3);
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {

        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            param.put("tahun", tahunAnggaran);
            param.put("kegiatan", kegiatan);
        }

        final Map< String, Object> mapData = new HashMap<String, Object>(3);
        mapData.put("cek", 1);
        mapData.put("aData", setorServices.getKegiatanBantuan(param));

        return mapData;

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

    @RequestMapping(value = "/json/getdatakegiatanbantuan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getdatakegiatanbantuan(final HttpServletRequest request) {

        final Integer idSetor = SipkdHelpers.getIntFromString(request.getParameter("idSetor"));
        final Map< String, Object> param = new HashMap<String, Object>(6);

        request.setAttribute("isall", 0);
        param.put("idSetor", idSetor);

        final Map< String, Object> mapData = new HashMap<String, Object>(5);
        mapData.put("cek", 1);
        mapData.put("aData", setorServices.getKegiatanBantuanById(idSetor));

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

    @RequestMapping(value = "/json/getforsetorrincibantuan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsonsbantuan(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idSetor = request.getParameter("idSetor");

        final String idskpd = request.getParameter("idskpd");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String id = request.getParameter("id");
        final String kegiatan = request.getParameter("kegiatan");

        param.put("id", id);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("kegiatan", kegiatan);
        param.put("idSetor", idSetor);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final Integer banyak = setorServices.getCountForRinciBantuan(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getForRinciBantuan(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getforsetorrincibiaya", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsonsbiaya(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idSetor = request.getParameter("idSetor");

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

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final Integer banyak = setorServices.getCountForRinciBiaya(param);

        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getForRinciBiaya(param));
        return mapData;
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

    @RequestMapping(value = "/json/getforaddsetorrincibiaya", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsonsaddbiaya(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idSetor = request.getParameter("idSetor");

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

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final Integer banyak = setorServices.getCountForAddRinciBiaya(param);

        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getForAddRinciBiaya(param));
        return mapData;
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

    @RequestMapping(value = "/json/getforeditbantuan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpjsonsbantuan(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idSetor = request.getParameter("idSetor");
        final String kegiatan = setorServices.getKodeKegiatanBantuan(idSetor);

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
        param.put("kegiatan", kegiatan);
        param.put("idSetor", idSetor);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final Integer banyak = setorServices.getCountForRinciBantuan(param);

        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getForRinciBantuan(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getforeditbiaya", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpjsonsbiaya(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idSetor = request.getParameter("idSetor");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idSetor", idSetor);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final Integer banyak = setorServices.getCountEditSetorBiaya(param);

        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getEditSetorBiaya(param));
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

    @RequestMapping(value = "/json/getlistsetorrincibanyakbiaya ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getlistspjrincibanyakbiaya(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String idSetor = request.getParameter("idSetor");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idSetor", idSetor);

        return setorServices.getCountForRinciBiaya(param);
    }

    @RequestMapping(value = "/json/getlistsetorrincibanyakbtl ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getlistspjrincibanyakbtl(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String idSetor = request.getParameter("idSetor");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idSetor", idSetor);
        param.put("idskpd", idskpd);

        return setorServices.getCountForRinciBtl(param);
    }

    @RequestMapping(value = "/json/getlistaddsetorrincibanyakbiaya ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getlistspjaddrincibanyakbiaya(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String idSetor = request.getParameter("idSetor");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idSetor", idSetor);

        return setorServices.getCountForAddRinciBiaya(param);
    }

    @RequestMapping(value = "/json/getlistaddsetorrincibanyakbtl ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getlistspjaddrincibanyakbtl(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String idSetor = request.getParameter("idSetor");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idSetor", idSetor);
        param.put("idskpd", idskpd);

        return setorServices.getCountForAddRinciBtl(param);
    }

    @RequestMapping(value = "/json/getlistsetorrincibanyakbantuan/{kegiatan}", method = RequestMethod.GET)
    public @ResponseBody
    Integer getlistspjrincibanyakbantuan(@PathVariable String kegiatan, final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String idSetor = request.getParameter("idSetor");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idSetor", idSetor);
        param.put("kegiatan", kegiatan);

        final Integer banyak = setorServices.getCountForRinciBantuan(param);

        return setorServices.getCountForRinciBantuan(param);
    }

    @RequestMapping(value = "/json/getidsetor ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getidsetorforbiaya() {
        return setorServices.getIdSetorBiaya();

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

        if ("1".equals(setor.getKodeSA())) {
            final String nilai = setorServices.getNilaiSetorSA(idsetor).toString();

            setor.setNilaiSetor(SipkdHelpers.getBigDecimalFromString(nilai));
        }

        return new ModelAndView("spj/setor/editsetor", "refsetor", setor);
    }

    @RequestMapping(value = "/editsetortu/{idsetor}", method = RequestMethod.GET)
    public ModelAndView edittu(@PathVariable Integer idsetor, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();

        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        final Setor setor = setorServices.getSetorById(idsetor);

        return new ModelAndView("spj/setor/editsetortu", "refsetor", setor);
    }

    @RequestMapping(value = "/editsetorbantuan/{idsetor}", method = RequestMethod.GET)
    public ModelAndView editbantuan(@PathVariable Integer idsetor, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(3);

        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        final Setor setor = setorServices.getSetorById(idsetor);

        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Integer idskpd = listSkpd.get(0).getIdSkpd();

        param.put("idskpd", idskpd);
        param.put("tahun", tahunAnggaran);
        param.put("idSetor", idsetor);

        final List<SetorRinci> listsetorrinci = setorServices.getKegiatanById(param);

        return new ModelAndView("spj/setor/editsetorbantuan", "refsetor", setor);
    }

    @RequestMapping(value = "/editsetorupgu/{idsetor}", method = RequestMethod.GET)
    public ModelAndView editupgu(@PathVariable Integer idsetor, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Integer idskpd = listSkpd.get(0).getIdSkpd();
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idsetor", idsetor);

        //final BigDecimal sisaTU = SipkdHelpers.getBigDecimalFromString((setorServices.getNilaiSisaTU(param)));
        //request.setAttribute("nilaisisaTU", sisaTU);
        final BigDecimal sisaUP = SipkdHelpers.getBigDecimalFromString((setorServices.getNilaiSisaUP(param)));
        request.setAttribute("nilaisisaUP", sisaUP);

        final Setor setor = setorServices.getSetorByIdUP(idsetor);
        return new ModelAndView("spj/setor/editsetorupgu", "refsetor", setor);
    }

    @RequestMapping(value = "/editsetorbiaya/{idsetor}", method = RequestMethod.GET)
    public ModelAndView editbiaya(@PathVariable Integer idsetor, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        final Setor setor = setorServices.getSetorByIdBiaya(idsetor);
        return new ModelAndView("spj/setor/editsetorbiaya", "refsetor", setor);
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
        return new ModelAndView("spj/setor/editsetorbtl", "refsetor", setor);
    }

    @RequestMapping(value = "/deletesetor/{idsetor}", method = RequestMethod.GET)
    public ModelAndView deletesetor(@PathVariable Integer idsetor, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        final Setor setor = setorServices.getSetorById(idsetor);
        return new ModelAndView("spj/setor/deletesetor", "refsetor", setor);
    }

    @RequestMapping(value = "/hapussetor", method = RequestMethod.POST)
    public String delete(@Valid @ModelAttribute("refsetor") Setor setor, BindingResult result) {
        setorServices.deleteSetorMaster(setor.getId());
        return "redirect:/setor";
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

    @RequestMapping(value = "/json/getlistsetoreditbanyakbiaya ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getlistsetoreditbanyakbiaya(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idSetor = request.getParameter("idSetor");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idSetor", idSetor);
        return setorServices.getCountEditSetorBiaya(param);
    }

    @RequestMapping(value = "/simpansetor2", method = RequestMethod.POST)
    public String prosesup(@Valid @ModelAttribute("refsetor") Setor setor,
            BindingResult result, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = request.getParameter("tahunAngg");
        final String kodeSA = request.getParameter("kodeSA");
        log.debug("************ EDIT SALDO AWAL, KODE SA ==========>>>> "+kodeSA);

        setor.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        setor.setTahunAngg(SipkdHelpers.getIntFromString(tahunAnggaran));
        setor.setIdEntry(pengguna.getIdPengguna());
        setor.setIdSetor(SipkdHelpers.getIntFromString(request.getParameter("idSetor")));

        if ("1".equals(kodeSA)) {
            log.debug("************ MASUK IF SA == 1 ==========>>>> ");
            final SetorRinci setorRinci = new SetorRinci();
            setorRinci.setNilaiSetor(setor.getNilaiSetor());

            setorRinci.setIdEntry(pengguna.getIdPengguna());
            setorRinci.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
            setorRinci.setBeban(setor.getBeban());
            setorRinci.setIdSetor(setor.getIdSetor());
            List<SetorRinci> listsetorRinci = new ArrayList<>();
            listsetorRinci.add(setorRinci);
            setor.setSetorRinci(listsetorRinci);

            setorServices.updateSetorUPGU(setor);
        } else {
            log.debug("************ MASUK ELSE SA == 0 ==========>>>> ");
            final Map< String, Object> param = new HashMap<String, Object>(6);

            final List<Skpd> listSkpd = pengguna.getSkpd();
            final Integer idskpd = listSkpd.get(0).getIdSkpd();
            final String idkegiatan = request.getParameter("idkegiatan");

            param.put("tahun", tahunAnggaran);
            param.put("idskpd", idskpd);
            param.put("idkegiatan", idkegiatan);

            //final Integer banyakrinci = setorServices.getCountForRinci(param);
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final List<SetorRinci> listSppTuRinci = new ArrayList<SetorRinci>(banyakrinci);

            Integer vtahun = SipkdHelpers.getIntFromString((String) request.getSession().getAttribute("tahunAnggaran"));
            Integer vtahunangg = SipkdHelpers.getIntFromString(tahunAnggaran);

            for (int i = 0; i < banyakrinci; i++) {
                final SetorRinci setorRinci = new SetorRinci();

                if (vtahun >= 2017 && vtahunangg >= 2017) {
                    final Integer penanda = (i + 1);
                    setorRinci.setIdBl(SipkdHelpers.getIntFromString(request.getParameter("idbl" + penanda)));
                    setorRinci.setIdkegiatan(SipkdHelpers.getIntFromString(request.getParameter("idKegiatan")));
                    setorRinci.setIdsetor(SipkdHelpers.getIntFromString(request.getParameter("idSetor")));
                    setorRinci.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + penanda)));
                    setorRinci.setNilaiSetor(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaisetor" + penanda)));
                    setorRinci.setIdEntry(pengguna.getIdPengguna());
                    setorRinci.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

                    listSppTuRinci.add(setorRinci);

                } else { // POLA LAMA
                    setor.setNoBku("-1");

                    final String penanda = "cekpilih" + (i + 1);
                    final String idBl = request.getParameter(penanda);

                    if (idBl != null && !idBl.isEmpty()) {

                        setorRinci.setIdBl(SipkdHelpers.getIntFromString(idBl));
                        setorRinci.setIdkegiatan(SipkdHelpers.getIntFromString(request.getParameter("idkegiatan")));
                        setorRinci.setIdsetor(SipkdHelpers.getIntFromString(request.getParameter("idSetor")));
                        setorRinci.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + idBl)));
                        setorRinci.setNilaiSetor(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaisetor" + idBl)));
                        setorRinci.setIdEntry(pengguna.getIdPengguna());
                        setorRinci.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

                        listSppTuRinci.add(setorRinci);
                    }
                }

            }
            setor.setSetorRinci(listSppTuRinci);
            setorServices.updateSetor(setor);
        }

        return "redirect:/setor";

    }

    @RequestMapping(value = "/simpansetor2tu", method = RequestMethod.POST)
    public String updatesetortu(@Valid @ModelAttribute("refsetor") Setor setor,
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

        //final Integer banyakrinci = setorServices.getCountForRinci(param);
        final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
        final List<SetorRinci> listSppTuRinci = new ArrayList<SetorRinci>(banyakrinci);

        for (int i = 0; i < banyakrinci; i++) {

            final String penanda = "cekpilih" + (i + 1);
            final String idBl = request.getParameter(penanda);

            if (idBl != null && !idBl.isEmpty()) {

                final SetorRinci setorRinci = new SetorRinci();

                setorRinci.setIdBl(SipkdHelpers.getIntFromString(idBl));
                setorRinci.setIdkegiatan(SipkdHelpers.getIntFromString(request.getParameter("idKegiatan")));
                setorRinci.setIdsetor(SipkdHelpers.getIntFromString(request.getParameter("idSetor")));
                setorRinci.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + idBl)));
                setorRinci.setNilaiSetor(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaisetor" + idBl)));
                setorRinci.setIdEntry(pengguna.getIdPengguna());
                setorRinci.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

                listSppTuRinci.add(setorRinci);
            }
        }
        setor.setSetorRinci(listSppTuRinci);
        setorServices.updateSetor(setor);

        return "redirect:/setor/indextu";

    }

    @RequestMapping(value = "/simpansetor2bantuan", method = RequestMethod.POST)
    public String prosesupdatebantuan(@Valid @ModelAttribute("refsetor") Setor setor,
            BindingResult result, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = request.getParameter("tahunAngg");

        setor.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        setor.setTahunAngg(SipkdHelpers.getIntFromString(tahunAnggaran));
        setor.setIdEntry(pengguna.getIdPengguna());
        setor.setIdSetor(SipkdHelpers.getIntFromString(request.getParameter("idSetor")));

        final Map< String, Object> param = new HashMap<String, Object>(6);

        final String idSetor = request.getParameter("idSetor");
        final String kegiatan = request.getParameter("kegiatan");

        param.put("tahun", tahunAnggaran);
        param.put("idSetor", idSetor);
        param.put("kegiatan", kegiatan);

        final Integer banyakrinci = setorServices.getCountForRinciBantuan(param);
        final List<SetorRinci> listSppTuRinci = new ArrayList<SetorRinci>(banyakrinci);

        for (int i = 0; i < banyakrinci; i++) {

            final String penanda = "cekpilih" + (i + 1);
            final String idBl = request.getParameter(penanda);

            if (idBl != null && !idBl.isEmpty()) {

                final SetorRinci setorRinci = new SetorRinci();

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
        setorServices.updateSetorBantuan(setor);

        return "redirect:/setor/indexbantuan";
    }

    @RequestMapping(value = "/simpansetor2biaya", method = RequestMethod.POST)
    public String prosesupdatebiaya(@Valid @ModelAttribute("refsetor") Setor setor,
            BindingResult result, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = request.getParameter("tahunAngg");

        setor.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        setor.setTahunAngg(SipkdHelpers.getIntFromString(tahunAnggaran));
        setor.setIdEntry(pengguna.getIdPengguna());
        setor.setIdSetor(SipkdHelpers.getIntFromString(request.getParameter("idSetor")));

        final String idSetor = request.getParameter("idSetor");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        param.put("tahun", tahun);
        param.put("idSetor", idSetor);

        final Integer banyakrinci = setorServices.getCountForRinciBiaya(param);

        final List<SetorRinci> listSppTuRinci = new ArrayList<SetorRinci>(banyakrinci);

        for (int i = 0; i < banyakrinci; i++) {

            final String penanda = "cekpilih" + (i + 1);
            final String idBl = request.getParameter(penanda);

            if (idBl != null && !idBl.isEmpty()) {

                final SetorRinci setorRinci = new SetorRinci();
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

        setorServices.updateSetorBiaya(setor);

        return "redirect:/setor/indexbiaya";
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

        if (vtahun >= 2017 && vtahunangg >= 2017) {
            banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci")); //setorServices.getCountForAddRinciBtl(param);
        } else {
            banyakrinci = setorServices.getCountForRinciBtl(param);
        }

        final List<SetorRinci> listSppTuRinci = new ArrayList<SetorRinci>(banyakrinci);

        for (int i = 0; i < banyakrinci; i++) {
            final SetorRinci setorRinci = new SetorRinci();

            if (vtahun >= 2017 && vtahunangg >= 2017) {
                final Integer penanda = (i + 1);

                setorRinci.setIdBl(SipkdHelpers.getIntFromString(request.getParameter("idBl" + penanda)));
                setorRinci.setIdsetor(SipkdHelpers.getIntFromString(request.getParameter("idSetor")));
                setorRinci.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idAkun" + penanda)));
                setorRinci.setNilaiSetor(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaisetor" + penanda)));
                setorRinci.setIdEntry(pengguna.getIdPengguna());
                setorRinci.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

                listSppTuRinci.add(setorRinci);

            } else { // POLA LAMA
                setor.setNoBku("-1");

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

        }
        setor.setSetorRinci(listSppTuRinci);

        setorServices.updateSetorBtl(setor);

        return "redirect:/setor/indexbtl";
    }

    @RequestMapping(value = "/simpansetor2upgu", method = RequestMethod.POST)
    public String prosesupdateupgu(@Valid @ModelAttribute("refsetor") Setor setor,
            BindingResult result, final HttpServletRequest request) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = request.getParameter("tahunAngg");

        setor.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        setor.setTahunAngg(SipkdHelpers.getIntFromString(tahunAnggaran));
        setor.setIdEntry(pengguna.getIdPengguna());

        final SetorRinci setorRinci = new SetorRinci();
        setorRinci.setNilaiSetor(setor.getNilaiSetor());

        setorRinci.setIdEntry(pengguna.getIdPengguna());
        setorRinci.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        setorRinci.setBeban(setor.getBeban());
        setorRinci.setIdKegiatan(setor.getIdKegiatan());
        setorRinci.setIdSetor(setor.getIdSetor());
        List<SetorRinci> listsetorRinci = new ArrayList<>();
        listsetorRinci.add(setorRinci);
        setor.setSetorRinci(listsetorRinci);

        setorServices.updateSetorUPGU(setor);

        return "redirect:/setor/indexupgu";
    }

    @RequestMapping(value = "/listkegiatantu", method = RequestMethod.GET)
    public ModelAndView listakunbukubesar(final SetorRinci setor, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("spj/setor/listkegiatantu", "refkegiatan", setor);
    }

    @RequestMapping(value = "/json/listkegiatanjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listkegiatanjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kode = request.getParameter("kode");
        final String nama = request.getParameter("nama");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("nama", nama);
        param.put("kode", kode);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = setorServices.getBanyakKegiatanTU(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getKegiatanTU(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getSisaTU", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSisaTU(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idkegiatan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idkegiatan", idkegiatan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", setorServices.getNilaiSisaTU(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getBebanSetorUP", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getBebanSetorUP(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", setorServices.getBebanSetorUP(param));

        return mapData;
    }

    @RequestMapping(value = "/listkegiatanls", method = RequestMethod.GET)
    public ModelAndView listkegiatanls(final SetorRinci setor, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("spj/setor/listkegiatan", "refkegiatan", setor);
    }

    @RequestMapping(value = "/json/listkegiatanlsjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listkegiatanlsjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kode = request.getParameter("kode");
        final String nama = request.getParameter("nama");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String jenis = request.getParameter("jenis");
        final String idsetor = request.getParameter("idsetor");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("nama", nama);
        param.put("kode", kode);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("idsetor", idsetor);
        param.put("jenis", jenis);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = setorServices.getBanyakKegiatanLS(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getKegiatanLS(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getbanyaklistblls", method = RequestMethod.GET)
    public @ResponseBody
    Integer getbanyaklistblls(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String nobku = request.getParameter("nobku");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("nobku", nobku);
        param.put("idkegiatan", idkegiatan);
        return setorServices.getBanyakListBlLs(param);
    }

    @RequestMapping(value = "/json/listsetorblls", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsetorblls(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String nobku = request.getParameter("nobku");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("nobku", nobku);
        param.put("idkegiatan", idkegiatan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = setorServices.getBanyakListBlLs(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getListBlLs(param));

        return mapData;
    }

    @RequestMapping(value = "/listsbbtl", method = RequestMethod.GET)
    public ModelAndView listsbbtl(final SetorRinci setor, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("spj/setor/listsbbtl", "refkegiatan", setor);
    }

    @RequestMapping(value = "/json/listkegiatanbtljson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listkegiatanbtljson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kode = request.getParameter("kode");
        final String nama = request.getParameter("nama");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String idsetor = request.getParameter("idsetor");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("nama", nama);
        param.put("kode", kode);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("idsetor", idsetor);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = setorServices.getBanyakKegiatanBTL(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getKegiatanBTL(param));

        return mapData;
    }

    @RequestMapping(value = "/json/listsetorbtlls", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsetorbtlls(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String nobku = request.getParameter("nobku");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("nobku", nobku);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = setorServices.getBanyakListBtlLs(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getListBtlLs(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getbanyaklistbtlls", method = RequestMethod.GET)
    public @ResponseBody
    Integer getbanyaklistbtlls(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String nobku = request.getParameter("nobku");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("nobku", nobku);
        return setorServices.getBanyakListBtlLs(param);
    }

    @RequestMapping(value = "/json/getdatabku", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getdatabku(final HttpServletRequest request) {

        final String tahunAnggaran = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String nobku = request.getParameter("nobku");

        final Map< String, Object> param = new HashMap<String, Object>(6);

        request.setAttribute("isall", 0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("nobku", nobku);

        final Map< String, Object> mapData = new HashMap<String, Object>(5);
        mapData.put("cek", 1);
        mapData.put("aData", setorServices.getDataBku(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getbanyaklisttu", method = RequestMethod.GET)
    public @ResponseBody
    Integer getbanyaklisttu(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String idsetor = request.getParameter("idsetor");
        final String idspd = request.getParameter("idspd");
        final String idkegiatan = request.getParameter("idkegiatan");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idsetor", idsetor);
        param.put("idspd", idspd);
        param.put("idkegiatan", idkegiatan);

        return setorServices.getBanyakListTU(param);
    }

    @RequestMapping(value = "/json/getrincitu", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getrincitu(final HttpServletRequest request) {
        final String tahunAnggaran = request.getParameter("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String idsetor = request.getParameter("idsetor");
        final String idspd = request.getParameter("idspd");
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
        param.put("idsetor", idsetor);
        param.put("idspd", idspd);
        param.put("idkegiatan", idkegiatan);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final Integer banyak = setorServices.getBanyakListTU(param);

        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getListTU(param));
        return mapData;

    }

    @RequestMapping(value = "/json/getSalsoAwalLs", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSalsoAwalLs(final HttpServletRequest request) {

        final String tahunAnggaran = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String idsetor = request.getParameter("idsetor");

        final Map< String, Object> param = new HashMap<String, Object>(6);

        request.setAttribute("isall", 0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idsetor", idsetor);

        final Map< String, Object> mapData = new HashMap<String, Object>(5);
        mapData.put("aData", setorServices.getSalsoAwalLs(param));

        return mapData;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
