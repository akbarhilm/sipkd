package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.BukuKasUmum;
import spp.model.Skpd;

/**
 *
 * @author Zainab
 */
public interface BkuServices {

    List<BukuKasUmum> getNoBukti(Map<String, Object> param);
    
    Integer getBannyaNoBukti(Map<String, Object> param);

    List<BukuKasUmum> getListBkuUpgutu(Map<String, Object> param);

    Integer getBannyakListBkuUpgutu(Map<String, Object> param);
  
    List<BukuKasUmum> getListBkuLs1(Map<String, Object> param);

    Integer getBannyakListBkuLs1(Map<String, Object> param);
  
    List<BukuKasUmum> getListBkuLs2(Map<String, Object> param);

    Integer getBannyakListBkuLs2(Map<String, Object> param);
  
    List<BukuKasUmum> getListBkuLs3(Map<String, Object> param);

    Integer getBannyakListBkuLs3(Map<String, Object> param);
  
    List<BukuKasUmum> getListBkuLs4(Map<String, Object> param);

    Integer getBannyakListBkuLs4(Map<String, Object> param);
    
    void insertBKU( List<BukuKasUmum> param);
  
    List<BukuKasUmum> getNilaiIndex(Map<String, Object> param);

    List<BukuKasUmum> getListIndex(BukuKasUmum param);

    Integer getBannyakListIndex(BukuKasUmum param);
    
    List<BukuKasUmum> getKegiatan(Map<String, Object> param);

    List<BukuKasUmum> getAkunCombo(Map<String, Object> param);

    Integer getBannyakKegiatan(Map<String, Object> param);
    
    void insertBkuSpj(List<BukuKasUmum> param);
    
    BukuKasUmum getBkuEdit(Map<String, Object> param);
    
    BukuKasUmum getBkuEditPajak(Map<String, Object> param);
  
    List<BukuKasUmum> getRinciSpjEdit(Map<String, Object> param);

    Integer getBanyakRinciSpjEdit(Map<String, Object> param);
    
    void updateBku(List<BukuKasUmum> param);
    
    void insertBkuSetorUp(BukuKasUmum param);
    
    List<BukuKasUmum> getKegiatanBantuan(Map<String, Object> param);

    Integer getBanyakKegiatanBantuan(Map<String, Object> param);
    
    List<BukuKasUmum> getKegiatanBl(Map<String, Object> param);

    Integer getBanyakKegiatanBl(Map<String, Object> param);
    
    List<BukuKasUmum> getAkunComboBtl(Map<String, Object> param);

    List<BukuKasUmum> getAkunComboBantuan(Map<String, Object> param);

    List<BukuKasUmum> getAkunComboBl(Map<String, Object> param);

    List<BukuKasUmum> getAkunComboBiaya(Map<String, Object> param);
    
    void insertBkuTanpaKeg(List<BukuKasUmum> param);
    
    BukuKasUmum getBkuEditStUp(Map<String, Object> param);
    
    void updateBkuSetorUp(BukuKasUmum param);
    
    void updateBkuLs2(List<BukuKasUmum> param);
    
    List<BukuKasUmum> getRinciEditLs2(Map<String, Object> param);

    Integer getBanyakRinciEditLs2(Map<String, Object> param);
    
    List<BukuKasUmum> getSkpdBl(Map<String, Object> param);
    
    List<BukuKasUmum> getSkpdBtl(Map<String, Object> param);
    
    List<BukuKasUmum> getListSPJ(Map<String, Object> param);

    Integer getBannyakListSPJ(Map<String, Object> param);
  
    BukuKasUmum getBkuSPJEdit(Map<String, Object> param);
    
    void updateBkuSpj(List<BukuKasUmum> param);
    
    void insertBkuCek(BukuKasUmum param);
    
    void updateBkuCek(BukuKasUmum param);
    
    BukuKasUmum getBkuEditCek(Map<String, Object> param);
    
    List<BukuKasUmum> getListSp2dEdit(Map<String, Object> param);

    Integer getBanyakListSp2dEdit(Map<String, Object> param);
    
    BukuKasUmum getBkuEditSp2d(Map<String, Object> param);
    
    void updateBkuSp2d(BukuKasUmum param);
    
    void insertBkuNM(List<BukuKasUmum> param);
    
    void insertBkuNP(List<BukuKasUmum> param);
    
    BukuKasUmum getBkuEditNPD(Map<String, Object> param);
  
    List<BukuKasUmum> getRinciNpdEdit(Map<String, Object> param);

    Integer getBanyakRinciNpdEdit(Map<String, Object> param);
    
    void updateBkuNP(List<BukuKasUmum> param);
    
    void updateBkuNM(List<BukuKasUmum> param);
    
    List<BukuKasUmum> getListNP(Map<String, Object> param);

