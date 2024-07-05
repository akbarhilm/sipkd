package sipkd.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import sipkd.model.Skpd;
import sipkd.model.SpdBTLMaster;
import sipkd.model.SpdBtlDetail;

public interface SpdBtlBantuanRevMapper {

    List<Skpd> getAllSkpdBtlBantuan(Map parameter);

    Integer getBanyakSkpdBtlBantuan(Map parameter);

    Skpd getAllSkpdById(final Integer id);

    Map getPaguDanSisaBtlBantuan(Map parameter);

    Integer getBanyakAnggaranBtlBantuanSkpd(Map parameter);

    List<SpdBTLMaster> getAnggaranBtlBantuanSkpd(Map parameter);

    Integer getBanyakTotalSPDBySKPDDanTahun(Map< String, Object> param);

    List<SpdBtlDetail> getListSPDDetailBySKPDDanTahun(Map parameter);

    List<SpdBtlDetail> getListSPDDetailBySKPDDanTahunEdit(Map parameter);

    BigDecimal getTotalAnggaranSkpd(Map parameter);

    BigDecimal getTotalSPDBySKPDDanTahun(Map parameter);

    SpdBTLMaster getdataspdbtlbatuanmaster(final Integer id);

    Integer getcekspddetailbyidspdandidbtl(Map parameter);

    void updatenilaispddetailbyidspdandidbtl(SpdBtlDetail spdBtlDetail);

    void insertspddetail(SpdBtlDetail spdBtlDetail);

    void hapusspdrincibtlbyakundanspd(Map parameter);

    void hapusspdbyidspd(Integer value);

    void hapusspdrincibtlbyidspd(Integer value);
}
