/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.services;

import java.util.List;
import java.util.Map;
import spp.model.SpmBiayaLs;
import java.math.BigDecimal;
import spp.model.SpmBiayaLsRinci;
/**
 *
 * @author Xalamaster
 */
public interface SpmBiayaLsServices {
    
    List<SpmBiayaLs> getAllSpmBiayaLs(Map param);

    Integer getBanyakSpmBiayaLs(Map param);

    SpmBiayaLs getSpmBiayaLsById(Integer param);
    
    Map<String, BigDecimal> getTotalAnggaranDanSpd(Map<String, Object> param);

//    void insertSpmBtlLsMaster(SpmBtlLs spmBtlLs);
    
    void updateSpmBiayaLsMaster(SpmBiayaLs spmBiayaLs);
    
    List<SpmBiayaLsRinci> getAllSpdBiaya(Map param);

    Integer getBanyakSpdBiaya(Map param);
    
   // void insertSpmBtlLsRinci(SpmBtlLsRinci spmUp);
    
    void insertSpmBiayaLsMaster(SpmBiayaLs spmBiayaLs);
    

}
