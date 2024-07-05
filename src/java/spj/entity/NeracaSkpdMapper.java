/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.Neraca;

/**
 *
 * @author Zainab
 */
public interface NeracaSkpdMapper {
   
  String getTanggalPosting (Map<String, Object> param);
  
  List<Map> getTanggalPostingMap (Map<String, Object> param);
  
  void insertNeracaSkpd(Neraca param);
  
  void insertNeracaPkpd(Neraca param);
  
  List<Map> getnilaiparam(Map param);
  
  List<Neraca> getBulan(Map<String, Object> param);
  
  String getKodeStatus (Map<String, Object> param);
   
  String getKodeStatusProvinsi (Map<String, Object> param);
  
   
}
