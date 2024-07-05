package spp.model;

import java.math.BigDecimal;

public class SppBlRinci extends BaseModel {

    private String noSpd;
    private Integer idSpd;
    private Integer idBl;
    private Akun akun;
    private Kegiatan kegiatan;
    private BigDecimal nilaiSpp;
    private BigDecimal nilaiSpd;
    private Integer idspp;
    private Integer idSppRinci;
    private BigDecimal nilaiSppSebelum;
    private BigDecimal nilaiSppSisa;
    private String noSpp;
    private Integer nomorBast;
    private String statusSpd;
    private Integer isCek;

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
     * @return the idBl
     */
    public Integer getIdBl() {
        return idBl;
    }

    /**
     * @param idBl the idBl to set
     */
    public void setIdBl(Integer idBl) {
        this.idBl = idBl;
    }

    /**
     * @return the akun
     */
    public Akun getAkun() {
        return akun;
    }

    /**
     * @param akun the akun to set
     */
    public void setAkun(Akun akun) {
        this.akun = akun;
    }

    /**
     * @return the nilaiSpp
     */
    public BigDecimal getNilaiSpp() {
        return nilaiSpp;
    }

    /**
     * @param nilaiSpp the nilaiSpp to set
     */
    public void setNilaiSpp(BigDecimal nilaiSpp) {
        this.nilaiSpp = nilaiSpp;
    }

    /**
     * @return the nilaiSpd
     */
    public BigDecimal getNilaiSpd() {
        return nilaiSpd;
    }

    /**
     * @param nilaiSpd the nilaiSpd to set
     */
    public void setNilaiSpd(BigDecimal nilaiSpd) {
        this.nilaiSpd = nilaiSpd;
    }

    /**
     * @return the noSpd
     */
    public String getNoSpd() {
        return noSpd;
    }

    /**
     * @param noSpd the noSpd to set
     */
    public void setNoSpd(String noSpd) {
        this.noSpd = noSpd;
    }

    /**
     * @return the idspp
     */
    public Integer getIdspp() {
        return idspp;
    }

    /**
     * @param idspp the idspp to set
     */
    public void setIdspp(Integer idspp) {
        this.idspp = idspp;
    }

    /**
     * @return the idSppRinci
     */
    public Integer getIdSppRinci() {
        return idSppRinci;
    }

    /**
     * @param idSppRinci the idSppRinci to set
     */
    public void setIdSppRinci(Integer idSppRinci) {
        this.idSppRinci = idSppRinci;
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
     * @return the nilaiSppSisa
     */
    public BigDecimal getNilaiSppSisa() {
        return nilaiSppSisa;
    }

    /**
     * @param nilaiSppSisa the nilaiSppSisa to set
     */
    public void setNilaiSppSisa(BigDecimal nilaiSppSisa) {
        this.nilaiSppSisa = nilaiSppSisa;
    }

    public void setKegiatan(Kegiatan kegiatan) {
        this.kegiatan = kegiatan;
    }

    /**
     * @return the kegiatan
     */
    public Kegiatan getKegiatan() {
        return kegiatan;
    }

    /**
     * @return the noSpp
     */
    public String getNoSpp() {
        return noSpp;
    }

    /**
     * @param noSpp the noSpp to set
     */
    public void setNoSpp(String noSpp) {
        this.noSpp = noSpp;
    }

    /**
     * @return the nomorBast
     */
    public Integer getNomorBast() {
        return nomorBast;
    }

    /**
     * @param nomorBast the nomorBast to set
     */
    public void setNomorBast(Integer nomorBast) {
        this.nomorBast = nomorBast;
    }

    /**
     * @return the statusSpd
     */
    public String getStatusSpd() {
        return statusSpd;
    }

    /**
     * @param statusSpd the statusSpd to set
     */
    public void setStatusSpd(String statusSpd) {
        this.statusSpd = statusSpd;
    }

    /**
     * @return the isCek
     */
    public Integer getIsCek() {
        return isCek;
    }

    /**
     * @param isCek the isCek to set
     */
    public void setIsCek(Integer isCek) {
        this.isCek = isCek;
    }

}
