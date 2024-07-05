/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.entity;

import java.util.List;
import java.util.Map;
//import spp.model.NilaiDataGu;
import spp.model.SppGu;
import spp.model.SppGuRinci;

/**
 *
 * @author erzy
 */
public interface SppGuMapper {

    List<SppGu> getAllSppGu(Map param);

    Integer getBanyakSppGu(Map param);

    List<SppGuRinci> getAllSpdBL(Map param);

    Integer getBanyakSpdBL(Map param);

    Map getBankRekByIdSkpd(Map param);

    Map getBankDki(Map param);

    Map getBendaharaByIdSkpd(Map param);

    Map getDataPagu(Map param);

    Map getDataPaguBKU(Map param);

    void insertSppGuMaster(SppGu sppGu);

    void insertSppGuRinci(SppGuRinci sppGu);

    SppGu getSppGuById(Integer idspp);

    List<SppGuRinci> getSppGuRinciByIdSpp(Integer idspp);

    void updateSppGuMaster(SppGu sppGu);

    void deleteSppGuMaster(SppGuRinci sppGu);

    Map getTotalSPDDanSPP(Map param);

    Integer getStatusGU(Map param);

    Integer getBanyakGU(Map param);
    
}
