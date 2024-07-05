package spp.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spp.entity.SppBtlGajiMapper;
import spp.model.SppBtl;
import spp.model.SppBtlGaji;
import spp.model.SppBtlRinci;
import spp.entity.GenNumberMapper;

/**
 *
 * @author Zainab
 */
@Transactional(readOnly = true)
@Service("sppBtlGajiServices")
public class SppBtlGajiImpl implements SppBtlGajiServices {
    
    private static final Logger log = LoggerFactory.getLogger(SppBtlGajiImpl.class);
    @Autowired
    private SppBtlGajiMapper sppBtlMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;
    private SppBtl sppBtl;
    
    
    @Transactional(readOnly = false)
    @Override
    public List<SppBtlGaji> getListGaji(Map param) {
        sppBtlMapper.deletePegRekap1(param);
        return sppBtlMapper.getListGaji(param);
    }
    
    @Override
    public Integer getBanyakListGaji(Map param) {
        return sppBtlMapper.getBanyakListGaji(param);
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
    @Transactional(readOnly = false)
    public void insertSppBtl(SppBtl sppBtl) {
        final Map param = new HashMap();
        param.put("tahun", sppBtl.getTahun());
        param.put("idskpd", sppBtl.getSkpd().getIdSkpd());        
        sppBtl.setNoSpp(genNumberMapper.getSppNo(param));
        sppBtlMapper.insertSppBtlMaster(sppBtl);
        sppBtlMapper.updateSimpeg(sppBtl);
        final List<SppBtlRinci> list = sppBtl.getSppBtlRinci();
        
        for (SppBtlRinci sppBtlRinci : list) {
            sppBtlRinci.setIdspp(sppBtl.getId());
            if(sppBtlRinci.getNilaiSpp().compareTo(BigDecimal.ZERO) == 1)
            sppBtlMapper.insertSppBtlRinci(sppBtlRinci);
        }
        
    }
    
    @Override
    public List<SppBtl> getAllSppBtl(Map param) {
        return sppBtlMapper.getAllSppBtl(param);
    }
    
    @Override
    public Integer getBanyakSppBtl(Map param) {
        return sppBtlMapper.getBanyakSppBtl(param);
    }
    
    @Override
    public List<SppBtlRinci> getAllRinciBtl(Map param) {
        return sppBtlMapper.getAllRinciBtl(param);
    }
    
    @Override
    public Integer getBanyakRinciBtl(Map param) {
        return sppBtlMapper.getBanyakRinciBtl(param);
        
    }
    
    @Override
    public List<Map<String, Object>> getNilaiSPP(Integer id, Integer kdsimpeg) {
        if(kdsimpeg == 2){
            return sppBtlMapper.getNilaiSPPTkd(id);
        }
        else if(kdsimpeg == 3){
            return sppBtlMapper.getNilaiSPPTransport(id);
        }
        else if(kdsimpeg == 4){
            return sppBtlMapper.getNilaiSPPPph(id);
        }
        else {
            return sppBtlMapper.getNilaiSPPGaji(id);
        }
        
    }
    
    @Override
    public SppBtl getSppBtlById(Integer idspp) {
        return sppBtlMapper.getSppBtlById(idspp);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void updateSppBtl(SppBtl sppBtl) {
        sppBtlMapper.updateSppBtlMaster(sppBtl);
        final Integer idspp = sppBtl.getId();
        final List<SppBtlRinci> list = sppBtl.getSppBtlRinci();
        // sppBtlMapper.deleteSppBtlMaster(idspp);
        sppBtlMapper.deleteSimpeg(idspp);
        sppBtlMapper.updateSimpeg(sppBtl);
        sppBtlMapper.deleteSppBtlRinci(idspp);
        for (SppBtlRinci sppBtlRinci : list) {
            sppBtlRinci.setIdspp(idspp);
            //sppBtlMapper.deleteSppBtlMasterByIdSppIdSpdIdBtlIdAkun(sppBtlRinci);
            sppBtlMapper.insertSppBtlRinci(sppBtlRinci);
        }
        
    }
    
    @Transactional(readOnly = false)
    @Override
    public void deleteSppBtl(Integer id) {
        sppBtlMapper.deleteSppBtlMaster(id);
        sppBtlMapper.deleteSimpeg(id);
        sppBtlMapper.deleteSppBtlRinci(id);
    }
    
    
    @Transactional(readOnly = false)
    @Override
    public Integer insertPegRekap(Map param) {
//        sppBtlMapper.deletePegRekap();
        Integer idPegRekap = sppBtlMapper.getIdPegRekap();
        param.put("id", idPegRekap);
        
        sppBtlMapper.insertPegRekap(param);
        //log.debug("ID ===== "+param.get("id").toString());
        return idPegRekap;
    }

    @Override
    public List<SppBtlGaji> getBulan(Map param) {
        return sppBtlMapper.getBulan(param);
    }

    

    
}
