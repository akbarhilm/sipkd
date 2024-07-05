package spp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spp.entity.GenNumberMapper;
import spp.entity.SppGuMapper;
//import spp.model.NilaiDataGu;
import spp.model.SppGu;
import spp.model.SppGuRinci;

@Transactional(readOnly = true)
@Service("sppGuServices")
public class SppGuImpl implements SppGuServices {
    
    private static final Logger log = LoggerFactory.getLogger(SppGuImpl.class);
    @Autowired
    private SppGuMapper sppGuMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;
    
    @Override
    public List<SppGu> getAllSppGu(Map param) {
        return sppGuMapper.getAllSppGu(param);
    }
    
    @Override
    public Integer getBanyakSppGu(Map param) {
        return sppGuMapper.getBanyakSppGu(param);
    }
    
    @Override
    public List<SppGuRinci> getAllSpdBL(Map param) {
        return sppGuMapper.getAllSpdBL(param);
    }
    
    @Override
    public Integer getBanyakSpdBL(Map param) {
        return sppGuMapper.getBanyakSpdBL(param);
    }
    
    @Override
    public Map getBankRekByIdSkpd(Map param) {
        return sppGuMapper.getBankRekByIdSkpd(param);
    }
    
    @Override
    public Map getBankDki(Map param) {
        return sppGuMapper.getBankDki(param);
    }
    
    @Override
    public Map getBendaharaByIdSkpd(Map param) {
        return sppGuMapper.getBendaharaByIdSkpd(param);
    }
    
    @Override
    public Map getDataPagu(Map param) {
        return sppGuMapper.getDataPagu(param);
    }
    
    @Override
    public Map getDataPaguBKU(Map param) {
        return sppGuMapper.getDataPaguBKU(param);
    }

    /*@Override
     public Map<String, Object> getTotalPaguDanSpp(Map<String, Object> param) {
     final Map<String, Object> hasil = new LinkedHashMap<String, Object>(2);
     List<SppGu> total = sppGuMapper.getDataPagu(param);
     hasil.put("totalSpp", (BigDecimal) total);
     hasil.put("totalSpj", (BigDecimal) total);
     hasil.put("nilaiSisaPaguSpp", (BigDecimal) total);
     
     return hasil;
      
 
        

     }*/
    @Override
    @Transactional(readOnly = false)
    public void insertSppGu(SppGu sppGu) {
        final Map param = new HashMap();
        param.put("tahun", sppGu.getTahun());
        param.put("idskpd", sppGu.getSkpd().getIdSkpd());        
        sppGu.setNoSpp(genNumberMapper.getSppNo(param));
        log.debug(" sppGu " + sppGu.getNoSpp());
        sppGuMapper.insertSppGuMaster(sppGu);
        final List<SppGuRinci> list = sppGu.getSppGuRinci();
        for (SppGuRinci sppGuRinci : list) {
            sppGuRinci.setIdspp(sppGu.getId());
            sppGuMapper.insertSppGuRinci(sppGuRinci);
        }
        
    }
    
    @Override
    public SppGu getSppGuById(Integer idspp) {
        return sppGuMapper.getSppGuById(idspp);
    }
    
    @Override
    public List<SppGuRinci> getSppGuRinciByIdSpp(Integer idspp) {
        return sppGuMapper.getSppGuRinciByIdSpp(idspp);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateSppGu(SppGu sppGu) {
        sppGuMapper.updateSppGuMaster(sppGu);
        final Integer idspp = sppGu.getId();
        final List<SppGuRinci> list = sppGu.getSppGuRinci();
        final SppGuRinci spppar = list.get(0);
        sppGuMapper.deleteSppGuMaster(spppar);
        for (SppGuRinci sppGuRinci : list) {
            sppGuRinci.setIdspp(idspp);
            if (sppGuRinci.getNilaiSpp().doubleValue() > 0.0) {
                sppGuMapper.insertSppGuRinci(sppGuRinci);
            }
        }
        
    }
    
    @Override
    public Map getTotalSPDDanSPP(Map param) {
        return sppGuMapper.getTotalSPDDanSPP(param);
    }
    
    @Override
    public Integer getStatusGU(Map param) {
        return sppGuMapper.getStatusGU(param);
    }
    
    @Override
    public Integer getBanyakGU(Map param) {
        return sppGuMapper.getBanyakGU(param);
    }
    
    
}
