package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import javax.validation.Valid;
import org.joda.time.DateTime;

public class Sp2dPajakTransfer extends BaseModel {

    private Integer id;
    @Valid
    private Skpd skpd;
    private String tahun;
    private String kodeJenis;
    private Integer idSkpd;
    private Integer idSpm;
    private Integer idSpmPot;
    private String idSpmPotFormat;
    private Integer idSp2d;
    private String kodeWilayah;
    private String npwpBud;
    private String namaBud;
    private String alamatBud;
    private String kotaBud;
    private String npwpRekanan;
    private String namaWp;
    private String alamatWp;
    private String kotaWp;
    private String noSp2d;
    private Date tglSp2dSah;
    private String tglSp2dSahFormat;
    private String kodeTrx;
    private String kodePotSpm;
    private BigDecimal nilaiPajak;
    private String uraian;
    private String noKontrak;
    private String kodeBank;
    private String noRekening;
    private String namaPengirimBank;
    private String kodeJenisSetor;
    private String akunPajak;
    private String bulanSah;
    private String masaPajak;
    private String noSk;
    private String kodeBilling;
    private Date tglBillExp;
    private String tglBillExpString;
    private String tglBuku;
    private String statusBpn;
    private String uraianPajak;
    private String kodeStan;
    private String nomorRef;
    private String bulkIdRequest;
    private String idRequest;
    private String idChannel;
    private String idResponse;
    private String namaKJS;
    private String namaMAP;
    private String tahunPajak;
    private String nop;
    private String noIdentitas;
    private String npwpPenyetor;
    private String namaPenyetor;
    private String kodeProses;
    private String kodeResponse;
    private String uraianResponse;
    private Date tglBayar;
    private Date tglTransmisi;
    private String tglBayarString;
    private String tglTransmisiString;
    private String ntb;
    private String ntpn;
    private String kodeRequest;
    private String kodeApp;
    private String persenPot;

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
     * @return the idSpmPot
     */
    public Integer getIdSpmPot() {
        return idSpmPot;
    }

