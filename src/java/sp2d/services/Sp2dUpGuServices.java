/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2d.services;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import spp.model.HariKerja;
import spp.model.Skpd;
import spp.model.Sp2dLs;

public interface Sp2dUpGuServices {

    List<Sp2dLs> getSp2dUpGu(Map<String, Object> param);

    Integer getCountSp2dUpGu(Map param);

    List<Sp2dLs> getSp2dRinciUpGu(Map<String, Object> param);

    Integer getCountSp2dRinciUpGu(Map param);

    List<Sp2dLs> getKbud(Map<String, Object> param);

    Integer getCountKbud(Map param);

    Sp2dLs getSp2dUpGuById(Map param);
    
    Sp2dLs getSp2dUpGuByIdSp2d(Map param);
    
    Skpd getSkpdById(Integer idskpd);

    HariKerja getHariKerjaSp2d(Date tgl);

    Integer getCountHariKerjaSp2d(Date tgl);

    void insertSp2dUpGu(Sp2dLs sp2dBtlLs);
    
    void updateSp2dUpGu(Sp2dLs sp2dBtlLs);
    
    void deleteSp2dBtlLs(Sp2dLs sp2dBtlLs);

}
