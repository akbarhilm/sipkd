/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sp2d.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import spp.entity.GenNumberMapper;
import sp2d.entity.GenNumberMapper;
import sp2d.entity.Sp2dBlTuMapper;
import spp.model.HariKerja;
import spp.model.Skpd;
import spp.model.Sp2dBlTu;


/**
 *
 * @author Xalamaster
 */
@Transactional(readOnly = true)
@Service("Sp2dBlTuServices")
public class Sp2dBlTuImpl implements Sp2dBlTuServices {
    
    private static final Logger log = LoggerFactory.getLogger(Sp2dBlTuImpl.class);
    @Autowired
    private Sp2dBlTuMapper Sp2dBlTuMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<Sp2dBlTu> getSp2dBlTu(Map<String, Object> param) {
        return Sp2dBlTuMapper.getSp2dBlTu(param);
    }

    @Override
    public Integer getCountSp2dBlTu(Map param) {
        return Sp2dBlTuMapper.getCountSp2dBlTu(param);
    }

    @Override
    public Sp2dBlTu getSp2dBlTuById(Map param) {
        return Sp2dBlTuMapper.getSp2dBlTuById(param);
    }

    @Override
    public HariKerja getHariKerjaSp2d(Date tgl) {
        return Sp2dBlTuMapper.getHariKerjaSp2d(tgl);
    }

    @Override
    public Integer getCountHariKerjaSp2d(Date tgl) {
        return Sp2dBlTuMapper.getCountHariKerjaSp2d(tgl);
    }

    @Override
    public List<Sp2dBlTu> getSp2dRinciBlTu(Map<String, Object> param) {
        return Sp2dBlTuMapper.getSp2dRinciBlTu(param);
    }
    
    @Override
    public Sp2dBlTu getTotalBlTu(Map<String, Object> param) {
        return Sp2dBlTuMapper.getTotalBlTu(param);
    }
 
    @Override
    public Integer getCountSp2dRinciBlTu(Map param) {
        return Sp2dBlTuMapper.getCountSp2dRinciBlTu(param);
    }

    @Override
    public List<Sp2dBlTu> getKbud(Map<String, Object> param) {
        return Sp2dBlTuMapper.getKbud(param);
    }

    @Override
    public Integer getCountKbud(Map param) {
        return Sp2dBlTuMapper.getCountKbud(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSp2dBlTu(Sp2dBlTu sp2dBlTu) {
        final HariKerja hasilKerja = Sp2dBlTuMapper.getHariKerjaSp2d(new Date(System.currentTimeMillis()));
        if (hasilKerja != null) {
            sp2dBlTu.setTglsp2d(hasilKerja.getTglRekam());
        } else {
            sp2dBlTu.setTglsp2d(new Date(System.currentTimeMillis()));
        }
        final Map param = new HashMap();
        param.put("tahun", sp2dBlTu.getTahun());
        param.put("wilsp2d", sp2dBlTu.getKodeWilayah());
        sp2dBlTu.setNoSp2d(genNumberMapper.getSppdNo(param));
        //sppUp.put("tglcetak", hasirKerja.getTglCetak());
        Sp2dBlTuMapper.insertSp2dBlTu(sp2dBlTu);
    }

    @Override
    public Skpd getSkpdById(Integer idskpd) {
      return Sp2dBlTuMapper.getSkpdById(idskpd);
    }

    @Override
    public Sp2dBlTu getSp2dBlTuByIdSp2d(Map param) {
        return Sp2dBlTuMapper.getSp2dBlTuByIdSp2d(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSp2dBlTu(Sp2dBlTu sp2dBlTu) {
     final HariKerja hasilKerja = Sp2dBlTuMapper.getHariKerjaSp2d(new Date(System.currentTimeMillis()));
        if (hasilKerja != null) {
            sp2dBlTu.setTglsp2d(hasilKerja.getTglRekam());
        } else {
            sp2dBlTu.setTglsp2d(new Date(System.currentTimeMillis()));
        }
        //sppUp.put("tglcetak", hasirKerja.getTglCetak());
        Sp2dBlTuMapper.updateSp2dBlTu(sp2dBlTu);}

    @Override
    @Transactional(readOnly = false)
    public void deleteSp2dBtlLs(Sp2dBlTu sp2dBtlLs) {
        Sp2dBlTuMapper.insertBatalSp2dBtlLs(sp2dBtlLs);
        Sp2dBlTuMapper.deleteSp2dBtlLs(sp2dBtlLs);
    }
}
