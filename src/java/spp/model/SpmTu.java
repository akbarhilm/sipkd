package spp.model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class SpmTu extends SppTu {

    private Integer idspm;
    private String noSpm;
    private Date tglSpm;
    private String keteranganSpm;
    private String noJurnal;
    private Date tglJurnal;
    private String noNpwp;
    private String kodeBank;
    private String namaBank;
    private String noRekeningSPM;
    private List<SpmTuRinci> spmTuRinci;

    /**
     * @return the idspm
     */
    public Integer getIdspm() {
        return idspm;
    }

    /**
     * @param idspm the idspm to set
     */
    public void setIdspm(Integer idspm) {
        this.idspm = idspm;
    }

    /**
     * @return the noSpm
     */
    public String getNoSpm() {
        return noSpm;
    }

    /**
     * @param noSpm the noSpm to set
     */
    public void setNoSpm(String noSpm) {
        this.noSpm = noSpm;
    }

    /**
     * @return the tglSpm
     */
    public Date getTglSpm() {
        return tglSpm;
    }

    /**
     * @param tglSpm the tglSpm to set
     */
    public void setTglSpm(Date tglSpm) {
        this.tglSpm = tglSpm;
    }

    /**
     * @return the keteranganSpm
     */
    public String getKeteranganSpm() {
        return keteranganSpm;
    }

    /**
     * @param keteranganSpm the keteranganSpm to set
     */
    public void setKeteranganSpm(String keteranganSpm) {
        this.keteranganSpm = keteranganSpm;
    }

    /**
     * @return the noJurnal
     */
    public String getNoJurnal() {
        return noJurnal;
    }

    /**
     * @param noJurnal the noJurnal to set
     */
    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    /**
     * @return the tglJurnal
     */
    public Date getTglJurnal() {
        return tglJurnal;
    }

    /**
     * @param tglJurnal the tglJurnal to set
     */
    public void setTglJurnal(Date tglJurnal) {
        this.tglJurnal = tglJurnal;
    }

    /**
     * @return the noNpwp
     */
    public String getNoNpwp() {
        return noNpwp;
    }

    /**
     * @param noNpwp the noNpwp to set
     */
    public void setNoNpwp(String noNpwp) {
        this.noNpwp = noNpwp;
    }

    /**
     * @return the kodeBank
     */
    public String getKodeBank() {
        return kodeBank;
    }

    /**
     * @param kodeBank the kodeBank to set
     */
    public void setKodeBank(String kodeBank) {
        this.kodeBank = kodeBank;
    }

    /**
     * @return the namaBank
     */
    public String getNamaBank() {
        return namaBank;
    }

    /**
     * @param namaBank the namaBank to set
     */
    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    /**
     * @return the noRekeningSPM
     */
    public String getNoRekeningSPM() {
        return noRekeningSPM;
    }

    /**
     * @param noRekeningSPM the noRekeningSPM to set
     */
    public void setNoRekeningSPM(String noRekeningSPM) {
        this.noRekeningSPM = noRekeningSPM;
    }

    /**
     * @return the spmTuRinci
     */
    public List<SpmTuRinci> getSpmTuRinci() {
        return spmTuRinci;
    }

    /**
     * @param spmTuRinci the spmTuRinci to set
     */
    public void setSpmTuRinci(List<SpmTuRinci> spmTuRinci) {
        this.spmTuRinci = spmTuRinci;
    }

}
