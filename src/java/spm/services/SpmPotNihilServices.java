/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.services;


import java.util.Map;
import java.util.List;
import spp.model.SpmPotNihil;
/**
 *
 * @author Xalamaster
 */
public interface SpmPotNihilServices {
     
    List<SpmPotNihil> getAllPot(Map<String, Object> param);
    
    Integer getBanyakPot(Map<String, Object> param);
    
    void addPot(SpmPotNihil spmpot);
    
    void updatePot(SpmPotNihil spmpot);
   // void deletePot(SpmPotNihil spmpot);
    
    List<SpmPotNihil>getAkunPendapatan(Map<String, Object> param);
}
