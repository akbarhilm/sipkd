package dash.services;



import dash.entity.PenggunaModulMapper;

import dash.model.GenerateId;
import dash.model.Skpd;
import dash.model.PenggunaModul;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zaenab
 */
@Service("penggunaModulServices")
@Transactional(readOnly = true)
public class PenggunaModulImpl implements PenggunaModulServices {

    @Autowired
    private PenggunaModulMapper modulMapper;
    
   

    @Override
    public List<PenggunaModul> getListModul(Map<String, Object> map) {
        return modulMapper.getListModul(map);
    }

    @Override
    public Integer getBanyakListModul(Map<String, Object> map) {
        return modulMapper.getBanyakListModul(map);
    }
    

    @Override
    @Transactional(readOnly = false)
    public void insertPenggunaModul(PenggunaModul pgnmdl) {
       
         
        modulMapper.insertPenggunaModul(pgnmdl);
       
    }
    
    @Override
    @Transactional(readOnly = false)
    public void deletePenggunaModul(PenggunaModul pgnmdl) {
        modulMapper.deletePenggunaModul(pgnmdl);

    }

}
