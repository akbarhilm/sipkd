/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spp.model;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Urusan extends BaseModel {
    
    private Integer idUrusan;
    private String namaUrusan;
    private String kodeUrusan;
    private String isAktif;
    private String noUrusan;
    private String kodeWajib;
    private Fungsi fungsi;

    /**
     * @return the idUrusan
     */
    public Integer getIdUrusan() {
        return idUrusan;
    }

    /**
     * @param idUrusan the idUrusan to set
     */
    public void setIdUrusan(Integer idUrusan) {
        this.idUrusan = idUrusan;
    }

    /**
     * @return the namaUrusan
     */
    public String getNamaUrusan() {
        return namaUrusan;
    }

    /**
     * @param namaUrusan the namaUrusan to set
     */
    public void setNamaUrusan(String namaUrusan) {
        this.namaUrusan = namaUrusan;
    }

    /**
     * @return the kodeUrusan
     */
    public String getKodeUrusan() {
        return kodeUrusan;
    }

    /**
     * @param kodeUrusan the kodeUrusan to set
     */
    public void setKodeUrusan(String kodeUrusan) {
        this.kodeUrusan = kodeUrusan;
    }

    /**
     * @return the isAktif
     */
    public String getIsAktif() {
        return isAktif;
    }

    /**
     * @param isAktif the isAktif to set
     */
    public void setIsAktif(String isAktif) {
        this.isAktif = isAktif;
    }

    /**
     * @return the fungsi
     */
    public Fungsi getFungsi() {
        return fungsi;
    }

    /**
     * @param fungsi the fungsi to set
     */
    public void setFungsi(Fungsi fungsi) {
        this.fungsi = fungsi;
    }

    /**
     * @return the noUrusan
     */
    public String getNoUrusan() {
        return noUrusan;
    }

    /**
     * @param noUrusan the noUrusan to set
     */
    public void setNoUrusan(String noUrusan) {
        this.noUrusan = noUrusan;
    }

    /**
     * @return the kodeWajib
     */
    public String getKodeWajib() {
        return kodeWajib;
    }

    /**
     * @param kodeWajib the kodeWajib to set
     */
    public void setKodeWajib(String kodeWajib) {
        this.kodeWajib = kodeWajib;
    }
    
}
