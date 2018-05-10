/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.dao.user;

import com.nepal.auctionhouse.dao.DAO;
import com.nepal.auctionhouse.entity.user.User;
import com.nepal.auctionhouse.entity.user.UserInfo;
import java.sql.SQLException;

/**
 *
 * @author Suzn
 */
public interface UserDAO extends DAO<UserInfo> {

    boolean isUserAvailable(User user) throws SQLException;

    boolean isUsernameAlreadyUsed(String username) throws SQLException;

    UserInfo loginUser(User user) throws SQLException;

}
