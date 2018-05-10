/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.bll.lot;

import com.nepal.auctionhouse.dao.lot.LotStateDAO;
import com.nepal.auctionhouse.dao.lot.LotStateDAOImpl;
import com.nepal.auctionhouse.database.DBConnection;
import com.nepal.auctionhouse.entity.LotState;
import com.nepal.auctionhouse.exception.DuplicateRecordException;
import com.nepal.auctionhouse.exception.RecordNotFoundException;
import com.nepal.auctionhouse.params.LotStateParams;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Suzn
 */
public class LotStateBLL {

    /**
     *
     * @param lotState
     * @return
     * @throws java.sql.SQLException
     * @throws com.nepal.auctionhouse.exception.DuplicateRecordException
     */
    public static int insertLotState(LotState lotState) throws SQLException,
            DuplicateRecordException {
        Connection con = DBConnection.geConnection();

        LotStateDAO lotStateDAO = new LotStateDAOImpl(con, LotStateParams.TABLE_NAME);
        if (lotStateDAO.isLotStateAvailable(lotState)) {
            throw new DuplicateRecordException();
        }
        return lotStateDAO.save(lotState);
    }

    /**
     *
     * @param lotState
     * @return
     * @throws com.nepal.auctionhouse.exception.RecordNotFoundException
     * @throws java.sql.SQLException
     */
    public static int updateLotState(LotState lotState) throws RecordNotFoundException,
            SQLException {
        Connection con = DBConnection.geConnection();

        LotStateDAO lotStateDAO = new LotStateDAOImpl(con, LotStateParams.TABLE_NAME);

        if (!lotStateDAO.isLotStateAvailable(lotState)) {
            throw new RecordNotFoundException();
        }

        return lotStateDAO.update(lotState);
    }

    /**
     *
     * @param lotState
     * @return
     * @throws com.nepal.auctionhouse.exception.RecordNotFoundException
     * @throws java.sql.SQLException
     *
     */
    public static int deleteLotState(LotState lotState) throws RecordNotFoundException,
            SQLException {
        Connection con = DBConnection.geConnection();

        LotStateDAO lotStateDAO = new LotStateDAOImpl(con, LotStateParams.TABLE_NAME);

        if (!lotStateDAO.isLotStateAvailable(lotState)) {
            throw new RecordNotFoundException();
        }
        return lotStateDAO.remove(lotState);
    }

    /**
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    public static LotState getLotStateById(int id) throws SQLException {
        Connection con = DBConnection.geConnection();
        LotStateDAO lotStateDAO = new LotStateDAOImpl(con, LotStateParams.TABLE_NAME);

        return lotStateDAO.findById(id);
    }

    /**
     *
     * @return @throws java.sql.SQLException
     */
    public static List<LotState> getAllLotState() throws SQLException {
        Connection con = DBConnection.geConnection();
        LotStateDAO lotStateDAO = new LotStateDAOImpl(con, LotStateParams.TABLE_NAME);

        return lotStateDAO.findAll();
    }

    /**
     *
     * @param lotState
     * @return
     * @throws java.sql.SQLException
     *
     */
    public static boolean isLotStateAvailable(LotState lotState) throws SQLException {
        return getLotStateById(lotState.getId()) != null;
    }

}
