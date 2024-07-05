/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.BukuBesar;

/**
 *
 * @author Zainab
 */
public interface BukuBesarJurnalSkpdAllMapper {
   
  String getTanggalPosting (Map<String, Object> param);
  
  List<Map> getTanggalPostingMap (Map<String, Object> param);
  
  void insertBukuBesarSkpd(BukuBesar param);
  
  List<Map> getnilaiparam(Map param);
  
  List<BukuBesar> getListBukuBesar(Map<String, Object> param);

  Integer getBannyakListBukuBesar(Map<String, Object> param);
   
}
