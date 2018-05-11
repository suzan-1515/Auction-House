/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.dao.vat;

import com.nepal.auctionhouse.dao.DAO;
import com.nepal.auctionhouse.entity.LotType;
import com.nepal.auctionhouse.entity.VATInfo;
import java.sql.SQLException;

/**
 *
 * @author Suzn
 */
public interface VATInfoDAO extends DAO<VATInfo> {

    boolean isVATInfoAvailable(VATInfo vatInfo) throws SQLException;

    boolean isVATApplicable(VATInfo vATInfo) throws SQLException;

    VATInfo findVATByLotType(LotType lotType) throws SQLException;

}
