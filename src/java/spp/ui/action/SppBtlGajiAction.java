package spp.ui.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spp.model.Akun;
import spp.model.Pengguna;
import spp.model.Skpd;
import spp.model.SppBtl;
import spp.model.SppBtlRinci;
import spp.services.ReferensiServices;
import spp.services.SpdBLServices;
import spp.services.SppBtlGajiServices;
import spp.util.BigDecimalPropertyEditor;
import spp.util.SipkdHelpers;
import spp.util.SqlDatePropertyEditor;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/btlgaji")
public class SppBtlGajiAction {

    private static final Logger log = LoggerFactory.getLogger(SppBtlGajiAction.class);

    @Autowired
    SppBtlGajiServices sppBtlServices;

    @Autowired
    ReferensiServices referensiServices;

    @Autowired
    SpdBLServices spdBLServices;

    @RequestMapping(value = "/indexbtl", method = RequestMethod.GET)
    public String index(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));
        }
        return "spp/btlgaji/indexbtl";

    }
    
    @RequestMapping(value = "/listgaji", method = RequestMethod.GET)
    public ModelAndView listgaji(final SppBtl spp, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("spp/btlgaji/listgaji", "refkegiatan", spp);
    }
    
    @RequestMapping(value = "/json/getlistgaji", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistgaji(final HttpServletRequest request) {
        
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String jenis = request.getParameter("jenis");
        final String macam = request.getParameter("macam");
        
        final Map< String, Object> param = new HashMap<String, Object>(2);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("jenis", jenis);
        param.put("macam", macam);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = sppBtlServices.getBanyakListGaji(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppBtlServices.getListGaji(param));
        return mapData;
    }
    
    @RequestMapping(value = "/json/prosessimpanlistpegrekap", method = RequestMethod.POST)
    public @ResponseBody
    Integer prosessimpanlistpegrekap(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        //List<KontrakRinci> listRinci = new ArrayList<>();

        final String idskpd = (String) mapdata.get("idskpd");
        final String bulan = (String) mapdata.get("bulan");
        final String tglpublish = (String) mapdata.get("tglpublish");
        final String macam = (String) mapdata.get("macam");
        final String jenis = (String) mapdata.get("jenis");
        
        log.debug("=================LOG DATA PEG REKAP===================");
        log.debug("IDSKPD =======> " + idskpd);
        log.debug("BULAN ========> " + bulan);
        log.debug("TGLPUBLISH ===> " + SipkdHelpers.getStringDateFormatFromString(tglpublish, "dd/MM/yyyy", "dd-MMM-yy"));
        log.debug("MACAM ========> " + macam);
        log.debug("JENIS ========> " + jenis);
        
        final Map< String, Object> param = new HashMap<String, Object>(5);
        param.put("idskpd", idskpd);
        param.put("bulan", bulan);
        param.put("tglpublish", SipkdHelpers.getStringDateFormatFromString(tglpublish, "dd/MM/yyyy", "dd-MMM-yy"));
        param.put("macam", macam);
        param.put("jenis", jenis);
        
        
        //final String idkegiatan = (String) mapdata.get("idkegiatan");
        //final String idkontrak = (String) mapdata.get("idkontrak");
        
//        for (Map<String, Object> mapnilailist : nilailist) {
//            KontrakRinci bukukas = new KontrakRinci();
//
//            Object penanda = mapnilailist.get("penanda");
//            Integer valid = SipkdHelpers.getIntFromString(penanda.toString());
//
//            if (valid == 1) {
//                bukukas.setTahun(tahun);
//                bukukas.setIdSkpd(SipkdHelpers.getIntFromString(idskpd.toString()));
//                bukukas.setIdEntry(pengguna.getIdPengguna());
//                bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan));
//                bukukas.setIdKontrak(SipkdHelpers.getIntFromString(idkontrak));
//
//                Object nilairinci = mapnilailist.get("nilairinci");
//                Object nilaiumk = mapnilailist.get("nilaiumk");
//                Object idbas = mapnilailist.get("idbas");
//                
//                bukukas.setNilaiKontrak(SipkdHelpers.getBigDecimalFromString(nilairinci.toString()));
//                bukukas.setNilaiUmk(SipkdHelpers.getBigDecimalFromString(nilaiumk.toString()));
//                bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
//                
//                listRinci.add(bukukas);
//            }
//        }

        //kontrakServices.insertKontrakRinci(listRinci);

        return sppBtlServices.insertPegRekap(param); //"Data Kontrak Rinci Berhasil Disimpan";
    }
    
    @RequestMapping(value = "/addsppbtl/{idskpd}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable Integer idskpd, final SppBtl sppBtl, final HttpServletRequest request, BindingResult result) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);

        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final Map<String, Object> paramBank = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            paramBank.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBank.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            final Map<String, String> dataBank = sppBtlServices.getBankRekByIdSkpd(paramBank);
            final Map<String, String> dataBankDki = sppBtlServices.getBankDki(paramBank); // data bank nya diganti jadi BANK DKI
            //log.debug(" >>>>>>>>>>>>>  " + dataBank.toString());

            if (dataBankDki != null) {
                sppBtl.setKodeBank(dataBankDki.get("C_BANK"));
                sppBtl.setKodeBankTransfer(dataBankDki.get("C_BANK_TRANSFER"));
                sppBtl.setNamaBankTransfer(dataBankDki.get("N_BANK_TRANSFER"));
                sppBtl.setIdBank(dataBankDki.get("I_ID").toString());
            }
            
            if (dataBank != null) {
                //sppBtl.setKodeBank(dataBank.get("C_BANK"));
                //sppBtl.setNamaBank(dataBank.get("N_BANK"));
                sppBtl.setNomorRekBank(dataBank.get("I_REK_BANKSPM"));
            }
            request.setAttribute("isall", 0);

            final Map<String, Object> paramBendahara = new HashMap<String, Object>();
            paramBendahara.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBendahara.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            final Map<String, String> dataBendahara = sppBtlServices.getBendaharaByIdSkpd(paramBendahara);

            if (dataBendahara != null) {
                sppBtl.setNipPptk(dataBendahara.get("I_NIP_PKBLJ"));
                sppBtl.setNamaPptk(dataBendahara.get("N_PKBLJ"));
            }

        }

        sppBtl.setSkpd(referensiServices.getDetailSkpdById(idskpd));
        return new ModelAndView("spp/btlgaji/addsppbtl", "refsppbtl", sppBtl);

    }
    
    @RequestMapping(value = "/json/getlistsppbtl", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistsppbtl(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final String tahunAnggaran = request.getParameter("tahun");
        final String skpd = request.getParameter("namaskpd");
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
        param.put("idskpd", idskpd);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = sppBtlServices.getBanyakSppBtl(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppBtlServices.getAllSppBtl(param));
        return mapData;
    }
    
    @RequestMapping(value = "/json/getlistrincibtl", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspd(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kodesimpeg = request.getParameter("kodesimpeg");
        param.put("kodesimpeg", kodesimpeg);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("idspp", SipkdHelpers.getIntFromString(request.getParameter("idspp")));
        //log.debug(param.toString());
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        //log.debug(param.toString());
        final long banyak = sppBtlServices.getBanyakRinciBtl(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sppBtlServices.getAllRinciBtl(param));
        return mapData;

    }
    
    @RequestMapping(value = "/json/getNilaiSPP/{id}/{kdsimpeg}", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> getNilaiSPP(@PathVariable Integer id, @PathVariable Integer kdsimpeg, final HttpServletRequest request) {
        return sppBtlServices.getNilaiSPP(id,kdsimpeg);
    }
    
    
    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpanspdbtl(@Valid @ModelAttribute("refsppbtl") SppBtl sppBtl,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        if (result.hasErrors()) {
            return "spp/btlgaji/addsppbtl";
        } else {

            sppBtl.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
            //log.debug(" hasil ################# " + result.getFieldError());
            final StringBuilder sburl = new StringBuilder("redirect:/btlgaji/indexbtl");
            final StringBuilder sbur2 = new StringBuilder("redirect:/btlgaji/addsppbtl/");
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            final List<Skpd> listSkpd = pengguna.getSkpd();
            BigDecimal totalspp = SipkdHelpers.getBigDecimalFromString(request.getParameter("totalspp"));
            BigDecimal jumkot = SipkdHelpers.getBigDecimalFromString(request.getParameter("textjumkotsimpeg"));//sppBtl.getJumkotSimpeg();

        //log.debug("total spp == "+ totalspp +" |||||||| total jumkot == "+jumkot);
            if (jumkot.compareTo(totalspp) == -1) {

                redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data Gagal Disimpan. ")
                        .append(" Nilai Total SPP tidak boleh lebih besar dari nilai Kotor Simpeg. ")
                        .toString());

                return sbur2.append(sppBtl.getSkpd().getIdSkpd()).toString();
                /*
                 JOptionPane.showMessageDialog(null, "Nilai Kotor Simpeg harus sama dengan nilai Total Rincian SPP BTL.");
                 return "spp/btlgaji/addsppbtl";
                 */
            } else {
                final String aa = request.getParameter("namaPenerima");
                final String ss = request.getParameter("namaYayasan");
            //final String dd = request.getParameter("alamatBantuan");

                String namatujuan = request.getParameter("dkinama");

                if ("".equals(namatujuan) || namatujuan == null) {
                    namatujuan = sppBtl.getNamaYayasan();
                }

            //log.debug("asas : "+aa);
                //log.debug("sasa : "+ss);
                if ((aa.equals(null)) || (ss.equals(null))) {
                    sppBtl.setNamaPenerima("---");
                    sppBtl.setNamaYayasan("---");
                //sppBtl.setAlamatBantuan("---");
                    //log.debug("11111 : "+sppBtl.getNamaPenerima());
                } else {
                    sppBtl.setNamaPenerima(request.getParameter("namaPenerima"));
                    sppBtl.setNamaYayasan(request.getParameter("namaYayasan"));
                //sppBtl.setAlamatBantuan(request.getParameter("alamatBantuan")); 
                    //log.debug("22222 : "+sppBtl.getNamaPenerima());
                }

                sppBtl.setTglSpp(new Date(System.currentTimeMillis()));
                sppBtl.setBulan(SipkdHelpers.splitString(sppBtl.getBulan(), "/", 0));
                sppBtl.setKodeUangMuka("0");
                sppBtl.setNamaTujuan(namatujuan);

                sppBtl.setIdEntry(pengguna.getIdPengguna());
                sppBtl.setTglEntry(new Timestamp(System.currentTimeMillis()));
                final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
                final List<SppBtlRinci> listSppBtlRinci = new ArrayList<SppBtlRinci>(banyakrinci);
            //log.debug(" Map param  "+  request.getParameterMap().toString());
                // final SppBtlRinci sppBtlRinci = new SppBtlRinci();
                for (int i = 0; i < banyakrinci; i++) {
                    int row = i + 1;
                    final String penanda = "pilih" + (i + 1);
                    final String nourutidx = request.getParameter(penanda);
                //    log.debug(penanda+" idBl ################# " + idBl);

                    if ("1".equals(nourutidx)) {
                        final String idBtl = request.getParameter("idbtl" + row);
                        final String noSpd = request.getParameter("noSpd" + row);
                        final SppBtlRinci sppBtlRinci = new SppBtlRinci();
                        //log.debug(" noSpd ################# " + noSpd);
                        final Akun akun = new Akun();
                        akun.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idakun" + row)));

                        sppBtlRinci.setAkun(akun);
                        //log.debug("Akun nya ==>  "+sppBtlRinci.getAkun().getIdAkun());
                        sppBtlRinci.setIdBtl(SipkdHelpers.getIntFromString(idBtl));
                        sppBtlRinci.setNoSpd(noSpd);
                        sppBtlRinci.setIdSpd(SipkdHelpers.getIntFromString(request.getParameter("idspd" + row)));
                        sppBtlRinci.setNilaiSpp(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaispp" + row)));
                        sppBtlRinci.setIdEntry(pengguna.getIdPengguna());
                        sppBtlRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));

                        listSppBtlRinci.add(sppBtlRinci);

                    }
                }

                sppBtl.setSppBtlRinci(listSppBtlRinci);
                sppBtlServices.insertSppBtl(sppBtl);

                redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                        .append(" berhasil ditambahkan ")
                        .toString());

                return sburl.toString();
            }
        }
        //return "berhasil";
    }
    
    @RequestMapping(value = "/editsppbtl/{idspp}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer idspp, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }

        //request.setAttribute("nospdfilter", request.getParameter("nospdfilter"));
        final SppBtl sppBtl = sppBtlServices.getSppBtlById(idspp);
        return new ModelAndView("spp/btlgaji/editsppbtl", "refsppbtl", sppBtl);
    }
    
    @RequestMapping(value = "/prosesupdate", method = RequestMethod.POST)
    public String prosesupdatesppbtlgaji(@Valid @ModelAttribute("refsppbtl") SppBtl sppBtl,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        sppBtl.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));
        //log.debug(" hasil ################# " + result.getFieldError());
        final StringBuilder sburl = new StringBuilder("redirect:/btlgaji/indexbtl");
        final StringBuilder sbur2 = new StringBuilder("redirect:/btlgaji/editsppbtl/");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        BigDecimal totalspp = SipkdHelpers.getBigDecimalFromString(request.getParameter("totalspp"));
        BigDecimal jumkot = sppBtl.getJumkotSimpeg();
        
        //log.debug("total spp == "+ totalspp +" |||||||| total jumkot == "+jumkot);
        
        if (jumkot.compareTo(totalspp) == -1) {
            
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data Gagal Disimpan. ")
                    .append(" Nilai Total SPP tidak boleh lebih besar dari nilai Kotor Simpeg. ")
                    .toString());
            
            return sbur2.append(sppBtl.getSkpd().getIdSkpd()).toString();
            /*
            JOptionPane.showMessageDialog(null, "Nilai Kotor Simpeg harus sama dengan nilai Total Rincian SPP BTL.");
            return "spp/btlgaji/addsppbtl";
            */
        } else {
            final String aa = request.getParameter("namaPenerima");
            final String ss = request.getParameter("namaYayasan");
            //final String dd = request.getParameter("alamatBantuan");
            
            String namatujuan = request.getParameter("dkinama");
            
            if ("".equals(namatujuan) || namatujuan == null){
                namatujuan = sppBtl.getNamaYayasan();
            }
            
            //log.debug("asas : "+aa);
            //log.debug("sasa : "+ss);
            
            if ( (aa.equals(null)) || (ss .equals(null)))
            {
                sppBtl.setNamaPenerima("---");
                sppBtl.setNamaYayasan("---");
                //sppBtl.setAlamatBantuan("---");
                 //log.debug("11111 : "+sppBtl.getNamaPenerima());
            }else
            {
                sppBtl.setNamaPenerima(request.getParameter("namaPenerima"));
                sppBtl.setNamaYayasan(request.getParameter("namaYayasan"));
                //sppBtl.setAlamatBantuan(request.getParameter("alamatBantuan")); 
                //log.debug("22222 : "+sppBtl.getNamaPenerima());
            }    
            
            
            sppBtl.setTglSpp(new Date(System.currentTimeMillis()));
            sppBtl.setBulan(SipkdHelpers.splitString(sppBtl.getBulan(), "/", 0));
            sppBtl.setKodeUangMuka("0");
            sppBtl.setNamaTujuan(namatujuan);
           
           
            sppBtl.setIdEntry(pengguna.getIdPengguna());
            sppBtl.setTglEntry(new Timestamp(System.currentTimeMillis()));
            final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
            final List<SppBtlRinci> listSppBtlRinci = new ArrayList<SppBtlRinci>(banyakrinci);
            //log.debug(" Map param  "+  request.getParameterMap().toString());
            // final SppBtlRinci sppBtlRinci = new SppBtlRinci();
            //log.debug("banyak rinci an ====== "+banyakrinci);
            for (int i = 0; i < banyakrinci; i++) {
                int row = i+1;
                final String penanda = "pilih" + (i + 1);
                final String nourutidx = request.getParameter(penanda);
                //    log.debug(penanda+" idBl ################# " + idBl);
               
                if ("1".equals(nourutidx)) {
                    final String idBtl = request.getParameter("idbtl" + row);
                    final String noSpd = request.getParameter("noSpd" + row);
                    final SppBtlRinci sppBtlRinci = new SppBtlRinci();
                    //log.debug(" noSpd ################# " + noSpd);
                    final Akun akun = new Akun();
                    akun.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idakun" + row)));
                    
                    sppBtlRinci.setAkun(akun);
                    //log.debug("Akun nya ==>  "+sppBtlRinci.getAkun().getIdAkun());
                    
                    sppBtlRinci.setIdBtl(SipkdHelpers.getIntFromString(idBtl));
                    sppBtlRinci.setNoSpd(noSpd);
                    sppBtlRinci.setIdSpd(SipkdHelpers.getIntFromString(request.getParameter("idspd" + row)));
                    sppBtlRinci.setNilaiSpp(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaispp" + row)));
                    sppBtlRinci.setIdEntry(pengguna.getIdPengguna());
                    sppBtlRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));
                    log.debug("Nilai SPP nya ==>  "+sppBtlRinci.getNilaiSpp());
                    listSppBtlRinci.add(sppBtlRinci);
                   
                }
            }
            
            sppBtl.setSppBtlRinci(listSppBtlRinci);
            sppBtlServices.updateSppBtl(sppBtl);
           
            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                    .append(" berhasil ditambahkan ")
                    .toString());

            return sburl.toString();
        }
    }
    
    @RequestMapping(value = "/deletesppbtl/{idspp}", method = RequestMethod.GET)
    public ModelAndView hapus(@PathVariable Integer idspp, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        }
        final SppBtl sppBtl = sppBtlServices.getSppBtlById(idspp);
        return new ModelAndView("spp/btlgaji/deletesppbtl", "refsppbtl", sppBtl);
    }

    @RequestMapping(value = "/prosesdelete", method = RequestMethod.POST)
    public String deletesppbtl(@Valid @ModelAttribute("refsppbtl") SppBtl sppBtl) {
        sppBtlServices.deleteSppBtl(sppBtl.getId());
        return "redirect:/btlgaji/indexbtl/";
    }
    
    
    
    
    //------------------------------------------------------------------------------------------------
