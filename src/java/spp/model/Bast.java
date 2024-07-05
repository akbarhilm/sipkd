/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author erzypratama
 */
public class Bast extends BaseModel {

    private static final Logger log = LoggerFactory.getLogger(Bast.class);
    private Skpd skpd;
    private Kegiatan kegiatan;
    private Kontrak kontrak;
    private Akun akun;

    private String noBast;
    private Integer idBl;
    private Integer idspp;
    private Integer idSpd;
    private String tahunAnggaran;
    private Date tglBast;
    private Integer idBast;
    private BigDecimal nilaiPrestasi;
    private BigDecimal nilaiBast;
    private BigDecimal nilaiKontrak;
    private String namaPptk;
    private String nipPptk;
    private Integer idKontrak;
    private String idkegiatan;
    private Integer idAkun;
    private String namaKegiatan;
    private String ketBast;
    private BigDecimal nilaiSpd;
    private BigDecimal nilaiAnggaran;
    private BigDecimal sisaSpd;
    private BigDecimal bastSebelum;
    private BigDecimal sisaBast;

    private String namaPemeriksaBarang;
    private String nipPemeriksaBarang;
    private Integer statusUangMuka;
    private BigDecimal nilaiBastEntry;
    private String alamatRekanan;
    private Integer banyakKontrakRinci;
    private String kodeUMK;
    private BigDecimal sisaKontrak;
    private String idKegAwal;
    private BigDecimal saldoUMK;
    private BigDecimal sisaUMK;
    private String kodeMultiyear;
    private String kodePotUMK;
    private String noSpd;

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
    public Integer getIdKontrak() {
        return idKontrak;
    }

