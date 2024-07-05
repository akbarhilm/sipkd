/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.services;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spm.entity.SpdMapper;

import spp.model.Spd;

/**
 *
 * @author erzy
 */

@Transactional(readOnly = true)
@Service("SpdServices")
public class SpdImpl implements SpdServices {
    
      @Autowired
    private SpdMapper spdMapper;
      
       @Override
    public List<Spd> getSpd(Map param) {
        return spdMapper.getSpd(param);
    }

    @Override
    public Integer getBanyakSpd(Map param) {
        return spdMapper.getBanyakSpd(param);
    }

    
}
