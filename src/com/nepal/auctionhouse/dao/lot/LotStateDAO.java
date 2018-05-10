/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.dao.lot;

import com.nepal.auctionhouse.dao.DAO;
import com.nepal.auctionhouse.entity.LotState;
import java.sql.SQLException;

/**
 *
 * @author Suzn
 */
public interface LotStateDAO extends DAO<LotState> {

    boolean isLotStateAvailable(LotState lotState) throws SQLException;

}
