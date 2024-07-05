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
import sp2d.entity.Sp2dBlLsMapper;
import spp.model.HariKerja;
import spp.model.Skpd;
import spp.model.Sp2dBlLs;


@Transactional(readOnly = true)
@Service("Sp2dBlLsServices")
public class Sp2dBlLsImpl implements Sp2dBlLsServices {

    private static final Logger log = LoggerFactory.getLogger(Sp2dBlLsImpl.class);
    @Autowired
    private Sp2dBlLsMapper Sp2dBlLsMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<Sp2dBlLs> getSp2dBlLs(Map<String, Object> param) {
        return Sp2dBlLsMapper.getSp2dBlLs(param);
    }

    @Override
    public Integer getCountSp2dBlLs(Map param) {
        return Sp2dBlLsMapper.getCountSp2dBlLs(param);
    }

    @Override
    public Sp2dBlLs getSp2dBlLsById(Map param) {
        return Sp2dBlLsMapper.getSp2dBlLsById(param);
    }

    @Override
    public HariKerja getHariKerjaSp2d(Date tgl) {
        return Sp2dBlLsMapper.getHariKerjaSp2d(tgl);
    }

    @Override
    public Integer getCountHariKerjaSp2d(Date tgl) {
        return Sp2dBlLsMapper.getCountHariKerjaSp2d(tgl);
    }

    @Override
    public List<Sp2dBlLs> getSp2dRinciBlLs(Map<String, Object> param) {
        return Sp2dBlLsMapper.getSp2dRinciBlLs(param);
    }

    @Override
    public Integer getCountSp2dRinciBlLs(Map param) {
        return Sp2dBlLsMapper.getCountSp2dRinciBlLs(param);
    }

    @Override
    public List<Sp2dBlLs> getKbud(Map<String, Object> param) {
        return Sp2dBlLsMapper.getKbud(param);
    }

    @Override
    public Integer getCountKbud(Map param) {
        return Sp2dBlLsMapper.getCountKbud(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSp2dBlLs(Sp2dBlLs sp2dBlLs) {
        final HariKerja hasilKerja = Sp2dBlLsMapper.getHariKerjaSp2d(new Date(System.currentTimeMillis()));
        if (hasilKerja != null) {
            sp2dBlLs.setTglsp2d(hasilKerja.getTglRekam());
        } else {
            sp2dBlLs.setTglsp2d(new Date(System.currentTimeMillis()));
        }
        final Map param = new HashMap();
        param.put("tahun", sp2dBlLs.getTahun());
        param.put("wilsp2d", sp2dBlLs.getKodeWilayah());
        sp2dBlLs.setNoSp2d(genNumberMapper.getSppdNo(param));
        //sppUp.put("tglcetak", hasirKerja.getTglCetak());
        Sp2dBlLsMapper.insertSp2dBlLs(sp2dBlLs);
    }

    @Override
    public Skpd getSkpdById(Integer idskpd) {
      return Sp2dBlLsMapper.getSkpdById(idskpd);
    }

    @Override
    public Sp2dBlLs getSp2dBlLsByIdSp2d(Map param) {
        return Sp2dBlLsMapper.getSp2dBlLsByIdSp2d(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSp2dBlLs(Sp2dBlLs sp2dBlLs) {
     final HariKerja hasilKerja = Sp2dBlLsMapper.getHariKerjaSp2d(new Date(System.currentTimeMillis()));
        if (hasilKerja != null) {
            sp2dBlLs.setTglsp2d(hasilKerja.getTglRekam());
        } else {
            sp2dBlLs.setTglsp2d(new Date(System.currentTimeMillis()));
        }
        //sppUp.put("tglcetak", hasirKerja.getTglCetak());
        Sp2dBlLsMapper.updateSp2dBlLs(sp2dBlLs);}

    @Override
    @Transactional(readOnly = false)
    public void deleteSp2dBtlLs(Sp2dBlLs sp2dBtlLs) {
        Sp2dBlLsMapper.insertBatalSp2dBtlLs(sp2dBtlLs);
        Sp2dBlLsMapper.deleteSp2dBtlLs(sp2dBtlLs);
    }
}
