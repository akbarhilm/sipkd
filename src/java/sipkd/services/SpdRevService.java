package sipkd.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import sipkd.model.PejabatPpkd;
import sipkd.model.Skpd;
import sipkd.model.SpdBTLMaster;
import sipkd.model.SpdBtlDetail;

/**
 * SPD BTL
 * @author User
 */
public interface SpdRevService {

    List<Skpd> getAllSkpd(Map<String, Object> param);

    Integer getBanyakAllSkpd(Map<String, Object> param);

    List<Skpd> getAllSkpdBtl();

    Map<String, BigDecimal> getPaguDanSisaRev(final Integer idSkpd, final String tahun);

    List<SpdBTLMaster> getAnggaranBtlSkpd(final Map<String, Object> param);
    
    List<SpdBTLMaster> getAnggaranBtlSkpdRev(final Map<String, Object> param);

    Integer getBanyakAnggaranBtlSkpd(final Map<String, Object> param);
    
    Integer getBanyakAnggaranBtlSkpdRev(final Map<String, Object> param);

    Map<String, BigDecimal> getTotalAnggaranDanSpd(final Map<String, Object> param);

    List<SpdBtlDetail> getListSPDDetailBySKPDDanTahun(Map parameter, int addedit);
    
    List<SpdBtlDetail> getListSPDDetailBySKPDDanTahunRev(Map parameter, int addedit);

    List<PejabatPpkd> getAllPejabatPpkd(Map parameter);

    Integer getBanyakPejabatPPKD(Map parameter);

    SpdBTLMaster insertspdmaster(SpdBTLMaster parameter);

    SpdBTLMaster updatespdmaster(SpdBTLMaster parameter);

    SpdBTLMaster getdataspdbtlmaster(Integer param);

    Integer getBanyakTotalSPDBySKPDDanTahun(Map parameter);

    void insertspddetail(SpdBtlDetail spdBtlDetail);

    void updatenilaispddetail(SpdBtlDetail spdBtlDetail);

    Integer getcekspddetailbyidspd(Integer param);

    Integer getcekspddetailbyidspdandidbtl(Map parameter);

    void insertspdsah(Map parameter);
    
    void updatespdsah(Map parameter);
    
    void updatespdsahrincibtl(Map parameter);
    
    void updatespdsahrincibl(Map parameter);
    
    void updatespdsahrincibantuan(Map parameter);
    
    void updatespdsahrincibiaya(Map parameter);

    void insertspdcetak(Map parameter);
    
    void updatespdcetak(Map parameter);
    
    void updatespdcetak1(Map parameter);

    void hapusspdrincibtl(Integer value);

    void hapusspdrincibtlbyakundanspd(Map parameter);

    void hapusspddanspdrincibtl(Integer value);

    List<Map> getlistcetakspdbtl(Map parameter);
    
    List<Map> getlistmonlap(Map parameter);
    
    List<Map> getnilaiparam(Map param);
    
     List<Map> getnilaiparam1(Map param);

    Integer getbanyakcetakspdbtl(Map parameter);
    
    Integer getbanyakmonlap(Map parameter);

    Map getcetakspdbyidspd(Integer value);

    List<Map> getlistvalidasispd(Map parameter);
    
    List<Map> getlistmoninitoringlaporan(Map parameter);

    Integer getbanyakvalidasispd(Map parameter);
    
    Map getspdsahbyidspd(Integer param);
    
    Map getspdsahid(Integer param);
    
    Map getspdsahnomor(Integer param);
    
    void deletespdcetak(Integer param);
    
     List<SpdBTLMaster> getPathFile(Map param);
}
