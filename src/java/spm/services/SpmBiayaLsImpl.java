/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import spm.entity.SpmBiayaLsMapper;
import spp.model.SpmBiayaLs;
import spp.model.SpmBiayaLsRinci;
import spm.entity.GenNumberMapper;
/**
 *
 * @author Xalamaster
 */

@Transactional(readOnly = true)
@Service("SpmBiayaLsServices")
public class SpmBiayaLsImpl implements SpmBiayaLsServices {

    private static final Logger log = LoggerFactory.getLogger(SpmBiayaLsImpl.class);
    @Autowired
    private SpmBiayaLsMapper spmBiayaLsMapper;
    
    @Autowired
    private GenNumberMapper genNumberMapper;
    
    @Override
    public List<SpmBiayaLs> getAllSpmBiayaLs(Map param) {
        return spmBiayaLsMapper.getAllSpmBiayaLs(param);
    }

    @Override
    public Integer getBanyakSpmBiayaLs(Map param) {
        return spmBiayaLsMapper.getBanyakSpmBiayaLs(param);

    }
    
     @Override
    public SpmBiayaLs getSpmBiayaLsById(Integer param) {
        return spmBiayaLsMapper.getSpmBiayaLsById(param);
    }
    
    
    @Override
    public Map<String, BigDecimal> getTotalAnggaranDanSpd(Map<String, Object> param) {
        final Map<String, BigDecimal> hasil = new LinkedHashMap<String, BigDecimal>(2);
        BigDecimal totalanggup = spmBiayaLsMapper.getTotalPaguBySkpd(param);
        hasil.put("anggaran", totalanggup);
        try {
            hasil.put("spd", totalanggup.subtract(spmBiayaLsMapper.getTotalSPDBySKPDDanTahun(param)));
        } catch (Exception e) {
            hasil.put("spd", new BigDecimal(0));
        }
        return hasil;
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateSpmBiayaLsMaster(SpmBiayaLs spmBiayaLs) {
        spmBiayaLsMapper.updateSpmBiayaLsMaster(spmBiayaLs);
    }  
    
    @Override
    public List<SpmBiayaLsRinci> getAllSpdBiaya(Map param) {
        return spmBiayaLsMapper.getAllSpdBiaya(param);
    }

    @Override
    public Integer getBanyakSpdBiaya(Map param) {
        return spmBiayaLsMapper.getBanyakSpdBiaya(param);

    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertSpmBiayaLsMaster(SpmBiayaLs spmBiayaLs) {
        //#{tahun} AND i_idskpd =  #{idskpd}
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("tahun", spmBiayaLs.getTahun());
        map.put("idskpd", spmBiayaLs.getSkpd().getIdSkpd());
        spmBiayaLs.setNoSpm(genNumberMapper.getSpmNo(map));
        spmBiayaLsMapper.insertSpmBiayaLsMaster(spmBiayaLs);
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
/*
   
*/
}
