/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.LaporanLraMapper;
import spp.model.LaporanLra;
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
@Service("laporanLraServices")
public class LaporanLraImpl implements LaporanLraServices {

    private static final Logger log = LoggerFactory.getLogger(LaporanLraImpl.class);
    @Autowired
    private LaporanLraMapper cetakMapper;
    
    
    @Override
    public List<Map> getnilaiparam(Map param) {
        return cetakMapper.getnilaiparam(param);
    }
    
    @Override
    public List<LaporanLra> getSkpdCombo(Integer idskpd) {
        return cetakMapper.getSkpdCombo(idskpd);
    }
    
    @Override
    public Integer getIdInduk(Integer idskpd) {
        return cetakMapper.getIdInduk(idskpd);
    }
    
    @Override
    public List<LaporanLra> getBulan(final Map<String, Object> param) {
        return cetakMapper.getBulan(param);
    }

}

  