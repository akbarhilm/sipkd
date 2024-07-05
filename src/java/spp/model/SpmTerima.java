package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Anita
 */
public class SpmTerima extends BaseModel {

    private Integer idSpmCetak;
    private String codeWilSp2dproses;
    private Integer iSpmno;
    private String iSpmnoDok;
    private String codeJenis;
    private String codeBeban;
    private Integer iIdskpd;
    private String codeSkpd;
    private String namaSkpd;
    private Date dSpmCetak;
    private String eSpm;
    private String kodeBank;
    private String namaBank;
    private String noRekening;
    private String namaRekanan;
    private String namaRekananBank;
    private String npwpRekanan;
    private String namaTujuan;
    private String kodeVA;
    private String noNpwp;
    private String namaNpwp;
    private String alamatNpwp;
    private String kotaNpwp;
    private String kodePkp;

    //@NotNull
    //@NotEmpty
    private String namaPegTerimakpkd;
    //@NotNull
    //@NotEmpty
    private String namaPegPemberiskpd;

    private String tahun;

    /**
     * @return the idSpmCetak
     */
    public Integer getIdSpmCetak() {
        return idSpmCetak;
    }

    /**
     * @param idSpmCetak the idSpmCetak to set
     */
    public void setIdSpmCetak(Integer idSpmCetak) {
        this.idSpmCetak = idSpmCetak;
    }

    /**
     * @return the codeWilSp2dproses
     */
    public String getCodeWilSp2dproses() {
        return codeWilSp2dproses;
    }

    /**
     * @param codeWilSp2dproses the codeWilSp2dproses to set
     */
    public void setCodeWilSp2dproses(String codeWilSp2dproses) {
        this.codeWilSp2dproses = codeWilSp2dproses;
    }

    /**
     * @return the iSpmno
     */
    public Integer getiSpmno() {
        return iSpmno;
    }

    /**
     * @param iSpmno the iSpmno to set
     */
    public void setiSpmno(Integer iSpmno) {
        this.iSpmno = iSpmno;
    }

    /**
     * @return the iSpmnoDok
     */
    public String getiSpmnoDok() {
        return iSpmnoDok;
    }

    /**
     * @param iSpmnoDok the iSpmnoDok to set
     */
    public void setiSpmnoDok(String iSpmnoDok) {
        this.iSpmnoDok = iSpmnoDok;
    }

    /**
     * @return the codeJenis
     */
    public String getCodeJenis() {
        return codeJenis;
    }

    /**
     * @param codeJenis the codeJenis to set
     */
    public void setCodeJenis(String codeJenis) {
        this.codeJenis = codeJenis;
    }

    /**
     * @return the codeBeban
     */
    public String getCodeBeban() {
        return codeBeban;
    }

    /**
     * @param codeBeban the codeBeban to set
     */
    public void setCodeBeban(String codeBeban) {
        this.codeBeban = codeBeban;
    }

    /**
     * @return the iIdskpd
     */
    public Integer getiIdskpd() {
        return iIdskpd;
    }

    /**
     * @param iIdskpd the iIdskpd to set
     */
    public void setiIdskpd(Integer iIdskpd) {
        this.iIdskpd = iIdskpd;
    }

    /**
     * @return the codeSkpd
     */
    public String getCodeSkpd() {
        return codeSkpd;
    }

    /**
     * @param codeSkpd the codeSkpd to set
     */
    public void setCodeSkpd(String codeSkpd) {
        this.codeSkpd = codeSkpd;
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
     * @return the dSpmCetak
     */
    public Date getdSpmCetak() {
        return dSpmCetak;
    }

    /**
     * @param dSpmCetak the dSpmCetak to set
     */
    public void setdSpmCetak(Date dSpmCetak) {
        this.dSpmCetak = dSpmCetak;
    }

    /**
     * @return the eSpm
     */
    public String geteSpm() {
        return eSpm;
    }

    /**
     * @param eSpm the eSpm to set
     */
    public void seteSpm(String eSpm) {
        this.eSpm = eSpm;
    }

    /**
     * @return the namaPegTerimakpkd
     */
    public String getNamaPegTerimakpkd() {
        return namaPegTerimakpkd;
    }

    /**
     * @param namaPegTerimakpkd the namaPegTerimakpkd to set
     */
    public void setNamaPegTerimakpkd(String namaPegTerimakpkd) {
        this.namaPegTerimakpkd = namaPegTerimakpkd;
    }

    /**
     * @return the namaPegPemberiskpd
     */
    public String getNamaPegPemberiskpd() {
        return namaPegPemberiskpd;
    }

    /**
     * @param namaPegPemberiskpd the namaPegPemberiskpd to set
     */
    public void setNamaPegPemberiskpd(String namaPegPemberiskpd) {
        this.namaPegPemberiskpd = namaPegPemberiskpd;
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

}
