package spp.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spp.entity.GenNumberMapper;
import spp.entity.SppUpMapper;
import spp.model.SppUp;
import spp.model.SppUpRinci;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("sppUpServices")
public class SppUpImpl implements SppUpServices {

    private static final Logger log = LoggerFactory.getLogger(SppUpImpl.class);

    @Autowired
    private SppUpMapper sppUpMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<SppUp> getAllSppUp(Map param) {
        return sppUpMapper.getAllSppUp(param);
    }

    @Override
    public Integer getBanyakSppUp(Map param) {
        return sppUpMapper.getBanyakSppUp(param);
    }

    @Override
    public List<SppUpRinci> getAllSpdBL(Map param) {
        return sppUpMapper.getAllSpdBL(param);
    }

    @Override
    public Integer getBanyakSpdBL(Map param) {
        return sppUpMapper.getBanyakSpdBL(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSppUp(SppUp sppUp) {
        final Map param = new HashMap();
        param.put("tahun", sppUp.getTahun());
        param.put("idskpd", sppUp.getSkpd().getIdSkpd());
        sppUp.setNoSpp(genNumberMapper.getSppNo(param));
        sppUpMapper.insertSppUpMaster(sppUp);
        final List<SppUpRinci> list = sppUp.getSppUpRinci();
        for (SppUpRinci sppUpRinci : list) {
            sppUpRinci.setIdspp(sppUp.getId());
            if (sppUpRinci.getNilaiSpp().compareTo(BigDecimal.ZERO) == 0) {
                //log.debug("-------------- nilai spp == 0 --------------");
            } else {
                sppUpMapper.insertSppUpRinci(sppUpRinci);
            }
        }
    }

    @Override
    public SppUp getSppUPById(Integer idspp) {
        return sppUpMapper.getSppUPById(idspp);
    }

    @Override
    public List<SppUpRinci> getSppUpRinciByIdSpp(Integer idspp) {
        return sppUpMapper.getSppUpRinciByIdSpp(idspp);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSppUp(SppUp sppUp) {
        sppUpMapper.updateSppUpMaster(sppUp);
        final Integer idspp = sppUp.getId();
        final List<SppUpRinci> list = sppUp.getSppUpRinci();
        if (list != null && list.size() > 0) {
            final SppUpRinci spppar = list.get(0);
            sppUpMapper.deleteSppUpMaster(spppar);
            for (SppUpRinci sppUpRinci : list) {
                sppUpRinci.setIdspp(idspp);
                if (sppUpRinci.getNilaiSpp().doubleValue() > 0.0) {
                    sppUpMapper.insertSppUpRinci(sppUpRinci);
                }
            }
        }

    }

    @Override
    public Map getTotalSPDDanSPP(Map param) {
        return sppUpMapper.getTotalSPDDanSPP(param);
    }

    @Override
    public Map getBankRekByIdSkpd(Map param) {
        return sppUpMapper.getBankRekByIdSkpd(param);
    }

    @Override
    public Map getBankDki(Map param) {
        return sppUpMapper.getBankDki(param);
    }

    @Override
    public Integer getCekBanyakSppUp(Map param) {
        return sppUpMapper.getCekBanyakSppUp(param);
    }

    @Override
    public String getTotalSpdBL(Map param) {
        return sppUpMapper.getTotalSpdBL(param);
    }

}
