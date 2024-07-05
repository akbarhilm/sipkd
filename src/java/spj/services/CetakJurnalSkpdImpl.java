/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.GenNumberMapper;
import spj.entity.CetakJurnalSkpdMapper;
import spp.model.CetakSkpd;
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
@Service("cetakJurnalSkpdServices")
public class CetakJurnalSkpdImpl implements CetakJurnalSkpdServices {

    private static final Logger log = LoggerFactory.getLogger(CetakJurnalSkpdImpl.class);
    @Autowired
    private CetakJurnalSkpdMapper cetakMapper;
    
    
    @Override
    public List<CetakSkpd> getNoJurnal() {
        return cetakMapper.getNoJurnal();
    }
    
    @Override
    public List<Map> getnilaiparam(Map param) {
        return cetakMapper.getnilaiparam(param);
    }
    
    @Override
    public List<CetakSkpd> getNoJurnalBySkpd(Map param) {
        return cetakMapper.getNoJurnalBySkpd(param);
    }
   
}

  