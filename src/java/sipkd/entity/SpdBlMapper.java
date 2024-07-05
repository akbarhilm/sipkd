package sipkd.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import sipkd.model.HariKerja;
import sipkd.model.SpdBTLMaster;
import sipkd.model.SpdBtlDetail;

public interface SpdBlMapper {

    Map<String, BigDecimal> getPaguDanSisaBantuanLangsung(Map<String, Object> parameter);

    Integer getBanyakAnggaranBlSkpd(Map<String, Object> parameter);

    List<SpdBTLMaster> getAnggaranBlSkpd(Map<String, Object> parameter);

    BigDecimal getTotalAnggaranSkpd(Map<String, Object> parameter);

    BigDecimal getTotalSPDBySKPDDanTahun(Map<String, Object> parameter);

    HariKerja getharikerjaspd(Date param);

    Integer getspdno(String param);

    Long getspdid();

    void insertspdmaster(SpdBTLMaster param);

    void updatespdmaster(SpdBTLMaster param);

    SpdBTLMaster getdataspdblmaster(Integer param);

    List<SpdBtlDetail> getListSPDDetailBySKPDDanTahun(Map parameter);

    List<SpdBtlDetail> getListSPDDetailBySKPDDanTahunEdit(Map parameter);

    Integer getBanyakTotalSPDBySKPDDanTahun(Map< String, Object> param);

    List<SpdBtlDetail> getListSPDDetailBelanjaLangsungBySKPDDanTahun(Map parameter);

    List<SpdBtlDetail> getListSPDDetailBelanjaLangsungBySKPDDanTahunEdit(Map parameter);

    Integer getBanyakSPDDetailBelanjaLangsungBySKPDDanTahun(Map< String, Object> param);
    
    Integer getcekspddetailbyidspdandidbelanjalangsung(Map< String, Object> param);
    
    void updatenilaispddetailbyidspdandidbelanjalangsung(SpdBtlDetail spdBtlDetail);
    
    void insertspddetailbelanjalangsung(SpdBtlDetail spdBtlDetail);
    
    void hapusspdrincibelanjalangsungbyakundanspd(Map parameter);
    
     void hapusspdbyidspd(Integer value);

    void hapusspdrinciblbyidspd(Integer value);
    
     BigDecimal getNilaiSPDDetailBelanjaLangsungBySKPDTahunDanIDGiat(Map<String, Object> parameter);
}
