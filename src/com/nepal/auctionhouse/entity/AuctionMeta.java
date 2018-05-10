/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.entity;

/**
 *
 * @author Suzn
 */
public final class AuctionMeta {

    private int id;
    private Auction auction;
    private Lot lot;

    public AuctionMeta() {
        auction = new Auction();
        lot = new Lot();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the auction
     */
    public Auction getAuction() {
        return auction;
    }

    /**
     * @param auction the auction to set
     */
    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    /**
     * @return the lot
     */
    public Lot getLot() {
        return lot;
    }

    /**
     * @param lot the lot to set
     */
    public void setLot(Lot lot) {
        this.lot = lot;
    }

}
