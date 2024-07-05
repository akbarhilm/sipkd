package sp2d.entity;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import spp.model.HariKerja;
import spp.model.SppUp;
import spp.model.Sp2dBatal;
import spp.model.Sp2d;

/**
 *
 * @author Admin
 */
public interface CetakSp2dMapper {

    void updatenamafile(Map map);

    Integer getbanyaksp2dcetakbtl3(Map map);

    Integer getbanyaksp2dcetakbtl4(Map map);

    String getskpdid(Map map);

    Integer getNoBa(Map map);

    Integer getbanyaksp2dcetakres3(Map map);

    void updatesp2dcetak(Map sp2d);

    void updatesp2dcetak1(Map sp2d);

    List<SppUp> getProsesCetakSp2d1(Map param);

    void updatesp2dsah(Map sp2d);

    Map getsp2dsahbyidsp2d(Integer param);

    Map getsp2dsahid(Integer param);

    Map getsp2dsahnomor(Integer param);

    HariKerja getharikerjaspp(Date tgl);

    List<Map> getnilaiparam(Map param);

    Integer getbanyaksp2dcetak(Map param);

    String getnamaWilayah(String param);

    Integer getbanyaksp2dsah(Map param);

    List<SppUp> getlistsp2dcetak(Map param);

    List<SppUp> getlistsp2dsah(Map param);

    void deletesp2dcetak(Integer param);

    public Integer getbanyaksp2dbatalsah(Map param);

    public List<Sp2dBatal> getlistsp2dbatalsah(Map param);

    public Sp2dBatal getSp2dByIdSp2d(Integer idsp2d);

    public void batalSp2dSah(Sp2dBatal sp2dbatal);

    public void insertTabelSp2dBatal(Sp2dBatal sp2dbatal);

    List<SppUp> getPathFile(Map param);

    List<SppUp> getPathFile1(Map param);

    Sp2d getUmkSah(Map param);

}
