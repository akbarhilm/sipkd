package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.Skpd;

/**
 *
 * @author Zainab
 */
public interface ListSkpdServices {

    List<Skpd> getAllSkpd(Map<String, Object> param);
    
    Integer getBanyakAllSkpd(Map<String, Object> param);
    
    List<Skpd> getSkpdwithID1(Map<String, Object> param);
  
    Integer getBanyakSkpdwithID1(Map<String, Object> param);
  
}
