/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.services;

import java.util.List;
import java.util.Map;
import spp.model.SpmBlLs;
import java.math.BigDecimal;
import spp.model.SpmBlLsRinci;
//import spp.model.SpmBlLsUjiCoba;

/**
 *
 * @author Xalamaster
 */
public interface SpmBlLsServices {
    
    List<SpmBlLs> getAllSpmBlLs(Map param);

    Integer getBanyakSpmBlLs(Map param);
    
    Integer getIdSpmByIdSpp(Integer param);

    SpmBlLs getSpmBlLsById(Integer param);
    
    Integer getCekSpm(Integer param);
    
    BigDecimal getPotonganValidatorNonAyat(Integer param);
    
    BigDecimal getPotonganValidatorUangMuka(Integer param);
    
    Map<String, BigDecimal> getTotalAnggaranDanSpd(Map<String, Object> param);

//    void insertSpmBtlLsMaster(SpmBtlLs spmBtlLs);
    
    void updateSpmBlLsMaster(SpmBlLs spmBlLs);
    
    List<SpmBlLsRinci> getAllSpdBl(Map param);

    Integer getBanyakSpdBl(Map param);
    
   // void insertSpmBtlLsRinci(SpmBtlLsRinci spmUp);
    
    void insertSpmBlLsMaster(SpmBlLs spmBlLs);
    
    Integer getAkunNihil(Map param);
    
    SpmBlLs getBasNihil(Map param);
    
   // void insertPotNihil(SpmBlLs spmBlLs);
    
    SpmBlLs getSppUangMuka(Map param);
    
    SpmBlLs getKodePotUmk(Map param);
       
    SpmBlLs getSpmBlLsPfkById(Integer param);
    
    BigDecimal getVaBank(Integer param);
    
    void updatePfk(SpmBlLs spmBlLs);
    
    void updateSppPfk(SpmBlLs spmBlLs);
    
    List<SpmBlLs> getBankInduk(Map param);

    Integer getCountBankInduk(Map param);   
    
    
}
