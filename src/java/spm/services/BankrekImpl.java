package spm.services;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spm.entity.BankrekMapper;
import spp.model.Bankrek;
import spp.model.Skpd;

@Transactional(readOnly = true)
@Service("bankrekServices")
public class BankrekImpl implements BankrekServices {

    @Autowired
    private BankrekMapper bankrekmapper;

    @Override
    public List<Skpd> getSKPD(Map param) {
        return bankrekmapper.getSKPD(param);
    }

    @Override
    public List<Bankrek> getBankrek(Map param) {
        return bankrekmapper.getBankrek(param);
    }

    @Override
    public Integer getBanyakAllBankrek(Map param) {
        return bankrekmapper.getBanyakAllBankrek(param);
    }

    @Override
    public Integer getBanyakAllSKPD(Map param) {
        return bankrekmapper.getBanyakAllSKPD(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBankrek(Bankrek param) {
        bankrekmapper.insertBankrek(param);
    }

    @Override
    public Bankrek getBankrekById(Integer id) {
        return bankrekmapper.getBankrekById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBankrek(Bankrek param) {
        bankrekmapper.updateBankrek(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBankrek(Integer id) {
        bankrekmapper.deleteBankrek(id);
    }

}
