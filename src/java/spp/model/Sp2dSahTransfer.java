package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import javax.validation.Valid;
import org.joda.time.DateTime;

public class Sp2dSahTransfer extends BaseModel {

    private Integer id;
    private String noSpp;
    private String status;
    private Date tglSpp;
    @Valid
    private Skpd skpd;

    @Override
    public String toString() {
        return "Sp2dSahTransfer{" + "id=" + id + ", noSpp=" + noSpp + ", status=" + status + ", tglSpp=" + tglSpp + ", skpd=" + skpd + ", spmup=" + spmup + ", sp2d=" + sp2d + ", tahun=" + tahun + ", bulan=" + bulan + ", kodeJenis=" + kodeJenis + ", kodeBeban=" + kodeBeban + ", namaPptk=" + namaPptk + ", namaBendahara=" + namaBendahara + ", nipPptk=" + nipPptk + ", nipBendahara=" + nipBendahara + ", nrkBendahara=" + nrkBendahara + ", kodeUangMuka=" + kodeUangMuka + ", keterangan=" + keterangan + ", dokTtd=" + dokTtd + ", sppUpRinci=" + sppUpRinci + ", totalAngaran=" + totalAngaran + ", nilaiSpp=" + nilaiSpp + ", tglSppCetak=" + tglSppCetak + ", fileCetakSpp=" + fileCetakSpp + ", statusCetak=" + statusCetak + ", tglSppSah=" + tglSppSah + ", kodeNihil=" + kodeNihil + ", namaBank=" + namaBank + ", kodeBank=" + kodeBank + ", nomorRekBank=" + nomorRekBank + ", alamatPdfOutput=" + alamatPdfOutput + ", levelSkpd=" + levelSkpd + ", idSp2d=" + idSp2d + ", nomorSp2d=" + nomorSp2d + ", idSkpd=" + idSkpd + ", kodeWilayahProses=" + kodeWilayahProses + ", namaFile=" + namaFile + ", kodeSah=" + kodeSah + ", nilaiPotongan=" + nilaiPotongan + ", noDokSp2d=" + noDokSp2d + ", norekTujuan=" + norekTujuan + ", namaTujuan=" + namaTujuan + ", alamat=" + alamat + ", npwp=" + npwp + ", norekPengirim=" + norekPengirim + ", namaPengirim=" + namaPengirim + ", kodeBilling=" + kodeBilling + ", ketSp2d=" + ketSp2d + ", kodeSkpd=" + kodeSkpd + ", namaSkpd=" + namaSkpd + ", nilaiAmountBalance=" + nilaiAmountBalance + ", nilaiBayar=" + nilaiBayar + ", statusBank=" + statusBank + ", msgKirim=" + msgKirim + ", trxTerimaBank=" + trxTerimaBank + ", nilaiBayarBank=" + nilaiBayarBank + ", nilaiKirimBank=" + nilaiKirimBank + ", msgTerimaBank=" + msgTerimaBank + ", tglProses=" + tglProses + ", kodeAkun=" + kodeAkun + ", kodeStan=" + kodeStan + ", nomorRef=" + nomorRef + ", tglProsesBank=" + tglProsesBank + ", tglTerimaBank=" + tglTerimaBank + ", bit4=" + bit4 + ", bit11=" + bit11 + ", bit12=" + bit12 + ", bit13=" + bit13 + ", bit37=" + bit37 + ", nodokSp2d=" + nodokSp2d + ", nilaiBruto=" + nilaiBruto + ", nilaiBayarRekon=" + nilaiBayarRekon + ", nilaiPot=" + nilaiPot + ", pengguna=" + pengguna + ", idskpdRekon=" + idskpdRekon + ", rnorekTujuan=" + rnorekTujuan + ", rnorekPengirim=" + rnorekPengirim + ", bit62=" + bit62 + '}';
    }
    private SpmUp spmup;
    private Sp2d sp2d;
    private String tahun;
    private String bulan;
    private String kodeJenis;
    private String kodeBeban;
    private String namaPptk;
    private String namaBendahara;
    private String nipPptk;
    private String nipBendahara;
    private String nrkBendahara;
    private String kodeUangMuka;
    private String keterangan;
    private DokTtd dokTtd;
    private List<SppUpRinci> sppUpRinci;
    private BigDecimal totalAngaran;
    private BigDecimal nilaiSpp;
    private Date tglSppCetak;
    private String fileCetakSpp;
    private Integer statusCetak;
    private Date tglSppSah;
    private String kodeNihil;
    private String namaBank;
    private String kodeBank;
    private String nomorRekBank;
    private String alamatPdfOutput;
    private String levelSkpd;
    private Integer idSp2d;
    private Integer nomorSp2d;
    private Integer idSkpd;
    private String kodeWilayahProses;
    private String namaFile;
    private String kodeSah;
    private BigDecimal nilaiPotongan;
    private String noDokSp2d;
    private String norekTujuan;
    private String namaTujuan;
    private String alamat;
    private String npwp;
    private String norekPengirim;
    private String namaPengirim;
    private String kodeBilling;
    private String ketSp2d;
    private String kodeSkpd;
    private String namaSkpd;
    private String nilaiAmountBalance;
    private BigDecimal nilaiBayar;
    private String statusBank;
    private String msgKirim;
    private String trxTerimaBank;
    private BigDecimal nilaiBayarBank;
    private BigDecimal nilaiKirimBank;
    //private DateTime tglProsesBank;
    private String msgTerimaBank;
    private String tglProses;
    private String kodeAkun;
    private String kodeStan;
    private String nomorRef;
    private Timestamp tglProsesBank;
    private Timestamp tglTerimaBank;

