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
import sipkd.entity.SpdBlMapper;
import sipkd.model.HariKerja;
import sipkd.model.SpdBTLMaster;
import sipkd.model.SpdBtlDetail;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("spdBLServices")
public class SpdBLImpl implements SpdBLServices {

    private static final Logger log = LoggerFactory.getLogger(SpdBiayaImpl.class);
    @Autowired
    private SpdBlMapper spdBlMapper;

    @Override
    public Map<String, BigDecimal> getPaguDanSisaBantuanLangsung(Map<String, Object> parameter) {
        return spdBlMapper.getPaguDanSisaBantuanLangsung(parameter);
    }

    @Override
    public Integer getBanyakAnggaranBlSkpd(Map<String, Object> parameter) {
        return spdBlMapper.getBanyakAnggaranBlSkpd(parameter);
    }

    @Override
    public List<SpdBTLMaster> getAnggaranBlSkpd(Map<String, Object> parameter) {
        return spdBlMapper.getAnggaranBlSkpd(parameter);
    }

    @Override
    public Map<String, BigDecimal> getTotalAnggaranDanSpd(Map<String, Object> param) {
        final Map<String, BigDecimal> hasil = new LinkedHashMap<>(2);
        hasil.put("anggaran", spdBlMapper.getTotalAnggaranSkpd(param));
        hasil.put("spd", spdBlMapper.getTotalAnggaranSkpd(param).subtract(spdBlMapper.getTotalSPDBySKPDDanTahun(param)));
        return hasil;
    }

    @Override
    @Transactional(readOnly = false)
    public SpdBTLMaster insertspdmaster(SpdBTLMaster parameter) {
        final HariKerja hariKerja = spdBlMapper.getharikerjaspd(parameter.getTglSpd());
        if (hariKerja == null) {

        } else {
            parameter.setTglSpd(hariKerja.getTglRekam());
        }
        parameter.setNoSpd(spdBlMapper.getspdno(parameter.getTahunAnggaran()));
        parameter.setJenis("3");
        parameter.setIdSpd(spdBlMapper.getspdid());
        spdBlMapper.insertspdmaster(parameter);
        return parameter;
    }

    @Override
    @Transactional(readOnly = false)
    public SpdBTLMaster updatespdmaster(SpdBTLMaster parameter) {
        HariKerja hariKerja = spdBlMapper.getharikerjaspd(parameter.getTglSpd());
        if (hariKerja == null) {

        } else {
            parameter.setTglSpd(hariKerja.getTglRekam());
        }
        parameter.setJenis("3");
        log.debug(" parameter " + parameter.toString());
        spdBlMapper.updatespdmaster(parameter);
        return parameter;
    }

    @Override
    public SpdBTLMaster getdataspdblmaster(Integer param) {
        return spdBlMapper.getdataspdblmaster(param);
    }

    @Override
    public List<SpdBtlDetail> getListSPDDetailBySKPDDanTahun(Map parameter, int addedit) {
        if (addedit == 1) {
            log.debug(" ## add ###  " + addedit);
            return spdBlMapper.getListSPDDetailBySKPDDanTahun(parameter);
        } else {
            log.debug(" ###  EDIT ####### " + addedit);
            return spdBlMapper.getListSPDDetailBySKPDDanTahunEdit(parameter);
        }

    }

    @Override
    public Integer getBanyakTotalSPDBySKPDDanTahun(Map<String, Object> param) {
        return spdBlMapper.getBanyakTotalSPDBySKPDDanTahun(param);
    }

    @Override
    public List<SpdBtlDetail> getListSPDDetailBelanjaLangsungBySKPDDanTahun(Map parameter, int addedit) {
        if (addedit == 1) {
            log.debug(" ## add ###  " + addedit);
            return spdBlMapper.getListSPDDetailBelanjaLangsungBySKPDDanTahun(parameter);
        } else {
            log.debug(" ###  EDIT ####### " + addedit);
            return spdBlMapper.getListSPDDetailBelanjaLangsungBySKPDDanTahunEdit(parameter);
        }
    }

    @Override
    public Integer getBanyakSPDDetailBelanjaLangsungBySKPDDanTahun(Map<String, Object> param) {
        log.debug(" ## parameter kirim  ###  " + param.toString());
        return spdBlMapper.getBanyakSPDDetailBelanjaLangsungBySKPDDanTahun(param);
    }

    @Override
    public Integer getcekspddetailbyidspdandidbelanjalangsung(Map<String, Object> param) {
        log.debug(" ==> " + param.toString());
        return spdBlMapper.getcekspddetailbyidspdandidbelanjalangsung(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatenilaispddetailbyidspdandidbelanjalangsung(SpdBtlDetail spdBtlDetail) {
        log.debug(" ==> " + spdBtlDetail.getIdBtl());
        spdBlMapper.updatenilaispddetailbyidspdandidbelanjalangsung(spdBtlDetail);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertspddetailbelanjalangsung(SpdBtlDetail spdBtlDetail) {
        log.debug(" ==> " + spdBtlDetail.getIdBtl());
        spdBlMapper.insertspddetailbelanjalangsung(spdBtlDetail);
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusspdrincibelanjalangsungbyakundanspd(Map parameter) {
        spdBlMapper.hapusspdrincibelanjalangsungbyakundanspd(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusspddanspdrincibl(Integer value) {
         spdBlMapper.hapusspdrinciblbyidspd(value);
        spdBlMapper.hapusspdbyidspd(value);
       
    }

    @Override
    public BigDecimal getNilaiSPDDetailBelanjaLangsungBySKPDTahunDanIDGiat(Map<String, Object> parameter) {
        return spdBlMapper.getNilaiSPDDetailBelanjaLangsungBySKPDTahunDanIDGiat(parameter);
    }

}
