package spm.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spm.entity.GenNumberMapper;
import spm.entity.SpmUpMapper;
import spp.model.SpmUp;
import spp.model.SppUpRinci;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("spmUpServices")
public class SpmUpImpl implements SpmUpServices {
    
    private static final Logger log = LoggerFactory.getLogger(SpmUpImpl.class);
    @Autowired
    private SpmUpMapper spmUpMapper;
    
    @Autowired
    private GenNumberMapper genNumberMapper;
    
    @Override
    public List<SpmUp> getAllSpmUp(Map param) {
        return spmUpMapper.getAllSpmUp(param);
    }
    
    @Override
    public Integer getBanyakSpmUp(Map param) {
        return spmUpMapper.getBanyakSpmUp(param);
        
    }
    
    @Override
    public SpmUp getSpmUPById(Integer param) {
        return spmUpMapper.getSpmUPById(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertSpmUpMaster(SpmUp spmUp) {
        //#{tahun} AND i_idskpd =  #{idskpd}
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tahun", spmUp.getTahun());
        map.put("idskpd", spmUp.getSkpd().getIdSkpd());
        spmUp.setNoSpm(genNumberMapper.getSpmNo(map));
        spmUpMapper.insertSpmUpMaster(spmUp);
        /* final List<SpmUpRinci> spmUpRinci = spmUp.getSpmUpRinci();
         final Integer idspm = spmUp.getIdspm();
         final Integer idspp = spmUp.getId();
         log.debug(" ################ idspm " + idspm);
         for (SpmUpRinci spmUpRinci1 : spmUpRinci) {
         spmUpRinci1.setIdspm(idspm);
         spmUpRinci1.setIdspp(idspp);
         spmUpMapper.insertSpmUpRinci(spmUpRinci1);
         }*/
        
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateSpmUpMaster(SpmUp spmUp) {
        spmUpMapper.updateSpmUpMaster(spmUp);
    }
    
    @Override
    public List<SppUpRinci> getAllSpdBLSPM(Map param) {
        return spmUpMapper.getAllSpdBLSPM(param);
    }
    
    @Override
    public Integer getBanyakSpdBLSPM(Map param) {
        return spmUpMapper.getBanyakSpdBLSPM(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void deleteDataSpmByid(Integer param) {
        spmUpMapper.deleteSpmPotonganByid(param);
        spmUpMapper.deleteSpmByid(param);
    }
    
}
