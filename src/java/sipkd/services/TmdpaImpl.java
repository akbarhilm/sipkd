package sipkd.services;

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
//import spp.entity.SpdBlMapper; 
import sipkd.model.Tmdpa;
import sipkd.entity.TmdpaMapper;

/**
 *
 * @author Xalamaster
 */
@Transactional(readOnly = true)
@Service("tmdpaServices")
public class TmdpaImpl implements TmdpaServices {
    //private static final Logger log = LoggerFactory.getLogger(TmdpaImpl.class);
    @Autowired
    private TmdpaMapper tmdpaMapper;
   
    @Override
    public Map<String, Object> getTmdpa(Map<String, Object> param){
       //final Map<String, Object> hasil = new LinkedHashMap<String, Object>(2);
        //hasil.put("");
       // hasil.put("idTmdpa", tmdpaMapper.getTmdpa(param));
       // hasil.put("namaPA", tmdpaMapper.getTmdpa(param));
        
        //return hasil;
       return tmdpaMapper.getTmdpa(param);
    }
    
    @Override
    public List<Tmdpa> getAllTmdpa(Map<String, Object> param) {
        return tmdpaMapper.getAllTmdpa(param);
    }
 
    @Override
    public Integer getBanyakSkpd(Map<String, Object> param) {
        return tmdpaMapper.getBanyakSkpd(param);
    }
    @Override
    @Transactional(readOnly = false)
    public void updateTmdpa(Tmdpa param) {
        tmdpaMapper.updateTmdpa(param);
    }
}
