/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spp.model;

import java.math.BigDecimal;
import java.util.List;

public class SpjRinci extends BaseModel{
    private Integer idSpjRinci;
    private Integer idBl;
    private Integer idSpj;
    private Integer noSpd;
    private String tahun;
    private String kodeBeban;
    private Akun akun;
    private Kegiatan kegiatan;
    private BigDecimal nilai_UPGUTU;
    private BigDecimal nilai_SPD;
    private BigDecimal nilai_LS;
    private BigDecimal nilai_TU;
    private BigDecimal sudah_spj;
    private BigDecimal SISA_spj;
    private BigDecimal nilai_spj;
    

    
    /**
     * @return the idBl
     */
    public Integer getIdBl() {
        return idBl;
    }

    /**
     * @param idBl the idBl to set
     */
    public void setIdBl(Integer idBl) {
        this.idBl = idBl;
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
     * @return the akun
     */
    public Akun getAkun() {
        return akun;
    }

    /**
     * @param akun the akun to set
     */
    public void setAkun(Akun akun) {
        this.akun = akun;
    }

    /**
     * @return the kegiatan
     */
    public Kegiatan getKegiatan() {
        return kegiatan;
    }

    /**
     * @param kegiatan the kegiatan to set
     */
    public void setKegiatan(Kegiatan kegiatan) {
        this.kegiatan = kegiatan;
    }

    /**
     * @return the nilai_UPGUTU
     */
    public BigDecimal getNilai_UPGUTU() {
        return nilai_UPGUTU;
    }

    /**
     * @param nilai_UPGUTU the nilai_UPGUTU to set
     */
    public void setNilai_UPGUTU(BigDecimal nilai_UPGUTU) {
        this.nilai_UPGUTU = nilai_UPGUTU;
    }

    /**
     * @return the sudah_spj
     */
    public BigDecimal getSudah_spj() {
        return sudah_spj;
    }

    /**
     * @param sudah_spj the sudah_spj to set
     */
    public void setSudah_spj(BigDecimal sudah_spj) {
        this.sudah_spj = sudah_spj;
    }

    /**
     * @return the SISA_spj
     */
    public BigDecimal getSISA_spj() {
        return SISA_spj;
    }

    /**
     * @param SISA_spj the SISA_spj to set
     */
    public void setSISA_spj(BigDecimal SISA_spj) {
        this.SISA_spj = SISA_spj;
    }

    /**
     * @return the nilai_spj
     */
    public BigDecimal getNilai_spj() {
        return nilai_spj;
    }

    /**
     * @param nilai_spj the nilai_spj to set
     */
    public void setNilai_spj(BigDecimal nilai_spj) {
        this.nilai_spj = nilai_spj;
    }

    /**
     * @return the idSpj
     */
    public Integer getIdSpj() {
        return idSpj;
    }

    /**
     * @param idSpj the idSpj to set
     */
    public void setIdSpj(Integer idSpj) {
        this.idSpj = idSpj;
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
     * @return the nilai_SPD
     */
    public BigDecimal getNilai_SPD() {
        return nilai_SPD;
    }

    /**
     * @param nilai_SPD the nilai_SPD to set
     */
    public void setNilai_SPD(BigDecimal nilai_SPD) {
        this.nilai_SPD = nilai_SPD;
    }

    /**
     * @return the nilai_LS
     */
    public BigDecimal getNilai_LS() {
        return nilai_LS;
    }

    /**
     * @param nilai_LS the nilai_LS to set
     */
    public void setNilai_LS(BigDecimal nilai_LS) {
        this.nilai_LS = nilai_LS;
    }

    /**
     * @return the nilai_TU
     */
    public BigDecimal getNilai_TU() {
        return nilai_TU;
    }

    /**
     * @param nilai_TU the nilai_TU to set
     */
    public void setNilai_TU(BigDecimal nilai_TU) {
        this.nilai_TU = nilai_TU;
    }

    /**
     * @return the idSpjRinci
     */
    public Integer getIdSpjRinci() {
        return idSpjRinci;
    }

    /**
     * @param idSpjRinci the idSpjRinci to set
     */
    public void setIdSpjRinci(Integer idSpjRinci) {
        this.idSpjRinci = idSpjRinci;
    }
}
