/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.Kegiatan;

/**
 *
 * @author Zainab
 */
public interface KegiatanMapper {
   
  List<Kegiatan> getKegiatan(Map<String, Object> param);

  Integer getBanyakKegiatan(Map<String, Object> param);
  
  
}
