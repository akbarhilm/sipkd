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
import spj.entity.BkuSpjPajakMapper;
import spp.model.BukuKasUmum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Zainab
 */
@Transactional(readOnly = true)
@Service("bkuSpjPajakServices")
public class BkuSpjPajakImpl implements BkuSpjPajakServices {
    
    private static final Logger log = LoggerFactory.getLogger(BkuSpjPajakImpl.class);
    @Autowired
    private BkuSpjPajakMapper bkuMapper;
    
    @Override
    public List<BukuKasUmum> getKegiatan(final Map<String, Object> param) {
        return bkuMapper.getKegiatan(param);
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
        final Integer noBku = bkuMapper.getBkuNo(genparam);
        
        log.debug("insertBkuSpj - no bku SPJ sebelum masuk loop ================= " + noBku);
        for (BukuKasUmum bku : param) {
            /*if (bku.getNoBKU() == null) {
             log.debug("masuk if == null");
             bku.setNoBKU(noBku);
             }*/
            
            if ("JJ".equals(bku.getKodeTransaksi())) {
                log.debug("insertBkuSpj -  if (bku.getKodeTransaksi() == \"JJ\") - no bku SPJ ================= " + noBku);
                bku.setNoBKU(noBku);
                bkuMapper.insertBkuSpj(bku);
            } else {
                Integer noBkuPajak = bkuMapper.getBkuNo(genparam);
                bku.setNoBKU(noBkuPajak);
                bku.setNoBkuSpj(noBku);
                bku.setKetKegiatan("PAJAK");
                bkuMapper.insertBkuSpj(bku);
            }
            
        }
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
    public Integer getBanyakListPajak(final Map<String, Object> param) {
        return bkuMapper.getBanyakListPajak(param);
    }
    
    @Override
    public List<BukuKasUmum> getListPajak(final Map<String, Object> param) {
        return bkuMapper.getListPajak(param);
    }
    
    @Override
    public List<BukuKasUmum> getBulanByRekap(final Map<String, Object> param) {
        return bkuMapper.getBulanByRekap(param);
    }
    
    @Override
    public List<BukuKasUmum> getListIndex(final BukuKasUmum param) {
        return bkuMapper.getListIndex(param);
    }
    
    @Override
    public Integer getBanyakListIndex(final BukuKasUmum param) {
        return bkuMapper.getBanyakListIndex(param);
    }
    
    public BukuKasUmum getBkuEdit(final Map<String, Object> param) {
        return bkuMapper.getBkuEdit(param);
    }
    
    @Override
    public List<BukuKasUmum> getDataPajak(final Map<String, Object> param) {
        return bkuMapper.getDataPajak(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateBkuSpj(List<BukuKasUmum> param) {
        Integer nobku = param.get(0).getNoBkuSpj();
        final Map delparam = new HashMap();
        delparam.put("idskpd", param.get(0).getIdskpd());
        delparam.put("nobku", nobku);
        delparam.put("tahun", param.get(0).getTahun());

        //bkuMapper.deleteBkuSpj(delparam);
        for (BukuKasUmum bku : param) {
            String status = bku.getBkuStatus();
            String kodetrx = bku.getKodeTransaksi();
            Integer idbku = bku.getIdBku();
            
            if ("JJ".equals(kodetrx)) {
                log.debug("IMPLEMENTS STATUS SPJ ========== "+status);
                
                if ("edit".equals(status)) {
                    bkuMapper.updateById(bku);
                } else if ("add".equals(status)) {
                    bkuMapper.insertBkuSpj(bku);
                } else if ("delete".equals(status)) {
                    delparam.put("idbku", idbku);
                    log.debug("ID BKU DELETE ========== "+idbku);
                    bkuMapper.deleteById(delparam);
                }
            } else { // untuk PAJAK
                if ("edit".equals(status)) {
                    bkuMapper.updateBku(bku);
                } else { // untuk P% input baru
                    Integer noBkuPajak = bkuMapper.getBkuNo(delparam);
                    bku.setNoBKU(noBkuPajak);
                    bku.setKetKegiatan("PAJAK");
                    bkuMapper.insertBkuSpj(bku);
                }
            }
        }
    }
    
    @Override
    public BukuKasUmum getNilaiSisaSpjTunai(final Map<String, Object> param) {
        return bkuMapper.getNilaiSisaSpjTunai(param);
    }
    
}
