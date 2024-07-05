/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dash.entity;

import dash.model.HistoriTransaksi;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 *
 * @author Zainab
 */
public interface DashBoardMapper {

    List<Map> getDataPenerimaan(Map<String, Object> param);

    List<Map> getDataPengeluaran(Map<String, Object> param);

    List<Map> getSaldoakhir();
    
    List<Date> getLastUpdate();

}