    /**
     * @param idKontrak the idKontrak to set
     */
    public void setIdKontrak(Integer idKontrak) {
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
    public Integer getIdAkun() {
        return idAkun;
    }

    /**
     * @param idAkun the idAkun to set
     */
    public void setIdAkun(Integer idAkun) {
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

    /**
     * @return the nilaiSpd
     */
    public BigDecimal getNilaiSpd() {
        return nilaiSpd;
    }

    /**
     * @param nilaiSpd the nilaiSpd to set
     */
    public void setNilaiSpd(BigDecimal nilaiSpd) {
        this.nilaiSpd = nilaiSpd;
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
     * @return the namaPemeriksaBarang
     */
    public String getNamaPemeriksaBarang() {
        return namaPemeriksaBarang;
    }

    /**
     * @param namaPemeriksaBarang the namaPemeriksaBarang to set
     */
    public void setNamaPemeriksaBarang(String namaPemeriksaBarang) {
        this.namaPemeriksaBarang = namaPemeriksaBarang;
    }

    /**
     * @return the nipPemeriksaBarang
     */
    public String getNipPemeriksaBarang() {
        return nipPemeriksaBarang;
    }

    /**
     * @param nipPemeriksaBarang the nipPemeriksaBarang to set
     */
    public void setNipPemeriksaBarang(String nipPemeriksaBarang) {
        this.nipPemeriksaBarang = nipPemeriksaBarang;
    }

    /**
     * @return the idSpd
     */
    public Integer getIdSpd() {
        return idSpd;
    }

    /**
     * @param idSpd the idSpd to set
     */
    public void setIdSpd(Integer idSpd) {
        this.idSpd = idSpd;
    }

    /**
     * @return the sisaSpd
     */
    public BigDecimal getSisaSpd() {
        return sisaSpd;
    }

    /**
     * @param sisaSpd the sisaSpd to set
     */
    public void setSisaSpd(BigDecimal sisaSpd) {
        this.sisaSpd = sisaSpd;
    }

    /**
     * @return the bastSebelum
     */
    public BigDecimal getBastSebelum() {
        return bastSebelum;
    }

    /**
     * @param bastSebelum the bastSebelum to set
     */
    public void setBastSebelum(BigDecimal bastSebelum) {
        this.bastSebelum = bastSebelum;
    }

    /**
     * @return the sisaBast
     */
    public BigDecimal getSisaBast() {
        return sisaBast;
    }

    /**
     * @param sisaBast the sisaBast to set
     */
    public void setSisaBast(BigDecimal sisaBast) {
        this.sisaBast = sisaBast;
    }

    /**
     * @return the statusUangMuka
     */
    public Integer getStatusUangMuka() {
        return statusUangMuka;
    }

    /**
     * @param statusUangMuka the statusUangMuka to set
     */
    public void setStatusUangMuka(Integer statusUangMuka) {
        this.statusUangMuka = statusUangMuka;
    }

    /**
     * @return the nilaiBastEntry
     */
    public BigDecimal getNilaiBastEntry() {
        return nilaiBastEntry;
    }

    /**
     * @param nilaiBastEntry the nilaiBastEntry to set
     */
    public void setNilaiBastEntry(BigDecimal nilaiBastEntry) {
        this.nilaiBastEntry = nilaiBastEntry;
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
     * @return the banyakKontrakRinci
     */
    public Integer getBanyakKontrakRinci() {
        return banyakKontrakRinci;
    }

    /**
     * @param banyakKontrakRinci the banyakKontrakRinci to set
     */
    public void setBanyakKontrakRinci(Integer banyakKontrakRinci) {
        this.banyakKontrakRinci = banyakKontrakRinci;
    }

    /**
     * @return the sisaKontrak
     */
    public BigDecimal getSisaKontrak() {
        return sisaKontrak;
    }

    /**
     * @param sisaKontrak the sisaKontrak to set
     */
    public void setSisaKontrak(BigDecimal sisaKontrak) {
        this.sisaKontrak = sisaKontrak;
    }

    /**
     * @return the kodeUMK
     */
    public String getKodeUMK() {
        return kodeUMK;
    }

    /**
     * @param kodeUMK the kodeUMK to set
     */
    public void setKodeUMK(String kodeUMK) {
        this.kodeUMK = kodeUMK;
    }

    /**
     * @return the idKegAwal
     */
    public String getIdKegAwal() {
        return idKegAwal;
    }

    /**
     * @param idKegAwal the idKegAwal to set
     */
    public void setIdKegAwal(String idKegAwal) {
        this.idKegAwal = idKegAwal;
    }

    /**
     * @return the saldoUMK
     */
    public BigDecimal getSaldoUMK() {
        return saldoUMK;
    }

    /**
     * @param saldoUMK the saldoUMK to set
     */
    public void setSaldoUMK(BigDecimal saldoUMK) {
        this.saldoUMK = saldoUMK;
    }

    /**
     * @return the sisaUMK
     */
    public BigDecimal getSisaUMK() {
        return sisaUMK;
    }

    /**
     * @param sisaUMK the sisaUMK to set
     */
    public void setSisaUMK(BigDecimal sisaUMK) {
        this.sisaUMK = sisaUMK;
    }

    /**
     * @return the kodeMultiyear
     */
    public String getKodeMultiyear() {
        return kodeMultiyear;
    }

    /**
     * @param kodeMultiyear the kodeMultiyear to set
     */
    public void setKodeMultiyear(String kodeMultiyear) {
        this.kodeMultiyear = kodeMultiyear;
    }

    /**
     * @return the kodePotUMK
     */
    public String getKodePotUMK() {
        return kodePotUMK;
    }

    /**
     * @param kodePotUMK the kodePotUMK to set
     */
    public void setKodePotUMK(String kodePotUMK) {
        this.kodePotUMK = kodePotUMK;
    }

    /**
     * @return the noSpd
     */
    public String getNoSpd() {
        return noSpd;
    }

    /**
     * @param noSpd the noSpd to set
     */
    public void setNoSpd(String noSpd) {
        this.noSpd = noSpd;
    }

}
