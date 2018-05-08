/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.entity.user;

import java.sql.Timestamp;

/**
 *
 * @author Suzn
 */
public class UserInfo extends User {

    private Timestamp loginTimestamp;
    private Timestamp logoutTimestamp;

    public UserInfo() {
    }

    
    public UserInfo(int id) {
        super(id);
    }

    /**
     * @return the loginTimestamp
     */
    public Timestamp getLoginTimestamp() {
        return loginTimestamp;
    }

    /**
     * @param loginTimestamp the loginTimestamp to set
     */
    public void setLoginTimestamp(Timestamp loginTimestamp) {
        this.loginTimestamp = loginTimestamp;
    }

    /**
     * @return the logoutTimestamp
     */
    public Timestamp getLogoutTimestamp() {
        return logoutTimestamp;
    }

    /**
     * @param logoutTimestamp the logoutTimestamp to set
     */
    public void setLogoutTimestamp(Timestamp logoutTimestamp) {
        this.logoutTimestamp = logoutTimestamp;
    }

}
