/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spm.services;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spm.entity.BankMapper;

import spp.model.Bank;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("bankServices")

public class BankImpl implements BankServices {

    @Autowired
    private BankMapper bankMapper;

    @Override
    public List<Bank> getBank(Map param) {
        return bankMapper.getBank(param);
    }

    @Override
    public Integer getCountBank(Map param) {
        return bankMapper.getCountBank(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertbank(Bank param) {
        bankMapper.insertBank(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatebank(Bank param) {
        bankMapper.updateBank(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void deletebank(Integer id) {
        bankMapper.deleteBank(id);
    }

    @Override
    public Bank getBankById(Integer id) {
        return bankMapper.getBankById(id);
    }

}
