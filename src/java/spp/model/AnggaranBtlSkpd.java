package spp.model;

import java.math.BigDecimal;

/**
 *
 * @author User
 */
public class AnggaranBtlSkpd extends BaseModel{

    private Integer id;
    private Integer idAkun;
    private String kodeAkun;
    private String namaAkun;
    private String tahunSkpd;
    private BigDecimal nilaiAnggaran;
   

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the idAkun
     */
    public Integer getIdAkun() {
        return idAkun;
    }

    /**
     * @param idAkun the idAkun to set
     */
    public void setIdAkun(Integer idAkun) {
        this.idAkun = idAkun;
    }

    /**
     * @return the kodeAkun
     */
    public String getKodeAkun() {
        return kodeAkun;
    }

    /**
     * @param kodeAkun the kodeAkun to set
     */
    public void setKodeAkun(String kodeAkun) {
        this.kodeAkun = kodeAkun;
    }

    /**
     * @return the namaAkun
     */
    public String getNamaAkun() {
        return namaAkun;
    }

    /**
     * @param namaAkun the namaAkun to set
     */
    public void setNamaAkun(String namaAkun) {
        this.namaAkun = namaAkun;
    }

    /**
     * @return the nilaiAnggaran
     */
    public BigDecimal getNilaiAnggaran() {
        return nilaiAnggaran;
    }

    /**
     * @param nilaiAnggaran the nilaiAnggaran to set
     */
    public void setNilaiAnggaran(BigDecimal nilaiAnggaran) {
        this.nilaiAnggaran = nilaiAnggaran;
    }

    /**
     * @return the tahunSkpd
     */
    public String getTahunSkpd() {
        return tahunSkpd;
    }

    /**
     * @param tahunSkpd the tahunSkpd to set
     */
    public void setTahunSkpd(String tahunSkpd) {
        this.tahunSkpd = tahunSkpd;
    }

     
}
