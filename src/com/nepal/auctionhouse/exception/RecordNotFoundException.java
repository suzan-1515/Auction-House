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
public class RecordNotFoundException extends Exception {

    public RecordNotFoundException() {
        this("Record does not exist!");
    }

    public RecordNotFoundException(String string) {
        super(string);
    }

    public RecordNotFoundException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

}
