/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.AkunMapper;
import spp.model.Akun;
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
@Service("akunServices")
public class AkunImpl implements AkunServices {

    private static final Logger log = LoggerFactory.getLogger(AkunImpl.class);
    @Autowired
    private AkunMapper journalMapper;
    
    
    
    @Override
    public Integer getBanyakAllAkun(final Map<String, Object> param) {
        return journalMapper.getBanyakAllAkun(param);
    }

    
    @Override
    public List<Akun> getAllAkun(final Map<String, Object> param) {
        return journalMapper.getAllAkun(param);
    }
    
    @Override
    public Integer getBanyakAkun123(final Map<String, Object> param) {
        return journalMapper.getBanyakAkun123(param);
    }

    
    @Override
    public List<Akun> getAkun123(final Map<String, Object> param) {
        return journalMapper.getAkun123(param);
    }
    
    @Override
    public Integer getBanyakAkunBukuBesar(final Map<String, Object> param) {
        return journalMapper.getBanyakAkunBukuBesar(param);
    }

    @Override
    public List<Akun> getAkunBukuBesar(final Map<String, Object> param) {
        return journalMapper.getAkunBukuBesar(param);
    }
    
    @Override
    public Integer getBanyakAkunBukuBesarPpkd(final Map<String, Object> param) {
        return journalMapper.getBanyakAkunBukuBesarPpkd(param);
    }

    @Override
    public List<Akun> getAkunBukuBesarPpkd(final Map<String, Object> param) {
        return journalMapper.getAkunBukuBesarPpkd(param);
    }
    
}
