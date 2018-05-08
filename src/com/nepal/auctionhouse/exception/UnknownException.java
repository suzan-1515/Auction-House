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
public class UnknownException extends Exception {

    /**
     * Creates a new instance of <code>UnknownException</code> without
     * detail message.
     */
    public UnknownException() {
        this("Something went wrong! Try again later...");
    }

    /**
     * Constructs an instance of <code>UnknownException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UnknownException(String msg) {
        super(msg);
    }

    public UnknownException(Throwable thrwbl) {
        super("Something went wrong! Try again later...", thrwbl);
    }

    public UnknownException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

}
