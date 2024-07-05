/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.JournalSPJ;

/**
 *
 * @author Zainab
 */
public interface JournalSPJMapper {
    
    Integer getBanyakJourSpj(Map param);
    
    List<JournalSPJ> getBulanJournal(Map param);
    
    List<JournalSPJ> getListJourSpj (Map<String, Object> param);
  
    void insertJournalSpj(JournalSPJ param);
}
