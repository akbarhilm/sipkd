/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.entity;

import java.util.List;
import java.util.Map;
import spp.model.MonitoringSpd;
import spp.model.Skpd;

public interface MonitoringSpdMapper {

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

    List<MonitoringSpd> getPopupDetailSpdSahBiaya(Map param);
    
    Integer getCountPopupDetailSpdSahBiaya(Map param);

    List<MonitoringSpd> getPopupDetailSpdCetakBiaya(Map param);
    
    Integer getCountPopupDetailSpdCetakBiaya(Map param);
    
    List<MonitoringSpd> getPopupDetailSpdSpdBiaya(Map param);
    
    Integer getCountPopupDetailSpdSpdBiaya(Map param);
    
    //BL
    
    List<MonitoringSpd> getPopupDetailSpdSpdBl(Map param);
    
    Integer getCountPopupDetailSpdSpdBl(Map param);
    
    List<MonitoringSpd> getPopupDetailSpdCetakBl(Map param);
    
    Integer getCountPopupDetailSpdCetakBl(Map param);
    
    List<MonitoringSpd> getPopupDetailSpdSahSah(Map param);
    
    Integer getCountPopupDetailSpdSahSah(Map param);
    
    //BTL
      List<MonitoringSpd> getPopupDetailSpdSpdBtl(Map param);
    
    Integer getCountPopupDetailSpdSpdBtl(Map param);
    
    List<MonitoringSpd> getPopupDetailSpdCetakBtl(Map param);
    
    Integer getCountPopupDetailSpdCetakBtl(Map param);
    
    List<MonitoringSpd> getPopupDetailSpdSahBtl(Map param);
    
    Integer getCountPopupDetailSpdSahBtl(Map param);
    
     //Bantuan
      List<MonitoringSpd> getPopupDetailSpdSpdBantuan(Map param);
    
    Integer getCountPopupDetailSpdSpdBantuan(Map param);
    
    List<MonitoringSpd> getPopupDetailSpdCetakBantuan(Map param);
    
    Integer getCountPopupDetailSpdCetakBantuan(Map param);
    
    List<MonitoringSpd> getPopupDetailSpdSahBantuan(Map param);
    
    Integer getCountPopupDetailSpdSahBantuan(Map param);
    
}
