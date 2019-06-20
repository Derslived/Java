/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cibt.kaampay.service;

import com.cibt.kaampay.entity.User;


/**
 *
 * @author Derslived
 */
public interface UserService extends GenericService<User> {
    
    User login(String email, String password)throws Exception;

   
}
