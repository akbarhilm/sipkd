package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.SppUp;
import spp.model.Sp2d;
import spp.model.Spj;
import spp.model.Setor;
import spp.model.Npd;

/**
 *
 * @author Admin
 */
public interface CetakValidasiSPJServices {

    List<Map> getnilaiparam(Map param);

    Map getspjsahbyidspj(Integer param);

       
    void insertspjsah(Map spj);

    void insertSpjCetak(Map spj);

    void deleteSpjCetak(Integer idspj);
    
    List<Spj> getSpjCetakBl(Map<String, Object> param);

    Integer getBanyakgetSpjCetakBl(Map param);
    
    List<Spj> getlistspjsah(Map<String, Object> param);

    Integer getbanyakspjsah(Map param);
    
   
}
