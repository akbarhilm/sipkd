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
public class SetorRinci extends BaseModel{
    
    private Integer idRinci;
    private Integer idKegiatan;
    private Integer idkegiatan;
    private String namaKeg;
    private String kodeKeg;
    private String pagu;
    private BigDecimal nilaiSetor;
   // private Akun akun;
    private Skpd skpd;
    private Kegiatan kegiatan;
    private Integer idskpd;
    private String tahun;
    private String akun;
    private String namaAkun;
    private String nilaiAnggaran;
    private String nilaiSetoran;
    private Integer idBl;
  //  private Integer idKegiatan;
    private Integer idSetorrinci;
    private Integer idAkun;
    private Integer idsetor;
    private Integer idSetorRinci;
    private Integer idSetor;
    private Integer DataTerakhir;
    //private BigDecimal nilaisetor;
    private String beban;
    
    public String getKodeKeg() {
        return kodeKeg;
    }
    
    public void setKodeKeg(String kodeKeg) {
        this.kodeKeg = kodeKeg;
    }
    
    
    public Integer getIdRinci() {
        return idRinci;
    }
    
    public void setIdRinci(Integer idRinci) {
        this.idRinci = idRinci;
    }
    
    public Skpd getSkpd() {
        return skpd;
    }

    public void setSkpd(Skpd skpd) {
        this.skpd = skpd;
    }
    
    public Integer getIdskpd() {
        return idskpd;
    }


    public void setIdskpd(Integer idskpd) {
        this.idskpd = idskpd;
    }
    
    public String getTahun() {
        return tahun;
    }
    
    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
    /*
    public Akun getAkun() {
        return akun;
    }

  
    public void setAkun(Akun akun) {
        this.akun = akun;
    }
    */
    
    public String getAkun() {
        return akun;
    }
    
    public void setAkun(String akun) {
        this.akun = akun;
    }
    
    public String getNamaAkun() {
        return namaAkun;
    }
    
    public void setNamaAkun(String namaAkun) {
        this.namaAkun = namaAkun;
    }
    
    public String getNilaiAnggaran() {
        return nilaiAnggaran;
    }
    
    public void setNilaiAnggaran(String nilaiAnggaran) {
        this.nilaiAnggaran = nilaiAnggaran;
    }
    
    public String getNilaiSetoran() {
        return nilaiSetoran;
    }
    
    public void setNilaiSetoran(String nilaiSetoran) {
        this.nilaiSetoran = nilaiSetoran;
    }

    /**
     * @return the idBl
     */
    public Integer getIdBl() {
        return idBl;
    }

    /**
     * @param idBl the idBl to set
     */
    public void setIdBl(Integer idBl) {
        this.idBl = idBl;
    }

    /**
     * @return the idKegiatan
     */
    
    public Integer getIdKegiatan() {
        return idKegiatan;
    }

    public void setIdKegiatan(Integer idKegiatan) {
        this.idKegiatan = idKegiatan;
    }
    
    public String getPagu() {
        return pagu;
    }

    public void setPagu(String pagu) {
        this.pagu = pagu;
    }
    
    public void setNamaKeg(String namaKeg) {
        this.namaKeg = namaKeg;
    }
    
    public String getNamaKeg() {
        return namaKeg;
    }

    
    

    /**
     * @return the idSetorrinci
     */
    public Integer getIdSetorrinci() {
        return idSetorrinci;
    }

    /**
     * @param idSetorrinci the idSetorrinci to set
     */
    public void setIdSetorrinci(Integer idSetorrinci) {
        this.idSetorrinci = idSetorrinci;
    }

    /**
     * @return the idAkun
     */
    public Integer getIdAkun() {
        return idAkun;
    }

    /**
     * @param idAkun the idAkun to set
     */
    public void setIdAkun(Integer idAkun) {
        this.idAkun = idAkun;
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
     * @return the nilaiSetor
     */
    public BigDecimal getNilaiSetor() {
        return nilaiSetor;
    }

    /**
     * @param nilaiSetor the nilaiSetor to set
     */
    public void setNilaiSetor(BigDecimal nilaiSetor) {
        this.nilaiSetor = nilaiSetor;
    }

    /**
     * @return the idsetor
     */
    public Integer getIdsetor() {
        return idsetor;
    }

    /**
     * @param idsetor the idsetor to set
     */
    public void setIdsetor(Integer idsetor) {
        this.idsetor = idsetor;
    }

    /**
     * @return the idSetorRinci
     */
    public Integer getIdSetorRinci() {
        return idSetorRinci;
    }

    /**
     * @param idSetorRinci the idSetorRinci to set
     */
    public void setIdSetorRinci(Integer idSetorRinci) {
        this.idSetorRinci = idSetorRinci;
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
     * @return the idkegiatan
     */
    public Integer getIdkegiatan() {
        return idkegiatan;
    }

    /**
     * @param idkegiatan the idkegiatan to set
     */
    public void setIdkegiatan(Integer idkegiatan) {
        this.idkegiatan = idkegiatan;
    }

    public void setIdKegiatan(SetorRinci setorrinci) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the beban
     */
    public String getBeban() {
        return beban;
    }

    /**
     * @param beban the beban to set
     */
    public void setBeban(String beban) {
        this.beban = beban;
    }

    
   
}
