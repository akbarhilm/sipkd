/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipkd.model;

import java.math.BigDecimal;

/**
 *
 * @author User
 */
public class SpdBtlDetail extends BaseModel {

    private Integer idSpdRinci;
    private Integer idSpd;
    private Integer idSkpdKoord;
    private Integer idAkun;
    private Integer idBtl;
    private String kodeAkun;
    private String namaAkun;
    private Integer idKegiatan;
    private String kodeKegiatan;
    private String namaKegiatan;

    private BigDecimal nilaiAnggaranTAPD;
    private BigDecimal nilaiAnggaranSPDSebelum;
    private BigDecimal nilaiAnggaranSPDCurrent;
    //--SPD_sdhada
    private BigDecimal nilaiAnggaranSPDSudahAda;
    //--SPD sisa
    private BigDecimal nilaiAnggaranSPDSisa;
    //--SPD_EDIT
    private BigDecimal nilaiAnggaranSPDEdit;
    //--SPD_baru
    private BigDecimal nilaiAnggaranSPDBaru;

    private BigDecimal nilaiSPDYangSudahDiserap;

    private BigDecimal nilaiAnggaranSPDMurni;

    private int isExistBl;

    /**
     * @return the idSpdRinci
     */
    public Integer getIdSpdRinci() {
        return idSpdRinci;
    }

    /**
     * @param idSpdRinci the idSpdRinci to set
     */
    public void setIdSpdRinci(Integer idSpdRinci) {
        this.idSpdRinci = idSpdRinci;
    }

    /**
     * @return the idSpd
     */
    public Integer getIdSpd() {
        return idSpd;
    }

    /**
     * @param idSpd the idSpd to set
     */
    public void setIdSpd(Integer idSpd) {
        this.idSpd = idSpd;
    }

    /**
     * @return the idAkun
     */
    public Integer getIdAkun() {
        return idAkun;
    }

    /**
     * @param idAkun the idAkun to set
     */
    public void setIdAkun(Integer idAkun) {
        this.idAkun = idAkun;
    }

    /**
     * @return the idBtl
     */
    public Integer getIdBtl() {
        return idBtl;
    }

    /**
     * @param idBtl the idBtl to set
     */
    public void setIdBtl(Integer idBtl) {
        this.idBtl = idBtl;
    }

    /**
     * @return the kodeAkun
     */
    public String getKodeAkun() {
        return kodeAkun;
    }

    /**
     * @param kodeAkun the kodeAkun to set
     */
    public void setKodeAkun(String kodeAkun) {
        this.kodeAkun = kodeAkun;
    }

    /**
     * @return the namaAkun
     */
    public String getNamaAkun() {
        return namaAkun;
    }

    /**
     * @param namaAkun the namaAkun to set
     */
    public void setNamaAkun(String namaAkun) {
        this.namaAkun = namaAkun;
    }

    /**
     * @return the nilaiAnggaranTAPD
     */
    public BigDecimal getNilaiAnggaranTAPD() {
        return nilaiAnggaranTAPD;
    }

    /**
     * @param nilaiAnggaranTAPD the nilaiAnggaranTAPD to set
     */
    public void setNilaiAnggaranTAPD(BigDecimal nilaiAnggaranTAPD) {
        this.nilaiAnggaranTAPD = nilaiAnggaranTAPD;
    }

    /**
     * @return the nilaiAnggaranSPDSebelum
     */
    public BigDecimal getNilaiAnggaranSPDSebelum() {
        return nilaiAnggaranSPDSebelum;
    }

    /**
     * @param nilaiAnggaranSPDSebelum the nilaiAnggaranSPDSebelum to set
     */
    public void setNilaiAnggaranSPDSebelum(BigDecimal nilaiAnggaranSPDSebelum) {
        this.nilaiAnggaranSPDSebelum = nilaiAnggaranSPDSebelum;
    }

    /**
     * @return the nilaiAnggaranSPDCurrent
     */
    public BigDecimal getNilaiAnggaranSPDCurrent() {
        return nilaiAnggaranSPDCurrent;
    }

    /**
     * @param nilaiAnggaranSPDCurrent the nilaiAnggaranSPDCurrent to set
     */
    public void setNilaiAnggaranSPDCurrent(BigDecimal nilaiAnggaranSPDCurrent) {
        this.nilaiAnggaranSPDCurrent = nilaiAnggaranSPDCurrent;
    }

    /**
     * @return the nilaiAnggaranSPDSudahAda
     */
    public BigDecimal getNilaiAnggaranSPDSudahAda() {
        return nilaiAnggaranSPDSudahAda;
    }

