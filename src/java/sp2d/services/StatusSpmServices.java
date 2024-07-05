package sp2d.services;

import java.util.List;
import java.util.Map;
import spp.model.StatusSpm;

/**
 *
 * @author Zainab
 */
public interface StatusSpmServices {

  List<StatusSpm> getSp2dWilayah(Map<String, Object> param);
  
  Integer getBanyakSp2dWilayah(Map<String, Object> param);

  
  
}
