/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spj.entity.KontrakMapper;
import spp.model.Kontrak;
import spp.model.Metode;

@Transactional(readOnly = true)
@Service("kontrakServices")
public class KontrakImpl implements KontrakServices {

    @Autowired
    private KontrakMapper kontrakMapper;

    @Override
    public List<Kontrak> getKontrak(Map<String, Object> param) {
        return kontrakMapper.getKontrak(param);
    }

    @Override
    public Integer getCountKontrak(Map param) {
        return kontrakMapper.getCountKontrak(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateKontrak(Kontrak param) {
        kontrakMapper.updateKontrak(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteKontrak(Integer id) {
        kontrakMapper.deleteKontrak(id);
    }

    @Override
    public Kontrak getKontrakById(Integer id) {
        return kontrakMapper.getKontrakById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertKontrak(Kontrak param) {
        kontrakMapper.insertKontrak(param);
    }

    @Override
    public List<Metode> getMetode(Map param) {
        return kontrakMapper.getMetode(param);
    }

    @Override
    public Integer getCountMetode(Map param) {
        return kontrakMapper.getCountMetode(param);
    }
    
    @Override
    public List<Kontrak> getKegiatan(Map param) {
        return kontrakMapper.getKegiatan(param);
    }

    @Override
    public Integer getCountKegiatan(Map param) {
        return kontrakMapper.getCountKegiatan(param);
    }
}
