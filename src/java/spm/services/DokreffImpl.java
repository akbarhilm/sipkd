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
import spm.entity.DokreffMapper;


import spp.model.Dokreff;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("dokreffServices")

public class DokreffImpl implements DokreffServices {

    @Autowired
    private DokreffMapper dokreffMapper;
    
    @Override
    public List<Dokreff> getDokreff(Map param){
        return dokreffMapper.getDokreff(param);
    }

    @Override
    public Integer getCountDokreff(Map param) {
        return dokreffMapper.getCountDokreff(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertdokreff(Dokreff param) {
        dokreffMapper.insertDokreff(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatedokreff(Dokreff param) {
        dokreffMapper.updateDokreff(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void deletedokreff(Integer id) {
        dokreffMapper.deleteDokreff(id);
    }

    @Override
    public Dokreff getDokreffById(Integer id) {
        return dokreffMapper.getDokreffById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void historydokreff(Dokreff param) {
        dokreffMapper.historydokreff(param);
    }
    

}
