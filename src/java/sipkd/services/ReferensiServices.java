/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipkd.services;

import java.util.List;
import java.util.Map;
import sipkd.model.RefProgram;
import sipkd.model.Skpd;
import sipkd.model.SppPaguUp;

/**
 *
 * @author R Tarman
 */
public interface ReferensiServices {

    List<RefProgram> getprogram();

    Integer getBanyakAllUrusan(Map<String, Object> param);

    List<RefProgram> getAllUrusan(Map<String, Object> param);

    Integer getBanyakAllProgram(Map<String, Object> param);

    List<RefProgram> getAllProgram(Map<String, Object> param);

    Skpd getDetailSKpd(Integer idSkpd);

    Skpd getDetailSKpdByLevel(Map<String, Object> param);

    List<SppPaguUp> getAllSppPaguUp(Map<String, Object> param);

    Integer getBanyakSppPaguUp(Map<String, Object> param);

    void updateSppPaguUp(SppPaguUp sppPaguUp);
    
     Integer getIDSpd(Map param);
    
}
