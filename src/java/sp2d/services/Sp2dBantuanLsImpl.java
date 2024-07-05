/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2d.services;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import spp.entity.GenNumberMapper;
import sp2d.entity.GenNumberMapper;
import sp2d.entity.Sp2dBantuanLsMapper;
import spp.model.HariKerja;
import spp.model.Skpd;
import spp.model.Sp2dLs;

/**
 *
 * @author Xalamaster
 */
@Transactional(readOnly = true)
@Service("sp2dBantuanLsServices")
public class Sp2dBantuanLsImpl implements Sp2dBantuanLsServices {

    private static final Logger log = LoggerFactory.getLogger(Sp2dBantuanLsImpl.class);
    @Autowired
    private Sp2dBantuanLsMapper Sp2dBantuanLsMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<Sp2dLs> getSp2dBantuanLs(Map<String, Object> param) {
        return Sp2dBantuanLsMapper.getSp2dBantuanLs(param);
    }

    @Override
    public Integer getCountSp2dBantuanLs(Map param) {
        return Sp2dBantuanLsMapper.getCountSp2dBantuanLs(param);
    }

    @Override
    public Sp2dLs getSp2dBantuanLsById(Map param) {
        return Sp2dBantuanLsMapper.getSp2dBantuanLsById(param);
    }

    @Override
    public HariKerja getHariKerjaSp2d(Date tgl) {
        return Sp2dBantuanLsMapper.getHariKerjaSp2d(tgl);
    }

    @Override
    public Integer getCountHariKerjaSp2d(Date tgl) {
        return Sp2dBantuanLsMapper.getCountHariKerjaSp2d(tgl);
    }

    @Override
    public List<Sp2dLs> getSp2dRinciBantuanLs(Map<String, Object> param) {
        return Sp2dBantuanLsMapper.getSp2dRinciBantuanLs(param);
    }

    @Override
    public Integer getCountSp2dRinciBantuanLs(Map param) {
        return Sp2dBantuanLsMapper.getCountSp2dRinciBantuanLs(param);
    }

    @Override
    public List<Sp2dLs> getKbud(Map<String, Object> param) {
        return Sp2dBantuanLsMapper.getKbud(param);
    }

    @Override
    public Integer getCountKbud(Map param) {
        return Sp2dBantuanLsMapper.getCountKbud(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSp2dBantuanLs(Sp2dLs sp2dBtlLs) {
        final HariKerja hasilKerja = Sp2dBantuanLsMapper.getHariKerjaSp2d(new Date(System.currentTimeMillis()));
        if (hasilKerja != null) {
            sp2dBtlLs.setTglsp2d(hasilKerja.getTglRekam());
        } else {
            sp2dBtlLs.setTglsp2d(new Date(System.currentTimeMillis()));
        }
        final Map param = new HashMap();
        param.put("tahun", sp2dBtlLs.getTahun());
        param.put("wilsp2d", sp2dBtlLs.getKodeWilayah());
        sp2dBtlLs.setNoSp2d(genNumberMapper.getSppdNo(param));
        //sppUp.put("tglcetak", hasirKerja.getTglCetak());
        Sp2dBantuanLsMapper.insertSp2dBantuanLs(sp2dBtlLs);
    }

    @Override
    public Skpd getSkpdById(Integer idskpd) {
      return Sp2dBantuanLsMapper.getSkpdById(idskpd);
    }

    @Override
    public Sp2dLs getSp2dBantuanLsByIdSp2d(Map param) {
        return Sp2dBantuanLsMapper.getSp2dBantuanLsByIdSp2d(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSp2dBantuanLs(Sp2dLs sp2dBtlLs) {
     final HariKerja hasilKerja = Sp2dBantuanLsMapper.getHariKerjaSp2d(new Date(System.currentTimeMillis()));
        if (hasilKerja != null) {
            sp2dBtlLs.setTglsp2d(hasilKerja.getTglRekam());
        } else {
            sp2dBtlLs.setTglsp2d(new Date(System.currentTimeMillis()));
        }
        //sppUp.put("tglcetak", hasirKerja.getTglCetak());
        Sp2dBantuanLsMapper.updateSp2dBantuanLs(sp2dBtlLs);}

    @Override
    @Transactional(readOnly = false)
    public void deleteSp2dBtlLs(Sp2dLs sp2dBtlLs) {
        Sp2dBantuanLsMapper.insertBatalSp2dBtlLs(sp2dBtlLs);
        Sp2dBantuanLsMapper.deleteSp2dBtlLs(sp2dBtlLs);
    }
}
