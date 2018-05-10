/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Suzn
 */
public class Logy {

    public static String TAG = "Auction House";

    public static void init(String tag) {
        TAG = tag;
    }

    public static void d(String message) {
        System.out.println(String.format("Log-%s: %s", TAG, message));
    }

    public static void e(Throwable t) {
        Logger.getLogger(TAG).log(Level.SEVERE, null, t);
    }

}
