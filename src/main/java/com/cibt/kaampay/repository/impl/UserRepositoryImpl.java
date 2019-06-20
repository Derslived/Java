/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cibt.kaampay.repository.impl;

import com.cibt.kaampay.core.JDBCTemplate;
import com.cibt.kaampay.core.RowMapper;
import com.cibt.kaampay.entity.User;
import com.cibt.kaampay.repository.UserRepository;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Derslived
 */
public class UserRepositoryImpl implements UserRepository {

    private JDBCTemplate<User> template = new JDBCTemplate();

    @Override
    public void insert(User user) throws Exception {

        String sql = "insert into tbl_users(email,password,status) values(?,?,?)";
        template.update(sql, new Object[]{user.getEmail(), user.getPassword(), user.isStatus()});
    }

    @Override
    public void update(User user) throws Exception {
        String sql = "update tbl_users set email=?,password=?,modfied_date=CURRENT_TIMESTAMP,"
                + "status=? where id=?";
        template.update(sql, new Object[]{user.getEmail(), user.getPassword(), user.isStatus(), user.getId()});
    }

    @Override
    public List<User> findAll() throws Exception {

        String sql = "select * from tbl_users";
        return template.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs) throws Exception {
                return mapUser(rs);
            }
        });

    }

    @Override
    public User findById(int id) throws Exception {

        String sql = "select * from tbl_users where id=?";
        return template.queryForObject(sql, new Object[]{id}, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs) throws Exception {
                return mapUser(rs);
            }
        });
    }

    private User mapUser(ResultSet rs) throws Exception {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setCreatedDate(new Date(rs.getDate("created_date").getTime()));
        if (rs.getDate("modfied_date") != null) {
            user.setModifiedDate(new Date(rs.getDate("modfied_date").getTime()));
        }
        user.setStatus(rs.getBoolean("status"));
        return user;
    }

    @Override
    public User login(String email, String password) throws Exception {

        String sql = "select * from tbl_users where email=? and password=?";
        return template.queryForObject(sql, new Object[]{email, password}, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs) throws Exception {
                return mapUser(rs);
            }
        });

    }

    @Override
    public User findByEmail(String email) throws Exception {
        String sql = "select * from tbl_users where email=?";
        return template.queryForObject(sql, new Object[]{email}, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs) throws Exception {
                return mapUser(rs);
            }
        });
    }

    @Override
    public boolean changeStatus(int id, boolean status) throws Exception {
        String sql = "update tbl_users set modfied_date=CURRENT_TIMESTAMP,"
                + "status=? where id=?";
        int result = template.update(sql, new Object[]{status, id});

        return result > 0;  //return true
    }

}
