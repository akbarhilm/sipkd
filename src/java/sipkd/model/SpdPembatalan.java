/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sipkd.model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.validation.Valid;
/**
 *
 * @author zainab
 */
public class SpdPembatalan extends BaseModel {
    @NotNull(message="SKPD Wajib di isi")
    private Integer idSkpd;
    private String namaSkpd;
    
    //tmspdsah
    private Integer idSpdSah;
    private Date tglSpdSah ; // sama dengan d_pgun_rekam
    private BigDecimal v_spd;
    private Integer peg_rekam;
   
    //thspdsah
    private String alasanBatal;
    
    //tambahan keterangan
    private String noDokSpd;
    private Date tglSpd;
    private String keterangan;
    private Integer noSpd;
   

    /**
     * @return the idSkpd
     */
    public Integer getIdSkpd() {
        return idSkpd;
    }

    /**
     * @param idSkpd the idSkpd to set
     */
    public void setIdSkpd(Integer idSkpd) {
        this.idSkpd = idSkpd;
    }

    /**
     * @return the idSpdSah
     */
    public Integer getIdSpdSah() {
        return idSpdSah;
    }

    /**
     * @param idSpdSah the idSpdSah to set
     */
    public void setIdSpdSah(Integer idSpdSah) {
        this.idSpdSah = idSpdSah;
    }

    /**
     * @return the tglSpdSah
     */
    public Date getTglSpdSah() {
        return tglSpdSah;
    }

    /**
     * @param tglSpdSah the tglSpdSah to set
     */
    public void setTglSpdSah(Date tglSpdSah) {
        this.tglSpdSah = tglSpdSah;
    }

    /**
     * @return the peg_rekam
     */
    public Integer getPeg_rekam() {
        return peg_rekam;
    }

    /**
     * @param peg_rekam the peg_rekam to set
     */
    public void setPeg_rekam(Integer peg_rekam) {
        this.peg_rekam = peg_rekam;
    }

    /**
     * @return the alasanBatal
     */
    public String getAlasanBatal() {
        return alasanBatal;
    }

    /**
     * @param alasanBatal the alasanBatal to set
     */
    public void setAlasanBatal(String alasanBatal) {
        this.alasanBatal = alasanBatal;
    }

    /**
     * @return the noDokSpd
     */
    public String getNoDokSpd() {
        return noDokSpd;
    }

    /**
     * @param noDokSpd the noDokSpd to set
     */
    public void setNoDokSpd(String noDokSpd) {
        this.noDokSpd = noDokSpd;
    }

    /**
     * @return the tglSpd
     */
    public Date getTglSpd() {
        return tglSpd;
    }

    /**
     * @param tglSpd the tglSpd to set
     */
    public void setTglSpd(Date tglSpd) {
        this.tglSpd = tglSpd;
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
     * @return the noSpd
     */
    public Integer getNoSpd() {
        return noSpd;
    }

    /**
     * @param noSpd the noSpd to set
     */
    public void setNoSpd(Integer noSpd) {
        this.noSpd = noSpd;
    }

    /**
     * @return the v_spd
     */
    public BigDecimal getV_spd() {
        return v_spd;
    }

    /**
     * @param v_spd the v_spd to set
     */
    public void setV_spd(BigDecimal v_spd) {
        this.v_spd = v_spd;
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
    
}


