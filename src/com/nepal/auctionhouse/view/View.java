/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.view;

import java.util.List;

/**
 *
 * @author Suzn
 * @param <T>
 */
public interface View<T> {

    void onViewActivated();
    
    void loadTableData();

    void fillTableData(List<T> list);
}
