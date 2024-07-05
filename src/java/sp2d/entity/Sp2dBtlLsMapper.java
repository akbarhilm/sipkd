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

public interface Sp2dBtlLsMapper {

    List<Sp2dLs> getSp2dBtlLs(Map<String, Object> param);

    Integer getCountSp2dBtlLs(Map param);

    List<Sp2dLs> getSp2dRinciBtlLs(Map<String, Object> param);

    Integer getCountSp2dRinciBtlLs(Map param);

    List<Sp2dLs> getKbud(Map<String, Object> param);

    Integer getCountKbud(Map param);

    Sp2dLs getSp2dBtlLsById(Map param);

    HariKerja getHariKerjaSp2d(Date tgl);

    Integer getCountHariKerjaSp2d(Date tgl);

    void insertSp2dBtlLs(Sp2dLs sp2dBtlLs);
    
    void updateSp2dBtlLs(Sp2dLs sp2dBtlLs);

    Skpd getSkpdById(Integer idskpd);

    Sp2dLs getSp2dBtlLsByIdSp2d(Map param);

    void insertBatalSp2dBtlLs(Sp2dLs sp2dBtlLs);
    
    void deleteSp2dBtlLs(Sp2dLs sp2dBtlLs);

}
