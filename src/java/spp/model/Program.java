/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spp.model;

import java.sql.Timestamp;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author maman sulaeman
 */
public class Program extends BaseModel{
    
    private Integer idUrusan;
    private Integer idProgram;
    @NotNull(message = "Kode Program Wajib diisi ")
    @NotEmpty(message = "Kode Program tidak boleh kosong ")
    private String kodeProgram;
    @NotNull(message = "Nama Program  Wajib diisi ")
    @NotEmpty(message = "Nama Program  tidak boleh kosong ")
    // @Length(min = 5, message = "Nama Program harus lebih dari 4 karakter ")
    private String namaProgram;
    private String kodeUrusan;
    private String namaUrusan;
    private String isAktif;
    private Urusan urusan;
    public Integer getIdProgram(){
        return idProgram;
    }
      public void setIdProgram(Integer idProgram) {
        this.idProgram = idProgram;
    }
     public Integer getIdUrusan(){
        return idUrusan;
    }
      public void setIdUrusan(Integer idUrusan) {
        this.idUrusan = idUrusan;
    }
      
    public String getKodeProgram() {
        return kodeProgram;
    }

    public void setKodeProgram(String kodeProgram) {
        this.kodeProgram = kodeProgram;
    }
    
    public String getNamaProgram() {
        return namaProgram;
    }

    public void setNamaProgram(String namaProgram) {
        this.namaProgram = namaProgram;
    }
    
    
    public String getKodeUrusan() {
        return namaProgram;
    }

    public void setKodeUrusan(String kodeUrusan) {
        this.kodeUrusan = kodeUrusan;
    }
      public String getNamaUrusan() {
        return namaUrusan;
    }

    public void setNamaUrusan(String kodeUrusan) {
        this.namaUrusan = namaUrusan;
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
    public Urusan getUrusan() {
        return urusan;
    }

    /**
     * @param skpd the skpd to set
     */
    public void setUrusan(Urusan urusan) {
        this.urusan = urusan;
    }
   

 
    
    
}
