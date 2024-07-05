/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spj.entity.SaldoAwalBkuMapper;
import spp.model.SaldoAwalBku;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Zainab
 */
@Transactional(readOnly = true)
@Service("SaldoAwalBkuServices")
public class SaldoAwalBkuImpl implements SaldoAwalBkuServices {

    private static final Logger log = LoggerFactory.getLogger(SaldoAwalBkuImpl.class);
    @Autowired
    private SaldoAwalBkuMapper saldoMapper;

    @Override
    @Transactional(readOnly = false)
    public void insertSaldoAwal(List<SaldoAwalBku> listsaldo) {
        final Map param = new HashMap();
        final SaldoAwalBku saldo0 = listsaldo.get(0);
        param.put("tahun", saldo0.getTahun());
        param.put("idskpd", saldo0.getIdskpd());

        //saldoMapper.deleteSaldoAwal(saldo0); 
        for (SaldoAwalBku saldo : listsaldo) {
            final BigDecimal nilaiSaldo = saldo.getNilaiSaldo();
            final String status = saldo.getKodeStatus();

            if ("ADD".equals(status)) {
                if (nilaiSaldo.compareTo(BigDecimal.ZERO) == 0) {
                    log.debug("-------------- nilai bast == 0 --------------");

                } else {
                    saldoMapper.insertSaldoAwal(saldo);
                }
            } else if ("EDIT".equals(status)){
                saldoMapper.updateSaldoAwal(saldo);
            }

        }
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSaldoAwal(List<SaldoAwalBku> listsaldo) {

        for (SaldoAwalBku saldo : listsaldo) {
            saldoMapper.updateSaldoAwal(saldo);
        }
    }

    @Override
    public List<SaldoAwalBku> getAkun(Map<String, Object> param) {
        return saldoMapper.getAkun(param);
    }

    @Override
    public Integer getBanyakAkun(Map<String, Object> param) {
        return saldoMapper.getBanyakAkun(param);
    }

    @Override
    public Integer getStatusSaldo(Map<String, Object> param) {
        return saldoMapper.getStatusSaldo(param);
    }

    
}
