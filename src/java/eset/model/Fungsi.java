/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eset.model;
public class Fungsi extends BaseModel{
    private Integer idFungsi;
    private String kodeFungsi;
    private String namaFungsi;
    private String isAktif;

    /**
     * @return the idFungsi
     */
    public Integer getIdFungsi() {
        return idFungsi;
    }

    /**
     * @param idFungsi the idFungsi to set
     */
    public void setIdFungsi(Integer idFungsi) {
        this.idFungsi = idFungsi;
    }

    /**
     * @return the kodeFungsi
     */
    public String getKodeFungsi() {
        return kodeFungsi;
    }

    /**
     * @param kodeFungsi the kodeFungsi to set
     */
    public void setKodeFungsi(String kodeFungsi) {
        this.kodeFungsi = kodeFungsi;
    }

    /**
     * @return the namaFungsi
     */
    public String getNamaFungsi() {
        return namaFungsi;
    }

    /**
     * @param namaFungsi the namaFungsi to set
     */
    public void setNamaFungsi(String namaFungsi) {
        this.namaFungsi = namaFungsi;
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
    
    
    
}
