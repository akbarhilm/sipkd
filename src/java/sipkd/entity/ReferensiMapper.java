/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipkd.entity;

import java.util.List;
import java.util.Map;
import sipkd.model.SppPaguUp;

/**
 *
 * @author Admin
 */
public interface ReferensiMapper {

    List<SppPaguUp> getAllSppPaguUp(Map<String, Object> param);

    Integer getBanyakSppPaguUp(Map<String, Object> param);

    void updateSppPaguUp(SppPaguUp sppPaguUp);

    Integer getIDSpd(Map param);
}
