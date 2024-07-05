/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.LaporanSkpdMapper;
import spp.model.CetakSkpd;
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
@Service("laporanSkpdServices")
public class LaporanSkpdImpl implements LaporanSkpdServices {

    private static final Logger log = LoggerFactory.getLogger(LaporanSkpdImpl.class);
    @Autowired
    private LaporanSkpdMapper cetakMapper;
    
    
    @Override
    public List<Map> getnilaiparam(Map param) {
        return cetakMapper.getnilaiparam(param);
    }
    
}

  