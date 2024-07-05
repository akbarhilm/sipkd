/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import spp.model.Bendahara;
import spp.model.Spj;
import spp.model.SpjRinci;

/**
 *
 * @author maman sulaeman
 */
public interface SpjMapper {

    List<Spj> getSpj(Map<String, Object> param);

    List<Spj> getSpjRinci(Map<String, Object> param);

    List<SpjRinci> getSpjRinciKegiatan(Map<String, Object> param);

    List<SpjRinci> getSpjRinciKegiatanTU(Map<String, Object> param);

    List<Spj> getComboKegiatan(Map<String, Object> param);

    Integer getCountComboKegiatan(Map param);

    Integer getCountSpj(Map param);

    Integer getBulanAdaSpj(Map param);
    
    String getValidasiUpgu(Map param);
    
    List<Spj> getListValidasiUpgu(Map param);
    
    String getNihilNihil(Map param);
    
    Integer getBulanSudahSpj(Map param);

    Integer getCountSpjRinci(Map param);

    String getBulanByBulan(Integer idspj);

    Integer getCountSpjRinciKegiatan(Map param);

    Integer getCountSpjRinciKegiatanTU(Map param);

    BigDecimal getTotalSpjByIdSkpdAndTahun(Map param);

    Integer getBanyakSpjBelumSah(Map param);

    Integer getBanyakBulan(Map param);
    
    Integer getBanyakJourSpj(Map param);

    List<Spj> getBulan(Map param);
    
    List<Spj> getBulanJournal(Map param);
    
    List<Spj> getListJourSpj (Map<String, Object> param);

    Integer getCountBulanSpjByIdSkpdDanBulan(Map param);

    Bendahara getBendaharaById(Map param);

    void insertSpj(Spj param);

    void insertJournalSpj(Spj param);

    void insertSpjRinci(SpjRinci spjRinci);

    void updateSpj(Spj param);

    void updateSpjRinci(SpjRinci spjRinci);

    void deleteSpjRinciKegiatan(SpjRinci spjRinci);

    void deleteSpjRinci();

    void deleteSpjBlMaster(Integer idSpj);

    void deleteUpdateSpjRinci(Map param);

    void deleteSpjRinciByIdSpj(Integer idSpj);

    Spj getSpjById(Integer id);

    Spj getKegiatanById(Map id);
    
    Spj getKodeAktif(Map param);
    
    Spj getTotalPagu(Map param);
    
    Spj getTotalPaguTU(Map param);
    
    Spj getTotalPaguIndex(Map param);
    
    List<Spj> getComboKegiatanAktif1(Map<String, Object> param);

    Integer getCountComboKegiatanAktif1(Map param);
    

}
