/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.CetakSkpd;
import spp.model.BukuKasUmum;
import spp.model.Kegiatan;

/**
 *
 * @author Zainab
 */
public interface CetakBKUPengeluaranMapper {
    
     List<Map> getListXlsBku(Map<String, Object> param);

    Integer getBanyakListXlsBku(Map<String, Object> param);
   
    List<BukuKasUmum> getBulanBKUPengeluaran(Map param);
    
    List<BukuKasUmum> getBulanBKUPengeluaran2(Map param);

    void insertBKUPengeluaran(BukuKasUmum param);

    List<Map> getnilaiparamBKU(Map param);

    List<BukuKasUmum> getKegiatanBKU(Map param);

    Integer getCountKegiatanBKU(Map param);
   
    List<BukuKasUmum> getListBkuPengeluaran(Map param);

    Integer getCountListBkuPengeluaran(Map param);
   
    List<BukuKasUmum> getListBkuPerKegiatan(Map param);

    Integer getCountListBkuPerKegiatan(Map param);

    List<BukuKasUmum> getTanggalBelumJurnal(Map param);
   
}








