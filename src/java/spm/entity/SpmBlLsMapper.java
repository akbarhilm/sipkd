/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.entity;
import java.util.List;
import java.util.Map;
import spp.model.SpmBlLs;
import spp.model.SpmBlLsRinci;
import java.math.BigDecimal;
//import spp.model.SpmBlLsUjiCoba;

/**
 *
 * @author Xalamaster
 */
public interface SpmBlLsMapper {
    
    List<SpmBlLs> getAllSpmBlLs(Map param);

    Integer getBanyakSpmBlLs(Map param);   
    
    Integer getCekSpm(Integer param);   
    
    Integer getIdSpmByIdSpp(Integer param);
    
    BigDecimal getPotonganValidatorNonAyat(Integer param);   
    
    BigDecimal getPotonganValidatorUangMuka(Integer param);   
    
    SpmBlLs getSpmBlLsById(Integer param);
    
    void updateSpmBlLsMaster(SpmBlLs spmUp);

    void insertSpmBlLsRinci(SpmBlLsRinci spmUp);
    
    void insertSpmBlLsMaster(SpmBlLs spmBlLs);
    
    BigDecimal getTotalAnggaranSkpd(Map<String, Object> parameter);

    BigDecimal getTotalSPDBySKPDDanTahun(Map<String, Object> parameter);
    
    BigDecimal getTotalPaguBySkpd(Map<String, Object> parameter);    
    
    List<SpmBlLsRinci> getAllSpdBl(Map param);

    Integer getBanyakSpdBl(Map param);   

    Integer getAkunNihil(Map param);
    
    SpmBlLs getBasNihil(Map param);
    
    void insertPotNihil(SpmBlLs spmBlLs);
    
    SpmBlLs getSppUangMuka(Map param);
    
    SpmBlLs getKodePotUmk(Map param);
    
    SpmBlLs getSpmBlLsPfkById(Integer param);
    
    BigDecimal getVaBank(Integer param);  
    
    void updatePfk(SpmBlLs spmUp);
    
    void updateSppPfk(SpmBlLs spmUp);
    
    List<SpmBlLs> getBankInduk(Map param);
    
    Integer getCountBankInduk(Map param);
    
    
}
