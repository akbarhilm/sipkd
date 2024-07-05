/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.model;

import java.math.BigDecimal;
import javax.validation.Valid;

/**
 *
 * @author Zainab
 */
public class Neraca extends BaseModel {

    private Integer id;

    @Valid
    private Pengguna pengguna;
    private Skpd skpd;
    private Integer idskpd;
    private String tahun;

    //trjourumumskpd
    private Integer tahunAngg;
    private String tglPosting;
    private String tglPostingAwal;
    private Integer idBas;
    private String kodeskpd;
    private String namaskpd;
    private String bulan;
    private String kodeBulan;
    private String ketBulan;
    
    // tambahan untuk akun
    private String namaakun;
    private String kodeakun;
    private String ketskpd;

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
     * @return the tahunAngg
     */
    public Integer getTahunAngg() {
        return tahunAngg;
    }

    /**
     * @param tahunAngg the tahunAngg to set
     */
    public void setTahunAngg(Integer tahunAngg) {
        this.tahunAngg = tahunAngg;
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
     * @return the namaakun
     */
    public String getNamaakun() {
        return namaakun;
    }

    /**
     * @param namaakun the namaakun to set
     */
    public void setNamaakun(String namaakun) {
        this.namaakun = namaakun;
    }

    
    /**
     * @return the kodeakun
     */
    public String getKodeakun() {
        return kodeakun;
    }

    /**
     * @param kodeakun the kodeakun to set
     */
    public void setKodeakun(String kodeakun) {
        this.kodeakun = kodeakun;
    }

    /**
     * @return the ketskpd
     */
    public String getKetskpd() {
        return ketskpd;
    }

    /**
     * @param ketskpd the ketskpd to set
     */
    public void setKetskpd(String ketskpd) {
        this.ketskpd = ketskpd;
    }

    /**
     * @return the tglPostingAwal
     */
    public String getTglPostingAwal() {
        return tglPostingAwal;
    }

    /**
     * @param tglPostingAwal the tglPostingAwal to set
     */
    public void setTglPostingAwal(String tglPostingAwal) {
        this.tglPostingAwal = tglPostingAwal;
    }

    /**
     * @return the kodeskpd
     */
    public String getKodeskpd() {
        return kodeskpd;
    }

    /**
     * @param kodeskpd the kodeskpd to set
     */
    public void setKodeskpd(String kodeskpd) {
        this.kodeskpd = kodeskpd;
    }

    /**
     * @return the namaskpd
     */
    public String getNamaskpd() {
        return namaskpd;
    }

    /**
     * @param namaskpd the namaskpd to set
     */
    public void setNamaskpd(String namaskpd) {
        this.namaskpd = namaskpd;
    }

    /**
     * @return the pengguna
     */
    public Pengguna getPengguna() {
        return pengguna;
    }

    /**
     * @param pengguna the pengguna to set
     */
    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
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
     * @return the kodeBulan
     */
    public String getKodeBulan() {
        return kodeBulan;
    }

    /**
     * @param kodeBulan the kodeBulan to set
     */
    public void setKodeBulan(String kodeBulan) {
        this.kodeBulan = kodeBulan;
    }

    /**
     * @return the ketBulan
     */
    public String getKetBulan() {
        return ketBulan;
    }

    /**
     * @param ketBulan the ketBulan to set
     */
    public void setKetBulan(String ketBulan) {
        this.ketBulan = ketBulan;
    }

}
