package spj.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import spp.model.SpdBTLMaster;

/**
 *
 * @author Admin
 */
public interface SpdBLServices {

   
    Integer getBanyakAnggaranBlSkpd(Map<String, Object> parameter);//

    List<SpdBTLMaster> getAnggaranBlSkpd(Map<String, Object> parameter);// 
    
    Map<String, BigDecimal> getTotalAnggaranDanSpd(Map<String, Object> param);
}
