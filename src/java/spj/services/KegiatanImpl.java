/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.KegiatanMapper;
import spp.model.Kegiatan;
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
@Service("kegiatanServices")
public class KegiatanImpl implements KegiatanServices {

    private static final Logger log = LoggerFactory.getLogger(KegiatanImpl.class);
    @Autowired
    private KegiatanMapper journalMapper;
    
    
    
    @Override
    public Integer getBanyakKegiatan(final Map<String, Object> param) {
        return journalMapper.getBanyakKegiatan(param);
    }

    
    @Override
    public List<Kegiatan> getKegiatan(final Map<String, Object> param) {
        return journalMapper.getKegiatan(param);
    }
    
    
}
