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
public interface CetakValidasiSETORServices {

    List<Map> getnilaiparam(Map param);

    void insertsetorcetak(Map setor);
        
    Integer getbanyaksetorcetak(Map param);

    List<Setor> getlistsetorcetak(Map param);
    
    void deletesetorcetak(Integer param);
     
    
}
