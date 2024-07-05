package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.CetakSkpd;
import spp.model.Skpd;

/**
 *
 * @author Zainab
 */
public interface CetakJurnalSkpdServices {

    List<CetakSkpd> getNoJurnal();
    
    List<Map> getnilaiparam(Map param);
    
    List<CetakSkpd> getNoJurnalBySkpd(Map param);
  
}
