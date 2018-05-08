/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.exception;

/**
 *
 * @author Suzn
 */
public class UnAvailableUsernameException extends Exception {

    public UnAvailableUsernameException() {
        this("Username already exist!");
    }

    public UnAvailableUsernameException(String string) {
        super(string);
    }

    public UnAvailableUsernameException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

}
