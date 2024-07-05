package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.BukuBesar;

/**
 *
 * @author Zainab
 */
public interface BukuBesarJurnalSkpdAllServices {

    String getTanggalPosting (Map<String, Object> param);
    
    List<Map> getTanggalPostingMap (Map<String, Object> param);
    
    void insertBukuBesarSkpd(BukuBesar param);
    
    List<Map> getnilaiparam(Map param);
    
    List<BukuBesar> getListBukuBesar(Map<String, Object> param);

    Integer getBannyakListBukuBesar(Map<String, Object> param);
    
}
