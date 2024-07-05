/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.services;


import java.util.Map;
import java.util.List;
import spp.model.SpmPotUangmuka;
/**
 *
 * @author Xalamaster
 */
public interface SpmPotUangmukaServices {
     
    List<SpmPotUangmuka> getAllPot(Map<String, Object> param);
    
    Integer getBanyakPot(Map<String, Object> param);
    
    void addPot(SpmPotUangmuka param);
    
    void updatePot(SpmPotUangmuka param);
    
    Map<String, Object> getDataSpp(Integer param);
    
    List<SpmPotUangmuka> getListPotUmk(Map param);

    Integer getBanyakPotUmk(Map param);
    
    void insertUMK (List<SpmPotUangmuka> param);

    List<SpmPotUangmuka> getKodeUmk(Map<String, Object> param);
    
    List<SpmPotUangmuka> getAkunDenda(Map<String, Object> param);
    
}
