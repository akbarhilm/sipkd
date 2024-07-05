package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.Akun;
import spp.model.JournalUnitSkpd;
import spp.model.Skpd;

/**
 *
 * @author Zainab
 */
public interface JournalUnitSkpdServices {

    Integer getNoJournalInteger(String tahun);

    JournalUnitSkpd getNoJournal(String tahun);

    String getNoJournalDok(Map param);

    List<JournalUnitSkpd> getNamaAkun(String akun);

    List<Skpd> getAllSkpd(Map<String, Object> param);
    
    List<Akun> getAllAkun(Map<String, Object> param);

    Integer getBanyakAllSkpd(Map<String, Object> param);
    
    Integer getBanyakAllAkun(Map<String, Object> param);
    
    List<JournalUnitSkpd> getAllJurnal(Map<String, Object> param);
  
    Integer getBanyakAllJurnal(Map<String, Object> param);

    void insertJournal( List<JournalUnitSkpd> param);
    
    List<JournalUnitSkpd> getListJurnal(Map<String, Object> param);
    
    Integer getBanyakListJurnal(Map<String, Object> param);
    
    List<JournalUnitSkpd> getKegiatan(Map<String, Object> param);
 
    Integer getBanyakKegiatan(Map<String, Object> param);
  
    void updateJournal(List<JournalUnitSkpd> param);
  
    void deleteJournal(JournalUnitSkpd idskpd);
   
    void updateAktifJournal(JournalUnitSkpd param);
    
    void updateAktifById(JournalUnitSkpd param);
   
    String getStatus (String noJurnal);
    
}
