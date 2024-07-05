/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.LRA;

/**
 *
 * @author Zainab
 */
public interface LraSkpdMapper {
   
  String getTanggalPosting (Map<String, Object> param);
  
  List<Map> getTanggalPostingMap (Map<String, Object> param);
  
  void insertLraSkpd(LRA param);
  
  List<Map> getnilaiparam(Map param);
  
  void insertLraProvinsi(LRA param);
  
  String getKodeStatus (Map<String, Object> param);
   
  String getKodeStatusProvinsi (Map<String, Object> param);
  
}
