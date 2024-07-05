package spp.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import spp.model.SppBtl; 
import spp.model.SppRestitusiRinci;

/**
 *
 * @author Rudolf
 */
public interface SppRestitusiMapper {

    List<SppBtl> getAllSppRes(Map param);

    Integer getBanyakSppRes(Map param);

    
    Integer getBanyakSpdBtl(Map param);

    void insertSppResMaster(SppBtl sppBtl);

    void insertSppResRinci(SppRestitusiRinci sppBtl);

    List<SppRestitusiRinci> getSppBtlRinciByIdSpp(Integer idspp);

    SppBtl getSppBtlById(Integer idspp);

    void updateSppBtlMaster(SppBtl sppBtl);

    void deleteSppBtlMaster(Integer idspp);

    void deleteSppBtlRinci(Integer idspp);

    Map getBankRekByIdSkpd(Map param);

    Map getBankDki(Map param);

    Map getBendaharaByIdSkpd(Map param);

    void deleteSppBtlMasterByIdSppIdSpdIdBtlIdAkun(SppRestitusiRinci sppBtl);

    Map getNoValidasiByIdSkpd(String noValidasi);

    List<SppRestitusiRinci> getAkunByNomorValidasi(Map param);

    Integer getBanyakAkunByNomorValidasi(Map param);

    Map<String, BigDecimal> getTotalNilaiAkunByNomorValidasi(Map param);
}
