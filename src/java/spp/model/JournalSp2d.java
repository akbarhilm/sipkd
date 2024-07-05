/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
import javax.validation.Valid;

/**
 *
 * @author Zainab
 */

public class JournalSp2d extends BaseModel{
    
    // tambahan journal
    private Pengguna pengguna;
    private Skpd skpd;
    private Integer idskpd;
    
    private String wilayah;
    private String kodewil;
    
    private String idsp2d;
    private String nosp2d;
    private String skpdket;
    private String kode;
    private BigDecimal nilai;
    private String tahun;
    private String tglsah;
    private String tglsahakhir;
    private String ketwilayah;
    private String kodetgl;
    private String kettgl;
    private String ketJurnal;
    private String kodeProsesJour;
    
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
     * @return the wilayah
     */
    public String getWilayah() {
        return wilayah;
    }

    /**
     * @param wilayah the wilayah to set
     */
    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
    }

    /**
     * @return the kodewil
     */
    public String getKodewil() {
        return kodewil;
    }

    /**
     * @param kodewil the kodewil to set
     */
    public void setKodewil(String kodewil) {
        this.kodewil = kodewil;
    }

    /**
     * @return the idsp2d
     */
    public String getIdsp2d() {
        return idsp2d;
    }

    /**
     * @param idsp2d the idsp2d to set
     */
    public void setIdsp2d(String idsp2d) {
        this.idsp2d = idsp2d;
    }

    /**
     * @return the nosp2d
     */
    public String getNosp2d() {
        return nosp2d;
    }

    /**
     * @param nosp2d the nosp2d to set
     */
    public void setNosp2d(String nosp2d) {
        this.nosp2d = nosp2d;
    }

    /**
     * @return the skpdket
     */
    public String getSkpdket() {
        return skpdket;
    }

    /**
     * @param skpdket the skpdket to set
     */
    public void setSkpdket(String skpdket) {
        this.skpdket = skpdket;
    }

    /**
     * @return the kode
     */
    public String getKode() {
        return kode;
    }

    /**
     * @param kode the kode to set
     */
    public void setKode(String kode) {
        this.kode = kode;
    }

    /**
     * @return the nilai
     */
    public BigDecimal getNilai() {
        return nilai;
    }

    /**
     * @param nilai the nilai to set
     */
    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
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
     * @return the tglsah
     */
    public String getTglsah() {
        return tglsah;
    }

    /**
     * @param tglsah the tglsah to set
     */
    public void setTglsah(String tglsah) {
        this.tglsah = tglsah;
    }

    /**
     * @return the ketwilayah
     */
    public String getKetwilayah() {
        return ketwilayah;
    }

    /**
     * @param ketwilayah the ketwilayah to set
     */
    public void setKetwilayah(String ketwilayah) {
        this.ketwilayah = ketwilayah;
    }

    /**
     * @return the kodetgl
     */
    public String getKodetgl() {
        return kodetgl;
    }

    /**
     * @param kodetgl the kodetgl to set
     */
    public void setKodetgl(String kodetgl) {
        this.kodetgl = kodetgl;
    }

    /**
     * @return the kettgl
     */
    public String getKettgl() {
        return kettgl;
    }

    /**
     * @param kettgl the kettgl to set
     */
    public void setKettgl(String kettgl) {
        this.kettgl = kettgl;
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
     * @return the tglsahakhir
     */
    public String getTglsahakhir() {
        return tglsahakhir;
    }

    /**
     * @param tglsahakhir the tglsahakhir to set
     */
    public void setTglsahakhir(String tglsahakhir) {
        this.tglsahakhir = tglsahakhir;
    }

    /**
     * @return the ketJurnal
     */
    public String getKetJurnal() {
        return ketJurnal;
    }

    /**
     * @param ketJurnal the ketJurnal to set
     */
    public void setKetJurnal(String ketJurnal) {
        this.ketJurnal = ketJurnal;
    }

    /**
     * @return the kodeProsesJour
     */
    public String getKodeProsesJour() {
        return kodeProsesJour;
    }

    /**
     * @param kodeProsesJour the kodeProsesJour to set
     */
    public void setKodeProsesJour(String kodeProsesJour) {
        this.kodeProsesJour = kodeProsesJour;
    }

    
}
