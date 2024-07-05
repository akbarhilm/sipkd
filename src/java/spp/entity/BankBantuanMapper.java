/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.entity;

import java.util.List;
import java.util.Map;
import spp.model.BankBantuan;

/**
 *
 * @author Zainab
 */
public interface BankBantuanMapper {

    List<BankBantuan> getListBantuan(Map param);

    Integer getBanyakListBantuan(Map param);

    void updateKodeBank(BankBantuan param);

    
}
