package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.JournalPenerimaan;

/**
 *
 * @author Zainab
 */
public interface JournalPenerimaanServices {

    
    List<JournalPenerimaan> getLoket();
    
    List<JournalPenerimaan> getTanggal(Map<String, Object> param);
    
    List<JournalPenerimaan> getListJurnal(Map<String, Object> param);
    
    Integer getBanyakListJournal(Map<String, Object> param);
    
    void insertJournalPenerimaan(JournalPenerimaan param);
    
}
