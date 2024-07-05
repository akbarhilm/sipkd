/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.model;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import spp.model.BaseModel;

/**
 *
 * @author maman sulaeman
 */
public class Bank extends BaseModel {

    private Integer idBank;
    @NotNull(message = "Kode Bank Wajib diisi ")
    @NotEmpty(message = "Kode Bank tidak boleh kosong ")
    private String kodeBank;
    @NotNull(message = "Nama Bank Wajib diisi ")
    @NotEmpty(message = "Nama Bank tidak boleh kosong ")
    private String namaBank;
    @NotNull(message = "Rekening Bank Wajib diisi ")
    @NotEmpty(message = "Rekening Bank tidak boleh kosong ")
    private String rekeningBank;
    private String isAktif;

    public String getKodeBank() {
        return kodeBank;
    }

    public void setKodeBank(String kodeBank) {
        this.kodeBank = kodeBank;
    }

    public Integer getIdBank() {
        return idBank;
    }

    public void setIdBank(Integer idBank) {
        this.idBank = idBank;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    public String getRekeningBank() {
        return rekeningBank;
    }

    public void setRekeningBank(String rekeningBank) {
        this.rekeningBank = rekeningBank;
    }

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
     * @return the id
     */
}
