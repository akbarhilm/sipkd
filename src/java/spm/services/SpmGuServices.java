package spm.services;

import java.util.List;
import java.util.Map;
import spp.model.SpmGu;

/**
 *
 * @author Admin
 */
public interface SpmGuServices {

    List<SpmGu> getAllSpmGu(Map param);

    Integer getBanyakSpmGu(Map param);

    SpmGu getSpmGUById(Integer param);

    void insertSpmGuMaster(SpmGu spmGu);
    
     void updateSpmGuMaster(SpmGu spmGu);
}
