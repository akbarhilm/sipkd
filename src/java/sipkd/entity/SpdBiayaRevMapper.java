package sipkd.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import sipkd.model.HariKerja;
import sipkd.model.PejabatPpkd;
import sipkd.model.Skpd;
import sipkd.model.SpdBTLMaster;
import sipkd.model.SpdBtlDetail;

public interface SpdBiayaRevMapper {

    List<Skpd> getAllSkpdBtl();

    Skpd getAllSkpdById(final Integer id);

    @Select("CALL  HITUNG_SPDBTL(#{parameter.tahun,mode=IN, jdbcType=VARCHAR},#{parameter.idspd,mode=IN, jdbcType=INTEGER},#{parameter.idskpd,mode=IN, jdbcType=INTEGER},#{parameter.pagu,mode=OUT,  jdbcType=NUMERIC},#{parameter.vspdsebelum,mode=OUT,  jdbcType=NUMERIC},#{parameter.vspd,mode=OUT,  jdbcType=NUMERIC},#{parameter.vapbd,mode=OUT,  jdbcType=NUMERIC},#{parameter.ret,mode=OUT,  jdbcType=NUMERIC}) ")
    @Options(statementType = StatementType.CALLABLE)
    void cariPaguDanSisaBTLBYIdSkpdDanIdSpd(@Param("parameter") Map parameter);

    BigDecimal gettotalspdbyidskpddantahun(Map parameter);

    BigDecimal gettotalpagubyidskpddantahun(Map parameter);

    List<SpdBTLMaster> getAnggaranBtlSkpd(Map parameter);

    Integer getBanyakAnggaranBtlSkpd(Map parameter);

    BigDecimal getTotalAnggaranSkpd(Map parameter);

    BigDecimal getTotalSPDBySKPDDanTahun(Map parameter);

    List<SpdBtlDetail> getListSPDDetailBySKPDDanTahun(Map parameter);

    List<SpdBtlDetail> getListSPDDetailBySKPDDanTahunEdit(Map parameter);

    Integer getBanyakTotalSPDBySKPDDanTahun(Map parameter);

    List<PejabatPpkd> getAllPejabatPpkd(Map parameter);

    Integer getBanyakPejabatPPKD(Map parameter);

    Integer insertspdmaster(SpdBTLMaster parameter);

    Integer updatespdmaster(SpdBTLMaster parameter);

    SpdBTLMaster getdataspdbtlmaster(Integer param);

    Integer getspdno(String tahun);

    Long getspdid();

    SpdBTLMaster getdataspdbtlmasterinsert(Integer param);

    void insertspddetail(SpdBtlDetail parameter);

    void updatenilaispddetail(SpdBtlDetail parameter);

    Integer getcekspddetailbyidspd(Integer param);

    void updatenilaispddetailbyidspdandidbtl(SpdBtlDetail parameter);

    Integer getcekspddetailbyidspdandidbtl(Map parameter);

    void insertspdsah(Map parameter);

    void insertspdcetak(Map parameter);

    Map getPaguDanSisaBiaya(Map parameter);

    void hapusspdrincibtl(Integer value);

    void hapusspdrincibtlbyakundanspd(Map parameter);

    HariKerja getharikerjaspd(Date value);

    void hapusspdbyidspd(Integer value);

    void hapusspdrincibtlbyidspd(Integer value);

    List<Map> getlistcetakspdbtl(Map parameter);

    Integer getbanyakcetakspdbtl(Map parameter);

    Map getcetakspdbyidspd(Integer value);

    List<Map> getlistvalidasispdbtl(Map parameter);

    Integer getbanyakvalidasispdbtl(Map parameter);
}
