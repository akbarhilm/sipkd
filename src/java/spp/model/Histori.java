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
public class Histori extends BaseModel{
    
    private String namaRekening;
    private String noRekening;
    private String namaWilayah;
    private String kodeWilSp2d;
     private String tglTrx;
    private String jamTrx;
    private String keterangan;
    private Double terima;
    private Double keluar;
    private String generateTime;
    private String ketBku;
    private String namaTujuan;

    public String getKodeWilSp2d() {
        return kodeWilSp2d;
    }

    public void setKodeWilSp2d(String kodeWilSp2d) {
        this.kodeWilSp2d = kodeWilSp2d;
    }

    public String getTglTrx() {
        return tglTrx;
    }

    public void setTglTrx(String tglTrx) {
        this.tglTrx = tglTrx;
    }

    public String getJamTrx() {
        return jamTrx;
    }

    public void setJamTrx(String jamTrx) {
        this.jamTrx = jamTrx;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Double getTerima() {
        return terima;
    }

    public void setTerima(Double terima) {
        this.terima = terima;
    }

    public Double getKeluar() {
        return keluar;
    }

    public void setKeluar(Double keluar) {
        this.keluar = keluar;
    }

    public String getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(String generateTime) {
        this.generateTime = generateTime;
    }

    public String getKetBku() {
        return ketBku;
    }

    public void setKetBku(String ketBku) {
        this.ketBku = ketBku;
    }

    public String getNamaTujuan() {
        return namaTujuan;
    }

    public void setNamaTujuan(String namaTujuan) {
        this.namaTujuan = namaTujuan;
    }

    public String getNamaRekening() {
        return namaRekening;
    }

    public void setNamaRekening(String namaRekening) {
        this.namaRekening = namaRekening;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public String getNamaWilayah() {
        return namaWilayah;
    }

    public void setNamaWilayah(String namaWilayah) {
        this.namaWilayah = namaWilayah;
    }
    
    
}
