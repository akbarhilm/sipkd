/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.LraSkpdMapper;
import spp.model.LRA;
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
@Service("neracaSkpdAllServices")
public class LraSkpdImpl implements LraSkpdServices {

    private static final Logger log = LoggerFactory.getLogger(LraSkpdImpl.class);
    @Autowired
    private LraSkpdMapper lraMapper;
    
    
    @Override
    public String getTanggalPosting(final Map<String, Object> param) {
        return lraMapper.getTanggalPosting(param);
    }
    
    @Override
    public List<Map> getTanggalPostingMap(final Map<String, Object> param) {
        return lraMapper.getTanggalPostingMap(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertLraSkpd(LRA lra) {
        lraMapper.insertLraSkpd(lra);
    }
    
    @Override
    public List<Map> getnilaiparam(Map param) {
        return lraMapper.getnilaiparam(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertLraProvinsi(LRA lra) {
        lraMapper.insertLraProvinsi(lra);
    }
    
    @Override
    public String getKodeStatus(final Map<String, Object> param) {
        return lraMapper.getKodeStatus(param);
    }
    
    @Override
    public String getKodeStatusProvinsi(final Map<String, Object> param) {
        return lraMapper.getKodeStatusProvinsi(param);
    }
    
}
