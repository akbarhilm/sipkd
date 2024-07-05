package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class SppBtlGaji extends BaseModel {

    private Integer idskpd;
    @NotNull
    private Integer idList;
    private String bulanRekap;
    private String tglPublish;
    private String macamGaji;
    private String jenisGaji;
    private BigDecimal jumKotor;
    private String uraianGaji;
    private String kodeBulan;
    private String bulanPosting;
    
    
    
    /**
     * @return the idskpd
     */
    public Integer getIdskpd() {
        return idskpd;
    }

    /**
     * @param idskpd the idskpd to set
     */
    public void setIdskpd(Integer idskpd) {
        this.idskpd = idskpd;
    }

    /**
     * @return the idList
     */
    public Integer getIdList() {
        return idList;
    }

    /**
     * @param idList the idList to set
     */
    public void setIdList(Integer idList) {
        this.idList = idList;
    }

    /**
     * @return the bulanRekap
     */
    public String getBulanRekap() {
        return bulanRekap;
    }

    /**
     * @param bulanRekap the bulanRekap to set
     */
    public void setBulanRekap(String bulanRekap) {
        this.bulanRekap = bulanRekap;
    }

    /**
     * @return the macamGaji
     */
    public String getMacamGaji() {
        return macamGaji;
    }

    /**
     * @param macamGaji the macamGaji to set
     */
    public void setMacamGaji(String macamGaji) {
        this.macamGaji = macamGaji;
    }

    /**
     * @return the jenisGaji
     */
    public String getJenisGaji() {
        return jenisGaji;
    }

    /**
     * @param jenisGaji the jenisGaji to set
     */
    public void setJenisGaji(String jenisGaji) {
        this.jenisGaji = jenisGaji;
    }

    /**
     * @return the jumKotor
     */
    public BigDecimal getJumKotor() {
        return jumKotor;
    }

    /**
     * @param jumKotor the jumKotor to set
     */
    public void setJumKotor(BigDecimal jumKotor) {
        this.jumKotor = jumKotor;
    }

    /**
     * @return the uraianGaji
     */
    public String getUraianGaji() {
        return uraianGaji;
    }

    /**
     * @param uraianGaji the uraianGaji to set
     */
    public void setUraianGaji(String uraianGaji) {
        this.uraianGaji = uraianGaji;
    }

    /**
     * @return the tglPublish
     */
    public String getTglPublish() {
        return tglPublish;
    }

    /**
     * @param tglPublish the tglPublish to set
     */
    public void setTglPublish(String tglPublish) {
        this.tglPublish = tglPublish;
    }

    /**
     * @return the kodeBulan
     */
    public String getKodeBulan() {
        return kodeBulan;
    }

    /**
     * @param kodeBulan the kodeBulan to set
     */
    public void setKodeBulan(String kodeBulan) {
        this.kodeBulan = kodeBulan;
    }

    /**
     * @return the bulanPosting
     */
    public String getBulanPosting() {
        return bulanPosting;
    }

    /**
     * @param bulanPosting the bulanPosting to set
     */
    public void setBulanPosting(String bulanPosting) {
        this.bulanPosting = bulanPosting;
    }

    
    
}
