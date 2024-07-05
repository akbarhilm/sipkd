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
import sipkd.entity.SpdRevMapper;
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
@Service("spdRevService")
public class SpdRevImpl implements SpdRevService {

    private static final Logger log = LoggerFactory.getLogger(SpdRevImpl.class);
    @Autowired
    private SpdRevMapper spdRevMapper;
    @Autowired
    private SkpdMapper skpdMapper;

    @Override
    public List<Skpd> getAllSkpdBtl() {
        return spdRevMapper.getAllSkpdBtl();
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
    public Map<String, BigDecimal> getPaguDanSisaRev(final Integer idSkpd, final String tahun) {
        final Map<String, BigDecimal> hasil = new LinkedHashMap<String, BigDecimal>(2);
        final Map<String, Object> param = new LinkedHashMap<String, Object>(2);
        param.put("tahun", tahun);
        param.put("idskpd", idSkpd);
        /*
         hasil.put("pagu", spdMapper.gettotalpagubyidskpddantahun(param));
         hasil.put("vspd", spdMapper.gettotalspdbyidskpddantahun(param));
         */
        final Map<String, BigDecimal> datapagu = spdRevMapper.getPaguDanSisaRev(param);
        hasil.put("pagu", (BigDecimal) datapagu.get("BTL_APBDP"));
        hasil.put("vspd", (BigDecimal) datapagu.get("SELISIH_APBDP"));
        return hasil;

    }

    @Override
    public List<SpdBTLMaster> getAnggaranBtlSkpd(final Map<String, Object> param) {
        return spdRevMapper.getAnggaranBtlSkpd(param);
    }

    @Override
    public List<SpdBTLMaster> getAnggaranBtlSkpdRev(final Map<String, Object> param) {
        return spdRevMapper.getAnggaranBtlSkpdRev(param);
    }

    @Override
    public Integer getBanyakAnggaranBtlSkpd(final Map<String, Object> param) {

        return spdRevMapper.getBanyakAnggaranBtlSkpd(param);
    }

    @Override
    public Integer getBanyakAnggaranBtlSkpdRev(final Map<String, Object> param) {

        return spdRevMapper.getBanyakAnggaranBtlSkpdRev(param);
    }

    @Override
    public Map<String, BigDecimal> getTotalAnggaranDanSpd(Map<String, Object> param) {
        final Map<String, BigDecimal> hasil = new LinkedHashMap<String, BigDecimal>(2);

        hasil.put("anggaran", spdRevMapper.getTotalAnggaranSkpd(param));
        hasil.put("spd", spdRevMapper.getTotalAnggaranSkpd(param).subtract(spdRevMapper.getTotalSPDBySKPDDanTahun(param)));
        return hasil;
    }

    @Override
    public List<SpdBtlDetail> getListSPDDetailBySKPDDanTahun(Map parameter, int addedit) {
        if (addedit == 1) {
            log.debug(" ## add ###  " + addedit);
            return spdRevMapper.getListSPDDetailBySKPDDanTahun(parameter);
        } else {
            log.debug(" ################# " + addedit);
            return spdRevMapper.getListSPDDetailBySKPDDanTahunEdit(parameter);
        }

    }

    @Override
    public List<SpdBtlDetail> getListSPDDetailBySKPDDanTahunRev(Map parameter, int addedit) {
        if (addedit == 1) {
            log.debug(" ## add ###  " + addedit);
            return spdRevMapper.getListSPDDetailBySKPDDanTahunRev(parameter);
        } else {
            log.debug(" ################# " + addedit);
            return spdRevMapper.getListSPDDetailBySKPDDanTahunEditRev(parameter);
        }

    }

    @Override
    public List<PejabatPpkd> getAllPejabatPpkd(Map parameter) {
        return spdRevMapper.getAllPejabatPpkd(parameter);
    }

    @Override
    public Integer getBanyakPejabatPPKD(Map parameter) {
        return spdRevMapper.getBanyakPejabatPPKD(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public SpdBTLMaster insertspdmaster(SpdBTLMaster parameter) {
        HariKerja hariKerja = spdRevMapper.getharikerjaspd(parameter.getTglSpd());
        if (hariKerja == null) {

        } else {
            parameter.setTglSpd(hariKerja.getTglRekam());
        }
        parameter.setNoSpd(spdRevMapper.getspdno(parameter.getTahunAnggaran()));
        parameter.setJenis("1");
        parameter.setIdSpd(spdRevMapper.getspdid());
        spdRevMapper.insertspdmaster(parameter);
        return parameter;
    }

    @Override
    public SpdBTLMaster getdataspdbtlmaster(Integer param) {
        return spdRevMapper.getdataspdbtlmasterinsert(param);
    }

    @Override
    public Integer getBanyakTotalSPDBySKPDDanTahun(Map parameter) {
        return spdRevMapper.getBanyakTotalSPDBySKPDDanTahun(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertspddetail(SpdBtlDetail spdBtlDetail) {
        spdRevMapper.insertspddetail(spdBtlDetail);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatenilaispddetail(SpdBtlDetail spdBtlDetail) {
        spdRevMapper.updatenilaispddetailbyidspdandidbtl(spdBtlDetail);
    }

    @Override
    public Integer getcekspddetailbyidspd(Integer param) {
        return spdRevMapper.getcekspddetailbyidspd(param);
    }

    @Override
    public Integer getcekspddetailbyidspdandidbtl(Map parameter) {
        return spdRevMapper.getcekspddetailbyidspdandidbtl(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public SpdBTLMaster updatespdmaster(SpdBTLMaster parameter) {
        final HariKerja hariKerja = spdRevMapper.getharikerjaspd(parameter.getTglSpd());
        if (hariKerja == null) {

        } else {
            parameter.setTglSpd(hariKerja.getTglRekam());
        }
        parameter.setJenis("1");
        log.info(" parameter " + parameter.toString());
        spdRevMapper.updatespdmaster(parameter);
        return parameter;

    }

    @Override
    @Transactional(readOnly = false)
    public void insertspdsah(Map parameter) {
        spdRevMapper.insertspdsah(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatespdsah(Map parameter) {
        spdRevMapper.updatespdsah(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatespdsahrincibtl(Map parameter) {
        spdRevMapper.updatespdsahrincibtl(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatespdsahrincibl(Map parameter) {
        spdRevMapper.updatespdsahrincibl(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatespdsahrincibantuan(Map parameter) {
        spdRevMapper.updatespdsahrincibantuan(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatespdsahrincibiaya(Map parameter) {
        spdRevMapper.updatespdsahrincibiaya(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatespdcetak(Map parameter) {

        final HariKerja hariKerja = spdRevMapper.getharikerjaspd(new Date(System.currentTimeMillis()));
        if (hariKerja == null) {
            parameter.put("tglc", new Timestamp(System.currentTimeMillis()));
        } else {

            final Date tglc = hariKerja.getTglCetak();
            parameter.put("tglc", tglc);
        }
        spdRevMapper.updatespdcetak(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatespdcetak1(Map parameter) {

        final HariKerja hariKerja = spdRevMapper.getharikerjaspd(new Date(System.currentTimeMillis()));
        if (hariKerja == null) {
            parameter.put("tglc", new Timestamp(System.currentTimeMillis()));
        } else {

            final Date tglc = hariKerja.getTglCetak();
            parameter.put("tglc", tglc);
        }
        spdRevMapper.updatespdcetak1(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertspdcetak(Map parameter) {

        final HariKerja hariKerja = spdRevMapper.getharikerjaspd(new Date(System.currentTimeMillis()));
        if (hariKerja == null) {
            parameter.put("tglc", new Timestamp(System.currentTimeMillis()));
        } else {

            final Date tglc = hariKerja.getTglCetak();
            parameter.put("tglc", tglc);
        }
        spdRevMapper.insertspdcetak(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusspdrincibtl(Integer value) {
        spdRevMapper.hapusspdrincibtl(value);
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusspdrincibtlbyakundanspd(Map parameter) {
        spdRevMapper.hapusspdrincibtlbyakundanspd(parameter);
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusspddanspdrincibtl(Integer value) {
        spdRevMapper.hapusspdrincibtlbyidspd(value);
        spdRevMapper.hapusspdbyidspd(value);

    }

    @Override
    public List<Map> getlistcetakspdbtl(Map parameter) {
        return spdRevMapper.getlistcetakspdbtl(parameter);
    }

    @Override
    public List<Map> getlistmonlap(Map parameter) {
        return spdRevMapper.getlistmonlap(parameter);
    }

    @Override
    public List<Map> getnilaiparam(Map param) {
        return spdRevMapper.getnilaiparam(param);
    }
    
     @Override
    public List<Map> getnilaiparam1(Map param) {
        return spdRevMapper.getnilaiparam1(param);
    }

    @Override
    public Integer getbanyakcetakspdbtl(Map parameter) {
        return spdRevMapper.getbanyakcetakspdbtl(parameter);
    }

    @Override
    public Integer getbanyakmonlap(Map parameter) {
        return spdRevMapper.getbanyakmonlap(parameter);
    }

    @Override
    public Map getcetakspdbyidspd(Integer value) {
        return spdRevMapper.getcetakspdbyidspd(value);
    }

    @Override
    public List<Map> getlistvalidasispd(Map parameter) {
        return spdRevMapper.getlistvalidasispd(parameter);
    }

    @Override
    public List<Map> getlistmoninitoringlaporan(Map parameter) {
        return spdRevMapper.getlistmoninitoringlaporan(parameter);
    }

    @Override
    public Integer getbanyakvalidasispd(Map parameter) {
        return spdRevMapper.getbanyakvalidasispd(parameter);
    }

    @Override
    public Map getspdsahbyidspd(Integer param) {
        return spdRevMapper.getspdsahbyidspd(param);
    }

    @Override
    public Map getspdsahid(Integer param) {
        return spdRevMapper.getspdsahid(param);
    }

    @Override
    public Map getspdsahnomor(Integer param) {
        return spdRevMapper.getspdsahnomor(param);
    }

    @Override
    public void deletespdcetak(Integer param) {
        spdRevMapper.deletespdcetak(param);
    }
    
    @Override
    public List<SpdBTLMaster> getPathFile(Map param) {
        return spdRevMapper.getPathFile(param);
    }
}
