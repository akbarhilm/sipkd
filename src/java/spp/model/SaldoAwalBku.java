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
public class SaldoAwalBku extends BaseModel {

    private Integer id;

    @Valid
    private Skpd skpd;
    private Integer idskpd;
    private String tahun;
    private BigDecimal nilaiSaldo;
    private Integer idBas;
    private String kodeAkun;
    private String namaAkun;
    private String kodeStatus;
    
   
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
     * @return the nilaiSaldo
     */
    public BigDecimal getNilaiSaldo() {
        return nilaiSaldo;
    }

    /**
     * @param nilaiSaldo the nilaiSaldo to set
     */
    public void setNilaiSaldo(BigDecimal nilaiSaldo) {
        this.nilaiSaldo = nilaiSaldo;
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
     * @return the kodeStatus
     */
    public String getKodeStatus() {
        return kodeStatus;
    }

    /**
     * @param kodeStatus the kodeStatus to set
     */
    public void setKodeStatus(String kodeStatus) {
        this.kodeStatus = kodeStatus;
    }

    
}
