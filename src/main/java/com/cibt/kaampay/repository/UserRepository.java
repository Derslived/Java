/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cibt.kaampay.repository;

import com.cibt.kaampay.entity.User;


/**
 *
 * @author Derslived
 */
public interface UserRepository extends CRUD<User> {

    User login(String email, String password)throws Exception;
    User findByEmail(String email)throws Exception;
    boolean changeStatus(int id,boolean status)throws Exception;

}
