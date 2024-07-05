/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import spp.model.Kontrak;
import spp.model.Metode;

public interface KontrakMapper {

    List<Kontrak> getKontrak(Map<String, Object> param);

    List<Metode> getMetode(Map param);

    List<Kontrak> getKegiatan(Map param);

    Integer getCountKegiatan(Map param);

    Integer getCountMetode(Map param);

    Integer getCountKontrak(Map param);

    void insertKontrak(Kontrak param);

    void updateKontrak(Kontrak param);

    void deleteKontrak(Integer id);

    void deleteKontrakRinci(Integer id);

    Kontrak getKontrakById(Integer id);

    String getnoKontrak(Integer param);

    String getSisaKontrak(Map param);

    Integer validasiNomorKontrak(String noKontrak);

    BigDecimal getTotalNilaiKontrakPerBast(Map<String, Integer> map);

    Integer getKodeMultiyear(Map param);

    List<Kontrak> getSisaKontrakSpj(Map param);

    List<Kontrak> getBankInduk(Map param);

    Integer getCountBankInduk(Map param);

    List<Kontrak> getNoKontrakCek(Map param);

    List<Kontrak> getSisaKontrakSpd(Map param);

    Kontrak getSisaAnggaran(Map param);

}
