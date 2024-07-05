package spm.services;

import java.util.List;
import java.util.Map;
import spp.model.SpmTu;
import spp.model.SpmTuRinci;

/**
 *
 * @author Admin
 */
public interface SpmTuServices {

    List<SpmTu> getAllSpmTu(Map param);

    Integer getBanyakSpmTu(Map param);

    SpmTu getSpmTUById(Integer param);

    void insertSpmTuMaster(SpmTu spmTu);
    
     void updateSpmTuMaster(SpmTu spmTu);
     
    
}
