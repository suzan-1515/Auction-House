/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.ui.dashboard;

import com.nepal.auctionhouse.access.UserAccess;
import com.nepal.auctionhouse.entity.user.UserInfo;
import com.nepal.auctionhouse.view.UserPermission;
import javax.swing.JFrame;

/**
 *
 * @author Suzn
 */
public abstract class BaseDashboard extends JFrame implements UserPermission {

    private final UserAccess userAccess;

    public BaseDashboard(String title) {
        super(title);
        userAccess = new UserAccess();
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
