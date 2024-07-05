package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.CetakSkpd;
import spp.model.BukuKasUmum;
import spp.model.Skpd;
import spp.model.Kegiatan;

/**
 *
 * @author Zainab
 */
public interface CetakBKUPengeluaranServices {
    
    List<Map> getListXlsBku(Map<String, Object> param);

    Integer getBanyakListXlsBku(Map<String, Object> param);

    List<BukuKasUmum> getBulanBKU(Map param);
    
    List<BukuKasUmum> getBulanBKUPengeluaran2(Map param);
    
    void insertBKUPengeluaran(BukuKasUmum param);

    List<Map> getnilaiparam(Map param);
    
    List<BukuKasUmum> getKegiatan(Map param);

    Integer getCountKegiatan(Map param);
    
    List<BukuKasUmum> getListBkuPengeluaran(Map param);

    Integer getCountListBkuPengeluaran(Map param);
   
    List<BukuKasUmum> getListBkuPerKegiatan(Map param);

    Integer getCountListBkuPerKegiatan(Map param);

    List<BukuKasUmum> getTanggalBelumJurnal(Map param);
   
    
}
