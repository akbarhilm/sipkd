/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.Akun;

/**
 *
 * @author Zainab
 */
public interface AkunMapper {
   
  List<Akun> getAllAkun(Map<String, Object> param);

  Integer getBanyakAllAkun(Map<String, Object> param);
  
  List<Akun> getAkun123(Map<String, Object> param);

  Integer getBanyakAkun123(Map<String, Object> param);
  
  List<Akun> getAkunBukuBesar(Map<String, Object> param);

  Integer getBanyakAkunBukuBesar(Map<String, Object> param);
  
  List<Akun> getAkunBukuBesarPpkd(Map<String, Object> param);

  Integer getBanyakAkunBukuBesarPpkd(Map<String, Object> param);
  
}
