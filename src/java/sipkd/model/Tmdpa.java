/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sipkd.model;
import java.math.BigDecimal;
import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
/**
 *
 * @author Xalamaster
 */
public class Tmdpa extends BaseModel {
    
    
    private Integer idTmdpa;
    //private Integer idSkpd;
    private Integer idPendapatan;
    private Integer skpdLevel;
    private Integer tahun;
    private Integer idskpd;
    
    private String kodeSkpd;
    private String namaSkpd;
    private String namaSkpdPendek; 
    private String isAktif;
    private String levelSkpd;  
    
    
    
    private Integer idSkpd;
    private Integer idDpa;
    private BigDecimal paguDpt;
    private BigDecimal paguBtl;
    private BigDecimal paguBl;
    private BigDecimal paguBiaya;
    private String noDpa;
    private Date tglDpa;
    private String noDpaPrbh;
    private Date tglDpaPrbh;
    //@Length(max = 5, message = "NRK Harus 6 Digit ")
    private String nrkPA;
    //@Length(max = 18, message = "NIP Harus 18 Digit ")
    private String nipPA;
    private String namaPA;
   // @Length(max = 5, message = "NRK Harus 6 Digit ")
    private String nrkBenPn;
    //@Length(max = 18, message = "NIP Harus 18 Digit ")
    private String nipBenPn;
    private String namaBenPn;
    //@Length(max = 6, message = "NRK Harus 6 Digit ")
    private String nrkBenPg;
   // @Length(max = 18, message = "NIP Harus 18 Digit ")
    private String nipBenPg;
    private String namaBenPg;
    //@Length(max = 6, message = "NRK Harus 6 Digit ")
    private String nrkBenPgBantuan;
  //  @Length(max = 18, message = "NIP Harus 18 Digit ")
    private String nipBenPgBantuan;
    private String namaBenPgBantuan;
 //   @Length(max = 6, message = "NRK Harus 6 Digit ")
    private String nrkBenPgBTT;
 //   @Length(max = 18, message = "NIP Harus 18 Digit ")
    private String nipBenPgBTT;
    private String namaBenPgBTT;
  //  @Length(max = 6, message = "NRK Harus 6 Digit ")
    private String nrkBenPgPembiayaan;
  //  @Length(max = 18, message = "NIP Harus 18 Digit ")
    private String nipBenPgPembiayaan;
    private String namaBenPgPembiayaan;
 //   @Length(max = 6, message = "NRK Harus 6 Digit ")
    private String nrkVerifikatorPn;
 //   @Length(max = 18, message = "NIP Harus 18 Digit ")
    private String nipVerifikatorPn;
    private String namaVerifikatorPn;
 //   @Length(max = 6, message = "NRK Harus 6 Digit ")
    private String nrkVerifikatorPg;
  //  @Length(max = 18, message = "NIP Harus 18 Digit ")
    private String nipVerifikatorPg;
    private String namaVerifikatorPg;
    
    private Skpd skpd;

    /**
     * @return the idDpa
     */
    public Integer getIdDpa() {
        return idDpa;
    }

    /**
     * @param idDpa the idDpa to set
     */
    public void setIdDpa(Integer idDpa) {
        this.idDpa = idDpa;
    }

    /**
     * @return the paguDpt
     */
    public BigDecimal getPaguDpt() {
        return paguDpt;
    }

    /**
     * @param paguDpt the paguDpt to set
     */
    public void setPaguDpt(BigDecimal paguDpt) {
        this.paguDpt = paguDpt;
    }

    /**
     * @return the paguBtl
     */
    public BigDecimal getPaguBtl() {
        return paguBtl;
    }

    /**
     * @param paguBtl the paguBtl to set
     */
    public void setPaguBtl(BigDecimal paguBtl) {
        this.paguBtl = paguBtl;
    }

    /**
     * @return the paguBl
     */
    public BigDecimal getPaguBl() {
        return paguBl;
    }

    /**
     * @param paguBl the paguBl to set
     */
    public void setPaguBl(BigDecimal paguBl) {
        this.paguBl = paguBl;
    }

    /**
     * @return the paguBiaya
     */
    public BigDecimal getPaguBiaya() {
        return paguBiaya;
    }

    /**
     * @param paguBiaya the paguBiaya to set
     */
    public void setPaguBiaya(BigDecimal paguBiaya) {
        this.paguBiaya = paguBiaya;
    }

