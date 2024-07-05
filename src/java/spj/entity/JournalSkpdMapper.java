/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.JournalSkpd;

/**
 *
 * @author Zainab
 */
public interface JournalSkpdMapper {
    
    List<JournalSkpd> getUnit();
    
    List<JournalSkpd> getListJurnal(Map<String, Object> param);
    
    Integer getBanyakListJournal(Map<String, Object> param);
    
    List<JournalSkpd> getTotal(Map<String, Object> param);
    
    List<JournalSkpd> getNamaAkun (String akun);
    
    void insertJourSaldoAwal(JournalSkpd param);
    
    void deleteJourSaldoAwal(JournalSkpd idskpd);
    
    void updateJourSaldoAwal(JournalSkpd param);
    
}
