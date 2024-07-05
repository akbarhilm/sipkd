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
import sp2d.entity.Sp2dRestitusiMapper;
import spp.model.HariKerja;
import spp.model.Skpd;
import spp.model.Sp2dLs;


@Transactional(readOnly = true)
@Service("Sp2dRestitusiServices")
public class Sp2dRestitusiImpl implements Sp2dRestitusiServices {

    private static final Logger log = LoggerFactory.getLogger(Sp2dRestitusiImpl.class);
    @Autowired
    private Sp2dRestitusiMapper Sp2dRestitusiMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<Sp2dLs> getSp2dRestitusi(Map<String, Object> param) {
        return Sp2dRestitusiMapper.getSp2dRestitusi(param);
    }

    @Override
    public Integer getCountSp2dRestitusi(Map param) {
        return Sp2dRestitusiMapper.getCountSp2dRestitusi(param);
    }

    @Override
    public Sp2dLs getSp2dRestitusiById(Map param) {
        return Sp2dRestitusiMapper.getSp2dRestitusiById(param);
    }

    @Override
    public HariKerja getHariKerjaSp2d(Date tgl) {
        return Sp2dRestitusiMapper.getHariKerjaSp2d(tgl);
    }

    @Override
    public Integer getCountHariKerjaSp2d(Date tgl) {
        return Sp2dRestitusiMapper.getCountHariKerjaSp2d(tgl);
    }

    @Override
    public List<Sp2dLs> getSp2dRinciRestitusi(Map<String, Object> param) {
        return Sp2dRestitusiMapper.getSp2dRinciRestitusi(param);
    }

    @Override
    public Integer getCountSp2dRinciRestitusi(Map param) {
        return Sp2dRestitusiMapper.getCountSp2dRinciRestitusi(param);
    }

    @Override
    public List<Sp2dLs> getKbud(Map<String, Object> param) {
        return Sp2dRestitusiMapper.getKbud(param);
    }

    @Override
    public Integer getCountKbud(Map param) {
        return Sp2dRestitusiMapper.getCountKbud(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSp2dRestitusi(Sp2dLs sp2dBtlLs) {
        final HariKerja hasilKerja = Sp2dRestitusiMapper.getHariKerjaSp2d(new Date(System.currentTimeMillis()));
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
        Sp2dRestitusiMapper.insertSp2dRestitusi(sp2dBtlLs);
    }

    @Override
    public Skpd getSkpdById(Integer idskpd) {
      return Sp2dRestitusiMapper.getSkpdById(idskpd);
    }

    @Override
    public Sp2dLs getSp2dRestitusiByIdSp2d(Map param) {
        return Sp2dRestitusiMapper.getSp2dRestitusiByIdSp2d(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSp2dRestitusi(Sp2dLs sp2dBtlLs) {
     final HariKerja hasilKerja = Sp2dRestitusiMapper.getHariKerjaSp2d(new Date(System.currentTimeMillis()));
        if (hasilKerja != null) {
            sp2dBtlLs.setTglsp2d(hasilKerja.getTglRekam());
        } else {
            sp2dBtlLs.setTglsp2d(new Date(System.currentTimeMillis()));
        }
        //sppUp.put("tglcetak", hasirKerja.getTglCetak());
        Sp2dRestitusiMapper.updateSp2dRestitusi(sp2dBtlLs);}

}
