/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.JournalPenerimaan;

/**
 *
 * @author Zainab
 */
public interface JournalPenerimaanMapper {
   
    List<JournalPenerimaan> getLoket();
    
    List<JournalPenerimaan> getTanggal(Map<String, Object> param);

    List<JournalPenerimaan> getListJurnal(Map<String, Object> param);

    Integer getBanyakListJournal(Map<String, Object> param);
    
    void insertJournalPenerimaan(JournalPenerimaan param);
 
}
