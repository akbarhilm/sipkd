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
public class Sp2dBlLs extends BaseModel  {
    
    private Integer id;
    private Skpd skpd;
    private Akun akun;
    private SpmBtlLs spmBtlLs;
    private Integer idspm;
    private String tahun;
    private Integer noSpm;
    private String tglSpm;
    private String keteranganSpm;
    private Integer idSpp;
    private String noSp2d;
    private String tanggalSp2d;
    private BigDecimal nilaiSp2d;
    private BigDecimal nilaiPotSp2d;
    private BigDecimal nilaiAnggaran;
    private BigDecimal nilaiSpm;
    private String status;
    private String nipKbud;
    private String namaKbud;
    private String nrkKbud;
    private String kodeDokTT;
    private String jabatanKbud;
    private String nipPenggunaAnggaran;
    private String namaPenggunaAnggaran;
    private String nrkPenggunaAnggaran;
    private Date tglsp2d;
    private String kodeWilayah;
    private String unitKoordinator;
    private Kegiatan kegiatan;
    

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
     * @return the spmBtlLs
     */
    public SpmBtlLs getSpmBtlLs() {
        return spmBtlLs;
    }

    /**
     * @param spmBtlLs the spmBtlLs to set
     */
    public void setSpmBtlLs(SpmBtlLs spmBtlLs) {
        this.spmBtlLs = spmBtlLs;
    }

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
    public Integer getNoSpm() {
        return noSpm;
    }

    /**
     * @param noSpm the noSpm to set
     */
    public void setNoSpm(Integer noSpm) {
        this.noSpm = noSpm;
    }

    /**
     * @return the tglSpm
     */
    public String getTglSpm() {
        return tglSpm;
    }

