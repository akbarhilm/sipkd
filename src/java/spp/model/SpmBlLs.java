/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spp.model;

import java.sql.Date;
import java.util.List;
/**
 *
 * @author Xalamaster
 */
public class SpmBlLs extends SppBl {
  //  private Integer id;
    private Integer idspm;
    private String noSpm;
    private Date tglSpm;
    private String keteranganSpm;
    private String noJurnal;
    private Date tglJurnal;
   // private String kodeBank;
   // private String namaBank;
   // private String noRekeningSPM;

    
    
    
    private List<SpmBlLsRinci> spmBlLsRinci;

    /**
     * @return the id
     
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the idspm
     */
    public Integer getIdspm() {
        return idspm;
    }

    /**
     * @param idspm the idspm to set
     */
    public void setIdspm(Integer idspm) {
        this.idspm = idspm;
    }

    /**
     * @return the noSpm
     */
    public String getNoSpm() {
        return noSpm;
    }

    /**
     * @param noSpm the noSpm to set
     */
    public void setNoSpm(String noSpm) {
        this.noSpm = noSpm;
    }

    /**
     * @return the tglSpm
     */
    public Date getTglSpm() {
        return tglSpm;
    }

    /**
     * @param tglSpm the tglSpm to set
     */
    public void setTglSpm(Date tglSpm) {
        this.tglSpm = tglSpm;
    }

    /**
     * @return the keteranganSpm
     */
    public String getKeteranganSpm() {
        return keteranganSpm;
    }

    /**
     * @param keteranganSpm the keteranganSpm to set
     */
    public void setKeteranganSpm(String keteranganSpm) {
        this.keteranganSpm = keteranganSpm;
    }

    /**
     * @return the noJurnal
     */
    public String getNoJurnal() {
        return noJurnal;
    }

    /**
     * @param noJurnal the noJurnal to set
     */
    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    /**
     * @return the tglJurnal
     */
    public Date getTglJurnal() {
        return tglJurnal;
    }

    /**
     * @param tglJurnal the tglJurnal to set
     */
    public void setTglJurnal(Date tglJurnal) {
        this.tglJurnal = tglJurnal;
    }

    /**
     * @return the kodeBank
     */

    /**
     * @return the noRekeningSPM
     */


    /**
     * @return the spmBlLsRinci
     */
    public List<SpmBlLsRinci> getSpmBlLsRinci() {
        return spmBlLsRinci;
    }

    /**
     * @param spmBlLsRinci the spmBlLsRinci to set
     */
    public void setSpmBlLsRinci(List<SpmBlLsRinci> spmBlLsRinci) {
        this.spmBlLsRinci = spmBlLsRinci;
    }

    /**
     * @return the kodeKegiatan
     */
    
}
