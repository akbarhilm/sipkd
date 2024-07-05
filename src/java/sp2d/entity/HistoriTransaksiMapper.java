package sp2d.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import spp.model.Histori;

/**
 *
 * @author Admin
 */
public interface HistoriTransaksiMapper {

    Histori getRekening(Integer id);

    List<Map> getSaldoAkhirall(Map<String, Object> param);

    void getMutasiAll(Map<String, Object> param);

    List<Histori> getTransaksiall(Map<String, Object> param);
    
    Integer getBanyakTransaksiall(Map<String, Object> param);
    
     Integer getBanyakListXls(Map<String, Object> param);

    List<Map> getListXls(Map<String, Object> param);


}
