package spp.services;

import java.util.List;
import java.util.Map;
import spp.model.SppTu;
import spp.model.SppTuRinci;

/**
 *
 * @author Admin
 */
public interface SppTuServices {

    List<SppTu> getAllSppTu(Map param);
    
    List<SppTu> getSpd(Map param);

    Integer getBanyakSppTu(Map param);
    
    Integer getBanyakSpd(Map param);

    List<SppTuRinci> getAllSpdBL(Map param);

    Integer getBanyakSpdBL(Map param);

    void insertSppTu(SppTu sppTu);
    
     void deleteSppTuMaster(Integer idspp);

    SppTu getSppTuById(Integer idspp);

    List<SppTuRinci> getSppTuRinciByIdSpp(Integer idspp);

    void updateSppTu(SppTu sppTu);
    
     Map getBankRekByIdSkpd(Map param);
     
     Map getBankDki(Map param);
     
    Map getBendaharaByIdSkpd(Map param);

    String cekTglSpjTuJurnal(Map<String, Object> param);
    
    Integer getBanyakTU(Map param);
    
}
