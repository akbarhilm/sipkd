package spp.model;

import javax.validation.constraints.NotNull;

/**
 *
 * @author Zainab
 */
public class StatusSpm extends BaseModel {
       
   private Integer i_id;
   private String wilayahSp2d;
   private String nodokSpm;
   private String jenis;
   private String beban;
   private String kodeSkpd;
   private Integer idSkpd;
   private String namaSkpd;
   private String tanggalCetak;
   private String tglterimaKpkd;
   private String tanggalSp2d;
   private String jumlahHari;
   private String ketSpm;
   private String tahun;
   private String noSp2d;

    public String getNoSp2d() {
        return noSp2d;
    }

    public void setNoSp2d(String noSp2d) {
        this.noSp2d = noSp2d;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public Integer getI_id() {
        return i_id;
    }

    public void setI_id(Integer i_id) {
        this.i_id = i_id;
    }

    public String getWilayahSp2d() {
        return wilayahSp2d;
    }

    public void setWilayahSp2d(String wilayahSp2d) {
        this.wilayahSp2d = wilayahSp2d;
    }

    public String getNodokSpm() {
        return nodokSpm;
    }

    public void setNodokSpm(String nodokSpm) {
        this.nodokSpm = nodokSpm;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getBeban() {
        return beban;
    }

    public void setBeban(String beban) {
        this.beban = beban;
    }

    public String getKodeSkpd() {
        return kodeSkpd;
    }

    public void setKodeSkpd(String kodeSkpd) {
        this.kodeSkpd = kodeSkpd;
    }

    public Integer getIdSkpd() {
        return idSkpd;
    }

    public void setIdSkpd(Integer idSkpd) {
        this.idSkpd = idSkpd;
    }

    public String getNamaSkpd() {
        return namaSkpd;
    }

    public void setNamaSkpd(String namaSkpd) {
        this.namaSkpd = namaSkpd;
    }

    public String getTanggalCetak() {
        return tanggalCetak;
    }

    public void setTanggalCetak(String tanggalCetak) {
        this.tanggalCetak = tanggalCetak;
    }

    public String getTglterimaKpkd() {
        return tglterimaKpkd;
    }

    public void setTglterimaKpkd(String tglterimaKpkd) {
        this.tglterimaKpkd = tglterimaKpkd;
    }

    public String getTanggalSp2d() {
        return tanggalSp2d;
    }

    public void setTanggalSp2d(String tanggalSp2d) {
        this.tanggalSp2d = tanggalSp2d;
    }

    public String getJumlahHari() {
        return jumlahHari;
    }

    public void setJumlahHari(String jumlahHari) {
        this.jumlahHari = jumlahHari;
    }

    public String getKetSpm() {
        return ketSpm;
    }

    public void setKetSpm(String ketSpm) {
        this.ketSpm = ketSpm;
    }
   
   
}
