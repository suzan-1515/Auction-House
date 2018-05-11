/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.dao.lot;

import com.nepal.auctionhouse.dao.DAO;
import com.nepal.auctionhouse.entity.Auction;
import com.nepal.auctionhouse.entity.LotMeta;
import com.nepal.auctionhouse.entity.Lot;
import java.sql.SQLException;

/**
 *
 * @author Suzn
 */
public interface LotMetaDAO extends DAO<LotMeta> {

    boolean isLotMetaAvailable(LotMeta auction) throws SQLException;

    Auction getAuctionAssignedForLot(Lot lot) throws SQLException;

    LotMeta getLotMetaByLotId(int id) throws SQLException;

}
