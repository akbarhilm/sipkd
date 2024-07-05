package spm.services;

import java.util.List;
import java.util.Map;
import spp.model.SppBiaya;
import spp.model.SppBiayaRinci;

/**
 *
 * @author Admin
 */
public interface SppBiayaServices {

    List<SppBiaya> getAllSppBiaya(Map param);

    Integer getBanyakSppBiaya(Map param);

    List<SppBiayaRinci> getAllSpdBiaya(Map param);

    Integer getBanyakSpdBiaya(Map param);

     void insertSppBiaya(SppBiaya sppBiaya);

    SppBiaya getSppBiayaById(Integer idspp);

    List<SppBiayaRinci> getSppBiayaRinciByIdSpp(Integer idspp);

    void updateSppBiaya(SppBiaya sppBiaya);

    void deleteSppBiayaMaster(Integer idspp);
    
     Map getBankRekByIdSkpd(Map param);
    
    Map getBendaharaByIdSkpd(Map param);
}
