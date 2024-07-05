package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.NoJurnal;

/**
 *
 * @author Zainab
 */
public interface NoJurnalServices {

  Integer getBanyakNoJurnalSkpdAll (Map<String, Object> param);
  
  Integer getBanyakNoJurnalSkpd (Map<String, Object> param);
  
  Integer getBanyakNoJurnal (Map<String, Object> param);
  
  Integer getBanyakNoJurnalAll (Map<String, Object> param);
  
  List<NoJurnal> getNoJurnalSkpdAll(Map<String, Object> param);
  
  List<NoJurnal> getNoJurnalSkpd(Map<String, Object> param);
  
  List<NoJurnal> getNoJurnal(Map<String, Object> param);
  
  List<NoJurnal> getNoJurnalAll(Map<String, Object> param);

}
