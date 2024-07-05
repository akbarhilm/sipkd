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

/**
 *
 * @author Husen
 */
public class DraftPajakOnline extends BaseModel {

    private String tahun;

    private String kodeJenis;

    private String idSkpd;
    private String idSpmpot;

    private String idSpmpotformat;
    private String idSp2d;

    private String kodeSkpd;
    private String namaSkpd;
    private String kodeWilsp2d;
    private String noNpwpbud;

    private String namaNpwpbud;
    private String alamatNpwpbud;

    private String kotaNpwnbud;
    private String namaPenyetor;
    private String noSp2d;
    private String noSp2dformat;

    private String tglSp2dsah;
    private String kodeSp2dsah;

    private String kodeTrans;
    private String kodePotspm;
    private String nilaiPotspm;
    private String ketPotsp2d;

    private String kontrak;
    private String noNpwprekan;
    private String namaNpwprekan;

    private String alamatNpwprekan;
    private String kotaNpwnrekan;
    private String kodeBankkpkd;
    private String noRekeningkpkd;
    private String kodeDpjkjs ;
   private String kodeDjpmap ;

    private String bulanSpp;
    private String bulanSp2dsah;
    private String masa;
    private String tahunPajak ;
    private String noSk;
    private String kodeBilling;
    private String tglBillingexp;

    private String tglBuku;
    private String bpnStatus;
    private String dPay;
    private String qPotcetak;
    private String noNtb;
    private String noNtpn;
    private String noBulkreq;
    private String persenPot;

    private String kodeBeban;

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getKodeJenis() {
        return kodeJenis;
    }

    public void setKodeJenis(String kodeJenis) {
        this.kodeJenis = kodeJenis;
    }

    public String getIdSkpd() {
        return idSkpd;
    }

    public void setIdSkpd(String idSkpd) {
        this.idSkpd = idSkpd;
    }

    public String getIdSpmpot() {
        return idSpmpot;
    }

    public void setIdSpmpot(String idSpmpot) {
        this.idSpmpot = idSpmpot;
    }

    public String getIdSpmpotformat() {
        return idSpmpotformat;
    }

    public void setIdSpmpotformat(String idSpmpotformat) {
        this.idSpmpotformat = idSpmpotformat;
    }

    public String getIdSp2d() {
        return idSp2d;
    }

    public void setIdSp2d(String idSp2d) {
        this.idSp2d = idSp2d;
    }

    public String getKodeSkpd() {
        return kodeSkpd;
    }

    public void setKodeSkpd(String kodeSkpd) {
        this.kodeSkpd = kodeSkpd;
    }

    public String getNamaSkpd() {
        return namaSkpd;
    }

    public void setNamaSkpd(String namaSkpd) {
        this.namaSkpd = namaSkpd;
    }

    public String getKodeWilsp2d() {
        return kodeWilsp2d;
    }

    public void setKodeWilsp2d(String kodeWilsp2d) {
        this.kodeWilsp2d = kodeWilsp2d;
    }

    public String getNoNpwpbud() {
        return noNpwpbud;
    }

    public void setNoNpwpbud(String noNpwpbud) {
        this.noNpwpbud = noNpwpbud;
    }

    public String getNamaNpwpbud() {
        return namaNpwpbud;
    }

    public void setNamaNpwpbud(String namaNpwpbud) {
        this.namaNpwpbud = namaNpwpbud;
    }

    public String getAlamatNpwpbud() {
        return alamatNpwpbud;
    }

    public void setAlamatNpwpbud(String alamatNpwpbud) {
        this.alamatNpwpbud = alamatNpwpbud;
    }

    public String getKotaNpwnbud() {
        return kotaNpwnbud;
    }

    public void setKotaNpwnbud(String kotaNpwnbud) {
        this.kotaNpwnbud = kotaNpwnbud;
    }

    public String getNamaPenyetor() {
        return namaPenyetor;
    }

    public void setNamaPenyetor(String namaPenyetor) {
        this.namaPenyetor = namaPenyetor;
    }

    public String getNoSp2d() {
        return noSp2d;
    }

    public void setNoSp2d(String noSp2d) {
        this.noSp2d = noSp2d;
    }

    public String getNoSp2dformat() {
        return noSp2dformat;
    }

    public void setNoSp2dformat(String noSp2dformat) {
        this.noSp2dformat = noSp2dformat;
    }

    public String getTglSp2dsah() {
        return tglSp2dsah;
    }

    public void setTglSp2dsah(String tglSp2dsah) {
        this.tglSp2dsah = tglSp2dsah;
    }

    public String getKodeSp2dsah() {
        return kodeSp2dsah;
    }

    public void setKodeSp2dsah(String kodeSp2dsah) {
        this.kodeSp2dsah = kodeSp2dsah;
    }

    public String getKodeTrans() {
        return kodeTrans;
    }

    public void setKodeTrans(String kodeTrans) {
        this.kodeTrans = kodeTrans;
    }

