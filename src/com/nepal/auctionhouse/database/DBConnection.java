/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.database;

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
        return DriverManager.getConnection(url + unicode, user, pass);
    }
}
