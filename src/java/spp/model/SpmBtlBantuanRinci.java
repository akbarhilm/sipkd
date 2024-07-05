/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
/**
 *
 * @author Xalamaster
 */
public class SpmBtlBantuanRinci extends BaseModel  {
    private Integer idspm;
    private Integer idspp;
    private Integer sppNo;
    private String tglSpp;
    private String nipPptk;
    private String nPptk;
    private BigDecimal nilaiSpp;
    private String cAkun;
    private String nAkun;

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
     * @return the idspp
     */
    public Integer getIdspp() {
        return idspp;
    }

    /**
     * @param idspp the idspp to set
     */
    public void setIdspp(Integer idspp) {
        this.idspp = idspp;
    }

    /**
     * @return the sppNo
     */
    public Integer getSppNo() {
        return sppNo;
    }

    /**
     * @param sppNo the sppNo to set
     */
    public void setSppNo(Integer sppNo) {
        this.sppNo = sppNo;
    }

    /**
     * @return the tglSpp
     */


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
     * @return the nPptk
     */
    public String getnPptk() {
        return nPptk;
    }

    /**
     * @param nPptk the nPptk to set
     */
    public void setnPptk(String nPptk) {
        this.nPptk = nPptk;
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
     * @return the tglSpp
     */
    public String getTglSpp() {
        return tglSpp;
    }

    /**
     * @param tglSpp the tglSpp to set
     */
    public void setTglSpp(String tglSpp) {
        this.tglSpp = tglSpp;
    }

    /**
     * @return the cAkun
     */
    public String getcAkun() {
        return cAkun;
    }

    /**
     * @param cAkun the cAkun to set
     */
    public void setcAkun(String cAkun) {
        this.cAkun = cAkun;
    }

    /**
     * @return the nAkun
     */
    public String getnAkun() {
        return nAkun;
    }

    /**
     * @param nAkun the nAkun to set
     */
    public void setnAkun(String nAkun) {
        this.nAkun = nAkun;
    }


}
