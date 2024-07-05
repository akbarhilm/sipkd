/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.entity;

import java.util.List;
import java.util.Map;
import spp.model.KontrakRinci;

/**
 *
 * @author Zainab
 */
public interface KontrakRinciMapper {

    void insertKontrakRinci(KontrakRinci bast);

    void updateKontrakRinci(KontrakRinci param);

    void updateNolKontrakRinci(KontrakRinci param);

    void deleteKontrakRinci(Integer id);

    List<KontrakRinci> getAkun(Map param);

    Integer getBanyakAkun(Map param);

    KontrakRinci getNilaiKontrak(Map param);

}
