/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.bll.lot;

import com.nepal.auctionhouse.dao.lot.LotDAO;
import com.nepal.auctionhouse.dao.lot.LotDAOImpl;
import com.nepal.auctionhouse.database.DBConnection;
import com.nepal.auctionhouse.entity.Lot;
import com.nepal.auctionhouse.exception.DuplicateRecordException;
import com.nepal.auctionhouse.exception.RecordNotFoundException;
import com.nepal.auctionhouse.params.LotParams;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Suzn
 */
public class LotBLL {

    /**
     *
     * @param lot
     * @return
     * @throws java.sql.SQLException
     * @throws com.nepal.auctionhouse.exception.DuplicateRecordException
     */
    public static int insertLot(Lot lot) throws SQLException,
            DuplicateRecordException {
        Connection con = DBConnection.geConnection();

        LotDAO lotDAO = new LotDAOImpl(con, LotParams.TABLE_NAME);
        if (lotDAO.isLotAvailable(lot)) {
            throw new DuplicateRecordException();
        }
        return lotDAO.save(lot);
    }

    /**
     *
     * @param lot
     * @return
     * @throws com.nepal.auctionhouse.exception.RecordNotFoundException
     * @throws java.sql.SQLException
     */
    public static int updateLot(Lot lot) throws RecordNotFoundException,
            SQLException {
        Connection con = DBConnection.geConnection();

        LotDAO lotDAO = new LotDAOImpl(con, LotParams.TABLE_NAME);

        if (!lotDAO.isLotAvailable(lot)) {
            throw new RecordNotFoundException();
        }

        return lotDAO.update(lot);
    }

    /**
     *
     * @param lot
     * @return
     * @throws com.nepal.auctionhouse.exception.RecordNotFoundException
     * @throws java.sql.SQLException
     *
     */
    public static int deleteLot(Lot lot) throws RecordNotFoundException,
            SQLException {
        Connection con = DBConnection.geConnection();

        LotDAO lotDAO = new LotDAOImpl(con, LotParams.TABLE_NAME);

        if (!lotDAO.isLotAvailable(lot)) {
            throw new RecordNotFoundException();
        }
        return lotDAO.remove(lot);
    }

    /**
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    public static Lot getLotById(int id) throws SQLException {
        Connection con = DBConnection.geConnection();
        LotDAO lotDAO = new LotDAOImpl(con, LotParams.TABLE_NAME);

        return lotDAO.findById(id);
    }

    /**
     *
     * @return @throws java.sql.SQLException
     */
    public static List<Lot> getAllLot() throws SQLException {
        Connection con = DBConnection.geConnection();
        LotDAO lotDAO = new LotDAOImpl(con, LotParams.TABLE_NAME);

        return lotDAO.findAll();
    }

    /**
     *
     * @param lot
     * @return
     * @throws java.sql.SQLException
     *
     */
    public static boolean isLotAvailable(Lot lot) throws SQLException {
        return getLotById(lot.getId()) != null;
    }

}
