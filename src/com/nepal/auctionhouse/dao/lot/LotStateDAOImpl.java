/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.dao.lot;

import com.nepal.auctionhouse.entity.LotState;
import com.nepal.auctionhouse.params.LotStateParams;
import com.nepal.auctionhouse.util.Logy;
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
public class LotStateDAOImpl implements LotStateDAO {

    private final String tableName;
    private final Connection connection;

    /**
     *
     * @param connection
     * @param tableName
     */
    public LotStateDAOImpl(Connection connection, String tableName) {
        this.tableName = tableName;
        this.connection = connection;
    }

    /**
     *
     * @param lotState
     * @return boolean
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isLotStateAvailable(LotState lotState) throws SQLException {
        return findById(lotState.getId()) != null;
    }

    /**
     *
     * @param t
     * @return Integer
     * @throws java.sql.SQLException
     */
    @Override
    public int save(LotState t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO " + tableName + ""
                + " values(?)",Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, t.getTitle());
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            rs.next();
            id = pst.getGeneratedKeys().getInt(1);

            Logy.d("LotState inserted successfully");
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
    public int update(LotState t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement(
                "UPDATE " + tableName + " SET "
                + LotStateParams.TITLE + "=? "
                + "WHERE " + LotStateParams.ID + "=?")) {
            pst.setString(1, t.getTitle());
            pst.setInt(2, t.getId());
            id = pst.executeUpdate();

            Logy.d("LotState updated successfully");
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
    public int remove(LotState t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement(
                "DELETE from " + tableName + " WHERE " + LotStateParams.ID + "=?")) {
            pst.setInt(1, t.getId());
            id = pst.executeUpdate();

            Logy.d("LotState deleted successfully");
        }

        return id;
    }

    /**
     *
     * @param id
     * @return LotState
     * @throws java.sql.SQLException
     */
    @Override
    public LotState findById(int id) throws SQLException {
        String query = "SELECT * FROM `" + tableName + "` WHERE " + LotStateParams.ID + " =? LIMIT 1";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                LotState lotState = new LotState();
                lotState.setId(resultSet.getInt(LotStateParams.ID));
                lotState.setTitle(resultSet.getString(LotStateParams.TITLE));

                Logy.d("LotState fetched successfully");

                return lotState;
            }
        }

        return null;
    }

    /**
     *
     * @return @throws java.sql.SQLException
     */
    @Override
    public List<LotState> findAll() throws SQLException {
        String query = "SELECT * FROM `" + tableName + "` ORDER BY DESC " + LotStateParams.ID;
        List<LotState> auctionInfoList = new ArrayList<>();

        try (Statement pst = connection.createStatement()) {
            ResultSet resultSet = pst.executeQuery(query);
            while (resultSet.next()) {
                LotState lotState = new LotState();
                lotState.setId(resultSet.getInt(LotStateParams.ID));
                lotState.setTitle(resultSet.getString(LotStateParams.TITLE));

                Logy.d("LotState fetched successfully");

                auctionInfoList.add(lotState);
            }
        }

        return auctionInfoList;
    }

}
