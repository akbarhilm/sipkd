package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.JournalSkpd;

/**
 *
 * @author Zainab
 */
public interface JournalSkpdServices {

    List<JournalSkpd> getUnit();
    
    List<JournalSkpd> getListJurnal(Map<String, Object> param);
    
    Integer getBanyakListJournal(Map<String, Object> param);
    
    List<JournalSkpd> getTotal(Map<String, Object> param);
    
    List<JournalSkpd> getNamaAkun (String akun);
    
    void insertJourSaldoAwal(List<JournalSkpd> setor);
    
    void deleteJourSaldoAwal(JournalSkpd idskpd);
    
    void updateJourSaldoAwal(List<JournalSkpd> param);
    
}
