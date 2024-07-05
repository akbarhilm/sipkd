package eset.entity;

import java.util.List;
import java.util.Map;
import eset.model.Akun;
import eset.model.Bendahara;
import eset.model.Skpd;
import eset.model.SpmProses;
import eset.model.SppPaguUp;
import eset.model.Urusan;

/**
 *
 * @author Admin
 */
public interface ReferensiMapper {

    List<SppPaguUp> getAllSppPaguUp(Map<String, Object> param);

    Integer getBanyakSppPaguUp(Map<String, Object> param);

    void updateSppPaguUp(SppPaguUp sppPaguUp);

    void pindahSppPaguTahun(Map value);

    void hapusSppPaguTahun(Map value);

    List<Map<String, Object>> getAllAkunRoot();

    List<Map<String, Object>> getAllAkunAnak(Map<String, Object> param);

    Skpd getSkpdById(Integer id);

    Akun getAkunById(Integer id);

    Akun getAkunByIdTambah(Integer id);

    void insertAkun(Akun param);

    List<Urusan> getUrusan(Map param);

    void editSkpd(Skpd param);

    void updateAkun(Akun param);

    void insertSkpd(Skpd param);

    Integer getBanyakAllUrusan(Map param);

    Integer getBanyakBendaharaSpp(Map param);

    List<Bendahara> getAllBendaharaSpp(Map param);

    SpmProses getSpmBatasProses( Map param);
}
