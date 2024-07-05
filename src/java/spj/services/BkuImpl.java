/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import static java.lang.System.console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spj.entity.BkuMapper;
import spp.model.BukuKasUmum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spj.util.SipkdHelpers;

/**
 *
 * @author Zainab
 */
@Transactional(readOnly = true)
@Service("bkuServices")
public class BkuImpl implements BkuServices {

    private static final Logger log = LoggerFactory.getLogger(BkuImpl.class);
    @Autowired
    private BkuMapper bkuMapper;

    @Override
    public List<BukuKasUmum> getNoBukti(final Map<String, Object> param) {
        return bkuMapper.getNoBukti(param);
    }

    @Override
    public Integer getBannyaNoBukti(final Map<String, Object> param) {
        return bkuMapper.getBannyaNoBukti(param);
    }

    @Override
    public List<BukuKasUmum> getListBkuUpgutu(final Map<String, Object> param) {
        return bkuMapper.getListBkuUpgutu(param);
    }

    @Override
    public Integer getBannyakListBkuUpgutu(final Map<String, Object> param) {
        return bkuMapper.getBannyakListBkuUpgutu(param);
    }

    @Override
    public List<BukuKasUmum> getListBkuLs1(final Map<String, Object> param) {
        return bkuMapper.getListBkuLs1(param);
    }

    @Override
    public Integer getBannyakListBkuLs1(final Map<String, Object> param) {
        return bkuMapper.getBannyakListBkuLs1(param);
    }

    @Override
    public List<BukuKasUmum> getListBkuLs2(final Map<String, Object> param) {
        return bkuMapper.getListBkuLs2(param);
    }

    @Override
    public Integer getBannyakListBkuLs2(final Map<String, Object> param) {
        return bkuMapper.getBannyakListBkuLs2(param);
    }

    @Override
    public List<BukuKasUmum> getListBkuLs3(final Map<String, Object> param) {
        return bkuMapper.getListBkuLs3(param);
    }

    @Override
    public Integer getBannyakListBkuLs3(final Map<String, Object> param) {
        return bkuMapper.getBannyakListBkuLs3(param);
    }

    @Override
    public List<BukuKasUmum> getListBkuLs4(final Map<String, Object> param) {
        return bkuMapper.getListBkuLs4(param);
    }

