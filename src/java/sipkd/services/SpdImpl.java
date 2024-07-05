package sipkd.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sipkd.entity.SkpdMapper;
import sipkd.entity.SpdMapper;
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
@Service("spdService")
public class SpdImpl implements SpdService {

    private static final Logger log = LoggerFactory.getLogger(SpdImpl.class);
    @Autowired
    private SpdMapper spdMapper;
    @Autowired
    private SkpdMapper skpdMapper;

    @Override
    public List<Skpd> getAllSkpdBtl() {
        return spdMapper.getAllSkpdBtl();
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
         hasil.put("pagu", spdMapper.gettotalpagubyidskpddantahun(param));
         hasil.put("vspd", spdMapper.gettotalspdbyidskpddantahun(param));
         */
        final Map datapagu = spdMapper.getPaguDanSisa(param);
        hasil.put("pagu", (BigDecimal) datapagu.get("BTL"));
        hasil.put("vspd", (BigDecimal) datapagu.get("SELISIH"));
        return hasil;

    }

    @Override
    public List<SpdBTLMaster> getAnggaranBtlSkpd(final Map<String, Object> param) {
        return spdMapper.getAnggaranBtlSkpd(param);
    }

    @Override
    public Integer getBanyakAnggaranBtlSkpd(final Map<String, Object> param) {

        return spdMapper.getBanyakAnggaranBtlSkpd(param);
    }

    @Override
    public Map<String, BigDecimal> getTotalAnggaranDanSpd(Map<String, Object> param) {
        final Map<String, BigDecimal> hasil = new LinkedHashMap<String, BigDecimal>(2);

        hasil.put("anggaran", spdMapper.getTotalAnggaranSkpd(param));
        hasil.put("spd", spdMapper.getTotalAnggaranSkpd(param).subtract(spdMapper.getTotalSPDBySKPDDanTahun(param)));
        return hasil;
    }

    @Override
    public List<SpdBtlDetail> getListSPDDetailBySKPDDanTahun(Map parameter, int addedit) {
        if (addedit == 1) {
            log.debug(" ## add ###  " + addedit);
            return spdMapper.getListSPDDetailBySKPDDanTahun(parameter);
        } else {
            log.debug(" ################# " + addedit);
            return spdMapper.getListSPDDetailBySKPDDanTahunEdit(parameter);
        }

    }

    @Override
    public List<PejabatPpkd> getAllPejabatPpkd(Map parameter) {
        return spdMapper.getAllPejabatPpkd(parameter);
    }

    @Override
    public Integer getBanyakPejabatPPKD(Map parameter) {
        return spdMapper.getBanyakPejabatPPKD(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public SpdBTLMaster insertspdmaster(SpdBTLMaster parameter) {
        HariKerja hariKerja = spdMapper.getharikerjaspd(parameter.getTglSpd());
        if (hariKerja == null) {

        } else {
            parameter.setTglSpd(hariKerja.getTglRekam());
        }
        parameter.setNoSpd(spdMapper.getspdno(parameter.getTahunAnggaran()));
        parameter.setJenis("1");
        parameter.setIdSpd(spdMapper.getspdid());
        spdMapper.insertspdmaster(parameter);
        return parameter;
    }

    @Override
    public SpdBTLMaster getdataspdbtlmaster(Integer param) {
        return spdMapper.getdataspdbtlmasterinsert(param);
    }

    @Override
    public Integer getBanyakTotalSPDBySKPDDanTahun(Map parameter) {
        return spdMapper.getBanyakTotalSPDBySKPDDanTahun(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertspddetail(SpdBtlDetail spdBtlDetail) {
        spdMapper.insertspddetail(spdBtlDetail);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatenilaispddetail(SpdBtlDetail spdBtlDetail) {
        spdMapper.updatenilaispddetailbyidspdandidbtl(spdBtlDetail);
    }

    @Override
    public Integer getcekspddetailbyidspd(Integer param) {
        return spdMapper.getcekspddetailbyidspd(param);
    }

    @Override
    public Integer getcekspddetailbyidspdandidbtl(Map parameter) {
        return spdMapper.getcekspddetailbyidspdandidbtl(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public SpdBTLMaster updatespdmaster(SpdBTLMaster parameter) {
        final HariKerja hariKerja = spdMapper.getharikerjaspd(parameter.getTglSpd());
        if (hariKerja == null) {

        } else {
            parameter.setTglSpd(hariKerja.getTglRekam());
        }
        parameter.setJenis("1");
        log.info(" parameter " + parameter.toString());
        spdMapper.updatespdmaster(parameter);
        return parameter;

    }

    @Override
    @Transactional(readOnly = false)
    public void insertspdsah(Map parameter) {
        spdMapper.insertspdsah(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertspdcetak(Map parameter) {

        final HariKerja hariKerja = spdMapper.getharikerjaspd(new Date(System.currentTimeMillis()));
        if (hariKerja == null) {
            parameter.put("tglc", new Timestamp(System.currentTimeMillis()));
        } else {

            final Date tglc = hariKerja.getTglCetak();
            parameter.put("tglc", tglc);
        }
        spdMapper.insertspdcetak(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusspdrincibtl(Integer value) {
        spdMapper.hapusspdrincibtl(value);
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusspdrincibtlbyakundanspd(Map parameter) {
        spdMapper.hapusspdrincibtlbyakundanspd(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusspddanspdrincibtl(Integer value) {
        spdMapper.hapusspdrincibtlbyidspd(value);
        spdMapper.hapusspdbyidspd(value);

    }

    @Override
    public List<Map> getlistcetakspdbtl(Map parameter) {
        return spdMapper.getlistcetakspdbtl(parameter);
    }

    @Override
    public List<Map> getnilaiparam(Map param) {
        return spdMapper.getnilaiparam(param);
    }
    
     @Override
    public List<Map> getnilaiparam1(Map param) {
        return spdMapper.getnilaiparam1(param);
    }

    @Override
    public Integer getbanyakcetakspdbtl(Map parameter) {
        return spdMapper.getbanyakcetakspdbtl(parameter);
    }

    @Override
    public Map getcetakspdbyidspd(Integer value) {
        return spdMapper.getcetakspdbyidspd(value);
    }

    @Override
    public List<Map> getlistvalidasispd(Map parameter) {
        return spdMapper.getlistvalidasispd(parameter);
    }

    @Override
    public Integer getbanyakvalidasispd(Map parameter) {
        return spdMapper.getbanyakvalidasispd(parameter);
    }

    @Override
    public Map getspdsahbyidspd(Integer param) {
        return spdMapper.getspdsahbyidspd(param);
    }
    
     @Override
    public Map getspdsahid(Integer param) {
        return spdMapper.getspdsahid(param);
    }
    
     @Override
    public Map getspdsahnomor(Integer param) {
        return spdMapper.getspdsahnomor(param);
    }

    @Override
    public void deletespdcetak(Integer param) {
        spdMapper.deletespdcetak(param);
    }

    @Override
    public List<Skpd> getAllSkpdBTL(Map<String, Object> param) {
      return skpdMapper.getAllSkpdBTL(param);
    }

    @Override
    public Integer getBanyakAllSkpdBTL(Map<String, Object> param) {
    return skpdMapper.getBanyakAllSkpdBTL(param);
    }
    
      @Override
    public String getPathPdf(Map param) {
        return spdMapper.getPathPdf(param);
    }
    
     @Override
    public List<SpdBTLMaster> getPathFile(Map param) {
        return spdMapper.getPathFile(param);
    }
    
}
