package spp.entity;

import java.util.List;
import java.util.Map;
import spp.model.SppUp;
import spp.model.SppUpRinci;

/**
 *
 * @author Admin
 */
public interface SppUpMapper {

    List<SppUp> getAllSppUp(Map param);

    Integer getBanyakSppUp(Map param);

    List<SppUpRinci> getAllSpdBL(Map param);

    Integer getBanyakSpdBL(Map param);

    void insertSppUpMaster(SppUp sppUp);

    void insertSppUpRinci(SppUpRinci sppUp);

    List<SppUpRinci> getSppUpRinciByIdSpp(Integer idspp);

    SppUp getSppUPById(Integer idspp);

    void updateSppUpMaster(SppUp sppUp);

    void deleteSppUpMaster(SppUpRinci sppUp);

    Map getTotalSPDDanSPP(Map param);
    
    Map getBankRekByIdSkpd(Map param);
    
    Map getBankDki(Map param);
    
    Integer getCekBanyakSppUp(Map param);
    
    String getTotalSpdBL(Map param);
}
