package spp.entity;

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
public interface CetakSppMapper {

    List<SppUp> getlistsppcetak(Map param);

    Integer getbanyaksppcetak(Map param);
    
    Integer getbanyaksppcetakbtl3(Map map);
    
    Integer getbanyaksppcetakbtl4(Map map);

    void insertsppcetak(Map sppUp);
    
    List<SppUp> getlistsppsah(Map param);

    Integer getbanyaksppsah(Map param);

    HariKerja getharikerjaspp(Date tgl);

    void insertsppsah(Map sppUp);

    List<Map> getnilaiparam(Map param);
    
    List<Map> getnilaiparam1(Map param);
    
    void deletesppcetak(Integer param);
    
     List<SppUp> getPathFile(Map param);
     
     void updateSppBtt(Map param);
     
}
