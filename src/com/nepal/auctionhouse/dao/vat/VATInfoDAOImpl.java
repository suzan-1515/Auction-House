/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.dao.vat;

import com.nepal.auctionhouse.entity.LotType;
import com.nepal.auctionhouse.entity.VATInfo;
import com.nepal.auctionhouse.params.LotTypeParams;
import com.nepal.auctionhouse.params.VATInfoParams;
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
public class VATInfoDAOImpl implements VATInfoDAO {

    private final String tableName;
    private final Connection connection;

    /**
     *
     * @param connection
     * @param tableName
     */
    public VATInfoDAOImpl(Connection connection, String tableName) {
        this.tableName = tableName;
        this.connection = connection;
    }

    /**
     *
     * @param vatInfo
     * @return boolean
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isVATInfoAvailable(VATInfo vatInfo) throws SQLException {
        return findById(vatInfo.getId()) != null;
    }

    /**
     *
     * @param t
     * @return Integer
     * @throws java.sql.SQLException
     */
    @Override
    public int save(VATInfo t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO " + tableName + ""
                + " values(?,?)")) {
            pst.setFloat(1, t.getPercentage());
            pst.setInt(2, t.getLotType().getId());
            id = pst.executeUpdate();

            Logy.d("VATInfo inserted successfully");
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
    public int update(VATInfo t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement(
                "UPDATE " + tableName + " SET "
                + VATInfoParams.PERCENT + "=?"
                + VATInfoParams.LOT_TYPE + "=?")) {
            pst.setFloat(1, t.getPercentage());
            pst.setInt(2, t.getLotType().getId());
            id = pst.executeUpdate();

            Logy.d("VATInfo updated successfully");
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
    public int remove(VATInfo t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement(
                "DELETE from " + tableName + " WHERE " + VATInfoParams.ID + "=?")) {
            pst.setInt(1, t.getId());
            id = pst.executeUpdate();

            Logy.d("VATInfo deleted successfully");
        }

        return id;
    }

    /**
     *
     * @param id
     * @return VATInfo
     * @throws java.sql.SQLException
     */
    @Override
    public VATInfo findById(int id) throws SQLException {
        String query = "SELECT ah_vat_meta.v_id,ah_vat_meta.v_percent,ah_lot_type.t_id,"
                + "ah_lot_type.t_title FROM `" + tableName + "` "
                + "INNER JOIN ah_lot_type ON ah_lot_type.t_id = ah_vat_meta.v_id "
                + "WHERE ah_vat_meta.v_id =? LIMIT 1";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                VATInfo vatInfo = new VATInfo();
                vatInfo.setId(resultSet.getInt(VATInfoParams.ID));
                vatInfo.setPercentage(resultSet.getFloat(VATInfoParams.PERCENT));

                LotType lotType = new LotType();
                lotType.setId(resultSet.getInt(LotTypeParams.ID));
                lotType.setTitle(resultSet.getString(LotTypeParams.TITLE));

                vatInfo.setLotType(lotType);

                Logy.d("VATInfo fetched successfully");

                return vatInfo;
            }
        }

        return null;
    }

    /**
     *
     * @return @throws java.sql.SQLException
     */
    @Override
    public List<VATInfo> findAll() throws SQLException {
        String query = "SELECT ah_vat_meta.v_id,ah_vat_meta.v_percent,ah_lot_type.t_id,"
                + "ah_lot_type.t_title FROM `" + tableName + "` "
                + "INNER JOIN ah_lot_type ON ah_lot_type.t_id = ah_vat_meta.v_id "
                + "ORDER BY DESC ah_vat_meta.v_id";
        List<VATInfo> auctionInfoList = new ArrayList<>();

        try (Statement pst = connection.createStatement()) {
            ResultSet resultSet = pst.executeQuery(query);
            while (resultSet.next()) {
                VATInfo vatInfo = new VATInfo();
                vatInfo.setId(resultSet.getInt(VATInfoParams.ID));
                vatInfo.setPercentage(resultSet.getFloat(VATInfoParams.PERCENT));

                LotType lotType = new LotType();
                lotType.setId(resultSet.getInt(LotTypeParams.ID));
                lotType.setTitle(resultSet.getString(LotTypeParams.TITLE));

                Logy.d("VATInfo fetched successfully");

                auctionInfoList.add(vatInfo);
            }
        }

        return auctionInfoList;
    }

    @Override
    public boolean isVATApplicable(VATInfo vATInfo) throws SQLException {
        String query = "SELECT * FROM `" + tableName + "` WHERE ah_vat_meta.v_lot_type =? LIMIT 1";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, vATInfo.getLotType().getId());
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Logy.d("VATInfo fetched successfully");

                return true;
            }
        }

        return false;
    }

}
