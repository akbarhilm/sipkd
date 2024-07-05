/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.Skpd;

/**
 *
 * @author Zainab
 */
public interface ListSkpdMapper {
   
  List<Skpd> getAllSkpd(Map<String, Object> param);
  
  Integer getBanyakAllSkpd(Map<String, Object> param);
  
  List<Skpd> getSkpdwithID1(Map<String, Object> param);
  
  Integer getBanyakSkpdwithID1(Map<String, Object> param);
  
}
