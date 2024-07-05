package spp.services;

import java.util.List;
import java.util.Map;
import spp.model.SppBl;
import spp.model.SppBlRinci;

/**
 *
 * @author Admin
 */
public interface SppBlServices {

    List<SppBl> getAllSppBl(Map param);

    Integer getBanyakSppBl(Map param);

    List<SppBlRinci> getAllSpdBl(Map param);

    Integer getBanyakSpdBl(Map param);

    Map getTotalSPDDanSPP(Map param);

    void insertSppBl(SppBl sppBl);

    SppBl getSppBlById(Integer idspp);

    SppBl getSppBlByIdExceptIdBast(Integer idspp);

    List<SppBlRinci> getSppBlRinciByIdSpp(Integer idspp);

    void updateSppBl(SppBl sppBl);

    void insertSppBlRinci(SppBlRinci sppBlRinci, SppBl sppBl);

    void deleteSppBlMaster(Integer idspp);

    List<SppBl> getBast(Map param);

    List<SppBl> getBast2(Map param);

    Integer getCountBast(Map param);

    Integer getCountBast2(Map param);

}
