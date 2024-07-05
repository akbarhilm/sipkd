/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spp.model;

import java.sql.Date;

public class SpdAndroid extends BaseModel {
    private Integer idSpd;
    private String tahunAnggaran;
    private String idSpdNo;
    private String kodeJenis;
    private String kodeBulanAwal;
    private String kodeBulanAkhir;
    private String ketSpd;
    private Date tanggalSpd;
    
   //BATAS SPD ANDROID
    
   private Integer idSkpd;
//    private String kodeSkpd;
 //   private Integer idInduk;
    private String namaSkpd;
    private String namaSkpdPendek;
   /* private String isAktif;
    private String levelSkpd;
    private String kodeKomisi;
    private String kodeAsisten;
    private String isNeraca;
    private String isPendapatan;
    private String kodeKecamatan;
    private String kodeKelurahan;
    private String tahunBerakhir;
    private String tahunBerlaku;
    private String kodeUnit;
    private String namaUnit;
*/

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

    /**
     * @return the namaSkpdPendek
     */
    public String getNamaSkpdPendek() {
        return namaSkpdPendek;
    }

    /**
     * @param namaSkpdPendek the namaSkpdPendek to set
     */
    public void setNamaSkpdPendek(String namaSkpdPendek) {
        this.namaSkpdPendek = namaSkpdPendek;
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
