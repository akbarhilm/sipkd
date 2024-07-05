/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2d.services;

import java.util.List;
import java.util.Map;
import sp2d.entity.StatusSpmMapper;
import spp.model.StatusSpm;
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
@Service("statusSpmServices")
public class StatusSpmImpl implements StatusSpmServices {

    private static final Logger log = LoggerFactory.getLogger(StatusSpmImpl.class);
    @Autowired
    private StatusSpmMapper journalMapper;
    
    
    
    @Override
    public Integer getBanyakSp2dWilayah(final Map<String, Object> param) {
        return journalMapper.getBanyakSp2dWilayah(param);
    } 

    
    @Override
    public List<StatusSpm> getSp2dWilayah(final Map<String, Object> param) {
        return journalMapper.getSp2dWilayah(param);
    }
    
    
    
}
