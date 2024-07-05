package sp2d.services;

import java.util.List;
import java.util.Map;
import spp.model.JournalSp2d;

/**
 *
 * @author Zainab
 */
public interface JournalSp2dServices {

    
    List<JournalSp2d> getWilayah();
    
    List<JournalSp2d> getWilayahByKode(Map<String, Object> param);
    
    List<JournalSp2d> getTanggal(Map<String, Object> param);
    
    List<JournalSp2d> getTanggalAll(Map<String, Object> param);
    
    List<JournalSp2d> getListJurnal(Map<String, Object> param);
    
    Integer getBanyakListJournal(Map<String, Object> param);
    
    void insertJournalSp2d(JournalSp2d param);
    
    List<JournalSp2d> getTanggalSkpd(Map<String, Object> param);

    List<JournalSp2d> getListJurnalSkpd(Map<String, Object> param);

    Integer getBanyakListJournalSkpd(Map<String, Object> param);
    
    void insertJournalSp2dAll(JournalSp2d param);
    
    Integer getBanyakSp2dJour(Map<String, Object> param);
    
    void insertSp2dJour(JournalSp2d param);
    
    JournalSp2d getKodeProsesJour(Map<String, Object> param);
    
}
