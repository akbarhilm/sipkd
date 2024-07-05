/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2d.services;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import spp.entity.GenNumberMapper;
//import sp2d.entity.GenNumberMapper;
import sp2d.entity.SpmTerimaMapper;
//import spp.model.HariKerja;
//import spp.model.Skpd;
import spp.model.SpmTerima;

@Transactional(readOnly = true)
@Service("SpmTerimaServices")
public class SpmTerimaImpl implements SpmTerimaServices {

    private static final Logger log = LoggerFactory.getLogger(SpmTerimaImpl.class);
    @Autowired
    private SpmTerimaMapper SpmTerimaMapper;
    //@Autowired
    //private GenNumberMapper genNumberMapper;

    @Override
    public List<SpmTerima> getSpmTerima(Map param) {
        return SpmTerimaMapper.getSpmTerima(param);
    }

    @Override
    public Integer getCountSpmTerima(Map param) {
        return SpmTerimaMapper.getCountSpmTerima(param);
    }

    @Override
    public SpmTerima getSpmTerimaById(Integer id) {
        return SpmTerimaMapper.getSpmTerimaById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSpmTerima(SpmTerima param) {
        SpmTerimaMapper.updateSpmTerima(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void delSpmTerima(SpmTerima param) {
        SpmTerimaMapper.delSpmTerima(param);
    }

    @Override
    public List<SpmTerima> getIdSpm(Map param) {
        return SpmTerimaMapper.getIdSpm(param);
    }

    @Override
    public SpmTerima getKodeBank(Map param) {
        return SpmTerimaMapper.getKodeBank(param);
    }
    
    @Override
    public SpmTerima getKodeVA(Map param) {
        return SpmTerimaMapper.getKodeVA(param);
    }
    
}
