/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.services;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import spm.entity.SpmPotUangmukaMapper;
import spp.model.SpmPotUangmuka;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Xalamaster
 */

@Transactional(readOnly = true)
@Service("SpmPotUangmukaServices")
public class SpmPotUangmukaImpl implements SpmPotUangmukaServices {
        
    @Autowired
    private SpmPotUangmukaMapper spmpotMapper;
    
    private static final Logger log = LoggerFactory.getLogger(SpmPotUangmukaImpl.class);
        
    @Override
    public List<SpmPotUangmuka> getAllPot(Map<String, Object> param) {
        return spmpotMapper.getAllPot(param);
    }
 
    @Override
    public Integer getBanyakPot(Map<String, Object> param) {
        return spmpotMapper.getBanyakPot(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void addPot(SpmPotUangmuka spmpot) {
        final BigDecimal nilaiPot = spmpot.getNilaiPot();
        final BigDecimal nilaiPotRound = nilaiPot.setScale(0, RoundingMode.UP);
        final Integer nilai = Integer.valueOf(nilaiPotRound.intValueExact());
        
        if (nilaiPotRound.compareTo(BigDecimal.ZERO) == 0) {
                //log.debug("-------------- nilai bast == 0 --------------");

        } else {
            spmpotMapper.addPot(spmpot);

        }
        
        /*if(nilai > 0){
            spmpotMapper.addPot(spmpot);
        }*/
        
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updatePot(SpmPotUangmuka spmpot) {
        spmpotMapper.updatePot(spmpot);
    }
        
    
    @Override
    public Map<String, Object> getDataSpp(Integer param) {
        return spmpotMapper.getDataSpp(param);
    }
    
    @Override
    public List<SpmPotUangmuka> getListPotUmk(Map param) {
        return spmpotMapper.getListPotUmk(param);
    }
 
    @Override
    public Integer getBanyakPotUmk(Map param) {
        return spmpotMapper.getBanyakPotUmk(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertUMK(List<SpmPotUangmuka> param) {
        final Map delparam = new HashMap();
        delparam.put("idskpd", param.get(0).getIdSkpd());
        delparam.put("idspm", param.get(0).getIdSpm());
        delparam.put("tahun", param.get(0).getTahun());
        spmpotMapper.deletePotUmk(delparam);
        
        for (SpmPotUangmuka umk : param) {
            spmpotMapper.addPot(umk);
        }
    }
    
    @Override
    public List<SpmPotUangmuka> getKodeUmk(Map<String, Object> param) {
        return spmpotMapper.getKodeUmk(param);
    }
    
    @Override
    public List<SpmPotUangmuka> getAkunDenda(Map<String, Object> param) {
        return spmpotMapper.getAkunDenda(param);
    }
    
}
