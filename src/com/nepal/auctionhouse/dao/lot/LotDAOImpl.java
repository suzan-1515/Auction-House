/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.dao.lot;

import com.nepal.auctionhouse.entity.Lot;
import com.nepal.auctionhouse.entity.LotState;
import com.nepal.auctionhouse.entity.LotType;
import com.nepal.auctionhouse.params.LotParams;
import com.nepal.auctionhouse.params.LotStateParams;
import com.nepal.auctionhouse.params.LotTypeParams;
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
public class LotDAOImpl implements LotDAO {

    private final String tableName;
    private final Connection connection;

    /**
     *
     * @param connection
     * @param tableName
     */
    public LotDAOImpl(Connection connection, String tableName) {
        this.tableName = tableName;
        this.connection = connection;
    }

    /**
     *
     * @param lot
     * @return boolean
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isLotAvailable(Lot lot) throws SQLException {
        return findById(lot.getId()) != null;
    }

    /**
     *
     * @param t
     * @return Integer
     * @throws java.sql.SQLException
     */
    @Override
    public int save(Lot t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO " + tableName + ""
                + " values(?,?,?,?,?)")) {
            pst.setString(1, t.getDescription());
            pst.setInt(2, t.getType().getId());
            pst.setFloat(3, t.getHammerPrice());
            pst.setFloat(4, t.getReservePrice());
            pst.setInt(5, t.getState().getId());
            id = pst.executeUpdate();

            Logy.d("Lot inserted successfully");
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
    public int update(Lot t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement(
                "UPDATE " + tableName + " SET "
                + LotParams.DESCRIPTION + "=?"
                + LotParams.TYPE + "=?"
                + LotParams.HAMMER_PRICE + "=?"
                + LotParams.RESERVE_PRICE + "=?"
                + LotParams.STATE + "=?")) {
            pst.setString(1, t.getDescription());
            pst.setInt(2, t.getType().getId());
            pst.setFloat(3, t.getHammerPrice());
            pst.setFloat(4, t.getReservePrice());
            pst.setInt(5, t.getState().getId());
            id = pst.executeUpdate();

            Logy.d("Lot updated successfully");
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
    public int remove(Lot t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement(
                "DELETE from " + tableName + " WHERE " + LotParams.ID + "=?")) {
            pst.setInt(1, t.getId());
            id = pst.executeUpdate();

            Logy.d("Lot deleted successfully");
        }

        return id;
    }

    /**
     *
     * @param id
     * @return Lot
     * @throws java.sql.SQLException
     */
    @Override
    public Lot findById(int id) throws SQLException {
        String query = "SELECT ah_lot.l_id,ah_lot.l_description,ah_lot_type.t_id,"
                + "ah_lot_type.t_title,ah_lot.l_reserve_price,ah_lot.l_hammer_price,"
                + "ah_lot_state.s_id,ah_lot_state.s_title FROM `" + tableName + "`"
                + "INNER JOIN ah_lot_type on ah_lot_type.t_id = ah_lot.l_type"
                + "INNER JOIN ah_lot_state on ah_lot_state.s_id = ah_lot.l_state"
                + "WHERE ah_lot.l_id =? LIMIT 1";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Lot lot = new Lot();
                lot.setId(resultSet.getInt(LotParams.ID));
                lot.setDescription(resultSet.getString(LotParams.DESCRIPTION));

                LotType lotType = new LotType();
                lotType.setId(resultSet.getInt(LotTypeParams.ID));
                lotType.setTitle(resultSet.getString(LotTypeParams.TITLE));
                lot.setType(lotType);

                lot.setReservePrice(resultSet.getFloat(LotParams.RESERVE_PRICE));
                lot.setHammerPrice(resultSet.getFloat(LotParams.HAMMER_PRICE));

                LotState lotState = new LotState();
                lotState.setId(resultSet.getInt(LotStateParams.ID));
                lotState.setTitle(resultSet.getString(LotStateParams.TITLE));
                lot.setState(lotState);

                Logy.d("Lot fetched successfully");

                return lot;
            }
        }

        return null;
    }

    /**
     *
     * @return @throws java.sql.SQLException
     */
    @Override
    public List<Lot> findAll() throws SQLException {
        String query = "SELECT ah_lot.l_id,ah_lot.l_description,ah_lot_type.t_id,"
                + "ah_lot_type.t_title,ah_lot.l_reserve_price,ah_lot.l_hammer_price,"
                + "ah_lot_state.s_id,ah_lot_state.s_title FROM `" + tableName + "` "
                + "INNER JOIN ah_lot_type on ah_lot_type.t_id = ah_lot.l_type "
                + "INNER JOIN ah_lot_state on ah_lot_state.s_id = ah_lot.l_state "
                + "ORDER BY DESC ah_lot.l_id";
        List<Lot> auctionInfoList = new ArrayList<>();

        try (Statement pst = connection.createStatement()) {
            ResultSet resultSet = pst.executeQuery(query);
            while (resultSet.next()) {
                Lot lot = new Lot();
                lot.setId(resultSet.getInt(LotParams.ID));
                lot.setDescription(resultSet.getString(LotParams.DESCRIPTION));

                LotType lotType = new LotType();
                lotType.setId(resultSet.getInt(LotTypeParams.ID));
                lotType.setTitle(resultSet.getString(LotTypeParams.TITLE));
                lot.setType(lotType);

                lot.setReservePrice(resultSet.getFloat(LotParams.RESERVE_PRICE));
                lot.setHammerPrice(resultSet.getFloat(LotParams.HAMMER_PRICE));

                LotState lotState = new LotState();
                lotState.setId(resultSet.getInt(LotStateParams.ID));
                lotState.setTitle(resultSet.getString(LotStateParams.TITLE));
                lot.setState(lotState);

                Logy.d("Lot fetched successfully");

                auctionInfoList.add(lot);
            }
        }

        return auctionInfoList;
    }

}
