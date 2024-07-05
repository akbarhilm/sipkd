/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spp.model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Xalamaster
 */
public class SpmBtlLs extends SppBtl {
    private Integer id;
    private Integer idspm;
    private String noSpm;
    private Date tglSpm;
    private String keteranganSpm;
    private String noJurnal;
    private Date tglJurnal;
    private String noNpwp;
    private String kodeBank;
    private String namaBank;
    private String noRekeningSPM;
     private String namaPenerima;
    private String alamatBantuan;
    private String namaYayasan;
    private String batas;
    private String kodePot;
    
    private List<SpmBtlLsRinci> spmBtlLsRinci;

    /**
     * @return the idspm
     */
    public Integer getIdspm() {
        return idspm;
    }

    /**
     * @param idspm the idspm to set
     */
    public void setIdspm(Integer idspm) {
        this.idspm = idspm;
    }

    /**
     * @return the noSpm
     */
    public String getNoSpm() {
        return noSpm;
    }

    /**
     * @param noSpm the noSpm to set
     */
    public void setNoSpm(String noSpm) {
        this.noSpm = noSpm;
    }

    /**
     * @return the tglSpm
     */
    public Date getTglSpm() {
        return tglSpm;
    }

    /**
     * @param tglSpm the tglSpm to set
     */
    public void setTglSpm(Date tglSpm) {
        this.tglSpm = tglSpm;
    }

    /**
     * @return the keteranganSpm
     */
    public String getKeteranganSpm() {
        return keteranganSpm;
    }

    /**
     * @param keteranganSpm the keteranganSpm to set
     */
    public void setKeteranganSpm(String keteranganSpm) {
        this.keteranganSpm = keteranganSpm;
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

    /**
     * @return the tglJurnal
     */
    public Date getTglJurnal() {
        return tglJurnal;
    }

    /**
     * @param tglJurnal the tglJurnal to set
     */
    public void setTglJurnal(Date tglJurnal) {
        this.tglJurnal = tglJurnal;
    }

    /**
     * @return the noNpwp
     */
    public String getNoNpwp() {
        return noNpwp;
    }

    /**
     * @param noNpwp the noNpwp to set
     */
    public void setNoNpwp(String noNpwp) {
        this.noNpwp = noNpwp;
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
     * @return the noRekeningSPM
     */
    public String getNoRekeningSPM() {
        return noRekeningSPM;
    }

    /**
     * @param noRekeningSPM the noRekeningSPM to set
     */
    public void setNoRekeningSPM(String noRekeningSPM) {
        this.noRekeningSPM = noRekeningSPM;
    }

    /**
     * @return the spmBtlLsRinci
     */
    public List<SpmBtlLsRinci> getSpmBtlLsRinci() {
        return spmBtlLsRinci;
    }

    /**
     * @param spmBtlLsRinci the spmBtlLsRinci to set
     */
    public void setSpmBtlLsRinci(List<SpmBtlLsRinci> spmBtlLsRinci) {
        this.spmBtlLsRinci = spmBtlLsRinci;
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

    /**
     * @return the namaPenerima
     */
    public String getNamaPenerima() {
        return namaPenerima;
    }

    /**
     * @param namaPenerima the namaPenerima to set
     */
    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    /**
     * @return the alamatBantuan
     */
    public String getAlamatBantuan() {
        return alamatBantuan;
    }

    /**
     * @param alamatBantuan the alamatBantuan to set
     */
    public void setAlamatBantuan(String alamatBantuan) {
        this.alamatBantuan = alamatBantuan;
    }

    /**
     * @return the namaYayasan
     */
    public String getNamaYayasan() {
        return namaYayasan;
    }

    /**
     * @param namaYayasan the namaYayasan to set
     */
    public void setNamaYayasan(String namaYayasan) {
        this.namaYayasan = namaYayasan;
    }

    /**
     * @return the batas
     */
    public String getBatas() {
        return batas;
    }

    /**
     * @param batas the batas to set
     */
    public void setBatas(String batas) {
        this.batas = batas;
    }

    /**
     * @return the kodePot
     */
    public String getKodePot() {
        return kodePot;
    }

    /**
     * @param kodePot the kodePot to set
     */
    public void setKodePot(String kodePot) {
        this.kodePot = kodePot;
    }

}
