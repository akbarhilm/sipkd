 
package spp.model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class HariKerja extends BaseModel {

    private Date tglRekam;
    private Date tglApprove;
    private Date tglCetak;
    private Date tglSah;

    /**
     * @return the tglRekam
     */
    public Date getTglRekam() {
        return tglRekam;
    }

    /**
     * @param tglRekam the tglRekam to set
     */
    public void setTglRekam(Date tglRekam) {
        this.tglRekam = tglRekam;
    }

    /**
     * @return the tglApprove
     */
    public Date getTglApprove() {
        return tglApprove;
    }

    /**
     * @param tglApprove the tglApprove to set
     */
    public void setTglApprove(Date tglApprove) {
        this.tglApprove = tglApprove;
    }

    /**
     * @return the tglCetak
     */
    public Date getTglCetak() {
        return tglCetak;
    }

    /**
     * @param tglCetak the tglCetak to set
     */
    public void setTglCetak(Date tglCetak) {
        this.tglCetak = tglCetak;
    }

    /**
     * @return the tglSah
     */
    public Date getTglSah() {
        return tglSah;
    }

    /**
     * @param tglSah the tglSah to set
     */
    public void setTglSah(Date tglSah) {
        this.tglSah = tglSah;
    }

}
