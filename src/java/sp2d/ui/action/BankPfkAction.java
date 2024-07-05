package sp2d.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.BankPfk;
import sp2d.services.BankPfkServices;
import sp2d.util.BigDecimalPropertyEditor;
import sp2d.util.SipkdHelpers;
import sp2d.util.SqlDatePropertyEditor;

@Controller
@RequestMapping("/bankpfk")
public class BankPfkAction {

    private static final Logger log = LoggerFactory.getLogger(BankPfkAction.class);
    @Autowired
    BankPfkServices bankServices;

    @RequestMapping(value = "/indexbankpfk", method = RequestMethod.GET)
    public String index(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
            request.setAttribute("pengguna", pengguna);
        }
        return "bankpfk/bankpfk";

    }

    @RequestMapping(value = "/json/getlistbankcabang", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistbankcabang(final HttpServletRequest request) {
        final String idinduk = request.getParameter("idinduk");
        final String nama = request.getParameter("nama");
        final String alamat = request.getParameter("alamat");
        String keterangan = "";

        if (!"".equals(nama) && !"".equals(alamat)) {
            keterangan = "NAMAALAMAT";
        } else if (!"".equals(nama) && "".equals(alamat)) {
            keterangan = "NAMA";
        } else if ("".equals(nama) && !"".equals(alamat)) {
            keterangan = "ALAMAT";
        }

        final Map< String, Object> param = new HashMap<String, Object>(2);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("idinduk", idinduk);
        param.put("keterangan", keterangan);
        param.put("nama", nama);
        param.put("alamat", alamat);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = bankServices.getBanyakBankCabang(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bankServices.getListBankCabang(param));
        return mapData;
    }

    @RequestMapping(value = "/addbankpfk/{idinduk}", method = RequestMethod.GET)
    public ModelAndView addbankpfk(@PathVariable Integer idinduk, BankPfk bank, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        bank = bankServices.getEditBankCabang(idinduk);

        return new ModelAndView("bankpfk/addbankpfk", "refsp2d", bank);

    }

    @RequestMapping(value = "/addbankinduk", method = RequestMethod.GET)
    public ModelAndView addbankinduk(BankPfk bank, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        return new ModelAndView("bankpfk/addbankinduk", "refsp2d", bank);

    }

    @RequestMapping(value = "/editbankpfk/{idinduk}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idinduk, BankPfk bank, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        bank = bankServices.getEditBankCabang(idinduk);

        return new ModelAndView("bankpfk/editbankpfk", "refsp2d", bank);

    }

    @RequestMapping(value = "/deletebankpfk/{idinduk}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Integer idinduk, BankPfk bank, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        bank = bankServices.getEditBankCabang(idinduk);
        return new ModelAndView("bankpfk/deletebankpfk", "refsp2d", bank);

    }

    @RequestMapping(value = "/editbankinduk/{idbank}", method = RequestMethod.GET)
    public ModelAndView editbankinduk(@PathVariable Integer idbank, BankPfk bank, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        bank = bankServices.getEditBankCabang(idbank);

        return new ModelAndView("bankpfk/editbankinduk", "refsp2d", bank);

    }

    @RequestMapping(value = "/deletebankinduk/{idbank}", method = RequestMethod.GET)
    public ModelAndView deletebankinduk(@PathVariable Integer idbank, BankPfk bank, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        bank = bankServices.getEditBankCabang(idbank);
        return new ModelAndView("bankpfk/deletebankinduk", "refsp2d", bank);

    }

    @RequestMapping(value = "/listbankinduk", method = RequestMethod.GET)
    public ModelAndView listbankinduk(final HttpServletResponse response, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        request.setAttribute("pengguna", pengguna);
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");

        return new ModelAndView("bankpfk/listbankinduk");
    }

    @RequestMapping(value = "/json/getlistbankinduk", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistbankinduk(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(2);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String nama = request.getParameter("nama");
        final String kode = request.getParameter("kode");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("nama", nama);
        param.put("kode", kode);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = bankServices.getBanyakBankInduk(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bankServices.getListBankInduk(param));
        return mapData;
    }

    @RequestMapping(value = "/json/prosessimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String kodebanktf = (String) mapdata.get("kodebanktf");
        final String namabanktf = (String) mapdata.get("namabanktf");
        final String kodebank = (String) mapdata.get("kodebank");
        final String namabank = (String) mapdata.get("namabank");
        final String kodebankrtgs = (String) mapdata.get("kodebankrtgs");
        final String namabankrtgs = (String) mapdata.get("namabankrtgs");
        final String kodeaktif = (String) mapdata.get("kodeaktif");
        final String alamat = (String) mapdata.get("alamat");
        final String idinduk = (String) mapdata.get("idinduk");
        final Integer idbank = (Integer) mapdata.get("idbank");

        BankPfk bank = new BankPfk();

        bank.setKodeBankTransfer(kodebanktf);
        bank.setNamaBankTransfer(namabanktf);
        bank.setKodeBank(kodebank);
        bank.setNamaBank(namabank);
        bank.setKodeBankRtgs(kodebankrtgs);
        bank.setNamaBankRtgs(namabankrtgs);
        bank.setKodeAktif(kodeaktif);
        bank.setAlamatBank(alamat);
        bank.setIdInduk(SipkdHelpers.getIntFromString(idinduk));
        bank.setIdBank(idbank);
        bank.setIdEntry(pengguna.getIdPengguna());

        bankServices.insertBankPfkUtama(bank);

        return "Data Bank Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/getIdBankUtama", method = RequestMethod.GET)
    public @ResponseBody
    Integer getIdBankUtama(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);

        return bankServices.getIdBankUtama(param);
    }

    @RequestMapping(value = "/json/prosesupdate", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdate(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String kodebanktf = (String) mapdata.get("kodebanktf");
        final String namabanktf = (String) mapdata.get("namabanktf");
        final String kodebank = (String) mapdata.get("kodebank");
        final String namabank = (String) mapdata.get("namabank");
        final String kodebankrtgs = (String) mapdata.get("kodebankrtgs");
        final String namabankrtgs = (String) mapdata.get("namabankrtgs");
        final String kodeaktif = (String) mapdata.get("kodeaktif");
        final String alamat = (String) mapdata.get("alamat");
        final String idbank = (String) mapdata.get("idbank");

        BankPfk bank = new BankPfk();

        bank.setKodeBankTransfer(kodebanktf);
        bank.setNamaBankTransfer(namabanktf);
        bank.setKodeBank(kodebank);
        bank.setNamaBank(namabank);
        bank.setKodeBankRtgs(kodebankrtgs);
        bank.setNamaBankRtgs(namabankrtgs);
        bank.setKodeAktif(kodeaktif);
        bank.setAlamatBank(alamat);
        bank.setIdBank(SipkdHelpers.getIntFromString(idbank));
        bank.setIdEntry(pengguna.getIdPengguna());

        bankServices.updateBankPfk(bank);

        return "Data Bank Berhasil Diubah";
    }

    @RequestMapping(value = "/json/prosesdelete", method = RequestMethod.POST)
    public @ResponseBody
    String prosesdelete(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String idbank = (String) mapdata.get("idbank");

        BankPfk bank = new BankPfk();

        bank.setIdBank(SipkdHelpers.getIntFromString(idbank));
        bank.setIdEntry(pengguna.getIdPengguna());

        bankServices.deleteBankPfk(bank);

        return "Data RKA Kegiatan Berhasil Dihapus";
    }

    @RequestMapping(value = "/json/prosessimpancabang", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpancabang(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String kodebanktf = (String) mapdata.get("kodebanktf");
        final String namabanktf = (String) mapdata.get("namabanktf");
        final String kodebank = (String) mapdata.get("kodebank");
        final String namabank = (String) mapdata.get("namabank");
        final String kodebankrtgs = (String) mapdata.get("kodebankrtgs");
        final String namabankrtgs = (String) mapdata.get("namabankrtgs");
        final String kodeaktif = (String) mapdata.get("kodeaktif");
        final String alamat = (String) mapdata.get("alamat");
        final String idinduk = (String) mapdata.get("idinduk");

        BankPfk bank = new BankPfk();

        bank.setKodeBankTransfer(kodebanktf);
        bank.setNamaBankTransfer(namabanktf);
        bank.setKodeBank(kodebank);
        bank.setNamaBank(namabank);
        bank.setKodeBankRtgs(kodebankrtgs);
        bank.setNamaBankRtgs(namabankrtgs);
        bank.setKodeAktif(kodeaktif);
        bank.setAlamatBank(alamat);
        bank.setIdInduk(SipkdHelpers.getIntFromString(idinduk));
        bank.setIdEntry(pengguna.getIdPengguna());

        bankServices.insertBankPfk(bank);

        return "Data Bank Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/editcabang", method = RequestMethod.POST)
    public @ResponseBody
    String editcabang(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BankPfk> listbank = new ArrayList<>();

        final String kodebanktf = (String) mapdata.get("kodebanktf");
        final String namabanktf = (String) mapdata.get("namabanktf");
        final String kodebank = (String) mapdata.get("kodebank");
        final String kodebankrtgs = (String) mapdata.get("kodebankrtgs");
        final String namabankrtgs = (String) mapdata.get("namabankrtgs");
        final String kodeaktif = (String) mapdata.get("kodeaktif");
        final String idinduk = (String) mapdata.get("idinduk");

        for (Map<String, Object> mapnilailist : nilailist) {
            BankPfk bank = new BankPfk();

            bank.setKodeBankTransfer(kodebanktf);
            bank.setNamaBankTransfer(namabanktf);
            bank.setKodeBank(kodebank);
            bank.setKodeBankRtgs(kodebankrtgs);
            bank.setNamaBankRtgs(namabankrtgs);
            bank.setKodeAktif(kodeaktif);
            bank.setIdInduk(SipkdHelpers.getIntFromString(idinduk));
            bank.setIdEntry(pengguna.getIdPengguna());

            Object namabank = mapnilailist.get("namabank");
            Object alamat = mapnilailist.get("alamat");
            Object idbank = mapnilailist.get("idbank");

            bank.setAlamatBank(alamat.toString());
            bank.setNamaBank(namabank.toString());
            bank.setIdBank(SipkdHelpers.getIntFromString(idbank.toString()));

            listbank.add(bank);
        }

        bankServices.updateBankCabang(listbank);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
