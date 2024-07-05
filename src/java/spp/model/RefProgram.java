/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.model;

/**
 *
 * @author R Tarman
 */
public class RefProgram extends BaseModel {

    private Integer idRefProgram;
    private String kodeProgram;
    private String kodeUrusan;
    private String namaProgram;

    /**
     * @return the idRefProgram
     */
    public Integer getIdRefProgram() {
        return idRefProgram;
    }

    /**
     * @param idRefProgram the idRefProgram to set
     */
    public void setIdRefProgram(Integer idRefProgram) {
        this.idRefProgram = idRefProgram;
    }

    /**
     * @return the kodeProgram
     */
    public String getKodeProgram() {
        return kodeProgram;
    }

    /**
     * @param kodeProgram the kodeProgram to set
     */
    public void setKodeProgram(String kodeProgram) {
        this.kodeProgram = kodeProgram;
    }

    /**
     * @return the namaProgram
     */
    public String getNamaProgram() {
        return namaProgram;
    }

    /**
     * @param namaProgram the namaProgram to set
     */
    public void setNamaProgram(String namaProgram) {
        this.namaProgram = namaProgram;
    }

    /**
     * @return the kodeUrusan
     */
    public String getKodeUrusan() {
        return kodeUrusan;
    }

    /**
     * @param kodeUrusan the kodeUrusan to set
     */
    public void setKodeUrusan(String kodeUrusan) {
        this.kodeUrusan = kodeUrusan;
    }
}
