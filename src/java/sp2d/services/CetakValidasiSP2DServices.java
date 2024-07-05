package sp2d.services;

import java.util.List;
import java.util.Map;
import spp.model.SppUp;
import spp.model.Sp2d;
import spp.model.Sp2dBatal;

/**
 *
 * @author Admin
 */
public interface CetakValidasiSP2DServices {

    void updatenamafile(Map map);

    List<Map> getnilaiparam(Map param);

    List<SppUp> getProsesCetakSp2d1(Map param);

    Integer getbanyaksp2dcetakbtl3(Map map);

    Integer getbanyaksp2dcetakbtl4(Map map);

    String getskpdid(Map map);

    Integer getNoBa(Map map);

    Integer getbanyaksp2dcetakres3(Map map);

    Map getsp2dsahbyidsp2d(Integer param);

    Map getsp2dsahid(Integer param);

    Map getsp2dsahnomor(Integer param);

    void updatesp2dcetak(Map sp2d);

    void updatesp2dcetak1(Map sp2d);

    void updatesp2dsah(Map sp2d);

    Integer getbanyaksp2dcetak(Map param);

    Integer getbanyaksp2dsah(Map param);

    String getnamaWilayah(String param);

    List<SppUp> getlistsp2dsah(Map param);

    List<SppUp> getlistsp2dcetak(Map param);

    void deletesp2dcetak(Integer param);

    public Integer getbanyaksp2dbatalsah(Map param);

    public List<Sp2dBatal> getlistsp2dbatalsah(Map param);

    public Sp2dBatal getSp2dByIdSp2d(Integer idsp2d);

    public void batalSp2dSah(Sp2dBatal sp2dbatal);

    List<SppUp> getPathFile(Map param);

    List<SppUp> getPathFile1(Map param);

    Sp2d getUmkSah(Map param);

}
