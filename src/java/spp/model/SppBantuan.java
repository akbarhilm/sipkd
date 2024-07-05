/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author erzy
 */
public class SppBantuan extends BaseModel {
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
    @NotNull(message="Bulan Wajib diisi")
    @NotEmpty(message="Bulan Wajib diisi")
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
    private List<SppBantuanRinci> sppBantuanRinci;
    private BigDecimal totalAngaran;
    private BigDecimal nilaiSpp;
    private BigDecimal nilaiSpd;
    private BigDecimal nilaiSppSisa;
    private Date tglSppCetak;
    private String fileCetakSpp;
    private Integer statusCetak;
    private Integer idSppRinci;
    private Date tglSppSah;
    private Integer idskpdkoor;
    private String kodeSkpdKoor;
    private String namaSkpdKoor;
    private Integer idBtlBantuan;
    private String noSpd;
    private Integer idSpd;
    private String rekeningBank;
    private String namaBank;
    private String kodeBank;
    private String namaPenerima;
    private String alamatBantuan;
    private String namaYayasan;
    private String kodeBankTransfer;
    private String namaBankTransfer;
    private String idBank;
    private String namaTujuan;
    

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
     * @return the totalAngaran
     */
    public BigDecimal getTotalAngaran() {
        return totalAngaran;
    }

    /**
     * @param totalAngaran the totalAngaran to set
     */
    public void setTotalAngaran(BigDecimal totalAngaran) {
        this.totalAngaran = totalAngaran;
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
     * @return the nilaiSpp
     */
    public BigDecimal getNilaiSpp() {
        return nilaiSpp;
    }

    /**
     * @param nilaiSpp the nilaiSpp to set
     */
    public void setNilaiSpp(BigDecimal nilaiSpp) {
        this.nilaiSpp = nilaiSpp;
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
     * @return the nilaiSppSisa
     */
    public BigDecimal getNilaiSppSisa() {
        return nilaiSppSisa;
    }

    /**
     * @param nilaiSppSisa the nilaiSppSisa to set
     */
    public void setNilaiSppSisa(BigDecimal nilaiSppSisa) {
        this.nilaiSppSisa = nilaiSppSisa;
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
     * @return the sppBantuanRinci
     */
    public List<SppBantuanRinci> getSppBantuanRinci() {
        return sppBantuanRinci;
    }

    /**
     * @param sppBantuanRinci the sppBantuanRinci to set
     */
    public void setSppBantuanRinci(List<SppBantuanRinci> sppBantuanRinci) {
        this.sppBantuanRinci = sppBantuanRinci;
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
     * @return the namaPenerima
     */
    public String getNamaPenerima() {
        return namaPenerima;
    }

    /**
     * @param namaPenerima the namaPenerima to set
     */
    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    /**
     * @return the alamatBantuan
     */
    public String getAlamatBantuan() {
        return alamatBantuan;
    }

    /**
     * @param alamatBantuan the alamatBantuan to set
     */
    public void setAlamatBantuan(String alamatBantuan) {
        this.alamatBantuan = alamatBantuan;
    }

    /**
     * @return the namaYayasan
     */
    public String getNamaYayasan() {
        return namaYayasan;
    }

    /**
     * @param namaYayasan the namaYayasan to set
     */
    public void setNamaYayasan(String namaYayasan) {
        this.namaYayasan = namaYayasan;
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
     * @return the idBank
     */
    public String getIdBank() {
        return idBank;
    }

    /**
     * @param idBank the idBank to set
     */
    public void setIdBank(String idBank) {
        this.idBank = idBank;
    }

    /**
     * @return the namaTujuan
     */
    public String getNamaTujuan() {
        return namaTujuan;
    }

    /**
     * @param namaTujuan the namaTujuan to set
     */
    public void setNamaTujuan(String namaTujuan) {
        this.namaTujuan = namaTujuan;
    }

    


}
