/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
import javax.validation.Valid;

/**
 *
 * @author Zainab
 */

public class LaporanLra extends BaseModel{
    
    private Skpd skpd;
    private Integer idskpd;
    private String tahun;
    
    private String pilihSkpd;
    private String kodeSkpd;
    private String ketSkpd;
    
    private String bulan;
    private String kodeBulan;
    private String ketBulan;
    
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
     * @return the pilihSkpd
     */
    public String getPilihSkpd() {
        return pilihSkpd;
    }

    /**
     * @param pilihSkpd the pilihSkpd to set
     */
    public void setPilihSkpd(String pilihSkpd) {
        this.pilihSkpd = pilihSkpd;
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
     * @return the kodeBulan
     */
    public String getKodeBulan() {
        return kodeBulan;
    }

    /**
     * @param kodeBulan the kodeBulan to set
     */
    public void setKodeBulan(String kodeBulan) {
        this.kodeBulan = kodeBulan;
    }

    /**
     * @return the ketBulan
     */
    public String getKetBulan() {
        return ketBulan;
    }

    /**
     * @param ketBulan the ketBulan to set
     */
    public void setKetBulan(String ketBulan) {
        this.ketBulan = ketBulan;
    }

    
    
    
}
