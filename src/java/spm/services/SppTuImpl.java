package spm.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spm.entity.GenNumberMapper;
import spm.entity.SppTuMapper;
import spp.model.SppTu;
import spp.model.SppTuRinci;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("sppTuServices")
public class SppTuImpl implements SppTuServices {

    private static final Logger log = LoggerFactory.getLogger(SppTuImpl.class);
    @Autowired
    private SppTuMapper sppTuMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<SppTu> getAllSppTu(Map param) {
        return sppTuMapper.getAllSppTu(param);
    }

    @Override
    public Integer getBanyakSppTu(Map param) {
        return sppTuMapper.getBanyakSppTu(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteSppTuMaster(Integer id) {
        sppTuMapper.deleteSppTuMaster(id);
        sppTuMapper.deleteSppTuRinci(id);
    }

    @Override
    public List<SppTu> getSpd(Map param) {
        return sppTuMapper.getSpd(param);
    }

    @Override
    public Integer getBanyakSpd(Map param) {
        return sppTuMapper.getBanyakSpd(param);
    }

    @Override
    public List<SppTuRinci> getAllSpdBL(Map param) {
        return sppTuMapper.getAllSpdBL(param);
    }

    @Override
    public Integer getBanyakSpdBL(Map param) {
        return sppTuMapper.getBanyakSpdBL(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSppTu(SppTu sppTu) {
        final Map param = new HashMap();
        param.put("tahun", sppTu.getTahun());
        param.put("idskpd", sppTu.getSkpd().getIdSkpd());
        sppTu.setNoSpp(genNumberMapper.getSppNo(param));
        sppTuMapper.insertSppTuMaster(sppTu);
        final List<SppTuRinci> list = sppTu.getSppTuRinci();
        for (SppTuRinci sppTuRinci : list) {
            sppTuRinci.setIdspp(sppTu.getId());
            sppTuRinci.setNoSpp(genNumberMapper.getSppNo(param));
            sppTuMapper.insertSppTuRinci(sppTuRinci);
        }

    }

    @Override
    public SppTu getSppTuById(Integer idspp) {
        return sppTuMapper.getSppTuById(idspp);

    }

    @Override
    public List<SppTuRinci> getSppTuRinciByIdSpp(Integer idspp) {
        return sppTuMapper.getSppTuRinciByIdSpp(idspp);
    }

    @Transactional(readOnly = false)
    @Override
    public void updateSppTu(SppTu sppTu) {
        sppTuMapper.updateSppTuMaster(sppTu);
        final Integer idspp = sppTu.getId();
        final List<SppTuRinci> list = sppTu.getSppTuRinci();
        //  sppTuMapper.deleteSppTuMaster(idspp);
        for (SppTuRinci sppTuRinci : list) {
            sppTuRinci.setIdspp(idspp);
            log.debug(" sppTuRinci "+sppTuRinci.getIdspp()+"  == "+sppTuRinci.getIdSpd()+" == "+sppTuRinci.getKegiatan().getIdKegiatan());
            sppTuMapper.deleteSppTuMasterbyIdSpdAndIdKegiatan(sppTuRinci);
            /* final BigDecimal bd = sppTuRinci.getNilaiSpp();
             if (bd != null && bd.compareTo(new BigDecimal(0)) == 0) {
             sppTuMapper.deleteSppTuMasterbyIdSpdAndIdKegiatan(sppTuRinci);
             }*/
            sppTuMapper.insertSppTuRinci(sppTuRinci);
        }

    }

    @Override
    public Map getBankRekByIdSkpd(Map param) {
        return sppTuMapper.getBankRekByIdSkpd(param);
    }

    @Override
    public Map getBendaharaByIdSkpd(Map param) {
        return sppTuMapper.getBendaharaByIdSkpd(param);
    }

}
