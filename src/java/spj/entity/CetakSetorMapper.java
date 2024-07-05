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
public interface CetakSetorMapper {

   
    HariKerja getharikerjaspp(Date tgl);
  
    void insertsetorcetak(Map setor);
    
    Integer getbanyaksetorcetak(Map param);

    List<Setor> getlistsetorcetak(Map param);
    
    List<Map> getnilaiparam(Map param);
    
    void deletesetorcetak(Integer param);
      
  
}
