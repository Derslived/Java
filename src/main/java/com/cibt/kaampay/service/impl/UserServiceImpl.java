/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cibt.kaampay.service.impl;

import com.cibt.kaampay.configuration.AppConfiguration;
import com.cibt.kaampay.entity.User;
import com.cibt.kaampay.entity.UserLog;
import com.cibt.kaampay.helper.MailHelper;
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
    private MailHelper mailer = AppConfiguration.getMailHelper();

    @Override
    public void save(User user) throws Exception {

        if (user.getId() == 0) {

            System.out.println("Insert Mode");

            userRepo.insert(user);
            sendRegisterEmail(user.getEmail());

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

    private void sendRegisterEmail(String email) {
        String url = "http://localhost:8080/KaamPayV1/verifyemail?email=" + email;

        
        String subject = "CIBT::You have registered succesfully please Verify your email";
        String body = "Dear Sir/Madam<br/>Thank You for register"
                + "with us. Please verfiy your email address with following url. "
                + "<a href=\"" + url + "\">Verify </a>";

        mailer.setSubject(subject).setTo(email).setBody(body).send();
    }

    @Override
    public boolean verify(String email) throws Exception {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            userRepo.changeStatus(user.getId(), true);
            String to = email;
            String subject = "CIBT::You Email Address is verfied";
            String body = "Dear Sir/Madam<br/>Thank You for verfying"
                    + "your email address " +  email 
                   +  "please visit our <a href='http://localhost:8080/KaamPayV1'>Website</a>";

            mailer.setSubject(subject).setTo(email).setBody(body).send();
            return true;
        }
        return false;
    }

}
