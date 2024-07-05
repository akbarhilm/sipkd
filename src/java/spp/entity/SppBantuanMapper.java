/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.entity;

/**
 *
 * @author erzy
 */
import java.util.List;
import java.util.Map;
import spp.model.SppBantuan;
import spp.model.SppBantuanRinci;

public interface SppBantuanMapper {

    List<SppBantuan> getAllSppBantuan(Map param);

    Integer getBanyakSppBantuan(Map param);

    List<SppBantuan> getSkpdKoorSah(Map param);

    Integer getBanyakSkpdKoorSah(Map param);

    List<SppBantuan> getSpdKegiatan(Map param);

    Integer getBanyakSpdKegiatan(Map param);

    List<SppBantuan> getBendahara(Map param);

    SppBantuan getSppBantuanById(Integer idspp);

    String getNamaSkpdKoordinatorById(Integer idskpdkoor);

      //SppBantuanRinci getAllSpdBtlBantuanById(Integer idspp);
    SppBantuan getSppBantuanRinciByIdSpp(Integer idspp);

    SppBantuan getSkpdKoorById(Integer idskpdkoor);

    Map getBankRekByIdSkpd(Map param);

    Map getBendaharaByIdSkpd(Map param);

    Map getRinciByIdSpp(Map param);

    Map getDetailRinciBantuan(Map param);

    void updateSppBantuanMaster(SppBantuan sppBantuan);

    void updateSppBantuanRinci(SppBantuanRinci SppBantuan);

    void deleteSppBantuanMaster(Integer idspp);

    void deleteSppBantuanRinci(Integer idspp);

    void insertSppBantuanMaster(SppBantuan SppBantuan);

    void insertSppBantuanRinci(SppBantuanRinci SppBantuan);

}
