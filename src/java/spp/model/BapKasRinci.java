package spp.model;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Anita
 */
public class BapKasRinci extends BaseModel{
   
    private String tahun;
    private Skpd skpd; 
    
    /*BAP KAS RINCI*/
    private BigDecimal nilaiBapKas; 
    private String namaBapKas;
    
    private Integer idSkpdBAPKas;
    private Integer idSkpdBAPKasRinci;
    
    private String noBap;
    
       private String noUrut;


    /**
     * @return the tahun
     */
    public String getTahun() {
        return tahun;
    }

    /**
     * @param tahun the tahun to set
     */
    public void setTahun(String tahun) {
        this.tahun = tahun;
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

    /**
     * @return the nilaiBapKas
     */
    public BigDecimal getNilaiBapKas() {
        return nilaiBapKas;
    }

    /**
     * @param nilaiBapKas the nilaiBapKas to set
     */
    public void setNilaiBapKas(BigDecimal nilaiBapKas) {
        this.nilaiBapKas = nilaiBapKas;
    }

    /**
     * @return the namaBapKas
     */
    public String getNamaBapKas() {
        return namaBapKas;
    }

    /**
     * @param namaBapKas the namaBapKas to set
     */
    public void setNamaBapKas(String namaBapKas) {
        this.namaBapKas = namaBapKas;
    }

    /**
     * @return the idSkpdBAPKas
     */
    public Integer getIdSkpdBAPKas() {
        return idSkpdBAPKas;
    }

    /**
     * @param idSkpdBAPKas the idSkpdBAPKas to set
     */
    public void setIdSkpdBAPKas(Integer idSkpdBAPKas) {
        this.idSkpdBAPKas = idSkpdBAPKas;
    }

    /**
     * @return the idSkpdBAPKasRinci
     */
    public Integer getIdSkpdBAPKasRinci() {
        return idSkpdBAPKasRinci;
    }

    /**
     * @param idSkpdBAPKasRinci the idSkpdBAPKasRinci to set
     */
    public void setIdSkpdBAPKasRinci(Integer idSkpdBAPKasRinci) {
        this.idSkpdBAPKasRinci = idSkpdBAPKasRinci;
    }

    /**
     * @return the noBap
     */
    public String getNoBap() {
        return noBap;
    }

    /**
     * @param noBap the noBap to set
     */
    public void setNoBap(String noBap) {
        this.noBap = noBap;
    }

    /**
     * @return the noUrut
     */
    public String getNoUrut() {
        return noUrut;
    }

    /**
     * @param noUrut the noUrut to set
     */
    public void setNoUrut(String noUrut) {
        this.noUrut = noUrut;
    }
     

}
