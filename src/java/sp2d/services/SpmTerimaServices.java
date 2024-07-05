/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2d.services;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import spp.model.HariKerja;
//import spp.model.Skpd;
import spp.model.SpmTerima;

public interface SpmTerimaServices {

   // public static int getCountSpmTerima(Map<String, Object> param);
    Integer getCountSpmTerima(Map param);

    List<SpmTerima> getSpmTerima(Map param);
    
    public SpmTerima getSpmTerimaById(Integer id);
    
    public void updateSpmTerima(SpmTerima spmTerima);
    
    void delSpmTerima(SpmTerima param);
    
    public List<SpmTerima> getIdSpm(Map param);

    SpmTerima getKodeBank(Map param);
    
    SpmTerima getKodeVA(Map param);
    
}
