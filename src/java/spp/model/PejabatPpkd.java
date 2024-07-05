/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.model;

import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
public class PejabatPpkd extends BaseModel {

    @NotNull(message = "Pejabat Wajib di isi") 
    private Integer idPejabatPPKD;
    private String isAktif;
    private String nip;
    private String nrk;
    private String namaPegawai;
    private String namaJabatan;

    /**
     * @return the idPejabatPPKD
     */
    public Integer getIdPejabatPPKD() {
        return idPejabatPPKD;
    }

    /**
     * @param idPejabatPPKD the idPejabatPPKD to set
     */
    public void setIdPejabatPPKD(Integer idPejabatPPKD) {
        this.idPejabatPPKD = idPejabatPPKD;
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
     * @return the nip
     */
    public String getNip() {
        return nip;
    }

    /**
     * @param nip the nip to set
     */
    public void setNip(String nip) {
        this.nip = nip;
    }

    /**
     * @return the nrk
     */
    public String getNrk() {
        return nrk;
    }

    /**
     * @param nrk the nrk to set
     */
    public void setNrk(String nrk) {
        this.nrk = nrk;
    }

    /**
     * @return the namaPegawai
     */
    public String getNamaPegawai() {
        return namaPegawai;
    }

    /**
     * @param namaPegawai the namaPegawai to set
     */
    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    /**
     * @return the namaJabatan
     */
    public String getNamaJabatan() {
        return namaJabatan;
    }

    /**
     * @param namaJabatan the namaJabatan to set
     */
    public void setNamaJabatan(String namaJabatan) {
        this.namaJabatan = namaJabatan;
    }
}
