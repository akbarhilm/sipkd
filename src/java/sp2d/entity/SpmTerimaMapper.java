/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2d.entity;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import spp.model.SpmTerima;

public interface SpmTerimaMapper {

    List<SpmTerima> getSpmTerima(Map param);

    Integer getCountSpmTerima(Map param);

    SpmTerima getSpmTerimaById(Integer id);

    void updateSpmTerima(SpmTerima param);

    void delSpmTerima(SpmTerima param);

    List<SpmTerima> getIdSpm(Map param);

    SpmTerima getKodeBank(Map param);
    
    SpmTerima getKodeVA(Map param);
    
}
