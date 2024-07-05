 
package sipkd.model;


import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import sipkd.model.BaseModel;

public class Dokttd extends BaseModel {
    
    private Integer id;
    private String kodeDok;
    private String idNip;
    private String idNrk;
    private String namaPegawai;
    private String jabatan;
    private String nilaiMin;
    private String nilaiMax;
    private String isAktif;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getKodeDok() {
        return kodeDok;
    }

    public void setKodeDok(String kodeDok) {
        this.kodeDok = kodeDok;
    }
    
    public String getIdNip() {
        return idNip;
    }

    public void setIdNip(String idNip) {
        this.idNip = idNip;
    }
    
    public String getIdNrk() {
        return idNrk;
    }

    public void setIdNrk(String idNrk) {
        this.idNrk = idNrk;
    }
    
    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }
    
    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }
    
    public String getNilaiMin() {
        return nilaiMin;
    }

    public void setNilaiMin(String nilaiMin) {
        this.nilaiMin = nilaiMin;
    }
    
    public String getNilaiMax() {
        return nilaiMax;
    }

    public void setNilaiMax(String nilaiMax) {
        this.nilaiMax = nilaiMax;
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
    
}
