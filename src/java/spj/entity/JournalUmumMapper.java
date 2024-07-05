/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.JournalUmum;
import spp.model.Akun;
import spp.model.Skpd;

/**
 *
 * @author Zainab
 */
public interface JournalUmumMapper {
   
  Integer getNoJournalInteger (String tahun);
   
  JournalUmum getNoJournal (String tahun);
   
  String getNoJournalDok (Map param);
   
  List<JournalUmum> getNamaAkun (String akun);
  
  List<Skpd> getAllSkpd(Map<String, Object> param);
  
  List<Akun> getAllAkun(Map<String, Object> param);
  
  Integer getBanyakAllSkpd(Map<String, Object> param);
  
  Integer getBanyakAllAkun(Map<String, Object> param);
  
  List<JournalUmum> getAllJurnal(Map<String, Object> param);
  
  Integer getBanyakAllJurnal(Map<String, Object> param);
  
  void insertJournal(JournalUmum param);
  
  List<JournalUmum> getListJurnal(Map<String, Object> param);
 
  Integer getBanyakListJurnal(Map<String, Object> param);
  
  List<JournalUmum> getKegiatan(Map<String, Object> param);
 
  Integer getBanyakKegiatan(Map<String, Object> param);
  
  void updateJournal(JournalUmum param);
  
  void deleteJournal(JournalUmum idskpd);
    
  void updateAktifJournal(JournalUmum param);
  
  void updateAktifById(JournalUmum param);
  
  String getStatus (String noJurnal);
    
}
