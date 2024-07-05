package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.BukuKasUmum;
import spp.model.Skpd;

/**
 *
 * @author Zainab
 */
public interface KartuKendaliServices {

    List<BukuKasUmum> getListKegiatan(Map<String, Object> param);
    
    Integer getBanyakListKegiatan(Map<String, Object> param);
    
    List<Map> getnilaiparam(Map param);
    
}
