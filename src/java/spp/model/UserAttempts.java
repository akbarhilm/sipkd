/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp.model;

import java.sql.Timestamp;

/**
 *
 * @author zaenab
 */
public class UserAttempts {
  /*
    DROP TABLE SIPKD.USER_ATTEMPTS CASCADE CONSTRAINTS;

CREATE TABLE SIPKD.USER_ATTEMPTS
(
  ID            INTEGER                         NOT NULL,
  USERNAME      VARCHAR2(50 BYTE)               NOT NULL,
  ATTEMPTS      INTEGER,
  LASTMODIFIED  TIMESTAMP(6)
)
TABLESPACE TSPKD
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

    
    */
    private int id;
    private String username;
    private int attempts;
    private Timestamp lastModified;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

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
     * @return the attempts
     */
    public int getAttempts() {
        return attempts;
    }

    /**
     * @param attempts the attempts to set
     */
    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    /**
     * @return the lastModified
     */
    public Timestamp getLastModified() {
        return lastModified;
    }

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }
}
