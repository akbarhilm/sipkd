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
import spj.entity.SetorMapper;
import spp.model.SetorRinci;

/**
 *
 * @author Husen
 */
@Transactional(readOnly = true)
@Service("setorServices")
public class SetorImpl implements SetorServices {

    private static final Logger log = LoggerFactory.getLogger(SetorImpl.class);
    @Autowired
    private SetorMapper setorMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<Setor> getSetor(Map param) {
        return setorMapper.getSetor(param);
    }

    @Override
    public List<Setor> getSetorTU(Map param) {
        return setorMapper.getSetorTU(param);
    }

    @Override
    public List<Setor> getSetorBantuan(Map param) {
        return setorMapper.getSetorBantuan(param);
    }

    @Override
    public List<Setor> getSetorUP(Map param) {
        return setorMapper.getSetorUP(param);
    }

    @Override
    public List<Setor> getSetorBiaya(Map param) {
        return setorMapper.getSetorBiaya(param);
    }

    @Override
    public List<Setor> getSetorBtl(Map param) {
        return setorMapper.getSetorBtl(param);
    }

    @Override
    public Integer getCountSetor(Map param) {
        return setorMapper.getCountSetor(param);
    }

    @Override
    public Integer getCountSetorTU(Map param) {
        return setorMapper.getCountSetorTU(param);
    }

    @Override
    public Integer getCountSetorUpGu(Map param) {
        return setorMapper.getCountSetorUpGu(param);
    }

    @Override
    public Integer getCountSetorBiaya(Map param) {
        return setorMapper.getCountSetorBiaya(param);
    }

    @Override
    public List<Setor> getEditSetor(Map param) {
        return setorMapper.getEditSetor(param);
    }

    @Override
    public List<Setor> getEditSetorBiaya(Map param) {
        return setorMapper.getEditSetorBiaya(param);
    }

    /*
    @Override
    public List<Setor> getEditSetorBtl(Map param) {
        return setorMapper.getEditSetorBtl(param);
    }
    */

    @Override
    public Integer getCountEditSetor(Map param) {
        return setorMapper.getCountEditSetor(param);
    }

    @Override
    public Integer getCountEditSetorBiaya(Map param) {
        return setorMapper.getCountEditSetorBiaya(param);
    }
    
