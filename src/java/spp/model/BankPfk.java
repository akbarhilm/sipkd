/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.validation.Valid;
/**
 *
 * @author Husen
 */
public class BankPfk extends BaseModel{
    
    private Integer id;
    private Integer idBank;
    private Integer idInduk;
    private String kodeBankTransfer;
    private String namaBankTransfer;
    private String kodeBank;
    private String namaBank;
    private String kodeBankRtgs;
    private String namaBankRtgs;
    private String alamatBank;
    private String kodeAktif;
    
    public Integer getId() {
        return id;
    }

    /**
     * @return the idBank
     */
    public Integer getIdBank() {
        return idBank;
    }

    /**
     * @param idBank the idBank to set
     */
    public void setIdBank(Integer idBank) {
        this.idBank = idBank;
    }

    /**
     * @return the idInduk
     */
    public Integer getIdInduk() {
        return idInduk;
    }

    /**
     * @param idInduk the idInduk to set
     */
    public void setIdInduk(Integer idInduk) {
        this.idInduk = idInduk;
    }

    /**
     * @return the kodeBankTransfer
     */
    public String getKodeBankTransfer() {
        return kodeBankTransfer;
    }

    /**
     * @param kodeBankTransfer the kodeBankTransfer to set
     */
    public void setKodeBankTransfer(String kodeBankTransfer) {
        this.kodeBankTransfer = kodeBankTransfer;
    }

    /**
     * @return the namaBankTransfer
     */
    public String getNamaBankTransfer() {
        return namaBankTransfer;
    }

    /**
     * @param namaBankTransfer the namaBankTransfer to set
     */
    public void setNamaBankTransfer(String namaBankTransfer) {
        this.namaBankTransfer = namaBankTransfer;
    }

    /**
     * @return the kodeBank
     */
    public String getKodeBank() {
        return kodeBank;
    }

    /**
     * @param kodeBank the kodeBank to set
     */
    public void setKodeBank(String kodeBank) {
        this.kodeBank = kodeBank;
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
     * @return the kodeBankRtgs
     */
    public String getKodeBankRtgs() {
        return kodeBankRtgs;
    }

    /**
     * @param kodeBankRtgs the kodeBankRtgs to set
     */
    public void setKodeBankRtgs(String kodeBankRtgs) {
        this.kodeBankRtgs = kodeBankRtgs;
    }

    /**
     * @return the namaBankRtgs
     */
    public String getNamaBankRtgs() {
        return namaBankRtgs;
    }

    /**
     * @param namaBankRtgs the namaBankRtgs to set
     */
    public void setNamaBankRtgs(String namaBankRtgs) {
        this.namaBankRtgs = namaBankRtgs;
    }

    /**
     * @return the alamatBank
     */
    public String getAlamatBank() {
        return alamatBank;
    }

    /**
     * @param alamatBank the alamatBank to set
     */
    public void setAlamatBank(String alamatBank) {
        this.alamatBank = alamatBank;
    }

    /**
     * @return the kodeAktif
     */
    public String getKodeAktif() {
        return kodeAktif;
    }

    /**
     * @param kodeAktif the kodeAktif to set
     */
    public void setKodeAktif(String kodeAktif) {
        this.kodeAktif = kodeAktif;
    }
    
    
}
