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
import spm.entity.SpmBtlLsMapper;
import spm.entity.SpmRestitusiMapper;
import spp.model.SpmBtlLs;
import spp.model.SpmBtlLsRinci;
import spm.entity.GenNumberMapper;
/**
 *
 * @author Xalamaster
 */

@Transactional(readOnly = true)
@Service("SpmRestitusiServices")
public class SpmRestitusiImpl implements SpmRestitusiServices {

    private static final Logger log = LoggerFactory.getLogger(SpmRestitusiImpl.class);
    @Autowired
    private SpmRestitusiMapper spmRestitusiMapper;
    
    @Autowired
    private GenNumberMapper genNumberMapper;
    
    @Override
    public List<SpmBtlLs> getAllSpmRes(Map param) {
        return spmRestitusiMapper.getAllSpmRes(param);
    }

    @Override
    public Integer getBanyakSpmRes(Map param) {
        return spmRestitusiMapper.getBanyakSpmRes(param);

    }
    
     @Override
    public SpmBtlLs getSpmResById(Integer param) {
        return spmRestitusiMapper.getSpmResById(param);
    }
    
     @Override
    public BigDecimal getPotonganValidator(Integer param) {
        return spmRestitusiMapper.getPotonganValidator(param);
    }
        
     @Override
    public Integer getIdSpmByIdSpp(Integer param) {
        return spmRestitusiMapper.getIdSpmByIdSpp(param);
    }
    
     @Override
    public Integer getCekSpm(Integer param) {
        return spmRestitusiMapper.getCekSpm(param);
    }
    
    
    
    @Override
    public Map<String, BigDecimal> getTotalAnggaranDanSpd(Map<String, Object> param) {
        final Map<String, BigDecimal> hasil = new LinkedHashMap<String, BigDecimal>(2);
        BigDecimal totalanggup = spmRestitusiMapper.getTotalPaguBySkpd(param);
        hasil.put("anggaran", totalanggup);
        try {
            hasil.put("spd", totalanggup.subtract(spmRestitusiMapper.getTotalSPDBySKPDDanTahun(param)));
        } catch (Exception e) {
            hasil.put("spd", new BigDecimal(0));
        }
        return hasil;
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateSpmResMaster(SpmBtlLs spmBtlLs) {
        spmRestitusiMapper.updateSpmResMaster(spmBtlLs);
    }  
    
    @Override
    public List<SpmBtlLsRinci> getAllSpdBtl(Map param) {
        return spmRestitusiMapper.getAllSpdBtl(param);
    }

    @Override
    public Integer getBanyakSpdBtl(Map param) {
        return spmRestitusiMapper.getBanyakSpdBtl(param);

    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertSpmResMaster(SpmBtlLs spmBtlLs) {
        //#{tahun} AND i_idskpd =  #{idskpd}
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("tahun", spmBtlLs.getTahun());
        map.put("idskpd", spmBtlLs.getSkpd().getIdSkpd());
        spmBtlLs.setNoSpm(genNumberMapper.getSpmNo(map));
        spmRestitusiMapper.insertSpmResMaster(spmBtlLs);
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
