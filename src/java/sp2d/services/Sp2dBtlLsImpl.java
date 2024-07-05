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
import sp2d.entity.Sp2dBtlLsMapper;
import spp.model.HariKerja;
import spp.model.Skpd;
import spp.model.Sp2dLs;


@Transactional(readOnly = true)
@Service("Sp2dBtlLsServices")
public class Sp2dBtlLsImpl implements Sp2dBtlLsServices {

    private static final Logger log = LoggerFactory.getLogger(Sp2dBtlLsImpl.class);
    @Autowired
    private Sp2dBtlLsMapper Sp2dBtlLsMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<Sp2dLs> getSp2dBtlLs(Map<String, Object> param) {
        return Sp2dBtlLsMapper.getSp2dBtlLs(param);
    }

    @Override
    public Integer getCountSp2dBtlLs(Map param) {
        return Sp2dBtlLsMapper.getCountSp2dBtlLs(param);
    }

    @Override
    public Sp2dLs getSp2dBtlLsById(Map param) {
        return Sp2dBtlLsMapper.getSp2dBtlLsById(param);
    }

    @Override
    public HariKerja getHariKerjaSp2d(Date tgl) {
        return Sp2dBtlLsMapper.getHariKerjaSp2d(tgl);
    }

    @Override
    public Integer getCountHariKerjaSp2d(Date tgl) {
        return Sp2dBtlLsMapper.getCountHariKerjaSp2d(tgl);
    }

    @Override
    public List<Sp2dLs> getSp2dRinciBtlLs(Map<String, Object> param) {
        return Sp2dBtlLsMapper.getSp2dRinciBtlLs(param);
    }

    @Override
    public Integer getCountSp2dRinciBtlLs(Map param) {
        return Sp2dBtlLsMapper.getCountSp2dRinciBtlLs(param);
    }

    @Override
    public List<Sp2dLs> getKbud(Map<String, Object> param) {
        return Sp2dBtlLsMapper.getKbud(param);
    }

    @Override
    public Integer getCountKbud(Map param) {
        return Sp2dBtlLsMapper.getCountKbud(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSp2dBtlLs(Sp2dLs sp2dBtlLs) {
        final HariKerja hasilKerja = Sp2dBtlLsMapper.getHariKerjaSp2d(new Date(System.currentTimeMillis()));
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
        Sp2dBtlLsMapper.insertSp2dBtlLs(sp2dBtlLs);
    }

    @Override
    public Skpd getSkpdById(Integer idskpd) {
      return Sp2dBtlLsMapper.getSkpdById(idskpd);
    }

    @Override
    public Sp2dLs getSp2dBtlLsByIdSp2d(Map param) {
        return Sp2dBtlLsMapper.getSp2dBtlLsByIdSp2d(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSp2dBtlLs(Sp2dLs sp2dBtlLs) {
     final HariKerja hasilKerja = Sp2dBtlLsMapper.getHariKerjaSp2d(new Date(System.currentTimeMillis()));
        if (hasilKerja != null) {
            sp2dBtlLs.setTglsp2d(hasilKerja.getTglRekam());
        } else {
            sp2dBtlLs.setTglsp2d(new Date(System.currentTimeMillis()));
        }
        //sppUp.put("tglcetak", hasirKerja.getTglCetak());
        Sp2dBtlLsMapper.updateSp2dBtlLs(sp2dBtlLs);}

    @Override
    @Transactional(readOnly = false)
    public void deleteSp2dBtlLs(Sp2dLs sp2dBtlLs) {
        Sp2dBtlLsMapper.insertBatalSp2dBtlLs(sp2dBtlLs);
        Sp2dBtlLsMapper.deleteSp2dBtlLs(sp2dBtlLs);
    }
}
