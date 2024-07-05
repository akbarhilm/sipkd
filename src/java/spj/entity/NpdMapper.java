/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.entity;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import spp.model.HariKerja;
import spp.model.Npd;
import spp.model.NpdRinci;
import spp.model.Skpd;
import spp.model.Sp2dLs;

public interface NpdMapper {

    List<Npd> getNpd(Map<String, Object> param);

    Integer getCountNpd(Map param);

    List<Npd> getKegiatanNpd(Map<String, Object> param);

    Integer getCountKegiatanNpd(Map param);
    
    List<Npd> getNpdRinci(Map<String, Object> param);

    Integer getCountNpdRinci(Map param);
    
    void insertNpd(Npd npd);
    
    void insertNpdRinci(NpdRinci npd);
    
    void updateNpd(Npd npd);
      
    void deleteNpdRinci(Integer idnpd);
    
    void deleteNpd(Integer idnpd);
    
    void deleteNpdRinciIfNol();
    
    Npd getNpdById(Integer idnpd);
}
