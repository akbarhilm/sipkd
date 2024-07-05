package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.JournalSPJ;

/**
 *
 * @author Zainab
 */
public interface JournalSPJServices {

    Integer getBanyakJourSpj(Map param);
    
    List<JournalSPJ> getBulanJournal(Map param);
    
    List<JournalSPJ> getListJourSpj (Map<String, Object> param);
  
    void insertJournalSpj(JournalSPJ param);

}
