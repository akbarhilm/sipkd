package spm.entity;

import java.util.List;
import java.util.Map;
import spp.model.SpmGu;
import spp.model.SpmGuRinci;

/**
 *
 * @author Admin
 */
public interface SpmGuMapper {

    List<SpmGu> getAllSpmGu(Map param);

    Integer getBanyakSpmGu(Map param);

    SpmGu getSpmGUById(Integer param);

    void insertSpmGuMaster(SpmGu spmGu);

    void updateSpmGuMaster(SpmGu spmGu);

    void insertSpmGuRinci(SpmGuRinci spmGu);
}
