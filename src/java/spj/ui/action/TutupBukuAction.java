/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
package spj.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import spp.model.LRA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.TutupBuku;
import spj.services.TutupBukuServices;
import spj.util.BigDecimalPropertyEditor;
import spj.util.SipkdHelpers;

import spj.util.SqlDatePropertyEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/tutupbuku")
public class TutupBukuAction {

    private static final Logger log = LoggerFactory.getLogger(TutupBukuAction.class);

    @Autowired
    TutupBukuServices tutupbukuServices;

    @RequestMapping(value = "/tutupbku", method = RequestMethod.GET)
    public ModelAndView add(final TutupBuku tutupbuku, final HttpServletRequest request, Model model) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());

            tutupbuku.setIdskpd(listSkpd.get(0).getIdSkpd());
        }

        final Integer idskpd = tutupbuku.getIdskpd();
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        //log.debug("TUTUP BUKU - TAHUN ============ "+tahun);
        //log.debug("TUTUP BUKU - IDSKPD =========== "+idskpd);
        final Map< String, Object> param = new HashMap<String, Object>(6);

        param.put("idskpd", idskpd);
        param.put("tahun", tahun);

        TutupBuku bku = tutupbukuServices.getBendahara(param);

        final List<TutupBuku> bulan = tutupbukuServices.getBulanTutup(param);
        model.addAttribute("listBulan", bulan);

        return new ModelAndView("tutupbuku/tutupbuku", "refbku", bku);
    }

    @RequestMapping(value = "/tutupbkupop", method = RequestMethod.GET)
    public ModelAndView tutupbkupop(final HttpServletResponse response, final TutupBuku tutupbuku, final HttpServletRequest request, Model model) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("cie", listSkpd.get(0).getIdSkpd());

            tutupbuku.setIdskpd(listSkpd.get(0).getIdSkpd());
        }

        final Integer idskpd = tutupbuku.getIdskpd();
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        final Map< String, Object> param = new HashMap<String, Object>(6);

        param.put("idskpd", idskpd);
        param.put("tahun", tahun);

        TutupBuku bku = new TutupBuku();
        //TutupBuku bku = tutupbukuServices.getBendahara(param);

        //final List<TutupBuku> bulan = tutupbukuServices.getBulanTutup(param);
        //model.addAttribute("listBulan", bulan);
        return new ModelAndView("tutupbuku/tutupbukupop", "refbku", bku);
    }

    @RequestMapping(value = "/json/getnilaikas ", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getvalidasi(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String bulan = request.getParameter("bulan");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        String kodewilproses = "0";
        if (idskpd.equals("761") || idskpd.equals("1234")) {
            if (pengguna.getKodeGrup().equals("11")) {
                kodewilproses = request.getParameter("kodewilproses");
            } else {
                kodewilproses = pengguna.getKodeProses();
            }
        }

        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("idskpd", idskpd);
        param.put("tahun", tahun);
        param.put("bulan", bulan);
        param.put("kodeWilProses", kodewilproses);

        final Map<String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("aData", tutupbukuServices.getNilaiKas(param));
        return mapData;

    }

    @RequestMapping(value = "/json/getnilaisaldo ", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getsaldo(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String bulan = request.getParameter("bulan");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        String kodewilproses = "0";
        if (idskpd.equals("761") || idskpd.equals("1234")) {
            if (pengguna.getKodeGrup().equals("11")) {
                kodewilproses = request.getParameter("kodewilproses");
            } else {
                kodewilproses = pengguna.getKodeProses();
            }
        }

        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("idskpd", idskpd);
        param.put("tahun", tahun);
        param.put("bulan", bulan);
        param.put("kodeWilProses", kodewilproses);

        final Map<String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("aData", tutupbukuServices.getNilaiSaldo(param));
        return mapData;

    }

    @RequestMapping(value = "/json/getBendahara", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getBendahara(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String idskpd = request.getParameter("idskpd");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("idskpd", idskpd);
        param.put("tahun", tahun);
        param.put("kodewil", pengguna.getKodeProses());

        final Map<String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("aData", tutupbukuServices.getBendahara(param));
        return mapData;

    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpan(@Valid @ModelAttribute("refbku") TutupBuku tutupbuku, BindingResult result,
            final HttpServletRequest request, final RedirectAttributes redirectAttributes) {

        final StringBuilder sburl = new StringBuilder("redirect:/tutupbuku/tutupbku");

        /*if (result.hasErrors()) {
         return "/tutupbuku/tutupbuku";
         } else { */
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        tutupbuku.setIdskpd(listSkpd.get(0).getIdSkpd());
        tutupbuku.setIdEntry(pengguna.getIdPengguna());

        tutupbukuServices.insertTutupBku(tutupbuku);
        //}

        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                .append(" berhasil disimpan ")
                .toString());

        return sburl.toString();

    }

    @RequestMapping(value = "/json/prosessimpan2", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan2(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        final String idskpd = (String) mapdata.get("idskpd");
        String kodewilproses = "0";
        if (idskpd.equals("761") || idskpd.equals("1234")) {
            if (pengguna.getKodeGrup().equals("11")) {
                kodewilproses = (String) mapdata.get("kodewilproses");
            } else {
                kodewilproses = pengguna.getKodeProses();
            }
        }

        final String kodetomboltutupbuku = (String) mapdata.get("kodetombol");
        //log.debug("kode tombol == "+kodetombol);

        final String bulan = (String) mapdata.get("bulan");
        final String tglpenutupan = (String) mapdata.get("tglpenutupan");
        final String nrkpa = (String) mapdata.get("nrkpa");
        final String nippa = (String) mapdata.get("nippa");
        final String namapa = (String) mapdata.get("namapa");
        final String nrkbend = (String) mapdata.get("nrkbend");
        final String nipbend = (String) mapdata.get("nipbend");
        final String namabend = (String) mapdata.get("namabend");
        final String kasterimalalu = mapdata.get("kasterimalalu").toString();
        final String kaskeluarlalu = mapdata.get("kaskeluarlalu").toString();
        final String kasterima = mapdata.get("kasterima").toString();
        final String kaskeluar = mapdata.get("kaskeluar").toString();
        final String kassaatini = mapdata.get("kassaatini").toString();
        final String saldotunai = mapdata.get("saldotunai").toString();
        final String saldobank = mapdata.get("saldobank").toString();
        final String saldolain = mapdata.get("saldolain").toString();

        //log.debug("kasterima int===== "+mapdata.get("kasterima"));
        //log.debug("kasterima string===== "+kasterima);
        final TutupBuku tutupbuku = new TutupBuku();

        tutupbuku.setIdEntry(pengguna.getIdPengguna());
        tutupbuku.setTahun(tahun);
        tutupbuku.setIdskpd(SipkdHelpers.getIntFromString(idskpd));
        tutupbuku.setBulan(bulan);
        tutupbuku.setTglPenutupan(tglpenutupan);
        tutupbuku.setNrkPA(nrkpa);
        tutupbuku.setNipPA(nippa);
        tutupbuku.setNamaPA(namapa);
        tutupbuku.setNrkBendahara(nrkbend);
        tutupbuku.setNipBendahara(nipbend);
        tutupbuku.setNamaBendahara(namabend);
        tutupbuku.setKasTerimaLalu(SipkdHelpers.getBigDecimalFromString(kasterimalalu));
        tutupbuku.setKasKeluarLalu(SipkdHelpers.getBigDecimalFromString(kaskeluarlalu));
        tutupbuku.setKasTerima(SipkdHelpers.getBigDecimalFromString(kasterima));
        tutupbuku.setKasKeluar(SipkdHelpers.getBigDecimalFromString(kaskeluar));
        tutupbuku.setKasSaatIni(SipkdHelpers.getBigDecimalFromString(kassaatini));
        tutupbuku.setSaldoTunai(SipkdHelpers.getBigDecimalFromString(saldotunai));
        tutupbuku.setSaldoBank(SipkdHelpers.getBigDecimalFromString(saldobank));
        tutupbuku.setSaldoLain(SipkdHelpers.getBigDecimalFromString(saldolain));

        tutupbuku.setKodeWilProses(kodewilproses);

        if (tutupbukuServices.getBanyakDataBKUPengeluaran(tutupbuku) == 0) { // dicek jika di bku pengeluaran tidak ada data dengan parameter idskpd, tahun, bulan
            tutupbuku.setKodeNihil("1");//kode nihil 1
        } else {
            tutupbuku.setKodeNihil("0");//kode nihil 0
        }

        tutupbuku.setKdTombolTutupBuku(kodetomboltutupbuku);
        /*
         //cek jika 0=draft ; 1=tutup buku
         if(kodetomboltutupbuku.equals("0")){
         tutupbuku.setBkuProses(null);
         }
         else{
         tutupbuku.setBkuProses(new Timestamp(System.currentTimeMillis()));
         }
         */
        tutupbukuServices.insertTutupBku(tutupbuku, kodetomboltutupbuku);

        return "Tutup BKU Pengeluaran Berhasil Disimpan";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
