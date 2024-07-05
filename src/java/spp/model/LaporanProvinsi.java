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

public class LaporanProvinsi extends BaseModel{
    
    private Skpd skpd;
    private Integer idskpd;
    private String tahun;
    
    private String nojurnal;
    private String valnojur;
    private String tglPosting;

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
     * @return the nojurnal
     */
    public String getNojurnal() {
        return nojurnal;
    }

    /**
     * @param nojurnal the nojurnal to set
     */
    public void setNojurnal(String nojurnal) {
        this.nojurnal = nojurnal;
    }

    /**
     * @return the valnojur
     */
    public String getValnojur() {
        return valnojur;
    }

    /**
     * @param valnojur the valnojur to set
     */
    public void setValnojur(String valnojur) {
        this.valnojur = valnojur;
    }

    /**
     * @return the tglPosting
     */
    public String getTglPosting() {
        return tglPosting;
    }

    /**
     * @param tglPosting the tglPosting to set
     */
    public void setTglPosting(String tglPosting) {
        this.tglPosting = tglPosting;
    }
    
    
   

    
    
}
