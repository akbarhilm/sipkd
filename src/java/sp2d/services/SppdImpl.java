package sp2d.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sp2d.entity.GenNumberMapper;
import sp2d.entity.Sp2dBtlLsMapper;
import sp2d.entity.Sp2dMapper;
import spp.model.HariKerja;
import spp.model.Sp2d;
import sp2d.util.SipkdHelpers;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("sppdServices")
public class SppdImpl implements SppdServices {

    private static final Logger log = LoggerFactory.getLogger(SppdImpl.class);
    @Autowired
    private Sp2dMapper sp2dMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;
    @Autowired
    private Sp2dBtlLsMapper sp2dBtlLsMapper;

    @Override
    public List<Sp2d> getAllSp2dUP(Map<String, Object> par) {
        return sp2dMapper.getAllSp2dUP(par);
    }

    @Override
    public Integer getBanyakAllSp2dUP(Map<String, Object> par) {
        return sp2dMapper.getBanyakAllSp2dUP(par);
    }

    @Override
    public Sp2d getSp2dUPById(Integer idsp2d) {
        return sp2dMapper.getSp2dUPById(idsp2d);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSp2dRinci(Sp2d sp2d) {
        final Map map = new HashMap();
        map.put("tahun", sp2d.getTahun());
        map.put("wilsp2d", sp2d.getKodeWilayahProses());
        sp2d.setNomorSp2d(SipkdHelpers.getIntFromString(genNumberMapper.getSppdNo(map)));
        final HariKerja hariKerja = sp2dBtlLsMapper.getHariKerjaSp2d(sp2d.getTglSp2d());
        if (hariKerja == null) {

        } else {
            sp2d.setTglSp2d(hariKerja.getTglRekam());
        }
        sp2dMapper.insertSp2dRinci(sp2d);
    }

    @Override
    public BigDecimal getNilaiTotalSPMUP(Integer idspm) {
        return sp2dMapper.getNilaiTotalSPMUP(idspm);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSp2dRinci(Sp2d sp2d) {
        sp2dMapper.updateSp2dRinci(sp2d);
    }

}
