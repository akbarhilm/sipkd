/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spj.entity.GenNumberMapper;
import spp.model.Setor;
import spj.entity.SetorJukorMapper;
import spp.model.SetorRinci;

/**
 *
 * @author Zainab
 */
@Transactional(readOnly = true)
@Service("setorJukorServices")
public class SetorJukorImpl implements SetorJukorServices {

    private static final Logger log = LoggerFactory.getLogger(SetorImpl.class);
    @Autowired
    private SetorJukorMapper setorMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<Setor> getSetor(Map param) {
        return setorMapper.getSetor(param);
    }

    @Override
    public Integer getCountSetor(Map param) {
        return setorMapper.getCountSetor(param);
    }

    @Override
    public List<SetorRinci> getKegiatan(Map<String, Object> param) {
        return setorMapper.getKegiatan(param);
    }

    @Override
    public List<SetorRinci> getForAddRinci(Map param) {
        return setorMapper.getForAddRinci(param);
    }

    @Override
    public Integer getCountForRinci(Map param) {
        return setorMapper.getCountForRinci(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSetor(Setor setor) {
        final Map param = new HashMap();
        param.put("tahun", setor.getTahun());
        param.put("idskpd", setor.getSkpd().getIdSkpd());
        setor.setNoSetor(genNumberMapper.getSETORNo(param));

        setorMapper.insertSetor(setor);

        final List<SetorRinci> list = setor.getSetorRinci();
        for (SetorRinci setorRinci : list) {
            setorRinci.setIdsetor(setor.getId());

            setorMapper.insertSetorRinci(setorRinci);
        }
    }

    @Override
    public List<Setor> getSetorBtl(Map param) {
        return setorMapper.getSetorBtl(param);
    }

    @Override
    public Integer getCountSetorBtl(Map param) {
        return setorMapper.getCountSetorBtl(param);
    }

    @Override
    public List<SetorRinci> getForAddRinciBtl(Map param) {
        return setorMapper.getForAddRinciBtl(param);
    }

    @Override
    public Integer getCountForAddRinciBtl(Map param) {
        return setorMapper.getCountForAddRinciBtl(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSetorBtl(Setor setor) {
        final Map param = new HashMap();
        param.put("tahun", setor.getTahun());
        param.put("idskpd", setor.getSkpd().getIdSkpd());
        setor.setNoSetor(genNumberMapper.getSETORNo(param));
        setorMapper.insertSetorBtl(setor);

        final List<SetorRinci> list = setor.getSetorRinci();
        for (SetorRinci setorRinci : list) {
            setorRinci.setIdsetor(setor.getId());
            setorMapper.insertSetorRinciBtl(setorRinci);
        }

    }

    @Override
    public List<Setor> getEditSetor(Map param) {
        return setorMapper.getEditSetor(param);
    }

    @Override
    public Integer getCountEditSetor(Map param) {
        return setorMapper.getCountEditSetor(param);
    }

    @Override
    public List<SetorRinci> getKegiatanById(Map<String, Object> param) {
        return setorMapper.getKegiatanById(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSetor(Setor setor) {
        setorMapper.updateSetor(setor);
        /* // rinci tidak berubah
        SetorRinci setorRinci1 = new SetorRinci();
        setorRinci1.setIdsetor(setor.getIdSetor());
        setorMapper.deleteSetorRinci(setorRinci1);

        final List<SetorRinci> list = setor.getSetorRinci();
        for (SetorRinci setorRinci : list) {
            setorMapper.insertSetorRinci(setorRinci);
        } */
    }

    @Override
    public Setor getSetorById(Integer idSetor) {
        return setorMapper.getSetorById(idSetor);
    }

    @Override
    public Setor getSetorByIdBtl(Integer idSetor) {
        return setorMapper.getSetorByIdBtl(idSetor);
    }

    @Override
    public List<SetorRinci> getForRinciBtl(Map param) {
        return setorMapper.getForRinciBtl(param);
    }

    @Override
    public Integer getCountForRinciBtl(Map param) {
        return setorMapper.getCountForRinciBtl(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSetorBtl(Setor setor) {
        setorMapper.updateSetorBtl(setor);
        /*
        SetorRinci setorRinci1 = new SetorRinci();
        setorRinci1.setIdsetor(setor.getIdSetor());
        setorMapper.deleteSetorRinci(setorRinci1);

        final List<SetorRinci> list = setor.getSetorRinci();
        for (SetorRinci setorRinci : list) {
            setorMapper.insertSetorRinciBtl(setorRinci);
        }
                */
    }

    @Override
    public Integer getIdskpdLama(Map param) {
        return setorMapper.getIdskpdLama(param);
    }

    @Override
    public List<Setor> getListKegJukorBlLs(Map param) {
        return setorMapper.getListKegJukorBlLs(param);
    }

    @Override
    public Integer getBanyakKegJukorBlLs(Map param) {
        return setorMapper.getBanyakKegJukorBlLs(param);
    }

    @Override
    public List<SetorRinci> getListAkunJukorBlLs(Map param) {
        return setorMapper.getListAkunJukorBlLs(param);
    }

    @Override
    public Integer getBanyakAkunJukorBlLs(Map param) {
        return setorMapper.getBanyakAkunJukorBlLs(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteSetor(SetorRinci setor) {
        setorMapper.deleteSetor(setor);
        setorMapper.deleteSetorRinci(setor);
        
    }
    
    @Override
    public List<Setor> getListJukorBtl(Map param) {
        return setorMapper.getListJukorBtl(param);
    }

    @Override
    public Integer getBanyakJukorBtl(Map param) {
        return setorMapper.getBanyakJukorBtl(param);
    }

    @Override
    public List<SetorRinci> getListAkunJukorBtl(Map param) {
        return setorMapper.getListAkunJukorBtl(param);
    }

    @Override
    public Integer getBanyakAkunJukorBtl(Map param) {
        return setorMapper.getBanyakAkunJukorBtl(param);
    }

}
