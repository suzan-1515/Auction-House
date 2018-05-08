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
public class DatabaseException extends Exception {

    /**
     * Creates a new instance of <code>DatabaseConnectionException</code>
     * without detail message.
     */
    public DatabaseException() {
        super("Error connecting to database. Make sure database is up and running!");
    }

    /**
     * Constructs an instance of <code>DatabaseConnectionException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public DatabaseException(String msg) {
        super(msg);
    }

    /**
     *
     * @param ex
     */
    public DatabaseException(Throwable ex) {
        super("Error connecting to database. Make sure database is up and running!", ex);
    }

    /**
     *
     * @param msg
     * @param ex
     */
    public DatabaseException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
