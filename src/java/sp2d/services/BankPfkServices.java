/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2d.services;

import java.util.List;
import java.util.Map;
import spp.model.BankPfk;

public interface BankPfkServices {

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
    
    void updateBankCabang(List<BankPfk> bank);
}
