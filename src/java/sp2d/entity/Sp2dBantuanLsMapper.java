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

public interface Sp2dBantuanLsMapper {

    List<Sp2dLs> getSp2dBantuanLs(Map<String, Object> param);

    Integer getCountSp2dBantuanLs(Map param);

    List<Sp2dLs> getSp2dRinciBantuanLs(Map<String, Object> param);

    Integer getCountSp2dRinciBantuanLs(Map param);

    List<Sp2dLs> getKbud(Map<String, Object> param);

    Integer getCountKbud(Map param);

    Sp2dLs getSp2dBantuanLsById(Map param);
    
    Sp2dLs getSp2dBantuanLsByIdSp2d(Map param);
    
    Skpd getSkpdById(Integer idskpd);

    HariKerja getHariKerjaSp2d(Date tgl);

    Integer getCountHariKerjaSp2d(Date tgl);

    void insertSp2dBantuanLs(Sp2dLs sp2dBtlLs);
    
    void updateSp2dBantuanLs(Sp2dLs sp2dBtlLs);
    
    void insertBatalSp2dBtlLs(Sp2dLs sp2dBtlLs);
    
    void deleteSp2dBtlLs(Sp2dLs sp2dBtlLs);

}
