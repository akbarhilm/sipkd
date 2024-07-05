/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.CetakSkpd;

/**
 *
 * @author Zainab
 */
public interface CetakJurnalSkpdMapper {
   
  List<CetakSkpd> getNoJurnal ();
  
  List<Map> getnilaiparam(Map param); 
  
  List<CetakSkpd> getNoJurnalBySkpd(Map param);
  
}
