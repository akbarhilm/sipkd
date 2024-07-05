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
import sipkd.entity.SkpdMapper;
import sipkd.entity.SpdBiayaMapper;
import sipkd.entity.SpdBtlBantuanMapper;
import sipkd.model.HariKerja;
import sipkd.model.Skpd;
import sipkd.model.SpdBTLMaster;
import sipkd.model.SpdBtlDetail;

/**
 *
 * @author Admin
 */
@Transactional(readOnly = true)
@Service("spdBtlBantuanServices")
public class SpdBtlBantuanImpl implements SpdBtlBantuanServices {

    private static final Logger log = LoggerFactory.getLogger(SpdBtlBantuanImpl.class);
    @Autowired
    private SpdBtlBantuanMapper spdBtlBantuanMapper;
    @Autowired
    private SkpdMapper skpdMapper;

    @Autowired
    private SpdBiayaMapper spdBiayaMapper;

    @Override
    public List<Skpd> getAllSkpdBtlBantuan(Map parameter) {
        return spdBtlBantuanMapper.getAllSkpdBtlBantuan(parameter);
    }

    @Override
    public Skpd getAllSkpdById(Integer id) {
        return spdBtlBantuanMapper.getAllSkpdById(id);
    }

    @Override
    public Map getPaguDanSisaBtlBantuan(final Integer idSkpd, final String tahun) {
        final Map<String, BigDecimal> hasil = new LinkedHashMap<>(2);
        final Map<String, Object> param = new LinkedHashMap<>(2);
        param.put("tahun", tahun);
        param.put("idskpd", idSkpd);
        final Map datapagu = spdBtlBantuanMapper.getPaguDanSisaBtlBantuan(param);
        if (datapagu != null && !datapagu.isEmpty()) {
            hasil.put("pagu", (BigDecimal) datapagu.get("BTL"));
            hasil.put("vspd", (BigDecimal) datapagu.get("SELISIH"));
        } else {
            hasil.put("pagu", new BigDecimal(0));
            hasil.put("vspd", new BigDecimal(0));
        }
        return hasil;
    }

    @Override
    public Integer getBanyakAnggaranBtlBantuanSkpd(Map parameter) {
        return spdBtlBantuanMapper.getBanyakAnggaranBtlBantuanSkpd(parameter);
    }

    @Override
    public List<SpdBTLMaster> getAnggaranBtlBantuanSkpd(Map parameter) {
        return spdBtlBantuanMapper.getAnggaranBtlBantuanSkpd(parameter);
    }

    @Override
    public Integer getBanyakTotalSPDBySKPDDanTahun(Map<String, Object> param) {
          log.info(" getBanyakTotalSPDBySKPDDanTahun  " +spdBtlBantuanMapper.getBanyakTotalSPDBySKPDDanTahun(param));
        return spdBtlBantuanMapper.getBanyakTotalSPDBySKPDDanTahun(param);
    }

    @Override
    public List<SpdBtlDetail> getListSPDDetailBySKPDDanTahun(Map parameter, int addedit) {
        if (addedit == 1) {
            log.debug(" ## add ###  " + addedit);
            return spdBtlBantuanMapper.getListSPDDetailBySKPDDanTahun(parameter);
        } else {
            log.debug(" ################# " + addedit);
            return spdBtlBantuanMapper.getListSPDDetailBySKPDDanTahunEdit(parameter);
        }
    }

    @Override
    public Map<String, BigDecimal> getTotalAnggaranDanSpd(Map<String, Object> param) {
        final Map<String, BigDecimal> hasil = new LinkedHashMap<String, BigDecimal>(2);

        hasil.put("anggaran", spdBtlBantuanMapper.getTotalAnggaranSkpd(param));
        hasil.put("spd", spdBtlBantuanMapper.getTotalAnggaranSkpd(param).subtract(spdBtlBantuanMapper.getTotalSPDBySKPDDanTahun(param)));
        return hasil;
    }

    @Override
    public Integer getBanyakSkpdBtlBantuan(Map parameter) {
        return spdBtlBantuanMapper.getBanyakSkpdBtlBantuan(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public SpdBTLMaster updatespdmaster(SpdBTLMaster parameter) {
        final HariKerja hariKerja = spdBiayaMapper.getharikerjaspd(parameter.getTglSpd());
        if (hariKerja == null) {

        } else {
            parameter.setTglSpd(hariKerja.getTglRekam());
        }
        parameter.setJenis("2");
        log.info(" parameter " + parameter.toString());
        // disimpan di spdBiayaMapper karena sama ke tmspd
        spdBiayaMapper.updatespdmaster(parameter);
        return parameter;
    }

    @Override
    @Transactional(readOnly = false)
    public SpdBTLMaster insertspdmaster(SpdBTLMaster parameter) {
        HariKerja hariKerja = spdBiayaMapper.getharikerjaspd(parameter.getTglSpd());
        if (hariKerja == null) {

        } else {
            parameter.setTglSpd(hariKerja.getTglRekam());
        }
        parameter.setNoSpd(spdBiayaMapper.getspdno(parameter.getTahunAnggaran()));
        parameter.setJenis("2");
        parameter.setIdSpd(spdBiayaMapper.getspdid());
        // disimpan di spdBiayaMapper karena sama ke tmspd
        spdBiayaMapper.insertspdmaster(parameter);
        return parameter;
    }

    @Override
    public SpdBTLMaster getdataspdbtlbatuanmaster(Integer id) {
        return spdBtlBantuanMapper.getdataspdbtlbatuanmaster(id);
    }

    @Override
    public Integer getcekspddetailbyidspdandidbtl(Map parameter) {
        return spdBtlBantuanMapper.getcekspddetailbyidspdandidbtl(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatenilaispddetail(SpdBtlDetail spdBtlDetail) {
        spdBtlBantuanMapper.updatenilaispddetailbyidspdandidbtl(spdBtlDetail);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertspddetail(SpdBtlDetail spdBtlDetail) {
        spdBtlBantuanMapper.insertspddetail(spdBtlDetail);
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusspdrincibtlbyakundanspd(Map parameter) {
        spdBtlBantuanMapper.hapusspdrincibtlbyakundanspd(parameter);
    }

    @Override
    public void hapusspddanspdrincibtl(Integer value) {
        spdBtlBantuanMapper.hapusspdrincibtlbyidspd(value);
        spdBtlBantuanMapper.hapusspdbyidspd(value);

    }

}
