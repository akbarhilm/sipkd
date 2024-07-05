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
import spp.entity.GenNumberMapper;
import spp.entity.MonitoringSpdMapper;
import spp.entity.SppTuMapper;
import spp.model.MonitoringSpd;
import spp.model.MonitoringSpd;
import spp.model.Skpd;
import spp.model.SppTu;
import spp.model.SppTuRinci;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("monitoringSpdServices")
public class MonitoringSpdImpl implements MonitoringSpdServices {

    private static final Logger log = LoggerFactory.getLogger(MonitoringSpdImpl.class);
    @Autowired
    private MonitoringSpdMapper monitoringSpdMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<MonitoringSpd> getDataSpdBantuan(Map param) {
        return monitoringSpdMapper.getDataSpdBantuan(param);
    }

    @Override
    public Integer getCountSpdBantuan(Map param) {
        return monitoringSpdMapper.getCountSpdBantuan(param);
    }

    @Override
    public List<MonitoringSpd> getSkpdKoor(Map param) {
        return monitoringSpdMapper.getSkpdKoor(param);
    }

    @Override
    public Integer getCountSkpdKoor(Map param) {
        return monitoringSpdMapper.getCountSkpdKoor(param);
    }

    @Override
    public List<MonitoringSpd> getDataSpdBiaya(Map param) {
        return monitoringSpdMapper.getDataSpdBiaya(param);
    }

    @Override
    public Integer getCountSpdBiaya(Map param) {
        return monitoringSpdMapper.getCountSpdBiaya(param);
    }

    @Override
    public List<MonitoringSpd> getSkpdBl(Map param) {
        return monitoringSpdMapper.getSkpdBl(param);
    }

    @Override
    public Integer getCountSkpdBl(Map param) {
        return monitoringSpdMapper.getCountSkpdBl(param);
    }

    @Override
    public List<MonitoringSpd> getDataSpdBl(Map param) {
        return monitoringSpdMapper.getDataSpdBl(param);
    }

    @Override
    public Integer getCountSpdBl(Map param) {
        return monitoringSpdMapper.getCountSpdBl(param);
    }

    @Override
    public Skpd getSkpdBiayaById() {
        return monitoringSpdMapper.getSkpdBiayaById();
    }

    @Override
    public List<MonitoringSpd> getDataSpdBtl(Map param) {
        return monitoringSpdMapper.getDataSpdBtl(param);
    }

    @Override
    public Integer getCountSpdBtl(Map param) {
        return monitoringSpdMapper.getCountSpdBtl(param);
    }

    @Override
    public List<MonitoringSpd> getSkpdPopupBtl(Map param) {
        return monitoringSpdMapper.getSkpdPopupBtl(param);
    }

    @Override
    public Integer getCountPopupSkpdBtl(Map param) {
        return monitoringSpdMapper.getCountPopupSkpdBtl(param);
    }
    /*
     1 = BTL
     2 = BL
     3 = BIAYA
     4 = BANTUAN
     */

    @Override
    public List<MonitoringSpd> getSpdBiayaSah(Map param) {
        return monitoringSpdMapper.getPopupDetailSpdSahBiaya(param); 
                }
    @Override
    public Integer getCountSpdBiayaSah(Map param) {
       return monitoringSpdMapper.getCountPopupDetailSpdSahBiaya(param);
    }

    @Override
    public List<MonitoringSpd> getSkpdPopupDetailSpd(Map param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getCountPopupDetailSpd(Map param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MonitoringSpd> getSpdBiayaCetak(Map param) {
        return monitoringSpdMapper.getPopupDetailSpdCetakBiaya(param); 
    }

    @Override
    public Integer getCountSpdBiayaCetak(Map param) {
        return monitoringSpdMapper.getCountPopupDetailSpdCetakBiaya(param);
    }

    @Override
    public List<MonitoringSpd> getSpdBiayaSpd(Map param) {
        return monitoringSpdMapper.getPopupDetailSpdSpdBiaya(param); 
    }

    @Override
    public Integer getCountSpdBiayaSpd(Map param) {
        return monitoringSpdMapper.getCountPopupDetailSpdSpdBiaya(param);
    }

    //BL
    
    @Override
    public List<MonitoringSpd> getSpdBlSpd(Map param) {
        return monitoringSpdMapper.getPopupDetailSpdSpdBl(param); 
    }

    @Override
    public Integer getCountSpdBlSpd(Map param) {
        return monitoringSpdMapper.getCountPopupDetailSpdSpdBl(param);
    }

    @Override
    public List<MonitoringSpd> getSpdBlCetak(Map param) {
        return monitoringSpdMapper.getPopupDetailSpdCetakBl(param); 
    }

    @Override
    public Integer getCountSpdBlCetak(Map param) {
        return monitoringSpdMapper.getCountPopupDetailSpdCetakBl(param);
    }

    @Override
    public List<MonitoringSpd> getSpdBlSah(Map param) {
        return monitoringSpdMapper.getPopupDetailSpdSahSah(param); 
    }

    @Override
    public Integer getCountSpdBlSah(Map param) {
        return monitoringSpdMapper.getCountPopupDetailSpdSahSah(param);
    }

     //BTL
    
    @Override
    public List<MonitoringSpd> getSpdBtlSpd(Map param) {
        return monitoringSpdMapper.getPopupDetailSpdSpdBtl(param); 
    }

    @Override
    public Integer getCountSpdBtlSpd(Map param) {
        return monitoringSpdMapper.getCountPopupDetailSpdSpdBtl(param);
    }

    @Override
    public List<MonitoringSpd> getSpdBtlCetak(Map param) {
        return monitoringSpdMapper.getPopupDetailSpdCetakBtl(param); 
    }

    @Override
    public Integer getCountSpdBtlCetak(Map param) {
        return monitoringSpdMapper.getCountPopupDetailSpdCetakBtl(param);
    }

    @Override
    public List<MonitoringSpd> getSpdBtlSah(Map param) {
        return monitoringSpdMapper.getPopupDetailSpdSahBtl(param); 
    }

    @Override
    public Integer getCountSpdBtlSah(Map param) {
        return monitoringSpdMapper.getCountPopupDetailSpdSahBtl(param);
    }
    
    
     //Bantuan
    
    @Override
    public List<MonitoringSpd> getSpdBantuanSpd(Map param) {
        return monitoringSpdMapper.getPopupDetailSpdSpdBantuan(param); 
    }

    @Override
    public Integer getCountSpdBantuanSpd(Map param) {
        return monitoringSpdMapper.getCountPopupDetailSpdSpdBantuan(param);
    }

    @Override
    public List<MonitoringSpd> getSpdBantuanCetak(Map param) {
        return monitoringSpdMapper.getPopupDetailSpdCetakBantuan(param); 
    }

    @Override
    public Integer getCountSpdBantuanCetak(Map param) {
        return monitoringSpdMapper.getCountPopupDetailSpdCetakBantuan(param);
    }

    @Override
    public List<MonitoringSpd> getSpdBantuanSah(Map param) {
        return monitoringSpdMapper.getPopupDetailSpdSahBantuan(param); 
    }

    @Override
    public Integer getCountSpdBantuanSah(Map param) {
        return monitoringSpdMapper.getCountPopupDetailSpdSahBantuan(param);
    }
}
