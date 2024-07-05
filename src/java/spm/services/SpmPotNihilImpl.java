/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.services;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import spm.entity.SpmPotNihilMapper;
import spp.model.SpmPotNihil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Xalamaster
 */

@Transactional(readOnly = true)
@Service("SpmPotNihilServices")
public class SpmPotNihilImpl implements SpmPotNihilServices {
        
    @Autowired
    private SpmPotNihilMapper spmpotMapper;
    
    private static final Logger log = LoggerFactory.getLogger(SpmPotNihilImpl.class);
        
    @Override
    public List<SpmPotNihil> getAllPot(Map<String, Object> param) {
        return spmpotMapper.getAllPot(param);
    }
 
    @Override
    public Integer getBanyakPot(Map<String, Object> param) {
        return spmpotMapper.getBanyakPot(param);
    }
    
    @Override
    public List<SpmPotNihil> getAkunPendapatan(Map<String, Object> param) {
        return spmpotMapper.getAkunPendapatan(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void addPot(SpmPotNihil spmpot) {
        final BigDecimal nilaiPot = spmpot.getNilaiPot();
        final BigDecimal nilaiPotRound = nilaiPot.setScale(0, RoundingMode.UP);
       // final Integer nilai = Integer.valueOf(nilaiPotRound.intValueExact());
        log.debug("sakit"+nilaiPotRound);
        log.debug("sakit"+nilaiPot);
        //log.debug("sakit"+nilaiPotRound);
        if (nilaiPotRound.compareTo(BigDecimal.ZERO) == 0) {
                //log.debug("-------------- nilai bast == 0 --------------");

        } else {
            log.debug("fucking shit");
            spmpotMapper.addPot(spmpot);

        }
        
        /*if(nilai > 0){
            spmpotMapper.addPot(spmpot);
        }*/
        
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updatePot(SpmPotNihil spmpot) {
         final BigDecimal nilaiPot = spmpot.getNilaiPot();
        final BigDecimal nilaiPotRound = nilaiPot.setScale(0, RoundingMode.UP);
        log.debug("sakit2"+nilaiPotRound);
        log.debug("sakit2"+nilaiPot);
        if (nilaiPotRound.compareTo(BigDecimal.ZERO) == 0) {
                //log.debug("-------------- nilai bast == 0 --------------");
             log.debug("fucking shit");
            spmpotMapper.deletePot(spmpot);
        }else{
        spmpotMapper.updatePot(spmpot);
    }
    }
        
    
}
