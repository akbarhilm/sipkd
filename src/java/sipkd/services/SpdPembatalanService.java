package sipkd.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import sipkd.model.PejabatPpkd;
import sipkd.model.SpdPembatalan;
import sipkd.model.SpdBTLMaster;
import sipkd.model.SpdBtlDetail;

/**
 * SPD BTL
 * @author User
 */
public interface SpdPembatalanService {
    List<SpdPembatalan> getlistvalidasispd(Map parameter);
    
    Integer getbanyakvalidasispd(Map parameter);
    
    SpdPembatalan getBatalById(Integer idSpdSah);
    
    //void deleteSpdSah(Integer idSpdSah);
    
    void insertThSpdSah(SpdPembatalan param);
    
    
}
