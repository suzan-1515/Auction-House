/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.access;

import com.nepal.auctionhouse.entity.user.UserInfo;

/**
 *
 * @author Suzn
 */
public class UserAccess {

    public static final int ADMIN = 1;
    public static final int USER = 2;

    public boolean isAdmin(UserInfo userInfo) {
        return userInfo.getRole().getId() == ADMIN;
    }

    public boolean isUser(UserInfo userInfo) {
        return userInfo.getRole().getId() == USER;
    }

}
