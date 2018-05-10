/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.bll.vat;

import com.nepal.auctionhouse.dao.vat.VATInfoDAO;
import com.nepal.auctionhouse.dao.vat.VATInfoDAOImpl;
import com.nepal.auctionhouse.database.DBConnection;
import com.nepal.auctionhouse.entity.VATInfo;
import com.nepal.auctionhouse.exception.DuplicateRecordException;
import com.nepal.auctionhouse.exception.RecordNotFoundException;
import com.nepal.auctionhouse.params.VATInfoParams;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Suzn
 */
public class VATBLL {

    /**
     *
     * @param vatInfo
     * @return
     * @throws java.sql.SQLException
     * @throws com.nepal.auctionhouse.exception.DuplicateRecordException
     */
    public static int insertVATInfo(VATInfo vatInfo) throws SQLException,
            DuplicateRecordException {
        Connection con = DBConnection.geConnection();

        VATInfoDAO vatInfoDAO = new VATInfoDAOImpl(con, VATInfoParams.TABLE_NAME);
        if (vatInfoDAO.isVATInfoAvailable(vatInfo)) {
            throw new DuplicateRecordException();
        }
        return vatInfoDAO.save(vatInfo);
    }

    /**
     *
     * @param vatInfo
     * @return
     * @throws com.nepal.auctionhouse.exception.RecordNotFoundException
     * @throws java.sql.SQLException
     */
    public static int updateVATInfo(VATInfo vatInfo) throws RecordNotFoundException,
            SQLException {
        Connection con = DBConnection.geConnection();

        VATInfoDAO vatInfoDAO = new VATInfoDAOImpl(con, VATInfoParams.TABLE_NAME);

        if (!vatInfoDAO.isVATInfoAvailable(vatInfo)) {
            throw new RecordNotFoundException();
        }

        return vatInfoDAO.update(vatInfo);
    }

    /**
     *
     * @param vatInfo
     * @return
     * @throws com.nepal.auctionhouse.exception.RecordNotFoundException
     * @throws java.sql.SQLException
     *
     */
    public static int deleteVATInfo(VATInfo vatInfo) throws RecordNotFoundException,
            SQLException {
        Connection con = DBConnection.geConnection();

        VATInfoDAO vatInfoDAO = new VATInfoDAOImpl(con, VATInfoParams.TABLE_NAME);

        if (!vatInfoDAO.isVATInfoAvailable(vatInfo)) {
            throw new RecordNotFoundException();
        }
        return vatInfoDAO.remove(vatInfo);
    }

    /**
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    public static VATInfo getVATInfoById(int id) throws SQLException {
        Connection con = DBConnection.geConnection();
        VATInfoDAO vatInfoDAO = new VATInfoDAOImpl(con, VATInfoParams.TABLE_NAME);

        return vatInfoDAO.findById(id);
    }

    /**
     *
     * @return @throws java.sql.SQLException
     */
    public static List<VATInfo> getAllVATInfo() throws SQLException {
        Connection con = DBConnection.geConnection();
        VATInfoDAO vatInfoDAO = new VATInfoDAOImpl(con, VATInfoParams.TABLE_NAME);

        return vatInfoDAO.findAll();
    }

    /**
     *
     * @param vatInfo
     * @return
     * @throws java.sql.SQLException
     *
     */
    public static boolean isVATInfoAvailable(VATInfo vatInfo) throws SQLException {
        return getVATInfoById(vatInfo.getId()) != null;
    }

    public static boolean IsVATApplicable(VATInfo vATInfo) throws SQLException {
        Connection con = DBConnection.geConnection();
        VATInfoDAO vatInfoDAO = new VATInfoDAOImpl(con, VATInfoParams.TABLE_NAME);

        return vatInfoDAO.isVATApplicable(vATInfo);
    }

}
