package spj.entity;

import java.util.List;
import java.util.Map;
import spp.model.Akun;
import spp.model.Bendahara;
import spp.model.Skpd;
import spp.model.SpmProses;
import spp.model.SppPaguUp;
import spp.model.Urusan;

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
