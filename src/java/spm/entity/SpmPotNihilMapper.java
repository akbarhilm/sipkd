/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.entity;
import java.util.List;
import java.util.Map;
import spp.model.SpmPotNihil;
/**
 *
 * @author Xalamaster
 */
public interface SpmPotNihilMapper {
    
    List<SpmPotNihil> getAllPot(Map<String, Object> param);

    Integer getBanyakPot(Map<String, Object> param);
    
    void addPot(SpmPotNihil spmpot);

    void updatePot(SpmPotNihil spmpot);
    
    void deletePot(SpmPotNihil spmpot);
    
    List<SpmPotNihil>getAkunPendapatan(Map<String, Object> param);
    
}
