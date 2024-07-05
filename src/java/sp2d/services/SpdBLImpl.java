package sp2d.services;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sp2d.entity.SpdBlMapper;
import spp.model.SpdBTLMaster;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("spdBLServices")
public class SpdBLImpl implements SpdBLServices {

    private static final Logger log = LoggerFactory.getLogger(SpdBLImpl.class);
    @Autowired
    private SpdBlMapper spdBlMapper;

    @Override
    public Integer getBanyakAnggaranBlSkpd(Map<String, Object> parameter) {
        return spdBlMapper.getBanyakAnggaranBlSkpd(parameter);
    }

    @Override
    public List<SpdBTLMaster> getAnggaranBlSkpd(Map<String, Object> parameter) {
        return spdBlMapper.getAnggaranBlSkpd(parameter);
    }

    @Override
    public Map<String, BigDecimal> getTotalAnggaranDanSpd(Map<String, Object> param) {
        final Map<String, BigDecimal> hasil = new LinkedHashMap<String, BigDecimal>(2);
        BigDecimal totalanggup = spdBlMapper.getTotalPaguBySkpd(param);
        hasil.put("anggaran", totalanggup);
        try {
            hasil.put("spd", totalanggup.subtract(spdBlMapper.getTotalSPDBySKPDDanTahun(param)));
        } catch (Exception e) {
            hasil.put("spd", new BigDecimal(0));
        }
        return hasil;
    }
}
