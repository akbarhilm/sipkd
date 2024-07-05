/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.Bast;
import spp.model.Kontrak;
import spp.model.Skpd;

/**
 *
 * @author erzy
 */
public interface BastServices {

    List<Skpd> getSKPD(Map param);
    
    List<Kontrak> getKontrak(Map param);

    Integer getBanyakAllBast(Map param);
    
    Integer getBanyakAllKontrak(Map param);
    Integer getBanyakAkun(Map param);

    List<Bast> getBast(Map param);
    List<Bast> getAkun(Map param);

    Integer getBanyakAllSKPD(Map param);

    void insertBast(Bast bast);

    void updateBast(Bast param);

    void deleteBast(Integer id);

    Bast getBastById(Integer id);
    Bast getKegiatanById(Integer idkegiatan);

}
