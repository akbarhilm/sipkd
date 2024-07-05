/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 

package sipkd.services;

import java.util.List;
import java.util.Map;
import sipkd.model.Dokttd;

public interface DokttdServices {
    
    List<Dokttd> getDokttd(Map param);
    
    Integer getCountDokttd(Map param);
    
    void insertdokttd(Dokttd param);
    
    void updatedokttd(Dokttd param);
    
    void deletedokttd(Integer id);
    
    Dokttd getDokttdById(Integer id);
    
}
