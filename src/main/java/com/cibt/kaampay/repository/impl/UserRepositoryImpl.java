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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

        String sql = "insert into tbl_users(email,password) values(?,?)";
        template.update(sql, new Object[]{user.getEmail(), user.getPassword()});
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
        });

    }

    @Override
    public User findById(int id) throws Exception {
        User user = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localHost/Kaampay";
        String userName = "root";
        String password = "";

        Connection conn = DriverManager.getConnection(url, userName, password);
        String sql = "select * from tbl_users where id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet result = stmt.executeQuery();
        while (result.next()) {
            user = new User();
            user.setId(result.getInt("id"));
            user.setEmail(result.getString("email"));
            user.setPassword(result.getString("password"));
            user.setCreatedDate(new Date(result.getDate("created_date").getTime()));
            if (result.getDate("modfied_date") != null) {
                user.setModifiedDate(new Date(result.getDate("modfied_date").getTime()));
            }
            user.setStatus(result.getBoolean("status"));

        }
        return user;

    }

    @Override
    public User login(String email, String password) throws Exception {
        User user = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localHost/Kaampay";
        String userName = "root";
        String pwd = "";
        Connection conn = DriverManager.getConnection(url, userName, pwd);
        PreparedStatement stmt = conn.prepareStatement("select * from tbl_users where email=? and password=?");
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet result = stmt.executeQuery();
        while (result.next()) {
            user = new User();
            user.setId(result.getInt("id"));
            user.setEmail(result.getString("email"));
            user.setPassword(result.getString("password"));
            user.setCreatedDate(new Date(result.getDate("created_date").getTime()));
            if (result.getDate("modfied_date") != null) {
                user.setModifiedDate(new Date(result.getDate("modfied_date").getTime()));
            }
            user.setStatus(result.getBoolean("status"));

        }
        return user;
    }

}
