/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.entity;

import java.util.List;
import java.util.Map;
import spp.model.SaldoAwalBku;

/**
 *
 * @author Zainab
 */
public interface SaldoAwalBkuMapper {

    void insertSaldoAwal(SaldoAwalBku param);

    void deleteSaldoAwal(SaldoAwalBku param);
    
    void updateSaldoAwal(SaldoAwalBku param);

    List<SaldoAwalBku> getAkun(Map<String, Object> param);

    Integer getBanyakAkun(Map<String, Object> param);

    Integer getStatusSaldo(Map<String, Object> param);

}
