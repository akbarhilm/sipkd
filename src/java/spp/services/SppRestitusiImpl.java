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
import spp.entity.SppRestitusiMapper;
import spp.model.SppBtl;
import spp.entity.GenNumberMapper;
import spp.model.SppRestitusiRinci;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("sppRestitusiServices")
public class SppRestitusiImpl implements SppRestitusiServices {

    private static final Logger log = LoggerFactory.getLogger(SppRestitusiImpl.class);
    @Autowired
    private SppRestitusiMapper sppRestitusiMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;
    private SppBtl sppBtl;

    @Override
    public List<SppBtl> getAllSppRes(Map param) {
        return sppRestitusiMapper.getAllSppRes(param);
    }

    @Override
    public Integer getBanyakSppRes(Map param) {
        return sppRestitusiMapper.getBanyakSppRes(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteSppBtlMaster(Integer id) {
        sppRestitusiMapper.deleteSppBtlMaster(id);
        sppRestitusiMapper.deleteSppBtlRinci(id);
    }

    @Transactional(readOnly = false)
    @Override
    public void updateSppBtl(SppBtl sppBtl) {
        sppRestitusiMapper.updateSppBtlMaster(sppBtl);
        final Integer idspp = sppBtl.getId();
        final List<SppRestitusiRinci> list = sppBtl.getSppRestitusiRinci();
        // sppBtlMapper.deleteSppBtlMaster(idspp);
        for (SppRestitusiRinci sppRestitusiRinci : list) {
            sppRestitusiRinci.setIdSpp(idspp);
            sppRestitusiMapper.deleteSppBtlMasterByIdSppIdSpdIdBtlIdAkun(sppRestitusiRinci);
            sppRestitusiMapper.insertSppResRinci(sppRestitusiRinci);
        }

    }

    @Override
    public Integer getBanyakSpdBtl(Map param) {
        return sppRestitusiMapper.getBanyakSpdBtl(param);

    }

    @Override
    @Transactional(readOnly = false)
    public void insertSppRes(SppBtl sppBtl) {
        final Map param = new HashMap();
        param.put("tahun", sppBtl.getTahun());
        param.put("idskpd", sppBtl.getSkpd().getIdSkpd());
        sppBtl.setNoSpp(genNumberMapper.getSppNo(param));
        sppRestitusiMapper.insertSppResMaster(sppBtl);
        final List<SppRestitusiRinci> list = sppBtl.getSppRestitusiRinci();
        for (SppRestitusiRinci sppRestitusiRinci : list) {
            sppRestitusiRinci.setIdSpp(sppBtl.getId());
            sppRestitusiMapper.insertSppResRinci(sppRestitusiRinci);
        }

    }

    @Override
    public SppBtl getSppBtlById(Integer idspp) {
        return sppRestitusiMapper.getSppBtlById(idspp);
    }

    @Override
    public List<SppRestitusiRinci> getSppBtlRinciByIdSpp(Integer idspp) {
        return sppRestitusiMapper.getSppBtlRinciByIdSpp(idspp);

    }

    @Override
    public Map getBankRekByIdSkpd(Map param) {
        return sppRestitusiMapper.getBankRekByIdSkpd(param);
    }

    @Override
    public Map getBankDki(Map param) {
        return sppRestitusiMapper.getBankDki(param);
    }

    @Override
    public Map getBendaharaByIdSkpd(Map param) {
        return sppRestitusiMapper.getBendaharaByIdSkpd(param);
    }

    @Override
    public Map getNoValidasiByIdSkpd(String noValidasi) {
        return sppRestitusiMapper.getNoValidasiByIdSkpd(noValidasi);
    }

    @Override
    public List<SppRestitusiRinci> getAkunByNomorValidasi(Map param) {
        return sppRestitusiMapper.getAkunByNomorValidasi(param);

    }

    @Override
    public Integer getBanyakAkunByNomorValidasi(Map param) {
        return sppRestitusiMapper.getBanyakAkunByNomorValidasi(param);
    }

    @Override
    public Map<String, BigDecimal> getTotalNilaiAkunByNomorValidasi(Map param) {
        return sppRestitusiMapper.getTotalNilaiAkunByNomorValidasi(param);
    }
}
