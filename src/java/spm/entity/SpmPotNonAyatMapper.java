/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spm.entity;
import java.util.List;
import java.util.Map;
import spp.model.Kontrak;
import spp.model.SpmPotNonAyat;
/**
 *
 * @author Xalamaster
 */
public interface SpmPotNonAyatMapper {
    
    List<SpmPotNonAyat> getTotSpm(Map<String, Object> param);

    List<SpmPotNonAyat> getVkontrak(Map<String, Object> param);

    List<SpmPotNonAyat> getJamsostek(Map<String, Object> param);

    List<SpmPotNonAyat> getValTblSpm(Map<String, Object> param);

    void addPotayat(SpmPotNonAyat param);

    void updatePotayat(SpmPotNonAyat param);

    void deletePotongan(SpmPotNonAyat param);
    
    SpmPotNonAyat getPotUmk(Map<String, Object> param);
    
    // BUAT NGETESSS
    SpmPotNonAyat getNamaAkun(String akun);
    
    String getNamaAkunString(String akun);

    List<Kontrak> getKontrak(Map<String, Object> param);
    
    
}
