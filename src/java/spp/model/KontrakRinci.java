/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author erzypratama
 */
public class KontrakRinci extends BaseModel {

    private static final Logger log = LoggerFactory.getLogger(KontrakRinci.class);
    private Skpd skpd;

    private Integer idSpd;
    private Integer idKontrakRinci;
    private Integer idKontrak;
    private Integer idSkpd;
    private Integer idKegiatan;
    private String tahun;
    private Integer idBas;
    private String kodeAkun;
    private String namaAkun;
    private BigDecimal nilaiKontrak;
    private BigDecimal nilaiSpd;
    private BigDecimal kontrakSebelum;
    private BigDecimal sisaKontrak;
    private BigDecimal nilaiBast;
    private BigDecimal nilaiSpj;
    private BigDecimal saldoUMK;
    private BigDecimal nilaiUmk;
    private BigDecimal nilaiAngg;

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
     * @return the idKontrak
     */
    public Integer getIdKontrak() {
        return idKontrak;
    }

    /**
     * @param idKontrak the idKontrak to set
     */
    public void setIdKontrak(Integer idKontrak) {
        this.idKontrak = idKontrak;
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
     * @return the idBas
     */
    public Integer getIdBas() {
        return idBas;
    }

    /**
     * @param idBas the idBas to set
     */
    public void setIdBas(Integer idBas) {
        this.idBas = idBas;
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
     * @return the nilaiKontrak
     */
    public BigDecimal getNilaiKontrak() {
        return nilaiKontrak;
    }

    /**
     * @param nilaiKontrak the nilaiKontrak to set
     */
    public void setNilaiKontrak(BigDecimal nilaiKontrak) {
        this.nilaiKontrak = nilaiKontrak;
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
     * @return the kontrakSebelum
     */
    public BigDecimal getKontrakSebelum() {
        return kontrakSebelum;
    }

    /**
     * @param kontrakSebelum the kontrakSebelum to set
     */
    public void setKontrakSebelum(BigDecimal kontrakSebelum) {
        this.kontrakSebelum = kontrakSebelum;
    }

    /**
     * @return the sisaKontrak
     */
    public BigDecimal getSisaKontrak() {
        return sisaKontrak;
    }

    /**
     * @param sisaKontrak the sisaKontrak to set
     */
    public void setSisaKontrak(BigDecimal sisaKontrak) {
        this.sisaKontrak = sisaKontrak;
    }

    /**
     * @return the idKontrakRinci
     */
    public Integer getIdKontrakRinci() {
        return idKontrakRinci;
    }

    /**
     * @param idKontrakRinci the idKontrakRinci to set
     */
    public void setIdKontrakRinci(Integer idKontrakRinci) {
        this.idKontrakRinci = idKontrakRinci;
    }

    /**
     * @return the nilaiBast
     */
    public BigDecimal getNilaiBast() {
        return nilaiBast;
    }

    /**
     * @param nilaiBast the nilaiBast to set
     */
    public void setNilaiBast(BigDecimal nilaiBast) {
        this.nilaiBast = nilaiBast;
    }

    /**
     * @return the nilaiSpj
     */
    public BigDecimal getNilaiSpj() {
        return nilaiSpj;
    }

    /**
     * @param nilaiSpj the nilaiSpj to set
     */
    public void setNilaiSpj(BigDecimal nilaiSpj) {
        this.nilaiSpj = nilaiSpj;
    }

    /**
     * @return the saldoUMK
     */
    public BigDecimal getSaldoUMK() {
        return saldoUMK;
    }

    /**
     * @param saldoUMK the saldoUMK to set
     */
    public void setSaldoUMK(BigDecimal saldoUMK) {
        this.saldoUMK = saldoUMK;
    }

    /**
     * @return the nilaiUmk
     */
    public BigDecimal getNilaiUmk() {
        return nilaiUmk;
    }

    /**
     * @param nilaiUmk the nilaiUmk to set
     */
    public void setNilaiUmk(BigDecimal nilaiUmk) {
        this.nilaiUmk = nilaiUmk;
    }

    /**
     * @return the nilaiAngg
     */
    public BigDecimal getNilaiAngg() {
        return nilaiAngg;
    }

    /**
     * @param nilaiAngg the nilaiAngg to set
     */
    public void setNilaiAngg(BigDecimal nilaiAngg) {
        this.nilaiAngg = nilaiAngg;
    }

}
