/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.bll.sale;

import com.nepal.auctionhouse.dao.sale.SaleDAO;
import com.nepal.auctionhouse.dao.sale.SaleDAOImpl;
import com.nepal.auctionhouse.database.DBConnection;
import com.nepal.auctionhouse.entity.Sale;
import com.nepal.auctionhouse.exception.DuplicateRecordException;
import com.nepal.auctionhouse.exception.RecordNotFoundException;
import com.nepal.auctionhouse.params.SaleParams;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Suzn
 */
public class SaleBLL {

    /**
     *
     * @param sale
     * @return
     * @throws java.sql.SQLException
     * @throws com.nepal.auctionhouse.exception.DuplicateRecordException
     */
    public static int insertSale(Sale sale) throws SQLException,
            DuplicateRecordException {
        Connection con = DBConnection.geConnection();

        SaleDAO saleDAO = new SaleDAOImpl(con, SaleParams.TABLE_NAME);
        if (saleDAO.isSaleAvailable(sale)) {
            throw new DuplicateRecordException();
        }
        return saleDAO.save(sale);
    }

    /**
     *
     * @param sale
     * @return
     * @throws com.nepal.auctionhouse.exception.RecordNotFoundException
     * @throws java.sql.SQLException
     */
    public static int updateSale(Sale sale) throws RecordNotFoundException,
            SQLException {
        Connection con = DBConnection.geConnection();

        SaleDAO saleDAO = new SaleDAOImpl(con, SaleParams.TABLE_NAME);

        if (!saleDAO.isSaleAvailable(sale)) {
            throw new RecordNotFoundException();
        }

        return saleDAO.update(sale);
    }

    /**
     *
     * @param sale
     * @return
     * @throws com.nepal.auctionhouse.exception.RecordNotFoundException
     * @throws java.sql.SQLException
     *
     */
    public static int deleteSale(Sale sale) throws RecordNotFoundException,
            SQLException {
        Connection con = DBConnection.geConnection();

        SaleDAO saleDAO = new SaleDAOImpl(con, SaleParams.TABLE_NAME);

        if (!saleDAO.isSaleAvailable(sale)) {
            throw new RecordNotFoundException();
        }
        return saleDAO.remove(sale);
    }

    /**
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    public static Sale getSaleById(int id) throws SQLException {
        Connection con = DBConnection.geConnection();
        SaleDAO saleDAO = new SaleDAOImpl(con, SaleParams.TABLE_NAME);

        return saleDAO.findById(id);
    }

    /**
     *
     * @return @throws java.sql.SQLException
     */
    public static List<Sale> getAllSale() throws SQLException {
        Connection con = DBConnection.geConnection();
        SaleDAO saleDAO = new SaleDAOImpl(con, SaleParams.TABLE_NAME);

        return saleDAO.findAll();
    }

    /**
     *
     * @param sale
     * @return
     * @throws java.sql.SQLException
     *
     */
    public static boolean isSaleAvailable(Sale sale) throws SQLException {
        return getSaleById(sale.getId()) != null;
    }

}
