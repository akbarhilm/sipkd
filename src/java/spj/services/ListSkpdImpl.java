/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.ListSkpdMapper;
import spp.model.Skpd;
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
@Service("listSkpdServices")
public class ListSkpdImpl implements ListSkpdServices {

    private static final Logger log = LoggerFactory.getLogger(ListSkpdImpl.class);
    @Autowired
    private ListSkpdMapper journalMapper;
    
    
    @Override
    public Integer getBanyakAllSkpd(final Map<String, Object> param) {
        return journalMapper.getBanyakAllSkpd(param);
    }
    
    @Override
    public List<Skpd> getAllSkpd(final Map<String, Object> param) {
        return journalMapper.getAllSkpd(param);
    }
    
    @Override
    public Integer getBanyakSkpdwithID1(final Map<String, Object> param) {
        return journalMapper.getBanyakSkpdwithID1(param);
    }
    
    @Override
    public List<Skpd> getSkpdwithID1(final Map<String, Object> param) {
        return journalMapper.getSkpdwithID1(param);
    }
    
    
}

  