package sipkd.model;

import java.util.List;

/**
 *
 * @author User
 */
public class Pengguna {

    private Integer idPengguna;
    private String namaPengguna;
    private boolean isAktif;
    private String passPengguna;
    private String nip;
    private String ipAddress;
    private String kodeGrup;
    private String nrk;
    private String kodeProses;
    private List<Skpd> skpd;

    /**
     * @return the idPengguna
     */
    public Integer getIdPengguna() {
        return idPengguna;
    }

    /**
     * @param idPengguna the idPengguna to set
     */
    public void setIdPengguna(Integer idPengguna) {
        this.idPengguna = idPengguna;
    }

    /**
     * @return the namaPengguna
     */
    public String getNamaPengguna() {
        return namaPengguna;
    }

    /**
     * @param namaPengguna the namaPengguna to set
     */
    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    /**
     * @return the isAktif
     */
    public boolean isIsAktif() {
        return isAktif;
    }

    /**
     * @param isAktif the isAktif to set
     */
    public void setIsAktif(boolean isAktif) {
        this.isAktif = isAktif;
    }

    /**
     * @return the passPengguna
     */
    public String getPassPengguna() {
        return passPengguna;
    }

    /**
     * @param passPengguna the passPengguna to set
     */
    public void setPassPengguna(String passPengguna) {
        this.passPengguna = passPengguna;
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
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * @return the kodeGrup
     */
    public String getKodeGrup() {
        return kodeGrup;
    }

    /**
     * @param kodeGrup the kodeGrup to set
     */
    public void setKodeGrup(String kodeGrup) {
        this.kodeGrup = kodeGrup;
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
     * @return the kodeProses
     */
    public String getKodeProses() {
        return kodeProses;
    }

    /**
     * @param kodeProses the kodeProses to set
     */
    public void setKodeProses(String kodeProses) {
        this.kodeProses = kodeProses;
    }

    /**
     * @return the skpd
     */
    public List<Skpd> getSkpd() {
        return skpd;
    }

    /**
     * @param skpd the skpd to set
     */
    public void setSkpd(List<Skpd> skpd) {
        this.skpd = skpd;
    }
}
