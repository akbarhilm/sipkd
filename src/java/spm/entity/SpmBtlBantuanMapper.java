/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.entity;

import java.util.List;
import java.util.Map;
import spp.model.SpmBtlBantuan;
import spp.model.SpmBtlBantuanRinci;
/**
 *
 * @author Xalamaster
 */
public interface SpmBtlBantuanMapper {
     
    List<SpmBtlBantuan> getAllSpmBtlBantuan(Map param);

    Integer getBanyakSpmBtlBantuan(Map param);   
    
    SpmBtlBantuan getSpmBtlBantuanById(Map param);
    
    void updateSpmBtlBantuanMaster(SpmBtlBantuan spmBtlBantuan);

    void insertSpmBtlBantuanMaster(SpmBtlBantuan spmBtlBantuan);
    
    List<SpmBtlBantuanRinci> getAllSpdBtl(Map param);

    Integer getBanyakSpdBtl(Map param);   

}
