package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.LaporanLra;

/**
 *
 * @author Zainab
 */
public interface LaporanLraServices {

    List<Map> getnilaiparam(Map param);
    
    List<LaporanLra> getSkpdCombo(Integer idskpd);
    
    Integer getIdInduk (Integer idskpd);
    
    List<LaporanLra> getBulan(Map<String, Object> param);
      
}
