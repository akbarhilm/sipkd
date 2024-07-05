package spj.entity;

import java.util.List;
import java.util.Map;
import spp.model.Berita;

public interface BeritaLoginMapper {

    List<Berita> getBerita(Map<String, Object> param);

    Integer getBanyakBerita(Map parameter);
    
    Map getImagePopup(Map param); 

    Integer getBanyakImagePopup(Map parameter);
    
    
}
