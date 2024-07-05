package spm.entity;

import java.util.List;
import java.util.Map;
import spp.model.SppTu;
import spp.model.SppTuRinci;

/**
 *
 * @author Admin
 */
public interface SppTuMapper {

    List<SppTu> getAllSppTu(Map param);

    List<SppTu> getSpd(Map param);

    Integer getBanyakSppTu(Map param);

    Integer getBanyakSpd(Map param);

    List<SppTuRinci> getAllSpdBL(Map param);

    Integer getBanyakSpdBL(Map param);

    void insertSppTuMaster(SppTu SppTu);

    void insertSppTuRinci(SppTuRinci SppTu);

    List<SppTuRinci> getSppTuRinciByIdSpp(Integer idspp);

    SppTu getSppTuById(Integer idspp);

    void updateSppTuMaster(SppTu SppTu);

    void deleteSppTuMaster(Integer idspp);

    void deleteSppTuRinci(Integer idspp);

    Map getBankRekByIdSkpd(Map param);

    Map getBendaharaByIdSkpd(Map param);

    void deleteSppTuMasterbyIdSpdAndIdKegiatan( SppTuRinci param);
}
