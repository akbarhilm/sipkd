package sp2d.services;

import java.util.List;
import java.util.Map;
import spp.model.Skpd;
import spp.model.Sp2d;

/**
 *
 * @author Admin
 */
public interface LaporanPnrmServices {

    List<Map> getnilaiparam(Map param);

    List<Sp2d> getWilayah(Map param);

    List<Skpd> getListSkpdPnrm(Map<String, Object> param);

    Integer getBanyakListSkpdPnrm(Map<String, Object> param);

}
