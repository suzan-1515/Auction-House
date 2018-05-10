/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.dao.sale;

import com.nepal.auctionhouse.dao.DAO;
import com.nepal.auctionhouse.entity.Sale;
import java.sql.SQLException;

/**
 *
 * @author Suzn
 */
public interface SaleDAO extends DAO<Sale> {

    boolean isSaleAvailable(Sale sale) throws SQLException;

}
