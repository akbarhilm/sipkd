/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.Dokreff;

/**
 *
 * @author Admin
 */
public interface DokreffServices {

    List<Dokreff> getDokreff(Map param);

    Integer getCountDokreff(Map param);

    void insertdokreff(Dokreff param);

    void updatedokreff(Dokreff param);

    void deletedokreff(Integer id);
    
    void historydokreff(Dokreff param);

    Dokreff getDokreffById(Integer id);
}
