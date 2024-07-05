/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eset.services;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eset.entity.ListDataMapper;
import eset.entity.SkpdMapper;
import eset.model.Akun;
import eset.model.Bendahara;
import eset.model.Eset;
import eset.model.PejabatPpkd;
import eset.model.Skpd;
import eset.model.SpmProses;
import eset.model.SppPaguUp;
import eset.model.EsetRinci;

/**
 *
 * @author R Tarman
 */
@Transactional(readOnly = true)
@Service("listDataServices")
public class ListDataImpl implements ListDataServices {

    private static final Logger log = LoggerFactory.getLogger(ListDataImpl.class);
    @Autowired
    private ListDataMapper esetMapper;

    @Override
    public List<Map<String, Object>> getListWil() {
        return esetMapper.getListWil();
    }

    @Override
    public Integer getBanyakListWil() {
        return esetMapper.getBanyakListWil();
    }

    @Override
    public List<Map<String, Object>> getListSp2dApp(Map<String, Object> param) {
        return esetMapper.getListSp2dApp(param);
    }
    ////////////////////////////////////////////////////////////////////////////
     @Override
    public List<Map<String, Object>> getSp2dTestSuc(Map<String, Object> param) {
        return esetMapper.getSp2dTestSuc(param);
    }
    
     
    @Override
    public Integer getCS(Map<String, Object> param) {
        return esetMapper.getCS(param);
    }
    
   
    /////////////////////////////////////////////////////////////////////
    @Override
    public List<Map<String, Object>> getSumSp2d(Map<String, Object> param) {
        return esetMapper.getSumSp2d(param);
    }
    
    

}
