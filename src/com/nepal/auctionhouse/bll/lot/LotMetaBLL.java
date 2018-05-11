/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.bll.lot;

import com.nepal.auctionhouse.dao.lot.LotMetaDAOImpl;
import com.nepal.auctionhouse.database.DBConnection;
import com.nepal.auctionhouse.entity.LotMeta;
import com.nepal.auctionhouse.entity.Lot;
import com.nepal.auctionhouse.exception.DuplicateRecordException;
import com.nepal.auctionhouse.exception.RecordNotFoundException;
import com.nepal.auctionhouse.params.LotMetaParams;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.nepal.auctionhouse.dao.lot.LotMetaDAO;
import com.nepal.auctionhouse.entity.Auction;

/**
 *
 * @author Suzn
 */
public class LotMetaBLL {

    /**
     *
     * @param auctionMeta
     * @return
     * @throws java.sql.SQLException
     * @throws com.nepal.auctionhouse.exception.DuplicateRecordException
     */
    public static int insertLotMeta(LotMeta auctionMeta) throws SQLException,
            DuplicateRecordException {
        Connection con = DBConnection.geConnection();

        LotMetaDAO auctionMetaDAO = new LotMetaDAOImpl(con, LotMetaParams.TABLE_NAME);
        if (auctionMetaDAO.isLotMetaAvailable(auctionMeta)) {
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
    public static int updateLotMeta(LotMeta auctionMeta) throws RecordNotFoundException,
            SQLException {
        Connection con = DBConnection.geConnection();

        LotMetaDAO auctionMetaDAO = new LotMetaDAOImpl(con, LotMetaParams.TABLE_NAME);

        if (!auctionMetaDAO.isLotMetaAvailable(auctionMeta)) {
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
    public static int deleteLotMeta(LotMeta auctionMeta) throws RecordNotFoundException,
            SQLException {
        Connection con = DBConnection.geConnection();

        LotMetaDAO auctionMetaDAO = new LotMetaDAOImpl(con, LotMetaParams.TABLE_NAME);

        if (!auctionMetaDAO.isLotMetaAvailable(auctionMeta)) {
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
    public static LotMeta getLotMetaById(int id) throws SQLException {
        Connection con = DBConnection.geConnection();
        LotMetaDAO auctionMetaDAO = new LotMetaDAOImpl(con, LotMetaParams.TABLE_NAME);

        return auctionMetaDAO.findById(id);
    }

    /**
     *
     * @return @throws java.sql.SQLException
     */
    public static List<LotMeta> getAllLotMeta() throws SQLException {
        Connection con = DBConnection.geConnection();
        LotMetaDAO auctionMetaDAO = new LotMetaDAOImpl(con, LotMetaParams.TABLE_NAME);

        return auctionMetaDAO.findAll();
    }

    /**
     *
     * @param auctionMeta
     * @return
     * @throws java.sql.SQLException
     *
     */
    public static boolean isLotMetaAvailable(LotMeta auctionMeta) throws SQLException {
        return getLotMetaById(auctionMeta.getId()) != null;
    }

    public static Auction getAuctionAssignedForLot(Lot lot) throws SQLException {
        Connection con = DBConnection.geConnection();
        LotMetaDAO auctionMetaDAO = new LotMetaDAOImpl(con, LotMetaParams.TABLE_NAME);

        return auctionMetaDAO.getAuctionAssignedForLot(lot);
    }

    public static LotMeta getLotMetaByLotId(int id) throws SQLException {
        Connection con = DBConnection.geConnection();
        LotMetaDAO auctionMetaDAO = new LotMetaDAOImpl(con, LotMetaParams.TABLE_NAME);

        return auctionMetaDAO.getLotMetaByLotId(id);
    }

}
