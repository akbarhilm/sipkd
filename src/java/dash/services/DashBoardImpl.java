package dash.services;

import dash.entity.DashBoardMapper;

//import ebkus.entity.ReqTokenMapper;
import dash.model.HistoriTransaksi;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zaenab
 */
@Service("dashBoardServices")
@Transactional(readOnly = true)
public class DashBoardImpl implements DashBoardServices {

    private static final Logger log = LoggerFactory.getLogger(DashBoardImpl.class);

    @Autowired
    private DashBoardMapper dashMapper;

//    @Autowired
//    private ReqTokenMapper reqTokenMapper;

    @Override
    public List<Map> getDataPenerimaan(Map<String, Object> param) {
        return dashMapper.getDataPenerimaan(param);
    }

    @Override
    public List<Map> getDataPengeluaran(Map<String, Object> param) {
        return dashMapper.getDataPengeluaran(param);
    }
    
    @Override
    public List<Map> getSaldoakhir() {
        return dashMapper.getSaldoakhir();
    }
    
    @Override
    public List<Date> getLastUpdate() {
        return dashMapper.getLastUpdate();
    }

   
}
