/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2d.services;

import java.math.BigDecimal;
import java.sql.Date;
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
import sp2d.entity.GenNumberMapper;
import sp2d.entity.HistoriTransaksiMapper;
import spp.model.HariKerja;
import spp.model.Skpd;
import spp.model.Histori;

@Transactional(readOnly = true)
@Service("historiTransaksiServices")
public class HistoriTransaksiImpl implements HistoriTransaksiServices {

    private static final Logger log = LoggerFactory.getLogger(HistoriTransaksiImpl.class);
    @Autowired
    private HistoriTransaksiMapper histmapper;
   
    @Override
    public Histori getRekening(Integer id) {
        return histmapper.getRekening(id);
    }

    @Override
    public  List<Map> getSaldoAkhirall(Map<String, Object> param) {
        return histmapper.getSaldoAkhirall(param);
    }
    
     @Override
    public  void getMutasiAll(Map<String, Object> param) {
         histmapper.getMutasiAll(param);
    }
    
      @Override
    public  List<Histori> getTransaksiall(Map<String, Object> param) {
        return histmapper.getTransaksiall(param);
    }
    
     @Override
    public  Integer getBanyakTransaksiall(Map<String, Object> param) {
        return histmapper.getBanyakTransaksiall(param);
    }
    
     @Override
    public  Integer getBanyakListXls(Map<String, Object> param) {
        return histmapper.getBanyakListXls(param);
        
    }
    @Override
    public  List<Map> getListXls(Map<String, Object> param) {
        return histmapper.getListXls(param);
    }
   
}
