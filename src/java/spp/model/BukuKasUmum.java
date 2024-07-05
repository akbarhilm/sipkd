/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
//import java.sql.Date;
import javax.validation.Valid;

/**
 *
 * @author Zainab
 */
public class BukuKasUmum extends BaseModel {

    private Integer id;

    @Valid
    private Skpd skpd;
    private Integer idskpd;
    private String tahun;

    private Integer idsp2d;
    private Integer idspp;
    private Integer tahunAngg;
    private String tglPosting;
    private String noDok;
    private Date tglDok;
    private String noBukti;
    private String noBuktiDok;
    private String uraianBukti;

    private String noJournal;
    private String noJournalDok;
    private Integer idBku;
    private Integer idBas;
    private Integer idKegiatan;
    private String ketKegiatan;
    private String kodeKeg;
    private String namaKeg;
    private String bulan;
    private String kodeBulan;
    private String bulanPosting;
    private String kodeTransaksi;
    private String idTransaksi;
    private String bkuStatus;
    private BigDecimal nilaiMasuk;
    private BigDecimal nilaiKeluar;
    private BigDecimal saldoKas;
    private BigDecimal nilaiSisa;
    private BigDecimal nilaiAnggaran;
    private BigDecimal nilaiBkuSp2d;
    private BigDecimal nilaiBkuSebelum;
    private BigDecimal nilaiBkuInput;
    private BigDecimal nilaiCek;
    private BigDecimal nilaiSpj;
    private BigDecimal nilaiNpd;
    
    private String namaakun;
    private String kodeakun;
    private String ketakun;
    private String ketskpd;
    private String jenis;
    private String beban;
    
    private String inboxFile;
    private String namaPptk;
    private String nipPptk;
    private Integer noBKU;
    private String uraian;
    private String uraianPn;
    private String uraianPg;
    private String akunPn;
    private String akunPg;
    private String kodeWilayah;
    private String ketWilayah;
    private Integer idbasPn;
    private Integer idbasPg;
    
    private String tglAwal;
    private String tglAkhir;
    
    private String statusSuadana;
    private String kodePembayaran;
    private String jenisPembayaran;
    private String kodeUangPersediaan;
    private String caraBayarPn;
    private String caraBayarPg;
    
    private BigDecimal nilaiBku;
    private BigDecimal nilaiSpd;
    private BigDecimal nilaiKontrak;
    private BigDecimal nilaiTU;
    private BigDecimal nilaiSetoranTU;
    private String kodeTglTutup;
    private String kodeKoreksi;
    
    private BigDecimal sisaPajak;
    private BigDecimal saldoAwalPajak;
    
    private Integer idSpd;
    private String noSpd;
    
    private String noSetor;
    private String noSts;
    private String noValidasi;
    private BigDecimal nilaiSetor;
    private String tglValidasi;
    private String tglSts;
    private BigDecimal nilaiPajak;
    private BigDecimal nilaiSpjNetto;
    private Integer noBkuSpj;
    private String kodeSA;
    private String noBkuPj;
    private Integer idBkuRef;
    private String kodeGrup;
    private BigDecimal nilaiSA;
    private String noBkuRef;
    private String kodeAnggSekolah;
    
    // tambahan
    private Integer offset;
    private Integer limit;
    private Integer iSortCol_0;
    private String sSortDir_0;

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
     * @return the idsp2d
     */
    public Integer getIdsp2d() {
        return idsp2d;
    }

    /**
     * @param idsp2d the idsp2d to set
     */
    public void setIdsp2d(Integer idsp2d) {
        this.idsp2d = idsp2d;
    }

    /**
     * @return the noBukti
     */
    public String getNoBukti() {
        return noBukti;
    }

    /**
     * @param noBukti the noBukti to set
     */
    public void setNoBukti(String noBukti) {
        this.noBukti = noBukti;
    }

