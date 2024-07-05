package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.Akun;
import spp.model.JournalUmumPPKD;
import spp.model.Skpd;

/**
 *
 * @author Zainab
 */
public interface JournalUmumPPKDServices {

    Integer getNoJournalInteger(String tahun);

    JournalUmumPPKD getNoJournal(String tahun);

    String getNoJournalDok(Map param);

    List<JournalUmumPPKD> getNamaAkun(String akun);

    List<Skpd> getAllSkpd(Map<String, Object> param);
    
    List<Akun> getAllAkun(Map<String, Object> param);

    Integer getBanyakAllSkpd(Map<String, Object> param);
    
    Integer getBanyakAllAkun(Map<String, Object> param);
    
    List<JournalUmumPPKD> getAllJurnal(Map<String, Object> param);
  
    Integer getBanyakAllJurnal(Map<String, Object> param);

    void insertJournal( List<JournalUmumPPKD> param);
    
    List<JournalUmumPPKD> getListJurnal(Map<String, Object> param);
    
    Integer getBanyakListJurnal(Map<String, Object> param);
    
    List<JournalUmumPPKD> getKegiatan(Map<String, Object> param);
 
    Integer getBanyakKegiatan(Map<String, Object> param);
  
    void updateJournal(List<JournalUmumPPKD> param);
  
    void deleteJournal(JournalUmumPPKD idskpd);
   
    void updateAktifJournal(JournalUmumPPKD param);
    
    void updateAktifById(JournalUmumPPKD param);
    
    String getStatus (String noJurnal);
  
}
