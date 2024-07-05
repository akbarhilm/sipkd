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
import sipkd.entity.SpdBiayaRevMapper;
import sipkd.model.HariKerja;
import sipkd.model.PejabatPpkd;
import sipkd.model.Skpd;
import sipkd.model.SpdBTLMaster;
import sipkd.model.SpdBtlDetail;

/**
 *
 * @author User
 */
@Transactional(readOnly = true)
@Service("spdBiayaRevService")
public class SpdBiayaRevImpl implements SpdBiayaRevService {

    private static final Logger log = LoggerFactory.getLogger(SpdBiayaRevImpl.class);
    @Autowired
    private SpdBiayaRevMapper spdBiayaRevMapper;
    @Autowired
    private SkpdMapper skpdMapper;

    @Override
    public List<Skpd> getAllSkpdBtl() {
        return spdBiayaRevMapper.getAllSkpdBtl();
    }

    @Override
    public List<Skpd> getAllSkpd(final Map<String, Object> param) {
        return skpdMapper.getAllSkpd(param);
    }

    @Override
    public Integer getBanyakAllSkpd(final Map<String, Object> param) {
        return skpdMapper.getBanyakAllSkpd(param);
    }

    @Override
    public Map<String, BigDecimal> getPaguDanSisa(final Integer idSkpd, final String tahun) {
        final Map<String, BigDecimal> hasil = new LinkedHashMap<String, BigDecimal>(2);
        final Map<String, Object> param = new LinkedHashMap<String, Object>(2);
        param.put("tahun", tahun);
        param.put("idskpd", idSkpd);
        /*
         hasil.put("pagu", spdBiayaMapper.gettotalpagubyidskpddantahun(param));
         hasil.put("vspd", spdBiayaMapper.gettotalspdbyidskpddantahun(param));
         */
        final Map datapagu = spdBiayaRevMapper.getPaguDanSisaBiaya(param);
        hasil.put("pagu", (BigDecimal) datapagu.get("BTL"));
        hasil.put("vspd", (BigDecimal) datapagu.get("SELISIH"));
        return hasil;

    }

    @Override
    public List<SpdBTLMaster> getAnggaranBtlSkpd(final Map<String, Object> param) {
        return spdBiayaRevMapper.getAnggaranBtlSkpd(param);
    }

    @Override
    public Integer getBanyakAnggaranBtlSkpd(final Map<String, Object> param) {

        return spdBiayaRevMapper.getBanyakAnggaranBtlSkpd(param);
    }

    @Override
    public Map<String, BigDecimal> getTotalAnggaranDanSpd(Map<String, Object> param) {
        final Map<String, BigDecimal> hasil = new LinkedHashMap<String, BigDecimal>(2);

        hasil.put("anggaran", spdBiayaRevMapper.getTotalAnggaranSkpd(param));
        hasil.put("spd", spdBiayaRevMapper.getTotalAnggaranSkpd(param).subtract(spdBiayaRevMapper.getTotalSPDBySKPDDanTahun(param)));
        return hasil;
    }

    @Override
    public List<SpdBtlDetail> getListSPDDetailBySKPDDanTahun(Map parameter, int addedit) {
        if (addedit == 1) {
            log.debug(" ## add ###  " + addedit);
            return spdBiayaRevMapper.getListSPDDetailBySKPDDanTahun(parameter);
        } else {
            log.debug(" ################# " + addedit);
            return spdBiayaRevMapper.getListSPDDetailBySKPDDanTahunEdit(parameter);
        }

    }

    @Override
    public List<PejabatPpkd> getAllPejabatPpkd(Map parameter) {
        return spdBiayaRevMapper.getAllPejabatPpkd(parameter);
    }

