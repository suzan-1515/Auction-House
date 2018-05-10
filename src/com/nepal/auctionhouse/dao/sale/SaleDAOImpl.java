/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.dao.sale;

import com.nepal.auctionhouse.entity.Lot;
import com.nepal.auctionhouse.entity.LotState;
import com.nepal.auctionhouse.entity.LotType;
import com.nepal.auctionhouse.entity.Role;
import com.nepal.auctionhouse.entity.Sale;
import com.nepal.auctionhouse.entity.user.User;
import com.nepal.auctionhouse.params.LotParams;
import com.nepal.auctionhouse.params.LotStateParams;
import com.nepal.auctionhouse.params.LotTypeParams;
import com.nepal.auctionhouse.params.RoleParams;
import com.nepal.auctionhouse.params.SaleParams;
import com.nepal.auctionhouse.params.UserParams;
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
public class SaleDAOImpl implements SaleDAO {

    private final String tableName;
    private final Connection connection;

    /**
     *
     * @param connection
     * @param tableName
     */
    public SaleDAOImpl(Connection connection, String tableName) {
        this.tableName = tableName;
        this.connection = connection;
    }

    /**
     *
     * @param sale
     * @return boolean
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isSaleAvailable(Sale sale) throws SQLException {
        return findById(sale.getId()) != null;
    }

    /**
     *
     * @param t
     * @return Integer
     * @throws java.sql.SQLException
     */
    @Override
    public int save(Sale t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO " + tableName + ""
                + " values(?,?,?,?,?)")) {
            pst.setInt(1, t.getLot().getId());
            pst.setFloat(2, t.getCommision());
            pst.setFloat(3, t.getVatAmount());
            pst.setInt(4, t.getUser().getId());
            pst.setDate(5, t.getDate());
            id = pst.executeUpdate();

            Logy.d("Sale inserted successfully");
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
    public int update(Sale t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement(
                "UPDATE " + tableName + " SET "
                + SaleParams.LOT + "=?"
                + SaleParams.COMMISSION + "=?"
                + SaleParams.VAT_AMOUNT + "=?"
                + SaleParams.USER + "=?")) {
            pst.setInt(1, t.getLot().getId());
            pst.setFloat(2, t.getCommision());
            pst.setFloat(3, t.getVatAmount());
            pst.setInt(5, t.getUser().getId());
            id = pst.executeUpdate();

            Logy.d("Sale updated successfully");
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
    public int remove(Sale t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement(
                "DELETE from " + tableName + " WHERE " + SaleParams.ID + "=?")) {
            pst.setInt(1, t.getId());
            id = pst.executeUpdate();

            Logy.d("Sale deleted successfully");
        }

        return id;
    }

    /**
     *
     * @param id
     * @return Sale
     * @throws java.sql.SQLException
     */
    @Override
    public Sale findById(int id) throws SQLException {
        String query = "SELECT ah_sales.s_id,ah_lot.l_id,ah_lot.l_description,ah_lot_type.t_id,ah_lot_type.t_title,ah_lot.l_reserve_price,\n"
                + "ah_lot.l_hammer_price,ah_lot_state.s_id,ah_lot_state.s_title,ah_user.u_id,ah_user.u_name,ah_user.u_username,\n"
                + "ah_user.u_password,ah_role.r_id,ah_role.r_title FROM `" + tableName + "` \n"
                + "INNER JOIN ah_lot ON ah_lot.l_id = ah_sales.s_lot\n"
                + "INNER JOIN ah_lot_type ON ah_lot_type.t_id = ah_lot.l_type\n"
                + "INNER JOIN ah_lot_state ON ah_lot_state.s_id = ah_lot.l_state\n"
                + "INNER JOIN ah_user ON ah_user.u_id = ah_sales.s_user\n"
                + "INNER JOIN ah_role On ah_role.r_id = ah_user.u_role\n"
                + "WHERE ah_sales.s_id =? LIMIT 1";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Sale sale = new Sale();
                sale.setId(resultSet.getInt(SaleParams.ID));

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

                sale.setLot(lot);
                sale.setCommision(resultSet.getFloat(SaleParams.COMMISSION));
                sale.setVatAmount(resultSet.getFloat(SaleParams.VAT_AMOUNT));

                User user = new User();
                user.setId(resultSet.getInt(UserParams.ID));
                user.setName(resultSet.getString(UserParams.NAME));
                user.setUsername(resultSet.getString(UserParams.USERNAME));
                user.setPassword(resultSet.getString(UserParams.PASSWORD));

                Role role = new Role();
                role.setId(resultSet.getInt(RoleParams.ID));
                role.setTitle(resultSet.getString(RoleParams.TITLE));

                user.setRole(role);
                sale.setUser(user);

                Logy.d("Sale fetched successfully");

                return sale;
            }
        }

        return null;
    }

    /**
     *
     * @return @throws java.sql.SQLException
     */
    @Override
    public List<Sale> findAll() throws SQLException {
        String query = "SELECT ah_sales.s_id,ah_lot.l_id,ah_lot.l_description,ah_lot_type.t_id,ah_lot_type.t_title,ah_lot.l_reserve_price,\n"
                + "ah_lot.l_hammer_price,ah_lot_state.s_id,ah_lot_state.s_title,ah_user.u_id,ah_user.u_name,ah_user.u_username,\n"
                + "ah_user.u_password,ah_role.r_id,ah_role.r_title FROM `" + tableName + "` \n"
                + "INNER JOIN ah_lot ON ah_lot.l_id = ah_sales.s_lot\n"
                + "INNER JOIN ah_lot_type ON ah_lot_type.t_id = ah_lot.l_type\n"
                + "INNER JOIN ah_lot_state ON ah_lot_state.s_id = ah_lot.l_state\n"
                + "INNER JOIN ah_user ON ah_user.u_id = ah_sales.s_user\n"
                + "INNER JOIN ah_role On ah_role.r_id = ah_user.u_role\n"
                + "ORDER BY DESC ah_sale.l_id";
        List<Sale> auctionInfoList = new ArrayList<>();

        try (Statement pst = connection.createStatement()) {
            ResultSet resultSet = pst.executeQuery(query);
            while (resultSet.next()) {
                Sale sale = new Sale();
                sale.setId(resultSet.getInt(SaleParams.ID));

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

                sale.setLot(lot);
                sale.setCommision(resultSet.getFloat(SaleParams.COMMISSION));
                sale.setVatAmount(resultSet.getFloat(SaleParams.VAT_AMOUNT));

                User user = new User();
                user.setId(resultSet.getInt(UserParams.ID));
                user.setName(resultSet.getString(UserParams.NAME));
                user.setUsername(resultSet.getString(UserParams.USERNAME));
                user.setPassword(resultSet.getString(UserParams.PASSWORD));

                Role role = new Role();
                role.setId(resultSet.getInt(RoleParams.ID));
                role.setTitle(resultSet.getString(RoleParams.TITLE));

                user.setRole(role);
                sale.setUser(user);

                Logy.d("Sale fetched successfully");

                auctionInfoList.add(sale);
            }
        }

        return auctionInfoList;
    }

}
