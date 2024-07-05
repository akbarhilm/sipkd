package spp.services;

import java.util.List;
import java.util.Map;
import spp.model.SppBtl;
import spp.model.SppBtlRinci;

/**
 *
 * @author Admin
 */
public interface SppBtlServices {

    List<SppBtl> getAllSppBtl(Map param);

    Integer getBanyakSppBtl(Map param);

    List<SppBtlRinci> getAllSpdBtl(Map param);

    Integer getBanyakSpdBtl(Map param);

    void insertSppBtl(SppBtl sppBtl);

    SppBtl getSppBtlById(Integer idspp);

    List<SppBtlRinci> getSppBtlRinciByIdSpp(Integer idspp);

    void updateSppBtl(SppBtl sppBtl);
    
    void updateSppBtt(SppBtl sppBtl);
    
     void updateSppBxx(SppBtl sppBtl);

    void deleteSppBtlMaster(Integer idspp);
    
    Map getBankRekByIdSkpd(Map param);
    
    Map getBankDki(Map param);

    Map getBendaharaByIdSkpd(Map param);
    
    Map getBendaharaByIdSkpd1(Map param);
    
    Integer getbanyaksppbtl3(Map map);
}
