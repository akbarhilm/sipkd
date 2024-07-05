/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spp.model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Xalamaster
 */
public class TrxBankDki extends BaseModel  {
    
    private Integer id;
    private Skpd skpd;
    
    private Integer idTransaksi;
    private String tahun;
    private String jenis;
    private String kodeDok;
    private String kodeSkpd;
    private String unitKerja;
    private String namaSkpd;
    private String ketDokumen;
    private String noKontrak;
    private String kodeBank;
    private String namaBank;
    private String rekeningBank;
    private String namaRekanan;
    private String alamatRekanan;
    private String dirRekanan;
    private BigDecimal nilaiTransfer;
    private String persenPot;
    private String uraianDok;
    private Date tglSah;
    private String npwpRekanan;
    private String tahunPajak;
    private String bulanAwal;
    private String bulanAkhir;
    private String kodeProsesDki;
    private Date tglProsesDki;
    private String statusDki;
    private String ketProsesDki;
    private Integer idUser;
    private String namaUser;
    
    
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
     * @return the jenis
     */
    public String getJenis() {
        return jenis;
    }

    /**
     * @param jenis the jenis to set
     */
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    /**
     * @return the kodeDok
     */
    public String getKodeDok() {
        return kodeDok;
    }

    /**
     * @param kodeDok the kodeDok to set
     */
    public void setKodeDok(String kodeDok) {
        this.kodeDok = kodeDok;
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
     * @return the unitKerja
     */
    public String getUnitKerja() {
        return unitKerja;
    }

    /**
     * @param unitKerja the unitKerja to set
     */
    public void setUnitKerja(String unitKerja) {
        this.unitKerja = unitKerja;
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
     * @return the ketDokumen
     */
    public String getKetDokumen() {
        return ketDokumen;
    }

    /**
     * @param ketDokumen the ketDokumen to set
     */
    public void setKetDokumen(String ketDokumen) {
        this.ketDokumen = ketDokumen;
    }

    /**
     * @return the noKontrak
     */
    public String getNoKontrak() {
        return noKontrak;
    }

    /**
     * @param noKontrak the noKontrak to set
     */
    public void setNoKontrak(String noKontrak) {
        this.noKontrak = noKontrak;
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
     * @return the rekeningBank
     */
    public String getRekeningBank() {
        return rekeningBank;
    }

    /**
     * @param rekeningBank the rekeningBank to set
     */
    public void setRekeningBank(String rekeningBank) {
        this.rekeningBank = rekeningBank;
    }

    /**
     * @return the namaRekanan
     */
    public String getNamaRekanan() {
        return namaRekanan;
    }

    /**
     * @param namaRekanan the namaRekanan to set
     */
    public void setNamaRekanan(String namaRekanan) {
        this.namaRekanan = namaRekanan;
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
     * @return the dirRekanan
     */
    public String getDirRekanan() {
        return dirRekanan;
    }

    /**
     * @param dirRekanan the dirRekanan to set
     */
    public void setDirRekanan(String dirRekanan) {
        this.dirRekanan = dirRekanan;
    }

    /**
     * @return the nilaiTransfer
     */
    public BigDecimal getNilaiTransfer() {
        return nilaiTransfer;
    }

    /**
     * @param nilaiTransfer the nilaiTransfer to set
     */
    public void setNilaiTransfer(BigDecimal nilaiTransfer) {
        this.nilaiTransfer = nilaiTransfer;
    }

    /**
     * @return the persenPot
     */
    public String getPersenPot() {
        return persenPot;
    }

    /**
     * @param persenPot the persenPot to set
     */
    public void setPersenPot(String persenPot) {
        this.persenPot = persenPot;
    }

    /**
     * @return the uraianDok
     */
    public String getUraianDok() {
        return uraianDok;
    }

    /**
     * @param uraianDok the uraianDok to set
     */
    public void setUraianDok(String uraianDok) {
        this.uraianDok = uraianDok;
    }

    /**
     * @return the tglSah
     */
    public Date getTglSah() {
        return tglSah;
    }

    /**
     * @param tglSah the tglSah to set
     */
    public void setTglSah(Date tglSah) {
        this.tglSah = tglSah;
    }

    /**
     * @return the npwpRekanan
     */
    public String getNpwpRekanan() {
        return npwpRekanan;
    }

    /**
     * @param npwpRekanan the npwpRekanan to set
     */
    public void setNpwpRekanan(String npwpRekanan) {
        this.npwpRekanan = npwpRekanan;
    }

    /**
     * @return the tahunPajak
     */
    public String getTahunPajak() {
        return tahunPajak;
    }

    /**
     * @param tahunPajak the tahunPajak to set
     */
    public void setTahunPajak(String tahunPajak) {
        this.tahunPajak = tahunPajak;
    }

    /**
     * @return the bulanAwal
     */
    public String getBulanAwal() {
        return bulanAwal;
    }

    /**
     * @param bulanAwal the bulanAwal to set
     */
    public void setBulanAwal(String bulanAwal) {
        this.bulanAwal = bulanAwal;
    }

    /**
     * @return the bulanAkhir
     */
    public String getBulanAkhir() {
        return bulanAkhir;
    }

    /**
     * @param bulanAkhir the bulanAkhir to set
     */
    public void setBulanAkhir(String bulanAkhir) {
        this.bulanAkhir = bulanAkhir;
    }

    /**
     * @return the kodeProsesDki
     */
    public String getKodeProsesDki() {
        return kodeProsesDki;
    }

    /**
     * @param kodeProsesDki the kodeProsesDki to set
     */
    public void setKodeProsesDki(String kodeProsesDki) {
        this.kodeProsesDki = kodeProsesDki;
    }

    /**
     * @return the tglProsesDki
     */
    public Date getTglProsesDki() {
        return tglProsesDki;
    }

    /**
     * @param tglProsesDki the tglProsesDki to set
     */
    public void setTglProsesDki(Date tglProsesDki) {
        this.tglProsesDki = tglProsesDki;
    }

    /**
     * @return the statusDki
     */
    public String getStatusDki() {
        return statusDki;
    }

    /**
     * @param statusDki the statusDki to set
     */
    public void setStatusDki(String statusDki) {
        this.statusDki = statusDki;
    }

    /**
     * @return the ketProsesDki
     */
    public String getKetProsesDki() {
        return ketProsesDki;
    }

    /**
     * @param ketProsesDki the ketProsesDki to set
     */
    public void setKetProsesDki(String ketProsesDki) {
        this.ketProsesDki = ketProsesDki;
    }

    /**
     * @return the idTransaksi
     */
    public Integer getIdTransaksi() {
        return idTransaksi;
    }

    /**
     * @param idTransaksi the idTransaksi to set
     */
    public void setIdTransaksi(Integer idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    /**
     * @return the idUser
     */
    public Integer getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the namaUser
     */
    public String getNamaUser() {
        return namaUser;
    }

    /**
     * @param namaUser the namaUser to set
     */
    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    

}
