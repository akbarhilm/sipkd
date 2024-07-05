/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.validation.constraints.NotNull;

public class Npd extends BaseModel {

    private Integer idNpd;
    private String noNpd;
    private String tglNpd;
    private Date tanggalNpd;
    private Skpd skpd;
    private Kegiatan kegiatan;
    private Program program;
    private Akun akun;
    private String ketNpd;
    private String tahunAnggaran;
    private BigDecimal nilaiNpd;
    private BigDecimal nilaiAnggaranTapd;
    private BigDecimal nilaiSpd;
    private BigDecimal nilaiSpdLs;
    private BigDecimal nilaiNpdSebelum;
    private BigDecimal sisaNpd;
    private String nipPptk;
    private String namaPptk;
    private String tahun;
    private String status;
    private List<NpdRinci> npdRinci;
    

    /**
     * @return the idNpd
     */
    public Integer getIdNpd() {
        return idNpd;
    }

    /**
     * @param idNpd the idNpd to set
     */
    public void setIdNpd(Integer idNpd) {
        this.idNpd = idNpd;
    }

    /**
     * @return the noNpd
     */
    public String getNoNpd() {
        return noNpd;
    }

    /**
     * @param noNpd the noNpd to set
     */
    public void setNoNpd(String noNpd) {
        this.noNpd = noNpd;
    }

    /**
     * @return the tglNpd
     */
    public String getTglNpd() {
        return tglNpd;
    }

    /**
     * @param tglNpd the tglNpd to set
     */
    public void setTglNpd(String tglNpd) {
        this.tglNpd = tglNpd;
    }

    /**
     * @return the tanggalNpd
     */
    public Date getTanggalNpd() {
        return tanggalNpd;
    }

    /**
     * @param tanggalNpd the tanggalNpd to set
     */
    public void setTanggalNpd(Date tanggalNpd) {
        this.tanggalNpd = tanggalNpd;
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
     * @return the kegiatan
     */
    public Kegiatan getKegiatan() {
        return kegiatan;
    }

    /**
     * @param kegiatan the kegiatan to set
     */
    public void setKegiatan(Kegiatan kegiatan) {
        this.kegiatan = kegiatan;
    }

    /**
     * @return the ketNpd
     */
    public String getKetNpd() {
        return ketNpd;
    }

    /**
     * @param ketNpd the ketNpd to set
     */
    public void setKetNpd(String ketNpd) {
        this.ketNpd = ketNpd;
    }

    /**
     * @return the tahunAnggaran
     */
    public String getTahunAnggaran() {
        return tahunAnggaran;
    }

    /**
     * @param tahunAnggaran the tahunAnggaran to set
     */
    public void setTahunAnggaran(String tahunAnggaran) {
        this.tahunAnggaran = tahunAnggaran;
    }

    /**
     * @return the nilaiNpd
     */
    public BigDecimal getNilaiNpd() {
        return nilaiNpd;
    }

    /**
     * @param nilaiNpd the nilaiNpd to set
     */
    public void setNilaiNpd(BigDecimal nilaiNpd) {
        this.nilaiNpd = nilaiNpd;
    }

    /**
     * @return the nipPptk
     */
    public String getNipPptk() {
        return nipPptk;
    }

    /**
     * @param nipPptk the nipPptk to set
     */
    public void setNipPptk(String nipPptk) {
        this.nipPptk = nipPptk;
    }

    /**
     * @return the namaPptk
     */
    public String getNamaPptk() {
        return namaPptk;
    }

    /**
     * @param namaPptk the namaPptk to set
     */
    public void setNamaPptk(String namaPptk) {
        this.namaPptk = namaPptk;
    }

    /**
     * @return the program
     */
    public Program getProgram() {
        return program;
    }

    /**
     * @param program the program to set
     */
    public void setProgram(Program program) {
        this.program = program;
    }

    /**
     * @return the nilaiAnggaranTapd
     */
    public BigDecimal getNilaiAnggaranTapd() {
        return nilaiAnggaranTapd;
    }

    /**
     * @param nilaiAnggaranTapd the nilaiAnggaranTapd to set
     */
    public void setNilaiAnggaranTapd(BigDecimal nilaiAnggaranTapd) {
        this.nilaiAnggaranTapd = nilaiAnggaranTapd;
    }

    /**
     * @return the nilaiSpd
     */
    public BigDecimal getNilaiSpd() {
        return nilaiSpd;
    }

    /**
     * @param nilaiSpd the nilaiSpd to set
     */
    public void setNilaiSpd(BigDecimal nilaiSpd) {
        this.nilaiSpd = nilaiSpd;
    }

    /**
     * @return the nilaiSpdLs
     */
    public BigDecimal getNilaiSpdLs() {
        return nilaiSpdLs;
    }

    /**
     * @param nilaiSpdLs the nilaiSpdLs to set
     */
    public void setNilaiSpdLs(BigDecimal nilaiSpdLs) {
        this.nilaiSpdLs = nilaiSpdLs;
    }

    /**
     * @return the nilaiNpdSebelum
     */
    public BigDecimal getNilaiNpdSebelum() {
        return nilaiNpdSebelum;
    }

    /**
     * @param nilaiNpdSebelum the nilaiNpdSebelum to set
     */
    public void setNilaiNpdSebelum(BigDecimal nilaiNpdSebelum) {
        this.nilaiNpdSebelum = nilaiNpdSebelum;
    }

    /**
     * @return the sisaNpd
     */
    public BigDecimal getSisaNpd() {
        return sisaNpd;
    }

    /**
     * @param sisaNpd the sisaNpd to set
     */
    public void setSisaNpd(BigDecimal sisaNpd) {
        this.sisaNpd = sisaNpd;
    }

    /**
     * @return the akun
     */
    public Akun getAkun() {
        return akun;
    }

    /**
     * @param akun the akun to set
     */
    public void setAkun(Akun akun) {
        this.akun = akun;
    }

   
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
     * @return the npdRinci
     */
    public List<NpdRinci> getNpdRinci() {
        return npdRinci;
    }

    /**
     * @param npdRinci the npdRinci to set
     */
    public void setNpdRinci(List<NpdRinci> npdRinci) {
        this.npdRinci = npdRinci;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
