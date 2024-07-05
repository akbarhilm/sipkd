/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.entity;

import java.util.List;
import java.util.Map;
import spp.model.BukuKasUmum;
import spp.model.FormBku;

/**
 *
 * @author Zainab
 */
public interface FormBkuMapper {

    List<Map> getnilaiparam(Map param);

    List<FormBku> getSaldoAwalTunai(Map<String, Object> param);

    List<FormBku> getSaldoAwalBank(Map<String, Object> param);

    List<FormBku> getSaldoAwalPanjar(Map<String, Object> param);

    List<FormBku> getAkunBelanja(Map<String, Object> param);
    
    List<BukuKasUmum> getWilayah(Map<String, Object> param);

    List<BukuKasUmum> getBulan(Map<String, Object> param);

    List<FormBku> getSaldoAwalPajak(Map<String, Object> param);
    
}
