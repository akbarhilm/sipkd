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
import spp.model.Sp2dBlTu;

/**
 *
 * @author Xalamaster
 */
public interface Sp2dBlTuServices {
    
    List<Sp2dBlTu> getSp2dBlTu(Map<String, Object> param);

    Integer getCountSp2dBlTu(Map param);

    List<Sp2dBlTu> getSp2dRinciBlTu(Map<String, Object> param);
    
    Sp2dBlTu getTotalBlTu(Map<String, Object> param);

    Integer getCountSp2dRinciBlTu(Map param);

    List<Sp2dBlTu> getKbud(Map<String, Object> param);

    Integer getCountKbud(Map param);

    Sp2dBlTu getSp2dBlTuById(Map param);
    
    Sp2dBlTu getSp2dBlTuByIdSp2d(Map param);
    
    Skpd getSkpdById(Integer idskpd);

    HariKerja getHariKerjaSp2d(Date tgl);

    Integer getCountHariKerjaSp2d(Date tgl);

    void insertSp2dBlTu(Sp2dBlTu sp2dBlTu);
    
    void updateSp2dBlTu(Sp2dBlTu sp2dBlTu);

    void deleteSp2dBtlLs(Sp2dBlTu sp2dBtlLs);
}
