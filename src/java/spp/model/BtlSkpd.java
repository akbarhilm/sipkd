/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.model;

import java.util.List;

/**
 *
 * @author User
 */
public class BtlSkpd extends BaseModel{
    private Skpd skpd;
    private List<AnggaranBtlSkpd> listAnggaranBtlSkpd;

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
     * @return the listAnggaranBtlSkpd
     */
    public List<AnggaranBtlSkpd> getListAnggaranBtlSkpd() {
        return listAnggaranBtlSkpd;
    }

    /**
     * @param listAnggaranBtlSkpd the listAnggaranBtlSkpd to set
     */
    public void setListAnggaranBtlSkpd(List<AnggaranBtlSkpd> listAnggaranBtlSkpd) {
        this.listAnggaranBtlSkpd = listAnggaranBtlSkpd;
    }
}
