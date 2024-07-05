/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.ProsesBkuMapper;
import spp.model.BukuKasUmum;
import spp.model.ProsesBku;
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
@Service("ProsesBkuServices")
public class ProsesBkuImpl implements ProsesBkuServices {

    private static final Logger log = LoggerFactory.getLogger(ProsesBkuImpl.class);
    @Autowired
    private ProsesBkuMapper ekuitasMapper;
    
    
    @Override
    @Transactional(readOnly = false)
    public void insertProsesBku(BukuKasUmum bku) {
        ekuitasMapper.insertProsesBku(bku);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertProsesBkuPPKD(BukuKasUmum bku) {
        ekuitasMapper.insertProsesBkuPPKD(bku);
    }
    
    @Override
    public List<Map> getnilaiparam(Map param) {
        return ekuitasMapper.getnilaiparam(param);
    }
    
    @Override
    public List<ProsesBku> getListJurnal(final Map<String, Object> param) {
        return ekuitasMapper.getListJurnal(param);
    }
    
    @Override
    public Integer getBanyakListJournal(final Map<String, Object> param) {
        return ekuitasMapper.getBanyakListJournal(param);
    }
    
    @Override
    public List<ProsesBku> getListJurnalXls(final Map<String, Object> param) {
        return ekuitasMapper.getListJurnalXls(param);
    }
    
    @Override
    public Integer getBanyakListJournalXls(final Map<String, Object> param) {
        return ekuitasMapper.getBanyakListJournalXls(param);
    }
    
}
