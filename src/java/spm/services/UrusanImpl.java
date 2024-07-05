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
import spm.entity.UrusanMapper;
import spp.model.Bank;
import spp.model.Fungsi;
import spp.model.Urusan;

@Transactional(readOnly = true)
@Service("urusanServices")
public class UrusanImpl implements UrusanServices {
    @Autowired
    private UrusanMapper urusanMapper;

    @Override
    public List<Urusan> getUrusan(Map param) {
    return urusanMapper.getUrusan(param);
    }

    @Override
    public Integer getBanyakAllUrusan(Map param) {
     return urusanMapper.getBanyakAllUrusan(param);
    }
    
    @Override
    public List<Fungsi> getAllFungsi(Map param) {
        return urusanMapper.getAllFungsi(param);
    }

    @Override
    public Integer getBanyakAllFungsi(Map param) {
        return urusanMapper.getBanyakAllFungsi(param);
    }
    @Override
    @Transactional(readOnly = false)
    public void insertUrusan(Urusan param) {
        urusanMapper.insertUrusan(param);
    }
    @Override
    public Urusan getUrusanById(Integer id) {
        return urusanMapper.getUrusanById(id);
    }
     @Override
    @Transactional(readOnly = false)
    public void updateUrusan(Urusan param) {
        urusanMapper.updateUrusan(param);
    }
 
    
    @Override
    @Transactional(readOnly = false)
    public void deleteUrusan(Integer id) {
        urusanMapper.deleteUrusan(id);
    }
}
