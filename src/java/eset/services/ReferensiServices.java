/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eset.services;

import java.util.List;
import java.util.Map;
import eset.model.Akun;
import eset.model.Bendahara;
import eset.model.PejabatPpkd;
import eset.model.Skpd;
import eset.model.SpmProses;
import eset.model.SppPaguUp;
import eset.model.Urusan;

/**
 *
 * @author R Tarman
 */
public interface ReferensiServices {

    List<Skpd> getAllSkpdBL(Map<String, Object> param);

    Integer getBanyakSkpdBL(Map<String, Object> param);
    
    List<Skpd> getSkpdAll(Map<String, Object> param);

    Integer getBanyakSkpdAll(Map<String, Object> param);
    
    List<Skpd> getSkpdWil(Map<String, Object> param);

    Integer getBanyakSkpdWil(Map<String, Object> param);
    
    List<Skpd> getAllSkpdBantuan(Map<String, Object> param);

    Integer getBanyakSkpdBantuan(Map<String, Object> param);
    

    List<SppPaguUp> getAllSppPaguUp(Map<String, Object> param);

    Integer getBanyakSppPaguUp(Map<String, Object> param);

    void updateSppPaguUp(SppPaguUp sppPaguUp);

    void updateSppPaguUpTahun(Map map);

    Skpd getDetailSkpdById(Integer idSkpd);

    List<PejabatPpkd> getAllPejabatPpkd(Map<String, Object> param);

    Integer getBanyakPejabatPPKD(Map<String, Object> param);

    List<Skpd> getAllSkpdAnak(Map<String, Object> param);

    List<Map<String, Object>> getAllSkpdRoot();

    List<Map<String, Object>> getAllAkunRoot();

    List<Map<String, Object>> getAllAkunAnak(Map<String, Object> param);

    Skpd getSkpdById(Integer id);
    
     void editSkpd(Skpd param);
     
     void updateAkun(Akun param);

    void insertSkpd(Skpd param);

    List<Urusan> getUrusan(Map param);

    Integer getBanyakAllUrusan(Map param);
    
    Akun getAkunById(Integer id);

    Akun getAkunByIdTambah(Integer id);
    
    void insertAkun(Akun param);
    
    Integer getBanyakBendaharaSpp(Map param);

    List<Bendahara> getAllBendaharaSpp(Map param);
    
    SpmProses getSpmBatasProses( Map param);
    
}
