/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.validation.lot;

import com.nepal.auctionhouse.util.Logy;
import com.nepal.auctionhouse.validation.BaseValidation;
import com.nepal.auctionhouse.widget.Alert;
import java.awt.Component;

/**
 *
 * @author Suzn
 */
public class LotValidation extends BaseValidation {

    public LotValidation(Component component) {
        super(component);
    }

    public boolean isLotFormValid(String desc, String reservePrice) {
        Logy.d("Validating lot insert form");

        if (isStringEmptyOrNull(desc)) {
            Logy.d("Description not valid");
            Alert.showError(component, "Description field cannot be empty.");
            return false;
        }

        if (isStringEmptyOrNull(reservePrice)) {
            Logy.d("Reserve price not valid");
            Alert.showError(component, "Reserve price field cannot be empty.");
            return false;
        } else {
            try {
                Float.parseFloat(reservePrice);
            } catch (NumberFormatException ex) {
                Logy.d("Reserve price invalid value");
                Alert.showError(component, "Reserve price invalid value.");
                return false;
            }
        }

        Logy.d("Lot insert form is validated");

        return true;
    }

    public boolean isAssignLotFormValid(Object selectedItem, String lotNumber) {
        Logy.d("Validating lot assign form");

        if (isStringEmptyOrNull(lotNumber)) {
            Logy.d("Lot number not valid");
            Alert.showError(component, "Lot number field cannot be empty.");
            return false;
        }

        if (selectedItem == null) {
            Logy.d("Auction not valid");
            Alert.showError(component, "Upcoming auction field cannot be empty.");
            return false;
        }

        Logy.d("Lot assign form is validated");

        return true;
    }

}
