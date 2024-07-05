package spm.entity;

import java.util.List;
import java.util.Map;
import spp.model.SppBtl;
import spp.model.SppBtlRinci;

/**
 *
 * @author Rudolf
 */
public interface SppBtlMapper {

    List<SppBtl> getAllSppBtl(Map param);

    Integer getBanyakSppBtl(Map param);

    List<SppBtlRinci> getAllSpdBtl(Map param);

    Integer getBanyakSpdBtl(Map param);

    void insertSppBtlMaster(SppBtl sppBtl);

    void insertSppBtlRinci(SppBtlRinci sppBtl);

    List<SppBtlRinci> getSppBtlRinciByIdSpp(Integer idspp);

    SppBtl getSppBtlById(Integer idspp);

    void updateSppBtlMaster(SppBtl sppBtl);

    void deleteSppBtlMaster(Integer idspp);

    void deleteSppBtlRinci(Integer idspp);

    Map getBankRekByIdSkpd(Map param);

    Map getBendaharaByIdSkpd(Map param);

    void deleteSppBtlMasterByIdSppIdSpdIdBtlIdAkun(SppBtlRinci sppBtl);
}
