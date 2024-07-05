/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.JournalSp2dMapper;

import spp.model.JournalSp2d;
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
@Service("journalSp2dServices")
public class JournalSp2dImpl implements JournalSp2dServices {
  
    private static final Logger log = LoggerFactory.getLogger(JournalSp2dImpl.class);
    @Autowired
  
    private JournalSp2dMapper journalMapper;
    
    @Override
    public List<JournalSp2d> getWilayah() {
        return journalMapper.getWilayah();
    }
    
    @Override
    public List<JournalSp2d> getTanggal(final Map<String, Object> param) {
        return journalMapper.getTanggal(param);
    }

    @Override
    public List<JournalSp2d> getListJurnal(final Map<String, Object> param) {
        return journalMapper.getListJurnal(param);
    }

    @Override
    public Integer getBanyakListJournal(final Map<String, Object> param) {
        return journalMapper.getBanyakListJournal(param);
    }
    
    @Override
    public List<JournalSp2d> getTanggalSkpd(final Map<String, Object> param) {
        return journalMapper.getTanggalSkpd(param);
    }

    @Override
    public List<JournalSp2d> getListJurnalSkpd(final Map<String, Object> param) {
        return journalMapper.getListJurnalSkpd(param);
    }

    @Override
    public Integer getBanyakListJournalSkpd(final Map<String, Object> param) {
        return journalMapper.getBanyakListJournalSkpd(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertJournalSp2d(JournalSp2d param) {
        journalMapper.insertJournalSp2d(param);
    }

}
