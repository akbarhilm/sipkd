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

public class JournalSPJ extends BaseModel{
    
    // tambahan journal
    private Skpd skpd;
    private String kodekeg;
    private String namakeg;
    private String akunbelanja;
    private String akunjurnal;
    private String idskpd;
    private Integer idSpj;
    private String tahun;
    private String bulan;
    private String kodeBulan;
    private String namaBulan;
    private BigDecimal nilai_spj;
    private Integer noBKU;
    

    /**
     * @return the kodekeg
     */
    public String getKodekeg() {
        return kodekeg;
    }

    /**
     * @param kodekeg the kodekeg to set
     */
    public void setKodekeg(String kodekeg) {
        this.kodekeg = kodekeg;
    }

    /**
     * @return the namakeg
     */
    public String getNamakeg() {
        return namakeg;
    }

    /**
     * @param namakeg the namakeg to set
     */
    public void setNamakeg(String namakeg) {
        this.namakeg = namakeg;
    }

    /**
     * @return the akunbelanja
     */
    public String getAkunbelanja() {
        return akunbelanja;
    }

    /**
     * @param akunbelanja the akunbelanja to set
     */
    public void setAkunbelanja(String akunbelanja) {
        this.akunbelanja = akunbelanja;
    }

    /**
     * @return the akunjurnal
     */
    public String getAkunjurnal() {
        return akunjurnal;
    }

    /**
     * @param akunjurnal the akunjurnal to set
     */
    public void setAkunjurnal(String akunjurnal) {
        this.akunjurnal = akunjurnal;
    }

    /**
     * @return the idskpd
     */
    public String getIdskpd() {
        return idskpd;
    }

    /**
     * @param idskpd the idskpd to set
     */
    public void setIdskpd(String idskpd) {
        this.idskpd = idskpd;
    }

    /**
     * @return the idSpj
     */
    public Integer getIdSpj() {
        return idSpj;
    }

    /**
     * @param idSpj the idSpj to set
     */
    public void setIdSpj(Integer idSpj) {
        this.idSpj = idSpj;
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
     * @return the namaBulan
     */
    public String getNamaBulan() {
        return namaBulan;
    }

    /**
     * @param namaBulan the namaBulan to set
     */
    public void setNamaBulan(String namaBulan) {
        this.namaBulan = namaBulan;
    }

    /**
     * @return the nilai_spj
     */
    public BigDecimal getNilai_spj() {
        return nilai_spj;
    }

    /**
     * @param nilai_spj the nilai_spj to set
     */
    public void setNilai_spj(BigDecimal nilai_spj) {
        this.nilai_spj = nilai_spj;
    }

    /**
     * @return the noBKU
     */
    public Integer getNoBKU() {
        return noBKU;
    }

    /**
     * @param noBKU the noBKU to set
     */
    public void setNoBKU(Integer noBKU) {
        this.noBKU = noBKU;
    }
    
     
}
