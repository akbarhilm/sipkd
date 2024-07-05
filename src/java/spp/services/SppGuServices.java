/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
//import spp.model.NilaiDataGu;
import spp.model.SppGu;
import spp.model.SppGuRinci;

/**
 *
 * @author erzy
 */
public interface SppGuServices {

    List<SppGu> getAllSppGu(Map param);

    Integer getBanyakSppGu(Map param);

    List<SppGuRinci> getAllSpdBL(Map param);

    Integer getBanyakSpdBL(Map param);

    Map getBankRekByIdSkpd(Map param);

    Map getBankDki(Map param);

    Map getBendaharaByIdSkpd(Map param);

    Map getDataPagu(Map param);

    Map getDataPaguBKU(Map param);

     //List<SppGu> getTotalPaguDanSpp(Map<String, Object> param);
    void insertSppGu(SppGu sppGu);

    SppGu getSppGuById(Integer idspp);

    List<SppGuRinci> getSppGuRinciByIdSpp(Integer idspp);

    void updateSppGu(SppGu sppGu);

    Map getTotalSPDDanSPP(Map param);

    Integer getStatusGU(Map param);

    Integer getBanyakGU(Map param);

}
