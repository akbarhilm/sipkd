package spp.model;

/**
 *
 * @author Admin
 */
public class Bendahara extends BaseModel {

    private Integer idBendahara;
    private Skpd skpd;
    private String nip;
    private String nrk;
    private String nama;
    private String namaJabatan;
    private Integer isAktif;

    /**
     * @return the idBendahara
     */
    public Integer getIdBendahara() {
        return idBendahara;
    }

    /**
     * @param idBendahara the idBendahara to set
     */
    public void setIdBendahara(Integer idBendahara) {
        this.idBendahara = idBendahara;
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
     * @return the nip
     */
    public String getNip() {
        return nip;
    }

    /**
     * @param nip the nip to set
     */
    public void setNip(String nip) {
        this.nip = nip;
    }

    /**
     * @return the nrk
     */
    public String getNrk() {
        return nrk;
    }

    /**
     * @param nrk the nrk to set
     */
    public void setNrk(String nrk) {
        this.nrk = nrk;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the namaJabatan
     */
    public String getNamaJabatan() {
        return namaJabatan;
    }

    /**
     * @param namaJabatan the namaJabatan to set
     */
    public void setNamaJabatan(String namaJabatan) {
        this.namaJabatan = namaJabatan;
    }

    /**
     * @return the isAktif
     */
    public Integer getIsAktif() {
        return isAktif;
    }

    /**
     * @param isAktif the isAktif to set
     */
    public void setIsAktif(Integer isAktif) {
        this.isAktif = isAktif;
    }

     

}
