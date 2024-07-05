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
import sp2d.entity.Sp2dUpGuMapper;
import spp.model.HariKerja;
import spp.model.Skpd;
import spp.model.Sp2dLs;


@Transactional(readOnly = true)
@Service("Sp2dUpGuServices")
public class Sp2dUpGuImpl implements Sp2dUpGuServices {

    private static final Logger log = LoggerFactory.getLogger(Sp2dUpGuImpl.class);
    @Autowired
    private Sp2dUpGuMapper Sp2dUpGuMapper;
    @Autowired
    private GenNumberMapper genNumberMapper;

    @Override
    public List<Sp2dLs> getSp2dUpGu(Map<String, Object> param) {
        return Sp2dUpGuMapper.getSp2dUpGu(param);
    }

    @Override
    public Integer getCountSp2dUpGu(Map param) {
        return Sp2dUpGuMapper.getCountSp2dUpGu(param);
    }

    @Override
    public Sp2dLs getSp2dUpGuById(Map param) {
        return Sp2dUpGuMapper.getSp2dUpGuById(param);
    }

    @Override
    public HariKerja getHariKerjaSp2d(Date tgl) {
        return Sp2dUpGuMapper.getHariKerjaSp2d(tgl);
    }

    @Override
    public Integer getCountHariKerjaSp2d(Date tgl) {
        return Sp2dUpGuMapper.getCountHariKerjaSp2d(tgl);
    }

    @Override
    public List<Sp2dLs> getSp2dRinciUpGu(Map<String, Object> param) {
        return Sp2dUpGuMapper.getSp2dRinciUpGu(param);
    }

    @Override
    public Integer getCountSp2dRinciUpGu(Map param) {
        return Sp2dUpGuMapper.getCountSp2dRinciUpGu(param);
    }

    @Override
    public List<Sp2dLs> getKbud(Map<String, Object> param) {
        return Sp2dUpGuMapper.getKbud(param);
    }

    @Override
    public Integer getCountKbud(Map param) {
        return Sp2dUpGuMapper.getCountKbud(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSp2dUpGu(Sp2dLs sp2dUpGu) {
        final HariKerja hasilKerja = Sp2dUpGuMapper.getHariKerjaSp2d(new Date(System.currentTimeMillis()));
        if (hasilKerja != null) {
            sp2dUpGu.setTglsp2d(hasilKerja.getTglRekam());
        } else {
            sp2dUpGu.setTglsp2d(new Date(System.currentTimeMillis()));
        }
        final Map param = new HashMap();
        param.put("tahun", sp2dUpGu.getTahun());
        param.put("wilsp2d", sp2dUpGu.getKodeWilayah());
        sp2dUpGu.setNoSp2d(genNumberMapper.getSppdNo(param));
        //sppUp.put("tglcetak", hasirKerja.getTglCetak());
        Sp2dUpGuMapper.insertSp2dUpGu(sp2dUpGu);
    }

    @Override
    public Skpd getSkpdById(Integer idskpd) {
      return Sp2dUpGuMapper.getSkpdById(idskpd);
    }

    @Override
    public Sp2dLs getSp2dUpGuByIdSp2d(Map param) {
        return Sp2dUpGuMapper.getSp2dUpGuByIdSp2d(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSp2dUpGu(Sp2dLs sp2dUpGu) {
     final HariKerja hasilKerja = Sp2dUpGuMapper.getHariKerjaSp2d(new Date(System.currentTimeMillis()));
        if (hasilKerja != null) {
            sp2dUpGu.setTglsp2d(hasilKerja.getTglRekam());
        } else {
            sp2dUpGu.setTglsp2d(new Date(System.currentTimeMillis()));
        }
        //sppUp.put("tglcetak", hasirKerja.getTglCetak());
        Sp2dUpGuMapper.updateSp2dUpGu(sp2dUpGu);}
    
    @Override
    @Transactional(readOnly = false)
    public void deleteSp2dBtlLs(Sp2dLs sp2dBtlLs) {
        Sp2dUpGuMapper.insertBatalSp2dBtlLs(sp2dBtlLs);
        Sp2dUpGuMapper.deleteSp2dBtlLs(sp2dBtlLs);
    }

}
