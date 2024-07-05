/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spp.model;

import java.sql.Timestamp;

/**
 *
 * @author M.Mustakim
 */
public class Sp2dPajakJson extends BaseModel{
    private Long id;
    private String idApp;
    private String kodeApp;
    private String tahun;
    private String kodeAction;
    private String jsonRequest;
    private String jsonResponse;
    private String error;
    private Timestamp timeRequest;
    private Timestamp timeResponse;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the idApp
     */
    public String getIdApp() {
        return idApp;
    }

    /**
     * @param idApp the idApp to set
     */
    public void setIdApp(String idApp) {
        this.idApp = idApp;
    }

    /**
     * @return the kodeApp
     */
    public String getKodeApp() {
        return kodeApp;
    }

    /**
     * @param kodeApp the kodeApp to set
     */
    public void setKodeApp(String kodeApp) {
        this.kodeApp = kodeApp;
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
     * @return the kodeAction
     */
    public String getKodeAction() {
        return kodeAction;
    }

    /**
     * @param kodeAction the kodeAction to set
     */
    public void setKodeAction(String kodeAction) {
        this.kodeAction = kodeAction;
    }

    /**
     * @return the jsonRequest
     */
    public String getJsonRequest() {
        return jsonRequest;
    }

    /**
     * @param jsonRequest the jsonRequest to set
     */
    public void setJsonRequest(String jsonRequest) {
        this.jsonRequest = jsonRequest;
    }

    /**
     * @return the jsonResponse
     */
    public String getJsonResponse() {
        return jsonResponse;
    }

    /**
     * @param jsonResponse the jsonResponse to set
     */
    public void setJsonResponse(String jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the timeRequest
     */
    public Timestamp getTimeRequest() {
        return timeRequest;
    }

    /**
     * @param timeRequest the timeRequest to set
     */
    public void setTimeRequest(Timestamp timeRequest) {
        this.timeRequest = timeRequest;
    }

    /**
     * @return the timeResponse
     */
    public Timestamp getTimeResponse() {
        return timeResponse;
    }

    /**
     * @param timeResponse the timeResponse to set
     */
    public void setTimeResponse(Timestamp timeResponse) {
        this.timeResponse = timeResponse;
    }
}