package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.Kegiatan;

/**
 *
 * @author Zainab
 */
public interface KegiatanServices {

  List<Kegiatan> getKegiatan(Map<String, Object> param);

  Integer getBanyakKegiatan(Map<String, Object> param);
  
}
