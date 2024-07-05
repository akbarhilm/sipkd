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
public class BukuBesar extends BaseModel {

    private Integer id;

    @Valid
    private Skpd skpd;
    private Integer idskpd;
    private String tahun;

    //trjourumumskpd
    private Integer tahunAngg;
    private String tglPosting;
    private String tglPostingAwal;
    private Integer idBas;
    
    // tambahan untuk akun
    private String namaakun;
    private String kodeakun;
    private String ketskpd;
    
    // list
    private String nourut;
    private String nojurnal;
    private String nodokumen;
    private BigDecimal debet;
    private BigDecimal kredit;
    private BigDecimal saldo;

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
     * @return the nourut
     */
    public String getNourut() {
        return nourut;
    }

    /**
     * @param nourut the nourut to set
     */
    public void setNourut(String nourut) {
        this.nourut = nourut;
    }

    /**
     * @return the nojurnal
     */
    public String getNojurnal() {
        return nojurnal;
    }

    /**
     * @param nojurnal the nojurnal to set
     */
    public void setNojurnal(String nojurnal) {
        this.nojurnal = nojurnal;
    }

    /**
     * @return the nodokumen
     */
    public String getNodokumen() {
        return nodokumen;
    }

    /**
     * @param nodokumen the nodokumen to set
     */
    public void setNodokumen(String nodokumen) {
        this.nodokumen = nodokumen;
    }

    /**
     * @return the debet
     */
    public BigDecimal getDebet() {
        return debet;
    }

    /**
     * @param debet the debet to set
     */
    public void setDebet(BigDecimal debet) {
        this.debet = debet;
    }

    /**
     * @return the kredit
     */
    public BigDecimal getKredit() {
        return kredit;
    }

    /**
     * @param kredit the kredit to set
     */
    public void setKredit(BigDecimal kredit) {
        this.kredit = kredit;
    }

    /**
     * @return the saldo
     */
    public BigDecimal getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

}
