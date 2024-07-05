/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.NoJurnalMapper;
import spp.model.NoJurnal;
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
@Service("noJurnalServices")
public class NoJurnalImpl implements NoJurnalServices {

    private static final Logger log = LoggerFactory.getLogger(NoJurnalImpl.class);
    @Autowired
    private NoJurnalMapper journalMapper;
    
    
    
    @Override
    public Integer getBanyakNoJurnalSkpdAll(final Map<String, Object> param) {
        return journalMapper.getBanyakNoJurnalSkpdAll(param);
    }
    
    @Override
    public Integer getBanyakNoJurnalSkpd(final Map<String, Object> param) {
        return journalMapper.getBanyakNoJurnalSkpd(param);
    }
    
    @Override
    public Integer getBanyakNoJurnal(final Map<String, Object> param) {
        return journalMapper.getBanyakNoJurnal(param);
    }
    
    @Override
    public Integer getBanyakNoJurnalAll(final Map<String, Object> param) {
        return journalMapper.getBanyakNoJurnalAll(param);
    }

    
    @Override
    public List<NoJurnal> getNoJurnalSkpdAll(final Map<String, Object> param) {
        return journalMapper.getNoJurnalSkpdAll(param);
    }
    
    @Override
    public List<NoJurnal> getNoJurnalSkpd(final Map<String, Object> param) {
        return journalMapper.getNoJurnalSkpd(param);
    }
    
    @Override
    public List<NoJurnal> getNoJurnal(final Map<String, Object> param) {
        return journalMapper.getNoJurnal(param);
    }
    
    @Override
    public List<NoJurnal> getNoJurnalAll(final Map<String, Object> param) {
        return journalMapper.getNoJurnalAll(param);
    }
    
}


