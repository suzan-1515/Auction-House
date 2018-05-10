/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.bll.user;

import com.nepal.auctionhouse.dao.user.UserDAO;
import com.nepal.auctionhouse.dao.user.UserDAOImpl;
import com.nepal.auctionhouse.database.DBConnection;
import com.nepal.auctionhouse.entity.user.User;
import com.nepal.auctionhouse.entity.user.UserInfo;
import com.nepal.auctionhouse.exception.RecordNotFoundException;
import com.nepal.auctionhouse.exception.UnAvailableUsernameException;
import com.nepal.auctionhouse.params.RoleParams;
import com.nepal.auctionhouse.params.UserParams;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Suzn
 */
public class UserBLL {

    /**
     *
     * @param userInfo
     * @return
     * @throws java.sql.SQLException
     * @throws com.nepal.auctionhouse.exception.UnAvailableUsernameException
     */
    public static int insertUser(UserInfo userInfo) throws SQLException,
            UnAvailableUsernameException {
        Connection con = DBConnection.geConnection();

        UserDAO userDAO = new UserDAOImpl(con, UserParams.TABLE_NAME);
        if (userDAO.isUsernameAlreadyUsed(userInfo.getUsername())) {
            throw new UnAvailableUsernameException();
        }
        return userDAO.save(userInfo);
    }

    /**
     *
     * @param userInfo
     * @return
     * @throws com.nepal.auctionhouse.exception.RecordNotFoundException
     * @throws java.sql.SQLException
     */
    public static int updateUser(UserInfo userInfo) throws RecordNotFoundException,
            SQLException {
        Connection con = DBConnection.geConnection();

        UserDAO userDAO = new UserDAOImpl(con, UserParams.TABLE_NAME);

        if (!userDAO.isUserAvailable(userInfo)) {
            throw new RecordNotFoundException();
        }

        return userDAO.update(userInfo);
    }

    /**
     *
     * @param userInfo
     * @return
     * @throws com.nepal.auctionhouse.exception.RecordNotFoundException
     * @throws java.sql.SQLException
     *
     */
    public static int deleteUser(UserInfo userInfo) throws RecordNotFoundException,
            SQLException {
        Connection con = DBConnection.geConnection();

        UserDAO userDAO = new UserDAOImpl(con, UserParams.TABLE_NAME);

        if (!userDAO.isUserAvailable(userInfo)) {
            throw new RecordNotFoundException();
        }
        return userDAO.remove(userInfo);
    }

    /**
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    public static UserInfo getUserById(int id) throws SQLException {
        Connection con = DBConnection.geConnection();
        UserDAO userDAO = new UserDAOImpl(con, UserParams.TABLE_NAME);

        return userDAO.findById(id);
    }

    /**
     *
     * @return @throws java.sql.SQLException
     */
    public static List<UserInfo> getAllUser() throws SQLException {
        Connection con = DBConnection.geConnection();
        UserDAO userDAO = new UserDAOImpl(con, UserParams.TABLE_NAME);

        return userDAO.findAll();
    }

    /**
     *
     * @param user
     * @return
     * @throws java.sql.SQLException
     *
     */
    public static boolean isUserAvailable(User user) throws SQLException {
        return getUserById(user.getId()) != null;
    }

    /**
     *
     * @param user
     * @return
     * @throws java.sql.SQLException
     *
     */
    public static UserInfo loginUser(User user) throws SQLException {
        Connection con = DBConnection.geConnection();
        UserDAO userDAO = new UserDAOImpl(con, UserParams.TABLE_NAME);

        return userDAO.loginUser(user);

    }

    /**
     *
     * @param loginUser
     * @return
     */
    public static boolean isUserAdmin(UserInfo loginUser) {
        if (loginUser == null) {
            throw new NullPointerException("User cannot be null");
        }
        if (loginUser.getRole() == null) {
            throw new NullPointerException("Role cannot be null");
        }

        return loginUser.getRole().getTitle().equalsIgnoreCase(RoleParams.ROLE_ADMIN);
    }

    public static boolean isUserUser(UserInfo loginUser) {
        if (loginUser == null) {
            throw new NullPointerException("User cannot be null");
        }
        if (loginUser.getRole() == null) {
            throw new NullPointerException("Role cannot be null");
        }

        return loginUser.getRole().getTitle().equalsIgnoreCase(RoleParams.ROLE_USER);
    }

}
