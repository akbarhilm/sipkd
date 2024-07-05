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

import spm.entity.BeritaLoginMapper;

import spp.model.Berita;

@Transactional(readOnly = true)
@Service("beritaLoginervices")

public class BeritaLoginImpl implements BeritaLoginServices {

    @Autowired
    private BeritaLoginMapper beritaMapper;

    @Override
    public List<Berita> getBerita(Map<String, Object> param) {
        return beritaMapper.getBerita(param);
    }

    @Override
    public Integer getBanyakBerita(Map parameter) {
        return beritaMapper.getBanyakBerita(parameter);
    }
    
    @Override
    public Map getImagePopup(Map param) {
        return beritaMapper.getImagePopup(param);
    }

    @Override
    public Integer getBanyakImagePopup(Map parameter) {
        return beritaMapper.getBanyakImagePopup(parameter);
    }
    
}
