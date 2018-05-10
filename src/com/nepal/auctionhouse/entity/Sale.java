/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.entity;

import com.nepal.auctionhouse.entity.user.User;
import java.sql.Date;

/**
 *
 * @author Suzn
 */
public final class Sale {

    private int id;
    private Lot lot;
    private float commision;
    private float vatAmount;
    private User user;
    private Date date;

    public Sale() {
        lot = new Lot();
        user = new User();
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

    /**
     * @return the commision
     */
    public float getCommision() {
        return commision;
    }

    /**
     * @param commision the commision to set
     */
    public void setCommision(float commision) {
        this.commision = commision;
    }

    /**
     * @return the vatAmount
     */
    public float getVatAmount() {
        return vatAmount;
    }

    /**
     * @param vatAmount the vatAmount to set
     */
    public void setVatAmount(float vatAmount) {
        this.vatAmount = vatAmount;
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

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

}
