/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.services;

import java.util.List;
import java.util.Map;
import spp.model.SpmBtlBantuan;
import java.math.BigDecimal;
import spp.model.SpmBtlBantuanRinci;
/**
 *
 * @author Xalamaster
 */
public interface SpmBtlBantuanServices {
    
    List<SpmBtlBantuan> getAllSpmBtlBantuan(Map param);

    Integer getBanyakSpmBtlBantuan(Map param);
    
    SpmBtlBantuan getSpmBtlBantuanById(Map param);
    
    void insertSpmBtlBantuanMaster(SpmBtlBantuan spmBtlBantuan);

    void updateSpmBtlBantuanMaster(SpmBtlBantuan spmBtlBantuan);
        
    List<SpmBtlBantuanRinci> getAllSpdBtl(Map param);

    Integer getBanyakSpdBtl(Map param);

}
