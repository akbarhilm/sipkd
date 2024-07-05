package spm.entity;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import spp.model.HariKerja;
import spp.model.SppUp;
import spp.model.Sp2d;
import spp.model.Setor;
import spp.model.Npd;

/**
 *
 * @author Admin
 */
public interface CetakSpmMapper {

    HariKerja getharikerjaspp(Date tgl);

    Integer getbanyakspmcetakbtl3(Map map);

    Integer getbanyakspmcetakbtl4(Map map);

    Integer getbanyakspmcetakbtl5(Map map);

    void insertspmcetak(Map sppUp);

    Integer getbanyakspmcetak(Map param);

    List<SppUp> getlistspmcetak(Map param);

    List<Map> getnilaiparam(Map param);

    void deletespmcetak(Integer param);

    List<Map> getidkegkon(Map param);

    List<Map> getnilai(Map param);

    List<SppUp> getPathFile(Map param);

    List<Map> getSisaUmk(Map param);

    List<Map> getKodeUmk(Map param);

    List<Map> getKodeSumbDana(Map param);
    
    List<Map> getNilaiBlud(Map param);

    List<Map> getKodeMultiyear(Map param);

    List<Map> getNilaiPotonganBtl(Map param);
    
    Map getUmkSah(Map param);
    
}
