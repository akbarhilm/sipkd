package sipkd.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import sipkd.model.SpdBTLMaster;
import sipkd.model.SpdBtlDetail;

/**
 *
 * @author Admin
 */
public interface SpdBLRevServices {

    Map<String, BigDecimal> getPaguDanSisaBantuanLangsung(Map<String, Object> parameter);

    Integer getBanyakAnggaranBlSkpd(Map<String, Object> parameter);

    List<SpdBTLMaster> getAnggaranBlSkpd(Map<String, Object> parameter);

    Map<String, BigDecimal> getTotalAnggaranDanSpd(Map<String, Object> param);

    SpdBTLMaster insertspdmaster(SpdBTLMaster parameter);

    SpdBTLMaster updatespdmaster(SpdBTLMaster parameter);

    SpdBTLMaster getdataspdblmaster(Integer param);

    List<SpdBtlDetail> getListSPDDetailBySKPDDanTahun(Map parameter, int addedit);

    Integer getBanyakTotalSPDBySKPDDanTahun(Map< String, Object> param, int addedit);

    List<SpdBtlDetail> getListSPDDetailBelanjaLangsungBySKPDDanTahun(Map parameter, int addedit);

    Integer getBanyakSPDDetailBelanjaLangsungBySKPDDanTahun(Map< String, Object> param, int addedit);
    
    Integer getcekspddetailbyidspdandidbelanjalangsung(Map< String, Object> param);
    
    void updatenilaispddetailbyidspdandidbelanjalangsung(SpdBtlDetail spdBtlDetail);

    void insertspddetailbelanjalangsung(SpdBtlDetail spdBtlDetail);
    
    void hapusspdrincibelanjalangsungbyakundanspd(Map parameter);
    
    void hapusspddanspdrincibl(Integer value);
    
     BigDecimal getNilaiSPDDetailBelanjaLangsungBySKPDTahunDanIDGiat(Map<String, Object> parameter);
}
