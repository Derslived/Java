/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cibt.kaampay.service.impl;

import com.cibt.kaampay.entity.User;
import com.cibt.kaampay.entity.UserLog;
import com.cibt.kaampay.repository.UserLogRepository;
import com.cibt.kaampay.repository.UserRepository;
import com.cibt.kaampay.repository.impl.UserLogRepositoryImpl;
import com.cibt.kaampay.repository.impl.UserRepositoryImpl;
import com.cibt.kaampay.service.UserService;
import java.util.List;

/**
 *
 * @author Derslived
 */
public class UserServiceImpl implements UserService {

    private UserRepository userRepo = new UserRepositoryImpl();
    private UserLogRepository userLogRepo = new UserLogRepositoryImpl();

    @Override
    public void save(User user) throws Exception {

        if (user.getId() == 0) {

            System.out.println("Insert Mode");
            userRepo.insert(user);

        } else {

            System.out.println("Update Mode");
            userRepo.update(user);
        }

    }

    @Override
    public List<User> findAll() throws Exception {

        return userRepo.findAll();
    }

    @Override
    public User findById(int id) throws Exception {
        return userRepo.findById(id);
    }

    @Override
    public User login(String email, String password) throws Exception {
        User user = userRepo.login(email, password);
        if (user != null) {
            userLogRepo.insert(new UserLog(user.getId(), user));
        }
        return user;
    }

}
