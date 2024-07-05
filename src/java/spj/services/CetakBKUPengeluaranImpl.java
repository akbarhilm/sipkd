/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.LaporanSkpdMapper;
import spj.entity.CetakBKUPengeluaranMapper;
import spp.model.CetakSkpd;
import spp.model.BukuKasUmum;
import spp.model.Kegiatan;
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
@Service("cetakbkuService")
public class CetakBKUPengeluaranImpl implements CetakBKUPengeluaranServices {

    private static final Logger log = LoggerFactory.getLogger(CetakBKUPengeluaranImpl.class);
    @Autowired
    private CetakBKUPengeluaranMapper laporancetakbkuMapper;
    
     @Override
    public List<Map> getListXlsBku(Map<String, Object> param) {
        return laporancetakbkuMapper.getListXlsBku(param);
    }

    @Override
    public Integer getBanyakListXlsBku(Map<String, Object> param) {
        return laporancetakbkuMapper.getBanyakListXlsBku(param);
    }
    
    @Override
    public List<Map> getnilaiparam(Map param) {
        return laporancetakbkuMapper.getnilaiparamBKU(param);
    }
    
    @Override
    public List<BukuKasUmum> getKegiatan(Map param) {
        return laporancetakbkuMapper.getKegiatanBKU(param);
    }

    @Override
    public Integer getCountKegiatan(Map param) {
        return laporancetakbkuMapper.getCountKegiatanBKU(param);
    }
    
    @Override
    public List<BukuKasUmum> getBulanBKU(Map param) {
    return laporancetakbkuMapper.getBulanBKUPengeluaran(param);
    }
    
    @Override
    public List<BukuKasUmum> getBulanBKUPengeluaran2(Map param) {
    return laporancetakbkuMapper.getBulanBKUPengeluaran2(param);
    }
    
    @Override 
    @Transactional(readOnly = false)
    public void insertBKUPengeluaran(BukuKasUmum bku) {
        laporancetakbkuMapper.insertBKUPengeluaran(bku);
    }
    
    @Override
    public List<BukuKasUmum> getListBkuPengeluaran(Map param) {
        return laporancetakbkuMapper.getListBkuPengeluaran(param);
    }

    @Override
    public Integer getCountListBkuPengeluaran(Map param) {
        return laporancetakbkuMapper.getCountListBkuPengeluaran(param);
    }
    
    @Override
    public List<BukuKasUmum> getListBkuPerKegiatan(Map param) {
        return laporancetakbkuMapper.getListBkuPerKegiatan(param);
    }

    @Override
    public Integer getCountListBkuPerKegiatan(Map param) {
        return laporancetakbkuMapper.getCountListBkuPerKegiatan(param);
    }

    @Override
    public List<BukuKasUmum> getTanggalBelumJurnal(Map param) {
        return laporancetakbkuMapper.getTanggalBelumJurnal(param);//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}


