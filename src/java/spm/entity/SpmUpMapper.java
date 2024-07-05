package spm.entity;

import java.util.List;
import java.util.Map;
import spp.model.SpmUp;
import spp.model.SpmUpRinci;
import spp.model.SppUpRinci;

/**
 *
 * @author Admin
 */
public interface SpmUpMapper {

    List<SpmUp> getAllSpmUp(Map param);

    Integer getBanyakSpmUp(Map param);

    SpmUp getSpmUPById(Integer param);

    void insertSpmUpMaster(SpmUp spmUp);

    void updateSpmUpMaster(SpmUp spmUp);

    void insertSpmUpRinci(SpmUpRinci spmUp);

    List<SppUpRinci> getAllSpdBLSPM(Map param);

    Integer getBanyakSpdBLSPM(Map param);

    void deleteSpmByid(Integer param);

    void deleteSpmPotonganByid(Integer param);

}
