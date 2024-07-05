/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spj.entity;

import java.util.List;
import java.util.Map;
import spp.model.Kontrak;
import spp.model.Metode;


public interface KontrakMapper {
    List<Kontrak> getKontrak(Map<String, Object> param);

    List<Metode> getMetode(Map param);
    
    
    List<Kontrak> getKegiatan(Map param);

    Integer getCountKegiatan(Map param);
    
    Integer getCountMetode(Map param);

    Integer getCountKontrak(Map param);

    void insertKontrak(Kontrak param);

    void updateKontrak(Kontrak param);

    void deleteKontrak(Integer id);

    Kontrak getKontrakById(Integer id);

}
