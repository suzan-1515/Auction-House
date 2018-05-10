/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.bll.auction;

import com.nepal.auctionhouse.dao.auction.AuctionMetaDAO;
import com.nepal.auctionhouse.dao.auction.AuctionMetaDAOImpl;
import com.nepal.auctionhouse.database.DBConnection;
import com.nepal.auctionhouse.entity.AuctionMeta;
import com.nepal.auctionhouse.exception.DuplicateRecordException;
import com.nepal.auctionhouse.exception.RecordNotFoundException;
import com.nepal.auctionhouse.params.AuctionMetaParams;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Suzn
 */
public class AuctionMetaBLL {

    /**
     *
     * @param auctionMeta
     * @return
     * @throws java.sql.SQLException
     * @throws com.nepal.auctionhouse.exception.DuplicateRecordException
     */
    public static int insertAuctionMeta(AuctionMeta auctionMeta) throws SQLException,
            DuplicateRecordException {
        Connection con = DBConnection.geConnection();

        AuctionMetaDAO auctionMetaDAO = new AuctionMetaDAOImpl(con, AuctionMetaParams.TABLE_NAME);
        if (auctionMetaDAO.isAuctionMetaAvailable(auctionMeta)) {
            throw new DuplicateRecordException();
        }
        return auctionMetaDAO.save(auctionMeta);
    }

    /**
     *
     * @param auctionMeta
     * @return
     * @throws com.nepal.auctionhouse.exception.RecordNotFoundException
     * @throws java.sql.SQLException
     */
    public static int updateAuctionMeta(AuctionMeta auctionMeta) throws RecordNotFoundException,
            SQLException {
        Connection con = DBConnection.geConnection();

        AuctionMetaDAO auctionMetaDAO = new AuctionMetaDAOImpl(con, AuctionMetaParams.TABLE_NAME);

        if (!auctionMetaDAO.isAuctionMetaAvailable(auctionMeta)) {
            throw new RecordNotFoundException();
        }

        return auctionMetaDAO.update(auctionMeta);
    }

    /**
     *
     * @param auctionMeta
     * @return
     * @throws com.nepal.auctionhouse.exception.RecordNotFoundException
     * @throws java.sql.SQLException
     *
     */
    public static int deleteAuctionMeta(AuctionMeta auctionMeta) throws RecordNotFoundException,
            SQLException {
        Connection con = DBConnection.geConnection();

        AuctionMetaDAO auctionMetaDAO = new AuctionMetaDAOImpl(con, AuctionMetaParams.TABLE_NAME);

        if (!auctionMetaDAO.isAuctionMetaAvailable(auctionMeta)) {
            throw new RecordNotFoundException();
        }
        return auctionMetaDAO.remove(auctionMeta);
    }

    /**
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    public static AuctionMeta getAuctionMetaById(int id) throws SQLException {
        Connection con = DBConnection.geConnection();
        AuctionMetaDAO auctionMetaDAO = new AuctionMetaDAOImpl(con, AuctionMetaParams.TABLE_NAME);

        return auctionMetaDAO.findById(id);
    }

    /**
     *
     * @return @throws java.sql.SQLException
     */
    public static List<AuctionMeta> getAllAuctionMeta() throws SQLException {
        Connection con = DBConnection.geConnection();
        AuctionMetaDAO auctionMetaDAO = new AuctionMetaDAOImpl(con, AuctionMetaParams.TABLE_NAME);

        return auctionMetaDAO.findAll();
    }

    /**
     *
     * @param auctionMeta
     * @return
     * @throws java.sql.SQLException
     *
     */
    public static boolean isAuctionMetaAvailable(AuctionMeta auctionMeta) throws SQLException {
        return getAuctionMetaById(auctionMeta.getId()) != null;
    }

}
