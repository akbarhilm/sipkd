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
public class JournalUnitSkpd extends BaseModel {

    private Integer id;

    @Valid
    private Skpd skpd;
    private Integer idskpd;
    private String tahun;

    //trjourumumskpd
    private Integer tahunAngg;
    private String tglPosting;
    private String noDok;
    private String tglDok;
    private String noJournal;
    private String noJournalDok;
    private Integer idBas;
    private BigDecimal nilaiDebet;
    private BigDecimal nilaiKredit;
    private String idKegiatan;
    private String ketKegiatan;
    private String kodeKeg;
    private String namaKeg;
    private String beban;
    private String jenis;
    private String ketJour;
    private Integer idJour;
    private String koreksiBPK;

    // tambahan untuk akun
    private String namaakun;
    private String kodeakun;
    private String ketskpd;

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
     * @return the noDok
     */
    public String getNoDok() {
        return noDok;
    }

    /**
     * @param noDok the noDok to set
     */
    public void setNoDok(String noDok) {
        this.noDok = noDok;
    }

    /**
     * @return the tglDok
     */
    public String getTglDok() {
        return tglDok;
    }

    /**
     * @param tglDok the tglDok to set
     */
    public void setTglDok(String tglDok) {
        this.tglDok = tglDok;
    }

    /**
     * @return the noJournal
     */
    public String getNoJournal() {
        return noJournal;
    }

    /**
     * @param noJournal the noJournal to set
     */
    public void setNoJournal(String noJournal) {
        this.noJournal = noJournal;
    }

    /**
     * @return the noJournalDok
     */
    public String getNoJournalDok() {
        return noJournalDok;
    }

    /**
     * @param noJournalDok the noJournalDok to set
     */
    public void setNoJournalDok(String noJournalDok) {
        this.noJournalDok = noJournalDok;
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
     * @return the idKegiatan
     */
    public String getIdKegiatan() {
        return idKegiatan;
    }

    /**
     * @param idKegiatan the idKegiatan to set
     */
    public void setIdKegiatan(String idKegiatan) {
        this.idKegiatan = idKegiatan;
    }

    /**
     * @return the beban
     */
    public String getBeban() {
        return beban;
    }

    /**
     * @param beban the beban to set
     */
    public void setBeban(String beban) {
        this.beban = beban;
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
     * @return the ketJour
     */
    public String getKetJour() {
        return ketJour;
    }

    /**
     * @param ketJour the ketJour to set
     */
    public void setKetJour(String ketJour) {
        this.ketJour = ketJour;
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
     * @return the idJour
     */
    public Integer getIdJour() {
        return idJour;
    }

    /**
     * @param idJour the idJour to set
     */
    public void setIdJour(Integer idJour) {
        this.idJour = idJour;
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
     * @return the ketKegiatan
     */
    public String getKetKegiatan() {
        return ketKegiatan;
    }

    /**
     * @param ketKegiatan the ketKegiatan to set
     */
    public void setKetKegiatan(String ketKegiatan) {
        this.ketKegiatan = ketKegiatan;
    }

    /**
     * @return the kodeKeg
     */
    public String getKodeKeg() {
        return kodeKeg;
    }

    /**
     * @param kodeKeg the kodeKeg to set
     */
    public void setKodeKeg(String kodeKeg) {
        this.kodeKeg = kodeKeg;
    }

    /**
     * @return the namaKeg
     */
    public String getNamaKeg() {
        return namaKeg;
    }

    /**
     * @param namaKeg the namaKeg to set
     */
    public void setNamaKeg(String namaKeg) {
        this.namaKeg = namaKeg;
    }

    /**
     * @return the koreksiBPK
     */
    public String getKoreksiBPK() {
        return koreksiBPK;
    }

    /**
     * @param koreksiBPK the koreksiBPK to set
     */
    public void setKoreksiBPK(String koreksiBPK) {
        this.koreksiBPK = koreksiBPK;
    }

}
