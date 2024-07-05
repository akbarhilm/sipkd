package sipkd.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import sipkd.model.PejabatPpkd;
import sipkd.model.Skpd;
import sipkd.model.SpdBTLMaster;
import sipkd.model.SpdBtlDetail;

/**
 * SPD biaya
 * @author User
 */
public interface SpdBiayaRevService {

    List<Skpd> getAllSkpd(Map<String, Object> param);

    Integer getBanyakAllSkpd(Map<String, Object> param);

    List<Skpd> getAllSkpdBtl();

    Map<String, BigDecimal> getPaguDanSisa(final Integer idSkpd, final String tahun);

    List<SpdBTLMaster> getAnggaranBtlSkpd(final Map<String, Object> param);

    Integer getBanyakAnggaranBtlSkpd(final Map<String, Object> param);

    Map<String, BigDecimal> getTotalAnggaranDanSpd(final Map<String, Object> param);

    List<SpdBtlDetail> getListSPDDetailBySKPDDanTahun(Map parameter, int addedit);

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

    void insertspdcetak(Map parameter);

    void hapusspdrincibtl(Integer value);

    void hapusspdrincibtlbyakundanspd(Map parameter);

    void hapusspddanspdrincibtl(Integer value);

    List<Map> getlistcetakspdbtl(Map parameter);

    Integer getbanyakcetakspdbtl(Map parameter);

    Map getcetakspdbyidspd(Integer value);

    List<Map> getlistvalidasispdbtl(Map parameter);

    Integer getbanyakvalidasispdbtl(Map parameter);
}
