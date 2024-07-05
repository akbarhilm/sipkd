package sipkd.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sipkd.entity.SpdPembatalanMapper;
import sipkd.model.HariKerja;
import sipkd.model.PejabatPpkd;
import sipkd.model.Skpd;
import sipkd.model.SpdPembatalan;

/**
 *
 * @author User
 */
@Transactional(readOnly = true)
@Service("SpdPembatalanService")
public class SpdPembatalanImpl implements SpdPembatalanService {

    private static final Logger log = LoggerFactory.getLogger(SpdPembatalanImpl.class);
    @Autowired
    private SpdPembatalanMapper SpdPembatalanMapper;
    
    @Override
    public List<SpdPembatalan> getlistvalidasispd(Map parameter) {
        return SpdPembatalanMapper.getlistvalidasispd(parameter);
    }
    
    @Override
    public Integer getbanyakvalidasispd(Map parameter) {
        return SpdPembatalanMapper.getbanyakvalidasispd(parameter);
    }
    
    @Override
    public SpdPembatalan getBatalById(Integer idSpdSah) {
        return SpdPembatalanMapper.getBatalById(idSpdSah);
    }
    
    
    @Override
    @Transactional(readOnly = false)
    public void insertThSpdSah(SpdPembatalan param) {
        SpdPembatalanMapper.insertThSpdSah(param);
        SpdPembatalanMapper.deleteSpdSah(param.getIdSpdSah());
        
    }
    

}
