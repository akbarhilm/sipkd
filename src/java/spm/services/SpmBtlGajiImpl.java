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
import spm.entity.SpmBtlGajiMapper;
import spp.model.SpmBtlLs;
import spp.model.SpmBtlLsRinci;
import spm.entity.GenNumberMapper;
import spm.entity.SpmUpMapper;
/**
 *
 * @author Xalamaster
 */

@Transactional(readOnly = true)
@Service("SpmBtlGajiServices")
public class SpmBtlGajiImpl implements SpmBtlGajiServices {

    private static final Logger log = LoggerFactory.getLogger(SpmBtlGajiImpl.class);
    @Autowired
    private SpmBtlGajiMapper spmBtlGajiMapper;
    
    @Autowired
    private SpmUpMapper spmUpMapper;
    
    @Autowired
    private GenNumberMapper genNumberMapper;
    
    @Override
    public List<SpmBtlLs> getAllSpmBtlGaji(Map param) {
        return spmBtlGajiMapper.getAllSpmBtlGaji(param);
    }

    @Override
    public Integer getBanyakSpmBtlGaji(Map param) {
        return spmBtlGajiMapper.getBanyakSpmBtlGaji(param);

    }
    
    @Override
    public SpmBtlLs getSpmBtlGajiById(Integer param) {
        return spmBtlGajiMapper.getSpmBtlGajiById(param);
    }
    
    @Override
    public Integer getCekSpm(Integer param) {
        return spmBtlGajiMapper.getCekSpm(param);
    }
    
    @Override
    public Integer getCekPotongan(Integer param) {
        return spmBtlGajiMapper.getCekPotongan(param);
    }
    
    @Override
    public Integer getIdSpmByIdSpp(Integer param) {
        return spmBtlGajiMapper.getIdSpmByIdSpp(param);
    }
    
    @Override
    public BigDecimal getPotonganValidator(Integer param) {
        return spmBtlGajiMapper.getPotonganValidator(param);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertSpmBtlGaji(SpmBtlLs spmBtlLs) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("tahun", spmBtlLs.getTahun());
        map.put("idskpd", spmBtlLs.getSkpd().getIdSkpd());
        spmBtlLs.setNoSpm(genNumberMapper.getSpmNo(map));
        spmBtlGajiMapper.insertSpmBtlGaji(spmBtlLs);
        spmBtlGajiMapper.updateSimpegIdSpm(spmBtlLs);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateSpmBtlGaji(SpmBtlLs spmBtlLs) {
        spmBtlGajiMapper.updateSpmBtlGaji(spmBtlLs);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void deleteDataSpmByid(Integer param) {
        spmUpMapper.deleteSpmPotonganByid(param);
        spmUpMapper.deleteSpmByid(param);
        spmBtlGajiMapper.deleteSimpegIdSpm(param);
    }
    
    /*
    
    
    
    
        
    
    
    
    
    
    
    @Override
    public Map<String, BigDecimal> getTotalAnggaranDanSpd(Map<String, Object> param) {
        final Map<String, BigDecimal> hasil = new LinkedHashMap<String, BigDecimal>(2);
        BigDecimal totalanggup = spmBtlGajiMapper.getTotalPaguBySkpd(param);
        hasil.put("anggaran", totalanggup);
        try {
            hasil.put("spd", totalanggup.subtract(spmBtlGajiMapper.getTotalSPDBySKPDDanTahun(param)));
        } catch (Exception e) {
            hasil.put("spd", new BigDecimal(0));
        }
        return hasil;
    }
    
      
    
    @Override
    public List<SpmBtlLsRinci> getAllSpdBtl(Map param) {
        return spmBtlGajiMapper.getAllSpdBtl(param);
    }

    @Override
    public Integer getBanyakSpdBtl(Map param) {
        return spmBtlGajiMapper.getBanyakSpdBtl(param);

    }
    
    

   
*/

    
}
