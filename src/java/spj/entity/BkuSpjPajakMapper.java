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
public interface BkuSpjPajakMapper {
    
    List<BukuKasUmum> getKegiatan(Map<String, Object> param);

    Integer getBannyakKegiatan(Map<String, Object> param);
    
    void insertBkuSpj(BukuKasUmum param);
    
    List<BukuKasUmum> getListSPJ(Map<String, Object> param);

    Integer getBannyakListSPJ(Map<String, Object> param);
  
    Integer getBkuNo(Map param);
    
    BukuKasUmum getNilaiSisaSpj(Map<String, Object> param);
    
    BukuKasUmum getNilaiValidasiSisaSpj(Map<String, Object> param);
    
    Integer getBebanSpjTu(Map<String, Object> param);
    
    List<BukuKasUmum> getKegiatanSPJ(Map<String, Object> param);

    Integer getBanyakKegiatanSPJ(Map<String, Object> param);
    
    List<BukuKasUmum> getListSpjTU(Map<String, Object> param);

    Integer getBannyakListSpjTU(Map<String, Object> param);
    
    List<BukuKasUmum> getKodeTutup(Map<String, Object> param);
    
    List<BukuKasUmum> getKodeTutupMax(Map<String, Object> param);
    
    BukuKasUmum getNilaiValidasiSisaSpjTU(Map<String, Object> param);
    
    List<BukuKasUmum> getNamaNipSpjPanjar(Map<String, Object> param);
    
    List<BukuKasUmum> getKegiatanSpjPanjar(Map<String, Object> param);

    Integer getBanyakKegiatanSpjPanjar(Map<String, Object> param);
    
    List<BukuKasUmum> getKegiatanByBeban(Map<String, Object> param);

    List<BukuKasUmum> getBulanByRekap(Map<String, Object> param);
    
    List<BukuKasUmum> getListPajak(Map<String, Object> param);

    Integer getBanyakListPajak(Map<String, Object> param);
    
    List<BukuKasUmum> getListIndex(BukuKasUmum param);

    Integer getBanyakListIndex(BukuKasUmum param);
    
    BukuKasUmum getBkuEdit(Map<String, Object> param);
    
    List<BukuKasUmum> getDataPajak(Map<String, Object> param);
    
    void deleteBkuSpj(Map<String, Object> param);
 
    void updateBku(BukuKasUmum param);
    
    void deleteById(Map<String, Object> param);
 
    void updateById(BukuKasUmum param);
    
    BukuKasUmum getNilaiSisaSpjTunai(Map<String, Object> param);
    
    
}

