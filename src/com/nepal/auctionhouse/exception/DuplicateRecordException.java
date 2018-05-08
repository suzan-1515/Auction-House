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
public class DuplicateRecordException extends Exception {

    public DuplicateRecordException() {
        this("Record already exist!");
    }

    public DuplicateRecordException(String string) {
        super(string);
    }

    public DuplicateRecordException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

}
