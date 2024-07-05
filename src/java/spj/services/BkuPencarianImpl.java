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
import spj.entity.BkuPencarianMapper;
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
@Service("bkuPencarianServices")
public class BkuPencarianImpl implements BkuPencarianServices {

    private static final Logger log = LoggerFactory.getLogger(BkuPencarianImpl.class);
    @Autowired
    private BkuPencarianMapper bkuMapper;

    
    @Override
    public List<BukuKasUmum> getPencarianNoDok(final Map<String, Object> param) {
        return bkuMapper.getPencarianNoDok(param);
    }

    @Override
    public List<BukuKasUmum> getPencarianKegiatan(final Map<String, Object> param) {
        return bkuMapper.getPencarianKegiatan(param);
    }

    @Override
    public List<BukuKasUmum> getPencarianSisaUangPptk(final Map<String, Object> param) {
        return bkuMapper.getPencarianSisaUangPptk(param);
    }

    @Override
    public List<BukuKasUmum> getPencarianSisaUangPerPptk(final Map<String, Object> param) {
        return bkuMapper.getPencarianSisaUangPerPptk(param);
    }

    @Override
    public Integer getBanyakCariNoDok(final Map<String, Object> param) {
        return bkuMapper.getBanyakCariNoDok(param);
    }

    @Override
    public Integer getBanyakCariKegiatan(final Map<String, Object> param) {
        return bkuMapper.getBanyakCariKegiatan(param);
    }

    @Override
    public Integer getBanyakCariSisaUangPptk(final Map<String, Object> param) {
        return bkuMapper.getBanyakCariSisaUangPptk(param);
    }

    @Override
    public Integer getBanyakCariSisaUangPerPptk(final Map<String, Object> param) {
        return bkuMapper.getBanyakCariSisaUangPerPptk(param);
    }

    @Override
    public List<BukuKasUmum> getListKegiatan(final Map<String, Object> param) {
        return bkuMapper.getListKegiatan(param);
    }

    @Override
    public List<BukuKasUmum> getListNoDokumen(final Map<String, Object> param) {
        return bkuMapper.getListNoDokumen(param);
    }

    @Override
    public Integer getBanyakListKegiatan(final Map<String, Object> param) {
        return bkuMapper.getBanyakListKegiatan(param);
    }

    @Override
    public Integer getBanyakListNoDokumen(final Map<String, Object> param) {
        return bkuMapper.getBanyakListNoDokumen(param);
    }
    
    @Override
    public List<BukuKasUmum> getPencarianCek(final Map<String, Object> param) {
        return bkuMapper.getPencarianCek(param);
    }

    @Override
    public List<BukuKasUmum> getPencarianSetoran(final Map<String, Object> param) {
        return bkuMapper.getPencarianSetoran(param);
    }

    @Override
    public List<BukuKasUmum> getPencarianNoDokSPJ(final Map<String, Object> param) {
        return bkuMapper.getPencarianNoDokSPJ(param);
    }

    @Override
    public List<BukuKasUmum> getPencarianPajak(final Map<String, Object> param) {
        return bkuMapper.getPencarianPajak(param);
    }

    @Override
    public List<BukuKasUmum> getPencarianPajakAll(final Map<String, Object> param) {
        return bkuMapper.getPencarianPajakAll(param);
    }

    @Override
    public Integer getBanyakCariCek(final Map<String, Object> param) {
        return bkuMapper.getBanyakCariCek(param);
    }

    @Override
    public Integer getBanyakCariSetoran(final Map<String, Object> param) {
        return bkuMapper.getBanyakCariSetoran(param);
    }

    @Override
    public Integer getBanyakCariNoDokSpj(final Map<String, Object> param) {
        return bkuMapper.getBanyakCariNoDokSpj(param);
    }
    
    @Override
    public Integer getBanyakCariPajak(final Map<String, Object> param) {
        return bkuMapper.getBanyakCariPajak(param);
    }

    @Override
    public Integer getBanyakCariPajakAll(final Map<String, Object> param) {
        return bkuMapper.getBanyakCariPajakAll(param);
    }
    
    @Override
    public List<BukuKasUmum> getListPptk(final Map<String, Object> param) {
        return bkuMapper.getListPptk(param);
    }

    @Override
    public Integer getBanyakListPptk(final Map<String, Object> param) {
        return bkuMapper.getBanyakListPptk(param);
    }
    
    @Override
    public BukuKasUmum getNilaiAnggaran(final Map<String, Object> param) {
        return bkuMapper.getNilaiAnggaran(param);
    }
    
    @Override
    public BukuKasUmum getNilaiAnggaranTU(final Map<String, Object> param) {
        return bkuMapper.getNilaiAnggaranTU(param);
    }
    
    @Override
    public List<BukuKasUmum> getPencarianKegiatanTU(final Map<String, Object> param) {
        return bkuMapper.getPencarianKegiatanTU(param);
    }

    @Override
    public Integer getBanyakCariKegiatanTU(final Map<String, Object> param) {
        return bkuMapper.getBanyakCariKegiatanTU(param);
    }

    @Override
    public Integer getBanyakListXlsBkuPencarianKegiatan(Map<String, Object> param) {
        return bkuMapper.getBanyakListXlsBkuPencarianKegiatan(param);//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Map> getListXlsBkuPencarianKegiatan(Map<String, Object> param) {
        return bkuMapper.getListXlsBkuPencarianKegiatan(param);//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}




