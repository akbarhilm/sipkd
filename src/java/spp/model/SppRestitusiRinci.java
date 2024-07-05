package spp.model;

import java.math.BigDecimal;

/**
 *
 * @author zaenab
 */
public class SppRestitusiRinci extends BaseModel {
    private Integer idSpp;
    private Integer idPnrmRinci;
    private Integer idPnrm;
    private Integer idAkun;
    private String kodeAkun;
    private String namaAkun;
    private BigDecimal nilaiPnrm;
    private BigDecimal nilaiSppSebelum;
    private BigDecimal sisaRestitusi;
    private BigDecimal nilaiSppSaatini;

    /**
     * @return the idPnrmRinci
     */
    public Integer getIdPnrmRinci() {
        return idPnrmRinci;
    }

    /**
     * @param idPnrmRinci the idPnrmRinci to set
     */
    public void setIdPnrmRinci(Integer idPnrmRinci) {
        this.idPnrmRinci = idPnrmRinci;
    }

    /**
     * @return the idPnrm
     */
    public Integer getIdPnrm() {
        return idPnrm;
    }

    /**
     * @param idPnrm the idPnrm to set
     */
    public void setIdPnrm(Integer idPnrm) {
        this.idPnrm = idPnrm;
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
     * @return the nilaiPnrm
     */
    public BigDecimal getNilaiPnrm() {
        return nilaiPnrm;
    }

    /**
     * @param nilaiPnrm the nilaiPnrm to set
     */
    public void setNilaiPnrm(BigDecimal nilaiPnrm) {
        this.nilaiPnrm = nilaiPnrm;
    }

    /**
     * @return the nilaiSppSebelum
     */
    public BigDecimal getNilaiSppSebelum() {
        return nilaiSppSebelum;
    }

    /**
     * @param nilaiSppSebelum the nilaiSppSebelum to set
     */
    public void setNilaiSppSebelum(BigDecimal nilaiSppSebelum) {
        this.nilaiSppSebelum = nilaiSppSebelum;
    }

    /**
     * @return the sisaRestitusi
     */
    public BigDecimal getSisaRestitusi() {
        return sisaRestitusi;
    }

    /**
     * @param sisaRestitusi the sisaRestitusi to set
     */
    public void setSisaRestitusi(BigDecimal sisaRestitusi) {
        this.sisaRestitusi = sisaRestitusi;
    }

    /**
     * @return the nilaiSppSaatini
     */
    public BigDecimal getNilaiSppSaatini() {
        return nilaiSppSaatini;
    }

    /**
     * @param nilaiSppSaatini the nilaiSppSaatini to set
     */
    public void setNilaiSppSaatini(BigDecimal nilaiSppSaatini) {
        this.nilaiSppSaatini = nilaiSppSaatini;
    }

    /**
     * @return the idSpp
     */
    public Integer getIdSpp() {
        return idSpp;
    }

    /**
     * @param idSpp the idSpp to set
     */
    public void setIdSpp(Integer idSpp) {
        this.idSpp = idSpp;
    }
}
