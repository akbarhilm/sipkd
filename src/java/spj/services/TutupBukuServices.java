package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.TutupBuku;
import spp.model.Skpd;

/**
 *
 * @author Zainab
 */
public interface TutupBukuServices {
    TutupBuku getBendahara (Map param);
    
    List<TutupBuku> getBulanTutup (Map param);
    
    TutupBuku getNilaiKas (Map param);
    
    void insertTutupBku(TutupBuku param, String kodetombol);
    
    void insertTutupBku(TutupBuku param);

    TutupBuku getNilaiSaldo(Map param);

    Integer getBanyakDataBKUPengeluaran(TutupBuku param);
    
}
