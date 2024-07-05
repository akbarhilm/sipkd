/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.GenNumberMapper;
import spj.entity.JournalUnitSkpdMapper;
import spp.model.Akun;
import spp.model.JournalUnitSkpd;
import spp.model.Skpd;
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
@Service("journalUnitSkpdServices")
public class JournalUnitSkpdImpl implements JournalUnitSkpdServices {

    private static final Logger log = LoggerFactory.getLogger(JournalUnitSkpdImpl.class);
    @Autowired
    private JournalUnitSkpdMapper journalMapper;
    
    
    @Override
    public Integer getNoJournalInteger(String tahun) {
        return journalMapper.getNoJournalInteger(tahun);
    }
    
    @Override
    public JournalUnitSkpd getNoJournal(String tahun) {
        return journalMapper.getNoJournal(tahun);
    }
    
    @Override
    public String getNoJournalDok(Map param) {
        //log.debug(" tgl posting "+param.toString());
        return journalMapper.getNoJournalDok(param);
    }
    
    @Override
    public List<JournalUnitSkpd> getNamaAkun(String akun) {
        return journalMapper.getNamaAkun(akun);
    }
    
    @Override
    public Integer getBanyakAllSkpd(final Map<String, Object> param) {
        return journalMapper.getBanyakAllSkpd(param);
    }
    
    @Override
    public Integer getBanyakAllAkun(final Map<String, Object> param) {
        return journalMapper.getBanyakAllAkun(param);
    }

    @Override
    public Integer getBanyakAllJurnal(final Map<String, Object> param) {
        return journalMapper.getBanyakAllJurnal(param);
    }

    @Override
    public Integer getBanyakListJurnal(final Map<String, Object> param) {
        return journalMapper.getBanyakListJurnal(param);
    }

    @Override
    public Integer getBanyakKegiatan(final Map<String, Object> param) {
        return journalMapper.getBanyakKegiatan(param);
    }

    @Override
    public List<Skpd> getAllSkpd(final Map<String, Object> param) {
        return journalMapper.getAllSkpd(param);
    }
    
    @Override
    public List<Akun> getAllAkun(final Map<String, Object> param) {
        return journalMapper.getAllAkun(param);
    }
    
    @Override
    public List<JournalUnitSkpd> getAllJurnal(final Map<String, Object> param) {
        return journalMapper.getAllJurnal(param);
    }
    
    @Override
    public List<JournalUnitSkpd> getListJurnal(final Map<String, Object> param) {
        return journalMapper.getListJurnal(param);
    }
    
    @Override
    public List<JournalUnitSkpd> getKegiatan(final Map<String, Object> param) {
        return journalMapper.getKegiatan(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertJournal(List<JournalUnitSkpd> param) {
        //journalMapper.insertJournal(journal);
        for (JournalUnitSkpd journal : param) {
            journalMapper.insertJournal(journal);
        }
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateJournal(List<JournalUnitSkpd> param) {
        for (JournalUnitSkpd journal : param) {
            journalMapper.updateJournal(journal);
        }
    }
    
    @Override
    @Transactional(readOnly = false)
    public void deleteJournal(JournalUnitSkpd param) {
        journalMapper.deleteJournal(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateAktifJournal(JournalUnitSkpd param) {
        journalMapper.updateAktifJournal(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateAktifById(JournalUnitSkpd param) {
        journalMapper.updateAktifById(param);
    }
    
    @Override
    public String getStatus(String noJurnal) {
        return journalMapper.getStatus(noJurnal);
    }
    
}

  