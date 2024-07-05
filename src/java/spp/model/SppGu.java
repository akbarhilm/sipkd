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
 * @author erzy
 */
public class SppGu extends BaseModel {
    
     private Integer id;
    private String noSpp;
    private String status;
    private Date tglSpp;
    @Valid
    private Skpd skpd;
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
    private List<SppGuRinci> sppGuRinci;
    private BigDecimal totalAngaran;
    private String totalSpp;
    private String totalSpj;
    private String nilaiSisaPaguSpp;
    private String nilaiSpp;
    private Date tglSppCetak;
    private String fileCetakSpp;
    private Integer statusCetak;
    private Date tglSppSah;

    private String namaBank;
    private String kodeBank;
    private String nomorRekBank;

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
     * @return the sppGuRinci
     */
    public List<SppGuRinci> getSppGuRinci() {
        return sppGuRinci;
    }

    /**
     * @param sppGuRinci the sppGuRinci to set
     */
    public void setSppGuRinci(List<SppGuRinci> sppGuRinci) {
        this.sppGuRinci = sppGuRinci;
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
     * @return the totalSpp
     */
    public String getTotalSpp() {
        return totalSpp;
    }

    /**
     * @param totalSpp the totalSpp to set
     */
    public void setTotalSpp(String totalSpp) {
        this.totalSpp = totalSpp;
    }

    /**
     * @return the totalSpj
     */
    public String getTotalSpj() {
        return totalSpj;
    }

    /**
     * @param totalSpj the totalSpj to set
     */
    public void setTotalSpj(String totalSpj) {
        this.totalSpj = totalSpj;
    }

    /**
     * @return the nilaiSisaPaguSpp
     */
    public String getNilaiSisaPaguSpp() {
        return nilaiSisaPaguSpp;
    }

    /**
     * @param nilaiSisaPaguSpp the nilaiSisaPaguSpp to set
     */
    public void setNilaiSisaPaguSpp(String nilaiSisaPaguSpp) {
        this.nilaiSisaPaguSpp = nilaiSisaPaguSpp;
    }

    /**
     * @return the nilaiSpp
     */
    public String getNilaiSpp() {
        return nilaiSpp;
    }

    /**
     * @param nilaiSpp the nilaiSpp to set
     */
    public void setNilaiSpp(String nilaiSpp) {
        this.nilaiSpp = nilaiSpp;
    }



    
}