    /**
     * @return the noDpa
     */
    public String getNoDpa() {
        return noDpa;
    }

    /**
     * @param noDpa the noDpa to set
     */
    public void setNoDpa(String noDpa) {
        this.noDpa = noDpa;
    }

    /**
     * @return the tglDpa
     */
    public Date getTglDpa() {
        return tglDpa;
    }

    /**
     * @param tglDpa the tglDpa to set
     */
    public void setTglDpa(Date tglDpa) {
        this.tglDpa = tglDpa;
    }

    /**
     * @return the noDpaPrbh
     */
    public String getNoDpaPrbh() {
        return noDpaPrbh;
    }

    /**
     * @param noDpaPrbh the noDpaPrbh to set
     */
    public void setNoDpaPrbh(String noDpaPrbh) {
        this.noDpaPrbh = noDpaPrbh;
    }

    /**
     * @return the tglDpaPrbh
     */
    public Date getTglDpaPrbh() {
        return tglDpaPrbh;
    }

    /**
     * @param tglDpaPrbh the tglDpaPrbh to set
     */
    public void setTglDpaPrbh(Date tglDpaPrbh) {
        this.setTglDpaPrbh(tglDpaPrbh);
    }

    /**
     * @return the nrkPA
     */
    public String getNrkPA() {
        return nrkPA;
    }

    /**
     * @param nrkPA the nrkPA to set
     */
    public void setNrkPA(String nrkPA) {
        this.nrkPA = nrkPA;
    }

    /**
     * @return the nipPA
     */
    public String getNipPA() {
        return nipPA;
    }

    /**
     * @param nipPA the nipPA to set
     */
    public void setNipPA(String nipPA) {
        this.nipPA = nipPA;
    }

    /**
     * @return the namaPA
     */
    public String getNamaPA() {
        return namaPA;
    }

    /**
     * @param namaPA the namaPA to set
     */
    public void setNamaPA(String namaPA) {
        this.namaPA = namaPA;
    }

    /**
     * @return the nrkBenPn
     */
    public String getNrkBenPn() {
        return nrkBenPn;
    }

    /**
     * @param nrkBenPn the nrkBenPn to set
     */
    public void setNrkBenPn(String nrkBenPn) {
        this.nrkBenPn = nrkBenPn;
    }

    /**
     * @return the nipBenPn
     */
    public String getNipBenPn() {
        return nipBenPn;
    }

    /**
     * @param nipBenPn the nipBenPn to set
     */
    public void setNipBenPn(String nipBenPn) {
        this.nipBenPn = nipBenPn;
    }

    /**
     * @return the namaBenPn
     */
    public String getNamaBenPn() {
        return namaBenPn;
    }

    /**
     * @param namaBenPn the namaBenPn to set
     */
    public void setNamaBenPn(String namaBenPn) {
        this.namaBenPn = namaBenPn;
    }

    /**
     * @return the nrkBenPg
     */
    public String getNrkBenPg() {
        return nrkBenPg;
    }

    /**
     * @param nrkBenPg the nrkBenPg to set
     */
    public void setNrkBenPg(String nrkBenPg) {
        this.nrkBenPg = nrkBenPg;
    }

    /**
     * @return the nipBenPg
     */
    public String getNipBenPg() {
        return nipBenPg;
    }

    /**
     * @param nipBenPg the nipBenPg to set
     */
    public void setNipBenPg(String nipBenPg) {
        this.nipBenPg = nipBenPg;
    }

    /**
     * @return the namaBenPg
     */
    public String getNamaBenPg() {
        return namaBenPg;
    }

    /**
     * @param namaBenPg the namaBenPg to set
     */
    public void setNamaBenPg(String namaBenPg) {
        this.namaBenPg = namaBenPg;
    }

    /**
     * @return the nrkBenPgBantuan
     */
    public String getNrkBenPgBantuan() {
        return nrkBenPgBantuan;
    }

    /**
     * @param nrkBenPgBantuan the nrkBenPgBantuan to set
     */
    public void setNrkBenPgBantuan(String nrkBenPgBantuan) {
        this.nrkBenPgBantuan = nrkBenPgBantuan;
    }

    /**
     * @return the nipBenPgBantuan
     */
    public String getNipBenPgBantuan() {
        return nipBenPgBantuan;
    }