    /**
     * @return the noBuktiDok
     */
    public String getNoBuktiDok() {
        return noBuktiDok;
    }

    /**
     * @param noBuktiDok the noBuktiDok to set
     */
    public void setNoBuktiDok(String noBuktiDok) {
        this.noBuktiDok = noBuktiDok;
    }

    /**
     * @return the bulan
     */
    public String getBulan() {
        return bulan;
    }

    /**
     * @param bulan the bulan to set
     */
    public void setBulan(String bulan) {
        this.bulan = bulan;
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
     * @return the kodeTransaksi
     */
    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    /**
     * @param kodeTransaksi the kodeTransaksi to set
     */
    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    /**
     * @return the uraianBukti
     */
    public String getUraianBukti() {
        return uraianBukti;
    }

    /**
     * @param uraianBukti the uraianBukti to set
     */
    public void setUraianBukti(String uraianBukti) {
        this.uraianBukti = uraianBukti;
    }

    /**
     * @return the nilaiMasuk
     */
    public BigDecimal getNilaiMasuk() {
        return nilaiMasuk;
    }

    /**
     * @param nilaiMasuk the nilaiMasuk to set
     */
    public void setNilaiMasuk(BigDecimal nilaiMasuk) {
        this.nilaiMasuk = nilaiMasuk;
    }

    /**
     * @return the nilaiKeluar
     */
    public BigDecimal getNilaiKeluar() {
        return nilaiKeluar;
    }

    /**
     * @param nilaiKeluar the nilaiKeluar to set
     */
    public void setNilaiKeluar(BigDecimal nilaiKeluar) {
        this.nilaiKeluar = nilaiKeluar;
    }

    /**
     * @return the nilaiSisa
     */
    public BigDecimal getNilaiSisa() {
        return nilaiSisa;
    }

    /**
     * @param nilaiSisa the nilaiSisa to set
     */
    public void setNilaiSisa(BigDecimal nilaiSisa) {
        this.nilaiSisa = nilaiSisa;
    }

    /**
     * @return the idTransaksi
     */
    public String getIdTransaksi() {
        return idTransaksi;
    }

    /**
     * @param idTransaksi the idTransaksi to set
     */
    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    /**
     * @return the tglDok
     */
    public Date getTglDok() {
        return tglDok;
    }

    /**
     * @param tglDok the tglDok to set
     */
    public void setTglDok(Date tglDok) {
        this.tglDok = tglDok;
    }

    /**
     * @return the idKegiatan
     */
    public Integer getIdKegiatan() {
        return idKegiatan;
    }

    /**
     * @param idKegiatan the idKegiatan to set
     */
    public void setIdKegiatan(Integer idKegiatan) {
        this.idKegiatan = idKegiatan;
    }

    /**
     * @return the idBku
     */
    public Integer getIdBku() {
        return idBku;
    }

    /**
     * @param idBku the idBku to set
     */
    public void setIdBku(Integer idBku) {
        this.idBku = idBku;
    }

    /**
     * @return the bkuStatus
     */
    public String getBkuStatus() {
        return bkuStatus;
    }

    /**
     * @param bkuStatus the bkuStatus to set
     */
    public void setBkuStatus(String bkuStatus) {
        this.bkuStatus = bkuStatus;
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
     * @return the nilaiBkuSp2d
     */
    public BigDecimal getNilaiBkuSp2d() {
        return nilaiBkuSp2d;
    }

    /**
     * @param nilaiBkuSp2d the nilaiBkuSp2d to set
     */
    public void setNilaiBkuSp2d(BigDecimal nilaiBkuSp2d) {
        this.nilaiBkuSp2d = nilaiBkuSp2d;
    }

    /**
     * @return the nilaiBkuSebelum
     */
    public BigDecimal getNilaiBkuSebelum() {
        return nilaiBkuSebelum;
    }

    /**
     * @param nilaiBkuSebelum the nilaiBkuSebelum to set
     */
    public void setNilaiBkuSebelum(BigDecimal nilaiBkuSebelum) {
        this.nilaiBkuSebelum = nilaiBkuSebelum;
    }

    /**
     * @return the nilaiBkuInput
     */
    public BigDecimal getNilaiBkuInput() {
        return nilaiBkuInput;
    }

    /**
     * @param nilaiBkuInput the nilaiBkuInput to set
     */
    public void setNilaiBkuInput(BigDecimal nilaiBkuInput) {
        this.nilaiBkuInput = nilaiBkuInput;
    }

    /**
     * @return the saldoKas
     */
    public BigDecimal getSaldoKas() {
        return saldoKas;
    }

    /**
     * @param saldoKas the saldoKas to set
     */
    public void setSaldoKas(BigDecimal saldoKas) {
        this.saldoKas = saldoKas;
    }

    /**
     * @return the offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * @param offset the offset to set
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * @return the limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * @return the iSortCol_0
     */
    public Integer getiSortCol_0() {
        return iSortCol_0;
    }

    /**
     * @param iSortCol_0 the iSortCol_0 to set
     */
    public void setiSortCol_0(Integer iSortCol_0) {
        this.iSortCol_0 = iSortCol_0;
    }

    /**
     * @return the sSortDir_0
     */
    public String getsSortDir_0() {
        return sSortDir_0;
    }

    /**
     * @param sSortDir_0 the sSortDir_0 to set
     */
    public void setsSortDir_0(String sSortDir_0) {
        this.sSortDir_0 = sSortDir_0;
    }

    /**
     * @return the kodeBulan
     */
    public String getKodeBulan() {
        return kodeBulan;
    }

    /**
     * @param kodeBulan the kodeBulan to set
     */
    public void setKodeBulan(String kodeBulan) {
        this.kodeBulan = kodeBulan;
    }

    /**
     * @return the bulanPosting
     */
    public String getBulanPosting() {
        return bulanPosting;
    }

    /**
     * @param bulanPosting the bulanPosting to set
     */
    public void setBulanPosting(String bulanPosting) {
        this.bulanPosting = bulanPosting;
    }

    /**
     * @return the inboxFile
     */
    public String getInboxFile() {
        return inboxFile;
    }

    /**
     * @param inboxFile the inboxFile to set
     */
    public void setInboxFile(String inboxFile) {
        this.inboxFile = inboxFile;
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
     * @return the noBKU
     */
    public Integer getNoBKU() {
        return noBKU;
    }

    /**
     * @param noBKU the noBKU to set
     */
    public void setNoBKU(Integer noBKU) {
        this.noBKU = noBKU;
    }

    /**
     * @return the uraian
     */
    public String getUraian() {
        return uraian;
    }

    /**
     * @param uraian the uraian to set
     */
    public void setUraian(String uraian) {
        this.uraian = uraian;
    }

    /**
     * @return the akunPn
     */
    public String getAkunPn() {
        return akunPn;
    }

    /**
     * @param akunPn the akunPn to set
     */
    public void setAkunPn(String akunPn) {
        this.akunPn = akunPn;
    }

    /**
     * @return the akunPg
     */
    public String getAkunPg() {
        return akunPg;
    }

    /**
     * @param akunPg the akunPg to set
     */
    public void setAkunPg(String akunPg) {
        this.akunPg = akunPg;
    }

    /**
     * @return the nilaiCek
     */
    public BigDecimal getNilaiCek() {
        return nilaiCek;
    }

    /**
     * @param nilaiCek the nilaiCek to set
     */
    public void setNilaiCek(BigDecimal nilaiCek) {
        this.nilaiCek = nilaiCek;
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
     * @return the nilaiSpj
     */
    public BigDecimal getNilaiSpj() {
        return nilaiSpj;
    }

    /**
     * @param nilaiSpj the nilaiSpj to set
     */
    public void setNilaiSpj(BigDecimal nilaiSpj) {
        this.nilaiSpj = nilaiSpj;
    }

    /**
     * @return the nilaiNpd
     */
    public BigDecimal getNilaiNpd() {
        return nilaiNpd;
    }

    /**
     * @param nilaiNpd the nilaiNpd to set
     */
    public void setNilaiNpd(BigDecimal nilaiNpd) {
        this.nilaiNpd = nilaiNpd;
    }

    /**
     * @return the ketakun
     */
    public String getKetakun() {
        return ketakun;
    }

    /**
     * @param ketakun the ketakun to set
     */
    public void setKetakun(String ketakun) {
        this.ketakun = ketakun;
    }

    /**
     * @return the idbasPn
     */
    public Integer getIdbasPn() {
        return idbasPn;
    }

    /**
     * @param idbasPn the idbasPn to set
     */
    public void setIdbasPn(Integer idbasPn) {
        this.idbasPn = idbasPn;
    }

    /**
     * @return the idbasPg
     */
    public Integer getIdbasPg() {
        return idbasPg;
    }

    /**
     * @param idbasPg the idbasPg to set
     */
    public void setIdbasPg(Integer idbasPg) {
        this.idbasPg = idbasPg;
    }

    /**
     * @return the tglAwal
     */
    public String getTglAwal() {
        return tglAwal;
    }

    /**
     * @param tglAwal the tglAwal to set
     */
    public void setTglAwal(String tglAwal) {
        this.tglAwal = tglAwal;
    }

    /**
     * @return the tglAkhir
     */
    public String getTglAkhir() {
        return tglAkhir;
    }

    /**
     * @param tglAkhir the tglAkhir to set
     */
    public void setTglAkhir(String tglAkhir) {
        this.tglAkhir = tglAkhir;
    }

    /**
     * @return the statusSuadana
     */
    public String getStatusSuadana() {
        return statusSuadana;
    }

    /**
     * @param statusSuadana the statusSuadana to set
     */
    public void setStatusSuadana(String statusSuadana) {
        this.statusSuadana = statusSuadana;
    }

    /**
     * @return the uraianPn
     */
    public String getUraianPn() {
        return uraianPn;
    }

    /**
     * @param uraianPn the uraianPn to set
     */
    public void setUraianPn(String uraianPn) {
        this.uraianPn = uraianPn;
    }

    /**
     * @return the uraianPg
     */
    public String getUraianPg() {
        return uraianPg;
    }

    /**
     * @param uraianPg the uraianPg to set
     */
    public void setUraianPg(String uraianPg) {
        this.uraianPg = uraianPg;
    }

    /**
     * @return the kodePembayaran
     */
    public String getKodePembayaran() {
        return kodePembayaran;
    }

    /**
     * @param kodePembayaran the kodePembayaran to set
     */
    public void setKodePembayaran(String kodePembayaran) {
        this.kodePembayaran = kodePembayaran;
    }

    /**
     * @return the jenisPembayaran
     */
    public String getJenisPembayaran() {
        return jenisPembayaran;
    }

    /**
     * @param jenisPembayaran the jenisPembayaran to set
     */
    public void setJenisPembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }

    /**
     * @return the kodeUangPersediaan
     */
    public String getKodeUangPersediaan() {
        return kodeUangPersediaan;
    }

    /**
     * @param kodeUangPersediaan the kodeUangPersediaan to set
     */
    public void setKodeUangPersediaan(String kodeUangPersediaan) {
        this.kodeUangPersediaan = kodeUangPersediaan;
    }

    /**
     * @return the caraBayarPn
     */
    public String getCaraBayarPn() {
        return caraBayarPn;
    }

    /**
     * @param caraBayarPn the caraBayarPn to set
     */
    public void setCaraBayarPn(String caraBayarPn) {
        this.caraBayarPn = caraBayarPn;
    }

    /**
     * @return the caraBayarPg
     */
    public String getCaraBayarPg() {
        return caraBayarPg;
    }

    /**
     * @param caraBayarPg the caraBayarPg to set
     */
    public void setCaraBayarPg(String caraBayarPg) {
        this.caraBayarPg = caraBayarPg;
    }

    /**
     * @return the nilaiBku
     */
    public BigDecimal getNilaiBku() {
        return nilaiBku;
    }

    /**
     * @param nilaiBku the nilaiBku to set
     */
    public void setNilaiBku(BigDecimal nilaiBku) {
        this.nilaiBku = nilaiBku;
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
     * @return the nilaiTU
     */
    public BigDecimal getNilaiTU() {
        return nilaiTU;
    }

    /**
     * @param nilaiTU the nilaiTU to set
     */
    public void setNilaiTU(BigDecimal nilaiTU) {
        this.nilaiTU = nilaiTU;
    }

    /**
     * @return the nilaiSetoranTU
     */
    public BigDecimal getNilaiSetoranTU() {
        return nilaiSetoranTU;
    }

    /**
     * @param nilaiSetoranTU the nilaiSetoranTU to set
     */
    public void setNilaiSetoranTU(BigDecimal nilaiSetoranTU) {
        this.nilaiSetoranTU = nilaiSetoranTU;
    }


    /**
     * @return the kodeTglTutup
     */
    public String getKodeTglTutup() {
        return kodeTglTutup;
    }

    /**
     * @param kodeTglTutup the kodeTglTutup to set
     */
    public void setKodeTglTutup(String kodeTglTutup) {
        this.kodeTglTutup = kodeTglTutup;
    }

    /**
     * @return the kodeKoreksi
     */
    public String getKodeKoreksi() {
        return kodeKoreksi;
    }

    /**
     * @param kodeKoreksi the kodeKoreksi to set
     */
    public void setKodeKoreksi(String kodeKoreksi) {
        this.kodeKoreksi = kodeKoreksi;
    }

    /**
     * @return the ketWilayah
     */
    public String getKetWilayah() {
        return ketWilayah;
    }

    /**
     * @param ketWilayah the ketWilayah to set
     */
    public void setKetWilayah(String ketWilayah) {
        this.ketWilayah = ketWilayah;
    }

    /**
     * @return the sisaPajak
     */
    public BigDecimal getSisaPajak() {
        return sisaPajak;
    }

    /**
     * @param sisaPajak the sisaPajak to set
     */
    public void setSisaPajak(BigDecimal sisaPajak) {
        this.sisaPajak = sisaPajak;
    }

    /**
     * @return the saldoAwalPajak
     */
    public BigDecimal getSaldoAwalPajak() {
        return saldoAwalPajak;
    }

    /**
     * @param saldoAwalPajak the saldoAwalPajak to set
     */
    public void setSaldoAwalPajak(BigDecimal saldoAwalPajak) {
        this.saldoAwalPajak = saldoAwalPajak;
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

    /**
     * @return the noSetor
     */
    public String getNoSetor() {
        return noSetor;
    }

    /**
     * @param noSetor the noSetor to set
     */
    public void setNoSetor(String noSetor) {
        this.noSetor = noSetor;
    }

    /**
     * @return the noSts
     */
    public String getNoSts() {
        return noSts;
    }

    /**
     * @param noSts the noSts to set
     */
    public void setNoSts(String noSts) {
        this.noSts = noSts;
    }

    /**
     * @return the noValidasi
     */
    public String getNoValidasi() {
        return noValidasi;
    }

    /**
     * @param noValidasi the noValidasi to set
     */
    public void setNoValidasi(String noValidasi) {
        this.noValidasi = noValidasi;
    }

    /**
     * @return the nilaiSetor
     */
    public BigDecimal getNilaiSetor() {
        return nilaiSetor;
    }

    /**
     * @param nilaiSetor the nilaiSetor to set
     */
    public void setNilaiSetor(BigDecimal nilaiSetor) {
        this.nilaiSetor = nilaiSetor;
    }

    /**
     * @return the tglValidasi
     */
    public String getTglValidasi() {
        return tglValidasi;
    }

    /**
     * @param tglValidasi the tglValidasi to set
     */
    public void setTglValidasi(String tglValidasi) {
        this.tglValidasi = tglValidasi;
    }

    /**
     * @return the tglSts
     */
    public String getTglSts() {
        return tglSts;
    }

    /**
     * @param tglSts the tglSts to set
     */
    public void setTglSts(String tglSts) {
        this.tglSts = tglSts;
    }

    /**
     * @return the nilaiPajak
     */
    public BigDecimal getNilaiPajak() {
        return nilaiPajak;
    }

    /**
     * @param nilaiPajak the nilaiPajak to set
     */
    public void setNilaiPajak(BigDecimal nilaiPajak) {
        this.nilaiPajak = nilaiPajak;
    }

    /**
     * @return the nilaiSpjNetto
     */
    public BigDecimal getNilaiSpjNetto() {
        return nilaiSpjNetto;
    }

    /**
     * @param nilaiSpjNetto the nilaiSpjNetto to set
     */
    public void setNilaiSpjNetto(BigDecimal nilaiSpjNetto) {
        this.nilaiSpjNetto = nilaiSpjNetto;
    }

    /**
     * @return the noBkuSpj
     */
    public Integer getNoBkuSpj() {
        return noBkuSpj;
    }

    /**
     * @param noBkuSpj the noBkuSpj to set
     */
    public void setNoBkuSpj(Integer noBkuSpj) {
        this.noBkuSpj = noBkuSpj;
    }

    /**
     * @return the kodeSA
     */
    public String getKodeSA() {
        return kodeSA;
    }

    /**
     * @param kodeSA the kodeSA to set
     */
    public void setKodeSA(String kodeSA) {
        this.kodeSA = kodeSA;
    }

    /**
     * @return the noBkuPj
     */
    public String getNoBkuPj() {
        return noBkuPj;
    }

    /**
     * @param noBkuPj the noBkuPj to set
     */
    public void setNoBkuPj(String noBkuPj) {
        this.noBkuPj = noBkuPj;
    }

    /**
     * @return the idBkuRef
     */
    public Integer getIdBkuRef() {
        return idBkuRef;
    }

    /**
     * @param idBkuRef the idBkuRef to set
     */
    public void setIdBkuRef(Integer idBkuRef) {
        this.idBkuRef = idBkuRef;
    }

    /**
     * @return the kodeGrup
     */
    public String getKodeGrup() {
        return kodeGrup;
    }

    /**
     * @param kodeGrup the kodeGrup to set
     */
    public void setKodeGrup(String kodeGrup) {
        this.kodeGrup = kodeGrup;
    }

    /**
     * @return the nilaiSA
     */
    public BigDecimal getNilaiSA() {
        return nilaiSA;
    }

    /**
     * @param nilaiSA the nilaiSA to set
     */
    public void setNilaiSA(BigDecimal nilaiSA) {
        this.nilaiSA = nilaiSA;
    }

    /**
     * @return the noBkuRef
     */
    public String getNoBkuRef() {
        return noBkuRef;
    }

    /**
     * @param noBkuRef the noBkuRef to set
     */
    public void setNoBkuRef(String noBkuRef) {
        this.noBkuRef = noBkuRef;
    }

    /**
     * @return the kodeAnggSekolah
     */
    public String getKodeAnggSekolah() {
        return kodeAnggSekolah;
    }

    /**
     * @param kodeAnggSekolah the kodeAnggSekolah to set
     */
    public void setKodeAnggSekolah(String kodeAnggSekolah) {
        this.kodeAnggSekolah = kodeAnggSekolah;
    }

}
