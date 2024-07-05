package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.LO;

/**
 *
 * @author Zainab
 */
public interface LoServices {

    List<Map> getnilaiparam(Map param);

    List<LO> getSkpdCombo(Integer idskpd);

    void insertLo(LO param);
    
    List<LO> getBulan(Map<String, Object> param);
   
    Integer getIdInduk (Integer idskpd);
  
}
