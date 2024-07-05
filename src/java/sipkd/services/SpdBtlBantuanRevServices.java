package sipkd.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import sipkd.model.Skpd;
import sipkd.model.SpdBTLMaster;
import sipkd.model.SpdBtlDetail;

/**
 *
 * @author Admin
 */
public interface SpdBtlBantuanRevServices {

    List<Skpd> getAllSkpdBtlBantuan(Map parameter);

    Integer getBanyakSkpdBtlBantuan(Map parameter);

    Skpd getAllSkpdById(final Integer id);

    Map getPaguDanSisaBtlBantuan(final Integer idSkpd, final String tahun);

    Integer getBanyakAnggaranBtlBantuanSkpd(Map parameter);

    List<SpdBTLMaster> getAnggaranBtlBantuanSkpd(Map parameter);

    Integer getBanyakTotalSPDBySKPDDanTahun(Map< String, Object> param);

    List<SpdBtlDetail> getListSPDDetailBySKPDDanTahun(Map parameter, int addedit);

    Map<String, BigDecimal> getTotalAnggaranDanSpd(Map<String, Object> param);

    SpdBTLMaster updatespdmaster(SpdBTLMaster parameter);

    SpdBTLMaster insertspdmaster(SpdBTLMaster parameter);

    SpdBTLMaster getdataspdbtlbatuanmaster(final Integer id);

    Integer getcekspddetailbyidspdandidbtl(Map parameter);

    void updatenilaispddetail(SpdBtlDetail spdBtlDetail);

    void insertspddetail(SpdBtlDetail spdBtlDetail);

    void hapusspdrincibtlbyakundanspd(Map parameter);

    void hapusspddanspdrincibtl(Integer value);

}