    @Override
    public Integer getBanyakPejabatPPKD(Map parameter) {
        return spdBiayaRevMapper.getBanyakPejabatPPKD(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public SpdBTLMaster insertspdmaster(SpdBTLMaster parameter) {
        final HariKerja hariKerja = spdBiayaRevMapper.getharikerjaspd(parameter.getTglSpd());
        if (hariKerja == null) {

        } else {
            parameter.setTglSpd(hariKerja.getTglRekam());
        }
        parameter.setNoSpd(spdBiayaRevMapper.getspdno(parameter.getTahunAnggaran()));
        parameter.setJenis("4");
        parameter.setIdSpd(spdBiayaRevMapper.getspdid());
        spdBiayaRevMapper.insertspdmaster(parameter);
        return parameter;
    }

    @Override
    public SpdBTLMaster getdataspdbtlmaster(Integer param) {
        return spdBiayaRevMapper.getdataspdbtlmasterinsert(param);
    }

    @Override
    public Integer getBanyakTotalSPDBySKPDDanTahun(Map parameter) {
       /* int banyak = 0;
        try {
            log.debug("paran =      "+parameter.toString());
            banyak = spdBiayaMapper.getBanyakTotalSPDBySKPDDanTahun(parameter);
        } catch (Exception e) {
            e.printStackTrace();
            
        }*/
        return spdBiayaRevMapper.getBanyakTotalSPDBySKPDDanTahun(parameter); 
    }

    @Override
    @Transactional(readOnly = false)
    public void insertspddetail(SpdBtlDetail spdBtlDetail) {
        spdBiayaRevMapper.insertspddetail(spdBtlDetail);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatenilaispddetail(SpdBtlDetail spdBtlDetail) {
        spdBiayaRevMapper.updatenilaispddetailbyidspdandidbtl(spdBtlDetail);
    }

    @Override
    public Integer getcekspddetailbyidspd(Integer param) {
        return spdBiayaRevMapper.getcekspddetailbyidspd(param);
    }

    @Override
    public Integer getcekspddetailbyidspdandidbtl(Map parameter) {
        return spdBiayaRevMapper.getcekspddetailbyidspdandidbtl(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public SpdBTLMaster updatespdmaster(SpdBTLMaster parameter) {
        final HariKerja hariKerja = spdBiayaRevMapper.getharikerjaspd(parameter.getTglSpd());
        if (hariKerja == null) {

        } else {
            parameter.setTglSpd(hariKerja.getTglRekam());
        }
        parameter.setJenis("4");
        log.info(" parameter " + parameter.toString());
        spdBiayaRevMapper.updatespdmaster(parameter);
        return parameter;

    }

    @Override
    @Transactional(readOnly = false)
    public void insertspdsah(Map parameter) {
        spdBiayaRevMapper.insertspdsah(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertspdcetak(Map parameter) {
        spdBiayaRevMapper.insertspdcetak(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusspdrincibtl(Integer value) {
        spdBiayaRevMapper.hapusspdrincibtl(value);
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusspdrincibtlbyakundanspd(Map parameter) {
        spdBiayaRevMapper.hapusspdrincibtlbyakundanspd(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusspddanspdrincibtl(Integer value) {
        spdBiayaRevMapper.hapusspdrincibtlbyidspd(value);
        spdBiayaRevMapper.hapusspdbyidspd(value);
        
    }

    @Override
    public List<Map> getlistcetakspdbtl(Map parameter) {
        return spdBiayaRevMapper.getlistcetakspdbtl(parameter);
    }

    @Override
    public Integer getbanyakcetakspdbtl(Map parameter) {
        return spdBiayaRevMapper.getbanyakcetakspdbtl(parameter);
    }

    @Override
    public Map getcetakspdbyidspd(Integer value) {
        return spdBiayaRevMapper.getcetakspdbyidspd(value);
    }

    @Override
    public List<Map> getlistvalidasispdbtl(Map parameter) {
        return spdBiayaRevMapper.getlistvalidasispdbtl(parameter);
    }

    @Override
    public Integer getbanyakvalidasispdbtl(Map parameter) {
        return spdBiayaRevMapper.getbanyakvalidasispdbtl(parameter);
    }
}
