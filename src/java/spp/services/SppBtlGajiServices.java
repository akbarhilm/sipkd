package spp.services;

import java.util.List;
import java.util.Map;
import spp.model.SppBtl;
import spp.model.SppBtlGaji;
import spp.model.SppBtlRinci;

/**
 *
 * @author Zainab
 */
public interface SppBtlGajiServices {

    List<SppBtlGaji> getListGaji(Map param);

    Integer getBanyakListGaji(Map param);

    Map getBankRekByIdSkpd(Map param);
    
    Map getBankDki(Map param);

    Map getBendaharaByIdSkpd(Map param);
    
    void insertSppBtl(SppBtl sppBtl);
    
    List<SppBtl> getAllSppBtl(Map param);

    Integer getBanyakSppBtl(Map param);
    
    Integer getBanyakRinciBtl(Map param);

    List<SppBtlRinci> getAllRinciBtl(Map param);
    
    List<Map<String, Object>> getNilaiSPP(Integer id, Integer kdsimpeg);
    
    SppBtl getSppBtlById(Integer idspp);
    
    void updateSppBtl(SppBtl sppBtl);
    
    void deleteSppBtl(Integer id);
    
    Integer insertPegRekap(Map param);
    
    List<SppBtlGaji> getBulan(Map param);
    

    

    

    
}
