package spm.entity;

import java.util.List;
import java.util.Map;
import spp.model.SpmTu;
import spp.model.SpmTuRinci;

/**
 *
 * @author Admin
 */
public interface SpmTuMapper {

    List<SpmTu> getAllSpmTu(Map param);

    Integer getBanyakSpmTu(Map param);

    SpmTu getSpmTUById(Integer param);

    void insertSpmTuMaster(SpmTu spmTu);

    void updateSpmTuMaster(SpmTu spmTu);

    void insertSpmTuRinci(SpmTuRinci spmTu);
}
