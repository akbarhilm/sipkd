package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author User
 */
public class SpdBTLMaster extends BaseModel {

    @Valid
    private Skpd skpd;
    private Skpd skpdKordinator;
    //@NotNull(message = "Tanggal SKPD tidak boleh kosong")
    private Date tglSpd;
    private Long idSpd;
    private Integer noSpd;
    private String status;
    @Size.List({
        @Size(min = 4, message = "Uraian harus lebih dari {min} characters"),
        @Size(max = 51, message = "Uraian tidak boleh lebih dari {max} characters")
    })
    private String uraian;
    @NotNull(message = "Bulan awal wajib di isi")
    @NotEmpty(message = "Bulan awal wajib tidak boleh kosong")
    private String bulanAwal;
    @NotNull(message = "Bulan awal akhir di isi")
    @NotEmpty(message = "Bulan awal akhir tidak boleh kosong")
    private String bulanAkhir;
    private String tahunAnggaran;
    private BigDecimal nilaiSpd;
    private BigDecimal totalAngaran;
    @Valid
    private PejabatPpkd pejabatPpkd;
    private String jenis;
     private String kodeKegiatan;

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
     * @return the tglSpd
     */
    public Date getTglSpd() {
        return tglSpd;
    }

    /**
     * @param tglSpd the tglSpd to set
     */
    public void setTglSpd(Date tglSpd) {
        this.tglSpd = tglSpd;
    }

    /**
     * @return the idSpd
     */
    public Long getIdSpd() {
        return idSpd;
    }

    /**
     * @param idSpd the idSpd to set
     */
    public void setIdSpd(Long idSpd) {
        this.idSpd = idSpd;
    }

    /**
     * @return the noSpd
     */
    public Integer getNoSpd() {
        return noSpd;
    }

    /**
     * @param noSpd the noSpd to set
     */
    public void setNoSpd(Integer noSpd) {
        this.noSpd = noSpd;
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
     * @return the totalAngaran
     */
    public BigDecimal getTotalAngaran() {
        return totalAngaran;
    }

    /**
     * @param totalAngaran the totalAngaran to set
     */
    public void setTotalAngaran(BigDecimal totalAngaran) {
        this.totalAngaran = totalAngaran;
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
     * @return the bulanAwal
     */
    public String getBulanAwal() {
        return bulanAwal;
    }

    /**
     * @param bulanAwal the bulanAwal to set
     */
    public void setBulanAwal(String bulanAwal) {
        this.bulanAwal = bulanAwal;
    }

    /**
     * @return the bulanAkhir
     */
    public String getBulanAkhir() {
        return bulanAkhir;
    }

    /**
     * @param bulanAkhir the bulanAkhir to set
     */
    public void setBulanAkhir(String bulanAkhir) {
        this.bulanAkhir = bulanAkhir;
    }

    /**
     * @return the pejabatPpkd
     */
    public PejabatPpkd getPejabatPpkd() {
        return pejabatPpkd;
    }

    /**
     * @param pejabatPpkd the pejabatPpkd to set
     */
    public void setPejabatPpkd(PejabatPpkd pejabatPpkd) {
        this.pejabatPpkd = pejabatPpkd;
    }

    @Override
    public String toString() {
        return new StringBuilder(" tglSpd ").append(tglSpd)
                .append(" idSpd ").append(idSpd).append("   noSpd ").append(noSpd)
                .append(" uraian ")
                .append(uraian)
                .append(" bulanAwal ").append(bulanAwal).toString();

    }

    /**
     * @return the jenis
     */
    public String getJenis() {
        return jenis;
    }

    /**
     * @param jenis the jenis to set
     */
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    /**
     * @return the skpdKordinator
     */
    public Skpd getSkpdKordinator() {
        return skpdKordinator;
    }

    /**
     * @param skpdKordinator the skpdKordinator to set
     */
    public void setSkpdKordinator(Skpd skpdKordinator) {
        this.skpdKordinator = skpdKordinator;
    }

    /**
     * @return the kodeKegiatan
     */
    public String getKodeKegiatan() {
        return kodeKegiatan;
    }

    /**
     * @param kodeKegiatan the kodeKegiatan to set
     */
    public void setKodeKegiatan(String kodeKegiatan) {
        this.kodeKegiatan = kodeKegiatan;
    }
}