    private String bit4;
    private String bit11;
    private String bit12;
    private String bit13;
    private String bit37;
    private String nodokSp2d;
    private String nilaiBruto;
    private String nilaiBayarRekon;
    private String nilaiPot;
    private String pengguna;
    private String idskpdRekon;
    private String rnorekTujuan;
    private String rnorekPengirim;
    private String bit62;
    private String kodeVA;

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

    public SpmUp getSpmUp() {
        return spmup;
    }

    /**
     * @param skpd the skpd to set
     */
    public void setSpmUp(SpmUp spmup) {
        this.spmup = spmup;
    }

    public Sp2d getSp2d() {
        return sp2d;
    }

    /**
     * @param skpd the skpd to set
     */
    public void setSp2d(Sp2d sp2d) {
        this.sp2d = sp2d;
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
     * @return the sppUpRinci
     */
    public List<SppUpRinci> getSppUpRinci() {
        return sppUpRinci;
    }

    /**
     * @param sppUpRinci the sppUpRinci to set
     */
    public void setSppUpRinci(List<SppUpRinci> sppUpRinci) {
        this.sppUpRinci = sppUpRinci;
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
     * @return the nrkBendahara
     */
    public String getNrkBendahara() {
        return nrkBendahara;
    }

    /**
     * @param nrkBendahara the nrkBendahara to set
     */
    public void setNrkBendahara(String nrkBendahara) {
        this.nrkBendahara = nrkBendahara;
    }

    /**
     * @return the kodeNihil
     */
    public String getKodeNihil() {
        return kodeNihil;
    }

    /**
     * @param kodeNihil the kodeNihil to set
     */
    public void setKodeNihil(String kodeNihil) {
        this.kodeNihil = kodeNihil;
    }

    /**
     * @return the alamatPdfOutput
     */
    public String getAlamatPdfOutput() {
        return alamatPdfOutput;
    }

    /**
     * @param alamatPdfOutput the alamatPdfOutput to set
     */
    public void setAlamatPdfOutput(String alamatPdfOutput) {
        this.alamatPdfOutput = alamatPdfOutput;
    }

    /**
     * @return the levelSkpd
     */
    public String getLevelSkpd() {
        return levelSkpd;
    }

    /**
     * @param levelSkpd the levelSkpd to set
     */
    public void setLevelSkpd(String levelSkpd) {
        this.levelSkpd = levelSkpd;
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
     * @return the nomorSp2d
     */
    public Integer getNomorSp2d() {
        return nomorSp2d;
    }

    /**
     * @param nomorSp2d the nomorSp2d to set
     */
    public void setNomorSp2d(Integer nomorSp2d) {
        this.nomorSp2d = nomorSp2d;
    }

    /**
     * @return the idSkpd
     */
    public Integer getIdSkpd() {
        return idSkpd;
    }

    /**
     * @param idSkpd the idSkpd to set
     */
    public void setIdSkpd(Integer idSkpd) {
        this.idSkpd = idSkpd;
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

    /**
     * @return the kodeSah
     */
    public String getKodeSah() {
        return kodeSah;
    }

    /**
     * @param kodeSah the kodeSah to set
     */
    public void setKodeSah(String kodeSah) {
        this.kodeSah = kodeSah;
    }

    /**
     * @return the nilaiPotongan
     */
    public BigDecimal getNilaiPotongan() {
        return nilaiPotongan;
    }

    /**
     * @param nilaiPotongan the nilaiPotongan to set
     */
    public void setNilaiPotongan(BigDecimal nilaiPotongan) {
        this.nilaiPotongan = nilaiPotongan;
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
     * @return the norekTujuan
     */
    public String getNorekTujuan() {
        return norekTujuan;
    }

    /**
     * @param norekTujuan the norekTujuan to set
     */
    public void setNorekTujuan(String norekTujuan) {
        this.norekTujuan = norekTujuan;
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

    /**
     * @return the alamat
     */
    public String getAlamat() {
        return alamat;
    }

    /**
     * @param alamat the alamat to set
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    /**
     * @return the npwp
     */
    public String getNpwp() {
        return npwp;
    }

    /**
     * @param npwp the npwp to set
     */
    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    /**
     * @return the norekPengirim
     */
    public String getNorekPengirim() {
        return norekPengirim;
    }

    /**
     * @param norekPengirim the norekPengirim to set
     */
    public void setNorekPengirim(String norekPengirim) {
        this.norekPengirim = norekPengirim;
    }

    /**
     * @return the namaPengirim
     */
    public String getNamaPengirim() {
        return namaPengirim;
    }

    /**
     * @param namaPengirim the namaPengirim to set
     */
    public void setNamaPengirim(String namaPengirim) {
        this.namaPengirim = namaPengirim;
    }

    /**
     * @return the kodeBilling
     */
    public String getKodeBilling() {
        return kodeBilling;
    }

    /**
     * @param kodeBilling the kodeBilling to set
     */
    public void setKodeBilling(String kodeBilling) {
        this.kodeBilling = kodeBilling;
    }

    /**
     * @return the ketSp2d
     */
    public String getKetSp2d() {
        return ketSp2d;
    }

    /**
     * @param ketSp2d the ketSp2d to set
     */
    public void setKetSp2d(String ketSp2d) {
        this.ketSp2d = ketSp2d;
    }

    /**
     * @return the kodeSkpd
     */
    public String getKodeSkpd() {
        return kodeSkpd;
    }

    /**
     * @param kodeSkpd the kodeSkpd to set
     */
    public void setKodeSkpd(String kodeSkpd) {
        this.kodeSkpd = kodeSkpd;
    }

    /**
     * @return the namaSkpd
     */
    public String getNamaSkpd() {
        return namaSkpd;
    }

    /**
     * @param namaSkpd the namaSkpd to set
     */
    public void setNamaSkpd(String namaSkpd) {
        this.namaSkpd = namaSkpd;
    }

    /**
     * @return the nilaiAmountBalance
     */
    public String getNilaiAmountBalance() {
        return nilaiAmountBalance;
    }

    /**
     * @param nilaiAmountBalance the nilaiAmountBalance to set
     */
    public void setNilaiAmountBalance(String nilaiAmountBalance) {
        this.nilaiAmountBalance = nilaiAmountBalance;
    }

    /**
     * @return the nilaiBayar
     */
    public BigDecimal getNilaiBayar() {
        return nilaiBayar;
    }

    /**
     * @param nilaiBayar the nilaiBayar to set
     */
    public void setNilaiBayar(BigDecimal nilaiBayar) {
        this.nilaiBayar = nilaiBayar;
    }

    /**
     * @return the statusBank
     */
    public String getStatusBank() {
        return statusBank;
    }

    /**
     * @param statusBank the statusBank to set
     */
    public void setStatusBank(String statusBank) {
        this.statusBank = statusBank;
    }

    /**
     * @return the msgKirim
     */
    public String getMsgKirim() {
        return msgKirim;
    }

    /**
     * @param msgKirim the msgKirim to set
     */
    public void setMsgKirim(String msgKirim) {
        this.msgKirim = msgKirim;
    }

    /**
     * @return the trxTerimaBank
     */
    public String getTrxTerimaBank() {
        return trxTerimaBank;
    }

    /**
     * @param trxTerimaBank the trxTerimaBank to set
     */
    public void setTrxTerimaBank(String trxTerimaBank) {
        this.trxTerimaBank = trxTerimaBank;
    }

    /**
     * @return the nilaiBayarBank
     */
    public BigDecimal getNilaiBayarBank() {
        return nilaiBayarBank;
    }

    /**
     * @param nilaiBayarBank the nilaiBayarBank to set
     */
    public void setNilaiBayarBank(BigDecimal nilaiBayarBank) {
        this.nilaiBayarBank = nilaiBayarBank;
    }

    /**
     * @return the msgTerimaBank
     */
    public String getMsgTerimaBank() {
        return msgTerimaBank;
    }

    /**
     * @param msgTerimaBank the msgTerimaBank to set
     */
    public void setMsgTerimaBank(String msgTerimaBank) {
        this.msgTerimaBank = msgTerimaBank;
    }

    /**
     * @return the tglProses
     */
    public String getTglProses() {
        return tglProses;
    }

    /**
     * @param tglProses the tglProses to set
     */
    public void setTglProses(String tglProses) {
        this.tglProses = tglProses;
    }

    /**
     * @return the kodeAkun
     */
    public String getKodeAkun() {
        return kodeAkun;
    }

    /**
     * @param kodeAkun the kodeAkun to set
     */
    public void setKodeAkun(String kodeAkun) {
        this.kodeAkun = kodeAkun;
    }

    /**
     * @return the kodeStan
     */
    public String getKodeStan() {
        return kodeStan;
    }

    /**
     * @param kodeStan the kodeStan to set
     */
    public void setKodeStan(String kodeStan) {
        this.kodeStan = kodeStan;
    }

    /**
     * @return the nomorRef
     */
    public String getNomorRef() {
        return nomorRef;
    }

    /**
     * @param nomorRef the nomorRef to set
     */
    public void setNomorRef(String nomorRef) {
        this.nomorRef = nomorRef;
    }

    /**
     * @return the tglProsesBank
     */
    public Timestamp getTglProsesBank() {
        return tglProsesBank;
    }

    /**
     * @param tglProsesBank the tglProsesBank to set
     */
    public void setTglProsesBank(Timestamp tglProsesBank) {
        this.tglProsesBank = tglProsesBank;
    }

    /**
     * @return the tglTerimaBank
     */
    public Timestamp getTglTerimaBank() {
        return tglTerimaBank;
    }

    /**
     * @param tglTerimaBank the tglTerimaBank to set
     */
    public void setTglTerimaBank(Timestamp tglTerimaBank) {
        this.tglTerimaBank = tglTerimaBank;
    }

    /**
     * @return the nilaiKirimBank
     */
    public BigDecimal getNilaiKirimBank() {
        return nilaiKirimBank;
    }

    /**
     * @param nilaiKirimBank the nilaiKirimBank to set
     */
    public void setNilaiKirimBank(BigDecimal nilaiKirimBank) {
        this.nilaiKirimBank = nilaiKirimBank;
    }

    /**
     * @return the bit4
     */
    public String getBit4() {
        return bit4;
    }

    /**
     * @param bit4 the bit4 to set
     */
    public void setBit4(String bit4) {
        this.bit4 = bit4;
    }

    /**
     * @return the bit11
     */
    public String getBit11() {
        return bit11;
    }

    /**
     * @param bit11 the bit11 to set
     */
    public void setBit11(String bit11) {
        this.bit11 = bit11;
    }

    /**
     * @return the bit12
     */
    public String getBit12() {
        return bit12;
    }

    /**
     * @param bit12 the bit12 to set
     */
    public void setBit12(String bit12) {
        this.bit12 = bit12;
    }

    /**
     * @return the bit13
     */
    public String getBit13() {
        return bit13;
    }

    /**
     * @param bit13 the bit13 to set
     */
    public void setBit13(String bit13) {
        this.bit13 = bit13;
    }

    /**
     * @return the bit37
     */
    public String getBit37() {
        return bit37;
    }

    /**
     * @param bit37 the bit37 to set
     */
    public void setBit37(String bit37) {
        this.bit37 = bit37;
    }

    /**
     * @return the nodokSp2d
     */
    public String getNodokSp2d() {
        return nodokSp2d;
    }

    /**
     * @param nodokSp2d the nodokSp2d to set
     */
    public void setNodokSp2d(String nodokSp2d) {
        this.nodokSp2d = nodokSp2d;
    }

    /**
     * @return the nilaiBruto
     */
    public String getNilaiBruto() {
        return nilaiBruto;
    }

    /**
     * @param nilaiBruto the nilaiBruto to set
     */
    public void setNilaiBruto(String nilaiBruto) {
        this.nilaiBruto = nilaiBruto;
    }

    /**
     * @return the nilaiBayarRekon
     */
    public String getNilaiBayarRekon() {
        return nilaiBayarRekon;
    }

    /**
     * @param nilaiBayarRekon the nilaiBayarRekon to set
     */
    public void setNilaiBayarRekon(String nilaiBayarRekon) {
        this.nilaiBayarRekon = nilaiBayarRekon;
    }

    /**
     * @return the nilaiPot
     */
    public String getNilaiPot() {
        return nilaiPot;
    }

    /**
     * @param nilaiPot the nilaiPot to set
     */
    public void setNilaiPot(String nilaiPot) {
        this.nilaiPot = nilaiPot;
    }

    /**
     * @return the pengguna
     */
    public String getPengguna() {
        return pengguna;
    }

    /**
     * @param pengguna the pengguna to set
     */
    public void setPengguna(String pengguna) {
        this.pengguna = pengguna;
    }

    /**
     * @return the idskpdRekon
     */
    public String getIdskpdRekon() {
        return idskpdRekon;
    }

    /**
     * @param idskpdRekon the idskpdRekon to set
     */
    public void setIdskpdRekon(String idskpdRekon) {
        this.idskpdRekon = idskpdRekon;
    }

    /**
     * @return the rnorekTujuan
     */
    public String getRnorekTujuan() {
        return rnorekTujuan;
    }

    /**
     * @param rnorekTujuan the rnorekTujuan to set
     */
    public void setRnorekTujuan(String rnorekTujuan) {
        this.rnorekTujuan = rnorekTujuan;
    }

    /**
     * @return the rnorekPengirim
     */
    public String getRnorekPengirim() {
        return rnorekPengirim;
    }

    /**
     * @param rnorekPengirim the rnorekPengirim to set
     */
    public void setRnorekPengirim(String rnorekPengirim) {
        this.rnorekPengirim = rnorekPengirim;
    }

    /**
     * @return the bit62
     */
    public String getBit62() {
        return bit62;
    }

    /**
     * @param bit62 the bit62 to set
     */
    public void setBit62(String bit62) {
        this.bit62 = bit62;
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

}
