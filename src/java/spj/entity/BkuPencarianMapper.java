/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package spj.entity;
import java.util.List;
import java.util.Map;
import spp.model.BukuKasUmum;

/**
 *
 * @author Zainab
 */
public interface BkuPencarianMapper {
    
    List<BukuKasUmum> getPencarianNoDok(Map<String, Object> param);
    
    List<BukuKasUmum> getPencarianKegiatan(Map<String, Object> param);
    
    List<BukuKasUmum> getPencarianSisaUangPerPptk(Map<String, Object> param);
    
    List<BukuKasUmum> getPencarianSisaUangPptk(Map<String, Object> param);
    
    Integer getBanyakCariNoDok(Map<String, Object> param);
    
    Integer getBanyakCariKegiatan(Map<String, Object> param);
    
    Integer getBanyakCariSisaUangPptk(Map<String, Object> param);
    
    Integer getBanyakCariSisaUangPerPptk(Map<String, Object> param);
    
    List<BukuKasUmum> getListKegiatan(Map<String, Object> param);
    
    List<BukuKasUmum> getListNoDokumen(Map<String, Object> param);
    
    Integer getBanyakListKegiatan(Map<String, Object> param);
    
    Integer getBanyakListNoDokumen(Map<String, Object> param);
    
    List<BukuKasUmum> getPencarianCek(Map<String, Object> param);
    
    List<BukuKasUmum> getPencarianSetoran(Map<String, Object> param);
    
    List<BukuKasUmum> getPencarianNoDokSPJ(Map<String, Object> param);
    
    List<BukuKasUmum> getPencarianPajak(Map<String, Object> param);
    
    List<BukuKasUmum> getPencarianPajakAll(Map<String, Object> param);
    
    Integer getBanyakCariCek(Map<String, Object> param);
    
    Integer getBanyakCariSetoran(Map<String, Object> param);
    
    Integer getBanyakCariNoDokSpj(Map<String, Object> param);
    
    Integer getBanyakCariPajak(Map<String, Object> param);
    
    Integer getBanyakCariPajakAll(Map<String, Object> param);
    
    List<BukuKasUmum> getListPptk(Map<String, Object> param);
    
    Integer getBanyakListPptk(Map<String, Object> param);
    
    BukuKasUmum getNilaiAnggaran(Map<String, Object> param);
    
    BukuKasUmum getNilaiAnggaranTU(Map<String, Object> param);
    
    List<BukuKasUmum> getPencarianKegiatanTU(Map<String, Object> param);
    
    Integer getBanyakCariKegiatanTU(Map<String, Object> param);

    List<Map> getListXlsBkuPencarianKegiatan(Map<String, Object> param);

    Integer getBanyakListXlsBkuPencarianKegiatan(Map<String, Object> param);
    
    
}
