/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.LO;

/**
 *
 * @author Zainab
 */
public interface LoMapper {
   
  List<Map> getnilaiparam(Map param);
  
  List<LO> getSkpdCombo(Integer idskpd);
  
  void insertLo(LO param);
  
  List<LO> getBulan(Map<String, Object> param);
  
  Integer getIdInduk (Integer idskpd);
  
  
  
}
