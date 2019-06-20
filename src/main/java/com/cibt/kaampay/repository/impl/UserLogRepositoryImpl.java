/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cibt.kaampay.repository.impl;

import com.cibt.kaampay.core.JDBCTemplate;

import com.cibt.kaampay.entity.UserLog;
import com.cibt.kaampay.repository.UserLogRepository;
import java.util.List;

/**
 *
 * @author Derslived
 */
public class UserLogRepositoryImpl implements UserLogRepository{
     private JDBCTemplate<UserLog> template = new JDBCTemplate();

    @Override
    public void insert(UserLog model) throws Exception {
        String sql = "insert into tbl_user_logs(user_id) VALUES (?)";
        template.update(sql, new Object[]{model.getUser().getId()});
   }

    @Override
    public void update(UserLog model) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserLog> findAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserLog findById(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserLog> findByUserId(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
