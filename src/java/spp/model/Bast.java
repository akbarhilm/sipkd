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
 * @author erzypratama
 */
public class Bast extends BaseModel {

    private Skpd skpd;
    private Kegiatan kegiatan;
    private Kontrak kontrak;
    private Akun akun;

    private String noBast;
    private Integer idBl;
    private Integer idspp;
    private String tahunAnggaran;
    private Date tglBast;
    private Integer idBast;
    private BigDecimal nilaiPrestasi;
    private BigDecimal nilaiBast;
    private BigDecimal nilaiKontrak;
    private String namaPptk;
    private String nipPptk;
    private String idKontrak;
    private String idkegiatan;
    private String idAkun;
    private String namaKegiatan;
    private String ketBast;
 

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
     * @return the kontrak
     */
    public Kontrak getKontrak() {
        return kontrak;
    }

    /**
     * @param kontrak the kontrak to set
     */
    public void setKontrak(Kontrak kontrak) {
        this.kontrak = kontrak;
    }

    /**
     * @return the noBast
     */
    public String getNoBast() {
        return noBast;
    }

    /**
     * @param noBast the noBast to set
     */
    public void setNoBast(String noBast) {
        this.noBast = noBast;
    }

    /**
     * @return the tahunAnggaran
     */
    public String getTahunAnggaran() {
        return tahunAnggaran;
    }

    /**
     * @param tahunAnggaran the tahunAnggaran to set
     */
    public void setTahunAnggaran(String tahunAnggaran) {
        this.tahunAnggaran = tahunAnggaran;
    }

    /**
     * @return the tglBast
     */
    public Date getTglBast() {
        return tglBast;
    }

    /**
     * @param tglBast the tglBast to set
     */
    public void setTglBast(Date tglBast) {
        this.tglBast = tglBast;
    }

    /**
     * @return the idBast
     */
    public Integer getIdBast() {
        return idBast;
    }

    /**
     * @param idBast the idBast to set
     */
    public void setIdBast(Integer idBast) {
        this.idBast = idBast;
    }

    /**
     * @return the nilaiPrestasi
     */
    public BigDecimal getNilaiPrestasi() {
        return nilaiPrestasi;
    }

    /**
     * @param nilaiPrestasi the nilaiPrestasi to set
     */
    public void setNilaiPrestasi(BigDecimal nilaiPrestasi) {
        this.nilaiPrestasi = nilaiPrestasi;
    }

    /**
     * @return the namaPptk
     */
    public String getNamaPptk() {
        return namaPptk;
    }

    /**
     * @param namaPptk the namaPptk to set
     */
    public void setNamaPptk(String namaPptk) {
        this.namaPptk = namaPptk;
    }

    /**
     * @return the nipPptk
     */
    public String getNipPptk() {
        return nipPptk;
    }

    /**
     * @param nipPptk the nipPptk to set
     */
    public void setNipPptk(String nipPptk) {
        this.nipPptk = nipPptk;
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
     * @return the idKontrak
     */
    public String getIdKontrak() {
        return idKontrak;
    }

    /**
     * @param idKontrak the idKontrak to set
     */
    public void setIdKontrak(String idKontrak) {
        this.idKontrak = idKontrak;
    }

    /**
     * @return the namaKegiatan
     */
    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    /**
     * @param namaKegiatan the namaKegiatan to set
     */
    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    /**
     * @return the ketBast
     */
    public String getKetBast() {
        return ketBast;
    }

    /**
     * @param ketBast the ketBast to set
     */
    public void setKetBast(String ketBast) {
        this.ketBast = ketBast;
    }

    /**
     * @return the nilaiBast
     */
    public BigDecimal getNilaiBast() {
        return nilaiBast;
    }

    /**
     * @param nilaiBast the nilaiBast to set
     */
    public void setNilaiBast(BigDecimal nilaiBast) {
        this.nilaiBast = nilaiBast;
    }

    /**
     * @return the idKegiatan
     */
    public String getIdkegiatan() {
        return idkegiatan;
    }

    /**
     * @param idKegiatan the idKegiatan to set
     */
    public void setIdkegiatan(String idkegiatan) {
        this.idkegiatan = idkegiatan;
    }

    /**
     * @return the idAkun
     */
    public String getIdAkun() {
        return idAkun;
    }

    /**
     * @param idAkun the idAkun to set
     */
    public void setIdAkun(String idAkun) {
        this.idAkun = idAkun;
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
     * @return the idBl
     */
    public Integer getIdBl() {
        return idBl;
    }

    /**
     * @param idBl the idBl to set
     */
    public void setIdBl(Integer idBl) {
        this.idBl = idBl;
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

 
}
