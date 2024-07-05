/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spj.entity.GenNumberMapper;
import spj.entity.JournalSkpdMapper;
import spp.model.JournalSkpd;
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
@Service("journalSkpdServices")
public class JournalSkpdImpl implements JournalSkpdServices {

    private static final Logger log = LoggerFactory.getLogger(JournalSkpdImpl.class);
    @Autowired
    private JournalSkpdMapper journalMapper;

    @Override
    public List<JournalSkpd> getUnit() {
        return journalMapper.getUnit();
    }

    @Override
    public List<JournalSkpd> getListJurnal(final Map<String, Object> param) {
        return journalMapper.getListJurnal(param);
    }

    @Override
    public Integer getBanyakListJournal(final Map<String, Object> param) {
        return journalMapper.getBanyakListJournal(param);
    }

    @Override
    public List<JournalSkpd> getTotal(final Map<String, Object> param) {
        return journalMapper.getTotal(param);
    }

    @Override
    public List<JournalSkpd> getNamaAkun(String akun) {
        return journalMapper.getNamaAkun(akun);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertJourSaldoAwal(List<JournalSkpd> param) {
        //journalMapper.deleteJourSaldoAwal(setor.get(0));
        for (JournalSkpd journalSkpd : param) {
            journalMapper.insertJourSaldoAwal(journalSkpd);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteJourSaldoAwal(JournalSkpd idskpd) {
        journalMapper.deleteJourSaldoAwal(idskpd);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateJourSaldoAwal(List<JournalSkpd> param) {
        for (JournalSkpd journalSkpd : param) {
            journalMapper.updateJourSaldoAwal(journalSkpd);
        }
    }
}
