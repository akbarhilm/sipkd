package spj.entity;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import spp.model.HariKerja;
import spp.model.SppUp;
import spp.model.Sp2d;
import spp.model.Setor;
import spp.model.Npd;

/**
 *
 * @author Admin
 */
public interface CetakNpdMapper {

    HariKerja getharikerjaspp(Date tgl);

    void insertnpdcetak(Map npd);

    Integer getbanyaknpdcetak(Map param);

    List<Npd> getlistnpdcetak(Map param);
    
    List<Map> getnilaiparam(Map param);
    
    void deletenpdcetak(Integer param);  
      
  
}
