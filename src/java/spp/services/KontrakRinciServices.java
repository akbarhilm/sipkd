/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.services;

import java.util.List;
import java.util.Map;
import spp.model.KontrakRinci;

/**
 *
 * @author erzy
 */
public interface KontrakRinciServices {

    void insertKontrakRinci(List<KontrakRinci> bast);

    void updateKontrakRinci(List<KontrakRinci> param);

    void deleteKontrakRinci(Integer id);

    List<KontrakRinci> getAkun(Map param);

    Integer getBanyakAkun(Map param);

    KontrakRinci getNilaiKontrak(Map param);

}
