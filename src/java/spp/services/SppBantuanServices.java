/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.services;

/**
 *
 * @author erzy
 */
import java.util.List;
import java.util.Map;

import spp.model.SppBantuan;
import spp.model.SppBantuanRinci;

public interface SppBantuanServices {

    List<SppBantuan> getAllSppBantuan(Map param);

    Integer getBanyakSppBantuan(Map param);

    List<SppBantuan> getSkpdKoorSah(Map param);

    Integer getBanyakSkpdKoorSah(Map param);

    List<SppBantuan> getSpdKegiatan(Map param);

    Integer getBanyakSpdKegiatan(Map param);

    List<SppBantuan> getBendahara(Map<String, Object> param);

    SppBantuan getSppBantuanById(Integer idspp);

    String getNamaSkpdKoordinatorById(Integer idskpdkoor);

    SppBantuan getSppBantuanRinciByIdSpp(Integer idspp);

      //SppBantuanRinci getAllSpdBtlBantuanById(Integer idspp);
    SppBantuan getSkpdKoorById(Integer idskpdkoor);

    void insertSppBantuan(SppBantuanRinci SppBantuanRinci, SppBantuan sppBantuan);

    void insertSppBantuanRinci(SppBantuanRinci SppBantuanRinci, SppBantuan sppBantuan);

    void deleteSppBantuanMaster(Integer idspp);

    Map getBankRekByIdSkpd(Map param);

    Map getBendaharaByIdSkpd(Map param);

    Map getRinciByIdSpp(Map param);

    Map getDetailRinciBantuan(Map param);

    void updateSppBantuan(SppBantuan SppBantuan);

    void updateSppBantuanRinci(SppBantuanRinci sppBantuanRinci, SppBantuan sppBantuan);

     //void insertSppBantuanRinci(SppBantuanRinci sppBantuanRinci);
}
