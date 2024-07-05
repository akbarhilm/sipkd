/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eset.model;

import java.util.List;

/**
 *
 * @author User
 */
public class Eset extends BaseModel {

    private String idSp2d;
    private String noDokSp2d;
    private String noSp2d;
    private String skpd;
    private String kodeSkpd;
    private String namaSkpd;
    private String nilaiSpp;
    private String nilaiPotSpm;
    private String nilaiSp2d;
    private String kodeBankTujuan;
    private String noRekTujuan;
    private String namaTujuan;
    private String kodeBeban;
    private String ketSp2d;
    private String wilSp2d;
    private String tglSp2d;
    private List<EsetRinci> listesrin;

    public List<EsetRinci> getListesrin() {
        return listesrin;
    }

    public void setListesrin(List<EsetRinci> listesrin) {
        this.listesrin = listesrin;
    }

    public String getSkpd() {
        return skpd;
    }

    public void setSkpd(String skpd) {
        this.skpd = skpd;
    }

    public String getIdSp2d() {
        return idSp2d;
    }

    public void setIdSp2d(String idSp2d) {
        this.idSp2d = idSp2d;
    }

    public String getNoDokSp2d() {
        return noDokSp2d;
    }

    public void setNoDokSp2d(String noDokSp2d) {
        this.noDokSp2d = noDokSp2d;
    }

    public String getNoSp2d() {
        return noSp2d;
    }

    public void setNoSp2d(String noSp2d) {
        this.noSp2d = noSp2d;
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

    public String getNilaiSpp() {
        return nilaiSpp;
    }

    public void setNilaiSpp(String nilaiSpp) {
        this.nilaiSpp = nilaiSpp;
    }

    public String getNilaiPotSpm() {
        return nilaiPotSpm;
    }

    public void setNilaiPotSpm(String nilaiPotSpm) {
        this.nilaiPotSpm = nilaiPotSpm;
    }

    public String getNilaiSp2d() {
        return nilaiSp2d;
    }

    public void setNilaiSp2d(String nilaiSp2d) {
        this.nilaiSp2d = nilaiSp2d;
    }

    public String getKodeBankTujuan() {
        return kodeBankTujuan;
    }

    public void setKodeBankTujuan(String kodeBankTujuan) {
        this.kodeBankTujuan = kodeBankTujuan;
    }

    public String getNoRekTujuan() {
        return noRekTujuan;
    }

    public void setNoRekTujuan(String noRekTujuan) {
        this.noRekTujuan = noRekTujuan;
    }

    public String getNamaTujuan() {
        return namaTujuan;
    }

    public void setNamaTujuan(String namaTujuan) {
        this.namaTujuan = namaTujuan;
    }

    public String getKodeBeban() {
        return kodeBeban;
    }

    public void setKodeBeban(String kodeBeban) {
        this.kodeBeban = kodeBeban;
    }

    public String getKetSp2d() {
        return ketSp2d;
    }

    public void setKetSp2d(String ketSp2d) {
        this.ketSp2d = ketSp2d;
    }

    public String getWilSp2d() {
        return wilSp2d;
    }

    public void setWilSp2d(String wilSp2d) {
        this.wilSp2d = wilSp2d;
    }

    public String getTglSp2d() {
        return tglSp2d;
    }

    public void setTglSp2d(String tglSp2d) {
        this.tglSp2d = tglSp2d;
    }

}
