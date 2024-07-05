package sipkd.services;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sipkd.entity.SpdBlRevMapper;
import sipkd.model.HariKerja;
import sipkd.model.SpdBTLMaster;
import sipkd.model.SpdBtlDetail;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("spdBLRevServices")
public class SpdBLRevImpl implements SpdBLRevServices {

    private static final Logger log = LoggerFactory.getLogger(SpdBiayaImpl.class);
    @Autowired
    private SpdBlRevMapper spdBlRevMapper;

    @Override
    public Map<String, BigDecimal> getPaguDanSisaBantuanLangsung(Map<String, Object> parameter) {
        return spdBlRevMapper.getPaguDanSisaBantuanLangsung(parameter);
    }

    @Override
    public Integer getBanyakAnggaranBlSkpd(Map<String, Object> parameter) {
        return spdBlRevMapper.getBanyakAnggaranBlSkpd(parameter);
    }

    @Override
    public List<SpdBTLMaster> getAnggaranBlSkpd(Map<String, Object> parameter) {
        return spdBlRevMapper.getAnggaranBlSkpd(parameter);
    }

    @Override
    public Map<String, BigDecimal> getTotalAnggaranDanSpd(Map<String, Object> param) {
        final Map<String, BigDecimal> hasil = new LinkedHashMap<>(2);
        hasil.put("anggaran", spdBlRevMapper.getTotalAnggaranSkpd(param));
        hasil.put("spd", spdBlRevMapper.getTotalAnggaranSkpd(param).subtract(spdBlRevMapper.getTotalSPDBySKPDDanTahun(param)));
        return hasil;
    }

    @Override
    @Transactional(readOnly = false)
    public SpdBTLMaster insertspdmaster(SpdBTLMaster parameter) {
        final HariKerja hariKerja = spdBlRevMapper.getharikerjaspd(parameter.getTglSpd());
        if (hariKerja == null) {

        } else {
            parameter.setTglSpd(hariKerja.getTglRekam());
        }
        parameter.setNoSpd(spdBlRevMapper.getspdno(parameter.getTahunAnggaran()));
        parameter.setJenis("3");
        parameter.setIdSpd(spdBlRevMapper.getspdid());
        spdBlRevMapper.insertspdmaster(parameter);
        return parameter;
    }

    @Override
    @Transactional(readOnly = false)
    public SpdBTLMaster updatespdmaster(SpdBTLMaster parameter) {
        HariKerja hariKerja = spdBlRevMapper.getharikerjaspd(parameter.getTglSpd());
        if (hariKerja == null) {

        } else {
            parameter.setTglSpd(hariKerja.getTglRekam());
        }
        parameter.setJenis("3");
        log.debug(" parameter " + parameter.toString());
        spdBlRevMapper.updatespdmaster(parameter);
        return parameter;
    }

    @Override
    public SpdBTLMaster getdataspdblmaster(Integer param) {
        return spdBlRevMapper.getdataspdblmaster(param);
    }

    @Override
    public List<SpdBtlDetail> getListSPDDetailBySKPDDanTahun(Map parameter, int addedit) {
        if (addedit == 1) {
            log.debug(" ## add ###  " + addedit);
            return spdBlRevMapper.getListSPDDetailBySKPDDanTahun(parameter);
        } else {
            log.debug(" ###  EDIT ####### " + addedit);
            return spdBlRevMapper.getListSPDDetailBySKPDDanTahunEdit(parameter);
        }

    }

    @Override
    public Integer getBanyakTotalSPDBySKPDDanTahun(Map<String, Object> param, int addedit) {
        if (addedit == 1) {
            log.debug(" ## add ###  " + addedit);
            return spdBlRevMapper.getBanyakTotalSPDBySKPDDanTahun(param);
        } else {
            log.debug(" ###  EDIT ####### " + addedit);
            return spdBlRevMapper.getBanyakTotalSPDBySKPDDanTahunEdit(param);
        }
        
    }

    @Override
    public List<SpdBtlDetail> getListSPDDetailBelanjaLangsungBySKPDDanTahun(Map parameter, int addedit) {
        if (addedit == 1) {
            log.debug(" ## add ###  " + addedit);
            return spdBlRevMapper.getListSPDDetailBelanjaLangsungBySKPDDanTahun(parameter);
        } else {
            log.debug(" ###  EDIT ####### " + addedit);
            return spdBlRevMapper.getListSPDDetailBelanjaLangsungBySKPDDanTahunEdit(parameter);
        }
    }

    @Override
    public Integer getBanyakSPDDetailBelanjaLangsungBySKPDDanTahun(Map<String, Object> param, int addedit) {
        log.debug(" ## parameter kirim  ###  " + param.toString());
        if (addedit == 1) {
            log.debug(" ## add ###  " + addedit);
            return spdBlRevMapper.getBanyakSPDDetailBelanjaLangsungBySKPDDanTahun(param);
        } else {
            log.debug(" ###  EDIT ####### " + addedit);
            return spdBlRevMapper.getBanyakSPDDetailBelanjaLangsungBySKPDDanTahunEdit(param);
        }
        
    }

    @Override
    public Integer getcekspddetailbyidspdandidbelanjalangsung(Map<String, Object> param) {
        log.debug(" ==> " + param.toString());
        return spdBlRevMapper.getcekspddetailbyidspdandidbelanjalangsung(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatenilaispddetailbyidspdandidbelanjalangsung(SpdBtlDetail spdBtlDetail) {
        log.debug(" ==> " + spdBtlDetail.getIdBtl());
        spdBlRevMapper.updatenilaispddetailbyidspdandidbelanjalangsung(spdBtlDetail);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertspddetailbelanjalangsung(SpdBtlDetail spdBtlDetail) {
        log.debug(" ==> " + spdBtlDetail.getIdBtl());
        spdBlRevMapper.insertspddetailbelanjalangsung(spdBtlDetail);
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusspdrincibelanjalangsungbyakundanspd(Map parameter) {
        spdBlRevMapper.hapusspdrincibelanjalangsungbyakundanspd(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusspddanspdrincibl(Integer value) {
         spdBlRevMapper.hapusspdrinciblbyidspd(value);
        spdBlRevMapper.hapusspdbyidspd(value);
       
    }

    @Override
    public BigDecimal getNilaiSPDDetailBelanjaLangsungBySKPDTahunDanIDGiat(Map<String, Object> parameter) {
        return spdBlRevMapper.getNilaiSPDDetailBelanjaLangsungBySKPDTahunDanIDGiat(parameter);
    }

}
