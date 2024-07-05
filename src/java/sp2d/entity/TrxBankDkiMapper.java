/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2d.entity;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import spp.model.TrxBankDki;

public interface TrxBankDkiMapper {

    List<TrxBankDki> getListTransaksi(Map param);

    Integer getBanyakTransaksi(Map param);

    void updateTransaksi(TrxBankDki param);

    List<TrxBankDki> getComboUser(Map param);

    
}
