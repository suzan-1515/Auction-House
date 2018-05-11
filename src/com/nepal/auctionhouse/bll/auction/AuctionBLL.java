/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.bll.auction;

import com.nepal.auctionhouse.dao.auction.AuctionDAO;
import com.nepal.auctionhouse.dao.auction.AuctionDAOImpl;
import com.nepal.auctionhouse.database.DBConnection;
import com.nepal.auctionhouse.entity.Auction;
import com.nepal.auctionhouse.exception.DuplicateRecordException;
import com.nepal.auctionhouse.exception.RecordNotFoundException;
import com.nepal.auctionhouse.params.AuctionParams;
import com.nepal.auctionhouse.util.Utils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Suzn
 */
public class AuctionBLL {

    /**
     *
     * @param auction
     * @return
     * @throws java.sql.SQLException
     * @throws com.nepal.auctionhouse.exception.DuplicateRecordException
     */
    public static int insertAuction(Auction auction) throws SQLException,
            DuplicateRecordException {
        Connection con = DBConnection.geConnection();

        AuctionDAO auctionDAO = new AuctionDAOImpl(con, AuctionParams.TABLE_NAME);
        if (auctionDAO.isAuctionAvailable(auction)) {
            throw new DuplicateRecordException();
        }
        return auctionDAO.save(auction);
    }

    /**
     *
     * @param auction
     * @return
     * @throws com.nepal.auctionhouse.exception.RecordNotFoundException
     * @throws java.sql.SQLException
     */
    public static int updateAuction(Auction auction) throws RecordNotFoundException,
            SQLException {
        Connection con = DBConnection.geConnection();

        AuctionDAO auctionDAO = new AuctionDAOImpl(con, AuctionParams.TABLE_NAME);

        if (!auctionDAO.isAuctionAvailable(auction)) {
            throw new RecordNotFoundException();
        }

        return auctionDAO.update(auction);
    }

    /**
     *
     * @param auction
     * @return
     * @throws com.nepal.auctionhouse.exception.RecordNotFoundException
     * @throws java.sql.SQLException
     *
     */
    public static int deleteAuction(Auction auction) throws RecordNotFoundException,
            SQLException {
        Connection con = DBConnection.geConnection();

        AuctionDAO auctionDAO = new AuctionDAOImpl(con, AuctionParams.TABLE_NAME);

        if (!auctionDAO.isAuctionAvailable(auction)) {
            throw new RecordNotFoundException();
        }
        return auctionDAO.remove(auction);
    }

    /**
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    public static Auction getAuctionById(int id) throws SQLException {
        Connection con = DBConnection.geConnection();
        AuctionDAO auctionDAO = new AuctionDAOImpl(con, AuctionParams.TABLE_NAME);

        return auctionDAO.findById(id);
    }

    /**
     *
     * @return @throws java.sql.SQLException
     */
    public static List<Auction> getAllAuction() throws SQLException {
        Connection con = DBConnection.geConnection();
        AuctionDAO auctionDAO = new AuctionDAOImpl(con, AuctionParams.TABLE_NAME);

        return auctionDAO.findAll();
    }

    /**
     *
     * @param date
     * @return
     * @throws SQLException
     */
    public static List<Auction> getUpcomingAuction(Date date) throws SQLException {
        Connection con = DBConnection.geConnection();
        AuctionDAO auctionDAO = new AuctionDAOImpl(con, AuctionParams.TABLE_NAME);

        return auctionDAO.getUpcomingAuction(date);
    }

    /**
     *
     * @param auction
     * @return
     * @throws java.sql.SQLException
     *
     */
    public static boolean isAuctionAvailable(Auction auction) throws SQLException {
        return getAuctionById(auction.getId()) != null;
    }

    /**
     *
     * @param date
     * @return
     */
    public static String getAuctionState(Date date) {
        return Utils.isBeforeCurrentDate(date) ? AuctionParams.STATE_PAST
                : AuctionParams.STATE_UPCOMING;
    }

    /**
     *
     * @param s
     * @return
     * @throws java.sql.SQLException
     */
    public static boolean isUpcomingAuction(Auction s) throws SQLException {
        Auction auction = getAuctionById(s.getId());
        return Utils.isAfterCurrentDate(auction.getDate());
    }

}
