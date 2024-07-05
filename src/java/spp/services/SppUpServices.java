package spp.services;

import java.util.List;
import java.util.Map;
import spp.model.SppUp;
import spp.model.SppUpRinci;

/**
 *
 * @author Admin
 */
public interface SppUpServices {

    List<SppUp> getAllSppUp(Map param);

    Integer getBanyakSppUp(Map param);

    List<SppUpRinci> getAllSpdBL(Map param);

    Integer getBanyakSpdBL(Map param);

    void insertSppUp(SppUp sppUp);

    SppUp getSppUPById(Integer idspp);

    List<SppUpRinci> getSppUpRinciByIdSpp(Integer idspp);

    void updateSppUp(SppUp sppUp);

    Map getTotalSPDDanSPP(Map param);

    Map getBankRekByIdSkpd(Map param);
    
    Map getBankDki(Map param);
    
    Integer getCekBanyakSppUp(Map param);
    
    String getTotalSpdBL(Map param);
}
