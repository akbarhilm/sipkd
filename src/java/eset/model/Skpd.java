package eset.model;

import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
public class Skpd extends BaseModel {

    @NotNull(message = "SKPD Wajib di isi")
    private Integer idSkpd;
    private String kodeSkpd;
    private Integer idInduk;
    private String namaSkpd;
    private String namaSkpdPendek;
    private String isAktif;
    private String levelSkpd;
    private String kodeKomisi;
    private String kodeAsisten;
    private String isNeraca;
    private String isPendapatan;
    private String kodeKecamatan;
    private String kodeKelurahan;
    private String tahunBerakhir;
    private String tahunBerlaku;
    private String kodeUnit;
    private String namaUnit;

    private Integer idSkpdkoord;
    private String kodeSkpdkoord;
    private String namaSkpdkoord;

    private Urusan urusan;
    private Skpd skpd;

    private String bendahara;
    private String nip_bendahara;

    /**
     * @return the idSkpd
     */
    public Integer getIdSkpd() {
        return idSkpd;
    }

    /**
     * @param idSkpd the idSkpd to set
     */
    public void setIdSkpd(Integer idSkpd) {
        this.idSkpd = idSkpd;
    }

    /**
     * @return the kodeSkpd
     */
    public String getKodeSkpd() {
        return kodeSkpd;
    }

    /**
     * @param kodeSkpd the kodeSkpd to set
     */
    public void setKodeSkpd(String kodeSkpd) {
        this.kodeSkpd = kodeSkpd;
    }

    /**
     * @return the namaSkpd
     */
    public String getNamaSkpd() {
        return namaSkpd;
    }

    /**
     * @param namaSkpd the namaSkpd to set
     */
    public void setNamaSkpd(String namaSkpd) {
        this.namaSkpd = namaSkpd;
    }

    /**
     * @return the isAktif
     */
    public String getIsAktif() {
        return isAktif;
    }

    /**
     * @param isAktif the isAktif to set
     */
    public void setIsAktif(String isAktif) {
        this.isAktif = isAktif;
    }

    /**
     * @return the namaSkpdPendek
     */
    public String getNamaSkpdPendek() {
        return namaSkpdPendek;
    }

    /**
     * @param namaSkpdPendek the namaSkpdPendek to set
     */
    public void setNamaSkpdPendek(String namaSkpdPendek) {
        this.namaSkpdPendek = namaSkpdPendek;
    }

    /**
     * @return the idInduk
     */
    public Integer getIdInduk() {
        return idInduk;
    }

    /**
     * @param idInduk the idInduk to set
     */
    public void setIdInduk(Integer idInduk) {
        this.idInduk = idInduk;
    }

    /**
     * @return the levelSkpd
     */
    public String getLevelSkpd() {
        return levelSkpd;
    }

    /**
     * @param levelSkpd the levelSkpd to set
     */
    public void setLevelSkpd(String levelSkpd) {
        this.levelSkpd = levelSkpd;
    }

    /**
     * @return the kodeKomisi
     */
    public String getKodeKomisi() {
        return kodeKomisi;
    }

    /**
     * @param kodeKomisi the kodeKomisi to set
     */
    public void setKodeKomisi(String kodeKomisi) {
        this.kodeKomisi = kodeKomisi;
    }

    /**
     * @return the kodeAsisten
     */
    public String getKodeAsisten() {
        return kodeAsisten;
    }

    /**
     * @param kodeAsisten the kodeAsisten to set
     */
    public void setKodeAsisten(String kodeAsisten) {
        this.kodeAsisten = kodeAsisten;
    }

    /**
     * @return the isNeraca
     */
    public String getIsNeraca() {
        return isNeraca;
    }

    /**
     * @param isNeraca the isNeraca to set
     */
    public void setIsNeraca(String isNeraca) {
        this.isNeraca = isNeraca;
    }

    /**
     * @return the isPendapatan
     */
    public String getIsPendapatan() {
        return isPendapatan;
    }

