package eset.entity;

import java.util.List;
import java.util.Map;
import eset.model.Akun;
import eset.model.Bendahara;
import eset.model.Eset;
import eset.model.Skpd;
import eset.model.SpmProses;
import eset.model.SppPaguUp;
import eset.model.EsetRinci;

/**
 *
 * @author Admin
 */
public interface ListDataMapper {

    List<Map<String, Object>> getListWil();

    Integer getBanyakListWil();

    List<Map<String, Object>> getListSp2dApp(Map<String, Object> param);
    
    //======================================================================
    List<Map<String, Object>> getSp2dTestSuc(Map<String, Object> param);
    
   
    
    Integer getCS(Map<String, Object> param);
    
   
    List<Map<String, Object>> getSumSp2d(Map<String, Object> param);
    
    
    
}
