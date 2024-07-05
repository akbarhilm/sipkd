package spm.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spm.entity.SppBiayaMapper;
import spp.model.SppBiaya;
import spp.model.SppBiayaRinci;
import spm.entity.GenNumberMapper;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("sppBiayaServices")
public class SppBiayaImpl implements SppBiayaServices {

    private static final Logger log = LoggerFactory.getLogger(SppBiayaImpl.class);
    @Autowired
    private SppBiayaMapper sppBiayaMapper;
     @Autowired
    private GenNumberMapper genNumberMapper;
    private SppBiaya sppBiaya;

    @Override
    public List<SppBiaya> getAllSppBiaya(Map param) {
        return sppBiayaMapper.getAllSppBiaya(param);
    }

    @Override
    public Integer getBanyakSppBiaya(Map param) {
        return sppBiayaMapper.getBanyakSppBiaya(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void deleteSppBiayaMaster(Integer id) {
        sppBiayaMapper.deleteSppBiayaMaster(id);
        sppBiayaMapper.deleteSppBiayaRinci(id);
    }

    
    @Transactional(readOnly = false)
    @Override
    public void updateSppBiaya(SppBiaya sppBiaya) {
        sppBiayaMapper.updateSppBiayaMaster(sppBiaya);
        final Integer idspp = sppBiaya.getId();
        final List<SppBiayaRinci> list = sppBiaya.getSppBiayaRinci();
        sppBiayaMapper.deleteSppBiayaMaster(idspp);
        for (SppBiayaRinci sppBiayaRinci : list) {
            sppBiayaRinci.setIdspp(idspp);
            sppBiayaMapper.insertSppBiayaRinci(sppBiayaRinci);
        }

    }

    @Override
    public List<SppBiayaRinci> getAllSpdBiaya(Map param) {
        return sppBiayaMapper.getAllSpdBiaya(param);
    }

    @Override
    public Integer getBanyakSpdBiaya(Map param) {
        return sppBiayaMapper.getBanyakSpdBiaya(param);
        
    }

   @Override
    @Transactional(readOnly = false)
    public void insertSppBiaya(SppBiaya sppBiaya) {
        final Map param = new HashMap();
        param.put("tahun", sppBiaya.getTahun());
        param.put("idskpd", sppBiaya.getSkpd().getIdSkpd());        
        sppBiaya.setNoSpp(genNumberMapper.getSppNo(param));
        sppBiayaMapper.insertSppBiayaMaster(sppBiaya);
        final List<SppBiayaRinci> list = sppBiaya.getSppBiayaRinci();
        for (SppBiayaRinci sppBiayaRinci : list) {
            sppBiayaRinci.setIdspp(sppBiaya.getId());
            sppBiayaMapper.insertSppBiayaRinci(sppBiayaRinci);
        }
        
    }

    @Override
    public SppBiaya getSppBiayaById(Integer idspp) {
        return sppBiayaMapper.getSppBiayaById(idspp);
    }

    @Override
    public List<SppBiayaRinci> getSppBiayaRinciByIdSpp(Integer idspp) {
        return sppBiayaMapper.getSppBiayaRinciByIdSpp(idspp);

   
}
    
     @Override
    public Map getBankRekByIdSkpd(Map param) {
        return sppBiayaMapper.getBankRekByIdSkpd(param);
    }
    
     @Override
    public Map getBendaharaByIdSkpd(Map param) {
        return sppBiayaMapper.getBendaharaByIdSkpd(param);
    }
}
   
    
    
    
    
    
    
    
    
    
    

    


