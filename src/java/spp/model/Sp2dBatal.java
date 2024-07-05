package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class Sp2dBatal extends BaseModel {

    private SpmUp spm;
    private Skpd skpd;
    private Integer idSp2d;
    private Integer noSp2d;
    private String keterangan;
    private BigDecimal nilaiSpp;
    private BigDecimal nilaiPotSpm;
    private BigDecimal nilaiSp2d;
    private String kodeBeban;
    private String kodeJenis;
    private Integer idSpp;
    private Integer idSpm;
    private String tahunAngg;
    private String noDokSp2d;
    private Date tanggalSp2d;
    private String kodeWilayahProses;
    private String namaWilayahProses;
    private String nipKbud;
    private String namaKbud;
    private String jabatanKbud;
    private String nipPenggunaAnggaran;
    private String namaPenggunaAnggaran;
    private String nrkPenggunaAnggaran;
    private String jabatanPenggunaAnggaran;
    private String kodeCetak;
    private String kodeCetakPot;
    private Date tanggalCetak;
    private String kodeSp2dSah;
    private Date tanggalSp2dSah;
    private String noJurnal;
    private String noJurnalRev;
    private String statusJurnal;
    private Date tanggalJurnal;
    private Date tanggalJurnalProses;
    private Integer idPenggunaCetak;
    private Timestamp tglPenggunaCetak;
    private Integer idPenggunaSah;
    private Timestamp tglPenggunaSah;
    private Integer idPenggunaJurnal;
    private Timestamp tglPenggunaJurnal;
    
    private String alasanBatal;
    private String noBeritaAcara;
    private Integer idPenggunaBatal;
    private Timestamp tglPenggunaBatal;
    
    private String namaFile;
    
    
    
    
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
     * @return the idSp2d
     */
    public Integer getIdSp2d() {
        return idSp2d;
    }

    /**
     * @param idSp2d the idSp2d to set
     */
    public void setIdSp2d(Integer idSp2d) {
        this.idSp2d = idSp2d;
    }

    /**
     * @return the noSp2d
     */
    public Integer getNoSp2d() {
        return noSp2d;
    }

    /**
     * @param noSp2d the noSp2d to set
     */
    public void setNoSp2d(Integer noSp2d) {
        this.noSp2d = noSp2d;
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
     * @return the nilaiPotSpm
     */
    public BigDecimal getNilaiPotSpm() {
        return nilaiPotSpm;
    }

    /**
     * @param nilaiPotSpm the nilaiPotSpm to set
     */
    public void setNilaiPotSpm(BigDecimal nilaiPotSpm) {
        this.nilaiPotSpm = nilaiPotSpm;
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
     * @return the noDokSp2d
     */
    public String getNoDokSp2d() {
        return noDokSp2d;
    }

    /**
     * @param noDokSp2d the noDokSp2d to set
     */
    public void setNoDokSp2d(String noDokSp2d) {
        this.noDokSp2d = noDokSp2d;
    }

    /**
     * @return the tanggalSp2d
     */
    public Date getTanggalSp2d() {
        return tanggalSp2d;
    }

    /**
     * @param tanggalSp2d the tanggalSp2d to set
     */
    public void setTanggalSp2d(Date tanggalSp2d) {
        this.tanggalSp2d = tanggalSp2d;
    }

    /**
     * @return the kodeWilayahProses
     */
    public String getKodeWilayahProses() {
        return kodeWilayahProses;
    }

    /**
     * @param kodeWilayahProses the kodeWilayahProses to set
     */
    public void setKodeWilayahProses(String kodeWilayahProses) {
        this.kodeWilayahProses = kodeWilayahProses;
    }

    /**
     * @return the namaWilayahProses
     */
    public String getNamaWilayahProses() {
        return namaWilayahProses;
    }

    /**
     * @param namaWilayahProses the namaWilayahProses to set
     */
    public void setNamaWilayahProses(String namaWilayahProses) {
        this.namaWilayahProses = namaWilayahProses;
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
     * @return the jabatanPenggunaAnggaran
     */
    public String getJabatanPenggunaAnggaran() {
        return jabatanPenggunaAnggaran;
    }

    /**
     * @param jabatanPenggunaAnggaran the jabatanPenggunaAnggaran to set
     */
    public void setJabatanPenggunaAnggaran(String jabatanPenggunaAnggaran) {
        this.jabatanPenggunaAnggaran = jabatanPenggunaAnggaran;
    }

    /**
     * @return the kodeCetak
     */
    public String getKodeCetak() {
        return kodeCetak;
    }

    /**
     * @param kodeCetak the kodeCetak to set
     */
    public void setKodeCetak(String kodeCetak) {
        this.kodeCetak = kodeCetak;
    }

    /**
     * @return the kodeCetakPot
     */
    public String getKodeCetakPot() {
        return kodeCetakPot;
    }

    /**
     * @param kodeCetakPot the kodeCetakPot to set
     */
    public void setKodeCetakPot(String kodeCetakPot) {
        this.kodeCetakPot = kodeCetakPot;
    }

    /**
     * @return the tanggalCetak
     */
    public Date getTanggalCetak() {
        return tanggalCetak;
    }

    /**
     * @param tanggalCetak the tanggalCetak to set
     */
    public void setTanggalCetak(Date tanggalCetak) {
        this.tanggalCetak = tanggalCetak;
    }

    /**
     * @return the kodeSp2dSah
     */
    public String getKodeSp2dSah() {
        return kodeSp2dSah;
    }

    /**
     * @param kodeSp2dSah the kodeSp2dSah to set
     */
    public void setKodeSp2dSah(String kodeSp2dSah) {
        this.kodeSp2dSah = kodeSp2dSah;
    }

    /**
     * @return the tanggalSp2dSah
     */
    public Date getTanggalSp2dSah() {
        return tanggalSp2dSah;
    }

    /**
     * @param tanggalSp2dSah the tanggalSp2dSah to set
     */
    public void setTanggalSp2dSah(Date tanggalSp2dSah) {
        this.tanggalSp2dSah = tanggalSp2dSah;
    }

    /**
     * @return the noJurnal
     */
    public String getNoJurnal() {
        return noJurnal;
    }

    /**
     * @param noJurnal the noJurnal to set
     */
    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    /**
     * @return the noJurnalRev
     */
    public String getNoJurnalRev() {
        return noJurnalRev;
    }

    /**
     * @param noJurnalRev the noJurnalRev to set
     */
    public void setNoJurnalRev(String noJurnalRev) {
        this.noJurnalRev = noJurnalRev;
    }

    /**
     * @return the statusJurnal
     */
    public String getStatusJurnal() {
        return statusJurnal;
    }

    /**
     * @param statusJurnal the statusJurnal to set
     */
    public void setStatusJurnal(String statusJurnal) {
        this.statusJurnal = statusJurnal;
    }

    /**
     * @return the tanggalJurnal
     */
    public Date getTanggalJurnal() {
        return tanggalJurnal;
    }

    /**
     * @param tanggalJurnal the tanggalJurnal to set
     */
    public void setTanggalJurnal(Date tanggalJurnal) {
        this.tanggalJurnal = tanggalJurnal;
    }

    /**
     * @return the tanggalJurnalProses
     */
    public Date getTanggalJurnalProses() {
        return tanggalJurnalProses;
    }

    /**
     * @param tanggalJurnalProses the tanggalJurnalProses to set
     */
    public void setTanggalJurnalProses(Date tanggalJurnalProses) {
        this.tanggalJurnalProses = tanggalJurnalProses;
    }

    /**
     * @return the tahunAngg
     */
    public String getTahunAngg() {
        return tahunAngg;
    }

    /**
     * @param tahunAngg the tahunAngg to set
     */
    public void setTahunAngg(String tahunAngg) {
        this.tahunAngg = tahunAngg;
    }

    /**
     * @return the alasanBatal
     */
    public String getAlasanBatal() {
        return alasanBatal;
    }

    /**
     * @param alasanBatal the alasanBatal to set
     */
    public void setAlasanBatal(String alasanBatal) {
        this.alasanBatal = alasanBatal;
    }

    /**
     * @return the noBeritaAcara
     */
    public String getNoBeritaAcara() {
        return noBeritaAcara;
    }

    /**
     * @param noBeritaAcara the noBeritaAcara to set
     */
    public void setNoBeritaAcara(String noBeritaAcara) {
        this.noBeritaAcara = noBeritaAcara;
    }

    /**
     * @return the spm
     */
    public SpmUp getSpm() {
        return spm;
    }

    /**
     * @param spm the spm to set
     */
    public void setSpm(SpmUp spm) {
        this.spm = spm;
    }

    /**
     * @return the idPenggunaCetak
     */
    public Integer getIdPenggunaCetak() {
        return idPenggunaCetak;
    }

    /**
     * @param idPenggunaCetak the idPenggunaCetak to set
     */
    public void setIdPenggunaCetak(Integer idPenggunaCetak) {
        this.idPenggunaCetak = idPenggunaCetak;
    }

    /**
     * @return the tglPenggunaCetak
     */
    public Timestamp getTglPenggunaCetak() {
        return tglPenggunaCetak;
    }

    /**
     * @param tglPenggunaCetak the tglPenggunaCetak to set
     */
    public void setTglPenggunaCetak(Timestamp tglPenggunaCetak) {
        this.tglPenggunaCetak = tglPenggunaCetak;
    }

    /**
     * @return the idPenggunaSah
     */
    public Integer getIdPenggunaSah() {
        return idPenggunaSah;
    }

    /**
     * @param idPenggunaSah the idPenggunaSah to set
     */
    public void setIdPenggunaSah(Integer idPenggunaSah) {
        this.idPenggunaSah = idPenggunaSah;
    }

    /**
     * @return the tglPenggunaSah
     */
    public Timestamp getTglPenggunaSah() {
        return tglPenggunaSah;
    }

    /**
     * @param tglPenggunaSah the tglPenggunaSah to set
     */
    public void setTglPenggunaSah(Timestamp tglPenggunaSah) {
        this.tglPenggunaSah = tglPenggunaSah;
    }

    /**
     * @return the idPenggunaBatal
     */
    public Integer getIdPenggunaBatal() {
        return idPenggunaBatal;
    }

    /**
     * @param idPenggunaBatal the idPenggunaBatal to set
     */
    public void setIdPenggunaBatal(Integer idPenggunaBatal) {
        this.idPenggunaBatal = idPenggunaBatal;
    }

    /**
     * @return the tglPenggunaBatal
     */
    public Timestamp getTglPenggunaBatal() {
        return tglPenggunaBatal;
    }

    /**
     * @param tglPenggunaBatal the tglPenggunaBatal to set
     */
    public void setTglPenggunaBatal(Timestamp tglPenggunaBatal) {
        this.tglPenggunaBatal = tglPenggunaBatal;
    }

    /**
     * @return the idPenggunaJurnal
     */
    public Integer getIdPenggunaJurnal() {
        return idPenggunaJurnal;
    }

    /**
     * @param idPenggunaJurnal the idPenggunaJurnal to set
     */
    public void setIdPenggunaJurnal(Integer idPenggunaJurnal) {
        this.idPenggunaJurnal = idPenggunaJurnal;
    }

    /**
     * @return the tglPenggunaJurnal
     */
    public Timestamp getTglPenggunaJurnal() {
        return tglPenggunaJurnal;
    }

    /**
     * @param tglPenggunaJurnal the tglPenggunaJurnal to set
     */
    public void setTglPenggunaJurnal(Timestamp tglPenggunaJurnal) {
        this.tglPenggunaJurnal = tglPenggunaJurnal;
    }

    /**
     * @return the namaFile
     */
    public String getNamaFile() {
        return namaFile;
    }

    /**
     * @param namaFile the namaFile to set
     */
    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }
    

}