    /**
     * @param nilaiAnggaranSPDSudahAda the nilaiAnggaranSPDSudahAda to set
     */
    public void setNilaiAnggaranSPDSudahAda(BigDecimal nilaiAnggaranSPDSudahAda) {
        this.nilaiAnggaranSPDSudahAda = nilaiAnggaranSPDSudahAda;
    }

    /**
     * @return the nilaiAnggaranSPDSisa
     */
    public BigDecimal getNilaiAnggaranSPDSisa() {
        return nilaiAnggaranSPDSisa;
    }

    /**
     * @param nilaiAnggaranSPDSisa the nilaiAnggaranSPDSisa to set
     */
    public void setNilaiAnggaranSPDSisa(BigDecimal nilaiAnggaranSPDSisa) {
        this.nilaiAnggaranSPDSisa = nilaiAnggaranSPDSisa;
    }

    /**
     * @return the nilaiAnggaranSPDEdit
     */
    public BigDecimal getNilaiAnggaranSPDEdit() {
        return nilaiAnggaranSPDEdit;
    }

    /**
     * @param nilaiAnggaranSPDEdit the nilaiAnggaranSPDEdit to set
     */
    public void setNilaiAnggaranSPDEdit(BigDecimal nilaiAnggaranSPDEdit) {
        this.nilaiAnggaranSPDEdit = nilaiAnggaranSPDEdit;
    }

    /**
     * @return the nilaiAnggaranSPDBaru
     */
    public BigDecimal getNilaiAnggaranSPDBaru() {
        return nilaiAnggaranSPDBaru;
    }

    /**
     * @param nilaiAnggaranSPDBaru the nilaiAnggaranSPDBaru to set
     */
    public void setNilaiAnggaranSPDBaru(BigDecimal nilaiAnggaranSPDBaru) {
        this.nilaiAnggaranSPDBaru = nilaiAnggaranSPDBaru;
    }

    /**
     * @return the kodeKegiatan
     */
    public String getKodeKegiatan() {
        return kodeKegiatan;
    }

    /**
     * @param kodeKegiatan the kodeKegiatan to set
     */
    public void setKodeKegiatan(String kodeKegiatan) {
        this.kodeKegiatan = kodeKegiatan;
    }

    /**
     * @return the namaKegiatan
     */
    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    /**
     * @param namaKegiatan the namaKegiatan to set
     */
    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    /**
     * @return the idSkpdKoord
     */
    public Integer getIdSkpdKoord() {
        return idSkpdKoord;
    }

    /**
     * @param idSkpdKoord the idSkpdKoord to set
     */
    public void setIdSkpdKoord(Integer idSkpdKoord) {
        this.idSkpdKoord = idSkpdKoord;
    }

    /**
     * @return the idKegiatan
     */
    public Integer getIdKegiatan() {
        return idKegiatan;
    }

    /**
     * @param idKegiatan the idKegiatan to set
     */
    public void setIdKegiatan(Integer idKegiatan) {
        this.idKegiatan = idKegiatan;
    }

    @Override
    public String toString() {
        return new StringBuilder(" idKegiatan ").append(idKegiatan)
                .append(" idSpd ").append(idSpd)
                .append(" idBtl ").append(idBtl)
                .append(" idAkun ").append(idAkun).toString();

    }

    /**
     * @return the isExistBl
     */
    public int getIsExistBl() {
        return isExistBl;
    }

    /**
     * @param isExistBl the isExistBl to set
     */
    public void setIsExistBl(int isExistBl) {
        this.isExistBl = isExistBl;
    }

    /**
     * @return the nilaiSPDYangSudahDiserap
     */
    public BigDecimal getNilaiSPDYangSudahDiserap() {
        return nilaiSPDYangSudahDiserap;
    }

    /**
     * @param nilaiSPDYangSudahDiserap the nilaiSPDYangSudahDiserap to set
     */
    public void setNilaiSPDYangSudahDiserap(BigDecimal nilaiSPDYangSudahDiserap) {
        this.nilaiSPDYangSudahDiserap = nilaiSPDYangSudahDiserap;
    }

    /**
     * @return the nilaiAnggaranSPDMurni
     */
    public BigDecimal getNilaiAnggaranSPDMurni() {
        return nilaiAnggaranSPDMurni;
    }

    /**
     * @param nilaiAnggaranSPDMurni the nilaiAnggaranSPDMurni to set
     */
    public void setNilaiAnggaranSPDMurni(BigDecimal nilaiAnggaranSPDMurni) {
        this.nilaiAnggaranSPDMurni = nilaiAnggaranSPDMurni;
    }
}
