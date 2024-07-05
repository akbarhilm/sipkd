package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class SppBl extends BaseModel {

    private Integer idskpd;
    private Integer id;
    private String noSpp;
    private String status;
    private Date tglSpp;
    private Akun akun;
    private Skpd skpd;
    private String tahun;
    private String bulan;
    private String kodeJenis;
    private String kodeBeban;
    private String namaPptk;
    private String nipBendahara;
    private String namaBendahara;
    private String nipPptk;
    private Kegiatan kegiatan;
    private Rekanan rekanan;
    //private String nipBendahara;
    private Boolean kodeUangMuka;
    private String keterangan;
    private Bast bast;
    private DokTtd dokTtd;
    private Kontrak kontrak;
    private List<SppBlRinci> sppBlRinci;
    private BigDecimal totalAngaran;
    private BigDecimal nilaiSpp;
    private BigDecimal nilaiSppSebelum;
    private BigDecimal nilaiSppSisa;
    private Date tglSppCetak;
    private String fileCetakSpp;
    private Integer statusCetak;
    private Date tglSppSah;
    private boolean potongan;
    private boolean potonganUangMuka;
    private String namaBank;
    private String kodeBank;
    private String nomorRekBank;
    private String rekeningBank;
    private String kodeKegiatan;
    private String namaKegiatan;
    private String noNpwp;
    private String noKontrak;
    private String alamatBank;
    private String namaRekanan;
    private String noRekeningPFK;

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
     * @return the kodeUangMuka
     */
    public Boolean getKodeUangMuka() {
        return kodeUangMuka;
    }

    /**
     * @param kodeUangMuka the kodeUangMuka to set
     */
    public void setKodeUangMuka(Boolean kodeUangMuka) {
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
     * @return the sppBlRinci
     */
    public List<SppBlRinci> getSppBlRinci() {
        return sppBlRinci;
    }

    /**
     * @param sppBlRinci the sppBlRinci to set
     */
    public void setSppBlRinci(List<SppBlRinci> sppBlRinci) {
        this.sppBlRinci = sppBlRinci;
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
     * @return the nilaiSppSebelum
     */
    public BigDecimal getNilaiSppSebelum() {
        return nilaiSppSebelum;
    }

    /**
     * @param nilaiSppSebelum the nilaiSppSebelum to set
     */
    public void setNilaiSppSebelum(BigDecimal nilaiSppSebelum) {
        this.nilaiSppSebelum = nilaiSppSebelum;
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
     * @return the potongan
     */
    public boolean isPotongan() {
        return potongan;
    }

    /**
     * @param potongan the potongan to set
     */
    public void setPotongan(boolean potongan) {
        this.potongan = potongan;
    }

    /**
     * @return the potonganUangMuka
     */
    public boolean isPotonganUangMuka() {
        return potonganUangMuka;
    }

    /**
     * @param potonganUangMuka the potonganUangMuka to set
     */
    public void setPotonganUangMuka(boolean potonganUangMuka) {
        this.potonganUangMuka = potonganUangMuka;
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
     * @return the nomorRekBank
     */
    public String getNomorRekBank() {
        return nomorRekBank;
    }

    /**
     * @param nomorRekBank the nomorRekBank to set
     */
    public void setNomorRekBank(String nomorRekBank) {
        this.nomorRekBank = nomorRekBank;
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
     * @return the kodeKegiatan
     */
    public String getKodeKegiatan() {
        return kodeKegiatan;
    }

    /**
     * @param kodeKegiatan the kodeKegiatan to set
     */
    public void setKodeKegiatan(String kodeKegiatan) {
        this.kodeKegiatan = kodeKegiatan;
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
     * @return the noNpwp
     */
    public String getNoNpwp() {
        return noNpwp;
    }

    /**
     * @param noNpwp the noNpwp to set
     */
    public void setNoNpwp(String noNpwp) {
        this.noNpwp = noNpwp;
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
     * @return the noRekeningPFK
     */
    public String getNoRekeningPFK() {
        return noRekeningPFK;
    }

    /**
     * @param noRekeningPFK the noRekeningPFK to set
     */
    public void setNoRekeningPFK(String noRekeningPFK) {
        this.noRekeningPFK = noRekeningPFK;
    }

}
