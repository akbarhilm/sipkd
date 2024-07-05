/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.entity;
import java.util.List;
import java.util.Map;
import spp.model.SpmPotUangmuka;
/**
 *
 * @author Xalamaster
 */
public interface SpmPotUangmukaMapper {
    
    List<SpmPotUangmuka> getAllPot(Map<String, Object> param);

    Integer getBanyakPot(Map<String, Object> param);
    
    void addPot(SpmPotUangmuka param);

    void updatePot(SpmPotUangmuka param);
    
    Map<String, Object> getDataSpp(Integer param);
    
    List<SpmPotUangmuka> getAkun(Map param);

    Integer getBanyakAllBast(Map param);

    List<SpmPotUangmuka> getListPotUmk(Map param);

    Integer getBanyakPotUmk(Map param);

    void deletePotUmk(Map<String, Object> param);
    
    List<SpmPotUangmuka> getKodeUmk(Map<String, Object> param);
 
    List<SpmPotUangmuka> getAkunDenda(Map<String, Object> param);
    
}
