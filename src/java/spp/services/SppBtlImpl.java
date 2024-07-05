package spp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spp.entity.SppBtlMapper;
import spp.model.SppBtl;
import spp.model.SppBtlRinci;
import spp.entity.GenNumberMapper;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("sppBtlServices")
public class SppBtlImpl implements SppBtlServices {
    
    private static final Logger log = LoggerFactory.getLogger(SppBtlImpl.class);
    @Autowired
    private SppBtlMapper sppBtlMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;
    private SppBtl sppBtl;
    
    @Override
    public List<SppBtl> getAllSppBtl(Map param) {
        return sppBtlMapper.getAllSppBtl(param);
    }
    
    @Override
    public Integer getBanyakSppBtl(Map param) {
        return sppBtlMapper.getBanyakSppBtl(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void deleteSppBtlMaster(Integer id) {
        sppBtlMapper.deleteSppBtlMaster(id);
        sppBtlMapper.deleteSppBtlRinci(id);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void updateSppBtl(SppBtl sppBtl) {
        sppBtlMapper.updateSppBtlMaster(sppBtl);
        final Integer idspp = sppBtl.getId();
        final List<SppBtlRinci> list = sppBtl.getSppBtlRinci();
        // sppBtlMapper.deleteSppBtlMaster(idspp);
        for (SppBtlRinci sppBtlRinci : list) {
            sppBtlRinci.setIdspp(idspp);
            sppBtlMapper.deleteSppBtlMasterByIdSppIdSpdIdBtlIdAkun(sppBtlRinci);
            sppBtlMapper.insertSppBtlRinci(sppBtlRinci);
        }
        
    }
    
     @Override
    public void updateSppBtt(SppBtl sppBtl) {
        sppBtlMapper.updateSppBtt(sppBtl);
        
        
    }
    
     @Override
    public void updateSppBxx(SppBtl sppBtl) {
        sppBtlMapper.updateSppBxx(sppBtl);
        
        
    }
    
    @Override
    public List<SppBtlRinci> getAllSpdBtl(Map param) {
        return sppBtlMapper.getAllSpdBtl(param);
    }
    
    @Override
    public Integer getBanyakSpdBtl(Map param) {
        return sppBtlMapper.getBanyakSpdBtl(param);
        
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertSppBtl(SppBtl sppBtl) {
        final Map param = new HashMap();
        param.put("tahun", sppBtl.getTahun());
        param.put("idskpd", sppBtl.getSkpd().getIdSkpd());        
        sppBtl.setNoSpp(genNumberMapper.getSppNo(param));
        sppBtlMapper.insertSppBtlMaster(sppBtl);
        final List<SppBtlRinci> list = sppBtl.getSppBtlRinci();
        for (SppBtlRinci sppBtlRinci : list) {
            sppBtlRinci.setIdspp(sppBtl.getId());
            sppBtlMapper.insertSppBtlRinci(sppBtlRinci);
        }
        
    }
    
    @Override
    public SppBtl getSppBtlById(Integer idspp) {
        return sppBtlMapper.getSppBtlById(idspp);
    }
    
    @Override
    public List<SppBtlRinci> getSppBtlRinciByIdSpp(Integer idspp) {
        return sppBtlMapper.getSppBtlRinciByIdSpp(idspp);
        
    }
    
    @Override
    public Map getBankRekByIdSkpd(Map param) {
        return sppBtlMapper.getBankRekByIdSkpd(param);
    }
    
    @Override
    public Map getBankDki(Map param) {
        return sppBtlMapper.getBankDki(param);
    }
    
    @Override
    public Map getBendaharaByIdSkpd(Map param) {
        return sppBtlMapper.getBendaharaByIdSkpd(param);
    }
    
    @Override
    public Map getBendaharaByIdSkpd1(Map param) {
        return sppBtlMapper.getBendaharaByIdSkpd1(param);
    }
    
    @Override
    public Integer getbanyaksppbtl3(Map map) {
        return sppBtlMapper.getbanyaksppbtl3(map);
    }
}
