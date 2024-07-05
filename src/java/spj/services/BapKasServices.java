/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import spp.model.Bendahara;
import spp.model.Spj;
import spp.model.BapKas;
import spp.model.BapKasRinci;
import spp.model.SpjRinci;

/**
 *
 * @author maman sulaeman
 */
public interface BapKasServices {

    List<BapKas> getBapKas(Map<String, Object> param);
    
    Integer getCountBapKas(Map param);
    
    Integer getCountTglBkuProses(Map param);
    
    String getHari(Map param);

    void insertBapKas(BapKas bapKas);
    
    void insertBapKasAll(BapKas bapKas);
    
    //void deleteBapKasRinci(Map param);
    
    void insertSaldoAwalBank(Map param2);
    
    void updateSaldoAwalBank(Map param2);
    
    void deleteSaldoAwalBank(Map param);
    
    void updateHari(Map param);
    
    void updateBapKas(BapKas bapkas);
    
    void updateBapKasAll(BapKas bapkas);

    void deleteBapKas(Map param);
    
    void deleteBapKasRinci2(Map param);

    BapKas getBapKasById(Map param);
    
    List<Map> getnilaiparam(Map param);
    
    List<Map> getBulanList(Map param);
    
    List<Map> getBulanListEdit(Map param);
    
    List<BapKas> getAllBAPKAS(Map<String, Object> param);
    
    Integer getBanyakAllBAPKAS(Map<String, Object> param);
    
    BapKas getNilaiKas(Map param);

   }
