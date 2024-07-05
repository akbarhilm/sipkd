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
public interface CetakValidasiNPDServices {

   List<Map> getnilaiparam(Map param);
   
    void insertnpdcetak(Map npd);

      
    Integer getbanyaknpdcetak(Map param);

    List<Npd> getlistnpdcetak(Map param);

    void deletenpdcetak(Integer param); 
    
    
}
