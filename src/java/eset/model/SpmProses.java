
package eset.model;


public class SpmProses extends BaseModel {

    private Integer id;
    private String kodeBeban;
    private Integer kodeJenis;
    private String batasWaktu;

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
     * @return the kodeBeban
     */
    public String getKodeBeban() {
        return kodeBeban;
    }

    /**
     * @param kodeBeban the kodeBeban to set
     */
    public void setKodeBeban(String kodeBeban) {
        this.kodeBeban = kodeBeban;
    }

    /**
     * @return the kodeJenis
     */
    public Integer getKodeJenis() {
        return kodeJenis;
    }

    /**
     * @param kodeJenis the kodeJenis to set
     */
    public void setKodeJenis(Integer kodeJenis) {
        this.kodeJenis = kodeJenis;
    }

    /**
     * @return the batasWaktu
     */
    public String getBatasWaktu() {
        return batasWaktu;
    }

    /**
     * @param batasWaktu the batasWaktu to set
     */
    public void setBatasWaktu(String batasWaktu) {
        this.batasWaktu = batasWaktu;
    }
}
