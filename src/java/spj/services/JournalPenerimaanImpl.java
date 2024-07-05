/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.JournalPenerimaanMapper;

import spp.model.JournalPenerimaan;
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
@Service("journalPenerimaanServices")
public class JournalPenerimaanImpl implements JournalPenerimaanServices {
  
    private static final Logger log = LoggerFactory.getLogger(JournalPenerimaanImpl.class);
    @Autowired
  
    private JournalPenerimaanMapper journalMapper;
    
    @Override
    public List<JournalPenerimaan> getLoket() {
        return journalMapper.getLoket();
    }
    
    @Override
    public List<JournalPenerimaan> getTanggal(final Map<String, Object> param) {
        return journalMapper.getTanggal(param);
    }

    @Override
    public List<JournalPenerimaan> getListJurnal(final Map<String, Object> param) {
        return journalMapper.getListJurnal(param);
    }

    @Override
    public Integer getBanyakListJournal(final Map<String, Object> param) {
        return journalMapper.getBanyakListJournal(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertJournalPenerimaan(JournalPenerimaan param) {
        journalMapper.insertJournalPenerimaan(param);
    }

}
