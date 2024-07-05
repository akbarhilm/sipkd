/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.services;

import java.util.List;
import java.util.Map;
import spp.model.MonitoringSpd;
import spp.model.Skpd;

public interface MonitoringSpdServices {

    List<MonitoringSpd> getDataSpdBantuan(Map param);

    Integer getCountSpdBantuan(Map param);

    List<MonitoringSpd> getDataSpdBl(Map param);

    Integer getCountSpdBl(Map param);

    List<MonitoringSpd> getSkpdKoor(Map param);

    Integer getCountSkpdKoor(Map param);
    
    List<MonitoringSpd> getDataSpdBiaya(Map param);

    Integer getCountSpdBiaya(Map param);

    List<MonitoringSpd> getSkpdBl(Map param);

    Integer getCountSkpdBl(Map param);
    
    Skpd getSkpdBiayaById();
    
    List<MonitoringSpd> getDataSpdBtl(Map param);

    Integer getCountSpdBtl(Map param);

    List<MonitoringSpd> getSkpdPopupBtl(Map param);

    Integer getCountPopupSkpdBtl(Map param);
    
    List<MonitoringSpd> getSkpdPopupDetailSpd(Map param);

    Integer getCountPopupDetailSpd(Map param);
    
    List<MonitoringSpd> getSpdBiayaSah(Map param);

    Integer getCountSpdBiayaSah(Map param);
    
    List<MonitoringSpd> getSpdBiayaCetak(Map param);

    Integer getCountSpdBiayaCetak(Map param);
    
    List<MonitoringSpd> getSpdBiayaSpd(Map param);

    Integer getCountSpdBiayaSpd(Map param);
      //BL
    List<MonitoringSpd> getSpdBlSpd(Map param);

    Integer getCountSpdBlSpd(Map param);
    
    List<MonitoringSpd> getSpdBlCetak(Map param);

    Integer getCountSpdBlCetak(Map param);
    
    List<MonitoringSpd> getSpdBlSah(Map param);

    Integer getCountSpdBlSah(Map param);
    
       //BL
    List<MonitoringSpd> getSpdBtlSpd(Map param);

    Integer getCountSpdBtlSpd(Map param);
    
    List<MonitoringSpd> getSpdBtlCetak(Map param);

    Integer getCountSpdBtlCetak(Map param);
    
    List<MonitoringSpd> getSpdBtlSah(Map param);

    Integer getCountSpdBtlSah(Map param);
    
          //Bantuan
    List<MonitoringSpd> getSpdBantuanSpd(Map param);

    Integer getCountSpdBantuanSpd(Map param);
    
    List<MonitoringSpd> getSpdBantuanCetak(Map param);

    Integer getCountSpdBantuanCetak(Map param);
    
    List<MonitoringSpd> getSpdBantuanSah(Map param);

    Integer getCountSpdBantuanSah(Map param);
}
