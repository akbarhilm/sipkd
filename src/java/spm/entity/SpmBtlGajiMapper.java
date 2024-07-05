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
public interface SpmBtlGajiMapper {
    List<SpmBtlLs> getAllSpmBtlGaji(Map param);

    Integer getBanyakSpmBtlGaji(Map param);
    
    SpmBtlLs getSpmBtlGajiById(Integer param);
    
    Integer getCekSpm(Integer param);
    
    Integer getCekPotongan(Integer param);
    
    Integer getIdSpmByIdSpp(Integer param);
    
    BigDecimal getPotonganValidator(Integer param);
    
    void insertSpmBtlGaji(SpmBtlLs spmBtlLs);
    
    void updateSimpegIdSpm(SpmBtlLs spmBtlLs);
    
    void updateSpmBtlGaji(SpmBtlLs spmBtlLs);
    
    void deleteSimpegIdSpm(Integer param);
    
    /*
       
    
    
    
    
    
       
    
    
    
    
    void insertSpmBtlLsRinci(SpmBtlLsRinci spmUp);
    
    
    
    BigDecimal getTotalAnggaranSkpd(Map<String, Object> parameter);

    BigDecimal getTotalSPDBySKPDDanTahun(Map<String, Object> parameter);
    
    BigDecimal getTotalPaguBySkpd(Map<String, Object> parameter);    
    
    List<SpmBtlLsRinci> getAllSpdBtl(Map param);

    Integer getBanyakSpdBtl(Map param);   

*/

    

    
}
