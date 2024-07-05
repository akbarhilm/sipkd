/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spj.entity.GenNumberMapper;
import spj.entity.SpjMapper;
import spp.model.Bendahara;
import spp.model.Spj;
import spp.model.SpjRinci;

@Transactional(readOnly = true)
@Service("spjServices")
public class SpjImpl implements SpjServices {

    private static final Logger log = LoggerFactory.getLogger(SpjImpl.class);

    @Autowired
    private SpjMapper spjMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<Spj> getSpj(Map<String, Object> param) {
        return spjMapper.getSpj(param);
    }

    @Override
    public List<Spj> getListJourSpj(Map<String, Object> param) {
        return spjMapper.getListJourSpj(param);
    }

    @Override
    public Integer getCountSpj(Map param) {
        return spjMapper.getCountSpj(param);
    }
    
    @Override
    public String getValidasiUpgu(Map param) {
        return spjMapper.getValidasiUpgu(param);
    }
    
    @Override
    public List<Spj> getListValidasiUpgu(Map param) {
        return spjMapper.getListValidasiUpgu(param);
    }
    
    @Override
    public Integer getBulanAdaSpj(Map param) {
        return spjMapper.getBulanAdaSpj(param);
    }
    
    @Override
    public String getNihilNihil(Map param) {
        return spjMapper.getNihilNihil(param);
    }
   
    @Override
    public Integer getBulanSudahSpj(Map param) {
        return spjMapper.getBulanSudahSpj(param);
    }

    @Override
    public Bendahara getBendaharaById(Map param) {
        return spjMapper.getBendaharaById(param);
    }

    @Override
    public List<Spj> getSpjRinci(Map<String, Object> param) {
        return spjMapper.getSpjRinci(param);
    }

    @Override
    public List<SpjRinci> getSpjRinciKegiatan(Map<String, Object> param) {
        return spjMapper.getSpjRinciKegiatan(param);
    }

    @Override
    public List<SpjRinci> getSpjRinciKegiatanTU(Map<String, Object> param) {
        return spjMapper.getSpjRinciKegiatanTU(param);
    }

    @Override
    public List<Spj> getComboKegiatan(Map<String, Object> param) {
        return spjMapper.getComboKegiatan(param);
    }

    @Override
    public Integer getCountSpjRinci(Map param) {
        return spjMapper.getCountSpjRinci(param);
    }

    @Override
    public Integer getCountSpjRinciKegiatan(Map param) {
        return spjMapper.getCountSpjRinciKegiatan(param);
    }

    @Override
    public Integer getCountSpjRinciKegiatanTU(Map param) {
        return spjMapper.getCountSpjRinciKegiatanTU(param);
    }

    @Override
    public Integer getCountComboKegiatan(Map param) {
        return spjMapper.getCountComboKegiatan(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSpj(Spj spj) {
        final Map param = new HashMap();
        param.put("tahun", spj.getTahun());
        param.put("idskpd", spj.getSkpd().getIdSkpd());
        spj.setNoSpj(genNumberMapper.getSpjNo(param));
        spj.setIdSpj(spj.getIdSpj());
        spjMapper.insertSpj(spj);
    }
    
    @Override
    public Spj getSpjById(Integer id) {
        return spjMapper.getSpjById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSpj(Spj spj) {
        spj.setIdSpj(spj.getIdSpj());
        spjMapper.updateSpj(spj);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSpjRinci(SpjRinci spjRinci) {
        log.debug("spj rinci ============ "+spjRinci.getIdSpjRinci());
        spjMapper.insertSpjRinci(spjRinci);
        spjMapper.deleteSpjRinci();
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertJournalSpj(Spj spj) {
        spjMapper.insertJournalSpj(spj);
    }

    @Override
    public Spj getKegiatanById(Map id) {
        log.debug(" id kegiatab " + id);
        return spjMapper.getKegiatanById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSpjRinci(SpjRinci spjRinci) {
        Integer idspj = spjRinci.getIdSpj();
      
        spjMapper.updateSpjRinci(spjRinci);
        //spjMapper.deleteSpjRinci();
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteSpjRinciKegiatan(SpjRinci spjRinci) {
        spjMapper.deleteSpjRinciKegiatan(spjRinci);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteSpjBlMaster(Integer idSpj) {
        spjMapper.deleteSpjRinciByIdSpj(idSpj);
        spjMapper.deleteSpjBlMaster(idSpj);
        //spjMapper.deleteSpjRinciByIdSpj(idSpj);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteUpdateSpjRinci(Map param) {
        spjMapper.deleteUpdateSpjRinci(param);
    }

    @Override
    public Integer getCountBulanSpjByIdSkpdDanBulan(Map param) {
    return spjMapper.getCountBulanSpjByIdSkpdDanBulan(param);
    }

    @Override
    public String getBulanByBulan(Integer idspj) {
    return spjMapper.getBulanByBulan(idspj);
    }

    @Override
    public BigDecimal getTotalSpjByIdSkpdAndTahun(Map param) {
    return spjMapper.getTotalSpjByIdSkpdAndTahun(param);
    }

    @Override
    public Integer getBanyakSpjBelumSah(Map param) {
    return spjMapper.getBanyakSpjBelumSah(param);
    }
    
    @Override
    public Integer getBanyakBulan(Map param) {
    return spjMapper.getBanyakBulan(param);
    }
    
    @Override
    public Integer getBanyakJourSpj(Map param) {
    return spjMapper.getBanyakJourSpj(param);
    }
    
    @Override
    public List<Spj> getBulan(Map param) {
    return spjMapper.getBulan(param);
    }
    
    @Override
    public List<Spj> getBulanJournal(Map param) {
    return spjMapper.getBulanJournal(param);
    }
    
    @Override
    public Spj getKodeAktif(Map param) {
        return spjMapper.getKodeAktif(param);
    }
    
    @Override
    public Spj getTotalPagu(Map param) {
        return spjMapper.getTotalPagu(param);
    }
    
    @Override
    public Spj getTotalPaguTU(Map param) {
        return spjMapper.getTotalPaguTU(param);
    }
    
    @Override
    public Spj getTotalPaguIndex(Map param) {
        return spjMapper.getTotalPaguIndex(param);
    }
    
    @Override
    public List<Spj> getComboKegiatanAktif1(Map<String, Object> param) {
        return spjMapper.getComboKegiatanAktif1(param);
    }

    @Override
    public Integer getCountComboKegiatanAktif1(Map param) {
        return spjMapper.getCountComboKegiatanAktif1(param);
    }

    

}