    /**
     * @param nipBenPgBantuan the nipBenPgBantuan to set
     */
    public void setNipBenPgBantuan(String nipBenPgBantuan) {
        this.nipBenPgBantuan = nipBenPgBantuan;
    }

    /**
     * @return the namaBenPgBantuan
     */
    public String getNamaBenPgBantuan() {
        return namaBenPgBantuan;
    }

    /**
     * @param namaBenPgBantuan the namaBenPgBantuan to set
     */
    public void setNamaBenPgBantuan(String namaBenPgBantuan) {
        this.namaBenPgBantuan = namaBenPgBantuan;
    }

    /**
     * @return the nrkBenPgBTT
     */
    public String getNrkBenPgBTT() {
        return nrkBenPgBTT;
    }

    /**
     * @param nrkBenPgBTT the nrkBenPgBTT to set
     */
    public void setNrkBenPgBTT(String nrkBenPgBTT) {
        this.nrkBenPgBTT = nrkBenPgBTT;
    }

    /**
     * @return the nipBenPgBTT
     */
    public String getNipBenPgBTT() {
        return nipBenPgBTT;
    }

    /**
     * @param nipBenPgBTT the nipBenPgBTT to set
     */
    public void setNipBenPgBTT(String nipBenPgBTT) {
        this.nipBenPgBTT = nipBenPgBTT;
    }

    /**
     * @return the namaBenPgBTT
     */
    public String getNamaBenPgBTT() {
        return namaBenPgBTT;
    }

    /**
     * @param namaBenPgBTT the namaBenPgBTT to set
     */
    public void setNamaBenPgBTT(String namaBenPgBTT) {
        this.namaBenPgBTT = namaBenPgBTT;
    }

    /**
     * @return the nrkBenPgPembiayaan
     */
    public String getNrkBenPgPembiayaan() {
        return nrkBenPgPembiayaan;
    }

    /**
     * @param nrkBenPgPembiayaan the nrkBenPgPembiayaan to set
     */
    public void setNrkBenPgPembiayaan(String nrkBenPgPembiayaan) {
        this.nrkBenPgPembiayaan = nrkBenPgPembiayaan;
    }

    /**
     * @return the nipBenPgPembiayaan
     */
    public String getNipBenPgPembiayaan() {
        return nipBenPgPembiayaan;
    }

    /**
     * @param nipBenPgPembiayaan the nipBenPgPembiayaan to set
     */
    public void setNipBenPgPembiayaan(String nipBenPgPembiayaan) {
        this.nipBenPgPembiayaan = nipBenPgPembiayaan;
    }

    /**
     * @return the namaBenPgPembiayaan
     */
    public String getNamaBenPgPembiayaan() {
        return namaBenPgPembiayaan;
    }

    /**
     * @param namaBenPgPembiayaan the namaBenPgPembiayaan to set
     */
    public void setNamaBenPgPembiayaan(String namaBenPgPembiayaan) {
        this.namaBenPgPembiayaan = namaBenPgPembiayaan;
    }

    /**
     * @return the skpd
     */
    public Skpd getSkpd() {
        return skpd;
    }

    /**
     * @param skpd the skpd to set
     */
    public void setSkpd(Skpd skpd) {
        this.skpd = skpd;
    }

    /**
     * @return the nrkVerifikator
     */
    public String getNrkVerifikatorPn() {
        return nrkVerifikatorPn;
    }

    /**
     * @param nrkVerifikatorPn
     */
    public void setNrkVerifikatorPn(String nrkVerifikatorPn) {
        this.nrkVerifikatorPn = nrkVerifikatorPn;
    }

    /**
     * @return the nipVerifikatorPn
     */
    public String getNipVerifikatorPn() {
        return nipVerifikatorPn;
    }

    /**
     * @param nipVerifikatorPn the nipVerifikator to set
     */
    public void setNipVerifikatorPn(String nipVerifikatorPn) {
        this.nipVerifikatorPn = nipVerifikatorPn;
    }

    /**
     * @return the namaVerifikatorPn
     */
    public String getNamaVerifikatorPn() {
        return namaVerifikatorPn;
    }

    /**
     * @param namaVerifikatorPn the namaVerifikator to set
     */
    public void setNamaVerifikatorPn(String namaVerifikatorPn) {
        this.namaVerifikatorPn = namaVerifikatorPn;
    }

