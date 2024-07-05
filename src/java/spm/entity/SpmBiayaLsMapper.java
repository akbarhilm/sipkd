/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.entity;
import java.util.List;
import java.util.Map;
import spp.model.SpmBiayaLs;
import spp.model.SpmBiayaLsRinci;
import java.math.BigDecimal;
/**
 *
 * @author Xalamaster
 */
public interface SpmBiayaLsMapper {
    
    List<SpmBiayaLs> getAllSpmBiayaLs(Map param);

    Integer getBanyakSpmBiayaLs(Map param);   
    
    SpmBiayaLs getSpmBiayaLsById(Integer param);
    
    void updateSpmBiayaLsMaster(SpmBiayaLs spmUp);

    void insertSpmBiayaLsRinci(SpmBiayaLsRinci spmUp);
    
    void insertSpmBiayaLsMaster(SpmBiayaLs spmBiayaLs);
    
    BigDecimal getTotalAnggaranSkpd(Map<String, Object> parameter);

    BigDecimal getTotalSPDBySKPDDanTahun(Map<String, Object> parameter);
    
    BigDecimal getTotalPaguBySkpd(Map<String, Object> parameter);    
    
    List<SpmBiayaLsRinci> getAllSpdBiaya(Map param);

    Integer getBanyakSpdBiaya(Map param);   


}