/*
    @RequestMapping(value = "/indexbtl/{idskpd}", method = RequestMethod.GET)
    public String index(@PathVariable Integer idskpd, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            log.debug(" listSkpd masuk == " + listSkpd.size());
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
            request.setAttribute("skpd", listSkpd.get(0));

        }
        request.setAttribute("skpd", referensiServices.getDetailSkpdById(idskpd));
        return "spp/btl/indexbtl";
    }

    
/*
    @RequestMapping(value = "/addsppbtl", method = RequestMethod.GET)
    public ModelAndView add(final SppBtl sppBtl, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Skpd> listSkpd = pengguna.getSkpd();
        if (listSkpd.isEmpty() || (!listSkpd.isEmpty() && listSkpd.size() > 1)) {
            request.setAttribute("isall", 1);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            request.setAttribute("isall", 0);
        } else if (!listSkpd.isEmpty() && listSkpd.size() == 1) {
            final Map<String, Object> paramBank = new HashMap<String, Object>();
            final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
            paramBank.put("idskpd", listSkpd.get(0).getIdSkpd());
            paramBank.put("tahun", SipkdHelpers.getIntFromString(tahunAnggaran));
            final Map<String, String> dataBank = sppBtlServices.getBankRekByIdSkpd(paramBank);
            log.debug(" >>>>>>>>>>>>>  " + dataBank.toString());
            sppBtl.setKodeBank(dataBank.get("C_BANK"));
            sppBtl.setNamaBank(dataBank.get("N_BANK"));
            sppBtl.setNomorRekBank(dataBank.get("I_REK_BANKSPM"));
            request.setAttribute("isall", 0);

        }
        return new ModelAndView("spp/btl/addsppbtl", "refsppbtl", sppBtl);

    }
*/
    /*
    
/*
    @RequestMapping(value = "/json/getanggarandanspdbantuanlangsung/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, BigDecimal> getanggarandanspd(@PathVariable Integer id, final HttpServletRequest request) {
        final String TahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("tahun", TahunAnggaran);
        param.put("idskpd", id);
        return spdBLServices.getTotalAnggaranDanSpd(param);
    }

    @RequestMapping(value = "/json/getlistspdbtlbanyak ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getlistspdbtlbanyak(final HttpServletRequest request) {
        final String TahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("tahun", TahunAnggaran);
        param.put("idskpd", idskpd);
        return sppBtlServices.getBanyakSpdBtl(param);
    }
*/
    

    
/*
    

    

    
    */

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
