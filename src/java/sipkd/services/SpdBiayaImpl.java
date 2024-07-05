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
@Service("spdBiayaService")
public class SpdBiayaImpl implements SpdBiayaService {

    private static final Logger log = LoggerFactory.getLogger(SpdBiayaImpl.class);
    @Autowired
    private SpdBiayaMapper spdBiayaMapper;
    @Autowired
    private SkpdMapper skpdMapper;

    @Override
    public List<Skpd> getAllSkpdBtl() {
        return spdBiayaMapper.getAllSkpdBtl();
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
        final Map datapagu = spdBiayaMapper.getPaguDanSisaBiaya(param);
        hasil.put("pagu", (BigDecimal) datapagu.get("BTL"));
        hasil.put("vspd", (BigDecimal) datapagu.get("SELISIH"));
        return hasil;

    }

    @Override
    public List<SpdBTLMaster> getAnggaranBtlSkpd(final Map<String, Object> param) {
        return spdBiayaMapper.getAnggaranBtlSkpd(param);
    }

    @Override
    public Integer getBanyakAnggaranBtlSkpd(final Map<String, Object> param) {

        return spdBiayaMapper.getBanyakAnggaranBtlSkpd(param);
    }

    @Override
    public Map<String, BigDecimal> getTotalAnggaranDanSpd(Map<String, Object> param) {
        final Map<String, BigDecimal> hasil = new LinkedHashMap<String, BigDecimal>(2);

        hasil.put("anggaran", spdBiayaMapper.getTotalAnggaranSkpd(param));
        hasil.put("spd", spdBiayaMapper.getTotalAnggaranSkpd(param).subtract(spdBiayaMapper.getTotalSPDBySKPDDanTahun(param)));
        return hasil;
    }

    @Override
    public List<SpdBtlDetail> getListSPDDetailBySKPDDanTahun(Map parameter, int addedit) {
        
        if (addedit == 1) {
            log.debug(" ####### getListSPDDetailBySKPDDanTahun add ##########  " + addedit);
            return spdBiayaMapper.getListSPDDetailBySKPDDanTahun(parameter);
        } else {
            log.debug(" ####### getListSPDDetailBySKPDDanTahun edit ########## " + addedit);
            return spdBiayaMapper.getListSPDDetailBySKPDDanTahunEdit(parameter);
        }

    }

    @Override
    public List<PejabatPpkd> getAllPejabatPpkd(Map parameter) {
        return spdBiayaMapper.getAllPejabatPpkd(parameter);
    }

    @Override
    public Integer getBanyakPejabatPPKD(Map parameter) {
        return spdBiayaMapper.getBanyakPejabatPPKD(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public SpdBTLMaster insertspdmaster(SpdBTLMaster parameter) {
        final HariKerja hariKerja = spdBiayaMapper.getharikerjaspd(parameter.getTglSpd());
        if (hariKerja == null) {

        } else {
            parameter.setTglSpd(hariKerja.getTglRekam());
        }
        parameter.setNoSpd(spdBiayaMapper.getspdno(parameter.getTahunAnggaran()));
        parameter.setJenis("4");
        parameter.setIdSpd(spdBiayaMapper.getspdid());
        spdBiayaMapper.insertspdmaster(parameter);
        return parameter;
    }

    @Override
    public SpdBTLMaster getdataspdbtlmaster(Integer param) {
        return spdBiayaMapper.getdataspdbtlmasterinsert(param);
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
        return spdBiayaMapper.getBanyakTotalSPDBySKPDDanTahun(parameter); 
    }

    @Override
    @Transactional(readOnly = false)
    public void insertspddetail(SpdBtlDetail spdBtlDetail) {
        spdBiayaMapper.insertspddetail(spdBtlDetail);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatenilaispddetail(SpdBtlDetail spdBtlDetail) {
        spdBiayaMapper.updatenilaispddetailbyidspdandidbtl(spdBtlDetail);
    }

    @Override
    public Integer getcekspddetailbyidspd(Integer param) {
        return spdBiayaMapper.getcekspddetailbyidspd(param);
    }

    @Override
    public Integer getcekspddetailbyidspdandidbtl(Map parameter) {
        return spdBiayaMapper.getcekspddetailbyidspdandidbtl(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public SpdBTLMaster updatespdmaster(SpdBTLMaster parameter) {
        final HariKerja hariKerja = spdBiayaMapper.getharikerjaspd(parameter.getTglSpd());
        if (hariKerja == null) {

        } else {
            parameter.setTglSpd(hariKerja.getTglRekam());
        }
        parameter.setJenis("4");
        log.info(" parameter " + parameter.toString());
        spdBiayaMapper.updatespdmaster(parameter);
        return parameter;

    }

    @Override
    @Transactional(readOnly = false)
    public void insertspdsah(Map parameter) {
        spdBiayaMapper.insertspdsah(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertspdcetak(Map parameter) {
        spdBiayaMapper.insertspdcetak(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusspdrincibtl(Integer value) {
        spdBiayaMapper.hapusspdrincibtl(value);
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusspdrincibtlbyakundanspd(Map parameter) {
        spdBiayaMapper.hapusspdrincibtlbyakundanspd(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusspddanspdrincibtl(Integer value) {
        spdBiayaMapper.hapusspdrincibtlbyidspd(value);
        spdBiayaMapper.hapusspdbyidspd(value);
        
    }

    @Override
    public List<Map> getlistcetakspdbtl(Map parameter) {
        return spdBiayaMapper.getlistcetakspdbtl(parameter);
    }

    @Override
    public Integer getbanyakcetakspdbtl(Map parameter) {
        return spdBiayaMapper.getbanyakcetakspdbtl(parameter);
    }

    @Override
    public Map getcetakspdbyidspd(Integer value) {
        return spdBiayaMapper.getcetakspdbyidspd(value);
    }

    @Override
    public List<Map> getlistvalidasispdbtl(Map parameter) {
        return spdBiayaMapper.getlistvalidasispdbtl(parameter);
    }

    @Override
    public Integer getbanyakvalidasispdbtl(Map parameter) {
        return spdBiayaMapper.getbanyakvalidasispdbtl(parameter);
    }
}
