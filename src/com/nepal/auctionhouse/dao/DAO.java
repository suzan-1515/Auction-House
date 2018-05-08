/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Suzn
 * @param <T>
 */
public interface DAO<T> {

    int save(T t) throws SQLException;

    int update(T t) throws SQLException;

    int remove(T t) throws SQLException;

    T findById(int id) throws SQLException;

    List<T> findAll() throws SQLException;
}
