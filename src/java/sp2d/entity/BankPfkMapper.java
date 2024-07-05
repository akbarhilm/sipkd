package sp2d.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import spp.model.BankPfk;

/**
 *
 * @author Admin
 */
public interface BankPfkMapper {

    List<BankPfk> getListBankInduk(Map<String, Object> par);

    Integer getBanyakBankInduk(Map<String, Object> par);

    List<BankPfk> getListBankCabang(Map<String, Object> par);

    Integer getBanyakBankCabang(Map<String, Object> par);
    
    BankPfk getEditBankCabang(Integer id);

    void insertBankPfk(BankPfk bank);

    void insertBankPfkUtama(BankPfk bank);

    void updateBankPfk(BankPfk bank);

    void deleteBankPfk(BankPfk bank);

    Integer getIdBankUtama(Map<String, Object> par);
    
}
