/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 

package sipkd.services;

import java.util.List;
import java.util.Map;
import sipkd.model.Berita;

public interface BeritaLoginServices {
    
    List<Berita> getBerita(Map<String, Object> param);
    
    Integer getBanyakBerita(Map parameter);
    
    Map getImagePopup(Map param); 

    Integer getBanyakImagePopup(Map parameter);
    
}
