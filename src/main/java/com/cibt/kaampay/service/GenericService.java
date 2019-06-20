/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cibt.kaampay.service;

import java.util.List;

/**
 *
 * @author Derslived
 */
public interface GenericService<T> {

    void save(T model) throws Exception;

    List<T> findAll() throws Exception;

    T findById(int id) throws Exception;
}
