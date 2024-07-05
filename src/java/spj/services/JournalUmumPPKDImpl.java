/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.GenNumberMapper;
import spj.entity.JournalUmumPPKDMapper;
import spp.model.Akun;
import spp.model.JournalUmumPPKD;
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
@Service("journalPPKDServices")
public class JournalUmumPPKDImpl implements JournalUmumPPKDServices {

    private static final Logger log = LoggerFactory.getLogger(JournalUmumPPKDImpl.class);
    @Autowired
    private JournalUmumPPKDMapper journalPPKDMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    
    @Override
    public Integer getNoJournalInteger(String tahun) {
        return journalPPKDMapper.getNoJournalInteger(tahun);
    }
    
    @Override
    public JournalUmumPPKD getNoJournal(String tahun) {
        return journalPPKDMapper.getNoJournal(tahun);
    }
    
    @Override
    public String getNoJournalDok(Map param) {
        //log.debug(" tgl posting "+param.toString());
        return journalPPKDMapper.getNoJournalDok(param);
    }
    
    @Override
    public List<JournalUmumPPKD> getNamaAkun(String akun) {
        return journalPPKDMapper.getNamaAkun(akun);
    }
    
    @Override
    public Integer getBanyakAllSkpd(final Map<String, Object> param) {
        return journalPPKDMapper.getBanyakAllSkpd(param);
    }
    
    @Override
    public Integer getBanyakAllAkun(final Map<String, Object> param) {
        return journalPPKDMapper.getBanyakAllAkun(param);
    }

    @Override
    public Integer getBanyakAllJurnal(final Map<String, Object> param) {
        return journalPPKDMapper.getBanyakAllJurnal(param);
    }

    @Override
    public Integer getBanyakListJurnal(final Map<String, Object> param) {
        return journalPPKDMapper.getBanyakListJurnal(param);
    }

    @Override
    public Integer getBanyakKegiatan(final Map<String, Object> param) {
        return journalPPKDMapper.getBanyakKegiatan(param);
    }

    @Override
    public List<Skpd> getAllSkpd(final Map<String, Object> param) {
        return journalPPKDMapper.getAllSkpd(param);
    }
    
    @Override
    public List<Akun> getAllAkun(final Map<String, Object> param) {
        return journalPPKDMapper.getAllAkun(param);
    }
    
    @Override
    public List<JournalUmumPPKD> getAllJurnal(final Map<String, Object> param) {
        return journalPPKDMapper.getAllJurnal(param);
    }
    
    @Override
    public List<JournalUmumPPKD> getListJurnal(final Map<String, Object> param) {
        return journalPPKDMapper.getListJurnal(param);
    }
    
    @Override
    public List<JournalUmumPPKD> getKegiatan(final Map<String, Object> param) {
        return journalPPKDMapper.getKegiatan(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertJournal(List<JournalUmumPPKD> param) {
        //journalMapper.insertJournal(journal);
        for (JournalUmumPPKD journal : param) {
            journalPPKDMapper.insertJournal(journal);
        }
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateJournal(List<JournalUmumPPKD> param) {
        for (JournalUmumPPKD journal : param) {
            journalPPKDMapper.updateJournal(journal);
        }
    }
    
    @Override
    @Transactional(readOnly = false)
    public void deleteJournal(JournalUmumPPKD param) {
        journalPPKDMapper.deleteJournal(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateAktifJournal(JournalUmumPPKD param) {
        journalPPKDMapper.updateAktifJournal(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateAktifById(JournalUmumPPKD param) {
        journalPPKDMapper.updateAktifById(param);
    }
    
    @Override
    public String getStatus(String noJurnal) {
        return journalPPKDMapper.getStatus(noJurnal);
    }
    
}

  