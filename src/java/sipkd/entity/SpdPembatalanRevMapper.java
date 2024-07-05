package sipkd.entity;

import java.util.List;
import java.util.Map;
import sipkd.model.SpdPembatalan;

public interface SpdPembatalanRevMapper {

    List<SpdPembatalan> getlistvalidasispd(Map parameter);
    
    Integer getbanyakvalidasispd(Map parameter);

    SpdPembatalan getBatalById(Integer idSpdSah);
    
    void deleteSpdSah(Integer idSpdSah);
    
    void updatespdsahbatal(Map parameter);
    
    void insertThSpdSah(SpdPembatalan param);
    
     void updatespdbatalrincibtl(Map parameter);
    
    void updatespdbatalrincibl(Map parameter);
     
     void updatespdbatalrincibtlbantuan(Map parameter);
     
     void updatespdbatalrincibiaya(Map parameter);
    
}
