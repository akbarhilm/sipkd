/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spp.model;

import java.math.BigDecimal;

/**
 *
 * @author erzy
 */
public class SppBantuanRinci extends BaseModel {
    
     private String noSpd;
    private Integer idSpd;
     private Integer id;
    private Integer idBtlBantuan;
    private Akun akun;
    private String tahun;
    private Skpd skpd;
    private String noSpp;
    private BigDecimal nilaiSpp;
    private BigDecimal nilaiSpd;
    private Integer idspp;
    private Integer idSppRinci;
     private BigDecimal nilaiSppSebelum;
    private BigDecimal nilaiSppSisa;
    private Kegiatan kegiatan;
    private Integer idskpdkoor;
    private String kodeSkpdKoor;
    private String namaSkpdKoor;
    private String rekeningBank;
    private String namaBank;

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
     * @return the idBtlBantuan
     */
    public Integer getIdBtlBantuan() {
        return idBtlBantuan;
    }

    /**
     * @param idBtlBantuan the idBtlBantuan to set
     */
    public void setIdBtlBantuan(Integer idBtlBantuan) {
        this.idBtlBantuan = idBtlBantuan;
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
     * @return the idskpdkoor
     */
    public Integer getIdskpdkoor() {
        return idskpdkoor;
    }

    /**
     * @param idskpdkoor the idskpdkoor to set
     */
    public void setIdskpdkoor(Integer idskpdkoor) {
        this.idskpdkoor = idskpdkoor;
    }

    /**
     * @return the kodeSkpdKoor
     */
    public String getKodeSkpdKoor() {
        return kodeSkpdKoor;
    }

    /**
     * @param kodeSkpdKoor the kodeSkpdKoor to set
     */
    public void setKodeSkpdKoor(String kodeSkpdKoor) {
        this.kodeSkpdKoor = kodeSkpdKoor;
    }

    /**
     * @return the namaSkpdKoor
     */
    public String getNamaSkpdKoor() {
        return namaSkpdKoor;
    }

    /**
     * @param namaSkpdKoor the namaSkpdKoor to set
     */
    public void setNamaSkpdKoor(String namaSkpdKoor) {
        this.namaSkpdKoor = namaSkpdKoor;
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
     * @return the tahun
     */
    public String getTahun() {
        return tahun;
    }

    /**
     * @param tahun the tahun to set
     */
    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

 

    /**
     * @return the namaBank
     */
    public String getNamaBank() {
        return namaBank;
    }

    /**
     * @param namaBank the namaBank to set
     */
    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    /**
     * @return the rekeningBank
     */
    public String getRekeningBank() {
        return rekeningBank;
    }

    /**
     * @param rekeningBank the rekeningBank to set
     */
    public void setRekeningBank(String rekeningBank) {
        this.rekeningBank = rekeningBank;
    }

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
    
}
