package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.Akun;
import spp.model.JournalUmum;
import spp.model.Skpd;

/**
 *
 * @author Zainab
 */
public interface JournalUmumServices {

    Integer getNoJournalInteger(String tahun);

    JournalUmum getNoJournal(String tahun);

    String getNoJournalDok(Map param);

    List<JournalUmum> getNamaAkun(String akun);

    List<Skpd> getAllSkpd(Map<String, Object> param);
    
    List<Akun> getAllAkun(Map<String, Object> param);

    Integer getBanyakAllSkpd(Map<String, Object> param);
    
    Integer getBanyakAllAkun(Map<String, Object> param);
    
    List<JournalUmum> getAllJurnal(Map<String, Object> param);
  
    Integer getBanyakAllJurnal(Map<String, Object> param);

    void insertJournal( List<JournalUmum> param);
    
    List<JournalUmum> getListJurnal(Map<String, Object> param);
    
    Integer getBanyakListJurnal(Map<String, Object> param);
    
    List<JournalUmum> getKegiatan(Map<String, Object> param);
 
    Integer getBanyakKegiatan(Map<String, Object> param);
  
    void updateJournal(List<JournalUmum> param);
  
    void deleteJournal(JournalUmum idskpd);
   
    void updateAktifJournal(JournalUmum param);
    
    void updateAktifById(JournalUmum param);
    
    String getStatus (String noJurnal);
  
}
