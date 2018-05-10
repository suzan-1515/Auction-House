/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.action;

import com.nepal.auctionhouse.entity.Auction;



/**
 *
 * @author Suzn
 */
public interface AuctionListener {

    void onAuctionDataChanged(Auction a);

    void onAuctionDataRemoved(Auction a);

}
