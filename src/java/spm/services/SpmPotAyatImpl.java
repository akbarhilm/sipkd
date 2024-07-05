/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spm.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.sql.Date;
import spp.model.SpmPotAyat;
import spm.entity.SpmPotAyatMapper;

/**
 *
 * @author Xalamaster
 */
@Transactional(readOnly = true)
@Service("SpmPotAyatServices")
public class SpmPotAyatImpl implements SpmPotAyatServices {

    @Autowired
    private SpmPotAyatMapper spmpotayatMapper;

    @Override
    public List<SpmPotAyat> getAllSpm(Map<String, Object> param) {
        return spmpotayatMapper.getAllSpm(param);
    }

    @Override
    public Integer getBanyakSpm(Map<String, Object> param) {
        return spmpotayatMapper.getBanyakSpm(param);
    }

    @Override
    public List<SpmPotAyat> getAllPotAyat(Map<String, Object> param) {
        return spmpotayatMapper.getAllPotAyat(param);
    }

    @Override
    public Integer getBanyakPotAyat(Map<String, Object> param) {
        return spmpotayatMapper.getBanyakPotAyat(param);
    }

    @Override
    public Integer getNoSpm(Integer param) {
        return spmpotayatMapper.getNoSpm(param);
    }

    @Override
    public Date getTglSpm(Integer param) {
        return spmpotayatMapper.getTglSpm(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void addPotayat(SpmPotAyat spmpotayat) {
        final BigDecimal nilaiPot = spmpotayat.getNilaiPot();
        final BigDecimal nilaiPotRound = nilaiPot.setScale(0, RoundingMode.UP);
        //final Integer nilai = Integer.valueOf(nilaiPotRound.intValueExact());

        if (nilaiPotRound.compareTo(BigDecimal.ZERO) == 0) {
            //log.debug("-------------- nilai bast == 0 --------------");

        } else {
            spmpotayatMapper.addPotayat(spmpotayat);

        }
    }

    @Override
    @Transactional(readOnly = false)
    public void updatePotayat(SpmPotAyat spmpotayat) {
        spmpotayatMapper.updatePotayat(spmpotayat);
    }

    @Override
    public Integer getKodeUmk(Integer param) {
        return spmpotayatMapper.getKodeUmk(param);
    }

    @Override
    public Integer getBanyakPotAyatGaji(Map<String, Object> param) {
        return spmpotayatMapper.getBanyakPotAyatGaji(param);
    }

    @Override
    public List<SpmPotAyat> getAllPotAyatGaji(Map<String, Object> param) {
        return spmpotayatMapper.getAllPotAyatGaji(param);
    }

    @Override
    public String getMacamSimpegByIdSpp(String idspp) {
        return spmpotayatMapper.getMacamSimpegByIdSpp(idspp);
    }

    @Override
    @Transactional(readOnly = false)
    public void addPotayatGaji(List<SpmPotAyat> listRinci) {
        for (SpmPotAyat spmPotAyat : listRinci) {
            if ("add".equals(spmPotAyat.getKodeEdit()) && spmPotAyat.getNilaiPot().compareTo(BigDecimal.ZERO) == 1) {
                spmpotayatMapper.addPotayat(spmPotAyat);
                //log.debug("=========nilai dpa=========  "+spmPotAyat.getNilaiPot());
            }
            else if("edit".equals(spmPotAyat.getKodeEdit())){
                spmpotayatMapper.updatePotayat(spmPotAyat);
            }
        }
    }

    @Override
    public BigDecimal getJumKotPotSpm(Integer idspm) {
        return spmpotayatMapper.getJumKotPotSpm(idspm);
    }

}
