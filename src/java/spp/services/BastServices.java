/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.services;

import java.util.List;
import java.util.Map;
import spp.model.Bast;
import spp.model.Skpd;

/**
 *
 * @author erzy
 */
public interface BastServices {

    List<Skpd> getSKPD(Map param);

    List<Bast> getKontrak(Map<String, Object> param);

    Integer getBanyakAllBast(Map param);

    Integer getBanyakAllKontrak(Map<String, Object> param);

    Integer getBanyakAkun(Map param);

    List<Bast> getBast(Map param);

    List<Bast> getAkun(Map param);

    Integer getBanyakAllSKPD(Map param);

    void insertBast(Bast bast);

    void insertBastList(List<Bast> bast);

    void updateBast(Bast param);

    void deleteBast(Integer id);

    Bast getBastById(Integer id);

    Bast getKegiatanById(Integer idkegiatan);

    List<Bast> getBastByNoBastSkpdAndTahun(Map param);

    void updateBastList(List<Bast> bast);

    void deleteBastByNoBastSkpdAndTahun(Map param);

    List<Bast> getSisaBast(Map param);

    String getIdspd(String idkontrak);

    Integer getStatusUangMuka(Map param);

    List<Bast> getStatusKontrakRinci(Map param);

    List<Bast> getAkunKontrakRinci(Map param);

    Integer getBanyakAkunKontrakRinci(Map param);

    Bast getKodeUMK(Map param);

    List<Bast> getAkunSpd(Map param);

    Integer getBanyakAkunSpd(Map param);

    List<Bast> getAkunKontrak(Map param);

    Integer getBanyakAkunKontrak(Map param);

}
