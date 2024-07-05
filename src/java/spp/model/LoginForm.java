/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.model;

/**
 *
 * @author User
 */
public class LoginForm extends BaseModel {
    private String username;
    private String password;
    private String tahun;
    
    private String passwordBaru;
    private String passwordBaruVerify;
    private String passwordLama;
    
    private String berita;
    private String dari;
    private String kepada;
    private String tanggal;
    private String namaPdf;
    private String judulPdf;
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return the passwordBaru
     */
    public String getPasswordBaru() {
        return passwordBaru;
    }

    /**
     * @param passwordBaru the passwordBaru to set
     */
    public void setPasswordBaru(String passwordBaru) {
        this.passwordBaru = passwordBaru;
    }

    /**
     * @return the passwordBaruVerify
     */
    public String getPasswordBaruVerify() {
        return passwordBaruVerify;
    }

    /**
     * @param passwordBaruVerify the passwordBaruVerify to set
     */
    public void setPasswordBaruVerify(String passwordBaruVerify) {
        this.passwordBaruVerify = passwordBaruVerify;
    }

    /**
     * @return the passwordLama
     */
    public String getPasswordLama() {
        return passwordLama;
    }

    /**
     * @param passwordLama the passwordLama to set
     */
    public void setPasswordLama(String passwordLama) {
        this.passwordLama = passwordLama;
    }

    /**
     * @return the berita
     */
    public String getBerita() {
        return berita;
    }

    /**
     * @param berita the berita to set
     */
    public void setBerita(String berita) {
        this.berita = berita;
    }

    /**
     * @return the dari
     */
    public String getDari() {
        return dari;
    }

    /**
     * @param dari the dari to set
     */
    public void setDari(String dari) {
        this.dari = dari;
    }

    /**
     * @return the kepada
     */
    public String getKepada() {
        return kepada;
    }

    /**
     * @param kepada the kepada to set
     */
    public void setKepada(String kepada) {
        this.kepada = kepada;
    }

    /**
     * @return the tanggal
     */
    public String getTanggal() {
        return tanggal;
    }

    /**
     * @param tanggal the tanggal to set
     */
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
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
