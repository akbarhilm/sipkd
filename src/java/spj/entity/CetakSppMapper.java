package spj.entity;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import spp.model.HariKerja;
import spp.model.SppUp;
import spp.model.Setor;
import spp.model.Npd;

/**
 *
 * @author Admin
 */
public interface CetakSppMapper {

    List<SppUp> getlistsppcetak(Map param);

    Integer getbanyaksppcetak(Map param);
    
    Integer getbanyaksppcetakbtl3(Map map);

    void insertsppcetak(Map sppUp);
    
    void updatesp2dcetak(Map sp2d);
    
    void updatesp2dsah(Map sp2d);
    
     Map getsp2dsahbyidsp2d(Integer param);
     
     Map getspjsahbyidspj(Integer param);

    List<SppUp> getlistsppsah(Map param);

    Integer getbanyaksppsah(Map param);

    HariKerja getharikerjaspp(Date tgl);

    void insertsppsah(Map sppUp);

    void insertspmcetak(Map sppUp);
    
    void insertsetorcetak(Map setor);
    
    void insertnpdcetak(Map npd);

    Integer getbanyakspmcetak(Map param);

    List<SppUp> getlistspmcetak(Map param);
    
    Integer getbanyaksetorcetak(Map param);

    List<Setor> getlistsetorcetak(Map param);
    
    Integer getbanyaknpdcetak(Map param);

    List<Npd> getlistnpdcetak(Map param);
    
    List<Map> getnilaiparam(Map param);
    
    Integer getbanyaksp2dcetak(Map param);
    
    Integer getbanyaksp2dsah(Map param);

    List<SppUp> getlistsp2dcetak(Map param);
    
    List<SppUp> getlistsp2dsah(Map param);
    
     void deletesppcetak(Integer param);
     
      void deletespmcetak(Integer param);
     
      void deletesetorcetak(Integer param);
      
     void deletenpdcetak(Integer param);  
      
     void deletesp2dcetak(Integer param); 
      
   // String getlevel();  

}
