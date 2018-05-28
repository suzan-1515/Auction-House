/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.entity;

import com.nepal.auctionhouse.entity.user.User;

/**
 *
 * @author Suzn
 */
public final class Lot {

    private int id;
    private String description;
    private LotType type;
    private float reservePrice;
    private float hammerPrice;
    private LotState state;
    private User user;
    private Auction auction;

    public Lot() {
        type = new LotType();
        state = new LotState();
        user = new User();
        auction = new Auction();
    }

    public Lot(int id, String description, LotType type, float reservePrice,
            LotState state) {
        this();
        this.id = id;
        this.description = description;
        this.type = type;
        this.reservePrice = reservePrice;
        this.state = state;
    }

    public Lot(int id) {
        this();
        this.id = id;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the type
     */
    public LotType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(LotType type) {
        this.type = type;
    }

    /**
     * @return the reservePrice
     */
    public float getReservePrice() {
        return reservePrice;
    }

    /**
     * @param reservePrice the reservePrice to set
     */
    public void setReservePrice(float reservePrice) {
        this.reservePrice = reservePrice;
    }

    /**
     * @return the hammerPrice
     */
    public float getHammerPrice() {
        return hammerPrice;
    }

    /**
     * @param hammerPrice the hammerPrice to set
     */
    public void setHammerPrice(float hammerPrice) {
        this.hammerPrice = hammerPrice;
    }

    /**
     * @return the state
     */
    public LotState getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(LotState state) {
        this.state = state;
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
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

}
