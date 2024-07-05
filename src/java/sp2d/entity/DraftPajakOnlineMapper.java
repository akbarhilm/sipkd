package sp2d.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import spp.model.DraftPajakOnline;

/**
 *
 * @author Admin
 */
public interface DraftPajakOnlineMapper {

    List<Map> getListDraftPajakOnline(Map<String, Object> param);

    Integer getBanyakDraftPajakOnline(Map<String, Object> par);

   
}
