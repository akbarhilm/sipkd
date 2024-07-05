/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.JournalSp2d;

/**
 *
 * @author Zainab
 */
public interface JournalSp2dMapper {
   
    List<JournalSp2d> getWilayah();
    
    List<JournalSp2d> getTanggal(Map<String, Object> param);

    List<JournalSp2d> getListJurnal(Map<String, Object> param);

    Integer getBanyakListJournal(Map<String, Object> param);
    
    List<JournalSp2d> getTanggalSkpd(Map<String, Object> param);

    List<JournalSp2d> getListJurnalSkpd(Map<String, Object> param);

    Integer getBanyakListJournalSkpd(Map<String, Object> param);
    
    void insertJournalSp2d(JournalSp2d param);
 
}


