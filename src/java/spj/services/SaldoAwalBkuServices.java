package spj.services;

import java.util.List;
import java.util.Map;
import spp.model.SaldoAwalBku;

/**
 *
 * @author Zainab
 */
public interface SaldoAwalBkuServices {

    void insertSaldoAwal(List<SaldoAwalBku> param);

    void updateSaldoAwal(List<SaldoAwalBku> param);

    List<SaldoAwalBku> getAkun(Map<String, Object> param);

    Integer getBanyakAkun(Map<String, Object> param);

    Integer getStatusSaldo(Map<String, Object> param);

}
