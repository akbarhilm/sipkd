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
 * @author erzy Reyvan, Basyir , Zainuddin, And Yuda
 */
public class MonitoringSpd extends BaseModel {
 
    private Integer id;
    private String noSpp;
    private String status;
    private Date tglSpp;
    // @Valid
    private Skpd skpd;
    private Bast bast;
    private Spd spd;
    private Akun akun;
    private Program program;
    private Kontrak kontrak;
    private Kegiatan kegiatan;
    private String tahun;
    private String bulan;
    private String kodeJenis;
    private String kodeBeban;
    private String namaPptk;
    private String namaBendahara;
    private String nipPptk;
    private String nipBendahara;
    private String kodeUangMuka;
    private String keterangan;
    private DokTtd dokTtd;

    private BigDecimal nilaiAnggaran;
    private BigDecimal nilaiSpdCetak;
    private BigDecimal nilaiSpdSah;
    private BigDecimal nilaiSisaAnggaran;

    private BigDecimal nilaiSpd;
    private Date tglSppCetak;
    private String fileCetakSpp;
    private Integer statusCetak;
    private Integer idSppRinci;
    private Date tglSppSah;
    private Integer idskpdkoor;
    private String kodeSkpdKoor;
    private String namaSkpdKoor;
    private Integer idBtlBantuan;
    private Integer idBtl;
    private String noSpd;
    private Integer idSpd;
    private String rekeningBank;
    private String namaBank;
    private String kodeBank;
    private String tanggalSpdBl;
    private Integer idDpaBl;
    private Integer idSpdRinciBtl;
    private Urusan urusan;

    private Integer idSpdRinciBiaya;
    private Integer idSpdRinci;
    private Integer idSpdRinciBl;
    private Integer idBiaya;
    private Date tglSpd;
    private String kodeBulanAwal;
    private String kodeBulanAkhir;
    private String ketSpd;

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
     * @return the noSpp
     */
    public String getNoSpp() {
        return noSpp;
    }

