/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cibt.kaampay;

import com.cibt.kaampay.entity.User;
import com.cibt.kaampay.service.UserService;
import com.cibt.kaampay.service.impl.UserServiceImpl;

/**
 *
 * @author Derslived
 */
public class Main {

    public static void main(String[] args) {

        try {
//            User user = new User(0, "akash@gmail.com", "karki", true); 
            UserService userService = new UserServiceImpl();
            for(User u: userService.findAll()){
              System.out.println(" Email: " + u.getEmail());
            } 
//            User user = userService.findById(1);
//            System.out.println(" User Email: " + user.getEmail());
//            System.out.println(" User password: " + user.getPassword());

            
            userService.login("akash@gmail.com", "karki");
            
            

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
