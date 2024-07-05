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
import spp.model.Sp2dBlLs;

public interface Sp2dBlLsServices {

    List<Sp2dBlLs> getSp2dBlLs(Map<String, Object> param);

    Integer getCountSp2dBlLs(Map param);

    List<Sp2dBlLs> getSp2dRinciBlLs(Map<String, Object> param);

    Integer getCountSp2dRinciBlLs(Map param);

    List<Sp2dBlLs> getKbud(Map<String, Object> param);

    Integer getCountKbud(Map param);

    Sp2dBlLs getSp2dBlLsById(Map param);
    
    Sp2dBlLs getSp2dBlLsByIdSp2d(Map param);
    
    Skpd getSkpdById(Integer idskpd);

    HariKerja getHariKerjaSp2d(Date tgl);

    Integer getCountHariKerjaSp2d(Date tgl);

    void insertSp2dBlLs(Sp2dBlLs sp2dBlLs);
    
    void updateSp2dBlLs(Sp2dBlLs sp2dBlLs);

    void deleteSp2dBtlLs(Sp2dBlLs sp2dBtlLs);
}
