/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.LaporanLra;

/**
 *
 * @author Zainab
 */
public interface LaporanLraMapper {
   
  List<Map> getnilaiparam(Map param);
  
  List<LaporanLra> getSkpdCombo(Integer idskpd);
  
  Integer getIdInduk (Integer idskpd);
  
  List<LaporanLra> getBulan(Map<String, Object> param);
   
}
