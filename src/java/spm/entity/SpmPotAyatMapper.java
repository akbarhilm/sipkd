/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.entity;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.sql.Date;
import spp.model.SpmPotAyat;
/**
 *
 * @author Xalamaster
 */
public interface SpmPotAyatMapper {
    List<SpmPotAyat> getAllSpm(Map<String, Object> param);

    Integer getBanyakSpm(Map<String, Object> param);
    
    List<SpmPotAyat> getAllPotAyat(Map<String, Object> param);

    Integer getBanyakPotAyat(Map<String, Object> param);
    
    Integer getNoSpm(Integer param);
    
    Date getTglSpm(Integer param);
    
    void addPotayat(SpmPotAyat param);

    void updatePotayat(SpmPotAyat param);
    
    Integer getKodeUmk(Integer param);

    Integer getBanyakPotAyatGaji(Map<String, Object> param);

    List<SpmPotAyat> getAllPotAyatGaji(Map<String, Object> param);

    String getMacamSimpegByIdSpp(String idspp);

    BigDecimal getJumKotPotSpm(Integer idspm);

    //void editPotayat(SpmPotAyat param);
    
    
}