    /**
     * @return the idSkpd
     */
    public Integer getIdSkpd() {
        return idSkpd;
    }

    /**
     * @param idSkpd the idSkpd to set
     */
    public void setIdSkpd(Integer idSkpd) {
        this.idSkpd = idSkpd;
    }

    /**
     * @return the idTmdpa
     */
    public Integer getIdTmdpa() {
        return idTmdpa;
    }

    /**
     * @param idTmdpa the idTmdpa to set
     */
    public void setIdTmdpa(Integer idTmdpa) {
        this.idTmdpa = idTmdpa;
    }

    /**
     * @return the idPendapatan
     */
    public Integer getIdPendapatan() {
        return idPendapatan;
    }

    /**
     * @param idPendapatan the idPendapatan to set
     */
    public void setIdPendapatan(Integer idPendapatan) {
        this.idPendapatan = idPendapatan;
    }

    /**
     * @return the skpdLevel
     */
    public Integer getSkpdLevel() {
        return skpdLevel;
    }

    /**
     * @param skpdLevel the skpdLevel to set
     */
    public void setSkpdLevel(Integer skpdLevel) {
        this.skpdLevel = skpdLevel;
    }

    /**
     * @return the tahun
     */
    public Integer getTahun() {
        return tahun;
    }

    /**
     * @param tahun the tahun to set
     */
    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    /**
     * @return the idskpd
     */
    public Integer getIdskpd() {
        return idskpd;
    }

    /**
     * @param idskpd the idskpd to set
     */
    public void setIdskpd(Integer idskpd) {
        this.idskpd = idskpd;
    }

    /**
     * @return the kodeSkpd
     */
    public String getKodeSkpd() {
        return kodeSkpd;
    }

    /**
     * @param kodeSkpd the kodeSkpd to set
     */
    public void setKodeSkpd(String kodeSkpd) {
        this.kodeSkpd = kodeSkpd;
    }

    /**
     * @return the namaSkpd
     */
    public String getNamaSkpd() {
        return namaSkpd;
    }

    /**
     * @param namaSkpd the namaSkpd to set
     */
    public void setNamaSkpd(String namaSkpd) {
        this.namaSkpd = namaSkpd;
    }

    /**
     * @return the namaSkpdPendek
     */
    public String getNamaSkpdPendek() {
        return namaSkpdPendek;
    }

    /**
     * @param namaSkpdPendek the namaSkpdPendek to set
     */
    public void setNamaSkpdPendek(String namaSkpdPendek) {
        this.namaSkpdPendek = namaSkpdPendek;
    }

    /**
     * @return the isAktif
     */
    public String getIsAktif() {
        return isAktif;
    }

    /**
     * @param isAktif the isAktif to set
     */
    public void setIsAktif(String isAktif) {
        this.isAktif = isAktif;
    }

    /**
     * @return the levelSkpd
     */
    public String getLevelSkpd() {
        return levelSkpd;
    }

    /**
     * @param levelSkpd the levelSkpd to set
     */
    public void setLevelSkpd(String levelSkpd) {
        this.levelSkpd = levelSkpd;
    }

    /**
     * @return the nrkVerifikatorPg
     */
    public String getNrkVerifikatorPg() {
        return nrkVerifikatorPg;
    }

    /**
     * @param nrkVerifikatorPg the nrkVerifikatorPg to set
     */
    public void setNrkVerifikatorPg(String nrkVerifikatorPg) {
        this.nrkVerifikatorPg = nrkVerifikatorPg;
    }

    /**
     * @return the nipVerifikatorPg
     */
    public String getNipVerifikatorPg() {
        return nipVerifikatorPg;
    }

    /**
     * @param nipVerifikatorPg the nipVerifikatorPg to set
     */
    public void setNipVerifikatorPg(String nipVerifikatorPg) {
        this.nipVerifikatorPg = nipVerifikatorPg;
    }

    /**
     * @return the namaVerifikatorPg
     */
    public String getNamaVerifikatorPg() {
        return namaVerifikatorPg;
    }

    /**
     * @param namaVerifikatorPg the namaVerifikatorPg to set
     */
    public void setNamaVerifikatorPg(String namaVerifikatorPg) {
        this.namaVerifikatorPg = namaVerifikatorPg;
    }

}
