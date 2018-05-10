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
public final class LotType {

    private int id;
    private String title;

    public LotType() {
    }

    public LotType(int id) {
        this.id = id;
    }
    
    public LotType(int id, String title) {
        this.id = id;
        this.title = title;
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