    /*
    @Override
    public Integer getCountEditSetorBtl(Map param) {
        return setorMapper.getCountEditSetorBtl(param);
    }
    */
    
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
    @Transactional(readOnly = false)
    public void insertSetorBiaya(Setor setor) {
        final Map param = new HashMap();
        param.put("tahun", setor.getTahun());
        param.put("idskpd", setor.getSkpd().getIdSkpd());
        setor.setNoSetor(genNumberMapper.getSETORNo(param));
        setorMapper.insertSetorBiaya(setor);

        final List<SetorRinci> list = setor.getSetorRinci();
        for (SetorRinci setorRinci : list) {
            setorRinci.setIdsetor(setor.getId());
            setorMapper.insertSetorRinciBiaya(setorRinci);
        }

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
    @Transactional(readOnly = false)
    public void insertSetorBantuan(Setor setor) {
        final Map param = new HashMap();
        param.put("tahun", setor.getTahun());
        param.put("idskpd", setor.getSkpd().getIdSkpd());
        setor.setNoSetor(genNumberMapper.getSETORNo(param));
        setorMapper.insertSetorBantuan(setor);

        final List<SetorRinci> list = setor.getSetorRinci();
        for (SetorRinci setorRinci : list) {
            setorRinci.setIdsetor(setor.getId());
            setorMapper.insertSetorRinciBantuan(setorRinci);
        }

    }

    @Override
    @Transactional(readOnly = false)
    public void insertSetorUPGU(Setor setor) {
        final Map param = new HashMap();
        param.put("tahun", setor.getTahun());
        param.put("idskpd", setor.getSkpd().getIdSkpd());
        setor.setNoSetor(genNumberMapper.getSETORNo(param));
        setorMapper.insertSetorUPGU(setor);

        List<SetorRinci> listsetorRinci = setor.getSetorRinci();

        SetorRinci setorRinci = listsetorRinci.get(0);
        setorRinci.setIdsetor(setor.getId());
        setorMapper.insertSetorRinciUPGU(setorRinci);

    }

    @Override
    public List<SetorRinci> getKegiatan(Map<String, Object> param) {
        return setorMapper.getKegiatan(param);
    }

    @Override
    public List<SetorRinci> getKegiatanBantuan(Map<String, Object> param) {
        return setorMapper.getKegiatanBantuan(param);
    }

    @Override
    public String getKodeKegiatanBantuan(String idSetor) {
        return setorMapper.getKodeKegiatanBantuan(idSetor);
    }

    @Override
    public List<SetorRinci> getKegiatanById(Map<String, Object> param) {
        return setorMapper.getKegiatanById(param);
    }

    @Override
    public List<SetorRinci> getKegiatanBantuanById(Integer idSetor) {
        return setorMapper.getKegiatanBantuanById(idSetor);
    }

    @Override
    public List<SetorRinci> getForRinci(Map param) {
        return setorMapper.getForRinci(param);
    }

    @Override
    public List<SetorRinci> getForAddRinci(Map param) {
        return setorMapper.getForAddRinci(param);
    }

    @Override
    public List<SetorRinci> getForRinciBiaya(Map param) {
        return setorMapper.getForRinciBiaya(param);
    }

    @Override
    public List<SetorRinci> getForRinciBtl(Map param) {
        return setorMapper.getForRinciBtl(param);
    }

    @Override
    public List<SetorRinci> getForAddRinciBiaya(Map param) {
        return setorMapper.getForAddRinciBiaya(param);
    }

    @Override
    public List<SetorRinci> getForAddRinciBtl(Map param) {
        return setorMapper.getForAddRinciBtl(param);
    }

    @Override
    public List<SetorRinci> getForRinciBantuan(Map param) {
        return setorMapper.getForRinciBantuan(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSetorRinci(SetorRinci setorRinci, Setor setor) {
        // sppBantuanRinci.setNoSpp(sppBantuan.getNoSpp());
        setorRinci.setIdsetor(setor.getId());
        setorMapper.insertSetorRinci(setorRinci);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSetorRinciBiaya(SetorRinci setorRinci, Setor setor) {
        setorRinci.setIdsetor(setor.getId());
        setorMapper.insertSetorRinciBiaya(setorRinci);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSetorRinciBtl(SetorRinci setorRinci, Setor setor) {
        setorRinci.setIdsetor(setor.getId());
        setorMapper.insertSetorRinciBtl(setorRinci);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSetorRinciBantuan(SetorRinci setorRinci, Setor setor) {
        // sppBantuanRinci.setNoSpp(sppBantuan.getNoSpp());
        setorRinci.setIdsetor(setor.getId());
        setorMapper.insertSetorRinciBantuan(setorRinci);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSetorRinciUPGU(SetorRinci setorRinci, Setor setor) {
        setorRinci.setIdsetor(setor.getId());
        setorMapper.insertSetorRinciUPGU(setorRinci);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSetor(Setor setor) {
        setorMapper.updateSetor(setor);
        SetorRinci setorRinci1 = new SetorRinci();
        setorRinci1.setIdsetor(setor.getIdSetor());
        
        setorMapper.deleteSetorRinciBL(setorRinci1);
        
        final List<SetorRinci> list = setor.getSetorRinci();
        for (SetorRinci setorRinci : list) {
            
            setorMapper.insertSetorRinci(setorRinci);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSetorBantuan(Setor setor) {
        setorMapper.updateSetorBantuan(setor);

        final List<SetorRinci> list = setor.getSetorRinci();
        for (SetorRinci setorRinci : list) {
            setorMapper.deleteSetorRinciBantuan(setorRinci);
            setorMapper.insertSetorRinciBantuan(setorRinci);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSetorBiaya(Setor setor) {
        setorMapper.updateSetorBiaya(setor);

        final List<SetorRinci> list = setor.getSetorRinci();
        for (SetorRinci setorRinci : list) {
            setorMapper.deleteSetorRinciBiaya(setorRinci);
            setorMapper.insertSetorRinciBiaya(setorRinci);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSetorBtl(Setor setor) {
        setorMapper.updateSetorBtl(setor);
        SetorRinci setorRinci1 = new SetorRinci();
        setorRinci1.setIdsetor(setor.getIdSetor());
        setorMapper.deleteSetorRinciBtl(setorRinci1);
            
        final List<SetorRinci> list = setor.getSetorRinci();
        for (SetorRinci setorRinci : list) {
            log.debug("setorRinci IMPLEMENT ============= "+setorRinci);
            setorMapper.insertSetorRinciBtl(setorRinci);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSetorUPGU(Setor setor) {
        setorMapper.updateSetorUPGU(setor);

        List<SetorRinci> listsetorRinci = setor.getSetorRinci();
        SetorRinci setorRinci = listsetorRinci.get(0);
        setorMapper.updateRinciUPGU(setorRinci);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateRinci(SetorRinci setorRinci, Setor setor) {
        setorMapper.updateRinci(setorRinci);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateRinciUPGU(SetorRinci setorRinci, Setor setor) {
        setorMapper.updateRinciUPGU(setorRinci);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateRinciBiaya(SetorRinci setorRinci, Setor setor) {
        setorMapper.updateRinciBiaya(setorRinci);
    }

    @Override
    public Integer getCountForRinci(Map param) {
        return setorMapper.getCountForRinci(param);
    }

    @Override
    public Integer getCountForRinciBiaya(Map param) {
        return setorMapper.getCountForRinciBiaya(param);
    }

    @Override
    public Integer getCountForRinciBtl(Map param) {
        //Integer cek = setorMapper.getCountForRinciBtl(param);
        //log.debug("CEK BANYAKK BTL = "+cek);
        return setorMapper.getCountForRinciBtl(param);
    }

    @Override
    public Integer getCountForAddRinciBiaya(Map param) {
        return setorMapper.getCountForAddRinciBiaya(param);
    }

    @Override
    public Integer getCountForAddRinciBtl(Map param) {
        return setorMapper.getCountForAddRinciBtl(param);
    }

    @Override
    public Integer getCountForRinciBantuan(Map param) {
        return setorMapper.getCountForRinciBantuan(param);
    }

    @Override
    public Setor getSetorById(Integer idSetor) {
        return setorMapper.getSetorById(idSetor);
    }

    @Override
    public Setor getSetorByIdUP(Integer idSetor) {
        return setorMapper.getSetorByIdUP(idSetor);
    }

    @Override
    public Setor getSetorByIdBiaya(Integer idSetor) {
        return setorMapper.getSetorByIdBiaya(idSetor);
    }
   
    @Override
    public Setor getSetorByIdBtl(Integer idSetor) {
        return setorMapper.getSetorByIdBtl(idSetor);
    }
   
    @Override
    public Integer getIdSetorBiaya() {
        final Setor setor = new Setor();
        setor.setIdSetor(setorMapper.getIdSetorBiaya());
        return setorMapper.getIdSetorBiaya();

    }

    @Override
    @Transactional(readOnly = false)
    public void deleteSetorMaster(Integer id) {
        setorMapper.deleteSetorMaster(id);
        setorMapper.deleteSetorRinci(id);
    }

    @Override
    public Setor getSetorByIdBantuan(Integer idSetor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String getNilaiSisaUP(Map param) {
        return setorMapper.getNilaiSisaUP(param);
    }

    @Override
    public String getNilaiSisaTU(Map param) {
        return setorMapper.getNilaiSisaTU(param);
    }

    @Override
    public Integer getBanyakKegiatanTU(Map param) {
        return setorMapper.getBanyakKegiatanTU(param);
    }

    @Override
    public List<SetorRinci> getKegiatanTU(Map param) {
        return setorMapper.getKegiatanTU(param);
    }
    
    @Override
    public Integer getBebanSetorUP(Map param) {
        return setorMapper.getBebanSetorUP(param);
    }

    @Override
    public Integer getBanyakKegiatanLS(Map param) {
        return setorMapper.getBanyakKegiatanLS(param);
    }

    @Override
    public List<Setor> getKegiatanLS(Map param) {
        return setorMapper.getKegiatanLS(param);
    }
    
    @Override
    public Integer getBanyakListBlLs(Map param) {
        return setorMapper.getBanyakListBlLs(param);
    }

    @Override
    public List<SetorRinci> getListBlLs(Map param) {
        return setorMapper.getListBlLs(param);
    }
    
    @Override
    public Integer getBanyakKegiatanBTL(Map param) {
        return setorMapper.getBanyakKegiatanBTL(param);
    }

    @Override
    public List<Setor> getKegiatanBTL(Map param) {
        return setorMapper.getKegiatanBTL(param);
    }
    
    @Override
    public Integer getBanyakListBtlLs(Map param) {
        return setorMapper.getBanyakListBtlLs(param);
    }

    @Override
    public List<SetorRinci> getListBtlLs(Map param) {
        return setorMapper.getListBtlLs(param);
    }
    
    @Override
    public List<Setor> getDataBku(Map<String, Object> param) {
        return setorMapper.getDataBku(param);
    }

    @Override
    public Integer getBanyakListTU(Map param) {
        return setorMapper.getBanyakListTU(param);
    }
    
    @Override
    public List<SetorRinci> getListTU(Map<String, Object> param) {
        return setorMapper.getListTU(param);
    }
    
    @Override
    public List<Setor> getSalsoAwalLs(Map param) {
        return setorMapper.getSalsoAwalLs(param);
    }

    @Override
    public Integer getNilaiSetorSA(Integer idSetor) {
        return setorMapper.getNilaiSetorSA(idSetor);
    }
}
