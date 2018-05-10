/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.dao.lot;

import com.nepal.auctionhouse.entity.LotType;
import com.nepal.auctionhouse.params.LotTypeParams;
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
public class LotTypeDAOImpl implements LotTypeDAO {

    private final String tableName;
    private final Connection connection;

    /**
     *
     * @param connection
     * @param tableName
     */
    public LotTypeDAOImpl(Connection connection, String tableName) {
        this.tableName = tableName;
        this.connection = connection;
    }

    /**
     *
     * @param lotType
     * @return boolean
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isLotTypeAvailable(LotType lotType) throws SQLException {
        return findById(lotType.getId()) != null;
    }

    /**
     *
     * @param t
     * @return Integer
     * @throws java.sql.SQLException
     */
    @Override
    public int save(LotType t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO " + tableName + ""
                + " values(?)")) {
            pst.setString(1, t.getTitle());
            id = pst.executeUpdate();

            Logy.d("LotType inserted successfully");
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
    public int update(LotType t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement(
                "UPDATE " + tableName + " SET "
                + LotTypeParams.TITLE + "=?")) {
            pst.setString(1, t.getTitle());
            id = pst.executeUpdate();

            Logy.d("LotType updated successfully");
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
    public int remove(LotType t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement(
                "DELETE from " + tableName + " WHERE " + LotTypeParams.ID + "=?")) {
            pst.setInt(1, t.getId());
            id = pst.executeUpdate();

            Logy.d("LotType deleted successfully");
        }

        return id;
    }

    /**
     *
     * @param id
     * @return LotType
     * @throws java.sql.SQLException
     */
    @Override
    public LotType findById(int id) throws SQLException {
        String query = "SELECT * FROM `" + tableName + "` WHERE " + LotTypeParams.ID + " =? LIMIT 1";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                LotType lotType = new LotType();
                lotType.setId(resultSet.getInt(LotTypeParams.ID));
                lotType.setTitle(resultSet.getString(LotTypeParams.TITLE));

                Logy.d("LotType fetched successfully");

                return lotType;
            }
        }

        return null;
    }

    /**
     *
     * @return @throws java.sql.SQLException
     */
    @Override
    public List<LotType> findAll() throws SQLException {
        String query = "SELECT * FROM `" + tableName + "` ORDER BY DESC " + LotTypeParams.ID;
        List<LotType> auctionInfoList = new ArrayList<>();

        try (Statement pst = connection.createStatement()) {
            ResultSet resultSet = pst.executeQuery(query);
            while (resultSet.next()) {
                LotType lotType = new LotType();
                lotType.setId(resultSet.getInt(LotTypeParams.ID));
                lotType.setTitle(resultSet.getString(LotTypeParams.TITLE));

                Logy.d("LotType fetched successfully");

                auctionInfoList.add(lotType);
            }
        }

        return auctionInfoList;
    }

}
