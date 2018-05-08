/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.database;

import com.sujan.lms.common.util.Logy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sujan
 */
public class DBConnection {

    static String url = "jdbc:mysql://localhost:3306/auction_house";
    static String user = "root";
    static String pass = "";
    static String unicode = "?useUnicode=yes&characterEncoding=UTF-8";

    public static Connection geConnection() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            return DriverManager.getConnection(url + unicode, user, pass);
        } catch (ClassNotFoundException ex) {
            Logy.e(ex);
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw ex;
        }
    }
}
