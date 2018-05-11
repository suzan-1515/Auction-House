/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.dao.auction;

import com.nepal.auctionhouse.entity.Auction;
import com.nepal.auctionhouse.params.AuctionParams;
import com.nepal.auctionhouse.util.Logy;
import java.sql.Connection;
import java.sql.Date;
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
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO " + tableName + " "
                + "values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, t.getId());
            pst.setDate(2, t.getDate());
            pst.setString(3, t.getSlot());
            pst.setString(4, t.getVenue());
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);

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
                + AuctionParams.DATE + "=?,"
                + AuctionParams.SLOT + "=?,"
                + AuctionParams.VENUE + "=? "
                + "WHERE " + AuctionParams.ID + "=?")) {
            pst.setDate(1, t.getDate());
            pst.setString(2, t.getSlot());
            pst.setString(3, t.getVenue());
            pst.setInt(4, t.getId());
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
                "SELECT * FROM " + tableName + " WHERE " + AuctionParams.ID + " =?  LIMIT 1")) {
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
        String query = "SELECT * FROM " + tableName;
        List<Auction> auctionInfoList = new ArrayList<>();

        try (Statement pst = connection.createStatement()) {
            ResultSet resultSet = pst.executeQuery(query);
            while (resultSet.next()) {
                Auction auction = new Auction();
                auction.setId(resultSet.getInt(AuctionParams.ID));
                auction.setDate(resultSet.getDate(AuctionParams.DATE));
                auction.setSlot(resultSet.getString(AuctionParams.SLOT));
                auction.setVenue(resultSet.getString(AuctionParams.VENUE));

                auctionInfoList.add(auction);
            }
            Logy.d("Auction fetched successfully");
        }

        return auctionInfoList;
    }

    /**
     *
     * @param date
     * @return
     * @throws SQLException
     */
    @Override
    public List<Auction> getUpcomingAuction(Date date) throws SQLException {
        String query = "SELECT * FROM " + tableName + " WHERE " + AuctionParams.DATE + " > ?";
        List<Auction> auctionInfoList = new ArrayList<>();

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setDate(1, date);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Auction auction = new Auction();
                auction.setId(resultSet.getInt(AuctionParams.ID));
                auction.setDate(resultSet.getDate(AuctionParams.DATE));
                auction.setSlot(resultSet.getString(AuctionParams.SLOT));
                auction.setVenue(resultSet.getString(AuctionParams.VENUE));

                auctionInfoList.add(auction);
            }
            Logy.d("Auction fetched successfully");
        }

        return auctionInfoList;
    }

}
