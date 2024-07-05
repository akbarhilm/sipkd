/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.GenNumberMapper;
import spj.entity.JournalSPJMapper;
import spp.model.JournalSPJ;
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
@Service("journalSpjServices")
public class JournalSPJImpl implements JournalSPJServices {

    private static final Logger log = LoggerFactory.getLogger(JournalSPJImpl.class);
    @Autowired
    private JournalSPJMapper journalMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    
    @Override
    public List<JournalSPJ> getListJourSpj(Map<String, Object> param) {
        return journalMapper.getListJourSpj(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertJournalSpj(JournalSPJ spj) {
        journalMapper.insertJournalSpj(spj);
    }
    
    @Override
    public Integer getBanyakJourSpj(Map param) {
    return journalMapper.getBanyakJourSpj(param);
    }
    
    @Override
    public List<JournalSPJ> getBulanJournal(Map param) {
    return journalMapper.getBulanJournal(param);
    }

}
