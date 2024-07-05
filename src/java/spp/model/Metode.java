/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spp.model;


public class Metode extends BaseModel {
    private Integer kodeMetodeGroup;
    private String kodeMetode;
    private String ketMetode;

    /**
     * @return the kodeMetodeGroup
     */
    public Integer getKodeMetodeGroup() {
        return kodeMetodeGroup;
    }

    /**
     * @param kodeMetodeGroup the kodeMetodeGroup to set
     */
    public void setKodeMetodeGroup(Integer kodeMetodeGroup) {
        this.kodeMetodeGroup = kodeMetodeGroup;
    }

    /**
     * @return the kodeMetode
     */
    public String getKodeMetode() {
        return kodeMetode;
    }

    /**
     * @param kodeMetode the kodeMetode to set
     */
    public void setKodeMetode(String kodeMetode) {
        this.kodeMetode = kodeMetode;
    }

    /**
     * @return the ketMetode
     */
    public String getKetMetode() {
        return ketMetode;
    }

    /**
     * @param ketMetode the ketMetode to set
     */
    public void setKetMetode(String ketMetode) {
        this.ketMetode = ketMetode;
    }
    
}
