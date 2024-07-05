/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 

package sipkd.entity;

import java.util.List;
import java.util.Map;
import sipkd.model.Dokttd;

public interface DokttdMapper {
    
  //  List<Dokttd> getAllDokttd(Map param);

    List<Dokttd> getDokttd(Map param);
    
    Integer getBanyakAllDokttd(Map param);

    Integer getCountDokttd(Map param);
    
    void updateDokttd(Dokttd param);
    
    void deleteDokttd(Integer id);
    
    void insertDokttd(Dokttd param);
    
    Dokttd getDokttdById(Integer id);
    
}
