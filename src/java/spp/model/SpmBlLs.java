/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Xalamaster
 */
public class SpmBlLs extends SppBl {

    //  private Integer id;
    private Integer idspm;
    private String noSpm;
    private Date tglSpm;
    private String keteranganSpm;
    private String noJurnal;
    private Date tglJurnal;
    private String kodeNihil;
    private Integer idbas;
    private BigDecimal nilaiSpp;
    private String uangmuka;
    private String batasWaktu;
    private String kodePotUmk;
    private Integer idspp;

    private String alamatRekanan;
    private String direkturRekanan;
    private String vaBank;
    private String kodeBankPfk;
    private String namaBankPfk;
    private String kodeBankTransfer;
    private String namaBankTransfer;
    private Integer idBankPfk;
    private boolean virtaBank;

    private List<SpmBlLsRinci> spmBlLsRinci;

    /**
     * @return the id
     *
     * public Integer getId() { return id; }
     *
     * /
     **
     * @param id the id to set
     *
     * public void setId(Integer id) { this.id = id; }
     *
     * /
     **
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
     * @return the kodeBank
     */
    /**
     * @return the noRekeningSPM
     */
    /**
     * @return the spmBlLsRinci
     */
    public List<SpmBlLsRinci> getSpmBlLsRinci() {
        return spmBlLsRinci;
    }

    /**
     * @param spmBlLsRinci the spmBlLsRinci to set
     */
    public void setSpmBlLsRinci(List<SpmBlLsRinci> spmBlLsRinci) {
        this.spmBlLsRinci = spmBlLsRinci;
    }

    /**
     * @return the kodeNihil
     */
    public String getKodeNihil() {
        return kodeNihil;
    }

    /**
     * @param kodeNihil the kodeNihil to set
     */
    public void setKodeNihil(String kodeNihil) {
        this.kodeNihil = kodeNihil;
    }

    /**
     * @return the idbas
     */
    public Integer getIdbas() {
        return idbas;
    }

    /**
     * @param idbas the idbas to set
     */
    public void setIdbas(Integer idbas) {
        this.idbas = idbas;
    }

    /**
     * @return the nilaiSpp
     */
    public BigDecimal getNilaiSpp() {
        return nilaiSpp;
    }

    /**
     * @param nilaiSpp the nilaiSpp to set
     */
    public void setNilaiSpp(BigDecimal nilaiSpp) {
        this.nilaiSpp = nilaiSpp;
    }

    /**
     * @return the uangmuka
     */
    public String getUangmuka() {
        return uangmuka;
    }

    /**
     * @param uangmuka the uangmuka to set
     */
    public void setUangmuka(String uangmuka) {
        this.uangmuka = uangmuka;
    }

    /**
     * @return the batasWaktu
     */
    public String getBatasWaktu() {
        return batasWaktu;
    }

    /**
     * @param batasWaktu the batasWaktu to set
     */
    public void setBatasWaktu(String batasWaktu) {
        this.batasWaktu = batasWaktu;
    }

    /**
     * @return the kodePotUmk
     */
    public String getKodePotUmk() {
        return kodePotUmk;
    }

    /**
     * @param kodePotUmk the kodePotUmk to set
     */
    public void setKodePotUmk(String kodePotUmk) {
        this.kodePotUmk = kodePotUmk;
    }

    /**
     * @return the idspp
     */
    public Integer getIdspp() {
        return idspp;
    }

    /**
     * @param idspp the idspp to set
     */
    public void setIdspp(Integer idspp) {
        this.idspp = idspp;
    }

    /**
     * @return the alamatRekanan
     */
    public String getAlamatRekanan() {
        return alamatRekanan;
    }

    /**
     * @param alamatRekanan the alamatRekanan to set
     */
    public void setAlamatRekanan(String alamatRekanan) {
        this.alamatRekanan = alamatRekanan;
    }

    /**
     * @return the direkturRekanan
     */
    public String getDirekturRekanan() {
        return direkturRekanan;
    }

    /**
     * @param direkturRekanan the direkturRekanan to set
     */
    public void setDirekturRekanan(String direkturRekanan) {
        this.direkturRekanan = direkturRekanan;
    }

    /**
     * @return the vaBank
     */
    public String getVaBank() {
        return vaBank;
    }

    /**
     * @param vaBank the vaBank to set
     */
    public void setVaBank(String vaBank) {
        this.vaBank = vaBank;
    }

    /**
     * @return the kodeBankPfk
     */
    public String getKodeBankPfk() {
        return kodeBankPfk;
    }

    /**
     * @param kodeBankPfk the kodeBankPfk to set
     */
    public void setKodeBankPfk(String kodeBankPfk) {
        this.kodeBankPfk = kodeBankPfk;
    }

    /**
     * @return the namaBankPfk
     */
    public String getNamaBankPfk() {
        return namaBankPfk;
    }

    /**
     * @param namaBankPfk the namaBankPfk to set
     */
    public void setNamaBankPfk(String namaBankPfk) {
        this.namaBankPfk = namaBankPfk;
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
     * @return the idBankPfk
     */
    public Integer getIdBankPfk() {
        return idBankPfk;
    }

    /**
     * @param idBankPfk the idBankPfk to set
     */
    public void setIdBankPfk(Integer idBankPfk) {
        this.idBankPfk = idBankPfk;
    }

    /**
     * @return the virtaBank
     */
    public boolean isVirtaBank() {
        return virtaBank;
    }

    /**
     * @param virtaBank the virtaBank to set
     */
    public void setVirtaBank(boolean virtaBank) {
        this.virtaBank = virtaBank;
    }

    /**
     * @return the kodeKegiatan
     */
}
