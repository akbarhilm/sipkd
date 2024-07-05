/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.model;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author erzy
 */
public class Bankrek extends BaseModel {

    private Integer idBankrek;
    @NotNull(message = "Kode Bank Wajib diisi ")
    @NotEmpty(message = "Kode Bank tidak boleh kosong ")
    private String kodeBankrek;

    @NotNull(message = "Nama Bank Wajib diisi ")
    @NotEmpty(message = "Nama Bank tidak boleh kosong ")
    private String namaBankrek;
    @NotNull(message = "BANK STS Wajib diisi ")
    @NotEmpty(message = "BANK STS tidak boleh kosong ")
    private String idBANKSTS;
    @NotNull(message = "BANK SPM Wajib diisi ")
    @NotEmpty(message = "BANK STS tidak boleh kosong ")
    private String idBANKSPM;

    @NotNull(message = "Tahun Berlaku tidak boleh kosong ")
    private Integer kodeThnBerlaku;
    @NotNull(message = "Tahun Berakhir tidak boleh kosong ")
    private Integer kodeThnBerakhir;

    // private String namaSkpd;
    private String isAktif;
   // private Integer idSkpd;
    // private String kodeSkpd;
    private Skpd skpd;

    /**
     * @return the idBankrek
     */
    public Integer getIdBankrek() {
        return idBankrek;
    }

    /**
     * @param idBankrek the idBankrek to set
     */
    public void setIdBankrek(Integer idBankrek) {
        this.idBankrek = idBankrek;
    }

    /**
     * @return the kodeBankrek
     */
    public String getKodeBankrek() {
        return kodeBankrek;
    }

    /**
     * @param kodeBankrek the kodeBankrek to set
     */
    public void setKodeBankrek(String kodeBankrek) {
        this.kodeBankrek = kodeBankrek;
    }

    /**
     * @return the namaBankrek
     */
    public String getNamaBankrek() {
        return namaBankrek;
    }

    /**
     * @param namaBankrek the namaBankrek to set
     */
    public void setNamaBankrek(String namaBankrek) {
        this.namaBankrek = namaBankrek;
    }

    /**
     * @return the idBANKSTS
     */
    public String getIdBANKSTS() {
        return idBANKSTS;
    }

    /**
     * @param idBANKSTS the idBANKSTS to set
     */
    public void setIdBANKSTS(String idBANKSTS) {
        this.idBANKSTS = idBANKSTS;
    }

    /**
     * @return the idBANKSPM
     */
    public String getIdBANKSPM() {
        return idBANKSPM;
    }

    /**
     * @param idBANKSPM the idBANKSPM to set
     */
    public void setIdBANKSPM(String idBANKSPM) {
        this.idBANKSPM = idBANKSPM;
    }

    /**
     * @return the kodeThnBerlaku
     */
    public Integer getKodeThnBerlaku() {
        return kodeThnBerlaku;
    }

    /**
     * @param kodeThnBerlaku the kodeThnBerlaku to set
     */
    public void setKodeThnBerlaku(Integer kodeThnBerlaku) {
        this.kodeThnBerlaku = kodeThnBerlaku;
    }

    /**
     * @return the kodeThnBerakhir
     */
    public Integer getKodeThnBerakhir() {
        return kodeThnBerakhir;
    }

    /**
     * @param kodeThnBerakhir the kodeThnBerakhir to set
     */
    public void setKodeThnBerakhir(Integer kodeThnBerakhir) {
        this.kodeThnBerakhir = kodeThnBerakhir;
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

}
