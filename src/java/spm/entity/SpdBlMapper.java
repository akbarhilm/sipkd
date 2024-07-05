package spm.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import spp.model.SpdBTLMaster;

public interface SpdBlMapper {

    Map<String, BigDecimal> getPaguDanSisaBantuanLangsung(Map<String, Object> parameter);

    Integer getBanyakAnggaranBlSkpd(Map<String, Object> parameter);//

    List<SpdBTLMaster> getAnggaranBlSkpd(Map<String, Object> parameter); //

    BigDecimal getTotalAnggaranSkpd(Map<String, Object> parameter);

    BigDecimal getTotalSPDBySKPDDanTahun(Map<String, Object> parameter);
    
    BigDecimal getTotalPaguBySkpd(Map<String, Object> parameter);

     

}
