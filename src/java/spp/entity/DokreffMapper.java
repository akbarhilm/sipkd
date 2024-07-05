package spp.entity;

import java.util.List;
import java.util.Map;
import spp.model.Dokreff;

/**
 *
 * @author Admin
 */
public interface DokreffMapper {

    List<Dokreff> getAllDokreff(Map param);

    List<Dokreff> getDokreff(Map param);

    Integer getBanyakAllDokreff(Map param);

    Integer getCountDokreff(Map param);

    void insertDokreff(Dokreff param);

    void updateDokreff(Dokreff param);

    void deleteDokreff(Integer id);

    Dokreff getDokreffById(Integer id);

   void historydokreff(Dokreff param);

}