    /**
     * @param isPendapatan the isPendapatan to set
     */
    public void setIsPendapatan(String isPendapatan) {
        this.isPendapatan = isPendapatan;
    }

    /**
     * @return the kodeKecamatan
     */
    public String getKodeKecamatan() {
        return kodeKecamatan;
    }

    /**
     * @param kodeKecamatan the kodeKecamatan to set
     */
    public void setKodeKecamatan(String kodeKecamatan) {
        this.kodeKecamatan = kodeKecamatan;
    }

    /**
     * @return the kodeKelurahan
     */
    public String getKodeKelurahan() {
        return kodeKelurahan;
    }

    /**
     * @param kodeKelurahan the kodeKelurahan to set
     */
    public void setKodeKelurahan(String kodeKelurahan) {
        this.kodeKelurahan = kodeKelurahan;
    }

    /**
     * @return the kodeUnit
     */
    public String getKodeUnit() {
        return kodeUnit;
    }

    /**
     * @param kodeUnit the kodeUnit to set
     */
    public void setKodeUnit(String kodeUnit) {
        this.kodeUnit = kodeUnit;
    }

    /**
     * @return the namaUnit
     */
    public String getNamaUnit() {
        return namaUnit;
    }

    /**
     * @param namaUnit the namaUnit to set
     */
    public void setNamaUnit(String namaUnit) {
        this.namaUnit = namaUnit;
    }

    /**
     * @return the urusan
     */
    public Urusan getUrusan() {
        return urusan;
    }

    /**
     * @param urusan the urusan to set
     */
    public void setUrusan(Urusan urusan) {
        this.urusan = urusan;
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
     * @return the tahunBerakhir
     */
    public String getTahunBerakhir() {
        return tahunBerakhir;
    }

    /**
     * @param tahunBerakhir the tahunBerakhir to set
     */
    public void setTahunBerakhir(String tahunBerakhir) {
        this.tahunBerakhir = tahunBerakhir;
    }

    /**
     * @return the tahunBerlaku
     */
    public String getTahunBerlaku() {
        return tahunBerlaku;
    }

    /**
     * @param tahunBerlaku the tahunBerlaku to set
     */
    public void setTahunBerlaku(String tahunBerlaku) {
        this.tahunBerlaku = tahunBerlaku;
    }

    /**
     * @return the idSkpdkoord
     */
    public Integer getIdSkpdkoord() {
        return idSkpdkoord;
    }

    /**
     * @param namaSkpdkoord the namaSkpdkoord to set
     */
    public void setNamaSkpdkoord(String namaSkpdkoord) {
        this.namaSkpdkoord = namaSkpdkoord;
    }

    /**
     * @param idSkpdkoord the idSkpdkoord to set
     */
    public void setIdSkpdkoord(Integer idSkpdkoord) {
        this.idSkpdkoord = idSkpdkoord;
    }

    /**
     * @return the kodeSkpdkoord
     */
    public String getKodeSkpdkoord() {
        return kodeSkpdkoord;
    }

    /**
     * @param kodeSkpdkoord the kodeSkpdkoord to set
     */
    public void setKodeSkpdkoord(String kodeSkpdkoord) {
        this.kodeSkpdkoord = kodeSkpdkoord;
    }

    /**
     * @return the bendahara
     */
    public String getBendahara() {
        return bendahara;
    }

    /**
     * @param bendahara the bendahara to set
     */
    public void setBendahara(String bendahara) {
        this.bendahara = bendahara;
    }

    /**
     * @return the nip_bendahara
     */
    public String getNip_bendahara() {
        return nip_bendahara;
    }

    /**
     * @param nip_bendahara the nip_bendahara to set
     */
    public void setNip_bendahara(String nip_bendahara) {
        this.nip_bendahara = nip_bendahara;
    }

    /**
     * @return the namaSkpdkoord
     */
    public String getNamaSkpdkoord() {
        return namaSkpdkoord;
    }

    /**
     * @return the level
     */
}
