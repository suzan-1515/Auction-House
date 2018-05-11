/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.validation.auction;

import com.nepal.auctionhouse.validation.BaseValidation;
import com.nepal.auctionhouse.widget.Alert;
import com.nepal.auctionhouse.util.Logy;
import com.nepal.auctionhouse.util.Utils;
import java.awt.Component;
import java.util.Calendar;

/**
 *
 * @author Suzn
 */
public class AuctionValidation extends BaseValidation {

    public AuctionValidation(Component component) {
        super(component);
    }

    public boolean isAuctionFormValid(Calendar auctionDate, String venue) {
        Logy.d("Validating auction insert form");
        if (isDateEmptyOrNull(auctionDate)) {
            Logy.d("Auction date not valid");
            Alert.showError(component, "Auction date field cannot be empty.");
            return false;
        } else if (Utils.isBeforeCurrentDate(auctionDate)) {
            Logy.d("Auction date not valid");
            Alert.showError(component, "Auction date must be after current date.");
            return false;
        }

        if (isStringEmptyOrNull(venue)) {
            Logy.d("Venue not valid");
            Alert.showError(component, "Venue field cannot be empty.");
            return false;
        }

        Logy.d("Auction insert form is validated");

        return true;
    }

}
