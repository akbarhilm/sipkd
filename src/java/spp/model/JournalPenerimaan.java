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

public class JournalPenerimaan extends BaseModel{
    
    // tambahan journal
    private Skpd skpd;
    private Integer idskpd;
    private String tahun;
    
    private String idpnrm;
    private String idloket;
    private String loket;
    private String namaloket;
    private String tglvalidasi;
    private String noloket;
    private String kodeskpd;
    private String namaskpd;
    private String sts;
    private String kode;
    private BigDecimal nilaipnrm;
    private Date d_validasi;
    private String kodetgl;
    private String kettgl;
    
    
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
     * @return the idpnrm
     */
    public String getIdpnrm() {
        return idpnrm;
    }

    /**
     * @param idpnrm the idpnrm to set
     */
    public void setIdpnrm(String idpnrm) {
        this.idpnrm = idpnrm;
    }

    /**
     * @return the idloket
     */
    public String getIdloket() {
        return idloket;
    }

    /**
     * @param idloket the idloket to set
     */
    public void setIdloket(String idloket) {
        this.idloket = idloket;
    }

    /**
     * @return the loket
     */
    public String getLoket() {
        return loket;
    }

    /**
     * @param loket the loket to set
     */
    public void setLoket(String loket) {
        this.loket = loket;
    }

    /**
     * @return the namaloket
     */
    public String getNamaloket() {
        return namaloket;
    }

    /**
     * @param namaloket the namaloket to set
     */
    public void setNamaloket(String namaloket) {
        this.namaloket = namaloket;
    }

    /**
     * @return the tglvalidasi
     */
    public String getTglvalidasi() {
        return tglvalidasi;
    }

    /**
     * @param tglvalidasi the tglvalidasi to set
     */
    public void setTglvalidasi(String tglvalidasi) {
        this.tglvalidasi = tglvalidasi;
    }

    /**
     * @return the noloket
     */
    public String getNoloket() {
        return noloket;
    }

    /**
     * @param noloket the noloket to set
     */
    public void setNoloket(String noloket) {
        this.noloket = noloket;
    }

    /**
     * @return the kodeskpd
     */
    public String getKodeskpd() {
        return kodeskpd;
    }

    /**
     * @param kodeskpd the kodeskpd to set
     */
    public void setKodeskpd(String kodeskpd) {
        this.kodeskpd = kodeskpd;
    }

    /**
     * @return the namaskpd
     */
    public String getNamaskpd() {
        return namaskpd;
    }

    /**
     * @param namaskpd the namaskpd to set
     */
    public void setNamaskpd(String namaskpd) {
        this.namaskpd = namaskpd;
    }

    /**
     * @return the sts
     */
    public String getSts() {
        return sts;
    }

    /**
     * @param sts the sts to set
     */
    public void setSts(String sts) {
        this.sts = sts;
    }

    /**
     * @return the kode
     */
    public String getKode() {
        return kode;
    }

    /**
     * @param kode the kode to set
     */
    public void setKode(String kode) {
        this.kode = kode;
    }

    /**
     * @return the d_validasi
     */
    public Date getD_validasi() {
        return d_validasi;
    }

    /**
     * @param d_validasi the d_validasi to set
     */
    public void setD_validasi(Date d_validasi) {
        this.d_validasi = d_validasi;
    }

    /**
     * @return the kodetgl
     */
    public String getKodetgl() {
        return kodetgl;
    }

    /**
     * @param kodetgl the kodetgl to set
     */
    public void setKodetgl(String kodetgl) {
        this.kodetgl = kodetgl;
    }

    /**
     * @return the kettgl
     */
    public String getKettgl() {
        return kettgl;
    }

    /**
     * @param kettgl the kettgl to set
     */
    public void setKettgl(String kettgl) {
        this.kettgl = kettgl;
    }

    /**
     * @return the nilaipnrm
     */
    public BigDecimal getNilaipnrm() {
        return nilaipnrm;
    }

    /**
     * @param nilaipnrm the nilaipnrm to set
     */
    public void setNilaipnrm(BigDecimal nilaipnrm) {
        this.nilaipnrm = nilaipnrm;
    }

    
    
}
