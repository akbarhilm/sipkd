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
import sp2d.entity.Sp2dBiayaLsMapper;
import spp.model.HariKerja;
import spp.model.Skpd;
import spp.model.Sp2dLs;

/**
 *
 * @author Xalamaster
 */
@Transactional(readOnly = true)
@Service("Sp2dBiayaLsServices")
public class Sp2dBiayaLsImpl implements Sp2dBiayaLsServices {

    private static final Logger log = LoggerFactory.getLogger(Sp2dBiayaLsImpl.class);
    @Autowired
    private Sp2dBiayaLsMapper Sp2dBiayaLsMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<Sp2dLs> getSp2dBiayaLs(Map<String, Object> param) {
        return Sp2dBiayaLsMapper.getSp2dBiayaLs(param);
    }

    @Override
    public Integer getCountSp2dBiayaLs(Map param) {
        return Sp2dBiayaLsMapper.getCountSp2dBiayaLs(param);
    }

    @Override
    public Sp2dLs getSp2dBiayaLsById(Map param) {
        return Sp2dBiayaLsMapper.getSp2dBiayaLsById(param);
    }

    @Override
    public HariKerja getHariKerjaSp2d(Date tgl) {
        return Sp2dBiayaLsMapper.getHariKerjaSp2d(tgl);
    }

    @Override
    public Integer getCountHariKerjaSp2d(Date tgl) {
        return Sp2dBiayaLsMapper.getCountHariKerjaSp2d(tgl);
    }

    @Override
    public List<Sp2dLs> getSp2dRinciBiayaLs(Map<String, Object> param) {
        return Sp2dBiayaLsMapper.getSp2dRinciBiayaLs(param);
    }

    @Override
    public Integer getCountSp2dRinciBiayaLs(Map param) {
        return Sp2dBiayaLsMapper.getCountSp2dRinciBiayaLs(param);
    }

    @Override
    public List<Sp2dLs> getKbud(Map<String, Object> param) {
        return Sp2dBiayaLsMapper.getKbud(param);
    }

    @Override
    public Integer getCountKbud(Map param) {
        return Sp2dBiayaLsMapper.getCountKbud(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSp2dBiayaLs(Sp2dLs sp2dBiayaLs) {
        final HariKerja hasilKerja = Sp2dBiayaLsMapper.getHariKerjaSp2d(new Date(System.currentTimeMillis()));
        if (hasilKerja != null) {
            sp2dBiayaLs.setTglsp2d(hasilKerja.getTglRekam());
        } else {
            sp2dBiayaLs.setTglsp2d(new Date(System.currentTimeMillis()));
        }
        final Map param = new HashMap();
        param.put("tahun", sp2dBiayaLs.getTahun());
        param.put("wilsp2d", sp2dBiayaLs.getKodeWilayah());
        sp2dBiayaLs.setNoSp2d(genNumberMapper.getSppdNo(param));
        //sppUp.put("tglcetak", hasirKerja.getTglCetak());
        Sp2dBiayaLsMapper.insertSp2dBiayaLs(sp2dBiayaLs);
    }

    @Override
    public Skpd getSkpdBiayaById(Map param) {
      return Sp2dBiayaLsMapper.getSkpdBiayaById(param);
    }

    @Override
    public Sp2dLs getSp2dBiayaLsByIdSp2d(Map param) {
        return Sp2dBiayaLsMapper.getSp2dBiayaLsByIdSp2d(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSp2dBiayaLs(Sp2dLs sp2dBiayaLs) {
     final HariKerja hasilKerja = Sp2dBiayaLsMapper.getHariKerjaSp2d(new Date(System.currentTimeMillis()));
        if (hasilKerja != null) {
            sp2dBiayaLs.setTglsp2d(hasilKerja.getTglRekam());
        } else {
            sp2dBiayaLs.setTglsp2d(new Date(System.currentTimeMillis()));
        }
        //sppUp.put("tglcetak", hasirKerja.getTglCetak());
        Sp2dBiayaLsMapper.updateSp2dBiayaLs(sp2dBiayaLs);}

   @Override
    @Transactional(readOnly = false)
    public void deleteSp2dBtlLs(Sp2dLs sp2dBtlLs) {
        Sp2dBiayaLsMapper.insertBatalSp2dBtlLs(sp2dBtlLs);
        Sp2dBiayaLsMapper.deleteSp2dBtlLs(sp2dBtlLs);
    }

}
