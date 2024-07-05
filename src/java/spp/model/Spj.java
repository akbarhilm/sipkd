/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spp.model;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author maman sulaeman
 */
public class Spj extends BaseModel{
    private Integer idBl;
    private Integer idSpj;
    private String tahun;
    private Skpd skpd;
    private Program program;
    private Kegiatan kegiatan;
    private Bendahara bendahara;
    private String noSpj;
    private String bulan;
    private BigDecimal nilaiSpjUp;
    private BigDecimal nilaiSpjGu;
    private BigDecimal nilaiSpjTu;
    private BigDecimal nilai_UPGUTU;
    private BigDecimal sudah_spj;
    private BigDecimal SISA_spj;
    private BigDecimal nilai_spj;
    private String ketSpj;
    private String nihil;
    private Akun akun;
    private Integer idJour;
    private BigDecimal nilaiSpjBtl;
    private BigDecimal nilaiSpjBl;
    private String kodeBeban;
    private BigDecimal nilaiSpp;
    
    private SpjRinci spjrinci;
    private List<SpjRinci> spjRinciKegiatan;
    private String tanda;
    
    // TAMBAHAN CETAK SPJ
    private String statusCetak;
    private String tglCetak;
    private String idSpjsah;
    
    //TAMBAHAN PEJABAT 
    private String nipPenggunaAnggaran;
    private String nrkPenggunaAnggaran;
    private String namaPenggunaAnggaran;
    private String nipVerifikator;
    private String nrkVerifikator;
    private String namaVerifikator;
    
    // tambahan combo
    
    private String kodeBulan;
    private String namaBulan;
    
    // tambahan journal
    private String kodekeg;
    private String namakeg;
    private String akunbelanja;
    private String akunjurnal;
    private String idskpd;
    
    // cek validasi sisa spj
    private BigDecimal nilaiSetor;
    private BigDecimal sisaSpj;
    
    private String kodeAktif;
    private BigDecimal nilaiPaguUp;
    private BigDecimal totalSpp;
    private BigDecimal totalSpj;
    private BigDecimal entrySpj;
    private BigDecimal sisaUang;
    private String persentase;
   
   
    // CHECKBOX
    private boolean spjnihil = false;
    /**
     * @return the idSpj
     */
    public Integer getIdSpj() {
        return idSpj;
    }
 

