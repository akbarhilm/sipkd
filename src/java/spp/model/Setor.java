/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.validation.Valid;

/**
 *
 * @author Husen
 */
public class Setor extends BaseModel {

    private Integer id;
    private Integer idSetor;
    private String kodeSetor;
    private String kodeBeban;
    private String kodeJenis;
    private String dokSetor;
    private String v_setor;
    private String noSetor;
    private BigDecimal nilaiSetor;
    private String kegiatan;
    private Integer idKegiatan;
    @Valid
    private Skpd skpd;
    private Integer idskpd;
    private Integer DataTerakhir;
    private String tahun;
    private String setor;
    // private String noSetor;
    private String tahunSetor;

    private Integer tahunAngg;
    private String beban;
    private String status;
    private String jenis;
    private String temuan;
    private String namaPa;
    private String nipPa;
    private String namaPptk;
    private String nipPptk;
    private List<SetorRinci> setorRinci;
    private Date tglSetor;
    
    private Integer idPenerimaan;
    private String noValidasi;
    private String keterangan;
    private String namakegiatan;
    
    private String noBku;
    private String noBuktiDok;
    private Date tglBku;
    private String ketBku;
    private Integer idSpd;
    private String kodeSA;
    private BigDecimal nilaiSA;
    private String noJurnal;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Skpd getSkpd() {
        return skpd;
    }

