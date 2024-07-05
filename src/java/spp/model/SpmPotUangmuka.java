/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spp.model;

import java.math.BigDecimal;
/**
 *
 * @author Xalamaster
 */
public class SpmPotUangmuka extends BaseModel  {
    
    private Integer tahun;
    private Integer idSkpd;
    private Integer idSpp;
    private Integer noSpp;
    private Integer idSpm;
    private String tglNoSpp;
    private String skpd;
    private String nospm;
    private BigDecimal totSpm;
    private String idPot;
    private String cPot;
    private String Pot;
    private Integer Status;
    private BigDecimal NilaiPot;
    private String idTmPotongan;
    private String kodeEdit;
    private BigDecimal paguUmk;
    private BigDecimal nilaiUmk;
    private BigDecimal nilaiSebelum;
    private BigDecimal nilaiPotongan;
    private BigDecimal sisaPotongan;
    private String kodeAkun;
    private String namaAkun;
    private Integer idBas;
    private String kodeUmk;
    
    /**
     * @return the tahun
     */
    public Integer getTahun() {
        return tahun;
    }

    /**
     * @param tahun the tahun to set
     */
    public void setTahun(Integer tahun) {
        this.tahun = tahun;
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
     * @return the idSpp
     */
    public Integer getIdSpp() {
        return idSpp;
    }

    /**
     * @param idSpp the idSpp to set
     */
    public void setIdSpp(Integer idSpp) {
        this.idSpp = idSpp;
    }

    /**
     * @return the noSpp
     */
    public Integer getNoSpp() {
        return noSpp;
    }

    /**
     * @param noSpp the noSpp to set
     */
    public void setNoSpp(Integer noSpp) {
        this.noSpp = noSpp;
    }

    /**
     * @return the idSpm
     */
    public Integer getIdSpm() {
        return idSpm;
    }

    /**
     * @param idSpm the idSpm to set
     */
    public void setIdSpm(Integer idSpm) {
        this.idSpm = idSpm;
    }

    /**
     * @return the tglNoSpp
     */
    public String getTglNoSpp() {
        return tglNoSpp;
    }

    /**
     * @param tglNoSpp the tglNoSpp to set
     */
    public void setTglNoSpp(String tglNoSpp) {
        this.tglNoSpp = tglNoSpp;
    }

    /**
     * @return the skpd
     */
    public String getSkpd() {
        return skpd;
    }

    /**
     * @param skpd the skpd to set
     */
    public void setSkpd(String skpd) {
        this.skpd = skpd;
    }

    /**
     * @return the nospm
     */
    public String getNospm() {
        return nospm;
    }

    /**
     * @param nospm the nospm to set
     */
    public void setNospm(String nospm) {
        this.nospm = nospm;
    }

    /**
     * @return the totSpm
     */
    public BigDecimal getTotSpm() {
        return totSpm;
    }

    /**
     * @param totSpm the totSpm to set
     */
    public void setTotSpm(BigDecimal totSpm) {
        this.totSpm = totSpm;
    }

    /**
     * @return the idPot
     */
    public String getIdPot() {
        return idPot;
    }

    /**
     * @param idPot the idPot to set
     */
    public void setIdPot(String idPot) {
        this.idPot = idPot;
    }

    /**
     * @return the cPot
     */
    public String getcPot() {
        return cPot;
    }

    /**
     * @param cPot the cPot to set
     */
    public void setcPot(String cPot) {
        this.cPot = cPot;
    }

    /**
     * @return the Pot
     */
    public String getPot() {
        return Pot;
    }

    /**
     * @param Pot the Pot to set
     */
    public void setPot(String Pot) {
        this.Pot = Pot;
    }

    /**
     * @return the Status
     */
    public Integer getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(Integer Status) {
        this.Status = Status;
    }

    /**
     * @return the NilaiPot
     */
    public BigDecimal getNilaiPot() {
        return NilaiPot;
    }

    /**
     * @param NilaiPot the NilaiPot to set
     */
    public void setNilaiPot(BigDecimal NilaiPot) {
        this.NilaiPot = NilaiPot;
    }

    /**
     * @return the idTmPotongan
     */
    public String getIdTmPotongan() {
        return idTmPotongan;
    }

    /**
     * @param idTmPotongan the idTmPotongan to set
     */
    public void setIdTmPotongan(String idTmPotongan) {
        this.idTmPotongan = idTmPotongan;
    }

    /**
     * @return the kodeEdit
     */
    public String getKodeEdit() {
        return kodeEdit;
    }

    /**
     * @param kodeEdit the kodeEdit to set
     */
    public void setKodeEdit(String kodeEdit) {
        this.kodeEdit = kodeEdit;
    }

    /**
     * @return the paguUmk
     */
    public BigDecimal getPaguUmk() {
        return paguUmk;
    }

    /**
     * @param paguUmk the paguUmk to set
     */
    public void setPaguUmk(BigDecimal paguUmk) {
        this.paguUmk = paguUmk;
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
     * @return the nilaiSebelum
     */
    public BigDecimal getNilaiSebelum() {
        return nilaiSebelum;
    }

    /**
     * @param nilaiSebelum the nilaiSebelum to set
     */
    public void setNilaiSebelum(BigDecimal nilaiSebelum) {
        this.nilaiSebelum = nilaiSebelum;
    }

    /**
     * @return the nilaiPotongan
     */
    public BigDecimal getNilaiPotongan() {
        return nilaiPotongan;
    }

    /**
     * @param nilaiPotongan the nilaiPotongan to set
     */
    public void setNilaiPotongan(BigDecimal nilaiPotongan) {
        this.nilaiPotongan = nilaiPotongan;
    }

    /**
     * @return the sisaPotongan
     */
    public BigDecimal getSisaPotongan() {
        return sisaPotongan;
    }

    /**
     * @param sisaPotongan the sisaPotongan to set
     */
    public void setSisaPotongan(BigDecimal sisaPotongan) {
        this.sisaPotongan = sisaPotongan;
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
     * @return the kodeUmk
     */
    public String getKodeUmk() {
        return kodeUmk;
    }

    /**
     * @param kodeUmk the kodeUmk to set
     */
    public void setKodeUmk(String kodeUmk) {
        this.kodeUmk = kodeUmk;
    }
    
}
