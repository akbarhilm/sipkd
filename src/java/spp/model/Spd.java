/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spp.model;

import java.sql.Date;

public class Spd extends BaseModel {
    private Integer idSpd;
    private String tahunAnggaran;
    private String idSpdNo;
    private String kodeJenis;
    private Skpd skpd;
    private String kodeBulanAwal;
    private String kodeBulanAkhir;
    private String ketSpd;
    private Date tanggalSpd;
    private Integer idSkpd;
    

    /**
     * @return the idSpd
     */
    public Integer getIdSpd() {
        return idSpd;
    }

    /**
     * @param idSpd the idSpd to set
     */
    public void setIdSpd(Integer idSpd) {
        this.idSpd = idSpd;
    }

    /**
     * @return the tahunAnggaran
     */
    public String getTahunAnggaran() {
        return tahunAnggaran;
    }

    /**
     * @param tahunAnggaran the tahunAnggaran to set
     */
    public void setTahunAnggaran(String tahunAnggaran) {
        this.tahunAnggaran = tahunAnggaran;
    }

    /**
     * @return the idSpdNo
     */
    public String getIdSpdNo() {
        return idSpdNo;
    }

    /**
     * @param idSpdNo the idSpdNo to set
     */
    public void setIdSpdNo(String idSpdNo) {
        this.idSpdNo = idSpdNo;
    }

    /**
     * @return the kodeJenis
     */
    public String getKodeJenis() {
        return kodeJenis;
    }

    /**
     * @param kodeJenis the kodeJenis to set
     */
    public void setKodeJenis(String kodeJenis) {
        this.kodeJenis = kodeJenis;
    }

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
     * @return the kodeBulanAwal
     */
    public String getKodeBulanAwal() {
        return kodeBulanAwal;
    }

    /**
     * @param kodeBulanAwal the kodeBulanAwal to set
     */
    public void setKodeBulanAwal(String kodeBulanAwal) {
        this.kodeBulanAwal = kodeBulanAwal;
    }

    /**
     * @return the kodeBulanAkhir
     */
    public String getKodeBulanAkhir() {
        return kodeBulanAkhir;
    }

    /**
     * @param kodeBulanAkhir the kodeBulanAkhir to set
     */
    public void setKodeBulanAkhir(String kodeBulanAkhir) {
        this.kodeBulanAkhir = kodeBulanAkhir;
    }

    /**
     * @return the ketSpd
     */
    public String getKetSpd() {
        return ketSpd;
    }

    /**
     * @param ketSpd the ketSpd to set
     */
    public void setKetSpd(String ketSpd) {
        this.ketSpd = ketSpd;
    }

    /**
     * @return the tanggalSpd
     */
    public Date getTanggalSpd() {
        return tanggalSpd;
    }

    /**
     * @param tanggalSpd the tanggalSpd to set
     */
    public void setTanggalSpd(Date tanggalSpd) {
        this.tanggalSpd = tanggalSpd;
    }

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

}
