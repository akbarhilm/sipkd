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

public interface SpdRevMapper {

    List<Skpd> getAllSkpdBtl();

    Skpd getAllSkpdById(final Integer id);

    @Select("CALL  HITUNG_SPDBTL(#{parameter.tahun,mode=IN, jdbcType=VARCHAR},#{parameter.idspd,mode=IN, jdbcType=INTEGER},#{parameter.idskpd,mode=IN, jdbcType=INTEGER},#{parameter.pagu,mode=OUT,  jdbcType=NUMERIC},#{parameter.vspdsebelum,mode=OUT,  jdbcType=NUMERIC},#{parameter.vspd,mode=OUT,  jdbcType=NUMERIC},#{parameter.vapbd,mode=OUT,  jdbcType=NUMERIC},#{parameter.ret,mode=OUT,  jdbcType=NUMERIC}) ")
    @Options(statementType = StatementType.CALLABLE)
    void cariPaguDanSisaBTLBYIdSkpdDanIdSpd(@Param("parameter") Map parameter);

    BigDecimal gettotalspdbyidskpddantahun(Map parameter);

    BigDecimal gettotalpagubyidskpddantahun(Map parameter);

    List<SpdBTLMaster> getAnggaranBtlSkpd(Map parameter);
    
    List<SpdBTLMaster> getAnggaranBtlSkpdRev(Map parameter);

    Integer getBanyakAnggaranBtlSkpd(Map parameter);
    
    Integer getBanyakAnggaranBtlSkpdRev(Map parameter);

    BigDecimal getTotalAnggaranSkpd(Map parameter);

    BigDecimal getTotalSPDBySKPDDanTahun(Map parameter);

    List<SpdBtlDetail> getListSPDDetailBySKPDDanTahun(Map parameter);
    
    List<SpdBtlDetail> getListSPDDetailBySKPDDanTahunRev(Map parameter);

    List<SpdBtlDetail> getListSPDDetailBySKPDDanTahunEdit(Map parameter);
    
    List<SpdBtlDetail> getListSPDDetailBySKPDDanTahunEditRev(Map parameter);

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
    
    void updatespdsah(Map parameter);
    
    void updatespdsahrincibl(Map parameter);
    
    void updatespdsahrincibtl(Map parameter);
    
    void updatespdsahrincibantuan(Map parameter);
    
    void updatespdsahrincibiaya(Map parameter);

    void insertspdcetak(Map parameter);
    
    void updatespdcetak(Map parameter);
    
    void updatespdcetak1(Map parameter);

    Map getPaguDanSisaRev(Map parameter);

    void hapusspdrincibtl(Integer value);

    void hapusspdrincibtlbyakundanspd(Map parameter);

    HariKerja getharikerjaspd(Date value);

    void hapusspdbyidspd(Integer value);

    void hapusspdrincibtlbyidspd(Integer value);

    List<Map> getlistcetakspdbtl(Map parameter);
    
     List<Map> getlistmonlap(Map parameter);
    
     List<Map> getnilaiparam(Map parameter);
     
    List<Map> getnilaiparam1(Map parameter); 

    Integer getbanyakcetakspdbtl(Map parameter);
    
    Integer getbanyakmonlap(Map parameter);

    Map getcetakspdbyidspd(Integer value);

    List<Map> getlistvalidasispd(Map parameter);

    Integer getbanyakvalidasispd(Map parameter);

    Map getspdsahbyidspd(Integer param);
    
    Map getspdsahid(Integer param);
    
    Map getspdsahnomor(Integer param);
    
    void deletespdcetak(Integer param);
    
    List<Map> getlistmoninitoringlaporan(Map parameter);
    
    List<SpdBTLMaster> getPathFile(Map param);
}
