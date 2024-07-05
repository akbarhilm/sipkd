package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.LRA;

/**
 *
 * @author Zainab
 */
public interface LraSkpdServices {

    String getTanggalPosting (Map<String, Object> param);

    List<Map> getTanggalPostingMap (Map<String, Object> param);

    void insertLraSkpd(LRA param);

    List<Map> getnilaiparam(Map param);

    void insertLraProvinsi(LRA param);
  
    String getKodeStatus (Map<String, Object> param);
   
    String getKodeStatusProvinsi (Map<String, Object> param);
  
}