    @Override
    public Integer getBannyakListBkuLs4(final Map<String, Object> param) {
        return bkuMapper.getBannyakListBkuLs4(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBKU(List<BukuKasUmum> param) {
        final Map genparam = new HashMap();
        genparam.put("tahun", param.get(0).getTahun());
        genparam.put("idskpd", param.get(0).getIdskpd());
        genparam.put("wilayah", param.get(0).getKodeWilayah());

        Integer noBku;

        if ("1234".equals(param.get(0).getIdskpd().toString())) {
            noBku = bkuMapper.getBkuNoPpkd(genparam);
        } else {
            noBku = bkuMapper.getBkuNo(genparam);
        }

        for (BukuKasUmum bku : param) {
            if (bku.getNoBKU() == null) {
                bku.setNoBKU(noBku);
            }

            bkuMapper.insertBKU(bku);
        }
    }

    @Override
    public List<BukuKasUmum> getNilaiIndex(final Map<String, Object> param) {
        return bkuMapper.getNilaiIndex(param);
    }

    @Override
    public List<BukuKasUmum> getListIndex(final BukuKasUmum param) {
        return bkuMapper.getListIndex(param);
    }

    @Override
    public Integer getBannyakListIndex(final BukuKasUmum param) {
        return bkuMapper.getBannyakListIndex(param);
    }

    @Override
    public List<BukuKasUmum> getKegiatan(final Map<String, Object> param) {
        return bkuMapper.getKegiatan(param);
    }

    @Override
    public List<BukuKasUmum> getAkunCombo(final Map<String, Object> param) {
        return bkuMapper.getAkunCombo(param);
    }

    @Override
    public Integer getBannyakKegiatan(final Map<String, Object> param) {
        return bkuMapper.getBannyakKegiatan(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBkuSpj(List<BukuKasUmum> param) {

        final Map genparam = new HashMap();
        genparam.put("tahun", param.get(0).getTahun());
        genparam.put("idskpd", param.get(0).getIdskpd());
        genparam.put("wilayah", param.get(0).getKodeWilayah());

        Integer noBku;
        if ("1234".equals(param.get(0).getIdskpd().toString())) {
            noBku = bkuMapper.getBkuNoPpkd(genparam);
        } else {
            noBku = bkuMapper.getBkuNo(genparam);
        }

        int i = 0;

        for (BukuKasUmum bku : param) {
            i++;

            if (bku.getNoBKU() == null) {
                log.debug("masuk if == null");
                bku.setNoBKU(noBku);
            }

            bkuMapper.insertBkuSpj(bku);
        }
    }

    @Override
    public BukuKasUmum getBkuEdit(final Map<String, Object> param) {
        return bkuMapper.getBkuEdit(param);
    }

    @Override
    public BukuKasUmum getBkuEditPajak(final Map<String, Object> param) {
        return bkuMapper.getBkuEditPajak(param);
    }

    @Override
    public List<BukuKasUmum> getRinciSpjEdit(final Map<String, Object> param) {
        return bkuMapper.getRinciSpjEdit(param);
    }

    @Override
    public Integer getBanyakRinciSpjEdit(final Map<String, Object> param) {
        return bkuMapper.getBanyakRinciSpjEdit(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBku(List<BukuKasUmum> param) {
        for (BukuKasUmum bku : param) {
            log.debug("updateBku() implement, kode akun ==== " + bku.getKodeakun());
            bkuMapper.updateBku(bku);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBkuSetorUp(BukuKasUmum param) {
        final Map genparam = new HashMap();

        genparam.put("tahun", param.getTahun());
        genparam.put("idskpd", param.getIdskpd());
        genparam.put("wilayah", param.getKodeWilayah());

        if ("1234".equals(param.getIdskpd().toString())) {
            param.setNoBKU(bkuMapper.getBkuNoPpkd(genparam));
        } else {
            param.setNoBKU(bkuMapper.getBkuNo(genparam));
        }

        bkuMapper.insertBkuSetorUp(param);
    }

    @Override
    public Integer getBanyakKegiatanBantuan(final Map<String, Object> param) {
        return bkuMapper.getBanyakKegiatanBantuan(param);
    }

    @Override
    public List<BukuKasUmum> getKegiatanBantuan(final Map<String, Object> param) {
        return bkuMapper.getKegiatanBantuan(param);
    }

    @Override
    public Integer getBanyakKegiatanBl(final Map<String, Object> param) {
        return bkuMapper.getBanyakKegiatanBl(param);
    }

    @Override
    public List<BukuKasUmum> getKegiatanBl(final Map<String, Object> param) {
        return bkuMapper.getKegiatanBl(param);
    }

    @Override
    public List<BukuKasUmum> getAkunComboBtl(final Map<String, Object> param) {
        return bkuMapper.getAkunComboBtl(param);
    }

    @Override
    public List<BukuKasUmum> getAkunComboBantuan(final Map<String, Object> param) {
        return bkuMapper.getAkunComboBantuan(param);
    }

    @Override
    public List<BukuKasUmum> getAkunComboBl(final Map<String, Object> param) {
        return bkuMapper.getAkunComboBl(param);
    }

    @Override
    public List<BukuKasUmum> getAkunComboBiaya(final Map<String, Object> param) {
        return bkuMapper.getAkunComboBiaya(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBkuTanpaKeg(List<BukuKasUmum> param) {
        final Map genparam = new HashMap();
        genparam.put("tahun", param.get(0).getTahun());
        genparam.put("idskpd", param.get(0).getIdskpd());
        genparam.put("wilayah", param.get(0).getKodeWilayah());

        Integer noBku;
        if ("1234".equals(param.get(0).getIdskpd().toString())) {
            noBku = (bkuMapper.getBkuNoPpkd(genparam));
        } else {
            noBku = (bkuMapper.getBkuNo(genparam));
        }

        for (BukuKasUmum bku : param) {
            if (bku.getNoBKU() == null) {
                bku.setNoBKU(noBku);
            }
            bkuMapper.insertBkuTanpaKeg(bku);
        }
    }

    @Override
    public BukuKasUmum getBkuEditStUp(final Map<String, Object> param) {
        return bkuMapper.getBkuEditStUp(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuSetorUp(BukuKasUmum param) {
        bkuMapper.updateBkuSetorUp(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuLs2(List<BukuKasUmum> param) {
        for (BukuKasUmum bku : param) {
            bkuMapper.updateBkuLs2(bku);
        }
    }

    @Override
    public List<BukuKasUmum> getRinciEditLs2(final Map<String, Object> param) {
        return bkuMapper.getRinciEditLs2(param);
    }

    @Override
    public Integer getBanyakRinciEditLs2(final Map<String, Object> param) {
        return bkuMapper.getBanyakRinciEditLs2(param);
    }

    @Override
    public List<BukuKasUmum> getSkpdBl(final Map<String, Object> param) {
        return bkuMapper.getSkpdBl(param);
    }

    @Override
    public List<BukuKasUmum> getSkpdBtl(final Map<String, Object> param) {
        return bkuMapper.getSkpdBtl(param);
    }

    @Override
    public List<BukuKasUmum> getListSPJ(final Map<String, Object> param) {
        return bkuMapper.getListSPJ(param);
    }

    @Override
    public Integer getBannyakListSPJ(final Map<String, Object> param) {
        return bkuMapper.getBannyakListSPJ(param);
    }

    @Override
    public BukuKasUmum getBkuSPJEdit(final Map<String, Object> param) {
        return bkuMapper.getBkuSPJEdit(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuSpj(List<BukuKasUmum> param) {
        String nodok = param.get(0).getNoDok();
        Integer nobku = param.get(0).getNoBKU();
        final Map delparam = new HashMap();
        delparam.put("idskpd", param.get(0).getIdskpd());
        delparam.put("nobku", nobku);
        delparam.put("tahun", param.get(0).getTahun());

        bkuMapper.deleteBkuSpj(delparam);

        for (BukuKasUmum bku : param) {
            bkuMapper.insertBkuSpj(bku);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBkuCek(BukuKasUmum param) {
        final Map genparam = new HashMap();
        genparam.put("tahun", param.getTahun());
        genparam.put("idskpd", param.getIdskpd());
        genparam.put("wilayah", param.getKodeWilayah());

        if ("1234".equals(param.getIdskpd().toString())) {
            param.setNoBKU(bkuMapper.getBkuNoPpkd(genparam));
        } else {
            param.setNoBKU(bkuMapper.getBkuNo(genparam));
        }

        bkuMapper.insertBkuCek(param);
        bkuMapper.insertBkuCekPg(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuCek(BukuKasUmum param) {
        bkuMapper.updateBkuCek(param);
        bkuMapper.updateBkuCekPg(param);
    }

    @Override
    public BukuKasUmum getBkuEditCek(final Map<String, Object> param) {
        return bkuMapper.getBkuEditCek(param);
    }

    @Override
    public List<BukuKasUmum> getListSp2dEdit(final Map<String, Object> param) {
        return bkuMapper.getListSp2dEdit(param);
    }

    @Override
    public Integer getBanyakListSp2dEdit(final Map<String, Object> param) {
        return bkuMapper.getBanyakListSp2dEdit(param);
    }

    @Override
    public BukuKasUmum getBkuEditSp2d(final Map<String, Object> param) {
        return bkuMapper.getBkuEditSp2d(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuSp2d(BukuKasUmum param) {
        bkuMapper.updateBkuSp2d(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBkuNM(List<BukuKasUmum> param) {

        final Map genparam = new HashMap();
        genparam.put("tahun", param.get(0).getTahun());
        genparam.put("idskpd", param.get(0).getIdskpd());
        genparam.put("wilayah", param.get(0).getKodeWilayah());
        
        //final Integer noBku = bkuMapper.getBkuNo(genparam);
        Integer noBku;

        if ("1234".equals(param.get(0).getIdskpd().toString())) {
            noBku = bkuMapper.getBkuNoPpkd(genparam);
        } else {
            noBku = bkuMapper.getBkuNo(genparam);
        }
        
        for (BukuKasUmum bku : param) {
            if (bku.getNoBKU() == null) {
                log.debug("masuk if == null");
                bku.setNoBKU(noBku);
            }

            bkuMapper.insertBkuNmPg(bku);
        }

        final BukuKasUmum buku = new BukuKasUmum();
        buku.setTahun(param.get(0).getTahun());
        buku.setIdskpd(param.get(0).getIdskpd());
        buku.setTglPosting(param.get(0).getTglPosting());
        buku.setKodeTransaksi(param.get(0).getKodeTransaksi());
        buku.setNoDok(param.get(0).getNoDok());
        buku.setTglDok(param.get(0).getTglDok());
        buku.setUraianBukti(param.get(0).getUraianBukti());
        buku.setIdKegiatan(param.get(0).getIdKegiatan());
        buku.setKodeKeg(param.get(0).getKodeKeg());
        buku.setIdBas(param.get(0).getIdBas());
        buku.setAkunPn(param.get(0).getAkunPn());
        buku.setNilaiCek(param.get(0).getNilaiCek());
        buku.setJenis(param.get(0).getJenis());
        buku.setBeban(param.get(0).getBeban());
        buku.setNoBKU(noBku);
        buku.setInboxFile(param.get(0).getInboxFile());
        buku.setNipPptk(param.get(0).getNipPptk());
        buku.setNamaPptk(param.get(0).getNamaPptk());
        buku.setUraian(param.get(0).getUraian());
        buku.setIdEntry(param.get(0).getIdEntry());
        buku.setKodeWilayah(param.get(0).getKodeWilayah());

        if ("add".equals(param.get(0).getUraian())) {
            bkuMapper.insertBkuNmPn(buku);
        }

    }

    @Override
    @Transactional(readOnly = false)
    public void insertBkuNP(List<BukuKasUmum> param) {

        final Map genparam = new HashMap();
        genparam.put("tahun", param.get(0).getTahun());
        genparam.put("idskpd", param.get(0).getIdskpd());
        genparam.put("wilayah", param.get(0).getKodeWilayah());
        
        //final Integer noBku = bkuMapper.getBkuNo(genparam);
        
        Integer noBku;

        if ("1234".equals(param.get(0).getIdskpd().toString())) {
            noBku = bkuMapper.getBkuNoPpkd(genparam);
        } else {
            noBku = bkuMapper.getBkuNo(genparam);
        }

        for (BukuKasUmum bku : param) {
            if (bku.getNoBKU() == null) {
                bku.setNoBKU(noBku);
            }

            bkuMapper.insertBkuNpPn(bku);
        }

        final BukuKasUmum buku = new BukuKasUmum();
        buku.setTahun(param.get(0).getTahun());
        buku.setIdskpd(param.get(0).getIdskpd());
        buku.setTglPosting(param.get(0).getTglPosting());
        buku.setKodeTransaksi(param.get(0).getKodeTransaksi());
        buku.setNoDok(param.get(0).getNoDok());
        buku.setTglDok(param.get(0).getTglDok());
        buku.setUraianBukti(param.get(0).getUraianBukti());
        buku.setIdKegiatan(param.get(0).getIdKegiatan());
        buku.setKodeKeg(param.get(0).getKodeKeg());
        buku.setIdBas(param.get(0).getIdBas());
        buku.setAkunPn(param.get(0).getAkunPn());
        buku.setNilaiCek(param.get(0).getNilaiCek());
        buku.setJenis(param.get(0).getJenis());
        buku.setBeban(param.get(0).getBeban());
        buku.setNoBKU(noBku);
        buku.setInboxFile(param.get(0).getInboxFile());
        buku.setNipPptk(param.get(0).getNipPptk());
        buku.setNamaPptk(param.get(0).getNamaPptk());
        buku.setUraian(param.get(0).getUraian());
        buku.setIdEntry(param.get(0).getIdEntry());
        buku.setKodeWilayah(param.get(0).getKodeWilayah());

        if ("add".equals(param.get(0).getUraian())) {
            bkuMapper.insertBkuNpPg(buku);
        }
    }

    @Override
    public BukuKasUmum getBkuEditNPD(final Map<String, Object> param) {
        return bkuMapper.getBkuEditNPD(param);
    }

    @Override
    public List<BukuKasUmum> getRinciNpdEdit(final Map<String, Object> param) {
        return bkuMapper.getRinciNpdEdit(param);
    }

    @Override
    public Integer getBanyakRinciNpdEdit(final Map<String, Object> param) {
        return bkuMapper.getBanyakRinciNpdEdit(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuNP(List<BukuKasUmum> param) {

        for (BukuKasUmum bku : param) {
            bkuMapper.updateBkuNP(bku);
        }

        final BukuKasUmum buku = new BukuKasUmum();
        buku.setTglPosting(param.get(0).getTglPosting());
        buku.setKodeTransaksi(param.get(0).getKodeTransaksi());
        buku.setNoDok(param.get(0).getNoDok());
        buku.setTglDok(param.get(0).getTglDok());
        buku.setAkunPn(param.get(0).getAkunPn());
        buku.setNilaiCek(param.get(0).getNilaiCek());
        buku.setBeban(param.get(0).getBeban());
        buku.setInboxFile(param.get(0).getInboxFile());
        buku.setNipPptk(param.get(0).getNipPptk());
        buku.setNamaPptk(param.get(0).getNamaPptk());
        buku.setIdEntry(param.get(0).getIdEntry());
        buku.setNoBKU(param.get(0).getNoBKU());
        buku.setKodeWilayah(param.get(0).getKodeWilayah());

        bkuMapper.updateBkuNPTunai(buku);

    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuNM(List<BukuKasUmum> param) {
        for (BukuKasUmum bku : param) {
            bkuMapper.updateBkuNM(bku);
        }

        final BukuKasUmum buku = new BukuKasUmum();
        buku.setTglPosting(param.get(0).getTglPosting());
        buku.setKodeTransaksi(param.get(0).getKodeTransaksi());
        buku.setNoDok(param.get(0).getNoDok());
        buku.setTglDok(param.get(0).getTglDok());
        buku.setAkunPn(param.get(0).getAkunPn());
        buku.setNilaiCek(param.get(0).getNilaiCek());
        buku.setBeban(param.get(0).getBeban());
        buku.setInboxFile(param.get(0).getInboxFile());
        buku.setNipPptk(param.get(0).getNipPptk());
        buku.setNamaPptk(param.get(0).getNamaPptk());
        buku.setIdEntry(param.get(0).getIdEntry());
        buku.setNoBKU(param.get(0).getNoBKU());
        buku.setKodeWilayah(param.get(0).getKodeWilayah());

        bkuMapper.updateBkuNMTunai(buku);
    }

    @Override
    public List<BukuKasUmum> getListNP(final Map<String, Object> param) {
        return bkuMapper.getListNP(param);
    }

    @Override
    public Integer getBanyakListNP(final Map<String, Object> param) {
        return bkuMapper.getBanyakListNP(param);
    }

    @Override
    public List<BukuKasUmum> getListNM(final Map<String, Object> param) {
        return bkuMapper.getListNM(param);
    }

    @Override
    public Integer getBanyakListNM(final Map<String, Object> param) {
        return bkuMapper.getBanyakListNM(param);
    }

    @Override
    public List<BukuKasUmum> getKegiatanNP(final Map<String, Object> param) {
        return bkuMapper.getKegiatanNP(param);
    }

    @Override
    public Integer getBanyakKegiatanNP(final Map<String, Object> param) {
        return bkuMapper.getBanyakKegiatanNP(param);
    }

    @Override
    public List<BukuKasUmum> getKegiatanNM(final Map<String, Object> param) {
        return bkuMapper.getKegiatanNM(param);
    }

    @Override
    public Integer getBanyakKegiatanNM(final Map<String, Object> param) {
        return bkuMapper.getBanyakKegiatanNM(param);
    }

    @Override
    public List<BukuKasUmum> getNamaPPTK(final Map<String, Object> param) {
        return bkuMapper.getNamaPPTK(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBkuNPNM(List<BukuKasUmum> param) {

        final Map genparam = new HashMap();
        genparam.put("tahun", param.get(0).getTahun());
        genparam.put("idskpd", param.get(0).getIdskpd());
        genparam.put("wilayah", param.get(0).getKodeWilayah());
        //final Integer noBku = bkuMapper.getBkuNo(genparam);

        Integer noBku;

        if ("1234".equals(param.get(0).getIdskpd().toString())) {
            noBku = bkuMapper.getBkuNoPpkd(genparam);
        } else {
            noBku = bkuMapper.getBkuNo(genparam);
        }
        
        for (BukuKasUmum bku : param) {
            if (bku.getNoBKU() == null) {
                log.debug("masuk if == null");
                bku.setNoBKU(noBku);
            }

            log.debug(" getCaraBayarPg :::::: " + bku.getCaraBayarPg());
            log.debug(" getCaraBayarPn :::::: " + bku.getCaraBayarPn());
            bkuMapper.insertBkuCek(bku);
            bkuMapper.insertBkuCekPg(bku);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuNPNM(List<BukuKasUmum> param) {

        for (BukuKasUmum bku : param) {
            //bkuMapper.updateBkuCek(bku);
            //bkuMapper.updateBkuCekPg(bku);
            bkuMapper.updateBkuNpd(bku);
            bkuMapper.updateBkuNpdPg(bku);
        }
    }

    @Override
    public BukuKasUmum getStatusSuadana(final Map<String, Object> param) {
        return bkuMapper.getStatusSuadana(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteById(List<BukuKasUmum> param) {
        for (BukuKasUmum bku : param) {
            bkuMapper.deleteById(bku);
        }
    }

    @Override
    public BukuKasUmum getNilaiSisaSpj(final Map<String, Object> param) {
        return bkuMapper.getNilaiSisaSpj(param);
    }

    @Override
    public BukuKasUmum getNilaiValidasiSisaSpj(final Map<String, Object> param) {
        return bkuMapper.getNilaiValidasiSisaSpj(param);
    }

    @Override
    public Integer getBebanSpjTu(final Map<String, Object> param) {
        return bkuMapper.getBebanSpjTu(param);
    }

    @Override
    public Integer getBanyakKegiatanSPJ(final Map<String, Object> param) {
        return bkuMapper.getBanyakKegiatanSPJ(param);
    }

    @Override
    public List<BukuKasUmum> getKegiatanSPJ(final Map<String, Object> param) {
        return bkuMapper.getKegiatanSPJ(param);
    }

    @Override
    public Integer getBannyakListSpjTU(final Map<String, Object> param) {
        return bkuMapper.getBannyakListSpjTU(param);
    }

    @Override
    public List<BukuKasUmum> getListSpjTU(final Map<String, Object> param) {
        return bkuMapper.getListSpjTU(param);
    }

    @Override
    public List<BukuKasUmum> getKodeTutup(final Map<String, Object> param) {
        return bkuMapper.getKodeTutup(param);
    }

    @Override
    public List<BukuKasUmum> getKodeTutupMax(final Map<String, Object> param) {
        return bkuMapper.getKodeTutupMax(param);
    }

    @Override
    public BukuKasUmum getNilaiValidasiSisaSpjTU(final Map<String, Object> param) {
        return bkuMapper.getNilaiValidasiSisaSpjTU(param);
    }

    @Override
    public List<BukuKasUmum> getNamaNipSpjPanjar(final Map<String, Object> param) {
        return bkuMapper.getNamaNipSpjPanjar(param);
    }

    @Override
    public Integer getBanyakKegiatanSpjPanjar(final Map<String, Object> param) {
        return bkuMapper.getBanyakKegiatanSpjPanjar(param);
    }

    @Override
    public List<BukuKasUmum> getKegiatanSpjPanjar(final Map<String, Object> param) {
        return bkuMapper.getKegiatanSpjPanjar(param);
    }

    @Override
    public List<BukuKasUmum> getKegiatanByBeban(final Map<String, Object> param) {
        return bkuMapper.getKegiatanByBeban(param);
    }

    @Override
    public Integer getBanyakKegiatanByBeban(final Map<String, Object> param) {
        return bkuMapper.getBanyakKegiatanByBeban(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuSpjKoreksi(BukuKasUmum param) {
        bkuMapper.updateBkuSpjKoreksi(param);
    }

    @Override
    public List<BukuKasUmum> getWilayah(final Map<String, Object> param) {
        return bkuMapper.getWilayah(param);
    }

    @Override
    public Integer getSetorUPSemester(final Map<String, Object> param) {
        return bkuMapper.getSetorUPSemester(param);
    }

    @Override
    public Integer getBanyakAkunLain(final Map<String, Object> param) {
        return bkuMapper.getBanyakAkunLain(param);
    }

    @Override
    public List<BukuKasUmum> getAkunLain(final Map<String, Object> param) {
        return bkuMapper.getAkunLain(param);
    }

    @Override
    public BukuKasUmum getBkuEditLainLain(final Map<String, Object> param) {
        return bkuMapper.getBkuEditLainLain(param);
    }

    @Override
    public List<BukuKasUmum> getBendahara(final Map<String, Object> param) {
        return bkuMapper.getBendahara(param);
    }

    @Override
    public List<BukuKasUmum> getSaldoAwal(final Map<String, Object> param) {
        return bkuMapper.getSaldoAwal(param);
    }

    @Override
    public List<BukuKasUmum> getBulanByRekap(final Map<String, Object> param) {
        return bkuMapper.getBulanByRekap(param);
    }

    @Override
    public BukuKasUmum getSaldoAwalPajak(final Map<String, Object> param) {
        return bkuMapper.getSaldoAwalPajak(param);
    }

    @Override
    public BukuKasUmum getSisaPajak(final Map<String, Object> param) {
        return bkuMapper.getSisaPajak(param);
    }

    @Override
    public List<BukuKasUmum> getKegiatanSetorTU(final Map<String, Object> param) {
        return bkuMapper.getKegiatanSetorTU(param);
    }

    @Override
    public Integer getBanyakKegiatanSetorTU(final Map<String, Object> param) {
        return bkuMapper.getBanyakKegiatanSetorTU(param);
    }

    @Override
    public List<BukuKasUmum> getAkunComboTU(final Map<String, Object> param) {
        return bkuMapper.getAkunComboTU(param);
    }

    @Override
    public List<BukuKasUmum> getListStBtl(final Map<String, Object> param) {
        return bkuMapper.getListStBtl(param);
    }

    @Override
    public Integer getBanyakListStBtl(final Map<String, Object> param) {
        return bkuMapper.getBanyakListStBtl(param);
    }

    @Override
    public List<BukuKasUmum> getListStBl(final Map<String, Object> param) {
        return bkuMapper.getListStBl(param);
    }

    @Override
    public Integer getBanyakListStBl(final Map<String, Object> param) {
        return bkuMapper.getBanyakListStBl(param);
    }

    @Override
    public List<BukuKasUmum> getAkunStBtl(final Map<String, Object> param) {
        return bkuMapper.getAkunStBtl(param);
    }

    @Override
    public Integer getBanyakAkunStBtl(final Map<String, Object> param) {
        return bkuMapper.getBanyakAkunStBtl(param);
    }

    @Override
    public List<BukuKasUmum> getAkunStBl(final Map<String, Object> param) {
        return bkuMapper.getAkunStBl(param);
    }

    @Override
    public Integer getBanyakAkunStBl(final Map<String, Object> param) {
        return bkuMapper.getBanyakAkunStBl(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuSt(List<BukuKasUmum> param) {
        Integer nobku = param.get(0).getNoBKU();
        final Map delparam = new HashMap();
        delparam.put("idskpd", param.get(0).getIdskpd());
        delparam.put("nobku", nobku);
        delparam.put("tahun", param.get(0).getTahun());

        bkuMapper.deleteBkuSpj(delparam);

        for (BukuKasUmum bku : param) {
            bkuMapper.insertBkuTanpaKeg(bku);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuStBl(List<BukuKasUmum> param) {
        Integer nobku = param.get(0).getNoBKU();
        final Map delparam = new HashMap();
        delparam.put("idskpd", param.get(0).getIdskpd());
        delparam.put("nobku", nobku);
        delparam.put("tahun", param.get(0).getTahun());

        bkuMapper.deleteBkuSpj(delparam);

        for (BukuKasUmum bku : param) {
            bkuMapper.insertBkuSpj(bku);
        }
    }

    @Override
    public List<BukuKasUmum> getListST(final Map<String, Object> param) {
        return bkuMapper.getListST(param);
    }

    @Override
    public Integer getBanyakListST(final Map<String, Object> param) {
        return bkuMapper.getBanyakListST(param);
    }

    @Override
    public List<BukuKasUmum> getSaldoJasaGiro(final Map<String, Object> param) {
        return bkuMapper.getSaldoJasaGiro(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBkuJasaGiro(BukuKasUmum param) {
        final Map genparam = new HashMap();
        genparam.put("tahun", param.getTahun());
        genparam.put("idskpd", param.getIdskpd());
        genparam.put("wilayah", param.getKodeWilayah());
        //param.setNoBKU(bkuMapper.getBkuNo(genparam));
        if ("1234".equals(param.getIdskpd().toString())){
            param.setNoBKU(bkuMapper.getBkuNoPpkd(genparam));
        } else {
            param.setNoBKU(bkuMapper.getBkuNo(genparam));
        }
        
        bkuMapper.insertBkuJasaGiro(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuJasaGiro(BukuKasUmum param) {
        bkuMapper.updateBkuJasaGiro(param);
    }

    @Override
    public List<BukuKasUmum> getSaldoBpjs(final Map<String, Object> param) {
        return bkuMapper.getSaldoBpjs(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuBpjs(BukuKasUmum param) {
        bkuMapper.updateBkuBpjs(param);
    }

    @Override
    public BukuKasUmum getKodeSA(final Map<String, Object> param) {
        return bkuMapper.getKodeSA(param);
    }

    @Override
    public List<BukuKasUmum> getListPnPajak(final Map<String, Object> param) {
        return bkuMapper.getListPnPajak(param);
    }

    @Override
    public Integer getBanyakListPnPajak(final Map<String, Object> param) {
        return bkuMapper.getBanyakListPnPajak(param);
    }

    @Override
    public List<BukuKasUmum> getListPgPajak(final Map<String, Object> param) {
        return bkuMapper.getListPgPajak(param);
    }

    @Override
    public Integer getBanyakListPgPajak(final Map<String, Object> param) {
        return bkuMapper.getBanyakListPgPajak(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBkuPajakSA(BukuKasUmum param) {
        final Map genparam = new HashMap();
        genparam.put("tahun", param.getTahun());
        genparam.put("idskpd", param.getIdskpd());
        genparam.put("wilayah", param.getKodeWilayah());
        //param.setNoBKU(bkuMapper.getBkuNo(genparam));
        if ("1234".equals(param.getIdskpd().toString())){
            param.setNoBKU(bkuMapper.getBkuNoPpkd(genparam));
        } else {
            param.setNoBKU(bkuMapper.getBkuNo(genparam));
        }
        bkuMapper.insertBkuPajakSA(param);
    }

    @Override
    public BukuKasUmum getNilaiBkuPg(final Map<String, Object> param) {
        return bkuMapper.getNilaiBkuPg(param);
    }

    @Override
    public List<BukuKasUmum> getListPgPajakEdit(final Map<String, Object> param) {
        return bkuMapper.getListPgPajakEdit(param);
    }

    @Override
    public Integer getBanyakListPgPajakEdit(final Map<String, Object> param) {
        return bkuMapper.getBanyakListPgPajakEdit(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBkuPjPg(List<BukuKasUmum> param) {

        final Map genparam = new HashMap();
        genparam.put("tahun", param.get(0).getTahun());
        genparam.put("idskpd", param.get(0).getIdskpd());
        genparam.put("wilayah", param.get(0).getKodeWilayah());
        //final Integer noBku = bkuMapper.getBkuNo(genparam);
        Integer noBku;

        if ("1234".equals(param.get(0).getIdskpd().toString())) {
            noBku = bkuMapper.getBkuNoPpkd(genparam);
        } else {
            noBku = bkuMapper.getBkuNo(genparam);
        }
        
        int i = 0;

        for (BukuKasUmum bku : param) {
            i++;

            if (bku.getNoBKU() == null) {
                log.debug("masuk if == null");
                bku.setNoBKU(noBku);
            }

            bkuMapper.insertBkuSpj(bku);
        }

        // update nobku pengeluaran ke bku penerimaan nya
        BukuKasUmum bkuref = new BukuKasUmum();
        bkuref.setTahun(param.get(0).getTahun());
        bkuref.setIdskpd(param.get(0).getIdskpd());
        bkuref.setNoBKU(noBku); // nobku pg
        bkuref.setNoBkuPj(param.get(0).getNoBkuPj()); // no bku pn
        bkuref.setKodeTransaksi(param.get(0).getKodeTransaksi());

        //bkuMapper.updateBkuNoRef(bkuref); -- ga jadi update i_bkuno ke Pajak Pn, karena nanti Pajak Pn diisi sama i_bkuno SPJ
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuPjPg(BukuKasUmum param) {
        bkuMapper.updateBkuPjPg(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuPjPn(List<BukuKasUmum> param) {
        BukuKasUmum bkuref = new BukuKasUmum();
        bkuref.setTahun(param.get(0).getTahun());
        bkuref.setIdskpd(param.get(0).getIdskpd());
        bkuref.setNoBkuPj(param.get(0).getNoBkuPj());

        bkuref.setIdEntry(param.get(0).getIdEntry());
        bkuref.setBeban(param.get(0).getBeban());

        for (BukuKasUmum bku : param) {
            bkuMapper.updateBku(bku);

            // update beban dan nilai pajak pengeluaran
            bkuref.setIdBas(bku.getIdBas());
            bkuref.setNilaiKeluar(bku.getNilaiMasuk());
            bkuref.setIdBkuRef(bku.getIdBkuRef());
            bkuref.setKodeakun(bku.getKodeakun());
            bkuMapper.updateNilaiPajak(bkuref);
        }
    }

    @Override
    public List<BukuKasUmum> getRinciPjPn(final Map<String, Object> param) {
        return bkuMapper.getRinciPjPn(param);
    }

    @Override
    public BukuKasUmum getRinciPjPg(final Map<String, Object> param) {
        return bkuMapper.getRinciPjPg(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBkuPjPn(List<BukuKasUmum> param) {

        final Map genparam = new HashMap();
        genparam.put("tahun", param.get(0).getTahun());
        genparam.put("idskpd", param.get(0).getIdskpd());
        genparam.put("nobku", param.get(0).getNoBkuPj());
        genparam.put("kodetrx", param.get(0).getKodeTransaksi());

        String bkuref = param.get(0).getNoBkuPj();
        BukuKasUmum bkupjpg = new BukuKasUmum();

        if (!"BELUM ADA PG".equals(bkuref)) {
            final BukuKasUmum valpjpg = bkuMapper.getRinciPjPg(genparam);

            bkupjpg.setTahun(param.get(0).getTahun());
            bkupjpg.setIdskpd(param.get(0).getIdskpd());
            bkupjpg.setKodeTransaksi(param.get(0).getKodeTransaksi());
            bkupjpg.setNoBKU(SipkdHelpers.getIntFromString(param.get(0).getNoBkuPj()));
            bkupjpg.setJenis(param.get(0).getJenis());
            bkupjpg.setBeban(param.get(0).getBeban());
            bkupjpg.setNoBkuPj(param.get(0).getNoBKU().toString());
            bkupjpg.setKodeKoreksi("0");
            bkupjpg.setNamaPptk("");
            bkupjpg.setNipPptk("");
            bkupjpg.setIdEntry(param.get(0).getIdEntry());
            bkupjpg.setTglPosting(valpjpg.getTglPosting());
            bkupjpg.setInboxFile(valpjpg.getInboxFile());
            bkupjpg.setNoDok(valpjpg.getNoDok());
            bkupjpg.setTglDok(valpjpg.getTglDok());
            bkupjpg.setUraian(valpjpg.getUraian());
            bkupjpg.setUraianBukti(valpjpg.getUraian());
            bkupjpg.setKodePembayaran(valpjpg.getKodePembayaran());
            bkupjpg.setKodeUangPersediaan(valpjpg.getKodePembayaran());
        }

        for (BukuKasUmum bku : param) {
            bkuMapper.insertBkuSpj(bku); // insert tambah row bku pajak penerimaan 

            if (!"BELUM ADA PG".equals(bkuref)) {
                // insert tambah row bku pajak pengeluaran
                bkupjpg.setIdKegiatan(bku.getIdKegiatan());
                bkupjpg.setKodeKeg(bku.getKodeKeg());
                bkupjpg.setIdBas(bku.getIdBas());
                bkupjpg.setKodeakun(bku.getKodeakun());
                bkupjpg.setNilaiMasuk(bku.getNilaiKeluar());
                bkupjpg.setNilaiKeluar(bku.getNilaiMasuk());

                bkuMapper.insertBkuSpj(bkupjpg);
            }
        }
    }

    @Override
    public List<BukuKasUmum> getJenisBku(final Map<String, Object> param) {
        return bkuMapper.getJenisBku(param);
    }

    @Override
    public List<BukuKasUmum> getListJGBP(final Map<String, Object> param) {
        return bkuMapper.getListJGBP(param);
    }

    @Override
    public Integer getBanyakListJGBP(final Map<String, Object> param) {
        return bkuMapper.getBanyakListJGBP(param);
    }

    @Override
    public BukuKasUmum getSaldoAawalJGBP(final Map<String, Object> param) {
        return bkuMapper.getSaldoAawalJGBP(param);
    }

    @Override
    public BukuKasUmum getDataKBUD(final Map<String, Object> param) {
        return bkuMapper.getDataKBUD(param);
    }

    
}
