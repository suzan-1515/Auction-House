/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.params;

/**
 *
 * @author Suzn
 */
public class LotParams {

    public static final String TABLE_NAME = "ah_lot";

    public static final String ID = "l_id";
    public static final String DESCRIPTION = "l_description";
    public static final String TYPE = "l_type";
    public static final String RESERVE_PRICE = "l_reserve_price";
    public static final String HAMMER_PRICE = "l_hammer_price";
    public static final String STATE = "l_state";
    public static final String USER = "l_user";

    public static class LotAllTableHeader {

        public static final String ID = "Lot No.";
        public static final String DESCRIPTION = "Description";
        public static final String TYPE = "Type";
        public static final String RESERVE_PRICE = "Reserve Price";
        public static final String STATE = "State";
    }

    public static class LotUnassignedTableHeader {

        public static final String ID = "Lot No.";
        public static final String DESCRIPTION = "Description";
        public static final String TYPE = "Type";
        public static final String RESERVE_PRICE = "Reserve Price";
    }

    public static class LotAssignedTableHeader {

        public static final String ID = "ID";
        public static final String LOT_NUMBER = "Lot No.";
        public static final String DESCRIPTION = "Description";
        public static final String TYPE = "Type";
        public static final String RESERVE_PRICE = "Reserve Price";
        public static final String AUCTION = "Auction No.";
    }

    public static class LotSoldTableHeader {

        public static final String ID = "Lot No.";
        public static final String DESCRIPTION = "Description";
        public static final String TYPE = "Type";
        public static final String RESERVE_PRICE = "Reserve Price";
        public static final String HAMMER_PRICE = "Hammer Price";
        public static final String COMMISSION = "Comm.";
        public static final String VAT = "VAT";
        public static final String CUSTOMER_PAYMENT = "Customer Payment";
    }

}
