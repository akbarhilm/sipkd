/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2d.services;

import java.util.List;
import java.util.Map;
import spp.model.Histori;


public interface HistoriTransaksiServices {

    Histori getRekening(Integer id);

    List<Map> getSaldoAkhirall(Map<String, Object> param);

    void getMutasiAll(Map<String, Object> param);

    List<Histori> getTransaksiall(Map<String, Object> param);
    
    Integer getBanyakTransaksiall(Map<String, Object> param);
        
     Integer getBanyakListXls(Map<String, Object> param);

    List<Map> getListXls(Map<String, Object> param);
}
