package spp.entity;

import java.util.List;
import java.util.Map;
import spp.model.SppBiaya;
import spp.model.SppBiayaRinci;

/**
 *
 * @author Admin
 */
public interface SppBiayaMapper {

    List<SppBiaya> getAllSppBiaya(Map param);

    Integer getBanyakSppBiaya(Map param);

    List<SppBiayaRinci> getAllSpdBiaya(Map param);

    Integer getBanyakSpdBiaya(Map param);

    void insertSppBiayaMaster(SppBiaya sppBiaya);

    void insertSppBiayaRinci(SppBiayaRinci sppBiaya);

    List<SppBiayaRinci> getSppBiayaRinciByIdSpp(Integer idspp);

    SppBiaya getSppBiayaById(Integer idspp);

    void updateSppBiayaMaster(SppBiaya sppBiaya);
    
    void deleteSppBiayaMaster(Integer idspp);
    
    void deleteSppBiayaRinci(Integer idspp);
    
    Map getBankRekByIdSkpd(Map param);
     
    Map getBankDki(Map param);
     
    Map getBendaharaByIdSkpd(Map param);
}
