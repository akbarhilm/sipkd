/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spj.entity;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import spp.model.HariKerja;
import spp.model.Spj;

/**
 *
 * @author maman sulaeman
 */
public interface CetakSpjMapper {
    
    
    List<Spj> getSpjCetakBl(Map<String, Object> param);

    Integer getBanyakgetSpjCetakBl(Map param);
    
     List<Spj> getlistspjsah(Map<String, Object> param);

    Integer getbanyakspjsah(Map param);
    
     List<Map> getnilaiparam(Map param);
    
    void insertspjsah(Map spj);
    
    void insertSpjCetak(Map spj);
    
    void deleteSpjCetak(Integer idspj);
    
    HariKerja getHariKerjaSpj(Date tgl);
    
    Map getspjsahbyidspj(Integer param);
    
}
