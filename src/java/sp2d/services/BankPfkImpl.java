/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2d.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import spp.entity.GenNumberMapper;
import sp2d.entity.GenNumberMapper;
import sp2d.entity.BankPfkMapper;
import spp.model.HariKerja;
import spp.model.Skpd;
import spp.model.BankPfk;

@Transactional(readOnly = true)
@Service("BankPfkServices")
public class BankPfkImpl implements BankPfkServices {

    private static final Logger log = LoggerFactory.getLogger(BankPfkImpl.class);
    @Autowired
    private BankPfkMapper BankPfkMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<BankPfk> getListBankInduk(Map<String, Object> param) {
        return BankPfkMapper.getListBankInduk(param);
    }

    @Override
    public Integer getBanyakBankInduk(Map param) {
        return BankPfkMapper.getBanyakBankInduk(param);
    }

    @Override
    public List<BankPfk> getListBankCabang(Map<String, Object> param) {
        return BankPfkMapper.getListBankCabang(param);
    }

    @Override
    public Integer getBanyakBankCabang(Map param) {
        return BankPfkMapper.getBanyakBankCabang(param);
    }

    @Override
    public BankPfk getEditBankCabang(Integer id) {
        return BankPfkMapper.getEditBankCabang(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBankPfk(BankPfk bank) {
        BankPfkMapper.insertBankPfk(bank);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBankPfkUtama(BankPfk bank) {
        BankPfkMapper.insertBankPfkUtama(bank);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBankPfk(BankPfk bank) {
        BankPfkMapper.updateBankPfk(bank);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBankPfk(BankPfk bank) {
        BankPfkMapper.deleteBankPfk(bank);
    }

    @Override
    public Integer getIdBankUtama(Map param) {
        return BankPfkMapper.getIdBankUtama(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBankCabang(List<BankPfk> param) {

        for (BankPfk bku : param) {
            BankPfkMapper.updateBankPfk(bku);
        }
    }
}
