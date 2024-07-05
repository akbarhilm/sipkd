package spp.entity;

import java.util.List;
import java.util.Map;
import spp.model.SppBtl;
import spp.model.SppBtlGaji;
import spp.model.SppBtlRinci;

/**
 *
 * @author Zainab
 */
public interface SppBtlGajiMapper {

    List<SppBtlGaji> getListGaji(Map param);

    Integer getBanyakListGaji(Map param);

    Map getBankRekByIdSkpd(Map param);

    Map getBankDki(Map param);

    Map getBendaharaByIdSkpd(Map param);
    
    void insertSppBtlMaster(SppBtl sppBtl);
    
    void updateSimpeg(SppBtl sppBtl);
    
    List<SppBtl> getAllSppBtl(Map param);

    Integer getBanyakSppBtl(Map param);
    
    List<SppBtlRinci> getAllRinciBtl(Map param);

    Integer getBanyakRinciBtl(Map param);
    
    List<Map<String, Object>> getNilaiSPPGaji(Integer id);
    
    List<Map<String, Object>> getNilaiSPPTkd(Integer id);
    
    List<Map<String, Object>> getNilaiSPPTransport(Integer id);
    
    List<Map<String, Object>> getNilaiSPPPph(Integer id);

    void insertSppBtlRinci(SppBtlRinci sppBtl);

    SppBtl getSppBtlById(Integer idspp);
    
    void updateSppBtlMaster(SppBtl sppBtl);
    
    void deleteSimpegOld(Integer idSimpeg);
    
    void deleteSimpeg(Integer idSimpeg);
    
    void deleteSppBtlRinci(Integer idspp);
    
    void deleteSppBtlMaster(Integer idspp);
    
    void deletePegRekap();

    void insertPegRekap(Map param);

    Integer getIdPegRekap();

    List<SppBtlGaji> getBulan(Map param);

    void deletePegRekap1(Map param);

    

    

    
}
