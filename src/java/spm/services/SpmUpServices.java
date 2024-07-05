package spm.services;

import java.util.List;
import java.util.Map;
import spp.model.SpmUp;
import spp.model.SppUpRinci;

/**
 *
 * @author Admin
 */
public interface SpmUpServices {

    List<SpmUp> getAllSpmUp(Map param);

    Integer getBanyakSpmUp(Map param);

    SpmUp getSpmUPById(Integer param);

    void insertSpmUpMaster(SpmUp spmUp);

    void updateSpmUpMaster(SpmUp spmUp);

    List<SppUpRinci> getAllSpdBLSPM(Map param);

    Integer getBanyakSpdBLSPM(Map param);
    
     void deleteDataSpmByid(Integer param);
}
