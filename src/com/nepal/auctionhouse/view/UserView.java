/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.view;

/**
 *
 * @author Suzn
 * @param <T>
 */
public interface UserView<T> extends View<T> {

    void onUserRowDataAdd(T u);

    void onUserDataRemove(T u);

}
