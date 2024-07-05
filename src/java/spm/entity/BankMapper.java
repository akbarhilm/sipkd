package spm.entity;

import java.util.List;
import java.util.Map;
import spp.model.Bank;

/**
 *
 * @author Admin
 */
public interface BankMapper {

    List<Bank> getAllBank(Map param);

    List<Bank> getBank(Map param);

    Integer getBanyakAllBank(Map param);

    Integer getCountBank(Map param);

    void insertBank(Bank param);

    void updateBank(Bank param);

    void deleteBank(Integer id);

    Bank getBankById(Integer id);

}
