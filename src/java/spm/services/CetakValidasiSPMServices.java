package spm.services;

import java.util.List;
import java.util.Map;
import spp.model.SppUp;

/**
 *
 * @author Admin
 */
public interface CetakValidasiSPMServices {

    Integer getbanyakspmcetakbtl3(Map map);

    Integer getbanyakspmcetakbtl4(Map map);

    Integer getbanyakspmcetakbtl5(Map map);

    List<Map> getnilaiparam(Map param);

    void insertspmcetak(Map sppUp);

    Integer getbanyakspmcetak(Map param);

    List<SppUp> getlistspmcetak(Map param);

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
