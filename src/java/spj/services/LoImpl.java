/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.LoMapper;
import spp.model.LO;
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
@Service("loServices")
public class LoImpl implements LoServices {

    private static final Logger log = LoggerFactory.getLogger(LoImpl.class);
    @Autowired
    private LoMapper cetakMapper;
    
    
    @Override
    public List<Map> getnilaiparam(Map param) {
        return cetakMapper.getnilaiparam(param);
    }
    
    @Override
    public List<LO> getSkpdCombo(Integer idskpd) {
        return cetakMapper.getSkpdCombo(idskpd);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertLo(LO lo) {
        cetakMapper.insertLo(lo);
    }
    
    @Override
    public List<LO> getBulan(final Map<String, Object> param) {
        return cetakMapper.getBulan(param);
    }
    
    @Override
    public Integer getIdInduk(Integer idskpd) {
        return cetakMapper.getIdInduk(idskpd);
    }
    
}

  