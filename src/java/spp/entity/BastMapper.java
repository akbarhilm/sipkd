/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.entity;

import java.util.List;
import java.util.Map;
import spp.model.Bast;
import spp.model.Skpd;

/**
 *
 * @author erzy
 */
public interface BastMapper {

    List<Bast> getBast(Map param);

    List<Bast> getAkun(Map param);

    Integer getBanyakAllBast(Map param);

    Integer getBanyakAllKontrak(Map param);

    Integer getBanyakAkun(Map param);

    List<Skpd> getSKPD(Map param);

    Integer getBanyakAllSKPD(Map param);

    void insertBast(Bast bast);

    void updateBast(Bast param);

    void deleteBast(Integer id);

    Bast getBastById(Integer id);

    Bast getKegiatanById(Integer idkegiatan);

    List<Bast> getKontrak(Map param);

    List<Bast> getBastByNoBastSkpdAndTahun(Map param);

    void deleteBastByNoBastSkpdAndTahun(Map param);

    void insertBastUpdate(Bast bast);

    List<Bast> getSisaBast(Map param);

    String getIdspd(String idkontrak);

    List<Bast> getAkunUpdate(Map param);

    Integer getStatusUangMuka(Map param);

    List<Bast> getStatusKontrakRinci(Map param);

    List<Bast> getAkunKontrakRinci(Map param);

    Integer getBanyakAkunKontrakRinci(Map param);

    Bast getKodeUMK(Map param);

    List<Bast> getAkunSpd(Map param);

    Integer getBanyakAkunSpd(Map param);

    List<Bast> getAkunKontrak(Map param);

    Integer getBanyakAkunKontrak(Map param);

    void updateUmkBast(Bast param);

}
