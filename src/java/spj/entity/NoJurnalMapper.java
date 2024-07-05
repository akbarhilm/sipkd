/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.NoJurnal;

/**
 *
 * @author Zainab
 */
public interface NoJurnalMapper {
   
  Integer getBanyakNoJurnalSkpdAll (Map<String, Object> param);
  
  Integer getBanyakNoJurnalSkpd (Map<String, Object> param);
  
  Integer getBanyakNoJurnal (Map<String, Object> param);
  
  Integer getBanyakNoJurnalAll (Map<String, Object> param);
  
  List<NoJurnal> getNoJurnalSkpdAll(Map<String, Object> param);
  
  List<NoJurnal> getNoJurnalSkpd(Map<String, Object> param);
  
  List<NoJurnal> getNoJurnal(Map<String, Object> param);
  
  List<NoJurnal> getNoJurnalAll(Map<String, Object> param);
  
  
}
 