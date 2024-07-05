/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author erzypratama
 */
public class BankBantuan extends BaseModel {

    private static final Logger log = LoggerFactory.getLogger(BankBantuan.class);
    private Skpd skpd;
    private String tahun;
    private String kodeBank;
    private String kodeBankTf;
    private String namaBank;
    private Integer idSpp;
    private Integer idskpd;
    private Integer idskpdKoord;
    private String noSpp;
    private String ketSkpd;
    private String ketBendahara;
    private String noRek;
    private String namaPenerima;
    private String namaOrg;
    private BigDecimal nilaiSpp;
    private String alamatPenerima;
    private String uraian;
    
    
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
     * @return the kodeBankTf
     */
    public String getKodeBankTf() {
        return kodeBankTf;
    }

    /**
     * @param kodeBankTf the kodeBankTf to set
     */
    public void setKodeBankTf(String kodeBankTf) {
        this.kodeBankTf = kodeBankTf;
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
     * @return the idskpdKoord
     */
    public Integer getIdskpdKoord() {
        return idskpdKoord;
    }

    /**
     * @param idskpdKoord the idskpdKoord to set
     */
    public void setIdskpdKoord(Integer idskpdKoord) {
        this.idskpdKoord = idskpdKoord;
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
     * @return the ketSkpd
     */
    public String getKetSkpd() {
        return ketSkpd;
    }

    /**
     * @param ketSkpd the ketSkpd to set
     */
    public void setKetSkpd(String ketSkpd) {
        this.ketSkpd = ketSkpd;
    }

    /**
     * @return the ketBendahara
     */
    public String getKetBendahara() {
        return ketBendahara;
    }

    /**
     * @param ketBendahara the ketBendahara to set
     */
    public void setKetBendahara(String ketBendahara) {
        this.ketBendahara = ketBendahara;
    }

    /**
     * @return the noRek
     */
    public String getNoRek() {
        return noRek;
    }

    /**
     * @param noRek the noRek to set
     */
    public void setNoRek(String noRek) {
        this.noRek = noRek;
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
     * @return the namaOrg
     */
    public String getNamaOrg() {
        return namaOrg;
    }

    /**
     * @param namaOrg the namaOrg to set
     */
    public void setNamaOrg(String namaOrg) {
        this.namaOrg = namaOrg;
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
     * @return the alamatPenerima
     */
    public String getAlamatPenerima() {
        return alamatPenerima;
    }

    /**
     * @param alamatPenerima the alamatPenerima to set
     */
    public void setAlamatPenerima(String alamatPenerima) {
        this.alamatPenerima = alamatPenerima;
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

    

}
