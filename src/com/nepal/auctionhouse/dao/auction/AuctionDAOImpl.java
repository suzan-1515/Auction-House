/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.dao.auction;

import com.nepal.auctionhouse.entity.Auction;
import com.nepal.auctionhouse.params.AuctionParams;
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
public class AuctionDAOImpl implements AuctionDAO {

    private final String tableName;
    private final Connection connection;

    /**
     *
     * @param connection
     * @param tableName
     */
    public AuctionDAOImpl(Connection connection, String tableName) {
        this.tableName = tableName;
        this.connection = connection;
    }

    /**
     *
     * @param auction
     * @return boolean
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isAuctionAvailable(Auction auction) throws SQLException {
        return findById(auction.getId()) != null;
    }

    /**
     *
     * @param t
     * @return Integer
     * @throws java.sql.SQLException
     */
    @Override
    public int save(Auction t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO " + tableName + ""
                + " values(?,?,?)")) {
            pst.setDate(1, t.getDate());
            pst.setString(2, t.getSlot());
            pst.setString(3, t.getVenue());
            id = pst.executeUpdate();

            Logy.d("Auction inserted successfully");
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
    public int update(Auction t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement(
                "UPDATE " + tableName + " SET "
                + AuctionParams.DATE + "=?"
                + AuctionParams.SLOT + "=?"
                + AuctionParams.VENUE + "=?")) {
            pst.setDate(1, t.getDate());
            pst.setString(2, t.getSlot());
            pst.setString(3, t.getVenue());
            id = pst.executeUpdate();

            Logy.d("Auction updated successfully");
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
    public int remove(Auction t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement(
                "DELETE from " + tableName + " WHERE " + AuctionParams.ID + "=?")) {
            pst.setInt(1, t.getId());
            id = pst.executeUpdate();

            Logy.d("Auction deleted successfully");
        }

        return id;
    }

    /**
     *
     * @param id
     * @return Auction
     * @throws java.sql.SQLException
     */
    @Override
    public Auction findById(int id) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(
                "SELECT * FROM " + AuctionParams.TABLE_NAME + " WHERE a_id =?  LIMIT 1")) {
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Auction auctionInfo = new Auction();
                auctionInfo.setId(resultSet.getInt(AuctionParams.ID));
                auctionInfo.setDate(resultSet.getDate(AuctionParams.DATE));
                auctionInfo.setSlot(resultSet.getString(AuctionParams.SLOT));
                auctionInfo.setVenue(resultSet.getString(AuctionParams.VENUE));

                Logy.d("Auction fetched successfully");

                return auctionInfo;
            }
        }

        return null;
    }

    /**
     *
     * @return @throws java.sql.SQLException
     */
    @Override
    public List<Auction> findAll() throws SQLException {
        String query = "SELECT * FROM " + AuctionParams.TABLE_NAME + " ORDER BY DESC a_id";
        List<Auction> auctionInfoList = new ArrayList<>();

        try (Statement pst = connection.createStatement()) {
            ResultSet resultSet = pst.executeQuery(query);
            while (resultSet.next()) {
                Auction auction = new Auction();
                auction.setId(resultSet.getInt(AuctionParams.ID));
                auction.setDate(resultSet.getDate(AuctionParams.DATE));
                auction.setSlot(resultSet.getString(AuctionParams.SLOT));
                auction.setVenue(resultSet.getString(AuctionParams.VENUE));

                Logy.d("Auction fetched successfully");

                auctionInfoList.add(auction);
            }
        }

        return auctionInfoList;
    }

}