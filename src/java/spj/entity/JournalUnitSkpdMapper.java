/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.JournalUnitSkpd;
import spp.model.Akun;
import spp.model.Skpd;

/**
 *
 * @author Zainab
 */
public interface JournalUnitSkpdMapper {
   
  Integer getNoJournalInteger (String tahun);
   
  JournalUnitSkpd getNoJournal (String tahun);
   
  String getNoJournalDok (Map param);
   
  List<JournalUnitSkpd> getNamaAkun (String akun);
  
  List<Skpd> getAllSkpd(Map<String, Object> param);
  
  List<Akun> getAllAkun(Map<String, Object> param);
  
  Integer getBanyakAllSkpd(Map<String, Object> param);
  
  Integer getBanyakAllAkun(Map<String, Object> param);
  
  List<JournalUnitSkpd> getAllJurnal(Map<String, Object> param);
  
  Integer getBanyakAllJurnal(Map<String, Object> param);
  
  void insertJournal(JournalUnitSkpd param);
  
  List<JournalUnitSkpd> getListJurnal(Map<String, Object> param);
 
  Integer getBanyakListJurnal(Map<String, Object> param);
  
  List<JournalUnitSkpd> getKegiatan(Map<String, Object> param);
 
  Integer getBanyakKegiatan(Map<String, Object> param);
  
  void updateJournal(JournalUnitSkpd param);
  
  void deleteJournal(JournalUnitSkpd idskpd);
    
  void updateAktifJournal(JournalUnitSkpd param);
  
  void updateAktifById(JournalUnitSkpd param);
  
  String getStatus (String noJurnal);
    
}
