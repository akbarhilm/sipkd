package sipkd.entity;

import java.util.List;
import java.util.Map;
import sipkd.model.SpdPembatalan;

public interface SpdPembatalanMapper {

    List<SpdPembatalan> getlistvalidasispd(Map parameter);
    
    Integer getbanyakvalidasispd(Map parameter);

    SpdPembatalan getBatalById(Integer idSpdSah);
    
    void deleteSpdSah(Integer idSpdSah);
    
    void insertThSpdSah(SpdPembatalan param);
    
}
