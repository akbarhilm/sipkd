/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
//import java.sql.Date;
import javax.validation.Valid;

/**
 *
 * @author Zainab
 */
public class ProsesBku extends BaseModel {

    private Integer id;

    @Valid
    private Skpd skpd;
    private Integer idskpd;
    private String tahun;
    private String kodeSkpd;
    private String namaSkpd;
    private String bulan;
    private String tglPosting;
    private String noUrut;
    private String kodeTransaksi;
    private String noBukti;
    private String tglBukti;
    private String keterangan;
    private String kodeKeg;
    private String namaKeg;
    private String namaAkun;
    private String kodeAkun;
    private BigDecimal nilaiMasuk;
    private BigDecimal nilaiKeluar;
    private BigDecimal saldoKas;
    private String jenis;
    private String beban;
    private String kodeUangPersediaan;
    private String noBku;
    private String noJurnal;
    
    
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
     * @return the bulan
     */
    public String getBulan() {
        return bulan;
    }

    /**
     * @param bulan the bulan to set
     */
    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    /**
     * @return the tglPosting
     */
    public String getTglPosting() {
        return tglPosting;
    }

    /**
     * @param tglPosting the tglPosting to set
     */
    public void setTglPosting(String tglPosting) {
        this.tglPosting = tglPosting;
    }

    /**
     * @return the noUrut
     */
    public String getNoUrut() {
        return noUrut;
    }

    /**
     * @param noUrut the noUrut to set
     */
    public void setNoUrut(String noUrut) {
        this.noUrut = noUrut;
    }

    /**
     * @return the kodeTransaksi
     */
    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    /**
     * @param kodeTransaksi the kodeTransaksi to set
     */
    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    /**
     * @return the noBukti
     */
    public String getNoBukti() {
        return noBukti;
    }

    /**
     * @param noBukti the noBukti to set
     */
    public void setNoBukti(String noBukti) {
        this.noBukti = noBukti;
    }

    /**
     * @return the tglBukti
     */
    public String getTglBukti() {
        return tglBukti;
    }

    /**
     * @param tglBukti the tglBukti to set
     */
    public void setTglBukti(String tglBukti) {
        this.tglBukti = tglBukti;
    }

    /**
     * @return the keterangan
     */
    public String getKeterangan() {
        return keterangan;
    }

    /**
     * @param keterangan the keterangan to set
     */
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    /**
     * @return the kodeKeg
     */
    public String getKodeKeg() {
        return kodeKeg;
    }

    /**
     * @param kodeKeg the kodeKeg to set
     */
    public void setKodeKeg(String kodeKeg) {
        this.kodeKeg = kodeKeg;
    }

    /**
     * @return the namaKeg
     */
    public String getNamaKeg() {
        return namaKeg;
    }

    /**
     * @param namaKeg the namaKeg to set
     */
    public void setNamaKeg(String namaKeg) {
        this.namaKeg = namaKeg;
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
     * @return the nilaiMasuk
     */
    public BigDecimal getNilaiMasuk() {
        return nilaiMasuk;
    }

    /**
     * @param nilaiMasuk the nilaiMasuk to set
     */
    public void setNilaiMasuk(BigDecimal nilaiMasuk) {
        this.nilaiMasuk = nilaiMasuk;
    }

    /**
     * @return the nilaiKeluar
     */
    public BigDecimal getNilaiKeluar() {
        return nilaiKeluar;
    }

    /**
     * @param nilaiKeluar the nilaiKeluar to set
     */
    public void setNilaiKeluar(BigDecimal nilaiKeluar) {
        this.nilaiKeluar = nilaiKeluar;
    }

    /**
     * @return the saldoKas
     */
    public BigDecimal getSaldoKas() {
        return saldoKas;
    }

    /**
     * @param saldoKas the saldoKas to set
     */
    public void setSaldoKas(BigDecimal saldoKas) {
        this.saldoKas = saldoKas;
    }

    /**
     * @return the jenis
     */
    public String getJenis() {
        return jenis;
    }

    /**
     * @param jenis the jenis to set
     */
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    /**
     * @return the beban
     */
    public String getBeban() {
        return beban;
    }

    /**
     * @param beban the beban to set
     */
    public void setBeban(String beban) {
        this.beban = beban;
    }

    /**
     * @return the kodeUangPersediaan
     */
    public String getKodeUangPersediaan() {
        return kodeUangPersediaan;
    }

    /**
     * @param kodeUangPersediaan the kodeUangPersediaan to set
     */
    public void setKodeUangPersediaan(String kodeUangPersediaan) {
        this.kodeUangPersediaan = kodeUangPersediaan;
    }

    /**
     * @return the noBku
     */
    public String getNoBku() {
        return noBku;
    }

    /**
     * @param noBku the noBku to set
     */
    public void setNoBku(String noBku) {
        this.noBku = noBku;
    }

    /**
     * @return the noJurnal
     */
    public String getNoJurnal() {
        return noJurnal;
    }

    /**
     * @param noJurnal the noJurnal to set
     */
    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    
}
