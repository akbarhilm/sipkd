/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2d.entity;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import spp.model.HariKerja;
import spp.model.Skpd;
import spp.model.Sp2dLs;

public interface Sp2dBiayaLsMapper {

    List<Sp2dLs> getSp2dBiayaLs(Map<String, Object> param);

    Integer getCountSp2dBiayaLs(Map param);

    List<Sp2dLs> getSp2dRinciBiayaLs(Map<String, Object> param);

    Integer getCountSp2dRinciBiayaLs(Map param);

    List<Sp2dLs> getKbud(Map<String, Object> param);

    Integer getCountKbud(Map param);

    Sp2dLs getSp2dBiayaLsById(Map param);
    
    Sp2dLs getSp2dBiayaLsByIdSp2d(Map param);
    
    Skpd getSkpdBiayaById(Map param);

    HariKerja getHariKerjaSp2d(Date tgl);

    Integer getCountHariKerjaSp2d(Date tgl);

    void insertSp2dBiayaLs(Sp2dLs sp2dBiayaLs);
    
    void updateSp2dBiayaLs(Sp2dLs sp2dBiayaLs);

    void insertBatalSp2dBtlLs(Sp2dLs sp2dBiayaLs);
    
    void deleteSp2dBtlLs(Sp2dLs sp2dBiayaLs);
    
}