    public String getKodePotspm() {
        return kodePotspm;
    }

    public void setKodePotspm(String kodePotspm) {
        this.kodePotspm = kodePotspm;
    }

    public String getNilaiPotspm() {
        return nilaiPotspm;
    }

    public void setNilaiPotspm(String nilaiPotspm) {
        this.nilaiPotspm = nilaiPotspm;
    }

    public String getKetPotsp2d() {
        return ketPotsp2d;
    }

    public void setKetPotsp2d(String ketPotsp2d) {
        this.ketPotsp2d = ketPotsp2d;
    }

    public String getKontrak() {
        return kontrak;
    }

    public void setKontrak(String kontrak) {
        this.kontrak = kontrak;
    }

    public String getNoNpwprekan() {
        return noNpwprekan;
    }

    public void setNoNpwprekan(String noNpwprekan) {
        this.noNpwprekan = noNpwprekan;
    }

    public String getNamaNpwprekan() {
        return namaNpwprekan;
    }

    public void setNamaNpwprekan(String namaNpwprekan) {
        this.namaNpwprekan = namaNpwprekan;
    }

    public String getAlamatNpwprekan() {
        return alamatNpwprekan;
    }

    public void setAlamatNpwprekan(String alamatNpwprekan) {
        this.alamatNpwprekan = alamatNpwprekan;
    }

    public String getKotaNpwnrekan() {
        return kotaNpwnrekan;
    }

    public void setKotaNpwnrekan(String kotaNpwnrekan) {
        this.kotaNpwnrekan = kotaNpwnrekan;
    }

    public String getKodeBankkpkd() {
        return kodeBankkpkd;
    }

    public void setKodeBankkpkd(String kodeBankkpkd) {
        this.kodeBankkpkd = kodeBankkpkd;
    }

    public String getNoRekeningkpkd() {
        return noRekeningkpkd;
    }

    public void setNoRekeningkpkd(String noRekeningkpkd) {
        this.noRekeningkpkd = noRekeningkpkd;
    }

    public String getKodeDpjkjs() {
        return kodeDpjkjs;
    }

    public void setKodeDpjkjs(String kodeDpjkjs) {
        this.kodeDpjkjs = kodeDpjkjs;
    }

    public String getKodeDjpmap() {
        return kodeDjpmap;
    }

    public void setKodeDjpmap(String kodeDjpmap) {
        this.kodeDjpmap = kodeDjpmap;
    }

    public String getBulanSpp() {
        return bulanSpp;
    }

    public void setBulanSpp(String bulanSpp) {
        this.bulanSpp = bulanSpp;
    }

    public String getBulanSp2dsah() {
        return bulanSp2dsah;
    }

    public void setBulanSp2dsah(String bulanSp2dsah) {
        this.bulanSp2dsah = bulanSp2dsah;
    }

    public String getMasa() {
        return masa;
    }

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public String getTahunPajak() {
        return tahunPajak;
    }

    public void setTahunPajak(String tahunPajak) {
        this.tahunPajak = tahunPajak;
    }

    public String getNoSk() {
        return noSk;
    }

    public void setNoSk(String noSk) {
        this.noSk = noSk;
    }

    public String getKodeBilling() {
        return kodeBilling;
    }

    public void setKodeBilling(String kodeBilling) {
        this.kodeBilling = kodeBilling;
    }

    public String getTglBillingexp() {
        return tglBillingexp;
    }

    public void setTglBillingexp(String tglBillingexp) {
        this.tglBillingexp = tglBillingexp;
    }

    public String getTglBuku() {
        return tglBuku;
    }

    public void setTglBuku(String tglBuku) {
        this.tglBuku = tglBuku;
    }

    public String getBpnStatus() {
        return bpnStatus;
    }

    public void setBpnStatus(String bpnStatus) {
        this.bpnStatus = bpnStatus;
    }

    public String getdPay() {
        return dPay;
    }

    public void setdPay(String dPay) {
        this.dPay = dPay;
    }

    public String getqPotcetak() {
        return qPotcetak;
    }

    public void setqPotcetak(String qPotcetak) {
        this.qPotcetak = qPotcetak;
    }

    public String getNoNtb() {
        return noNtb;
    }

    public void setNoNtb(String noNtb) {
        this.noNtb = noNtb;
    }

    public String getNoNtpn() {
        return noNtpn;
    }

    public void setNoNtpn(String noNtpn) {
        this.noNtpn = noNtpn;
    }

    public String getNoBulkreq() {
        return noBulkreq;
    }

    public void setNoBulkreq(String noBulkreq) {
        this.noBulkreq = noBulkreq;
    }

    public String getPersenPot() {
        return persenPot;
    }

    public void setPersenPot(String persenPot) {
        this.persenPot = persenPot;
    }

    public String getKodeBeban() {
        return kodeBeban;
    }

    public void setKodeBeban(String kodeBeban) {
        this.kodeBeban = kodeBeban;
    }
    
    

}
