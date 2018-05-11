/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.ui;

import com.nepal.auctionhouse.access.UserAccess;
import com.nepal.auctionhouse.entity.user.UserInfo;
import com.nepal.auctionhouse.view.UserPermission;

/**
 *
 * @author Suzn
 */
public abstract class BaseUserPanel extends javax.swing.JPanel implements UserPermission {

    private final UserAccess userAccess;

    public BaseUserPanel() {
        this.userAccess = new UserAccess();
    }

    @Override
    public void setupUserView(UserInfo userInfo) {
        if (userAccess.isAdmin(userInfo)) {
            setupAdminView();
        } else if (userAccess.isUser(userInfo)) {
            setupUserView();
        }
    }

    protected abstract void setupAdminView();

    protected abstract void setupUserView();

}
