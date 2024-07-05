/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.BukuBesarJurnalSkpdAllMapper;
import spp.model.BukuBesar;
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
@Service("bukuBesarJurnalSkpdAllServices")
public class BukuBesarJurnalSkpdAllImpl implements BukuBesarJurnalSkpdAllServices {

    private static final Logger log = LoggerFactory.getLogger(BukuBesarJurnalSkpdAllImpl.class);
    @Autowired
    private BukuBesarJurnalSkpdAllMapper cetakMapper;
    
    
    @Override
    public String getTanggalPosting(final Map<String, Object> param) {
        return cetakMapper.getTanggalPosting(param);
    }
    
    @Override
    public List<Map> getTanggalPostingMap(final Map<String, Object> param) {
        return cetakMapper.getTanggalPostingMap(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertBukuBesarSkpd(BukuBesar spj) {
        cetakMapper.insertBukuBesarSkpd(spj);
    }
    
    @Override
    public List<Map> getnilaiparam(Map param) {
        return cetakMapper.getnilaiparam(param);
    }

    @Override
    public Integer getBannyakListBukuBesar(final Map<String, Object> param) {
        return cetakMapper.getBannyakListBukuBesar(param);
    }

    @Override
    public List<BukuBesar> getListBukuBesar(final Map<String, Object> param) {
        return cetakMapper.getListBukuBesar(param);
    }
    
}

