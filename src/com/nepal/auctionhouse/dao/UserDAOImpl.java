/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.dao;

import com.nepal.auctionhouse.entity.role.Role;
import com.nepal.auctionhouse.entity.user.User;
import com.nepal.auctionhouse.entity.user.UserInfo;
import com.nepal.auctionhouse.params.RoleParams;
import com.nepal.auctionhouse.params.UserParams;
import com.sujan.lms.common.util.Logy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Suzn
 */
public class UserDAOImpl implements UserDAO {

    private final String tableName;
    private final Connection connection;

    /**
     *
     * @param connection
     * @param tableName
     */
    public UserDAOImpl(Connection connection, String tableName) {
        this.tableName = tableName;
        this.connection = connection;
    }

    /**
     *
     * @param user
     * @return boolean
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isUserAvailable(User user) throws SQLException {
        return findById(user.getId()) != null;
    }

    /**
     *
     * @param t
     * @return Integer
     * @throws java.sql.SQLException
     */
    @Override
    public int save(UserInfo t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO " + tableName + ""
                + " values(?,?,?,?)")) {
            pst.setString(1, t.getName());
            pst.setString(2, t.getUsername());
            pst.setString(3, t.getPassword());
            pst.setInt(4, t.getRole().getId());
            id = pst.executeUpdate();
            Logy.d("User inserted successfully");
        }

        return id;

    }

    /**
     *
     * @param t
     * @return Integer
     * @throws java.sql.SQLException
     */
    @Override
    public int update(UserInfo t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement(
                "UPDATE " + tableName + " SET "
                + UserParams.NAME + "=?")) {
            pst.setString(1, t.getName());
            id = pst.executeUpdate();
            Logy.d("User updated successfully");
        }

        return id;
    }

    /**
     *
     * @param t
     * @return Integer
     * @throws java.sql.SQLException
     */
    @Override
    public int remove(UserInfo t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement(
                "DELETE from " + tableName + " WHERE " + UserParams.ID + "=?")) {
            pst.setInt(1, t.getId());
            id = pst.executeUpdate();
            Logy.d("User deleted successfully");
        }

        return id;
    }

    /**
     *
     * @param id
     * @return UserInfo
     * @throws java.sql.SQLException
     */
    @Override
    public UserInfo findById(int id) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(
                "SELECT usr.u_id, usr.u_name, usr.u_username, usr.u_password, "
                + "role.r_id, role.r_title FROM " + UserParams.TABLE_NAME + " "
                + "usr INNER JOIN " + RoleParams.TABLE_NAME + " role ON usr.u_role = role.r_id "
                + "WHERE usr.u_id = 1 LIMIT 1")) {
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                UserInfo userInfo = new UserInfo();
                userInfo.setId(resultSet.getInt(UserParams.ID));
                userInfo.setName(resultSet.getString(UserParams.NAME));
                userInfo.setUsername(resultSet.getString(UserParams.USERNAME));
                userInfo.setPassword(resultSet.getString(UserParams.PASSWORD));

                Role role = new Role();
                role.setId(resultSet.getInt(RoleParams.ID));
                role.setTitle(resultSet.getString(RoleParams.TITLE));
                userInfo.setRole(role);

                Logy.d("User fetched successfully");

                return userInfo;
            }
        }

        return null;
    }

    /**
     *
     * @return @throws java.sql.SQLException
     */
    @Override
    public List<UserInfo> findAll() throws SQLException {
        String query = "SELECT usr.u_id, usr.u_name, usr.u_username, usr.u_password, "
                + "role.r_id, role.r_title FROM " + UserParams.TABLE_NAME + " "
                + "usr INNER JOIN " + RoleParams.TABLE_NAME + " role ON usr.u_role = role.r_id "
                + " ORDER BY DESC usr.u_id";
        List<UserInfo> userInfoList = new ArrayList<>();

        try (Statement pst = connection.createStatement()) {
            ResultSet resultSet = pst.executeQuery(query);
            while (resultSet.next()) {
                UserInfo userInfo = new UserInfo();
                userInfo.setId(resultSet.getInt(UserParams.ID));
                userInfo.setName(resultSet.getString(UserParams.NAME));
                userInfo.setUsername(resultSet.getString(UserParams.USERNAME));
                userInfo.setPassword(resultSet.getString(UserParams.PASSWORD));

                Role role = new Role();
                role.setId(resultSet.getInt(RoleParams.ID));
                role.setTitle(resultSet.getString(RoleParams.TITLE));
                userInfo.setRole(role);

                Logy.d("User fetched successfully");

                userInfoList.add(userInfo);
            }
        }

        return userInfoList;
    }

    /**
     *
     * @param user
     * @return UserInfo
     * @throws java.sql.SQLException
     */
    @Override
    public UserInfo loginUser(User user) throws SQLException {
        String query = "SELECT usr.u_id, usr.u_name, usr.u_username, usr.u_password, "
                + "role.r_id, role.r_title FROM " + UserParams.TABLE_NAME + " "
                + "usr INNER JOIN " + RoleParams.TABLE_NAME + " role ON usr.u_role = role.r_id "
                + "WHERE usr.u_username =? AND usr.u_password=? LIMIT 1";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                UserInfo userInfo = new UserInfo();
                userInfo.setId(resultSet.getInt(UserParams.ID));
                userInfo.setName(resultSet.getString(UserParams.NAME));
                userInfo.setUsername(resultSet.getString(UserParams.USERNAME));
                userInfo.setPassword(resultSet.getString(UserParams.PASSWORD));

                Role role = new Role();
                role.setId(resultSet.getInt(RoleParams.ID));
                role.setTitle(resultSet.getString(RoleParams.TITLE));
                userInfo.setRole(role);

                Logy.d("User login successfully");

                return userInfo;
            }

        }

        return null;
    }

    /**
     *
     * @param username
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isUsernameAlreadyUsed(String username) throws SQLException {

        String query = "SELECT u_username from " + UserParams.TABLE_NAME + " LIMIT 1";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, username);
            ResultSet resultSet = pst.executeQuery();

            return resultSet.next();
        }

    }

}
