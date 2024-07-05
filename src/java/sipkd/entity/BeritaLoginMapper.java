package sipkd.entity;

import java.util.List;
import java.util.Map;
import sipkd.model.Berita;

public interface BeritaLoginMapper {

    List<Berita> getBerita(Map<String, Object> param);

    Integer getBanyakBerita(Map parameter);
    
    Map getImagePopup(Map param); 

    Integer getBanyakImagePopup(Map parameter);
    
    
}
