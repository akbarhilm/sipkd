/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.NeracaSkpdMapper;
import spp.model.Neraca;
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
@Service("neracaSkpdServices")
public class NeracaSkpdImpl implements NeracaSkpdServices {

    private static final Logger log = LoggerFactory.getLogger(NeracaSkpdImpl.class);
    @Autowired
    private NeracaSkpdMapper neracaMapper;
    
    
    @Override
    public String getTanggalPosting(final Map<String, Object> param) {
        return neracaMapper.getTanggalPosting(param);
    }
    
    @Override
    public List<Map> getTanggalPostingMap(final Map<String, Object> param) {
        return neracaMapper.getTanggalPostingMap(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertNeracaSkpd(Neraca neraca) {
        neracaMapper.insertNeracaSkpd(neraca);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertNeracaPkpd(Neraca neraca) {
        neracaMapper.insertNeracaPkpd(neraca);
    }
    
    @Override
    public List<Map> getnilaiparam(Map param) {
        return neracaMapper.getnilaiparam(param);
    }
    
    @Override
    public List<Neraca> getBulan(final Map<String, Object> param) {
        return neracaMapper.getBulan(param);
    }

    @Override
    public String getKodeStatus(final Map<String, Object> param) {
        return neracaMapper.getKodeStatus(param);
    }
    
    @Override
    public String getKodeStatusProvinsi(final Map<String, Object> param) {
        return neracaMapper.getKodeStatusProvinsi(param);
    }
    
}

