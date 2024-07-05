package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Admin
 */
public class Sp2d extends SpmUp {

    private Integer idSp2d;
    private Integer nomorSp2d;
    @NotNull
    private Date tglSp2d;
    private String kodeWilayahProses;
    private String pejabatTtdSp2d;
    private String namaPejabatTtdSp2d;
    private BigDecimal nilaiSp2d;
    private String kodeCetak;
    private String kodePengesahan;
    private String kodeCetakPotongan;
    private Date tglSp2dSah;

    /**
     * @return the idSp2d
     */
    public Integer getIdSp2d() {
        return idSp2d;
    }

    /**
     * @param idSp2d the idSp2d to set
     */
    public void setIdSp2d(Integer idSp2d) {
        this.idSp2d = idSp2d;
    }

    /**
     * @return the nomorSp2d
     */
    public Integer getNomorSp2d() {
        return nomorSp2d;
    }

    /**
     * @param nomorSp2d the nomorSp2d to set
     */
    public void setNomorSp2d(Integer nomorSp2d) {
        this.nomorSp2d = nomorSp2d;
    }

    /**
     * @return the tglSp2d
     */
    public Date getTglSp2d() {
        return tglSp2d;
    }

    /**
     * @param tglSp2d the tglSp2d to set
     */
    public void setTglSp2d(Date tglSp2d) {
        this.tglSp2d = tglSp2d;
    }

    /**
     * @return the kodeWilayahProses
     */
    public String getKodeWilayahProses() {
        return kodeWilayahProses;
    }

    /**
     * @param kodeWilayahProses the kodeWilayahProses to set
     */
    public void setKodeWilayahProses(String kodeWilayahProses) {
        this.kodeWilayahProses = kodeWilayahProses;
    }

    /**
     * @return the pejabatTtdSp2d
     */
    public String getPejabatTtdSp2d() {
        return pejabatTtdSp2d;
    }

    /**
     * @param pejabatTtdSp2d the pejabatTtdSp2d to set
     */
    public void setPejabatTtdSp2d(String pejabatTtdSp2d) {
        this.pejabatTtdSp2d = pejabatTtdSp2d;
    }

    /**
     * @return the nilaiSp2d
     */
    public BigDecimal getNilaiSp2d() {
        return nilaiSp2d;
    }

    /**
     * @param nilaiSp2d the nilaiSp2d to set
     */
    public void setNilaiSp2d(BigDecimal nilaiSp2d) {
        this.nilaiSp2d = nilaiSp2d;
    }

    /**
     * @return the kodeCetak
     */
    public String getKodeCetak() {
        return kodeCetak;
    }

    /**
     * @param kodeCetak the kodeCetak to set
     */
    public void setKodeCetak(String kodeCetak) {
        this.kodeCetak = kodeCetak;
    }

    /**
     * @return the kodePengesahan
     */
    public String getKodePengesahan() {
        return kodePengesahan;
    }

    /**
     * @param kodePengesahan the kodePengesahan to set
     */
    public void setKodePengesahan(String kodePengesahan) {
        this.kodePengesahan = kodePengesahan;
    }

    /**
     * @return the kodeCetakPotongan
     */
    public String getKodeCetakPotongan() {
        return kodeCetakPotongan;
    }

    /**
     * @param kodeCetakPotongan the kodeCetakPotongan to set
     */
    public void setKodeCetakPotongan(String kodeCetakPotongan) {
        this.kodeCetakPotongan = kodeCetakPotongan;
    }

    /**
     * @return the namaPejabatTtdSp2d
     */
    public String getNamaPejabatTtdSp2d() {
        return namaPejabatTtdSp2d;
    }

    /**
     * @param namaPejabatTtdSp2d the namaPejabatTtdSp2d to set
     */
    public void setNamaPejabatTtdSp2d(String namaPejabatTtdSp2d) {
        this.namaPejabatTtdSp2d = namaPejabatTtdSp2d;
    }

    /**
     * @return the tglSp2dSah
     */
    public Date getTglSp2dSah() {
        return tglSp2dSah;
    }

    /**
     * @param tglSp2dSah the tglSp2dSah to set
     */
    public void setTglSp2dSah(Date tglSp2dSah) {
        this.tglSp2dSah = tglSp2dSah;
    }
}
