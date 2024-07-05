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
public interface SpdPembatalanRevService {
    List<SpdPembatalan> getlistvalidasispd(Map parameter);
    
    Integer getbanyakvalidasispd(Map parameter);
    
    SpdPembatalan getBatalById(Integer idSpdSah);
    
    //void deleteSpdSah(Integer idSpdSah);
    
     void updatespdsahbatal(Map parameter);
     
     void updatespdbatalrincibtl(Map parameter);
     
     void updatespdbatalrincibl(Map parameter);
     
     void updatespdbatalrincibtlbantuan(Map parameter);
     
     void updatespdbatalrincibiaya(Map parameter);
    
    void insertThSpdSah(SpdPembatalan param);
    
    
}
