package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.Akun;

/**
 *
 * @author Zainab
 */
public interface AkunServices {

  List<Akun> getAllAkun(Map<String, Object> param);

  Integer getBanyakAllAkun(Map<String, Object> param);
  
  List<Akun> getAkun123(Map<String, Object> param);

  Integer getBanyakAkun123(Map<String, Object> param);
  
  List<Akun> getAkunBukuBesar(Map<String, Object> param);

  Integer getBanyakAkunBukuBesar(Map<String, Object> param);
  
  List<Akun> getAkunBukuBesarPpkd(Map<String, Object> param);

  Integer getBanyakAkunBukuBesarPpkd(Map<String, Object> param);
  
}
