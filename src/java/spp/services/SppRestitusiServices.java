package spp.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import spp.model.SppBtl;
import spp.model.SppBtlRinci;
import spp.model.SppRestitusiRinci;

/**
 *
 * @author Admin
 */
public interface SppRestitusiServices {

    List<SppBtl> getAllSppRes(Map param);

    Integer getBanyakSppRes(Map param);

    Integer getBanyakSpdBtl(Map param);

    void insertSppRes(SppBtl sppBtl);

    SppBtl getSppBtlById(Integer idspp);

    List<SppRestitusiRinci> getSppBtlRinciByIdSpp(Integer idspp);

    void updateSppBtl(SppBtl sppBtl);

    void deleteSppBtlMaster(Integer idspp);

    Map getBankRekByIdSkpd(Map param);

    Map getBankDki(Map param);

    Map getBendaharaByIdSkpd(Map param);

    Map getNoValidasiByIdSkpd(String noValidasi);

    List<SppRestitusiRinci> getAkunByNomorValidasi(Map param);

    Integer getBanyakAkunByNomorValidasi(Map param);
    
     Map<String, BigDecimal> getTotalNilaiAkunByNomorValidasi(Map param);

}
