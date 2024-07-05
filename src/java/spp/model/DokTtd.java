package spp.model;

import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class DokTtd {

    private Integer id;
    private String isAKtif;
    private String nip;
    private String nama;
    private String namaJabatan;
    private String kodeDokumen;
    private BigDecimal nilaiDokMin;
    private BigDecimal nilaiDokMax;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the isAKtif
     */
    public String getIsAKtif() {
        return isAKtif;
    }

    /**
     * @param isAKtif the isAKtif to set
     */
    public void setIsAKtif(String isAKtif) {
        this.isAKtif = isAKtif;
    }

    /**
     * @return the nip
     */
    public String getNip() {
        return nip;
    }

    /**
     * @param nip the nip to set
     */
    public void setNip(String nip) {
        this.nip = nip;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the namaJabatan
     */
    public String getNamaJabatan() {
        return namaJabatan;
    }

    /**
     * @param namaJabatan the namaJabatan to set
     */
    public void setNamaJabatan(String namaJabatan) {
        this.namaJabatan = namaJabatan;
    }

    /**
     * @return the kodeDokumen
     */
    public String getKodeDokumen() {
        return kodeDokumen;
    }

    /**
     * @param kodeDokumen the kodeDokumen to set
     */
    public void setKodeDokumen(String kodeDokumen) {
        this.kodeDokumen = kodeDokumen;
    }

    /**
     * @return the nilaiDokMin
     */
    public BigDecimal getNilaiDokMin() {
        return nilaiDokMin;
    }

    /**
     * @param nilaiDokMin the nilaiDokMin to set
     */
    public void setNilaiDokMin(BigDecimal nilaiDokMin) {
        this.nilaiDokMin = nilaiDokMin;
    }

    /**
     * @return the nilaiDokMax
     */
    public BigDecimal getNilaiDokMax() {
        return nilaiDokMax;
    }

    /**
     * @param nilaiDokMax the nilaiDokMax to set
     */
    public void setNilaiDokMax(BigDecimal nilaiDokMax) {
        this.nilaiDokMax = nilaiDokMax;
    }
    

}
