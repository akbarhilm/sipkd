/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spj.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spj.entity.GenNumberMapper;
import spj.entity.SpjMapper;
import spj.entity.BapKasMapper;
import spp.model.Bendahara;
import spp.model.Spj;
import spp.model.BapKas;
import spp.model.BapKasRinci;
import spp.model.SpjRinci;

@Transactional(readOnly = true)
@Service("bapKasServices")
public class BapKasImpl implements BapKasServices {

    private static final Logger log = LoggerFactory.getLogger(BapKasImpl.class);

    @Autowired
    private BapKasMapper bapKasMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<BapKas> getBapKas(Map<String, Object> param) {
        return bapKasMapper.getBapKas(param);
    }

    @Override
    public Integer getCountBapKas(Map param) {
        return bapKasMapper.getCountBapKas(param);
    }

    @Override
    public Integer getCountTglBkuProses(Map param) {
        return bapKasMapper.getCountTglBkuProses(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBapKas(BapKas bapKas) {
        final Map param = new HashMap();
        param.put("tahun", bapKas.getTahun());
        param.put("idskpd", bapKas.getSkpd().getIdSkpd());

        bapKasMapper.insertBapKas(bapKas);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBapKasAll(BapKas bapKas) {
        final Map param = new HashMap();
        param.put("tahun", bapKas.getTahun());
        param.put("idskpd", bapKas.getSkpd().getIdSkpd());
        //param.put("idSkpdBAPKas", bapKas.getIdSkpdBAPKas());  
        log.debug("anitaikan impl >>>>>>>>>>>  " + param.toString());
        //bapKas.setNoBap(genNumberMapper.getNoBap(param));
        bapKasMapper.insertBapKas(bapKas);
        final List<BapKasRinci> list = bapKas.getBapKasRinci();
        log.debug("anitaikan impl list >>>>>>>>>>>  " + list);
        for (BapKasRinci bapKasRinci : list) {
            bapKasRinci.setIdSkpdBAPKas(bapKas.getIdSkpdBAPKas());
            log.debug("anitaikan impl >>>>>>>>>>> masuk ke list rinci >> getIdSkpdBAPKas = " + bapKas.getIdSkpdBAPKas());
            bapKasMapper.insertBapKasRinci(bapKasRinci);
        }
    }
    /* @Transactional(readOnly = false)
     @Override
     public void updateSppBtl(SppBtl sppBtl) {
     sppBtlMapper.updateSppBtlMaster(sppBtl);
     final Integer idspp = sppBtl.getId();
     final List<SppBtlRinci> list = sppBtl.getSppBtlRinci();
     // sppBtlMapper.deleteSppBtlMaster(idspp);
     for (SppBtlRinci sppBtlRinci : list) {
     sppBtlRinci.setIdspp(idspp);
     sppBtlMapper.deleteSppBtlMasterByIdSppIdSpdIdBtlIdAkun(sppBtlRinci);
     sppBtlMapper.insertSppBtlRinci(sppBtlRinci);
     }
     }*/

    @Override
    @Transactional(readOnly = false)
    public void updateBapKasAll(BapKas bapKas) {
        final Map param = new HashMap();
        param.put("idSkpdBAPKas", bapKas.getIdSkpdBAPKas());
        param.put("tahun", bapKas.getTahun());
        param.put("idskpd", bapKas.getSkpd().getIdSkpd());
        bapKasMapper.updateBapKas(bapKas);

        final Integer idSkpdBAPKas = bapKas.getIdSkpdBAPKas();

        final List<BapKasRinci> list = bapKas.getBapKasRinci();

        for (BapKasRinci bapKasRinci : list) {
            final Integer idSkpdBAPKasRinci = bapKasRinci.getIdSkpdBAPKasRinci();
            log.debug("cek di impl idSkpdBAPKasRinci===" + idSkpdBAPKasRinci);
            bapKasRinci.setIdSkpdBAPKas(idSkpdBAPKas);
            //bapKasMapper.updateBapKasRinci(bapKasRinci); 
            //bapKasMapper.deleteBapKasRinci(bapKasRinci);
            //bapKasMapper.insertBapKasRinci(bapKasRinci);
            if (bapKasRinci.getIdSkpdBAPKasRinci() != 0) {
                bapKasMapper.updateBapKasRinci(bapKasRinci);
            } else {
                bapKasMapper.insertBapKasRinci(bapKasRinci);
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSaldoAwalBank(Map param2) {

        bapKasMapper.insertSaldoAwalBank(param2);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSaldoAwalBank(Map param2) {

        bapKasMapper.updateSaldoAwalBank(param2);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteSaldoAwalBank(Map param) {

        bapKasMapper.deleteSaldoAwalBank(param);
    }

    @Override
    public BapKas getBapKasById(Map param) {
        return bapKasMapper.getBapKasById(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBapKas(BapKas bapkas) {
        final Map param = new HashMap();
        param.put("tahun", bapkas.getTahun());
        param.put("idskpd", bapkas.getSkpd().getIdSkpd());
        log.debug("Parameter ==> " + bapkas.getTahun() + " ===> " + bapkas.getSkpd().getIdSkpd());
        bapKasMapper.updateBapKas(bapkas);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBapKas(Map param) {

        bapKasMapper.deleteBapKas(param);
        bapKasMapper.deleteBapKasRinci2(param);

    }

    @Override
    public String getHari(Map param1) {
        return bapKasMapper.getHari(param1);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateHari(Map param) {
        bapKasMapper.updateHari(param);
    }

    @Override
    public List<Map> getnilaiparam(Map param) {
        return bapKasMapper.getnilaiparam(param);
    }

    @Override
    public List<Map> getBulanList(Map param) {
        return bapKasMapper.getBulanList(param);
    }

    @Override
    public List<Map> getBulanListEdit(Map param) {
        return bapKasMapper.getBulanListEdit(param);
    }

    @Override
    public List<BapKas> getAllBAPKAS(Map<String, Object> param) {
        return bapKasMapper.getAllBAPKAS(param);
    }

    @Override
    public Integer getBanyakAllBAPKAS(Map<String, Object> param) {
        return bapKasMapper.getBanyakAllBAPKAS(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBapKasRinci2(Map param) {

        bapKasMapper.deleteBapKasRinci2(param);

    }
    
    @Override
    public BapKas getNilaiKas(Map param) {
        return bapKasMapper.getNilaiKas(param);
    }
}
