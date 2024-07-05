
package spm.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import spp.entity.GenNumberMapper;
import spm.entity.SpmBtlBantuanMapper;
import spp.model.SpmBtlBantuan;
import spp.model.SpmBtlBantuanRinci;
import spm.entity.GenNumberMapper;
/**
 *
 * @author Xalamaster
 */

@Transactional(readOnly = true)
@Service("spmBtlBantuanServices")
public class SpmBtlBantuanImpl implements SpmBtlBantuanServices {

    private static final Logger log = LoggerFactory.getLogger(SpmBtlBantuanImpl.class);
    @Autowired
    private SpmBtlBantuanMapper spmBtlBantuanMapper;
    
    @Autowired
    private GenNumberMapper genNumberMapper;
    
    @Override
    public List<SpmBtlBantuan> getAllSpmBtlBantuan(Map param) {
        return spmBtlBantuanMapper.getAllSpmBtlBantuan(param);
    }

    @Override
    public Integer getBanyakSpmBtlBantuan(Map param) {
        return spmBtlBantuanMapper.getBanyakSpmBtlBantuan(param);

    }

     @Override
    public SpmBtlBantuan getSpmBtlBantuanById(Map param) {
        return spmBtlBantuanMapper.getSpmBtlBantuanById(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateSpmBtlBantuanMaster(SpmBtlBantuan spmBtlBantuan) {
        spmBtlBantuanMapper.updateSpmBtlBantuanMaster(spmBtlBantuan);
    }  
    
    @Override
    public List<SpmBtlBantuanRinci> getAllSpdBtl(Map param) {
        return spmBtlBantuanMapper.getAllSpdBtl(param);
    }

    @Override
    public Integer getBanyakSpdBtl(Map param) {
        return spmBtlBantuanMapper.getBanyakSpdBtl(param);

    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertSpmBtlBantuanMaster(SpmBtlBantuan spmBtlBantuan) {
        //#{tahun} AND i_idskpd =  #{idskpd}
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("tahun", spmBtlBantuan.getTahun());
        map.put("idskpd", spmBtlBantuan.getIdskpd());
        spmBtlBantuan.setNoSpm(genNumberMapper.getSpmNo(map));
        spmBtlBantuanMapper.insertSpmBtlBantuanMaster(spmBtlBantuan);
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

}
