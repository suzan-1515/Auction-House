/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.validation;

import java.awt.Component;
import java.util.Calendar;

/**
 *
 * @author Suzn
 */
public abstract class BaseValidation {

    protected final Component component;

    public BaseValidation(Component component) {
        this.component = component;
    }

    protected boolean isStringEmptyOrNull(String s) {
        s = s.trim();
        return (s == null || s.isEmpty() || s.matches(""));
    }

    protected boolean isContactValid(String c) {
        c = c.trim();
        return (c.length() < 9 || c.length() > 10);
    }

    protected boolean isDateEmptyOrNull(Calendar date) {
        return (date == null);
    }

    protected boolean isBeforeDate(Calendar expiryDate) {
        return expiryDate.before(java.util.Calendar.getInstance());
    }

}
