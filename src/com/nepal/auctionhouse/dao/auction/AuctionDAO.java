/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.dao.auction;

import com.nepal.auctionhouse.dao.DAO;
import com.nepal.auctionhouse.entity.Auction;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Suzn
 */
public interface AuctionDAO extends DAO<Auction> {

    boolean isAuctionAvailable(Auction auction) throws SQLException;
    
    List<Auction> getUpcomingAuction(Date date) throws SQLException;

}
