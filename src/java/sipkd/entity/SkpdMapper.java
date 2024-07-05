package sipkd.entity;

import java.util.List;
import java.util.Map;
import sipkd.model.Skpd;

public interface SkpdMapper {

    List<Skpd> getAllSkpd(Map<String, Object> param);

    Integer getBanyakAllSkpd(Map<String, Object> param);

    Skpd getDetailSkpd(Integer id);

    Skpd getDetailSkpdByLevel(Map<String, Object> param);

    Integer getBanyakAllSkpdBTL(Map<String, Object> param);

    List<Skpd> getAllSkpdBTL(Map<String, Object> param);
}
