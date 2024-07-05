/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.FormBkuMapper;
import spp.model.FormBku;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 
import spp.model.BukuKasUmum;

/**
 *
 * @author Zainab
 */

@Transactional(readOnly = true)
@Service("formBkuServices")
public class FormBkuImpl implements FormBkuServices {

    private static final Logger log = LoggerFactory.getLogger(FormBkuImpl.class);
    @Autowired
    private FormBkuMapper cetakMapper;
    
    
    @Override
    public List<Map> getnilaiparam(Map param) {
        return cetakMapper.getnilaiparam(param);
    }
    
    @Override
    public List<FormBku> getSaldoAwalTunai(Map<String, Object> param) {
        return cetakMapper.getSaldoAwalTunai(param);
    }
    
    @Override
    public List<FormBku> getSaldoAwalBank(Map<String, Object> param) {
        return cetakMapper.getSaldoAwalBank(param);
    }
    
    @Override
    public List<FormBku> getSaldoAwalPanjar(Map<String, Object> param) {
        return cetakMapper.getSaldoAwalPanjar(param);
    }
    
    @Override
    public List<FormBku> getAkunBelanja(Map<String, Object> param) {
        return cetakMapper.getAkunBelanja(param);
    }
    
    @Override
    public List<BukuKasUmum> getWilayah(final Map<String, Object> param) {
        return cetakMapper.getWilayah(param);
    }
    
    @Override
    public List<BukuKasUmum> getBulan(final Map<String, Object> param) {
        return cetakMapper.getBulan(param);
    }
    
    @Override
    public List<FormBku> getSaldoAwalPajak(Map<String, Object> param) {
        return cetakMapper.getSaldoAwalPajak(param);
    }
    
}

  