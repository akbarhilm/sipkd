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
public class SpmPotNonAyat extends BaseModel {

    private Integer tahun;
    private Integer idSkpd;
    private Integer idSpp;
    private Integer noSpp;
    private Integer idSpm;
    private Integer spmNo;
    private String tglNoSpp;

    private BigDecimal totSpm;

    private String idPot;
    private String cPot;
    private String Pot;
    private String pPot;
    private Integer Status;
    private Float persen;
    private BigDecimal NilaiPot;
    private BigDecimal nilaiKontrak;
    private BigDecimal nilaiJamsostek;
    private String statusPPN;
    // tambahan untuk tes
    private String namaakun;
    
    private String idTmPotongan;
    private String kodeEdit;
    
    // TAMBAHAN BARU 20181024
    private String kodeJenisSetor;
    private String kodeAkunPajak;
    private String kodePegawai;
    private String kodePns;

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
     * @return the spmNo
     */
    public Integer getSpmNo() {
        return spmNo;
    }

    /**
     * @param spmNo the spmNo to set
     */
    public void setSpmNo(Integer spmNo) {
        this.spmNo = spmNo;
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
     * @return the persen
     */
    public Float getPersen() {
        return persen;
    }

    /**
     * @param persen the persen to set
     */
    public void setPersen(Float persen) {
        this.persen = persen;
    }

    /**
     * @return the pPot
     */
    public String getpPot() {
        return pPot;
    }

    /**
     * @param pPot the pPot to set
     */
    public void setpPot(String pPot) {
        this.pPot = pPot;
    }

    /**
     * @return the nilaiKontrak
     */
    public BigDecimal getNilaiKontrak() {
        return nilaiKontrak;
    }

    /**
     * @param nilaiKontrak the nilaiKontrak to set
     */
    public void setNilaiKontrak(BigDecimal nilaiKontrak) {
        this.nilaiKontrak = nilaiKontrak;
    }

    /**
     * @return the nilaiJamsostek
     */
    public BigDecimal getNilaiJamsostek() {
        return nilaiJamsostek;
    }

    /**
     * @param nilaiJamsostek the nilaiJamsostek to set
     */
    public void setNilaiJamsostek(BigDecimal nilaiJamsostek) {
        this.nilaiJamsostek = nilaiJamsostek;
    }

    /**
     * @return the statusPPN
     */
    public String getStatusPPN() {
        return statusPPN;
    }

    /**
     * @param statusPPN the statusPPN to set
     */
    public void setStatusPPN(String statusPPN) {
        this.statusPPN = statusPPN;
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
     * @return the kodeJenisSetor
     */
    public String getKodeJenisSetor() {
        return kodeJenisSetor;
    }

    /**
     * @param kodeJenisSetor the kodeJenisSetor to set
     */
    public void setKodeJenisSetor(String kodeJenisSetor) {
        this.kodeJenisSetor = kodeJenisSetor;
    }

    /**
     * @return the kodeAkunPajak
     */
    public String getKodeAkunPajak() {
        return kodeAkunPajak;
    }

    /**
     * @param kodeAkunPajak the kodeAkunPajak to set
     */
    public void setKodeAkunPajak(String kodeAkunPajak) {
        this.kodeAkunPajak = kodeAkunPajak;
    }

    /**
     * @return the kodePegawai
     */
    public String getKodePegawai() {
        return kodePegawai;
    }

    /**
     * @param kodePegawai the kodePegawai to set
     */
    public void setKodePegawai(String kodePegawai) {
        this.kodePegawai = kodePegawai;
    }

    /**
     * @return the kodePns
     */
    public String getKodePns() {
        return kodePns;
    }

    /**
     * @param kodePns the kodePns to set
     */
    public void setKodePns(String kodePns) {
        this.kodePns = kodePns;
    }
}