    public void setSkpd(Skpd skpd) {
        this.skpd = skpd;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getNoSetor() {
        return noSetor;
    }

    public void setNoSetor(String NoSetor) {
        this.noSetor = NoSetor;
    }

    public String getTahunSetor() {
        return tahunSetor;
    }

    public void setTahunSetor(String tahunSetor) {
        this.tahunSetor = tahunSetor;
    }

    public String getBeban() {
        return beban;
    }

    public void setBeban(String beban) {
        this.beban = beban;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    /**
     * @return the idskpd
     */
    public Integer getIdskpd() {
        return idskpd;
    }

    /**
     * @param idskpd the idskpd to set
     */
    public void setIdskpd(Integer idskpd) {
        this.idskpd = idskpd;
    }

    public List<SetorRinci> getSetorRinci() {
        return setorRinci;
    }

    public void setSetorRinci(List<SetorRinci> setorRinci) {
        this.setorRinci = setorRinci;
    }

    /**
     * @return the DataTerakhir
     */
    public Integer getDataTerakhir() {
        return DataTerakhir;
    }

    /**
     * @param DataTerakhir the DataTerakhir to set
     */
    public void setDataTerakhir(Integer DataTerakhir) {
        this.DataTerakhir = DataTerakhir;
    }

    /**
     * @return the tglSetor
     */
    public Date getTglSetor() {
        return tglSetor;
    }

    /**
     * @param tglSetor the tglSetor to set
     */
    public void setTglSetor(Date tglSetor) {
        this.tglSetor = tglSetor;
    }

    /**
     * @return the idSetor
     */
    public Integer getIdSetor() {
        return idSetor;
    }

    /**
     * @param idSetor the idSetor to set
     */
    public void setIdSetor(Integer idSetor) {
        this.idSetor = idSetor;
    }

    /**
     * @return the kodeSetor
     */
    public String getKodeSetor() {
        return kodeSetor;
    }

    /**
     * @param kodeSetor the kodeSetor to set
     */
    public void setKodeSetor(String kodeSetor) {
        this.kodeSetor = kodeSetor;
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
    public String getKodeJenis() {
        return kodeJenis;
    }

    /**
     * @param kodeJenis the kodeJenis to set
     */
    public void setKodeJenis(String kodeJenis) {
        this.kodeJenis = kodeJenis;
    }

    /**
     * @return the dokSetor
     */
    public String getDokSetor() {
        return dokSetor;
    }

    /**
     * @param dokSetor the dokSetor to set
     */
    public void setDokSetor(String dokSetor) {
        this.dokSetor = dokSetor;
    }

    /**
     * @return the v_setor
     */
    public String getV_setor() {
        return v_setor;
    }

    /**
     * @param v_setor the v_setor to set
     */
    public void setV_setor(String v_setor) {
        this.v_setor = v_setor;
    }

    /**
     * @return the nilaiSetor
     */
    public BigDecimal getNilaiSetor() {
        return nilaiSetor;
    }

    /**
     * @param NilaiSetor the nilaiSetor to set
     */
    public void setNilaiSetor(BigDecimal NilaiSetor) {
        this.nilaiSetor = NilaiSetor;
    }

    /**
     * @return the tahunAngg
     */
    public Integer getTahunAngg() {
        return tahunAngg;
    }

    /**
     * @param tahunAngg the tahunAngg to set
     */
    public void setTahunAngg(Integer tahunAngg) {
        this.tahunAngg = tahunAngg;
    }

    /**
     * @return the temuan
     */
    public String getTemuan() {
        return temuan;
    }

    /**
     * @param temuan the temuan to set
     */
    public void setTemuan(String temuan) {
        this.temuan = temuan;
    }

    /**
     * @return the kegiatan
     */
    public String getKegiatan() {
        return kegiatan;
    }

    /**
     * @param kegiatan the kegiatan to set
     */
    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
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
     * @return the namaPa
     */
    public String getNamaPa() {
        return namaPa;
    }

    /**
     * @param namaPa the namaPa to set
     */
    public void setNamaPa(String namaPa) {
        this.namaPa = namaPa;
    }

    /**
     * @return the nipPa
     */
    public String getNipPa() {
        return nipPa;
    }

    /**
     * @param nipPa the nipPa to set
     */
    public void setNipPa(String nipPa) {
        this.nipPa = nipPa;
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
     * @return the idPenerimaan
     */
    public Integer getIdPenerimaan() {
        return idPenerimaan;
    }

    /**
     * @param idPenerimaan the idPenerimaan to set
     */
    public void setIdPenerimaan(Integer idPenerimaan) {
        this.idPenerimaan = idPenerimaan;
    }

    /**
     * @return the noValidasi
     */
    public String getNoValidasi() {
        return noValidasi;
    }

    /**
     * @param noValidasi the noValidasi to set
     */
    public void setNoValidasi(String noValidasi) {
        this.noValidasi = noValidasi;
    }

    /**
     * @return the keterangan
     */
    public String getKeterangan() {
        return keterangan;
    }

    /**
     * @param keterangan the keterangan to set
     */
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    /**
     * @return the idKegiatan
     */
    public Integer getIdKegiatan() {
        return idKegiatan;
    }

    /**
     * @param idKegiatan the idKegiatan to set
     */
    public void setIdKegiatan(Integer idKegiatan) {
        this.idKegiatan = idKegiatan;
    }

    /**
     * @return the namakegiatan
     */
    public String getNamakegiatan() {
        return namakegiatan;
    }

    /**
     * @param namakegiatan the namakegiatan to set
     */
    public void setNamakegiatan(String namakegiatan) {
        this.namakegiatan = namakegiatan;
    }

    /**
     * @return the noBku
     */
    public String getNoBku() {
        return noBku;
    }

    /**
     * @param noBku the noBku to set
     */
    public void setNoBku(String noBku) {
        this.noBku = noBku;
    }

    /**
     * @return the noBuktiDok
     */
    public String getNoBuktiDok() {
        return noBuktiDok;
    }

    /**
     * @param noBuktiDok the noBuktiDok to set
     */
    public void setNoBuktiDok(String noBuktiDok) {
        this.noBuktiDok = noBuktiDok;
    }

    /**
     * @return the tglBku
     */
    public Date getTglBku() {
        return tglBku;
    }

    /**
     * @param tglBku the tglBku to set
     */
    public void setTglBku(Date tglBku) {
        this.tglBku = tglBku;
    }

    /**
     * @return the ketBku
     */
    public String getKetBku() {
        return ketBku;
    }

    /**
     * @param ketBku the ketBku to set
     */
    public void setKetBku(String ketBku) {
        this.ketBku = ketBku;
    }

    /**
     * @return the idSpd
     */
    public Integer getIdSpd() {
        return idSpd;
    }

    /**
     * @param idSpd the idSpd to set
     */
    public void setIdSpd(Integer idSpd) {
        this.idSpd = idSpd;
    }

    /**
     * @return the kodeSA
     */
    public String getKodeSA() {
        return kodeSA;
    }

    /**
     * @param kodeSA the kodeSA to set
     */
    public void setKodeSA(String kodeSA) {
        this.kodeSA = kodeSA;
    }

    /**
     * @return the nilaiSA
     */
    public BigDecimal getNilaiSA() {
        return nilaiSA;
    }

    /**
     * @param nilaiSA the nilaiSA to set
     */
    public void setNilaiSA(BigDecimal nilaiSA) {
        this.nilaiSA = nilaiSA;
    }

    /**
     * @return the noJurnal
     */
    public String getNoJurnal() {
        return noJurnal;
    }

    /**
     * @param noJurnal the noJurnal to set
     */
    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

}
