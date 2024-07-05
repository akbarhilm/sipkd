/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2d.services;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sp2d.entity.TrxBankDkiMapper;
import spp.model.TrxBankDki;

@Transactional(readOnly = true)
@Service("TrxBankDkiServices")
public class TrxBankDkiImpl implements TrxBankDkiServices {

    private static final Logger log = LoggerFactory.getLogger(TrxBankDkiImpl.class);
    @Autowired
    private TrxBankDkiMapper TrxBankDkiMapper;
    
    
    @Override
    public List<TrxBankDki> getListTransaksi(Map param) {
        return TrxBankDkiMapper.getListTransaksi(param);
    }

    @Override
    public Integer getBanyakTransaksi(Map param) {
        return TrxBankDkiMapper.getBanyakTransaksi(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateTransaksi(TrxBankDki param) {
        TrxBankDkiMapper.updateTransaksi(param);
    }

    @Override
    public List<TrxBankDki> getComboUser(Map param) {
        return TrxBankDkiMapper.getComboUser(param);
    }
}
