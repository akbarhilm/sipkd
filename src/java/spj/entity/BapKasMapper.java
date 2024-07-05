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
import spp.model.BapKas;
import spp.model.BapKasRinci;
import spp.model.SpjRinci;

/**
 *
 * @author maman sulaeman
 */
public interface BapKasMapper {

    List<BapKas> getBapKas(Map<String, Object> param);

    Integer getCountBapKas(Map param);
    
    Integer getCountTglBkuProses(Map param);

    String getHari(Map param1);

    void insertBapKas(BapKas param);
    
    void insertSaldoAwalBank(Map param2);
    
    void updateSaldoAwalBank(Map param2);
    
    void deleteSaldoAwalBank(Map param);

    void updateBapKas(BapKas param);

    void deleteBapKas(Map param);
    
    void deleteBapKas2(Map param);
    
    void deleteBapKasRinci(BapKasRinci bapKas);

    BapKas getBapKasById(Map param);

    public void updateHari(Map param);
    
    List<Map> getnilaiparam(Map parameter);
    
   List<Map> getBulanList(Map param);
   
   List<Map> getBulanListEdit(Map param);
   
    List<BapKas> getAllBAPKAS(Map<String, Object> param);
    
    Integer getBanyakAllBAPKAS(Map<String, Object> param);

    public void insertBapKasRinci(BapKasRinci bapKasRinci);
    
    void updateBapKasRinci(BapKasRinci param);

    public void deleteBapKasRinci2(Map param);
    
    BapKas getNilaiKas(Map param);
}
