/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Kontrak extends BaseModel {

    private Integer idKontrak;
    @Valid
    private String tahunAnggaran;
    private Kegiatan kegiatan;
    private String noKontrak;
    private Integer idspp;
    private Integer idSpd;
    private Integer noSpd;
    private Skpd skpd;
    private Metode metode;
    @NotNull
    private String idDokReferensi;
    @NotNull
    private String idKontrakReferensi;
    private Date tanggalKontrak;
    private BigDecimal nilaiKontrak;
    @NotNull
    private String idDokKontrNo;
    @NotNull
    private String idKontrakMohon;
    private Date tglKontrakMohon;
    private String kodeAnggaranSumbdana;
    private Rekanan rekanan;
    private Date tanggalDokTtkontr;
    private String idDokSimpan;
    private String idLokasiKontrak;
    private String kodeWilayahSkpd;
    private String kodeWilayahProsesSpm;
    private String ketPekrKontrak;
    private String idSkPimPro;
    private String namaPimPro;
    private Date tanggalSkPimPro;
    private String idRekananTender;
    private String idRekananDikdip;
    private Date tanggalRekananDikdip;
    private String nomorPo;
    private Date tanggalAwalJadwalKontrak;
    private Date tanggalAkhirJadwalKontrak;
    private String idDendaKontrak;
    private String idBuktiDendaKontrak;
    private String idKontrakPph;
    private String idLelang;
    private String idRevisi;
    private Spd spd;
    private Urusan urusan;
    private BigDecimal nilaiSpd;
    private BigDecimal nilaiKontrakTotal;
    private RefProgram refProgram;
    private String namaRekananDirektur;
    private String alamatRekanan;
    private String noAkteRekanan;
    private Date tanggalAkteRekanan;
    private String noNpwpRekanan;
    private String namaRekanan;
    private String namaRekananBank;
    private String namaBank;
    private String alamatBank;
    private String rekeningBank;
    private BigDecimal nilai1;
    private BigDecimal nilai2;
    private Date tglmulai;
    private Date tglakhir;
    private BigDecimal nilaiSpdOrig;
    private Integer idBast;
    private String pesan;
    private BigDecimal nilaiBku;
    private BigDecimal nilaiSisa;
    private String noKontrakTotal;
    private String kodeBankPfk;
    private String namaBankPfk;
    private String kodeBankTransfer;
    private String namaBankTransfer;
    private Integer idBankPfk;
    private Integer idKegiatanLama;
    private String kodeVA;
    private String namaNpwp;
    private String alamatNpwp;
    private String kotaNpwp;
    private String kodePkp;
    private BigDecimal nilaiAngg;
    private BigDecimal nilaiKontrakSebelum;
    private BigDecimal nilaiTU;
    private BigDecimal nilaiSetoran;
    private BigDecimal nilaiUPGU;
    private String kodeSuratKet;
    private String nomorSuratKet;
    private String kodeBelanja;

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
     * @return the noSpd
     */
    public Integer getNoSpd() {
        return noSpd;
    }

    /**
     * @param noSpd the noSpd to set
     */
    public void setNoSpd(Integer noSpd) {
        this.noSpd = noSpd;
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
     * @return the metode
     */
    public Metode getMetode() {
        return metode;
    }

    /**
     * @param metode the metode to set
     */
    public void setMetode(Metode metode) {
        this.metode = metode;
    }

    /**
     * @return the idDokReferensi
     */
    public String getIdDokReferensi() {
        return idDokReferensi;
    }

    /**
     * @param idDokReferensi the idDokReferensi to set
     */
    public void setIdDokReferensi(String idDokReferensi) {
        this.idDokReferensi = idDokReferensi;
    }

    /**
     * @return the idKontrakReferensi
     */
    public String getIdKontrakReferensi() {
        return idKontrakReferensi;
    }

    /**
     * @param idKontrakReferensi the idKontrakReferensi to set
     */
    public void setIdKontrakReferensi(String idKontrakReferensi) {
        this.idKontrakReferensi = idKontrakReferensi;
    }

    /**
     * @return the tanggalKontrak
     */
    public Date getTanggalKontrak() {
        return tanggalKontrak;
    }

    /**
     * @param tanggalKontrak the tanggalKontrak to set
     */
    public void setTanggalKontrak(Date tanggalKontrak) {
        this.tanggalKontrak = tanggalKontrak;
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
     * @return the idDokKontrNo
     */
    public String getIdDokKontrNo() {
        return idDokKontrNo;
    }

    /**
     * @param idDokKontrNo the idDokKontrNo to set
     */
    public void setIdDokKontrNo(String idDokKontrNo) {
        this.idDokKontrNo = idDokKontrNo;
    }

    /**
     * @return the idKontrakMohon
     */
    public String getIdKontrakMohon() {
        return idKontrakMohon;
    }

    /**
     * @param idKontrakMohon the idKontrakMohon to set
     */
    public void setIdKontrakMohon(String idKontrakMohon) {
        this.idKontrakMohon = idKontrakMohon;
    }

    /**
     * @return the tglKontrakMohon
     */
    public Date getTglKontrakMohon() {
        return tglKontrakMohon;
    }

    /**
     * @param tglKontrakMohon the tglKontrakMohon to set
     */
    public void setTglKontrakMohon(Date tglKontrakMohon) {
        this.tglKontrakMohon = tglKontrakMohon;
    }

    /**
     * @return the kodeAnggaranSumbdana
     */
    public String getKodeAnggaranSumbdana() {
        return kodeAnggaranSumbdana;
    }

    /**
     * @param kodeAnggaranSumbdana the kodeAnggaranSumbdana to set
     */
    public void setKodeAnggaranSumbdana(String kodeAnggaranSumbdana) {
        this.kodeAnggaranSumbdana = kodeAnggaranSumbdana;
    }

    /**
     * @return the rekanan
     */
    public Rekanan getRekanan() {
        return rekanan;
    }

    /**
     * @param rekanan the rekanan to set
     */
    public void setRekanan(Rekanan rekanan) {
        this.rekanan = rekanan;
    }

    /**
     * @return the tanggalDokTtkontr
     */
    public Date getTanggalDokTtkontr() {
        return tanggalDokTtkontr;
    }

    /**
     * @param tanggalDokTtkontr the tanggalDokTtkontr to set
     */
    public void setTanggalDokTtkontr(Date tanggalDokTtkontr) {
        this.tanggalDokTtkontr = tanggalDokTtkontr;
    }

    /**
     * @return the idDokSimpan
     */
    public String getIdDokSimpan() {
        return idDokSimpan;
    }

    /**
     * @param idDokSimpan the idDokSimpan to set
     */
    public void setIdDokSimpan(String idDokSimpan) {
        this.idDokSimpan = idDokSimpan;
    }

    /**
     * @return the idLokasiKontrak
     */
    public String getIdLokasiKontrak() {
        return idLokasiKontrak;
    }

    /**
     * @param idLokasiKontrak the idLokasiKontrak to set
     */
    public void setIdLokasiKontrak(String idLokasiKontrak) {
        this.idLokasiKontrak = idLokasiKontrak;
    }

    /**
     * @return the kodeWilayahSkpd
     */
    public String getKodeWilayahSkpd() {
        return kodeWilayahSkpd;
    }

    /**
     * @param kodeWilayahSkpd the kodeWilayahSkpd to set
     */
    public void setKodeWilayahSkpd(String kodeWilayahSkpd) {
        this.kodeWilayahSkpd = kodeWilayahSkpd;
    }

    /**
     * @return the kodeWilayahProsesSpm
     */
    public String getKodeWilayahProsesSpm() {
        return kodeWilayahProsesSpm;
    }

    /**
     * @param kodeWilayahProsesSpm the kodeWilayahProsesSpm to set
     */
    public void setKodeWilayahProsesSpm(String kodeWilayahProsesSpm) {
        this.kodeWilayahProsesSpm = kodeWilayahProsesSpm;
    }

    /**
     * @return the ketPekrKontrak
     */
    public String getKetPekrKontrak() {
        return ketPekrKontrak;
    }

    /**
     * @param ketPekrKontrak the ketPekrKontrak to set
     */
    public void setKetPekrKontrak(String ketPekrKontrak) {
        this.ketPekrKontrak = ketPekrKontrak;
    }

    /**
     * @return the idSkPimPro
     */
    public String getIdSkPimPro() {
        return idSkPimPro;
    }

    /**
     * @param idSkPimPro the idSkPimPro to set
     */
    public void setIdSkPimPro(String idSkPimPro) {
        this.idSkPimPro = idSkPimPro;
    }

    /**
     * @return the namaPimPro
     */
    public String getNamaPimPro() {
        return namaPimPro;
    }

    /**
     * @param namaPimPro the namaPimPro to set
     */
    public void setNamaPimPro(String namaPimPro) {
        this.namaPimPro = namaPimPro;
    }

    /**
     * @return the tanggalSkPimPro
     */
    public Date getTanggalSkPimPro() {
        return tanggalSkPimPro;
    }

    /**
     * @param tanggalSkPimPro the tanggalSkPimPro to set
     */
    public void setTanggalSkPimPro(Date tanggalSkPimPro) {
        this.tanggalSkPimPro = tanggalSkPimPro;
    }

    /**
     * @return the idRekananTender
     */
    public String getIdRekananTender() {
        return idRekananTender;
    }

    /**
     * @param idRekananTender the idRekananTender to set
     */
    public void setIdRekananTender(String idRekananTender) {
        this.idRekananTender = idRekananTender;
    }

    /**
     * @return the idRekananDikdip
     */
    public String getIdRekananDikdip() {
        return idRekananDikdip;
    }

    /**
     * @param idRekananDikdip the idRekananDikdip to set
     */
    public void setIdRekananDikdip(String idRekananDikdip) {
        this.idRekananDikdip = idRekananDikdip;
    }

    /**
     * @return the tanggalRekananDikdip
     */
    public Date getTanggalRekananDikdip() {
        return tanggalRekananDikdip;
    }

    /**
     * @param tanggalRekananDikdip the tanggalRekananDikdip to set
     */
    public void setTanggalRekananDikdip(Date tanggalRekananDikdip) {
        this.tanggalRekananDikdip = tanggalRekananDikdip;
    }

    /**
     * @return the nomorPo
     */
    public String getNomorPo() {
        return nomorPo;
    }

    /**
     * @param nomorPo the nomorPo to set
     */
    public void setNomorPo(String nomorPo) {
        this.nomorPo = nomorPo;
    }

    /**
     * @return the tanggalAwalJadwalKontrak
     */
    public Date getTanggalAwalJadwalKontrak() {
        return tanggalAwalJadwalKontrak;
    }

    /**
     * @param tanggalAwalJadwalKontrak the tanggalAwalJadwalKontrak to set
     */
    public void setTanggalAwalJadwalKontrak(Date tanggalAwalJadwalKontrak) {
        this.tanggalAwalJadwalKontrak = tanggalAwalJadwalKontrak;
    }

    /**
     * @return the tanggalAkhirJadwalKontrak
     */
    public Date getTanggalAkhirJadwalKontrak() {
        return tanggalAkhirJadwalKontrak;
    }

    /**
     * @param tanggalAkhirJadwalKontrak the tanggalAkhirJadwalKontrak to set
     */
    public void setTanggalAkhirJadwalKontrak(Date tanggalAkhirJadwalKontrak) {
        this.tanggalAkhirJadwalKontrak = tanggalAkhirJadwalKontrak;
    }

    /**
     * @return the idDendaKontrak
     */
    public String getIdDendaKontrak() {
        return idDendaKontrak;
    }

    /**
     * @param idDendaKontrak the idDendaKontrak to set
     */
    public void setIdDendaKontrak(String idDendaKontrak) {
        this.idDendaKontrak = idDendaKontrak;
    }

    /**
     * @return the idBuktiDendaKontrak
     */
    public String getIdBuktiDendaKontrak() {
        return idBuktiDendaKontrak;
    }

    /**
     * @param idBuktiDendaKontrak the idBuktiDendaKontrak to set
     */
    public void setIdBuktiDendaKontrak(String idBuktiDendaKontrak) {
        this.idBuktiDendaKontrak = idBuktiDendaKontrak;
    }

    /**
     * @return the idKontrakPph
     */
    public String getIdKontrakPph() {
        return idKontrakPph;
    }

    /**
     * @param idKontrakPph the idKontrakPph to set
     */
    public void setIdKontrakPph(String idKontrakPph) {
        this.idKontrakPph = idKontrakPph;
    }

    /**
     * @return the idLelang
     */
    public String getIdLelang() {
        return idLelang;
    }

    /**
     * @param idLelang the idLelang to set
     */
    public void setIdLelang(String idLelang) {
        this.idLelang = idLelang;
    }

    /**
     * @return the idRevisi
     */
    public String getIdRevisi() {
        return idRevisi;
    }

    /**
     * @param idRevisi the idRevisi to set
     */
    public void setIdRevisi(String idRevisi) {
        this.idRevisi = idRevisi;
    }

    /**
     * @return the spd
     */
    public Spd getSpd() {
        return spd;
    }

    /**
     * @param spd the spd to set
     */
    public void setSpd(Spd spd) {
        this.spd = spd;
    }

    /**
     * @return the urusan
     */
    public Urusan getUrusan() {
        return urusan;
    }

    /**
     * @param urusan the urusan to set
     */
    public void setUrusan(Urusan urusan) {
        this.urusan = urusan;
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
     * @return the refProgram
     */
    public RefProgram getRefProgram() {
        return refProgram;
    }

    /**
     * @param refProgram the refProgram to set
     */
    public void setRefProgram(RefProgram refProgram) {
        this.refProgram = refProgram;
    }

    /**
     * @return the namaRekananDirektur
     */
    public String getNamaRekananDirektur() {
        return namaRekananDirektur;
    }

    /**
     * @param namaRekananDirektur the namaRekananDirektur to set
     */
    public void setNamaRekananDirektur(String namaRekananDirektur) {
        this.namaRekananDirektur = namaRekananDirektur;
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
     * @return the noAkteRekanan
     */
    public String getNoAkteRekanan() {
        return noAkteRekanan;
    }

    /**
     * @param noAkteRekanan the noAkteRekanan to set
     */
    public void setNoAkteRekanan(String noAkteRekanan) {
        this.noAkteRekanan = noAkteRekanan;
    }

    /**
     * @return the tanggalAkteRekanan
     */
    public Date getTanggalAkteRekanan() {
        return tanggalAkteRekanan;
    }

    /**
     * @param tanggalAkteRekanan the tanggalAkteRekanan to set
     */
    public void setTanggalAkteRekanan(Date tanggalAkteRekanan) {
        this.tanggalAkteRekanan = tanggalAkteRekanan;
    }

    /**
     * @return the noNpwpRekanan
     */
    public String getNoNpwpRekanan() {
        return noNpwpRekanan;
    }

    /**
     * @param noNpwpRekanan the noNpwpRekanan to set
     */
    public void setNoNpwpRekanan(String noNpwpRekanan) {
        this.noNpwpRekanan = noNpwpRekanan;
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
     * @return the nilai1
     */
    public BigDecimal getNilai1() {
        return nilai1;
    }

    /**
     * @param nilai1 the nilai1 to set
     */
    public void setNilai1(BigDecimal nilai1) {
        this.nilai1 = nilai1;
    }

    /**
     * @return the nilai2
     */
    public BigDecimal getNilai2() {
        return nilai2;
    }

    /**
     * @param nilai2 the nilai2 to set
     */
    public void setNilai2(BigDecimal nilai2) {
        this.nilai2 = nilai2;
    }

    /**
     * @return the tglmulai
     */
    public Date getTglmulai() {
        return tglmulai;
    }

    /**
     * @param tglmulai the tglmulai to set
     */
    public void setTglmulai(Date tglmulai) {
        this.tglmulai = tglmulai;
    }

    /**
     * @return the tglakhir
     */
    public Date getTglakhir() {
        return tglakhir;
    }

    /**
     * @param tglakhir the tglakhir to set
     */
    public void setTglakhir(Date tglakhir) {
        this.tglakhir = tglakhir;
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
     * @return the alamatBank
     */
    public String getAlamatBank() {
        return alamatBank;
    }

    /**
     * @param alamatBank the alamatBank to set
     */
    public void setAlamatBank(String alamatBank) {
        this.alamatBank = alamatBank;
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
     * @return the nilaiSpdOrig
     */
    public BigDecimal getNilaiSpdOrig() {
        return nilaiSpdOrig;
    }

    /**
     * @param nilaiSpdOrig the nilaiSpdOrig to set
     */
    public void setNilaiSpdOrig(BigDecimal nilaiSpdOrig) {
        this.nilaiSpdOrig = nilaiSpdOrig;
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
     * @return the pesan
     */
    public String getPesan() {
        return pesan;
    }

    /**
     * @param pesan the pesan to set
     */
    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    /**
     * @return the nilaiKontrakTotal
     */
    public BigDecimal getNilaiKontrakTotal() {
        return nilaiKontrakTotal;
    }

    /**
     * @param nilaiKontrakTotal the nilaiKontrakTotal to set
     */
    public void setNilaiKontrakTotal(BigDecimal nilaiKontrakTotal) {
        this.nilaiKontrakTotal = nilaiKontrakTotal;
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
     * @return the noKontrakTotal
     */
    public String getNoKontrakTotal() {
        return noKontrakTotal;
    }

    /**
     * @param noKontrakTotal the noKontrakTotal to set
     */
    public void setNoKontrakTotal(String noKontrakTotal) {
        this.noKontrakTotal = noKontrakTotal;
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
     * @return the idKegiatanLama
     */
    public Integer getIdKegiatanLama() {
        return idKegiatanLama;
    }

    /**
     * @param idKegiatanLama the idKegiatanLama to set
     */
    public void setIdKegiatanLama(Integer idKegiatanLama) {
        this.idKegiatanLama = idKegiatanLama;
    }

    /**
     * @return the kodeVA
     */
    public String getKodeVA() {
        return kodeVA;
    }

    /**
     * @param kodeVA the kodeVA to set
     */
    public void setKodeVA(String kodeVA) {
        this.kodeVA = kodeVA;
    }

    /**
     * @return the namaNpwp
     */
    public String getNamaNpwp() {
        return namaNpwp;
    }

    /**
     * @param namaNpwp the namaNpwp to set
     */
    public void setNamaNpwp(String namaNpwp) {
        this.namaNpwp = namaNpwp;
    }

    /**
     * @return the alamatNpwp
     */
    public String getAlamatNpwp() {
        return alamatNpwp;
    }

    /**
     * @param alamatNpwp the alamatNpwp to set
     */
    public void setAlamatNpwp(String alamatNpwp) {
        this.alamatNpwp = alamatNpwp;
    }

    /**
     * @return the kotaNpwp
     */
    public String getKotaNpwp() {
        return kotaNpwp;
    }

    /**
     * @param kotaNpwp the kotaNpwp to set
     */
    public void setKotaNpwp(String kotaNpwp) {
        this.kotaNpwp = kotaNpwp;
    }

    /**
     * @return the namaRekananBank
     */
    public String getNamaRekananBank() {
        return namaRekananBank;
    }

    /**
     * @param namaRekananBank the namaRekananBank to set
     */
    public void setNamaRekananBank(String namaRekananBank) {
        this.namaRekananBank = namaRekananBank;
    }

    /**
     * @return the kodePkp
     */
    public String getKodePkp() {
        return kodePkp;
    }

    /**
     * @param kodePkp the kodePkp to set
     */
    public void setKodePkp(String kodePkp) {
        this.kodePkp = kodePkp;
    }

    /**
     * @return the nilaiAngg
     */
    public BigDecimal getNilaiAngg() {
        return nilaiAngg;
    }

    /**
     * @param nilaiAngg the nilaiAngg to set
     */
    public void setNilaiAngg(BigDecimal nilaiAngg) {
        this.nilaiAngg = nilaiAngg;
    }

    /**
     * @return the nilaiKontrakSebelum
     */
    public BigDecimal getNilaiKontrakSebelum() {
        return nilaiKontrakSebelum;
    }

    /**
     * @param nilaiKontrakSebelum the nilaiKontrakSebelum to set
     */
    public void setNilaiKontrakSebelum(BigDecimal nilaiKontrakSebelum) {
        this.nilaiKontrakSebelum = nilaiKontrakSebelum;
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
     * @return the nilaiSetoran
     */
    public BigDecimal getNilaiSetoran() {
        return nilaiSetoran;
    }

    /**
     * @param nilaiSetoran the nilaiSetoran to set
     */
    public void setNilaiSetoran(BigDecimal nilaiSetoran) {
        this.nilaiSetoran = nilaiSetoran;
    }

    /**
     * @return the nilaiUPGU
     */
    public BigDecimal getNilaiUPGU() {
        return nilaiUPGU;
    }

    /**
     * @param nilaiUPGU the nilaiUPGU to set
     */
    public void setNilaiUPGU(BigDecimal nilaiUPGU) {
        this.nilaiUPGU = nilaiUPGU;
    }

    /**
     * @return the kodeSuratKet
     */
    public String getKodeSuratKet() {
        return kodeSuratKet;
    }

    /**
     * @param kodeSuratKet the kodeSuratKet to set
     */
    public void setKodeSuratKet(String kodeSuratKet) {
        this.kodeSuratKet = kodeSuratKet;
    }

    /**
     * @return the nomorSuratKet
     */
    public String getNomorSuratKet() {
        return nomorSuratKet;
    }

    /**
     * @param nomorSuratKet the nomorSuratKet to set
     */
    public void setNomorSuratKet(String nomorSuratKet) {
        this.nomorSuratKet = nomorSuratKet;
    }

    /**
     * @return the kodeBelanja
     */
    public String getKodeBelanja() {
        return kodeBelanja;
    }

    /**
     * @param kodeBelanja the kodeBelanja to set
     */
    public void setKodeBelanja(String kodeBelanja) {
        this.kodeBelanja = kodeBelanja;
    }

}