    /**
     * @param tglSpm the tglSpm to set
     */
    public void setTglSpm(String tglSpm) {
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
     * @return the noSp2d
     */
    public String getNoSp2d() {
        return noSp2d;
    }

    /**
     * @param noSp2d the noSp2d to set
     */
    public void setNoSp2d(String noSp2d) {
        this.noSp2d = noSp2d;
    }

    /**
     * @return the tanggalSp2d
     */
    public String getTanggalSp2d() {
        return tanggalSp2d;
    }

    /**
     * @param tanggalSp2d the tanggalSp2d to set
     */
    public void setTanggalSp2d(String tanggalSp2d) {
        this.tanggalSp2d = tanggalSp2d;
    }

    /**
     * @return the nilaiSp2d
     */
    public BigDecimal getNilaiSp2d() {
        return nilaiSp2d;
    }

    /**
     * @param nilaiSp2d the nilaiSp2d to set
     */
    public void setNilaiSp2d(BigDecimal nilaiSp2d) {
        this.nilaiSp2d = nilaiSp2d;
    }

    /**
     * @return the nilaiPotSp2d
     */
    public BigDecimal getNilaiPotSp2d() {
        return nilaiPotSp2d;
    }

    /**
     * @param nilaiPotSp2d the nilaiPotSp2d to set
     */
    public void setNilaiPotSp2d(BigDecimal nilaiPotSp2d) {
        this.nilaiPotSp2d = nilaiPotSp2d;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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
     * @return the nipKbud
     */
    public String getNipKbud() {
        return nipKbud;
    }

    /**
     * @param nipKbud the nipKbud to set
     */
    public void setNipKbud(String nipKbud) {
        this.nipKbud = nipKbud;
    }

    /**
     * @return the namaKbud
     */
    public String getNamaKbud() {
        return namaKbud;
    }

    /**
     * @param namaKbud the namaKbud to set
     */
    public void setNamaKbud(String namaKbud) {
        this.namaKbud = namaKbud;
    }

    /**
     * @return the jabatanKbud
     */
    public String getJabatanKbud() {
        return jabatanKbud;
    }

    /**
     * @param jabatanKbud the jabatanKbud to set
     */
    public void setJabatanKbud(String jabatanKbud) {
        this.jabatanKbud = jabatanKbud;
    }

    /**
     * @return the akun
     */
    public Akun getAkun() {
        return akun;
    }

    /**
     * @param akun the akun to set
     */
    public void setAkun(Akun akun) {
        this.akun = akun;
    }

    /**
     * @return the nrkKbud
     */
    public String getNrkKbud() {
        return nrkKbud;
    }

    /**
     * @param nrkKbud the nrkKbud to set
     */
    public void setNrkKbud(String nrkKbud) {
        this.nrkKbud = nrkKbud;
    }

    /**
     * @return the kodeDokTT
     */
    public String getKodeDokTT() {
        return kodeDokTT;
    }

    /**
     * @param kodeDokTT the kodeDokTT to set
     */
    public void setKodeDokTT(String kodeDokTT) {
        this.kodeDokTT = kodeDokTT;
    }

    /**
     * @return the nipPenggunaAnggaran
     */
    public String getNipPenggunaAnggaran() {
        return nipPenggunaAnggaran;
    }

    /**
     * @param nipPenggunaAnggaran the nipPenggunaAnggaran to set
     */
    public void setNipPenggunaAnggaran(String nipPenggunaAnggaran) {
        this.nipPenggunaAnggaran = nipPenggunaAnggaran;
    }

    /**
     * @return the namaPenggunaAnggaran
     */
    public String getNamaPenggunaAnggaran() {
        return namaPenggunaAnggaran;
    }

    /**
     * @param namaPenggunaAnggaran the namaPenggunaAnggaran to set
     */
    public void setNamaPenggunaAnggaran(String namaPenggunaAnggaran) {
        this.namaPenggunaAnggaran = namaPenggunaAnggaran;
    }

    /**
     * @return the nrkPenggunaAnggaran
     */
    public String getNrkPenggunaAnggaran() {
        return nrkPenggunaAnggaran;
    }

    /**
     * @param nrkPenggunaAnggaran the nrkPenggunaAnggaran to set
     */
    public void setNrkPenggunaAnggaran(String nrkPenggunaAnggaran) {
        this.nrkPenggunaAnggaran = nrkPenggunaAnggaran;
    }

    /**
     * @return the tglsp2d
     */
    public Date getTglsp2d() {
        return tglsp2d;
    }

    /**
     * @param tglsp2d the tglsp2d to set
     */
    public void setTglsp2d(Date tglsp2d) {
        this.tglsp2d = tglsp2d;
    }

    /**
     * @return the kodeWilayah
     */
    public String getKodeWilayah() {
        return kodeWilayah;
    }

    /**
     * @param kodeWilayah the kodeWilayah to set
     */
    public void setKodeWilayah(String kodeWilayah) {
        this.kodeWilayah = kodeWilayah;
    }

    /**
     * @return the unitKoordinator
     */
    public String getUnitKoordinator() {
        return unitKoordinator;
    }

    /**
     * @param unitKoordinator the unitKoordinator to set
     */
    public void setUnitKoordinator(String unitKoordinator) {
        this.unitKoordinator = unitKoordinator;
    }

    /**
     * @return the kegiatan
     */
    public Kegiatan getKegiatan() {
        return kegiatan;
    }

    /**
     * @param kegiatan the kegiatan to set
     */
    public void setKegiatan(Kegiatan kegiatan) {
        this.kegiatan = kegiatan;
    }

    /**
     * @return the nilaiAnggaran
     */
    public BigDecimal getNilaiAnggaran() {
        return nilaiAnggaran;
    }

    /**
     * @param nilaiAnggaran the nilaiAnggaran to set
     */
    public void setNilaiAnggaran(BigDecimal nilaiAnggaran) {
        this.nilaiAnggaran = nilaiAnggaran;
    }

    /**
     * @return the nilaiSpm
     */
    public BigDecimal getNilaiSpm() {
        return nilaiSpm;
    }

    /**
     * @param nilaiSpm the nilaiSpm to set
     */
    public void setNilaiSpm(BigDecimal nilaiSpm) {
        this.nilaiSpm = nilaiSpm;
    }

}
