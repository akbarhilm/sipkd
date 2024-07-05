/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.entity;

import java.util.List;
import java.util.Map;
import spp.model.Bankrek;
import spp.model.Skpd;


/**
 *
 * @author erzy
 */
public interface BankrekMapper {

    List<Bankrek> getBankrek(Map param);

    Integer getBanyakAllBankrek(Map param);

    List<Skpd> getSKPD(Map param);

    Integer getBanyakAllSKPD(Map param);

    void insertBankrek(Bankrek param);

    void updateBankrek(Bankrek param);

    void deleteBankrek(Integer id);

    Bankrek getBankrekById(Integer id);

}
