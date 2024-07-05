package spp.model;

import java.math.BigDecimal;
import java.sql.Date;

public class SppBtlRinci extends BaseModel {

    private String noSpd;
    private Integer idSpd;
    private Integer idBtl;
    private Akun akun;
    private Kegiatan kegiatan;
    private BigDecimal nilaiSpp;
    private BigDecimal nilaiSpd;
    private Integer idspp;
    private Integer idSppRinci;
    private BigDecimal nilaiSppSebelum;
    private BigDecimal nilaiSppSisa;
    private Integer SppNo;
    private Date tglSpd;
    private Date tglSpp;
    private String nipPptk;
    private String nPptk;
    private String statusSpd;
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
    public Integer getIdBtl() {
        return idBtl;
    }

    /**
     * @param idBtl the idBl to set
     */
    public void setIdBtl(Integer idBtl) {
        this.idBtl = idBtl;
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

    /**
     * @return the SppNo
     */
    public Integer getSppNo() {
        return SppNo;
    }

    /**
     * @param SppNo the SppNo to set
     */
    public void setSppNo(Integer SppNo) {
        this.SppNo = SppNo;
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
     * @return the tglSpp
     */
    public Date getTglSpp() {
        return tglSpp;
    }

    /**
     * @param tglSpp the tglSpp to set
     */
    public void setTglSpp(Date tglSpp) {
        this.tglSpp = tglSpp;
    }

    /**
     * @return the nipPptk
     */
    public String getNipPptk() {
        return nipPptk;
    }

    /**
     * @param nipPptk the nipPptk to set
     */
    public void setNipPptk(String nipPptk) {
        this.nipPptk = nipPptk;
    }

    /**
     * @return the nPptk
     */
    public String getnPptk() {
        return nPptk;
    }

    /**
     * @param nPptk the nPptk to set
     */
    public void setnPptk(String nPptk) {
        this.nPptk = nPptk;
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

}
