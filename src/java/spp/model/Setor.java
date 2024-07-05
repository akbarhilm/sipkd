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
public class Setor extends BaseModel{
    
    private Integer id;
    private Integer idSetor;
    private String kodeSetor;
    private String kodeBeban;
    private String kodeJenis;
    private String dokSetor;
    private String v_setor;
    private String noSetor;
    private BigDecimal nilaiSetor;
    @Valid
    private Skpd skpd;
    private Integer idskpd;
    private Integer DataTerakhir;
    private String tahun;
    private String setor;
   // private String noSetor;
    private String tahunSetor;
    private String beban;
    private String jenis;
    private List<SetorRinci> setorRinci;
    private Date tglSetor;
    
    
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
     
}
