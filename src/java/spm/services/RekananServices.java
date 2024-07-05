/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spm.services;

import java.util.List;
import java.util.Map;
import spp.model.Rekanan;

/**
 *
 * @author maman sulaeman
 */
public interface RekananServices {

    List<Rekanan> getRekanan(Map<String, Object> param);

    Integer getCountRekanan(Map param);

    void insertRekanan(Rekanan param);

    void updateRekanan(Rekanan param);

    void deleteRekanan(Integer id);

    Rekanan getRekananById(Integer id);

}
