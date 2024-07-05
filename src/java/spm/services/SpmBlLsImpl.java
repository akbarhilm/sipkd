/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.services;

import static java.lang.System.console;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import spp.entity.GenNumberMapper;
import spm.entity.SpmBlLsMapper;
import spp.model.SpmBlLs;
import spp.model.SpmBlLsRinci;
import spm.entity.GenNumberMapper;
//import spp.model.SpmBlLsUjiCoba;

/**
 *
 * @author Xalamaster
 */
@Transactional(readOnly = true)
@Service("SpmBlLsServices")
public class SpmBlLsImpl implements SpmBlLsServices {
    
    private static final Logger log = LoggerFactory.getLogger(SpmBlLsImpl.class);
    @Autowired
    private SpmBlLsMapper spmBlLsMapper;
    
    @Autowired
    private GenNumberMapper genNumberMapper;
    
    
    @Override
    public List<SpmBlLs> getAllSpmBlLs(Map param) {
        return spmBlLsMapper.getAllSpmBlLs(param);
    }

    @Override
    public Integer getBanyakSpmBlLs(Map param) {
        return spmBlLsMapper.getBanyakSpmBlLs(param);

    }
    
    @Override
    public Integer getCekSpm(Integer param) {
        return spmBlLsMapper.getCekSpm(param);

    }
    
     @Override
    public SpmBlLs getSpmBlLsById(Integer param) {
        return spmBlLsMapper.getSpmBlLsById(param);
    }
    
     @Override
    public BigDecimal getPotonganValidatorNonAyat(Integer param) {
        return spmBlLsMapper.getPotonganValidatorNonAyat(param);
    }
        
     @Override
    public BigDecimal getPotonganValidatorUangMuka(Integer param) {
        return spmBlLsMapper.getPotonganValidatorUangMuka(param);
    }
        
     @Override
    public Integer getIdSpmByIdSpp(Integer param) {
        return spmBlLsMapper.getIdSpmByIdSpp(param);
    }
    
    
    
    @Override
    public Map<String, BigDecimal> getTotalAnggaranDanSpd(Map<String, Object> param) {
        final Map<String, BigDecimal> hasil = new LinkedHashMap<String, BigDecimal>(2);
        BigDecimal totalanggup = spmBlLsMapper.getTotalPaguBySkpd(param);
        hasil.put("anggaran", totalanggup);
        try {
            hasil.put("spd", totalanggup.subtract(spmBlLsMapper.getTotalSPDBySKPDDanTahun(param)));
        } catch (Exception e) {
            hasil.put("spd", new BigDecimal(0));
        }
        return hasil;
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateSpmBlLsMaster(SpmBlLs spmBlLs) {
        spmBlLsMapper.updateSpmBlLsMaster(spmBlLs);
    }  
    
    @Override
    public List<SpmBlLsRinci> getAllSpdBl(Map param) {
        return spmBlLsMapper.getAllSpdBl(param);
    }

    @Override
    public Integer getBanyakSpdBl(Map param) {
        return spmBlLsMapper.getBanyakSpdBl(param);

    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertSpmBlLsMaster(SpmBlLs spmBlLs) {
        //#{tahun} AND i_idskpd =  #{idskpd}
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("tahun", spmBlLs.getTahun());
        map.put("idskpd", spmBlLs.getSkpd().getIdSkpd());
        spmBlLs.setNoSpm(genNumberMapper.getSpmNo(map));
        spmBlLsMapper.insertSpmBlLsMaster(spmBlLs);
        
        final String kodenihil = spmBlLs.getKodeNihil();
        log.debug("insertSpmBlLsMaster(), kode nihil ----> "+kodenihil);
        log.debug("insertSpmBlLsMaster(), id spm --------> "+spmBlLs.getIdspm());
        
        // 2017 INSERT POT NIHIL TIDAK OTOMATIS, ADA PANEL KHUSUS UNTUK SIMPAN POTONGAN, AKUNNYA > 1
        /*
        if ("1".equals(kodenihil)){
            try {
                 spmBlLsMapper.insertPotNihil(spmBlLs);
            } catch (Exception e) {
            }
        }
        */
          
        /* final List<SpmUpRinci> spmUpRinci = spmUp.getSpmUpRinci();
         final Integer idspm = spmUp.getIdspm();
         final Integer idspp = spmUp.getId();
         log.debug(" ################ idspm " + idspm);
         for (SpmUpRinci spmUpRinci1 : spmUpRinci) {
         spmUpRinci1.setIdspm(idspm);
         spmUpRinci1.setIdspp(idspp);
         spmUpMapper.insertSpmUpRinci(spmUpRinci1);
         }*/

    }

    @Override
    public Integer getAkunNihil(Map param) {
        return spmBlLsMapper.getAkunNihil(param);
    }
    
    @Override
    public SpmBlLs getBasNihil(Map param) {
        return spmBlLsMapper.getBasNihil(param);
    }
    
    @Override
    public SpmBlLs getSppUangMuka(Map param) {
        return spmBlLsMapper.getSppUangMuka(param);
    }
    
    @Override
    public SpmBlLs getKodePotUmk(Map param) {
        return spmBlLsMapper.getKodePotUmk(param);
    }
    
    @Override
    public SpmBlLs getSpmBlLsPfkById(Integer param) {
        return spmBlLsMapper.getSpmBlLsPfkById(param);
    }
    
    @Override
    public BigDecimal getVaBank(Integer param) {
        return spmBlLsMapper.getVaBank(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updatePfk(SpmBlLs spmBlLs) {
        spmBlLsMapper.updatePfk(spmBlLs);
    }  
    
    @Override
    @Transactional(readOnly = false)
    public void updateSppPfk(SpmBlLs spmBlLs) {
        spmBlLsMapper.updateSppPfk(spmBlLs);
    }  
    
    @Override
    public List<SpmBlLs> getBankInduk(Map param) {
        return spmBlLsMapper.getBankInduk(param);
    }

    @Override
    public Integer getCountBankInduk(Map param) {
        return spmBlLsMapper.getCountBankInduk(param);
    }
    
}
