package sipkd.services;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sipkd.entity.SpdPembatalanRevMapper;
import sipkd.model.SpdPembatalan;

/**
 *
 * @author User
 */
@Transactional(readOnly = true)
@Service("SpdPembatalanRevService")
public class SpdPembatalanRevImpl implements SpdPembatalanRevService {

    private static final Logger log = LoggerFactory.getLogger(SpdPembatalanRevImpl.class);
    @Autowired
    private SpdPembatalanRevMapper SpdPembatalanRevMapper;
    
    @Override
    public List<SpdPembatalan> getlistvalidasispd(Map parameter) {
        return SpdPembatalanRevMapper.getlistvalidasispd(parameter);
    }
    
    @Override
    public Integer getbanyakvalidasispd(Map parameter) {
        return SpdPembatalanRevMapper.getbanyakvalidasispd(parameter);
    }
    
    @Override
    public SpdPembatalan getBatalById(Integer idSpdSah) {
        return SpdPembatalanRevMapper.getBatalById(idSpdSah);
    }
    
    
    @Override
    @Transactional(readOnly = false)
    public void insertThSpdSah(SpdPembatalan param) {
        SpdPembatalanRevMapper.insertThSpdSah(param);
       // SpdPembatalanRevMapper.deleteSpdSah(param.getIdSpdSah());
        
    }
    
     @Override
    @Transactional(readOnly = false)
    public void updatespdsahbatal(Map parameter) {
        SpdPembatalanRevMapper.updatespdsahbatal(parameter);
    }
    
    
     @Override
    @Transactional(readOnly = false)
    public void updatespdbatalrincibtl(Map parameter) {
        SpdPembatalanRevMapper.updatespdbatalrincibtl(parameter);
    }
    
     @Override
    @Transactional(readOnly = false)
    public void updatespdbatalrincibl(Map parameter) {
        SpdPembatalanRevMapper.updatespdbatalrincibl(parameter);
    }
    
     @Override
    @Transactional(readOnly = false)
    public void updatespdbatalrincibtlbantuan(Map parameter) {
        SpdPembatalanRevMapper.updatespdbatalrincibtlbantuan(parameter);
    }
    
     @Override
    @Transactional(readOnly = false)
    public void updatespdbatalrincibiaya(Map parameter) {
        SpdPembatalanRevMapper.updatespdbatalrincibiaya(parameter);
    }

}