    /**
     * @param idSpj the idSpj to set
     */
    public void setIdSpj(Integer idSpj) {
        this.idSpj = idSpj;
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
     * @return the noSpj
     */
    public String getNoSpj() {
        return noSpj;
    }

    /**
     * @param noSpj the noSpj to set
     */
    public void setNoSpj(String noSpj) {
        this.noSpj = noSpj;
    }

    /**
     * @return the bulan
     */
    public String getBulan() {
        return bulan;
    }

    /**
     * @param bulan the bulan to set
     */
    public void setBulan(String bulan) {
        this.bulan = bulan;
    }
    
    
    /**
     * @return the ketSpj
     */
    public String getKetSpj() {
        return ketSpj;
    }

    /**
     * @param ketSpj the ketSpj to set
     */
    public void setKetSpj(String ketSpj) {
        this.ketSpj = ketSpj;
    }

    /**
     * @return the idJour
     */
    public Integer getIdJour() {
        return idJour;
    }

    /**
     * @param idJour the idJour to set
     */
    public void setIdJour(Integer idJour) {
        this.idJour = idJour;
    }

  

    /**
     * @return the spjnihil
     */
    public boolean isSpjnihil() {
        return spjnihil;
    }

    /**
     * @param spjnihil the spjnihil to set
     */
    public void setSpjnihil(boolean spjnihil) {
        this.spjnihil = spjnihil;
    }

    /**
     * @return the bendahara
     */
    public Bendahara getBendahara() {
        return bendahara;
    }

    /**
     * @param bendahara the bendahara to set
     */
    public void setBendahara(Bendahara bendahara) {
        this.bendahara = bendahara;
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
     * @return the spjrinci
     */
    public SpjRinci getSpjrinci() {
        return spjrinci;
    }

    /**
     * @param spjrinci the spjrinci to set
     */
    public void setSpjrinci(SpjRinci spjrinci) {
        this.spjrinci = spjrinci;
    }

    /**
     * @return the nilai_UPGUTU
     */
    public BigDecimal getNilai_UPGUTU() {
        return nilai_UPGUTU;
    }

    /**
     * @param nilai_UPGUTU the nilai_UPGUTU to set
     */
    public void setNilai_UPGUTU(BigDecimal nilai_UPGUTU) {
        this.nilai_UPGUTU = nilai_UPGUTU;
    }

    /**
     * @return the nilaiSpp
     */
    public BigDecimal getNilaiSpp() {
        return nilaiSpp;
    }

    /**
     * @param nilaiSpp the nilaiSpp to set
     */
    public void setNilaiSpp(BigDecimal nilaiSpp) {
        this.nilaiSpp = nilaiSpp;
    }

    /**
     * @return the sudah_spj
     */
    public BigDecimal getSudah_spj() {
        return sudah_spj;
    }

    /**
     * @param sudah_spj the sudah_spj to set
     */
    public void setSudah_spj(BigDecimal sudah_spj) {
        this.sudah_spj = sudah_spj;
    }

    /**
     * @return the SISA_spj
     */
    public BigDecimal getSISA_spj() {
        return SISA_spj;
    }

    /**
     * @param SISA_spj the SISA_spj to set
     */
    public void setSISA_spj(BigDecimal SISA_spj) {
        this.SISA_spj = SISA_spj;
    }

    /**
     * @return the nilai_spj
     */
    public BigDecimal getNilai_spj() {
        return nilai_spj;
    }

    /**
     * @param nilai_spj the nilai_spj to set
     */
    public void setNilai_spj(BigDecimal nilai_spj) {
        this.nilai_spj = nilai_spj;
    }

    /**
     * @return the nilaiSpjUp
     */
    public BigDecimal getNilaiSpjUp() {
        return nilaiSpjUp;
    }

    /**
     * @param nilaiSpjUp the nilaiSpjUp to set
     */
    public void setNilaiSpjUp(BigDecimal nilaiSpjUp) {
        this.nilaiSpjUp = nilaiSpjUp;
    }

    /**
     * @return the nilaiSpjGu
     */
    public BigDecimal getNilaiSpjGu() {
        return nilaiSpjGu;
    }

    /**
     * @param nilaiSpjGu the nilaiSpjGu to set
     */
    public void setNilaiSpjGu(BigDecimal nilaiSpjGu) {
        this.nilaiSpjGu = nilaiSpjGu;
    }

    /**
     * @return the nilaiSpjTu
     */
    public BigDecimal getNilaiSpjTu() {
        return nilaiSpjTu;
    }

    /**
     * @param nilaiSpjTu the nilaiSpjTu to set
     */
    public void setNilaiSpjTu(BigDecimal nilaiSpjTu) {
        this.nilaiSpjTu = nilaiSpjTu;
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
     * @return the spjRinciKegiatan
     */
    public List<SpjRinci> getSpjRinciKegiatan() {
        return spjRinciKegiatan;
    }

    /**
     * @param spjRinciKegiatan the spjRinciKegiatan to set
     */
    public void setSpjRinciKegiatan(List<SpjRinci> spjRinciKegiatan) {
        this.spjRinciKegiatan = spjRinciKegiatan;
    }

    /**
     * @return the tanda
     */
    public String getTanda() {
        return tanda;
    }

    /**
     * @param tanda the tanda to set
     */
    public void setTanda(String tanda) {
        this.tanda = tanda;
    }

    /**
     * @return the nilaiSpjBtl
     */
    public BigDecimal getNilaiSpjBtl() {
        return nilaiSpjBtl;
    }

    /**
     * @param nilaiSpjBtl the nilaiSpjBtl to set
     */
    public void setNilaiSpjBtl(BigDecimal nilaiSpjBtl) {
        this.nilaiSpjBtl = nilaiSpjBtl;
    }

    /**
     * @return the nilaiSpjBl
     */
    public BigDecimal getNilaiSpjBl() {
        return nilaiSpjBl;
    }

    /**
     * @param nilaiSpjBl the nilaiSpjBl to set
     */
    public void setNilaiSpjBl(BigDecimal nilaiSpjBl) {
        this.nilaiSpjBl = nilaiSpjBl;
    }

    /**
     * @return the nihil
     */
    public String getNihil() {
        return nihil;
    }

    /**
     * @param nihil the nihil to set
     */
    public void setNihil(String nihil) {
        this.nihil = nihil;
    }

    /**
     * @return the statusCetak
     */
    public String getStatusCetak() {
        return statusCetak;
    }

    /**
     * @param statusCetak the statusCetak to set
     */
    public void setStatusCetak(String statusCetak) {
        this.statusCetak = statusCetak;
    }

    /**
     * @return the tglCetak
     */
    public String getTglCetak() {
        return tglCetak;
    }

    /**
     * @param tglCetak the tglCetak to set
     */
    public void setTglCetak(String tglCetak) {
        this.tglCetak = tglCetak;
    }

    /**
     * @return the nipPenggunaAnggaran
     */
    public String getNipPenggunaAnggaran() {
        return nipPenggunaAnggaran;
    }

    /**
     * @param nipPenggunaAnggaran the nipPenggunaAnggaran to set
     */
    public void setNipPenggunaAnggaran(String nipPenggunaAnggaran) {
        this.nipPenggunaAnggaran = nipPenggunaAnggaran;
    }

    /**
     * @return the nrkPenggunaAnggaran
     */
    public String getNrkPenggunaAnggaran() {
        return nrkPenggunaAnggaran;
    }

    /**
     * @param nrkPenggunaAnggaran the nrkPenggunaAnggaran to set
     */
    public void setNrkPenggunaAnggaran(String nrkPenggunaAnggaran) {
        this.nrkPenggunaAnggaran = nrkPenggunaAnggaran;
    }

    /**
     * @return the namaPenggunaAnggaran
     */
    public String getNamaPenggunaAnggaran() {
        return namaPenggunaAnggaran;
    }

    /**
     * @param namaPenggunaAnggaran the namaPenggunaAnggaran to set
     */
    public void setNamaPenggunaAnggaran(String namaPenggunaAnggaran) {
        this.namaPenggunaAnggaran = namaPenggunaAnggaran;
    }

    /**
     * @return the nipVerifikator
     */
    public String getNipVerifikator() {
        return nipVerifikator;
    }

    /**
     * @param nipVerifikator the nipVerifikator to set
     */
    public void setNipVerifikator(String nipVerifikator) {
        this.nipVerifikator = nipVerifikator;
    }

    /**
     * @return the nrkVerifikator
     */
    public String getNrkVerifikator() {
        return nrkVerifikator;
    }

    /**
     * @param nrkVerifikator the nrkVerifikator to set
     */
    public void setNrkVerifikator(String nrkVerifikator) {
        this.nrkVerifikator = nrkVerifikator;
    }

    /**
     * @return the namaVerifikator
     */
    public String getNamaVerifikator() {
        return namaVerifikator;
    }

    /**
     * @param namaVerifikator the namaVerifikator to set
     */
    public void setNamaVerifikator(String namaVerifikator) {
        this.namaVerifikator = namaVerifikator;
    }

    /**
     * @return the kodeBulan
     */
    public String getKodeBulan() {
        return kodeBulan;
    }

    /**
     * @return the namaBulan
     */
    public String getNamaBulan() {
        return namaBulan;
    }

    /**
     * @param kodeBulan the kodeBulan to set
     */
    public void setKodeBulan(String kodeBulan) {
        this.kodeBulan = kodeBulan;
    }

    /**
     * @param namaBulan the namaBulan to set
     */
    public void setNamaBulan(String namaBulan) {
        this.namaBulan = namaBulan;
    }

    /**
     * @return the namakeg
     */
    public String getNamakeg() {
        return namakeg;
    }

    /**
     * @param namakeg the namakeg to set
     */
    public void setNamakeg(String namakeg) {
        this.namakeg = namakeg;
    }

    /**
     * @return the akunbelanja
     */
    public String getAkunbelanja() {
        return akunbelanja;
    }

    /**
     * @param akunbelanja the akunbelanja to set
     */
    public void setAkunbelanja(String akunbelanja) {
        this.akunbelanja = akunbelanja;
    }

    /**
     * @return the akunjurnal
     */
    public String getAkunjurnal() {
        return akunjurnal;
    }

    /**
     * @param akunjurnal the akunjurnal to set
     */
    public void setAkunjurnal(String akunjurnal) {
        this.akunjurnal = akunjurnal;
    }

    /**
     * @return the kodekeg
     */
    public String getKodekeg() {
        return kodekeg;
    }

    /**
     * @param kodekeg the kodekeg to set
     */
    public void setKodekeg(String kodekeg) {
        this.kodekeg = kodekeg;
    }

    /**
     * @return the idskpd
     */
    public String getIdskpd() {
        return idskpd;
    }

    /**
     * @param idskpd the idskpd to set
     */
    public void setIdskpd(String idskpd) {
        this.idskpd = idskpd;
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
     * @return the sisaSpj
     */
    public BigDecimal getSisaSpj() {
        return sisaSpj;
    }

    /**
     * @param sisaSpj the sisaSpj to set
     */
    public void setSisaSpj(BigDecimal sisaSpj) {
        this.sisaSpj = sisaSpj;
    }

    /**
     * @return the idSpjsah
     */
    public String getIdSpjsah() {
        return idSpjsah;
    }

    /**
     * @param idSpjsah the idSpjsah to set
     */
    public void setIdSpjsah(String idSpjsah) {
        this.idSpjsah = idSpjsah;
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
     * @return the nilaiPaguUp
     */
    public BigDecimal getNilaiPaguUp() {
        return nilaiPaguUp;
    }

    /**
     * @param nilaiPaguUp the nilaiPaguUp to set
     */
    public void setNilaiPaguUp(BigDecimal nilaiPaguUp) {
        this.nilaiPaguUp = nilaiPaguUp;
    }

    /**
     * @return the totalSpp
     */
    public BigDecimal getTotalSpp() {
        return totalSpp;
    }

    /**
     * @param totalSpp the totalSpp to set
     */
    public void setTotalSpp(BigDecimal totalSpp) {
        this.totalSpp = totalSpp;
    }

    /**
     * @return the totalSpj
     */
    public BigDecimal getTotalSpj() {
        return totalSpj;
    }

    /**
     * @param totalSpj the totalSpj to set
     */
    public void setTotalSpj(BigDecimal totalSpj) {
        this.totalSpj = totalSpj;
    }

    /**
     * @return the entrySpj
     */
    public BigDecimal getEntrySpj() {
        return entrySpj;
    }

    /**
     * @param entrySpj the entrySpj to set
     */
    public void setEntrySpj(BigDecimal entrySpj) {
        this.entrySpj = entrySpj;
    }

    /**
     * @return the persentase
     */
    public String getPersentase() {
        return persentase;
    }

    /**
     * @param persentase the persentase to set
     */
    public void setPersentase(String persentase) {
        this.persentase = persentase;
    }

    /**
     * @return the sisaUang
     */
    public BigDecimal getSisaUang() {
        return sisaUang;
    }

    /**
     * @param sisaUang the sisaUang to set
     */
    public void setSisaUang(BigDecimal sisaUang) {
        this.sisaUang = sisaUang;
    }


}
