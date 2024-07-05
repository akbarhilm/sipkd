/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.services;

import java.util.List;
import java.util.Map;
import spp.model.SpmBtlLs;
import java.math.BigDecimal;
import spp.model.SpmBtlLsRinci;
/**
 *
 * @author Xalamaster
 */
public interface SpmRestitusiServices {
    
    List<SpmBtlLs> getAllSpmRes(Map param);

    Integer getBanyakSpmRes(Map param);
    
    Integer getIdSpmByIdSpp(Integer param);

    Integer getCekSpm(Integer param);

    SpmBtlLs getSpmResById(Integer param);
    
    BigDecimal getPotonganValidator(Integer param);
    
    Map<String, BigDecimal> getTotalAnggaranDanSpd(Map<String, Object> param);

//    void insertSpmBtlLsMaster(SpmBtlLs spmBtlLs);
    
    void updateSpmResMaster(SpmBtlLs spmBtlLs);
    
    List<SpmBtlLsRinci> getAllSpdBtl(Map param);

    Integer getBanyakSpdBtl(Map param);
    
   // void insertSpmBtlLsRinci(SpmBtlLsRinci spmUp);
    
    void insertSpmResMaster(SpmBtlLs spmBtlLs);
    

}
