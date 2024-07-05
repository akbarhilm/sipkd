package eset.model;

import java.sql.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
public class Berita extends BaseModel{
    private String tahun;
    private Integer idBerita;
    private String kodeBerita;
    private String ketKode;
    private Date tglBerlaku;
    private Date tglBerakhir;
    private String noUrut;
    private String uraian;
    private String tujuanBerita;
    private String pengirimBerita;
    private String kodeAktif;
    private String tglAkhir;
    private String namaPdf;
    private String judulPdf;

    /**
     * @return the tahun
     */
    public String getTahun() {
        return tahun;
    }

    /**
     * @param tahun the tahun to set
     */
    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    /**
     * @return the idBerita
     */
    public Integer getIdBerita() {
        return idBerita;
    }

    /**
     * @param idBerita the idBerita to set
     */
    public void setIdBerita(Integer idBerita) {
        this.idBerita = idBerita;
    }

    /**
     * @return the kodeBerita
     */
    public String getKodeBerita() {
        return kodeBerita;
    }

    /**
     * @param kodeBerita the kodeBerita to set
     */
    public void setKodeBerita(String kodeBerita) {
        this.kodeBerita = kodeBerita;
    }

    /**
     * @return the ketKode
     */
    public String getKetKode() {
        return ketKode;
    }

    /**
     * @param ketKode the ketKode to set
     */
    public void setKetKode(String ketKode) {
        this.ketKode = ketKode;
    }

    /**
     * @return the tglBerlaku
     */
    public Date getTglBerlaku() {
        return tglBerlaku;
    }

    /**
     * @param tglBerlaku the tglBerlaku to set
     */
    public void setTglBerlaku(Date tglBerlaku) {
        this.tglBerlaku = tglBerlaku;
    }

    /**
     * @return the tglBerakhir
     */
    public Date getTglBerakhir() {
        return tglBerakhir;
    }

    /**
     * @param tglBerakhir the tglBerakhir to set
     */
    public void setTglBerakhir(Date tglBerakhir) {
        this.tglBerakhir = tglBerakhir;
    }

    /**
     * @return the noUrut
     */
    public String getNoUrut() {
        return noUrut;
    }

    /**
     * @param noUrut the noUrut to set
     */
    public void setNoUrut(String noUrut) {
        this.noUrut = noUrut;
    }

    /**
     * @return the uraian
     */
    public String getUraian() {
        return uraian;
    }

    /**
     * @param uraian the uraian to set
     */
    public void setUraian(String uraian) {
        this.uraian = uraian;
    }

    /**
     * @return the tujuanBerita
     */
    public String getTujuanBerita() {
        return tujuanBerita;
    }

    /**
     * @param tujuanBerita the tujuanBerita to set
     */
    public void setTujuanBerita(String tujuanBerita) {
        this.tujuanBerita = tujuanBerita;
    }

    /**
     * @return the pengirimBerita
     */
    public String getPengirimBerita() {
        return pengirimBerita;
    }

    /**
     * @param pengirimBerita the pengirimBerita to set
     */
    public void setPengirimBerita(String pengirimBerita) {
        this.pengirimBerita = pengirimBerita;
    }

    /**
     * @return the kodeAktif
     */
    public String getKodeAktif() {
        return kodeAktif;
    }

    /**
     * @param kodeAktif the kodeAktif to set
     */
    public void setKodeAktif(String kodeAktif) {
        this.kodeAktif = kodeAktif;
    }

    /**
     * @return the tglAkhir
     */
    public String getTglAkhir() {
        return tglAkhir;
    }

    /**
     * @param tglAkhir the tglAkhir to set
     */
    public void setTglAkhir(String tglAkhir) {
        this.tglAkhir = tglAkhir;
    }

    /**
     * @return the namaPdf
     */
    public String getNamaPdf() {
        return namaPdf;
    }

    /**
     * @param namaPdf the namaPdf to set
     */
    public void setNamaPdf(String namaPdf) {
        this.namaPdf = namaPdf;
    }

    /**
     * @return the judulPdf
     */
    public String getJudulPdf() {
        return judulPdf;
    }

    /**
     * @param judulPdf the judulPdf to set
     */
    public void setJudulPdf(String judulPdf) {
        this.judulPdf = judulPdf;
    }

    
     
}
