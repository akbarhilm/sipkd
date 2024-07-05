/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.services;

/**
 *
 * @author erzy
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spp.entity.GenNumberMapper;
import spp.entity.SppBantuanMapper;
import spp.model.SppBantuan;
import spp.model.SppBantuanRinci;

@Transactional(readOnly = true)
@Service("sppBantuanServices")
public class SppBantuanImpl implements SppBantuanServices {

    private static final Logger log = LoggerFactory.getLogger(SppBantuanImpl.class);
    @Autowired
    private SppBantuanMapper sppBantuanMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<SppBantuan> getAllSppBantuan(Map param) {
        return sppBantuanMapper.getAllSppBantuan(param);
    }

    @Override
    public List<SppBantuan> getBendahara(Map param) {
        return sppBantuanMapper.getBendahara(param);
    }

    @Override
    public Integer getBanyakSppBantuan(Map param) {
        return sppBantuanMapper.getBanyakSppBantuan(param);
    }

    @Override
    public List<SppBantuan> getSkpdKoorSah(Map param) {
        return sppBantuanMapper.getSkpdKoorSah(param);
    }

    @Override
    public Integer getBanyakSkpdKoorSah(Map param) {
        return sppBantuanMapper.getBanyakSkpdKoorSah(param);
    }

    @Override
    public List<SppBantuan> getSpdKegiatan(Map param) {
        return sppBantuanMapper.getSpdKegiatan(param);
    }

    @Override
    public Integer getBanyakSpdKegiatan(Map param) {
        return sppBantuanMapper.getBanyakSpdKegiatan(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSppBantuan(SppBantuanRinci sppBantuanRinci, SppBantuan sppBantuan) {
        final Map param = new HashMap();
        param.put("tahun", sppBantuan.getTahun());
        param.put("idskpd", sppBantuan.getSkpd().getIdSkpd());
        final String nospp = genNumberMapper.getSppNo(param);
        sppBantuan.setNoSpp(nospp);
        sppBantuanMapper.insertSppBantuanMaster(sppBantuan);
        sppBantuanRinci.setNoSpp(nospp);
        sppBantuanRinci.setIdspp(sppBantuan.getId());
        sppBantuanRinci.setIdSpd(sppBantuan.getIdSpd());
        sppBantuanRinci.setNilaiSpp(sppBantuan.getNilaiSpp());
        sppBantuanRinci.setAkun(sppBantuan.getAkun());
        sppBantuanRinci.setIdskpdkoor(sppBantuan.getIdskpdkoor());
        sppBantuanMapper.insertSppBantuanRinci(sppBantuanRinci);

    }

    @Override
    @Transactional(readOnly = false)
    public void insertSppBantuanRinci(SppBantuanRinci sppBantuanRinci, SppBantuan sppBantuan) {
        // sppBantuanRinci.setNoSpp(sppBantuan.getNoSpp());
        sppBantuanRinci.setIdspp(sppBantuan.getId());
        sppBantuanRinci.setIdSpd(sppBantuan.getIdSpd());
        sppBantuanRinci.setNilaiSpp(sppBantuan.getNilaiSpp());
        sppBantuanRinci.setAkun(sppBantuan.getAkun());
        sppBantuanRinci.setIdskpdkoor(sppBantuan.getIdskpdkoor());
        sppBantuanMapper.insertSppBantuanRinci(sppBantuanRinci);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSppBantuan(SppBantuan sppBantuan) {
        final Map param = new HashMap();
        param.put("tahun", sppBantuan.getTahun());
        param.put("idskpd", sppBantuan.getSkpd().getIdSkpd());
        sppBantuan.setNoSpp(genNumberMapper.getSppNo(param));
        sppBantuanMapper.updateSppBantuanMaster(sppBantuan);

    }

    @Override
    @Transactional(readOnly = false)
    public void updateSppBantuanRinci(SppBantuanRinci sppBantuanRinci, SppBantuan sppBantuan) {
        // sppBantuanRinci.setNoSpp(sppBantuan.getNoSpp());
        sppBantuanRinci.setIdspp(sppBantuan.getId());
        sppBantuanRinci.setIdSpd(sppBantuan.getIdSpd());
        sppBantuanRinci.setNilaiSpp(sppBantuan.getNilaiSpp());
        sppBantuanRinci.setAkun(sppBantuan.getAkun());
        sppBantuanRinci.setIdskpdkoor(sppBantuan.getIdskpdkoor());

        sppBantuanMapper.updateSppBantuanRinci(sppBantuanRinci);

    }

    /*@Override
     public void  getSppBantuanDetailById(Integer idspp) {
     sppBantuanMapper.getSppBantuanById(idspp);
     sppBantuanMapper.getAllSpdBtlBantuanById(idspp);
        
     }*/
    /* @Override
     public SppBantuanRinci getAllSpdBtlBantuanById(Integer idspp) {
     return sppBantuanMapper.getAllSpdBtlBantuanById(idspp);
     }*/
    @Override
    public SppBantuan getSppBantuanRinciByIdSpp(Integer idspp) {
        return sppBantuanMapper.getSppBantuanRinciByIdSpp(idspp);
    }

    @Override
    public SppBantuan getSppBantuanById(Integer idspp) {
        return sppBantuanMapper.getSppBantuanById(idspp);
    }

    @Override
    public String getNamaSkpdKoordinatorById(Integer idskpdkoor) {
        return sppBantuanMapper.getNamaSkpdKoordinatorById(idskpdkoor);
    }

    @Override
    public SppBantuan getSkpdKoorById(Integer idskpdkoor) {
        return sppBantuanMapper.getSkpdKoorById(idskpdkoor);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteSppBantuanMaster(Integer id) {
        sppBantuanMapper.deleteSppBantuanMaster(id);
        sppBantuanMapper.deleteSppBantuanRinci(id);
    }

    @Override
    public Map getBankRekByIdSkpd(Map param) {
        return sppBantuanMapper.getBankRekByIdSkpd(param);
    }

    @Override
    public Map getDetailRinciBantuan(Map param) {
        return sppBantuanMapper.getDetailRinciBantuan(param);
    }

    @Override
    public Map getBendaharaByIdSkpd(Map param) {
        return sppBantuanMapper.getBendaharaByIdSkpd(param);
    }

    @Override
    public Map getRinciByIdSpp(Map param) {
        return sppBantuanMapper.getRinciByIdSpp(param);
    }

}
