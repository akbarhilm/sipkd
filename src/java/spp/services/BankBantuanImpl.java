package spp.services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spp.entity.BankBantuanMapper;
import spp.entity.GenNumberMapper;
import spp.model.BankBantuan;

@Transactional(readOnly = true)
@Service("bankBantuanServices")
public class BankBantuanImpl implements BankBantuanServices {

    @Autowired
    private BankBantuanMapper bankmapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    private static final Logger log = LoggerFactory.getLogger(BankBantuanImpl.class);

    @Override
    public List<BankBantuan> getListBantuan(Map param) {
        return bankmapper.getListBantuan(param);
    }

    @Override
    public Integer getBanyakListBantuan(Map param) {
        return bankmapper.getBanyakListBantuan(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateKodeBank(BankBantuan param) {
        bankmapper.updateKodeBank(param);
    }

}
