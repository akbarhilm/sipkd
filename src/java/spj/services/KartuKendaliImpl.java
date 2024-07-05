/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import static java.lang.System.console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spj.entity.KartuKendaliMapper;
import spp.model.BukuKasUmum;
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
@Service("kartuKendaliServices")
public class KartuKendaliImpl implements KartuKendaliServices {

    private static final Logger log = LoggerFactory.getLogger(KartuKendaliImpl.class);
    @Autowired
    private KartuKendaliMapper bkuMapper;

   

    @Override
    public List<BukuKasUmum> getListKegiatan(final Map<String, Object> param) {
        return bkuMapper.getListKegiatan(param);
    }

    @Override
    public Integer getBanyakListKegiatan(final Map<String, Object> param) {
        return bkuMapper.getBanyakListKegiatan(param);
    }

    @Override
    public List<Map> getnilaiparam(Map param) {
        return bkuMapper.getnilaiparam(param);
    }
    
    
}




