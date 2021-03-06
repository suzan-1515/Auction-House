/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.dao.lot;

import com.nepal.auctionhouse.dao.DAO;
import com.nepal.auctionhouse.entity.Lot;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Suzn
 */
public interface LotDAO extends DAO<Lot> {

    boolean isLotAvailable(Lot lot) throws SQLException;

    List<Lot> getAllLotByState(int state) throws SQLException;
    
    boolean isLotAddedByUser(Lot lot) throws SQLException;

}
