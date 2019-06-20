/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cibt.kaampay.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Derslived
 */
public class JDBCTemplate<T> {

    public int update(String sql, Object[] params) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localHost/kaampay";
        String userName = "root";
        String password = "";
        Connection conn = DriverManager.getConnection(url, userName, password);

        PreparedStatement stmt = conn.prepareStatement(sql);
        int counter = 1;
        for (Object param : params) {
            stmt.setObject(counter, param);
            counter++;
        }
        int result = stmt.executeUpdate();
        return result;
    }

    public List<T> query(String sql, RowMapper<T> mapper) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localHost/kaampay";
        String userName = "root";
        String password = "";
        Connection conn = DriverManager.getConnection(url, userName, password);

        PreparedStatement stmt = conn.prepareStatement(sql);
        
        List<T> rows = new ArrayList<>();
        ResultSet rs =stmt.executeQuery();
        while(rs.next()){
            rows.add(mapper.mapRow(rs));
        }
        return rows;
        
    }

}