    /**
     * @param idSpmPot the idSpmPot to set
     */
    public void setIdSpmPot(Integer idSpmPot) {
        this.idSpmPot = idSpmPot;
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
     * @return the npwpBud
     */
    public String getNpwpBud() {
        return npwpBud;
    }

    /**
     * @param npwpBud the npwpBud to set
     */
    public void setNpwpBud(String npwpBud) {
        this.npwpBud = npwpBud;
    }

    /**
     * @return the namaBud
     */
    public String getNamaBud() {
        return namaBud;
    }

    /**
     * @param namaBud the namaBud to set
     */
    public void setNamaBud(String namaBud) {
        this.namaBud = namaBud;
    }

    /**
     * @return the alamatBud
     */
    public String getAlamatBud() {
        return alamatBud;
    }

    /**
     * @param alamatBud the alamatBud to set
     */
    public void setAlamatBud(String alamatBud) {
        this.alamatBud = alamatBud;
    }

    /**
     * @return the kotaBud
     */
    public String getKotaBud() {
        return kotaBud;
    }

    /**
     * @param kotaBud the kotaBud to set
     */
    public void setKotaBud(String kotaBud) {
        this.kotaBud = kotaBud;
    }

    /**
     * @return the npwpRekanan
     */
    public String getNpwpRekanan() {
        return npwpRekanan;
    }

    /**
     * @param npwpRekanan the npwpRekanan to set
     */
    public void setNpwpRekanan(String npwpRekanan) {
        this.npwpRekanan = npwpRekanan;
    }

    /**
     * @return the namaWp
     */
    public String getNamaWp() {
        return namaWp;
    }

    /**
     * @param namaWp the namaWp to set
     */
    public void setNamaWp(String namaWp) {
        this.namaWp = namaWp;
    }

    /**
     * @return the alamatWp
     */
    public String getAlamatWp() {
        return alamatWp;
    }

    /**
     * @param alamatWp the alamatWp to set
     */
    public void setAlamatWp(String alamatWp) {
        this.alamatWp = alamatWp;
    }

    /**
     * @return the kotaWp
     */
    public String getKotaWp() {
        return kotaWp;
    }

    /**
     * @param kotaWp the kotaWp to set
     */
    public void setKotaWp(String kotaWp) {
        this.kotaWp = kotaWp;
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
     * @return the tglSp2dSah
     */
    public Date getTglSp2dSah() {
        return tglSp2dSah;
    }

    /**
     * @param tglSp2dSah the tglSp2dSah to set
     */
    public void setTglSp2dSah(Date tglSp2dSah) {
        this.tglSp2dSah = tglSp2dSah;
    }

    /**
     * @return the kodeTrx
     */
    public String getKodeTrx() {
        return kodeTrx;
    }

    /**
     * @param kodeTrx the kodeTrx to set
     */
    public void setKodeTrx(String kodeTrx) {
        this.kodeTrx = kodeTrx;
    }

    /**
     * @return the kodePotSpm
     */
    public String getKodePotSpm() {
        return kodePotSpm;
    }

    /**
     * @param kodePotSpm the kodePotSpm to set
     */
    public void setKodePotSpm(String kodePotSpm) {
        this.kodePotSpm = kodePotSpm;
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
     * @return the noRekening
     */
    public String getNoRekening() {
        return noRekening;
    }

    /**
     * @param noRekening the noRekening to set
     */
    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    /**
     * @return the kodeJenisSetor
     */
    public String getKodeJenisSetor() {
        return kodeJenisSetor;
    }

    /**
     * @param kodeJenisSetor the kodeJenisSetor to set
     */
    public void setKodeJenisSetor(String kodeJenisSetor) {
        this.kodeJenisSetor = kodeJenisSetor;
    }

    /**
     * @return the akunPajak
     */
    public String getAkunPajak() {
        return akunPajak;
    }

    /**
     * @param akunPajak the akunPajak to set
     */
    public void setAkunPajak(String akunPajak) {
        this.akunPajak = akunPajak;
    }

    /**
     * @return the bulanSah
     */
    public String getBulanSah() {
        return bulanSah;
    }

    /**
     * @param bulanSah the bulanSah to set
     */
    public void setBulanSah(String bulanSah) {
        this.bulanSah = bulanSah;
    }

    /**
     * @return the masaPajak
     */
    public String getMasaPajak() {
        return masaPajak;
    }

    /**
     * @param masaPajak the masaPajak to set
     */
    public void setMasaPajak(String masaPajak) {
        this.masaPajak = masaPajak;
    }

    /**
     * @return the noSk
     */
    public String getNoSk() {
        return noSk;
    }

    /**
     * @param noSk the noSk to set
     */
    public void setNoSk(String noSk) {
        this.noSk = noSk;
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
     * @return the tglBillExp
     */
    public Date getTglBillExp() {
        return tglBillExp;
    }

    /**
     * @param tglBillExp the tglBillExp to set
     */
    public void setTglBillExp(Date tglBillExp) {
        this.tglBillExp = tglBillExp;
    }

    /**
     * @return the statusBpn
     */
    public String getStatusBpn() {
        return statusBpn;
    }

    /**
     * @param statusBpn the statusBpn to set
     */
    public void setStatusBpn(String statusBpn) {
        this.statusBpn = statusBpn;
    }

    /**
     * @return the uraianPajak
     */
    public String getUraianPajak() {
        return uraianPajak;
    }

    /**
     * @param uraianPajak the uraianPajak to set
     */
    public void setUraianPajak(String uraianPajak) {
        this.uraianPajak = uraianPajak;
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
     * @return the namaPengirimBank
     */
    public String getNamaPengirimBank() {
        return namaPengirimBank;
    }

    /**
     * @param namaPengirimBank the namaPengirimBank to set
     */
    public void setNamaPengirimBank(String namaPengirimBank) {
        this.namaPengirimBank = namaPengirimBank;
    }

    /**
     * @return the bulkIdRequest
     */
    public String getBulkIdRequest() {
        return bulkIdRequest;
    }

    /**
     * @param bulkIdRequest the bulkIdRequest to set
     */
    public void setBulkIdRequest(String bulkIdRequest) {
        this.bulkIdRequest = bulkIdRequest;
    }

    /**
     * @return the tglBuku
     */
    public String getTglBuku() {
        return tglBuku;
    }

    /**
     * @param tglBuku the tglBuku to set
     */
    public void setTglBuku(String tglBuku) {
        this.tglBuku = tglBuku;
    }

    /**
     * @return the idRequest
     */
    public String getIdRequest() {
        return idRequest;
    }

    /**
     * @param idRequest the idRequest to set
     */
    public void setIdRequest(String idRequest) {
        this.idRequest = idRequest;
    }

    /**
     * @return the idChannel
     */
    public String getIdChannel() {
        return idChannel;
    }

    /**
     * @param idChannel the idChannel to set
     */
    public void setIdChannel(String idChannel) {
        this.idChannel = idChannel;
    }

    /**
     * @return the idResponse
     */
    public String getIdResponse() {
        return idResponse;
    }

    /**
     * @param idResponse the idResponse to set
     */
    public void setIdResponse(String idResponse) {
        this.idResponse = idResponse;
    }

    /**
     * @return the namaKJS
     */
    public String getNamaKJS() {
        return namaKJS;
    }

    /**
     * @param namaKJS the namaKJS to set
     */
    public void setNamaKJS(String namaKJS) {
        this.namaKJS = namaKJS;
    }

    /**
     * @return the namaMAP
     */
    public String getNamaMAP() {
        return namaMAP;
    }

    /**
     * @param namaMAP the namaMAP to set
     */
    public void setNamaMAP(String namaMAP) {
        this.namaMAP = namaMAP;
    }

    /**
     * @return the tahunPajak
     */
    public String getTahunPajak() {
        return tahunPajak;
    }

    /**
     * @param tahunPajak the tahunPajak to set
     */
    public void setTahunPajak(String tahunPajak) {
        this.tahunPajak = tahunPajak;
    }

    /**
     * @return the nop
     */
    public String getNop() {
        return nop;
    }

    /**
     * @param nop the nop to set
     */
    public void setNop(String nop) {
        this.nop = nop;
    }

    /**
     * @return the noIdentitas
     */
    public String getNoIdentitas() {
        return noIdentitas;
    }

    /**
     * @param noIdentitas the noIdentitas to set
     */
    public void setNoIdentitas(String noIdentitas) {
        this.noIdentitas = noIdentitas;
    }

    /**
     * @return the npwpPenyetor
     */
    public String getNpwpPenyetor() {
        return npwpPenyetor;
    }

    /**
     * @param npwpPenyetor the npwpPenyetor to set
     */
    public void setNpwpPenyetor(String npwpPenyetor) {
        this.npwpPenyetor = npwpPenyetor;
    }

    /**
     * @return the namaPenyetor
     */
    public String getNamaPenyetor() {
        return namaPenyetor;
    }

    /**
     * @param namaPenyetor the namaPenyetor to set
     */
    public void setNamaPenyetor(String namaPenyetor) {
        this.namaPenyetor = namaPenyetor;
    }

    /**
     * @return the kodeProses
     */
    public String getKodeProses() {
        return kodeProses;
    }

    /**
     * @param kodeProses the kodeProses to set
     */
    public void setKodeProses(String kodeProses) {
        this.kodeProses = kodeProses;
    }

    /**
     * @return the kodeResponse
     */
    public String getKodeResponse() {
        return kodeResponse;
    }

    /**
     * @param kodeResponse the kodeResponse to set
     */
    public void setKodeResponse(String kodeResponse) {
        this.kodeResponse = kodeResponse;
    }

    /**
     * @return the uraianResponse
     */
    public String getUraianResponse() {
        return uraianResponse;
    }

    /**
     * @param uraianResponse the uraianResponse to set
     */
    public void setUraianResponse(String uraianResponse) {
        this.uraianResponse = uraianResponse;
    }

    /**
     * @return the tglBayar
     */
    public Date getTglBayar() {
        return tglBayar;
    }

    /**
     * @param tglBayar the tglBayar to set
     */
    public void setTglBayar(Date tglBayar) {
        this.tglBayar = tglBayar;
    }

    /**
     * @return the tglTransmisi
     */
    public Date getTglTransmisi() {
        return tglTransmisi;
    }

    /**
     * @param tglTransmisi the tglTransmisi to set
     */
    public void setTglTransmisi(Date tglTransmisi) {
        this.tglTransmisi = tglTransmisi;
    }

    /**
     * @return the ntb
     */
    public String getNtb() {
        return ntb;
    }

    /**
     * @param ntb the ntb to set
     */
    public void setNtb(String ntb) {
        this.ntb = ntb;
    }

    /**
     * @return the ntpn
     */
    public String getNtpn() {
        return ntpn;
    }

    /**
     * @param ntpn the ntpn to set
     */
    public void setNtpn(String ntpn) {
        this.ntpn = ntpn;
    }

    /**
     * @return the kodeRequest
     */
    public String getKodeRequest() {
        return kodeRequest;
    }

    /**
     * @param kodeRequest the kodeRequest to set
     */
    public void setKodeRequest(String kodeRequest) {
        this.kodeRequest = kodeRequest;
    }

    /**
     * @return the tglBillExpString
     */
    public String getTglBillExpString() {
        return tglBillExpString;
    }

    /**
     * @param tglBillExpString the tglBillExpString to set
     */
    public void setTglBillExpString(String tglBillExpString) {
        this.tglBillExpString = tglBillExpString;
    }

    /**
     * @return the tglBayarString
     */
    public String getTglBayarString() {
        return tglBayarString;
    }

    /**
     * @param tglBayarString the tglBayarString to set
     */
    public void setTglBayarString(String tglBayarString) {
        this.tglBayarString = tglBayarString;
    }

    /**
     * @return the tglTransmisiString
     */
    public String getTglTransmisiString() {
        return tglTransmisiString;
    }

    /**
     * @param tglTransmisiString the tglTransmisiString to set
     */
    public void setTglTransmisiString(String tglTransmisiString) {
        this.tglTransmisiString = tglTransmisiString;
    }

    /**
     * @return the idSpmPotFormat
     */
    public String getIdSpmPotFormat() {
        return idSpmPotFormat;
    }

    /**
     * @param idSpmPotFormat the idSpmPotFormat to set
     */
    public void setIdSpmPotFormat(String idSpmPotFormat) {
        this.idSpmPotFormat = idSpmPotFormat;
    }

    /**
     * @return the tglSp2dSahFormat
     */
    public String getTglSp2dSahFormat() {
        return tglSp2dSahFormat;
    }

    /**
     * @param tglSp2dSahFormat the tglSp2dSahFormat to set
     */
    public void setTglSp2dSahFormat(String tglSp2dSahFormat) {
        this.tglSp2dSahFormat = tglSp2dSahFormat;
    }

    /**
     * @return the kodeApp
     */
    public String getKodeApp() {
        return kodeApp;
    }

    /**
     * @param kodeApp the kodeApp to set
     */
    public void setKodeApp(String kodeApp) {
        this.kodeApp = kodeApp;
    }

    /**
     * @return the persenPot
     */
    public String getPersenPot() {
        return persenPot;
    }

    /**
     * @param persenPot the persenPot to set
     */
    public void setPersenPot(String persenPot) {
        this.persenPot = persenPot;
    }

}
