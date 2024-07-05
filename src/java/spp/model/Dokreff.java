
package spp.model;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import spp.model.BaseModel;


public class Dokreff extends BaseModel {

    private Integer idDokreff;
    private String namaDaerahJudul;
    private String namaDaerah;
    private String namaKota;
    private String noPerda;
    private String kodePerdaTanggal;
    private String kodePerdaTahun;
    private String ketPeraturanSpd1;
    private String ketPeraturanSpd2;
    private String ketPeraturanSpd3;
    private String ketPeraturanSpd4;
    private String ketPeraturanSpd5;
    private String namaPpkd;
    private String nipPpkd;

    /**
     * @return the idDokreff
     */
    public Integer getIdDokreff() {
        return idDokreff;
    }

    /**
     * @param idDokreff the idDokreff to set
     */
    public void setIdDokreff(Integer idDokreff) {
        this.idDokreff = idDokreff;
    }

    /**
     * @return the namaDaerahJudul
     */
    public String getNamaDaerahJudul() {
        return namaDaerahJudul;
    }

    /**
     * @param namaDaerahJudul the namaDaerahJudul to set
     */
    public void setNamaDaerahJudul(String namaDaerahJudul) {
        this.namaDaerahJudul = namaDaerahJudul;
    }

    /**
     * @return the namaDaerah
     */
    public String getNamaDaerah() {
        return namaDaerah;
    }

    /**
     * @param namaDaerah the namaDaerah to set
     */
    public void setNamaDaerah(String namaDaerah) {
        this.namaDaerah = namaDaerah;
    }

    /**
     * @return the namaKota
     */
    public String getNamaKota() {
        return namaKota;
    }

    /**
     * @param namaKota the namaKota to set
     */
    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

    /**
     * @return the noPerda
     */
    public String getNoPerda() {
        return noPerda;
    }

    /**
     * @param noPerda the noPerda to set
     */
    public void setNoPerda(String noPerda) {
        this.noPerda = noPerda;
    }

    /**
     * @return the kodePerdaTanggal
     */
    public String getKodePerdaTanggal() {
        return kodePerdaTanggal;
    }

    /**
     * @param kodePerdaTanggal the kodePerdaTanggal to set
     */
    public void setKodePerdaTanggal(String kodePerdaTanggal) {
        this.kodePerdaTanggal = kodePerdaTanggal;
    }

    /**
     * @return the kodePerdaTahun
     */
    public String getKodePerdaTahun() {
        return kodePerdaTahun;
    }

    /**
     * @param kodePerdaTahun the kodePerdaTahun to set
     */
    public void setKodePerdaTahun(String kodePerdaTahun) {
        this.kodePerdaTahun = kodePerdaTahun;
    }

    /**
     * @return the ketPeraturanSpd1
     */
    public String getKetPeraturanSpd1() {
        return ketPeraturanSpd1;
    }

    /**
     * @param ketPeraturanSpd1 the ketPeraturanSpd1 to set
     */
    public void setKetPeraturanSpd1(String ketPeraturanSpd1) {
        this.ketPeraturanSpd1 = ketPeraturanSpd1;
    }

    /**
     * @return the ketPeraturanSpd2
     */
    public String getKetPeraturanSpd2() {
        return ketPeraturanSpd2;
    }

    /**
     * @param ketPeraturanSpd2 the ketPeraturanSpd2 to set
     */
    public void setKetPeraturanSpd2(String ketPeraturanSpd2) {
        this.ketPeraturanSpd2 = ketPeraturanSpd2;
    }

    /**
     * @return the ketPeraturanSpd3
     */
    public String getKetPeraturanSpd3() {
        return ketPeraturanSpd3;
    }

    /**
     * @param ketPeraturanSpd3 the ketPeraturanSpd3 to set
     */
    public void setKetPeraturanSpd3(String ketPeraturanSpd3) {
        this.ketPeraturanSpd3 = ketPeraturanSpd3;
    }

    /**
     * @return the ketPeraturanSpd4
     */
    public String getKetPeraturanSpd4() {
        return ketPeraturanSpd4;
    }

    /**
     * @param ketPeraturanSpd4 the ketPeraturanSpd4 to set
     */
    public void setKetPeraturanSpd4(String ketPeraturanSpd4) {
        this.ketPeraturanSpd4 = ketPeraturanSpd4;
    }

    /**
     * @return the ketPeraturanSpd5
     */
    public String getKetPeraturanSpd5() {
        return ketPeraturanSpd5;
    }

    /**
     * @param ketPeraturanSpd5 the ketPeraturanSpd5 to set
     */
    public void setKetPeraturanSpd5(String ketPeraturanSpd5) {
        this.ketPeraturanSpd5 = ketPeraturanSpd5;
    }

    /**
     * @return the namaPpkd
     */
    public String getNamaPpkd() {
        return namaPpkd;
    }

    /**
     * @param namaPpkd the namaPpkd to set
     */
    public void setNamaPpkd(String namaPpkd) {
        this.namaPpkd = namaPpkd;
    }

    /**
     * @return the nipPpkd
     */
    public String getNipPpkd() {
        return nipPpkd;
    }

    /**
     * @param nipPpkd the nipPpkd to set
     */
    public void setNipPpkd(String nipPpkd) {
        this.nipPpkd = nipPpkd;
    }

    

   
}