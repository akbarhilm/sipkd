package sp2d.services;

import java.util.List;
import java.util.Map;
import spp.model.SppUp;
import spp.model.Sp2dBatal;

/**
 *
 * @author Admin
 */
public interface RekonBankDkiServices {

    List<Map> getListRekon(Map param);

    Integer getBanyakRekon(Map param);

}
