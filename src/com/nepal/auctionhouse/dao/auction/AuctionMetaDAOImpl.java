/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.dao.auction;

import com.nepal.auctionhouse.entity.Auction;
import com.nepal.auctionhouse.entity.AuctionMeta;
import com.nepal.auctionhouse.entity.Lot;
import com.nepal.auctionhouse.entity.LotState;
import com.nepal.auctionhouse.entity.LotType;
import com.nepal.auctionhouse.params.AuctionMetaParams;
import com.nepal.auctionhouse.params.AuctionParams;
import com.nepal.auctionhouse.params.LotParams;
import com.nepal.auctionhouse.params.LotStateParams;
import com.nepal.auctionhouse.params.LotTypeParams;
import com.nepal.auctionhouse.util.Logy;
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
public class AuctionMetaDAOImpl implements AuctionMetaDAO {

    private final String tableName;
    private final Connection connection;

    /**
     *
     * @param connection
     * @param tableName
     */
    public AuctionMetaDAOImpl(Connection connection, String tableName) {
        this.tableName = tableName;
        this.connection = connection;
    }

    /**
     *
     * @param auctionMeta
     * @return boolean
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isAuctionMetaAvailable(AuctionMeta auctionMeta) throws SQLException {
        return findById(auctionMeta.getId()) != null;
    }

    /**
     *
     * @param t
     * @return Integer
     * @throws java.sql.SQLException
     */
    @Override
    public int save(AuctionMeta t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO " + tableName + ""
                + " values(?,?)",Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, t.getAuction().getId());
            pst.setInt(2, t.getLot().getId());
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            rs.next();
            id = pst.getGeneratedKeys().getInt(1);

            Logy.d("AuctionMeta inserted successfully");
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
    public int update(AuctionMeta t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement(
                "UPDATE " + tableName + " SET "
                + AuctionMetaParams.AUCTION + "=?,"
                + AuctionMetaParams.LOT + "=? "
                + "WHERE " + AuctionMetaParams.ID + "=?")) {
            pst.setInt(1, t.getAuction().getId());
            pst.setInt(2, t.getLot().getId());
            pst.setInt(3, t.getId());
            id = pst.executeUpdate();

            Logy.d("AuctionMeta updated successfully");
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
    public int remove(AuctionMeta t) throws SQLException {
        int id;
        try (PreparedStatement pst = connection.prepareStatement(
                "DELETE from " + tableName + " WHERE " + AuctionMetaParams.ID + "=?")) {
            pst.setInt(1, t.getId());
            id = pst.executeUpdate();

            Logy.d("AuctionMeta deleted successfully");
        }

        return id;
    }

    /**
     *
     * @param id
     * @return AuctionMeta
     * @throws java.sql.SQLException
     */
    @Override
    public AuctionMeta findById(int id) throws SQLException {
        String query = "SELECT am.m_id,auc.a_id,auc.a_date,auc.a_slot,auc.a_venue,"
                + "lot.l_id,lot.l_description,typ.t_id, typ.t_title,lot.l_reserve_price,"
                + "lot.l_hammer_price,state.s_id,state.s_title FROM `" + tableName + "` am "
                + "INNER JOIN ah_auction auc on am.m_auction = auc.a_id "
                + "INNER JOIN ah_lot lot ON am.m_lot = lot.l_id "
                + "INNER JOIN ah_lot_type typ ON lot.l_type = typ.t_id "
                + "INNER JOIN ah_lot_state state on lot.l_state = state.s_id "
                + "WHERE am.m_id =?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                AuctionMeta auctionMeta = new AuctionMeta();
                auctionMeta.setId(resultSet.getInt(AuctionMetaParams.ID));

                Auction auction = new Auction();
                auction.setId(resultSet.getInt(AuctionParams.ID));
                auction.setDate(resultSet.getDate(AuctionParams.DATE));
                auction.setSlot(resultSet.getString(AuctionParams.SLOT));
                auction.setVenue(resultSet.getString(AuctionParams.VENUE));
                auctionMeta.setAuction(auction);

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

                Logy.d("AuctionMeta fetched successfully");

                return auctionMeta;
            }
        }

        return null;
    }

    /**
     *
     * @return @throws java.sql.SQLException
     */
    @Override
    public List<AuctionMeta> findAll() throws SQLException {
        String query = "SELECT am.m_id,auc.a_id,auc.a_date,auc.a_slot,auc.a_venue,"
                + "lot.l_id,lot.l_description,typ.t_id, typ.t_title,lot.l_reserve_price,"
                + "lot.l_hammer_price,state.s_id,state.s_title FROM `" + tableName + "` am "
                + "INNER JOIN ah_auction auc on am.m_auction = auc.a_id "
                + "INNER JOIN ah_lot lot ON am.m_lot = lot.l_id "
                + "INNER JOIN ah_lot_type typ ON lot.l_type = typ.t_id "
                + "INNER JOIN ah_lot_state state on lot.l_state = state.s_id ";
        List<AuctionMeta> auctionInfoList = new ArrayList<>();

        try (Statement pst = connection.createStatement()) {
            ResultSet resultSet = pst.executeQuery(query);
            while (resultSet.next()) {
                AuctionMeta auctionMeta = new AuctionMeta();
                auctionMeta.setId(resultSet.getInt(AuctionMetaParams.ID));

                Auction auction = new Auction();
                auction.setId(resultSet.getInt(AuctionParams.ID));
                auction.setDate(resultSet.getDate(AuctionParams.DATE));
                auction.setSlot(resultSet.getString(AuctionParams.SLOT));
                auction.setVenue(resultSet.getString(AuctionParams.VENUE));
                auctionMeta.setAuction(auction);

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

                Logy.d("AuctionMeta fetched successfully");

                auctionInfoList.add(auctionMeta);
            }
        }

        return auctionInfoList;
    }

}