    /**
     * @param noSpp the noSpp to set
     */
    public void setNoSpp(String noSpp) {
        this.noSpp = noSpp;
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
     * @return the tglSpp
     */
    public Date getTglSpp() {
        return tglSpp;
    }

    /**
     * @param tglSpp the tglSpp to set
     */
    public void setTglSpp(Date tglSpp) {
        this.tglSpp = tglSpp;
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
     * @return the bast
     */
    public Bast getBast() {
        return bast;
    }

    /**
     * @param bast the bast to set
     */
    public void setBast(Bast bast) {
        this.bast = bast;
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
     * @return the program
     */
    public Program getProgram() {
        return program;
    }

    /**
     * @param program the program to set
     */
    public void setProgram(Program program) {
        this.program = program;
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
     * @return the kodeJenis
     */
    public String getKodeJenis() {
        return kodeJenis;
    }

    /**
     * @param kodeJenis the kodeJenis to set
     */
    public void setKodeJenis(String kodeJenis) {
        this.kodeJenis = kodeJenis;
    }

    /**
     * @return the kodeBeban
     */
    public String getKodeBeban() {
        return kodeBeban;
    }

    /**
     * @param kodeBeban the kodeBeban to set
     */
    public void setKodeBeban(String kodeBeban) {
        this.kodeBeban = kodeBeban;
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
     * @return the namaBendahara
     */
    public String getNamaBendahara() {
        return namaBendahara;
    }

    /**
     * @param namaBendahara the namaBendahara to set
     */
    public void setNamaBendahara(String namaBendahara) {
        this.namaBendahara = namaBendahara;
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
     * @return the nipBendahara
     */
    public String getNipBendahara() {
        return nipBendahara;
    }

    /**
     * @param nipBendahara the nipBendahara to set
     */
    public void setNipBendahara(String nipBendahara) {
        this.nipBendahara = nipBendahara;
    }

    /**
     * @return the kodeUangMuka
     */
    public String getKodeUangMuka() {
        return kodeUangMuka;
    }

    /**
     * @param kodeUangMuka the kodeUangMuka to set
     */
    public void setKodeUangMuka(String kodeUangMuka) {
        this.kodeUangMuka = kodeUangMuka;
    }

    /**
     * @return the keterangan
     */
    public String getKeterangan() {
        return keterangan;
    }

    /**
     * @param keterangan the keterangan to set
     */
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    /**
     * @return the dokTtd
     */
    public DokTtd getDokTtd() {
        return dokTtd;
    }

    /**
     * @param dokTtd the dokTtd to set
     */
    public void setDokTtd(DokTtd dokTtd) {
        this.dokTtd = dokTtd;
    }

    /**
     * @return the totalAngaran
     */
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
     * @return the tglSppCetak
     */
    public Date getTglSppCetak() {
        return tglSppCetak;
    }

    /**
     * @param tglSppCetak the tglSppCetak to set
     */
    public void setTglSppCetak(Date tglSppCetak) {
        this.tglSppCetak = tglSppCetak;
    }

    /**
     * @return the fileCetakSpp
     */
    public String getFileCetakSpp() {
        return fileCetakSpp;
    }

    /**
     * @param fileCetakSpp the fileCetakSpp to set
     */
    public void setFileCetakSpp(String fileCetakSpp) {
        this.fileCetakSpp = fileCetakSpp;
    }

    /**
     * @return the statusCetak
     */
    public Integer getStatusCetak() {
        return statusCetak;
    }

    /**
     * @param statusCetak the statusCetak to set
     */
    public void setStatusCetak(Integer statusCetak) {
        this.statusCetak = statusCetak;
    }

    /**
     * @return the idSppRinci
     */
    public Integer getIdSppRinci() {
        return idSppRinci;
    }

    /**
     * @param idSppRinci the idSppRinci to set
     */
    public void setIdSppRinci(Integer idSppRinci) {
        this.idSppRinci = idSppRinci;
    }

    /**
     * @return the tglSppSah
     */
    public Date getTglSppSah() {
        return tglSppSah;
    }

    /**
     * @param tglSppSah the tglSppSah to set
     */
    public void setTglSppSah(Date tglSppSah) {
        this.tglSppSah = tglSppSah;
    }

    /**
     * @return the idskpdkoor
     */
    public Integer getIdskpdkoor() {
        return idskpdkoor;
    }

    /**
     * @param idskpdkoor the idskpdkoor to set
     */
    public void setIdskpdkoor(Integer idskpdkoor) {
        this.idskpdkoor = idskpdkoor;
    }

    /**
     * @return the kodeSkpdKoor
     */
    public String getKodeSkpdKoor() {
        return kodeSkpdKoor;
    }

    /**
     * @param kodeSkpdKoor the kodeSkpdKoor to set
     */
    public void setKodeSkpdKoor(String kodeSkpdKoor) {
        this.kodeSkpdKoor = kodeSkpdKoor;
    }

    /**
     * @return the namaSkpdKoor
     */
    public String getNamaSkpdKoor() {
        return namaSkpdKoor;
    }

    /**
     * @param namaSkpdKoor the namaSkpdKoor to set
     */
    public void setNamaSkpdKoor(String namaSkpdKoor) {
        this.namaSkpdKoor = namaSkpdKoor;
    }

    /**
     * @return the idBtlBantuan
     */
    public Integer getIdBtlBantuan() {
        return idBtlBantuan;
    }

    /**
     * @param idBtlBantuan the idBtlBantuan to set
     */
    public void setIdBtlBantuan(Integer idBtlBantuan) {
        this.idBtlBantuan = idBtlBantuan;
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
     * @return the nilaiSpdCetak
     */
    public BigDecimal getNilaiSpdCetak() {
        return nilaiSpdCetak;
    }

    /**
     * @param nilaiSpdCetak the nilaiSpdCetak to set
     */
    public void setNilaiSpdCetak(BigDecimal nilaiSpdCetak) {
        this.nilaiSpdCetak = nilaiSpdCetak;
    }

    /**
     * @return the nilaiSpdSah
     */
    public BigDecimal getNilaiSpdSah() {
        return nilaiSpdSah;
    }

    /**
     * @param nilaiSpdSah the nilaiSpdSah to set
     */
    public void setNilaiSpdSah(BigDecimal nilaiSpdSah) {
        this.nilaiSpdSah = nilaiSpdSah;
    }

    /**
     * @return the nilaiSisaAnggaran
     */
    public BigDecimal getNilaiSisaAnggaran() {
        return nilaiSisaAnggaran;
    }

    /**
     * @param nilaiSisaAnggaran the nilaiSisaAnggaran to set
     */
    public void setNilaiSisaAnggaran(BigDecimal nilaiSisaAnggaran) {
        this.nilaiSisaAnggaran = nilaiSisaAnggaran;
    }

    /**
     * @return the idSpdRinciBiaya
     */
    public Integer getIdSpdRinciBiaya() {
        return idSpdRinciBiaya;
    }

    /**
     * @param idSpdRinciBiaya the idSpdRinciBiaya to set
     */
    public void setIdSpdRinciBiaya(Integer idSpdRinciBiaya) {
        this.idSpdRinciBiaya = idSpdRinciBiaya;
    }

    /**
     * @return the idBiaya
     */
    public Integer getIdBiaya() {
        return idBiaya;
    }

    /**
     * @param idBiaya the idBiaya to set
     */
    public void setIdBiaya(Integer idBiaya) {
        this.idBiaya = idBiaya;
    }

    /**
     * @return the tglSpd
     */
    public Date getTglSpd() {
        return tglSpd;
    }

    /**
     * @param tglSpd the tglSpd to set
     */
    public void setTglSpd(Date tglSpd) {
        this.tglSpd = tglSpd;
    }

    /**
     * @return the kodeBulanAwal
     */
    public String getKodeBulanAwal() {
        return kodeBulanAwal;
    }

    /**
     * @param kodeBulanAwal the kodeBulanAwal to set
     */
    public void setKodeBulanAwal(String kodeBulanAwal) {
        this.kodeBulanAwal = kodeBulanAwal;
    }

    /**
     * @return the kodeBulanAkhir
     */
    public String getKodeBulanAkhir() {
        return kodeBulanAkhir;
    }

    /**
     * @param kodeBulanAkhir the kodeBulanAkhir to set
     */
    public void setKodeBulanAkhir(String kodeBulanAkhir) {
        this.kodeBulanAkhir = kodeBulanAkhir;
    }

    /**
     * @return the ketSpd
     */
    public String getKetSpd() {
        return ketSpd;
    }

    /**
     * @param ketSpd the ketSpd to set
     */
    public void setKetSpd(String ketSpd) {
        this.ketSpd = ketSpd;
    }

    /**
     * @return the idSpdRinci
     */
    public Integer getIdSpdRinci() {
        return idSpdRinci;
    }

    /**
     * @param idSpdRinci the idSpdRinci to set
     */
    public void setIdSpdRinci(Integer idSpdRinci) {
        this.idSpdRinci = idSpdRinci;
    }

    /**
     * @return the tanggalSpdBl
     */
    public String getTanggalSpdBl() {
        return tanggalSpdBl;
    }

    /**
     * @param tanggalSpdBl the tanggalSpdBl to set
     */
    public void setTanggalSpdBl(String tanggalSpdBl) {
        this.tanggalSpdBl = tanggalSpdBl;
    }

    /**
     * @return the idDpaBl
     */
    public Integer getIdDpaBl() {
        return idDpaBl;
    }

    /**
     * @param idDpaBl the idDpaBl to set
     */
    public void setIdDpaBl(Integer idDpaBl) {
        this.idDpaBl = idDpaBl;
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
     * @return the idSpdRinciBl
     */
    public Integer getIdSpdRinciBl() {
        return idSpdRinciBl;
    }

    /**
     * @param idSpdRinciBl the idSpdRinciBl to set
     */
    public void setIdSpdRinciBl(Integer idSpdRinciBl) {
        this.idSpdRinciBl = idSpdRinciBl;
    }

    /**
     * @return the idSpdRinciBtl
     */
    public Integer getIdSpdRinciBtl() {
        return idSpdRinciBtl;
    }

    /**
     * @param idSpdRinciBtl the idSpdRinciBtl to set
     */
    public void setIdSpdRinciBtl(Integer idSpdRinciBtl) {
        this.idSpdRinciBtl = idSpdRinciBtl;
    }

    /**
     * @return the idBtl
     */
    public Integer getIdBtl() {
        return idBtl;
    }

    /**
     * @param idBtl the idBtl to set
     */
    public void setIdBtl(Integer idBtl) {
        this.idBtl = idBtl;
    }

}
