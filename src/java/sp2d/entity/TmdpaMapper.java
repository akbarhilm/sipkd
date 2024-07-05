package sp2d.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
//import spp.model.Skpd;
import spp.model.Tmdpa;
/**
 *
 * @author Xalamaster
 */
public interface TmdpaMapper {
        Map<String, Object> getTmdpa(Map<String, Object> param);
        
        List<Tmdpa> getAllTmdpa(Map<String, Object> param);
        
        Integer getBanyakSkpd(Map<String, Object> param);
        
        void updateTmdpa(Tmdpa param);
        
        List<Tmdpa> getTmdpaById(Map<String, Object> param);
}
