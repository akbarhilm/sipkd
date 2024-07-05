package sipkd.model;

import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
public class Skpd extends BaseModel{
    @NotNull(message="SKPD Wajib di isi")
    private Integer idSkpd;
    private String kodeSkpd;
    private String namaSkpd; 
    private String levelSkpd; 

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

    /**
     * @return the kodeSkpd
     */
    public String getKodeSkpd() {
        return kodeSkpd;
    }

    /**
     * @param kodeSkpd the kodeSkpd to set
     */
    public void setKodeSkpd(String kodeSkpd) {
        this.kodeSkpd = kodeSkpd;
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
     * @return the levelSkpd
     */
    public String getLevelSkpd() {
        return levelSkpd;
    }

    /**
     * @param levelSkpd the levelSkpd to set
     */
    public void setLevelSkpd(String levelSkpd) {
        this.levelSkpd = levelSkpd;
    }

     
}
