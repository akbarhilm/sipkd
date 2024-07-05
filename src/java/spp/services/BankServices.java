/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.services;

import java.util.List;
import java.util.Map;
import spp.model.Bank;

/**
 *
 * @author Admin
 */
public interface BankServices {

    List<Bank> getBank(Map param);

    Integer getCountBank(Map param);

    void insertbank(Bank param);

    void updatebank(Bank param);

    void deletebank(Integer id);

    Bank getBankById(Integer id);
}
