/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.Kontrak;
import spp.model.Metode;

public interface KontrakServices {

    List<Kontrak> getKontrak(Map<String, Object> param);
    
    List<Metode> getMetode(Map param);
    
    List<Kontrak> getKegiatan(Map param);

    Integer getCountKegiatan(Map param);
    
    Integer getCountKontrak(Map param);
    
    Integer getCountMetode(Map param);

    void insertKontrak(Kontrak param);

    void updateKontrak(Kontrak param);

    void deleteKontrak(Integer id);

    Kontrak getKontrakById(Integer id);

   

}
