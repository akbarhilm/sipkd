/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package sipkd.services;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sipkd.entity.DokttdMapper;

import sipkd.model.Dokttd;

@Transactional(readOnly = true)
@Service("dokttdServices")

public class DokttdImpl implements DokttdServices {

    @Autowired
    private DokttdMapper dokttdMapper;

    @Override
    public List<Dokttd> getDokttd(Map param) {
        return dokttdMapper.getDokttd(param);
    }

    @Override
    public Integer getCountDokttd(Map param) {
        return dokttdMapper.getCountDokttd(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertdokttd(Dokttd param) {
        dokttdMapper.insertDokttd(param);
    }

    @Override
    public Dokttd getDokttdById(Integer id) {
        return dokttdMapper.getDokttdById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatedokttd(Dokttd param) {
        dokttdMapper.updateDokttd(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void deletedokttd(Integer id) {
        dokttdMapper.deleteDokttd(id);
    }

}
