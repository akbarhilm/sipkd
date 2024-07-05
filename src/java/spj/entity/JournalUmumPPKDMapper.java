/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.JournalUmumPPKD;
import spp.model.Akun;
import spp.model.Skpd;

/**
 *
 * @author Zainab
 */
public interface JournalUmumPPKDMapper {
   
  Integer getNoJournalInteger (String tahun);
   
  JournalUmumPPKD getNoJournal (String tahun);
   
  String getNoJournalDok (Map param);
   
  List<JournalUmumPPKD> getNamaAkun (String akun);
  
  List<Skpd> getAllSkpd(Map<String, Object> param);
  
  List<Akun> getAllAkun(Map<String, Object> param);
  
  Integer getBanyakAllSkpd(Map<String, Object> param);
  
  Integer getBanyakAllAkun(Map<String, Object> param);
  
  List<JournalUmumPPKD> getAllJurnal(Map<String, Object> param);
  
  Integer getBanyakAllJurnal(Map<String, Object> param);
  
  void insertJournal(JournalUmumPPKD param);
  
  List<JournalUmumPPKD> getListJurnal(Map<String, Object> param);
 
  Integer getBanyakListJurnal(Map<String, Object> param);
  
  List<JournalUmumPPKD> getKegiatan(Map<String, Object> param);
 
  Integer getBanyakKegiatan(Map<String, Object> param);
  
  void updateJournal(JournalUmumPPKD param);
  
  void deleteJournal(JournalUmumPPKD idskpd);
    
  void updateAktifJournal(JournalUmumPPKD param);
  
  void updateAktifById(JournalUmumPPKD param);
  
  String getStatus (String noJurnal);
  
}
