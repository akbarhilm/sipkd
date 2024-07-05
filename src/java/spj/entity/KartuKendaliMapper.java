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
public interface KartuKendaliMapper {

    List<BukuKasUmum> getListKegiatan(Map<String, Object> param);
    
    Integer getBanyakListKegiatan(Map<String, Object> param);
    
    List<Map> getnilaiparam(Map param);

}
