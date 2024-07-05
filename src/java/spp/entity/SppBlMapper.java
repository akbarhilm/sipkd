package spp.entity;

import java.util.List;
import java.util.Map;
import spp.model.SppBl;
import spp.model.SppBlRinci;

/**
 *
 * @author Admin
 */
public interface SppBlMapper {

    List<SppBl> getAllSppBl(Map param);

    Integer getBanyakSppBl(Map param);

    List<SppBlRinci> getAllSpdBl(Map param);

    Integer getBanyakSpdBl(Map param);

    void insertSppBlMaster(SppBl sppBl);

    void insertSppBlMasterExceptIdBast(SppBl sppBl);

    SppBl getSppBlByIdExceptIdBast(Integer idspp);

    void insertSppBlRinci(SppBlRinci sppBl);

    List<SppBlRinci> getSppBlRinciByIdSpp(Integer idspp);

    SppBl getSppBlById(Integer idspp);

    void updateSppBlMaster(SppBl sppBl);

    void deleteSppBlMaster(Integer idspp);

    void deleteSppBlRinci(Integer idspp);

    public Map getTotalSPDDanSPP(Map param);

    List<SppBl> getBast(Map param);

    List<SppBl> getBast2(Map param);

    Integer getCountBast(Map param);

    Integer getCountBast2(Map param);
}
