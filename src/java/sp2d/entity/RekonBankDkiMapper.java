package sp2d.entity;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface RekonBankDkiMapper {

    List<Map> getListRekon(Map param);

    Integer getBanyakRekon(Map param);

    
}
