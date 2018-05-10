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
public final class VATInfo {

    private int id;
    private float percentage;
    private LotType lotType;

    public VATInfo() {
        lotType = new LotType();
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
     * @return the percentage
     */
    public float getPercentage() {
        return percentage;
    }

    /**
     * @param percentage the percentage to set
     */
    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    /**
     * @return the lotType
     */
    public LotType getLotType() {
        return lotType;
    }

    /**
     * @param lotType the lotType to set
     */
    public void setLotType(LotType lotType) {
        this.lotType = lotType;
    }

}
