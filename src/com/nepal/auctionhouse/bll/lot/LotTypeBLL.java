/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.bll.lot;

import com.nepal.auctionhouse.dao.lot.LotTypeDAO;
import com.nepal.auctionhouse.dao.lot.LotTypeDAOImpl;
import com.nepal.auctionhouse.database.DBConnection;
import com.nepal.auctionhouse.entity.LotType;
import com.nepal.auctionhouse.exception.DuplicateRecordException;
import com.nepal.auctionhouse.exception.RecordNotFoundException;
import com.nepal.auctionhouse.params.LotTypeParams;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Suzn
 */
public class LotTypeBLL {

    /**
     *
     * @param lotType
     * @return
     * @throws java.sql.SQLException
     * @throws com.nepal.auctionhouse.exception.DuplicateRecordException
     */
    public static int insertLotType(LotType lotType) throws SQLException,
            DuplicateRecordException {
        Connection con = DBConnection.geConnection();

        LotTypeDAO lotTypeDAO = new LotTypeDAOImpl(con, LotTypeParams.TABLE_NAME);
        if (lotTypeDAO.isLotTypeAvailable(lotType)) {
            throw new DuplicateRecordException();
        }
        return lotTypeDAO.save(lotType);
    }

    /**
     *
     * @param lotType
     * @return
     * @throws com.nepal.auctionhouse.exception.RecordNotFoundException
     * @throws java.sql.SQLException
     */
    public static int updateLotType(LotType lotType) throws RecordNotFoundException,
            SQLException {
        Connection con = DBConnection.geConnection();

        LotTypeDAO lotTypeDAO = new LotTypeDAOImpl(con, LotTypeParams.TABLE_NAME);

        if (!lotTypeDAO.isLotTypeAvailable(lotType)) {
            throw new RecordNotFoundException();
        }

        return lotTypeDAO.update(lotType);
    }

    /**
     *
     * @param lotType
     * @return
     * @throws com.nepal.auctionhouse.exception.RecordNotFoundException
     * @throws java.sql.SQLException
     *
     */
    public static int deleteLotType(LotType lotType) throws RecordNotFoundException,
            SQLException {
        Connection con = DBConnection.geConnection();

        LotTypeDAO lotTypeDAO = new LotTypeDAOImpl(con, LotTypeParams.TABLE_NAME);

        if (!lotTypeDAO.isLotTypeAvailable(lotType)) {
            throw new RecordNotFoundException();
        }
        return lotTypeDAO.remove(lotType);
    }

    /**
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    public static LotType getLotTypeById(int id) throws SQLException {
        Connection con = DBConnection.geConnection();
        LotTypeDAO lotTypeDAO = new LotTypeDAOImpl(con, LotTypeParams.TABLE_NAME);

        return lotTypeDAO.findById(id);
    }

    /**
     *
     * @return @throws java.sql.SQLException
     */
    public static List<LotType> getAllLotType() throws SQLException {
        Connection con = DBConnection.geConnection();
        LotTypeDAO lotTypeDAO = new LotTypeDAOImpl(con, LotTypeParams.TABLE_NAME);

        return lotTypeDAO.findAll();
    }

    /**
     *
     * @param lotType
     * @return
     * @throws java.sql.SQLException
     *
     */
    public static boolean isLotTypeAvailable(LotType lotType) throws SQLException {
        return getLotTypeById(lotType.getId()) != null;
    }

}
