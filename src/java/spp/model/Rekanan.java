/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spp.model;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Rekanan extends BaseModel{
   private Integer idRekanan;
    @NotNull(message = "Kode Rekanan Wajib diisi ")
    @NotEmpty(message = "Kode Rekanan tidak boleh kosong ") 
     private String kodeRekanan;
    @NotNull(message = "Nama Rekanan Wajib diisi ")
    @NotEmpty(message = "Nama Rekanan tidak boleh kosong ")
    
    private String namaRekanan;
    @NotNull(message = "Nama Direktur Rekanan Wajib diisi ")
    @NotEmpty(message = "Nama Direktur Rekanan tidak boleh kosong ")
    
    private String namaDirekturRekanan;
    @NotNull(message = "Id Rekanan Wajib diisi ")
    @NotEmpty(message = "Id Rekanan tidak boleh kosong ")
     private String idRekananAkte;
    @NotNull(message = "NPWP Rekanan Wajib diisi ")
    @NotEmpty(message = "NPWP Rekanan tidak boleh kosong ")
    private String idRekananNpwp;
    @NotNull(message = "Alamat Rekanan Wajib diisi ")
    @NotEmpty(message = "Alamat Rekanan tidak boleh kosong ")
    private String alamatRekanan;
    private Date tanggalRekananAkte;
     
    
    public Integer getIdRekanan() {
        return idRekanan;
    }

    public void setIdRekanan(Integer idRekanan) {
        this.idRekanan = idRekanan;
    }
    
    public String getKodeRekanan() {
        return kodeRekanan;
    }

    public void setKodeRekanan(String kodeRekanan) {
        this.kodeRekanan = kodeRekanan;
    }
     public String getNamaRekanan() {
        return namaRekanan;
    }

    public void setNamaRekanan(String namaRekanan) {
        this.namaRekanan = namaRekanan;
    }
    
    public String getNamaDirekturRekanan() {
        return namaDirekturRekanan;
    }

    public void setNamaDirekturRekanan(String namaDirekturRekanan) {
        this.namaDirekturRekanan = namaDirekturRekanan;
    }
    
     public String getIdRekananAkte() {
        return idRekananAkte;
    }

    public void setidRekananAkte(String idRekananAkte) {
        this.idRekananAkte = idRekananAkte;
    }          
    
    public String getIdRekananNpwp() {
        return idRekananNpwp;
    }

    public void setidRekananNpwp(String idRekananNpwp) {
        this.idRekananNpwp = idRekananNpwp;
    }      
    
     public String getAlamatRekanan() {
        return alamatRekanan;
    }

    public void setAlamatRekanan(String alamatRekanan) {
        this.alamatRekanan = alamatRekanan;
    }

    /**
     * @return the tanggalRekananAkte
     */
    public Date getTanggalRekananAkte() {
        return tanggalRekananAkte;
    }

    /**
     * @param tanggalRekananAkte the tanggalRekananAkte to set
     */
    public void setTanggalRekananAkte(Date tanggalRekananAkte) {
        this.tanggalRekananAkte = tanggalRekananAkte;
    }
    
             
             
}
