package sp2d.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import spp.model.Sp2d;

/**
 *
 * @author Admin
 */
public interface Sp2dMapper {

    List<Sp2d> getAllSp2dUP(Map<String, Object> par);

    Integer getBanyakAllSp2dUP(Map<String, Object> par);

    Sp2d getSp2dUPById(Integer idsp2d);

    void insertSp2dRinci(Sp2d sp2d);

    void updateSp2dRinci(Sp2d sp2d);

    BigDecimal getNilaiTotalSPMUP(Integer idsp2d);

    List<Sp2d> getAllSp2dSah(Map<String, Object> par);

    Integer getBanyakSp2dSah(Map<String, Object> par);
}
