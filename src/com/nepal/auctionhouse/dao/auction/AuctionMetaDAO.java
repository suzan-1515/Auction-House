/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.dao.auction;

import com.nepal.auctionhouse.dao.DAO;
import com.nepal.auctionhouse.entity.AuctionMeta;
import java.sql.SQLException;

/**
 *
 * @author Suzn
 */
public interface AuctionMetaDAO extends DAO<AuctionMeta> {

    boolean isAuctionMetaAvailable(AuctionMeta auction) throws SQLException;

}