    Integer getBanyakListNP(Map<String, Object> param);
    
    List<BukuKasUmum> getListNM(Map<String, Object> param);

    Integer getBanyakListNM(Map<String, Object> param);
    
    List<BukuKasUmum> getKegiatanNP(Map<String, Object> param);

    Integer getBanyakKegiatanNP(Map<String, Object> param);
    
    List<BukuKasUmum> getKegiatanNM(Map<String, Object> param);

    Integer getBanyakKegiatanNM(Map<String, Object> param);
    
    List<BukuKasUmum> getNamaPPTK(Map<String, Object> param);
    
    void insertBkuNPNM(List<BukuKasUmum> param);
    
    void updateBkuNPNM(List<BukuKasUmum> param);
    
    BukuKasUmum getStatusSuadana(Map<String, Object> param);
    
    void deleteById(List<BukuKasUmum> param);
    
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

    Integer getBanyakKegiatanByBeban(Map<String, Object> param);
    
    void updateBkuSpjKoreksi(BukuKasUmum param);
    
    List<BukuKasUmum> getWilayah(Map<String, Object> param);

    Integer getSetorUPSemester(Map<String, Object> param);
 
    List<BukuKasUmum> getAkunLain(Map<String, Object> param);

    Integer getBanyakAkunLain(Map<String, Object> param);
    
    BukuKasUmum getBkuEditLainLain(Map<String, Object> param);
    
    List<BukuKasUmum> getBendahara(Map<String, Object> param);
    
    List<BukuKasUmum> getSaldoAwal(Map<String, Object> param);
 
    List<BukuKasUmum> getBulanByRekap(Map<String, Object> param);
    
    BukuKasUmum getSaldoAwalPajak(Map<String, Object> param);
    
    BukuKasUmum getSisaPajak(Map<String, Object> param);
 
    List<BukuKasUmum> getKegiatanSetorTU(Map<String, Object> param);

    Integer getBanyakKegiatanSetorTU(Map<String, Object> param);
    
    List<BukuKasUmum> getAkunComboTU(Map<String, Object> param);

    List<BukuKasUmum> getListStBtl(Map<String, Object> param);
    
    Integer getBanyakListStBtl(Map<String, Object> param);

    List<BukuKasUmum> getListStBl(Map<String, Object> param);
    
    Integer getBanyakListStBl(Map<String, Object> param);

    List<BukuKasUmum> getAkunStBtl(Map<String, Object> param);
    
    Integer getBanyakAkunStBtl(Map<String, Object> param);

    List<BukuKasUmum> getAkunStBl(Map<String, Object> param);
    
    Integer getBanyakAkunStBl(Map<String, Object> param);

    void updateBkuSt(List<BukuKasUmum> param);
    
    void updateBkuStBl(List<BukuKasUmum> param);
 
    List<BukuKasUmum> getListST(Map<String, Object> param);
    
    Integer getBanyakListST(Map<String, Object> param);

    List<BukuKasUmum> getSaldoJasaGiro(Map<String, Object> param);
    
    void insertBkuJasaGiro(BukuKasUmum param);
    
    void updateBkuJasaGiro(BukuKasUmum param);
    
    List<BukuKasUmum> getSaldoBpjs(Map<String, Object> param);
    
    void updateBkuBpjs(BukuKasUmum param);
    
    BukuKasUmum getKodeSA(Map<String, Object> param);
    
    List<BukuKasUmum> getListPnPajak(Map<String, Object> param);

    Integer getBanyakListPnPajak(Map<String, Object> param);
    
    List<BukuKasUmum> getListPgPajak(Map<String, Object> param);

    Integer getBanyakListPgPajak(Map<String, Object> param);
    
    void insertBkuPajakSA(BukuKasUmum param);
    
    BukuKasUmum getNilaiBkuPg(Map<String, Object> param);
    
    List<BukuKasUmum> getListPgPajakEdit(Map<String, Object> param);

    Integer getBanyakListPgPajakEdit(Map<String, Object> param);
    
    void insertBkuPjPg(List<BukuKasUmum> param);
    
    void updateBkuPjPg(BukuKasUmum param);
    
    void updateBkuPjPn(List<BukuKasUmum> param);
    
    List<BukuKasUmum> getRinciPjPn(Map<String, Object> param);

    BukuKasUmum getRinciPjPg(Map<String, Object> param);
    
    void insertBkuPjPn(List<BukuKasUmum> param);
    
    List<BukuKasUmum> getJenisBku(Map<String, Object> param);

    List<BukuKasUmum> getListJGBP(Map<String, Object> param);

    Integer getBanyakListJGBP(Map<String, Object> param);
    
    BukuKasUmum getSaldoAawalJGBP(Map<String, Object> param);    
    
    BukuKasUmum getDataKBUD(Map<String, Object> param);

}
