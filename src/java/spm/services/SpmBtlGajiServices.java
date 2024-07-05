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
public interface SpmBtlGajiServices {
    
    List<SpmBtlLs> getAllSpmBtlGaji(Map param);

    Integer getBanyakSpmBtlGaji(Map param);
    
    SpmBtlLs getSpmBtlGajiById(Integer param);
    
    Integer getCekSpm(Integer param);
    
    Integer getCekPotongan(Integer param);
    
    Integer getIdSpmByIdSpp(Integer param);
    
    BigDecimal getPotonganValidator(Integer param);
    
    void insertSpmBtlGaji(SpmBtlLs spmBtlLs);
    
    void updateSpmBtlGaji(SpmBtlLs spmBtlLs);
    
    void deleteDataSpmByid(Integer integer);
    
    /*
    
    
    

    

    
    
    
    
    Map<String, BigDecimal> getTotalAnggaranDanSpd(Map<String, Object> param);

//    void insertSpmBtlLsMaster(SpmBtlLs spmBtlLs);
    
    
    
    List<SpmBtlLsRinci> getAllSpdBtl(Map param);

    Integer getBanyakSpdBtl(Map param);
    
   // void insertSpmBtlLsRinci(SpmBtlLsRinci spmUp);
    
    
    */

    

}
