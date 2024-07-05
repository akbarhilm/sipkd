/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spj.entity.GenNumberMapper;
import spj.entity.NpdMapper;
import spj.entity.SpjMapper;
import spp.model.Npd;
import spp.model.NpdRinci;
import spp.model.SppTu;
import spp.model.SppTuRinci;

@Transactional(readOnly = true)
@Service("npdServices")
public class NpdImpl implements NpdServices {

    private static final Logger log = LoggerFactory.getLogger(SpjImpl.class);

    @Autowired
    private NpdMapper npdMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<Npd> getNpd(Map<String, Object> param) {
        return npdMapper.getNpd(param);
    }

    @Override
    public Integer getCountNpd(Map param) {
        return npdMapper.getCountNpd(param);
    }

    @Override
    public List<Npd> getKegiatanNpd(Map<String, Object> param) {
        return npdMapper.getKegiatanNpd(param);
     }

    @Override
    public Integer getCountKegiatanNpd(Map param) {
        return npdMapper.getCountKegiatanNpd(param);
    }

    @Override
    public List<Npd> getNpdRinci(Map<String, Object> param) {
       return npdMapper.getNpdRinci(param);
    }

    @Override
    public Integer getCountNpdRinci(Map param) {
       return npdMapper.getCountNpdRinci(param);
    }
    @Override
    @Transactional(readOnly = false)
    public void insertNpd(Npd npd) {
        final Map param = new HashMap();
        param.put("tahun",  npd.getTahun());
        param.put("idskpd", npd.getSkpd().getIdSkpd());        
        npd.setNoNpd(genNumberMapper.getNPDNo(param));
        npdMapper.insertNpd(npd);
        final List<NpdRinci> list = npd.getNpdRinci();
        for (NpdRinci npdRinci : list) {
            npdRinci.setIdNpd(npd.getIdNpd());
            npdMapper.insertNpdRinci(npdRinci);
        }
        
    }

    @Override
    public Npd getNpdById(Integer idnpd) {
        return npdMapper.getNpdById(idnpd);
    }

    @Override
    public void updateNpd(Npd npd) {
     npdMapper.updateNpd(npd);
        final Integer idnpd = npd.getIdNpd();
        final List<NpdRinci> list = npd.getNpdRinci();
        npdMapper.deleteNpdRinci(idnpd);
        for (NpdRinci npdRinci : list) {
            npdRinci.setIdNpd(npd.getIdNpd());
            npdMapper.insertNpdRinci(npdRinci);
        }
        npdMapper.deleteNpdRinciIfNol();
    }

    @Override
    public void deleteNpd(Integer idNpd) {
    npdMapper.deleteNpdRinci(idNpd);
    npdMapper.deleteNpd(idNpd);
    }

}
