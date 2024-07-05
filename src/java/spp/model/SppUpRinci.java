package spp.model;

import java.math.BigDecimal;
import java.sql.Date;

public class SppUpRinci extends BaseModel {

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
    private Date tglSpd;

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
     * @return the kegiatan
     */
    public Kegiatan getKegiatan() {
        return kegiatan;
    }

    /**
     * @param kegiatan the kegiatan to set
     */
    public void setKegiatan(Kegiatan kegiatan) {
        this.kegiatan = kegiatan;
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

    /**
     * @return the tglSpd
     */
    public Date getTglSpd() {
        return tglSpd;
    }

    /**
     * @param tglSpd the tglSpd to set
     */
    public void setTglSpd(Date tglSpd) {
        this.tglSpd = tglSpd;
    }

}
