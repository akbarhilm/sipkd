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

public class JournalSkpd extends BaseModel{
    
    // tambahan journal
    private Skpd skpd;
    private Integer idskpd;
    
    private String kodeSkpd;
    private String namaSkpd;
    private String unit;
    private String tahun;
    
    private Integer idBas;
    private String kodeAkun;
    private String namaAkun;
    private BigDecimal nilaiDebet;
    private BigDecimal nilaiKredit;
    private BigDecimal totalDebet;
    private BigDecimal totalKredit;
    private BigDecimal nilaiSaldo;
    

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
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
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
     * @return the nilaiDebet
     */
    public BigDecimal getNilaiDebet() {
        return nilaiDebet;
    }

    /**
     * @param nilaiDebet the nilaiDebet to set
     */
    public void setNilaiDebet(BigDecimal nilaiDebet) {
        this.nilaiDebet = nilaiDebet;
    }

    /**
     * @return the nilaiKredit
     */
    public BigDecimal getNilaiKredit() {
        return nilaiKredit;
    }

    /**
     * @param nilaiKredit the nilaiKredit to set
     */
    public void setNilaiKredit(BigDecimal nilaiKredit) {
        this.nilaiKredit = nilaiKredit;
    }

    /**
     * @return the totalDebet
     */
    public BigDecimal getTotalDebet() {
        return totalDebet;
    }

    /**
     * @param totalDebet the totalDebet to set
     */
    public void setTotalDebet(BigDecimal totalDebet) {
        this.totalDebet = totalDebet;
    }

    /**
     * @return the totalKredit
     */
    public BigDecimal getTotalKredit() {
        return totalKredit;
    }

    /**
     * @param totalKredit the totalKredit to set
     */
    public void setTotalKredit(BigDecimal totalKredit) {
        this.totalKredit = totalKredit;
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
    
}
