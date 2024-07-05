/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.entity;
import java.util.List;
import java.util.Map;
import spp.model.SpmBtlLs;
import spp.model.SpmBtlLsRinci;
import java.math.BigDecimal;
/**
 *
 * @author Xalamaster
 */
public interface SpmBtlLsMapper {
    
    List<SpmBtlLs> getAllSpmBtlLs(Map param);

    Integer getBanyakSpmBtlLs(Map param);   
    
    Integer getIdSpmByIdSpp(Integer param);
    
    Integer getCekSpm(Integer param);
    
    BigDecimal getPotonganValidator(Integer param);   
    
    SpmBtlLs getSpmBtlLsById(Integer param);
    
    void updateSpmBtlLsMaster(SpmBtlLs spmUp);

    void insertSpmBtlLsRinci(SpmBtlLsRinci spmUp);
    
    void insertSpmBtlLsMaster(SpmBtlLs spmBtlLs);
    
    BigDecimal getTotalAnggaranSkpd(Map<String, Object> parameter);

    BigDecimal getTotalSPDBySKPDDanTahun(Map<String, Object> parameter);
    
    BigDecimal getTotalPaguBySkpd(Map<String, Object> parameter);    
    
    List<SpmBtlLsRinci> getAllSpdBtl(Map param);

    Integer getBanyakSpdBtl(Map param);   


}
