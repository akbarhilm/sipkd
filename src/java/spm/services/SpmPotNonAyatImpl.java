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
import spp.model.SpmPotNonAyat;
import spm.entity.SpmPotNonAyatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spp.model.Kontrak;
/**
 *
 * @author Xalamaster
 */
@Transactional(readOnly = true)
@Service("SpmPotNonAyatServices")
public class SpmPotNonAyatImpl implements SpmPotNonAyatServices  {
    
    @Autowired
    private SpmPotNonAyatMapper spmpotnonayatMapper;
    
    private static final Logger log = LoggerFactory.getLogger(SpmPotNonAyatImpl.class);
    
    @Override
    public List<SpmPotNonAyat> getTotSpm(Map<String, Object> param) {
        return spmpotnonayatMapper.getTotSpm(param);
    }
       
    @Override
    public List<SpmPotNonAyat> getVkontrak(Map<String, Object> param) {
        return spmpotnonayatMapper.getVkontrak(param);
    }
       
    @Override
    public List<SpmPotNonAyat> getJamsostek(Map<String, Object> param) {
        return spmpotnonayatMapper.getJamsostek(param);
    }
       
    @Override
    public List<SpmPotNonAyat> getValTblSpm(Map<String, Object> param) {
        return spmpotnonayatMapper.getValTblSpm(param);
    }
        
    @Override
    @Transactional(readOnly = false)
    public void addPotayat(SpmPotNonAyat spmpotnonayat) {
        final BigDecimal nilaiPot = spmpotnonayat.getNilaiPot();
        final BigDecimal nilaiPotRound = nilaiPot.setScale(0, RoundingMode.UP);
        //final Integer nilai = Integer.valueOf(nilaiPotRound.intValueExact());
        
        log.debug("============ NILAI POTONGAN nilai rounding ============ " + nilaiPotRound);
        
        if (nilaiPotRound.compareTo(BigDecimal.ZERO) == 0) {
                //log.debug("-------------- nilai bast == 0 --------------");

        } else {
            spmpotnonayatMapper.addPotayat(spmpotnonayat);

        }
        /*if(nilai > 0){
            spmpotnonayatMapper.addPotayat(spmpotnonayat);
        }*/
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updatePotayat(SpmPotNonAyat spmpotnonayat) {
        spmpotnonayatMapper.updatePotayat(spmpotnonayat);
    }
    
    @Override
    public SpmPotNonAyat getPotUmk(Map<String, Object> param) {
        return spmpotnonayatMapper.getPotUmk(param);
    }
        
    
    @Override
    public SpmPotNonAyat getNamaAkun(String akun) {
        return spmpotnonayatMapper.getNamaAkun(akun);
    }
    
    @Override
    public String getNamaAkunString(String akun) {
        return spmpotnonayatMapper.getNamaAkunString(akun);
    }

    @Override
    public List<Kontrak> getKontrak(Map<String, Object> param) {
        return spmpotnonayatMapper.getKontrak(param);
    }
    
    
}
